<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da; /* Light red background */
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 300px;
            text-align: center;
        }
        h1 {
            color: #dc3545; /* Light red for the heading */
            margin-bottom: 20px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
        }
        a {
            color: #dc3545; /* Light red for the links */
            text-decoration: none;
            font-size: 18px;
            display: block;
        }
        a:hover {
            text-decoration: underline;
        }
        .logout-button {
            position: fixed;
            top: 20px; /* Adjust as needed */
            right: 20px; /* Adjust as needed */
        }
        .logout-button input[type="submit"] {
            background-color: #dc3545; /* Light red for the button */
            color: #fff;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .logout-button input[type="submit"]:hover {
            background-color: #c82333; /* Darker red on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Manager Dashboard</h1>
        <ul>
            <li><a href="registerCustomer.jsp">Register a Customer</a></li>
            <li><a href="balanceCustomer.jsp">Customer Balance</a></li>
            <li><a href="viewCustomer.jsp">View Customer Details</a></li>
            <li><a href="closeCustomer.jsp">Close Customer Account</a></li>
            <li><a href="editCustomer.jsp">Edit Customer Details</a></li>
        </ul>
    </div>
    <div class="logout-button">
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>
</body>
</html>
