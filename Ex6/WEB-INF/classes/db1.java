import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class db1 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        Statement stmt = null;
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/character", "root", "");
            if (conn != null) {
                out.println("<h1> Character Information</h1>");
            }
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM characters";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String cid = rs.getString("cid");
                String Name = rs.getString("Name");
                String Movie_name = rs.getString("Movie_name");
                Integer Imdb_rating = rs.getInt("Imdb_rating");
                Integer No_of_parts = rs.getInt("No_of_parts");
                out.println("<hr><p> <b>Character ID :</b> " + cid + "<br>");
                out.println("<b>Name : </b>" + Name + "<br>");
                out.println("<b>Anime Name : </b>" + Movie_name + "<br>");
                out.println("<b>Imdb Rating : </b>" + Imdb_rating + "<br>");
                out.println("<b>Total Episodes : </b>" + No_of_parts + "<br></p>");
            }
            out.println("</body></html>");
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Do not connect to DB - Error:" + e);
        }
    }
}
