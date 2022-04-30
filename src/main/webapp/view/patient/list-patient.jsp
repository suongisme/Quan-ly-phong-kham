<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách bệnh nhân - Quản lý phòng khám</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<body class="p-2">
<jsp:include page="../home.jsp"/>
<div class="d-flex align-items-center justify-content-between" style="height: 65px">
    <button class="btn btn-primary">
        <a style="color: white; text-decoration: none" href="${pageContext.request.contextPath}/patient/form">Tạo mới bệnh nhân</a>
    </button>

    <form class="d-flex" method="get" action="${pageContext.request.contextPath}/patient/search">
        <input style="min-width: 250px;" class="form-control mr-2" placeholder="Nhập mã hoặc tên bệnh nhân" name="keyword" value="${pageContext.request.getParameter('keyword')}">
        <button class="btn btn-primary" style="min-width: 100px;">
            <a>Tìm kiếm</a>
        </button>
    </form>
</div>
<table class="table">
    <thead class="thead-dark">
        <th scope="col">Mã bệnh nhân</th>
        <th scope="col">Tên bệnh nhân</th>
        <th scope="col">Tuổi</th>
        <th scope="col">Giới tính</th>
        <th scope="col">Địa chỉ</th>
        <th colspan="2"></th>
    </thead>
    <tbody>
    <c:forEach var="patient" items="${patients}">
        <tr>
            <td>${patient.code}</td>
            <td>${patient.name}</td>
            <td>${patient.age}</td>
            <td>${patient.gender ne 0 ? 'Nữ' : 'Nam'}</td>
            <td>${patient.address}</td>
            <td>
                <a href="${pageContext.request.contextPath}/patient/update?code=${patient.code}">Cập nhật</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/patient/delete?code=${patient.code}">&times;</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
