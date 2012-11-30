package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orgen_ta_usuario_lista;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import javax.sql.DataSource;


/*
 ADAPTADO PARA MYSQL
 */

/**
 * @programador marco estrella cardenas
 */
public class orgen_ta_usuario_DAO
{
     protected DataSource ds;
     Connection cn;
     private int in_codigo_usuario;
     private String vc_usuario;
     private String vc_nombre_usuario;
     private String in_codigo_rol;
     private String vc_nombre_rol;
     private String vc_nom_depen_facul;
     private String vc_tipo_depen_facul;
     private int in_codigo_depen_facul;
     private int in_codigo_cargo;

    public orgen_ta_usuario_DAO(DataSource ds)
    {
        this.ds = ds;
    }

    //obtiene el id, nombre de los usuario activos
    public ArrayList<orgen_ta_usuario> getNombresUsuario()
    {
        ArrayList<orgen_ta_usuario> listaUsuario = null;
        try
        {
            listaUsuario = new ArrayList<orgen_ta_usuario>();
            cn = ds.getConnection();
            String query = "SELECT IN_CODIGO_USUARIO,VC_USUARIO FROM ORGEN_TA_USUARIO WHERE CH_ESTADO = '01' ORDER BY VC_USUARIO ASC";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                orgen_ta_usuario dataForm = new orgen_ta_usuario();
                dataForm.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
                dataForm.setVc_usuario(rs.getString("VC_USUARIO"));
                listaUsuario.add(dataForm);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO -getNombresUsuario() : "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO -getNombresUsuario(): "+ex);}
       }
       return listaUsuario;
    }

    public boolean logear(String usuario,String clave)
    {
        boolean estado=false;
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT U.IN_CODIGO_USUARIO,U.VC_CORREO,U.VC_USUARIO, U.VC_USUARIO,U.VC_NOMBRES,U.VC_APELLIDO_MATERNO," +
                         "  U.VC_APELLIDO_PATERNO,R.CH_CODIGO_ROL,R.VC_NOMBRE AS NOMBRE_ROL," +
                         "       UDF.IN_CODIGO_DEPEN_FACU," +
                         "   UDF.CH_TIPO_DEPEN_FACU, "+
                         "   CASE TRIM(UDF.CH_TIPO_DEPEN_FACU)                      " +
                         "       WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD=UDF.IN_CODIGO_DEPEN_FACU)" +
                         "      WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=UDF.IN_CODIGO_DEPEN_FACU)" +
                         "   ELSE '-'   " +
                         "   END AS FACULTAD_DEPENDECIA, " +
                         "  CU.IN_CODIGO_CARGO CODCARGO "+
                         "   FROM ORGEN_TA_USUARIO U  " +
                         "   INNER JOIN ORGEN_TA_USUARIO_ROL_FUN RU ON  RU.IN_CODIGO_USUARIO=U.IN_CODIGO_USUARIO  " +
                         "   INNER JOIN ORGEN_TA_ROL R ON R.CH_CODIGO_ROL=RU.CH_CODIGO_ROL AND R.CH_ESTADO='01'  " +
                         "   INNER JOIN ORGEN_TA_USUARIO_DEPEN_FACU UDF ON  UDF.IN_CODIGO_USUARIO=U.IN_CODIGO_USUARIO" +
                         " inner join orgen_ta_usuario_cargo cu on u.in_codigo_usuario = cu.in_codigo_usuario  "+
                         "  WHERE U.VC_USUARIO=? AND U.VC_CLAVE=? AND U.CH_ESTADO='01' AND @rownum := 1";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, clave);
            ResultSet rs =  pstm.executeQuery();           
            while(rs.next())
            {                
                this.in_codigo_usuario = rs.getInt("IN_CODIGO_USUARIO");
                this.vc_usuario = rs.getString("VC_USUARIO");
                this.vc_nombre_usuario = rs.getString("VC_NOMBRES") +" " +rs.getString("VC_APELLIDO_PATERNO");// +" " + rs.getString("VC_APELLIDO_MATERNO")
                this.in_codigo_rol = rs.getString("CH_CODIGO_ROL");
                this.vc_nombre_rol = rs.getString("NOMBRE_ROL");
                this.vc_tipo_depen_facul = rs.getString("CH_TIPO_DEPEN_FACU");
                this.in_codigo_depen_facul = rs.getInt("IN_CODIGO_DEPEN_FACU");
                this.vc_nom_depen_facul = rs.getString("FACULTAD_DEPENDECIA");
                this.in_codigo_cargo = rs.getInt("CODCARGO");

                estado = true;
            }
            
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO -logear(,) : "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO -logear(,) :  "+ex);}
            return estado;
       }    
    }
   
    public boolean usuarioExiste(String usuario)
    {
        //System.out.println("Comprobar si el usuario existe ");
        boolean estado=false;
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT VC_USUARIO FROM " +
                         "ORGEN_TA_USUARIO  " +
                         "WHERE VC_USUARIO=?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, usuario);            
            ResultSet rs =  pstm.executeQuery();
            estado = rs.next();
            //System.out.println(rs.first());
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - usuarioExiste() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO -usuarioExiste() "+ex);}
            return estado;
       }
    }

    public boolean validarSiCorreoExiste(String correo)
    {
        //System.out.println("Comprobar si el usuario existe ");
        boolean estado=false;
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT VC_CORREO FROM ORGEN_TA_USUARIO WHERE VC_CORREO = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, correo);
            ResultSet rs =  pstm.executeQuery();
            estado = rs.next();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - validarSiCorreoExiste() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO - validarSiCorreoExiste() "+ex);}
            return estado;
       }
    }

    public boolean modificarUsuario(orgen_ta_usuario dataForm,String usu,String id)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);

	     	CallableStatement cstm = cn.prepareCall("{ CALL SP_ORGEN_TA_USUARIO_MODIFICAR(?,?,?,?,?,?,?,?,?,?) }");
            cstm.setInt(   "P_IN_CODIGO_USUARIO",    dataForm.getIn_codigo_usuario());
            cstm.setString("P_VC_USUARIO",           dataForm.getVc_usuario().toUpperCase().trim() );
            cstm.setString("P_VC_CLAVE",             dataForm.getVc_clave().toUpperCase().trim() );
            cstm.setString("P_VC_CORREO",            dataForm.getVc_correo().trim() );
            cstm.setString("P_VC_NOMBRES",           dataForm.getVc_nombres());
            cstm.setString("P_VC_APELLIDO_PATERNO",  dataForm.getVc_apellido_paterno() );
            cstm.setString("P_VC_APELLIDO_MATERNO",  dataForm.getVc_apellido_materno());
            cstm.setString("P_VC_USUARIO_MODIFICA",  usu.toString().trim() );
            cstm.setInt("P_IN_CODIGO_USU_LOG",       Integer.parseInt(id) );
            cstm.setString("P_VC_GRADO_ACADEMICO", dataForm.getVc_grado_academico());

            cstm.execute();
	     	cn.commit();
            estado = true;
	     	cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - modificarUsuario() "+e);
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
                System.out.println("error en la clase orgen_ta_usuario_DAO - modificarUsuario() "+ex);
            }
            return estado;
       }
    }

    public boolean modificarFirmaUsuario(orgen_ta_usuario dataForm,String usu,String id,String nombreArchivo)
    {
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);

	     	CallableStatement cstm = cn.prepareCall("{ CALL SP_ORGEN_TA_USUARIO_MOD_FIRMA(?,?,?,?) }");
            cstm.setInt(   "P_IN_CODIGO_USUARIO",    dataForm.getIn_codigo_usuario());
            cstm.setString("P_VC_USUARIO_MODIFICA",  usu.toString().trim() );
            cstm.setInt("P_IN_CODIGO_USU_LOG",       Integer.parseInt(id) );
            cstm.setString("P_VC_RUTA_FIRMA", nombreArchivo);

            cstm.execute();
	     	cn.commit();
            estado = true;
	     	cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - modificarUsuario() "+e);
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
                System.out.println("error en la clase orgen_ta_usuario_DAO - modificarUsuario() "+ex);
            }
            return estado;
       }
    }

    public boolean guardarUsuario(orgen_ta_usuario dataForm,String xusu,String xid,String nombreArchivo)
    {
        
        boolean estado = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);

	     	CallableStatement cstm = cn.prepareCall("{ CALL SP_ORGEN_TA_USUARIO_INSERT(?,?,?,?,?,?,?,?,?,?) }");
            cstm.setString("P_VC_USUARIO", dataForm.getVc_usuario().toUpperCase().trim() );
            cstm.setString("P_VC_CLAVE",  dataForm.getVc_clave().toUpperCase().trim());
            cstm.setString("P_VC_CORREO", dataForm.getVc_correo().trim() );
            cstm.setString("P_VC_NOMBRES",  dataForm.getVc_nombres());
            cstm.setString("P_VC_APELLIDO_PATERNO", dataForm.getVc_apellido_paterno() );
            cstm.setString("P_VC_APELLIDO_MATERNO",  dataForm.getVc_apellido_materno());
            cstm.setString("P_VC_USUARIO_CREA", xusu );//dataForm.getVc_usuario_crea().toUpperCase());P_IN_CODIGO_USUARIO
	     	cstm.setInt("P_IN_CODIGO_USUARIO",Integer.parseInt(xid) );
            cstm.setString("P_VC_RUTA_FIRMA", nombreArchivo);
            cstm.setString("P_VC_GRADO_ACADEMICO", dataForm.getVc_grado_academico());

            cstm.execute();
	     	cn.commit();
            estado = true;
	     	cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - guardarUsuario()  "+e);
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
                System.out.println("finally - error en la clase orgen_ta_usuario_DAO - guardarUsuario()  "+ex);
            }
            return estado;
       }
    }
   
    public orgen_ta_usuario getUsuarioSegunNombre(String vc_usuario)
    {
        orgen_ta_usuario obj = new orgen_ta_usuario();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_USUARIO,VC_USUARIO,VC_CLAVE,VC_CORREO,VC_NOMBRES,VC_APELLIDO_PATERNO,VC_APELLIDO_MATERNO,CH_ESTADO,VC_USUARIO_CREA, DT_FECHA_CREA, VC_USUARIO_MODIFICA,DT_FECHA_MODIFICA FROM  ORGEN_TA_USUARIO  WHERE VC_USUARIO = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, vc_usuario);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
                obj.setVc_usuario(rs.getString("VC_USUARIO"));
                obj.setVc_clave(rs.getString("VC_CLAVE"));
                obj.setVc_correo(rs.getString("VC_CORREO"));
                obj.setVc_nombres(rs.getString("VC_NOMBRES"));
                obj.setVc_apellido_paterno(rs.getString("VC_APELLIDO_PATERNO"));
                obj.setVc_apellido_materno(rs.getString("VC_APELLIDO_MATERNO"));
                obj.setVc_estado(rs.getString("CH_ESTADO"));
                obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
                obj.setDt_fecha_crea( String.valueOf( rs.getDate("DT_FECHA_CREA") ));
                obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
                obj.setVc_fecha_modifica( String.valueOf( rs.getDate("DT_FECHA_MODIFICA") ));
            }
            // System.out.println("NOMBRE USUARIO: "+obj.getVc_usuario());
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuarioSegunNombre()  "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuarioSegunNombre() "+ex);}
       }
        return obj;
    }

    public orgen_ta_usuario getUsuario2(int in_codigo_usuario)
    {
        orgen_ta_usuario obj = new orgen_ta_usuario();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_USUARIO,VC_USUARIO,VC_CLAVE,VC_CORREO,VC_NOMBRES,VC_APELLIDO_PATERNO,VC_APELLIDO_MATERNO,CH_ESTADO,VC_USUARIO_CREA, DT_FECHA_CREA, VC_USUARIO_MODIFICA,DT_FECHA_MODIFICA,VC_RUTA_FIRMA,VC_GRADO_ACADEMICO " +
                    "FROM  ORGEN_TA_USUARIO  " +
                    "WHERE IN_CODIGO_USUARIO = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, in_codigo_usuario);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
                obj.setVc_usuario(rs.getString("VC_USUARIO"));
                obj.setVc_clave(rs.getString("VC_CLAVE"));
                obj.setVc_correo(rs.getString("VC_CORREO"));
                obj.setVc_nombres(rs.getString("VC_NOMBRES"));
                obj.setVc_apellido_paterno(rs.getString("VC_APELLIDO_PATERNO"));
                obj.setVc_apellido_materno(rs.getString("VC_APELLIDO_MATERNO"));
                obj.setVc_estado(rs.getString("CH_ESTADO"));
                obj.setVc_usuario_crea(rs.getString("VC_USUARIO_CREA"));
                obj.setDt_fecha_crea( rs.getDate("DT_FECHA_CREA").toString() );
                obj.setVc_usuario_modifica(rs.getString("VC_USUARIO_MODIFICA"));
                obj.setVc_fecha_modifica( String.valueOf( rs.getDate("DT_FECHA_MODIFICA") ));
                obj.setVc_nombre_archivo(rs.getString("VC_RUTA_FIRMA"));
                obj.setVc_grado_academico(rs.getString("VC_GRADO_ACADEMICO"));
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuario2() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuario2() "+ex);}
       }
       return obj;
    }

    public orgen_ta_usuario getUsuarioParaExportarOficio(int in_codigo_usuario)
    {
        orgen_ta_usuario obj = new orgen_ta_usuario();
        try
        {
            cn = ds.getConnection();
            String sql =    "SELECT "+
                            "CONCAT(u.VC_NOMBRES,' ',u.VC_APELLIDO_PATERNO,' ',u.VC_APELLIDO_MATERNO) AS NOMBRECOMPLETO, "+
                            "u.VC_RUTA_FIRMA, "+
                            "cu.VC_NOMBRE AS NOMCARGO, "+
                            "u.VC_GRADO_ACADEMICO "+
                            "FROM orgen_ta_usuario_cargo uc "+
                            "INNER JOIN orgen_ta_usuario u "+
                            "on u.in_codigo_usuario      = uc.in_codigo_usuario "+
                            "INNER JOIN orgen_ta_cargo_usuario cu "+
                            "on cu.IN_CODIGO_CARGO_ESTRUC = uc.in_codigo_cargo "+
                            "WHERE uc.in_codigo_usuario = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, in_codigo_usuario);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setVc_nombres(rs.getString("NOMBRECOMPLETO"));
                obj.setVc_nombre_archivo(rs.getString("VC_RUTA_FIRMA"));
                obj.setVc_grado_academico(rs.getString("VC_GRADO_ACADEMICO"));
                obj.setVc_cargo(rs.getString("NOMCARGO"));
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuario2() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuario2() "+ex);}
       }
       return obj;
    }

    public orgen_ta_usuario getUsuarioUsuarioJefeOCDO()
    {
        orgen_ta_usuario obj = new orgen_ta_usuario();
        try
        {
            cn = ds.getConnection();
            String sql =    "SELECT "+
                            "CONCAT(u.VC_NOMBRES,' ',u.VC_APELLIDO_PATERNO,' ',u.VC_APELLIDO_MATERNO) AS NOMBRECOMPLETO, "+
                            "cu.VC_NOMBRE AS NOMCARGO, "+
                            "u.VC_GRADO_ACADEMICO  "+
                            "FROM orgen_ta_usuario_cargo uc "+ 
                            "INNER JOIN orgen_ta_usuario u "+
                            "on u.in_codigo_usuario      = uc.in_codigo_usuario "+
                            "INNER JOIN orgen_ta_cargo_usuario cu "+
                            "on cu.IN_CODIGO_CARGO_ESTRUC = uc.in_codigo_cargo "+
                            "INNER JOIN orgen_ta_usuario_rol_fun urf "+
                            "on urf.IN_CODIGO_USUARIO = u.IN_CODIGO_USUARIO "+
                            "WHERE urf.CH_CODIGO_ROL = 'ROL01' and cu.IN_CODIGO_CARGO_ESTRUC = 1 "+
                            "and u.CH_ESTADO = '01' ";
            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setVc_nombres(rs.getString("NOMBRECOMPLETO"));
                obj.setVc_grado_academico(rs.getString("VC_GRADO_ACADEMICO"));
                obj.setVc_cargo(rs.getString("NOMCARGO"));
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuarioUsuarioJefeOCDO() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuarioUsuarioJefeOCDO() "+ex);}
       }
       return obj;
    }

    public orgen_ta_usuario getUsuario(int in_codigo_usuario)
    {
        //System.out.println("metodo getUsuario ");
        orgen_ta_usuario obj = new orgen_ta_usuario();
        try
        {
            cn = ds.getConnection();
            CallableStatement cstm = cn.prepareCall("{ CALL SP_ORGEN_TA_USUARIO_SELECT(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            cstm.setInt("P_IN_CODIGO_USUARIO_I", in_codigo_usuario);
            cstm.registerOutParameter("P_IN_CODIGO_USUARIO",  Types.INTEGER );
            cstm.registerOutParameter("P_VC_USUARIO",         Types.VARCHAR );
            cstm.registerOutParameter("P_VC_CLAVE",           Types.VARCHAR );
            cstm.registerOutParameter("P_VC_CORREO",          Types.VARCHAR );
            cstm.registerOutParameter("P_VC_CARGO",           Types.VARCHAR );
            cstm.registerOutParameter("P_VC_NOMBRES",         Types.VARCHAR );
            cstm.registerOutParameter("P_VC_APELLIDO_PATERNO",Types.VARCHAR );
            cstm.registerOutParameter("P_VC_APELLIDO_MATERNO",Types.VARCHAR );
            cstm.registerOutParameter("P_CH_ESTADO",          Types.CHAR );
            cstm.registerOutParameter("P_VC_USUARIO_CREA",    Types.VARCHAR );
            cstm.registerOutParameter("P_DT_FECHA_CREA",      Types.DATE );
            cstm.registerOutParameter("P_VC_USUARIO_MODIFICA",Types.VARCHAR );
            cstm.registerOutParameter("P_DT_FECHA_MODIFICA",  Types.DATE );

	     	cstm.execute();
	     	cn.commit();

            obj.setIn_codigo_usuario(cstm.getInt("P_IN_CODIGO_USUARIO"));
            obj.setVc_usuario( cstm.getString("P_VC_USUARIO").trim());
            obj.setVc_clave(cstm.getString("P_VC_CLAVE").trim());
            obj.setVc_correo( cstm.getString("P_VC_CORREO").trim() );
            obj.setVc_cargo( cstm.getString("P_VC_CARGO").trim() );
            obj.setVc_nombres( cstm.getString("P_VC_NOMBRES").trim() );
            obj.setVc_apellido_paterno( cstm.getString("P_VC_APELLIDO_PATERNO").trim() );
            obj.setVc_apellido_materno( cstm.getString("P_VC_APELLIDO_MATERNO").trim() );
            obj.setVc_estado( cstm.getString("P_CH_ESTADO").trim() );
            obj.setVc_usuario_crea( cstm.getString("P_VC_USUARIO_CREA").trim() );
            obj.setDt_fecha_crea(String.valueOf(cstm.getDate("P_DT_FECHA_CREA")).trim());
            obj.setVc_usuario_modifica( cstm.getString("P_VC_USUARIO_MODIFICA").trim() );
            obj.setVc_fecha_modifica(String.valueOf(cstm.getDate("P_DT_FECHA_MODIFICA")).trim());
            cstm.close();
            System.out.println("obj - apellido: "+cstm.getString("P_VC_APELLIDO_PATERNO"));

        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuario(): "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuario() "+ex);}
       }
        return obj;
    }

    public boolean eliminarUsuario(orgen_ta_usuario obj )
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORGEN_TA_USUARIO_ELIMINAR(?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_USUARIO", obj.getIn_codigo_usuario());
            cstm.setString("P_CH_ESTADO", obj.getVc_estado());
            cstm.executeUpdate();
            cn.commit();
             exito = true;
            cstm.close();          
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("error en la clase orgen_ta_usuario_DAO - eliminarUsuario() "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("error en la clase orgen_ta_usuario_DAO - eliminarUsuario() "+ex);
            }
            return exito;
       }  
    }

    public ArrayList<orgen_ta_usuario_lista> getUsuarios()
    {
        ArrayList<orgen_ta_usuario_lista> listaUsuario = null;
        try
        {
            listaUsuario = new ArrayList<orgen_ta_usuario_lista>();
            cn = ds.getConnection();
            String query = "SELECT IN_CODIGO_USUARIO,VC_USUARIO,VC_CLAVE,VC_CORREO,VC_NOMBRES,VC_APELLIDO_PATERNO,VC_APELLIDO_MATERNO,CH_ESTADO " +
                    "FROM ORGEN_TA_USUARIO " +
                    "ORDER BY VC_USUARIO ASC";
            //WHERE  ROWNUM>=1 and ROWNUM<=3 
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                orgen_ta_usuario_lista dataForm = new orgen_ta_usuario_lista();
                dataForm.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
                dataForm.setVc_usuario(rs.getString("VC_USUARIO"));
                dataForm.setVc_clave(rs.getString("VC_CLAVE"));
                dataForm.setVc_correo(rs.getString("VC_CORREO"));
                dataForm.setVc_nombres(rs.getString("VC_NOMBRES"));
                dataForm.setVc_apellido_paterno(rs.getString("VC_APELLIDO_PATERNO"));
                dataForm.setVc_apellido_materno(rs.getString("VC_APELLIDO_MATERNO"));
                dataForm.setVc_estado(rs.getString("CH_ESTADO"));
                listaUsuario.add(dataForm);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuarios() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuarios()  "+ex);}
       }
       return listaUsuario;
    }
    ///
    public ArrayList<orgen_ta_usuario_lista> getUsuariosActivos()
    {
        ArrayList<orgen_ta_usuario_lista> listaUsuario = null;
        try
        {
            listaUsuario = new ArrayList<orgen_ta_usuario_lista>();
            cn = ds.getConnection();
            String query = "SELECT IN_CODIGO_USUARIO,VC_USUARIO,VC_NOMBRES,VC_APELLIDO_PATERNO," +
                    " VC_APELLIDO_MATERNO FROM ORGEN_TA_USUARIO " +
                    " WHERE CH_ESTADO='01' ORDER BY VC_USUARIO ASC";
            //WHERE  ROWNUM>=1 and ROWNUM<=3
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                orgen_ta_usuario_lista dataForm = new orgen_ta_usuario_lista();
                dataForm.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));                
                dataForm.setVc_nombres(rs.getString("VC_NOMBRES"));
                dataForm.setVc_usuario(rs.getString("VC_USUARIO"));
                dataForm.setVc_apellido_paterno(rs.getString("VC_APELLIDO_PATERNO"));
                dataForm.setVc_apellido_materno(rs.getString("VC_APELLIDO_MATERNO"));                
                listaUsuario.add(dataForm);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuariosActivos() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("error en la clase orgen_ta_usuario_DAO - getUsuariosActivos() "+ex);}
       }
       return listaUsuario;
    }

    public ArrayList<orgen_ta_usuario> getListaUsuarioOCDO(int idCodFacDep,String tipoFacDep)
    {
        ArrayList<orgen_ta_usuario> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_usuario>();
            cn = ds.getConnection();
            String query = "SELECT U.IN_CODIGO_USUARIO,CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_PATERNO) AS NOMBRE_USUARIO  " +
                    "FROM orgen_ta_usuario_depen_facu UDF " +
                    "INNER JOIN ORGEN_TA_USUARIO U ON UDF.IN_CODIGO_USUARIO = U.IN_CODIGO_USUARIO " +
                    "WHERE UDF.IN_CODIGO_DEPEN_FACU = ? AND UDF.CH_TIPO_DEPEN_FACU=? ";
            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1,idCodFacDep );
            pstm.setString(2, tipoFacDep);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orgen_ta_usuario usuario = new orgen_ta_usuario();

                usuario.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO") );
                usuario.setVc_usuario(rs.getString("NOMBRE_USUARIO") );

                lista.add(usuario);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getListaUsuarioOCDO() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("finally - error en la clase orgen_ta_usuario_DAO - getListaUsuarioOCDO() "+ex);}
       }
       return lista;
    }

    //para ver detalle
    public String getCargoSegunIDUsuario(int idUsuario)
    {
        String cargo = null;
        try
        {
            cn = ds.getConnection();
            String query =  "SELECT CU.VC_NOMBRE FROM ORGEN_TA_USUARIO_CARGO UC "+
                            "INNER JOIN ORGEN_TA_CARGO_USUARIO CU ON  UC.IN_CODIGO_CARGO = CU.IN_CODIGO_CARGO_ESTRUC "+
                            "WHERE UC.IN_CODIGO_USUARIO = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, idUsuario);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                cargo = rs.getString("VC_NOMBRE");

            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getListaUsuarioOCDO() "+e);

        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("finally - error en la clase orgen_ta_usuario_DAO - getListaUsuarioOCDO() "+ex);}
       }
       return cargo;
    }

    //para ver detalle
    public String getROLSegunIDUsuario(int idUsuario)
    {
        String rol = null;
        try
        {
            cn = ds.getConnection();
            String query =  "select r.vc_nombre from orgen_ta_usuario_rol_fun ru"+
                            " inner join orgen_ta_rol r on ru.ch_codigo_rol = r.ch_codigo_rol"+
                            " where in_codigo_usuario = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, idUsuario);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                rol = rs.getString("VC_NOMBRE");

            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getListaUsuarioOCDO() "+e);

        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("finally - error en la clase orgen_ta_usuario_DAO - getListaUsuarioOCDO() "+ex);}
       }
       return rol;
    }

    //para ver detalle
    public String getDEPENDENCIASegunIDUsuario(int idUsuario)
    {
        String dependencia = null;
        try
        {
            cn = ds.getConnection();
            String query =  "select "+
                            "CASE ch_tipo_depen_facu WHEN null THEN 'NO DEFINIDO' "+
                            "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD = in_codigo_depen_facu) "+
                            "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA=in_codigo_depen_facu) "+
                            "ELSE 'NO DEFINIDO' "+
                            "END AS FACULTAD_DEPENDENCIA "+
                            "from orgen_ta_usuario_depen_facu "+
                            "where in_codigo_usuario = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, idUsuario);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                dependencia = rs.getString("FACULTAD_DEPENDENCIA");

            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getListaUsuarioOCDO() "+e);

        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("finally - error en la clase orgen_ta_usuario_DAO - getListaUsuarioOCDO() "+ex);}
       }
       return dependencia;
    }

    //para ver detalle
    public String getCorreoElectronicoSegunID(int idUsuario)
    {
        String correo = null;
        try
        {
            cn = ds.getConnection();
            String query =  "SELECT vc_correo FROM orgen_ta_usuario "+
                            "WHERE in_codigo_usuario = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, idUsuario);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                correo = rs.getString("vc_correo");

            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase getCorreoElectronicoSegunID() "+e);

        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("finally - error en la clase getCorreoElectronicoSegunID() "+ex);}
       }
       return correo;
    }

    //para ver detalle
    public String getCorreoElectronicoUsuarioSegunIDOFICIO(int idOficio,String tipoUsuario)
    {
        String correo = null;
        try
        {
            String query = "";
            if(tipoUsuario.trim().equals("creador"))
            {
               query = "SELECT U.VC_CORREO FROM ORPRO_OFICIO_CIRCULAR OC "+
                       "INNER JOIN ORGEN_TA_USUARIO U ON OC.IN_CODIGO_USUARIO = U.IN_CODIGO_USUARIO "+
                       "WHERE IN_CODIGO_OFICIO = ?";
            }
            else if( tipoUsuario.trim().equals("emisor") )
            {
               query = "SELECT U.VC_CORREO FROM ORPRO_OFICIO_CIRCULAR OC "+
                       "INNER JOIN ORGEN_TA_USUARIO U ON OC.IN_USUARIO_EMISOR = U.IN_CODIGO_USUARIO "+
                       "WHERE IN_CODIGO_OFICIO = ?";
            }
            else if(tipoUsuario.trim().equals("receptor"))
            {
               query = "";
            }
            
            cn = ds.getConnection();

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, idOficio);

            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                correo = rs.getString("vc_correo");

            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase getCorreoElectronicoSegunID() "+e);

        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("finally - error en la clase getCorreoElectronicoSegunID() "+ex);}
       }
       return correo;
    }

    public ArrayList<orgen_ta_usuario> getListaUsuariosSegunFacDepen(int in_codigo_depen_facu, String ch_tipo_depen_facu )
    {
        ArrayList<orgen_ta_usuario> lista = null;
        try
        {
            lista = new ArrayList<orgen_ta_usuario>();
            cn = ds.getConnection();
            String query = "select u.in_codigo_usuario,concat(u.vc_nombres,' ',u.vc_apellido_paterno) as nombre_usuario,ca.vc_nombre as cargo "+
                            " from orgen_ta_usuario u "+
                            " inner join orgen_ta_usuario_depen_facu ufd on u.in_codigo_usuario = ufd.in_codigo_usuario "+
                            " inner join orgen_ta_usuario_cargo uc on u.in_codigo_usuario = uc.in_codigo_usuario "+
                            " inner join orgen_ta_cargo_usuario ca on uc.in_codigo_cargo = ca.in_codigo_cargo_estruc "+
                            " where ufd.in_codigo_depen_facu = ? and ufd.ch_tipo_depen_facu = ? ";
            
            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, in_codigo_depen_facu);
            pstm.setString(2, ch_tipo_depen_facu);

            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orgen_ta_usuario usuario = new orgen_ta_usuario();

                usuario.setIn_codigo_usuario(rs.getInt("in_codigo_usuario") );
                usuario.setVc_usuario(rs.getString("nombre_usuario") );
                usuario.setVc_cargo(rs.getString("cargo"));

                lista.add(usuario);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error en la clase orgen_ta_usuario_DAO - getListaUsuariosSegunFacDepen() "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("finally - error en la clase orgen_ta_usuario_DAO - getListaUsuariosSegunFacDepen() "+ex);}
       }
       return lista;
    }

    public int getIn_codigo_depen_facul()
    {
        return in_codigo_depen_facul;
    }

    public void setIn_codigo_depen_facul(int in_codigo_depen_facul)
    {
        this.in_codigo_depen_facul = in_codigo_depen_facul;
    }

    public String getIn_codigo_rol() {
        return in_codigo_rol;
    }

    public void setIn_codigo_rol(String in_codigo_rol) {
        this.in_codigo_rol = in_codigo_rol;
    }

    public int getIn_codigo_usuario() {
        return in_codigo_usuario;
    }

    public void setIn_codigo_usuario(int in_codigo_usuario) {
        this.in_codigo_usuario = in_codigo_usuario;
    }

    public String getVc_nom_depen_facul() {
        return vc_nom_depen_facul;
    }

    public void setVc_nom_depen_facul(String vc_nom_depen_facul) {
        this.vc_nom_depen_facul = vc_nom_depen_facul;
    }

    public String getVc_nombre_rol() {
        return vc_nombre_rol;
    }

    public void setVc_nombre_rol(String vc_nombre_rol) {
        this.vc_nombre_rol = vc_nombre_rol;
    }

    public String getVc_nombre_usuario() {
        return vc_nombre_usuario;
    }

    public void setVc_nombre_usuario(String vc_nombre_usuario) {
        this.vc_nombre_usuario = vc_nombre_usuario;
    }

    public String getVc_tipo_depen_facul() {
        return vc_tipo_depen_facul;
    }

    public void setVc_tipo_depen_facul(String vc_tipo_depen_facul) {
        this.vc_tipo_depen_facul = vc_tipo_depen_facul;
    }

    public String getVc_usuario() {
        return vc_usuario;
    }

    public void setVc_usuario(String vc_usuario) {
        this.vc_usuario = vc_usuario;
    }

    public int getIn_codigo_cargo() {
        return in_codigo_cargo;
    }

    public void setIn_codigo_cargo(int in_codigo_cargo) {
        this.in_codigo_cargo = in_codigo_cargo;
    }

    //Entidades para los datos del usuario logueado
}
