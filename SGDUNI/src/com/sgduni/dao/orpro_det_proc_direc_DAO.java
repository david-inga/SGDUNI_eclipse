package com.sgduni.dao;

import com.sgduni.forms.orpro_det_proc_direc;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/*
 * @author marco
 */
public class orpro_det_proc_direc_DAO
{

     private DataSource ds;
    private Connection cn;

    public orpro_det_proc_direc_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean agregarProcedimientoADirectiva(orpro_det_proc_direc obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_DET_PROC_DIREC_INSERT(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_DIRECTIVA", obj.getIn_codigo_directiva()     );
            cstm.setInt("P_IN_CODIGO_PROCEDIMIENTO", obj.getIn_codigo_procedimiento()    );
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

    public ArrayList<orpro_det_proc_direc> getProcedimientoDir(String in_codigo_dir)
    {
        ArrayList<orpro_det_proc_direc> lista = null;
        try
        {
            lista = new ArrayList<orpro_det_proc_direc>();
            cn = ds.getConnection();
            String sql = "SELECT p.vc_nombre  as nompro FROM orpro_det_proc_direc pd ";
                   sql += "inner join orgen_ta_procedimiento p on  pd.in_codigo_procedimiento = p.in_codigo_procedimiento ";
                   sql += "where pd.in_codigo_directiva = "+in_codigo_dir;
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();

            while( rs.next() )
            {
               orpro_det_proc_direc obj = new orpro_det_proc_direc();

               obj.setNombre_procedimiento( rs.getString("NOMPRO") );
               //obj.setVc_descripcion( rs.getString("VC_DESCRIPCION") );
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

    public ArrayList<orpro_det_proc_direc> getProcedimientoDirExportar(String in_codigo_dir)
    {
        ArrayList<orpro_det_proc_direc> lista = null;
        try
        {
            lista = new ArrayList<orpro_det_proc_direc>();
            cn = ds.getConnection();
            String sql = "SELECT p.vc_nombre  as nompro,p.vc_descripcion as descripcion FROM orpro_det_proc_direc pd ";
                   sql += "inner join orgen_ta_procedimiento p on  pd.in_codigo_procedimiento = p.in_codigo_procedimiento ";
                   sql += "where pd.in_codigo_directiva = "+in_codigo_dir;
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();

            while( rs.next() )
            {
               orpro_det_proc_direc obj = new orpro_det_proc_direc();

               obj.setNombre_procedimiento( rs.getString("NOMPRO") );
               obj.setDescripcion_proce( rs.getString("DESCRIPCION") );
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
