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

@WebServlet("/ViewCustomerServlet")
public class ViewCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountno = request.getParameter("accountno");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "19381@Reddy");

            PreparedStatement pst = conn.prepareStatement("SELECT accountno, firstname, lastname, email, aadharno, phoneno, address, balance FROM customer1 WHERE accountno = ?");
            pst.setString(1, accountno);
            ResultSet rs = pst.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Customer Details</title>");
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
            out.println("    width: 500px;");
            out.println("    text-align: center;");
            out.println("}");
            out.println("h1 {");
            out.println("    color: #dc3545; /* Light red for the heading */");
            out.println("    margin-bottom: 20px;");
            out.println("}");
            out.println("p {");
            out.println("    color: #333;");
            out.println("    margin-bottom: 10px;");
            out.println("}");
            out.println(".button {");
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
            out.println(".button:hover {");
            out.println("    background-color: #c82333; /* Darker red on hover */");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Customer Details</h1>");

            if (rs.next()) {
                out.println("<p><strong>Account Number:</strong> " + rs.getString("accountno") + "</p>");
                out.println("<p><strong>First Name:</strong> " + rs.getString("firstname") + "</p>");
                out.println("<p><strong>Last Name:</strong> " + rs.getString("lastname") + "</p>");
                out.println("<p><strong>Email:</strong> " + rs.getString("email") + "</p>");
                out.println("<p><strong>Aadhar Number:</strong> " + rs.getString("aadharno") + "</p>");
                out.println("<p><strong>Phone Number:</strong> " + rs.getString("phoneno") + "</p>");
                out.println("<p><strong>Address:</strong> " + rs.getString("address") + "</p>");
                out.println("<p><strong>Balance:</strong> " + rs.getDouble("balance") + "</p>");
            } else {
                out.println("<p>Customer not found.</p>");
            }

            out.println("<a href='adminDashboard.jsp' class='button'>Back to Admin Dashboard</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
