using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;
using System.IO;

namespace Hemisphere
{
    public partial class ProductionAddition : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            lblProdAdd.Visible = false;
            if (Page.IsPostBack)
            {
                lblProdAdd.Visible = true;
            }

        }

        protected void btnAddProduct_Click(object sender, EventArgs e)
        {
            SqlConnection connection = null;
            SqlCommand command = null;
            var db = new GroupDBDataContext();
            dynamic use = from u in db.PRODUCTs
                          select u;

            var newProd = new PRODUCT
            {

                PROD_NAME = txtProductName.Text,

                PROD_PRICE = Convert.ToDouble(txtProductPrice.Text),
                PROD_CATEGORY = ProdCatDropDown.SelectedValue,
                PROD_DESCRIPTION = txtDescription.Text,
                PROD_QUANTITY_AVAILABLE = Convert.ToInt32(txtProductQuantity.Text),
                PROD_IMAGE_PATH = txtImage.Text,
                PROD_ISBN = txtISBN.Text,
                PROD_AUTHOR = txtAuthor.Text

            };
            db.PRODUCTs.InsertOnSubmit(newProd);
            try
            {
                db.SubmitChanges();
                lblProdAdd.Visible = true;

            }
            catch (IOException E)
            {
                E.GetBaseException();
            }
            Response.Redirect("ProductAddition.aspx");


        }

        protected void btnReset_Click(object sender, EventArgs e)
        {
            lblProdAdd.Visible = false;
            ProdCatDropDown.SelectedIndex = 0;
            txtDescription.Text = "";
            txtProductName.Text = "";
            txtProductPrice.Text = "";
            txtProductQuantity.Text = "";
            txtAuthor.Text = "";
            txtISBN.Text = "";
        }
    }
}