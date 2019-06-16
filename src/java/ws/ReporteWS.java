/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import beans.Reporte;
import beans.ReporteDictamen;
import beans.ReporteResumido;
import beans.Respuesta;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.dao.ReporteDAO;

/**
 * REST Web Service
 *
 * @author Juan Carlos
 */
@Path("reportes")
public class ReporteWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReporteWS
     */
    public ReporteWS() {
    }

    @POST
    @Path("nuevoReporte")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta agregarReporte(
            @FormParam ("descripcion") String descripcion,
            @FormParam ("idConductor") String idConductorString,
            @FormParam ("latitud") String latitud,
            @FormParam ("longitud") String longitud,
            @FormParam ("placasVehiculos") String placasVehiculos,
            @FormParam ("hora") String hora
    ){
        Respuesta res = new Respuesta();
        int idConductor = Integer.parseInt(idConductorString);
        Reporte reporte = new Reporte();
        reporte.setDescripcion(descripcion);
        reporte.setIdConductor(idConductor);
        reporte.setLatitud(latitud);
        reporte.setLongitud(longitud);
        reporte.setPlacasVehiculos(placasVehiculos);
        reporte.setEstado(1);
        int fa = ReporteDAO.agregarReporte(reporte);
        if (fa > 0) {
            res.setError(false);
            res.setErrorcode(0);
            res.setMensaje("Reporte enviado exitosamente");
        } else {
            switch (fa) {
                case -1:
                    res.setError(true);
                    res.setErrorcode(1);
                    res.setMensaje("Error al levantar reporte, datos no válidos");
                    break;
                case -2:
                    res.setError(true);
                    res.setErrorcode(3);
                    res.setMensaje("Error de conexión");
                    break;
            }
        }
        return res;
    }
    
    @POST
    @Path("getReportes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReporteResumido> getReportes(
            @FormParam("idConductor") String idConductorString
    ){
        List<ReporteResumido> list = new ArrayList<>();
        int idConductor = Integer.parseInt(idConductorString);
        list = ReporteDAO.getReportes(idConductor);
        return list;
    }
    
    @POST
    @Path("obtenerDetalleReporte")
    @Produces(MediaType.APPLICATION_JSON)
    public ReporteDictamen getDetallesReporte(
            @FormParam("idReporte") String idReporte
    ){
        ReporteDictamen reporte = new ReporteDictamen();
        reporte = ReporteDAO.getDetallesReporte(Integer.parseInt(idReporte));
        return reporte;
    }
}
