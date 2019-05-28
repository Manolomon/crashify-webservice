/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import beans.Conductor;
import beans.Respuesta;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.dao.ConductorDAO;

/**
 * REST Web Service
 *
 * @author manolo
 * @author Juan Carlos
 */
@Path("usuario")
public class ConductorWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ConductorWS
     */
    public ConductorWS() {
    }

    @POST
    @Path("registrarConductor")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarUsuario(
            @FormParam("nombre") String nombre,
            @FormParam("telefono") String telefono,
            @FormParam("password") String password,
            @FormParam("fechaNacimiento") String fechaNacimiento,
            @FormParam("numeroLicencia") String numeroLicencia
    ) {
        Respuesta res = new Respuesta();
        Conductor conductor = new Conductor(nombre, fechaNacimiento,
                numeroLicencia, telefono, password);
        int fa = ConductorDAO.registrarConductor(conductor);
        if (fa > 0) {
            res.setError(false);
            res.setErrorcode(0);
            res.setMensaje("Conductor registrado exitosamente");
        } else {
            switch (fa) {
                case -1:
                    res.setError(true);
                    res.setErrorcode(1);
                    res.setMensaje("Error al registrar conductor, datos no válidos");
                    break;
                case -2:
                    res.setError(true);
                    res.setErrorcode(2);
                    res.setMensaje("Error al registrar conductor, ese numero "
                            + "ya está registrado");
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

    /**
     * Retrieves representation of an instance of ws.UsuarioWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UsuarioWS
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
