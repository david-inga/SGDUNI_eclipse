package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_cargo_usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/*
 * @author marco
 */
public class orgen_ta_cargo_usuario_DAO
{
    private DataSource ds;
    private Connection cn;

    public orgen_ta_cargo_usuario_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean modificarCargo(orgen_ta_cargo_usuario obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CARGO_USUARIO_MOD(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CARGO_ESTRUC", obj.getIn_codigo_cargo_estruc()      );
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().trim()                 );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().trim()       );
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

    public boolean cambiarEstado(orgen_ta_cargo_usuario obj,String idUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CARGO_USUARIO_ELI(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CARGO_ESTRUC", obj.getIn_codigo_cargo_estruc()       );
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
            System.out.println("Error al cambiar el estado del cargo de usuario: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al cambiar el estado del cargo de usuario: "+ex);
            }
       }
       return exito;
    }

    public boolean guardarCargoEstructural(orgen_ta_cargo_usuario obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_CARGO_USUARIO_INSE(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().trim()     );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().trim()     );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsuario ) );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );

            cstm.executeUpdate();
            cn.commit();
            estado = true;
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al guardar el nuevo cargo de usuario: "+e);
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
                System.out.println("finally - Error al guardar el nuevo cargo de usuario: "+ex);
            }
            return  estado;
       }
    }

    public orgen_ta_cargo_usuario getCargoUsuarioSegunCodigo(String in_codigo)
    {
        orgen_ta_cargo_usuario obj = new orgen_ta_cargo_usuario();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_CARGO_ESTRUC,VC_NOMBRE,VC_DESCRIPCION FROM ORGEN_TA_CARGO_USUARIO WHERE IN_CODIGO_CARGO_ESTRUC = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
               obj.setIn_codigo_cargo_estruc(  rs.getInt("IN_CODIGO_CARGO_ESTRUC") );
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));            
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al obtener el cargo estructural segun el id "+in_codigo+": "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex)
            { System.out.println("Error al obtener el cargo estructural segun el id "+in_codigo+": "+ex);}
       }
        return obj;
    }

    public ArrayList<orgen_ta_cargo_usuario > getCargosEstructurales()
    {
        ArrayList<orgen_ta_cargo_usuario> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_cargo_usuario>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_CARGO_ESTRUC,VC_NOMBRE,VC_DESCRIPCION,CH_ESTADO FROM ORGEN_TA_CARGO_USUARIO";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_cargo_usuario obj = new orgen_ta_cargo_usuario();
               obj.setIn_codigo_cargo_estruc(rs.getInt("IN_CODIGO_CARGO_ESTRUC"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               lista.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al obtener los cargos de usuario: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex)
            { System.out.println("Error al obtener los cargos de usuario: "+ex);}
       }
       return lista;
    }

    public ArrayList<orgen_ta_cargo_usuario > getCargosEstructuralesActivos()
    {
        ArrayList<orgen_ta_cargo_usuario> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_cargo_usuario>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_CARGO_ESTRUC,VC_NOMBRE FROM ORGEN_TA_CARGO_USUARIO WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_cargo_usuario obj = new orgen_ta_cargo_usuario();
               obj.setIn_codigo_cargo_estruc(  rs.getInt("IN_CODIGO_CARGO_ESTRUC") );
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               lista.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al obtener los cargos de usuario activos : "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("finally - Error al obtener los cargos de usuario activos: "+ex);}
       }
       return lista;
    }

    //Cargo Estructural segun usuarios

    public ArrayList<orgen_ta_cargo_usuario > getCargosEstructuralesSegunUsuario(int idUsuario)
    {
        ArrayList<orgen_ta_cargo_usuario> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_cargo_usuario>();
            cn = ds.getConnection();
            String sql = "SELECT CE.IN_CODIGO_CARGO_ESTRUC,CE.VC_NOMBRE,UCE.IN_CODIGO_USUARIO " +
                    "   FROM ORGEN_TA_CARGO_ESTRUCTURAL CE " +
                    "  LEFT JOIN ORGEN_TA_USUARIO_CARG_EST UCE " +
                    "  ON UCE.IN_CODIGO_CARGO_ESTRUC=CE.IN_CODIGO_CARGO_ESTRUC AND UCE.IN_CODIGO_USUARIO=?" +
                    "  WHERE CE.CH_ESTADO='01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idUsuario);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_cargo_usuario obj = new orgen_ta_cargo_usuario();
               obj.setIn_codigo_cargo_estruc( rs.getInt("IN_CODIGO_CARGO_ESTRUC"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
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

        //Borrar toda LA CONFIGURACION POR USUARIO
      public boolean eliminarUsuarioCargoEstructural(int idUsu)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_USUARIO_CARGO_ELI(?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_USUARIO",idUsu);
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
    //Guardar CARGO ESTRUCTURAL - USUARIO
       public boolean guardarUsuarioCargoEstructural(int idUsu,int idDepFac,String tipoDepFac)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_USUARIO_CARGO_INS(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_USUARIO",idUsu);
            cstm.setInt("P_IN_CODIGO_DEPEN_FACU",idDepFac);
            cstm.setString("P_CH_TIPO_DEPEN_FACU",tipoDepFac);
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
    //Buscar si el usuario existe en cargo estrucutrado
    private int in_codigo_depen_facu;
    private String tipo_depen_facu;
    
    public boolean buscarUsuarioCargoEstructural(int idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_DEPEN_FACU,CH_TIPO_DEPEN_FACU FROM ORGEN_TA_USUARIO_DEPEN_FACU WHERE IN_CODIGO_USUARIO=? AND @ROWNUM = 1";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idUsuario);            
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
              this.in_codigo_depen_facu=rs.getInt("IN_CODIGO_DEPEN_FACU");
              this.tipo_depen_facu=rs.getString("CH_TIPO_DEPEN_FACU");
              estado = true;
            }
            
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
            return  estado;
       }
    }

    

    public int getIn_codigo_depen_facu() {
        return in_codigo_depen_facu;
    }

    public void setIn_codigo_depen_facu(int in_codigo_depen_facu) {
        this.in_codigo_depen_facu = in_codigo_depen_facu;
    }

    public String getTipo_depen_facu() {
        return tipo_depen_facu;
    }

    public void setTipo_depen_facu(String tipo_depen_facu) {
        this.tipo_depen_facu = tipo_depen_facu;
    }
}
