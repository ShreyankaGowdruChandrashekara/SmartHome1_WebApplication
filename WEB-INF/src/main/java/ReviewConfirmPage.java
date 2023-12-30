import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import java.io.*;

@WebServlet("/ReviewConfirmPage")


public class ReviewConfirmPage extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		
		String ratings = request.getParameter("ratings");
		String reviews = request.getParameter("reviews");
		String userName = request.getParameter("userName");
		//String userID = request.getParameter("userID");
		String prodID = request.getParameter("prodID");
		String age = request.getParameter("age");
		String prodCategory = request.getParameter("prodCategory");

		String gender = request.getParameter("gender");
		String occupation = request.getParameter("occupation");

		String result; 
		System.out.println("ReviewConfirmPage");
		System.out.println(" Ratings : " + ratings + " reviews : " + reviews + " userName :" + userName ); //+ " userID : " + userID);
		

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 
        
        AllProducts allProducts   = MySQLDataStoreUtilities.getproduct(Integer.parseInt(prodID));
        //StoreDetails storeDetails = MySQLDataStoreUtilities.fetchUserStore(userName);
        
        ReviewDetail reviewDetail = new ReviewDetail();
		LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = dateObj.format(formatter);
		reviewDetail.setReviewDate(date);
		reviewDetail.setReviewRating(ratings);
		reviewDetail.setReviewText(reviews);
		reviewDetail.setUserId(userName);
		reviewDetail.setUserOccupation(occupation);
		reviewDetail.setUserGender(gender);
		reviewDetail.setUserAge(age);
		reviewDetail.setRetailerName("Michigan Avenue");
		reviewDetail.setRetailerState("Chicago");
		reviewDetail.setRetailerZip("600101");
		reviewDetail.setRetailerCity("Chicago");
		// reviewDetail.setRetailerName(storeDetails.getName());
		// reviewDetail.setRetailerState(storeDetails.getState());
		// reviewDetail.setRetailerZip(String.valueOf(storeDetails.getZipcode()));
		// reviewDetail.setRetailerCity(storeDetails.getCity());
		
		reviewDetail.setProductRebate("Yes");
		
		/////////////////////////////
		System.out.println(prodCategory);
		switch(prodCategory) {
		case "speaker":{
				Speaker speaker = allProducts.getSpeaker();
				if(speaker.getDiscount() > 0)
					reviewDetail.setOnSale("Yes");
				else 
					reviewDetail.setOnSale("No");
				
				reviewDetail.setProductCompany(speaker.getRetailer());
				reviewDetail.setProductName(speaker.getName());
				reviewDetail.setProductID(speaker.getId());
				reviewDetail.setProductPrice(String.valueOf(speaker.getPrice()));
				
				break;	
				}
		case "smartDoorLock":{
			SmartDoorLock smartDoorLock = allProducts.getSmartDoorLock();
			if(smartDoorLock.getDiscount() > 0)
				reviewDetail.setOnSale("Yes");
			else 
				reviewDetail.setOnSale("No");
			reviewDetail.setProductCompany(smartDoorLock.getRetailer());
			reviewDetail.setProductName(smartDoorLock.getName());
			reviewDetail.setProductID(smartDoorLock.getId());
			reviewDetail.setProductPrice(String.valueOf(smartDoorLock.getPrice()));
			break;	
				}
    	case "smartDoorbell":{
    		SmartDoorbell smartDoorbell = allProducts.getSmartDoorbell();
    		if(smartDoorbell.getDiscount() > 0)
    			reviewDetail.setOnSale("Yes");
    		else 
    			reviewDetail.setOnSale("No");
    		reviewDetail.setProductCompany(smartDoorbell.getRetailer());
    		reviewDetail.setProductName(smartDoorbell.getName());
    		reviewDetail.setProductID(smartDoorbell.getId());
    		reviewDetail.setProductPrice(String.valueOf(smartDoorbell.getPrice()));
    		break;	
    			}

        case "smartThermostat":{
    		SmartThermostat smartThermostat = allProducts.getSmartThermostat();
    		if(smartThermostat.getDiscount() > 0)
    			reviewDetail.setOnSale("Yes");
    		else 
    			reviewDetail.setOnSale("No");
    		reviewDetail.setProductCompany(smartThermostat.getRetailer());
    		reviewDetail.setProductName(smartThermostat.getName());
    		reviewDetail.setProductID(smartThermostat.getId());
    		reviewDetail.setProductPrice(String.valueOf(smartThermostat.getPrice()));
    		break;	
    			}
    	case "smartLighting":{
    		SmartLighting smartLighting = allProducts.getSmartLighting();
    		if(smartLighting.getDiscount() > 0)
    			reviewDetail.setOnSale("Yes");
    		else 
    			reviewDetail.setOnSale("No");
    		reviewDetail.setProductCompany(smartLighting.getRetailer());
    		reviewDetail.setProductName(smartLighting.getName());
    		reviewDetail.setProductID(smartLighting.getId());
    		reviewDetail.setProductPrice(String.valueOf(smartLighting.getPrice()));
    		break;	
    			}
    		}
    		reviewDetail.setProductCategory(prodCategory);
    		
    		MongoDbDataStoreUtilities.connect();
    		MongoDbDataStoreUtilities.addReview(reviewDetail);
    		MongoDbDataStoreUtilities.closeConnection();
    
    		
    		utility.printHtml("Header.html");
    		utility.printHtml("LeftNavigationBar.html");
    		pw.print("<b> Review Added succesfully. Go to prodcust to view the reviews </b>");
    		utility.printHtml("Footer.html");
		
}

}