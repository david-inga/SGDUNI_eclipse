package com.sgduni.dao;

import com.sgduni.forms.orpro_ta_proc_tupa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * @author marco
 */
public class orpro_ta_proc_tupa_DAO
{
    private DataSource ds;
    private Connection cn;

    public orpro_ta_proc_tupa_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean guardarTupaSubDet(orpro_ta_proc_tupa obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_PROC_TUPA_INSERT(?,?,?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_DE_TRAMITACION_PORC", obj.getDe_tramitacion_porc()     );
            cstm.setInt("P_DE_TRAMITACION_SOL",  obj.getDe_tramitacion_sol()    );
            cstm.setString("P_CH_CALIF_AUTOMA", obj.getCh_calif_automa().toString().trim());
            cstm.setString("P_CH_EVALUACION_PREVIA",obj.getCh_evaluacion_previa().toString().trim() );
            cstm.setInt("P_IN_PLAZO_RESOLVER_DIAS", obj.getIn_plazo_resolver_dias());
            cstm.setString("P_VC_INICIO_PROCEDIMIENTO", obj.getVc_inicio_procedimiento().toString().trim().toUpperCase());
            cstm.setString("P_VC_RECONSIDERACION",obj.getVc_reconsideracion().toString().trim().toUpperCase() );
            cstm.setString("P_VC_APELACION",obj.getVc_apelacion().toString().trim().toUpperCase() );
            cstm.setInt("P_IN_AUTORIDAD_COM_RESOLVER",obj.getIn_autoridad_com_resolver() );
            cstm.setInt("P_IN_CODIGO_DET", obj.getIn_codigo_det());
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsuario ) );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );

            cstm.executeUpdate();
            cn.commit();
            estado = true;
            cstm.close();

            System.out.println(" --SUBDETALLE GUARDADO-- ");
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

    public ArrayList<orpro_ta_proc_tupa> getSubDetallesTupaSegunDetalle(String in_codigo_det)
    {
        System.out.println("llego al metodo dao");
        ArrayList<orpro_ta_proc_tupa> lista = null;
        try
        {
            lista = new ArrayList<orpro_ta_proc_tupa>();
            cn = ds.getConnection();

            String sql = "select pt.de_tramitacion_porc as DE_TRAMITACION_PORC, pt.de_tramitacion_sol as DE_TRAMITACION_SOL, pt.ch_calif_automa as CH_CALIF_AUTOMA, pt.ch_evaluacion_previa as CH_EVALUACION_PREVIA, pt.in_plazo_resolver_dias as IN_PLAZO_RESOLVER_DIAS, ";
                   sql += " pt.vc_reconsideracion as VC_RECONSIDERACION, pt.vc_inicio_procedimiento as VC_INICIO_PROCEDIMIENTO, pt.vc_apelacion as VC_APELACION,ce.vc_nombre as NOMAUTORIDAD ";
                   sql += "from orpro_ta_proc_tupa pt ";
                   sql += "inner join orgen_ta_cargo_estructural ce on pt.in_autoridad_com_resolver = ce.in_codigo_cargo_estruc ";
                   sql += "where pt.in_codigo_det = "+in_codigo_det.toString().trim();

                   System.out.println("SQL = "+sql);
                   
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();

            while( rs.next() )
            {
               orpro_ta_proc_tupa obj = new orpro_ta_proc_tupa();

               obj.setDe_tramitacion_porc( rs.getInt("DE_TRAMITACION_PORC") );
               obj.setDe_tramitacion_sol( rs.getInt("DE_TRAMITACION_SOL") );
               obj.setCh_calif_automa( rs.getString("CH_CALIF_AUTOMA") );
               obj.setCh_evaluacion_previa(rs.getString("CH_EVALUACION_PREVIA"));
               obj.setIn_plazo_resolver_dias(rs.getInt("IN_PLAZO_RESOLVER_DIAS"));              
               obj.setVc_reconsideracion(rs.getString("VC_RECONSIDERACION"));
               obj.setVc_inicio_procedimiento(rs.getString("VC_INICIO_PROCEDIMIENTO"));
               obj.setVc_apelacion(rs.getString("VC_APELACION"));
               obj.setNombre_autoridad(rs.getString("NOMAUTORIDAD"));
               
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
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("Failed to finalize JDBC task: "+ex);}
       }
        return lista;
    }


}
