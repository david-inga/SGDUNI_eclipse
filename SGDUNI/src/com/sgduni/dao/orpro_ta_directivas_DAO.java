/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.dao;

import com.sgduni.forms.orpro_ta_directivas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * @author marco
 */
public class orpro_ta_directivas_DAO
{
    private DataSource ds;
    private Connection cn;
    private int in_codigo_dir;

    public orpro_ta_directivas_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public int getIn_codigo_dir() {
        return in_codigo_dir;
    }

    public void setIn_codigo_dir(int in_codigo_dir) {
        this.in_codigo_dir = in_codigo_dir;
    }

    
    //Codigo Generedo
    public String getCodigoGenerado()
    {
        String nuevoCodigo="";
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT  CONCAT('DIR-',MAX(d.in_codigo_directiva) + 1) AS NUEVO_CODIGO FROM  ORPRO_TA_DIRECTIVAS d ORDER BY d.in_codigo_directiva DESC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();
            while(rs.next())
            {
                nuevoCodigo = rs.getString("NUEVO_CODIGO");
            }
            if(nuevoCodigo.equals(""))
            {
              nuevoCodigo="DIR-1";
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

    //Codigo Generedo
    public String getIdGeneradosegunCodigo(String ch_codigo_directiva)
    {
        String nuevoCodigo="";
        try
        {
            cn = ds.getConnection();
            String sql = "select in_codigo_directiva from orpro_ta_directivas where ch_codigo_directiva='"+ch_codigo_directiva+"'";
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setString(1, ch_codigo_directiva);
            ResultSet rs =  pstm.executeQuery();
            while(rs.next())
            {
                nuevoCodigo = rs.getString("in_codigo_directiva");
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

    public boolean guardarDirectiva(orpro_ta_directivas obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_DIRECTIVAS_INSERT(?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setString("P_CH_CODIGO_DIRECTIVA", obj.getCh_codigo_directiva()   );
            cstm.setString("P_DT_FECHA", getDateFormateada(obj.getDt_fecha())    );
            cstm.setString("P_VC_ALCANCE", obj.getVc_alcance().trim().toUpperCase() );
            cstm.setString("P_VC_RESPONSABILIDAD", obj.getVc_responsabilidad().trim().toUpperCase() );
            cstm.setString("P_CH_ESTADO", obj.getCh_estado() );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsuario )  );
            cstm.registerOutParameter("P_IN_CODIGO_DIR",Types.NUMERIC );
            cstm.executeUpdate();
            cn.commit();

            this.in_codigo_dir = cstm.getInt("P_IN_CODIGO_DIR");
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


    public ArrayList<orpro_ta_directivas > getDirectivas()
    {
        //System.out.println("estamos en el metodo dao y la fecha que ingresa es: ");
        ArrayList<orpro_ta_directivas> lista = null;
        try
        {
            lista = new ArrayList<orpro_ta_directivas>();
            cn = ds.getConnection();
            String sql = "SELECT D.IN_CODIGO_DIRECTIVA ,D.CH_CODIGO_DIRECTIVA,D.DT_FECHA,D.VC_ALCANCE,D.VC_RESPONSABILIDAD ";
            sql += "FROM ORPRO_TA_DIRECTIVAS D  ";
            sql += "order by d.dt_fecha desc";

            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1, Integer.parseInt(idCap) );
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_directivas obj = new orpro_ta_directivas();
               obj.setIn_codigo_directiva(rs.getInt("IN_CODIGO_DIRECTIVA"));
               obj.setCh_codigo_directiva(rs.getString("CH_CODIGO_DIRECTIVA"));
               obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
               obj.setVc_alcance(rs.getString("VC_ALCANCE"));
               obj.setVc_responsabilidad(rs.getString("VC_RESPONSABILIDAD"));
  
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

    public ArrayList<orpro_ta_directivas > getDirectivasUsuario(String tipo,String fac_dep)
    {
        System.out.println("tipo: "+tipo+":::: fac dep = "+fac_dep);
        ArrayList<orpro_ta_directivas> lista = null;
        try
        {
            lista = new ArrayList<orpro_ta_directivas>();
            cn = ds.getConnection();
            String sql = "select d.in_codigo_directiva,d.ch_codigo_directiva,d.dt_fecha,d.vc_alcance,d.vc_responsabilidad from orpro_ta_directivas d ";
            sql += "inner join orgen_ta_usuario_depen_facu df on d.in_codigo_usuario = df.in_codigo_usuario ";
            sql += "where df.ch_tipo_depen_facu = '"+tipo.trim()+"' and df.in_codigo_depen_facu ="+fac_dep.trim();
            sql += " order by d.dt_fecha desc";

            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1, Integer.parseInt(idCap) );
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_directivas obj = new orpro_ta_directivas();
               obj.setIn_codigo_directiva(rs.getInt("IN_CODIGO_DIRECTIVA"));
               obj.setCh_codigo_directiva(rs.getString("CH_CODIGO_DIRECTIVA"));

               obj.setDt_fecha(rs.getDate("DT_FECHA").toString());

               obj.setVc_alcance(rs.getString("VC_ALCANCE"));
               obj.setVc_responsabilidad(rs.getString("VC_RESPONSABILIDAD"));

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
     //Buscar Directiva
    public orpro_ta_directivas BuscarDirectiva(int in_codigo_directiva)
    {
        orpro_ta_directivas obj = new orpro_ta_directivas();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_DIRECTIVA,CH_CODIGO_DIRECTIVA,DT_FECHA,VC_ALCANCE,VC_RESPONSABILIDAD " +
                         " FROM ORPRO_TA_DIRECTIVAS" +
                         " WHERE IN_CODIGO_DIRECTIVA=?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1,in_codigo_directiva);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_directiva(rs.getInt("IN_CODIGO_DIRECTIVA"));
                obj.setVc_alcance(rs.getString("VC_ALCANCE"));
                obj.setDt_fecha(rs.getString("DT_FECHA"));
                obj.setVc_responsabilidad(rs.getString("VC_RESPONSABILIDAD"));
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
