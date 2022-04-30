<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đơn thuốc</title>
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
    <h3>Đơn thuốc: </h3>
    <c:if test="${error ne null}">
        <p style="color: red">${error}</p>
    </c:if>
    <form method="post">

        <div class="form-group">
            <label class="required" for="patient">Bệnh nhân: </label>
            <select class="form-control" name="patientCode" id="patient">
                <c:forEach var="patient" items="${patients}">
                    <option ${prescription.patientCode eq patient.code ? 'selected' : ''} value="${patient.code}">${patient.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label class="required" for="diseases">Bệnh được chuẩn đoán: </label>
            <textarea class="form-control" id="diseases" rows="3" name="diseases">
                ${prescription.diseases.trim()}
            </textarea>
        </div>

        <div class="form-group">
            <label class="required" for="medicine">Thuốc: </label>
            <select class="form-control" name="medicineCode" id="medicine" multiple>
                <c:forEach var="medicine" items="${medicines}">
                    <option value="${medicine.code}">${medicine.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label class="required" for="dosage">Liều dùng: </label>
            <textarea class="form-control" id="dosage" rows="3" name="dosage">
                ${prescription.diseases.trim()}
            </textarea>
        </div>

        <c:if test="${readOnly == false}">
            <button type="submit" class="btn btn-primary">Lưu lại</button>
            <a href="${pageContext.request.contextPath}/home">Quay lại trang chủ</a>
        </c:if>
    </form>
</div>
</body>
</html>
