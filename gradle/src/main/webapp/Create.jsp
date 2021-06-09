<%--
  Created by IntelliJ IDEA.
  User: cyber
  Date: 6/9/2021
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductList</title>
</head>
<body>
<h1 align="left">Add New Product</h1>

<c:if test="${message!= null}">
    <p style="color: red"><c:out value="${message}"/></p>
</c:if>
<form method="post">
    <table align="left">
        <tr>
            <td>Name:</td>
        </tr>
        <tr>
            <td> <input type="text" name="name"></td>
            <td></td>
        </tr>
        <tr>
            <td>Price</td>
        </tr>
        <tr>
            <td><input type="text" name="price"> </td>
        </tr>
        <tr>
            <td><input type ="text" name="quantity"></td>
        </tr>
        <tr>
            <td><input type="text" name =" color"></td>
        </tr>
        <tr>
            <td><input type="text" name="category"></td>
        </tr>
        <tr>
            <td>
                <button>Create</button>
                <button href="/product"> Back</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
