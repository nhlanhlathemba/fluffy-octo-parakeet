<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="MyAccount.aspx.cs" Inherits="Hemisphere.MyAccount" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <asp:Label ID="lblCannotViewPageContent" style="font-size:large;" runat="server" CssClass="label label-danger" Height="30px" Width="100%"></asp:Label>
    <center>
    <div id="myProfileDiv" runat="server">
       
        <h1>My Account</h1>
    <table>
        <tr>
            <td style="width: 112px">
                <label for="lblSurname">Surname: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator7" runat="server" ControlToValidate="txtsurname" ErrorMessage="Required field" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtsurname" type="text" placeholder="Surname here"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label for="lblName">Name: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator6" runat="server" ControlToValidate="txtName" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtName" type="text" placeholder="First name here"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label for="lblgender">Gender: (M/F)</label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator8" runat="server" ControlToValidate="GenderDropDown" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:DropDownList ID="GenderDropDown" runat="server">
                    <asp:ListItem>Select Gender</asp:ListItem>
                    <asp:ListItem Text="M"></asp:ListItem>
                    <asp:ListItem Text="F"></asp:ListItem>
                </asp:DropDownList>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label for="lblPhoneNumber">Phone Number: </label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="txtPhone" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtPhone" type="text" placeholder="Phone number here e.g.(07123456789)"></asp:TextBox>
            </td>
        </tr>

        <tr>
            <td style="width: 112px">
                <label for="lblEmail">Email:</label>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="txtEmail" ErrorMessage="Field required" ForeColor="Red">*</asp:RequiredFieldValidator>

            </td>
            <td>
                <asp:TextBox runat="server" CssClass="form-control" ID="txtEmail" type="text" placeholder="E-mail here"></asp:TextBox>
                <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" ControlToValidate="txtEmail" ErrorMessage="Enter e-mail" ForeColor="Red" ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*">Enter e-mail</asp:RegularExpressionValidator>
            </td>
        </tr>

    </table>

    
    <asp:Button ID="btnUpdate" type="submit" CssClass="btn btn-primary" OnClick="btnSumbit_Click" runat="server" Text="Update Info" Width="147px"></asp:Button>
        <asp:Button ID="btnChangePassword"   type="submit" CssClass="btn btn-primary"  runat="server" Text="Change Password"   Width="147px" OnClick="btnChangePassword_Click" ></asp:Button>
    
    <br />
        <br/>
        <asp:Label ID="lblSucces" CssClass="label label-success" runat="server" Text="Update Successful!!"></asp:Label>
        <br/>
        <asp:Label ID="lblSucces0" CssClass="alert-warning" runat="server" Text="Update failed"></asp:Label>
        <br />
    </div>
    </center>
</asp:Content>
