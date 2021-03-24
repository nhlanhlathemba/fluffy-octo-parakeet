using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Hemisphere
{
    public class Update
    {
        public void UpdateQuantity(int ProdID, int incr, GroupDBDataContext datb)
        {
            var prod = (from p in datb.SHOPPING_CART_ITEMs
                        where p.ProductID.Equals(ProdID)
                        select p).FirstOrDefault();



            Double total = Convert.ToDouble(incr * prod.ItemPrice);
            var newItem = new SHOPPING_CART_ITEM
            {
                ProductID = prod.ProductID,
                ProductName = prod.ProductName,
                Quantity = incr,
                ItemPrice = prod.ItemPrice,
                TotalPrice = total,
                USER_ID = prod.USER_ID
            };
            var Delcomm = "DELETE FROM [SHOPPING_CART_ITEMS] " + "WHERE ProductID=" + ProdID.ToString() + ";";
            datb.ExecuteCommand(Delcomm);
            datb.SHOPPING_CART_ITEMs.InsertOnSubmit(newItem);

            try
            {
                datb.SubmitChanges();
               
            }
            catch (Exception x)
            {
                x.GetBaseException();
            }



            /*  command = null;
              connection = new SqlConnection();
              connection.ConnectionString = @"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Users\HRMANAGER\Documents\Hemisphere\Hemisphere\App_Data\groupDB.mdf;Integrated Security=True";
              string commandString = "UPDATE [SHOPPING_CART_ITEMS] SET Quantity = "+ incr.ToString()+ "WHERE ProductID=" + ProdID.ToString()+";";
              command = new SqlCommand(commandString, connection);

              command.CommandType = CommandType.Text;
              command.Connection = connection;
              command.Connection.Open();



              command.ExecuteNonQuery();
              command.Connection.Close();
              command.Dispose(); */
        }
    }
}