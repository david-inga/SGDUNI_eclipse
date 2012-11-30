package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_documento;
import com.sgduni.utilitarios.FormatoFecha;
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
public class orgen_ta_documento_DAO {

    protected DataSource ds;
    Connection cn;
    public orgen_ta_documento_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean eliminarDocument(int idDoc)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_DOCUMENT_ELIMIN(?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_DOC",idDoc);
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
   //MODIFICAR
     public boolean modificarDocument(orgen_ta_documento obj,int idUsu)
    {
         boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_DOCUMENT_MODIFC(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_DOC",obj.getIn_codigo_doc());
            cstm.setString("P_VC_NOMBRE",obj.getVc_nombre());
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion() );
            cstm.setInt("P_IN_CODIGO_USUARIO_MOFI", idUsu );
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
    //Guardar
    public boolean guardarDocument(orgen_ta_documento obj,int idUsu)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_DOCUMENT_INSERT(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_VC_NOMBRE", obj.getVc_nombre());
            cstm.setString("P_VC_DESCRIPCION", obj.getVc_descripcion());
            cstm.setInt("IN_CODIGO_USUARIO_CREA",idUsu);
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

    public ArrayList<orgen_ta_documento> getListaDocument()
    {
        ArrayList<orgen_ta_documento> listar = null;
        try
        {
            listar = new ArrayList<orgen_ta_documento>();
            cn = ds.getConnection();
            String sql = "SELECT " +
                        "  CF.IN_CODIGO_DOC,CF.VC_NOMBRE,CF.VC_DESCRIPCION,CF.DT_FECHA_CREA,CF.DT_FECHA_MODIF,CF.CH_ESTADO," +
                        "  (SELECT UCREA.VC_NOMBRES||' '||UCREA.VC_APELLIDO_PATERNO||' '||UCREA.VC_APELLIDO_MATERNO " +
                        "    FROM ORGEN_TA_USUARIO UCREA WHERE UCREA.IN_CODIGO_USUARIO=CF.IN_CODIGO_USUARIO_CREA) AS USUARIO_CREA," +
                        "  (SELECT UMODI.VC_NOMBRES||' '||UMODI.VC_APELLIDO_PATERNO||' '||UMODI.VC_APELLIDO_MATERNO" +
                        "   FROM ORGEN_TA_USUARIO UMODI WHERE UMODI.IN_CODIGO_USUARIO=CF.IN_CODIGO_USUARIO_MODI) AS USUARIO_MODIFICA" +
                        " FROM ORGEN_TA_DOCUMENTO CF";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               FormatoFecha fechaWeb=new FormatoFecha();
               orgen_ta_documento obj = new orgen_ta_documento();
               obj.setIn_codigo_doc(rs.getInt("IN_CODIGO_DOC"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
               obj.setDt_fecha_crea(fechaWeb.getFormatoFecha(rs.getDate("DT_FECHA_CREA")));
               obj.setDt_fecha_modif(fechaWeb.getFormatoFecha(rs.getDate("DT_FECHA_MODIF")));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setUsuarioCrea(rs.getString("USUARIO_CREA"));
               obj.setUsuarioModi(rs.getString("USUARIO_MODIFICA"));

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
       return listar;
    }
    //BUSCAR
    public orgen_ta_documento getDocument(int inDoc )
    {
        orgen_ta_documento obj = new orgen_ta_documento();
        try
        {
            cn = ds.getConnection();
            String sql="SELECT IN_CODIGO_DOC,VC_NOMBRE,VC_DESCRIPCION FROM ORGEN_TA_DOCUMENTO WHERE IN_CODIGO_DOC=?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, inDoc);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               obj.setIn_codigo_doc(rs.getInt("IN_CODIGO_DOC"));
               obj.setVc_nombre(rs.getString("VC_NOMBRE"));
               obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
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
       return obj;
    }

}