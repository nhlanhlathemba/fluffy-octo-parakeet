<%@ Page Title="" Language="C#" ValidateRequest="false" MasterPageFile="~/Site1.Master" AutoEventWireup="true" CodeBehind="ProductUpdate.aspx.cs" Inherits="Hemisphere.ProductUpdate" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <p>
        <table class="nav-justified">
            <tr>
                <td>
                    <asp:Label ID="Label1" runat="server" Text="Name"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtProductName" runat="server"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="Label2" runat="server" Text="Price"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtProductPrice" runat="server"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>Category</td>
                <td>
                    <asp:TextBox ID="txtCategory" runat="server"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="Label4" runat="server" Text="Description"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtDescription" runat="server"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="Label5" runat="server" Text="Quantity "></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtProductQuantity" runat="server"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="Label6" runat="server" Text="Image"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtImage" runat="server"></asp:TextBox>
                </td>
            </tr>
        </table>
        <br />
        <asp:Button ID="Update" runat="server" CausesValidation="False" OnClick="Update_Click" Text="Update" />
    </p>
</asp:Content>
