package com.sgduni.dao;

import com.sgduni.forms.orpro_ta_proc_tupa;
import com.sgduni.forms.orpro_ta_tupa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import javax.sql.DataSource;

/*
 * @author marco
 */
public class orpro_ta_tupa_DAO
{
    private DataSource ds;
    private Connection cn;
    private Integer in_codigo_tupa;

    public orpro_ta_tupa_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public Integer getIn_codigo_tupa() {
        return in_codigo_tupa;
    }

    public void setIn_codigo_tupa(Integer in_codigo_tupa) {
        this.in_codigo_tupa = in_codigo_tupa;
    }


    public ArrayList<orpro_ta_tupa> getTupaDeDependencias(String idDependnecia)
    {
        ArrayList<orpro_ta_tupa> lista = null;
        try
        {
            lista = new ArrayList<orpro_ta_tupa>();
            cn = ds.getConnection();
            String sql = "SELECT t.in_codigo_tupa,t.ch_codigo_tupa,t.dt_fecha,d.vc_nombre as nomdep FROM orpro_ta_tupa t ";
            sql += "inner join orgen_ta_dependencia d on t.in_cod_depend_fac = d.in_codigo_dependencia ";
            sql += "where t.in_cod_depend_fac="+idDependnecia.trim()+" and t.ch_tipo_depend_fac='d' ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1, Integer.parseInt(idDependnecia) );
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_tupa obj = new orpro_ta_tupa();
               obj.setIn_codigo_tupa(rs.getInt("in_codigo_tupa"));
               obj.setCh_codigo_tupa(rs.getString("ch_codigo_tupa"));
               obj.setDt_fecha(rs.getString("dt_fecha"));
               obj.setNombre_DependFac(rs.getString("nomdep"));
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


    public ArrayList<orpro_ta_tupa> getCapDeFacultades(String idFacultad)
    {
        ArrayList<orpro_ta_tupa> lista = null;
        try
        {
            lista = new ArrayList<orpro_ta_tupa>();
            cn = ds.getConnection();
            String sql = "SELECT t.in_codigo_tupa,t.ch_codigo_tupa,t.dt_fecha,f.vc_nombre as nomfac FROM orpro_ta_tupa t ";
            sql += "inner join orgen_ta_facultad f on t.in_cod_depend_fac = f.in_codigo_facultad ";
            sql += "where t.in_cod_depend_fac = "+idFacultad.trim()+" and t.ch_tipo_depend_fac='f' ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1, Integer.parseInt(idFacultad) );
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_tupa obj = new orpro_ta_tupa();
               obj.setIn_codigo_tupa(rs.getInt("in_codigo_tupa"));
               obj.setCh_codigo_tupa(rs.getString("ch_codigo_tupa"));
               obj.setDt_fecha(rs.getString("dt_fecha"));
               obj.setNombre_DependFac(rs.getString("nomfac"));
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

    //Codigo Generedo
    public String getCodigoGenerado()
    {
        String nuevoCodigo="";
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT  CONCAT('TUPA-',TUPA.IN_CODIGO_TUPA + 1) AS NUEVO_CODIGO FROM ORPRO_TA_TUPA TUPA WHERE ROWNUM=1 ORDER BY TUPA.IN_CODIGO_TUPA DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();
            while(rs.next())
            {
                nuevoCodigo=rs.getString("NUEVO_CODIGO");
            }
            if(nuevoCodigo.equals("" )){
                nuevoCodigo = "TUPA-1";
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
        return nuevoCodigo;
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

    public boolean guardarTupa(orpro_ta_tupa obj, String nomUsuario, String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_TUPA_INSERT(?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_TUPA", obj.getCh_codigo_tupa() .toUpperCase().trim()     );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(idUsuario)   );
            cstm.setString("P_DT_FECHA", getDateFormateada(obj.getDt_fecha()) );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado() );
            cstm.setInt("P_IN_COD_DEPEND_FAC", obj.getIn_cod_depend_fac()   );
            cstm.setString("P_CH_TIPO_DEPEND_FAC", obj.getCh_tipo_depend_fac() );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
            cstm.registerOutParameter("P_IN_CODIGO_TUPA",Types.NUMERIC );
            cstm.executeUpdate();
            cn.commit();
            this.in_codigo_tupa = cstm.getInt("P_IN_CODIGO_TUPA");
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

    //Buscar tupac
    public orpro_ta_tupa buscarTupa(Integer in_codigo_tupa)
    {
        orpro_ta_tupa obj = new orpro_ta_tupa();
        try
        {
            cn = ds.getConnection();
            String sql = "select t.in_codigo_tupa,t.ch_codigo_tupa, ";
            sql += "CASE TRIM(t.ch_tipo_depend_fac) ";
            sql += "WHEN null THEN 'NO DEFINIDO(NULL)' ";
            sql += "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=t.in_cod_depend_fac) ";
            sql += "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=t.in_cod_depend_fac) ";
            sql += "ELSE 'NO DEFINIDO' ";
            sql += "END AS NOM_FAC_DEPEND ";
            sql += "from  orpro_ta_tupa t where t.in_codigo_tupa = "+in_codigo_tupa.toString() ;

            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1,in_codigo_tupa);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_tupa( rs.getInt("in_codigo_tupa") );
                obj.setCh_codigo_tupa(rs.getString("ch_codigo_tupa") );
                obj.setNombre_DependFac( rs.getString("NOM_FAC_DEPEND") );
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

    //Buscar tupac
    public ArrayList<orpro_ta_proc_tupa> listarDetalleSubDetalleTupa(Integer in_codigo_tupa)
    {
        ArrayList<orpro_ta_proc_tupa> lista = new ArrayList<orpro_ta_proc_tupa>();
        try
        {
            cn = ds.getConnection();
            String sql = "select p.vc_nombre as NOMPROC,r.vc_nombre_req as NOMREQ,pt.de_tramitacion_porc,pt.de_tramitacion_sol,pt.ch_calif_automa,pt.ch_evaluacion_previa,  ";
            sql += "pt.in_plazo_resolver_dias, pt.vc_inicio_procedimiento,ce.vc_nombre as NOMCARGO,pt.vc_reconsideracion,pt.vc_apelacion ";
            sql += "from orpro_detalle_proc_req pr ";
            sql += "inner join orgen_ta_procedimiento p on  pr.in_codigo_procedimiento = p.in_codigo_procedimiento ";
            sql += "inner join orgen_ta_requisitos r on pr.in_codigo_req = r.in_codigo_req ";
            sql += "left join orpro_ta_proc_tupa pt on pr.in_codigo_det = pt.in_codigo_det ";
            sql += "left join orgen_ta_cargo_estructural ce ON pt.in_autoridad_com_resolver =  ce.in_codigo_cargo_estruc ";
            sql += "where in_codigo_tupa = "+in_codigo_tupa.toString() ;

            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1,in_codigo_tupa);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                orpro_ta_proc_tupa obj = new orpro_ta_proc_tupa();
                obj.setNomproc( rs.getString("NOMPROC") );
                obj.setNomreq( rs.getString("NOMREQ") );
                obj.setDe_tramitacion_porc(rs.getInt("de_tramitacion_porc") );
                obj.setDe_tramitacion_sol(rs.getInt("de_tramitacion_sol") );
                obj.setCh_calif_automa(rs.getString("ch_calif_automa"));
                obj.setCh_evaluacion_previa(rs.getString("ch_evaluacion_previa"));
                obj.setIn_plazo_resolver_dias(rs.getInt("in_plazo_resolver_dias"));
                obj.setVc_inicio_procedimiento(rs.getString("vc_inicio_procedimiento"));
                obj.setNombre_autoridad(rs.getString("NOMCARGO"));
                obj.setVc_reconsideracion(rs.getString("vc_reconsideracion"));
                obj.setVc_apelacion(rs.getString("vc_apelacion"));
                lista.add(obj);
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
        return lista;
    }



}
