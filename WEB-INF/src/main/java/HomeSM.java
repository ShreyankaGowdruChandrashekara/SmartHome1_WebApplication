import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomeSM")

public class HomeSM extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
		pw.print("<body style='background-color: rgb(228, 198, 164);'>");
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header1.html");
		utility.printHtml("LeftSMNavigationBar.html");
		utility.printHtml("Footer.html");
				
	}

}