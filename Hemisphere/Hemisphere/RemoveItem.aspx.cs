using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Hemisphere
{
    public partial class RemoveItem : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            var db = new GroupDBDataContext();
            

            var command =  "DELETE FROM[SHOPPING_CART_ITEMS]  WHERE ItemID = " + Request.QueryString["ItemID"] + "; ";
            db.ExecuteCommand(command);

            Response.Redirect("ShoppingCart.aspx");
        }
    }
}