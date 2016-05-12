<%-- 
    Document   : list
    Created on : 09-05-2016, 23:03:38
    Author     : alejandro
--%>

<%@page import="dto.RecursoHumanoDTO"%>
<%@page import="dto.UsuarioDTO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="mp" tagdir="/WEB-INF/tags" %>

<% 
    String _htmlBody = "";
    
    if(request.getAttribute("rrhhListObj") != null){
        List<RecursoHumanoDTO> objList = (List<RecursoHumanoDTO>)request.getAttribute("rrhhListObj");
        if(objList.size() > 0){
            for(int x = 0; x < objList.size(); x++){
                _htmlBody += "<tr>";
                _htmlBody += "<td>" +
                             "<a href=\"RrhhController?action=editar&id=" + objList.get(x).GetId() + "\">" +
                                "<span class=\"glyphicon glyphicon-pencil\" title=\"Editar\">&nbsp;</span></a>&nbsp;" +
                             "<a href=\"javascript:void(0)\" onclick=\"EliminarUsuario('" + objList.get(x).GetId() + "')\">" + 
                                "<span class=\"glyphicon glyphicon-remove\" title=\"Eliminar\">&nbsp;</span></a>" +
                             "</td>";
                _htmlBody += "<td>" + objList.get(x).GetId() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetNombrePersona() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetFchNacimiento().toString() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetDireccion() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetComuna() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetRegion() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetEmail() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetTelefono() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetSexo() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetFchContrato().toString() + "</td>";
                _htmlBody += "<td>" + objList.get(x).GetDepartamento() + "</td>";
                _htmlBody += "</tr>";
            }
        }
        else{
             _htmlBody += "<tr>" +
                     "<td colspan=\"12\">No hay datos para mostrar...</td>" +
                     "</tr>";
        }
    }
    else{
        _htmlBody += "<tr>" +
                     "<td colspan=\"12\">No hay datos para mostrar...</td>" +
                     "</tr>";
    }
    
    request.setAttribute("datosTabla", _htmlBody);
%>

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
             <li>Home</li> <li>Mantenedores</li> <li>Recursos Humanos</li>
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
          <form id="formBuscar" action="RrhhController" method="post">
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
                        <label class="control-label col-sm-2" for="depto">Departamento:</label>
                        <div class="col-sm-10">     
                          <input type="text" id="txtDepto" class="form-control" runat="server" placeholder="Departamento" 
                          autofocus="autofocus" name="depto">
                        </div>
                      </div>
                  </div>
                  <div class="col-xs-6 col-md-4">
                      <div class="form-group">
                          <label for="direccion" class="control-label col-sm-2">Direcci&oacute;n:</label>
                          <div class="col-sm-10">     
                              <input type="text" id="txtDireccion" class="form-control" runat="server" placeholder="Dirección" 
                                  autofocus="autofocus" name="direccion">
                          </div>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <div class="col-xs-6 col-md-4">
                      <div class="form-group">
                          <label for="region" class="control-label col-sm-2">Regi&oacute;n:</label>
                          <div class="col-sm-10">
                              <input type="text" id="txtRegion" class="form-control" runat="server" placeholder="Región" 
                          autofocus="autofocus" name="region">
                          </div>
                      </div>
                  </div>
                  <div class="col-xs-6 col-md-4">
                      <div class="form-group">
                          <label for="comuna" class="control-label col-sm-2">Comuna:</label>
                          <div class="col-sm-10">
                              <input type="text" id="txtComuna" class="form-control" runat="server" placeholder="Comuna" 
                          autofocus="autofocus" name="comuna">
                          </div>
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
                      <input type="submit" value="Buscar" class="btn btn-primary">
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
                    <form id="formCrearNuevo" action="RrhhController" method="post">
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
                            <th>Nombre</th>
                            <th>Fch. Nacimiento</th>
                            <th>Direcci&oacute;n</th>
                            <th>Comuna</th>
                            <th>Regi&oacute;n</th>
                            <th>Email</th>
                            <th>Tel&eacute;fono</th>
                            <th>Sexo</th>
                            <th>Fch. Contrato</th>
                            <th>Depto</th>
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
