using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Hackathon
{
    public partial class login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            TextBox6.Visible = false;
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            if (TextBox4.Text == "hackathon1996@gmail.com" && TextBox5.Text == "H@ckathon1234")
            {
                Response.Redirect("home.aspx");
            }
            else
            {
                TextBox6.Visible = true;
            }
        }

        protected void Button3_Click(object sender, EventArgs e)
        {
            Response.Redirect("login.aspx");
        }

        protected void Button1_Click1(object sender, EventArgs e)
        {
            Response.Redirect("login.aspx");
        }
    }
}