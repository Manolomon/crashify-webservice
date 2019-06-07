/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.Evidencia;
import beans.Reporte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juan Carlos
 */
public class EvidenciaDAO {
    
    
    public static int agregarImagen(byte[] datos, int idReporte) throws Exception {
        int fa = 0;
        SqlSession conn = null;
        HashMap<String, Object> map = new HashMap<>();
        map.put("foto", datos);
        map.put("idReporte", idReporte);
        System.out.println(datos.length);
        try {
            conn = MyBatisUtils.getSession();
            fa = conn.insert("Evidencia.insertarImagen", map);
            conn.commit();
            if (fa > 0) {
                return fa;
            } else {
                return -3;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
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
