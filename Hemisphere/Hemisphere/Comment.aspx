<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="Comment.aspx.cs" Inherits="Hemisphere.Comment" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <asp:Label ID="lblCannotViewPageContent" style="font-size:large;" runat="server" CssClass="label label-danger" Height="30px" Width="100%"></asp:Label>
    <div id="commentDiv" runat="server">
        <center><h1>Comment ...</h1>
    <form class="form-inline" role="form">
  <div class="form-group">
      <asp:Label ID="lblSubject" runat="server" Text=""><b>Subject:</b></asp:Label>
      <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtSubject" ErrorMessage="Enter comment subject" ForeColor="Red">*</asp:RequiredFieldValidator>
      <asp:TextBox ID="txtSubject" runat="server" placeholder="Subject of the comment" CssClass="form-control"></asp:TextBox>
  </div>
  <div class="form-group">
      <asp:Label ID="lblComment" runat="server" Text=""><b>Comment:</b></asp:Label>
      <asp:TextBox ID="txtComment" runat="server" CssClass="form-control" height="300px" TextMode="MultiLine" placeholder="Comment here..."></asp:TextBox>
  </div>
  <asp:Button class="btn btn-primary" type="submit" ID="Button1" runat="server" OnClick="btnSubmit_Click" Text="Submit"/> <asp:Button class="btn btn-default" type="submit" ID="Button2" runat="server" OnClick="btnReset_Click" Text="Reset Form"/> 
<br />
        <br />
            <asp:Label ID="lblSucces" runat="server" Text="Comment Submitted!!" ><span class="label label-success" style ="text-align : right"></span> </asp:Label>   
</form>
            </center>
    </div>
    
</asp:Content>
