package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_rol;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import javax.sql.DataSource;

/*
 * @author marco
 */
public class orgen_ta_rol_DAO
{
    protected DataSource ds;
    Connection cn;
    public orgen_ta_rol_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean eliminarRol(orgen_ta_rol obj )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_ROL_ELIMINAR(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_ROL", obj.getCh_codigo_rol()        );
            cstm.setString("P_CH_ESTADO", obj.getEstado()       );
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

     public boolean modificarRol(orgen_ta_rol obj,String usu,String codUsu)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_ROL_MODIFICAR(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_ROL", obj.getCh_codigo_rol().toUpperCase()         );
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase()                 );
            cstm.setString("P_VC_USUARIO_MODIFICA", usu );
            cstm.setInt("P_IN_CODIGO_USU_LOG", Integer.parseInt(codUsu) );
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

    public boolean guardarRol(orgen_ta_rol obj,String usu,String id)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_ROL_INSERT(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_ROL", obj.getCh_codigo_rol().toUpperCase()         );
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase()                 );
            cstm.setString("P_VC_USUARIO_CREA", usu     );
            cstm.setInt("P_IN_CODIGO_USU_LOG", Integer.parseInt(id) );
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

    public ArrayList<orgen_ta_rol> getRoles()
    {
        ArrayList<orgen_ta_rol> listaRoles = null;
        try
        {
            listaRoles = new ArrayList<orgen_ta_rol>();
            cn = ds.getConnection();
            String sql = "SELECT CH_CODIGO_ROL,VC_NOMBRE,CH_ESTADO,VC_USUARIO_CREA,VC_USUARIO_MODIFICA,DT_FECHA_CREA,DT_FECHA_MODIFICA FROM ORGEN_TA_ROL";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_rol obj = new orgen_ta_rol();
               obj.setCh_codigo_rol(rs.getString("CH_CODIGO_ROL"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setEstado(rs.getString("CH_ESTADO"));
               obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
               obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
               obj.setDt_fecha_crea(rs.getString("DT_FECHA_CREA"));
               obj.setDt_fecha_modifica(rs.getString("DT_FECHA_MODIFICA"));
               listaRoles.add(obj);
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
       return listaRoles;
    }

    public orgen_ta_rol getRol(String ch_codigo_rol )
    {
        orgen_ta_rol obj = new orgen_ta_rol();
        try
        {         
            cn = ds.getConnection();
            CallableStatement cstm = cn.prepareCall("{ CALL SP_ORGEN_TA_ROL_SELECT(?,?,?,?,?,?,?,?) }");
            cstm.setString("P_CH_CODIGO_ROL_I", ch_codigo_rol);
            cstm.registerOutParameter("P_CH_CODIGO_ROL",Types.VARCHAR );
            cstm.registerOutParameter("P_VC_NOMBRE",Types.VARCHAR );
            cstm.registerOutParameter("P_CH_ESTADO",Types.CHAR );
            cstm.registerOutParameter("P_VC_USUARIO_CREA",Types.VARCHAR );
            cstm.registerOutParameter("P_VC_USUARIO_MODIFICA",Types.VARCHAR );
            cstm.registerOutParameter("P_DT_FECHA_CREA",Types.DATE );
            cstm.registerOutParameter("P_DT_FECHA_MODIFICA",Types.DATE );

	     	cstm.execute();
	     	cn.commit();

            obj.setCh_codigo_rol(cstm.getString("P_CH_CODIGO_ROL").trim());
            obj.setVc_nombre( cstm.getString("P_VC_NOMBRE").trim());
            obj.setEstado(cstm.getString("P_CH_ESTADO").trim());
            obj.setVc_usuario_crea( cstm.getString("P_VC_USUARIO_CREA").trim() );
            obj.setVc_usuario_modifica( cstm.getString("P_VC_USUARIO_MODIFICA").trim() );
            obj.setDt_fecha_crea(String.valueOf(cstm.getDate("P_DT_FECHA_CREA")).trim());
            obj.setDt_fecha_modifica(String.valueOf(cstm.getDate("P_DT_FECHA_MODIFICA")).trim());
            cstm.close();
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

    public ArrayList<orgen_ta_rol> getNombreRoles()
    {
        ArrayList<orgen_ta_rol> listaRoles = null;
        try
        {
            listaRoles = new ArrayList<orgen_ta_rol>();
            cn = ds.getConnection();
            String sql = "SELECT CH_CODIGO_ROL,VC_NOMBRE FROM ORGEN_TA_ROL WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_rol obj = new orgen_ta_rol();
               obj.setCh_codigo_rol(rs.getString("CH_CODIGO_ROL"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               listaRoles.add(obj);
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
       return listaRoles;
    }
    //fallaa
    public ArrayList<orgen_ta_rol> getNombreRolesUsuario(String coduser)
    {
        ArrayList<orgen_ta_rol> listaRolesUsuario = null;
        try
        {
            listaRolesUsuario = new ArrayList<orgen_ta_rol>();
            cn = ds.getConnection();
            String sql = "SELECT R.CH_CODIGO_ROL,U.IN_CODIGO_USUARIO,U.VC_USUARIO,R.VC_NOMBRE FROM ORGEN_TA_USUARIO_ROL_FUN RF INNER JOIN ORGEN_TA_USUARIO U ON RF.IN_CODIGO_USUARIO = U.IN_CODIGO_USUARIO AND U.IN_CODIGO_USUARIO = ? right OUTER JOIN  ORGEN_TA_ROL R ON RF.CH_CODIGO_ROL = R.CH_CODIGO_ROL ORDER BY R.CH_CODIGO_ROL ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(coduser));
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_rol obj = new orgen_ta_rol();
               obj.setCh_codigo_rol(rs.getString("CH_CODIGO_ROL"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
               listaRolesUsuario.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("m - Failed to execute a JDBC task: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("m - Failed to finalize JDBC task: "+ex);}
       }
       return listaRolesUsuario;
    }

    ////    bien
    public ArrayList<orgen_ta_rol> getRolesSegunUsuario(int in_usuario)
    {
        ArrayList<orgen_ta_rol> listaRolesUsuario = null;
        try
        {
            listaRolesUsuario = new ArrayList<orgen_ta_rol>();
            cn = ds.getConnection();
            String sql = "SELECT R.CH_CODIGO_ROL,R.VC_NOMBRE FROM ORGEN_TA_USUARIO U INNER JOIN ORGEN_TA_USUARIO_ROL_FUN RU ON  RU.IN_CODIGO_USUARIO=U.IN_CODIGO_USUARIO INNER JOIN ORGEN_TA_ROL R ON R.CH_CODIGO_ROL=RU.CH_CODIGO_ROL AND R.CH_ESTADO='01' WHERE U.IN_CODIGO_USUARIO=? AND U.CH_ESTADO='01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1,in_usuario);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_rol obj = new orgen_ta_rol();
               obj.setCh_codigo_rol(rs.getString("CH_CODIGO_ROL"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));               
               listaRolesUsuario.add(obj);
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
       return listaRolesUsuario;
    }

   //PARA VALIDAR SI EL ROL PERTENECE AL USUARIO LOGUEADO - BIEN
    public boolean  getRoleSegunUsuarioValidar(int idUsuario,String idRol)
    {
        boolean exito=false;
        try
        {
            
            cn = ds.getConnection();
            String sql = "SELECT R.CH_CODIGO_ROL,R.VC_NOMBRE FROM ORGEN_TA_USUARIO U " +
                         "  INNER JOIN ORGEN_TA_USUARIO_ROL_FUN RU ON  RU.IN_CODIGO_USUARIO=U.IN_CODIGO_USUARIO" +
                         "  INNER JOIN ORGEN_TA_ROL R ON R.CH_CODIGO_ROL=RU.CH_CODIGO_ROL AND R.CH_ESTADO='01' " +
                         " WHERE U.IN_CODIGO_USUARIO="+idUsuario+" AND R.CH_CODIGO_ROL='"+idRol+"'  AND U.CH_ESTADO='01'";
            //System.out.println("SQL RESUL "+sql);
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1,idUsuario);
            //pstm.setString(2,idRol);
            ResultSet rs = pstm.executeQuery();
            exito = rs.next();
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
       return exito;
    }


}
