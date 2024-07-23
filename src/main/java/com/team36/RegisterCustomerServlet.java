package com.team36;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String aadharno = request.getParameter("aadharno");
        String phoneno = request.getParameter("phoneno");
        String address = request.getParameter("address");
        String balanceStr = request.getParameter("balance");
        double balance = Double.parseDouble(balanceStr);

        // Generate unique account number
        String accountno = generateAccountNumber();

        // Generate temporary password
        String tempPassword = generateTempPassword();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "19381@Reddy");

            PreparedStatement pst = conn.prepareStatement("INSERT INTO customer1 (accountno, firstname, lastname, email, aadharno, phoneno, address, password, balance, temp_colum) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, accountno);
            pst.setString(2, firstname);
            pst.setString(3, lastname);
            pst.setString(4, email);
            pst.setString(5, aadharno);
            pst.setString(6, phoneno);
            pst.setString(7, address);
            pst.setString(8, tempPassword);
            pst.setDouble(9, balance);
            pst.setInt(10, 1); // Set temp_colum to 1
            int rowCount = pst.executeUpdate();

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registration Status</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f8d7da; color: #721c24; margin: 20px; }");
            out.println("h1 { color: #721c24; }");
            out.println("p { font-size: 16px; }");
            out.println("form { margin-top: 20px; }");
            out.println("input[type='submit'] { background-color: #f5c6cb; border: none; color: #721c24; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer; border-radius: 5px; }");
            out.println("input[type='submit']:hover { background-color: #f1b0b7; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            if (rowCount > 0) {
                out.println("<h1>Customer Registered Successfully</h1>");
                out.println("<p>Account Number: " + accountno + "</p>");
                out.println("<p>Temporary Password: " + tempPassword + "</p>");
            } else {
                out.println("<h1>Failed to Register Customer</h1>");
            }
            
            out.println("<form action='adminDashboard.jsp'>");
            out.println("<input type='submit' value='Back to Admin Dashboard'>");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateAccountNumber() {
        Random rand = new Random();
        int num = 1000000 + rand.nextInt(9000000);
        return String.valueOf(num);
    }

    private String generateTempPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
