/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import java.util.ArrayList;
import beans.Marca;
import model.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Juan Carlos
 */
public class MarcaDAO {

    public static List<Marca> getMarcas() {
        List<Marca> list = new ArrayList<>();
        SqlSession conn = null;

        try {
            conn = MyBatisUtils.getSession();
            list = conn.selectList("Marca.getMarcas");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static Marca getMarca(int idMarca) {
        Marca marca = new Marca();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            marca = conn.selectOne("Marca.getMarca", idMarca);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return marca;
    }
}
