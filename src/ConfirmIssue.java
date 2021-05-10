

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ConfirmIssue")
public class ConfirmIssue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:postgresql://localhost/librarymanagement";
	String user="postgres";
	String pas="fermions";   
   
    public ConfirmIssue() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,pas);
			
		
			PreparedStatement ps=con.prepareStatement("insert into issue_ledger(book_id,username) values(?,?)");
			ps.setInt(1, Integer.parseInt(request.getParameter("bookid")));
			ps.setString(2, request.getParameter("username"));
//			ps.setDate(2,java.sql.Date.valueOf(request.getParameter("date_issued")));
//			ps.setDate(3,java.sql.Date.valueOf(request.getParameter("due_date")));
			int i=ps.executeUpdate();
			if(i>0) {
				out.print("Book Issued");
				out.print(request.getParameter("date_issued"));
			}
		}catch(Exception e){out.print(e);}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
