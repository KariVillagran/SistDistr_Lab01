/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.UsuarioDTO;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.LoginModel;

/**
 *
 * @author aialiagam
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    private static String CPREFIX = "Lab01_RMI_ClienteWeb.src.controller.LoginController";
    private LoginModel loginModel;
    private static final long serialVersionUID = 1L;
    private static Logger logger;
    // </editor-fold>
 
    // <editor-fold defaultstate="collapsed" desc="constructores">
    public LoginController() {
        super();        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="eventos">
    
    // <editor-fold defaultstate="collapsed" desc="doPost">
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String MPREFIX = " [doPost(HttpServletRequest request, HttpServletResponse response)]";
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        try{
            // Creamos un nuevo objeto tipo UsuarioDTO
            UsuarioDTO objRequest = new UsuarioDTO();
            objRequest.SetUserName(username);
            // Aplicamos "hashing" + salt al password
            objRequest.SetPassword(utils.GlobalMethods.HashPassword(password));
            // Llamamos al metodo del model para validar el Login 
            UsuarioDTO objResult = this.getLoginModel().ValidarUsuario(objRequest);

            if(objResult != null){
                rd = request.getRequestDispatcher("home.jsp");
                //Cookie loginCookie = new Cookie("currentUser", objResult.GetUserName());
                //loginCookie.setMaxAge(conf.Parameters.PARAM_COOKIE_TIMEOUT * 60);
                request.setAttribute("user", objResult);
            }
            else{
                rd = request.getRequestDispatcher("login.jsp");
                request.setAttribute("error", "Usuario y/o Contraseña incorrectos!");
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
                
            logger.log(Level.WARNING, 
                    String.format("%s %s %s", 
                            CPREFIX, 
                            MPREFIX, 
                            "=> Error al intentar validar el usuario. Detalle: " + ex.getMessage()));
        }
        
        rd.forward(request, response);
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos privados">
    private LoginModel getLoginModel(){
        if(this.loginModel == null){
            this.loginModel = LoginModel.GetInstance();
        }
        
        return this.loginModel;
    }
    // </editor-fold>
}
