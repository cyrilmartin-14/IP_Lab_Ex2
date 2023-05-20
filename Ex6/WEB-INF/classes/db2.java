import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class db2 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String cid = request.getParameter("cid");
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/character", "root", "");

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM stats where cid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cid);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String gender = rs.getString("gender");
                String skill = rs.getString("skill");
                String weapon = rs.getString("weapon");
                out.println("<p> <b>Character ID :</b> " + cid + "<br>");
                out.println("<b>Gender : </b>" + gender + "<br><hr style='border: 1px solid'><br>");
                out.println("<b>Skill : </b>" + skill + "<br>");
                out.println("<b>Weapon : </b>" + weapon + "<br>");
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
