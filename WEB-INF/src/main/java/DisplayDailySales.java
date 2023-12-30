import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
//import org.json.JSONObject;
import com.google.gson.Gson;

@WebServlet("/DisplayDailySales")

public class DisplayDailySales extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	    response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		System.out.println("Entered DisplayProducts get");
		Utilities util=new Utilities(request,out);
		Gson gson=new Gson();
		util.printHtml("Header1.html");
		util.printHtml("LeftSMNavigationBar.html");
        HttpSession session=request.getSession();
        out.print("<div id='container' style=\"background-color:lightbrown;width:80%;margin-left:20px\">");
        out.print("<section id='content' style=\"width:70%;background-color:lightbrown;\">");
        out.print("<article> <h1 align=\"center\"><span style='color:lightbrown;'>"+"Daily Sales Display</span></h1> ");
        if(request.getAttribute("checkMsg") != null )
        {
            out.println("<h2 style='color:white;background-color:green;'>"+(String)(request.getAttribute("checkMsg"))+"</h2>");
            request.setAttribute("checkMsg","");
        }
        out.print("<div id=\"table_DailySales\" ></div>");
        out.print("</article></section>");
        out.print("</div>");
        
        util.printHtml("Footer.html");
//		MySQLDataStoreUtilities = new MySqlDataStoreUtilities();
//		MySQLDataStoreUtilities.connect();
//		ArrayList<Order> items = dbInstance.getTotalSale();
//		String result=gson.toJson(items);
//		out.println(result);
	}	
}