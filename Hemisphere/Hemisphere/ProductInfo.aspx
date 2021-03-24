<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="ProductInfo.aspx.cs" Inherits="Hemisphere.ProductInfo" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <asp:Label ID="lblCannotViewPageContent" style="font-size:large;" runat="server" CssClass="label label-danger" Height="30px" Width="100%"></asp:Label>
    <div id="productDiv" runat="server">

    </div>
    <br />
    <asp:Button ID="AddToCart" class="btn btn-primary" runat="server" Text="Add To Cart" OnClick="AddToCart_Click" />
</asp:Content>
