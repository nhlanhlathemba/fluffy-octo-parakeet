using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using IFM2B_2018_PST_A;
using System.Data;
using System.Data.SqlClient;
using System.Text;
namespace Hemisphere.Login_and_Registration
{
    public partial class Registration : System.Web.UI.Page
    {
        private SqlConnection connection;
        private SqlCommand command;
        protected void Page_Load(object sender, EventArgs e)
        {
            ValidationSettings.UnobtrusiveValidationMode = UnobtrusiveValidationMode.None;
            if (!Page.IsPostBack)
            {
                lblSucces.Visible = false;
                lblAccountExists.Visible = false;
            }
        }

        protected void btnSumbit_Click(object sender, EventArgs e)
        {
            var db = new GroupDBDataContext();

            char UserStatus;
            bool isreg = false;

            dynamic CheckUser = from u in db.USERs
                                select u;
            foreach (USER u in CheckUser)
            {
                if ((u.USER_EMAIL_ADDRESS.Equals(txtEmail.Text)))
                {
                    isreg = true;

                }
            }
            if (isreg == true)
            {
                //   report.InnerHtml = "<p>User already exists</p>";
                lblAccountExists.Text = "Username already exists";
                lblAccountExists.Visible = true;
                lblSucces.Visible = false;
                //   Response.Redirect("Login.aspx");
            }
            if (isreg == false)
            {
               
              
                    UserStatus = 'U';
                

                // var Aid = Convert.ToInt32(id)+ 1;
                var newUser = new USER
                {
                    //  USER_ID=Aid,
                    USER_SURNAME = txtSurname.Text,
                    USER_NAME = txtName.Text,
                    USER_GENDER = Convert.ToChar(GenderDropDown.SelectedValue),
                    USER_PHONE_NUMBER = txtPhone.Text,
                    USER_EMAIL_ADDRESS = txtEmail.Text,
                    USER_AUTHENTICATION_LEVEL = UserStatus,
                    USER_USER_PASSWORD = Secrecy.HashPassword(txtPassword1.Text),
                    SECURITY_QUESTION = txtSecurityQuiz.Text,
                    ANSWER = txtAnswer.Text,
                    REG_DATE = DateTime.Now
                };
                db.USERs.InsertOnSubmit(newUser);
                try
                {
                    db.SubmitChanges();
                    Response.Redirect("Login.aspx");
                }
                catch (Exception ex)
                {
                    // ex.GetBaseException();
                    report.InnerHtml = "<p>" + ex.GetBaseException() + "</p>";
                    //  Response.Redirect("ErrorPage.aspx");
                }
            }
            /*
            connection = new SqlConnection();
            connection.ConnectionString = @"Data Source=(LocalDB)\v.12;AttachDbFilename=|DataDirectory|\groupDB.mdf;Integrated Security=True";
            String commandstring = "INSERT INTO [USER] VALUES (@Surname, @Name, @Gender,@Phone, @Email,@UserLevel, @password,@securityQuestion, @Answer, @RegDate);";
            String checkUser = "SELECT COUNT(*) FROM [USER] WHERE USER_EMAIL_ADDRESS=@Email;";
            command = new SqlCommand(checkUser, connection);
            command.CommandType = CommandType.Text;
            command.Parameters.AddWithValue("@Email", txtEmail.Text);
            command.Connection = connection;
            command.Connection.Open();
            command.ExecuteNonQuery();

            int count = (Int32)command.ExecuteScalar();
            if (count >= 1)
            {
                lblAccountExists.Text = "Username already exists";
                lblAccountExists.Visible = true;
                lblSucces.Visible = false;
            }
            else
            {
                command = null;
                command = new SqlCommand(commandstring, connection);
                command.CommandType = CommandType.Text;
                command.Parameters.AddWithValue("@Surname", txtsurname.Text);
                command.Parameters.AddWithValue("@Name", txtName.Text);
                command.Parameters.AddWithValue("@Gender", GenderDropDown.SelectedItem.ToString());
                command.Parameters.AddWithValue("@Phone", txtPhone.Text);
                command.Parameters.AddWithValue("@Email", txtEmail.Text);
                command.Parameters.AddWithValue("@UserLevel", "U");
                command.Parameters.AddWithValue("@password", Secrecy.HashPassword(txtPassword2.Text));
                command.Parameters.AddWithValue("@securityQuestion", txtSecurityQuiz.Text.ToUpper());
                command.Parameters.AddWithValue("@Answer", txtAnswer.Text.ToUpper());
                command.Parameters.AddWithValue("@RegDate", DateTime.UtcNow);
                command.ExecuteNonQuery();

                lblSucces.Visible = true;
                lblAccountExists.Visible = false;

                txtName.Text = "";
                txtEmail.Text = "";
                txtPassword1.Text = "";
                txtPassword2.Text = "";
                txtPhone.Text = "";
                txtsurname.Text = "";
                GenderDropDown.SelectedIndex = 0;

                Response.Redirect("Login.aspx");
            }


            command.Connection.Close();
            command.Dispose();
            connection.Close();
            */
        }

        protected void btnReset_Click(object sender, EventArgs e)
        {
            lblSucces.Visible = false;
            txtName.Text = "";
            txtEmail.Text = "";
            txtPassword1.Text = "";
            txtPassword2.Text = "";
            txtPhone.Text = "";
            txtSurname.Text = "";
            GenderDropDown.SelectedIndex = 0;
        }
    }
}