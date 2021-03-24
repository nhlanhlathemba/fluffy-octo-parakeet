<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="CommentReport.aspx.cs" Inherits="Hemisphere.CommentReport" %>

<%@ Register Assembly="Microsoft.ReportViewer.WebForms" Namespace="Microsoft.Reporting.WebForms" TagPrefix="rsweb" %>


<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>
    <rsweb:ReportViewer ID="ReportViewer1" runat="server" Font-Names="Verdana" Font-Size="8pt" WaitMessageFont-Names="Verdana" WaitMessageFont-Size="14pt"  SizeToReportContent="true" OnPreRender="Page_Load" AsyncRendering="false" ShowBackButton="False" ShowCredentialPrompts="False" ShowDocumentMapButton="False" ShowExportControls="False" ShowFindControls="False" ShowPageNavigationControls="False" ShowParameterPrompts="False" ShowPrintButton="False" ShowPromptAreaButton="False" ShowRefreshButton="False" ShowReportBody="True" ShowToolBar="False" ShowWaitControlCancelLink="False" ShowZoomControl="False">
        <LocalReport ReportPath="CommentReport.rdlc">
            <DataSources>
                <rsweb:ReportDataSource Name="DataSetComment" DataSourceId="ObjectDataSource1"></rsweb:ReportDataSource>
            </DataSources>
        </LocalReport>
    </rsweb:ReportViewer>

    <asp:ObjectDataSource runat="server" SelectMethod="GetData" TypeName="Hemisphere.HemisphereDBDataSetCommentTableAdapters.COMMENTTableAdapter" ID="ObjectDataSource1" ></asp:ObjectDataSource>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server"></asp:SqlDataSource>
</asp:Content>

