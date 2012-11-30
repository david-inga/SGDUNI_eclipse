package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_requisitos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * @author marco
 */
public class orgen_ta_requisitos_DAO
{
    private DataSource ds;
    private Connection cn;

    public orgen_ta_requisitos_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean modificarRequisito(orgen_ta_requisitos obj,String usu,String codUsu)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_REQUISITOS_MODI(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_REQ",    obj.getIn_codigo_req()         );
            cstm.setString("P_VC_NOMBRE_REQ"           ,    obj.getVc_nombre_req().toUpperCase()        );
            cstm.setString("P_VC_DESCRIPCION_REQ"      ,    obj.getVc_descripcion_req().toUpperCase()    );
            cstm.setString("P_VC_USUARIO_MODIF" ,    usu                                      );
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
    
    public orgen_ta_requisitos getRequisito(String in_codigo_req)
    {
        System.out.println("metodo getUsuario ");
        orgen_ta_requisitos obj = new orgen_ta_requisitos();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_REQ,CH_CODIGO_REQ,VC_NOMBRE_REQ,VC_DESCRIPCION_REQ,CH_ESTADO FROM ORGEN_TA_REQUISITOS WHERE  IN_CODIGO_REQ = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo_req));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_req(rs.getInt("IN_CODIGO_REQ"));
                obj.setCh_codigo_req(rs.getString("CH_CODIGO_REQ"));
                obj.setVc_descripcion_req(rs.getString("VC_DESCRIPCION_REQ"));
                obj.setVc_nombre_req(rs.getString("VC_NOMBRE_REQ"));
                obj.setCh_estado(rs.getString("CH_ESTADO"));
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

    public boolean guardarRequisito( orgen_ta_requisitos obj , String nomUsuario , String idUsuario )
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_REQUISITOS_INSE(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);

            cstm.setString("P_CH_CODIGO_REQ", obj.getCh_codigo_req()     );
            cstm.setString("P_VC_NOMBRE_REQ", obj.getVc_nombre_req().toUpperCase()     );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion_req().toUpperCase()    );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsuario ) );
            cstm.setString("P_VC_NOMBRE_USUARIO", nomUsuario );

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

    public boolean cambiarEstado(orgen_ta_requisitos obj,String idUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_REQUISITOS_ELIMIN(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql); 
            cstm.setInt("P_IN_CODIGO_REQ", obj.getIn_codigo_req()        );
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

    public ArrayList<orgen_ta_requisitos> getRequisitos()
    {
        ArrayList<orgen_ta_requisitos> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_requisitos>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_REQ,CH_CODIGO_REQ,VC_NOMBRE_REQ,VC_DESCRIPCION_REQ,CH_ESTADO FROM ORGEN_TA_REQUISITOS";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_requisitos obj = new orgen_ta_requisitos();

               obj.setIn_codigo_req(rs.getInt("IN_CODIGO_REQ"));
               obj.setCh_codigo_req(rs.getString("CH_CODIGO_REQ"));
               obj.setVc_nombre_req(rs.getString("VC_NOMBRE_REQ"));
               obj.setVc_descripcion_req(rs.getString("VC_DESCRIPCION_REQ"));
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

        public ArrayList<orgen_ta_requisitos> getRequisitosActivos()
    {
        ArrayList<orgen_ta_requisitos> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_requisitos>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_REQ,CH_CODIGO_REQ,VC_NOMBRE_REQ,CH_ESTADO,VC_DESCRIPCION_REQ FROM ORGEN_TA_REQUISITOS WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_requisitos obj = new orgen_ta_requisitos();

               obj.setIn_codigo_req(rs.getInt("IN_CODIGO_REQ"));
               obj.setCh_codigo_req(rs.getString("CH_CODIGO_REQ"));
               obj.setVc_nombre_req(rs.getString("VC_NOMBRE_REQ"));
               obj.setVc_descripcion_req(rs.getString("VC_DESCRIPCION_REQ"));
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
