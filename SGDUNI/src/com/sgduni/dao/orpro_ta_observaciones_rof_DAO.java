package com.sgduni.dao;

import com.sgduni.forms.orpro_ta_observaciones_rof;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
/**
 *
 * @author Sistemas
 */
public class orpro_ta_observaciones_rof_DAO
{
    protected DataSource ds;
    Connection cn;
    
    public orpro_ta_observaciones_rof_DAO(DataSource ds)
    {
        this.ds = ds;
    }
    
    //Eliminar
    public boolean eliminarObservacionRof(orpro_ta_observaciones_rof obj )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_ROL_ELIMINAR(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            //cstm.setString("P_CH_CODIGO_ROL", obj.getCh_codigo_rol());
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

    //Modificar
     public boolean modificarObservacionRof(orpro_ta_observaciones_rof obj,String usu,String codUsu)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_ROL_MODIFICAR(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            //cstm.setString("P_CH_CODIGO_ROL", obj.getCh_codigo_rol().toUpperCase());
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

     public boolean guardarObservacionRo(orpro_ta_observaciones_rof obj,int idUsu)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_OBS_ROF_USU(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ROF", obj.getIn_codigo_rof());
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsu);
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

     //Guardar OCDO
    public boolean guardarObservacionRofparaOCDO(orpro_ta_observaciones_rof obj,int idUsu,int idEstado)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_OBS_ROF_OCDO(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ROF", obj.getIn_codigo_rof());
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsu);
            cstm.setInt("P_IN_CODIGO_ESTADO_OCDO", idEstado);
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

    //Guardar A. LEGAL
    public boolean guardarObservacionRofparaALEGAL(orpro_ta_observaciones_rof obj,int idUsu,int idEstado)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_OBS_ROF_ASES(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ROF", obj.getIn_codigo_rof());
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsu);
            cstm.setInt("P_IN_CODIGO_ESTADO_A_LEGAL", idEstado);
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

    //Guardar RECTOR
    public boolean guardarObservacionRofparaRECTORADO(orpro_ta_observaciones_rof obj,int idUsu,int idEstado)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_OBS_ROF_RECTOR(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ROF", obj.getIn_codigo_rof());
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsu);
            cstm.setInt("P_IN_CODIGO_ESTADO_RECTORADO", idEstado);
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

    //Listas
    public ArrayList<orpro_ta_observaciones_rof> getListaObservacionesRof(int codRof)
    {
        ArrayList<orpro_ta_observaciones_rof> listaObservaciones = null;
        try
        {
            listaObservaciones = new ArrayList<orpro_ta_observaciones_rof>();
            cn = ds.getConnection();
            String sql ="SELECT O.IN_CODIGO_OBSERVA,O.IN_CODIGO_USUARIO, "+
                        "O.VC_OBSERVACION,O.DT_FECHA_CREA, "+
                        "CONCAT( U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS USUARIO "+
                        "FROM ORPRO_TA_OBSERVACIONES_ROF O "+
                        "INNER JOIN ORGEN_TA_USUARIO U ON O.IN_CODIGO_USUARIO = U.IN_CODIGO_USUARIO "+
                        "WHERE IN_CODIGO_ROF = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, codRof);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_observaciones_rof obj = new orpro_ta_observaciones_rof();
               obj.setIn_codigo_observa(rs.getInt("IN_CODIGO_OBSERVA"));
               obj.setNombreUsuario(rs.getString("USUARIO") );
               obj.setVc_observacion(rs.getString("VC_OBSERVACION"));
               obj.setDt_fecha_crea(rs.getTimestamp("DT_FECHA_CREA").toString());
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
               listaObservaciones.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println(" getListaObservacionesRof : "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println(" getListaObservacionesRof : "+ex);}
       }
       return listaObservaciones;
    }
}
