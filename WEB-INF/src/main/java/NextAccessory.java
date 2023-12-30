import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NextAccessory")

public class NextAccessory extends HttpServlet {

	/* Tv Page Displays all the Tvs and their Information in Game Speed */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		String ProductName = request.getParameter("access");
		int j = Integer.parseInt(request.getParameter("value"));


		/* Checks the Laptops type whether it is microsft or sony or nintendo */

		HashMap<String, SmartDoorbell> hm = new HashMap<String, SmartDoorbell>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.smartDoorbells);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("dbAmazon"))
		   {
			 for(Map.Entry<String,SmartDoorbell> entry : SaxParserDataStore.smartDoorbells.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("dbAmazon"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Amazon";
		   }
		   else if(CategoryName.equals("dbGoogle"))
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
			else if(CategoryName.equals("dbArlo"))
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
				else if(CategoryName.equals("dbAnkerInnovations"))
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
			else if(CategoryName.equals("dbSimpliSafe"))
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
		
		/* Header, Left Navigation Bar are Printed.

		All the Tv and Tv information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" SmartDoorbells</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
	//	int i = 1; int size= hm.size();
		for(Map.Entry<String, SmartDoorbell> entry : hm.entrySet())
		{
			SmartDoorbell smartDoorbell = entry.getValue();
			if (smartDoorbell.getName().equals(ProductName))
			{
			pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+smartDoorbell.getName()+"</h3>");
			pw.print("<strong>$"+smartDoorbell.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/tvs/"+smartDoorbell.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='smartDoorbells'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("</ul></div></td>");
			pw.print("</tr>");
			
			}
		}		
		pw.print("</table></div></div></div>");	
		
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+ProductName+" Accessories</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		SmartDoorbell smartDoorbell1 = hm.get(ProductName);
		System.out.print(ProductName);
		int i = 1; int size= hm.size();
		pw.print("<tr>");
		ArrayList<String> arr = new ArrayList<String> ();
		for(Map.Entry<String, String> acc:smartDoorbell1.getAccessories().entrySet())
		{
			
			arr.add(acc.getValue());
		}	
		
		pw.print("<td><li><form method='post' action='PrevAccessory'>" +
					"<input type='hidden' name='name' value='"+arr+"'>"+
					"<input type='hidden' name='value' value='"+j+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value='"+ProductName+"'>"+
					"<input type='submit' class='btnbuy' value='Prev'></form></li></td>");
		for (int k =1;j < arr.size()&& k <= 2;j++,k++){
	    Accessory accessory= SaxParserDataStore.accessories.get(arr.get(j));
		    
			
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+accessory.getName()+"</h3>");
			pw.print("<strong>"+accessory.getPrice()+"$</strong><ul>");
			pw.print("<li id='item'><img src='images/accessories/"+accessory.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+arr.get(j)+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value='"+ProductName+"'>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
		
			pw.print("</ul></div></td>");
			
			
			
			
		}
		if (j != arr.size())
		{
		pw.print("<td><li><form method='post' action='NextAccessory'>" +
					"<input type='hidden' name='name' value='"+arr+"'>"+
					"<input type='hidden' name='value' value='"+j+"'>"+
					"<input type='hidden' name='type' value='accessories'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value='"+ProductName+"'>"+
					"<input type='submit' class='btnbuy' value='Next'></form></li></td>");
		}
		pw.print("</tr>");		
		pw.print("</table><a class='prev' onclick='plusSlides(-1)'>&#10094</a>"+
       "<a class='next' onclick='plusSlides(1)'>&#10095</a></div></div></div>");
     		
		utility.printHtml("Footer.html");
		
	}
}
