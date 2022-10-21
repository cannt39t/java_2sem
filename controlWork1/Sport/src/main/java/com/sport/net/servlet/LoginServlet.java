package com.sport.net.servlet;

import com.sport.net.model.User;
import com.sport.net.service.UserService;
import com.sport.net.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("_login");
        String password = req.getParameter("_password");
        System.out.println(login);
        System.out.println(password);
        UserService userService = new UserServiceImpl();
        User user = new User(login, password);
        System.out.println(userService.login(user));
        if (userService.login(user)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setMaxInactiveInterval(60 * 60);
        }
        resp.sendRedirect("/main");
    }
}
