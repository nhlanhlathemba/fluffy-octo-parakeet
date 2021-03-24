<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="ProductReport.aspx.cs" Inherits="Hemisphere.ProductReport" %>

<%@ Register Assembly="Microsoft.ReportViewer.WebForms" Namespace="Microsoft.Reporting.WebForms" TagPrefix="rsweb" %>



<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
   <div id="RepDiv" runat="server">
        <h1> Product Reporting</h1>
        <asp:TextBox ID="txtNuProd" runat="server"></asp:TextBox>

        <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>
       <rsweb:ReportViewer ID="ReportViewer1" runat="server" Font-Names="Verdana" Font-Size="8pt" WaitMessageFont-Names="Verdana" WaitMessageFont-Size="14pt" Width="20%" Height="500%" SizeToReportContent="true" OnPreRender="Page_Load" AsyncRendering="false">
            <LocalReport ReportPath="ProductReport.rdlc">
                <DataSources>
                    <rsweb:ReportDataSource Name="DataSet1" DataSourceId="ObjectDataSource1"></rsweb:ReportDataSource>
                </DataSources>
            </LocalReport>
        </rsweb:ReportViewer>
    </div>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server"></asp:SqlDataSource>
        <asp:ObjectDataSource runat="server" SelectMethod="GetData" TypeName="group.DataSetProductsTableAdapters.PRODUCTTableAdapter" ID="ObjectDataSource1"></asp:ObjectDataSource>
</asp:Content>
