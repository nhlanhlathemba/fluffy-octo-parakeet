<%@ Page Language="C#" MasterPageFile="~/Site1.Master" AutoEventWireup="true" CodeBehind="Registration.aspx.cs" Inherits="Hemisphere.Login_and_Registration.Registration" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <center>
    <h1>Sign Up</h1>
    
    <table>
        <tr>
            <td style="width: 112px">
                <label class="txt" for="lblSurname">Surname: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator7" CssClass="txt" runat="server" ControlToValidate="txtsurname" ErrorMessage="Required field" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" class ="txt" CssClass="form-control txt" ID="txtSurname" type="text" placeholder="Surname here" Height="49px" Font-Size="Small"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label class ="txt" for="lblName">Name: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" CssClass="txt" ControlToValidate="txtName" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                 <asp:TextBox runat="server" class ="txt" CssClass="form-control txt" ID="txtName" type="text" placeholder="First name here" Height="49px" Font-Size="Small"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label class="txt" for="lblgender">Gender: (M/F)</label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator8" runat="server" ControlToValidate="GenderDropDown" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:DropDownList CssClass="txt" ID="GenderDropDown" runat="server">

                    <asp:ListItem >Select Gender</asp:ListItem>

                    <asp:ListItem Text="M"></asp:ListItem>
                    <asp:ListItem Text="F"></asp:ListItem>
                </asp:DropDownList>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label class ="txt" for="lblPhoneNumber">Phone Number: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator5" CssClass="txt" runat="server" ControlToValidate="txtPhone" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                 <asp:TextBox runat="server" class ="txt" CssClass="form-control txt" ID="txtPhone" type="text" placeholder="Phone number here" Height="49px" Font-Size="Small"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label class ="txt" for="lblEmail">Email:</label>
                <asp:RequiredFieldValidator CssClass="txt" ID="RequiredFieldValidator2" runat="server" ControlToValidate="txtEmail" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control txtbox" ID="txtEmail" type="text" placeholder="E-mail here" Height="49px" Font-Size="Small"></asp:TextBox>
                <asp:RegularExpressionValidator CssClass="txt" ID="RegularExpressionValidator1" runat="server" ControlToValidate="txtEmail" ErrorMessage="Enter e-mail" ForeColor="Red" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*">Enter e-mail</asp:RegularExpressionValidator>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label class="txt" for="lblPassword1">Password: </label>
                <asp:RequiredFieldValidator CssClass="txt" ID="RequiredFieldValidator3" runat="server" ControlToValidate="txtPassword1" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control txtbox" ID="txtPassword1" type="password" placeholder="Password here" Height="49px" Font-Size="Small"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label class="txt" for="lblPassword2">Confirm Password: </label> 
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ControlToValidate="txtPassword2" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" type="password" ID="txtPassword2" placeholder="Confirm password here" Height="49px" Font-Size="Small"></asp:TextBox>
                <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToCompare="txtPassword1" ControlToValidate="txtPassword2" ErrorMessage="Passwords not the same" ForeColor="Red">Passwords not the same</asp:CompareValidator>
            </td>
        </tr>

         <tr>
            <td style="width: 112px">
                <label class="txt" for="lblSecurity">Security Question: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtSecurityQuiz" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtSecurityQuiz" placeholder="Security question here" TextMode="MultiLine"></asp:TextBox>
            </td>
        </tr>
         <tr>
            <td style="width: 112px">
                <label class="txt" for="lblAnswer">Answer: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator9" runat="server" ControlToValidate="txtAnswer" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtAnswer" placeholder="Answer to security question here" TextMode="MultiLine" Height="49px" Font-Size="Small"></asp:TextBox>
            </td>
        </tr>
    </table>

    <p class="txt">
        By clicking on "Register" you agree to the  <a href="AboutUs.aspx">Terms and Conditions.</a>
    </p>


    <asp:Button ID="Button1" type="submit" CssClass="btn btn-primary" OnClick="btnSumbit_Click" runat="server" Text="Register" Height="53px" Width="102px" Font-Size="Small"></asp:Button>
    <asp:Button ID="btnReset" type="submit" CssClass="btn btn-default" OnClick="btnReset_Click" runat="server" Text="Reset Form"  Height="53px" Width="102px" Font-Size="Small" /><br />
    <br />

    <span align="right" class="label label-success">
        <asp:Label ID="lblSucces" runat="server" Text="Registration Successful!!" EnableViewState="False"></asp:Label></span>
    <span align="right" class="label label-warning">
    <asp:Label ID="lblAccountExists" runat="server" Text="" EnableViewState="False"></asp:Label></span>
    <div runat="server" id ="report"></div>


</center>
</asp:Content>

