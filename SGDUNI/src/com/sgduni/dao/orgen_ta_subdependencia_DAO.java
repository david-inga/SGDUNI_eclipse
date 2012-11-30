/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_subdependencia;
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
public class orgen_ta_subdependencia_DAO
{
    protected DataSource ds;
    Connection cn;
    
    public orgen_ta_subdependencia_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean modificarSubDependencia(orgen_ta_subdependencia obj,String nomUsu,String codUsu)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "UPDATE ORGEN_TA_SUBDEPENDENCIA SET VC_NOMBRE = ?,VC_DESCRIPCION = ?,IN_CODIGO_DEPENDENCIA  = ?, VC_USUARIO_MODIFICA = ?,DT_USUARIO_MODIFICA = CURRENT_TIMESTAMP WHERE IN_CODIGO_SUBDEPENDENCIA = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1  , obj.getVc_nombre().trim().toString().toUpperCase() );
            pstm.setString(2  , obj.getVc_descripcion().trim().toString().toUpperCase());
            pstm.setInt(   3  , obj.getIn_codigo_dependecia() );
            pstm.setString(4  , nomUsu  );
            pstm.setInt(   5  , obj.getIn_codigo_subdependencia());
            pstm.executeUpdate();
            cn.commit();
            estado = true;
            pstm.close();
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

    public orgen_ta_subdependencia getSubDependencia(String in_codigo_subdepen)
    {
        System.out.println("metodo getDependencia");
        orgen_ta_subdependencia obj = new orgen_ta_subdependencia();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_SUBDEPENDENCIA,VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA,IN_CODIGO_DEPENDENCIA FROM ORGEN_TA_SUBDEPENDENCIA WHERE IN_CODIGO_SUBDEPENDENCIA =?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo_subdepen));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_subdependencia(rs.getInt("IN_CODIGO_SUBDEPENDENCIA"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));
                obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
                obj.setCh_estado(rs.getString("CH_ESTADO"));
                obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
                obj.setDt_fecha_crea( String.valueOf( rs.getDate("DT_FECHA_CREA") ));
                obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
                obj.setVc_usuario_modifica( String.valueOf( rs.getDate("DT_USUARIO_MODIFICA") ));
                obj.setIn_codigo_dependecia( rs.getInt("IN_CODIGO_DEPENDENCIA") );
            }
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
        return obj;
    }

    public boolean cambiarEstado(orgen_ta_subdependencia obj,String idUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_SUBDEPENDENCIA_ELI(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_SUBDEPENDENCIA", obj.getIn_codigo_subdependencia()        );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado()      );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsu  )     );
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

    public ArrayList<orgen_ta_subdependencia> getSubDependencias()
    {
        ArrayList<orgen_ta_subdependencia> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_subdependencia>();
            cn = ds.getConnection();
            String sql = "SELECT S.IN_CODIGO_SUBDEPENDENCIA,S.VC_NOMBRE,S.VC_DESCRIPCION,S.CH_ESTADO,D.VC_NOMBRE AS NOM_DEP FROM ORGEN_TA_SUBDEPENDENCIA S INNER JOIN ORGEN_TA_DEPENDENCIA D ON S.IN_CODIGO_DEPENDENCIA = D.IN_CODIGO_DEPENDENCIA ORDER BY S.VC_NOMBRE ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_subdependencia obj = new orgen_ta_subdependencia();

               obj.setIn_codigo_subdependencia(rs.getInt("IN_CODIGO_SUBDEPENDENCIA"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setNombre_dependencia(rs.getString("NOM_DEP"));
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
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Failed to finalize JDBC task: "+ex);}
       }
       return lista;
    }

    public ArrayList<orgen_ta_subdependencia> getSubDependenciasActivas()
    {
        ArrayList<orgen_ta_subdependencia> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_subdependencia>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_SUBDEPENDENCIA, VC_NOMBRE FROM ORGEN_TA_SUBDEPENDENCIA WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_subdependencia obj = new orgen_ta_subdependencia();

               obj.setIn_codigo_subdependencia(rs.getInt("IN_CODIGO_SUBDEPENDENCIA"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
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
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Failed to finalize JDBC task: "+ex);}
       }
       return lista;
    }


    public boolean guardarSubDependencia(orgen_ta_subdependencia obj,String nomUsuario,int idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_SUBDEPENDENCIA_INS(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase()     );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase()     );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsuario );
            cstm.setInt("P_IN_CODIGO_DEP", obj.getIn_codigo_dependecia() );
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
}
