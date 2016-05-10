<%-- 
    Document   : list
    Created on : 09-05-2016, 23:03:18
    Author     : alejandro
--%>

<%@page import="dto.FinanzaDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Finanzas List</h1>
        <%List lista=(List)session.getAttribute("finanzaAll");%>
        <table>
            <tr>
                <td>ID</td> 
                <td>Nombre</td>
                <td>Movimiento</td> 
                <td>fecha Movimiento</td>
            </tr> 
            <% for(int i=0; i<lista.size(); i++){
                FinanzaDTO fin=(FinanzaDTO)lista.get(i);
                out.print("<tr>");
                out.print("<td>");
                    out.print(fin.GetId());
                out.print("</td>");
                out.print("<td>");
                out.print(fin.GetNombrePersonal());
                out.print("</td>");
                out.print("<td>");
                out.print(fin.GetSaldoMovimiento());
                out.print("</td>");
                out.print("<td>");
                out.print(fin.GetFchMovimiento());
                out.print("</td>");
                out.print("</tr>");
            }
              
            %>
            
            
        </table>
        
    </body>
</html>
