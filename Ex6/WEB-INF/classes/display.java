import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class display extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        Statement stmt = null;
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesdb", "root", "");
            if (conn != null) {
                out.println("<h1> Connection established successfully </h1>");
            }
            stmt = conn.createStatement();

            // Update the contact information of the customer with the given name
            String sql = "Select * from shows";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Retrieve by column name
                String Title = rs.getString("Title");
                String year = rs.getString("year");
                String co = rs.getString("co");
                String comment = rs.getString("comment");
                // Display values
                out.println("<p> Title: " + Title + "<br>");
                out.println("Year: " + year + "<br>");
                out.println("Country of Origin: " + co + "<br>");
                out.println("Comment: " + comment + " %<br></p>");
            }
            out.println("</body></html>");
            rs.close();
            // Clean-up environment
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Error connecting to DB - Error:" + e);
        }
    }
}
