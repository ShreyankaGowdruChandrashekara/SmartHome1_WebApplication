import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li style='color:black;s'><a style='color:black;' href='ViewOrder'><span class='glyphicon'>Orders</span></a></li>"
						+ "<li style='color:black;'><a style='color:black;' href='Account'><span class='glyphicon glyphicon-user'>Hello,"+username+"</span></a></li>"
						+ "<li style='color:black;'><a style='color:black;' href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			}
			else
				result = result +"<li ><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li ><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li ><a href='Cart'><span class='glyphicon glyphicon-shopping-cart'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	
	/*
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment1SGC\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	*/

	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		User user =MySQLDataStoreUtilities.fetchUserDetails(username());
		return user;
	}
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	/*
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment1SGC\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=size + 1;
					 
			}
			return size;		
	}
*/
	
	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin()){
			return getCustomerOrders().size();
			
		}
		return 0;
	}
	

	public OrderItem CartRemove(int i){
		return getCustomerOrders().remove(i);
		
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String id,String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("smartDoorbell")){
			SmartDoorbell smartDoorbell;
			smartDoorbell = MySQLDataStoreUtilities.getproduct(Integer.parseInt(id)).getSmartDoorbell();
			//SaxParserDataStore.smartDoorbells.get(name);
			double price = smartDoorbell.getPrice() * (1 - smartDoorbell.getDiscount()/100);
			BigDecimal bd = BigDecimal.valueOf(price);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			OrderItem orderitem = new OrderItem(smartDoorbell.getName(), bd.doubleValue(), smartDoorbell.getImage(), smartDoorbell.getRetailer(), smartDoorbell.getDescription(),type);
			orderItems.add(orderitem);
		}
		if(type.equals("smartThermostat")){
			SmartThermostat smartThermostat = null;
			smartThermostat = MySQLDataStoreUtilities.getproduct(Integer.parseInt(id)).getSmartThermostat();
			double price = smartThermostat.getPrice() * (1 - smartThermostat.getDiscount()/100);
			BigDecimal bd = BigDecimal.valueOf(price);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			OrderItem orderitem = new OrderItem(smartThermostat.getName(),bd.doubleValue(), smartThermostat.getImage(), smartThermostat.getRetailer(), smartThermostat.getDescription(),type);
			orderItems.add(orderitem);
		}
		if(type.equals("smartDoorLock")){
			SmartDoorLock smartDoorLock = null;
			smartDoorLock = MySQLDataStoreUtilities.getproduct(Integer.parseInt(id)).getSmartDoorLock();
			double price = smartDoorLock.getPrice() * (1 - smartDoorLock.getDiscount()/100);
			BigDecimal bd = BigDecimal.valueOf(price);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			OrderItem orderitem = new OrderItem(smartDoorLock.getName(),bd.doubleValue(), smartDoorLock.getImage(), smartDoorLock.getRetailer(), smartDoorLock.getDescription(),type);
			orderItems.add(orderitem);
		}
		if(type.equals("speaker")){
			Speaker speaker = null;
			speaker = MySQLDataStoreUtilities.getproduct(Integer.parseInt(id)).getSpeaker();
			double price = speaker.getPrice() * (1 - speaker.getDiscount()/100);
			BigDecimal bd = BigDecimal.valueOf(price);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			OrderItem orderitem = new OrderItem(speaker.getName(),bd.doubleValue(), speaker.getImage(), speaker.getRetailer(),speaker.getDescription(),type);
			orderItems.add(orderitem);
		}
		if(type.equals("smartLighting")){
			SmartLighting smartLighting = null;
			smartLighting = MySQLDataStoreUtilities.getproduct(Integer.parseInt(id)).getSmartLighting();
			double price = smartLighting.getPrice() * (1 - smartLighting.getDiscount()/100);
			BigDecimal bd = BigDecimal.valueOf(price);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			OrderItem orderitem = new OrderItem(smartLighting.getName(), bd.doubleValue(), smartLighting.getImage(), smartLighting.getRetailer(), smartLighting.getDescription(),type);
			orderItems.add(orderitem);
		}
		if(type.equals("accessories")){	
			Accessory accessory = SaxParserDataStore.accessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer(), accessory.getDescription(),type);
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String firstname,String creditCardNo){
			MySQLDataStoreUtilities.addOrderDetails(orderId,orderName,orderPrice,username(), Long.parseLong(creditCardNo), firstname);
	}
		/*
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment1SGC\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),firstname,orderName,orderPrice,creditCardNo);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try
			{	
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Assignment1SGC\\PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}
	*/

	
	/* getTvs Functions returns the Hashmap with all tvs in the store.*/

	public HashMap<String, SmartDoorbell> getSmartDoorbell(){
			HashMap<String, SmartDoorbell> hm = new HashMap<String, SmartDoorbell>();
			hm.putAll(SaxParserDataStore.smartDoorbells);
			return hm;
	}
	
	/* getLaptops Functions returns the Hashmap with all Laptop in the store.*/

	public HashMap<String, SmartLighting> getSmartLighting(){
			HashMap<String, SmartLighting> hm = new HashMap<String, SmartLighting>();
			hm.putAll(SaxParserDataStore.smartLightings);
			return hm;
	}

		/* getLaptops Functions returns the Hashmap with all Tablets in the store.*/

		public HashMap<String, SmartThermostat> getSmartThermostat(){
			HashMap<String, SmartThermostat> hm = new HashMap<String, SmartThermostat>();
			hm.putAll(SaxParserDataStore.smartThermostats);
			return hm;
	}

	public HashMap<String, Speaker> getSpeaker(){
			HashMap<String, Speaker> hm = new HashMap<String, Speaker>();
			hm.putAll(SaxParserDataStore.speakers);
			return hm;
	}

	public HashMap<String, SmartDoorLock> getSmartDoorLock(){
			HashMap<String, SmartDoorLock> hm = new HashMap<String, SmartDoorLock>();
			hm.putAll(SaxParserDataStore.smartDoorLocks);
			return hm;
	}
	
	/* getProducts Functions returns the Arraylist of tvs in the store.*/
/*
	public ArrayList<String> getProducts(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SmartDoorbell> entry : getsmartDoorbells().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of tablets in the store.*/
/*
	public ArrayList<String> getProductsSmartThermostat(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SmartThermostat> entry : getSmartThermostat().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of SmartDoorbell in the store.*/
/*
	public ArrayList<String> getProductsSmartDoorLocks(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SmartDoorLock> entry : getSmartDoorLocks().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	/* getProducts Functions returns the Arraylist of Laptops in the store.*/
/*
	public ArrayList<String> getProductsSmartLightings(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SmartLighting> entry : getSmartLightings().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsSpeakers(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Speaker> entry : getSpeaker().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
*/
}
