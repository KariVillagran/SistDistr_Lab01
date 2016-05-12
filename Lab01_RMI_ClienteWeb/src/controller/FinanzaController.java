/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FinanzaModel;

/**
 *
 * @author aialiagam
 */
@WebServlet(name = "FinanzaController", urlPatterns = {"/FinanzaController"})
public class FinanzaController extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    private static String CPREFIX = "Lab01_RMI_ClienteWeb.src.controller.FinanzaControler";
    private FinanzaModel finanzaModel;
    private static final long serialVersionUID = 1L;
    private static Logger logger;
    // </editor-fold>
 
    // <editor-fold defaultstate="collapsed" desc="constructores">
    public FinanzaController() {
        super();        
        logger = Logger.getLogger(getClass().getName());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="eventos">
    
    // <editor-fold defaultstate="collapsed" desc="doPost">
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String MPREFIX = " [doPost(HttpServletRequest request, HttpServletResponse response)]";
        
        String id = request.getParameter("idFinanza"); // no visible
        String nombrePersona = request.getParameter("nombrePersona");
        String fechaMoviento = request.getParameter("fechaMovimiento");
        String saldoMoviento = request.getParameter("saldoMovimiento");
        String transaccion = request.getParameter("transaccion");
        
        RequestDispatcher rd = null;
         System.out.println("controller.FinanzaController.doPost()1"+transaccion);
        try{
            // Creamos un nuevo objeto tipo UsuarioDTO
            System.out.println("controller.FinanzaController.doPost()2");

            FinanzaDTO objRequest = new FinanzaDTO();
            if(id==null || id.equals(""))
            {id="0";}   
             objRequest.SetId(Integer.parseInt(id));
             System.out.println("controller.FinanzaController.doPost()3");

             objRequest.SetNombrePersonal(nombrePersona);
                          System.out.println("controller.FinanzaController.doPost()4");

             objRequest.SetFchMovimiento(new Date());
             System.out.println("controller.FinanzaController.doPost()5");
             switch(transaccion)
             {
             case "buscarAll": 
                    List<FinanzaDTO> objResult = this.getFinanzaModel().buscarAll();
                    if(objResult != null){
                        rd = request.getRequestDispatcher("paginas/finanzas/list.jsp");
                        request.setAttribute("finanzaAllObj", objResult);
                    }
                    else{
                        rd = request.getRequestDispatcher("paginas/finanzas/list.jsp");
                        request.setAttribute("finanzaAllObj", objResult);
                        request.setAttribute("error", "No existen datos para mostrar!");
                    }
             break;
             case "buscarId": 
           
                    FinanzaDTO objsalida = this.getFinanzaModel().buscarById(objRequest.GetId());
                    if(objsalida != null){
                    rd = request.getRequestDispatcher("/paginas/finanzas/list.jsp");
                    //Cookie loginCookie = new Cookie("currentUser", objResult.GetUserName());
                    //loginCookie.setMaxAge(conf.Parameters.PARAM_COOKIE_TIMEOUT * 60);
                    request.setAttribute("finanzaId", objsalida);
                     request.setAttribute("finanzaAll", buscarTodo());

                    }
                    else{
                        rd = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("error", "Se ha presentado un problema al realizar peticion, conectate  mesa de ayuda");
                        }
                 
             break;
             case "eliminar": 
                 
                     boolean salida5 = this.getFinanzaModel().borrarFinanza(objRequest);
                    if(salida5){
                    rd = request.getRequestDispatcher("/paginas/finanzas/finanzaMantenedor.jsp");
                    //Cookie loginCookie = new Cookie("currentUser", objResult.GetUserName());
                    //loginCookie.setMaxAge(conf.Parameters.PARAM_COOKIE_TIMEOUT * 60);
                    request.setAttribute("salida", salida5);
                    request.setAttribute("finanzaAll", buscarTodo());
                    }
                    else{
                        rd = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("error", "Se ha presentado un problema al realizar peticion, conectate  mesa de ayuda");
                        }
                 
             break;
             case "update": 
                               boolean salida2 = this.getFinanzaModel().update(objRequest);
                    if(salida2){
                    rd = request.getRequestDispatcher("/paginas/finanzas/finanzaMantenedor.jsp");
                    //Cookie loginCookie = new Cookie("currentUser", objResult.GetUserName());
                    //loginCookie.setMaxAge(conf.Parameters.PARAM_COOKIE_TIMEOUT * 60);
                    request.setAttribute("salida", salida2);
                    request.setAttribute("finanzaAll", buscarTodo());
                    }
                    else{
                        rd = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("error", "Se ha presentado un problema al realizar peticion, conectate  mesa de ayuda");
                        }   
                 
             break;
            case "insert": 
                
                                 boolean salida3 = this.getFinanzaModel().insertarFinanza(objRequest);
                    if(salida3){
                    rd = request.getRequestDispatcher("/paginas/finanzas/finanzaMantenedor.jsp");
                    //Cookie loginCookie = new Cookie("currentUser", objResult.GetUserName());
                    //loginCookie.setMaxAge(conf.Parameters.PARAM_COOKIE_TIMEOUT * 60);
                    request.setAttribute("salida", salida3);
                    request.setAttribute("finanzaAll", buscarTodo());
                    }
                    else{
                        rd = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("error", "Se ha presentado un problema al realizar peticion, conectate  mesa de ayuda");
                        }
            break;
            case "mantenedor":
                
                List<FinanzaDTO> objResult2 = this.getFinanzaModel().buscarAll();
                    System.out.println("controller.FinanzaController.doPost()22");
                    if(objResult2 != null){
                        System.out.println("controller.FinanzaController.doPost()33");
                          System.out.println("controller.FinanzaController.doPost()"+objResult2.size());
                    request.getSession().setAttribute("finanzaAll",objResult2); 
                    rd = request.getRequestDispatcher("/paginas/finanzas/finanzaMantenedor.jsp");
                    //Cookie loginCookie = new Cookie("currentUser", objResult.GetUserName());
                    //loginCookie.setMaxAge(conf.Parameters.PARAM_COOKIE_TIMEOUT * 60);
                     
                    
                    }
                    else{
                        rd = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("error", "Se ha presentado un problema al realizar peticion, conectate  mesa de ayuda");
                        }
            break;
            default: 
            break; 
             }
                     
   
        }
        catch(RemoteException ex){
            rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("error", "Ha ocurrido un error en el servidor. Disculpe las molestias :( ");
                
            logger.log(Level.WARNING, 
                    String.format("%s %s %s", 
                            CPREFIX, 
                            MPREFIX, 
                            "=> Error al intentar validar el usuario. Detalle: " + ex.getMessage()));
        }
        catch(Exception ex){
            rd = request.getRequestDispatcher("login.jsp");
            request.setAttribute("error", "Ha ocurrido un error durante la solicitud. Por favor, intentelo nuevamente");
                
           /* logger.log(Level.WARNING, 
                    String.format("%s %s %s", 
                            CPREFIX, 
                            MPREFIX, 
                            "=> Error al intentar validar el usuario. Detalle: " + ex.getMessage()));*/
        }
        
        rd.forward(request, response);
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos privados">
    private FinanzaModel getFinanzaModel(){
        if(this.finanzaModel == null){
            this.finanzaModel = FinanzaModel.GetInstance();
        }
        
        return this.finanzaModel;
    }
    // </editor-fold>
    
    private List<FinanzaDTO> buscarTodo()
    {
        try {
            List<FinanzaDTO> objResult = this.getFinanzaModel().buscarAll();
            return objResult;
        } catch (Exception ex) {
            Logger.getLogger(FinanzaController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    } 
      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       /* try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           /* out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
            doPost(request, response);
    }
}
