package com.sport.net.servlet;

import com.sport.net.model.User;
import com.sport.net.service.UserService;
import com.sport.net.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("register.html");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String _username = req.getParameter("_username");
        String _password = req.getParameter("_password");

        System.out.println(_username);
        System.out.println(_password);

        User user = new User(_username,  _password);
        UserService userService = new UserServiceImpl();
        userService.register(user);

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);
        httpSession.setMaxInactiveInterval(60 * 60);

        resp.sendRedirect("/main");
    }
}
