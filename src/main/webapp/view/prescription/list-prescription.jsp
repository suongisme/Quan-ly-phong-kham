<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Đơn thuốc - Quản lý phòng khám</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<body class="p-2">
<style>
    #popup {
        position: fixed;
        display: none;
    }

    #popup > .background {
        position: fixed;
        top: 0;
        right: 0;
        left: 0;
        bottom: 0;
        background: black;
        opacity: 0.5;
    }

    .popup-content {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 100;
        width: 60%;
        height: 60%;
        overflow-y: auto;
        background: white;
    }
</style>
<jsp:include page="../home.jsp"/>
<div style="height: 65px" class="d-flex align-items-center justify-content-between">
    <a class="btn btn-primary" style="color: white; text-decoration: none" href="${pageContext.request.contextPath}/prescription/form">Tạo mới đơn thuốc</a>

    <form class="d-flex" method="get" action="${pageContext.request.contextPath}/prescription//search">
        <input style="min-width: 250px;" class="form-control mr-2" placeholder="Nhập mã bệnh nhân" name="keyword" value="${pageContext.request.getParameter('keyword')}">
        <button class="btn btn-primary" style="min-width: 100px;">
            <a>Tìm kiếm</a>
        </button>
    </form>
</div>
<table class="table">
    <thead class="thead-dark">
        <th scope="col">Số thứ tự</th>
        <th scope="col">Bệnh nhân</th>
        <th scope="col">Bệnh được chuẩn đoán</th>
        <th scope="col">Ngày khám</th>
        <th></th>
    </thead>
    <tbody>
    <c:forEach var="prescription" items="${prescriptions}">
        <tr>
            <td>${prescription.numericOrder}</td>
            <td>${prescription.patientCode}</td>
            <td>${prescription.diseases}</td>
            <td>${prescription.createdDate}</td>
            <td>
                <button class="btn btn-primary" data-order="${prescription.numericOrder}">Xem chi tiết</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div id="popup">
    <div class="background"></div>
    <div class="popup-content"></div>
</div>
</body>

<script>

    const popup = document.querySelector('#popup');
    const popupContent = document.querySelector('.popup-content');
    const buttons = document.querySelector('tbody').querySelectorAll('button');

    buttons.forEach(button => {
        button.addEventListener('click', event => {
            const { order } = event.currentTarget.dataset;
            fetch("${pageContext.request.contextPath}/prescription/view?numericOrder="+order)
            .then(result => result.text())
            .then(html => {
                popup.style.display = 'block';
                popupContent.innerHTML = html;
            })
        })
    })

    const backgroundDom = document.querySelector('.background');
    backgroundDom.addEventListener('click', event => {
        popup.style.display = 'none';
    })
</script>
</html>
