using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;


namespace Hemisphere
{
    public partial class Invoice : System.Web.UI.Page
    {
        private SqlConnection connection;
        private SqlCommand command;
        private SqlDataReader reader;
        private String Html = "";
        protected void Page_Load(object sender, EventArgs e)
        {




            lblTotalPayment.Visible = false;
            connection = new SqlConnection();
            connection.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\HRMANAGER\Documents\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
            String commandString = "(SELECT [INVOICE].INV_NUMBER, [INVOICE_PRODUCT].PROD_ID,[PRODUCT].PROD_NAME,[INVOICE_PRODUCT].QUANTITY ,  [PRODUCT].PROD_PRICE , [INVOICE].INV_DATE FROM(([INVOICE] INNER JOIN[INVOICE_PRODUCT] ON[INVOICE].INV_NUMBER = [INVOICE_PRODUCT].INV_NUMBER) INNER JOIN[PRODUCT] ON[INVOICE_PRODUCT].PROD_ID = [PRODUCT].PROD_ID )WHERE [INVOICE].USER_ID = " + Session["UserID"].ToString() + ") ORDER BY[INVOICE].INV_NUMBER ASC; ";
            command = new SqlCommand(commandString, connection);
            command.CommandType = CommandType.Text;

            command.Connection = connection;
            command.Connection.Open();
            reader = command.ExecuteReader();

            String ProdHTML = "";

            if (reader.HasRows)
            {

                ProdHTML += "<b>Invoice(s) of " + Session["Title"] + " " + Session["Surname"] + "</b>" + "<b> on " + DateTime.UtcNow + "</b><br/>";
                /*   ProdHTML += "<hr/>";
                   ProdHTML += "<table  border='1'>";
                   ProdHTML += "<th>PRODUCT NAME</th><th>PRICE</th><th>QUANTITY</th><th>TOTAL</th><th>PURCHASE DATE</th></tr>"; 
                  */
                double total = 0;
                double totalPay = 0;
                double InvTotal = 0;
                int Quantity = 0;
                int InvNo = 0;
                while (reader.Read())
                {
                    if (InvNo < (Int32)reader["INV_NUMBER"] && InvNo != 0)
                    {
                        //end of previous invoice
                        ProdHTML += "</table>";
                        ProdHTML += "<b> Books Purchased: R" + InvTotal.ToString() + "</b>";
                        InvTotal = 0;

                        //start of next invoice
                        ProdHTML += "<br><hr>";
                        ProdHTML += "<h3>Invoice no." + reader["INV_NUMBER"] + " </h3>";
                        ProdHTML += "<table  border='1'>";
                        ProdHTML += "<th>PRODUCT NAME</th><th>PRICE</th><th>QUANTITY</th><th>TOTAL</th><th>PURCHASE DATE</th></tr>";
                    }
                    else if (InvNo == 0)
                    {
                        //start of first invoice
                        ProdHTML += "<br><hr>";
                        ProdHTML += "<h3>Invoice no." + reader["INV_NUMBER"] + " </h3>";
                        ProdHTML += "<table  border='1'>";
                        ProdHTML += "<th>PRODUCT NAME</th><th>PRICE</th><th>QUANTITY</th><th>TOTAL</th><th>PURCHASE DATE</th></tr>";
                    }
                    InvNo = (Int32)reader["INV_NUMBER"];
                    Quantity = (Int32)reader["QUANTITY"];
                    double Price = (double)reader["PROD_PRICE"];
                    total = Price * Quantity;
                    totalPay += total;
                    InvTotal += total;
                    ProdHTML += "<tr><td>" + reader["PROD_NAME"] + "</td><td>R " + reader["PROD_PRICE"] + "</td><td>" + Quantity.ToString() + "</td><td>R "
                        + total.ToString() + "</td><td>" + reader["INV_DATE"] + "</td></tr>";// +"<tr>PAYMENT = R " + reader["PAYMENT"] + "</tr>";


                }
                //end of final invoice
                ProdHTML += "</table>";
                ProdHTML += "<b> Books Purchased: R" + InvTotal.ToString() + "</b>";

                lblTotalPayment.Text = "<b> TOTAL PAYMENT = R " + totalPay.ToString() + "</b>";
                lblTotalPayment.Visible = true;

            }
            invoiceDiv.InnerHtml = ProdHTML;
            command.Connection.Close();
            reader.Dispose();
            connection.Close();

        }
    }
}