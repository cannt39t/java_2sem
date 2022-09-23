package com.kaznacheev.net.server;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String date = req.getParameter("date");

        if (LOGIN.equals(login) && PASSWORD.equals(password)) {
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", login);
            httpSession.setMaxInactiveInterval(60 * 60);

            Cookie httpCookie1 = new Cookie("username", login);
            Cookie httpCookie2 = new Cookie("date", date);
            httpCookie1.setMaxAge(24 * 60 * 60);
            httpCookie2.setMaxAge(24 * 60 * 60);
            resp.addCookie(httpCookie1);
            resp.addCookie(httpCookie2);

            resp.sendRedirect("main.jsp");
        } else {
            resp.sendRedirect("/login");
        }
    }
}
