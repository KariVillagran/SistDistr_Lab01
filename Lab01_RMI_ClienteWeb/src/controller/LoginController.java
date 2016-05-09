/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.UsuarioDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    private LoginModel loginModel;
    private static final long serialVersionUID = 1L;
 
    public LoginController() {
        super();
        loginModel = LoginModel.GetInstance();
    }
    
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        try{
            // Instanciamos el model
            LoginModel model = LoginModel.GetInstance();

            UsuarioDTO objRequest = new UsuarioDTO();
            objRequest.SetUserName(username);
            objRequest.SetPassword(password);


            UsuarioDTO objResult = model.ValidarUsuario(objRequest);

            if(objResult != null){
                //rd = request.getRequestDispatcher("/success.jsp");
                //User user = new User(username, password);
                //request.setAttribute("user", user);
            }
            else{
                
            }
        }
        catch(Exception ex){

        }
//            Authenticator authenticator = new Authenticator();
//            String result = authenticator.authenticate(username, password);
//            if (result.equals("success")) {
//                    rd = request.getRequestDispatcher("/success.jsp");
//                    User user = new User(username, password);
//                    request.setAttribute("user", user);
//            } else {
//                    rd = request.getRequestDispatcher("/error.jsp");
//            }
        rd.forward(request, response);
    }
}
