/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;

import com.sgduni.forms.orpro_det_cap;
import com.sgduni.forms.orpro_ta_cap;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author marco
 */
public class orpro_ta_cap_DAO
{
    private DataSource ds;
    private Connection cn;
    private Integer in_codigo_cap;

    public orpro_ta_cap_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public Integer getIn_codigo_cap() {
        return in_codigo_cap;
    }

    public void setIn_codigo_cap(Integer in_codigo_cap) {
        this.in_codigo_cap = in_codigo_cap;
    }


//BIEN
    //Buscar tupac
    public orpro_ta_cap buscarCap(Integer in_codigo_cap )
    {
        orpro_ta_cap obj = new orpro_ta_cap();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT in_codigo_cap,ch_codigo_cap, ";
            sql += "CASE TRIM(ch_tipo_depend_fac ) ";
            sql += "WHEN null THEN 'NO DEFINIDO(NULL)'  ";
            sql += "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD = in_cod_depend_fac  )  ";
            sql += "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA = in_cod_depend_fac) ";
            sql += "ELSE 'NO DEFINIDO' ";
            sql += "END AS NOM_FAC_DEPEND ";
            sql += "from orpro_ta_cap where in_codigo_cap = "+in_codigo_cap.toString().trim();

            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1,in_codigo_tupa);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_cap( rs.getInt("in_codigo_cap") );
                obj.setCh_codigo_cap(rs.getString("ch_codigo_cap") );
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


    public ArrayList<orpro_det_cap> getDetalleCap(Integer idCap)
    {
        ArrayList<orpro_det_cap> lista = null;
        try
        {
            lista = new ArrayList<orpro_det_cap>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_CARGO_ESTRUC,IN_TOTAL,IN_SITUA_CARGO_PREV,IN_SITUA_CARGO_OCUPADO,CH_CARGO_CONFIANZA,VC_OBSERVACION FROM ORPRO_DET_CAP WHERE IN_CODIGO_CAP = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idCap);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_det_cap obj = new orpro_det_cap();

               obj.setIn_codigo_cargo_estruc(rs.getInt("IN_CODIGO_CARGO_ESTRUC"));
               obj.setIn_total(rs.getInt("IN_TOTAL"));
               obj.setIn_situa_cargo_prev(rs.getInt("IN_SITUA_CARGO_PREV"));
               obj.setIn_situa_cargo_ocupado(rs.getInt("IN_SITUA_CARGO_OCUPADO"));
               obj.setCh_cargo_confianza(rs.getString("CH_CARGO_CONFIANZA"));
               obj.setVc_observacion( rs.getString("VC_OBSERVACION") );
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

    

    public ArrayList<orpro_ta_cap> getCapDeDependnecias(String idDependnecia)
    {
        ArrayList<orpro_ta_cap> lista = null;
        try
        {
            lista = new ArrayList<orpro_ta_cap>();
            cn = ds.getConnection();
            String sql = "SELECT CAP.IN_CODIGO_CAP,CAP.CH_CODIGO_CAP,CAP.DT_FECHA_CAP,E.VC_NOMBRE_ESTADO FROM ORPRO_TA_CAP CAP INNER JOIN ORGEN_TA_DEPENDENCIA D ON CAP.IN_COD_DEPEND_FAC = D.IN_CODIGO_DEPENDENCIA INNER JOIN ORGEN_TA_ESTADO E ON cap.ch_estado_cap = e.in_codigo_estado WHERE CAP.IN_COD_DEPEND_FAC = ? AND CH_TIPO_DEPEND_FAC = 'd'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(idDependnecia) );
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_cap obj = new orpro_ta_cap();
               obj.setIn_codigo_cap(rs.getInt("IN_CODIGO_CAP"));
               obj.setCh_codigo_cap(rs.getString("CH_CODIGO_CAP"));
               obj.setDt_fecha_cap(rs.getString("DT_FECHA_CAP"));
               obj.setNombre_estado(rs.getString("VC_NOMBRE_ESTADO"));
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


    public ArrayList<orpro_ta_cap> getCapDeFacultades(String idFacultad)
    {
        ArrayList<orpro_ta_cap> lista = null;
        try
        {
            lista = new ArrayList<orpro_ta_cap>();
            cn = ds.getConnection();
            String sql = "SELECT CAP.IN_CODIGO_CAP,CAP.CH_CODIGO_CAP,CAP.DT_FECHA_CAP,E.VC_NOMBRE_ESTADO FROM ORPRO_TA_CAP CAP INNER JOIN ORGEN_TA_FACULTAD F ON CAP.IN_COD_DEPEND_FAC = F.IN_CODIGO_FACULTAD INNER JOIN ORGEN_TA_ESTADO E ON cap.ch_estado_cap = e.in_codigo_estado WHERE CAP.IN_COD_DEPEND_FAC = ? AND CH_TIPO_DEPEND_FAC = 'f'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(idFacultad) );
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_cap obj = new orpro_ta_cap();
               obj.setIn_codigo_cap(rs.getInt("IN_CODIGO_CAP"));
               obj.setCh_codigo_cap(rs.getString("CH_CODIGO_CAP"));
               obj.setDt_fecha_cap(rs.getString("DT_FECHA_CAP"));
               obj.setNombre_estado(rs.getString("VC_NOMBRE_ESTADO"));
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

    public boolean guardarCap(orpro_ta_cap obj, String nomUsuario, String idUsuario)
    {
      
        boolean estado = false;
        try
        {
            
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_CAP_INSERT(?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_CAP", obj.getCh_codigo_cap().toUpperCase()     );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(idUsuario)   );
            cstm.setString("P_DT_FECHA_CAP", getDateFormateada(obj.getDt_fecha_cap() ) );
            cstm.setString("P_CH_ESTADO_CAP", "13" );
            cstm.setInt("P_IN_COD_DEPEND_FAC", obj.getIn_cod_depend_fac()   );
            cstm.setString("P_CH_TIPO_DEPEND_FAC", obj.getCh_tipo_depend_fac() );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
            cstm.registerOutParameter("P_IN_CODIGO_CAP",Types.NUMERIC );
            cstm.executeUpdate();
            cn.commit();

            this.in_codigo_cap=cstm.getInt("P_IN_CODIGO_CAP");
            cstm.close();
             
            estado = true;
            
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

     public String getDateFormateada(String fecha)
    {
        String[] fechaA = fecha.split("/");
        String anio = fechaA[2];
        String mes =  fechaA[1];
        String dia =  fechaA[0];
        String FechaFormateada = anio+"/"+mes+"/"+dia;
      return FechaFormateada;
    }

    public boolean guardarCap(orpro_ta_cap obj, String nomUsuario, String idUsuario,int idEstado)
    {
        System.out.println("id del estado = "+idEstado);
        boolean estado = false;
        try
        {

            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_CAP_INSERT(?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_CAP", obj.getCh_codigo_cap().toUpperCase()     );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt(idUsuario)   );
            cstm.setString("P_DT_FECHA_CAP", getDateFormateada(obj.getDt_fecha_cap()) );
            cstm.setString("P_CH_ESTADO_CAP", String.valueOf(idEstado) );
            cstm.setInt("P_IN_COD_DEPEND_FAC", obj.getIn_cod_depend_fac()   );
            cstm.setString("P_CH_TIPO_DEPEND_FAC", obj.getCh_tipo_depend_fac() );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
            cstm.registerOutParameter("P_IN_CODIGO_CAP",Types.NUMERIC );
            cstm.executeUpdate();
            cn.commit();

            this.in_codigo_cap=cstm.getInt("P_IN_CODIGO_CAP");
            cstm.close();

            estado = true;

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

    public int getIDGenerado(String idCap)
    {
        orpro_ta_cap obj = new orpro_ta_cap();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_CAP FROM ORPRO_TA_CAP WHERE CH_CODIGO_CAP = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, idCap );
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_cap(rs.getInt("IN_CODIGO_CAP"));
                 System.out.println("ID RETORNADO ES "+rs.getInt("IN_CODIGO_CAP"));
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
        return obj.getIn_codigo_cap();
    }
    //Codigo Generedo
    public String getCodigoGenerado()
    {
        String nuevoCodigo="";
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT  CONCAT('CAP-',CP.IN_CODIGO_CAP + 1) AS NUEVO_CODIGO FROM  ORPRO_TA_CAP CP WHERE ROWNUM=1 ORDER BY CP.IN_CODIGO_CAP DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();
            while(rs.next())
            {                
                nuevoCodigo=rs.getString("NUEVO_CODIGO");
            }
            if(nuevoCodigo.equals(""))
            {
              nuevoCodigo="CAP-1";
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
}
