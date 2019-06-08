/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;

import beans.Aseguradora;
import model.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
/**
 *
 * @author Juan Carlos
 */
public class AseguradoraDAO {
    
    public static List<Aseguradora> getAseguradoras(){
        List<Aseguradora> list = new ArrayList<>();
        SqlSession conn = null;
        
        try{
         conn = MyBatisUtils.getSession();
         list = conn.selectList("Aseguradora.getAseguradoras");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn != null){
                conn.close();
            }
        }
        
        return list;
    }
    
    public static Aseguradora getAseguradora(int idAseguradora){
        Aseguradora aseguradora = new Aseguradora();
        SqlSession conn = null;
        
        try{
         conn = MyBatisUtils.getSession();
         aseguradora = conn.selectOne("Aseguradora.getAseguradora",idAseguradora);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn != null){
                conn.close();
            }
        }
        
        return aseguradora;
    }
}
