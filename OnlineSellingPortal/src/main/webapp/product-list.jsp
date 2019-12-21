<%-- 
    Document   : product-list
    Created on : Dec 21, 2019, 2:15:19 PM
    Author     : ahnaf
--%>



<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"> 
        <style>
            to{
                padding: 10px;
            }

            div{
                width: 60%;
                border: 2px solid black;
                border-radius: 5px;
                background-color: grey;
            }
        </style>


        <title>Product Lists</title>
    </head>

    <body>
    <center>
        <h1>Product Management</h1>
        <br><br>
        <center>

            <form method="post" action="ProductDeleteServlet">
                <b>ID : </b>  <input type="text" name="pId" >
                <br><br>
                <input id="delete" type="submit" value="Delete">  &nbsp;&nbsp;&nbsp;
                <input type="submit" id="update" value="Update" onclick="form.action = 'ProductServlet';">
            </form>

        </center>
        <br><br>
        <h2>
            <a href="product-insert-form.jsp">Add New Product</a>
            &nbsp;&nbsp;&nbsp;
            <a href="ProductServlet" method="POST">List All Products</a>

        </h2>

        <div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>List of Products</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                    <th>Info</th>
                    <th>Image</th>
                    <th>Available</th>
                    <th>Sold</th>
                </tr>

                <%-- Fetching the attributes of the request object 
                  which was previously set by the servlet  
                --%>  
                <%ArrayList<Product> std
                            = (ArrayList<Product>) session.getAttribute("productList");
                    for (Product s : std) {%> 
                <%-- Arranging data in tabular form 
                --%> 
                <tr>

                    <td><%=s.getId()%></td>
                    <td><%=s.getProductName()%></td>
                    <td><%=s.getProductType()%></td>
                    <td><%=s.getPrice()%></td>
                    <td><%=s.getProductInfo()%></td>
                    <td><%=s.getImageSource()%></td>
                    <td><%=s.getAvailableQuantity()%></td>
                    <td><%=s.getSoldQuantity()%></td>

                </tr>
                <%}%> 
            </table>
        </div> 
    </center>
</body>


</html>
