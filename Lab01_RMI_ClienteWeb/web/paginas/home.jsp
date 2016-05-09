<%-- 
    Document   : home
    Created on : 09-05-2016, 11:58:26
    Author     : aialiagam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lab01 - Home</title>
    </head>
    <body>
        <h1>P&aacute;gina de Inicio</h1>
        <br/>
        <span>Bienvenido(a): ${requestScope['user'].GetUserName()}</span>
    </body>
</html>
