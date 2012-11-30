/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;
//import com.sgduni.forms.orpro_ta_estructura_organica;
import com.sgduni.forms.orgen_ta_estructura_organica;
import com.sgduni.forms.orpro_ta_observacion_estruc;
import com.sgduni.forms.orpro_ta_versiones_estruc;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;

/**
 *
 * @author Sistemas
 */
public class orpro_ta_estructura_organica_DAO
{
    private DataSource ds;
    private Connection cn;
    
    public orpro_ta_estructura_organica_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    //Numero de estructuras organicas pendientes general
    public int getCountEstructurasPendientes()
    {
        int exito=0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(IN_CODIGO_ESTRUCTURA) AS NUM_ESTRUC FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG WHERE ORG.IN_CODIGO_ESTADO <>23 and ORG.IN_CODIGO_ESTADO <> 31";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NUM_ESTRUC");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("Error al obtener la cantidad total de E.O. Pendientes : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al obtener la cantidad total de E.O. Pendientes: "+ex);
            }
       }
       return exito;
    }


    // Numero de estructuras organicas pendientes por usuario (DEPENDDENCIAS Y/O FACULTADES) ID 12 (REVISION USUARIOS)
    public int getCountEstructurasPendientesPorUsuarios(int idFaculDep, String tipFaculDep)
    {
        int exito=0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(IN_CODIGO_ESTRUCTURA) AS NUM_ESTRUC " +
                    "FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG " +
                    "WHERE ORG.IN_CODIGO_ESTADO <> 23 and ORG.IN_CODIGO_ESTADO <> 31 " +
                    "AND ORG.IN_FACU_DEPEND = ? AND ORG.CH_TIPO_FAC_DEPEND = ?";
            PreparedStatement pstm = cn.prepareStatement(query);
                pstm.setInt(1, idFaculDep);
                pstm.setString(2, tipFaculDep);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NUM_ESTRUC");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("Error al obtener la cantidad de E.O. Pendientes segun el usuario: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al obtener la cantidad de E.O. Pendientes segun el usuario: "+ex);
            }
       }
       return exito;
    }

    //25/07/2012
    // Numero de estructuras organicas pendientes por usuario (DEPENDDENCIAS Y/O FACULTADES) ID 12 (REVISION USUARIOS)
    public int getCountEstructurasAprobadasPorUsuarios(int idFaculDep, String tipFaculDep)
    {
        int exito=0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(IN_CODIGO_ESTRUCTURA) AS NUM_ESTRUC " +
                    "FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG " +
                    "WHERE ORG.IN_CODIGO_ESTADO = 23 " +
                    "AND ORG.IN_FACU_DEPEND = ? AND ORG.CH_TIPO_FAC_DEPEND = ?";
            PreparedStatement pstm = cn.prepareStatement(query);
                pstm.setInt(1, idFaculDep);
                pstm.setString(2, tipFaculDep);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NUM_ESTRUC");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("Error al obtener la cantidad de E.O. aprobado segun el usuario: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al obtener la cantidad de E.O. Pendientes segun el usuario: "+ex);
            }
       }
       return exito;
    }

    //Numero de estructuras organicas pendientes general
    public int getCountEstructurasAprobadas()
    {
        int exito=0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(IN_CODIGO_ESTRUCTURA) AS NUM_ESTRUC FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG WHERE ORG.IN_CODIGO_ESTADO = 23";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NUM_ESTRUC");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("Error al obtener la cantidad total de E.O. aprobado : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al obtener la cantidad total de E.O. Pendientes: "+ex);
            }
       }
       return exito;
    }

    //25/07/2012
    // Numero de estructuras organicas pendientes por usuario (DEPENDDENCIAS Y/O FACULTADES) ID 12 (REVISION USUARIOS)
    public int getCountEstructurasHistorialPorUsuarios(int idFaculDep, String tipFaculDep)
    {
        int exito=0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(IN_CODIGO_ESTRUCTURA) AS NUM_ESTRUC " +
                    "FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG " +
                    "WHERE ORG.IN_CODIGO_ESTADO = 31 " +
                    "AND ORG.IN_FACU_DEPEND = ? AND ORG.CH_TIPO_FAC_DEPEND = ?";
            PreparedStatement pstm = cn.prepareStatement(query);
                pstm.setInt(1, idFaculDep);
                pstm.setString(2, tipFaculDep);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NUM_ESTRUC");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("Error al obtener la cantidad de E.O. historial segun el usuario: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al obtener la cantidad de E.O. Pendientes segun el usuario: "+ex);
            }
       }
       return exito;
    }

    //Numero de estructuras organicas pendientes general
    public int getCountEstructurasHistorial()
    {
        int exito=0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(IN_CODIGO_ESTRUCTURA) AS NUM_ESTRUC FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG WHERE ORG.IN_CODIGO_ESTADO = 31";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NUM_ESTRUC");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("Error al obtener la cantidad total de E.O. historial : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al obtener la cantidad total de E.O. historial: "+ex);
            }
       }
       return exito;
    }

    //Consulta a un registro en especifico de la estructura
    public orgen_ta_estructura_organica getEstructuraOrganica(String in_codigo_estructura)
    {
        //System.out.println("metodo getEstructura() ");
        orgen_ta_estructura_organica obj = new orgen_ta_estructura_organica();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_ESTRUCTURA,CH_CODIGO_ESTRUCTURA, DT_FECHA,IN_FACU_DEPEND, " +
                    "CH_TIPO_FAC_DEPEND,VC_NOMBRE_ARCHIVO,VC_RUTA_ARCHIVO FROM orpro_ta_estructura_organica " +
                    " WHERE IN_CODIGO_ESTRUCTURA = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(in_codigo_estructura));
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_estructura(rs.getInt("IN_CODIGO_ESTRUCTURA"));
                obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA") );
                obj.setDt_fecha(rs.getDate("DT_FECHA").toString() );
                obj.setIn_facu_depend( rs.getInt("IN_FACU_DEPEND") );
                obj.setCh_tipo_fac_depend(rs.getString("CH_TIPO_FAC_DEPEND"));
                obj.setVc_nombre_archivo(rs.getString("VC_NOMBRE_ARCHIVO"));
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error, getEstructura(INT ID) : "+e);
        }
       finally
       {
            try{ cn.close(); }
            catch ( Exception ex){ System.out.println("Finally - Error, getEstructura(INT ID) : "+ex);}
       }
        return obj;
    }

    //Lista de estructuras visualizacion OCDO (LISTA DE ESTRUCUTRAS NO APROBADAS - DIFERENTE AL ESATADO ID 13)
    //SI FUNCIONA lista todo menos lo que esta aprobado
    public ArrayList<orgen_ta_estructura_organica> getListaEstructuras()
    {
        ArrayList<orgen_ta_estructura_organica> listaEstructuras = null;
        try
        {
            listaEstructuras = new ArrayList<orgen_ta_estructura_organica>();

            cn = ds.getConnection();

            String sql = "SELECT ORG.IN_CODIGO_ESTRUCTURA,ORG.CH_CODIGO_ESTRUCTURA," +
                    "( select ve.vc_ruta_archivo from orpro_ta_versiones_estruc ve where in_codigo_estruc = ORG.IN_CODIGO_ESTRUCTURA order by ve.in_codigo_versiones desc LIMIT 1) AS VC_RUTA_ARCHIVO, " +
                    "ORG.DT_FECHA,ORG.DT_FECHA_HORA," +
                    "    CASE TRIM(ORG.CH_TIPO_FAC_DEPEND)" +
                    "       WHEN null THEN 'NO DEFINIDO'" +
                    "       WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ORG.IN_FACU_DEPEND)" +
                    "       WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ORG.IN_FACU_DEPEND)" +
                    "    ELSE 'NO DEFINIDO'" +
                    "    END AS FACULTAD_DEPENDECIA," +
                    "    E.IN_CODIGO_ESTADO, " +
                    "   CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS NOMBRE_USUARIO " +
                    "  FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG" +
                    "  INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=ORG.IN_CODIGO_ESTADO" +
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=ORG.IN_CODIGO_USUARIO" +
                    " WHERE ORG.IN_CODIGO_ESTADO <> 23 AND ORG.IN_CODIGO_ESTADO <> 31 ORDER BY ORG.DT_FECHA_HORA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();            
            while(rs.next() )
            {               
               orgen_ta_estructura_organica obj = new orgen_ta_estructura_organica();

               obj.setIn_codigo_estructura(Integer.parseInt( rs.getString("IN_CODIGO_ESTRUCTURA")));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setVc_nombre_archivo(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
               obj.setNombre_FacDep(rs.getString("FACULTAD_DEPENDECIA"));
               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setNombre_Usuario(rs.getString("NOMBRE_USUARIO"));
               listaEstructuras.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al traer la lista de estructuras organicas: "+e);
        }
        finally
        {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al traer la lista de estructuras organicas: "+ex);}
        }
        return listaEstructuras;
    }


    public ArrayList<orgen_ta_estructura_organica> getListaEstructurasAprobadas()
    {
        ArrayList<orgen_ta_estructura_organica> listaEstructuras = null;
        try
        {
            listaEstructuras = new ArrayList<orgen_ta_estructura_organica>();

            cn = ds.getConnection();

            String sql = "SELECT ORG.IN_CODIGO_ESTRUCTURA,ORG.CH_CODIGO_ESTRUCTURA," +
                    "( select ve.vc_ruta_archivo from orpro_ta_versiones_estruc ve where in_codigo_estruc = ORG.IN_CODIGO_ESTRUCTURA order by ve.in_codigo_versiones desc LIMIT 1) AS VC_RUTA_ARCHIVO, " +
                    "ORG.DT_FECHA,ORG.DT_FECHA_HORA," +
                    "    CASE TRIM(ORG.CH_TIPO_FAC_DEPEND)" +
                    "       WHEN null THEN 'NO DEFINIDO'" +
                    "       WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ORG.IN_FACU_DEPEND)" +
                    "       WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ORG.IN_FACU_DEPEND)" +
                    "    ELSE 'NO DEFINIDO'" +
                    "    END AS FACULTAD_DEPENDECIA," +
                    "    E.IN_CODIGO_ESTADO, " +
                    "   CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS NOMBRE_USUARIO " +
                    "  FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG" +
                    "  INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=ORG.IN_CODIGO_ESTADO" +
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=ORG.IN_CODIGO_USUARIO" +
                    " WHERE ORG.IN_CODIGO_ESTADO = 23 ORDER BY ORG.DT_FECHA_HORA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_estructura_organica obj = new orgen_ta_estructura_organica();

               obj.setIn_codigo_estructura(Integer.parseInt( rs.getString("IN_CODIGO_ESTRUCTURA")));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setVc_nombre_archivo(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
               obj.setNombre_FacDep(rs.getString("FACULTAD_DEPENDECIA"));
               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setNombre_Usuario(rs.getString("NOMBRE_USUARIO"));
               listaEstructuras.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al traer la lista de estructuras organicas: "+e);
        }
        finally
        {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al traer la lista de estructuras organicas: "+ex);}
        }
        return listaEstructuras;
    }
    
    //25/07/2012
    public ArrayList<orgen_ta_estructura_organica> getListaEstructurasHistorialPorUsuario(int idFaculDep,String tipFaculDep)
    {
        ArrayList<orgen_ta_estructura_organica> listaEstructuras = null;
        try
        {
            listaEstructuras = new ArrayList<orgen_ta_estructura_organica>();
            cn = ds.getConnection();
            String sql = "SELECT ORG.IN_CODIGO_ESTRUCTURA, " +
                    "( select ve.vc_ruta_archivo from orpro_ta_versiones_estruc ve where in_codigo_estruc = ORG.IN_CODIGO_ESTRUCTURA order by ve.in_codigo_versiones desc LIMIT 1) AS VC_RUTA_ARCHIVO, "+
                    "ORG.CH_CODIGO_ESTRUCTURA,ORG.DT_FECHA," +
                    "    CASE TRIM(ORG.CH_TIPO_FAC_DEPEND)" +
                    "       WHEN null THEN 'NO DEFICNIDO'" +
                    "       WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ORG.IN_FACU_DEPEND)" +
                    "       WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ORG.IN_FACU_DEPEND)" +
                    "    ELSE 'NO DEFICNIDO'" +
                    "    END AS FACULTAD_DEPENDECIA," +
                    "    E.IN_CODIGO_ESTADO, " +
                    "   CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS NOMBRE_USUARIO " +
                    "  FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG" +
                    "  INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=ORG.IN_CODIGO_ESTADO" +
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=ORG.IN_CODIGO_USUARIO" +
                    " WHERE ORG.IN_CODIGO_ESTADO = 31 AND ORG.IN_FACU_DEPEND=? AND ORG.CH_TIPO_FAC_DEPEND=? " +
                    " ORDER BY ORG.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setInt(1, idFaculDep);
                pstm.setString(2, tipFaculDep);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_estructura_organica obj = new orgen_ta_estructura_organica();
               obj.setIn_codigo_estructura(Integer.parseInt( rs.getString("IN_CODIGO_ESTRUCTURA")));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
               obj.setNombre_FacDep(rs.getString("FACULTAD_DEPENDECIA"));
               obj.setVc_nombre_archivo(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setNombre_Usuario(rs.getString("NOMBRE_USUARIO"));
               listaEstructuras.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al listar estructuras organicas segun usuario: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al listar estructuras organicas segun usuario: "+ex);}
       }
       return listaEstructuras;
    }


    // 25/07/2012
    public ArrayList<orgen_ta_estructura_organica> getListaEstructurasHistorial()
    {
        ArrayList<orgen_ta_estructura_organica> listaEstructuras = null;
        try
        {
            listaEstructuras = new ArrayList<orgen_ta_estructura_organica>();

            cn = ds.getConnection();

            String sql = "SELECT ORG.IN_CODIGO_ESTRUCTURA,ORG.CH_CODIGO_ESTRUCTURA," +
                    "( select ve.vc_ruta_archivo from orpro_ta_versiones_estruc ve where in_codigo_estruc = ORG.IN_CODIGO_ESTRUCTURA order by ve.in_codigo_versiones desc LIMIT 1) AS VC_RUTA_ARCHIVO, " +
                    "ORG.DT_FECHA,ORG.DT_FECHA_HORA," +
                    "    CASE TRIM(ORG.CH_TIPO_FAC_DEPEND)" +
                    "       WHEN null THEN 'NO DEFINIDO'" +
                    "       WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ORG.IN_FACU_DEPEND)" +
                    "       WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ORG.IN_FACU_DEPEND)" +
                    "    ELSE 'NO DEFINIDO'" +
                    "    END AS FACULTAD_DEPENDECIA," +
                    "    E.IN_CODIGO_ESTADO, " +
                    "   CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS NOMBRE_USUARIO " +
                    "  FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG" +
                    "  INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=ORG.IN_CODIGO_ESTADO" +
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=ORG.IN_CODIGO_USUARIO" +
                    " WHERE ORG.IN_CODIGO_ESTADO = 31 ORDER BY ORG.DT_FECHA_HORA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_estructura_organica obj = new orgen_ta_estructura_organica();

               obj.setIn_codigo_estructura(Integer.parseInt( rs.getString("IN_CODIGO_ESTRUCTURA")));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setVc_nombre_archivo(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
               obj.setNombre_FacDep(rs.getString("FACULTAD_DEPENDECIA"));
               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setNombre_Usuario(rs.getString("NOMBRE_USUARIO"));
               listaEstructuras.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al traer la lista de estructuras organicas: "+e);
        }
        finally
        {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al traer la lista de estructuras organicas: "+ex);}
        }
        return listaEstructuras;
    }

    //pendiente spor estado y usuario
    public ArrayList<orgen_ta_estructura_organica> getListaEstructurasPendientesPorUsuario(int idFaculDep,String tipFaculDep)
    {
        ArrayList<orgen_ta_estructura_organica> listaEstructuras = null;
        try
        {
            listaEstructuras = new ArrayList<orgen_ta_estructura_organica>();
            cn = ds.getConnection();
            String sql = "SELECT ORG.IN_CODIGO_ESTRUCTURA, " +
                    "( select ve.vc_ruta_archivo from orpro_ta_versiones_estruc ve where in_codigo_estruc = ORG.IN_CODIGO_ESTRUCTURA order by ve.in_codigo_versiones desc LIMIT 1) AS VC_RUTA_ARCHIVO, "+
                    "ORG.CH_CODIGO_ESTRUCTURA,ORG.DT_FECHA," +
                    "    CASE TRIM(ORG.CH_TIPO_FAC_DEPEND)" +
                    "       WHEN null THEN 'NO DEFICNIDO'" +
                    "       WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ORG.IN_FACU_DEPEND)" +
                    "       WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ORG.IN_FACU_DEPEND)" +
                    "    ELSE 'NO DEFICNIDO'" +
                    "    END AS FACULTAD_DEPENDECIA," +
                    "    E.IN_CODIGO_ESTADO, " +
                    "   CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS NOMBRE_USUARIO " +
                    "  FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG" +
                    "  INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=ORG.IN_CODIGO_ESTADO" +
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=ORG.IN_CODIGO_USUARIO" +
                    " WHERE ORG.IN_CODIGO_ESTADO <> 23 AND ORG.IN_FACU_DEPEND=? AND ORG.CH_TIPO_FAC_DEPEND=? " +
                    " ORDER BY ORG.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setInt(1, idFaculDep);
                pstm.setString(2, tipFaculDep);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_estructura_organica obj = new orgen_ta_estructura_organica();
               obj.setIn_codigo_estructura(Integer.parseInt( rs.getString("IN_CODIGO_ESTRUCTURA")));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
               obj.setNombre_FacDep(rs.getString("FACULTAD_DEPENDECIA"));
               obj.setVc_nombre_archivo(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setNombre_Usuario(rs.getString("NOMBRE_USUARIO"));
               listaEstructuras.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al listar estructuras organicas segun usuario: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al listar estructuras organicas segun usuario: "+ex);}
       }
       return listaEstructuras;
    }
    
    //pendiente spor estado y usuario
    public ArrayList<orgen_ta_estructura_organica> getListaEstructurasAprobadasPorUsuario(int idFaculDep,String tipFaculDep)
    {
        ArrayList<orgen_ta_estructura_organica> listaEstructuras = null;
        try
        {
            listaEstructuras = new ArrayList<orgen_ta_estructura_organica>();
            cn = ds.getConnection();
            String sql = "SELECT ORG.IN_CODIGO_ESTRUCTURA, " +
                    "( select ve.vc_ruta_archivo from orpro_ta_versiones_estruc ve where in_codigo_estruc = ORG.IN_CODIGO_ESTRUCTURA order by ve.in_codigo_versiones desc LIMIT 1) AS VC_RUTA_ARCHIVO, "+
                    "ORG.CH_CODIGO_ESTRUCTURA,ORG.DT_FECHA," +
                    "    CASE TRIM(ORG.CH_TIPO_FAC_DEPEND)" +
                    "       WHEN null THEN 'NO DEFICNIDO'" +
                    "       WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ORG.IN_FACU_DEPEND)" +
                    "       WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ORG.IN_FACU_DEPEND)" +
                    "    ELSE 'NO DEFICNIDO'" +
                    "    END AS FACULTAD_DEPENDECIA," +
                    "    E.IN_CODIGO_ESTADO, " +
                    "   CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS NOMBRE_USUARIO " +
                    "  FROM ORPRO_TA_ESTRUCTURA_ORGANICA ORG" +
                    "  INNER JOIN ORGEN_TA_ESTADO E ON E.IN_CODIGO_ESTADO=ORG.IN_CODIGO_ESTADO" +
                    " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=ORG.IN_CODIGO_USUARIO" +
                    " WHERE ORG.IN_CODIGO_ESTADO = 23 AND ORG.IN_FACU_DEPEND=? AND ORG.CH_TIPO_FAC_DEPEND=? " +
                    " ORDER BY ORG.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setInt(1, idFaculDep);
                pstm.setString(2, tipFaculDep);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_estructura_organica obj = new orgen_ta_estructura_organica();
               obj.setIn_codigo_estructura(Integer.parseInt( rs.getString("IN_CODIGO_ESTRUCTURA")));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
               obj.setNombre_FacDep(rs.getString("FACULTAD_DEPENDECIA"));
               obj.setVc_nombre_archivo(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setNombre_Usuario(rs.getString("NOMBRE_USUARIO"));
               listaEstructuras.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al listar estructuras organicas segun usuario: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al listar estructuras organicas segun usuario: "+ex);}
       }
       return listaEstructuras;
    }

    public boolean guardarEstructuraOficio(int idEstructura,int idOficio)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_ESTRUC_ORG_OFICIO_GUARDAR(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("p_in_codigo_estructura", idEstructura);
            cstm.setInt("p_in_codigo_oficio", idOficio );
            cstm.executeUpdate();
            cn.commit();
            estado = true;
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("ERROR AL GUARDAR eo-oficio: "+e);
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
                System.out.println("finally - ERROR AL GUARDAR eo-oficio: "+ex);
            }
            return  estado;
       }
    }


    public Integer getIDDeLaEstructuraOrganica(String ch_codigo_oficio)
    {
        Integer codigo_retorno = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT EO.IN_CODIGO_ESTRUCTURA FROM ORPRO_TA_ESTRUCTURA_ORGANICA EO WHERE EO.CH_CODIGO_ESTRUCTURA = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setString(1,ch_codigo_oficio );

            ResultSet rs = pstm.executeQuery();
            rs.next();
            codigo_retorno = rs.getInt("IN_CODIGO_ESTRUCTURA");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al generar el codigo autogenerado : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al generar el codigo autogenerado : "+ex);
            }
            return codigo_retorno;
       }
    }


    //codigo nuevo genera
    public String getCodigoGenerado()
    {
        String NuevoCodigo="";
        String codigo_retorno="";
        try
        {
            cn = ds.getConnection();


            cn = ds.getConnection();
            String sql = "SELECT FNL_GENERAR_NUEVO_CODIGO_EST_ORG()";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
               NuevoCodigo = rs.getString(1);
            }

            pstm.close();

            if(NuevoCodigo == null || NuevoCodigo.equals(""))
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY");
                Date fecha = new Date();
              codigo_retorno="EO-1-"+dateFormat.format(fecha);
            }
            else
            {
               codigo_retorno = NuevoCodigo;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error al obtener el codigo generado E.O.: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Finally - Error al obtener el codigo generado E.O.: "+ex);}
            return codigo_retorno;
        }
    }

    public boolean guardarEstructuraOrganica(orgen_ta_estructura_organica obj, int idUser, String NombreArchivo,String nomUsuario)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_ESTRUC_INSERT(?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);

            cstm.setString("P_CH_CODIGO_ESTRUCTURA",obj.getCh_codigo_estructura() );
            cstm.setString("P_DT_FECHA",obj.getDt_fecha());
            cstm.setInt("P_IN_FACU_DEPEND",obj.getIn_facu_depend());
            cstm.setString("P_CH_TIPO_FAC_DEPEND",obj.getCh_tipo_fac_depend());
            cstm.setString("P_VC_NOMBRE_ARCHIVO",obj.getVc_nombre_archivo());
            cstm.setString("P_VC_RUTA_ARCHIVO",NombreArchivo);
            cstm.setInt( "P_IN_CODIGO_ESTADO", 21 );//PENDIENTE PARA LA OCDO
            cstm.setInt("P_IN_CODIGO_USUARIO",idUser);
            cstm.setString("P_VC_NOMBRE_USUARIO",nomUsuario);
            cstm.setInt("P_IN_FORMATO",obj.getIn_formato() );
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
            
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al intentar guardar la Estructura Organica: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al intentar guardar la Estructura Organica: "+ex);
            }
       }
       return exito;
    }

    public boolean guardarNuevaVersionEstructuraOrganica(orgen_ta_estructura_organica obj, String nomUsuario, int idUsuario ,String NombreArchivo)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_VERS_ESTRUCT_INSERTAR(?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);

            cstm.setInt("P_IN_CODIGO_ESTRUC",obj.getIn_codigo_estructura() );
            cstm.setString("P_VC_NOMBRE_ARCHIVO",obj.getVc_nombre_archivo());
            cstm.setString("P_VC_RUTA_ARCHIVO",NombreArchivo);
            cstm.setInt("P_IN_CODIGO_USUARIO",idUsuario);
            cstm.setString("P_VC_NOMBRE_USUARIO",nomUsuario);
            cstm.setString("P_DT_FECHA", obj.getDt_fecha() );
            cstm.setInt("P_IN_CODIGO_ESTADO",21 );//PENDIENTE PARA LA OCDO
            cstm.setInt("P_IN_FORMATO", obj.getIn_formato() );
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al intentar guardar la version de la Estructura Organica: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al intentar guardar la version de la Estructura Organica: "+ex);
            }
       }
       return exito;
    }
    
    public ArrayList<orpro_ta_versiones_estruc> getListaVersionesEstructura(String idEstructura)
    {
        ArrayList<orpro_ta_versiones_estruc> listaVersionesEstructura = null;
        try
        {
            listaVersionesEstructura = new ArrayList<orpro_ta_versiones_estruc>();

            cn = ds.getConnection();

            String sql = "select in_codigo_versiones,in_codigo_estruc,dt_fecha,vc_ruta_archivo from orpro_ta_versiones_estruc where in_codigo_estruc = ? order by in_codigo_versiones desc";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(idEstructura));
            ResultSet rs = pstm.executeQuery();

            rs.last();//va al ultimo
            int cont = rs.getRow();//obtenemos el numero de la ultima fila
           // System.out.println("num filas "+cont);
            rs.beforeFirst();// y la regresamos al principio
            while(rs.next() )
            {
              // System.out.println("dentro bucle - num filas "+cont);
               orpro_ta_versiones_estruc obj = new orpro_ta_versiones_estruc();

               obj.setIn_codigo_versiones(Integer.parseInt( rs.getString("in_codigo_versiones")));
               obj.setIn_codigo_estruc(rs.getInt("in_codigo_estruc"));
               obj.setDt_fecha(rs.getDate("dt_fecha").toString());
               obj.setVc_nombre_archivo(rs.getString("vc_ruta_archivo"));
               obj.setNum_version("V. 0"+cont);

               listaVersionesEstructura.add(obj);

               cont--;
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al traer la lista versiones de la estructuras organicas: "+e);
        }
        finally
        {
            try{cn.close();}
            catch ( Exception ex)
            { System.out.println("Error al traer la lista de versiones de la estructuras organicas: "+ex);}
        }
        return listaVersionesEstructura;
    }

   public ArrayList<orpro_ta_observacion_estruc> getListaObservacionesEO(int codEstructura)
    {
        ArrayList<orpro_ta_observacion_estruc> listaObservaciones = null;
        try
        {
            listaObservaciones = new ArrayList<orpro_ta_observacion_estruc>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_OBSERVA,VC_OBSERVACION,VC_NOMBRE_USUARIO,IN_CODIGO_USUARIO,DT_FECHA_CREA " +
                    "FROM ORPRO_TA_OBSERVACION_ESTRUC " +
                    "WHERE IN_CODIGO_ESTRUCTURA = ? " +
                    "ORDER BY DT_FECHA_CREA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, codEstructura);
            ResultSet rs = pstm.executeQuery();
            while( rs.next() )
            {
               SimpleDateFormat fechaWeb = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

               orpro_ta_observacion_estruc obj = new orpro_ta_observacion_estruc();
               obj.setIn_codigo_observa(rs.getInt("IN_CODIGO_OBSERVA"));
               obj.setVc_nombre_usuario(rs.getString("VC_NOMBRE_USUARIO"));
               obj.setDt_fecha_crea(fechaWeb.format( rs.getTimestamp("DT_FECHA_CREA")) );
               obj.setVc_observacion(rs.getString("VC_OBSERVACION"));
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));

               listaObservaciones.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al listar las observacionesde E.O.: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al listar las observacionesde E.O.: "+ex);}
       }
       return listaObservaciones;
    }

    //Guardar
    public boolean guardarObservacionEO(orpro_ta_observacion_estruc obj,int idUsu,int idEstado)
    {
        System.out.println("Guardar Observacion metodo DAO");
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_OBSV_ESTRUCTURA_INSERT(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_ESTRUCTURA", obj.getIn_codigo_estructura());
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsu);
            cstm.setString("P_VC_NOMBRE_USUARIO", obj.getVc_nombre_usuario());
            cstm.setInt("P_IN_CODIGO_ESTADO", idEstado);


            cstm.executeUpdate();
            cn.commit();
             estado = true;
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("ERROR AL GUARDAR OBSERVACIONES: "+e);
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
                System.out.println("finally - ERROR AL GUARDAR OBSERVACIONES: "+ex);
            }
            return  estado;
       }
    }


    //Numero de oficios pendientes LISTA - USARIOS (DEPENDENCIA Y/O FACULTAD)
    public ArrayList<orgen_ta_estructura_organica > getListaDeOrganigramasAprobadasSegunUsuario(String idDepFacu,String tipDepFac)
    {
        ArrayList<orgen_ta_estructura_organica> listaOrganigrama = null;

        try
        {
            listaOrganigrama = new ArrayList<orgen_ta_estructura_organica>();
            cn = ds.getConnection();
            String query = "select in_codigo_estructura,ch_codigo_estructura,dt_fecha " +
                    "from orpro_ta_estructura_organica " +
                    "where in_codigo_estado = 23 and in_facu_depend = ? and ch_tipo_fac_depend= ?";
            PreparedStatement pstm = cn.prepareStatement(query);

            System.out.println("DAO Cod:"+idDepFacu+"Tipo:"+tipDepFac+":");

            pstm.setInt(1,Integer.parseInt(idDepFacu) );
            pstm.setString(2,tipDepFac);
            
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orgen_ta_estructura_organica dataForm = new orgen_ta_estructura_organica();
                dataForm.setIn_codigo_estructura(rs.getInt("in_codigo_estructura"));
                dataForm.setCh_codigo_estructura(rs.getString("ch_codigo_estructura"));
                dataForm.setDt_fecha(rs.getDate("dt_fecha").toString());
                listaOrganigrama.add(dataForm);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error al obtener la lista de organigramas aprobados segun usuario: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al obtener la lista de organigramas aprobados  segun usuario: "+ex);}
       }
       return listaOrganigrama;
    }
}
