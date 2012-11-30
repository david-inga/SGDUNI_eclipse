/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgduni.dao;
import com.sgduni.forms.orpro_ta_tramite_documentos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author Sistemas
 */
public class orpro_ta_tramite_documentos_DAO {
    protected DataSource ds;
    Connection cn;
    public orpro_ta_tramite_documentos_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    //Enviar y guardar si es necesario el oficio circular
    public boolean guardarTramite(orpro_ta_tramite_documentos obj,int idUser,int idEstado)
    {
        boolean exito=false;
        
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_TRAM_DOC_INSERT(?,?,?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_DEPEND_FACU",obj.getIn_depend_facu());
            cstm.setString("P_CH_CODIGO_PROC", obj.getCh_codigo_proc());
            cstm.setString("P_CH_TIPO_DEPEND_FACU", obj.getCh_tipo_depend_facu());
            cstm.setString("P_DT_FECHA", obj.getDt_fecha());
            cstm.setInt("P_IN_CODIGO_OFICIO", obj.getIn_codigo_oficio());
            cstm.setInt("P_IN_CODIGO_USUARIO", idUser);
            cstm.setInt("P_IN_CODIGO_ESTRUCTURA", obj.getIn_codigo_estructura());
            cstm.setInt("P_IN_CODIGO_ROF", obj.getIn_codigo_rof());
            cstm.setInt("P_IN_CODIGO_MOF", obj.getIn_codigo_mof());
            cstm.setInt("P_IN_CODIGO_MAPRO", obj.getIn_codigo_mapro());
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_ESTADO", idEstado);
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

    //Numero de tramites pendientes
    public int tramitesPendientes()
    {
        int exito=0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(*) AS NumTramites FROM ORPRO_TA_TRAMITE_DOCUMENTOS";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NumTramites");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error en la clase orpro_ta_tramite_documentos_DAO - tramitesPendientes() "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error en la clase orpro_ta_tramite_documentos_DAO - tramitesPendientes(): "+ex);
            }
       }
       return exito;
    }

    //lista de TRAMITES PENDIENTES
    public ArrayList<orpro_ta_tramite_documentos> getListaTramites()
    {
       
        ArrayList<orpro_ta_tramite_documentos> listaTramit = null;
        try
        {
            listaTramit = new ArrayList<orpro_ta_tramite_documentos>();
            cn = ds.getConnection();
            String query = "SELECT TD.IN_CODIGO_PROC,TD.CH_CODIGO_PROC,TD.DT_FECHA,TD.IN_CODIGO_OFICIO,TD.IN_CODIGO_ESTRUCTURA," +
                    " ROF.IN_CODIGO_ROF, ROF.VC_NOMBRE_ARCHIVO AS NOM_ROF,ROF.VC_RUTA_ARCHIVO_V1 AS ARCHIVO_ROF, " +
                       " EROF.VC_NOMBRE_ESTADO AS ESTADO_ROF,EROF.IN_CODIGO_ESTADO AS CODIGO_ESTADO_ROF, "+
                    " MOF.IN_CODIGO_MOF, MOF.VC_NOMBRE_ARCHIVO AS NOM_MOF,MOF.VC_RUTA_ARCHIVO_V1 AS ARCHIVO_MOF, " +
                       " EMOF.VC_NOMBRE_ESTADO AS ESTADO_MOF,EMOF.IN_CODIGO_ESTADO AS CODIGO_ESTADO_MOF, "+
                    " MAPRO.IN_CODIGO_MAPRO, MAPRO.VC_NOMBRE_ARCHIVO AS NOM_MAPRO,MAPRO.VC_RUTA_ARCHIVO_V1 AS ARCHIVO_MAPRO, " +
                       " EMAPRO.VC_NOMBRE_ESTADO AS ESTADO_MAPRO,EMAPRO.IN_CODIGO_ESTADO AS CODIGO_ESTADO_MAPRO, "+
                    " U.VC_NOMBRES AS NOM_USUARIO,U.VC_APELLIDO_PATERNO AS APP_USUARIO,U.VC_APELLIDO_MATERNO AS APM_USUARIO,    " +
                    " CASE TRIM(TD.CH_TIPO_DEPEND_FACU)" +
                    "    WHEN null THEN 'NO DEFICNIDO'" +
                    "    WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=TD.IN_DEPEND_FACU)" +
                    "    WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=TD.IN_DEPEND_FACU)" +
                    "    ELSE 'NO DEFICNIDO'" +
                    " END AS FACULTAS_DEPENDECIA " +
                    " FROM ORPRO_TA_TRAMITE_DOCUMENTOS TD " +
                    " INNER JOIN ORPRO_TA_ROF ROF ON ROF.IN_CODIGO_ROF=TD.IN_CODIGO_ROF " +
                       "INNER JOIN ORGEN_TA_ESTADO EROF ON EROF.IN_CODIGO_ESTADO=ROF.IN_CODIGO_ESTADO "+
                    " INNER JOIN ORPRO_TA_MOF MOF ON MOF.IN_CODIGO_MOF=TD.IN_CODIGO_MOF " +
                       "INNER JOIN ORGEN_TA_ESTADO EMOF ON EMOF.IN_CODIGO_ESTADO=MOF.IN_CODIGO_ESTADO  "+
                    " INNER JOIN ORPRO_TA_MAPRO MAPRO ON MAPRO.IN_CODIGO_MAPRO=TD.IN_CODIGO_MAPRO " +
                       "INNER JOIN ORGEN_TA_ESTADO EMAPRO ON EMAPRO.IN_CODIGO_ESTADO=MAPRO.IN_CODIGO_ESTADO  "+
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=TD.IN_CODIGO_USUARIO " +
                    " WHERE TD.IN_CODIGO_PROC NOT IN (SELECT TRM.IN_CODIGO_PROC FROM ORPRO_RESOLUCION_RECTORAL TRM)";

            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                orpro_ta_tramite_documentos dataForm = new orpro_ta_tramite_documentos();
                dataForm.setIn_codigo_proc(rs.getInt("IN_CODIGO_PROC"));
                dataForm.setCh_codigo_proc(rs.getString("CH_CODIGO_PROC"));
                
                SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");

                dataForm.setDt_fecha(fechWeb.format(rs.getDate("DT_FECHA")));

                dataForm.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                dataForm.setIn_codigo_estructura(rs.getInt("IN_CODIGO_ESTRUCTURA"));

                dataForm.setNomArchivoDBMapro(rs.getString("ARCHIVO_MAPRO"));
                dataForm.setNomArchivoMapro(rs.getString("NOM_MAPRO"));
                dataForm.setIn_codigo_mapro(rs.getInt("IN_CODIGO_MAPRO"));
                
                dataForm.setNomArchivoDBMof(rs.getString("ARCHIVO_MOF"));
                dataForm.setNomArchivoMof(rs.getString("NOM_MOF"));
                dataForm.setIn_codigo_mof(rs.getInt("IN_CODIGO_MOF"));
                
                dataForm.setNomArchivoDBRof(rs.getString("ARCHIVO_ROF"));
                dataForm.setNomArchivoRof(rs.getString("NOM_ROF"));
                dataForm.setIn_codigo_rof(rs.getInt("IN_CODIGO_ROF"));

                dataForm.setNomUsuario(rs.getString("NOM_USUARIO")+" "+rs.getString("APP_USUARIO"));
                dataForm.setNomDependenFacu(rs.getString("FACULTAS_DEPENDECIA"));

                dataForm.setNomEstadoMapro(rs.getString("ESTADO_MAPRO"));
                  dataForm.setIn_codigo_estado_mapro(rs.getInt("CODIGO_ESTADO_MAPRO"));
                dataForm.setNomEstadoMof(rs.getString("ESTADO_MOF"));
                  dataForm.setIn_codigo_estado_mof(rs.getInt("CODIGO_ESTADO_MOF"));
                dataForm.setNomEstadoRof(rs.getString("ESTADO_ROF"));
                  dataForm.setIn_codigo_estado_rof(rs.getInt("CODIGO_ESTADO_ROF"));


                //dataForm.setIn_codigo_rof(rs.getInt("IN_CODIGO_ROF"));
                //dataForm.setIn_codigo_mof(rs.getInt("IN_CODIGO_MOF"));
                //dataForm.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
                //dataForm.setCh_tipo_depend_facu(rs.getString("CH_TIPO_DEPEND_FACU"));
                //dataForm.setIn_depend_facu(rs.getInt("IN_DEPEND_FACU"));
                listaTramit.add(dataForm);
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
       return listaTramit;
    }
    //lista de TRAMITES PENDIENTES POR USUARIO
    public ArrayList<orpro_ta_tramite_documentos> getListaTramitesPorUsuario(int idDepFacu,String tipDepFacu)
    {

        ArrayList<orpro_ta_tramite_documentos> listaTramit = null;
        try
        {
            listaTramit = new ArrayList<orpro_ta_tramite_documentos>();
            cn = ds.getConnection();
            String query = "SELECT TD.IN_CODIGO_PROC,TD.CH_CODIGO_PROC,TD.DT_FECHA,TD.IN_CODIGO_OFICIO,TD.IN_CODIGO_ESTRUCTURA," +
                    " ROF.IN_CODIGO_ROF, ROF.VC_NOMBRE_ARCHIVO AS NOM_ROF,ROF.VC_RUTA_ARCHIVO_V1 AS ARCHIVO_ROF, " +
                       " EROF.VC_NOMBRE_ESTADO AS ESTADO_ROF,EROF.IN_CODIGO_ESTADO AS CODIGO_ESTADO_ROF, "+
                    " MOF.IN_CODIGO_MOF, MOF.VC_NOMBRE_ARCHIVO AS NOM_MOF,MOF.VC_RUTA_ARCHIVO_V1 AS ARCHIVO_MOF, " +
                       " EMOF.VC_NOMBRE_ESTADO AS ESTADO_MOF,EMOF.IN_CODIGO_ESTADO AS CODIGO_ESTADO_MOF, "+
                    " MAPRO.IN_CODIGO_MAPRO, MAPRO.VC_NOMBRE_ARCHIVO AS NOM_MAPRO,MAPRO.VC_RUTA_ARCHIVO_V1 AS ARCHIVO_MAPRO, " +
                       " EMAPRO.VC_NOMBRE_ESTADO AS ESTADO_MAPRO,EMAPRO.IN_CODIGO_ESTADO AS CODIGO_ESTADO_MAPRO, "+
                    " U.VC_NOMBRES AS NOM_USUARIO,U.VC_APELLIDO_PATERNO AS APP_USUARIO,U.VC_APELLIDO_MATERNO AS APM_USUARIO,    " +
                    " CASE TRIM(TD.CH_TIPO_DEPEND_FACU)" +
                    "    WHEN null THEN 'NO DEFICNIDO'" +
                    "    WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=TD.IN_DEPEND_FACU)" +
                    "    WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=TD.IN_DEPEND_FACU)" +
                    "    ELSE 'NO DEFICNIDO'" +
                    " END AS FACULTAS_DEPENDECIA " +
                    " FROM ORPRO_TA_TRAMITE_DOCUMENTOS TD " +
                    " INNER JOIN ORPRO_TA_ROF ROF ON ROF.IN_CODIGO_ROF=TD.IN_CODIGO_ROF " +
                       "INNER JOIN ORGEN_TA_ESTADO EROF ON EROF.IN_CODIGO_ESTADO=ROF.IN_CODIGO_ESTADO "+
                    " INNER JOIN ORPRO_TA_MOF MOF ON MOF.IN_CODIGO_MOF=TD.IN_CODIGO_MOF " +
                       "INNER JOIN ORGEN_TA_ESTADO EMOF ON EMOF.IN_CODIGO_ESTADO=MOF.IN_CODIGO_ESTADO  "+
                    " INNER JOIN ORPRO_TA_MAPRO MAPRO ON MAPRO.IN_CODIGO_MAPRO=TD.IN_CODIGO_MAPRO " +
                       "INNER JOIN ORGEN_TA_ESTADO EMAPRO ON EMAPRO.IN_CODIGO_ESTADO=MAPRO.IN_CODIGO_ESTADO  "+
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=TD.IN_CODIGO_USUARIO " +
                    " WHERE TD.IN_CODIGO_PROC NOT IN (SELECT TRM.IN_CODIGO_PROC FROM ORPRO_RESOLUCION_RECTORAL TRM) " +
                    " AND TD.IN_DEPEND_FACU=? AND TRIM(TD.CH_TIPO_DEPEND_FACU)=? ";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, idDepFacu);
            pstm.setString(2, tipDepFacu);
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                orpro_ta_tramite_documentos dataForm = new orpro_ta_tramite_documentos();
                dataForm.setIn_codigo_proc(rs.getInt("IN_CODIGO_PROC"));
                dataForm.setCh_codigo_proc(rs.getString("CH_CODIGO_PROC"));

                SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");

                dataForm.setDt_fecha(fechWeb.format(rs.getDate("DT_FECHA")));

                dataForm.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                dataForm.setIn_codigo_estructura(rs.getInt("IN_CODIGO_ESTRUCTURA"));

                dataForm.setNomArchivoDBMapro(rs.getString("ARCHIVO_MAPRO"));
                dataForm.setNomArchivoMapro(rs.getString("NOM_MAPRO"));
                dataForm.setIn_codigo_mapro(rs.getInt("IN_CODIGO_MAPRO"));

                dataForm.setNomArchivoDBMof(rs.getString("ARCHIVO_MOF"));
                dataForm.setNomArchivoMof(rs.getString("NOM_MOF"));
                dataForm.setIn_codigo_mof(rs.getInt("IN_CODIGO_MOF"));

                dataForm.setNomArchivoDBRof(rs.getString("ARCHIVO_ROF"));
                dataForm.setNomArchivoRof(rs.getString("NOM_ROF"));
                dataForm.setIn_codigo_rof(rs.getInt("IN_CODIGO_ROF"));

                dataForm.setNomUsuario(rs.getString("NOM_USUARIO")+" "+rs.getString("APP_USUARIO"));
                dataForm.setNomDependenFacu(rs.getString("FACULTAS_DEPENDECIA"));
                //dataForm.setIn_codigo_rof(rs.getInt("IN_CODIGO_ROF"));
                //dataForm.setIn_codigo_mof(rs.getInt("IN_CODIGO_MOF"));
                //dataForm.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
                //dataForm.setCh_tipo_depend_facu(rs.getString("CH_TIPO_DEPEND_FACU"));
                //dataForm.setIn_depend_facu(rs.getInt("IN_DEPEND_FACU"));

                dataForm.setNomEstadoMapro(rs.getString("ESTADO_MAPRO"));
                  dataForm.setIn_codigo_estado_mapro(rs.getInt("CODIGO_ESTADO_MAPRO"));
                dataForm.setNomEstadoMof(rs.getString("ESTADO_MOF"));
                  dataForm.setIn_codigo_estado_mof(rs.getInt("CODIGO_ESTADO_MOF"));
                dataForm.setNomEstadoRof(rs.getString("ESTADO_ROF"));
                  dataForm.setIn_codigo_estado_rof(rs.getInt("CODIGO_ESTADO_ROF"));

                listaTramit.add(dataForm);
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
       return listaTramit;
    }
}


