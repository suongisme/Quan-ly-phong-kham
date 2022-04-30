<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Đăng nhập - Quản lý phòng khám</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<body>
<style>
    .error {
        color: red;
    }
</style>

<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <form method="POST" class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-2 text-uppercase">Đăng nhập</h2>
                            <p class="text-white-50">Nhập thông tin để đăng nhập!</p>

                            <c:if test="${error ne null}" >
                                <p class="error">${error}</p>
                            </c:if>

                            <div class="form-outline form-white mb-4">
                                <input id="typeText" class="form-control form-control-lg" name="username"/>
                                <label class="form-label" for="typeText">Tài khoản</label>
                            </div>

                            <div class="form-outline form-white mb-4">
                                <input type="password" id="typePasswordX" class="form-control form-control-lg" name="password"/>
                                <label class="form-label" for="typePasswordX">Mật khẩu</label>
                            </div>

                            <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Quên mật khẩu?</a></p>

                            <button class="btn btn-outline-light btn-lg px-5" type="submit">Đăng nhập</button>

                        </form>

                        <div>
                            <p class="mb-0">Bạn chưa có tài khoản? <a href="#" class="text-white-50 fw-bold">Đăng ký</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
