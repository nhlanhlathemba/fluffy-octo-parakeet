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
    public partial class Login : System.Web.UI.Page
    {
       
        protected void Page_Load(object sender, EventArgs e)
        {
            ValidationSettings.UnobtrusiveValidationMode = UnobtrusiveValidationMode.None;
            if (!Page.IsPostBack)
            {
                lblError.Visible = false;
            }
            else
            {
                lblError.Visible = true;
            }
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            var Email = txtUserName.Text;
            var Password = Secrecy.HashPassword(txtPassword.Text);

            var db = new GroupDBDataContext();
            
            var user = from u in db.USERs
                       where u.USER_EMAIL_ADDRESS.Equals(Email)
                       && u.USER_USER_PASSWORD.Equals(Password)
                       select u;

            var UserID = (from u in db.USERs
                          where u.USER_EMAIL_ADDRESS.Equals(Email)
                          && u.USER_USER_PASSWORD.Equals(Password)
                          select u.USER_ID).FirstOrDefault();

            var level =( from u in db.USERs
                        where u.USER_EMAIL_ADDRESS.Equals(Email)
                        && u.USER_USER_PASSWORD.Equals(Password)
                        select u.USER_AUTHENTICATION_LEVEL).FirstOrDefault();

            var Title = (from u in db.USERs
                        where u.USER_EMAIL_ADDRESS.Equals(Email)
                        && u.USER_USER_PASSWORD.Equals(Password)
                        select u.USER_GENDER).FirstOrDefault();

            var Surname = (from u in db.USERs
                        where u.USER_EMAIL_ADDRESS.Equals(Email)
                        && u.USER_USER_PASSWORD.Equals(Password)
                        select u.USER_SURNAME).FirstOrDefault();

            lblError.Text = "Incorrect username/password combination. <a href='Registration.aspx'>Register</a> ";
            if (user.Count() != 0)
            {
               

                Session["Surname"] = Surname;
                Session["Username"] = Email;
                Session["UserID"] = UserID;
                Session["USER_AUTHENTICATION_LEVEL"] = level;
                if(Title .Equals("F"))
                   {
                     Session["Title"] = "Ms.";
                    }else
                {
                    Session["Title"] = "Mr.";
                }
             
                Response.Redirect("index.aspx");
            }
            else
            {
                lblError.Visible = true;
            }

          
        }
    }
}