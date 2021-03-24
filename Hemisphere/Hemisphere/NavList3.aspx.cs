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
    public partial class NavList3 : System.Web.UI.Page
    {
       

        protected void Page_Load(object sender, EventArgs e)
        {
            var db = new GroupDBDataContext();

            dynamic list = from u in db.PRODUCTs
                           where u.PROD_CATEGORY.Equals("Textbooks")
                           select u;

            String ProdHTML = "";
          
             
                ProdHTML += "<center><H2>TEXTBOOKS</H2><center>";
                foreach (PRODUCT p in list)
                {
                    ProdHTML += "<div class='card mb-3' style = 'max-width: 50rem;'>";
                    ProdHTML += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";

                    ProdHTML += " <img height = 200 width= 200  src = 'images/" + p.PROD_IMAGE_PATH + "'" + " alt = 'Card Image' />";
                    ProdHTML += " <div class = 'card-body'>";
                    ProdHTML += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p> " +
                        " <a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a>";
                    ProdHTML += "</div></div>";
                }
        
            

            NavDiv.InnerHtml = ProdHTML;

        }
    }
}