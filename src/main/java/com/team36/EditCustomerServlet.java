package com.team36;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditCustomerServlet")
public class EditCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountno  = Integer.parseInt(request.getParameter("accountno"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String aadharno = request.getParameter("aadharno");
        String phoneno = request.getParameter("phoneno");
        String address = request.getParameter("address");
        String updatePassword = request.getParameter("updatePassword");

        String newTempPassword = null;
        int tempColumnValue = 0;

        if ("yes".equalsIgnoreCase(updatePassword)) {
            newTempPassword = generateTempPassword();
            tempColumnValue = 1;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "19381@Reddy");

            String sql = "UPDATE customer1 SET firstname = ?, lastname = ?, email = ?, aadharno = ?, phoneno = ?, address = ? WHERE accountno = ?";
            if (newTempPassword != null) {
                sql += ", password = ?, temp_colum = ?";
            }
            sql += " WHERE accountno = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, firstname);
            pst.setString(2, lastname);
            pst.setString(3, email);
            pst.setString(4, aadharno);
            pst.setString(5, phoneno); 
            pst.setString(6, address);
            int parameterIndex = 7;
            if (newTempPassword != null) {
                pst.setString(parameterIndex++, newTempPassword);
                pst.setInt(parameterIndex++, tempColumnValue);
            }
            pst.setInt(parameterIndex, accountno);

            int rowCount = pst.executeUpdate();

            PrintWriter out = response.getWriter();
            if (rowCount > 0) {
                out.println("Customer details updated successfully.");
                if (newTempPassword != null) {
                    out.println("New temporary password: " + newTempPassword);
                }
            } else {
                out.println("Customer not found.");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
