package com.sgduni.dao;
import com.sgduni.forms.orpro_ta_mof;
import com.sgduni.utilitarios.FormatoFecha;
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
public class orpro_ta_mof_DAO {
    protected DataSource ds;
    Connection cn;
    public orpro_ta_mof_DAO(DataSource ds)
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
    
    //codigo nuevo genera
    public String getCodigoGenerado()
    {
        String NuevoCodigo="";
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT CONCAT('MOF-',in_codigo_mof + 1) AS NUENO_CODIGO FROM orpro_ta_mof WHERE ROWNUM=1 ORDER BY in_codigo_mof DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();
            while(rs.next())
            {
                NuevoCodigo = rs.getString("NUENO_CODIGO");

            }
            if(NuevoCodigo.equals(""))
            {
              NuevoCodigo="MOF-1";
            }
            //System.out.println("Nuevo Codigob: "+NuevoCodigo);
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
        return NuevoCodigo;
    }

    //Enviar y guardar si es necesario el oficio circular
    public boolean guardarMof(orpro_ta_mof obj,int idUser,int idEstado,String nomArchivoDb)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_MOF_INSERT(?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_MOF",obj.getCh_codigo_mof());
            cstm.setInt("P_IN_DEPEND_FAC",obj.getIn_depend_fac());
            cstm.setString("P_CH_TIPO_DEPEND_FAC",obj.getCh_tipo_depend_fac());
            cstm.setString("P_DT_FECHA",getDateFormateada(obj.getDt_fecha()));
            cstm.setString("P_VC_NOMBRE_ARCHIVO",obj.getVc_nombre_archivo());
            cstm.setString("P_VC_RUTA_ARCHIVO_V1",nomArchivoDb);
            cstm.setInt("P_IN_CODIGO_ESTADO",idEstado);
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
    //Modificar
    public boolean modificarMof(orpro_ta_mof obj,int idMof,int idUser,int idEstado,String nomArchivoDb)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_MOF_MODIFA(?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_MOF",idMof);
            cstm.setString("P_CH_CODIGO_MOF",obj.getCh_codigo_mof());
            cstm.setInt("P_IN_DEPEND_FAC",obj.getIn_depend_fac());
            cstm.setString("P_CH_TIPO_DEPEND_FAC",obj.getCh_tipo_depend_fac());
            cstm.setString("P_DT_FECHA",getDateFormateada(obj.getDt_fecha()));
            cstm.setString("P_VC_NOMBRE_ARCHIVO",obj.getVc_nombre_archivo());
            cstm.setString("P_VC_RUTA_ARCHIVO_V1",nomArchivoDb);
            cstm.setInt("P_IN_CODIGO_ESTADO",idEstado);
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
    public ArrayList<orpro_ta_mof> getListaMof()
    {
        ArrayList<orpro_ta_mof> listaMof = null;
        try
        {
            listaMof = new ArrayList<orpro_ta_mof>();
            cn = ds.getConnection();
            String sql = "SELECT MOF.IN_CODIGO_MOF,MOF.CH_CODIGO_MOF,MOF.DT_FECHA,MOF.VC_NOMBRE_ARCHIVO,MOF.VC_RUTA_ARCHIVO_V1," +
                         "   U.IN_CODIGO_USUARIO,U.VC_NOMBRES AS NOM_USUARIO,U.VC_APELLIDO_PATERNO AS APP_USUARIO,U.VC_APELLIDO_MATERNO AS APM_USUARIO," +
                         "       E.VC_NOMBRE_ESTADO AS NOM_ESTADO," +
                         " CASE MOF.CH_TIPO_DEPEND_FAC" +
                         "    WHEN null THEN 'NO DEFICNIDO'" +
                         "    WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=MOF.IN_DEPEND_FAC)" +
                         "    WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=MOF.IN_DEPEND_FAC)" +
                         " ELSE 'NO DEFICNIDO'" +
                         " END AS FACULTAS_DEPENDECIA ,MOF.CH_ESTADO" +
                         " FROM ORPRO_TA_MOF MOF " +
                         " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=MOF.IN_CODIGO_USUARIO" +
                         " INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=MOF.IN_CODIGO_ESTADO" +
                         " WHERE MOF.CH_ESTADO='01'" +
                         " AND MOF.IN_CODIGO_MOF NOT IN (SELECT TRM.IN_CODIGO_MOF FROM ORPRO_TA_TRAMITE_DOCUMENTOS TRM)"+
                         " ORDER BY MOF.DT_FECHA DESC";
            //System.out.println(":"+sql);
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");
               orpro_ta_mof obj = new orpro_ta_mof();
               obj.setIn_codigo_mof(Integer.parseInt(rs.getString("IN_CODIGO_MOF")));
               obj.setCh_codigo_mof(rs.getString("CH_CODIGO_MOF"));
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
                if(rs.getString("DT_FECHA")!=null)
                {
                 obj.setDt_fecha(fechWeb.format(rs.getDate("DT_FECHA")));
                }
               obj.setVc_nombre_archivo(rs.getString("VC_NOMBRE_ARCHIVO"));
               obj.setNombre_Archivo_DB(rs.getString("VC_RUTA_ARCHIVO_V1"));
               obj.setNombreUsuario(rs.getString("NOM_USUARIO")+" "+rs.getString("APP_USUARIO"));
               obj.setNombreFaculDepen(rs.getString("FACULTAS_DEPENDECIA"));
               obj.setNombreEstado(rs.getString("NOM_ESTADO"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               listaMof.add(obj);
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
       return listaMof;
    }
    //Lista
    public ArrayList<orpro_ta_mof> getListaMofPorUsuario(int idFacuDepen,String tipoFacuDepen)
    {
        ArrayList<orpro_ta_mof> listaMof = null;
        try
        {
            listaMof = new ArrayList<orpro_ta_mof>();
            cn = ds.getConnection();
            String sql = "SELECT MOF.IN_CODIGO_MOF,MOF.CH_CODIGO_MOF,MOF.DT_FECHA,MOF.VC_NOMBRE_ARCHIVO,MOF.VC_RUTA_ARCHIVO_V1," +
                         "       U.IN_CODIGO_USUARIO,U.VC_NOMBRES AS NOM_USUARIO,U.VC_APELLIDO_PATERNO AS APP_USUARIO,U.VC_APELLIDO_MATERNO AS APM_USUARIO," +
                         "       E.VC_NOMBRE_ESTADO AS NOM_ESTADO," +
                         " CASE MOF.CH_TIPO_DEPEND_FAC" +
                         "    WHEN null THEN 'NO DEFICNIDO'" +
                         "    WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=MOF.IN_DEPEND_FAC)" +
                         "    WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=MOF.IN_DEPEND_FAC)" +
                         " ELSE 'NO DEFICNIDO'" +
                         " END AS FACULTAS_DEPENDECIA ,MOF.CH_ESTADO" +
                         " FROM ORPRO_TA_MOF MOF " +
                         " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=MOF.IN_CODIGO_USUARIO" +
                         " INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=MOF.IN_CODIGO_ESTADO" +
                         " WHERE MOF.CH_ESTADO='01'" +
                         " AND MOF.IN_CODIGO_MOF NOT IN (SELECT TRM.IN_CODIGO_MOF FROM ORPRO_TA_TRAMITE_DOCUMENTOS TRM)"+
                         "  AND MOF.IN_DEPEND_FAC=? AND MOF.CH_TIPO_DEPEND_FAC=?  "+
                         " ORDER BY MOF.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idFacuDepen);
            pstm.setString(2, tipoFacuDepen);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               SimpleDateFormat fechWeb=new SimpleDateFormat("dd MMM yyyy");
               orpro_ta_mof obj = new orpro_ta_mof();
               obj.setIn_codigo_mof(Integer.parseInt(rs.getString("IN_CODIGO_MOF")));
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
               obj.setCh_codigo_mof(rs.getString("CH_CODIGO_MOF"));
                if(rs.getString("DT_FECHA")!=null)
                {
                 obj.setDt_fecha(fechWeb.format(rs.getDate("DT_FECHA")));
                }
               obj.setVc_nombre_archivo(rs.getString("VC_NOMBRE_ARCHIVO"));
               obj.setNombre_Archivo_DB(rs.getString("VC_RUTA_ARCHIVO_V1"));
               obj.setNombreUsuario(rs.getString("NOM_USUARIO")+" "+rs.getString("APP_USUARIO"));
               obj.setNombreFaculDepen(rs.getString("FACULTAS_DEPENDECIA"));
               obj.setNombreEstado(rs.getString("NOM_ESTADO"));
               obj.setCh_estado(rs.getString("CH_ESTADO"));
               listaMof.add(obj);
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
       return listaMof;
    }

    //Buscar MOF
        public orpro_ta_mof BuscarMof(int in_codigo_mof)
    {
       
        orpro_ta_mof obj = new orpro_ta_mof();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_MOF,IN_CODIGO_ESTADO,CH_CODIGO_MOF,IN_DEPEND_FAC,CH_TIPO_DEPEND_FAC,DT_FECHA," +
                    "  VC_NOMBRE_ARCHIVO,VC_RUTA_ARCHIVO_V1 FROM ORPRO_TA_MOF " +
                    " WHERE IN_CODIGO_MOF=? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1,in_codigo_mof);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_mof(rs.getInt("IN_CODIGO_MOF"));
                obj.setCh_codigo_mof(rs.getString("CH_CODIGO_MOF"));
                obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
                FormatoFecha fechWeb=new FormatoFecha();
                obj.setDt_fecha(fechWeb.getFormatoFecha(rs.getDate("DT_FECHA"),"DD/MM/yyyy"));
                obj.setVc_nombre_archivo(rs.getString("VC_NOMBRE_ARCHIVO"));
                obj.setNombre_Archivo_DB(rs.getString("VC_RUTA_ARCHIVO_V1"));
                obj.setIn_depend_fac(rs.getInt("IN_DEPEND_FAC"));
                obj.setCh_tipo_depend_fac(rs.getString("CH_TIPO_DEPEND_FAC"));
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