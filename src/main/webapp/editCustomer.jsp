<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer Details</title>
</head>
<body>
    <h1>Edit Customer Details</h1>
    <form action="EditCustomerServlet" method="post">
        <label for="accountno">Account Number:</label>
        <input type="text" id="accountno" name="accountno" required><br><br>
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" required><br><br>
        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" name="lastname" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="aadharno">Aadhar Number:</label>
        <input type="text" id="aadharno" name="aadharno" required><br><br>
        <label for="phoneno">Phone Number:</label>
        <input type="text" id="phoneno" name="phoneno" required><br><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br><br>
        <label for="updatePassword">Generate New Password?</label>
        <input type="checkbox" id="updatePassword" name="updatePassword" value="yes"><br><br>
        <input type="submit" value="Edit Customer">
    </form>
</body>
</html>
