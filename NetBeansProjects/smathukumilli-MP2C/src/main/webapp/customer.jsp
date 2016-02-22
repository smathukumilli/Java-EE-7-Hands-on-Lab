<%-- 
    Document   : customer
    Created on : Feb 21, 2016, 11:16:14 PM
    Author     : shanmukh
--%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>JSTL SQL TAGS</title>
</head>
<body>
 
<sql:setDataSource var="itmd4515" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/sakila"
     user="itmd4515"  password="itmd4515"/>

<sql:query dataSource="${itmd4515}" var="result">
SELECT * from customer;
</sql:query>
 
<table border="1" width="100%">
<tr>
<th>Customer ID</th>
<th>First Name</th>
<th>Last Name</th>

</tr>
<c:forEach var="row" items="${result.rows}">
<tr>
<td><c:out value="${row.customerID}"/></td>
<td><c:out value="${row.firstname}"/></td>
<td><c:out value="${row.lastname}"/></td>
</tr>
</c:forEach>
</table>

</body>
</html>