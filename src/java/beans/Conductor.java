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
public class Conductor {

    private Integer idConductor;
    private Integer idEstatus;
    private String nombre;
    private String fechaNacimiento;
    private String numLicencia;
    private String telefono;
    private String password;
    private String tokenAcceso;
    
    public Conductor() {
    }

    public Conductor(Integer idConductor, String nombre, String fechaNacimiento, String numLicencia, String telefono, String password) {
        this.idConductor = idConductor;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.numLicencia = numLicencia;
        this.telefono = telefono;
        this.password = password;
    }

    public Conductor(String nombre, String fechaNacimiento, String numLicencia, String telefono, String password) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.numLicencia = numLicencia;
        this.telefono = telefono;
        this.password = password;
    }

    /**
     * @return the idConductor
     */
    public Integer getIdConductor() {
        return idConductor;
    }

    /**
     *
     * @return the idEstatus
     */
    public Integer getIdEstatus() {
        return this.idEstatus;
    }

    /**
     *
     * @param idEstatus the idEstatus to set
     */
    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
     * @param idConductor the idConductor to set
     */
    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the numLicencia
     */
    public String getNumLicencia() {
        return numLicencia;
    }

    /**
     * @param numLicencia the numLicencia to set
     */
    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * 
     * @return the tokenAcceso
     */
    public String getTokenAcceso(){
        return this.tokenAcceso;
    }
    
    /**
     * 
     * @param tokenAcceso the tokenAcceso to set
     */
    public void setTokenAcceso(String tokenAcceso){
        this.tokenAcceso = tokenAcceso;
    }

}
