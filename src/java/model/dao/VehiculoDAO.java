/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.InfoVehiculo;
import beans.Respuesta;
import beans.Vehiculo;
import beans.VehiculoAnonimo;
import beans.VehiculoData;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import model.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juan Carlos
 */
public class VehiculoDAO {

    public static int registrarVehiculo(Vehiculo vehiculo, Date fechaVencimiento, int idConductor) {
        int fa = 0;
        SqlSession conn = null;
        fa = validarVehiculo(vehiculo);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if (fa != 0) {
            return fa;
        }
        if (buscarVehiculo(vehiculo)) {
            return -2;
        }
        try {
            
            VehiculoData data = new VehiculoData();
            data.setNumPlacas(vehiculo.getNumPlacas());
            data.setIdMarca(vehiculo.getIdMarca());
            data.setModelo(vehiculo.getModelo());
            data.setColor(vehiculo.getColor());
            data.setYear(vehiculo.getYear());
            data.setNumPoliza(vehiculo.getNumPoliza());
            data.setIdAseguradora(vehiculo.getIdAseguradora());
            data.setFechaVencimiento(fechaVencimiento);
            data.setFa(0);
            map.put("numPlacas", data.getNumPlacas().toString());
            map.put("idMarca", data.getIdMarca());
            map.put("modelo", data.getModelo());
            map.put("color", data.getColor());
            map.put("year", data.getYear());
            map.put("numPoliza", data.getNumPoliza());
            map.put("idAseguradora", data.getIdAseguradora());
            map.put("fechaVencimiento", data.getFechaVencimiento());
            map.put("idConductor", idConductor);
            map.put("fa", data.getFa());
            conn = MyBatisUtils.getSession();
            fa = conn.update("Vehiculo.registrar", map);
            conn.commit();
            if (fa < 0) {
                return -3;
            }
        } catch (Exception ex) {
            System.out.println(map);
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return fa;
    }
    
    public static int registrarVehiculoAnonimo(VehiculoAnonimo vehiculo) {
        int res = 1;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            res = conn.insert("Vehiculo.registrarAnonimo", vehiculo);
            conn.commit();
            if (res <= 0) {
                return -3;
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

    public static List<InfoVehiculo> getVehiculos(Integer idConductor) {
        List<InfoVehiculo> list = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            list = conn.selectList("Vehiculo.getVehiculosConductor", idConductor);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static int editarVehiculo(Vehiculo vehiculo) {
        int res = 0;
        SqlSession conn = null;

        if (validarVehiculo(vehiculo) == 0) {
            try {
                conn = MyBatisUtils.getSession();
                res = conn.update("Vehiculo.editarVehiculo", vehiculo);
                conn.commit();
                if (res <= 0) {
                    return -3;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return res;
    }

    public static Respuesta eliminarVehiculo(String numPlacas) {
        int res = 0;
        Respuesta respuesta = new Respuesta();
        respuesta.setError(false);
        respuesta.setErrorcode(0);
        SqlSession conn = null;

        if (numPlacas == null || numPlacas.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setErrorcode(2);
            respuesta.setMensaje("Numplacas vacias");
        } else {
            try {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("numPlacas", numPlacas);
            map.put("resultado", 0);
            conn = MyBatisUtils.getSession();
            res = conn.update("Vehiculo.eliminarVehiculo",map);
            conn.commit();
            System.out.println(res);
                if (res < 0) {
                    respuesta.setError(true);
                    respuesta.setErrorcode(3);
                    respuesta.setMensaje("Error al eliminar vehiculo de la BD");
                } else {
                    respuesta.setError(false);
                    respuesta.setErrorcode(200);
                    respuesta.setMensaje("Vehiculo eliminado exitosamente");
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

    public static List<Vehiculo> getVehiculosReporte(Integer idReporte) {
        List<Vehiculo> list = new ArrayList<>();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            list = conn.selectList("Vehiculo.getVehiculosReporte", idReporte);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    private static int validarVehiculo(Vehiculo vehiculo) {
        if (vehiculo.getIdMarca() == 0) {
            return -1;
        }
        if (vehiculo.getModelo() == null
                || vehiculo.getModelo().toString().trim().isEmpty()) {
            return -1;
        }
        if (vehiculo.getColor() == null
                || vehiculo.getColor().toString().trim().isEmpty()) {
            return -1;
        }
        if (vehiculo.getNumPlacas() == null
                || vehiculo.getNumPlacas().toString().trim().isEmpty()) {
            return -1;
        }
        if (vehiculo.getYear() == null
                || vehiculo.getYear().toString().trim().isEmpty()) {
            return -1;
        }
        return 0;
    }

    private static boolean buscarVehiculo(Vehiculo vehiculo) {
        String numPlacas = vehiculo.getNumPlacas();
        boolean respuesta = false;
        List<Vehiculo> list = new ArrayList<Vehiculo>();
        if (numPlacas != null && !numPlacas.isEmpty()) {
            SqlSession conn = null;
            try {
                conn = MyBatisUtils.getSession();
                list = conn.selectList("Vehiculo.buscarVehiculo", numPlacas);
                if (!list.isEmpty()) {
                    String idVehiculo = list.get(0).getNumPlacas();
                    String idVehiculoAux = vehiculo.getNumPlacas();
                    if (!Objects.equals(idVehiculo, idVehiculoAux)) {
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
}
