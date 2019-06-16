/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.Conductor;
import beans.Respuesta;
import beans.RespuestaValidacion;
import gateway.sms.JaxSms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import model.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juan Carlos
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
        JaxSms jax = new JaxSms();
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
        conductor.setEstatus(1);
        try {
            conn = MyBatisUtils.getSession();
            res = conn.insert("Conductor.registrar", conductor);
            conn.commit();
            if(res > 0){
                String mensaje = "Tu token para Crashify es: " + conductor.getTokenAcceso();
                jax.enviar(conductor.getTelefono(), mensaje);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return res;
    }

    /**
     * Método para validar un conductor asociado a un teléfono con un token de
     * acceso especifico
     *
     * @param telefono le telefono a validar
     * @param token el token de acceso ingresado por el conductor
     * @return Respuesta Validación: Error si no se ha podido validar o el
     * conductor validado
     */
    public static RespuestaValidacion autenticarConductor(String telefono, String token) {
        RespuestaValidacion respuestaValidacion = new RespuestaValidacion();
        Respuesta error = new Respuesta();
        Conductor conductor;
        int filas = 0;
        if (telefono == null || telefono.isEmpty()) {
            error.setError(true);
            error.setErrorcode(1);
            error.setMensaje("Ingrese telefono");
            respuestaValidacion.setError(error);
        } else if (token == null || token.isEmpty()) {
            error.setError(true);
            error.setErrorcode(2);
            error.setMensaje("Ingrese token");
            respuestaValidacion.setError(error);
        } else if (!verificarTelefono(telefono)) {
            error.setError(true);
            error.setErrorcode(3);
            error.setMensaje("Error de registro, telefono ya validado");
            respuestaValidacion.setError(error);
        } else {
            conductor = buscarConductor(telefono);
            if (conductor.getIdConductor() != 0) {
                SqlSession conn = null;
                try {
                    conn = MyBatisUtils.getSession();
                    filas = conn.update("Conductor.validarConductor", conductor);
                    conn.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.close();
                    }
                }
            }
            if (filas > 0) {
                conductor = ConductorDAO.buscarConductor(telefono);
                respuestaValidacion.setConductor(conductor);
            } else {
                error.setError(true);
                error.setErrorcode(4);
                error.setMensaje("Error al validar conductor");
            }
        }
        return respuestaValidacion;
    }

    /**
     * Método para que el conductor pueda iniciar autenticarse en el sistema
     * tomando como referencia la información almacenada en la base de datos
     *
     * @param telefono
     * @param password
     * @return
     */
    public static RespuestaValidacion iniciarSesion(String telefono, String password) {
        RespuestaValidacion respuesta = new RespuestaValidacion();
        Respuesta error = new Respuesta();
        Conductor conductor = new Conductor();
        int filas = 0;
        if (telefono == null || telefono.isEmpty()) {
            error.setError(true);
            error.setErrorcode(1);
            error.setMensaje("Ingrese telefono");
            respuesta.setError(error);
        } else if (password == null || password.isEmpty()) {
            error.setError(true);
            error.setErrorcode(2);
            error.setMensaje("Ingrese token");
            respuesta.setError(error);
        } else if (buscarConductor(telefono).getIdConductor() == 0) {
            error.setError(true);
            error.setErrorcode(3);
            error.setMensaje("Error de registro, no existe un conductor con ese telefono");
            respuesta.setError(error);
        } else {
            SqlSession conn = null;
            try {
                HashMap<String, Object> param = new HashMap<String, Object>();
                param.put("telefono", telefono);
                param.put("password", password);
                conn = MyBatisUtils.getSession();
                conductor = conn.selectOne("Conductor.iniciarSesion", param);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }

            if (conductor.getIdConductor() != 0) {
                respuesta.setConductor(conductor);
            } else {
                error.setError(true);
                error.setErrorcode(5);
                error.setMensaje("Error al cargar conductor");
                respuesta.setError(error);
            }
        }
        return respuesta;
    }

    /**
     * Método para buscar si el telefono de un conductor ya fue registrado
     *
     * @param conductor el conductor dueño del telefono
     * @return Boolean: true si ya esta registrado, false en caso contrario
     */
    private static boolean buscarTelefono(Conductor conductor) {
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
     * @return String: Una parte del token de acceso.
     */
    private static String generarParteToken() {
        Integer parte = 0;
        Random rg = new Random();
        parte = rg.nextInt(9);
        return parte.toString();
    }

    /**
     * Método para verificar si un telefono ya ha sido validado antes
     *
     * @param telefono el telefono a validar
     * @return Boolean: true si aun no ha sido registrado
     */
    private static boolean verificarTelefono(String telefono) {
        List<Conductor> list = new ArrayList<>();
        if (telefono != null && !telefono.isEmpty()) {
            SqlSession conn = null;
            try {
                conn = MyBatisUtils.getSession();
                list = conn.selectList("Conductor.buscarTelefonoNoValidado", telefono);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return !list.isEmpty();
    }

    /**
     * Método para obtener el conductor asociado al teléfono registrado
     *
     * @param telefono el teléfono a comparar
     * @return Conductor: el conductor encontrado, null en caso contrario
     */
    private static Conductor buscarConductor(String telefono) {
        Conductor conductor = new Conductor();
        if (telefono != null && !telefono.isEmpty()) {
            SqlSession conn = null;
            try {
                conn = MyBatisUtils.getSession();
                conductor = conn.selectOne("Conductor.buscarTelefono", telefono);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return conductor;
    }
}
