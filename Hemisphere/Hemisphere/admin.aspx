<%@ Page Title="" Language="C#" MasterPageFile="~/Site1.Master" AutoEventWireup="true" CodeBehind="admin.aspx.cs" Inherits="Hemisphere.admin" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <div id="adminBar"  visible="true" runat="server" style="padding-top: -19px 0 0 0">
             <h4>Administration Menu</h4><br />
                        <ul>
                        
                            
                                <li class="txt"><b>Product Management</b></li>
                                <li class="txt"><b><a href="ProductAddition.aspx">Product Addition</a></b></li><br />

                                <li class="txt"><b>User Management</b></li>
                                <li class="txt"><b><a href="Users.aspx">User Database</a></b></li>
                             <br />

                              <li class="txt"><b>Comments</b></li>
                              <li class="txt"><b><a href="ViewComments.aspx">View Comments</a></b></li>

                            <br />

                              <li class="txt"><b>Reporting</b></li>
                              <li class="txt"><b><a href="ProductReport.aspx">View Product Report</a></b></li>
                              <li class="txt"><b><a href="CommentReport.aspx">View Commenting Report</a></b></li>
                              <li class="txt"><b><a href="UserReport.aspx">View User Report</a></b></li>
                             
                        </ul>
     </div>
</asp:Content>
