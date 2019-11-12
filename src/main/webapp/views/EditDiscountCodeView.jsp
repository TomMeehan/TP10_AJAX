<%-- 
    Document   : EditDiscountCodeView
    Created on : 6 nov. 2019, 13:21:17
    Author     : pedago
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edition d'un taux de remise</title>
        <style>
                    .table
                    {
                        display:table;
                        border-collapse:separate;
                        border-spacing:2px;
                    }
                    .thead
                    {
                        display:table-header-group;
                        color:white;
                        font-weight:bold;
                        background-color:grey;
                    }
                    .tbody
                    {
                        display:table-row-group;
                    }
                    .tr
                    {
                        display:table-row;
                    }
                    .td
                    {
                        display:table-cell;
                        border:1px solid black;
                        padding:1px;
                    }
        </style>	
    </head>
    <body>
        <h1>Edit a discount code</h1>
        <form method='GET'>
            Code : <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES">
            <br>
            Rate : <input name="rate" type="number" step="0.01" min="0.0" max="99.99" size="5">
            <br>
            <input name="action" type="submit" value="ADD">
        </form>
        <br><div><h4><c:if test="${ error != null }">${ error }</c:if></h4></div><br>
            <div class="table">
                <div class="thead"><div class="td">Code</div><div class="td">Rate</div><div class="td"">Action</div><div class="td"">Action</div></div>
                <div class="tbody">
                <c:forEach var="discount_code" items="${ codes }">
                    <form class="tr" method="get">
                        <div class="td"><input type="text" name="code" value="${discount_code.code}" readonly></div><div class="td"><input name="rate" type="number" step="0.01" min="0.0" max="99.99" size="5" value="${discount_code.rate}"></div><div class="td"><input type="submit" name="action" value="DELETE"></div><div class="td"><input type="submit" name="action" value="UPDATE"></div>
                    </form>
                </c:forEach>
                </div>
            </div>
        <br><div><h4><c:if test="${ updated != null }">${ updated }</c:if></h4></div><br>
    </body>
</html>
