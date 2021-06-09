<%--
  Created by IntelliJ IDEA.
  User: cyber
  Date: 6/9/2021
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<div align="left">
<span >
    <button href="/product?action=create" >+ ADD NEW PRODUCT</button>
</span>
</div>
<div class="table-wrapper">
    <table class="fl-table" style="font-size: 17px">
        <thead>
        <tr>
            <th>#</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th> Category</th>
            <th> Acition</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ProductList}" var="product">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.quantity}"/></td>
            <td><c:out value="${product.color}"/></td>
            <td><c:out value="${product.category}"/></td>
            <td>
                <button href="/product?action=edit&id=${category.id}">Eidt</button>
                <button href="/product?action=delete&id=${category.id}">Delete</button>
            </td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>
</body>
</html>
