import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/*
		 * User Information(username,password,usertype) is obtained from
		 * HttpServletRequest, Based on the Type of user(customer,retailer,manager)
		 * respective hashmap is called and the username and password are validated and
		 * added to session variable and display Login Function is called
		 */

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		if(MySQLDataStoreUtilities.checkUserCredentails(username, password, usertype)) {
			System.out.println("Login.java line 31");
			System.out.println("username : " + username + " password : "+ password + " usertype :"+ usertype);
			User user = MySQLDataStoreUtilities.fetchUserDteails(username, password, usertype);
			System.out.println("username : " + username + " password : "+ user.getPassword() + " usertype :"+ user.getUsertype());
			if (user.getUsertype().equals(usertype) && user.getPassword().equals(password)) {
				System.out.println("Login.java line 36");
				if (usertype.equals("storeManager")) {
					HttpSession session = request.getSession(true);
					session.setAttribute("username", user.getName());
					session.setAttribute("usertype", user.getUsertype());
					session.setAttribute("userID", user.getId());
					response.sendRedirect("HomeSM");
					return;
				}

				if (usertype.equals("salesManager")) {
					HttpSession session1 = request.getSession(true);
					session1.setAttribute("username", user.getName());
					session1.setAttribute("usertype", user.getUsertype());
					session1.setAttribute("userID", user.getId());
					response.sendRedirect("HomeSAM");
					return;
				}

				if (usertype.equals("customer")) {
					System.out.println("Login.java line 51");
					HttpSession session2 = request.getSession(true);
					session2.setAttribute("username", user.getName());
					session2.setAttribute("usertype", user.getUsertype());
					session2.setAttribute("userID", user.getId());
					response.sendRedirect("Home");
					return;
				}
			}
			displayLogin(request, response, pw, true);
		}
		// test
//		User user = hm.get(username);
//		System.out.println(user.getUsertype());
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}

	/*
	 * Login Screen is Displayed, Registered User specifies credentials and logins
	 * into the Game Speed Application.
	 */
	protected void displayLogin(HttpServletRequest request, HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div class='post' style='float: none; width: 100%' ;background-color: rgb(211, 158, 98)>");
		pw.print("<h2 class='title meta' style='text-align:center' ><a style='font-size: 24px;color:red'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if (error)
			pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
		HttpSession session = request.getSession(true);
		if (session.getAttribute("login_msg") != null) {
			pw.print("<h4 style='color:red'>" + session.getAttribute("login_msg") + "</h4>");
			session.removeAttribute("login_msg");
		}
		pw.print("<form method='post' action='Login'>" + "<table style='width:100%' ;background-color: rgb(211, 158, 98)><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer'>Customer</option><option value='storeManager'>Store Manager</option><option value='salesManager'>Salesman</option></select>"
				+ "</td></tr><tr><td></td><td>"
				+ "<input type='submit' class='btnbuy' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr><tr><td></td><td>"
				+ "<strong><a class='' href='Registration' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"
				+ "</td></tr></table>" + "</form>" + "</div></div></div>");
		utility.printHtml("Footer.html");
	}

}
