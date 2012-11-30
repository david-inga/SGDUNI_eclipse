package com.sgduni.dao;

import com.sgduni.forms.orpro_det_cap;
import com.sgduni.forms.orpro_ta_listar_detalle_cap;
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
public class orpro_det_cap_DAO
{
    private DataSource ds;
    private Connection cn;

    public orpro_det_cap_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean guardarDetalleCAP(orpro_det_cap obj,String nomUsuario,String idUsuario)
    {
        System.out.println("metodo guardar del detalle cap");
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_DET_CAP_INSERT(?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_CAP", obj.getIn_codigo_cap());
            cstm.setInt("P_IN_CODIGO_CARGO_ESTRUC", obj.getIn_codigo_cargo_estruc()    );
            cstm.setInt("P_IN_TOTAL", obj.getIn_total());
            cstm.setInt("P_IN_SITUA_CARGO_PREV", obj.getIn_situa_cargo_prev() );
            cstm.setInt("P_IN_SITUA_CARGO_OCUPADO", obj.getIn_situa_cargo_ocupado() );
            cstm.setString("P_CH_CARGO_CONFIANZA", obj.getCh_cargo_confianza().toUpperCase() );
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion().toUpperCase() );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsuario )  );
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

    public ArrayList<orpro_det_cap> getDetalleCap(String codCap)
    {
        ArrayList<orpro_det_cap> lista = null;
        try
        {
            lista = new ArrayList<orpro_det_cap>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_CARGO_ESTRUC,IN_TOTAL,IN_SITUA_CARGO_PREV,IN_SITUA_CARGO_OCUPADO,CH_CARGO_CONFIANZA,VC_OBSERVACION FROM ORPRO_DET_CAP WHERE CH_CODIGO_CAP = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, codCap);
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

    public ArrayList<orpro_ta_listar_detalle_cap > getDetallesSegunCap(int idCap)
    {
        System.out.println("estamos en el metodo dao y el valor que ingresa es: "+idCap);
        ArrayList<orpro_ta_listar_detalle_cap> lista = null;
        try
        {
            lista = new ArrayList<orpro_ta_listar_detalle_cap>();
            cn = ds.getConnection();
            String sql = "select cc.vc_nombre as c_clasi, ";
            sql += " clas.vc_nombre as codigo,ce.vc_nombre as c_est, ";
            sql += " clas.vc_descripcion as clasificacion,det.in_total, ";
            sql += " det.in_situa_cargo_ocupado as ocupado,det.in_situa_cargo_prev as previsto, ";
            sql += " det.ch_cargo_confianza as c_confianza ";
            sql += " from ORPRO_TA_CAP  CAP ";
            sql += " INNER JOIN ORPRO_DET_CAP det ON cap.in_codigo_cap = det.in_codigo_cap ";
            sql += " INNER JOIN orgen_ta_cargo_estructural CE ON det.in_codigo_cargo_estruc = ce.in_codigo_cargo_estruc ";
            sql += " inner join orgen_ta_cargo_clasificado cc on ce.in_codigo_cargo_clasif = cc.in_codigo_cargo_clasif ";
            sql += " inner join orgen_ta_clasi_cargo clas on ce.in_codigo_clasi = clas.in_codigo_clasi ";
            sql += " WHERE cap.in_codigo_cap = "+idCap+" ";
            sql += " ORDER BY ce.vc_nombre ASC ";
               System.out.println("LISTA SQL "+sql);
            PreparedStatement pstm = cn.prepareStatement(sql);
            //pstm.setInt(1, Integer.parseInt(idCap) );
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_listar_detalle_cap obj = new orpro_ta_listar_detalle_cap();

               obj.setC_clasi(rs.getString("c_clasi"));
               obj.setCodigo(rs.getString("codigo"));
               obj.setC_est(rs.getString("c_est"));
               obj.setClasificacion(rs.getString("clasificacion"));
               obj.setIn_total(rs.getInt("in_total"));
               obj.setOcupado(rs.getInt("ocupado"));
               obj.setPrevisto(rs.getInt("previsto"));
               obj.setC_confianza(rs.getString("c_confianza"));
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

}
