<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="contact.aspx.cs" Inherits="Hackathon.contact" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
    <style type="text/css">
        .auto-style1 {
            font-size: large;
        }
        .auto-style2 {
            color: #000066;
            font-size: 30px;
        }
        </style>
</head>

<body>
    <form id="form1" runat="server">

    <div id="page">
		
        <div id="header">
        	<div id="headerSubText">&nbsp;&nbsp;&nbsp;&nbsp; <span class="auto-style2">ড্রাইভিং লাইসেন্স ও মোটরযান ফিটনেস নবায়ন </span> </div>
            <div id="headerSubText" class="auto-style1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; বাংলাদেশ রোড ট্রান্সপোর্ট অথরিটি (বি আর টি এ)</div>
        
        </div>
        <div id="bar">
        	<a href="home.aspx">Home</a>
            <a href="makequestion.aspx">প্রশ্ন তৈরি করুন</a>
            <a href="fitness.aspx">ফিটনেস</a>
            <asp:Button ID="Button1" runat="server" Text="সকল রিকুয়েস্ট দেখুন" BackColor="#CC9900" OnClick="Button1_Click1" ForeColor="#FFFFCC" Height="25px" style="font-size: small" Width="136px" />
            <a href="contact.aspx">যোগাযোগ</a>
            <a href="login.aspx">লগ ইন</a>
      </div>
  
        <div class="contentText">আপনাদের সার্বক্ষণিক সহযোগিতায় আমরা বদ্ধপরিকর।</div> 
        <div class="contentText">আমাদের সিস্টেম সম্পর্কিত যে কোন তথ্যের জন্য যোগাযোগ করুনঃ</div>  
        <div class="contentText">মোবাইলঃ ০১৭৮৩১৪৫৫৯৪,০১৫২১৪২৪১৫৪</div>  
        <div class="contentText">ইমেইলঃ sen1607066@stud.kuet.ac.bd,imtiaz1607073@stud.kuet.ac.bd</div> 
</div>
        <div id="footer">Development by Team OSS</div>
        </form>
</body>
</html>
