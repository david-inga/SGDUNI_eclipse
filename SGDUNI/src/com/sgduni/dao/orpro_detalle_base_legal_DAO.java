/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;

import com.sgduni.forms.orpro_detalle_base_legal;
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
public class orpro_detalle_base_legal_DAO
{

    private DataSource ds;
    private Connection cn;

    public orpro_detalle_base_legal_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean agregarBaseLegalADirectiva(orpro_detalle_base_legal obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_DET_BASE_DIR_INSERT(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_DIRECTIVA", obj.getIn_codigo_directiva()     );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase()     );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsuario ) );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
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

    public ArrayList<orpro_detalle_base_legal> getBasesLegalesDir(String in_codigo_dir)
    {
        ArrayList<orpro_detalle_base_legal> lista = null;
        try
        {
            lista = new ArrayList<orpro_detalle_base_legal>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_DET_BASE_LEGAL,VC_DESCRIPCION FROM ORPRO_DETALLE_BASE_LEGAL WHERE IN_CODIGO_DIRECTIVA = "+in_codigo_dir.trim().toString();
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();

            while( rs.next() )
            {
               orpro_detalle_base_legal obj = new orpro_detalle_base_legal();

               obj.setIn_codigo_det_base_legal( rs.getInt("IN_CODIGO_DET_BASE_LEGAL") );
               obj.setVc_descripcion( rs.getString("VC_DESCRIPCION") );
               lista.add(obj);
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
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("Failed to finalize JDBC task: "+ex);}
       }
        return lista;
    }

}
