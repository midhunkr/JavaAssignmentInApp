

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserAuth")
public class UserAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:postgresql://localhost/librarymanagement";
	String user="postgres";
	String pas="fermions";
       
    
    public UserAuth() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		String userId;
		PrintWriter out=response.getWriter();
		out.print(userName);
		try {
			
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,pas);
			PreparedStatement ps=con.prepareStatement("SELECT * FROM user_data where username=? and password=?");
			ps.setString(1,userName);
			ps.setString(2, password);
			
	
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("userhome.jsp");
				rd.forward(request, response);
			}else {
				out.print("Wrong credentials");
			}
			con.close();
		}catch(Exception e){out.print(e);}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
