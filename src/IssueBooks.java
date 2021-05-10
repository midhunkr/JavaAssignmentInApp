

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/IssueBooks")
public class IssueBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:postgresql://localhost/librarymanagement";
	String user="postgres";
	String pas="fermions";
       
    
    public IssueBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		try {
			
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,pas);
			PreparedStatement ps=con.prepareStatement("select  * from book inner join request_book on book.book_id=request_book.book_id");
			
			
	
			ResultSet rs=ps.executeQuery();
			out.print("<html><body bgcolor=\"#c9a5c6\"><h1>Pending Book Requests</h1><table border=\"1.0\">");
			out.print("<tr><td>BookId</td><td>Book Name</td><td>Author Name</td>");
			out.print("<td>Category</td><td>Published Year</td><td>Status</td><td>UserName</td><td>Action</td></tr>");
			while(rs.next()) {
				int id=rs.getInt(1);
				String uid=Integer.toString(id);
				String username=rs.getString(8);
				out.print("<form action=book_issue_date.jsp>");
				out.print("<tr>");
				out.print("<td>"+uid+"</td>");
				out.print("<td>"+username+"</td>");
				out.print("<td>"+rs.getString(3)+"</td>");
				out.print("<td>"+rs.getString(4)+"</td>");
				out.print("<td>"+rs.getString(5)+"</td>");
				out.print("<td>"+rs.getString(6)+"</td>");
				out.print("<td>"+rs.getString(8)+"</td>");
				out.print("<td><input type=submit value=IssueBook></td>");
				out.print("<input type=hidden value="+uid+" name=bookid>");
				out.print("<input type=hidden value="+username+" name=username>");
				out.print("</tr>");
				out.print("</form>");
			}
			out.print("</table></body></html>");
			con.close();
		}catch(Exception e){out.print(e);}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
