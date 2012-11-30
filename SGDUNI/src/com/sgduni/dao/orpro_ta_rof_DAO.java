package com.sgduni.dao;
import com.sgduni.forms.orgen_ta_estructura_organica;
import com.sgduni.forms.orpro_ta_base_legal_rof;
import com.sgduni.forms.orpro_ta_registro_rof;
import com.sgduni.forms.orpro_ta_rof;
import com.sgduni.forms.orpro_ta_rof_funciones_generales;
import com.sgduni.forms.orpro_ta_rof_unidad_area;
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
public class orpro_ta_rof_DAO
{
    protected DataSource ds;
    Connection cn;
    
    public orpro_ta_rof_DAO(DataSource ds)
    {
        this.ds = ds;
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
            String sql = "SELECT FNL_GENERAR_NUEVO_CODIGO_ROF()";
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
                SimpleDateFormat dateFormatMes = new SimpleDateFormat("MM");
                Date fecha = new Date();

                codigo_retorno="ROF-N-1-"+dateFormat.format(fecha)+"-"+dateFormatMes.format(fecha);
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

    public ArrayList<orpro_ta_rof> getListasReporteRof(int idRof, int idVersionRof)
    {
        System.out.println("inicio el metodo getListasReporteRof");
        ArrayList<orpro_ta_rof> listaReporteRof = null;
        try
        {
            listaReporteRof = new ArrayList<orpro_ta_rof>();
            cn = ds.getConnection();

                String sql = "SELECT "
                +"V.DT_FECHA,V.VC_INTRODUCCION,V.VC_NATURALEZA_FINALIDAD, "
                +"V.VC_ALCANCE,V.VC_RELACIONES_INTERINSTITUCIONALES, "
                +"V.VC_DISPOSICIONES_FINALES , E.VC_RUTA_ARCHIVO, "
                +"CASE TRIM(R.CH_TIPO_DEPEND_FAC) "
                +" WHEN null THEN 'NO DEFINIDO' "
                +" WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=R.IN_DEPEND_FAC)"
                +"   WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=R.IN_DEPEND_FAC)"
                +"   ELSE 'NO DEFINIDO' "
                +"   END AS FACULTAD_DEPENDECIA "
                +"FROM orpro_ta_rof_versiones V "
                +"inner join orpro_ta_rof  R on V.IN_CODIGO_ROF = R.IN_CODIGO_ROF "
                +"INNER JOIN orpro_ta_estructura_organica E on R.IN_CODIGO_ESTRUCTURA = E.IN_CODIGO_ESTRUCTURA "
                +"WHERE R.IN_CODIGO_ROF= ? AND V.IN_CODIGO_ROF_VERSION = ?";

                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setInt(1, idRof);
                pstm.setInt(2, idVersionRof);
                ResultSet rs = pstm.executeQuery();
                while(rs.next() )
                {
                   orpro_ta_rof obj = new orpro_ta_rof();

                   obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
                   obj.setVc_introduccion(rs.getString("VC_INTRODUCCION"));
                   obj.setVc_naturaleza_finalidad(rs.getString("VC_NATURALEZA_FINALIDAD"));
                   obj.setVc_alcance(rs.getString("VC_ALCANCE"));
                   obj.setNombreEstructura(rs.getString("VC_RUTA_ARCHIVO"));
                   obj.setVc_relaciones_interinstitucionales(rs.getString("VC_RELACIONES_INTERINSTITUCIONALES"));
                   obj.setVc_disposiciones_finales(rs.getString("VC_DISPOSICIONES_FINALES"));
                   obj.setNombreFaculDepen(rs.getString("FACULTAD_DEPENDECIA"));

                   listaReporteRof.add(obj);
                }
                rs.close();
                pstm.close();
                System.out.println(" SE cumplio el  dao PDF se genero bien  ");
        }
        catch(Exception e)
        {
            System.out.println("Error en getListaRegistrosRof: "+e);
        }
       finally
       {
            try
            {
                cn.close();
                System.out.println(" SE cerro la coenxcion en  el  dao PDF bien ");
            }
            catch ( Exception ex)
            {
                System.out.println("Error en getListaRegistrosRof(): "+ex);
            }
       }
       return listaReporteRof;
    }

    //Guarda la primera parte del rof
    public boolean guardarPrimeraParteRof(orpro_ta_rof obj,int idUser)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_ROF_GUARDAR(?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
             cstm.setString("p_ch_codigo_rof",obj.getCh_codigo_rof() );
             cstm.setInt("p_in_depend_fac",obj.getIn_depend_fac());
             cstm.setString("p_ch_tipo_depend_fac",obj.getCh_tipo_depend_fac()  );
             cstm.setString("p_vc_introduccion",obj.getVc_introduccion() );
             cstm.setString("p_vc_naturaleza_finalidad",obj.getVc_naturaleza_finalidad() );
             cstm.setInt("p_in_codigo_usuario",idUser );

            cstm.executeUpdate();

            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("error al guardar la primera parte del rof: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al guardar la primera parte del rof: "+ex);
            }
       }
       return exito;
    }

    //Guarda la segunda parte del rof
    public boolean guardarSegundaParteRof(orpro_ta_rof obj,int idversion)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_ROF_GUARDAR_PDOS(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("p_in_codigo_rof",obj.getIn_codigo_rof() );
            cstm.setInt("p_in_codigo_rof_version",idversion );
            cstm.setString("p_vc_alcance",obj.getVc_alcance() );
            cstm.setInt("p_in_codigo_estructura",obj.getIn_codigo_estructura()  );
            cstm.executeUpdate();

            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("error al guardar la segunda parte del rof: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al guardar la segunda parte del rof: "+ex);
            }
       }
       return exito;
    }

     //Guarda la tercera parte del rof
    public boolean guardarTerceraParteRof(orpro_ta_rof obj,int idVersion)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_ROF_GUARDAR_PTRES(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("p_in_codigo_rof",obj.getIn_codigo_rof() );
            cstm.setInt("p_in_codigo_rof_version",idVersion );
            cstm.setString("p_vc_relaciones_interinstitucionales",obj.getVc_relaciones_interinstitucionales() );
            cstm.setString("p_vc_disposiciones_finales",obj.getVc_disposiciones_finales()  );
            cstm.executeUpdate();

            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("error al guardar la tercera parte del rof: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al guardar la tercera parte del rof: "+ex);
            }
       }
       return exito;
    }

    //se obtiene el id del rof
    public orpro_ta_rof getIDDelROF(String ch_codigo_rof)
    {
        orpro_ta_rof rof = new orpro_ta_rof();
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT ROF.IN_CODIGO_ROF,ROF.CH_CODIGO_ROF FROM ORPRO_TA_ROF ROF WHERE ROF.CH_CODIGO_ROF = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setString(1,ch_codigo_rof );

            ResultSet rs = pstm.executeQuery();
            rs.next();
            rof.setIn_codigo_rof( rs.getInt("IN_CODIGO_ROF") );
            rof.setCh_codigo_rof(rs.getString("CH_CODIGO_ROF"));
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
            return rof;
       }
    }

    //se obtiene el id del la version segun el id del rof
    public int getIDVERSIONDelROF(int in_codigo_rof)
    {
        int in_version_rof = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT RV.IN_CODIGO_ROF_VERSION FROM ORPRO_TA_ROF_VERSIONES RV WHERE RV.IN_CODIGO_ROF = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1,in_codigo_rof );

            ResultSet rs = pstm.executeQuery();
            rs.next();
            in_version_rof = rs.getInt("IN_CODIGO_ROF_VERSION");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            in_version_rof = 0;
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
            return in_version_rof;
       }
    }

    //metodos para base legal BASE LEGAL
    
    public boolean guardarBaseLegal(orpro_ta_base_legal_rof obj)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ROF_GUARDAR_BASE_LEGAL(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
             cstm.setInt("p_in_codigo_rof",obj.getIn_codigo_rof() );
             cstm.setString("p_vc_orden",obj.getOrden() );
             cstm.setString("p_vc_descripcion",obj.getVc_descripcion() );
             cstm.setInt("p_in_codigo_version", obj.getIn_codigo_version());

            cstm.executeUpdate();

            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al guardar la base legal: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al guardar la base legal: "+ex);
            }
       }
       return exito;
    }

     public ArrayList<orpro_ta_base_legal_rof> getListBaseLegales(int idROF , int idVersionRof)
    {
        ArrayList<orpro_ta_base_legal_rof> listaRofBaseLegal = null;

        try
        {
            listaRofBaseLegal = new ArrayList<orpro_ta_base_legal_rof>();

            cn = ds.getConnection();

            String sql="SELECT CH_ORDEN, VC_DESCRIPCION FROM orpro_ta_rof_base_legal WHERE IN_CODIGO_ROF = ? and IN_CODIGO_VERSION = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idROF);
            pstm.setInt(2, idVersionRof);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
              orpro_ta_base_legal_rof obj = new  orpro_ta_base_legal_rof();
              obj.setOrden(rs.getString("CH_ORDEN"));
              obj.setVc_descripcion(rs.getString("VC_DESCRIPCION"));
              listaRofBaseLegal.add(obj);

            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en getListBaseLegales(): "+e);
        }
        finally
        {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en getListBaseLegales(): "+ex);}
        }
        return listaRofBaseLegal;
    }


    //metodos para Funcion General
    public boolean guardarFuncionGeneral(orpro_ta_rof_funciones_generales obj)
    {
        System.out.println("se ejecuto el metodod guardarFuncionGeneral ");
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ROF_GUARDAR_FUNCION_GENERAL(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);

            cstm.setInt("p_in_codigo_rof",obj.getIn_codigo_rof() );
            cstm.setString("p_vc_orden",obj.getCh_orden() );
            cstm.setString("p_vc_descripcion",obj.getVc_descripcion() );
            cstm.setInt("p_in_codigo_version", obj.getIn_codigo_version());

            cstm.executeUpdate();

            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al guardar la funcion: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al guardar la funcion: "+ex);
            }
       }
       return exito;
    }

    public ArrayList<orpro_ta_rof_funciones_generales> getListaFuncionGeneralSegunIDRof(int idRof,int idVersion)
    {
        System.out.println("inicio el metodo getListaFuncionGeneralSegunIDRof ");
        ArrayList<orpro_ta_rof_funciones_generales> listaFuncionesRof = null;
        try
        {
            listaFuncionesRof = new ArrayList<orpro_ta_rof_funciones_generales>();
            cn = ds.getConnection();

                String sql = "select in_codigo_funcion,ch_orden,vc_descripcion " +
                        "from orpro_ta_rof_funciones_generales " +
                        "where in_codigo_rof = ? and in_codigo_version = ?";
                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setInt(1, idRof);
                pstm.setInt(2, idVersion);
                ResultSet rs = pstm.executeQuery();
                while(rs.next() )
                {
                   orpro_ta_rof_funciones_generales obj = new orpro_ta_rof_funciones_generales();

                   obj.setIn_codigo_funcion(rs.getInt("in_codigo_funcion"));
//                 obj.setIn_codigo_rof(rs.getInt("in_codigo_rof"));
                   obj.setCh_orden(rs.getString("ch_orden"));
                   obj.setVc_descripcion(rs.getString("vc_descripcion"));
//                 obj.setIn_codigo_version(rs.getInt("in_codigo_version") );

                   listaFuncionesRof.add(obj);
                }
                rs.close();
                pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Erro en getListaFuncionGeneralSegunIDRof: "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("Error en getListaFuncionGeneralSegunIDRof(): "+ex);}
       }
       return listaFuncionesRof;
    }

    //metodos para registro
    public ArrayList<orpro_ta_registro_rof> getListaRegistrosSegunIDRof(int idRof,int idVersion)
    {
        System.out.println("inicio el metodo getListaRegistrosSegunIDRof ");
        ArrayList<orpro_ta_registro_rof> listaBaseLegalRof = null;
        try
        {
            listaBaseLegalRof = new ArrayList<orpro_ta_registro_rof>();
            cn = ds.getConnection();

                String sql = "";
                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setInt(1, idRof);
                ResultSet rs = pstm.executeQuery();
                while(rs.next() )
                {
                   orpro_ta_registro_rof obj = new orpro_ta_registro_rof();

//                   obj.setIn_codigo_funcion(rs.getInt("in_codigo_funcion"));
//                   obj.setIn_codigo_rof(rs.getInt("in_codigo_rof"));
//                   obj.setCh_orden(rs.getString("ch_orden"));
//                   obj.setVc_descripcion(rs.getString("vc_descripcion"));
//                   obj.setIn_codigo_version(rs.getInt("in_codigo_version") );

                   listaBaseLegalRof.add(obj);
                }
                rs.close();
                pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error en getListaRegistrosSegunIDRof: "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("Error en getListaRegistrosSegunIDRof(): "+ex);}
       }
       return listaBaseLegalRof;
    }

    public boolean guardarRegistroROF(orpro_ta_registro_rof obj)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_REGISTRO_ROF_GUARDAR(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
             cstm.setInt("p_in_codigo_rof",obj.getIn_codigo_rof() );
             cstm.setInt("p_in_codigo_organo",obj.getIn_codigo_organo() );
             cstm.setString("p_vc_nombre_unidad",obj.getVc_nombre_unidad() );
             cstm.setString("p_vc_descripcion_unidad",obj.getVc_descripcion_unidad() );
             cstm.setInt("p_in_codigo_version", obj.getIn_codigo_version());
            cstm.executeUpdate();

            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al guardar la base legal: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al guardar la base legal: "+ex);
            }
       }
       return exito;
    }

     public ArrayList<orpro_ta_registro_rof> getListaRegistrosRof(int idRof, int idVersionRof)
    {
        System.out.println("inicio el metodo getListaRegistrosRof");
        ArrayList<orpro_ta_registro_rof> listaRegistroRof = null;
        try
        {
            listaRegistroRof = new ArrayList<orpro_ta_registro_rof>();
            cn = ds.getConnection();

                String sql = "SELECT in_codigo_registro,vc_nombre_unidad " +
                        "FROM orpro_ta_registro_rof " +
                        "WHERE in_codigo_rof = ? and in_codigo_version = ?";
                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setInt(1, idRof);
                pstm.setInt(2, idVersionRof);
                ResultSet rs = pstm.executeQuery();
                while(rs.next() )
                {
                   orpro_ta_registro_rof obj = new orpro_ta_registro_rof();

                   obj.setIn_codigo_registro(rs.getInt("in_codigo_registro"));
                   obj.setVc_nombre_unidad(rs.getString("vc_nombre_unidad"));

                   listaRegistroRof.add(obj);
                }
                rs.close();
                pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Erro en getListaRegistrosRof: "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("Error en getListaRegistrosRof(): "+ex);}
       }
       return listaRegistroRof;
    }

    //metodos para areas

    public boolean guardarAreadeUnidadROF(orpro_ta_rof_unidad_area obj)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_REGISTRO_AREA_ROF_GUARDAR(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
             cstm.setInt("p_in_codigo_registro",obj.getIn_codigo_registro() );
             cstm.setString("p_vc_nombre_area",obj.getVc_nombre_area() );
             cstm.setString("p_vc_descripcion_area",obj.getVc_descripcion_area() );
            cstm.executeUpdate();

            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error guardarAreadeUnidadROF(): "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error guardarAreadeUnidadROF() "+ex);
            }
       }
       return exito;
    }

     public ArrayList<orpro_ta_rof_unidad_area> getListaAreasSegunUnidadRof(int idRegistro)
    {
        System.out.println("inicio el metodo getListaAreasSegunUnidadRof");
        ArrayList<orpro_ta_rof_unidad_area> listaAreasRof = null;
        try
        {
            listaAreasRof = new ArrayList<orpro_ta_rof_unidad_area>();
            cn = ds.getConnection();

                String sql = "SELECT vc_nombre_area " +
                        "FROM orpro_ta_rof_unidad_area " +
                        "WHERE in_codigo_registro = ?";
                PreparedStatement pstm = cn.prepareStatement(sql);
                pstm.setInt(1, idRegistro);
                ResultSet rs = pstm.executeQuery();
                while(rs.next() )
                {
                   orpro_ta_rof_unidad_area obj = new orpro_ta_rof_unidad_area();
                   obj.setVc_nombre_area(     rs.getString("vc_nombre_area"));
                   //obj.setVc_descripcion_area(rs.getString("vc_descripcion_area"));
                   listaAreasRof.add(obj);
                }
                rs.close();
                pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Erro en getListaAreasSegunUnidadRof(): "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("Error en getListaAreasSegunUnidadRof(): "+ex);}
       }
       return listaAreasRof;
    }

      // ORGRANIGRAMA PARA ADJUNTAR AL ROF
    public ArrayList<orgen_ta_estructura_organica> getListaDeOrganigramasAprovadasSegunUsuario(String idDepFacu,String tipDepFac)
    {
        ArrayList<orgen_ta_estructura_organica> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orgen_ta_estructura_organica>();
            cn = ds.getConnection();
            String query = "";
            PreparedStatement pstm = cn.prepareStatement(query);

            System.out.println("DAO Cod:"+idDepFacu+"Tipo:"+tipDepFac+":");

            pstm.setString(1,tipDepFac);
            pstm.setInt(2,Integer.parseInt(idDepFacu) );
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orgen_ta_estructura_organica dataForm = new orgen_ta_estructura_organica();
                dataForm.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));

                //System.out.println("datos FIN.> "+rs.getInt("DIAS_TRANSCURRIDOS"));
                listaOficios.add(dataForm);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error al obtener la lista de oficios pendientes  segun usuario: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al obtener la lista de oficios pendientes  segun usuario: "+ex);}
       }
       return listaOficios;
    }

    //LISTA DE ROF GENERAL EN TRAMITE
    public ArrayList<orpro_ta_rof> getListaRof()
    {
        ArrayList<orpro_ta_rof> listaRof = null;
        try
        {
            listaRof = new ArrayList<orpro_ta_rof>();
            cn = ds.getConnection();
            String sql = "SELECT ROF.IN_CODIGO_ROF,ROF.CH_CODIGO_ROF, ROF.DT_FECHA, "+
                        "CASE ROF.CH_TIPO_DEPEND_FAC WHEN null THEN 'NO DEFINIDO' "+
                        "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ROF.IN_DEPEND_FAC) "+
                        "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ROF.IN_DEPEND_FAC) "+
                        "ELSE 'NO DEFINIDO' "+
                        "END AS FACULTAD_DEPENDENCIA, "+
                        "EO.IN_CODIGO_ESTRUCTURA, "+
                        "EO.CH_CODIGO_ESTRUCTURA, "+
                        "EO.VC_RUTA_ARCHIVO "+
                        "FROM ORPRO_TA_ROF ROF "+
                        "INNER JOIN ORPRO_TA_ESTRUCTURA_ORGANICA EO ON ROF.IN_CODIGO_ESTRUCTURA = EO.IN_CODIGO_ESTRUCTURA "+
                        "WHERE ROF.CH_ESTADO='2' ORDER BY ROF.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_rof obj = new orpro_ta_rof();
               obj.setIn_codigo_rof(Integer.parseInt(rs.getString("IN_CODIGO_ROF")));
               obj.setCh_codigo_rof(rs.getString("CH_CODIGO_ROF"));
               obj.setDt_fecha(rs.getString("DT_FECHA"));
               obj.setNombreFaculDepen(rs.getString("FACULTAD_DEPENDENCIA"));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
                obj.setNombreEstructura(rs.getString("VC_RUTA_ARCHIVO"));
               listaRof.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en getListaRof(): "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en getListaRof(): "+ex);}
       }
       return listaRof;
    }
    
    //LISTA DE ROF EN TRAMITE VISTA SOLO PARA EL USUARIO APODERADO
    public ArrayList<orpro_ta_rof> getListaRofPorUsuario(int idFacuDepen,String tipoFacuDepen)
    {
        ArrayList<orpro_ta_rof> listaRof = null;
        try
        {
            listaRof = new ArrayList<orpro_ta_rof>();
            cn = ds.getConnection();
            String sql = "SELECT ROF.IN_CODIGO_ROF,ROF.CH_CODIGO_ROF, ROF.DT_FECHA, "+
                        "CASE ROF.CH_TIPO_DEPEND_FAC WHEN null THEN 'NO DEFINIDO' "+
                        "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ROF.IN_DEPEND_FAC) "+
                        "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ROF.IN_DEPEND_FAC) "+
                        "ELSE 'NO DEFINIDO' "+
                        "END AS FACULTAD_DEPENDENCIA, "+
                        "EO.IN_CODIGO_ESTRUCTURA, "+
                        "EO.CH_CODIGO_ESTRUCTURA, "+
                        "EO.VC_RUTA_ARCHIVO "+
                        "FROM ORPRO_TA_ROF ROF "+
                        "INNER JOIN ORPRO_TA_ESTRUCTURA_ORGANICA EO ON ROF.IN_CODIGO_ESTRUCTURA = EO.IN_CODIGO_ESTRUCTURA "+
                        "WHERE ROF.CH_ESTADO='2'  AND IN_DEPEND_FAC=?  and CH_TIPO_DEPEND_FAC=? ORDER BY ROF.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idFacuDepen);
            pstm.setString(2, tipoFacuDepen);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_rof obj = new orpro_ta_rof();
               obj.setIn_codigo_rof(Integer.parseInt(rs.getString("IN_CODIGO_ROF")));
               obj.setCh_codigo_rof(rs.getString("CH_CODIGO_ROF"));
               obj.setNombreEstructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setDt_fecha(rs.getString("DT_FECHA"));
               obj.setNombreFaculDepen(rs.getString("FACULTAD_DEPENDENCIA"));
                obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
                obj.setNombreEstructura(rs.getString("VC_RUTA_ARCHIVO"));
               listaRof.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Erro en getListaRofPorUsuario: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error en getListaRofPorUsuario(): "+ex);}
       }
       return listaRof;
    }

     //Lista DE DOCUMENTOS ROF 
    public ArrayList<orpro_ta_rof> getListaRofBorrador()
    {
        ArrayList<orpro_ta_rof> listaRof = null;
        try
        {
            listaRof = new ArrayList<orpro_ta_rof>();
            cn = ds.getConnection();
            String sql = "SELECT ROF.IN_CODIGO_ROF,ROF.CH_CODIGO_ROF, ROF.DT_FECHA, "+
                        "CASE ROF.CH_TIPO_DEPEND_FAC WHEN null THEN 'NO DEFINIDO' "+
                        "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ROF.IN_DEPEND_FAC) "+
                        "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ROF.IN_DEPEND_FAC) "+
                        "ELSE 'NO DEFINIDO' "+
                        "END AS FACULTAD_DEPENDENCIA, "+
                        "EO.IN_CODIGO_ESTRUCTURA, "+
                        "EO.CH_CODIGO_ESTRUCTURA, "+
                        "EO.VC_RUTA_ARCHIVO,OV.IN_CODIGO_GUARDADO "+
                        "FROM ORPRO_TA_ROF ROF "+
                        "LEFT JOIN ORPRO_TA_ESTRUCTURA_ORGANICA EO ON ROF.IN_CODIGO_ESTRUCTURA = EO.IN_CODIGO_ESTRUCTURA "+
                        "INNER JOIN ORPRO_TA_ROF_VERSIONES OV ON ROF.IN_CODIGO_ROF = OV.IN_CODIGO_ROF "+
                        "WHERE ROF.CH_ESTADO='1' ORDER BY ROF.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_rof obj = new orpro_ta_rof();
               obj.setIn_codigo_rof(Integer.parseInt(rs.getString("IN_CODIGO_ROF")));
               obj.setCh_codigo_rof(rs.getString("CH_CODIGO_ROF"));
               obj.setDt_fecha(rs.getString("DT_FECHA"));
               obj.setNombreFaculDepen(rs.getString("FACULTAD_DEPENDENCIA"));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
                obj.setNombreEstructura(rs.getString("VC_RUTA_ARCHIVO"));
               listaRof.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en getListaRof(): "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en getListaRof(): "+ex);}
       }
       return listaRof;
    }

    //LISTA DE ROF QUE NO ESTAN EN TRAMITE VISTA SOLO PARA EL USUARIO APODERADO
    public ArrayList<orpro_ta_rof> getListaRofPorUsuarioBorrador(int idFacuDepen,String tipoFacuDepen)
    {
        ArrayList<orpro_ta_rof> listaRof = null;
        try
        {
            listaRof = new ArrayList<orpro_ta_rof>();
            cn = ds.getConnection();
            String sql ="SELECT ROF.IN_CODIGO_ROF,ROF.CH_CODIGO_ROF, ROF.DT_FECHA, "+
                        "CASE ROF.CH_TIPO_DEPEND_FAC WHEN null THEN 'NO DEFINIDO' "+
                        "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=ROF.IN_DEPEND_FAC) "+
                        "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=ROF.IN_DEPEND_FAC) "+
                        "ELSE 'NO DEFINIDO' "+
                        "END AS FACULTAD_DEPENDENCIA, "+
                        "EO.IN_CODIGO_ESTRUCTURA, "+
                        "EO.CH_CODIGO_ESTRUCTURA, "+
                        "EO.VC_RUTA_ARCHIVO,OV.IN_CODIGO_GUARDADO "+
                        "FROM ORPRO_TA_ROF ROF "+
                        "LEFT JOIN ORPRO_TA_ESTRUCTURA_ORGANICA EO ON ROF.IN_CODIGO_ESTRUCTURA = EO.IN_CODIGO_ESTRUCTURA "+
                        "INNER JOIN ORPRO_TA_ROF_VERSIONES OV ON ROF.IN_CODIGO_ROF = OV.IN_CODIGO_ROF "+
                        "WHERE ROF.CH_ESTADO='1'  AND IN_DEPEND_FAC=?  and CH_TIPO_DEPEND_FAC=? ORDER BY ROF.DT_FECHA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idFacuDepen);
            pstm.setString(2, tipoFacuDepen);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_rof obj = new orpro_ta_rof();
               obj.setIn_codigo_rof(rs.getInt("IN_CODIGO_ROF"));
               obj.setCh_codigo_rof(rs.getString("CH_CODIGO_ROF"));
               obj.setNombreEstructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setDt_fecha(rs.getString("DT_FECHA"));
               obj.setNombreFaculDepen(rs.getString("FACULTAD_DEPENDENCIA"));
               obj.setCh_codigo_estructura(rs.getString("CH_CODIGO_ESTRUCTURA"));
               obj.setNombreEstructura(rs.getString("VC_RUTA_ARCHIVO"));
               obj.setIn_codigo_guardado(rs.getInt("IN_CODIGO_GUARDADO"));
               listaRof.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Erro en getListaRofPorUsuario: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error en getListaRofPorUsuario(): "+ex);}
       }
       return listaRof;
    }

}

