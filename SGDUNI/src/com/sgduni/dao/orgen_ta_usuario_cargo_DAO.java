package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orgen_ta_usuario_cargo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author JMarcos
 */
public class orgen_ta_usuario_cargo_DAO
{
    protected DataSource ds;
    Connection cn;

    public orgen_ta_usuario_cargo_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public ArrayList<orgen_ta_usuario_cargo> getNombreCargosDelUsuario(String coduser)
    {
        ArrayList<orgen_ta_usuario_cargo> listaCargosUsuario = null;
        try
        {
            listaCargosUsuario = new ArrayList<orgen_ta_usuario_cargo>();
            cn = ds.getConnection();
            String sql = "SELECT CU.IN_CODIGO_CARGO_ESTRUC,U.IN_CODIGO_USUARIO,CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS NOMBRE_USUARIO,CU.VC_NOMBRE AS NOMBRE_CARGO " +
                    "FROM ORGEN_TA_USUARIO_CARGO UC " +
                    "INNER JOIN ORGEN_TA_USUARIO U ON UC.IN_CODIGO_USUARIO = U.IN_CODIGO_USUARIO AND U.IN_CODIGO_USUARIO = ? " +
                    "RIGHT OUTER JOIN ORGEN_TA_CARGO_USUARIO CU ON UC.IN_CODIGO_CARGO = CU.IN_CODIGO_CARGO_ESTRUC " +
                    " WHERE CU.CH_ESTADO = '01' "+
                    "ORDER BY CU.VC_NOMBRE ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(coduser));
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_usuario_cargo obj = new orgen_ta_usuario_cargo();
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
               obj.setIn_codigo_cargo(rs.getInt("IN_CODIGO_CARGO_ESTRUC"));
               obj.setVc_nombre_usuario(rs.getString("NOMBRE_USUARIO"));
               obj.setVc_nombre_cargo(rs.getString("NOMBRE_CARGO"));
               listaCargosUsuario.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error al traer la lista de cargos: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error al traer la lista de cargos"+ex);}
       }
       return listaCargosUsuario;
    }

    //Guardar USUARIO-CARGO
    public boolean guardarCargodelUsuario(orgen_ta_usuario_cargo obj)
    {
        boolean exitoso=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_USU_CARGO_GUARDAR(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_USUARIO", obj.getIn_codigo_usuario());
            cstm.setInt("P_IN_CODIGO_CARGO", obj.getIn_codigo_cargo());
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
            exitoso=true;
        }
        catch(Exception e)
        {
           exitoso=false;
            System.out.println("Error al insertar el Cargo al Usuario: "+e);
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
                System.out.println("finally - Error al insertar el Cargo al Usuario: "+ex);
            }
            return exitoso;
       }

    }

    //Eliminar
    public void eliminarCargoDelUsuario(int cod_usuario)
    {
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "DELETE FROM ORGEN_TA_USUARIO_CARGO WHERE in_codigo_usuario=?";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt(1, cod_usuario);
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al eliminar el cargo del usuario: "+e);
        }
        finally
        {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al eliminar el cargo del usuario: "+ex);
            }
        }
    }


}
