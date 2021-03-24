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
    public partial class MyAccount : System.Web.UI.Page
    {
        SqlConnection connection = null;
        SqlCommand command = null;
        SqlDataReader reader = null;

        protected void Page_Load(object sender, EventArgs e)
        {
            lblSucces.Visible = false;
            if(Session["Username"] == null)
            {
                myProfileDiv.Visible = false;
                lblCannotViewPageContent.Text = "To view the contents of this page please " + "<a color=antiquewhite href='Login.aspx'>" + "Login" + "</a>" + "/" + "<a href='Registration.aspx'>" + "Register" + "</a>";
            }
            else
            {
                /*   lblCannotViewPageContent.Visible = false;
                   connection = new SqlConnection();
                   connection.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\groupDB.mdf;Integrated Security=True";
                   string commandString = "SELECT * FROM [USER] WHERE USER_EMAIL_ADDRESS=@uID;";
                   command = new SqlCommand(commandString, connection);

                   command.Connection = connection;
                   command.CommandType = CommandType.Text;

                   command.Parameters.AddWithValue("@uID", Session["Username"]);

                   command.Connection.Open();
                   reader = command.ExecuteReader();

                   if(reader.HasRows)
                   {
                       while (reader.Read())
                       {
                           txtPhone.Text = (String)reader["USER_PHONE_NUMBER"];
                           txtsurname.Text = (String)reader["USER_SURNAME"];
                           txtName.Text = (String)reader["USER_NAME"];
                           if ((String)reader["USER_GENDER"] == "F")
                           {
                               GenderDropDown.SelectedIndex = 2;
                           }
                           else
                           {
                               GenderDropDown.SelectedIndex = 1;
                           }
                           txtEmail.Text = (String)reader["USER_EMAIL_ADDRESS"];
                       }
                   }
                   command.Connection.Close();
                   command.Dispose();*/
                var db = new GroupDBDataContext();

                dynamic use = from u in db.USERs
                              where u.USER_EMAIL_ADDRESS.Equals(Session["Username"].ToString())
                              select u;
                foreach (USER u in use)
                {
                    txtPhone.Text = u.USER_PHONE_NUMBER;
                    txtsurname.Text = u.USER_SURNAME;
                    txtName.Text = u.USER_NAME;
                    if (u.USER_GENDER.ToString() == "F")
                    {
                        GenderDropDown.SelectedIndex = 2;
                    }
                    else
                    {
                        GenderDropDown.SelectedIndex = 1;
                    }
                    txtEmail.Text = u.USER_EMAIL_ADDRESS;
                }
            }
        }

        protected void btnSumbit_Click(object sender, EventArgs e)
        {
            if (!Page.IsPostBack)
            {
                var db = new GroupDBDataContext();

                USER nU = db.USERs.Single(u => u.USER_EMAIL_ADDRESS == Session["Username"].ToString());
                nU.USER_SURNAME = txtsurname.Text;
                nU.USER_NAME = txtName.Text;
                nU.USER_GENDER = Convert.ToChar(GenderDropDown.SelectedItem.Text);
                nU.USER_EMAIL_ADDRESS = txtEmail.Text;
                lblSucces0.Visible = false;
                try
                {
                    db.SubmitChanges();
                    lblSucces.Visible = true;
                }
                catch (Exception ex)
                {
                    ex.GetBaseException();
                    lblSucces0.Visible = true;
                }


                /*
                command = null;
                string commandString = "UPDATE [USER] SET USER_SURNAME = @SurName, USER_NAME=@uName, USER_GENDER=@Gend, USER_EMAIL_ADDRESS=@Email, USER_USER_PASSWORD=@Pass;";
                command = new SqlCommand(commandString, connection);

                command.Connection = connection;
                command.CommandType = CommandType.Text;

                command.Connection.Open();

                command.Parameters.AddWithValue("@SurName", txtsurname.Text);
                command.Parameters.AddWithValue("@uName", txtName.Text);
                command.Parameters.AddWithValue("@Gend", GenderDropDown.SelectedItem);
                command.Parameters.AddWithValue("@Email", txtEmail.Text);

                command.ExecuteNonQuery();
                command.Connection.Close();
                command.Dispose();
                */
                lblCannotViewPageContent.Visible = false;
            }
            else
            {
                lblCannotViewPageContent.Visible = false;
            }
        }
           
        

        protected void btnChangePassword_Click(object sender, EventArgs e)
        {
            Response.Redirect("PasswordChange.aspx");
        }
    }
}