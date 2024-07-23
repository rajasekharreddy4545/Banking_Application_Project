package com.team36;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TransactionsServlet")
public class TransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountno = request.getParameter("accountno");
        String download = request.getParameter("download");

        // Retrieve transactions
        List<String> transactions = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "19381@Reddy");
            pst = conn.prepareStatement("SELECT * FROM transactions WHERE accountno = ? ORDER BY timestamp DESC LIMIT 5");
            pst.setString(1, accountno);
            rs = pst.executeQuery();

            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("timestamp");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");

                String transactionInfo = "Timestamp: " + timestamp + ", Type: " + type + ", Amount: " + amount;
                transactions.add(transactionInfo);
            }

            if ("true".equals(download)) {
                // Download transactions as CSV
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"transactions.csv\"");
                PrintWriter out = response.getWriter();
                out.println("Timestamp,Type,Amount");

                for (String transaction : transactions) {
                    String[] parts = transaction.split(", ");
                    String timestamp = parts[0].split(": ")[1];
                    String type = parts[1].split(": ")[1];
                    String amount = parts[2].split(": ")[1];
                    out.println(timestamp + "," + type + "," + amount);
                }

                out.flush();
                out.close();
            } else {
                // Display transactions with a download button
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html><head><title>Transactions</title><style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #ffe6e6; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
                out.println(".container { background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.2); text-align: center; width: 80%; max-width: 600px; }");
                out.println("h1 { color: #b30000; margin-bottom: 20px; }");
                out.println("ul { list-style-type: none; padding: 0; }");
                out.println("li { background-color: #ffcccc; margin: 10px 0; padding: 10px; border: 1px solid #ff9999; border-radius: 4px; }");
                out.println("form { margin-top: 20px; }");
                out.println("</style></head><body><div class='container'>");
                out.println("<h1>Transactions</h1>");
                out.println("<ul>");
                for (String transaction : transactions) {
                    out.println("<li>" + transaction + "</li>");
                }
                out.println("</ul>");
                out.println("<form action='TransactionsServlet' method='get'>");
                out.println("<input type='hidden' name='accountno' value='" + accountno + "'>");
                out.println("<input type='hidden' name='download' value='true'>");
                out.println("<button type='submit'>Download Last 5 Transactions</button>");
                out.println("</form>");
                out.println("</div></body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}