/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author alejandro
 */
public class UsuarioDTO {
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // **
    // propiedades privadas
    private int id;
    private String userName;
    private String password;
    private String rol;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    //**
    // constructores
    public UsuarioDTO(){
        this.id = 0;
        this.userName = null;
        this.password = null;
        this.rol = null;
    }
    // </editor-fold>
   
    // <editor-fold defaultstate="collapsed" desc="metodos accesores">
    // ** 
    // metodos accesores
    public int GetId(){
        return this.id;
    }
            
    public String GetUserName(){
        return this.userName;
    }
    
    public String GetPassword(){
        return this.password;
    }
    
    public String GetRol(){
        return this.rol;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos mutadores">
    // **
    // metodos mutadores
    public void SetId(int id){
        this.id = id;
    }
    
    public void SetUserName(String userName){
        this.userName = userName;
    }
    
    public void SetPassword(String password){
        this.password = password;
    }
    
    public void SetRol(String rol){
        this.rol = rol;
    }
    // </editor-fold>
}
