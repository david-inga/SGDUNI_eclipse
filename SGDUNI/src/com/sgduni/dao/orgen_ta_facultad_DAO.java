package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_facultad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/*
 * @author marco
 */
public class orgen_ta_facultad_DAO
{
    private DataSource ds;
    private Connection cn;

    public orgen_ta_facultad_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean cambiarEstado(orgen_ta_facultad obj,String idUsu )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_FACULTAD_ELIMINAR(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_FACULTAD", obj.getIn_codigo_facultad()        );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado()      );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(idUsu)        );
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al cambiar de estado la facultad: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al cambiar de estado la facultad: "+ex);
            }
       }
       return exito;
    }
    
    public ArrayList<orgen_ta_facultad> getFacultades()
    {
        ArrayList<orgen_ta_facultad> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_facultad>();
            cn = ds.getConnection();
            String sql = "SELECT F.IN_CODIGO_FACULTAD,F.VC_NOMBRE,F.VC_ABREV_NOM,F.VC_DESCRIPCION,F.CH_ESTADO,O.VC_NOMBRE AS NOM_ORG " +
                    "FROM ORGEN_TA_FACULTAD F " +
                    "INNER JOIN ORGEN_TA_ORGANO O ON F.IN_CODIGO_ORGANO = O.IN_CODIGO_ORGANO " +
                    "ORDER BY F.IN_CODIGO_FACULTAD ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_facultad obj = new orgen_ta_facultad();
               obj.setIn_codigo_facultad( rs.getInt("IN_CODIGO_FACULTAD"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_abrev_nom(rs.getString("VC_ABREV_NOM"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setVc_nombre_organo(rs.getString("NOM_ORG"));
               lista.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al listar todas las facultades: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al listar todas las facultades: "+ex);}
       }
       return lista;
    }

    public ArrayList<orgen_ta_facultad> getAllFacultadesActivas()
    {
        ArrayList<orgen_ta_facultad> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_facultad>();
            cn = ds.getConnection();
            String sql = "SELECT F.IN_CODIGO_FACULTAD,F.VC_NOMBRE,F.VC_ABREV_NOM,F.VC_DESCRIPCION,F.CH_ESTADO,O.VC_NOMBRE AS NOM_ORG " +
                    "FROM ORGEN_TA_FACULTAD F " +
                    "INNER JOIN ORGEN_TA_ORGANO O ON F.IN_CODIGO_ORGANO = O.IN_CODIGO_ORGANO " +
                    " WHERE F.CH_ESTADO = '01' "+
                    "ORDER BY F.IN_CODIGO_FACULTAD ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_facultad obj = new orgen_ta_facultad();
               obj.setIn_codigo_facultad( rs.getInt("IN_CODIGO_FACULTAD"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_abrev_nom(rs.getString("VC_ABREV_NOM"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setVc_nombre_organo(rs.getString("NOM_ORG"));
               lista.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al listar todas las facultades: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al listar todas las facultades: "+ex);}
       }
       return lista;
    }

    //metodo para cargar combos
    public ArrayList<orgen_ta_facultad> getFacultadesActivas()
    {
        ArrayList<orgen_ta_facultad> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_facultad>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_FACULTAD,VC_NOMBRE " +
                    "FROM ORGEN_TA_FACULTAD " +
                    "WHERE CH_ESTADO = '01'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_facultad obj = new orgen_ta_facultad();
               obj.setIn_codigo_facultad( rs.getInt("IN_CODIGO_FACULTAD"));
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

    public boolean guardarFacultad(orgen_ta_facultad obj,String nomUsu,String idUsu )
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_FACULTAD_INSERT(?,?,?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE"      , obj.getVc_nombre() );
            cstm.setString("P_VC_DESCRIPCION" , obj.getVc_descripcion()   );
            cstm.setString("P_VC_USUARIO_CREA", nomUsu );
            cstm.setInt("P_IN_CODIGO_ORGANO"  , obj.getIn_codigo_organo() );
            cstm.setInt("P_IN_CODIGO_USUARIO" , Integer.parseInt(idUsu));
            cstm.setString("P_VC_ABREV_NOM",obj.getVc_abrev_nom() );
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
            return  estado;
       }
    }

    public boolean modificarFacultad(orgen_ta_facultad obj,String nomUsu,String codUsu)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_FACULTAD_MODIFICAR(?,?,?,?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_FACULTAD", obj.getIn_codigo_facultad()         );
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre().trim()                 );
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion()       );
            cstm.setInt("P_IN_CODIGO_ORGANO", obj.getIn_codigo_organo()         );
            cstm.setString("P_VC_ABREV_NOM",obj.getVc_abrev_nom() );
            cstm.setString("P_VC_USUARIO_MODIFICA", nomUsu );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(codUsu));
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

    //metodo para modificar la facultad
    public orgen_ta_facultad getFacultad(String in_codigo_facultad)
    {
        orgen_ta_facultad obj = new orgen_ta_facultad();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_FACULTAD,VC_NOMBRE,VC_ABREV_NOM,VC_DESCRIPCION,CH_ESTADO,VC_USUARIO_CREA,DT_FECHA_CREA,VC_USUARIO_MODIFICA,DT_USUARIO_MODIFICA,IN_CODIGO_ORGANO,VC_DIRECCION,VC_APARTADO,VC_TELEFONO,VC_TELEFONO_CENTRAL,VC_ANEXO,VC_CORREO FROM ORGEN_TA_FACULTAD WHERE IN_CODIGO_FACULTAD = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo_facultad));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_facultad(rs.getInt("IN_CODIGO_FACULTAD"));
                obj.setVc_nombre(rs.getString("VC_NOMBRE"));
                obj.setVc_abrev_nom(rs.getString("VC_ABREV_NOM"));
                obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
                obj.setCh_estado(rs.getString("CH_ESTADO"));
                obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
                obj.setDt_fecha_crea( String.valueOf( rs.getDate("DT_FECHA_CREA") ));
                obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
                obj.setVc_usuario_modifica( String.valueOf( rs.getDate("DT_USUARIO_MODIFICA") ));
                obj.setIn_codigo_organo( rs.getInt("IN_CODIGO_ORGANO") );
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
            System.out.println("Failed to execute a JDBC task: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Failed to finalize JDBC task: "+ex);}
       }
        return obj;
    }
}
