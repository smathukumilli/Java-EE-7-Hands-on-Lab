<%-- 
    Document   : customerinfo
    Created on : Feb 21, 2016, 1:17:38 PM
    Author     : shanmukh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>customer Information</title>
    </head>
    <body>
        <table border="1">
            <td><a href="customerinfo.jsp">customer Info</a></td>
            <td><a href="${pageContext.request.contextPath}/AllCustomers">all customers</a></td>            
        </table>
        <br />

        <form action="${pageContext.request.contextPath}/CustomerServlet" method="POST">
            <table>
                <tr>
                    <td>customer ID:</td>
                    <td><input type="text" name="id" value="${customer.customerID}"/></td>
                </tr>
                <tr>
                    <td>first Name:</td>
                    <td><input type="text" name="firstname" value="${customer.firstname}"/></td>
                </tr>
                <tr>
                    <td>last name:</td>
                    <td><input type="text" name="lastname" value="${customer.lastname}" /></td>
                </tr>
                
                <tr>
                    <td colspan="2"><input type="Submit" name="operation" value="Add" />
                    <input type="Submit" name="operation" value="Edit" />
                    <input type="Submit" name="operation" value="Delete" />
                    <input type="Submit" name="operation" value="Search" /></td>
                </tr>                
            </table>
        </form>
    </body>
</html>
