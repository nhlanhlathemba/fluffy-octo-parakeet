<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="Invoice.aspx.cs" Inherits="Hemisphere.Invoice" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <center><h1>Invoice</h1>
    <div id="invoiceDiv" runat="server">

    </div>
    <br/>
    <asp:Label ID="lblTotalPayment" runat="server" Text=""></asp:Label>
         </center>
</asp:Content>