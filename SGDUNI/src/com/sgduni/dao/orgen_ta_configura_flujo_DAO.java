/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_configura_flujo;
import com.sgduni.utilitarios.FormatoFecha;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author marco
 */
public class orgen_ta_configura_flujo_DAO {

    protected DataSource ds;
    Connection cn;
    public orgen_ta_configura_flujo_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean eliminarConfigFlujo(int idConfFluj)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CONFIG_FLUJO_ELIMI(?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CONG",idConfFluj);
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Failed to execute a JDBC task: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Failed to finalize JDBC task: "+ex);
            }
       }
       return exito;
    }
   //MODIFICAR
     public boolean modificarConfigFlujo(orgen_ta_configura_flujo obj,int idUsu)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CONFIG_FLUJO_MODIF(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CONG",obj.getIn_codigo_cong());
            cstm.setString("P_VC_NOMBRE",obj.getVc_nombre());
            cstm.setInt("P_IN_CODIGO_USUARIO_MODI", idUsu );
            cstm.executeUpdate();
            cn.commit();
            estado = true;
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Failed to execute a JDBC task: "+e);
            estado = false;
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Failed to finalize JDBC task: "+ex);
            }
            return estado;
       }
    }
    //Guardar
    public boolean guardarConfigFlujo(orgen_ta_configura_flujo obj,int idUsu)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CONFIG_FLUJO_INSER(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre());
            cstm.setInt("P_IN_CODIGO_USUARIO_CREA",idUsu);
            cstm.executeUpdate();
            cn.commit();
             estado = true;
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Failed to execute a JDBC task: "+e);
             estado = false;
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Failed to finalize JDBC task: "+ex);
            }
            return  estado;
       }
    }
    //
    public ArrayList<orgen_ta_configura_flujo> getListaConfigFlujo()
    {
        ArrayList<orgen_ta_configura_flujo> listar = null;
        try
        {
            listar = new ArrayList<orgen_ta_configura_flujo>();
            cn = ds.getConnection();
            String sql = "SELECT " +
                        "  CF.IN_CODIGO_CONG,CF.VC_NOMBRE,CF.DT_FECHA_CREA,CF.DT_FECHA_MODIF,CF.CH_ESTADO," +
                        "  (SELECT UCREA.VC_NOMBRES||' '||UCREA.VC_APELLIDO_PATERNO||' '||UCREA.VC_APELLIDO_MATERNO " +
                        "    FROM ORGEN_TA_USUARIO UCREA WHERE UCREA.IN_CODIGO_USUARIO=CF.IN_CODIGO_USUARIO_CREA) AS USUARIO_CREA," +
                        "  (SELECT UMODI.VC_NOMBRES||' '||UMODI.VC_APELLIDO_PATERNO||' '||UMODI.VC_APELLIDO_MATERNO" +
                        "   FROM ORGEN_TA_USUARIO UMODI WHERE UMODI.IN_CODIGO_USUARIO=CF.IN_CODIGO_USUARIO_MODI) AS USUARIO_MODIFICA" +
                        " FROM ORGEN_TA_CONFIGURA_FLUJO CF";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               FormatoFecha fechaWeb=new FormatoFecha();
               orgen_ta_configura_flujo obj = new orgen_ta_configura_flujo();
               obj.setIn_codigo_cong(rs.getInt("IN_CODIGO_CONG"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setDt_fecha_crea(fechaWeb.getFormatoFecha(rs.getDate("DT_FECHA_CREA")));
               obj.setDt_fecha_modif(fechaWeb.getFormatoFecha(rs.getDate("DT_FECHA_MODIF")));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setUsuarioCrea(rs.getString("USUARIO_CREA"));
               obj.setUsuarioModi(rs.getString("USUARIO_MODIFICA"));
               
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
       return listar;
    }
    //Lista para select
    public ArrayList<orgen_ta_configura_flujo> getListaUsuarioConfigFlujo(int idUsuario)
    {
        ArrayList<orgen_ta_configura_flujo> listar = null;
        try
        {
            listar = new ArrayList<orgen_ta_configura_flujo>();
            cn = ds.getConnection();
            String sql = "  SELECT CF.IN_CODIGO_CONG,CF.VC_NOMBRE,UF.IN_CODIGO_USUARIO FROM ORGEN_TA_CONFIGURA_FLUJO CF " +
                         "   LEFT JOIN ORGEN_TA_USUARIO_CONF_FLUJO UF ON UF.IN_CODIGO_CONG=CF.IN_CODIGO_CONG AND UF.IN_CODIGO_USUARIO=?" +
                         "   WHERE CF.CH_ESTADO='01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idUsuario);
            ResultSet rs = pstm.executeQuery();            
            while(rs.next() )
            {               
               orgen_ta_configura_flujo obj = new orgen_ta_configura_flujo();
               obj.setIn_codigo_cong(rs.getInt("IN_CODIGO_CONG"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setIn_codigo_usuario_crea(rs.getInt("IN_CODIGO_USUARIO"));
               listar.add(obj);
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
       return listar;
    }
    //BUSCAR
    public orgen_ta_configura_flujo getConfigFlujo(int inConfFluj )
    {
        orgen_ta_configura_flujo obj = new orgen_ta_configura_flujo();
        try
        {
            cn = ds.getConnection();
            String sql="SELECT IN_CODIGO_CONG,VC_NOMBRE FROM ORGEN_TA_CONFIGURA_FLUJO WHERE IN_CODIGO_CONG=?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, inConfFluj);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               obj.setIn_codigo_cong(rs.getInt("IN_CODIGO_CONG"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
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
       return obj;
    }

}
