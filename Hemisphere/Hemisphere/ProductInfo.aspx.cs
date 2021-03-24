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
    public partial class ProductInfo : System.Web.UI.Page
    {
        private SqlConnection connection;
        private SqlCommand command;
        private SqlDataReader reader;

        protected void Page_Load(object sender, EventArgs e)
        {
            lblCannotViewPageContent.Text = "To view the contents of this page please " + "<a href='Login.aspx'>" + "Login" + "</a>" + "/" + "<a href='Registration.aspx'>" + "Register" + "</a>";
            if (Session["Username"] != null){
                if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == "A")
                {
                    AddToCart.Text = "Back";
                }
                lblCannotViewPageContent.Visible = false;
            
                var db = new GroupDBDataContext();
                var ProductID = from u in db.PRODUCTs
                                where u.PROD_ID.Equals(Request.QueryString["ProdID"])
                                select u;



                String ProdHTML = "";
              
                
                ProdHTML += "<table>";
                foreach (PRODUCT o in ProductID){
                        if (o.PROD_CATEGORY.Equals("TextBooks") || (o.PROD_CATEGORY.Equals("Books")) || (o.PROD_CATEGORY.Equals("Novels"))){
                            ProdHTML += "<tr><td><a href='ProductInfo.aspx?ProdID=" + o.PROD_ID + "'>" + "</a></td></tr>" + "<tr><td>" + "<img src = 'images/" + o.PROD_IMAGE_PATH + "'" + "width='80px' height='80px'" + "/>" + "</td></tr>" + "<tr><td>Name: " + o.PROD_NAME + "</td></tr>"
                            + "<tr><td>ISBN     : " + o.PROD_ISBN + "</tr></td>" + "<tr><td>Author      : " + o.PROD_AUTHOR + "</tr></td>" + "<tr><td>R " + o.PROD_PRICE + "</td></tr>" + "<tr><td>Quantity     : " + o.PROD_QUANTITY_AVAILABLE + "</tr></td>"
                            + "<tr><td>Category : " + o.PROD_CATEGORY + "</td></tr>" + "<tr><td>Details     :   " + o.PROD_DESCRIPTION + "</td></tr>";
                        }
                       
                }

                ProdHTML += "</table>";                
                productDiv.InnerHtml = ProdHTML;
         
            }
            else {
                lblCannotViewPageContent.Visible = true;
                productDiv.Visible = false;
                AddToCart.Visible = false;
            }
        }

        private bool ProdExist(int ProdID, GroupDBDataContext db)
        {
            var Items = from u in db.SHOPPING_CART_ITEMs
                        select u;
            bool val = false;
            foreach(SHOPPING_CART_ITEM p in Items)
            {
                if (p.ProductID.Equals(ProdID))
                {
                    val = true;
                }
            }
            return val;
        }

        private void UpdateQuantity(int ProdID, int incr, GroupDBDataContext datb )
        {
            var prod = (from p in datb.SHOPPING_CART_ITEMs
                        where p.ProductID.Equals(ProdID)
                        select p).FirstOrDefault();

          

           Double total = Convert.ToDouble(incr * prod.ItemPrice);
            var newItem = new SHOPPING_CART_ITEM
            {
                ProductID = prod.ProductID,
                ProductName = prod.ProductName,
                Quantity = incr,
                ItemPrice = prod.ItemPrice,
                TotalPrice = total,
                USER_ID = prod.USER_ID
            };
            var Delcomm = "DELETE FROM [SHOPPING_CART_ITEMS] " + "WHERE ProductID=" + ProdID.ToString() + ";";
            datb.ExecuteCommand(Delcomm);
            datb.SHOPPING_CART_ITEMs.InsertOnSubmit(newItem);

            try
            {
                datb.SubmitChanges();
                Response.Redirect("Home.aspx");
            }
            catch (Exception x)
            {
                x.GetBaseException();
            }



            /*  command = null;
              connection = new SqlConnection();
              connection.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\HRMANAGER\Documents\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
              string commandString = "UPDATE [SHOPPING_CART_ITEMS] SET Quantity = "+ incr.ToString()+ "WHERE ProductID=" + ProdID.ToString()+";";
              command = new SqlCommand(commandString, connection);

              command.CommandType = CommandType.Text;
              command.Connection = connection;
              command.Connection.Open();



              command.ExecuteNonQuery();
              command.Connection.Close();
              command.Dispose(); */
        }

        protected void AddToCart_Click(object sender, EventArgs e)
        {

           
            if (Session["Username"] != null) {
               if(Session["USER_AUTHENTICATION_LEVEL"].ToString()== "A")
               {
                    Response.Redirect("index.aspx");

               }
                var db = new GroupDBDataContext();
               

                var Product = (from u in db.PRODUCTs
                                 where u.PROD_ID.Equals(Request.QueryString["ProdID"])
                                 select u).FirstOrDefault();

               

                var userID = (from u in db.USERs
                            where u.USER_EMAIL_ADDRESS.Equals(Session["Username"].ToString())
                            select u.USER_ID).FirstOrDefault();

                int quant = (from u in db.SHOPPING_CART_ITEMs
                            where u.USER_ID.Equals(userID) && u.ProductID.Equals(Product.PROD_ID)
                            select u.Quantity).FirstOrDefault();


                int prodId = Convert.ToInt32(Product.PROD_ID.ToString());
                string productName = Product.PROD_NAME;
                double itemPrice = Convert.ToDouble(Product.PROD_PRICE);

                double total ;

                if (ProdExist(prodId, db))
                {
                    quant = quant + 1; 
                    UpdateQuantity(prodId, quant,db);
                    Response.Redirect("index.aspx");
                   
                }
                else 
                {
                    quant = 1;
                    total = Convert.ToDouble(quant * itemPrice);
                    var newItem = new SHOPPING_CART_ITEM
                    {
                        ProductID = prodId,
                        ProductName = productName,
                        Quantity = quant,
                        ItemPrice = itemPrice,
                        TotalPrice = total,
                        USER_ID = userID
                    };
                    db.SHOPPING_CART_ITEMs.InsertOnSubmit(newItem);

                    try
                    {
                        db.SubmitChanges();
                        Response.Redirect("index.aspx");
                    }
                    catch (Exception x)
                    {
                        x.GetBaseException();
                    }
                }
          
             
             



             
               
                
            }
        }
    }
}
    
