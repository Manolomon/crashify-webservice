/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.Evidencia;
import beans.Reporte;
import java.util.ArrayList;
import java.util.List;
import model.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juan Carlos
 */
public class EvidenciaDAO {
    public static int agregarEvidencia(Evidencia evidencia) {
        int res = 0;
        SqlSession conn = null;
        if(evidencia.getBytes().length == 0){
            return -1;
        }
        if(evidencia.getIdReporte() == 0){
            return -1;
        }
        try {
            conn = MyBatisUtils.getSession();
            res = conn.insert("Evidencia.registrar", evidencia);
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
    
    public static List<Evidencia> getEvidencias(int idReporte){
        List<Evidencia> list = new ArrayList<>();
        SqlSession conn = null;
        try {
                conn = MyBatisUtils.getSession();
                list = conn.selectList("Evidencia.obtenerFotosReporte", idReporte);
            } catch (Exception ex) {
                ex.printStackTrace();
         }finally{
            if (conn != null){
                conn.close();
            }
        }
        return list;
    }
}
