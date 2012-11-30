package com.sgduni.dao;

import com.sgduni.forms.orpro_detalle_proc_req;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * @author marco
 */
public class orpro_detalle_proc_req_DAO
{
    private DataSource ds;
    private Connection cn;

    public orpro_detalle_proc_req_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public boolean guardarDetalleProcReq(orpro_detalle_proc_req obj,String nomUsuario,String idUsuario)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_DET_PROC_REQ_INSERT(?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_PROCEDIMIENTO", obj.getIn_codigo_procedimiento()     );
            cstm.setInt("P_IN_CODIGO_REQ", obj.getIn_codigo_req()     );
            cstm.setInt("P_IN_CODIGO_TUPA", obj.getIn_codigo_tupa()     );
            cstm.setInt("P_IN_CODIGO_USUARIO", Integer.parseInt( idUsuario ) );
            cstm.setString("P_VC_USUARIO_CREA", nomUsuario );
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


    public ArrayList<orpro_detalle_proc_req> getRequisitosSegunProcedimientoTUPA(String codTupa)
    {
        ArrayList<orpro_detalle_proc_req> lista = null;
        try
        {
            lista = new ArrayList<orpro_detalle_proc_req>();
            cn = ds.getConnection();
            String sql = "SELECT dt.in_codigo_det, p.vc_nombre AS NOMPRO,r.vc_nombre_req AS NOMREQ FROM ORPRO_DETALLE_PROC_REQ DT ";
            sql += "INNER JOIN orgen_ta_procedimiento P ON p.in_codigo_procedimiento = dt.in_codigo_procedimiento ";
            sql += "INNER JOIN orgen_ta_requisitos R ON r.in_codigo_req = dt.in_codigo_req ";
            sql += " WHERE dt.in_codigo_tupa = "+codTupa.trim().toString().trim();
            sql += " order by NOMPRO asc";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs =  pstm.executeQuery();

            while( rs.next() )
            {
               orpro_detalle_proc_req obj = new orpro_detalle_proc_req();


               obj.setIn_codigo_det(rs.getInt("in_codigo_det"));
               obj.setNombre_procedimiento( rs.getString("NOMPRO") );
               obj.setNombre_requisito( rs.getString("NOMREQ") );
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
