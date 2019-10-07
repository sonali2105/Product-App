import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class SearchProduct extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) {
		PrintWriter out=null;
		try{
			res.setContentType("text/html");
			out=res.getWriter();
			out.println("<html> <body>");
			out.println("<h1>Product App</h1>");
			String n=req.getParameter("Name");
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","incapp");
			Statement  st=c.createStatement();
			ResultSet rs=st.executeQuery("select * from product_info where name like '%"+n+"%'");
			int flag=0;
			while(rs.next()) {
				flag=1;
				out.println("<br> <br> Product ID:"+rs.getInt("Pid"));
				out.println("<br> Name: "+rs.getString("Name"));
				out.println("<br> Price: "+rs.getInt("Price"));
				out.println("<br> About Product: '"+rs.getString("Info"));
			}
			if(flag==0){
				out.println("No Data Found !");
			}
			out.println("</body></html> ");
			out.close();
		}catch(Exception ex) {
			out.println(ex);
			out.close();
		}
	}
	public String ServletInfo() {
		return "This is my Product App";
	}
}