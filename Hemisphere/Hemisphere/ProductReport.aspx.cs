using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Microsoft.Reporting.WebForms;



namespace Hemisphere
{
    public partial class ProductReport : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            ReportViewer1.LocalReport.Refresh();
            ReportParameter parass;
            parass = new ReportParameter("ReportParameterNumProducts");
           
           
            this.ReportViewer1.LocalReport.ReportPath = "ProductReport.rdlc";

        }
    }
}