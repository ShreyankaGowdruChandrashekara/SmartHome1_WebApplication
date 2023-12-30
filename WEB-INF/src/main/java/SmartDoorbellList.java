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

@WebServlet("/SmartDoorbellList")

public class SmartDoorbellList extends HttpServlet {

	/* Trending Page Displays all the SmartDoorbells and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the SmartDoorbells type whether it is Ring Video Doorbell or Nest Hello Video Doorbell or Arlo Video Doorbell or Eufy Video            Doorbell or SimpliSafe Video Doorbell */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, SmartDoorbell> hm = new HashMap<String, SmartDoorbell>();

	///////////////////////////////////////changed code/////////////////////////////////////////
		List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("SmartDoorbell");
		List<SmartDoorbell> listTv = new ArrayList<>();
		int l_size = list.size();
		System.out.println(l_size);
		if(CategoryName==null){
			int i=0;
			for(AllProducts allProds : list) {
				SmartDoorbell smartDoorbell = allProds.getSmartDoorbell();
				hm.put("SmartDoorbell"+ i, smartDoorbell);
				i++;
			}
			name = "Smart Doorbells";
		}
		else
		{
			int i=0;
			for(AllProducts allProds : list) {
				SmartDoorbell smartDoorbell = allProds.getSmartDoorbell();
				String retailer = smartDoorbell	.getRetailer();
				if(smartDoorbell.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("SmartDoorbell"+ i, smartDoorbell);
					i++;
					name = smartDoorbell.getRetailer() + " Smart Doorbells" ;
				}
			}
		
		}

///////////////////////////////////////////////////////////////////////////////////


/*

		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.smartDoorbells);
			name = "Smart Doorbells";
		} 
		else 
		{
			if(CategoryName.equals("dbAmazon")) 
			{	
				for(Map.Entry<String,SmartDoorbell> entry : SaxParserDataStore.smartDoorbells.entrySet())
				{
					System.out.println(entry.getValue().getRetailer());
				  if(entry.getValue().getRetailer().equals("dbAmazon"))
				  {
					hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Amazon";
			} 
			else if (CategoryName.equals("dbGoogle"))
			{
				for(Map.Entry<String,SmartDoorbell> entry : SaxParserDataStore.smartDoorbells.entrySet())
				{
				  	if(entry.getValue().getRetailer().equals("dbGoogle"))
				  	{
						hm.put(entry.getValue().getId(),entry.getValue());
				  	}
				}
				name = "Google";
			} 
			else if (CategoryName.equals("dbArlo")) 
			{
				for(Map.Entry<String,SmartDoorbell> entry : SaxParserDataStore.smartDoorbells.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("dbArlo"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Arlo";
			}
			else if (CategoryName.equals("dbAnkerInnovations")) 
			{
				for(Map.Entry<String,SmartDoorbell> entry : SaxParserDataStore.smartDoorbells.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("dbAnkerInnovations"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Anker Innovations";
			}
			else if (CategoryName.equals("dbSimpliSafe")) 
			{
				for(Map.Entry<String,SmartDoorbell> entry : SaxParserDataStore.smartDoorbells.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("dbSimpliSafe"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Simpli Safe";
			}			
	    }
		// Add this line to set the background color in your servlet response
		pw.print("<body style='background-color: rgb(228, 198, 164);'>");

*/
/////////////////////////////////////////////////////////////////////////////////
		/* Header, Left Navigation Bar are Printed.

		All the smartDoorbells and SmartDoorbell information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content' style='background-color: rgb(228, 198, 164);'><div class='post' style='background-color: rgb(228, 198, 164);'><h2 class='title meta' style='background-color: rgb(228, 198, 164);'>");
		pw.print("<a style='font-size: 24px; background-color: rgb(228, 198, 164);'>" + name + " </a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, SmartDoorbell> entry : hm.entrySet()) {
			SmartDoorbell SmartDoorbell = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr style='background-color: rgb(228, 198, 164);'>"); 
			pw.print("<td><div id='shop_item' style='background-color: rgb(228, 198, 164);'>");
			pw.print("<ul style='background-color: rgb(228, 198, 164);'><li id='item' style='background-color: rgb(228, 198, 164);border: none;'><img src='images/smartDoorbell/"
					+ SmartDoorbell.getImage() + "' alt='' /></li>");
			pw.print("<li><h3>" + SmartDoorbell.getName() + "</h3>");
			pw.print("<strong>" + SmartDoorbell.getPrice() + "$</strong></li>");
			pw.print("<strong>"+ SmartDoorbell.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			//////////////////////////////////////////////////////////////

			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ SmartDoorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartDoorbell.getId()+"'>"+
					"<input type='hidden' name='type' value='smartDoorbell'>"+
					"<input type='hidden' name='price' value='"+SmartDoorbell.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartDoorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartDoorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+ SmartDoorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartDoorbell.getId()+"'>"+
					"<input type='hidden' name='type' value='smartDoorbell'>"+
					"<input type='hidden' name='price' value='"+SmartDoorbell.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartDoorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartDoorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ SmartDoorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartDoorbell.getId()+"'>"+
					"<input type='hidden' name='category' value='smartDoorbell'>"+
					"<input type='hidden' name='price' value='"+SmartDoorbell.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartDoorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartDoorbell.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-success' value='Write Product Review' style='margin-left:10%'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ SmartDoorbell.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartDoorbell.getId()+"'>"+
					"<input type='hidden' name='category' value='smartDoorbell'>"+
					"<input type='hidden' name='price' value='"+SmartDoorbell.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartDoorbell.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartDoorbell.getDescription()+"'>"+
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
					"<input type='hidden' name='type' value='smartDoorbells'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now'>"+
					"</div></form>");
			// pw.print("<li><form method='post' action='ProductDetailsPage'>" +
			// 		"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
			// 		"<input type='hidden' name='id' value='"+SmartDoorbell.getId()+"'>"+
			// 		"<input type='hidden' name='type' value='smartDoorbells'>"+
			// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
			// 		"<div class ='btn-group'>" +
			// 			"<input type='submit' class='btn btn-default' value='View Product'>"+
			// 		"</div>" + 
			// 		"</form></li>");



			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type ='hidden' name='des' value='"+SmartDoorbell.getDescription()+"'>" +
					"<input type ='hidden' name='img' value='images/smartDoorbell/"+SmartDoorbell.getImage()+"'>" +
					"<input type='hidden' name='price' value='"+SmartDoorbell.getPrice()+"'>"+
					"<input type='hidden' name='type' value='SmartDoorbells'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class ='btn-group'>" +
						"<input type='submit' class='btnbuy' value='View Product'>"+
					"</div>" + 
					"</form></li>");





			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}*/
