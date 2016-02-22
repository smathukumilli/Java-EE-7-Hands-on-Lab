<%-- 
    Document   : allcustomers
    Created on : Feb 21, 2016, 1:32:53 PM
    Author     : shanmukh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:if test="${not empty requestScope[customer]}">
    <h2>${requestScope.customer.firstName} ${requestScope.customer.lastName}</h2>
</c:if>

<c:if test="${not empty requestScope.violations}">
    <h2>Violations were found and passed back in the request scope</h2>
    <ul>
        <c:forEach items="${requestScope.violations}" var="violation">
            <li>
                <c:out value="${violation.propertyPath}"/>: ${violation.message}
            </li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${not empty requestScope.messages}">
    <h2>Messages from controller:</h2>
    <ul>
        <c:forEach items="${requestScope.messages}" var="message">
            <li>
                <c:out value="${message.key}"/>: ${message.value}
            </li>
        </c:forEach>
    </ul>
</c:if>

        <table border="1">
            <td><a href="customerinfo.jsp">customer Info</a></td>
                     
        </table>
        <br />
        <h3>List of customers</h3>
        <br />
        <table border="1">
            <th>Customer ID</th>
            <th>first Name</th>
            <th>Last name</th>
            <th>Update operation</th>
            <th>Delete Operation</th>
           
            <c:forEach items="${requestScope.list}" var="customer">
                <tr>
                    <td>${customer.customerID}</td>
                    <td>${customer.firstname}</td>
                    <td>${customer.lastname}</td>
                    <td><a href="${pageContext.request.contextPath}/GetCustomer?id=${customer.customerID}">update</a></td>
                     <td><a href="${pageContext.request.contextPath}/GetCustomer?id=${customer.customerID}">delete</a></td>
                </tr>
            </c:forEach>                 
        </table>
  
