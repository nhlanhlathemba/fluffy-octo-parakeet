using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Hemisphere
{
    public partial class ProductUpdate : System.Web.UI.Page
    {
        private int quan;
        protected void Page_Load(object sender, EventArgs e)
        {
          if(!IsPostBack)  {
                var db = new GroupDBDataContext();
                var prodId = (from u in db.PRODUCTs
                              where u.PROD_ID.Equals(Request.QueryString["ProdID"])
                              select u).FirstOrDefault();
               
                {
                    //   foreach (PRODUCT p in prodId)
                    {
                        txtProductName.Text = prodId.PROD_NAME;
                        txtProductPrice.Text = Convert.ToString(prodId.PROD_PRICE);
                        txtCategory.Text = prodId.PROD_CATEGORY;
                        txtDescription.Text = prodId.PROD_DESCRIPTION;
                        txtProductQuantity.Text = prodId.PROD_QUANTITY_AVAILABLE.ToString();
                        txtImage.Text = prodId.PROD_IMAGE_PATH;
                        //  quan = p.PROD_QUANTITY_AVAILABLE;
                    }
                }
            }
        }

        protected void Update_Click(object sender, EventArgs e)
        {
            if (IsPostBack)
            {

                var db = new GroupDBDataContext();
                var use = from u in db.PRODUCTs
                          where u.PROD_ID.Equals(Request.QueryString["ProdID"])
                          select u;
                foreach (PRODUCT p in use)
                {
                    p.PROD_NAME = txtProductName.Text;
                    p.PROD_PRICE=Convert.ToDouble(txtProductPrice.Text);
                    p.PROD_CATEGORY= txtCategory.Text;
                    p.PROD_DESCRIPTION= txtDescription.Text;
                    p.PROD_QUANTITY_AVAILABLE =Convert.ToInt32(txtProductQuantity.Text);
                    p.PROD_IMAGE_PATH = txtImage.Text;

                }
                try
                {
                    db.SubmitChanges();
                    Response.Redirect("Home.aspx");
                }
                catch (Exception ex)
                {
                    ex.GetBaseException();

                }
            }
        }
    }
}