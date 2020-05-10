using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Hackathon
{
    public partial class crops : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click1(object sender, EventArgs e)
        {
            Response.Redirect("crops.aspx");
        }

        protected void Button2_Click1(object sender, EventArgs e)
        {
            Session["crop"] = "rice";
            Response.Redirect("request.aspx");

        }

        protected void Button3_Click(object sender, EventArgs e)
        {
            Session["crop"] = "wheat";
            Response.Redirect("request.aspx");
        }

        protected void Button4_Click(object sender, EventArgs e)
        {
            Session["crop"] = "potato";
            Response.Redirect("request.aspx");
        }

        protected void Button6_Click(object sender, EventArgs e)
        {
            Session["crop"] = "tomato";
            Response.Redirect("request.aspx");
        }

        protected void Button5_Click(object sender, EventArgs e)
        {
            Session["crop"] = "pulse";
            Response.Redirect("request.aspx");
        }
    }
}