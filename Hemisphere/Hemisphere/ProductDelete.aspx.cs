using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

namespace Hemisphere
{
    public partial class ProductionDelete : System.Web.UI.Page
    {
        SqlConnection connection = null;
        SqlCommand command = null;
        SqlDataReader reader = null;
        
        protected void Page_Load(object sender, EventArgs e)
        {
            int ProductID = Int32.Parse(Request.QueryString["ProdID"]);
           var db = new GroupDBDataContext();
            var prodId = (from u in db.PRODUCTs
                         where u.PROD_ID.Equals(Request.QueryString["ProdID"])
                         select u).FirstOrDefault();
          //  PRODUCT pU=db.PRODUCTs.Single(u=>u.PROD_ID.Equals(Request.QueryString["ProdID"]))
         /*    foreach(var d in prodId)
            {
                db.PRODUCTs.DeleteOnSubmit(d);
            }
            try
            {
                db.SubmitChanges();
                Response.Redirect("ProductDelete.aspx");

            }catch(Exception I)
            {
                I.GetBaseException();
            }*/
            
         /*  connection = new SqlConnection();
            connection.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\groupDB.mdf;Integrated Security=True";
            string commandString = "SELECT * FROM [PRODUCT] WHERE PROD_ID=@pID;";
            command = new SqlCommand(commandString, connection);
            command.CommandType = CommandType.Text;

            command.Parameters.AddWithValue("@pID", ProductID);

            command.Connection = connection;
            command.Connection.Open();

            reader = command.ExecuteReader();*/
          //  if (reader.HasRows)
            {
                 if(prodId!=null)
                {
                    txtProductName.Text = prodId.PROD_NAME;
                    txtProductPrice.Text =Convert.ToString(prodId.PROD_PRICE);
                    txtCategory.Text = prodId.PROD_CATEGORY;
                    txtDescription.Text = prodId.PROD_DESCRIPTION;
                    txtProductQuantity.Text =prodId.PROD_QUANTITY_AVAILABLE.ToString();
                    txtImage.Text = prodId.PROD_IMAGE_PATH;
                }
            }
          //  command.Connection.Close();
           // connection.Close();
            
        }

        protected void btnDelete_Click(object sender, EventArgs e)
        {
            var db = new GroupDBDataContext();
            var prodId = (from u in db.PRODUCTs
                          where u.PROD_ID.Equals(Request.QueryString["ProdID"])
                          select u);
            //   int ProductID = Int32.Parse(Request.QueryString["ProdID"]);
            foreach (var d in prodId)
            {
                db.PRODUCTs.DeleteOnSubmit(d);
            }
            try
            {
                db.SubmitChanges();
              //  Response.Redirect("ProductDelete.aspx");

            }
            catch (Exception I)
            {
                I.GetBaseException();
            }
           /* command = null;
            string commandStringDel = "DELETE  FROM [PRODUCT] WHERE PROD_ID = @prodid;";
            command = new SqlCommand(commandStringDel, connection);
            command.CommandType = CommandType.Text;


            command.Parameters.AddWithValue("@prodid", ProductID);
            command.Connection = connection;
            command.Connection.Open();

            command.ExecuteNonQuery();

            command.Connection.Close();
            connection.Close();
            */
            Response.Redirect("Home.aspx");
        }
    }
}