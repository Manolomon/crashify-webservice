/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

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

    public static int registrarVehiculo(Vehiculo vehiculo, Date fechaVencimiento) {
        int res = 0;
        SqlSession conn = null;
        res = validarVehiculo(vehiculo);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if (res != 0) {
            return res;
        }
        if (buscarVehiculo(vehiculo)) {
            return -2;
        }
        try {
            VehiculoData data = new VehiculoData();
            data.setColor(vehiculo.getColor());
            data.setFechaVencimiento(fechaVencimiento);
            data.setIdAseguradora(vehiculo.getIdAseguradora());
            data.setIdMarca(vehiculo.getIdMarca());
            data.setModelo(vehiculo.getModelo());
            data.setNumPlacas(vehiculo.getNumPlacas());
            data.setNumPoliza(vehiculo.getNumPoliza());
            data.setYear(vehiculo.getYear());
            
            System.out.println(map);
            conn = MyBatisUtils.getSession();
            conn.update("Vehiculo.registrar", map);
            conn.commit();
            res = data.getFa();
            if (res <= 0) {
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
        return res;
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

    public static List<Vehiculo> getVehiculos(Integer idConductor) {
        List<Vehiculo> list = new ArrayList<>();
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

    public static int eliminarVehiculo(int idVehiculo) {
        int res = 0;
        SqlSession conn = null;

        try {
            conn = MyBatisUtils.getSession();
            res = conn.delete("Vehiculo.eliminarVehiculo", idVehiculo);
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
                    Integer idVehiculo = list.get(0).getIdVehiculo();
                    Integer idVehiculoAux = vehiculo.getIdVehiculo();
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
