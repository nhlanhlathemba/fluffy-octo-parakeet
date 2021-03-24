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
    public partial class index : System.Web.UI.Page
    {

        protected void Page_Load(object sender, EventArgs e)
        {
         
            string Adminlevel = "A";
            String ProdTextbooks = "";
            String ProdBooks = "";
            String ProdNovels = "";

            int BCounter = 0;
            int TBCounter = 0;
            int NCounter = 0;

            var db = new GroupDBDataContext();

            dynamic list = from u in db.PRODUCTs
                           select u;

            //   if (reader.HasRows)
            {
                ProdTextbooks += "<div class='card-group'>";
                ProdBooks +="<div class='card-group'>";
                ProdNovels +="<div class='card-group'>";
               

                foreach (PRODUCT p in list)
                {
                    if (p.PROD_CATEGORY.Equals("TextBooks"))
                    {


                        ProdTextbooks += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                        ProdTextbooks += " <h3 class='card-header'>" +p.PROD_NAME +" </h3>";
                        ProdTextbooks += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                        ProdTextbooks += "<p class='card-text'>Price : R"+ p.PROD_PRICE+" </p>  </div><div class='card-body'>";
                        ProdTextbooks += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";
                        
                        if (Session["Username"]!=null)
                        {
                            if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                            {
                                ProdTextbooks += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                ProdTextbooks += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                            }
                               
                        }
                        ProdTextbooks += "</div>";
                        TBCounter++;                     
                        if (TBCounter == 6)
                        {
                            ProdTextbooks += "</div>";
                            ProdTextbooks += "<div class='card-group'>";
                            TBCounter = 0;
                        }
                       



                    }



                    if (p.PROD_CATEGORY.Equals("Books"))
                    {

                        ProdBooks += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                        ProdBooks += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";
                        ProdBooks += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                        ProdBooks += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p>  </div><div class='card-body'>";
                        ProdBooks += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";

                        if (Session["Username"] != null)
                        {
                            if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                            {
                                ProdBooks += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                ProdBooks += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                            }

                        }

                        ProdBooks += "</div>";
                        BCounter++;
                        if (BCounter == 6)
                        {
                            ProdBooks += "</div>";
                            ProdBooks += "<div class='card-group'>";
                            BCounter = 0;
                        }

                    }

                    if (p.PROD_CATEGORY.Equals("Novels"))
                    {

                        ProdNovels += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                        ProdNovels += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";
                        ProdNovels += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                        ProdNovels += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p>  </div><div class='card-body'>";
                        ProdNovels += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";

                        if (Session["Username"] != null)
                        {
                            if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                            {
                                ProdNovels += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                ProdNovels += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                            }

                        }

                        ProdNovels += "</div>";
                        NCounter++;
                        if (NCounter == 6)
                        {
                            ProdNovels += "</div>";
                            ProdNovels += "<div class='card-group'>";
                            NCounter = 0;
                        }
                    } 
                }
                

            

            }

            BooksDiv.InnerHtml += ProdBooks;
            Noveldiv.InnerHtml += ProdNovels; 
            Textbookdiv.InnerHtml += ProdTextbooks;

         
        }

        protected void LinkButtonSortByPrice_Click(object sender, EventArgs e)
        {
            int BCounter = 0;
            int TBCounter = 0;
            int NCounter = 0;
            Textbookdiv.InnerHtml = null;
            BooksDiv.InnerHtml = null;
            Noveldiv.InnerHtml = null;
            if (Page.IsPostBack)
            {
                string Adminlevel = "A";
                var db = new GroupDBDataContext();

                var list = from u in db.PRODUCTs
                           orderby u.PROD_PRICE ascending
                           select u;
                String ProdBooks = "";
                String ProdNovels = "";
                String ProdTextbooks = "";

                // if (reader.HasRows)
                {
                    ProdTextbooks += "<div class='card-group'>";
                    ProdBooks += "<div class='card-group'>";
                    ProdNovels += "<div class='card-group'>";


                    foreach (PRODUCT p in list)
                    {
                        if (p.PROD_CATEGORY.Equals("TextBooks"))
                        {


                            ProdTextbooks += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                            ProdTextbooks += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";
                            ProdTextbooks += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                            ProdTextbooks += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p>  </div><div class='card-body'>";
                            ProdTextbooks += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";

                            if (Session["Username"] != null)
                            {
                                if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                                {
                                    ProdTextbooks += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                    ProdTextbooks += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                                }

                            }
                            ProdTextbooks += "</div>";
                            TBCounter++;
                            if (TBCounter == 6)
                            {
                                ProdTextbooks += "</div>";
                                ProdTextbooks += "<div class='card-group'>";
                                TBCounter = 0;
                            }




                        }



                        if (p.PROD_CATEGORY.Equals("Books"))
                        {

                            ProdBooks += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                            ProdBooks += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";
                            ProdBooks += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                            ProdBooks += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p>  </div><div class='card-body'>";
                            ProdBooks += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";

                            if (Session["Username"] != null)
                            {
                                if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                                {
                                    ProdBooks += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                    ProdBooks += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                                }

                            }

                            ProdBooks += "</div>";
                            BCounter++;
                            if (BCounter == 6)
                            {
                                ProdBooks += "</div>";
                                ProdBooks += "<div class='card-group'>";
                                BCounter = 0;
                            }

                        }

                        if (p.PROD_CATEGORY.Equals("Novels"))
                        {

                            ProdNovels += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                            ProdNovels += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";
                            ProdNovels += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                            ProdNovels += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p>  </div><div class='card-body'>";
                            ProdNovels += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";

                            if (Session["Username"] != null)
                            {
                                if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                                {
                                    ProdNovels += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                    ProdNovels += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                                }

                            }

                            ProdNovels += "</div>";
                            NCounter++;
                            if (NCounter == 6)
                            {
                                ProdNovels += "</div>";
                                ProdNovels += "<div class='card-group'>";
                                NCounter = 0;
                            }
                        }
                    }




                }

                BooksDiv.InnerHtml += ProdBooks;
                Noveldiv.InnerHtml += ProdNovels;
                Textbookdiv.InnerHtml += ProdTextbooks;
              
            }
        }

        protected void btnAllProd_Click(object sender, EventArgs e)
        {
            Response.Redirect("prodList.aspx");
        }

            protected void LinkButtonName_Click(object sender, EventArgs e)
            {
            Textbookdiv.InnerHtml = null;
            BooksDiv.InnerHtml = null;
            Noveldiv.InnerHtml = null;

            string Adminlevel = "A";
            int BCounter = 0;
            int TBCounter = 0;
            int NCounter = 0;

            if (Page.IsPostBack)
            {
                var db = new GroupDBDataContext();

                var list = from u in db.PRODUCTs
                           orderby u.PROD_NAME ascending
                           select u;

                String ProdBooks = "";
                String ProdNovels = "";
                String ProdTextbooks = "";

              
                    ProdTextbooks += "<div class='card-group'>";
                    ProdBooks += "<div class='card-group'>";
                    ProdNovels += "<div class='card-group'>";


                    foreach (PRODUCT p in list)
                    {
                        if (p.PROD_CATEGORY.Equals("TextBooks"))
                        {


                            ProdTextbooks += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                            ProdTextbooks += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";
                            ProdTextbooks += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                            ProdTextbooks += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p>  </div><div class='card-body'>";
                            ProdTextbooks += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";

                            if (Session["Username"] != null)
                            {
                                if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                                {
                                    ProdTextbooks += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                    ProdTextbooks += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                                }

                            }
                            ProdTextbooks += "</div>";
                            TBCounter++;
                            if (TBCounter == 6)
                            {
                                ProdTextbooks += "</div>";
                                ProdTextbooks += "<div class='card-group'>";
                                TBCounter = 0;
                            }




                        }



                        if (p.PROD_CATEGORY.Equals("Books"))
                        {

                            ProdBooks += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                            ProdBooks += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";
                            ProdBooks += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                            ProdBooks += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p>  </div><div class='card-body'>";
                            ProdBooks += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";

                            if (Session["Username"] != null)
                            {
                                if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                                {
                                    ProdBooks += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                    ProdBooks += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                                }

                            }

                            ProdBooks += "</div>";
                            BCounter++;
                            if (BCounter == 6)
                            {
                                ProdBooks += "</div>";
                                ProdBooks += "<div class='card-group'>";
                                BCounter = 0;
                            }

                        }

                        if (p.PROD_CATEGORY.Equals("Novels"))
                        {

                            ProdNovels += "<div class='card mb-3' style = 'max-width: 20rem;'>";
                            ProdNovels += " <h3 class='card-header'>" + p.PROD_NAME + " </h3>";
                            ProdNovels += " <img src = 'images/" + p.PROD_IMAGE_PATH + "'" + "width='150px' height='200px'" + "/> <div class='card-body'>";
                            ProdNovels += "<p class='card-text'>Price : R" + p.PROD_PRICE + " </p>  </div><div class='card-body'>";
                            ProdNovels += "<a  href='ProductInfo.aspx?ProdID=" + p.PROD_ID + "' class='card-link'>more info</a> </div> ";

                            if (Session["Username"] != null)
                            {
                                if (Session["USER_AUTHENTICATION_LEVEL"].ToString() == Adminlevel)
                                {
                                    ProdNovels += "<a  class = 'card-link txt' href='ProductUpdate.aspx?ProdID=" + p.PROD_ID + "'>" + "Update" + "</a>";
                                    ProdNovels += "<a  class = 'card-link txt' href='ProductDelete.aspx?ProdID=" + p.PROD_ID + "'>" + "Delete" + "</a>";
                                }

                            }

                            ProdNovels += "</div>";
                            NCounter++;
                            if (NCounter == 6)
                            {
                                ProdNovels += "</div>";
                                ProdNovels += "<div class='card-group'>";
                                NCounter = 0;
                            }
                        }
                    }




                

                BooksDiv.InnerHtml += ProdBooks;
                Noveldiv.InnerHtml += ProdNovels;
                Textbookdiv.InnerHtml += ProdTextbooks;
               

            }
        }
    }
}