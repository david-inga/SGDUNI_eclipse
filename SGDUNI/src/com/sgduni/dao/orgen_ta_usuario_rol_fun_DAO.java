package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_rol;
import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orgen_ta_usuario_rol_fun;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * @author marco
 */
public class orgen_ta_usuario_rol_fun_DAO
{
    protected DataSource ds;
    Connection cn;
    
    public orgen_ta_usuario_rol_fun_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public ArrayList<orgen_ta_rol> getNombreRolesSegunUsuario(orgen_ta_usuario objU)
    {
        ArrayList<orgen_ta_rol> listaRoles = null;
        try
        {
            listaRoles = new ArrayList<orgen_ta_rol>();
            cn = ds.getConnection();
            String sql = "SELECT CH_CODIGO_ROL,VC_NOMBRE FROM ORGEN_TA_ROL WHERE CH_ESTADO = '01'  ORDER BY VC_NOMBRE ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while( rs.next() )
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
    //Guardar USUARIO-ROL
    public boolean guardarRol(orgen_ta_usuario_rol_fun obj)
    {
        boolean exitoso=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_USU_ROL_FUN_INSERT(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_ROL", obj.getCh_codigo_rol().toUpperCase());
            cstm.setInt("P_IN_CODIGO_USUARIO", obj.getIn_usuario_codigo());
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
            exitoso=true;
        }
        catch(Exception e)
        {
           exitoso=false;
            System.out.println("Error al insertar el ROL al Usuario: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
               exitoso=false; 
                System.out.println("finally - Error al insertar el ROL al Usuario: "+ex);
            }
            return exitoso;
       }
       
    }

    //Eliminar
        public void eliminarRolUsuario(int cod_usuario)
    {
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "DELETE FROM orgen_ta_usuario_rol_fun WHERE in_codigo_usuario=?";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt(1, cod_usuario);
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al eliminar los roles del usuario: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al eliminar los roles del usuario: "+ex);
            }
       }
    }
}
