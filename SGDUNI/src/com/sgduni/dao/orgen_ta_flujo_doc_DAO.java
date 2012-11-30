package com.sgduni.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import javax.sql.DataSource;
/**
 *
 * @author marco
 */
public class orgen_ta_flujo_doc_DAO {

    protected DataSource ds;
    Connection cn;
    public orgen_ta_flujo_doc_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean guardarFlujoDoc(int idDoc,int idConfFluj)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_FLUJO_DOC_INSERT(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_DOC",idDoc);
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
    //Borrar toda todos los flujos por documento
      public boolean eliminarFlujoDoc(int idDoc)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_FLUJO_DOC_ELIMINAR(?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_DOC",idDoc);
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

