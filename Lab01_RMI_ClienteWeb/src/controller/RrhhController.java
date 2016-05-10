/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.RecursoHumanoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RrhhModel;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "RrhhController", urlPatterns = {"/RrhhController"})
public class RrhhController extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    private static String CPREFIX = "Lab01_RMI_ClienteWeb.src.controller.RrhhController";
    private RrhhModel rrhhModel;
    private static final long serialVersionUID = 1L;
    private static Logger logger;
    // </editor-fold>
 
    // <editor-fold defaultstate="collapsed" desc="constructores">
    public RrhhController() {
        super();        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="eventos">
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String MPREFIX = "[processRequest(HttpServletRequest request, HttpServletResponse response)]";
        
        String action = request.getParameter("action").toString();
        RequestDispatcher rd = null;
        
        switch(action){
            case "lista":
                // <editor-fold defaultstate="collapsed" desc="Lista">
                try{
                
                    List<RecursoHumanoDTO> objResult = this.getRrhhModel().GetListaRecursosHumanos();

                    if(objResult != null){
                        rd = request.getRequestDispatcher("paginas/rrhh/list.jsp");
                        request.setAttribute("rrhhListObj", objResult);
                    }
                    else{
                        rd = request.getRequestDispatcher("paginas/rrhh/list.jsp");
                        request.setAttribute("rrhhListObj", objResult);
                        request.setAttribute("error", "No existen datos para mostrar!");
                    }
                }
                catch(RemoteException ex){
                     rd = request.getRequestDispatcher("paginas/rrhh//list.jsp");
                     request.setAttribute("rrhhListObj", null);
                     request.setAttribute("error", "Ha ocurrido un error en el servidor. Disculpe las molestias :( ");

                    logger.log(Level.WARNING, 
                            String.format("%s %s %s", 
                                    CPREFIX, 
                                    MPREFIX, 
                                    "=> Error al intentar validar el usuario. Detalle: " + ex.getMessage()));
                }
                catch(Exception ex){
                     rd = request.getRequestDispatcher("paginas/rrhh//list.jsp");
                      request.setAttribute("rrhhListObj", null);
                     request.setAttribute("error", "Ha ocurrido un error durante la solicitud. Por favor, intentelo nuevamente");

                    logger.log(Level.WARNING, 
                            String.format("%s %s %s", 
                                    CPREFIX, 
                                    MPREFIX, 
                                    "=> Error al intentar validar el usuario. Detalle: " + ex.getMessage()));
                }

                rd.forward(request, response);
                //</editor-fold>              
                break;
                
            case "nuevo":
                // <editor-fold defaultstate="collapsed" desc="Nuevo">
                try{
                    rd = request.getRequestDispatcher("paginas/rrhh/nuevo.jsp");
                    request.setAttribute("error", null);
                }
                catch(Exception ex){
                     rd = request.getRequestDispatcher("paginas/home.jsp");
                     request.setAttribute("error", "Ha ocurrido un error durante la solicitud. Por favor, intentelo nuevamente");

                    logger.log(Level.WARNING, 
                            String.format("%s %s %s", 
                                    CPREFIX, 
                                    MPREFIX, 
                                    "=> Error al intentar validar el usuario. Detalle: " + ex.getMessage()));
                }

                rd.forward(request, response);
                // </editor-fold>
                break;
            
            case "guardarNuevo":
                // <editor-fold defaultstate="collapsed" desc="Guardar Nuevo">
                // </editor-fold>
                break;
                
            case "editar":
                // <editor-fold defaultstate="collapsed" desc="Editar">
                // </editor-fold>
                break;
                
            case "eliminar":
                // <editor-fold defaultstate="collapsed" desc="Eliminar">
                // </editor-fold>
                break;
                
            case "buscar":
                // <editor-fold defaultstate="collapsed" desc="Buscar">
                try{
                
                    RecursoHumanoDTO objFiltro = new RecursoHumanoDTO();
                    
                    if(!request.getParameter("nombre").equals("")){
                        objFiltro.SetNombrePersona(request.getParameter("nombre"));
                    }
                    if(!request.getParameter("depto").equals("")){
                        objFiltro.SetDepartamento(request.getParameter("depto"));
                    }
                    if(!request.getParameter("direccion").equals("")){
                        objFiltro.SetDireccion(request.getParameter("direccion"));
                    }
                    if(!request.getParameter("region").equals("")){
                        objFiltro.SetRegion(request.getParameter("region"));
                    }
                    if(!request.getParameter("comuna").equals("")){
                        objFiltro.SetComuna(request.getParameter("comuna"));
                    }
                    
                    List<RecursoHumanoDTO> objResult = this.getRrhhModel().GetListaRecursosHumanosByFiltro(objFiltro);

                    if(objResult != null){
                        rd = request.getRequestDispatcher("paginas/rrhh/list.jsp");
                        request.setAttribute("rrhhListObj", objResult);
                    }
                    else{
                        rd = request.getRequestDispatcher("paginas/rrhh/list.jsp");
                        request.setAttribute("rrhhListObj", objResult);
                        request.setAttribute("error", "No existen datos para mostrar!");
                    }
                }
                catch(RemoteException ex){
                     rd = request.getRequestDispatcher("paginas/rrhh//list.jsp");
                     request.setAttribute("rrhhListObj", null);
                     request.setAttribute("error", "Ha ocurrido un error en el servidor. Disculpe las molestias :( ");

                    logger.log(Level.WARNING, 
                            String.format("%s %s %s", 
                                    CPREFIX, 
                                    MPREFIX, 
                                    "=> Error al intentar validar el usuario. Detalle: " + ex.getMessage()));
                }
                catch(Exception ex){
                     rd = request.getRequestDispatcher("paginas/rrhh//list.jsp");
                     request.setAttribute("rrhhListObj", null);
                     request.setAttribute("error", "Ha ocurrido un error durante la solicitud. Por favor, intentelo nuevamente");

                    logger.log(Level.WARNING, 
                            String.format("%s %s %s", 
                                    CPREFIX, 
                                    MPREFIX, 
                                    "=> Error al intentar validar el usuario. Detalle: " + ex.getMessage()));
                }

                rd.forward(request, response);
                // </editor-fold>
                break;
                
            default:
                break;
        }    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos privados">
    private RrhhModel getRrhhModel(){
        if(this.rrhhModel == null){
            this.rrhhModel = RrhhModel.GetInstance();
        }
        
        return this.rrhhModel;
    }
    // </editor-fold>
}
