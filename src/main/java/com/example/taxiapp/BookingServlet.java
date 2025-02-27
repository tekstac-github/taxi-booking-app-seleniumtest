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
        handleBooking(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Taxi Booking Form</h2>");
        resp.getWriter().write("<form method='post' action='/taxiapp/book'>");
        resp.getWriter().write("Pickup: <input type='text' name='pickup'><br>");
        resp.getWriter().write("Destination: <input type='text' name='destination'><br>");
        resp.getWriter().write("<input type='submit' value='Book Taxi'>");
        resp.getWriter().write("</form>");
        resp.getWriter().write("</body></html>");
    }

    private void handleBooking(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
