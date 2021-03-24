<%@ Page Title="ShoppingCart" Language="C#" MasterPageFile="~/Site1.Master" AutoEventWireup="true" CodeBehind="ShoppingCart.aspx.cs" Inherits="group.ShoppingCart" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
         <center><h1>Shopping Cart</h1>
    <br/>
    <div id="shoppingCartDiv" runat="server" visible="false">
        <asp:TextBox ID="txtprodID" runat="server" Text="yaj"></asp:TextBox>
    </div>
    <br/>
        <asp:Button ID="btnUpdate" runat="server" Text="Update" CssClass="btn btn-primary" OnClick="btnUpdate_Click" Visible="False"/>  <asp:Button ID="btnCheckOut" Visible="false" runat="server" Text="CheckOut" CssClass="btn btn-primary" OnClick="btnCheckOut_Click"/>
             </center>
</asp:Content>
