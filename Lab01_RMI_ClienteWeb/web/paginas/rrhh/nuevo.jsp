<%-- 
    Document   : nuevo
    Created on : 10-05-2016, 11:27:19
    Author     : aialiagam
--%>

<%@page import="dto.RecursoHumanoDTO"%>
<%@page import="dto.UsuarioDTO"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="mp" tagdir="/WEB-INF/tags" %>

<mp:home-master>
    <jsp:attribute name="pageTitle">
      Lab01 - Crear Recurso Humano
    </jsp:attribute>
    <jsp:attribute name="userName">
      ${sessionScope.currentUser.GetUserName()}
    </jsp:attribute>
    <jsp:attribute name="pageSubtitle">
      Lab01 - Crear Recurso Humano
    </jsp:attribute>
     <jsp:attribute name="breadcrumb">
         <ul class="breadcrumb">
             <li>Home</li> <li>Mantenedores</li> <li>Recursos Humanos</li> <li>Crear</li>
         </ul>
    </jsp:attribute>
    <jsp:body>
        <form id="formNuevoRRHH" action="RrhhController" method="post">
        <input type="hidden" name="action" value="guardarNuevo">
        
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Datos del Recurso Humano</h3>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="nombre">Nombre:</label>
                             <div class="col-sm-10">
                                 <input type="text" class="form-control" id="txtNombre" name="nombre" 
                                     required="required" placeholder="Nombre" maxlength="100">
                             </div>  
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="direccion">Direcci&oacute;n:</label>
                             <div class="col-sm-10">
                                <input type="text" class="form-control" id="txtDireccion" name="direccion" 
                                 required="required" placeholder="Dirección" maxlength="100">
                             </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="region">Regi&oacute;n:</label>
                             <div class="col-sm-10">
                                <select id="ddlRegion" name="region" class="form-control" required="required">
                                     <option value="Arica y Parinacota">Arica y Parinacota</option>
                                     <option value="Tarapacá">Tarapac&aacute;</option>
                                     <option value="Antofagasta">Antofagasta</option>
                                     <option value="Atacama">Atacama</option>
                                     <option value="Coquimbo">Coquimbo</option>
                                     <option value="Valparaíso">Valpara&iacute;so</option>
                                     <option value="OHiggins">O'Higgins</option>
                                     <option value="Maule">Maule</option>
                                     <option value="Bío-Bío">B&iacute;o-B&iacute;o</option>
                                     <option value="La Araucanía">La Araucan&iacute;a</option>
                                     <option value="Los Ríos">Los R&iacute;os</option>
                                     <option value="Los Lagos">Los Lagos</option>
                                     <option value="Aysén">Ays&eacute;n</option>
                                     <option value="Magallanes">Magallanes</option>
                                     <option value="R.M.">R.M.</option>
                                 </select>
                             </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="comuna">Comuna:</label>
                             <div class="col-sm-10">
                                <input type="text" class="form-control" id="txtComuna" name="comuna" 
                                 required="required" placeholder="Comuna" maxlength="50">
                             </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="email">Email:</label>
                             <div class="col-sm-10">
                                <input type="text" class="form-control" id="txtEmail" name="email" 
                                       required="required" placeholder="Email" maxlength="100">
                             </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="telefono">Tel&eacute;fono</label>
                             <div class="col-sm-10">
                                <input type="text" class="form-control" id="txtTelefono" name="telefono" 
                                       required="required" placeholder="Teléfono" maxlength="25">
                             </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="fchNacimiento">Fecha Nacimiento:</label>
                             <div class="col-sm-10">
                                 <input type="date" class="form-control" id="txtFchNacimiento" name="fchNacimiento" 
                                 required="required" placeholder="Fecha Nacimiento">
                             </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="fchContrato">Fecha Contrato:</label>
                             <div class="col-sm-10">
                               <input type="date" class="form-control" id="txtFchContrato" name="fchContrato" 
                                 required="required" placeholder="Fecha Contrato">
                            </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="sexo">Sexo:</label>
                             <div class="col-sm-10">
                                 <select id="ddlSexo" name="sexo" class="form-control" required="required">
                                     <option value="Masculino">Masculino</option>
                                     <option value="Femenenino">Femenino</option>
                                 </select>
                             </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-8">
                         <div class="form-group">
                             <label class="control-label col-sm-2" for="depto">Departamento:</label>
                             <div class="col-sm-10">
                                 <select id="ddlDepto" name="depto" class="form-control" required="required">
                                     <option value="Informática">Inform&aacute;tica</option>
                                     <option value="RRHH">Recursos Humanos</option>
                                     <option value="Finanzas">Finanzas</option>
                                 </select>
                             </div>
                         </div>
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-xs-6 col-md-4">
                    </div>
                    <div class="col-xs-6 col-md-4">
                        <input type="submit" value="Guardar" class="btn btn-success"> &nbsp;
                        <input type="button" value="Volver" class="btn btn-default" onclick="javacript:location.href = 'RrhhController?action=lista'"
                               formnovalidate="formnovalidate">
                    </div>
                    <div class="col-xs-6 col-md-4">
                    </div>
                </div>
            </div>
        </div>
        </form>
    </jsp:body>
</mp:home-master>