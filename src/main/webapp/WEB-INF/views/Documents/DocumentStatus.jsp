<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document Status</title>

    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 30px auto;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .PENDING {
            color: orange;
            font-weight: bold;
        }
        .VERIFIED {
            color: green;
            font-weight: bold;
        }
        .REJECTED {
            color: red;
            font-weight: bold;
        }
    </style>
</head>

<body>

<h2 style="text-align:center;">📄 Uploaded Document Status</h2>

<c:if test="${empty documents}">
    <p style="text-align:center; color:red;">
        No documents uploaded yet.
    </p>
</c:if>

<c:if test="${not empty documents}">
<table>
    <tr>
        <th>Document Type</th>
        <th>Status</th>
        <th>Remarks</th>
        <th>Uploaded Date</th>
    </tr>

    <c:forEach var="doc" items="${documents}">
        <tr>
            <td>${doc.documentType}</td>

            <td class="${doc.status}">
                ${doc.status}
            </td>

            <td>
                <c:choose>
                    <c:when test="${empty doc.remarks}">
                        --
                    </c:when>
                    <c:otherwise>
                        ${doc.remarks}
                    </c:otherwise>
                </c:choose>
            </td>

            <td>${doc.uploadedAt}</td>
        </tr>
    </c:forEach>
</table>
</c:if>

<div style="text-align:center;">
    <a href="/student/dashboard">⬅ Back to Dashboard</a>
</div>

</body>
</html>
