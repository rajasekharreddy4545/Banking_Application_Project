package com.team36;

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

@WebServlet("/CloseCustomerServlet")
public class CloseCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountno = request.getParameter("accountno");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "19381@Reddy");

            PreparedStatement pst = conn.prepareStatement("DELETE FROM customer1 WHERE accountno = ?");
            pst.setString(1, accountno);
            int rowCount = pst.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Close Customer Account</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    font-family: Arial, sans-serif;");
            out.println("    background-color: #f8d7da; /* Light red background */");
            out.println("    margin: 0;");
            out.println("    padding: 0;");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    height: 100vh;");
            out.println("}");
            out.println(".container {");
            out.println("    background-color: #fff;");
            out.println("    padding: 20px;");
            out.println("    border-radius: 8px;");
            out.println("    box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);");
            out.println("    width: 400px;");
            out.println("    text-align: center;");
            out.println("}");
            out.println("h1 {");
            out.println("    color: #dc3545; /* Light red for the heading */");
            out.println("    margin-bottom: 20px;");
            out.println("}");
            out.println("p {");
            out.println("    margin-bottom: 20px;");
            out.println("    color: #666;");
            out.println("}");
            out.println("form input[type='submit'] {");
            out.println("    background-color: #dc3545; /* Light red for the button */");
            out.println("    color: #fff;");
            out.println("    border: none;");
            out.println("    padding: 10px;");
            out.println("    border-radius: 4px;");
            out.println("    cursor: pointer;");
            out.println("    font-size: 16px;");
            out.println("    width: 100%;");
            out.println("}");
            out.println("form input[type='submit']:hover {");
            out.println("    background-color: #c82333; /* Darker red on hover */");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");

            if (rowCount > 0) {
                out.println("<h1>Account Closed</h1>");
                out.println("<p>Customer account closed successfully.</p>");
            } else {
                out.println("<h1>Account Not Found</h1>");
                out.println("<p>Customer not found.</p>");
            }

            out.println("<form action='adminDashboard.jsp'>");
            out.println("<input type='submit' value='Back to Admin Dashboard'>");
            out.println("</form>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
