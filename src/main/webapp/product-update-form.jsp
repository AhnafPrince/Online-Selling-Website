<%-- 
    Document   : product-form
    Created on : Dec 21, 2019, 4:30:59 PM
    Author     : ahnaf
--%>

<%@page import="java.lang.String"%>
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

        <title>Product Form</title>
    </head>



    <body>
        <h2>

            <center><a href="ProductServlet">List All Products</a></center> 

        </h2>

        <br><br><br>

    <center><h1><strong>Update Product</strong></h1></center>

    <br>

    <br>
    
    
    <%-- Fetching the attributes of the request object 
                  which was previously set by the servlet  
                --%>  
                <%String[] s1 = (String[]) session.getAttribute("productForUpdate");%>
                   
    
    
    
    <center>
        <form name="form1" action="UpdateConfirmServlet" method="POST">
            <div>
                <table>

                    <tr>

                        <td><b><pre><br>Product ID  </pre></b></td>
                        <td><input type="text" class="id" class="form-control" name="id" value="<%=s1[7]%> "></td>

                    </tr>

                    <tr>

                        <td><b><pre><br>Product Name </pre></b></td>
                        <td><input type="text" class="productName" class="form-control" name="productName" value="<%=s1[0]%>"> </td>

                    </tr>

                    <tr>

                        <td><b><pre><br>Product Type  </pre></b></td>
                        <td><input type="text" class="type" class="form-control" name="type" value="<%=s1[1]%>"> </td>

                    </tr>

                    <tr>

                        <td><b><pre><br> Price </pre></b></td>
                        <td><input type="text" class="price" class="form-control" name="price" value="<%=s1[2] %>"> </td>

                    </tr>

                    <tr>

                        <td><b><pre><br>Product Info</pre></b></td>
                        <td><input type="text" class="info" class="form-control" name="info" value="<%=s1[3] %> "></td>

                    </tr>

                    <tr>

                        <td><b><pre><br> Image </pre></b></td>
                        <td><input type="file" name="image" accept="image/*" value="<%=s1[4] %>"> </td>

                    </tr>

                    <tr>

                        <td><b><pre><br>Available Quantity</pre></b></td>
                        <td><input type="text" class="available" class="form-control" name="available" value="<%=s1[5]%>"> </td>

                    </tr>

                    <tr>

                        <td><b><pre><br>Sold Quantity</pre></b></td>
                        <td><input type="text" class="sold" class="form-control" name="sold" value="<%=s1[6]%>"> </td>

                    </tr>
                   
                    <tr>
                        <td colspan="2" style="text-align: center"> <br> <input class="btn btn-success" type="submit" value="Update"></td>
                        <td colspan="2" style="text-align: justify"> <br> <input class="btn btn-success" type="reset" value="Clear"></td>
                    </tr>
                    <br>
                    
                </table>
            </div>
        </form>
    </center>



</body>



</html>
