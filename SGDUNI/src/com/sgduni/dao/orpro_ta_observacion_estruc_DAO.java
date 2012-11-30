/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;
import com.sgduni.forms.orpro_ta_observacion_estruc;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.DataSource;
/**
 *
 * @author Sistemas
 */
public class orpro_ta_observacion_estruc_DAO {

    protected DataSource ds;
    Connection cn;
    public orpro_ta_observacion_estruc_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    //Eliminar
    public boolean eliminarObservacion(orpro_ta_observacion_estruc obj )
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
    //Guardar
    public boolean guardarObservacion(orpro_ta_observacion_estruc obj,int idUsuario,int idEstado)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_OBSERV_ESTRUCTURA(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ESTRUCTURA",obj.getIn_codigo_estructura());
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_USUARIO",idUsuario);
            cstm.setInt("P_IN_CODIGO_ESTADO",idEstado);
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

    //Lista Observaciones por Estrucura
    public ArrayList<orpro_ta_observacion_estruc> getObservacionesPorEstructura(int CodEstruc)
    {
        ArrayList<orpro_ta_observacion_estruc> listaObservaciones = null;
        try
        {
            listaObservaciones = new ArrayList<orpro_ta_observacion_estruc>();
            cn = ds.getConnection();
            String sql = "SELECT OB.IN_CODIGO_OBSERVA,OB.VC_OBSERVACION,OB.DT_FECHA_CREA AS FECHA,U.IN_CODIGO_USUARIO, U.VC_NOMBRES,U.VC_APELLIDO_PATERNO,U.VC_APELLIDO_MATERNO " +
                    "FROM ORPRO_TA_OBSERVACION_ESTRUC OB " +
                    "INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=OB.IN_CODIGO_USUARIO " +
                    "WHERE OB.IN_CODIGO_ESTRUCTURA=? AND OB.CH_ESTADO='01' ORDER BY OB.DT_FECHA_CREA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, CodEstruc);
            ResultSet rs = pstm.executeQuery();            
            while(rs.next() )
            {
               SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");
               SimpleDateFormat horaWeb=new SimpleDateFormat("hh:mm a");

               orpro_ta_observacion_estruc obj = new orpro_ta_observacion_estruc();
               obj.setIn_codigo_observa(rs.getInt("IN_CODIGO_OBSERVA"));
               // falla - obj.setNombreUsuario(rs.getString("VC_NOMBRES")+" "+rs.getString("VC_APELLIDO_PATERNO")+" "+rs.getString("VC_APELLIDO_MATERNO"));
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
