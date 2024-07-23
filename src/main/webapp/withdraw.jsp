<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Withdraw Money</title>
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
            width: 300px;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        label {
            margin-bottom: 10px;
            color: #666;
        }
        input[type="number"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 15px;
            width: calc(100% - 24px);
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #e57373; /* Light red button */
            color: #fff;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #c62828; /* Darker red on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Withdraw Money</h1>
        <form action="WithdrawServlet" method="post">
            <input type="hidden" name="accountno" value="<%= request.getParameter("accountno") %>">
            <label for="amount">Amount to Withdraw:</label>
            <input type="number" id="amount" name="amount" step="0.01" required><br><br>
            <input type="submit" value="Withdraw">
        </form>
    </div>
</body>
</html>
