package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_dependencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * @author Marco
 */
public class orgen_ta_dependencia_DAO
{
    private DataSource ds;
    private Connection cn;

    public orgen_ta_dependencia_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public orgen_ta_dependencia getDependencias(String codigo)
    {
        //System.out.println("metodo getUsuario ");
        orgen_ta_dependencia obj = new orgen_ta_dependencia();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_DEPENDENCIA,VC_NOMBRE,VC_ABREV_NOM,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA,IN_CODIGO_ORGANO,VC_DIRECCION,VC_APARTADO,VC_TELEFONO,VC_TELEFONO_CENTRAL,VC_ANEXO,VC_CORREO FROM ORGEN_TA_DEPENDENCIA WHERE IN_CODIGO_DEPENDENCIA = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(codigo));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_dependencia(rs.getInt("IN_CODIGO_DEPENDENCIA"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));
                obj.setVc_abrev_nom(rs.getString("VC_ABREV_NOM"));
                obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
                obj.setCh_estado(rs.getString("CH_ESTADO"));
                obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
                obj.setDt_fecha_crea( String.valueOf( rs.getDate("DT_FECHA_CREA") ));
                obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
                obj.setVc_usuario_modifica( String.valueOf( rs.getDate("DT_USUARIO_MODIFICA") ));
                obj.setIn_codigo_organo( rs.getInt("IN_CODIGO_ORGANO") );
                //29/07/2012
                //MODIFICADO EL 29/07/2012
                obj.setVc_direccion(rs.getString("VC_DIRECCION"));
                obj.setVc_apartado(rs.getString("VC_APARTADO"));
                obj.setVc_telefono(rs.getString("VC_TELEFONO"));
                obj.setVc_telefono_central(rs.getString("VC_TELEFONO_CENTRAL"));
                obj.setVc_anexo(rs.getString("VC_ANEXO"));
                obj.setVc_correo(rs.getString("VC_CORREO"));
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al traer los datos de la dependencia: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Finally - Error al traer los datos de la dependencia: "+ex);}
       }
        return obj;
    }

    public ArrayList<orgen_ta_dependencia> getDependenciasActivas()
    {
        ArrayList<orgen_ta_dependencia> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_dependencia>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_DEPENDENCIA,VC_NOMBRE,CH_ESTADO FROM ORGEN_TA_DEPENDENCIA WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_dependencia obj = new orgen_ta_dependencia();

               obj.setIn_codigo_dependencia(rs.getInt("IN_CODIGO_DEPENDENCIA"));
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

    public boolean modificarDependencia(orgen_ta_dependencia obj,String nomUsuario,int idUsuario)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_DEPENDENCIA_MOD(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_DEPEN", obj.getIn_codigo_dependencia());
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase().trim());
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase().trim());
            cstm.setString("P_VC_USUARIO_MODIFICA", nomUsuario.trim().toString());
            cstm.setInt(   "P_IN_CODIGO_USUARIO", idUsuario );
            cstm.setString("P_VC_ABREV_NOM", obj.getVc_abrev_nom() );
            cstm.setInt   ("P_IN_CODIGO_ORGANO", obj.getIn_codigo_organo());
            //
            cstm.setString("P_VC_DIRECCION",obj.getVc_direccion() );
            cstm.setString("P_VC_APARTADO",obj.getVc_apartado() );
            cstm.setString("P_VC_TELEFONO",obj.getVc_telefono() );
            cstm.setString("P_VC_TELEFONO_CENTRAL",obj.getVc_telefono_central() );
            cstm.setString("P_VC_ANEXO",obj.getVc_anexo() );
            cstm.setString("P_VC_CORREO",obj.getVc_correo() );
            
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

    public boolean guardarDependencia(orgen_ta_dependencia obj,String nomUsuario,int idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_DEPENDENCIA_INSERT(?,?,?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().toUpperCase()     );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion().toUpperCase()     );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsuario );
            cstm.setInt("P_IN_CODIGO_ORGANO", obj.getIn_codigo_organo() );
            cstm.setString("P_VC_ABREV_NOM", obj.getVc_abrev_nom() );
            //
            cstm.setString("P_VC_DIRECCION",obj.getVc_direccion() );
            cstm.setString("P_VC_APARTADO",obj.getVc_apartado() );
            cstm.setString("P_VC_TELEFONO",obj.getVc_telefono() );
            cstm.setString("P_VC_TELEFONO_CENTRAL",obj.getVc_telefono_central() );
            cstm.setString("P_VC_ANEXO",obj.getVc_anexo() );
            cstm.setString("P_VC_CORREO",obj.getVc_correo() );
            cstm.executeUpdate();
            cn.commit();
            estado = true;
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error al guardar la depednencia : "+e);
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

    public boolean cambiarEstado(orgen_ta_dependencia obj,String codUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_DEPENDENCIA_ELIMIN(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_DEPENDENCIA", obj.getIn_codigo_dependencia()        );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado()      );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(codUsu)     );
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

    public ArrayList<orgen_ta_dependencia> getDependencias()
    {
        ArrayList<orgen_ta_dependencia> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_dependencia>();
            cn = ds.getConnection();
            String sql = "SELECT D.IN_CODIGO_DEPENDENCIA,D.VC_NOMBRE,D.VC_ABREV_NOM,D.VC_DESCRIPCION,D.CH_ESTADO,O.VC_NOMBRE AS NOM_ORG " +
                    "FROM ORGEN_TA_DEPENDENCIA D " +
                    "INNER JOIN ORGEN_TA_ORGANO O ON D.IN_CODIGO_ORGANO = O.IN_CODIGO_ORGANO " +
                    "ORDER BY D.IN_CODIGO_DEPENDENCIA ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_dependencia obj = new orgen_ta_dependencia();

               obj.setIn_codigo_dependencia(rs.getInt("IN_CODIGO_DEPENDENCIA"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setNombre_organo(rs.getString("NOM_ORG"));
               obj.setVc_abrev_nom(rs.getString("VC_ABREV_NOM"));
               lista.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error: getDependencias(): "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error: getDependencias(): "+ex);}
       }
       return lista;
    }

    public ArrayList<orgen_ta_dependencia> getAllDependenciasActivas()
    {
        ArrayList<orgen_ta_dependencia> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_dependencia>();
            cn = ds.getConnection();
            String sql = "SELECT D.IN_CODIGO_DEPENDENCIA,D.VC_NOMBRE,D.VC_ABREV_NOM,D.VC_DESCRIPCION,D.CH_ESTADO,O.VC_NOMBRE AS NOM_ORG " +
                    "FROM ORGEN_TA_DEPENDENCIA D " +
                    "INNER JOIN ORGEN_TA_ORGANO O ON D.IN_CODIGO_ORGANO = O.IN_CODIGO_ORGANO " +
                    "WHERE D.CH_ESTADO = '01' "+
                    "ORDER BY D.VC_NOMBRE ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_dependencia obj = new orgen_ta_dependencia();

               obj.setIn_codigo_dependencia(rs.getInt("IN_CODIGO_DEPENDENCIA"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setNombre_organo(rs.getString("NOM_ORG"));
               obj.setVc_abrev_nom(rs.getString("VC_ABREV_NOM"));
               lista.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error: getDependencias(): "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error: getDependencias(): "+ex);}
       }
       return lista;
    }
}
