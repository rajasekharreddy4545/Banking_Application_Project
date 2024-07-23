package com.team36;

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

@WebServlet("/BalanceCustomerServlet")
public class BalanceCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountno = request.getParameter("accountno");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Write the HTML with inline CSS
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Customer Balance</title>");
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
        out.println("    color: #666;");
        out.println("    margin: 10px 0;");
        out.println("}");
        out.println(".back-button {");
        out.println("    background-color: #dc3545; /* Light red for the button */");
        out.println("    color: #fff;");
        out.println("    border: none;");
        out.println("    padding: 10px;");
        out.println("    border-radius: 4px;");
        out.println("    cursor: pointer;");
        out.println("    font-size: 16px;");
        out.println("    text-decoration: none;");
        out.println("    display: inline-block;");
        out.println("    margin-top: 20px;");
        out.println("}");
        out.println(".back-button:hover {");
        out.println("    background-color: #c82333; /* Darker red on hover */");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "19381@Reddy");

            PreparedStatement pst = conn.prepareStatement("SELECT accountno, firstname, lastname, email, aadharno, phoneno, address, balance FROM customer1 WHERE accountno = ?");
            pst.setString(1, accountno);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                out.println("<h1>Customer Balance</h1>");
                out.println("<p>Account Number: " + rs.getString("accountno") + "</p>");
                out.println("<p>Balance: " + rs.getDouble("balance") + "</p>");
            } else {
                out.println("<h1>Customer Not Found</h1>");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("<a href='adminDashboard.jsp' class='back-button'>Back to Admin Dashboard</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
