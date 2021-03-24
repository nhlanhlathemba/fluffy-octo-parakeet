<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="RemoveUser.aspx.cs" Inherits="Hemisphere.RemoveUser" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
      <center><h1>User Deletion</h1></center>

    <table class="nav-justified">
        
        <tr>
            <td style="width: 281px"><b>Name:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtName">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtName" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Surname:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtSurname">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtSurname" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Gender:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtGender">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtGender" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Phone Number:</b>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtPNumber">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtPNumber" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Email:</b>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ErrorMessage="Required field" ForeColor="Red" ControlToValidate="txtEmail">*</asp:RequiredFieldValidator>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtEmail" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 281px"><b>Authentication Level:</b>

            </td>

            <td style="width: 715px">
                <asp:TextBox ID="txtAuthLevel" runat="server" class="form-control" Width="233px" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>
    </table>
    <br />
    <br />
    <asp:Button ID="btnDelete" runat="server" Text="Delete User" Width="134px" class="btn btn-danger" type="submit" OnClick="btnDelete_Click" />  
    <br/>
    <br/>
</asp:Content>
