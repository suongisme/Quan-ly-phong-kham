<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách thuốc - Quản lý phòng khám</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<body class="p-2">

<div class="d-flex align-items-center justify-content-between" style="height: 65px">
    <button class="btn btn-primary">
        <a style="color: white; text-decoration: none" href="${pageContext.request.contextPath}/medicine/form">Tạo mới thuốc</a>
    </button>

    <form class="d-flex" method="get" action="${pageContext.request.contextPath}/medicine/search">
        <input style="min-width: 250px;" class="form-control mr-2" placeholder="Nhập mã hoặc tên thuốc" name="keyword" value="${pageContext.request.getParameter('keyword')}">
        <button class="btn btn-primary" style="min-width: 100px;">
            <a>Tìm kiếm</a>
        </button>
    </form>
</div>
<table class="table">
    <thead class="thead-dark">
        <th scope="col">Mã thuốc</th>
        <th scope="col">Tên thuốc</th>
        <th scope="col">Nước sản xuất</th>
        <th scope="col">Giá tiền</th>
        <th scope="col">Hạn sử dụng</th>
        <th scope="col">Mô tả</th>
        <th colspan="2"></th>
    </thead>
    <tbody>
    <c:forEach var="medicine" items="${medicines}">
        <tr>
            <td>${medicine.code}</td>
            <td>${medicine.name}</td>
            <td>${medicine.country}</td>
            <td>${medicine.price}</td>
            <td>${medicine.expire}</td>
            <td>${medicine.description}</td>
            <td>
                <a href="${pageContext.request.contextPath}/medicine/update?code=${medicine.code}">Cập nhật</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/medicine/delete?code=${medicine.code}">&times;</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
