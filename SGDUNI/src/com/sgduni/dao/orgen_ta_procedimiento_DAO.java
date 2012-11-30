package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_procedimiento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * @author marco
 */

public class orgen_ta_procedimiento_DAO
{
    protected DataSource ds;
    Connection cn;
    
    public orgen_ta_procedimiento_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean modificarProcedimiento(orgen_ta_procedimiento obj,String usu,String codUsu)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_PROCEDIMIENTO_MODI(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_PROCEDIMIENTO",    obj.getIn_codigo_procedimiento()         );
            cstm.setString("P_VC_NOMBRE"           ,    obj.getVc_nombre().toUpperCase()         );
            cstm.setString("P_VC_DESCRIPCION"      ,    obj.getVc_descripcion().toUpperCase()    );
            cstm.setString("P_VC_USUARIO_MOD" ,    usu                                      );
            cstm.setInt   ("P_IN_CODIGO_USUARIO"   ,    Integer.parseInt(codUsu)                 );
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

     public orgen_ta_procedimiento getProcedimiento(String in_codigo_procedimiento)
    {
        System.out.println("metodo getUsuario ");
        orgen_ta_procedimiento obj = new orgen_ta_procedimiento();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_PROCEDIMIENTO,VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA FROM ORGEN_TA_PROCEDIMIENTO WHERE IN_CODIGO_PROCEDIMIENTO = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo_procedimiento));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_procedimiento(rs.getInt("IN_CODIGO_PROCEDIMIENTO"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));
                obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
                obj.setCh_estado(rs.getString("CH_ESTADO"));
                obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
                obj.setDt_fecha_crea( String.valueOf( rs.getDate("DT_FECHA_CREA") ));
                obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
                obj.setVc_usuario_modifica( String.valueOf( rs.getDate("DT_USUARIO_MODIFICA") ));
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




    public boolean guardarProcedimiento( orgen_ta_procedimiento obj , String nomUsuario , String idUsuario )
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_PROCEDIMIENTO_INSE(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);

            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase()     );
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

    public boolean cambiarEstado(orgen_ta_procedimiento obj,String idUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_PROCEDIMIEN_ELIMIN(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_PROCEDIMIENTO", obj.getIn_codigo_procedimiento()        );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado()      );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(idUsu)      );
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

    public ArrayList<orgen_ta_procedimiento> getProcedimientos()
    {
        ArrayList<orgen_ta_procedimiento> listaOrganos = null;
        try
        {
            listaOrganos = new ArrayList<orgen_ta_procedimiento>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_PROCEDIMIENTO,VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO FROM ORGEN_TA_PROCEDIMIENTO";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_procedimiento obj = new orgen_ta_procedimiento();

               obj.setIn_codigo_procedimiento(rs.getInt("IN_CODIGO_PROCEDIMIENTO"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               listaOrganos.add(obj);
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
       return listaOrganos;
    }

    public ArrayList<orgen_ta_procedimiento> getProcedimientosActivos()
    {
        ArrayList<orgen_ta_procedimiento> listaOrganos = null;
        try
        {
            listaOrganos = new ArrayList<orgen_ta_procedimiento>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_PROCEDIMIENTO,VC_NOMBRE,VC_DESCRIPCION FROM ORGEN_TA_PROCEDIMIENTO WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_procedimiento obj = new orgen_ta_procedimiento();

               obj.setIn_codigo_procedimiento(rs.getInt("IN_CODIGO_PROCEDIMIENTO"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               listaOrganos.add(obj);
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
       return listaOrganos;
    }
}
