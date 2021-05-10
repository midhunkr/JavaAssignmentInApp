

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AddNewBook")
public class AddNewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:postgresql://localhost/librarymanagement";
	String user="postgres";
	String pas="fermions";
    
    public AddNewBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,pas);
			PreparedStatement ps=con.prepareStatement("insert into book values(?,?,?,?,?,?) ");
			ps.setInt(1, 4656);
			ps.setString(2,request.getParameter("bookname"));
			ps.setString(3, request.getParameter("authorname"));
			ps.setString(4, request.getParameter("category"));
			ps.setInt(5, 2018);
			ps.setString(6, "available");
			int rs=ps.executeUpdate();
			if(rs>0) {
				
				RequestDispatcher rd=request.getRequestDispatcher("addnewbook.html");
				rd.forward(request, response);
				
			}
		}catch(Exception e){out.print(e);}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
