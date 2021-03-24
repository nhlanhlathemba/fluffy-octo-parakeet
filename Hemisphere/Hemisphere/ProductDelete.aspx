<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="ProductDelete.aspx.cs" Inherits="Hemisphere.ProductionDelete" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <center><h1>Product Deletion</h1></center>

    <table class="nav-justified">
        
        <tr>
            <td style="width: 281px"><b>Product Name:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtProductName">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtProductName" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px; height: 55px;"><b>Product Price:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtProductPrice">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px; height: 55px;">
                <asp:TextBox ID="txtProductPrice" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Category:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtCategory">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtCategory" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Description:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtDescription">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtDescription" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Quantity:</b>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtProductQuantity">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtProductQuantity" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Image Path:</b>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtImage" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
    </table>
    <br />
    <br />
    <asp:Button ID="btnDelete" runat="server" Text="Delete Product" Width="134px" class="btn btn-danger" type="submit" OnClick="btnDelete_Click" />  
    <br/>
    <br/>
</asp:Content>