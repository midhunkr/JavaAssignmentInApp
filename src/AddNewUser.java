

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddNewUser")
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:postgresql://localhost/librarymanagement";
	String user="postgres";
	String pas="fermions";
       
    
    public AddNewUser() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,pas);
			PreparedStatement ps=con.prepareStatement("insert into user_data values(?,?,?,?)");
			ps.setString(1, request.getParameter("username"));
			ps.setString(2, request.getParameter("password"));
			ps.setInt(3, Integer.parseInt(request.getParameter("id")));
			ps.setInt(4, 0);
			
			int i=ps.executeUpdate();
			if(i>0) {
				out.print("Entered value");
			}
		}
		catch(Exception e){out.print(e);}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
