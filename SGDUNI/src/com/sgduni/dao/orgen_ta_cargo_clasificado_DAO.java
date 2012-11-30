package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_cargo_clasificado;
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
public class orgen_ta_cargo_clasificado_DAO
{
    private DataSource ds;
    private Connection cn;

    public orgen_ta_cargo_clasificado_DAO(DataSource ds)
    {
        this.ds = ds;
    }


    public boolean modificarCargoClasificado(orgen_ta_cargo_clasificado obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CARGO_CLASI_MOD(?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CARGO_CLASI", obj.getIn_codigo_cargo_clasi()       );
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase().trim()                 );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase().trim()       );
            cstm.setInt("P_IN_CODIGO_AREA", obj.getIn_codigo_area()        );
            cstm.setString("P_VC_USUARIO_MODIFICA", nomUsuario );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(idUsuario));
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

    public boolean cambiarEstado(orgen_ta_cargo_clasificado obj,String idUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CARGO_CLASI_ELI(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CARGO_CLASI", obj.getIn_codigo_cargo_clasi()       );
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

    
    public orgen_ta_cargo_clasificado getCargoClasificado(String in_codigo)
    {
        orgen_ta_cargo_clasificado obj = new orgen_ta_cargo_clasificado();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_CARGO_CLASIF,VC_NOMBRE,VC_DESCRIPCION,IN_CODIGO_AREA,CH_ESTADO, VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA FROM ORGEN_TA_CARGO_CLASIFICADO WHERE IN_CODIGO_CARGO_CLASIF =?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_cargo_clasi(rs.getInt("IN_CODIGO_CARGO_CLASIF"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));
                obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
                obj.setIn_codigo_area(rs.getInt("IN_CODIGO_AREA"));
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

    public ArrayList<orgen_ta_cargo_clasificado> getgetCargosClasificados()
    {
        ArrayList<orgen_ta_cargo_clasificado> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_cargo_clasificado>();
            cn = ds.getConnection();
            String sql = "SELECT C.IN_CODIGO_CARGO_CLASIF,C.VC_NOMBRE,C.VC_DESCRIPCION,C.CH_ESTADO,AR.VC_NOMBRE AS NOM_AREA FROM ORGEN_TA_CARGO_CLASIFICADO C INNER JOIN ORGEN_TA_AREA AR ON C.IN_CODIGO_AREA = AR.IN_CODIGO_AREA";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_cargo_clasificado obj = new orgen_ta_cargo_clasificado();
               obj.setIn_codigo_cargo_clasi( Integer.parseInt( rs.getString("IN_CODIGO_CARGO_CLASIF") ));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setNombre_area(rs.getString("NOM_AREA"));
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

    public ArrayList<orgen_ta_cargo_clasificado> getCargosClasificadosActivos()
    {
        ArrayList<orgen_ta_cargo_clasificado> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_cargo_clasificado>();
            cn = ds.getConnection();
            String sql = " SELECT IN_CODIGO_CARGO_CLASIF,VC_NOMBRE FROM ORGEN_TA_CARGO_CLASIFICADO WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_cargo_clasificado obj = new orgen_ta_cargo_clasificado();
               obj.setIn_codigo_cargo_clasi( Integer.parseInt( rs.getString("IN_CODIGO_CARGO_CLASIF") ));
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

    public boolean guardarCargoClasificado(orgen_ta_cargo_clasificado obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CARGO_CLASI_INSERT(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase()     );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase()     );
            cstm.setInt("P_IN_CODIGO_AREA", obj.getIn_codigo_area() );
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

    

}
