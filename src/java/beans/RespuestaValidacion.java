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
public class RespuestaValidacion {

    private Respuesta error;
    private Conductor conductor;
    
    public RespuestaValidacion(){
        
    }

    /**
     * @return the error
     */
    public Respuesta getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(Respuesta error) {
        this.error = error;
    }

    /**
     * @return the usuario
     */
    public Conductor getConductor() {
        return conductor;
    }

    /**
     * @param conductor the usuario to set
     */
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

}
