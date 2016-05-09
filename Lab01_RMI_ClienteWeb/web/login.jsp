<%-- 
    Document   : login
    Created on : 09-05-2016, 11:58:14
    Author     : aialiagam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <!-- Latest compiled and minified CSS -->
        <link href="Assets/css/bootstrap.css" rel="stylesheet">
        <!-- Optional theme -->
        <link href="Assets/css/bootstrap-theme.css" rel="stylesheet">
        <!-- Estilos de Librería Bootstrap Dialog -->
        <link href="Assets/css/bootstrap-dialog.css" rel="stylesheet">
        <!-- Título de la página -->
        <title>Lab01 - Inicio de Sesión</title>
        <!-- Agregamos la librería de Jquery -->
        <script type="text/javascript" src="Assets/scripts/jquery-1.12.3.min.js"></script>
        <!-- Agregamos las librería de Bootstrap -->
        <script type="text/javascript" src="Assets/scripts/bootstrap.min.js"></script>
        <!-- Agregamos la librería de Bootstrap-Dialog -->
        <script type="text/javascript" src="Assets/scripts/bootstrap-dialog.js"></script>
        <!-- Agregamos la librería de spin.js -->
        <script type="text/javascript" src="Assets/scripts/spin.min.js"></script>
        
        <!-- Estilos Customs -->
         <style type="text/css">
            body {
              padding-top: 40px;
              padding-bottom: 40px;
              background-color: #fff; 
            }
            .form-signin {
              max-width: 450px;
              padding: 15px;
              margin: 0 auto;
            }
            .form-signin .form-signin-heading,
            .form-signin .checkbox {
              margin-bottom: 10px;
            }
            .form-signin .checkbox {
              font-weight: normal;
            }
            .form-signin .form-control {
              position: relative;
              height: auto;
              -webkit-box-sizing: border-box;
                 -moz-box-sizing: border-box;
                      box-sizing: border-box;
              padding: 10px;
              font-size: 16px;
            }
            .form-signin .form-control:focus {
              z-index: 2;
            }
            .form-signin input[type="email"] {
              margin-bottom: -1px;
              border-bottom-right-radius: 0;
              border-bottom-left-radius: 0;
            }
            .form-signin input[type="password"] {
              margin-bottom: 10px;
              border-top-left-radius: 0;
              border-top-right-radius: 0;
            }
            fieldset
            {
                    border-color:#b30315;
            }
            fieldset > legend
            {
                    color: #b30315;
                    margin-left:auto;
                    margin-right:auto;
                    text-align:center;
            }
            .logoLogin .titleLogo
            {
                    margin-left:auto;
                    margin-right:0px;
                    font-size:1.8em;
                    font-weight:bold;
                    text-align:right;
                    color:#e10707;
            }
            #divModalLoading {
                position: fixed;
                z-index: 9999;
                height: 100%;
                width: 100%;
                top: 0;
                left: 0;
                background-color:#000;
                filter: alpha(opacity=60);
                opacity: 0.6;
                -moz-opacity: 0.8;
            }
        </style>
    </head>
    <body>
        <script type="text/javascript">
            //Sys.WebForms.PageRequestManager.getInstance().add_initializeRequest(InitRequestHandler);
            //Sys.WebForms.PageRequestManager.getInstance().add_endRequest(EndRequestHandler);

            function InitRequestHandler(sender, args) {
                var opts = {
                    lines: 13 // The number of lines to draw
                        , length: 28 // The length of each line
                        , width: 14 // The line thickness
                        , radius: 42 // The radius of the inner circle
                        , scale: 1 // Scales overall size of the spinner
                        , corners: 1 // Corner roundness (0..1)
                        , color: '#fff' // #rgb or #rrggbb or array of colors
                        , opacity: 0.25 // Opacity of the lines
                        , rotate: 0 // The rotation offset
                        , direction: 1 // 1: clockwise, -1: counterclockwise
                        , speed: 1 // Rounds per second
                        , trail: 60 // Afterglow percentage
                        , fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
                        , zIndex: 2e9 // The z-index (defaults to 2000000000)
                        , className: 'spinner' // The CSS class to assign to the spinner
                        , top: '50%' // Top position relative to parent
                        , left: '50%' // Left position relative to parent
                        , shadow: false // Whether to render a shadow
                        , hwaccel: false // Whether to use hardware acceleration
                        , position: 'absolute' // Element positioning
                };

                var spinner = new Spinner(opts).spin();
                var target = document.getElementById('divModalLoading');
                $(target).html('');
                $(target).append(spinner.el);
                $(target).show();
            }

            function EndRequestHandler(sender, args) {
                var target = document.getElementById('divModalLoading');
                $(target).html('');
                $(target).hide();
            }
            
            function ShowMessageDialog(type, title, message) {
                $('#divMessage').html('');
                var _html = "<div class=\"alert " + type + " alert-dismissible\" role=\"alert\">" +
                            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</button>" +
                            "<span style=\"font-size:1.2em;font-weight:bold\">" + title + "</span><br/>" +
                            "<span>" + message + "</span>" +
                            "</div>";

                $('#divMessage').html(_html);
            }

            function CleanMessageDialog() {
                $('#divMessage').html('');
            }
            
            function ValidarDatos(){
                var isValid = true;
                var validationMessage = '<ul>';

                var _rut = $('[id$=txtUsuario]').val();
                if (_rut.trim() == '') {
                    validationMessage += '<li>Debe ingresar el Usuario</li>';
                    isValid = false;
                }
                
                var _password = $('[id$=txtPassword]').val();
                if (_password.trim() == '') {
                    validationMessage += '<li>Debe ingresar la Contraseña</li>';
                    isValid = false;
                }

                validationMessage += '</ul>';

                if (!isValid) {
                    ShowMessageDialog(
                    'alert-info',
                    "Lab01 - Inicio de Sesión",
                    "<h3>Debe ingresar los siguientes campos:</h3>" + validationMessage
                  );
                }

                return isValid;
            }
            
            
            $(document).ready(function() {
                var _msjError = '${requestScope['error']}';
                if(_msjError != ''){
                    ShowMessageDialog('alert-danger', 'Lab01 - Error', _msjError);
                }
                else{
                    CleanMessageDialog();
                }
            });
        </script>
        
        <div class="container">
            <div class="modal fade">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                      <h4 class="modal-title">Modal title</h4>
                    </div>
                    <div class="modal-body">
                      <p>One fine body…</p>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Aceptar</button>
                      <button type="button" class="btn btn-primary">Cancelar</button>
                    </div>
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
              </div><!-- /.modal -->
        
            <div id="divMessage">
            </div>
        
            <div id="divModalLoading" style="display: none">
            </div>
            
              <form id="formLogin" action="LoginController" method="post" onsubmit="return ValidarDatos()">
                <div id="formLogin" class="form-signin">
                    <h2 style="margin-bottom:25px">Lab01 - Cliente RMI Web</h2>
                    <fieldset>
                        <legend>Inicio de Sesión</legend>
                        
                        <label for="username" class="sr-only">Usuario:</label>
                        <input type="text" id="txtUsuario" name="username" class="form-control" placeholder="Usuario (Ej: usuario01)">
                        
                        <label for="password" class="sr-only">Contrase&ntilde;a:</label>
                        <input type="password" id="txtPassword" name="password" class="form-control" placeholder="Contraseña">
                        
                        <input type="submit" id="btnIngresar" value="Ingresar" class="btn btn-lg btn-danger btn-block">
                    </fieldset>
                </div>
            </form>
              
        </div>
    </body>
</html>
