<%-- 
    Document   : home
    Created on : Aug 1, 2013, 10:27:56 AM
    Author     : Aravind Sarma Yeluripati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home page</title>
    </head>
    <body>        
        <h1>Welcome ${loggedInUser}</h1>
        <a href="/usermgmt/home">Home page</a><br/>
        <a href="/usermgmt/logout">Log Out</a>
    </body>
</html>
