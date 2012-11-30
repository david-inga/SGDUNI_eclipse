/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;
import com.sgduni.forms.orpro_ta_observaciones_mof;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.DataSource;
/**
 *
 * @author Sistemas
 */
public class orpro_ta_observaciones_mof_DAO {
    protected DataSource ds;
    Connection cn;
    public orpro_ta_observaciones_mof_DAO(DataSource ds)
    {
        this.ds = ds;
    }
    //Eliminar
    public boolean eliminarObservacionMof(orpro_ta_observaciones_mof obj )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_MOL_ELIMINAR(?,?) }";
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
     public boolean modificarObservacionMof(orpro_ta_observaciones_mof obj,String usu,String codUsu)
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
    //Guardar
    public boolean guardarObservacionMof(orpro_ta_observaciones_mof obj,int idUsu,int idEstado)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_OBSV_MOF_INSERT(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_MOF", obj.getIn_codigo_mof());
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsu);
            cstm.setInt("P_IN_CODIGO_ESTADO", idEstado);

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
    public ArrayList<orpro_ta_observaciones_mof> getListaObservacionesMof(int codMof)
    {
        ArrayList<orpro_ta_observaciones_mof> listaObservaciones = null;
        try
        {
            listaObservaciones = new ArrayList<orpro_ta_observaciones_mof>();
            cn = ds.getConnection();
            String sql = "SELECT OB.IN_CODIGO_OBSERVA,OB.VC_OBSERVACION,OB.DT_FECHA_CREA AS FECHA,U.IN_CODIGO_USUARIO, " +
                         "  U.VC_NOMBRES AS NOM_USUARIO,U.VC_APELLIDO_PATERNO AS APP_USUARIO,U.VC_APELLIDO_MATERNO AS APM_USUARIO " +
                         " FROM ORPRO_TA_OBSERVACIONES_MOF OB " +
                         " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=OB.IN_CODIGO_USUARIO " +
                         " WHERE OB.IN_CODIGO_MOF=? AND OB.CH_ESTADO='01' ORDER BY OB.DT_FECHA_CREA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, codMof);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");
               SimpleDateFormat horaWeb=new SimpleDateFormat("hh:mm a");

               orpro_ta_observaciones_mof obj = new orpro_ta_observaciones_mof();
               obj.setIn_codigo_observa(rs.getInt("IN_CODIGO_OBSERVA"));
               obj.setNombreUsuario(rs.getString("NOM_USUARIO")+" "+rs.getString("APP_USUARIO")+" "+rs.getString("APM_USUARIO"));
               obj.setFechaWeb(fechWeb.format(rs.getDate("FECHA")));
               obj.setHoraWeb(horaWeb.format(rs.getDate("FECHA")));
               obj.setVc_observacion(rs.getString("VC_OBSERVACION"));
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
               listaObservaciones.add(obj);
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
       return listaObservaciones;
    }
}
