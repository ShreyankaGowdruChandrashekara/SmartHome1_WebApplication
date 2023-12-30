import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

@WebServlet("/Cart")

public class Cart extends HttpServlet {

	int coupon = 0;
	int warranty = 0;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/
		pw.print("<body style='background-color: rgb(228, 198, 164);'>");

		Utilities utility = new Utilities(request, pw);
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
;

		if(access.equals("0")){
			System.out.println("Entered access 0");
			coupon = 1;
		}

		if(access.equals("1")){
			System.out.println("Entered access 1");
			warranty = 200;
		}
		
		System.out.println("id = "+ id + ", name" + name + ", type" + type + ", maker" + maker + ", accesee" + access);
		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		utility.storeProduct(id,name, type, maker, access);
		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Set<String> types = new HashSet<>();
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content' style='background-color: rgb(228, 198, 164);'><div class='post' style='background-color: rgb(228, 198, 164);'><h2 class='title meta' style='background-color: rgb(228, 198, 164);'>");
		pw.print("<a style='font-size: 24px;color:brown;'>Cart("+utility.CartCount()+")</a>");
		pw.print("</h2><div class='entry' style='background-color: rgb(228, 198, 164);'>");
		// pw.print("<form id='Cart' name ='Cart' action='CheckOut' method='post'>");
		if(utility.CartCount()>0)
		{
			int i = 1;
			double total = 0;
			pw.print("<table class='gridable' style='background-color: rgb(228, 198, 164);'>");
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<form name ='CartItem' action='RemovefromCart' style='background-color: rgb(228, 198, 164);' method='post'>"+
							"<tr style='background-color: rgb(228, 198, 164);'><td style='background-color: rgb(228, 198, 164);'>"+i+".</td><td style='background-color: rgb(228, 198, 164);'>"+oi.getName()+"</td><td style='background-color: rgb(228, 198, 164);'>: "+oi.getPrice()+"</td>"+
							"<input type='hidden' name='orderItemName' style='background-color: rgb(228, 198, 164);' value='"+oi.getName()+"'>"+
							"<td><input type='submit' class='btn btn-danger' name='removefromcart' value='RemoveItem'></td></tr></form><br></br>");
				total = total +oi.getPrice();
				//types.add(oi.getType());
				i++;
			}

			if(coupon==1)
				total = total - 100;
			
			total = total + warranty;

			pw.print("</table>");
			pw.print("<tr style='background-color: rgb(228, 198, 164);'><th style='background-color: rgb(228, 198, 164);'></th><th style='background-color: rgb(228, 198, 164);'>Total:	</th><th style='background-color: rgb(228, 198, 164);'>"+total+"</th>");
			pw.print("<br><br><tr style='background-color: rgb(228, 198, 164);'>Click to Promo code</tr>");
			
			pw.print("<tr style='background-color: rgb(228, 198, 164);'><input class='form-control' type='text' style='background-color: rgb(228, 198, 164);'/></tr>");
			pw.print("<tr style='background-color: rgb(228, 198, 164);'><form action='Cart' method='post' style='background-color: rgb(228, 198, 164);'><input type='submit' class='btn btn-primary' value='Apply '><input type='hidden' style='background-color: rgb(228, 198, 164);' name='name' value='0'><input type='hidden' name='type' value='0'><input type='hidden' name='maker' value='0'><input type='hidden' name='access' value='0'></form></tr>");
			
			pw.print("<br><br><tr style='background-color: rgb(228, 198, 164);'>Click to Add Warranty</tr>");
			pw.print("<tr style='background-color: rgb(228, 198, 164);'><form action='Cart' method='post' style='background-color: rgb(228, 198, 164);'><input type='submit' class='btn btn-primary' value='Apply Warranty'><input type='hidden' name='name' value='0'><input type='hidden' name='type' value='0'><input type='hidden' name='maker' value='0'><input type='hidden' name='access' value='1'></form></tr>");
			
			

			pw.print("<form name ='ChkoutBtn' action='CheckOut' method='post' style='background-color: rgb(228, 198, 164);'><tr style='background-color: rgb(228, 198, 164);'><td style='background-color: rgb(228, 198, 164);'></td><td></td><td style='background-color: rgb(228, 198, 164);'></td>"+
					"<td style='background-color: rgb(228, 198, 164);'><input type='hidden' name='orderTotal' style='background-color: rgb(228, 198, 164);' value='"+total+"'>"+
					"<input type='submit' name='CheckOut' value='CheckOut' style='background-color: rgb(228, 198, 164);'></td></tr>");
					
			pw.print("<br><br><br><br><br><br><br>");		
					
			///////////////////////////////////////
			////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////
			
			for(String type : types)
			{
				if(type.equals("smartDoorbell")){
					pw.print("<h2>Accessory for Door Bells");
					pw.print("<table><tr><td style='padding: 60px;'></td");
					pw.print("<td style='padding: 20px;'><div id='shop_item'>");
					pw.print("<h3> Spare Parts </h3>");
					pw.print("<strong>$ 7.99 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/spareParts.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='wedgemount'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='blink'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td>");
					pw.print("<td></td>");
					pw.print("<td style='padding: 20px;'><div id='shop_item'>");
					pw.print("<h3> Wedge Kit </h3>");
					pw.print("<strong>$ 23.86 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/wedgeKit.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='raincover'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='HomeAll'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td></tr></table>");
		
		
		
				}
				else if (type.equals("speaker"))
				{
		
					pw.print("<h2>Accessory for Speakers");
					pw.print("<table><tr><td style='padding: 60px;'></td");
					pw.print("<td style='padding: 20px;'><div id='shop_item'>");
					pw.print("<h3> High Fidility Speaker Cable </h3>");
					pw.print("<strong>$ 12.59 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/highFidilityCable.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='holder'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='holder'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td>");
		
					pw.print("<td style='padding: 20px;'><div id='shop_item'>");
					pw.print("<h3> Speaker Case </h3>");
					pw.print("<strong>$ 16.99 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/speakerCase.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='mountit'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='mountit'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td></tr></table>");
		
				}
				else if (type.equals("smartLighting"))
				{
		
					pw.print("<h2>Accessory for Lightings");
					pw.print("<table><tr><td style='padding: 60px;'></td");
					pw.print("<td style='padding: 20px;'><div id='shop_item'>");
					pw.print("<h3> Track Lighting Kit </h3>");
					pw.print("<strong>$ 44.99 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/trackLightingKit.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='switchmodule'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='philips'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td>");
		
					pw.print("<td style='padding: 20px;'><div id='shop_item'>");
					pw.print("<h3> Dimmer Switch </h3>");
					pw.print("<strong>$ 49.99 </strong><ul>");
					pw.print("<li id='item'><img src='images/accessories/dimmerSwitch.jpg' alt='' /></li>");
					
					pw.print("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='tapdialswitch'>"+
							"<input type='hidden' name='type' value='accessories'>"+
							"<input type='hidden' name='maker' value='philips'>"+
							"<input type='hidden' name='access' value=''>"+
							"<div class='btn-group'>"+
							"<input type='submit' class='btn btn-success' value='Buy Now'>"+
							"</div></form> </td></tr>");
				}
			}
			
			// pw.print("<div style= background-color: rgb(228, 198, 164);'><h2 style= background-color: rgb(228, 198, 164);'>" +
			// 	"<a style='font-size: 24px;color:red;text-align:center;font-weight:bold;margin-left:35%'>" +"Recommended Accessories" +"</a></h2>"+ 
			// 	"</h2><div style= background-color: rgb(228, 198, 164);'><table id='bestseller' style= background-color: rgb(228, 198, 164);'>"+ "<tr style= background-color: rgb(228, 198, 164);'>"+ 
			// 	"<td style= background-color: rgb(228, 198, 164);'><div id='shop_item' style= background-color: rgb(228, 198, 164);'>"+ 
			// 	"<ul style= background-color: rgb(228, 198, 164);'><li id='item' style= background-color: rgb(228, 198, 164);'><img src='images/smartLighting/PhilipsHueSmartBulbs.jpg' alt='' /></li>"+ 
			// 	"<li style= background-color: rgb(228, 198, 164);'><h3>" + "Philips Hue Smart Bulbs" + "</h3>"+ 
			// 	"<strong>" + "97.99" + "$</strong></li>" + 
			// 	"<li><form method='post' action='Cart' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "<input type='hidden' name='name' value='PhilipsHueSmartBulbs' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "	<input type='hidden' name='type' value='smartLightings' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "	<input type='hidden' name='maker' value='sPhilips' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "	<input type='hidden' name='access' value='' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "	<div class='btn-group'>"
			// 	+ "	<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"
			// 	+ "	 </div></form></li>" +
			// 	"</ul></div></td>"+
			// 	"<td><div id='shop_item' style= background-color: rgb(228, 198, 164);'>"+ 
			// 	"<ul><li id='item' style= background-color: rgb(228, 198, 164);'><img src='images/smartLighting/LIFXSmartBulbs.jpg' alt='' /></li>"+ 
			// 	"<li><h3>" + "LIFX Smart Bulbs" + "</h3>"+ 
			// 	"<strong>" + "59.98" + "$</strong></li>" + 
			// 	"<li><form method='post' action='Cart' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "<input type='hidden' name='name' value='LIFXSmartBulbs' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "	<input type='hidden' name='type' value='smartLightings' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "	<input type='hidden' name='maker' value='lLIFX' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "	<input type='hidden' name='access' value='' style= background-color: rgb(228, 198, 164);'>"
			// 	+ "	<div class='btn-group'>"
			// 	+ "	<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"
			// 	+ "	 </div></form></li>" +
			// 	"</ul></div></td>"+
			// 	"</tr>" + "</div></div>");
		
					
					
			pw.print("</table></form>");
	
			////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////
			

		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
			
		pw.print("<br><br><br><br><br><br><br>");
		pw.print("</div></div></div>");	
		utility.printHtml("Footer.html");
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}
}

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.math.BigDecimal;
// import java.math.RoundingMode;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.ArrayList;

// @WebServlet("/Cart")

// public class Cart extends HttpServlet {

// 	int coupon = 0;

// 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
// 		response.setContentType("text/html");
// 		PrintWriter pw = response.getWriter();

// 		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

// 		Utilities utility = new Utilities(request, pw);
// 		String id = request.getParameter("id");
// 		String name = request.getParameter("name");
// 		String type = request.getParameter("type");
// 		String maker = request.getParameter("maker");
// 		String access = request.getParameter("access");

// 		System.out.println("id = "+ id + " name" + name + "type" + type + "maker" + maker + "accesee" + access);
		
// 		if(access.equals("0")){
// 			coupon = 1;
// 		}

// 		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

// 		utility.storeProduct(id,name, type, maker, access);
// 		displayCart(request, response);
// 	}
	

// /* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

// 	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 		response.setContentType("text/html");
// 		PrintWriter pw = response.getWriter();
// 		Utilities utility = new Utilities(request,pw);
// 		Carousel carousel = new Carousel();
// 		if(!utility.isLoggedin()){
// 			HttpSession session = request.getSession(true);				
// 			session.setAttribute("login_msg", "Please Login to add items to cart");
// 			response.sendRedirect("Login");
// 			return;
// 		}
		
// 		utility.printHtml("Header.html");
// 		utility.printHtml("LeftNavigationBar.html");
// 		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
// 		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
// 		pw.print("</h2><div class='entry'>");
// 		if(utility.CartCount()>0)
// 		{
// 			int i = 1;
// 			double total = 0;
// 			pw.print("<div class='container'>");
// 			for (OrderItem oi : utility.getCustomerOrders()) 
// 			{
// 				pw.print("<div class='row'><form name ='CartItem' action='RemovefromCart' method='post'>"+
// 							"<b>"+i+".         "+oi.getName()+"   :   "+oi.getPrice()+"</b> <tr><th></th></tr>"+
// 							"<input type='hidden' name='orderItemName' value='"+oi.getName()+"'>"+
// 							"<input type='submit' class='btn btn-danger' name='removefromcart' value='RemoveItem'></form></div><br></br>");
// 				total = total +oi.getPrice();
// 				i++;
// 			}
// 			double price = total;
// 			System.out.println(" Total = "+ total);
// 			if(coupon==1)
// 				price = total * (1 - 0.15);
// 			BigDecimal bd = BigDecimal.valueOf(price);
// 		    bd = bd.setScale(2, RoundingMode.HALF_UP);
// 		    System.out.println(" price = "+ price);	
// 			pw.print("</div>");
// 			pw.print("<br></br><tr><th></th><b style='color :red'>Total:	</b><b style='color :red'>"+bd.doubleValue()+"</b>");
			
// 			pw.print("<br></br><br></br><p style='color:blue;font-weight:bold;font-size:18px'> Enter Promo Code here</p>");
// 			pw.print("<tr><input class='form-control' type='text' style= 'max-width:250px'/></tr>");
// 			pw.print("<tr><form action='Cart' method='post'><input type='submit' class='btn btn-primary' value='Apply'><input type='hidden' name='name' value='0'><input type='hidden' name='type' value='0'><input type='hidden' name='maker' value='0'><input type='hidden' name='access' value='0'></form></tr>");
			
			
// 			pw.print("<form name ='ChkoutBtn' action='CheckOut' method='post'><tr><td></td><td></td><td></td>"+
// 					"<td><input class='form-control' type='hidden'  style='margin:10%' name='orderTotal' value='"+bd.doubleValue()+"'>"+
// 					"<input type='submit' class='btn btn-primary btn-lg' style='margin:10%;width : 60%' name='CheckOut' value='CheckOut'></td></tr></table></form>");
// 			pw.print(carousel.carouselfeature(utility));
// 		}
// 		else
// 		{
// 			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
// 		}
// 		pw.print("</div></div></div>");		
// 		utility.printHtml("Footer.html");
// 	}
// 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
// 		response.setContentType("text/html");
// 		PrintWriter pw = response.getWriter();
// 		Utilities utility = new Utilities(request, pw);
		
// 		displayCart(request, response);
// 	}
// }
