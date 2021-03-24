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
    public partial class RemoveUser : System.Web.UI.Page
    {


        protected void Page_Load(object sender, EventArgs e)
        {
               int userID = Int32.Parse(Request.QueryString["UserID"]);

            var db = new GroupDBDataContext();

            var users = from u in db.USERs
                        where u.USER_ID.Equals(userID.ToString())
                        select u;

            foreach (USER u in users)
            {
                txtName.Text = u.USER_NAME;
                txtSurname.Text = u.USER_SURNAME;
                txtGender.Text = u.USER_GENDER.ToString();
                txtPNumber.Text = u.USER_PHONE_NUMBER.ToString();
                txtEmail.Text = u.USER_EMAIL_ADDRESS;
                txtAuthLevel.Text = u.USER_AUTHENTICATION_LEVEL.ToString();
            }
        }




        protected void btnDelete_Click(object sender, EventArgs e)
        {
            var db = new GroupDBDataContext();


            var command = "DELETE FROM[USER]  WHERE USER_ID = " + Request.QueryString["UserID"] + "; ";
            db.ExecuteCommand(command);

            Response.Redirect("Users.aspx");
          
        }
    }

}