import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class NewProduct extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) {
		PrintWriter out=null;
		try{
			res.setContentType("text/html");
			out=res.getWriter();
			out.println("<html> <body>");
			out.println("<h1>Product App</h1>");
			String n=req.getParameter("Name");
			int p=Integer.parseInt(req.getParameter("Price"));
			String i=req.getParameter("info");
			Class.forName("com.mysql.jdbc.Driver");
			Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","incapp");
			Statement st=c.createStatement();
			st.executeUpdate(
			"insert into product_info (name,price,info) values('"+n+"',"+p+",'"+i+"')");
			out.println("<br> Product Added Successfully !");
			out.println("</body></html> ");
			out.close();
		}catch(Exception ex) {
			out.println("<br> Product Addition Failed ! <br>");
			out.println(ex);
			out.close();
		}
	}
}