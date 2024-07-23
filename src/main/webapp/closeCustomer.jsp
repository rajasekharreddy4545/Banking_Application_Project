<!DOCTYPE html>
<html>
<head>
    <title>Close Customer Account</title>
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
            width: 400px;
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
        input[type="text"] {
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Close Customer Account</h1>
        <form action="CloseCustomerServlet" method="post">
            <label for="accountno">Account Number:</label>
            <input type="text" id="accountno" name="accountno" required><br>
            <input type="submit" value="Close Account">
        </form>
    </div>
</body>
</html>
