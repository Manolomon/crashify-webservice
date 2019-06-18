/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.Reporte;
import beans.ReporteDictamen;
import beans.ReporteResumido;
import java.util.ArrayList;
import java.util.HashMap;
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
        if (res != 0) {
            return res;
        }
        try {
            conn = MyBatisUtils.getSession();
            conn.update("Reporte.registrar", reporte);
            conn.commit();
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
    
    public static ReporteDictamen getDetallesReporte(int idReporte){
        ReporteDictamen reporte = new ReporteDictamen();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            List<ReporteDictamen> reportes;
            reportes= conn.selectList("Reporte.obtenerDetallesReporte", idReporte);
            if(!reportes.isEmpty()){
                reporte = reportes.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        
        return reporte;
    }

    public static List<ReporteResumido> getReportes(int idConductor) {
        List<ReporteResumido> list = new ArrayList<>();
        SqlSession conn = null;
        try {
            List<ReporteResumido>listAux = new ArrayList<>();
            conn = MyBatisUtils.getSession();
            list = conn.selectList("Reporte.buscarReportes", idConductor);
            List<Integer>arrayIds = new ArrayList<>();
            for(ReporteResumido reporte:list){
                int id = reporte.getIdReporte();
                if(!arrayIds.contains(id)){
                    listAux.add(reporte);
                    arrayIds.add(id);
                }
            }
            list = listAux;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
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
