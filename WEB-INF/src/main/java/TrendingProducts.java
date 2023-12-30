import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TrendingProducts")

public class TrendingProducts extends HttpServlet {

	/* Trending Page Displays all the Tvs and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Tvs type whether it is microsft or sony or nintendo then add products to hashmap*/

		String name = "Most Sold";
		String CategoryName = request.getParameter("maker");
		
		
		Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
		int i=1;
		try {
            
	        
	        MongoDbDataStoreUtilities.connect();
	        HashMap<String,ArrayList<ReviewDetail>> rs = MongoDbDataStoreUtilities.getAllReviews();
	        
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
	        pw.print("<a style='font-size: 24px;'>"+name+" Products</a>");
	        pw.print("</h2><div class='entry'><table id='bestseller'>");
	        HashMap<String,Float> avgMap = new HashMap<String,Float>();
	        for(Map.Entry<String, ArrayList<ReviewDetail>> e : rs.entrySet())
	        {
	            ArrayList<ReviewDetail> rsList = e.getValue();          
	            String pName = e.getKey();
	            if(!rsList.isEmpty())
	            {   
	                int jj=0;
	                float sum=0;
	                float [] avgRating = new float[rsList.size()];
	                
	                for(ReviewDetail reviewDetail : rsList)
	                {
	                        avgRating[jj]= Float.parseFloat(reviewDetail.getReviewRating());
	                        jj++;
	                }
	                
	                for(int j=0; j<jj; j++)
	                {
	                    sum+= avgRating[j];
	                }
	                float a1=sum/jj;
	                avgMap.put(pName,a1);
	            }   
	        }
	        int m=5;
	        Map<String, Float> sortAvg = sortByAvg(avgMap,false);
	        System.out.println("Liked prods avgMap " + avgMap.size());
	        String productID = null,productName = null,productPrice = null,prodImgUrl = null,productCategory = null,prodDesc = null,prodImg=null;
	        for(Map.Entry<String, Float> ee : sortAvg.entrySet())
	        {
	            if(m > 0)
	            {   
	                    String pName = ee.getKey();
	                    AllProducts AllProducts= MySQLDataStoreUtilities.getproduct(Integer.parseInt(pName));
	                    
	                    
	                    if(AllProducts.getSmartDoorbell() != null) {
	                        SmartDoorbell smartDoorbell= AllProducts.getSmartDoorbell();
	                        productID = smartDoorbell.getId();
	                        productName = smartDoorbell.getName();
	                        productPrice = String.valueOf(smartDoorbell.getPrice());
	                        prodImgUrl = "smartDoorbell/" + smartDoorbell.getImage();
	                        productCategory = "smartDoorbell";
	                        prodDesc = smartDoorbell.getDescription();
	                        prodImg = smartDoorbell.getImage();
	                        
	                    }
	                    else if(AllProducts.getSmartDoorLock() != null) {
	                        SmartDoorLock smartDoorLock= AllProducts.getSmartDoorLock();
	                        productID = smartDoorLock.getId();
	                        productName = smartDoorLock.getName();  
	                        productPrice = String.valueOf(smartDoorLock.getPrice());
	                        prodImgUrl = "smartDoorLock/" + smartDoorLock.getImage();
	                        productCategory = "smartDoorLock";
	                        prodDesc = smartDoorLock.getDescription();
	                        prodImg = smartDoorLock.getImage();
	                    } 
	                    else if(AllProducts.getSmartLighting() != null) {
	                        SmartLighting smartLighting= AllProducts.getSmartLighting();
	                        productID = smartLighting.getId();
	                        productName = smartLighting.getName();
	                        productPrice = String.valueOf(smartLighting.getPrice());
	                        prodImgUrl = "smartLighting/" + smartLighting.getImage();
	                        productCategory = "smartLighting";
	                        prodDesc = smartLighting.getDescription();
	                        prodImg = smartLighting.getImage();
	                    } 
                        else if(AllProducts.getSpeaker() != null) {
	                        Speaker speaker= AllProducts.getSpeaker();
	                        productID = speaker.getId();
	                        productName = speaker.getName();
	                        productPrice = String.valueOf(speaker.getPrice());
	                        prodImgUrl = "speaker/" + speaker.getImage();
	                        productCategory = "speaker";
	                        prodDesc = speaker.getDescription();
	                        prodImg = speaker.getImage();
	                    } 
	                    else if(AllProducts.getSmartThermostat() != null) {
	                        SmartThermostat smartThermostat= AllProducts.getSmartThermostat();
	                        productID = smartThermostat.getId();
	                        productName = smartThermostat.getName();
	                        productPrice = String.valueOf(smartThermostat.getPrice());
	                        prodImgUrl = "smartThermostat/" + smartThermostat.getImage();
	                        productCategory = "smartThermostat";
	                        prodDesc = smartThermostat.getDescription();
	                        prodImg = smartThermostat.getImage();
	                        
	                    } 
	                    
	                    if(i%3==1 && i==1) {
							pw.print("<tr>");
						}
	                    pw.print("<td><div id='shop_item'>");
	                    pw.print("<h3>"+productName+"</h3>");
	                    pw.print("<strong>$"+productPrice+"</strong><ul>");
	                    pw.print("<li id='item'><img src='images/"+prodImgUrl +"' alt='' /></li>");
	                    pw.print("<li><form method='post' action='Cart'>" +
	                            "<input type='hidden' name='name' value='"+ productName+"'>"+
	                            "<input type='hidden' name='id' value='"+productID+"'>"+
	                            "<input type='hidden' name='type' value='"+productCategory+"'>"+
	                            "<input type='hidden' name='price' value='"+productPrice+"'>"+
	                            "<input type='hidden' name='img' value='"+prodImg+"'>"+
	                            "<input type='hidden' name='desc' value='"+prodDesc+"'>"+
	                            "<input type='hidden' name='maker' value='"+productCategory+"'>"+
	                            "<input type='hidden' name='access' value=''>"+
	                            "<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
	                    pw.print("<li><form method='post' action='ProductDetailsPage'>" +
	                            "<input type='hidden' name='name' value='"+ productName+"'>"+
	                            "<input type='hidden' name='id' value='"+productID+"'>"+
	                            "<input type='hidden' name='type' value='"+productCategory+"'>"+
	                            "<input type='hidden' name='price' value='"+productPrice+"'>"+
	                            "<input type='hidden' name='img' value='"+prodImg+"'>"+
	                            "<input type='hidden' name='desc' value='"+prodDesc+"'>"+
	                            "<input type='hidden' name='maker' value='"+productCategory+"'>"+
	                            "<input type='hidden' name='access' value=''>"+
	                            "<input type='submit' class='btnbuy' value='View Product'></form></li>");
	                    pw.print("</ul></div></td>");
	                    
						// if(i%3==0) pw.print("</tr>");
						// i++;
	                    if(i%3==0) pw.print("</tr>"); //|| i == sortAvg.size()
	                    i++; 
	            }
	            m--;
	        }
	        pw.print("</table></div></div></div>"); 
	        ;
	        }catch(Exception e) {
	            e.printStackTrace();
	        }

		utility.printHtml("Footer.html");
	}
	
	private static Map sortByAvg(Map<String, Float> temp,final boolean o)
    {

        LinkedList<Entry<String, Float>> ll = new LinkedList<Entry<String, Float>>(temp.entrySet());

        Collections.sort(ll, new Comparator<Entry<String, Float>>()
        {
            public int compare(Entry<String, Float> o1,Entry<String, Float> o2)
            {
                if (o)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        Map<String, Float> mainMap = new LinkedHashMap<String, Float>();
        for (Entry<String, Float> eee : ll)
        {
            mainMap.put(eee.getKey(), eee.getValue());
        }

        return mainMap;
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
	

}
