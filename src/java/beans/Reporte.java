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
public class Reporte {

    private Integer idReporte;
    private String latitud;
    private String longitud;
    private Integer estado;
    private Integer idConductor;
    private String dictamen;
    private String descripcion;
    private String placasVehiculos;
    private Integer idSiniestro;
    private String hora;

    public Reporte() {
    }

    public Reporte(String latitud, String longitud, int estado) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }

    public Reporte(Integer idReporte, String latitud, String longitud, int estado) {
        this.idReporte = idReporte;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }

    /**
     * @return the idReporte
     */
    public Integer getIdReporte() {
        return idReporte;
    }

    /**
     * @param idReporte the idReporte to set
     */
    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdConductor() {
        return this.idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    /**
     * @return the dictamen
     */
    public String getDictamen() {
        return dictamen;
    }

    /**
     * @param dictamen the dictamen to set
     */
    public void setDictamen(String dictamen) {
        this.dictamen = dictamen;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the dictamen to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * @return the placasVehiculos
     */
    public String getPlacasVehiculos() {
        return placasVehiculos;
    }

    /**
     * @param placasVehiculos the placasVehiculos to set
     */
    public void setPlacasVehiculos(String placasVehiculos) {
        this.placasVehiculos = placasVehiculos;
    }
    
        /**
     * @return the idSiniestro
     */
    public Integer getIdSiniestro() {
        return idSiniestro;
    }

    /**
     * @param idSiniestro the idSiniestro to set
     */
    public void setIdSiniestro(Integer idSiniestro) {
        this.idSiniestro = idSiniestro;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

}
