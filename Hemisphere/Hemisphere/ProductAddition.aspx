<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="ProductAddition.aspx.cs" Inherits="Hemisphere.ProductionAddition" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <center><h1>Product Addition </h1></center>

    <table class="nav-justified">
        
        <tr>
            <td style="width: 281px"><b>Product Name:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtProductName">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtProductName" runat="server" class="form-control" Width="233px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Price:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtProductPrice">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtProductPrice" runat="server" class="form-control" Width="233px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Category:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="ProdCatDropDown">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:DropDownList ID="ProdCatDropDown" runat="server">
                    <asp:ListItem >Select Category</asp:ListItem>
                    <asp:ListItem Text="Books"></asp:ListItem>
                    <asp:ListItem Text="Novels"></asp:ListItem>
                    <asp:ListItem Text="TextBooks"></asp:ListItem>
                </asp:DropDownList>
               
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Description:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtDescription">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtDescription" runat="server" class="form-control" Width="233px" TextMode="MultiLine"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Quantity:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtProductQuantity">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtProductQuantity" runat="server" class="form-control" Width="233px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Product Image Name:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator14" runat="server" ControlToValidate="txtImage" ErrorMessage="Required field" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtImage" runat="server" class="form-control" Width="233px"></asp:TextBox>
            </td>
        </tr>

         <tr>
            <td style="width: 281px"><b>Product ISBN:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtISBN">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtISBN" runat="server" class="form-control" Width="233px"></asp:TextBox>
            </td>
        </tr>

         <tr>
            <td style="width: 281px"><b>Author:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator7" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtAuthor">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtAuthor" runat="server" class="form-control" Width="233px"></asp:TextBox>
            </td>
        </tr>
    </table>
    <br />
    <br />
    <asp:Button ID="btnAddProduct" runat="server" Text="Add Product" Width="134px" class="btn btn-primary" type="button" OnClick="btnAddProduct_Click" />
    <asp:Button ID="Button1" runat="server" Text="Reset Form" Width="134px" class="btn btn-default" type="submit" OnClick="btnReset_Click" />
    <br />
    <br />
    <asp:Label ID="lblProdAdd" CssClass="label label-success" runat="server" Text="Product Successfully Added"></asp:Label>


</asp:Content>
