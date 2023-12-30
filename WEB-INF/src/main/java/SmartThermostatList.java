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

@WebServlet("/SmartThermostatList")

public class SmartThermostatList extends HttpServlet {

	/* Trending Page Displays all the SmartThermostats and their Information in Game Speed */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the SmartThermostats type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, SmartThermostat> hm = new HashMap<String, SmartThermostat>();

////////////////////////////////////////////////////////////////////////////////////////
List<AllProducts> list = new ArrayList<>();
		list = MySQLDataStoreUtilities.getAllWearable("SmartThermostat");
		List<SmartThermostat> listTv = new ArrayList<>();
		int l_size = list.size();
		System.out.println(l_size);
		if(CategoryName==null){
			int i=0;
			for(AllProducts allProds : list) {
				SmartThermostat smartThermostat = allProds.getSmartThermostat();
				hm.put("SmartThermostat"+ i, smartThermostat);
				i++;
			}
			name = "smart Thermostat";
		}
		else
		{
			int i=0;
			for(AllProducts allProds : list) {
				SmartThermostat smartThermostat = allProds.getSmartThermostat();
				String retailer = smartThermostat.getRetailer();
				if(smartThermostat.getRetailer().toUpperCase().equals(CategoryName.toUpperCase())) {
					hm.put("SmartThermostat"+ i, smartThermostat);
					i++;
					name = smartThermostat.getRetailer() + " Smart Thermostat";
				}
			}		
		}


//////////////////////////////////////////////////////////////////////////////////////////
/*
		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.smartThermostats);
			name = "Smart Thermostats";
		} 
		else 
		{
			if(CategoryName.equals("tNestLabs")) 
			{	
				for(Map.Entry<String,SmartThermostat> entry : SaxParserDataStore.smartThermostats.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("tNestLabs"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Nest Labs";
			} 
			else if (CategoryName.equals("tEcobee")) 
			{
				for(Map.Entry<String,SmartThermostat> entry : SaxParserDataStore.smartThermostats.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("tEcobee"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Ecobee";
			}
			else if (CategoryName.equals("tHoneywellInternational")) 
			{
				for(Map.Entry<String,SmartThermostat> entry : SaxParserDataStore.smartThermostats.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("tHoneywellInternational"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Honeywell International";
			}	
			else if (CategoryName.equals("tEmersonElectric")) 
			{
				for(Map.Entry<String,SmartThermostat> entry : SaxParserDataStore.smartThermostats.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("tEmersonElectric"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Emerson Electric";
			}						
	    }
		pw.print("<body style='background-color: rgb(228, 198, 164);'>");

/////////////////////////////////////////////////////////////////////////////////
*/
		/* Header, Left Navigation Bar are Printed.

		All the Tablets and phone information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'  style='background-color: rgb(228, 198, 164);'><div class='post' style='background-color: rgb(228, 198, 164);'><h2 class='title meta' style='background-color: rgb(228, 198, 164);'>");
		pw.print("<a style='font-size: 24px; background-color: rgb(228, 198, 164);'>" + name + " </a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, SmartThermostat> entry : hm.entrySet()) {
			SmartThermostat SmartThermostat = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr style='background-color: rgb(228, 198, 164);'>");
			pw.print("<td><div id='shop_item' style='background-color: rgb(228, 198, 164);'>");
			pw.print("<ul style='background-color: rgb(228, 198, 164);'><li id='item' style='background-color: rgb(228, 198, 164);border: none;'><img src='images/smartThermostat/"
					+ SmartThermostat.getImage() + "' alt='' /></li>");
			pw.print("<li><h3>" + SmartThermostat.getName() + "</h3>");
			pw.print("<strong>" + SmartThermostat.getPrice() + "$</strong></li>");
			pw.print("<strong>"+ SmartThermostat.getDiscount()+ "% disocunt is offered "+"</strong></li>");
			pw.print("<li><div style='text-align: center;'>"); // Center-align content
			
			/////////////////////////////////////////////////////////////////////////////////////
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+ SmartThermostat.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartThermostat.getId()+"'>"+
					"<input type='hidden' name='type' value='smartThermostat'>"+
					"<input type='hidden' name='price' value='"+SmartThermostat.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartThermostat.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartThermostat.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<div class='btn-group'>"+
					"<input type='submit' class='btn btn-success' value='Buy Now' style='margin-left:70%'>"+
					"</div></form>");
			pw.print("<li><form method='post' action='ProductDetailsPage'>" +
					"<input type='hidden' name='name' value='"+ SmartThermostat.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartThermostat.getId()+"'>"+
					"<input type='hidden' name='type' value='smartThermostat'>"+
					"<input type='hidden' name='price' value='"+SmartThermostat.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartThermostat.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartThermostat.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Product'></form></li>");
			pw.print("<li><form method='post' action='WriteReviewsPage'>" +
					"<input type='hidden' name='name' value='"+ SmartThermostat.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartThermostat.getId()+"'>"+
					"<input type='hidden' name='category' value='smartThermostat'>"+
					"<input type='hidden' name='price' value='"+SmartThermostat.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartThermostat.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartThermostat.getDescription()+"'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btn btn-success' value='Write Product Review' style='margin-left:10%'></form></li>");
			pw.print("<li><form method='get' action='DisplayReviews'>" +
					"<input type='hidden' name='name' value='"+ SmartThermostat.getName()+"'>"+
					"<input type='hidden' name='id' value='"+SmartThermostat.getId()+"'>"+
					"<input type='hidden' name='category' value='smartThermostat'>"+
					"<input type='hidden' name='price' value='"+SmartThermostat.getPrice()+"'>"+
					"<input type='hidden' name='img' value='"+SmartThermostat.getImage()+"'>"+
					"<input type='hidden' name='desc' value='"+SmartThermostat.getDescription()+"'>"+
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
			pw.print("<form method='post' action='Cart'>" +
         		"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
         		"<input type='hidden' name='type' value='smartThermostats'>"+
         		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
         		"<input type='hidden' name='access' value=''>"+
         		"<div class='btn-group'>"+
         		"<input type='submit' class='btn btn-success' value='Buy Now'>"+
         		"</div></form>");
			pw.print("</div></li>");

			pw.print("<li><div style='text-align: center;'>"); // Center-align content
			pw.print("<form method='post' action='ProductDetailsPage'>" +
         		"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
         		"<input type='hidden' name='id' value='"+SmartThermostat.getId()+"'>"+
         		"<input type='hidden' name='type' value='smartThermostats'>"+
         		"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
         		"<input type='hidden' name='access' value=''>"+
         		"<div class ='btn-group'>" +
         		"<input type='submit' class='btnbuy' value='View Product'>"+
         		"</div>" + 
         	"</form></div></li>");
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