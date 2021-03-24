using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;
using Hemisphere;


namespace group
{
    public partial class ShoppingCart : System.Web.UI.Page
    {
        private SqlConnection Conn = null;
        private SqlCommand command = null;
        private SqlDataReader reader = null;
        string ItemId;

        protected void Page_Load(object sender, EventArgs e)
        {


            if (Session["Username"] != null)
            {
                shoppingCartDiv.Visible = true;
                Conn = new SqlConnection();
                Conn.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\HRMANAGER\Documents\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
                string commandString = "SELECT [SHOPPING_CART_ITEMS].ItemID, [SHOPPING_CART_ITEMS].ProductName, [SHOPPING_CART_ITEMS].Quantity, [SHOPPING_CART_ITEMS].ItemPrice, [SHOPPING_CART_ITEMS].TotalPrice, ([SHOPPING_CART_ITEMS].ItemPrice*[SHOPPING_CART_ITEMS].Quantity) AS TOTAL FROM [SHOPPING_CART_ITEMS] INNER JOIN [USER]  ON [SHOPPING_CART_ITEMS].USER_ID = [USER].USER_ID WHERE [SHOPPING_CART_ITEMS].USER_ID = @USER_ID ;";
                command = new SqlCommand(commandString, Conn);
                command.CommandType = CommandType.Text;
                command.Parameters.AddWithValue("@USER_ID", Session["UserId"]);

                command.Connection = Conn;
                command.Connection.Open();

                reader = command.ExecuteReader();

                String ProdHTML = "";

                if (reader.HasRows)
                {
                    ProdHTML += "<div class = 'table-responsive'><table border='1' class='.info'>";
                    ProdHTML += "<tr><th>ITEM ID</th><th>PRODUCT NAME</th><th>QUANTITY</th><th>PRICE</th><th>TOTAL</th><th>REMOVE</th></tr>";
                    while (reader.Read())
                    {
                        ItemId = reader["ItemID"].ToString();
                        ProdHTML += "<tr><td>" + (Int32)reader["ItemID"] + "</td>" + "<td>" + (String)reader["ProductName"] + "</td>" + "<td>" + "<input type ='number' name='" + ItemId + "' 'runat='server'  value='" + (Int32)reader["Quantity"] + "'>" + "</td>" + "<td>R " + reader["ItemPrice"] + "</td>" + "<td>R " + reader["TOTAL"] + "</td>" + "<td><a href = 'RemoveItem.aspx?ItemID=" + (Int32)reader["ItemID"] + "'>" + "Remove Item" + "</a> " + "</td></tr>";
                    }
                    ProdHTML += "</table></div>";
                    btnUpdate.Visible = true;
                    btnCheckOut.Visible = true;
                }

                shoppingCartDiv.InnerHtml = ProdHTML;
                command.Connection.Close();
                command.Dispose();
                reader = null;
            }
        }


        protected void btnUpdate_Click(object sender, EventArgs e)
        {
            


            shoppingCartDiv.Visible = true;
            int upQuan = Int32.Parse(Request.Form[ItemId]);

            Conn = new SqlConnection();
            Conn.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\HRMANAGER\Documents\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            string commandString = "UPDATE [SHOPPING_CART_ITEMS] SET Quantity ="+ upQuan.ToString() + " WHERE USER_ID=" + Session["UserId"].ToString() + " AND ItemID="+ItemId +";";
            command = new SqlCommand(commandString, Conn);
            command.CommandType = CommandType.Text;
            command.Connection = Conn;
            command.Connection.Open();

        /*    command.Parameters.AddWithValue("@QUANTITY", upQuan);
            command.Parameters.AddWithValue("@UID", Session["UserId"]);
            command.Parameters.AddWithValue("@Item", ItemId);*/

            command.ExecuteNonQuery();
            command.Connection.Close();
            Conn.Close();
            Response.Redirect("ShoppingCart.aspx"); 
        }

        protected void btnCheckOut_Click(object sender, EventArgs e)
        {

            var db = new GroupDBDataContext();
            DateTime date = DateTime.UtcNow;
            var newInvoice = new INVOICE
            {
                USER_ID = Convert.ToInt32(Session["UserId"].ToString()),
                INV_DATE = date

            };
            db.INVOICEs.InsertOnSubmit(newInvoice);
            try
            {
                db.SubmitChanges();
            }
            catch(Exception ex)
            {
                ex.GetBaseException();
            }

            //Retrieve invoice 

            var invoice = (from i in db.INVOICEs
                           where Session["UserId"].ToString().Equals(i.USER_ID.ToString()) && i.INV_DATE.Equals(date)
                           select i).FirstOrDefault();

            // Get shopping cart Items
            var scItems = from si in db.SHOPPING_CART_ITEMs
                          where si.USER_ID.ToString().Equals(Session["UserId"].ToString())
                          select si;
             
            foreach(SHOPPING_CART_ITEM s in scItems)
            {
                var newProd = new INVOICE_PRODUCT
                {
                    PROD_ID = s.ProductID,
                    INV_NUMBER = invoice.INV_NUMBER,
                    QUANTITY = s.Quantity
                };
                db.INVOICE_PRODUCTs.InsertOnSubmit(newProd);
                try
                {
                    db.SubmitChanges();
                }
                catch (Exception ex)
                {
                    ex.GetBaseException();
                }
                var command = "DELETE FROM [SHOPPING_CART_ITEMS] WHERE ProductID = " + s.ProductID.ToString() + " ;";
                db.ExecuteCommand(command);

            }

         /*
            shoppingCartDiv.Visible = false;
            Conn = new SqlConnection();
            Conn.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\HRMANAGER\Documents\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            string commandString = "SELECT ProductID FROM [SHOPPING_CART_ITEMS] WHERE USER_ID = @UID";
            string commandInsert = "INSERT INTO [INVOICE] VALUES(@USER_ID, @INV_DATE);";
            command = new SqlCommand(commandString, Conn);
            command.CommandType = CommandType.Text;

            command.Parameters.AddWithValue("@UID", Session["UserId"]); // NB

            command.Connection = Conn;
            command.Connection.Open();

            reader = command.ExecuteReader();

            int productId = 0;
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    productId = (Int32)reader["ProductID"];
                }
            }
            command.Connection.Close();
            Conn.Close();
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Conn = new SqlConnection();
            Conn.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=E:\Group Hemisphere\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            command = null;
            command = new SqlCommand(commandInsert, Conn);
            command.CommandType = CommandType.Text;

            command.Connection = Conn;
            command.Connection.Open();

            command.Parameters.AddWithValue("@USER_ID", Session["UserId"]);
            command.Parameters.AddWithValue("@INV_DATE", DateTime.UtcNow);
            command.ExecuteNonQuery();
            command.Connection.Close();
            Conn.Close();
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Conn = new SqlConnection();
            Conn.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=E:\Group Hemisphere\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            command = null;
            string commandSelect = "SELECT INV_NUMBER FROM [INVOICE] WHERE USER_ID = @USER_ID;";
            command = new SqlCommand(commandSelect, Conn);
            command.CommandType = CommandType.Text;

            command.Parameters.AddWithValue("@USER_ID", Session["UserId"]);

            command.Connection = Conn;
            command.Connection.Open();

            reader = command.ExecuteReader();

            int invoiceNum = 0;
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    invoiceNum = (Int32)reader["INV_NUMBER"];
                }
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Conn = new SqlConnection();
            Conn.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=E:\Group Hemisphere\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            command = null;
            commandInsert = null;
            commandInsert = "INSERT INTO [INVOICE_PRODUCT] VALUES (@PROD_ID, @INV_NUMBER,@QUANTITY);";
            command = new SqlCommand(commandInsert, Conn);
            command.CommandType = CommandType.Text;

            command.Connection = Conn;
            command.Connection.Open();
            int quantity = Int32.Parse(Request.Form[ItemId]);
            command.Parameters.AddWithValue("@PROD_ID", productId);
            command.Parameters.AddWithValue("@INV_NUMBER", invoiceNum);
            command.Parameters.AddWithValue("@QUANTITY", quantity);
            command.ExecuteNonQuery();
            command.Connection.Close();
            Conn.Close();

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //Removing product from the cart
            //Conn = new SqlConnection();
            //Conn.ConnectionString = @"Data Source=(LocalDB)\v11.0;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
            //command = null;
            //commandString = null;
            //commandString = "DELETE FROM [SHOPPING_CART_ITEMS] WHERE ItemID= @ItemID;";
            //command = new SqlCommand(commandString, Conn);
            //command.CommandType = CommandType.Text;

            //command.Connection = Conn;
            //command.Connection.Open();

            //command.Parameters.AddWithValue("@ItemID", ItemID);
            //command.ExecuteNonQuery();

            //command.Connection.Close();
            //Conn.Close();
            //Response.Redirect("Invoice.aspx");

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //CHANGE THE QUANTITY IN THE DATABASE
            Conn = new SqlConnection();
            Conn.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=E:\Group Hemisphere\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            command = null;
            commandString = null;
            commandString = "SELECT [SHOPPING_CART_ITEMS].Quantity, [SHOPPING_CART_ITEMS].ProductID, [PRODUCT].PROD_QUANTITY_AVAILABLE FROM [SHOPPING_CART_ITEMS] INNER JOIN [PRODUCT] ON [SHOPPING_CART_ITEMS].ProductID= [PRODUCT].PROD_ID WHERE USER_ID = @UID;";
            command = new SqlCommand(commandString, Conn);
            command.CommandType = CommandType.Text;

            command.Connection = Conn;
            command.Connection.Open();
            command.Parameters.AddWithValue("@UID", Session["UserId"]);
            reader = command.ExecuteReader();
            int prodQuan = 0;
            int prodID = 0;
            int prodQuanInCart = 0;
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    prodQuan = (Int32)reader["PROD_QUANTITY_AVAILABLE"];
                    prodID = (Int32)reader["ProductID"];
                    prodQuanInCart = (Int32)reader["Quantity"];
                }
            }
            command.Connection.Close();
            Conn.Close();
            command.Dispose();
            reader = null;
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //Update
            Conn = new SqlConnection();
            Conn.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=E:\Group Hemisphere\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            command = null;
            commandString = null;
            commandString = "UPDATE [PRODUCT] SET PROD_QUANTITY_AVAILABLE = @QUANTITY WHERE PROD_ID = @PROD_ID;";
            command = new SqlCommand(commandString, Conn);
            command.CommandType = CommandType.Text;

            command.Connection = Conn;
            command.Connection.Open();

            command.Parameters.AddWithValue("@QUANTITY", prodQuan - prodQuanInCart);
            command.Parameters.AddWithValue("@PROD_ID", prodID);

            command.ExecuteNonQuery();
            command.Connection.Close();
            Conn.Close();
            command.Dispose();*/
            Response.Redirect("Invoice.aspx"); 
        }
    }
}