<%-- 
    Document   : home
    Created on : 09-05-2016, 11:58:26
    Author     : aialiagam
--%>

<%@page import="dto.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib prefix="mp" tagdir="/WEB-INF/tags" %>

<mp:home-master>
    <jsp:attribute name="pageTitle">
      Lab01 - Home
    </jsp:attribute>
    <jsp:attribute name="userName">
        ${sessionScope.currentUser.GetUserName()}
    </jsp:attribute>
    <jsp:attribute name="pageSubtitle">
      Lab01 - P&aacute;gina de Inicio
    </jsp:attribute>
     <jsp:attribute name="breadcrumb">
         <ul class="breadcrumb">
             <li>Home</li>
         </ul>
    </jsp:attribute>
    <jsp:body>
        <p>Bienvenido(a): <b>${sessionScope.currentUser.GetNombreCompleto()}</b>, al <u>Sistema Lab01 RMI Web</u> :D!!</p>
        <br/>
        <p>En este sitema podr&aacute;s probar la funcionalidad de una arquitectura Cliente-Servidor.</p>
        <p>Utiliza los diferentes mantenedores para realizar las pruebas respectivas.</p>
        <br/>
        <p>Por cualquier referencia al c&oacute;digo fuente, dir&iacute;gete a la siguiente p&aacute;gina web: 
            <a href="https://github.com/aledev/SistDistr_Lab01" target="_blank">github.com/aledev/SistDistr_Lab01</a></p>
    </jsp:body>
</mp:home-master>

