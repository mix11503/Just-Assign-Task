<%-- 
    Document   : debug
    Created on : Mar 19, 2017, 2:36:40 PM
    Author     : Kittisak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${hwwww}</h1>
        <h1><%= request.getAttribute("hwwww")  %></h1>
    </body>
</html>
