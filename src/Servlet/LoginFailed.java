package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginFailed
 */
public class LoginFailed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFailed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Servlet Testing</TITLE>");
			out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<h1>Welcome User:</h1>");
		out.println("<h1>Sorry You denied for permission</h1>");
		out.println("<h1>Please Visit our Login Page Again</h1>");
		out.println("<h1>click here</h1><h1> <a href=http://localhost:8080/ZapStittch/Login.jsp>LOGIN</a></h1>");
		//out.println("<BODY>");
		out.println("</BODY>");
		out.println("<HTML>");
       
	}

}
