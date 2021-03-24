using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Hemisphere
{
    public partial class Site1 : System.Web.UI.MasterPage
    {
        string adminLevel = "A";
        protected void Page_Load(object sender, EventArgs e)
        {
            

         if (Session["Username"] != null)
         {
              lblWelcome.Text = "Hi " + Session["Title"] + " " + Session["Surname"] + " !";
              LinkButton1.Visible = true;
              Cart.Visible = true;
              login.Visible = false;
              registerele.Visible = false;
              invce.Visible = true;
                admin.Visible = false;
              if (Session["USER_AUTHENTICATION_LEVEL"].ToString()==adminLevel)
              {
                  admin.Visible = true;
                  Cart.Visible = false;
                  invce.Visible = false;
                    comment.Visible = false;
                    about.Visible = false;
                    contact.Visible = false;
                 //   Response.Redirect("admin.aspx");
                }
              else
              {
               //  adminBar.Visible = false;
              }
         }
         else
         {
            lblWelcome.Visible = false;
            LinkButton1.Visible = false;
            Cart.Visible = false;
            invce.Visible = false;
         }
        }

        protected void LinkButton1_Click(object sender, EventArgs e)
        {
            Session["USER_AUTHENTICATION_LEVEL"] = null;


            Session.Abandon();

            LinkButton1.Visible = false;
            lblWelcome.Visible = false;
            Response.Redirect("Login.aspx");
        }

        protected void lnkBtnNovels_Click(object sender, EventArgs e)
        {
            Response.Redirect("NavList2.aspx");
        }

        protected void lnkBtnBooks_Click(object sender, EventArgs e)
        {
            Response.Redirect("NavList.aspx");
        }

        protected void lnlBtnTextBooks_Click(object sender, EventArgs e)
        {
            Response.Redirect("NavList3.aspx");
        }
    }
}

