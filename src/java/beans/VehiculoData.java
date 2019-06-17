/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;

/**
 *
 * @author Juan Carlos
 */
public class VehiculoData {

    private String numPlacas;
    private Integer idMarca;
    private String modelo;
    private String color;
    private String year;
    private String numPoliza;
    private Integer idAseguradora;
    private Date fechaVencimiento;
    private Integer fa;
    
    public VehiculoData(){
        
    }
    
    /**
     * @return the numPlacas
     */
    public String getNumPlacas() {
        return numPlacas;
    }

    /**
     * @param numPlacas the numPlacas to set
     */
    public void setNumPlacas(String numPlacas) {
        this.numPlacas = numPlacas;
    }

    /**
     * @return the idMarca
     */
    public Integer getIdMarca() {
        return idMarca;
    }

    /**
     * @param idMarca the idMarca to set
     */
    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the numPoliza
     */
    public String getNumPoliza() {
        return numPoliza;
    }

    /**
     * @param numPoliza the numPoliza to set
     */
    public void setNumPoliza(String numPoliza) {
        this.numPoliza = numPoliza;
    }

    /**
     * @return the idAseguradora
     */
    public int getIdAseguradora() {
        return idAseguradora;
    }

    /**
     * @param idAseguradora the idAseguradora to set
     */
    public void setIdAseguradora(int idAseguradora) {
        this.idAseguradora = idAseguradora;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the fa
     */
    public Integer getFa() {
        return fa;
    }

    /**
     * @param fa the fa to set
     */
    public void setFa(Integer fa) {
        this.fa = fa;
    }
    
    
}
