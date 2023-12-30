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

@WebServlet("/SmartDoorLockList")

public class SmartDoorLockList extends HttpServlet {

	/* Trending Page Displays all the SmartDoorLocks and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the SmartDoorLocks type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, SmartDoorLock> hm = new HashMap<String, SmartDoorLock>();



//////////////////////////////////////////////////////////////////////////////////////
	List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("SmartDoorLock");
		List<SmartDoorLock> listTv = new ArrayList<>();
		int l_size = list.size();
		System.out.println(l_size);
		if(CategoryName==null){
			int i=0;
			for(AllProducts allProds : list) {
				SmartDoorLock smartDoorLock = allProds.getSmartDoorLock();
				hm.put("SmartDoorLock"+ i, smartDoorLock);
				i++;
			}
			name = "Smart DoorLock";
		}
		else
		{
			int i=0;
			for(AllProducts allProds : list) {
				SmartDoorLock smartDoorLock = allProds.getSmartDoorLock();
				String retailer = smartDoorLock.getRetailer();
				if(smartDoorLock.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("SmartDoorLock"+ i, smartDoorLock);
					i++;
					name = smartDoorLock.getRetailer() + " Smart DoorLock";
				}
			}		
		}



//////////////////////////////////////////////////////////////////////////////////////
/*
		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.smartDoorLocks);
			name = "";
		} 
		else 
		{
			if(CategoryName.equals("dlAssaAbloy")) 
			{	
				for(Map.Entry<String,SmartDoorLock> entry : SaxParserDataStore.smartDoorLocks.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("dlAssaAbloy"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Assa Abloy";
			} 
			else if (CategoryName.equals("dlAllegion"))
			{
				for(Map.Entry<String,SmartDoorLock> entry : SaxParserDataStore.smartDoorLocks.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("dlAllegion"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Allegion";
			} 
			else if (CategoryName.equals("dlSpectrumBrands")) 
			{
				for(Map.Entry<String,SmartDoorLock> entry : SaxParserDataStore.smartDoorLocks.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("dlSpectrumBrands"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Spectrum Brands";
			}
			else if (CategoryName.equals("dlPINGenie")) 
			{
				for(Map.Entry<String,SmartDoorLock> entry : SaxParserDataStore.smartDoorLocks.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("dlPINGenie"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "PIN Genie";
			}
	    }
		pw.print("<body style='background-color: rgb(228, 198, 164);'>");

		*/
		///////////////////////////////////////////////////////////////////////////////
		/* Header, Left Navigation Bar are Printed.

		All the SmartDoorLocks and SmartDoorLock information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content' style='background-color: rgb(228, 198, 164);'><div class='post' style='background-color: rgb(228, 198, 164);'><h2 class='title meta' style='background-color: rgb(228, 198, 164);'>");
		pw.print("<a style='font-size: 24px;background-color: rgb(228, 198, 164);'>" + name + " </a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, SmartDoorLock> entry : hm.entrySet()) {
			SmartDoorLock SmartDoorLock = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr style='background-color: rgb(228, 198, 164);'>");
			pw.print("<td><div id='shop_item' style='background-color: rgb(228, 198, 164);'>");



			pw.print("<ul style='background-color: rgb(228, 198, 164);'><li id='item' style='background-color: rgb(228, 198, 164);border: none;'><img src='images/smartDoorLock/"
					+ SmartDoorLock.getImage() + "' alt='' /></li>");
			pw.print("<li><h3>" + SmartDoorLock.getName() + "</h3>");
			pw.print("<strong>" + SmartDoorLock.getPrice() + "$</strong></li>");
			pw.print("<strong>"+ SmartDoorLock.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			
			/////////////////////////////////////////////////////////////////////////////////////
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ SmartDoorLock.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartDoorLock.getId()+"'>"+
					"<input type='hidden' name='type' value='smartDoorLock'>"+
					"<input type='hidden' name='price' value='"+SmartDoorLock.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartDoorLock.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartDoorLock.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+ SmartDoorLock.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartDoorLock.getId()+"'>"+
					"<input type='hidden' name='type' value='smartDoorLock'>"+
					"<input type='hidden' name='price' value='"+SmartDoorLock.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartDoorLock.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartDoorLock.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ SmartDoorLock.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartDoorLock.getId()+"'>"+
					"<input type='hidden' name='category' value='smartDoorLock'>"+
					"<input type='hidden' name='price' value='"+SmartDoorLock.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartDoorLock.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartDoorLock.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-success' value='Write Product Review' style='margin-left:10%'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ SmartDoorLock.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartDoorLock.getId()+"'>"+
					"<input type='hidden' name='category' value='smartDoorLock'>"+
					"<input type='hidden' name='price' value='"+SmartDoorLock.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartDoorLock.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartDoorLock.getDescription()+"'>"+
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
					"<input type='hidden' name='type' value='smartDoorLocks'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now'>"+
					"</div></form>");


			// pw.print("<h3>" + smartDoorLock.getName() + "</h3>");
			// pw.print("<strong>" + smartDoorLock.getPrice() + "$</strong><ul>");
			// pw.print("<li id='item'><img src='images/smartDoorLock/"
			// 		+ smartDoorLock.getImage() + "' alt='' /></li>");
			// pw.print("<li><form method='post' action='Cart'>" +
			// 		"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
			// 		"<input type='hidden' name='type' value='smartDoorLocks'>"+
			// 		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
			// 		"<input type='hidden' name='access' value=''>"+
			// 		"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type ='hidden' name='des' value='"+smartDoorLock.getDescription()+"'>" +
					"<input type ='hidden' name='img' value='images/smartDoorLock/"+smartDoorLock.getImage()+"'>" +
					"<input type='hidden' name='price' value='"+smartDoorLock.getPrice()+"'>"+
					"<input type='hidden' name='type' value='SmartDoorLocks'>"+
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
