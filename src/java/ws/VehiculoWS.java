/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import beans.Respuesta;
import beans.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.dao.VehiculoDAO;

/**
 * REST Web Service
 *
 * @author Juan Carlos
 */
@Path("vehiculos")
public class VehiculoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VehiculoWS
     */
    public VehiculoWS() {
    }

    @POST
    @Path("registrarVehiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarVehiculo(
            @FormParam("numPlacas") String numPlacas,
            @FormParam("idMarca") String idMarca,
            @FormParam("modelo") String modelo,
            @FormParam("color") String color,
            @FormParam("año") String year,
            @FormParam("numPoliza") String numPoliza
    ) {
        Respuesta res = new Respuesta();
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setColor(color);
        vehiculo.setNumPoliza(numPoliza);
        vehiculo.setIdMarca(Integer.parseInt(idMarca));
        vehiculo.setModelo(modelo);
        vehiculo.setNumPlacas(numPlacas);
        vehiculo.setYear(year);
        int fa = VehiculoDAO.registrarVehiculo(vehiculo);
        if (fa > 0) {
            res.setError(false);
            res.setErrorcode(0);
            res.setMensaje("Vehículo registrado exitosamente");
        } else {
            switch (fa) {
                case -1:
                    res.setError(true);
                    res.setErrorcode(1);
                    res.setMensaje("Error al registrar Vehículo, datos no válidos");
                    break;
                case -2:
                    res.setError(true);
                    res.setErrorcode(2);
                    res.setMensaje("Error al registrar Vehículo,"
                            + " ya hay un vehiculo registrado con las mismas placas");
                    break;
                default:
                    res.setError(true);
                    res.setErrorcode(3);
                    res.setMensaje("Error de conexión");
                    break;
            }
        }
        return res;
    }
    
    @POST
    @Path("getVehiculos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehiculo> getVehiculos(
            @FormParam("idConductor") String idConductorString
    ){
        List<Vehiculo> list = new ArrayList<>();
        int idConductor = Integer.parseInt(idConductorString);
        return VehiculoDAO.getVehiculos(idConductor);
    }
    
    @POST
    @Path("editarVehiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta editarVehiculo(
            @FormParam("idVehiculo") String idVehiculoString,
            @FormParam("numPlacas") String numPlacas,
            @FormParam("marca") String idMarca,
            @FormParam("modelo") String modelo,
            @FormParam("color") String color,
            @FormParam("año") String year,
            @FormParam("numPoliza") String numPoliza
    ){
        Respuesta res = new Respuesta();
        int idVehiculo = Integer.parseInt(idVehiculoString);
        Vehiculo vehiculo = new Vehiculo(idVehiculo, numPlacas, modelo, Integer.parseInt(idMarca), year, color, numPoliza);
        int fa = VehiculoDAO.editarVehiculo(vehiculo);
        if (fa > 0) {
            res.setError(false);
            res.setErrorcode(0);
            res.setMensaje("Vehículo actualizado exitosamente");
        } else {
            switch (fa) {
                case -1:
                    res.setError(true);
                    res.setErrorcode(1);
                    res.setMensaje("Error al actualizar Vehículo, datos no válidos");
                    break;
                case -2:
                    res.setError(true);
                    res.setErrorcode(2);
                    res.setMensaje("Error al registrar Vehículo,"
                            + " ya hay un vehiculo registrado con las mismas placas");
                    break;
                default:
                    res.setError(true);
                    res.setErrorcode(3);
                    res.setMensaje("Error de conexión");
                    break;
            }
        }
        return res;
    }
    
    @POST
    @Path("getVehiculosReporte")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehiculo> getVehiculosReporte(
            @FormParam("idReporte") String idReporteString
    ){
        List<Vehiculo> list = new ArrayList<>();
        int idReporte = Integer.parseInt(idReporteString);
        return VehiculoDAO.getVehiculos(idReporte);
    }
}
