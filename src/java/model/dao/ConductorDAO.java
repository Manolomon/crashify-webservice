/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.Conductor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import model.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author benji
 */
public class ConductorDAO {

    private static final Integer TOKEN_LENGTH = 4;

    /**
     * Método para registrar el conductor en la base de datos
     *
     * @param conductor el conductor a registrar
     * @return Integer: el numero de filas afectadas o un código de error
     */
    public static int registrarConductor(Conductor conductor) {
        int res = 0;
        SqlSession conn = null;
        if (conductor.getNombre() == null
                || conductor.getNombre().toString().trim().isEmpty()) {
            return -1;
        }
        if (conductor.getTelefono().length() > 10) {
            return -1;
        }

        if (conductor.getFechaNacimiento() == null
                || conductor.getFechaNacimiento().toString()
                        .trim().isEmpty()) {
            return -1;
        }

        if (conductor.getNumLicencia() == null
                || conductor.getNumLicencia().toString()
                        .trim().isEmpty()) {
            return -1;
        }

        if (buscarTelefono(conductor)) {
            return -2;
        }
        String token = "";
        for (int x = 0; x < TOKEN_LENGTH; x++) {
            token += generarParteToken();
        }
        conductor.setTokenAcceso(token);
        conductor.setIdEstatus(1);
        try{
            conn = MyBatisUtils.getSession();
            res = conn.insert("Conductor.registrar",conductor);
            conn.commit();
            /*if(res > 0){
                String mensaje = "Hola " + conductor.getNombre() + 
                        " \n Tu token de acceso es: " + conductor.getTokenAcceso();
                jax.enviar(usuario.getTelefono(), mensaje);
            }*/
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if (conn !=null){
                conn.close();
            }
        }
        return res;
    }

    /**
     * Método para buscar si el telefono de un conductor ya fue registrado
     *
     * @param conductor el conductor dueño del telefono
     * @return Boolean true si ya esta registrado, false en caso contrario
     */
    public static boolean buscarTelefono(Conductor conductor) {
        String telefono = conductor.getTelefono();
        boolean respuesta = false;
        List<Conductor> list = new ArrayList<Conductor>();
        if (telefono != null && !telefono.isEmpty()) {
            SqlSession conn = null;
            try {
                conn = MyBatisUtils.getSession();
                list = conn.selectList("Conductor.buscarTelefono", telefono);
                if (!list.isEmpty()) {
                    Integer idUsuario = list.get(0).getIdConductor();
                    Integer idUsuarioAux = conductor.getIdConductor();
                    if (!Objects.equals(idUsuario, idUsuarioAux)) {
                        respuesta = true;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return respuesta;
    }

    /**
     * Método para generar de manera aleatoria una parte del token de acceso
     *
     * @return Una parte del token de acceso.
     */
    private static String generarParteToken() {
        Integer parte = 0;
        Random rg = new Random();
        parte = rg.nextInt(9);
        return parte.toString();
    }
}
