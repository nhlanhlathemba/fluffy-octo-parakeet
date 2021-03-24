<%@ Page Language="C#" MasterPageFile="~/Site1.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="Hemisphere.Login" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <center><h1>Login</h1>
    <br />
        <br />
    <table>
        <tr>
            <td>
                <label class="txt" for="lblUsername">Email: </label>
                <asp:RequiredFieldValidator class ="txt" ID="RequiredFieldValidator1" runat="server" ErrorMessage="Required field" ControlToValidate="txtUserName" ForeColor="Red">*</asp:RequiredFieldValidator>
            </td>
            <td>
                <asp:TextBox runat="server" class ="txt" CssClass="form-control txt" ID="txtUserName" type="text" placeholder="Email here" Height="49px" Font-Size="Small"></asp:TextBox>
                <asp:RegularExpressionValidator class ="txt" ID="RegularExpressionValidator1" runat="server" ErrorMessage="E-mail required" ControlToValidate="txtUserName" ForeColor="Red" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*">E-mail required</asp:RegularExpressionValidator>
            </td>
        </tr>
        <tr>
            <td>
                <label class="txt" for="lblPassword">Password: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" class ="txt" ErrorMessage="Required field" ControlToValidate="txtPassword" ForeColor="Red">*</asp:RequiredFieldValidator>
            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtPassword" type="password" placeholder="Password here" class ="txt" Height="50px" Font-Size="Small"></asp:TextBox>
                <br/>
            </td>
        </tr>

    </table>
    <asp:Button ID="btnLogin" type="submit" CssClass="btn btn-primary txt" class ="txt" OnClick="btnLogin_Click" runat="server" Text="Login" Height="53px" Width="102px" Font-Size="Small"></asp:Button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class ="txt" href="PasswordReset.aspx"> Reset Password</a>
    <br/><br/>
    <asp:Label ID="lblError" runat="server" Text="" CssClass="label label-danger"></asp:Label>
        </center>
</asp:Content>