<%-- 
    Document   : list
    Created on : 09-05-2016, 23:03:18
    Author     : alejandro
--%>

<%@page import="dto.FinanzaDTO"%>
<%@page import="dto.UsuarioDTO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="mp" tagdir="/WEB-INF/tags" %>

<% 
    String _htmlBody = "";
    
    if(request.getAttribute("finanzaAllObj") != null){
        List<FinanzaDTO> objList = (List<FinanzaDTO>)request.getAttribute("finanzaAllObj");
        if(objList.size() > 0){
            for(int x = 0; x < objList.size(); x++){
                _htmlBody += "<tr>";
                _htmlBody += "<td>" +
                             "<a href=\"FinanzaController?action=editar&id=" + objList.get(x).GetId() + "\">" +
                                "<span class=\"glyphicon glyphicon-pencil\" title=\"Editar\">&nbsp;</span></a>&nbsp;" +
                             "<a href=\"javascript:void(0)\" onclick=\"EliminarFinanza('" + objList.get(x).GetId() + "')\">" + 
                                "<span class=\"glyphicon glyphicon-remove\" title=\"Eliminar\">&nbsp;</span></a>" +
                             "</td>";
                _htmlBody += "<td>" + objList.get(x).GetId() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetNombrePersonal() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetSaldoMovimiento().toString() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetFchMovimiento().toString() + "</td>";
                _htmlBody += "</tr>";
            }
        }
        else{
             _htmlBody += "<tr>" +
                     "<td colspan=\"5\">No hay datos para mostrar...</td>" +
                     "</tr>";
        }
    }
    else{
        _htmlBody += "<tr>" +
                     "<td colspan=\"5\">No hay datos para mostrar...</td>" +
                     "</tr>";
    }
    
    request.setAttribute("datosTabla", _htmlBody);
%>

<mp:home-master>
    <jsp:attribute name="pageTitle">
      Lab01 - Lista de Finanzas
    </jsp:attribute>
    <jsp:attribute name="userName">
      ${sessionScope.currentUser.GetUserName()}
    </jsp:attribute>
    <jsp:attribute name="pageSubtitle">
      Lab01 - Mantenedor de Finanzas
    </jsp:attribute>
     <jsp:attribute name="breadcrumb">
         <ul class="breadcrumb">
             <li>Home</li> <li>Mantenedores</li> <li>Finanzas</li>
         </ul>
    </jsp:attribute>
    <jsp:body>
        <script type="text/javascript">
            $(document).ready(function () {
                OnInit();

                $(window).resize(function () {
                    OnInit();
                });
            });
            
            function OnInit() {
                var _rows = $('[id$=dgvListaDatos]').find('tbody > tr ');

                if (_rows.length > 0) {
                    $(_rows).each(function (i) {
                        var _cells = $(this).find('td');

                        if (_cells.length > 0) {
                            $(_cells).each(function (j) {
                                $(this).css('max-width', $(this).css('width'));
                                $(this).css('text-overflow', 'ellipsis');
                                $(this).css('overflow', 'hidden');
                                $(this).css('white-space', 'nowrap');
                            });
                        }
                    });
                }
                
                var _msjInfo = '${requestScope['infocreate']}';
                if(_msjInfo != ''){
                    ShowMessageDialog('alert-info', 'Lab01 - Información', _msjInfo);
                }
                else{
                    CleanMessageDialog();
                }
            }
            
            function EliminarUsuario(id){
                var result = confirm('¿Desea eliminar el usuario?');
                
                if(result){
                    location.href = 'RrhhController?action=eliminar&id=' + id;
                }
            }
        </script>
        
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title"><a href="#" onclick="ShowHideFilter(this)"><span class="glyphicon glyphicon-menu-up"></span></a> &nbsp; Filtro de B&uacute;squeda</h3>
          </div>
          <form id="formBuscar" action="FinanzaController" method="post">
            <input type="hidden" name="action" value="buscar">
            <div class="panel-body">
              <div class="row">
                  <div class="col-xs-6 col-md-4">
                      <div class="form-group">
                        <label class="control-label col-sm-2" for="nombre">Nombre:</label>
                        <div class="col-sm-10">     
                          <input type="text" id="txtNombre" class="form-control" runat="server" placeholder="Nombre" 
                              autofocus="autofocus" name="nombre">
                        </div>
                      </div>
                  </div>
                  <div class="col-xs-6 col-md-4">
                      <div class="form-group">
                         &nbsp;
                      </div>
                  </div>
                  <div class="col-xs-6 col-md-4">
                      <div class="form-group">
                           &nbsp;
                      </div>
                  </div>
              </div>
              <br />
              <div class="row">
                  <div class="col-xs-6 col-md-4">
                      <input type="button" value="Buscar" class="btn btn-primary">
                  </div>
                  <div class="col-xs-6 col-md-4">
                  </div>
                  <div class="col-xs-6 col-md-4">
                  </div>
              </div>
          </div>
        </form>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Lista de Datos</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-xs-6 col-md-4">
                </div>
                <div class="col-xs-6 col-md-4">
                </div>
                <div class="col-xs-6 col-md-4 text-right">
                    <form id="formCrearNuevo" action="FinanzaController" method="post">
                        <input type="hidden" name="action" value="nuevo">
                        <input type="submit" value="Crear Nuevo" class="btn btn-primary">
                    </form>
                </div>
            </div>
            <br />
            <div class="row">
                <table id="dgvListaDatos" class="table table-hover table-striped table-custom">
                    <thead>
                        <tr>
                            <th>&nbsp;</th>
                            <th>Id</th>
                            <th>Nombre Persona</th>
                            <th>Saldo Movimiento</th>
                            <th>Fch. Movimiento</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${datosTabla}
                    </tbody>
                </table>
            </div>
            <div class="row">
                
            </div>
        </div>
    </div>
    </jsp:body>
</mp:home-master>
