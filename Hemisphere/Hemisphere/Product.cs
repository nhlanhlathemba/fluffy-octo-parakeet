using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Hemisphere
{
    public class Product
    {

        public int Id { get; set; }
        public decimal Price { get; set; }
        public string Description { get; set; }

        public Product(int id)
        {
            var db = new GroupDBDataContext();
            var use = (from u in db.PRODUCTs
                       where u.PROD_ID.Equals(id)
                       select u).FirstOrDefault();
            this.Id = id;
           // switch (id)
            {
               // case 1:
                    this.Price =Convert.ToInt32(use.PROD_PRICE);
                    this.Description = use.PROD_DESCRIPTION;
              //      break;
              //  case 2:
                  //  this.Price = 9.95m;
                   // this.Description = "Shirt";
              //      break;
              //  case 3:
                   // this.Price = 14.95m;
                   // this.Description = "Pants";
                 //   break;
            }
        }

    }
}