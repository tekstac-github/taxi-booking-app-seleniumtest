package com.example.taxiapp;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookingServletTest {

    @Test
    public void testValidBooking() throws Exception {
        var request = mock(javax.servlet.http.HttpServletRequest.class);
        var response = mock(HttpServletResponse.class);
        var outputStream = new ByteArrayOutputStream();
        var writer = new PrintWriter(outputStream);

        when(request.getParameter("pickup")).thenReturn("Downtown");
        when(request.getParameter("destination")).thenReturn("Airport");
        when(response.getWriter()).thenReturn(writer);

        var servlet = new BookingServlet();
        servlet.doPost(request, response);

        writer.flush();
        assertEquals("Booking confirmed from Downtown to Airport", outputStream.toString().trim());
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void testInvalidBooking() throws Exception {
        var request = mock(javax.servlet.http.HttpServletRequest.class);
        var response = mock(HttpServletResponse.class);
        var outputStream = new ByteArrayOutputStream();
        var writer = new PrintWriter(outputStream);

        when(request.getParameter("pickup")).thenReturn("");
        when(request.getParameter("destination")).thenReturn("");
        when(response.getWriter()).thenReturn(writer);

        var servlet = new BookingServlet();
        servlet.doPost(request, response);

        writer.flush();
        assertEquals("Invalid booking details. Please provide both pickup and destination.", outputStream.toString().trim());
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}