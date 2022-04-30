package com.clinic.controller;

import com.clinic.entity.User;
import com.clinic.utils.Convertor;
import com.clinic.utils.SystemUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(value = {"/auth/login", "/auth/logout"})
public class AuthController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String servletPath = req.getServletPath();
        if ("/auth/login".equals(servletPath)) {
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
            return;
        }

        if ("/auth/logout".equals(servletPath)) {
            HttpSession session = req.getSession();
            session.removeAttribute("currentUser");
            resp.sendRedirect(req.getContextPath()+"/auth/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = SystemUtils.getValueConfig("login.username");
        String password = SystemUtils.getValueConfig("login.password");

        User user = Convertor.requestParamToObject(req, User.class);

        if (!username.equals(user.getUsername())) {
            req.setAttribute("error", "Tài khoản không chính xác");
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(user.getPassword())) {
            req.setAttribute("error", "Mật khẩu không chính xác");
            req.getRequestDispatcher("/view/auth/login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("currentUser", user);
        Object returnUrl = session.getAttribute("returnUrl");
        if (Objects.nonNull(returnUrl)) {
            session.removeAttribute("returnUrl");
            resp.sendRedirect(returnUrl.toString());
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/home");
    }
}