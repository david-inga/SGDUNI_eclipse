package com.sgduni.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import javax.sql.DataSource;
/**
 *
 * @author marco
 */
public class orgen_ta_usuario_conf_flujo_DAO {

    protected DataSource ds;
    Connection cn;
    public orgen_ta_usuario_conf_flujo_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean guardarUsuarioConfFlujo(int idUsu,int idConfFluj)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_USUA_CONF_FLUJ_INS(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_USUARIO",idUsu);
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
    //Borrar toda LA CONFIGURACION POR USUARIO
      public boolean eliminarUsuarioConfFlujo(int idUsu)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_USUA_CONF_FLUJ_ELI(?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_USUARIO",idUsu);            
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
}
