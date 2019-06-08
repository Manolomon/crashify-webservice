/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import beans.Aseguradora;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import model.dao.AseguradoraDAO;

/**
 * REST Web Service
 *
 * @author benji
 */
@Path("aseguradoras")
public class AseguradoraWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AseguradoraWS
     */
    public AseguradoraWS() {
    }

    @POST
    @Path("getAseguradoras")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aseguradora> getAseguradoras(){
        List<Aseguradora> list = new ArrayList<>();
        list = AseguradoraDAO.getAseguradoras();
        return list;
    }
    
    
    @POST
    @Path("getAseguradora")
    @Produces(MediaType.APPLICATION_JSON)
    public Aseguradora getAseguradora(
            @FormParam("idAseguradora") String idAseguradora
    ){
        Aseguradora aseguradora = new Aseguradora();
        aseguradora = AseguradoraDAO.getAseguradora(
                Integer.parseInt(idAseguradora)
        );
        return aseguradora;
    }
}
