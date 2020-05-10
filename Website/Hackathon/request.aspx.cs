using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using FireSharp.Config;
using FireSharp.Interfaces;
using FireSharp.Response;
using System.Data;

namespace Hackathon
{
    public partial class request : System.Web.UI.Page
    {
        DataTable dt = new DataTable();
        DataTable dt2 = new DataTable();
       
        IFirebaseConfig config = new FirebaseConfig
        {
            AuthSecret = "J36uz58TIaBJvimhrI1mC7yzXhPTdJ2ix0eWuhCl",
            BasePath = "https://cropcollector-be7e7.firebaseio.com/"
        };
        IFirebaseClient client;
        protected async void Page_Load(object sender, EventArgs e)
        {
            client = new FireSharp.FirebaseClient(config);
            if (client != null)
            {
                //Response.Write("Connection established");
            }
            dt.Columns.Add("Type");
            dt.Columns.Add("Number");
            dt.Columns.Add("Time_of_insertion");
            dt.Columns.Add("Measure");
            dt.Columns.Add("Address");
            dt2.Columns.Add("Latiude");
            dt2.Columns.Add("Longitude");
           // dt.Columns.Add("View on map");
            string type =Session["crop"].ToString();
            for(int i=0; ;i++)
            {
                string nn=i.ToString();
                FirebaseResponse res = await client.GetTaskAsync("Crops/"+type+"/"+nn);
                try
                {
                    Data obj = res.ResultAs<Data>();
                    System.Diagnostics.Debug.WriteLine(i);
                    DataRow r = dt.NewRow();
                    DataRow r2 = dt2.NewRow();
                    r["Type"] = type;
                    r2["Latiude"] = obj.Latiude;
                    r2["Longitude"] = obj.Longitude;
                    r["Address"] = obj.Town;
                    r["Measure"] = obj.measure;
                    r["Number"] = obj.number;
                    r["Time_of_insertion"] = obj.time_of_insertion;
                    dt.Rows.Add(r);
                    dt2.Rows.Add(r2);
                }
                catch (Exception z)
                {
                    break ;
                }
                
            }
            //this.GridView1.Visible = true;
            GridView1.DataSource = dt;
            GridView1.DataBind();
            foreach (GridViewRow gvr in GridView1.Rows)
            {
                ((HyperLink)gvr.Cells[0].Controls[0]).NavigateUrl =
                "https://www.google.com/maps?q=" + dt2.Rows[gvr.RowIndex]["Latiude"] + "," + dt2.Rows[gvr.RowIndex]["Longitude"] + "";
            }
        }

        protected void Button1_Click1(object sender, EventArgs e)
        {
            Response.Redirect("crops.aspx");
        }
    }
}