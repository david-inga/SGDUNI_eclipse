package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

/*
 * @author marco
 */
public class orgen_ta_estado_DAO
{
    private DataSource ds;
    private Connection cn;

    public orgen_ta_estado_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    public ArrayList<orgen_ta_estado> getEstados()
    {
        ArrayList<orgen_ta_estado> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_estado>();
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_ESTADO,VC_NOMBRE_ESTADO FROM ORGEN_TA_ESTADO WHERE CH_ESTADO = '01' ORDER BY VC_NOMBRE_ESTADO ASC";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orgen_ta_estado obj = new orgen_ta_estado();

               obj.setIn_codigo_estado(rs.getInt("IN_CODIGO_ESTADO"));
               obj.setVc_nombre_estado(rs.getString("VC_NOMBRE_ESTADO"));
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
