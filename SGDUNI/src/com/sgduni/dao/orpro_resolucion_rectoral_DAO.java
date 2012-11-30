/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;
import com.sgduni.forms.orpro_resolucion_rectoral;
import com.sgduni.utilitarios.FormatoFecha;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

public class orpro_resolucion_rectoral_DAO {
    protected DataSource ds;
    Connection cn;
    public orpro_resolucion_rectoral_DAO(DataSource ds)
    {
        this.ds = ds;
    }

     public String getDateFormateada(String fecha)
    {
        String[] fechaA = fecha.split("/");
        String anio = fechaA[2];
        String mes =  fechaA[1];
        String dia =  fechaA[0];
        String FechaFormateada = anio+"/"+mes+"/"+dia;
      return FechaFormateada;
    }

    //Enviar y guardar si es necesario el oficio circular
    public boolean guardarResolucion(orpro_resolucion_rectoral obj,int idUser,int idEstado,String nomArchivoDb)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            System.out.println("INGRESO PROCEDIDMN");
            String sql = "{ CALL SP_ORPRO_RESOL_RET_INSERT(?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
              //System.out.println("P_CH_CODIGO_RESOLUCION "+obj.getCh_codigo_resolucion());
            cstm.setString("P_CH_CODIGO_RESOLUCION",obj.getCh_codigo_resolucion());
              //System.out.println("P_DT_FECHA "+obj.getDt_fecha());
            cstm.setString("P_DT_FECHA",getDateFormateada(obj.getDt_fecha()));
              //System.out.println("P_VC_NOMBRE_ARCHIVO "+obj.getVc_nombre_archivo());
            cstm.setString("P_VC_NOMBRE_ARCHIVO",obj.getVc_nombre_archivo());
             // System.out.println("P_VC_RUTA_ARCHIVO "+obj.getVc_ruta_archivo());
            cstm.setString("P_VC_RUTA_ARCHIVO",obj.getVc_ruta_archivo());
            cstm.setInt("P_IN_CODIGO_PROC",obj.getIn_codigo_proc());
            cstm.setInt("P_IN_CODIGO_USUARIO",idUser);
           
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

    //Lista
    public ArrayList<orpro_resolucion_rectoral> getListaResolucion()
    {
        ArrayList<orpro_resolucion_rectoral> listaResoluc = null;
        try
        {
            listaResoluc = new ArrayList<orpro_resolucion_rectoral>();
            cn = ds.getConnection();//,RR.VC_RUTA_ARCHIVO
            String sql = "SELECT " +
                    " RR.IN_CODIGO_RESOLUCION,RR.CH_CODIGO_RESOLUCION,RR.DT_FECHA,RR.VC_NOMBRE_ARCHIVO," +
                    " U.VC_NOMBRES AS NOM_USUARIO,U.VC_APELLIDO_PATERNO AS APP_USUARIO,U.VC_APELLIDO_MATERNO AS APM_USUARIO," +
                    " TD.IN_CODIGO_PROC,TD.CH_CODIGO_PROC,RR.CH_ESTADO" +
                    " FROM ORPRO_RESOLUCION_RECTORAL RR " +
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=RR.IN_CODIGO_USUARIO " +
                    " INNER JOIN ORPRO_TA_TRAMITE_DOCUMENTOS TD ON TD.IN_CODIGO_PROC=RR.IN_CODIGO_PROC " +
                    " ORDER BY RR.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               FormatoFecha fechWeb=new FormatoFecha();
               orpro_resolucion_rectoral obj = new orpro_resolucion_rectoral();
               obj.setIn_codigo_resolucion(rs.getInt("IN_CODIGO_RESOLUCION"));
               obj.setCh_codigo_resolucion(rs.getString("CH_CODIGO_RESOLUCION"));
               obj.setDt_fecha(fechWeb.getFormatoFecha(rs.getDate("DT_FECHA")));
               obj.setVc_nombre_archivo(rs.getString("VC_NOMBRE_ARCHIVO"));
               //obj.setNomArchivoDB(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setNomUsuario(rs.getString("NOM_USUARIO")+" "+rs.getString("APP_USUARIO")+" "+rs.getString("APM_USUARIO"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               obj.setIn_codigo_proc(rs.getInt("IN_CODIGO_PROC"));
               obj.setCh_codigo_proc(rs.getString("CH_CODIGO_PROC"));
               listaResoluc.add(obj);
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
       return listaResoluc;
    }

    //SABER SI UN TRAMITE YA ESTA COMO RESOLUCION RECTORAL
    public boolean TramiteResolucion(int idTramite)
    {
        boolean estado=false;
        try
        {            
            cn = ds.getConnection();
            String sql = "SELECT RS.IN_CODIGO_RESOLUCION FROM ORPRO_RESOLUCION_RECTORAL RS WHERE RS.IN_CODIGO_PROC=?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idTramite);
            ResultSet rs = pstm.executeQuery();
            estado=rs.next();
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
       return estado;
    }
    
    //
    //Buscar MOF
        public orpro_resolucion_rectoral BuscarResolucionRec(int in_codigo_resolucion)
    {
       
        orpro_resolucion_rectoral obj = new orpro_resolucion_rectoral();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_RESOLUCION,VC_RUTA_ARCHIVO FROM ORPRO_RESOLUCION_RECTORAL " +
                    " WHERE IN_CODIGO_RESOLUCION=? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1,in_codigo_resolucion);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setVc_ruta_archivo(rs.getString("VC_RUTA_ARCHIVO"));
                obj.setIn_codigo_resolucion(rs.getInt("IN_CODIGO_RESOLUCION"));
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
