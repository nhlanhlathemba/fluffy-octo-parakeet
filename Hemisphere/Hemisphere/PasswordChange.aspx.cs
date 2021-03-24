using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using IFM2B_2018_PST_A;
using System.Data;
using System.Data.SqlClient;

namespace Hemisphere
{
    public partial class PasswordChange : System.Web.UI.Page
    {
        private SqlConnection connection;
        private SqlCommand command;
        private SqlDataReader reader;
        protected void Page_Load(object sender, EventArgs e)
        {
            ChangePassword.Visible = false;
            lblCurrentPassword.Visible = true;
            lblNewPassword.Visible = false;
            lblConfirmNewPassword.Visible = false;
            txtConfirmNewPassword.Visible = false;
            txtNewPassword.Visible = false;
            txtCurrentPassword.Visible = true;

        }

        protected void ChangePassword_Click(object sender, EventArgs e)
        {
            if(Session["Username"] != null)
            {
                /*  connection = new SqlConnection();
                  connection.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\groupDB.mdf;Integrated Security=True";
                  string commandUpdate = "UPDATE [USER] SET USER_USER_PASSWORD = @NEWPASS WHERE USER_ID = @USER_ID;";
                  command = new SqlCommand(commandUpdate, connection);*/
                var db = new GroupDBDataContext();
                var oldpass = Secrecy.HashPassword(txtCurrentPassword.Text);
                var check = from u in db.USERs
                            where u.USER_EMAIL_ADDRESS.Equals(Session["Username"].ToString())
                            && u.USER_USER_PASSWORD.Equals(oldpass)
                            select u;
                if (check.Count() != 0)
                {
                    // var db = new HemisphereDBDataContext();

                    USER nU = db.USERs.Single(u => u.USER_EMAIL_ADDRESS == Session["Username"].ToString());
                    nU.USER_USER_PASSWORD = Secrecy.HashPassword(txtNewPassword.Text);

                    try
                    {
                        db.SubmitChanges();
                        Response.Redirect("MyAccount.aspx");
                    }
                    catch (Exception ex)
                    {
                        ex.GetBaseException();

                    }

                }
            //    string newPass = Secrecy.HashPassword(txtConfirmNewPassword.Text);

               /* command.Connection = connection;
                command.CommandType = CommandType.Text;

                command.Parameters.AddWithValue("@NEWPASS", newPass);
                command.Parameters.AddWithValue("@USER_ID", Session["UserId"]);

                command.Connection.Open();
                command.ExecuteNonQuery();

                connection.Close();
                command.Dispose();*/
                Response.Redirect("Login.aspx");
                Session.Abandon();
                
            }
        }

        protected void btnChangePassword_Click(object sender, EventArgs e)
        {
            if (Session["Surname"] != null)
            {
                connection = new SqlConnection();
                connection.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\groupDB.mdf;Integrated Security=True";
                string commandString = "SELECT * FROM [USER] WHERE USER_EMAIL_ADDRESS = @uEmail;";
                command = new SqlCommand(commandString, connection);
                command.CommandType = CommandType.Text;
                command.Parameters.AddWithValue("@uEmail", Session["Username"]);

                command.Connection = connection;
                command.Connection.Open();
                reader = command.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        if ((String)reader["USER_USER_PASSWORD"] == Secrecy.HashPassword(txtCurrentPassword.Text))
                        {
                            lblCurrentPassword.Visible = false;
                            txtCurrentPassword.Visible = false;
                            lblNewPassword.Visible = true;
                            txtConfirmNewPassword.Visible = true;
                            txtNewPassword.Visible = true;
                            lblConfirmNewPassword.Visible = true;
                            btnChangePassword.Visible = false;
                            ChangePassword.Visible = true;
                        }
                    }

                    command.Connection.Close();
                    connection.Close();
                    command.Dispose();
                }
            }
        }
    }
}