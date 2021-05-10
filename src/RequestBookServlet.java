

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


@WebServlet("/RequestBookServlet")
public class RequestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:postgresql://localhost/librarymanagement";
	String user="postgres";
	String pas="fermions";
   
    public RequestBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,pas);
			PreparedStatement ps=con.prepareStatement("insert into request_book values(?,?)");
			ps.setInt(1,Integer.parseInt(request.getParameter("param")) );
			ps.setString(2, request.getParameter("username"));
			int rs=ps.executeUpdate();
			if(rs>0) {
				out.print("<html><body bgcolor=#dbbe67><h1>The Book Has Been requested.It will be approved by the admin.Check the status.</h1></body></html>");
			}else {
				out.print("Failed to request book");
			}
		}catch(Exception e){out.print(e);}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
