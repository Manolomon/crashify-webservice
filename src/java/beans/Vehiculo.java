/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Juan Carlos
 */
public class Vehiculo {

    private Integer idVehiculo;
    private String numPlacas;
    private String modelo;
    private String marca;
    private String year;
    private String color;
    private Integer idConductor;

    public Vehiculo() {
    }

    public Vehiculo(Integer idVehiculo, String numPlacas, String modelo, String marca, String year, String color, Integer idConductor) {
        this.idVehiculo = idVehiculo;
        this.numPlacas = numPlacas;
        this.modelo = modelo;
        this.marca = marca;
        this.year = year;
        this.color = color;
        this.idConductor = idConductor;
    }
    
    
    /**
     * @return the idVehiculo
     */
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * @param idVehiculo the idVehiculo to set
     */
    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
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
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
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
     * @return the idConductor
     */
    public int getIdConductor() {
        return idConductor;
    }

    /**
     * @param idConductor the idConductor to set
     */
    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }
    
    
    
}
