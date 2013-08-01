<%-- 
    Document   : index
    Created on : Jul 31, 2013, 1:47:44 PM
    Author     : Aravind Sarma Yeluripati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>usermgmt - login page</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css"/>
        <script type="text/JavaScript">
            function validateLogin(form) {
                var returnValue = true;

                var userId = form.txtUserId.value;
                var password = form.txtPassword.value; 

                if(userId.length == 0) {
                    returnValue = false;
                    alert("You didn't enter a userId\nPlease try again.");
                    formLogin.txtUserId.focus();            
                }else if (password.length == 0) {
                    returnValue = false;
                    alert("You didn't enter a password \nPlease try again.");    
                    formLogin.txtPassword.focus();
                }
                return returnValue;
            }
        </script>
    </head>
    <body>
        <h1>User Management</h1>        
        <form name="formLogin" method="post" action="login" onsubmit="return validateLogin(this)">
            <fieldset>
                <legend>Login</legend>
                <label for="userId">User Id: <span class="required">*</span></label>
                <div class="input">
                    <input type="text" name="txtUserId" id="userId" size="16"/>				
                </div>
                <label for="pwd">Password: <span class="required">*</span></label>
                <div class="input">
                    <input type="password" name="txtPassword" id="pwd" size="16"/>				
                </div>			
                <div class="submit"><input type="submit" value="Enter"/></div>
                <span class="required">*</span> = required
            </fieldset>
        </form>
    </body>
</html>
