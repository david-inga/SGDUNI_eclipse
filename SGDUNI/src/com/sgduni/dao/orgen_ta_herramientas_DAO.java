/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;
import com.sgduni.forms.orgen_ta_herramientas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
/**
 *
 * @author Sistemas
 */
public class orgen_ta_herramientas_DAO {
    protected DataSource ds;
    Connection cn;

    public orgen_ta_herramientas_DAO(DataSource ds){
        this.ds=ds;
    }

    //Lista de Herramientas
    public ArrayList<orgen_ta_herramientas> getHerramientas()
    {
        ArrayList<orgen_ta_herramientas> listaHerramientas = null;
        try
        {
            listaHerramientas = new ArrayList<orgen_ta_herramientas>();
            cn = ds.getConnection();
            String sql = "SELECT CH_CODIGO_HERRAMIENTA,VC_NOMBRE,VC_URL_HERRAMIENTA FROM ORGEN_TA_HERRAMIENTA ORDER BY VC_NOMBRE DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);            
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_herramientas obj = new orgen_ta_herramientas();
               obj.setCh_codigo_herramienta(rs.getString("CH_CODIGO_HERRAMIENTA"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_url_herramienta(rs.getString("VC_URL_HERRAMIENTA"));
               listaHerramientas.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Failed to execute a JDBC task: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Failed to finalize JDBC task: "+ex);}
       }
       return listaHerramientas;
    }

        //Lista de Herramientas Segun PROTOCOLO
    public ArrayList<orgen_ta_herramientas> getHerramientasSegunProtocolo(String codRol)
    {
        ArrayList<orgen_ta_herramientas> listaHerramientas = null;
        try
        {
            listaHerramientas = new ArrayList<orgen_ta_herramientas>();
            cn = ds.getConnection();
            String sql = "SELECT DISTINCT H.CH_CODIGO_HERRAMIENTA,H.VC_NOMBRE,H.VC_ICONO,H.VC_TARGET,H.VC_URL_HERRAMIENTA  " +
                         " FROM ORGEN_TA_ROL_FUN RF " +
                         " INNER JOIN ORGEN_TA_HERRAMIENTA_FUN HF ON HF.CH_CODIGO_FUNCIONALIDAD=RF.CH_CODIGO_FUNCIONALIDAD " +
                         " INNER JOIN ORGEN_TA_HERRAMIENTA H ON H.CH_CODIGO_HERRAMIENTA=HF.CH_CODIGO_HERRAMIENTA" +
                         " WHERE RF.CH_CODIGO_ROL='"+codRol+"'";            
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setString(1, codRol.trim());
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_herramientas obj = new orgen_ta_herramientas();
               obj.setCh_codigo_herramienta(rs.getString("CH_CODIGO_HERRAMIENTA"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_url_herramienta(rs.getString("VC_URL_HERRAMIENTA"));
               obj.setVc_icono(rs.getString("VC_ICONO"));
               obj.setVc_target(rs.getString("VC_TARGET"));
               listaHerramientas.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Failed to execute a JDBC task: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Failed to finalize JDBC task: "+ex);}
       }
       return listaHerramientas;
    }
}
