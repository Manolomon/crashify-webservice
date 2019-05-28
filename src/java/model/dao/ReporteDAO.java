/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.Reporte;
import beans.Respuesta;
import java.util.ArrayList;
import java.util.List;
import model.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juan Carlos
 */
public class ReporteDAO {

    public static int agregarReporte(Reporte reporte) {
        int res = 0;
        SqlSession conn = null;
        res = validarReporte(reporte);
        if(res != 0){
            return res;
        }
        try {
            conn = MyBatisUtils.getSession();
            res = conn.insert("Reporte.registrar", reporte);
            if (res < 0) {
                return -2;
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
    
    public static List<Reporte> getReportes(int idConductor){
        List<Reporte> list = new ArrayList<>();
        SqlSession conn = null;
        try {
                conn = MyBatisUtils.getSession();
                list = conn.selectList("Reporte.buscarReportes", idConductor);
            } catch (Exception ex) {
                ex.printStackTrace();
         }finally{
            if (conn != null){
                conn.close();
            }
        }
        return list;
    }

    private static int validarReporte(Reporte reporte) {
        int res = 0;
        if (reporte.getLatitud() == null
                || reporte.getLatitud().toString().trim().isEmpty()) {
            return -1;
        }
        if (reporte.getLongitud() == null
                || reporte.getLongitud().toString().trim().isEmpty()) {
            return -1;
        }
        if (reporte.getDescripcion() == null
                || reporte.getDescripcion().toString().trim().isEmpty()) {
            return -1;
        }
        if (reporte.getPlacasVehiculos() == null
                || reporte.getPlacasVehiculos().toString().trim().isEmpty()) {
            return -1;
        }
        if (reporte.getIdConductor() == 0) {
            return -1;
        }
        return res;
    }
}
