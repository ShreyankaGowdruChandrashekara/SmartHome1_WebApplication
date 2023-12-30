import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductUpdatePage")

public class ProductUpdatePage extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session= request.getSession();
		Utilities util=new Utilities(request,out);
		
		System.out.println("pname is " + request.getParameter("pname"));
		
		util.printHtml("Header1.html");
		out.println("<div id='body' style=\"background-color:white;width:100%;\">");
		out.println("<section id='content' style=\"width:100%;background-color:white;\">");
		out.println("<article> <h1 align=\"center\"><span style='color:green;'>"+"Update Product</span></h1> ");
		if(request.getParameter("pname") == null) {
		out.println("<form  style='border:2px #f1f1f1 solid;' action=\"ProductUpdatePage\" method = 'get'><div style='padding:12px;'>");
		out.println("<label><b>Product Name</b></label><select name='pname'>");
        
        HashMap<Integer,String> map = new HashMap <>();
        map = MySQLDataStoreUtilities.getAllProducts();
        for(Map.Entry<Integer,String> map1 : map.entrySet()) {
            out.print("<option value = '" + map1.getKey()+ "'>" + map1.getValue() + "</option>");
        }
        out.println("<input type='submit' name='Order' value='Update Product' class='btnbuy'>");
        out.println("</div></form>");
		}
		
		else {
		    
		AllProducts allProducts = MySQLDataStoreUtilities.getproduct(Integer.parseInt(request.getParameter("pname")));
		
		 String productID = null,productName = null,productPrice = null,prodDisc = null,productCategory = null,prodDesc = null,prodImg=null,prodCondition = null,prodReatiler = null,quantity= null;
		 
		if(allProducts.getSpeaker() != null) {
            Speaker speaker= allProducts.getSpeaker();
            productID = speaker.getId();
            productName = speaker.getName();
            productPrice = String.valueOf(speaker.getPrice());
            prodDisc = String.valueOf(speaker.getDiscount());
            productCategory = "Speaker";
            prodDesc = speaker.getDescription();
            prodImg = speaker.getImage();
            prodCondition = speaker.getCondition();
            prodReatiler = speaker.getRetailer();
            quantity = String.valueOf(speaker.getQuantity());
            
        }
        else if(allProducts.getSmartDoorbell() != null) {
            SmartDoorbell smartDoorbell= allProducts.getSmartDoorbell();
            productID = smartDoorbell.getId();
            productName = smartDoorbell.getName();  
            productPrice = String.valueOf(smartDoorbell.getPrice());
            prodDisc = String.valueOf(smartDoorbell.getDiscount());
            productCategory = "SmartDoorbell";
            prodDesc = smartDoorbell.getDescription();
            prodImg = smartDoorbell.getImage();
            prodCondition = smartDoorbell.getCondition();
            prodReatiler = smartDoorbell.getRetailer();
            quantity = String.valueOf(smartDoorbell.getQuantity());
        } 
        else if(allProducts.getSmartDoorLock() != null) {
            SmartDoorLock smartDoorLock= allProducts.getSmartDoorLock();
            productID = smartDoorLock.getId();
            productName = smartDoorLock.getName();
            productPrice = String.valueOf(smartDoorLock.getPrice());
            prodDisc = String.valueOf(smartDoorLock.getDiscount());
            productCategory = "SmartDoorLock";
            prodDesc = smartDoorLock.getDescription();
            prodImg = smartDoorLock.getImage();
            prodCondition = smartDoorLock.getCondition();
            prodReatiler = smartDoorLock.getRetailer();
            quantity = String.valueOf(smartDoorLock.getQuantity());
        } 
		else if(allProducts.getSmartThermostat() != null) {
            SmartThermostat smartThermostat= allProducts.getSmartThermostat();
            productID = smartThermostat.getId();
            productName = smartThermostat.getName();
            productPrice = String.valueOf(smartThermostat.getPrice());
            prodDisc = String.valueOf(smartThermostat.getDiscount());
            productCategory = "SmartThermostat";
            prodDesc = smartThermostat.getDescription();
            prodImg = smartThermostat.getImage();
            prodCondition = smartThermostat.getCondition();
            prodReatiler = smartThermostat.getRetailer();
            quantity = String.valueOf(smartThermostat.getQuantity());
        } 
        else if(allProducts.getSmartLighting() != null) {
            SmartLighting smartLighting= allProducts.getSmartLighting();
            productID = smartLighting.getId();
            productName = smartLighting.getName();
            productPrice = String.valueOf(smartLighting.getPrice());
            prodDisc = String.valueOf(smartLighting.getDiscount());
            productCategory = "SmartLighting";
            prodDesc = smartLighting.getDescription();
            prodImg = smartLighting.getImage();
            prodCondition = smartLighting.getCondition();
            prodReatiler = smartLighting.getRetailer();
            quantity = String.valueOf(smartLighting.getQuantity());
            
        } 
        
        out.println("<form  style='border:2px #f1f1f1 solid;' action=\"ProductChanges\" method = 'post'><div style='padding:12px;'>");   
        out.println("<label><b>Product Name</b></label></br><textarea style='width:100%;' placeholder='Enter Product Name' name='pname' required>"+ productName +"</textarea></br></br>");
        out.println("<label><b>Product Description</b></label></br><textarea style='width:100%;' placeholder='Enter Product Description' name='pdescription' required>"+prodDesc +"</textarea></br></br>");
		out.println("<label><b>Product Discount</b></label></br><textarea style='width:100%;' type='text' placeholder='Enter Product Discount' name='pdisc' required \">"+ prodDisc +"</textarea></br></br>");
		out.println("<label><b>Product Price</b></label><textarea style='width:100%' type='text' placeholder='Enter Product Price' name='pprice' \">"+ productPrice +"</textarea></br></br>");
		out.println("<label><b>Enter Product Image Name</b></label></br><textarea style='width:100%;' type='text'  placeholder='Enter Product Image Name(with Extension)' name='pimage' required >"+ prodImg +"</textarea></br></br>");
		out.println("<label><b>Product Condition</b></label></br><textarea style='width:100%;' type='text' placeholder='Enter Product Condition' name='pcondition' required \">"+ prodCondition +"</textarea></br></br>");
		out.println("<label><b>Product Company</b></label></br><textarea style='width:100%;' type='text' placeholder='Enter Product Company' name='pcompany' required \">"+prodReatiler +"</textarea></br></br>");
		out.println("<label><b>Product Quantity</b></label></br><textarea style='width:100%;' type='number' placeholder='Enter Product Quantity' name='pquantity' required \">"+quantity +"</textarea></br></br>");
		out.println(
                "<label><b>Is there any rebate on the product?</b></label></br><select name='pRebate'><option value='1' selected>Yes</option><option value='0'>No</option></select></br></br>");
		out.println(
                "<label><b>Is there any Sale on the product?</b></label></br><select name='pSale'><option value='1' selected>Yes</option><option value='0'>No</option></select></br></br>");
		out.println(
                "<label><b>Select Category</b></label></br><select name='pcategory'><option value='Speaker' selected>Speaker</option><option value='SmartDoorbell'>Smart Doorbell</option><option value='SmartDoorLock'>Smart DoorLock</option><option value='SmartLighting'>Smart Lighting</option><option value='SmartThermostat'>Smart Thermostat</option> "+ productCategory+"</select>");
		out.println("<input type='hidden' name='operation' value='update'>");
		out.println("<input type='hidden' name='prodID' value='"+productID+"'>");
		out.println("<br/> <br/> <input type='submit' name='Update Product' class='btn btn-primary btn-lg' style='width : 90%' value='Update Product'>");
		out.println("</div></form>");
        }
		out.println("</article></section>");
	
	}
}