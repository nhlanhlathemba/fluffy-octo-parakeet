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
    public partial class Users : System.Web.UI.Page
    {


        protected void Page_Load(object sender, EventArgs e)
        {
            var db = new GroupDBDataContext();

            var Users = from u in db.USERs
                        select u;
   

            string HTML = "";



                HTML += "<table>";
                foreach (USER u in Users)
                {
                    HTML += "<tr><td><b>User Id</b>: " + u.USER_ID + "<tr><td>" + "<tr><td><b>Surname</b>: " + u.USER_SURNAME + "</td></tr>" + "<tr><td><b>Name</b>: " + u.USER_NAME + "</td></tr>"
                              + "<tr><td><b>Gender</b>: " + u.USER_GENDER + "</td></tr>" + "<tr><td><b>Phone Number</b>: " + u.USER_PHONE_NUMBER + "</td></tr>" +
                              "<tr><td><b>Email</b>: " + u.USER_EMAIL_ADDRESS + "</td></tr>" + "<tr><td><b>Authentication Level</b>: " + u.USER_AUTHENTICATION_LEVEL + "</td></tr>";
                    HTML += "<tr><td><a href='AdminAddition.aspx?UserID=" + u.USER_ID + "'>" + "Add As Admin" + "</a></td></tr>";
                    HTML += "<tr><td><a href='RemoveUser.aspx?UserID=" + u.USER_ID + "'>" + "Delete User" + "</a></td></tr>";
                    HTML += "<br/><br/><br/>";
                }
                HTML += "</table>";
            


            usersDiv.InnerHtml += HTML;
            //  command.Connection.Close();
            // command.Dispose();
            //  reader = null;
        }
    }
}