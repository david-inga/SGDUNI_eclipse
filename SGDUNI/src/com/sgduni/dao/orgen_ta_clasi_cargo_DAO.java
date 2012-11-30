package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_clasi_cargo;
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
public class orgen_ta_clasi_cargo_DAO
{
    private DataSource ds;
    private Connection cn;

    public orgen_ta_clasi_cargo_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public orgen_ta_clasi_cargo getClasiCargo(String in_codigo)
    {
        orgen_ta_clasi_cargo obj = new orgen_ta_clasi_cargo();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_CLASI, VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA FROM ORGEN_TA_CLASI_CARGO WHERE IN_CODIGO_CLASI = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_clasi(rs.getInt("IN_CODIGO_CLASI"));
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

    public ArrayList<orgen_ta_clasi_cargo> getClasiCargo()
    {
        ArrayList<orgen_ta_clasi_cargo> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_clasi_cargo>();
            cn = ds.getConnection();
            String sql = " SELECT IN_CODIGO_CLASI, VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA FROM ORGEN_TA_CLASI_CARGO";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_clasi_cargo obj = new orgen_ta_clasi_cargo();
               obj.setIn_codigo_clasi( Integer.parseInt( rs.getString("IN_CODIGO_CLASI") ));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
               obj.setDt_fecha_crea(rs.getString("DT_FECHA_CREA"));
               obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
               obj.setDt_usuario_modifica("DT_USUARIO_MODIFICA");
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

    public ArrayList<orgen_ta_clasi_cargo> getClasiCargoActivos()
    {
        ArrayList<orgen_ta_clasi_cargo> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_clasi_cargo>();
            cn = ds.getConnection();
            String sql = " SELECT IN_CODIGO_CLASI, VC_NOMBRE FROM ORGEN_TA_CLASI_CARGO WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_clasi_cargo obj = new orgen_ta_clasi_cargo();
               obj.setIn_codigo_clasi( Integer.parseInt( rs.getString("IN_CODIGO_CLASI") ));
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

    public boolean cambiarEstado(orgen_ta_clasi_cargo obj,String idUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CLASI_CARGO_ELI(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CLASI", obj.getIn_codigo_clasi()       );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado()      );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(idUsu)     );
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


    public boolean guardarClasiCargo( orgen_ta_clasi_cargo obj , String nomUsuario , String idUsuario )
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CLASI_CARGO_INSE(?,?,?,?) }";
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


    public boolean modificarClasiCargo(orgen_ta_clasi_cargo obj,String usu,String codUsu)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CLASI_CARGO_MOD(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CLASI",    obj.getIn_codigo_clasi()         );
            cstm.setString("P_VC_NOMBRE"           ,    obj.getVc_nombre().toUpperCase()         );
            cstm.setString("P_VC_DESCRIPCION"      ,    obj.getVc_descripcion().toUpperCase()    );
            cstm.setString("P_VC_USUARIO_MODIFICA" ,    usu                                      );
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


}
