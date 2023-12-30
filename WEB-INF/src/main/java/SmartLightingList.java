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

 @WebServlet("/SmartLightingList")

 public class SmartLightingList extends HttpServlet {

 	/* SmartLightings Page Displays all the SmartLightings and their Information in SmartLighting Speed */

 	private static final long serialVersionUID = 1L;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 		response.setContentType("text/html");
 		PrintWriter pw = response.getWriter();

 		/* Checks the SmartLightings type whether it is electronicArts or activision or takeTwoInteractive */
				
 		String name = null;
 		String CategoryName = request.getParameter("maker");
 		HashMap<String, SmartLighting> hm = new HashMap<String, SmartLighting>();
		
		/////////////////////////////////////////////////////////////////////////////////
		List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("SmartLighting");
		List<SmartLighting> listTv = new ArrayList<>();
		int l_size = list.size();
		System.out.println(l_size);
		if(CategoryName==null){
			int i=0;
			for(AllProducts allProds : list) {
				SmartLighting smartLighting = allProds.getSmartLighting();
				hm.put("SmartLighting"+ i, smartLighting);
				i++;
			}
			name = "Smart Lighting";
		}
		else
		{
			int i=0;
			for(AllProducts allProds : list) {
				SmartLighting smartLighting = allProds.getSmartLighting();
				if(smartLighting.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("SmartLighting"+ i, smartLighting);
					i++;
					name = smartLighting.getRetailer() + " Smart Lighting";
				}
			}	
		}




		//////////////////////////////////////////////////////////////////////////////////////////
		/*
 		if(CategoryName==null)
 		{
 			hm.putAll(SaxParserDataStore.smartLightings);
 			name = "";
 		}
 		else
 		{
 		  if(CategoryName.equals("sPhilips"))
 		  {
 			for(Map.Entry<String,SmartLighting> entry : SaxParserDataStore.smartLightings.entrySet())
 				{
 				if(entry.getValue().getRetailer().equals("sPhilips"))
 				 {
 					 hm.put(entry.getValue().getId(),entry.getValue());
 		 }
 				}
 			name = "Philips";
 		  }
 		  else if(CategoryName.equals("lLIFX"))
 		  {
 			for(Map.Entry<String,SmartLighting> entry : SaxParserDataStore.smartLightings.entrySet())
 				{
 				if(entry.getValue().getRetailer().equals("lLIFX"))
 				 {
 					 hm.put(entry.getValue().getId(),entry.getValue());
 				 }
 				}	
 			name = "LIFX";
 		  }
 		  else if(CategoryName.equals("lNanoleaf"))
 		  {
 			for(Map.Entry<String,SmartLighting> entry : SaxParserDataStore.smartLightings.entrySet())
 				{
 				if(entry.getValue().getRetailer().equals("lNanoleaf"))
 				 {
 					 hm.put(entry.getValue().getId(),entry.getValue());
 				 }
 				}
 			name = "Nanoleaf";
 		  }
 		   else if(CategoryName.equals("lSengled"))
 		  {
 			for(Map.Entry<String,SmartLighting> entry : SaxParserDataStore.smartLightings.entrySet())
 				{
 				if(entry.getValue().getRetailer().equals("lSengled"))
 				 {
 					 hm.put(entry.getValue().getId(),entry.getValue());
 				 }
 				}
 			name = "Sengled";
 		  }
 		   
 		}

		pw.print("<body style='background-color: rgb(228, 198, 164);'>");



///////////////////////////////////////////////////////////////////////////////////////
*/
 		/* Header, Left Navigation Bar are Printed.
 		All the SmartLightings and SmartLightings information are dispalyed in the Content Section

 		and then Footer is Printed*/
	
 		Utilities utility = new Utilities(request,pw);
 		utility.printHtml("Header.html");
 		utility.printHtml("LeftNavigationBar.html");
 		pw.print("<div id='content' style='background-color: rgb(228, 198, 164);'><div class='post' style='background-color: rgb(228, 198, 164);'><h2 class='title meta' style='background-color: rgb(228, 198, 164);'>");
 		pw.print("<a style='font-size: 24px; background-color: rgb(228, 198, 164);'>" +name+" </a>");
 		pw.print("</h2><div class='entry'><table id='bestseller'>");
 		int i = 1; int size= hm.size();
 		for(Map.Entry<String, SmartLighting> entry : hm.entrySet()){
 			SmartLighting SmartLighting = entry.getValue();
 			if(i%3==1) 
				pw.print("<tr style='background-color: rgb(228, 198, 164);'>"); 
 			pw.print("<td><div id='shop_item' style='background-color: rgb(228, 198, 164);'>");
 			
			// pw.print("<h3>"+SmartLighting.getName()+"</h3>");
 			// pw.print("<strong>"+ "$" + SmartLighting.getPrice() + "</strong><ul style='background-color: rgb(228, 198, 164);'>");
 			// pw.print("<li id='item' style='background-color: rgb(228, 198, 164);border: none;'><img src='images/smartLighting/"+SmartLighting.getImage()+"' alt='' ></li>");
 			
			pw.print("<ul style='background-color: rgb(228, 198, 164);'><li id='item' style='background-color: rgb(228, 198, 164);border: none;'><img src='images/smartLighting/"
					+ SmartLighting.getImage() + "' alt='' /></li>");
			pw.print("<li><h3>" + SmartLighting.getName() + "</h3>");
			pw.print("<strong>" + SmartLighting.getPrice() + "$</strong></li>");
			pw.print("<strong>"+ SmartLighting.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			
			/////////////////////////////////////////////////////////////////////////////////////
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ SmartLighting.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartLighting.getId()+"'>"+
					"<input type='hidden' name='type' value='smartLighting'>"+
					"<input type='hidden' name='price' value='"+SmartLighting.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartLighting.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartLighting.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+ SmartLighting.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartLighting.getId()+"'>"+
					"<input type='hidden' name='type' value='smartLighting'>"+
					"<input type='hidden' name='price' value='"+SmartLighting.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartLighting.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartLighting.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ SmartLighting.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartLighting.getId()+"'>"+
					"<input type='hidden' name='category' value='smartLighting'>"+
					"<input type='hidden' name='price' value='"+SmartLighting.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartLighting.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartLighting.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-success' value='Write Product Review' style='margin-left:10%'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ SmartLighting.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartLighting.getId()+"'>"+
					"<input type='hidden' name='category' value='smartLighting'>"+
					"<input type='hidden' name='price' value='"+SmartLighting.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartLighting.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartLighting.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Product Reviews'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}

			
			
			/*
			pw.print("<li><form method='post' action='Cart'>" +
 					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
 					"<input type='hidden' name='type' value='smartLightings'>"+
 					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
 					"<input type='hidden' name='access' value=''>"+
 					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");


 			// pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
 			// 		"<input type='hidden' name='type' value='smartLightings'>"+
 			// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
 			// 		"<input type='hidden' name='access' value=''>"+
 			// 	    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
 			// pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
 			// 		"<input type='hidden' name='type' value='smartLightings'>"+
 			// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
 			// 		"<input type='hidden' name='access' value=''>"+
 			// 	    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
 			
			
			pw.print("<li><div style='text-align: center;'>"); // Center-align content
			pw.print("<form method='post' action='ProductDetailsPage'>" +
         	"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
         	"<input type ='hidden' name='des' value='"+SmartLighting.getDescription()+"'>" +
         	"<input type ='hidden' name='img' value='images/smartDoorbell/"+SmartLighting.getImage()+"'>" +
         	"<input type='hidden' name='price' value='"+SmartLighting.getPrice()+"'>"+
         	"<input type='hidden' name='type' value='SmartDoorbells'>"+
         	"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
         	"<input type='hidden' name='access' value=''>"+
         	"<div class ='btn-group'>" +
         	"<input type='submit' class='btnbuy' value='View Product'>"+
         	"</div>" + 
         	"</form></div></li>");
			// pw.print("<li><form method='post' action='ProductDetailsPage'>" +
			// 		"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
			// 		"<input type ='hidden' name='des' value='"+SmartLighting.getDescription()+"'>" +
			// 		"<input type ='hidden' name='img' value='images/smartDoorbell/"+SmartLighting.getImage()+"'>" +
			// 		"<input type='hidden' name='price' value='"+SmartLighting.getPrice()+"'>"+
			// 		"<input type='hidden' name='type' value='SmartDoorbells'>"+
			// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
			// 		"<input type='hidden' name='access' value=''>"+
			// 		"<div class ='btn-group'>" +
			// 			"<input type='submit' class='btnbuy' value='View Product'>"+
			// 		"</div>" + 
			// 		"</form></li>");
			
			pw.print("</ul></div></td>");
 			if(i%3==0 || i == size) pw.print("</tr>");
 			i++;
 		}		
 		pw.print("</table></div></div></div>");		
 		utility.printHtml("Footer.html");
		
 	}

 }
*/