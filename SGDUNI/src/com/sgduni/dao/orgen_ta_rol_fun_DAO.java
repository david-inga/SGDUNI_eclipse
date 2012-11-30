package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_funcionalidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;


public class orgen_ta_rol_fun_DAO
{
   protected DataSource ds;
   Connection cn;

    public orgen_ta_rol_fun_DAO(DataSource ds)
    {
        this.ds = ds;
    }
    //Guardar
    public boolean guardarRolFuncionalidad(String codRol,String codFuncionalidad){
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_TA_ORGEN_ROL_FUN_INSERT(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_ROL",codRol);
            cstm.setString("P_CH_CODIGO_FUNCIONALIDAD",codFuncionalidad);
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
    //Borrar toda funcionalidad segun ROL
    public boolean eliminarRolFuncionalidad(String codRol,String codHerr){
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_TA_ORGEN_ROL_FUN_ELIMINAR(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_ROL",codRol);
            cstm.setString("P_CH_CODIGO_HERRAMIENTA",codHerr);
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