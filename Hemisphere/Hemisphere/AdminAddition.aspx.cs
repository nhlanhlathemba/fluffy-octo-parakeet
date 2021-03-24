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
    public partial class AdminAddition : System.Web.UI.Page
    {
        private SqlConnection connection;
        private SqlCommand command;
        private SqlDataReader reader;
        protected void Page_Load(object sender, EventArgs e)
        {
            lblErr.Visible = false;
            int UserID = Int32.Parse(Request.QueryString["UserID"]);
            connection = new SqlConnection();
            connection.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=E:\Group Hemisphere\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            string commandString = "SELECT * FROM [USER] WHERE USER_ID=@userID;";
            command = new SqlCommand(commandString, connection) ;

            command.Connection = connection;
            command.CommandType = CommandType.Text;

            command.Parameters.AddWithValue("@userID", UserID);

            command.Connection.Open();
            reader = command.ExecuteReader();
            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    txtName.Text = (String)reader["USER_NAME"];
                    txtSurname.Text = (String)reader["USER_SURNAME"];
                    txtGender.Text = (String)reader["USER_GENDER"];
                    txtPNumber.Text = (String)reader["USER_PHONE_NUMBER"];
                    txtEmail.Text = (String)reader["USER_EMAIL_ADDRESS"];
                    txtAuthLevel.Text = (String)reader["USER_AUTHENTICATION_LEVEL"];
                }    
            }
            command.Connection.Close();
            command.Dispose();
            
        }

        protected void btnAddAdmin_Click(object sender, EventArgs e)
        {
            int UserID = Int32.Parse(Request.QueryString["UserID"]);
            command = null;
            connection = new SqlConnection();
            connection.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=E:\Group Hemisphere\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            string commandString = "UPDATE [USER] SET USER_AUTHENTICATION_LEVEL = @AuthLevel WHERE USER_ID=@userID;";
            command = new SqlCommand(commandString, connection);

            command.CommandType = CommandType.Text;
            command.Connection = connection;
            command.Connection.Open();
            if (txtAuthLevel.Text.ToUpper() != "A" || txtAuthLevel.Text.ToUpper() != "U")
            {
                lblErr.Text = "Please enter a valid user level character (U or A)";
                lblErr.Visible = true;
            }
            else
            {
                command.Parameters.AddWithValue("@userID", UserID);
                command.Parameters.AddWithValue("@AuthLevel", txtAuthLevel.Text.ToUpper());

                command.ExecuteNonQuery();
                command.Connection.Close();
                command.Dispose();
            }
        }
    }
}