package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_organo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/*
 * @author marco
 */
public class orgen_ta_organo_DAO
{
    private DataSource ds;
    private Connection cn;
    
    public orgen_ta_organo_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean cambiarEstado(orgen_ta_organo obj )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_ORGANO_ELIMINAR(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ORGANO", obj.getIn_codigo_organo()        );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado()      );
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

    public boolean modificarOrgano(orgen_ta_organo obj,String nomUsu, String codUsu)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_ORGANO_MODIFICAR(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ORGANO", obj.getIn_codigo_organo()         );
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase().trim()                 );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase().trim()                 );
            cstm.setString("P_VC_USUARIO_MODIFICA", nomUsu );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(codUsu));
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

    public orgen_ta_organo getOrgano(String in_codigo_usuario)
    {
        System.out.println("metodo getUsuario ");
        orgen_ta_organo obj = new orgen_ta_organo();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_ORGANO,VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA FROM ORGEN_TA_ORGANO WHERE IN_CODIGO_ORGANO = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo_usuario));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_organo(rs.getInt("IN_CODIGO_ORGANO"));
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

    public ArrayList<orgen_ta_organo> getOrganos()
    {
        ArrayList<orgen_ta_organo> listaOrganos = null;
        try
        {
            listaOrganos = new ArrayList<orgen_ta_organo>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_ORGANO,VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA FROM ORGEN_TA_ORGANO";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_organo obj = new orgen_ta_organo();
               obj.setIn_codigo_organo( Integer.parseInt( rs.getString("IN_CODIGO_ORGANO") ));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
               obj.setDt_fecha_crea(rs.getString("DT_FECHA_CREA"));
               obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA")); 
               obj.setDt_usuario_modifica("DT_USUARIO_MODIFICA");
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

    public ArrayList<orgen_ta_organo> getOrganosActivos()
    {
        ArrayList<orgen_ta_organo> listaOrganos = null;
        try
        {
            listaOrganos = new ArrayList<orgen_ta_organo>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_ORGANO,VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO FROM ORGEN_TA_ORGANO WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_organo obj = new orgen_ta_organo();
               obj.setIn_codigo_organo( Integer.parseInt( rs.getString("IN_CODIGO_ORGANO") ));
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

    public boolean guardarOrgano(orgen_ta_organo obj,String nomUsu,String codUsu)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_ORGANO_INSERT(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase()     );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase()     );
            cstm.setString("P_VC_USUARIO_CREA", nomUsu );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(codUsu));
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
