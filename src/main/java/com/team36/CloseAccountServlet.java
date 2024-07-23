package com.team36;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CloseAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountno = request.getParameter("accountno");

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "19381@Reddy");

            // Delete account from the database
            pst = conn.prepareStatement("DELETE from customer1 WHERE accountno = ?");
            pst.setString(1, accountno);
            int rowCount = pst.executeUpdate();
            
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<html><head><title>Close Account</title><style>");
            response.getWriter().println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
            response.getWriter().println(".container { background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.2); text-align: center; }");
            response.getWriter().println("h1 { color: #333; }");
            response.getWriter().println(".message { padding: 10px; border-radius: 4px; margin-bottom: 15px; }");
            response.getWriter().println(".success { background-color: #d4edda; color: #155724; }");
            response.getWriter().println(".error { background-color: #f8d7da; color: #721c24; }");
            response.getWriter().println("</style></head><body><div class='container'>");

            if (rowCount > 0) {
                response.getWriter().println("<h1>Close Account</h1>");
                response.getWriter().println("<div class='message success'>Account closed successfully.</div>");
            } else {
                response.getWriter().println("<h1>Close Account</h1>");
                response.getWriter().println("<div class='message error'>Failed to close account. Please try again.</div>");
            }

            response.getWriter().println("</div></body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
