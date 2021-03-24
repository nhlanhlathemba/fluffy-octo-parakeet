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
    public partial class ViewComments : System.Web.UI.Page
    {

        protected void Page_Load(object sender, EventArgs e)
        {
            if(Session["Username"]!=null)
            {
                var db = new GroupDBDataContext();
                var user = from u in db.USERs
                        
                           select u;
                string HTML = "";
                int counter = 0;
                foreach (USER r in user)
                {
                    var Com = (from u in db.COMMENTs
                              where u.USER_ID.ToString().Equals(r.USER_ID.ToString())
                              select u).FirstOrDefault();
                    if(Com!=null)
                    {
                       HTML += " <br/><b>SUBJECT</b>: " + Com.SUBJECT + "<br/>" +
                            " On the " + Com.DATE + " " + r.USER_NAME + "(" + r.USER_EMAIL_ADDRESS + ")" + " said: <br/>"
                        +  Com.COMMENT1 + "<br/><hr size='4'/>";
                        counter++;
                    }
                 
                }
                if (counter == 0)
                {
                    HTML = "<h2> no comments available</h2>";
                }
                commentsDiv.InnerHtml = HTML;
            }


        }
    }
}