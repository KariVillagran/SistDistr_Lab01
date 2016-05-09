<%-- 
    Document   : login
    Created on : 09-05-2016, 11:58:14
    Author     : aialiagam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de Sesión</title>
    </head>
    <body>
        <form id="formLogin" action="LoginController" method="POST">
        <h1>Cliente RMI Web</h1>
        <fieldset>
            <legend>Inicio de Sesión</legend>
            <table>
                <tr>
                    <td>
                        Usuario:
                    </td>
                    <td>
                        <input type="text" id="txtUsuario" name="username">
                    </td>
                </tr>
                <tr>
                    <td>
                        Contrase&ntilde;a:
                    </td>
                    <td>
                        <input type="password" id="txtPassword" name="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" id="btnIngresar" value="Ingresar">
                    </td>
                </tr>
            </table>
        </fieldset>
    </body>
</html>
