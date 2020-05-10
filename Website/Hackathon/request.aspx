<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="request.aspx.cs" Inherits="Hackathon.request" Async="true"%>

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
    <form id="form2" runat="server">

    <div id="page">
		
        <div id="header">
        	<div id="headerSubText">&nbsp;&nbsp;&nbsp;&nbsp; <span class="auto-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;কৃষক বাচাও অ্যাপ</span> </div>
            <div id="headerSubText" class="auto-style1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;ACT COVID 19</div>
        
        </div>
        <div id="bar">
        	<a href="home.aspx">Home</a>
            <a href="farmer.aspx">কৃষক</a>
            <asp:Button ID="Button1" runat="server" Text="সকল রিকুয়েস্ট দেখুন" BackColor="#CC9900" OnClick="Button1_Click1" ForeColor="#FFFFCC" Height="25px" style="font-size: small" Width="136px" />
            <a href="contact.aspx">যোগাযোগ</a>
            <a href="login.aspx">লগ ইন</a>
      </div>
        <div>
    
        <asp:GridView ID="GridView1" runat="server" BackColor="White" BorderColor="#669999" BorderWidth="2px" CellPadding="4" Height="204px" Width="665px" BorderStyle="Solid" Font-Bold="False" Font-Underline="False">
            <Columns>
                <asp:HyperLinkField HeaderText="View on map" Target="_blank" Text="Click to see on map ">
                <ControlStyle Font-Bold="True" Font-Underline="True" ForeColor="#660066" />
                <HeaderStyle Font-Bold="True" ForeColor="White" />
                </asp:HyperLinkField>
            </Columns>
            <EditRowStyle BorderStyle="Solid" BorderWidth="1px" />
            <FooterStyle BackColor="White" ForeColor="#000066" />
            <HeaderStyle BackColor="#669900" Font-Bold="True" ForeColor="White" BorderColor="#669999" BorderStyle="Solid" BorderWidth="1px" Font-Size="Medium" HorizontalAlign="Center" />
            <PagerStyle ForeColor="#000066" HorizontalAlign="Left" BackColor="White" />
            <RowStyle BorderColor="#669999" BorderStyle="Solid" Font-Bold="True" ForeColor="#003300" HorizontalAlign="Center" />
            <SelectedRowStyle BackColor="#669999" ForeColor="White" Font-Bold="True" />
            <SortedAscendingCellStyle BackColor="#F1F1F1" />
            <SortedAscendingHeaderStyle BackColor="#007DBB" />
            <SortedDescendingCellStyle BackColor="#CAC9C9" />
            <SortedDescendingHeaderStyle BackColor="#00547E" />

        </asp:GridView>
    
    </div>
        <asp:SqlDataSource ID="SqlDataSource2" runat="server"></asp:SqlDataSource>
    
</div>
        <div id="footer">Development by Team CALL FOR NATION </div>
        </form>
</body>
</html>
