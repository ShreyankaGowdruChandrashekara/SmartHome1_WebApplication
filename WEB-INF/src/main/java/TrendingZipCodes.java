// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.HashMap;
// import java.util.LinkedHashMap;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Map;
// import java.util.Map.Entry;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// @WebServlet("/TrendingZipCodes")

// public class TrendingZipCodes extends HttpServlet {

// 	/* Trending Page Displays all the Tvs and their Information in Game Speed*/

// 	protected void doGet(HttpServletRequest request,
// 		HttpServletResponse response) throws ServletException, IOException {
// 		response.setContentType("text/html");
// 		PrintWriter pw = response.getWriter();

// 		/* Checks the Tvs type whether it is microsft or sony or nintendo then add products to hashmap*/

// 		String name = "Trending";
// 		String CategoryName = request.getParameter("maker");

// 		Utilities utility = new Utilities(request, pw);
// 		utility.printHtml("Header.html");
// 		utility.printHtml("LeftNavigationBar.html");
// 		int i=0;
// 		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
// 		pw.print("<a style='font-size: 24px;'> Most Trending stores</a>");
// 		pw.print("</h2><div class='entry'><table id='bestseller'>");
// 		List<StoreDetails> list2 = new ArrayList<>();
// 		list2 = MySQLDataStoreUtilities.getMostSalesStore();
// 		for(StoreDetails storeDetails : list2) {
// 			if(i%3==1) pw.print("<tr>");
// 			pw.print("<td><div id='shop_item'>");
// 			pw.print("<strong style = 'color : red'>"+storeDetails.getName()+"</strong>");
// 			pw.print("<strong>"+storeDetails.getCity()+" , "+ storeDetails.getState() +"</strong>");
// 			pw.print("<strong>"+storeDetails.getZipcode() +"</strong>");
// 			pw.print("</div></td>");
// 			if(i%3==0 || i == list2.size()) pw.print("</tr>");
// 			i++;
// 		}
// 		pw.print("</table></div></div>");;
// 	}
	
// 	protected void doPost(HttpServletRequest request,
// 			HttpServletResponse response) throws ServletException, IOException {

// 	}
	

// }

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TrendingZipCodes")

public class TrendingZipCodes extends HttpServlet {

	/* Trending Page Displays all the Tvs and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Tvs type whether it is microsft or sony or nintendo then add products to hashmap*/

		
		String CategoryName = request.getParameter("maker");
		HashMap<String, AllProducts> hm = new HashMap<String, AllProducts>();
		List<AllProducts> list = new ArrayList<>();
		
    int i=1;

		/* Header, Left Navigation Bar are Printed.

		All the tvs and Tv information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'> Most Trending stores</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		List<StoreDetails> list2 = new ArrayList<>();
		list2 = MySQLDataStoreUtilities.getMostSalesStore();
		System.out.print("list in trending zip code"+list2);
		for(StoreDetails storeDetails : list2) {
			if(i%3==1 && i==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<strong style = 'color : red'>"+storeDetails.getName()+"</strong>");
			pw.print("<strong>"+storeDetails.getCity()+" , "+ storeDetails.getState() +"</strong>");
			pw.print("<strong>"+storeDetails.getZipcode() +"</strong>");
			pw.print("</div></td>");
			if(i%3==0 ) pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");	


		
		utility.printHtml("Footer.html");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
