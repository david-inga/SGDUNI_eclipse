package com.sgduni.dao;

import com.sgduni.forms.orpro_detalle_objetivo_direc;
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
public class orpro_detalle_objetivo_direc_DAO
{
    private DataSource ds;
    private Connection cn;

    public orpro_detalle_objetivo_direc_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean agregarObjetivoADirectiva(orpro_detalle_objetivo_direc obj,String nomUsuario,String idUsuario)
    {
        System.out.println("Objetivo de directivas");
        System.out.println("id "+obj.getIn_codigo_directiva());
         System.out.println("desc "+obj.getVc_descripcion());
        System.out.println("Objetivo de directivas");
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_DET_OBJ_DIR_INSERT(?,?,?,?) }";
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

    public ArrayList<orpro_detalle_objetivo_direc> getObjetivosDir(String in_codigo_dir)
    {
        ArrayList<orpro_detalle_objetivo_direc> lista = null;
        try
        {
            lista = new ArrayList<orpro_detalle_objetivo_direc>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_DET_OBJ_DIREC,VC_DESCRIPCION FROM ORPRO_DETALLE_OBJETIVO_DIREC  WHERE IN_CODIGO_DIRECTIVA = '"+in_codigo_dir.trim().toString()+"'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();

            while( rs.next() )
            {
               orpro_detalle_objetivo_direc obj = new orpro_detalle_objetivo_direc();
               
               obj.setIn_codigo_det_obj_direc( rs.getInt("IN_CODIGO_DET_OBJ_DIREC") );
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
