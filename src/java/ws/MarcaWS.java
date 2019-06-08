/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import beans.Marca;
import java.util.ArrayList;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.dao.MarcaDAO;

/**
 * REST Web Service
 *
 * @author Juan Carlos
 */
@Path("marcas")
public class MarcaWS { 

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MarcaWS
     */
    public MarcaWS() {
    }

    
    @POST
    @Path("getMarcas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Marca> getMarcas(){
        List<Marca> list = new ArrayList<>();
        list = MarcaDAO.getMarcas();
        return list;
    }
    
    @POST
    @Path("getMarca")
    @Produces(MediaType.APPLICATION_JSON)
    public Marca getMarca(
            @FormParam ("idMarca") String idMarca
    ){
        Marca marca = new Marca();
        marca = MarcaDAO.getMarca(Integer.parseInt(idMarca));
        return marca;
    }
}
