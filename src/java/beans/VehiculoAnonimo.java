package beans;

/**
 *
 * @author Daniel Escamilla
 */
public class VehiculoAnonimo {
    private Integer idVehiculo;
    private String numPlacas;
    private String modelo;
    private Integer idMarca;
    private String year;
    private String color;
    private String numPoliza;
    private Integer idAseguradora;

    public VehiculoAnonimo(Integer idVehiculo, String numPlacas, String modelo, Integer idMarca, String year, String color, String numPoliza, Integer idAseguradora) {
        this.idVehiculo = idVehiculo;
        this.numPlacas = numPlacas;
        this.modelo = modelo;
        this.idMarca = idMarca;
        this.year = year;
        this.color = color;
        this.numPoliza = numPoliza;
        this.idAseguradora = idAseguradora;
    }

    public VehiculoAnonimo() {
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNumPlacas() {
        return numPlacas;
    }

    public void setNumPlacas(String numPlacas) {
        this.numPlacas = numPlacas;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumPoliza() {
        return numPoliza;
    }

    public void setNumPoliza(String numPoliza) {
        this.numPoliza = numPoliza;
    }

    public Integer getIdAseguradora() {
        return idAseguradora;
    }

    public void setIdAseguradora(Integer idAseguradora) {
        this.idAseguradora = idAseguradora;
    }
    
    
}
