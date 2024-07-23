<!DOCTYPE html>
<html>
<head>
    <title>Register Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da; /* Light red background */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 600px;
            text-align: center;
        }
        h1 {
            color: #dc3545; /* Light red for the heading */
            margin-bottom: 20px;
        }
        label {
            margin-bottom: 5px;
            color: #666;
            display: block;
        }
        input[type="text"],
        input[type="email"],
        input[type="number"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 15px;
            width: 100%;
        }
        input[type="submit"] {
            background-color: #dc3545; /* Light red for the button */
            color: #fff;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #c82333; /* Darker red on hover */
        }
        a.back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #dc3545;
            font-size: 16px;
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #dc3545;
        }
        a.back-link:hover {
            background-color: #dc3545;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Register New Customer</h1>
        <form action="RegisterCustomerServlet" method="post">
            <label for="firstname">First Name:</label>
            <input type="text" id="firstname" name="firstname" required><br>
            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname" required><br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>
            <label for="aadharno">Aadhar Number:</label>
            <input type="text" id="aadharno" name="aadharno" required><br>
            <label for="phoneno">Phone Number:</label>
            <input type="text" id="phoneno" name="phoneno" required><br>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required><br>
            <label for="balance">Initial Balance:</label>
            <input type="number" step="0.01" id="balance" name="balance" required><br>
            <input type="submit" value="Register Customer">
        </form>
        <a href="adminDashboard.jsp" class="back-link">Back to Admin Dashboard</a>
    </div>
</body>
</html>
