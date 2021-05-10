

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


@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url="jdbc:postgresql://localhost/librarymanagement";
	String user="postgres";
	String pas="fermions";
   
    public SearchBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		int parameterintValue;
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url,user,pas);
			String paramType=request.getParameter("parameter");
			String paramValue=request.getParameter("parametervalue");
			String userName=request.getParameter("user_name");
			PreparedStatement ps;
			if(paramType.equals("BookName")) {
				ps=con.prepareStatement("select * from book where book_name=?");	
				ps.setString(1,paramValue);
			}else if(paramType.equals("AuthorName")){
				 ps=con.prepareStatement("select * from book where author_name=?");
				 ps.setString(1,paramValue);
			}else{
				 ps=con.prepareStatement("select * from book where book_id=?");
				 ps.setInt(1,Integer.parseInt(paramValue));
			}
		
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				out.print("<html><body bgcolor=#86a2cf>");
				out.print("<h2>Book found.<h2>");
				out.print("<table border=1>");
				out.print("<tr>");
				out.print("<th>Book Id</th>");
				out.print("<th> Book Name</th>");
				out.print("<th>Author Name</th>");
				out.print("<th> Category</th>");
				out.print("<th>Year</th>");
				out.print("<th>status</th>");
				parameterintValue=Integer.parseInt(rs.getString(7));
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td>"+rs.getString(7)+"</td>");
				out.print("<td>"+rs.getString(2)+"</td>");
				out.print("<td>"+rs.getString(3)+"</td>");
				out.print("<td>"+rs.getString(4)+"</td>");
				out.print("<td>"+rs.getString(5)+"</td>");
				out.print("<td>"+rs.getString(6)+"</td>");
				
				out.print("</tr></table>");
				if(rs.getString(6).equals("available")) {
					out.print("<form action=RequestBookServlet method=get>");
					out.print("<input type=hidden value="+parameterintValue+" name=param>");
					out.print("<input type=hidden value="+userName+" name=username>");
					out.print("<input type=submit value=Request Book></form>");
				}
				out.print("<form action=login.html><input type=submit value=submit></form>");
				
				out.print("</html></body>");
			}else {
				out.print("Book not found!!");
			}
		}catch(Exception e){out.print(e);}
	    
	}

}
