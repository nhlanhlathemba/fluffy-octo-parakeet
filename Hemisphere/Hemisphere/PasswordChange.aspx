<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="PasswordChange.aspx.cs" Inherits="Hemisphere.PasswordChange" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <center><h1>Change Password</h1></center>
    <br/>
    <table class="nav-justified">
        <tr>
            <td style="width: 243px">
                <b>
                <asp:Label ID="lblCurrentPassword" runat="server" Text="Please Enter Current Password:"></asp:Label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator7" runat="server" ControlToValidate="txtCurrentPassword" ErrorMessage="Required field" ForeColor="Red">*</asp:RequiredFieldValidator>
                </b>
            </td>
            <td>
                <asp:TextBox ID="txtCurrentPassword" class="form-control" runat="server" TextMode="Password"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 243px">
                <b>
                <asp:Label ID="lblNewPassword"  runat="server" Text="Please Enter New Password:"></asp:Label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ControlToValidate="txtNewPassword" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>
                </b>
            </td>
            <td>
                <asp:TextBox ID="txtNewPassword"   class="form-control"  runat="server" TextMode="Password" ControlToCompare="txtNewPassword"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 243px">
                <b>
                <asp:Label ID="lblConfirmNewPassword" runat="server" Text="Confirm new password:"></asp:Label>
                </b>
            </td>
            <td>
                <asp:TextBox ID="txtConfirmNewPassword"   class="form-control" runat="server" TextMode="Password"></asp:TextBox>
                <asp:CompareValidator ID="CompareValidator1" runat="server" ErrorMessage="Passwords not the same" ControlToCompare="txtNewPassword" ControlToValidate="txtConfirmNewPassword" ForeColor="Red">*</asp:CompareValidator>
            </td>
        </tr>
        <tr>
            <td style="width: 243px">
                <asp:Button ID="btnChangePassword"   class="btn btn-primary"  runat="server" Text="Next" Width="159px" OnClick="btnChangePassword_Click" />
            </td>
            <td>
                <asp:Button ID="ChangePassword" runat="server" Text="Change Password" class="btn btn-primary" OnClick="ChangePassword_Click" Width="174px"/>
            </td>
        </tr>
    </table>



</asp:Content>

