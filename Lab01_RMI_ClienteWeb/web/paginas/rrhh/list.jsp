<%-- 
    Document   : list
    Created on : 09-05-2016, 23:03:38
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mp" tagdir="/WEB-INF/tags" %>

<mp:home-master>
    <jsp:attribute name="pageTitle">
      Lab01 - Lista de Recursos Humanos
    </jsp:attribute>
    <jsp:attribute name="userName">
      ${sessionScope.currentUser.GetUserName()}
    </jsp:attribute>
    <jsp:attribute name="pageSubtitle">
      Lab01 - Mantenedor de Recursos Humanos
    </jsp:attribute>
     <jsp:attribute name="breadcrumb">
         <ul class="breadcrumb">
             <li>Home</li> > <li>Mantenedores</li> > <li>Recursos Humanos</li>
         </ul>
    </jsp:attribute>
    <jsp:body>
        <p>P&aacute;gina con Masterpage de prueba!</p>
    </jsp:body>
</mp:home-master>
