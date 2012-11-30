/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;
import com.sgduni.forms.orpro_ta_versiones_rof;
import com.sgduni.utilitarios.FormatoFecha;
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
public class orpro_ta_versiones_rof_DAO {
    protected DataSource ds;
    Connection cn;
    public orpro_ta_versiones_rof_DAO(DataSource ds)
    {
        this.ds = ds;
    }
    //Eliminar
    public boolean eliminarVersionRof(orpro_ta_versiones_rof obj )
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
     public boolean modificarVersionEstadoRof(int idRof,int idEstado)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_VERS_ROF_EST_MODI(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
             cstm.setInt(1,idRof);
             cstm.setInt(2,idEstado);
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
    public boolean guardarVersionRof(orpro_ta_versiones_rof obj,int idUsu,int idEstado,String nomArchivoDB)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_VERS_ROF_INSERT(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ROF", obj.getIn_codigo_rof());
            cstm.setString("P_VC_NOMBRE_ARCHIVO", obj.getVc_nombre_archivo());
            cstm.setString("P_VC_RUTA_ARCHIVO", nomArchivoDB);
            cstm.setInt("P_IN_CODIGO_ESTADO",idEstado);
            cstm.setInt("P_IN_CODIGO_USUARIO",idUsu);
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
    public ArrayList<orpro_ta_versiones_rof> getListaVersionesRof(int codRof)
    {
        ArrayList<orpro_ta_versiones_rof> listaVerRof = null;
        try
        {
            listaVerRof = new ArrayList<orpro_ta_versiones_rof>();
            cn = ds.getConnection();
            String sql = "SELECT VROF.IN_CODIGO_VERSIONES,VROF.VC_NOMBRE_ARCHIVO,VROF.VC_RUTA_ARCHIVO," +
                         " VROF.DT_FECHA_CREA," +
                         " U.VC_NOMBRES AS NOM_USUARIO,U.VC_APELLIDO_PATERNO AS APP_USUARIO,U.VC_APELLIDO_MATERNO AS APM_USUARIO" +
                         " ,E.VC_NOMBRE_ESTADO AS NOMBRE_ESTADO,E.IN_CODIGO_ESTADO " +
                         " FROM ORPRO_TA_VERSIONES_ROF VROF" +
                         " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=VROF.IN_CODIGO_USUARIO" +
                         " INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=VROF.IN_CODIGO_ESTADO" +
                         " WHERE VROF.CH_ESTADO='01' AND VROF.IN_CODIGO_ROF=? ORDER BY VROF.DT_FECHA_CREA DESC";
            //System.out.println(sql);
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, codRof);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               //SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");
                FormatoFecha fechWeb=new FormatoFecha();
               orpro_ta_versiones_rof obj = new orpro_ta_versiones_rof();
               obj.setIn_codigo_versiones(rs.getInt("IN_CODIGO_VERSIONES"));
               obj.setVc_nombre_archivo(rs.getString("VC_NOMBRE_ARCHIVO"));
               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setNomEstado(rs.getString("NOMBRE_ESTADO"));
               obj.setNomArchivoDB(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setDt_fecha_crea(fechWeb.getFormatoFecha(rs.getDate("DT_FECHA_CREA")));
               obj.setNomUsuario(rs.getString("NOM_USUARIO")+" "+rs.getString("APP_USUARIO"));
               listaVerRof.add(obj);
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
       return listaVerRof;
    }
}
