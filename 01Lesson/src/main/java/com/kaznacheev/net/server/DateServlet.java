package com.kaznacheev.net.server;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static java.time.temporal.ChronoUnit.*;

@WebServlet(name = "DateServlet", value = "/DateServlet")
public class DateServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.ftl");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("name", name);
        httpSession.setAttribute("date", format(date));
        httpSession.setMaxInactiveInterval(60 * 60);
        // 2003-02-12

        try {
            req.getRequestDispatcher("res.ftl").forward(req, resp);
        } catch (ServletException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public static String format(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");


        LocalDate dateTime1 = LocalDate.parse(date.replace("-", ""), formatter);
        LocalDate dateTime2 = LocalDate.now();

        int diffInDays = (int) DAYS.between(dateTime1, dateTime2);
        int diffInMonths = (int) MONTHS.between(dateTime1, dateTime2);
        int diffInYears = (int) YEARS.between(dateTime1, dateTime2);


        return "Years: " + diffInYears + " Months: " + diffInMonths + " Days: " + diffInDays;
    }
}

