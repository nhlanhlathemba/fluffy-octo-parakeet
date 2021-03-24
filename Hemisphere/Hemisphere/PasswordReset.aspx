<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="PasswordReset.aspx.cs" Inherits="Hemisphere.PasswordReset" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
          <center><h1>Password Reset</h1></center>
    <br/>
    <table>
        <tr>
            <td>
                <label for="lblUsername">Username: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Required field" ControlToValidate="txtUserName" ForeColor="Red">*</asp:RequiredFieldValidator>
            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtUserName" type="text" placeholder="Email here"></asp:TextBox>
                <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" ErrorMessage="E-mail required" ControlToValidate="txtUserName" ForeColor="Red" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*">E-mail required</asp:RegularExpressionValidator>
            </td>
        </tr>
        <tr>
            <td style="width: 112px">
                <label id="lblSQ" runat="server">Security Question: </label>
            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtSecurityQuiz" placeholder="Security question here" TextMode="MultiLine" ReadOnly="True"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label id="lblSA" runat="server">Answer: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator9" runat="server" ControlToValidate="txtAnswer" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>
            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtAnswer" placeholder="Answer to security question here" TextMode="MultiLine"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label id="lblPassword1" runat="server">Password: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ControlToValidate="txtPassword1" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtPassword1" type="password" placeholder="Password here"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label id="lblPassword2" runat="server">Confirm Password: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ControlToValidate="txtPassword2" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" type="password" ID="txtPassword2" placeholder="Confirm password here"></asp:TextBox>
                <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToCompare="txtPassword1" ControlToValidate="txtPassword2" ErrorMessage="Passwords not the same" ForeColor="Red">Passwords not the same</asp:CompareValidator>
            </td>
        </tr>
    </table>

    <asp:Button ID="Button2" runat="server" Text="Next" CssClass="btn btn-danger" OnClick="Button2_Click" />
    <asp:Button ID="Button1" runat="server" Text="Submit"  CssClass="btn btn-primary" OnClick="Button1_Click"/>
    <br/><br/>
    <asp:Label ID="lblError" runat="server" Text="" CssClass="label label-danger"></asp:Label>
</asp:Content>
