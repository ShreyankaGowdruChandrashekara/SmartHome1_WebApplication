import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
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


import java.io.*;

@WebServlet("/ProductDetailsPage")


public class ProductDetailsPage extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html");
        
        Product product = (Product) request.getAttribute("data");
        String name = product.getName();
        String id = product.getId();
        String type = null;
        String maker = product.getRetailer();
        String access = request.getParameter("access");
        
		
		System.out.println("Get categore CHECK: "+product.getCategory());
        
		
		switch(product.getCategory()) {
            case "SmartDoorbell":{
                    type = "smartDoorbell";
                    break;  
                    }
            case "SmartDoorLock":{
                    type = "smartDoorLock";
                break;  
                    }
            case "SmartLighting":{
                    type = "smartLighting";
                break;  
                    }
            case "SmartThermostat":{
                    type = "smartThermostat";
                break;  
                    }
			case "Speaker":{
                    type = "speaker";
                break;  
                    }
                }
        

        Double price = product.getPrice();
        String imgAdd = "images/"+type +"/"+ product.getImage();
        String desc = product.getDescription();
        /*"<input type='hidden' name='name' value='"+ tv.getName()+"'>"+
        "<input type='hidden' name='id' value='"+tv.getId()+"'>"+
        "<input type='hidden' name='type' value='tvs'>"+
        "<input type='hidden' name='price' value='"+tv.getPrice()+"'>"+
        "<input type='hidden' name='img' value='"+tv.getImage()+"'>"+
        "<input type='hidden' name='desc' value='"+tv.getDescription()+"'>"+
        "<input type='hidden' name='maker' value='"+CategoryName+"'>"+
        */
        String result; 
        

        System.out.println("name : " + name + " \n price: " + price + "\n img ADD: " + imgAdd + "\n desc" + desc + "\n type: " + type +" maker " + maker);
        

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><form method='post' action='Cart'><div class='post'><h2 class='title meta'>" +
                                        "<a style='font-size: 24px;color:purple;text-align:center;font-weight:bold;margin-left:35%'>" + name +"</a></h2>" +
                                    "<div class='productContainer'> <img src = '" + imgAdd +"' alt='' style='width :90%'><h2 style='text-align :center'>" + desc +
                                    "</h2> <h2 style='text-align :center'> Price - $" + price + "</h2></div></div>"
                                            + "<input type='hidden' name='name' value='"+name+"'>"
                                            + "<input type='hidden' name='id' value='"+id+"'>"
                                            + "<input type='hidden' name='type' value='"+type+"'>"
                                            + "<input type='hidden' name='price' value='"+price+"'>"
                                            + "<input type='hidden' name='img' value='"+request.getParameter("img")+"'>"
                                            + "<input type='hidden' name='desc' value='"+desc+"'>"
                                            + "<input type='hidden' name='maker' value='"+maker+"'>"
                                            +  "<input type='hidden' name='access' value=''>"
                                            + "<div class='btn-group'>"
                                            + "<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:20%;width:600px !important'>"
                                            + " </div></form></div>" );
        
        
        
        // pw.print("<div id='content'><h2 class='title meta'>" +
        //         "<a style='font-size: 24px;color:red;text-align:center;font-weight:bold;margin-left:35%'>" +"Recommended Accessories" +"</a></h2>"+ 
        //         "</h2><div class='entry'><table id='bestseller'>"+ "<tr>"+ 
        //         "<td><div id='shop_item'>"+ 
        //         "<ul><li id='item'><img src='images/tvs/AppleWatchSE.jpg' alt='' /></li>"+ 
        //         "<li><h3>" + "Apple Watch Series 3" + "</h3>"+ 
        //         "<strong>" + "99.95" + "$</strong></li>" + 
        //         "<li><form method='post' action='Cart'>"
        //         + "<input type='hidden' name='name' value='applese'>"
        //         + " <input type='hidden' name='type' value='tvs'>"
        //         + " <input type='hidden' name='maker' value='Smart'>"
        //         + " <input type='hidden' name='access' value=''>"
        //         + " <div class='btn-group'>"
        //         + " <input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"
        //         + "  </div></form></li>" +
        //         "</ul></div></td>"+
        //         "<td><div id='shop_item'>"+ 
        //         "<ul><li id='item'><img src='images/tvs/fossil-watch-giant.jpg' alt='' /></li>"+ 
        //         "<li><h3>" + "Fossil Gen 5 LTE Smartwatch" + "</h3>"+ 
        //         "<strong>" + "199.00" + "$</strong></li>" + 
        //         "<li><form method='post' action='Cart'>"
        //         + "<input type='hidden' name='name' value='samsungg3'>"
        //         + " <input type='hidden' name='type' value='tvs'>"
        //         + " <input type='hidden' name='maker' value='Smart'>"
        //         + " <input type='hidden' name='access' value=''>"
        //         + " <div class='btn-group'>"
        //         + " <input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"
        //         + "  </div></form></li>" +
        //         "</ul></div></td>"+
        //         "</tr>" + "</div></div>");
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
		utility.printHtml("Footer.html");


}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		
		System.out.println(request.getParameter("data"));
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");

		String price = request.getParameter("price");
		String imgAdd = "images/"+type +"/"+ request.getParameter("img");
		String desc = request.getParameter("desc");
		/*"<input type='hidden' name='name' value='"+ tv.getName()+"'>"+
        "<input type='hidden' name='id' value='"+tv.getId()+"'>"+
        "<input type='hidden' name='type' value='tvs'>"+
        "<input type='hidden' name='price' value='"+tv.getPrice()+"'>"+
        "<input type='hidden' name='img' value='"+tv.getImage()+"'>"+
        "<input type='hidden' name='desc' value='"+tv.getDescription()+"'>"+
        "<input type='hidden' name='maker' value='"+CategoryName+"'>"+
        */
		String result; 
	// protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	// 	response.setContentType("text/html");
		
	// 	String name = request.getParameter("name");
	// 	String type = request.getParameter("type");
	// 	String maker = request.getParameter("maker");
	// 	String access = request.getParameter("access");

	// 	String price = request.getParameter("price");
	// 	String imgAdd = "images/"+type +"/"+ request.getParameter("img");
	// 	String desc = request.getParameter("desc");

	// 	String result; 
		

	/////////////--------------------------------------------------//////////////////////////////////
//		if(name.equals("Ring Video Doorbell")) {
//			name = "Ring Video Doorbell";
//			imgAdd = "images/phones/ringVideoDoorbell.jpg";
//			desc = "1080p HD video, improved motion detection, easy installation Satin Nickel.";
//			price = "99.99";
//		}
//		else if (name.equals("appleHomePod")) {
//			name = "Apple HomePod";
//			imgAdd = "images/phones/appleHomePod.jpg";
//			desc = "Bluetooth speakers with minimum 1 year Warranty";
//			price = "259.99";
//		}

		System.out.print("name : " + name + " \n price: " + price + "\n img ADD: " + imgAdd + "\n desc" + desc + "\n type: " + type);
		
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		// Utilities utility = new Utilities(request,pw);
		// if(!utility.isLoggedin()){
		// 	HttpSession session = request.getSession(true);				
		// 	session.setAttribute("login_msg", "Please Login to buy or view products");
		// 	response.sendRedirect("Login");
		// 	return;
		// }

		// utility.printHtml("Header.html");
		// utility.printHtml("LeftNavigationBar.html");
		pw.print("<body style='background-color: rgb(228, 198, 164);'>");

		Set<String> types = new HashSet<>();
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content' style='background-color: rgb(228, 198, 164);'><div class='post' style='background-color: rgb(228, 198, 164);'><h2 class='title meta' style='background-color: rgb(228, 198, 164);'>" +
										"<a style='font-size: 24px;color:purple;text-align:center;font-weight:bold;margin-left:35%'>" + name +"</a></h2>" +
									"<div class='productContainer' style= background-color: rgb(228, 198, 164);'> <img src = '" + imgAdd +"' alt='' style='width :90%'><h2 style='text-align :center'>" + desc +
									"</h2> <h2 style='text-align :center';style= background-color: rgb(228, 198, 164);'> Price - $" + price + "</h2></div></div></div>" );
		

		System.out.print("Before if statement");
		if(type == "smartDoorbell"){
			System.out.print("After if statement");
			pw.print("<h2 style= background-color: rgb(228, 198, 164);'>Accessory for Smart Doorbells");
			pw.print("<table style= background-color: rgb(228, 198, 164);'><tr style= background-color: rgb(228, 198, 164);'><td style= background-color: rgb(228, 198, 164);'><div id='shop_item' style= background-color: rgb(228, 198, 164);'>");
			pw.print("<h3 style= background-color: rgb(228, 198, 164);'> Pop Grip</h3>");
			pw.print("<strong style= background-color: rgb(228, 198, 164);'>$ 10.00 </strong><ul>");
			pw.print("<li id='item' style= background-color: rgb(228, 198, 164);'><img src='images/accessories/charger.jpg' alt='' /></li>");
			
		pw.print("<li style= background-color: rgb(228, 198, 164);'><form method='post' style= background-color: rgb(228, 198, 164);' action='Cart'>" +
					"<input type='hidden' name='name' value='Adapter' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='type' value='accessories' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='maker' value='Vizio' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='access' value='' style= background-color: rgb(228, 198, 164);'>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now'>"+
					"</div></form> </td>");

		pw.print("<td><div id='shop_item' style= background-color: rgb(228, 198, 164);'>");
			pw.print("<h3 style= background-color: rgb(228, 198, 164);'> Pop Grip</h3>");
			pw.print("<strong style= background-color: rgb(228, 198, 164);'>$ 10.00 </strong><ul>");
			pw.print("<li id='item' style= background-color: rgb(228, 198, 164);'><img src='images/accessories/remote.jpg' alt='' /></li>");
			
			pw.print("<li style= background-color: rgb(228, 198, 164);'><form method='post' action='Cart' style= background-color: rgb(228, 198, 164);'>" +
					"<input type='hidden' name='name' value='blackremote' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='type' value='accessories' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='maker' value='Sony' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='access' value='' style= background-color: rgb(228, 198, 164);'>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now'>"+
					"</div></form> </td></tr></table>");

		}
		
		else if (type == "SmartDoorLock"){
		pw.print("<h2 style= background-color: rgb(228, 198, 164);'>Accessory for Smart DoorLocks");
			pw.print("<table style= background-color: rgb(228, 198, 164);'><tr style= background-color: rgb(228, 198, 164);'><td style= background-color: rgb(228, 198, 164);'><div id='shop_item' style= background-color: rgb(228, 198, 164);'>");
			pw.print("<h3 style= background-color: rgb(228, 198, 164);'> Pop Grip</h3>");
			pw.print("<strong style= background-color: rgb(228, 198, 164);'>$ 10.00 </strong><ul>");
			pw.print("<li id='item' style= background-color: rgb(228, 198, 164);'><img src='images/accessories/charger.jpg' alt='' /></li>");
			
		pw.print("<li style= background-color: rgb(228, 198, 164);'><form method='post' action='Cart' style= background-color: rgb(228, 198, 164);'>" +
					"<input type='hidden' name='name' value='Adapter' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='type' value='accessories' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='maker' value='Vizio' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='access' value='' style= background-color: rgb(228, 198, 164);'>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now'>"+
					"</div></form> </td>");

		pw.print("<td><div id='shop_item' style= background-color: rgb(228, 198, 164);'>");
			pw.print("<h3 style= background-color: rgb(228, 198, 164);'> Pop Grip</h3>");
			pw.print("<strong style= background-color: rgb(228, 198, 164);'>$ 10.00 </strong><ul>");
			pw.print("<li id='item' style= background-color: rgb(228, 198, 164);'><img src='images/accessories/remote.jpg' alt='' /></li>");
			
			pw.print("<li style= background-color: rgb(228, 198, 164);'><form method='post' action='Cart' style= background-color: rgb(228, 198, 164);'>" +
					"<input type='hidden' name='name' value='blackremote' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='type' value='accessories' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='maker' value='Sony' style= background-color: rgb(228, 198, 164);'>"+
					"<input type='hidden' name='access' value='' style= background-color: rgb(228, 198, 164);'>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now'>"+
					"</div></form> </td></tr></table>");

		}			

		//////////////////////
		/////////////////////////////
		////////////////////////////////////////////
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
	
		// pw.print("<div id='content' style= background-color: rgb(228, 198, 164);'><h2 class='title meta' style= background-color: rgb(228, 198, 164);'>" +
		// 		"<a style='font-size: 24px;color:red;text-align:center;font-weight:bold;margin-left:35%'>" +"Recommended Accessories" +"</a></h2>"+ 
		// 		"</h2><div class='entry' style= background-color: rgb(228, 198, 164);'><table id='bestseller' style= background-color: rgb(228, 198, 164);'>"+ "<tr style= background-color: rgb(228, 198, 164);'>"+ 
		// 		"<td style= background-color: rgb(228, 198, 164);'><div id='shop_item' style= background-color: rgb(228, 198, 164);'>"+ 
		// 		"<ul style= background-color: rgb(228, 198, 164);'><li id='item' style= background-color: rgb(228, 198, 164);'><img src='images/smartLighting/PhilipsHueSmartBulbs.jpg' alt='' /></li>"+ 
		// 		"<li style= background-color: rgb(228, 198, 164);'><h3>" + "Philips Hue Smart Bulbs" + "</h3>"+ 
		// 		"<strong>" + "97.99" + "$</strong></li>" + 
		// 		"<li><form method='post' action='Cart' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "<input type='hidden' name='name' value='PhilipsHueSmartBulbs' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "	<input type='hidden' name='type' value='smartLightings' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "	<input type='hidden' name='maker' value='sPhilips' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "	<input type='hidden' name='access' value='' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "	<div class='btn-group'>"
		// 		+ "	<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"
		// 		+ "	 </div></form></li>" +
		// 		"</ul></div></td>"+
		// 		"<td><div id='shop_item' style= background-color: rgb(228, 198, 164);'>"+ 
		// 		"<ul><li id='item' style= background-color: rgb(228, 198, 164);'><img src='images/smartLighting/LIFXSmartBulbs.jpg' alt='' /></li>"+ 
		// 		"<li><h3>" + "LIFX Smart Bulbs" + "</h3>"+ 
		// 		"<strong>" + "59.98" + "$</strong></li>" + 
		// 		"<li><form method='post' action='Cart' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "<input type='hidden' name='name' value='LIFXSmartBulbs' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "	<input type='hidden' name='type' value='smartLightings' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "	<input type='hidden' name='maker' value='lLIFX' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "	<input type='hidden' name='access' value='' style= background-color: rgb(228, 198, 164);'>"
		// 		+ "	<div class='btn-group'>"
		// 		+ "	<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"
		// 		+ "	 </div></form></li>" +
		// 		"</ul></div></td>"+
		// 		"</tr>" + "</div></div>");



		//////////////////////////////////
		/*pw.print("<img src='images/tvs/NanoleafLightPanels.jpg' alt='' /></li>");
		pw.print("<li><h3>" + "Nanoleaf Light Panels" + "</h3>");
		pw.print("<strong>" + "159.99" + "$</strong></li>"); */
		//utility.printHtml("Footer.html");
		
}

}