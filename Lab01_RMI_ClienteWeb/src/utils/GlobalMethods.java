/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;

/**
 *
 * @author aialiagam
 */
public class GlobalMethods {
    // <editor-fold defaultstate="collapsed" desc="metodos publicos">
    
    // <editor-fold defaultstate="collapsed" desc="HashPassword">
    public static String HashPassword(String p_Pass){
        String resultPass = "";
        
        try{
            // Obtenemos el password y le agregamos la "salt"
            String completePass = conf.Parameters.PARAM_PASS_SALT + p_Pass;
            
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(completePass.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            resultPass = hexString.toString();
        }
        catch(Exception ex){
            resultPass = p_Pass;
        }
        
        return resultPass;
    }
    // </editor-fold>
    
    // </editor-fold>
}
