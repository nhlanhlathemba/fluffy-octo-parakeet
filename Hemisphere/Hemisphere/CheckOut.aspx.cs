using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

namespace group
{
    public partial class CheckOut : System.Web.UI.Page
    {
        private SqlConnection Conn = null;
        private SqlCommand command = null;
        private SqlDataReader reader = null;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Surname"] != null)
            {
                Conn = new SqlConnection();
                Conn.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
                string commandString = "SELECT * FROM [SHOPPING_CART_ITEMS] WHERE USER_ID=@UID;";
                command = new SqlCommand(commandString, Conn);
                command.CommandType = CommandType.Text;

                command.Parameters.AddWithValue("@UID", Session["UserId"]); // NB

                command.Connection = Conn;
                command.Connection.Open();

                reader = command.ExecuteReader();

                String ProdHTML = "";
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        ProdHTML += "<b>Product Name:</b>" + reader["ProductName"] + "<br/><b>Price:</b>" + reader["ItemPrice"] + "<br/><b>Quantity:</b>" + reader["Quantity"] +
                            "<br/><b>Total:</b>" + reader["TotalPrice"];
                    }
                }
                checkOutDiv.InnerHtml = ProdHTML;
                command.Connection.Close();
                command.Dispose();
                reader = null;
            }

        }

        protected void btnCheckOut_Click(object sender, EventArgs e)
        {
            Conn = new SqlConnection();
            Conn.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
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
            Conn.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
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
            Conn.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
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
            Conn.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
            command = null;
            commandInsert = null;
            commandInsert = "INSERT INTO [INVOICE_PRODUCT] VALUES (@PROD_ID, @INV_NUMBER);";
            command = new SqlCommand(commandInsert, Conn);
            command.CommandType = CommandType.Text;

            command.Connection = Conn;
            command.Connection.Open();

            command.Parameters.AddWithValue("@PROD_ID", productId);
            command.Parameters.AddWithValue("@INV_NUMBER", invoiceNum);
            command.ExecuteNonQuery();
            command.Connection.Close();
            Conn.Close();

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //Removing product from the cart
            //Conn = new SqlConnection();
            //Conn.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
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
            Conn.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
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
            Conn.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\GroupDB.mdf;Integrated Security=True";
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
            command.Dispose();
            Response.Redirect("Invoice.aspx");
        }
    }
}