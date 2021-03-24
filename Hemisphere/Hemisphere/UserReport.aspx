<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="UserReport.aspx.cs" Inherits="Hemisphere.UserReport" %>


<%@ Register assembly="Microsoft.ReportViewer.WebForms" namespace="Microsoft.Reporting.WebForms" tagprefix="rsweb" %>


<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <asp:ScriptManager ID="Scriptmanager1" runat="server" ></asp:ScriptManager>
    <rsweb:ReportViewer ID="ReportViewer1" runat="server" Font-Names="Verdana" Font-Size="8pt" WaitMessageFont-Names="Verdana" WaitMessageFont-Size="14pt">
        <LocalReport ReportPath="UserReport.rdlc">
            <DataSources>
                <rsweb:ReportDataSource Name="UserDataSet" DataSourceId="ObjectDataSource1" />
            </DataSources>
        </LocalReport>
    </rsweb:ReportViewer>
    <asp:ObjectDataSource runat="server" SelectMethod="GetData" TypeName="Hemishere.HemisphereDBDataSetTableAdapters.USERTableAdapter" ID="ObjectDataSource1"></asp:ObjectDataSource>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server"></asp:SqlDataSource>

</asp:Content>