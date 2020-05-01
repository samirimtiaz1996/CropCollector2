package com.samsofts.cropcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    private CountryCodePicker ccp;
    private EditText phoneText;
    private EditText codeText;
    private Button continueAndNextBtn;
    private String checker="",phoneNumber="",mVerificationId;
    private RelativeLayout relativeLayout;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken resendingToken;
    private ProgressDialog progressDialog;

    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET,Manifest.permission.ACCESS_FINE_LOCATION, "android.permission.ACCESS_FINE_LOCATION"}, PackageManager.PERMISSION_GRANTED);


        mAuth=FirebaseAuth.getInstance ();
        progressDialog=new ProgressDialog (this);
        phoneText=findViewById (R.id.phoneText);
        codeText=findViewById (R.id.codeText);
        continueAndNextBtn=findViewById (R.id.continueNextButton);
        relativeLayout=findViewById (R.id.phoneAuth);
        ccp=(CountryCodePicker) findViewById (R.id.ccp);
        ccp.registerCarrierNumberEditText (phoneText);


        setupMediaPlayer ();
        mediaPlayer.start();
        continueAndNextBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (continueAndNextBtn.getText ().equals ("Submit") || checker.equals ("Code Sent")){
                    String verificationCode=codeText.getText ().toString ();
                    if (verificationCode.equals ("")){
                        Toast.makeText (MainActivity.this,"Please write verification code",Toast.LENGTH_LONG).show ();
                    }
                    else {
                        progressDialog.setTitle ("Code Verification");
                        progressDialog.setMessage ("Please wait until code is received");
                        progressDialog.setCanceledOnTouchOutside (false);
                        progressDialog.show ();

                        PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential (mVerificationId,verificationCode);
                        signInWithPhoneAuthCredential (phoneAuthCredential);
                    }
                }
                else{
                    phoneNumber=ccp.getFullNumberWithPlus ();
                    if (!phoneNumber.equals ("")){
                        progressDialog.setTitle ("Phone Number Verification");
                        progressDialog.setMessage ("Please wait until number is verified");
                        progressDialog.setCanceledOnTouchOutside (false);
                        progressDialog.show ();
                        PhoneAuthProvider.getInstance().verifyPhoneNumber (
                                phoneNumber,        // Phone number to verify
                                60,                 // Timeout duration
                                TimeUnit.SECONDS,   // Unit of timeout
                                MainActivity.this,               // Activity (for callback binding)
                                mCallbacks);        // OnVerificationStateChangedCallbacks

                    }
                    else{
                        Toast.makeText (MainActivity.this,"Please Give Us A Phone Number",Toast.LENGTH_SHORT).show ();
                    }
                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential (phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText (MainActivity.this,"Invalid Phone Number . Error no :"+e.getMessage (),Toast.LENGTH_LONG).show ();
                relativeLayout.setVisibility (View.VISIBLE);

                continueAndNextBtn.setText ("Continue");
                codeText.setVisibility (View.GONE);
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent (s, forceResendingToken);

                mVerificationId=s;
                resendingToken=forceResendingToken;
                relativeLayout.setVisibility (View.GONE);
                checker="Code Sent";
                continueAndNextBtn.setText ("Submit");
                codeText.setVisibility (View.VISIBLE);
                progressDialog.dismiss ();
                sendUserToProfile ();
                Toast.makeText (MainActivity.this,"Verification Code Has Been Sent",Toast.LENGTH_LONG).show ();
            }
        };
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss ();
                            Toast.makeText (MainActivity.this,"You Are Logged in",Toast.LENGTH_LONG);
                            sendUserToProfile ();
                        } else {
                            progressDialog.dismiss ();
                            String error=task.getException ().toString ();
                            Toast.makeText (MainActivity.this,"Error: "+error,Toast.LENGTH_LONG);
                        }
                    }
                });
    }
    private void sendUserToProfile(){
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;

        startActivity (new Intent (MainActivity.this,InfoActivity.class));
        finish ();
    }
    private void setupMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.login_request);
    }
}
