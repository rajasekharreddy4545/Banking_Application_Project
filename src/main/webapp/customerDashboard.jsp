<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5; /* Light grey background color */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: rgba(255, 204, 204, 0.9); /* Light red background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 500px;
            text-align: left;
        }
        h1, h2 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
        }
        a, input[type="submit"] {
            background-color: #e57373; /* Light red button */
            color: #fff;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
        }
        a:hover, input[type="submit"]:hover {
            background-color: #c62828; /* Darker red on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Customer Dashboard</h1>
        <%
            String accountno = request.getParameter("accountno");
            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "root", "12345678");

                pst = conn.prepareStatement("SELECT * FROM customer WHERE accountno = ?");
                pst.setString(1, accountno);
                rs = pst.executeQuery();

                if (rs.next()) {
                    out.println("Account Number: " + rs.getString("accountno") + "<br>");
                    out.println("First Name: " + rs.getString("firstname") + "<br>");
                    out.println("Last Name: " + rs.getString("lastname") + "<br>");
                    out.println("Email: " + rs.getString("email") + "<br>");
                    out.println("Aadhar Number: " + rs.getString("aadharno") + "<br>");
                    out.println("Phone Number: " + rs.getString("phoneno") + "<br>");
                    out.println("Address: " + rs.getString("address") + "<br>");
                    out.println("Balance: " + rs.getDouble("balance") + "<br>");
                } else {
                    out.println("Customer not found.");
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
        %>
        <h2>Actions:</h2>
        <ul>
            <li><a href='deposit.jsp?accountno=<%= accountno %>'>Deposit</a></li>
            <li><a href='withdraw.jsp?accountno=<%= accountno %>'>Withdraw</a></li>
           
            <li>
                <form action="CloseAccountServlet" method="post">
                    <input type="hidden" name="accountno" value="<%= accountno %>">
                    <input type="submit" value="Close Account">
                </form>
            </li>
            <li><a href='TransactionsServlet?accountno=<%= accountno %>'>View Transactions</a></li>
            <li><a href='CustomerLogout.jsp?accountno=<%= accountno %>'>Logout</a></li>
        </ul>
    </div>
</body>
</html>
