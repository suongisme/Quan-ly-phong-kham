<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thuốc - Quản lý phòng khám</title>
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
<div class="container">
    <h3>Thuốc: </h3>
    <c:if test="${error ne null}">
        <p style="color: red">${error}</p>
    </c:if>
    <form method="post">

        <div class="form-group">
            <label class="required" for="medicineCode">Mã thuốc: </label>
            <input class="form-control" id="medicineCode" aria-describedby="medicineCode" placeholder="Nhập mã thuốc" value="${medicine.code}" name="code" ${ medicine.code ne null ? 'disabled' : ''}>
        </div>

        <div class="form-group">
            <label class="required" for="medicineName">Tên thuốc: </label>
            <input class="form-control" id="medicineName" placeholder="Nhập tên thuốc" value="${medicine.name}" name="name">
        </div>

        <div class="form-group">
            <label class="required" for="medicineCountry">Nước sản xuất: </label>
            <select class="form-control" name="country" id="medicineCountry">
                <option value="">Chọn quốc gia</option>
                <c:forEach var="country" items="${countries}">
                    <option ${medicine.country eq country ? 'selected' : ''} value="${country}">${country}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label class="required" for="medicinePrice">Giá tiền: </label>
            <input class="form-control" id="medicinePrice" placeholder="Nhập giá tiền" type="number" value="${medicine.price}" name="price">
        </div>

        <div class="form-group">
            <label class="required" for="medicineExpire">Hạn sử dụng: </label>
            <input class="form-control" id="medicineExpire" placeholder="Nhập hạn sử dụng" type="date" value="${medicine.expire}" name="expire">
        </div>

        <div class="form-group">
            <label class="required" for="Medicine Description">Mô tả: </label>
            <textarea class="form-control" id="Medicine Description" rows="3" name="description">
                ${medicine.description.trim()}
            </textarea>
        </div>

        <button type="submit" class="btn btn-primary">Lưu lại</button>
        <a href="${pageContext.request.contextPath}/home">Quay lại trang chủ</a>
    </form>
</div>
</body>
</html>
