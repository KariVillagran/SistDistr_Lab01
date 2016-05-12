/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author alejandro
 */
public class FinanzaDTO implements  Serializable{
    // <editor-fold defaultstate="collapsed" desc="propiedades privadas">
    // ***
    // propiedades privadas
    private int id;
    private String nombrePersonal;
    private Date fchMovimiento;
    private Double saldoMovimiento;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="constructores">
    // ***
    // constructores
    public FinanzaDTO(){
        this.id = 0;
        this.nombrePersonal = null;
        this.fchMovimiento = null;
        this.saldoMovimiento = null;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos accesores">
    // ***
    // metodos accesores
    public int GetId(){
        return this.id;
    }
    
    public String GetNombrePersonal(){
        return this.nombrePersonal;
    }
    
    public Date GetFchMovimiento(){
        return this.fchMovimiento;
    }
    
    public Double GetSaldoMovimiento(){
        return this.saldoMovimiento;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="metodos mutadores">
    // ***
    // metodos mutadores
    public void SetId(int id){
        this.id = id;
    }
    
    public void SetNombrePersonal(String nombrePersonal){
        this.nombrePersonal = nombrePersonal;
    }
    
    public void SetFchMovimiento(Date fchMovimiento){
        this.fchMovimiento = fchMovimiento;
    }
    
    public void SetSaldoMovimiento(Double saldoMovimiento){
        this.saldoMovimiento = saldoMovimiento;
    }
    // </editor-fold>
}
