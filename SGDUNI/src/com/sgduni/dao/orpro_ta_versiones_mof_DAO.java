/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;
import com.sgduni.forms.orpro_ta_versiones_mof;
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
public class orpro_ta_versiones_mof_DAO {
    protected DataSource ds;
    Connection cn;
    public orpro_ta_versiones_mof_DAO(DataSource ds)
    {
        this.ds = ds;
    }
    //Eliminar
    public boolean eliminarVersionMof(orpro_ta_versiones_mof obj )
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
     public boolean modificarVersionEstadoMof(int idRof,int idEstado)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_VERS_MOF_EST_MODI(?,?) }";
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
    public boolean guardarVersionMof(orpro_ta_versiones_mof obj,int idUsu,int idEstado,String nomArchivoDB)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_VERS_MOF_INSERT(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            //System.out.println("Cod mof:"+obj.getIn_codigo_mof()+" Id Estado:"+idEstado+" id Usu:"+idUsu);
            cstm.setInt("P_IN_CODIGO_MOF", obj.getIn_codigo_mof());
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
    public ArrayList<orpro_ta_versiones_mof> getListaVersionesMof(int codMof)
    {
        ArrayList<orpro_ta_versiones_mof> listaVerMof = null;
        try
        {
            listaVerMof = new ArrayList<orpro_ta_versiones_mof>();
            cn = ds.getConnection();
            String sql = "SELECT VMOF.IN_CODIGO_VERSIONES,VMOF.VC_NOMBRE_ARCHIVO,VMOF.VC_RUTA_ARCHIVO," +
                         " VMOF.DT_FECHA_CREA," +
                         " U.VC_NOMBRES AS NOM_USUARIO,U.VC_APELLIDO_PATERNO AS APP_USUARIO,U.VC_APELLIDO_MATERNO AS APM_USUARIO" +
                         " ,E.VC_NOMBRE_ESTADO AS NOMBRE_ESTADO,E.IN_CODIGO_ESTADO " +
                         " FROM ORPRO_TA_VERSIONES_MOF VMOF" +
                         " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=VMOF.IN_CODIGO_USUARIO" +
                         " INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=VMOF.IN_CODIGO_ESTADO" +
                         " WHERE VMOF.CH_ESTADO='01' AND VMOF.IN_CODIGO_MOF=? ORDER BY VMOF.DT_FECHA_CREA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, codMof);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               //SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");
                FormatoFecha fechWeb=new FormatoFecha();
               orpro_ta_versiones_mof obj = new orpro_ta_versiones_mof();
               obj.setIn_codigo_versiones(rs.getInt("IN_CODIGO_VERSIONES"));
               obj.setVc_nombre_archivo(rs.getString("VC_NOMBRE_ARCHIVO"));
               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setNomEstado(rs.getString("NOMBRE_ESTADO"));
               obj.setNomArchivoDB(rs.getString("VC_RUTA_ARCHIVO"));
              obj.setDt_fecha_crea(fechWeb.getFormatoFecha(rs.getDate("DT_FECHA_CREA")));
               obj.setNomUsuario(rs.getString("NOM_USUARIO")+" "+rs.getString("APP_USUARIO"));
               listaVerMof.add(obj);
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
       return listaVerMof;
    }
}
