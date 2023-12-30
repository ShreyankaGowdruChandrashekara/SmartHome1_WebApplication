import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

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

@WebServlet("/RemovefromCart")


public class RemovefromCart extends HttpServlet {

	int coupon = 0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		
        String orderItemName = request.getParameter("orderItemName");

		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/
		pw.print("<body style='background-color: rgb(228, 198, 164);'>");
		Utilities utility = new Utilities(request, pw);
        ArrayList<OrderItem> orderItems = utility.getCustomerOrders();
        for(OrderItem oi : orderItems){
            if(oi.getName().equalsIgnoreCase(orderItemName)){
                orderItems.remove(oi);
                System.out.println("Hello removed this: "+oi.getName());
                displayCart(request,response);
                break;
            }
        }
        displayCart(request,response);
    }

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		Set<String> types = new HashSet<>();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content' style='background-color: rgb(228, 198, 164);'><div class='post' style='background-color: rgb(228, 198, 164);'><h2 class='title meta' style='background-color: rgb(228, 198, 164);'>");
		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
		pw.print("</h2><div class='entry' style='background-color: rgb(228, 198, 164);'>");
		// pw.print("<form id='Cart' name ='Cart' action='CheckOut' method='post'>");
		if(utility.CartCount()>0)
		{
			int i = 1;
			double total = 0;
			pw.print("<div class='container' style='background-color: rgb(228, 198, 164);'>");
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<div class='row' style='background-color: rgb(228, 198, 164);'><form name ='CartItem' action='RemovefromCart' style='background-color: rgb(228, 198, 164);' method='post'>"+
							"<b>"+i+".         "+oi.getName()+"   :   "+oi.getPrice()+"</b> <tr><th></th></tr>"+
							"<input type='hidden' style='background-color: rgb(228, 198, 164);' name='orderItemName' value='"+oi.getName()+"'>"+
							"<input type='submit' class='btn btn-danger' name='removefromcart' value='RemoveItem'></form></div><br></br>");
				total = total +oi.getPrice();
				//types.add(oi.getType());
				i++;
			}
			double price = total;
			if(coupon==1)
				price = total * (1 - 0.15);
			BigDecimal bd = BigDecimal.valueOf(price);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
				
			pw.print("</div>");
			pw.print("<br></br><tr><th></th><b style='color :red'>Total:	</b><b style='color :red'>"+bd.doubleValue()+"</b>");
			
			pw.print("<br></br><br></br><p style='color:brown;font-weight:bold;font-size:18px;background-color: rgb(228, 198, 164);'> Enter Promo Code here</p>");
			pw.print("<tr style='background-color: rgb(228, 198, 164);'><input class='form-control'  type='text' style= 'background-color: rgb(228, 198, 164);max-width:250px'/></tr>");
			pw.print("<tr style='background-color: rgb(228, 198, 164);'><form action='Cart' style='background-color: rgb(228, 198, 164);' method='post'><input type='submit' class='btn btn-primary' value='Apply'><input type='hidden' name='name' value='0'><input type='hidden' name='type' value='0'><input type='hidden' name='maker' value='0'><input type='hidden' name='access' value='0'></form></tr>");
			
			
			pw.print("<form name ='ChkoutBtn' action='CheckOut' method='post'><tr><td></td><td></td><td></td>"+
					"<td><input class='form-control' type='hidden'  style='margin:10%' name='orderTotal' value='"+bd.doubleValue()+"'>"+
					"<input type='submit' class='btn btn-primary btn-lg' style='margin:10%;width : 60%' name='CheckOut' value='CheckOut'></td></tr>");
			/* This code is calling Carousel.java code to implement carousel feature*/
			pw.print(carousel.carouselfeature(utility));
		
		



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
					pw.print("<h3> high Fidility Speaker Cable </h3>");
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

		
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
}