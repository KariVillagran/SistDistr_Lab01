<%-- 
    Document   : home-master
    Created on : 09-05-2016, 16:54:53
    Author     : aialiagam
--%>

<%@tag description="Lab01 - Home Master Page" pageEncoding="UTF-8"%>
<%@attribute name="userName" fragment="true" %>
<%@attribute name="pageTitle" fragment="true" %>
<%@attribute name="breadcrumb" fragment="true" %>

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
            
             function ValidarCerrarSesion() {
                BootstrapDialog.show({
                    type: BootstrapDialog.TYPE_DANGER,
                    title: 'Lab01 RMI - Cerrar Sesión',
                    message: '¿Desea cerrar la sesión actual?',
                    buttons: [
                        {
                            label: 'Sí',
                            cssClass: 'btn-primary',
                            action: function() {
                                $('[id$=btnCerrarSesion]').click();
                            }
                        },
                        {
                            label: 'No',
                            cssClass: 'btn-default',
                            action: function(dialogRef) {
                                dialogRef.close();
                            }
                        }
                    ]
                });
            }
        </script>
        
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

        <div id="divModalLoading" style="display: none">
        </div>
        
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
              <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Sistema Lab01 RMI</a>
              </div>
              <div id="navbar" class="navbar-collapse collapse">
                  <p class="navbar-text navbar-right">
                      <span class="loginNameTitle">Bienvenido(a):</span>&nbsp;
                      <a href="#" class="navbar-link">
                          <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                          <span><jsp:invoke fragment="userName"/></span>&nbsp;
                      </a>
                      <a href="#" class="navbar-link sign-out-custom" onclick="ValidarCerrarSesion()">
                          <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                      </a>
                  </p>
                  <div style="display:none">
                      <form action="LogoutController" method="post">
                        <input id="btnCerrarSesion" type="submit" value="[x]">
                      </form>
                  </div>
              </div>
            </div>
        </nav>
        <div class="container-fluid">
          <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar"></ul>    
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h2 class="sub-header">
                    <jsp:invoke fragment="pageTitle"/>
                </h2>
                <div id="breadcrumb">
                    <jsp:invoke fragment="breadcrumb"/>
                </div>
                <div id="divMessage">
                </div>
                
                <jsp:doBody />
                
            </div>
          </div>
        </div>
  </body>
</html>