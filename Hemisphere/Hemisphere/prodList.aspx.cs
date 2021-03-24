using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Hemisphere
{
    public partial class prodList : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        
            
                var db = new GroupDBDataContext();

                dynamic list = from u in db.PRODUCTs
                           orderby u.PROD_NAME ascending
                           select u;
          String prodHTML = "<h2>All Products<h2> <hr/><ul>";

            int fi;
                foreach (PRODUCT p in list)
                {
                                  
                    prodHTML += "<li class = 'txt'> <a href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' >" + p.PROD_NAME + "</a></li> ";
                   
                }
                prodHTML += "</ul>";

                html.InnerHtml += prodHTML;
            
        }

       
    }
}