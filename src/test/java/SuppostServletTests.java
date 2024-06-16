import org.example.SupportPackage.SupportServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SuppostServletTests {
    private SupportServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        servlet = new SupportServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testDoGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        verify(response, times(1)).setContentType("text/plain");
        assertFalse(stringWriter.toString().isEmpty());
    }

//    @Test
//    public void testDoPost() throws Exception {
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(writer);
//
//        // Create a mock ServletInputStream
//        ServletInputStream servletInputStream = mock(ServletInputStream.class);
//        when(request.getInputStream()).thenReturn(servletInputStream);
//
//        servlet.doPost(request, response);
//
//        verify(response, times(1)).setContentType("text/plain");
//        assertFalse(stringWriter.toString().isEmpty());
//    }

}
