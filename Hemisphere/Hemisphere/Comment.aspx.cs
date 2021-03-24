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
    public partial class Comment : System.Web.UI.Page
    {
        private SqlConnection connection = null;
        private SqlCommand command = null;
        private SqlDataReader reader = null;
        protected void Page_Load(object sender, EventArgs e)
        {
            lblSucces.Visible = false;
            if (Page.IsPostBack)
            {
                lblSucces.Visible = true;
            }

            if (Session["Surname"] == null)
            {
                commentDiv.Visible = false;
                lblCannotViewPageContent.Text = "To view the contents of this page please " + "<a href='Login.aspx'>" + "Login" + "</a>" + "/" + "<a href='Registration.aspx'>" + "Register" + "</a>";
            }
            else
            {
                lblCannotViewPageContent.Visible = false;
            }
        }

        protected void btnReset_Click(object sender, EventArgs e)
        {
            txtComment.Text = " ";
            txtSubject.Text = " ";
            lblSucces.Visible = false;
        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            connection = new SqlConnection();
            connection.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\HRMANAGER\Documents\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            string commandString = "INSERT INTO [COMMENT] VALUES(@USER_ID,@COMMENT,@DATE,@SUBJECT);";
            command = new SqlCommand(commandString, connection);

            command.CommandType = CommandType.Text;

            command.Parameters.AddWithValue("@USER_ID", Session["UserId"]);
            command.Parameters.AddWithValue("@COMMENT", txtComment.Text);
            command.Parameters.AddWithValue("@DATE", DateTime.UtcNow);
            command.Parameters.AddWithValue("@SUBJECT", txtSubject.Text);

            command.Connection = connection;
            command.Connection.Open();
            command.ExecuteNonQuery();
            command.Connection.Close();
            command.Dispose();
            connection.Close();
            lblSucces.Visible = true;
            txtComment.Text = " ";
            txtSubject.Text = " ";
        }
    }
}