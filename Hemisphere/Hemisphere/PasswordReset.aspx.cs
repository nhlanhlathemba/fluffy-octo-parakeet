using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using IFM2B_2018_PST_A;


namespace Hemisphere
{
    public partial class PasswordReset : System.Web.UI.Page
    {

        private SqlConnection connection;
        private SqlCommand command;
        private SqlDataReader reader;
        private string username;
        protected void Page_Load(object sender, EventArgs e)
        {
            lblError.Visible = false;
            lblPassword1.Visible = false;
            lblPassword2.Visible = false;
            lblSA.Visible = false;
            lblSQ.Visible = false;
            txtPassword1.Visible = false;
            txtPassword2.Visible = false;
            txtSecurityQuiz.Visible = false;
            txtAnswer.Visible = false;
            Button1.Visible = false;
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            /*  connection = new SqlConnection();
              connection.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\groupDB.mdf;Integrated Security=True";
              string commandString = "SELECT * FROM [USER] WHERE USER_EMAIL_ADDRESS = @EMAIL;";
              command = new SqlCommand(commandString, connection);

              command.Connection = connection;
              command.CommandType = CommandType.Text;

              string username = txtUserName.Text;
              command.Parameters.AddWithValue("@EMAIL", username);

              command.Connection.Open();
              reader = command.ExecuteReader();*/
             username = txtUserName.Text;
            var db = new GroupDBDataContext();

            var use = (from u in db.USERs
                          where u.USER_EMAIL_ADDRESS.Equals(username)
                          select u).FirstOrDefault();
          //  if (reader.HasRows)
            {
               // while (reader.Read())
                {
                    if (use!=null)
                    {
                        lblSA.Visible = true;
                        lblSQ.Visible = true;
                        txtSecurityQuiz.Visible = true;
                        txtAnswer.Visible = true;
                        Button1.Visible = false;
                        txtUserName.Text = username;
                        txtSecurityQuiz.Text = (String)reader["SECURITY_QUESTION"];

                        // txtAnswer.Text = "";

                        if (txtAnswer.Text.ToUpper() == use.SECURITY_QUESTION.ToString())
                        {

                            lblPassword1.Visible = true;
                            lblPassword2.Visible = true;
                            txtPassword1.Visible = true;
                            txtPassword2.Visible = true;
                            txtPassword1.Text = "";
                            txtPassword2.Text = "";
                            Button1.Visible = true;
                            Button2.Visible = false;
                        }
                    }
                }
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
         /*   connection = new SqlConnection();
            connection.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\groupDB.mdf;Integrated Security=True";
            command = null;
            string commandUpdate = "UPDATE [USER] SET USER_USER_PASSWORD = @NEWPASS WHERE USER_EMAIL_ADDRESS = @EMAIL;";
            command = new SqlCommand(commandUpdate, connection);
            command.CommandType = CommandType.Text;*/
            string newPass = Secrecy.HashPassword(txtPassword2.Text);
          //  string username = txtUserName.Text;
            var db = new GroupDBDataContext();
            USER nU = db.USERs.Single(u => u.USER_EMAIL_ADDRESS == username);
            nU.USER_USER_PASSWORD = newPass;

            try
            {
                db.SubmitChanges();
                Response.Redirect("Login.aspx");
            }
            catch (Exception ex)
            {
                ex.GetBaseException();

            }
          
           
        }
    }
}