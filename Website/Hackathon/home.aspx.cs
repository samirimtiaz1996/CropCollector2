using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using FireSharp.Config;
using FireSharp.Interfaces;
using FireSharp.Response;

namespace Hackathon
{
    public partial class home : System.Web.UI.Page
    {
        IFirebaseConfig config = new FirebaseConfig
        {
            AuthSecret = "Jtit8bF6dZqDFmoWSi7K8wycjEyKzEo7vq8muQf2",
            BasePath = "https://autolicense-1d6f2.firebaseio.com/"
        };
        IFirebaseClient client;
        protected void Page_Load(object sender, EventArgs e)
        {
            client = new FireSharp.FirebaseClient(config);
            if (client != null)
            {
                //Response.Write("Connection established");
            }
        }

        protected void Button1_Click1(object sender, EventArgs e)
        {
           Response.Redirect("crops.aspx");
        }
    }
}