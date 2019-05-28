/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import beans.Evidencia;
import beans.Respuesta;
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
import model.dao.EvidenciaDAO;

/**
 * REST Web Service
 *
 * @author Juan Carlos
 */
@Path("evidencias")
public class EvidenciaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EvidenciaWS
     */
    public EvidenciaWS() {
    }

    @POST
    @Path("agregarEvidencia")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta agregarEvidencia(
            @FormParam("idReporte") String idReporteString,
            @FormParam("evidencia") byte[] bytes
    ){
        Respuesta res = new Respuesta();
        int idReporte = Integer.parseInt(idReporteString);
        Evidencia evidencia = new Evidencia();
        evidencia.setIdReporte(idReporte);
        evidencia.setBytes(bytes);
        
        int fa = EvidenciaDAO.agregarEvidencia(evidencia);
        if (fa > 0) {
            res.setError(false);
            res.setErrorcode(0);
            res.setMensaje("Evidencia enviada exitosamente");
        } else {
            switch (fa) {
                case -1:
                    res.setError(true);
                    res.setErrorcode(1);
                    res.setMensaje("Error al enviar evidencia, datos no válidos");
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
    @Path("getEvidencias")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evidencia> getEvidencias(
            @FormParam("idReporte") String idReporteString
    ){
        List<Evidencia> list = new ArrayList<>();
        int idReporte = Integer.parseInt(idReporteString);
        list = EvidenciaDAO.getEvidencias(idReporte);
        return list;
    }
}
