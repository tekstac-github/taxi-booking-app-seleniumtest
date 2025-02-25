package com.example.taxiapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pickup = req.getParameter("pickup");
        String destination = req.getParameter("destination");

        if (pickup == null || pickup.isEmpty() || destination == null || destination.isEmpty()) {
            resp.setContentType("text/plain");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid booking details. Please provide both pickup and destination.");
        } else {
            resp.setContentType("text/plain");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Booking confirmed from " + pickup + " to " + destination);
        }
    }
}