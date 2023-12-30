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

@WebServlet("/SpeakerList")

public class SpeakerList extends HttpServlet {

	/* Trending Page Displays all the Speakers and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the Speakers type whether it is ----------------------------------------------------microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Speaker> hm = new HashMap<String, Speaker>();

//////////////////////////////////////////////////////////////////////////////////////////

List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("Speaker");
		List<Speaker> listTv = new ArrayList<>();
		int l_size = list.size();
		System.out.println(l_size);
		if(CategoryName==null){
			int i=0;
			for(AllProducts allProds : list) {
				Speaker speaker = allProds.getSpeaker();
				hm.put("Speaker"+ i, speaker);
				i++;
			}
			name = "Speaker";
		}
		else
		{
			int i=0;
			for(AllProducts allProds : list) {
				Speaker speaker = allProds.getSpeaker();
				if(speaker.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("Speaker"+ i, speaker);
					i++;
					name = speaker.getRetailer() + " Speaker" ;
				}
			}
		
		}
///////////////////////////////////////////////////////////////////////////////////////////
/*
		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.speakers);
			name = "";
		} 
		else 
		{
			if(CategoryName.equals("sAmazon")) 
			{	
				for(Map.Entry<String,Speaker> entry : SaxParserDataStore.speakers.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("sAmazon"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Amazon";
			} 
			else if (CategoryName.equals("sGoogle")) 
			{
				for(Map.Entry<String,Speaker> entry : SaxParserDataStore.speakers.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("sGoogle"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Google";
			}
			else if (CategoryName.equals("sApple")) 
			{
				for(Map.Entry<String,Speaker> entry : SaxParserDataStore.speakers.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("sApple"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Apple";
			}
			else if (CategoryName.equals("sBose")) 
			{
				for(Map.Entry<String,Speaker> entry : SaxParserDataStore.speakers.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("sBose"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Bose";
			}						
	    }
		pw.print("<body style='background-color: rgb(228, 198, 164);'>");

		///////////////////////////////////////////////////////////////////////////////////
		*/

		/* Header, Left Navigation Bar are Printed.

		All the speakers and speaker information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content' style='background-color: rgb(228, 198, 164);'><div class='post' style='background-color: rgb(228, 198, 164);'><h2 class='title meta' style='background-color: rgb(228, 198, 164);'>");
		pw.print("<a style='font-size: 24px;background-color: rgb(228, 198, 164);'>" + name + " </a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, Speaker> entry : hm.entrySet()) {
			Speaker Speaker = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr style='background-color: rgb(228, 198, 164);'>"); 
			pw.print("<td><div id='shop_item' style='background-color: rgb(228, 198, 164);'>");
			pw.print("<ul style='background-color: rgb(228, 198, 164);'><li id='item' style='background-color: rgb(228, 198, 164);border: none;'><img src='images/speaker/"
					+ Speaker.getImage() + "' alt='' /></li>");
			pw.print("<li><h3>" + Speaker.getName() + "</h3>");
			pw.print("<strong>" + Speaker.getPrice() + "$</strong></li>");
			pw.print("<strong>"+ Speaker.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			
			/////////////////////////////////////////////////////////////////////////////////////
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ Speaker.getName()+"'>"+
					"<input type='hidden' name='id' value='"+Speaker.getId()+"'>"+
					"<input type='hidden' name='type' value='speaker'>"+
					"<input type='hidden' name='price' value='"+Speaker.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+Speaker.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+Speaker.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+ Speaker.getName()+"'>"+
					"<input type='hidden' name='id' value='"+Speaker.getId()+"'>"+
					"<input type='hidden' name='type' value='speaker'>"+
					"<input type='hidden' name='price' value='"+Speaker.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+Speaker.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+Speaker.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ Speaker.getName()+"'>"+
					"<input type='hidden' name='id' value='"+Speaker.getId()+"'>"+
					"<input type='hidden' name='category' value='speaker'>"+
					"<input type='hidden' name='price' value='"+Speaker.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+Speaker.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+Speaker.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-success' value='Write Product Review' style='margin-left:10%'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ Speaker.getName()+"'>"+
					"<input type='hidden' name='id' value='"+Speaker.getId()+"'>"+
					"<input type='hidden' name='category' value='speaker'>"+
					"<input type='hidden' name='price' value='"+Speaker.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+Speaker.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+Speaker.getDescription()+"'>"+
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
			
			
			
			
			
			
			
			
			
			
			
			/*pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='speakers'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='id' value='"+Speaker.getId()+"'>"+
					"<input type='hidden' name='type' value='speakers'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Product'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
*/