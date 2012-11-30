/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;
import com.sgduni.forms.orgen_ta_funcionalidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
/**
 *
 * @author marco
 */
public class orgen_ta_funcionalidad_DAO {

    protected DataSource ds;
   Connection cn;

    public orgen_ta_funcionalidad_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public ArrayList<orgen_ta_funcionalidad> getFuncionalidadSegunHerramientarRol(String codHerr,String codRol)
    {
        ArrayList<orgen_ta_funcionalidad> listaFuncionalidad = null;
        try
        {
            listaFuncionalidad = new ArrayList<orgen_ta_funcionalidad>();
            cn = ds.getConnection();
            String sql = "SELECT F.CH_CODIGO_FUNCIONALIDAD,F.VC_NOMBRE,F.VC_URL_FUNCIONALIDAD,F.VC_ICONO,H.CH_CODIGO_HERRAMIENTA, (SELECT RRF.CH_CODIGO_ROL FROM ORGEN_TA_ROL_FUN RRF WHERE RRF.CH_CODIGO_FUNCIONALIDAD=F.CH_CODIGO_FUNCIONALIDAD AND RRF.CH_CODIGO_ROL='"+codRol+"') AS CH_CODIGO_ROL " +
                    "FROM ORGEN_TA_HERRAMIENTA_FUN HF " +
                    "INNER JOIN ORGEN_TA_HERRAMIENTA H ON H.CH_CODIGO_HERRAMIENTA=HF.CH_CODIGO_HERRAMIENTA " +
                    " INNER JOIN ORGEN_TA_FUNCIONALIDAD F ON F.CH_CODIGO_FUNCIONALIDAD=HF.CH_CODIGO_FUNCIONALIDAD" +
                    " WHERE H.CH_CODIGO_HERRAMIENTA='"+codHerr+"' ORDER BY F.VC_NOMBRE DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setString(1, codHerr);
            ResultSet rs = pstm.executeQuery();            
            while(rs.next())
            {
                //System.out.println("recorre ");
                orgen_ta_funcionalidad obj = new orgen_ta_funcionalidad();
                obj.setCh_codigo_funcionalidad(rs.getString("CH_CODIGO_FUNCIONALIDAD"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));
                obj.setCh_codigo_rol(rs.getString("CH_CODIGO_ROL"));                
                obj.setVc_icono(rs.getString("VC_ICONO"));
                obj.setVc_url_funcionalidad(rs.getString("VC_URL_FUNCIONALIDAD"));
                listaFuncionalidad.add(obj);
            }
           // System.out.println("Fin Recorre ");
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
       return listaFuncionalidad;
    }

    //para el menu BODY segun herramienta
    public ArrayList<orgen_ta_funcionalidad> getFuncionalidadSegunHerramientarRolMenu(String codHerr,String codRol)
    {
        ArrayList<orgen_ta_funcionalidad> listaFuncionalidad = null;
        try
        {
            listaFuncionalidad = new ArrayList<orgen_ta_funcionalidad>();
            cn = ds.getConnection();
            String sql = "SELECT DISTINCT H.CH_CODIGO_FUNCIONALIDAD,H.VC_NOMBRE,H.VC_ICONO,H.VC_TARGET,H.VC_URL_FUNCIONALIDAD" +
                         " FROM ORGEN_TA_ROL_FUN RF " +
                         " INNER JOIN ORGEN_TA_HERRAMIENTA_FUN HF ON HF.CH_CODIGO_FUNCIONALIDAD=RF.CH_CODIGO_FUNCIONALIDAD " +
                         " INNER JOIN ORGEN_TA_FUNCIONALIDAD H ON H.CH_CODIGO_FUNCIONALIDAD=HF.CH_CODIGO_FUNCIONALIDAD AND H.CH_ESTADO='01'" +
                         " WHERE RF.CH_CODIGO_ROL='"+codRol+"' AND HF.CH_CODIGO_HERRAMIENTA='"+codHerr+"'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setString(1, codHerr);
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                //System.out.println("recorre ");
                orgen_ta_funcionalidad obj = new orgen_ta_funcionalidad();
                obj.setCh_codigo_funcionalidad(rs.getString("CH_CODIGO_FUNCIONALIDAD"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));                
                obj.setVc_icono(rs.getString("VC_ICONO"));
                obj.setVc_url_funcionalidad(rs.getString("VC_URL_FUNCIONALIDAD"));
                listaFuncionalidad.add(obj);
            }
           // System.out.println("Fin Recorre ");
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
       return listaFuncionalidad;
    }
    //para el menu BODY menu inicio center
    public ArrayList<orgen_ta_funcionalidad> getFuncionalidadSegunRolMenu(String codRol)
    {
        ArrayList<orgen_ta_funcionalidad> listaFuncionalidad = null;
        try
        {
            listaFuncionalidad = new ArrayList<orgen_ta_funcionalidad>();
            cn = ds.getConnection();
            String sql = "SELECT DISTINCT H.CH_CODIGO_FUNCIONALIDAD,H.VC_NOMBRE,H.VC_ICONO,H.VC_TARGET,H.VC_URL_FUNCIONALIDAD" +
                         " FROM ORGEN_TA_ROL_FUN RF " +
                         " INNER JOIN ORGEN_TA_HERRAMIENTA_FUN HF ON HF.CH_CODIGO_FUNCIONALIDAD=RF.CH_CODIGO_FUNCIONALIDAD " +
                         " INNER JOIN ORGEN_TA_FUNCIONALIDAD H ON H.CH_CODIGO_FUNCIONALIDAD=HF.CH_CODIGO_FUNCIONALIDAD AND H.CH_ESTADO='01'" +
                         " WHERE RF.CH_CODIGO_ROL='"+codRol+"'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setString(1, codHerr);
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                //System.out.println("recorre ");
                orgen_ta_funcionalidad obj = new orgen_ta_funcionalidad();
                obj.setCh_codigo_funcionalidad(rs.getString("CH_CODIGO_FUNCIONALIDAD"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));
                obj.setVc_icono(rs.getString("VC_ICONO"));
                obj.setVc_url_funcionalidad(rs.getString("VC_URL_FUNCIONALIDAD"));
                listaFuncionalidad.add(obj);
            }
           // System.out.println("Fin Recorre ");
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
       return listaFuncionalidad;
    }
}
