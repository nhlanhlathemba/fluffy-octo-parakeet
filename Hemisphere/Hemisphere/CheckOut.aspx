<%@ Page Title="" Language="C#" MasterPageFile="~/Site1.Master" AutoEventWireup="true" CodeBehind="CheckOut.aspx.cs" Inherits="group.CheckOut" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <center><h1>Check Out</h1></center>
     <div id="checkOutDiv" runat="server">

    </div>
    <asp:Button ID="btnCheckOut" class="btn btn-primary" runat="server" Text="Check Out" OnClick="btnCheckOut_Click"/>
</asp:Content>
