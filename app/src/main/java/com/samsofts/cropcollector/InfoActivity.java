package com.samsofts.cropcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class InfoActivity extends AppCompatActivity {

    private Button button,savebtn;
    private DatabaseReference cropReference;
    Address currentAddress;
    FusedLocationProviderClient fusedLocationProviderClient;
    HashMap<String, Object> info;
    private Intent intent;
    Location location;
    private MediaPlayer mediaPlayer,mediaPlayerBeforeSave,getMediaPlayerEndMessage,mediaPlayerUnableToUnderstand;
    private SpeechRecognizer speechRecognizer;
    private TextToSpeech textToSpeech;
    private TextView textView;
    long serial,tempSerial,i;
    String tempStringCrop,stringCrop,stringMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_info);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        savebtn=(Button)findViewById (R.id.savebtn);
        final HashMap<String, Object> info = new HashMap();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        button.setEnabled(false);
        serial=0;i=0;
        tempStringCrop="null";
        stringCrop = "";
        stringMeasure = "";

        savebtn.setVisibility (View.INVISIBLE);
        button.setVisibility (View.VISIBLE);
        getLocation();
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "bn-BD");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        setupMediaPlayer();
        mediaPlayer.start();

        speechRecognizer=SpeechRecognizer.createSpeechRecognizer (this);
        speechRecognizer.setRecognitionListener (new RecognitionListener () {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList (SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    List<String> keywords = Arrays.asList (((String) matches.get (0)).split (" "));
                    if (keywords.contains ("টমেটো")) {
                        stringCrop = "tomato";
                    }
                    if (keywords.contains ("রসুন")) {
                        stringCrop = "garlic";
                    }
                    if (keywords.contains ("পেঁয়াজ")) {
                        stringCrop = "onion";
                    }
                    if (keywords.contains ("ধান")) {
                        stringCrop = "rice";
                    }
                    if (keywords.contains ("5")) {
                        stringMeasure = "৫ কেজির বেশি";
                    }
                    if (keywords.contains ("10")) {
                        stringMeasure = "১০ কেজির বেশি";
                    }
                    if (keywords.contains ("20")) {
                        stringMeasure = "২০ কেজির বেশি ";
                    }
                    if (keywords.contains ("30")) {
                        stringMeasure = "৩০ কেজির বেশি";
                    }

                    if (stringCrop !="" && stringMeasure != "") {
                        info.put ("measure", stringMeasure);
                        info.put ("Longitude", currentAddress.getLongitude ());
                        info.put ("Latiude", currentAddress.getLatitude ());
                        info.put ("Town", InfoActivity.this.currentAddress.getAddressLine (0));
                        info.put ("number",FirebaseAuth.getInstance ().getCurrentUser ().getPhoneNumber ());
                        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date ());
                        info.put("time_of_insertion",currentDateandTime );
                        childCount (stringCrop);
//                            FirebaseDatabase.getInstance ().getReference ().child ("Crops").child (stringCrop).child (String.valueOf (serial)).updateChildren (info);
//                            FirebaseDatabase.getInstance ().getReference ().child ("Crops").child (stringCrop).child (String.valueOf (tempSerial)).updateChildren (info);
                        textView.setText (matches.get (0));
                        mediaPlayerBeforeSave.start ();
                        savebtn.setVisibility (View.VISIBLE);

                    }
                    else{
                        mediaPlayerUnableToUnderstand.start ();
                    }

                }
            }
            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        savebtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance ().getReference ().child ("Crops").child (stringCrop).child (String.valueOf (serial)).updateChildren (info);
                savebtn.setVisibility (View.INVISIBLE);
                button.setVisibility (View.VISIBLE);
                getMediaPlayerEndMessage.start ();
            }
        });
        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                speechRecognizer.startListening (intent);
                savebtn.setVisibility (View.INVISIBLE);
            }
        });
    }


    private void setupMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.user_data_request);
        mediaPlayerBeforeSave = new MediaPlayer();
        mediaPlayerBeforeSave = MediaPlayer.create(getApplicationContext(), R.raw.user_save_request);
        getMediaPlayerEndMessage = new MediaPlayer();
        getMediaPlayerEndMessage = MediaPlayer.create(getApplicationContext(), R.raw.end_message);
        mediaPlayerUnableToUnderstand = new MediaPlayer();
        mediaPlayerUnableToUnderstand = MediaPlayer.create(getApplicationContext(), R.raw.unable);
    }

    public void childCount(String category){
        FirebaseDatabase.getInstance ().getReference ().child ("Crops").child(category).addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                serial=dataSnapshot.getChildrenCount ();
                Toast.makeText (InfoActivity.this,String.valueOf (dataSnapshot.getChildrenCount ()),Toast.LENGTH_LONG).show ();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location> () {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                //initialize location
                Location location = task.getResult();
                if(location != null){

                    try {
                        //initialize geoCoder
                        Geocoder geocoder = new Geocoder (InfoActivity.this, Locale.getDefault());
                        //initialize address list
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                        currentAddress = addresses.get(0);
                        button.setEnabled(true);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("error",e.getMessage());
                    }
                }
            }
        });





    }
}
