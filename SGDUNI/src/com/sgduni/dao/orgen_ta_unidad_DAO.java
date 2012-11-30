package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_unidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/*
 * @author marco
 */
public class orgen_ta_unidad_DAO
{
    private DataSource ds;
    private Connection cn;

    public orgen_ta_unidad_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean modificarUnidad(orgen_ta_unidad obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_UNIDAD_MODIFICAR(?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt(   "P_IN_CODIGO_UNIDAD", obj.getIn_codigo_unidad()       );
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase().trim()                 );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase().trim()       );  
            cstm.setString("P_VC_USUARIO_MODIFICA", nomUsuario );
            cstm.setInt(   "P_IN_CODIGO_SUBDEP", obj.getIn_codigo_subdependecia()       );
            cstm.setInt(   "P_IN_CODIGO_USUARIO", Integer.parseInt(idUsuario));
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


    public boolean guardarUnidad(orgen_ta_unidad obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_UNIDAD_INSERT(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase()     );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase());
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
            cstm.setInt("P_IN_CODIGO_SUBDEP", obj.getIn_codigo_subdependecia() );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsuario ) );
           
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

    public boolean cambiarEstado(orgen_ta_unidad obj,String idUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_UNIDAD_ELIMINAR(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_UNIDAD", obj.getIn_codigo_unidad()        );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado()      );
            cstm.setInt("P_IN_CODIGO_USUARIO",Integer.parseInt(idUsu)     );
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

    public ArrayList<orgen_ta_unidad> getUnidades()
    {
        ArrayList<orgen_ta_unidad> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_unidad>();
            cn = ds.getConnection();
            String sql = "SELECT U.IN_CODIGO_UNIDAD,U.VC_NOMBRE,U.VC_DESCRIPCION,U.CH_ESTADO,S.VC_NOMBRE AS NOM_SUBDEP FROM ORGEN_TA_UNIDAD U INNER JOIN ORGEN_TA_SUBDEPENDENCIA S ON U.IN_CODIGO_SUBDEPENDENCIA = S.IN_CODIGO_SUBDEPENDENCIA";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_unidad obj = new orgen_ta_unidad();

               obj.setIn_codigo_unidad(rs.getInt("IN_CODIGO_UNIDAD"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setNombre_subdependencia( rs.getString("NOM_SUBDEP") );
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

    public orgen_ta_unidad getUnidad(String codigo)
    {
        // System.out.println("metodo getarea ");
        orgen_ta_unidad obj = new orgen_ta_unidad();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_UNIDAD,VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA,IN_CODIGO_SUBDEPENDENCIA FROM ORGEN_TA_UNIDAD WHERE IN_CODIGO_UNIDAD =?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(codigo));
            ResultSet rs = pstm.executeQuery();

            while( rs.next() )
            {
                obj.setIn_codigo_unidad(rs.getInt("IN_CODIGO_UNIDAD"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));
                obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
                obj.setCh_estado(rs.getString("CH_ESTADO"));
                obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
                obj.setDt_fecha_crea( String.valueOf( rs.getDate("DT_FECHA_CREA") ));
                obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
                obj.setVc_usuario_modifica( String.valueOf( rs.getDate("DT_USUARIO_MODIFICA") ));
                obj.setIn_codigo_subdependecia( rs.getInt("IN_CODIGO_SUBDEPENDENCIA") );
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

    public ArrayList<orgen_ta_unidad> getUnidadesActivas()
    {
        ArrayList<orgen_ta_unidad> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_unidad>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_UNIDAD,VC_NOMBRE,CH_ESTADO FROM ORGEN_TA_UNIDAD WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_unidad obj = new orgen_ta_unidad();

               obj.setIn_codigo_unidad(rs.getInt("IN_CODIGO_UNIDAD"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
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

}
