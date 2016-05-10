<%-- 
    Document   : finanzaMantenedor
    Created on : 10-may-2016, 1:44:42
    Author     : progre
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
    <script>
              function  limpiar()
        {
            document.getElementById("nombreFin").value="";
            document.getElementById("montoFin").value="";
            document.getElementById("selectID").value=0;
        }
        
       function  ingresar()
        {
           nombre= document.getElementById("nombreFin").value;
           motin= document.getElementById("montoFin").value;
           opcion= document.getElementById("selectID").value; 

                    window.alert(nombre+motin+opcion);

         /* if(opcion!=null && nombre!=null && motin!=null)
          {  
             window.alert("distinto de null");
          location.href="/FinanzaController?transaccion=insert&nombrePersona="+nombre+"&saldoMovimiento="+motin+"&idFinanza="+opcion+"&";
          }
          else
          {
             window.alert("no pueden haber campos vacios");
   
          }  */ 
        }
       function  update()
        {
           /*nombre= document.getElementById("nombreFin").value;
           motin= document.getElementById("montoFin").value;
           opcion= document.getElementById("selectID").value;        
                    window.alert(nombre+motin+opcion);

          if(opcion!=null && nombre!=null && motin!=null)
          {  
             window.alert("distinto de null");
          location.href="/FinanzaController?transaccion=update&nombrePersona="+nombre+"&saldoMovimiento="+motin+"&idFinanza="+opcion+"&";
          }
          else
          {
             window.alert("no pueden haber campos vacios");
   
          } */
        }
       function  eliminar()
      window.alert("sdfsdfsd");
           
           /*nombre= document.getElementById("nombreFin").value;
           motin= document.getElementById("montoFin").value;
           opcion= document.getElementById("selectID").value; 
         */
         /*if(opcion!=null && nombre!=null && motin!=null)
          {  
             window.alert("distinto de null");
          location.href="/FinanzaController?transaccion=eliminar&nombrePersona="+nombre+"&saldoMovimiento="+motin+"&idFinanza="+opcion+"&"";
          }
          else
          {
             window.alert("no pueden haber campos vacios");
   
          } */
   
        }
 
    </script>
    <body>
        <%List lista=(List)session.getAttribute("finanzaAll");%>
        <h1>Mantenedor Finanza</h1>
        
        <table>
            
            <tr>
                
                <td><select id="selectID">
                        <option value="0">...</option>
                        <%
                        /* <option value="value1">Value 1</option> 
                            <option value="value2" selected>Value 2</option>
                            <option value="value3">Value 3</option>
                            */ 

                 for(int i=0; i<lista.size(); i++){
                        FinanzaDTO fin=(FinanzaDTO)lista.get(i);
                        out.print("<option value='");
                        out.print(fin.GetId());
                        out.print("'>");
                        out.print(fin.GetNombrePersonal());
                        out.print("</option>");
                         }
                        %>
                    </select></td>
            </tr>
            
            <tr>
                <td>Nombre:</td>
                <td><input id="nombreFin" type="text"></td>
            </tr>
            
            <tr>
                <td>Monto:</td>
                <td><input id="montoFin" type="text"></td>
            </tr>
            <tr>
                <td><input  onclick="ingresar()" type="button" value="Ingresar"></td>
                <td><input  onclick="update()" type="button" value="actualizar"></td>
                <td><input onclick="eliminar()" type="button" value="Eliminar"></td>
                <td><input onclick="limpiar()" type="button" value="Limpiar"></td>
            </tr>
            
        </table>
        <%String mensaje="";
          if(null!=(String)session.getAttribute("salida")){
            mensaje=(String)session.getAttribute("salida");
          }
          out.print(mensaje);
        %>
    </body>
</html>
