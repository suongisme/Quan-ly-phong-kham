<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Bệnh nhân - Quản lý phòng khám</title>
</head>
<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
    .container {
        padding: 10px;
    }

    .required:after {
        content: ' *';
        color: red;
    }
</style>
<jsp:include page="../home.jsp"/>
<div class="container">
    <h3>Bệnh nhân: </h3>
    <c:if test="${error ne null}">
        <p style="color: red">${error}</p>
    </c:if>
    <form method="post">

        <div class="form-group">
            <label class="required" for="patientCode">Mã bệnh nhân: </label>
            <input class="form-control" id="patientCode" aria-describedby="patientCode" placeholder="Nhập mã bệnh nhân" value="${patient.code}" name="code" ${ patient.code ne null ? 'disabled' : ''}>
        </div>

        <div class="form-group">
            <label class="required" for="patientName">Tên bệnh nhân: </label>
            <input class="form-control" id="patientName" placeholder="Nhập tên bệnh nhân" value="${patient.name}" name="name">
        </div>

        <div class="form-group">
            <label class="required" for="patientName">Tuổi: </label>
            <input class="form-control" id="patientAge" placeholder="Nhập tuổi" value="${patient.age}" name="age" type="number">
        </div>

        <div class="form-group">
            <label class="required" for="patientGender">Giới tính: </label>
            <select class="form-control" name="gender" id="patientGender">
                <option ${patient.gender ne 0 ? 'selected' : ''} value="0">Nữ</option>
                <option ${patient.gender ne 1 ? 'selected' : ''} value="1">Nam</option>
            </select>
        </div>

        <div class="form-group">
            <label class="required" for="Patient Address">Địa chỉ: </label>
            <textarea class="form-control" id="Patient Address" rows="3" name="address">${patient.address.trim()}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">Lưu lại</button>
        <a href="${pageContext.request.contextPath}/home">Quay lại trang chủ</a>
    </form>
</div>
</body>
</html>
