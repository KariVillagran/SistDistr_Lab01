<%-- 
    Document   : home
    Created on : 09-05-2016, 11:58:26
    Author     : aialiagam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mp" tagdir="/WEB-INF/tags" %>

<mp:home-master>
    <jsp:attribute name="pageTitle">
      Lab01 - Home
    </jsp:attribute>
    <jsp:attribute name="userName">
      ${requestScope['user'].GetUserName()}
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
        <p>P&aacute;gina con Masterpage de prueba!</p>
    </jsp:body>
</mp:home-master>

