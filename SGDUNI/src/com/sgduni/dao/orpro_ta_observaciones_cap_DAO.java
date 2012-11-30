/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;

import com.sgduni.forms.orpro_ta_observaciones_cap;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author marco
 */
public class orpro_ta_observaciones_cap_DAO
{
    protected DataSource ds;
    Connection cn;
    public orpro_ta_observaciones_cap_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    //Guardar
    public boolean guardarObservacionCap(orpro_ta_observaciones_cap obj,int idUsuario,int idEstado)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_OBSERV_CAP(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CAP",obj.getIn_codigo_cap() );
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_USUARIO",idUsuario);
            cstm.setInt("P_IN_CODIGO_ESTADO_CAP",idEstado);
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
    public ArrayList<orpro_ta_observaciones_cap> getObservacionesPorCap(int CodCap)
    {
        ArrayList<orpro_ta_observaciones_cap> listaObservaciones = null;
        try
        {
            listaObservaciones = new ArrayList<orpro_ta_observaciones_cap>();
            cn = ds.getConnection();
            String sql = "SELECT OB.IN_CODIGO_OBSERVA,OB.VC_OBSERVACION,OB.DT_FECHA_CREA AS FECHA, ";
            sql += "U.IN_CODIGO_USUARIO, U.VC_NOMBRES,U.VC_APELLIDO_PATERNO,U.VC_APELLIDO_MATERNO ";
            sql += "FROM orpro_ta_observaciones_CAP OB ";
            sql += "INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO = OB.IN_CODIGO_USUARIO ";
            sql += "WHERE OB.IN_CODIGO_CAP="+CodCap+" AND OB.CH_ESTADO='01' ORDER BY OB.DT_FECHA_CREA DESC ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1, CodCap);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");
               SimpleDateFormat horaWeb=new SimpleDateFormat("hh:mm a");

               orpro_ta_observaciones_cap obj = new orpro_ta_observaciones_cap();
               obj.setIn_codigo_observa(rs.getInt("IN_CODIGO_OBSERVA"));
               obj.setNombreUsuario(rs.getString("VC_NOMBRES")+" "+rs.getString("VC_APELLIDO_PATERNO")+" "+rs.getString("VC_APELLIDO_MATERNO"));
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
