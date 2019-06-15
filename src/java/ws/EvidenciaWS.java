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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Path("/subirImagen/{idReporte}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Respuesta subirImagen(
            @PathParam("idReporte") Integer idReporte, byte[] bytes
    ){
        Respuesta res = new Respuesta();
        int fa = 0;
        try{
            fa = EvidenciaDAO.agregarImagen(bytes, idReporte);
            if (fa > 0){
                res.setError(false);
                res.setErrorcode(200);
                res.setMensaje("Imagen agregada exitosamente");
            }else{
                res.setError(true);
                res.setErrorcode(404);
                res.setMensaje("Error al guardar datos");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje(ex.getMessage());
            res.setErrorcode(405);
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
