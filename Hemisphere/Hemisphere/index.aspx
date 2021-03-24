<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site1.Master" CodeBehind="index.aspx.cs" Inherits="Hemisphere.index" %>

<asp:Content ID ="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <asp:LinkButton ID="LinkButtonSortByPrice" runat="server" OnClick="LinkButtonSortByPrice_Click">Sort By Price [L-H]</asp:LinkButton>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      <asp:LinkButton ID="LinkButtonName" runat="server" OnClick="LinkButtonName_Click">Sort by Name [A-Z]</asp:LinkButton>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:LinkButton ID="btnAllProd" runat="server" OnClick="btnAllProd_Click">View all products [A-Z]</asp:LinkButton> 
  <h2><a href="NavList3.aspx">TextBooks</a></h2>     
    
    <hr  style="height:1px; background-color:#000000; border:0"/>
     <div id="Textbookdiv" runat="server" style="width:auto;height:auto ;overflow:auto;padding:5px;">
                     
     </div>

       <h2><a href="NavList2.aspx">Novels</a></h2>
    <hr  style="height:1px; background-color:#000000; border:0"/>
      <div id="Noveldiv" runat="server" style="width:auto;height:auto ;overflow:auto;padding:5px;">
                      

     </div>
   
              <h2><a href="NavList.aspx">Books</a></h2>  
    <hr  style="height:1px; background-color:#000000; border:0"/>
      <div id="BooksDiv" runat="server" style="width:auto;height:auto ;overflow:auto;padding:5px;">
     </div>   

</asp:Content>
