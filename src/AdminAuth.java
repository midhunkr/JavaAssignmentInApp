
import java.sql.*;
import java.io.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminAuth
 */
@WebServlet("/AdminAuth")
public class AdminAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:postgresql://localhost/librarymanagement";
	String user="postgres";
	String pas="fermions";
   
    public AdminAuth() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,pas);
			PreparedStatement ps=con.prepareStatement("select * from admin");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals(userName)&&rs.getString(2).equals(password))
				{
//					out.print("<html><body bgcolor=#8b89a1>");
//					out.print("<h1>Hi, "+rs.getString(1)+"</h1>");
//					out.print("</body></html>");
					RequestDispatcher rd=request.getRequestDispatcher("adminhome.jsp");
					rd.forward(request, response);
				}else {
					out.print("Invalide UserName");
				}
				
				
			}
			con.close();
		}catch(Exception e){out.print(e);}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
