package com.sgduni.dao;

import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orpro_oficio_circular;
import com.sgduni.forms.orpro_ta_observacion_oficio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;

/**
 * @author JMarcos
 */
public class orpro_oficio_circular_DAO
{
    protected DataSource ds;
    Connection cn;
    
    public orpro_oficio_circular_DAO(DataSource ds)
    {
        this.ds = ds;
    }
    
    //Guardar si es necesario el oficio circular
    public boolean guardarOficio(orpro_oficio_circular obj,int idUser)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_OFICIO_GUARDAR(?,?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
             cstm.setString("P_CH_CODIGO_OFICIO",obj.getCh_codigo_oficio());
             cstm.setInt("P_IN_CODIGO_USUARIO",idUser);
             cstm.setString("P_DT_FECHA",obj.getDt_fecha());
             cstm.setString("P_VC_CIUDAD",obj.getVc_ciudad());
             cstm.setString("P_VC_NOMBRE_ANIO",obj.getVc_nombre_anio());
             cstm.setString("P_VC_CUERPO_DOC", obj.getVc_cuerpo_doc());
             cstm.setInt("P_IN_USUARIO_EMISOR",obj.getIn_usuario_emisor() );
             cstm.setInt("P_IN_CODIGO_ESTADO", obj.getIn_codigo_estado() );
             cstm.setInt("P_IN_COD_FAC_DEP", obj.getIn_cod_fac_dep() );
             cstm.setString("P_CH_TIPO_FAC_DEP", obj.getCh_tipo_fac_dep());
            cstm.executeUpdate();
            
            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al intentar guardar el OFICIO : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al intentar guardar el OFICIO: "+ex);
            }
       }
       return exito;
    }

    //es usado por la accion guardar
    public orpro_oficio_circular getCodigoDelOficio(String ch_codigo_oficio)
    {
        orpro_oficio_circular oficio = new orpro_oficio_circular();
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT OF.IN_CODIGO_OFICIO,OF.CH_CODIGO_OFICIO FROM ORPRO_OFICIO_CIRCULAR OF WHERE OF.CH_CODIGO_OFICIO = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setString(1,ch_codigo_oficio );

            ResultSet rs = pstm.executeQuery();
            rs.next();
            oficio.setIn_codigo_oficio( rs.getInt("IN_CODIGO_OFICIO") );
            oficio.setCh_codigo_oficio(rs.getString("CH_CODIGO_OFICIO"));
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
            return oficio;
       }
    }

    public orgen_ta_usuario getUsuarioEmisorDelOficio(int in_codigo_oficio)
    {
        orgen_ta_usuario usuario = new orgen_ta_usuario();
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT O.IN_USUARIO_EMISOR, "+
            "CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,'',U.VC_APELLIDO_MATERNO) AS NOMBRE_COMPLETO "+
            "FROM orpro_oficio_circular O "+
            "INNER JOIN ORGEN_TA_USUARIO  U ON O.IN_USUARIO_EMISOR = U.IN_CODIGO_USUARIO "+
            "WHERE IN_CODIGO_OFICIO = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1,in_codigo_oficio );

            ResultSet rs = pstm.executeQuery();
            while( rs.next()){
            usuario.setIn_codigo_usuario(rs.getInt("IN_USUARIO_EMISOR"));
            usuario.setVc_nombres( rs.getString("NOMBRE_COMPLETO") );
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al traer el ch_codigo_oficio : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al traer el ch_codigo_oficios : "+ex);
            }
            return usuario;
       }
    }

    public String getCh_Codigo_OficioSegunID(int in_codigo_oficio)
    {
        String ch_codigo_oficio = "";
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT OF.CH_CODIGO_OFICIO FROM ORPRO_OFICIO_CIRCULAR OF WHERE OF.IN_CODIGO_OFICIO = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1,in_codigo_oficio );

            ResultSet rs = pstm.executeQuery();
            rs.next();
            ch_codigo_oficio  = rs.getString("CH_CODIGO_OFICIO");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al traer el ch_codigo_oficio : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al traer el ch_codigo_oficios : "+ex);
            }
            return ch_codigo_oficio;
       }
    }

    //MODIFICAR OFICIO
    public boolean modificarOficio(orpro_oficio_circular obj,int idUser)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_OFICIO_MODIFICAR(?,?,?,?,?,?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_OFICIO",obj.getIn_codigo_oficio());
            cstm.setInt("P_IN_CODIGO_USUARIO",idUser);
            cstm.setString("P_CH_CODIGO_OFICIO",obj.getCh_codigo_oficio());
            cstm.setString("P_DT_FECHA",obj.getDt_fecha());
            cstm.setString("P_VC_CIUDAD", obj.getVc_ciudad());
            cstm.setString("P_VC_NOMBRE_ANIO", obj.getVc_nombre_anio());
            cstm.setString("P_VC_CUERPO_DOC", obj.getVc_cuerpo_doc() );
            cstm.setInt("P_IN_USUARIO_EMISOR", obj.getIn_usuario_emisor());
            cstm.setInt("P_IN_CODIGO_ESTADO", obj.getIn_codigo_estado());
            cstm.executeUpdate();
            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al modficar el oficio circular: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al modficar oficio circular: "+ex);
            }
       }
       return exito;
    }

    //Enviar si es necesario el oficio circular
    public boolean enviarOficio(orpro_oficio_circular objForm, int idUser, Integer[] listaFacultades, Integer[] listaDependencias, Integer[] listaReceptoresFac, Integer[] listaReceptoresDep) {

        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
           
                //INSERT INTO ORPRO_OFICIO_CIR_DEPEN_FACU VALUES(P_IN_CODIGO_OFICIO,L_DATA(i),'f');
                for (int i = 0; i < listaFacultades.length ; i++)
                {
                    //System.out.println("i = "+i+", valor "+listaFacultades[i]);

                    if(  listaFacultades[i] != 0 && listaReceptoresFac[i] != 0 )
                    {
                      String sql = "{ CALL ORPRO_OFICIO_ENVIAR(?,?,?,?,?) }";
                      CallableStatement cstmD = cn.prepareCall(sql);
                      cstmD.setInt("P_IN_CODIGO_OFICIO",objForm.getIn_codigo_oficio() );
                      cstmD.setInt("P_IN_CODIGO_DEPEN_FACU", listaFacultades[i]);
                      cstmD.setString("P_CH_TIPO_DEPEN_FACU", "f");
                      cstmD.setInt("P_IN_CODIGO_USUARIO",listaReceptoresFac[i] );
                      cstmD.setInt("P_IN_ESTADO",28 );//28 pendiente
                      cstmD.executeUpdate();
                      cn.commit();
                      cstmD.close();
                    }
                    else
                    {
                    System.out.println(" fac y receptor  Es NULL " );
                    }
                }


                //INSERT INTO ORPRO_OFICIO_CIR_DEPEN_FACU VALUES(P_IN_CODIGO_OFICIO,L_DATA(i),'d');
                for (int i = 0; i < listaDependencias.length; i++)
                {
                    
                    if( listaDependencias[i] != 0 && listaReceptoresDep[i] != 0)
                    {
                     //  System.out.println("DIFERENTES A 0 O NULL = "+listaDependencias[i]);
                     //  System.out.println(" recpetor dep = " + listaReceptoresDep[i]);
                      String sql = "{ CALL ORPRO_OFICIO_ENVIAR(?,?,?,?,?) }";
                      CallableStatement cstmD = cn.prepareCall(sql);
                      cstmD.setInt("P_IN_CODIGO_OFICIO",objForm.getIn_codigo_oficio() );
                      cstmD.setInt("P_IN_CODIGO_DEPEN_FACU", listaDependencias[i]);
                      cstmD.setString("P_CH_TIPO_DEPEN_FACU", "d");
                      cstmD.setInt("P_IN_CODIGO_USUARIO",listaReceptoresDep[i] );
                      cstmD.setInt("P_IN_ESTADO",28 );//pendiente
                      cstmD.executeUpdate();
                      cn.commit();
                      cstmD.close();
                    }
                    else
                    {
                      System.out.println(" dep y receptor  Es NULL " );
                    }
                }

                //cambiando estado en orpro_ta_oficio
                cambiarEstadoOficio(27, objForm.getIn_codigo_oficio());

            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("OFICIO - error al enviar : "+e);
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
       }
       //System.out.println("error aqui");
       return exito;
    }

    public boolean cambiarEstadoOficio(int estado,int in_codigo_oficio)
    {
        boolean exito = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);

            //cambiando de estado el oficio a enviado
            String query = "UPDATE orpro_oficio_circular SET IN_CODIGO_ESTADO = ? WHERE IN_CODIGO_OFICIO = ?";
            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, estado);
            pstm.setInt(2, in_codigo_oficio);
            pstm.executeUpdate();
            cn.commit();
            pstm.close();
            exito = true;

        }
        catch(Exception e)
        {
            exito = false;
            System.out.println("error al cambiar de estado a oficio: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("error al cambiar de estado a oficio: "+ex);
            }
       }
       return exito;
    }

    public boolean cambiarEstadoOficioEnTramite(int estado,int in_codigo_oficio, int in_codigo_fac_depend, String ch_tipo_depend_fac)
    {
        boolean exito = false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);

            //cambiando de estado el oficio a enviado
            String query = "UPDATE orpro_oficio_cir_depen_facu SET IN_ESTADO = ? WHERE IN_CODIGO_OFICIO = ? AND IN_CODIGO_DEPEN_FACU = ? AND CH_TIPO_DEPEND_FACU = ?";
            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, estado);
            pstm.setInt(2, in_codigo_oficio);
            pstm.setInt(3,in_codigo_fac_depend );
            pstm.setString(4, ch_tipo_depend_fac);
            pstm.executeUpdate();
            cn.commit();
            pstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito = false;
            System.out.println("error al cambiar de estado a oficio en tramite: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("error al cambiar de estado a oficio en tramite: "+ex);
            }
       }
       return exito;
    }

    //CAMBIA EL ESTADO DE LOS OFICIOS QUE ESTAN EN TRAMITE
    public boolean cambiarEstadoOficioEnTramite(int in_cod_facdep,String ch_tipo_facdep,int in_codigo_estado)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_CAMBIAR_ESTADO_OFICIO_TRAMITE(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);

             cstm.setInt("P_IN_COD_DEP_FAC",in_cod_facdep);
             cstm.setString("P_CH_TIPO_DEP_FAC",ch_tipo_facdep);
             cstm.setInt("P_IN_ESTADO",in_codigo_estado);

            cstm.executeUpdate();

            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al intentar aprobar el OFICIO : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al intentar aprobar el OFICIO: "+ex);
            }
       }
       return exito;
    }

    //Numero de oficios pendientes
    public int getoficiosTotalesPendientes()
    {
        int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(*) AS NumOficiosPendientes " +
                    "FROM orpro_oficio_cir_depen_facu " +
                    "where in_estado=28";

            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
              exito = rs.getInt("NumOficiosPendientes");
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios totales: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios totales: "+ex);
            }
       }
       return exito;
    }

    //Numero de oficios pendientes
    public int getoficiosTotalesHistorialEnviados()
    {
        int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(in_codigo_oficio) AS NumOficiosenviados  FROM orpro_oficio_circular where in_codigo_estado = 27";

            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while( rs.next()){
              exito = rs.getInt("NumOficiosenviados");
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios totales: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios totales: "+ex);
            }
       }
       return exito;
    }

    //Numero de oficios pendientes por USUARIO (DEPENDECIA-FACULTAD)
    public int getOficiosPendientesSegunUsuarios(int idDepFacu,String tipDepFac)
    {
        int exito=0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
             String query = "SELECT COUNT(*) AS NumOficiosPendientes " +
                    "FROM orpro_oficio_cir_depen_facu " +
                    "where in_estado=28 and in_codigo_depen_facu = ? and ch_tipo_depend_facu = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1,idDepFacu );
            pstm.setString(2, tipDepFac);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NumOficiosPendientes");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios segun usuario: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios segun usuario: "+ex);
            }
       }
       return exito;
    }

    //Numero de oficios pendientes LISTA - USUARIOS (DEPENDENCIA Y/O FACULTAD)
    public ArrayList<orpro_oficio_circular> getListaDeOficiosEnviadosPendientesSegunUsuario(String idDepFacu,String tipDepFac)
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();
            cn = ds.getConnection();
            String query = "select ofdf.in_codigo_oficio, of.ch_codigo_oficio,concat(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS usuario_receptor, " +
                    "ofdf.in_codigo_usuario as codigo_receptor, " +
                    "CASE TRIM(ofdf.CH_TIPO_DEPEND_FACU) " +
                    "WHEN null THEN 'NO DEFICNIDO' " +
                    "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD = ofdf.IN_CODIGO_DEPEN_FACU) " +
                    "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA = ofdf.IN_CODIGO_DEPEN_FACU) " +
                    "ELSE 'NO DEFICNIDO' " +
                    "END AS FACULTAD_DEPENDECIA, " +
                    "ofdf.dt_fecha, " +
                    "ABS(datediff(OFdf.DT_FECHA,date (curdate() ) )) AS DIAS_TRANSCURRIDOS," +
                    "ofdf.IN_CODIGO_DEPEN_FACU, "+
                    "ofdf.CH_TIPO_DEPEND_FACU, "+
                    "of.in_usuario_emisor " +
                    "from orpro_oficio_cir_depen_facu ofdf " +
                    "inner join ORPRO_OFICIO_CIRCULAR of on ofdf.in_codigo_oficio = of.in_codigo_oficio " +
                    "inner join ORGEN_TA_USUARIO u on ofdf.in_codigo_usuario = u.in_codigo_usuario " +
                    "WHERE ofdf.in_estado = 28 and ofdf.CH_TIPO_DEPEND_FACU=? and ofdf.IN_CODIGO_DEPEN_FACU = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            
            pstm.setString(1,tipDepFac);
            pstm.setInt(2,Integer.parseInt(idDepFacu) );
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular dataForm = new orpro_oficio_circular();
                
                dataForm.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                dataForm.setCh_codigo_oficio(rs.getString("ch_codigo_oficio"));
                dataForm.setNom_usuario(rs.getString("usuario_receptor"));
                dataForm.setNombreDependFacu(rs.getString("FACULTAD_DEPENDECIA"));
                dataForm.setDt_fecha(rs.getDate("dt_fecha").toString());
                dataForm.setNumDiasTranscurridos(rs.getInt("DIAS_TRANSCURRIDOS"));
                dataForm.setIn_usuario_emisor(rs.getInt("in_usuario_emisor"));
                dataForm.setIn_cod_fac_dep( rs.getInt("IN_CODIGO_DEPEN_FACU") );
                dataForm.setCh_tipo_fac_dep(rs.getString("CH_TIPO_DEPEND_FACU"));
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

    //Numero de oficios pendientes LISTA - USUARIOS (DEPENDENCIA Y/O FACULTAD)
    public ArrayList<orpro_oficio_circular> getListaDeOficiosAdjuntoSegunUsuario(String idDepFacu,String tipDepFac)
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();
            cn = ds.getConnection();
            String query = "select ofdf.in_codigo_oficio, of.ch_codigo_oficio,concat(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS usuario_receptor, " +
                    "ofdf.in_codigo_usuario as codigo_receptor, " +
                    "CASE TRIM(ofdf.CH_TIPO_DEPEND_FACU) " +
                    "WHEN null THEN 'NO DEFICNIDO' " +
                    "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD = ofdf.IN_CODIGO_DEPEN_FACU) " +
                    "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA = ofdf.IN_CODIGO_DEPEN_FACU) " +
                    "ELSE 'NO DEFICNIDO' " +
                    "END AS FACULTAD_DEPENDECIA, " +
                    "ofdf.dt_fecha, " +
                    "ABS(datediff(OFdf.DT_FECHA,date (curdate() ) )) AS DIAS_TRANSCURRIDOS," +
                    "of.in_usuario_emisor " +
                    "from orpro_oficio_cir_depen_facu ofdf " +
                    "inner join ORPRO_OFICIO_CIRCULAR of on ofdf.in_codigo_oficio = of.in_codigo_oficio " +
                    "inner join ORGEN_TA_USUARIO u on ofdf.in_codigo_usuario = u.in_codigo_usuario " +
                    "WHERE ofdf.in_estado = 29 and ofdf.CH_TIPO_DEPEND_FACU=? and ofdf.IN_CODIGO_DEPEN_FACU = ?";

            PreparedStatement pstm = cn.prepareStatement(query);

            pstm.setString(1,tipDepFac);
            pstm.setInt(2,Integer.parseInt(idDepFacu) );
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular dataForm = new orpro_oficio_circular();
                dataForm.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                dataForm.setCh_codigo_oficio(rs.getString("ch_codigo_oficio"));
                dataForm.setNom_usuario(rs.getString("usuario_receptor"));
                dataForm.setNombreDependFacu(rs.getString("FACULTAD_DEPENDECIA"));
                dataForm.setDt_fecha(rs.getDate("dt_fecha").toString());
                dataForm.setNumDiasTranscurridos(rs.getInt("DIAS_TRANSCURRIDOS"));
                dataForm.setIn_usuario_emisor(rs.getInt("in_usuario_emisor"));
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

    //Numero de oficios pendientes LISTA
    public ArrayList<orpro_oficio_circular> getListaOficiosEnviadosOCDO()
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();

            cn = ds.getConnection();//DBMS_LOB.substr(OFC.VC_RUTA_DOC,DBMS_LOB.getlength(OFC.VC_RUTA_DOC),1)
            String query = "SELECT OF.IN_CODIGO_OFICIO, OF.CH_CODIGO_OFICIO,OF.DT_FECHA,OF.IN_USUARIO_EMISOR, CONCAT(U.vc_nombres,' ',U.vc_apellido_paterno ) AS USUARIO "+
                    "FROM ORPRO_OFICIO_CIRCULAR OF "+
                    "INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=OF.IN_USUARIO_EMISOR "+
                    "WHERE OF.IN_CODIGO_ESTADO = 27 "+
                    "ORDER BY OF.IN_CODIGO_OFICIO DESC";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular oOficio = new orpro_oficio_circular();
                oOficio.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                oOficio.setCh_codigo_oficio(rs.getString("CH_CODIGO_OFICIO"));
                oOficio.setDt_fecha(rs.getString("DT_FECHA"));
                oOficio.setNom_usuario(rs.getString("USUARIO"));
                oOficio.setIn_usuario_emisor(rs.getInt("IN_USUARIO_EMISOR"));

                listaOficios.add(oOficio);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar la lista de oficios enviados que no se han enviado: "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex){ System.out.println("finally - Error al cargar la lista de oficios enviados que no se han enviado: "+ex);}
       }
       return listaOficios;
    }

    //Numero de oficios pendientes LISTA
    public ArrayList<orpro_oficio_circular> getListaOficiosEnviadosAlJefeOCDO()
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();

            cn = ds.getConnection();//DBMS_LOB.substr(OFC.VC_RUTA_DOC,DBMS_LOB.getlength(OFC.VC_RUTA_DOC),1)
            String query = "SELECT OF.IN_CODIGO_OFICIO, OF.CH_CODIGO_OFICIO,OF.DT_FECHA,OF.IN_USUARIO_EMISOR, CONCAT(U.vc_nombres,' ',U.vc_apellido_paterno ) AS USUARIO "+
                    "FROM ORPRO_OFICIO_CIRCULAR OF "+
                    "INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=OF.IN_USUARIO_EMISOR "+
                    "WHERE OF.IN_CODIGO_ESTADO = 41 "+
                    "ORDER BY OF.IN_CODIGO_OFICIO DESC";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular oOficio = new orpro_oficio_circular();
                oOficio.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                oOficio.setCh_codigo_oficio(rs.getString("CH_CODIGO_OFICIO"));
                oOficio.setDt_fecha(rs.getString("DT_FECHA"));
                oOficio.setNom_usuario(rs.getString("USUARIO"));
                oOficio.setIn_usuario_emisor(rs.getInt("IN_USUARIO_EMISOR"));

                listaOficios.add(oOficio);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar la lista de oficios enviados que no se han enviado: "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex){ System.out.println("finally - Error al cargar la lista de oficios enviados que no se han enviado: "+ex);}
       }
       return listaOficios;
    }

     //lis de oficios parobados por la ocdo
    public ArrayList<orpro_oficio_circular> getListaOficiosAprobadosProElJefeOCDO()
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();

            cn = ds.getConnection();//DBMS_LOB.substr(OFC.VC_RUTA_DOC,DBMS_LOB.getlength(OFC.VC_RUTA_DOC),1)
            String query = "SELECT OF.IN_CODIGO_OFICIO, OF.CH_CODIGO_OFICIO,OF.DT_FECHA,OF.IN_USUARIO_EMISOR, CONCAT(U.vc_nombres,' ',U.vc_apellido_paterno ) AS USUARIO "+
                    "FROM ORPRO_OFICIO_CIRCULAR OF "+
                    "INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=OF.IN_USUARIO_EMISOR "+
                    "WHERE OF.IN_CODIGO_ESTADO = 42 "+
                    "ORDER BY OF.IN_CODIGO_OFICIO DESC";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular oOficio = new orpro_oficio_circular();
                oOficio.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                oOficio.setCh_codigo_oficio(rs.getString("CH_CODIGO_OFICIO"));
                oOficio.setDt_fecha(rs.getString("DT_FECHA"));
                oOficio.setNom_usuario(rs.getString("USUARIO"));
                oOficio.setIn_usuario_emisor(rs.getInt("IN_USUARIO_EMISOR"));

                listaOficios.add(oOficio);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar la lista de oficios enviados que no se han enviado: "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex){ System.out.println("finally - Error al cargar la lista de oficios enviados que no se han enviado: "+ex);}
       }
       return listaOficios;
    }

    //obtiene la lista de oficios que no se han enviado, es para la modificación
    public ArrayList<orpro_oficio_circular> getListaOficiosGuardados(int cod_fac_dep, String tipo_fac_dep)
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();

            cn = ds.getConnection();//DBMS_LOB.substr(OFC.VC_RUTA_DOC,DBMS_LOB.getlength(OFC.VC_RUTA_DOC),1)
            String query = "SELECT OF.IN_CODIGO_OFICIO, OF.CH_CODIGO_OFICIO,OF.DT_FECHA,OF.IN_USUARIO_EMISOR, CONCAT(U.vc_nombres,' ',U.vc_apellido_paterno ) AS USUARIO "+
                    "FROM ORPRO_OFICIO_CIRCULAR OF "+
                    "INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO=OF.IN_USUARIO_EMISOR "+
                    "WHERE OF.IN_CODIGO_ESTADO = 26 and in_cod_fac_dep = ? and ch_tipo_fac_dep = ? "+
                    "ORDER BY OF.IN_CODIGO_OFICIO DESC";
            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, cod_fac_dep);
            pstm.setString(2, tipo_fac_dep);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular oOficio = new orpro_oficio_circular();
                oOficio.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                oOficio.setCh_codigo_oficio(rs.getString("CH_CODIGO_OFICIO"));
                oOficio.setDt_fecha(rs.getString("DT_FECHA"));
                oOficio.setNom_usuario(rs.getString("USUARIO"));
                oOficio.setIn_usuario_emisor(rs.getInt("IN_USUARIO_EMISOR"));

                listaOficios.add(oOficio);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar la lista de oficios que no se han enviado: "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex){ System.out.println("finally - Error al cargar la lista de oficios que no se han enviado: "+ex);}
       }
       return listaOficios;
    }

    public int getCountOficiosGuardados(int cod_fac_dep, String tipo_fac_dep)
    {
      int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "select count(in_codigo_oficio) as num from ORPRO_OFICIO_CIRCULAR where in_codigo_estado = 26 and in_cod_fac_dep = ? and ch_tipo_fac_dep = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, cod_fac_dep);
            pstm.setString(2, tipo_fac_dep);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("num");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios pendientes: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios pendientes: "+ex);
            }
       }
       return exito;
    }

    public int getCountOficiosEnviadosAlJefeOCDO(int cod_fac_dep, String tipo_fac_dep)
    {
      int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "select count(in_codigo_oficio) as num from ORPRO_OFICIO_CIRCULAR where in_codigo_estado = 41 and in_cod_fac_dep = ? and ch_tipo_fac_dep = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, cod_fac_dep);
            pstm.setString(2, tipo_fac_dep);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("num");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios pendientes: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios pendientes: "+ex);
            }
       }
       return exito;
    }

    public int getCountOficiosAprobadosPorJefeOCDO(int cod_fac_dep, String tipo_fac_dep)
    {
      int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "select count(in_codigo_oficio) as num from ORPRO_OFICIO_CIRCULAR where in_codigo_estado = 42 and in_cod_fac_dep = ? and ch_tipo_fac_dep = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, cod_fac_dep);
            pstm.setString(2, tipo_fac_dep);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("num");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios pendientes: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios pendientes: "+ex);
            }
       }
       return exito;
    }

    public int getCountOficiosEnviadosADependencias()
    {
      int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "select count(in_codigo_oficio) as num from orpro_oficio_cir_depen_facu where in_estado = 28";

            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("num");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios pendientes: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios pendientes: "+ex);
            }
       }
       return exito;
    }

    //lista de oficios bandeja
    public ArrayList<orpro_oficio_circular> getListaTotalOficiosPendientes()
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();
            cn = ds.getConnection();
            String query = "select ofdf.in_codigo_oficio, of.ch_codigo_oficio,concat(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS usuario_receptor, " +
                    "ofdf.in_codigo_usuario as codigo_receptor, " +
                    "CASE TRIM(ofdf.CH_TIPO_DEPEND_FACU) " +
                    "WHEN null THEN 'NO DEFICNIDO' " +
                    "WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD = ofdf.IN_CODIGO_DEPEN_FACU) " +
                    "WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA = ofdf.IN_CODIGO_DEPEN_FACU) " +
                    "ELSE 'NO DEFICNIDO' " +
                    "END AS FACULTAD_DEPENDECIA, " +
                    "ofdf.dt_fecha, " +
                    "ABS(datediff(OFdf.DT_FECHA,date (curdate() ) )) AS DIAS_TRANSCURRIDOS," +
                    "ofdf.IN_CODIGO_DEPEN_FACU, "+
                    "ofdf.CH_TIPO_DEPEND_FACU, "+
                    "of.in_usuario_emisor " +
                    "from orpro_oficio_cir_depen_facu ofdf " +
                    "inner join ORPRO_OFICIO_CIRCULAR of on ofdf.in_codigo_oficio = of.in_codigo_oficio " +
                    "inner join ORGEN_TA_USUARIO u on ofdf.in_codigo_usuario = u.in_codigo_usuario " +
                    "WHERE ofdf.in_estado = 28 ";
            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular dataForm = new orpro_oficio_circular();
                dataForm.setIn_codigo_usuario(rs.getInt("codigo_receptor"));
                dataForm.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                dataForm.setCh_codigo_oficio(rs.getString("ch_codigo_oficio"));
                dataForm.setNom_usuario(rs.getString("usuario_receptor"));
                dataForm.setNombreDependFacu(rs.getString("FACULTAD_DEPENDECIA"));
                dataForm.setDt_fecha(rs.getDate("dt_fecha").toString());
                dataForm.setNumDiasTranscurridos(rs.getInt("DIAS_TRANSCURRIDOS"));
                dataForm.setIn_usuario_emisor(rs.getInt("in_usuario_emisor"));
                dataForm.setIn_cod_fac_dep( rs.getInt("IN_CODIGO_DEPEN_FACU") );
                dataForm.setCh_tipo_fac_dep(rs.getString("CH_TIPO_DEPEND_FACU"));
                //System.out.println("datos FIN.> "+rs.getInt("DIAS_TRANSCURRIDOS"));
                listaOficios.add(dataForm);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("error al obtener la lista de oficios pendientes  totales: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al obtener la lista de oficios pendientes  totales: "+ex);}
       }
       return listaOficios;
    }
    
    //Obtener oficio segun ID para la VISTA
    public orpro_oficio_circular getOficioParalaVista(int inEmisor,int in_codigo_oficio)
    {
        orpro_oficio_circular obj = new orpro_oficio_circular();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT OF.IN_CODIGO_OFICIO,OF.CH_CODIGO_OFICIO,OF.DT_FECHA,OF.VC_CIUDAD,OF.VC_NOMBRE_ANIO,OF.VC_CUERPO_DOC, " +
                    "CONCAT(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS USUARIO_EMISOR, " +
                    "(SELECT CU.VC_NOMBRE FROM ORGEN_TA_CARGO_USUARIO CU WHERE CU.IN_CODIGO_CARGO_ESTRUC = " +
                    "(SELECT UC.IN_CODIGO_CARGO  FROM ORGEN_TA_USUARIO_CARGO UC WHERE UC.IN_CODIGO_USUARIO = ?) ) AS CARGO_EMISOR, OF.IN_USUARIO_EMISOR " +
                    "FROM ORPRO_OFICIO_CIRCULAR OF " +
                    "INNER JOIN ORGEN_TA_USUARIO U ON OF.IN_USUARIO_EMISOR = U.IN_CODIGO_USUARIO " +
                    "WHERE OF.IN_CODIGO_OFICIO = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1,inEmisor);
            pstm.setInt(2,in_codigo_oficio);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                obj.setCh_codigo_oficio(rs.getString("CH_CODIGO_OFICIO"));
                obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
                obj.setVc_ciudad(rs.getString("VC_CIUDAD"));
                obj.setVc_nombre_anio(rs.getString("VC_NOMBRE_ANIO"));
                obj.setVc_cuerpo_doc(rs.getString("VC_CUERPO_DOC"));
                obj.setNom_usuario(rs.getString("USUARIO_EMISOR"));
                obj.setCargo_usuario(rs.getString("CARGO_EMISOR"));
                obj.setIn_usuario_emisor(rs.getInt("IN_USUARIO_EMISOR"));
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("VISTA - Error al obtener el oficio segun el ID: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("VISTA - Error al obtener el oficio segun el ID: "+ex);}
       }
        return obj;
    }

    //Obtener oficio segun ID para modificar
    public orpro_oficio_circular getOficioParaModificar(int in_codigo_oficio)
    {
        orpro_oficio_circular obj = new orpro_oficio_circular();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT IN_CODIGO_OFICIO,IN_CODIGO_USUARIO,CH_CODIGO_OFICIO, " +
                    "DT_FECHA,VC_CIUDAD,VC_NOMBRE_ANIO,VC_CUERPO_DOC,IN_USUARIO_EMISOR,CH_TIPO_FAC_DEP,IN_COD_FAC_DEP " +
                    "FROM ORPRO_OFICIO_CIRCULAR WHERE IN_CODIGO_OFICIO = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1,in_codigo_oficio);
            ResultSet rs =  pstm.executeQuery();

            while(rs.next())
            {
                obj.setIn_codigo_oficio(rs.getInt("IN_CODIGO_OFICIO"));
                obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
                obj.setCh_codigo_oficio(rs.getString("CH_CODIGO_OFICIO"));
                obj.setDt_fecha(rs.getDate("DT_FECHA").toString());
                obj.setVc_ciudad(rs.getString("VC_CIUDAD"));
                obj.setVc_nombre_anio(rs.getString("VC_NOMBRE_ANIO"));
                obj.setVc_cuerpo_doc(rs.getString("VC_CUERPO_DOC"));
                obj.setIn_usuario_emisor(rs.getInt("IN_USUARIO_EMISOR"));
                obj.setCh_tipo_fac_dep(rs.getString("CH_TIPO_FAC_DEP") );
                obj.setIn_cod_fac_dep(rs.getInt("IN_COD_FAC_DEP"));
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al obtener el oficio segun el ID para modificar: "+e);
        }
        finally
        {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Error al obtener el oficio segun el ID para modificar: "+ex);}
        }
        return obj;
    }

    //codigo nuevo genera
    public String getCodigoGenerado()
    {
        String NuevoCodigo="";
        String codigo_retorno="";
        try
        {
            cn = ds.getConnection();

            String sql = "SELECT FNL_GENERAR_NUEVO_CODIGO_OFICIO()";
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
              codigo_retorno="OFICIO-N-1-OCDO-"+dateFormat.format(fecha);
            }
            else
            {
               codigo_retorno = NuevoCodigo;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error al obtener el codigo generado: "+e);
        }
       finally
       {
            try{cn.close();}
            catch ( Exception ex){ System.out.println("Finally - Error al obtener el codigo generado: "+ex);}
            return codigo_retorno;
        }
    }

    //funcion para el jefe ocdo , podra aprobar el oficio
    //modificado dia 26/07/2012
    public boolean aprobarOficioProOcdo(int in_codigo_oficio,int in_codigo_estado, int idUser)
    {
        boolean exito=false;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_OFICIO_APROBAR(?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);

             cstm.setInt("P_IN_COD_OFICIO",in_codigo_oficio);
             cstm.setInt("P_IN_COD_ESTADO",in_codigo_estado);
             cstm.setInt("P_IN_USUARIO_EMISOR", idUser);

            cstm.executeUpdate();
            
            cn.commit();
            cstm.close();
            exito = true;
        }
        catch(Exception e)
        {
            exito=false;
            System.out.println("Error al intentar aprobar el OFICIO : "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - Error al intentar aprobar el OFICIO: "+ex);
            }
       }
       return exito;
    }

    //escribe comentario y lo almacena
    public boolean crearObservacionOficio(orpro_ta_observacion_oficio obj,int idUsu)
    {
        boolean estado = false;
        
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String sql = "{ CALL SP_ORPRO_TA_OBSV_OFICIO_INSERT(?,?,?,?) }";
            CallableStatement cstm = cn.prepareCall(sql);
            cstm.setInt("P_IN_CODIGO_OFICIO", obj.getIn_codigo_oficio() );
            cstm.setString("P_VC_OBSERVACION", obj.getVc_observacion());
            cstm.setString("P_VC_NOMBRE_USUARIO", obj.getVc_nombre_usuario());
            cstm.setInt("P_IN_CODIGO_USUARIO", idUsu);
            cstm.executeUpdate();
            cn.commit();
            estado = true;
            cstm.close();
        }
        catch(Exception e)
        {
            System.out.println("crearObservacionOficio : "+e);
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
                System.out.println("finally - crearObservacionOficio : "+ex);
            }
            return  estado;
       }
    }

    //Lista de observaciones segun id oficio
    public ArrayList<orpro_ta_observacion_oficio> getListaObservacionesDeOficio(int codOficio)
    {
        ArrayList<orpro_ta_observacion_oficio> listaObservaciones = null;

        try
        {
            listaObservaciones = new ArrayList<orpro_ta_observacion_oficio>();
            cn = ds.getConnection();
            String sql ="SELECT IN_CODIGO_OBSERVA,VC_OBSERVACION,VC_NOMBRE_USUARIO,DT_FECHA_CREA,IN_CODIGO_USUARIO "+
                        "FROM orpro_ta_observaciones_oficio "+
                        "WHERE IN_CODIGO_OFICIO = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setInt(1, codOficio);
            ResultSet rs = pstm.executeQuery();
            while(rs.next() )
            {
               orpro_ta_observacion_oficio obj = new orpro_ta_observacion_oficio();

               obj.setIn_codigo_obs_oficio(rs.getInt("IN_CODIGO_OBSERVA"));
               obj.setNombreUsuario(rs.getString("VC_NOMBRE_USUARIO") );
               obj.setVc_observacion(rs.getString("VC_OBSERVACION"));
               obj.setDt_fecha_crea(rs.getTimestamp("DT_FECHA_CREA").toString());
               obj.setIn_codigo_usuario(rs.getInt("IN_CODIGO_USUARIO"));
               listaObservaciones.add(obj);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println(" getListaObservacionesDeOficio : "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println(" getListaObservacionesDeOficio : "+ex);}
       }
       return listaObservaciones;
    }

    // 24/07/2012
    //lista de oficio recibidos por la dependencia y enviado por la ocdo
    public ArrayList<orpro_oficio_circular> getListaHistorialDeOficioRecibidosPorDependencia(String ch_tipo_depend_facu,int in_codigo_depen_facu )
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();

            cn = ds.getConnection();
            String query =  "SELECT "+
                            "ofdf.in_codigo_oficio, "+
                            "of.ch_codigo_oficio, "+
                            "( "+
                            "  SELECT concat(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) "+
                            "  FROM ORGEN_TA_USUARIO U WHERE U.IN_CODIGO_USUARIO = of.in_usuario_emisor "+
                            ") AS USUARIO_EMISOR, "+
                            "( "+
                            "  SELECT concat(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) "+
                            "  FROM ORGEN_TA_USUARIO U WHERE U.IN_CODIGO_USUARIO = ofdf.in_codigo_usuario "+
                            ") AS USUARIO_RECEPTOR, "+
                            "ofdf.dt_fecha, "+
                            "ofdf.IN_CODIGO_DEPEN_FACU, "+
                            "ofdf.CH_TIPO_DEPEND_FACU, "+
                            "E.VC_NOMBRE_ESTADO "+
                            "FROM orpro_oficio_cir_depen_facu ofdf "+
                            "inner join ORGEN_TA_ESTADO E ON ofdf.IN_ESTADO = E.IN_CODIGO_ESTADO "+
                            "inner join ORPRO_OFICIO_CIRCULAR of on of.in_codigo_oficio = ofdf.in_codigo_oficio "+
                            "WHERE ofdf.CH_TIPO_DEPEND_FACU = ? and ofdf.IN_CODIGO_DEPEN_FACU = ?";
            
            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setString(1, ch_tipo_depend_facu);
            pstm.setInt(2, in_codigo_depen_facu);
            
            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular oOficio = new orpro_oficio_circular();
                oOficio.setIn_codigo_oficio(       rs.getInt("in_codigo_oficio"));
                oOficio.setCh_codigo_oficio(       rs.getString("ch_codigo_oficio"));
                oOficio.setNombre_usuario_emisor(  rs.getString("USUARIO_EMISOR"));
                oOficio.setNombre_usuario_receptor(rs.getString("USUARIO_RECEPTOR"));
                oOficio.setDt_fecha(               rs.getString("dt_fecha"));
                oOficio.setCh_estado(              rs.getString("VC_NOMBRE_ESTADO"));
                oOficio.setIn_cod_fac_dep( rs.getInt("IN_CODIGO_DEPEN_FACU") );
                oOficio.setCh_tipo_fac_dep(rs.getString("CH_TIPO_DEPEND_FACU"));

                listaOficios.add(oOficio);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el historial de oficios de la dependencia : "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("finally - Error al cargar el historial de oficios de la dependencia : "+ex);}
       }
       return listaOficios;
    }

    //  24/07/2012
    public ArrayList<orpro_oficio_circular> getListaHistorialDeOficioTramitadosTotal( )
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();

            cn = ds.getConnection();
            String query =  "SELECT "+
                            "ofdf.in_codigo_oficio, "+
                            "of.ch_codigo_oficio, "+
                            "( "+
                            "  SELECT concat(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) "+
                            "  FROM ORGEN_TA_USUARIO U WHERE U.IN_CODIGO_USUARIO = of.in_usuario_emisor "+
                            ") AS USUARIO_EMISOR, "+
                            "( "+
                            "  SELECT concat(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) "+
                            "  FROM ORGEN_TA_USUARIO U WHERE U.IN_CODIGO_USUARIO = ofdf.in_codigo_usuario "+
                            ") AS USUARIO_RECEPTOR, "+
                            "ofdf.dt_fecha, "+
                            "ofdf.IN_CODIGO_DEPEN_FACU, "+
                            "ofdf.CH_TIPO_DEPEND_FACU, "+
                            "E.VC_NOMBRE_ESTADO "+
                            "FROM orpro_oficio_cir_depen_facu ofdf "+
                            "inner join ORGEN_TA_ESTADO E ON ofdf.IN_ESTADO = E.IN_CODIGO_ESTADO "+
                            "inner join ORPRO_OFICIO_CIRCULAR of on of.in_codigo_oficio = ofdf.in_codigo_oficio ";

            PreparedStatement pstm = cn.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular oOficio = new orpro_oficio_circular();
                oOficio.setIn_codigo_oficio(       rs.getInt("in_codigo_oficio"));
                oOficio.setCh_codigo_oficio(       rs.getString("ch_codigo_oficio"));
                oOficio.setNombre_usuario_emisor(  rs.getString("USUARIO_EMISOR"));
                oOficio.setNombre_usuario_receptor(rs.getString("USUARIO_RECEPTOR"));
                oOficio.setDt_fecha(               rs.getString("dt_fecha"));
                oOficio.setCh_estado(              rs.getString("VC_NOMBRE_ESTADO"));
                oOficio.setIn_cod_fac_dep( rs.getInt("IN_CODIGO_DEPEN_FACU") );
                oOficio.setCh_tipo_fac_dep(rs.getString("CH_TIPO_DEPEND_FACU"));

                listaOficios.add(oOficio);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el historial de oficios  - total : "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("finally - Error al cargar el historial de oficios - total : "+ex);}
       }
       return listaOficios;
    }

    //24/07/2012
    //Numero de oficios tramitados
    public int getTotalOficioTramitado()
    {
        int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT count(ofdf.in_codigo_oficio) as NumOficiostramitados FROM orpro_oficio_cir_depen_facu ofdf";

            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NumOficiostramitados");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios tramitados totales: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios tramitados totales: "+ex);
            }
       }
       return exito;
    }

    //24/07/2012
    //Numero de oficios tramitados
    public int getTotalOficioTramitadoSegunDependencia(int in_cod_facdep, String ch_tipo_facdep)
    {
        int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT count(ofdf.in_codigo_oficio) as NumOficiostramitados " +
                           "FROM orpro_oficio_cir_depen_facu ofdf "+
                           " WHERE ofdf.CH_TIPO_DEPEND_FACU = ? and ofdf.IN_CODIGO_DEPEN_FACU = ?";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setString(1, ch_tipo_facdep);
            pstm.setInt(2, in_cod_facdep);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NumOficiostramitados");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios tramitados segun dependencia: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios tramitados segun dependencia: "+ex);
            }
       }
       return exito;
    }

    //  24/07/2012
    // para los ocdo , muestra todos los oficios de las dependencias emitidas como respuetas
    public ArrayList<orpro_oficio_circular> getListaHistorialDeOficiosRespuestaDeDependenciasTotal( )
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();

            cn = ds.getConnection();
            String query =  "SELECT OF.IN_CODIGO_OFICIO, OF.CH_CODIGO_OFICIO,OF.DT_FECHA,OF.IN_USUARIO_EMISOR, "+
                            " CONCAT(U.vc_nombres,' ',U.vc_apellido_paterno ) AS USUARIO_EMISOR, "+
                            " CASE TRIM(OF.CH_TIPO_FAC_DEP) "+
                            " WHEN null THEN 'NO DEFINIDO' "+
                            " WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD = OF.IN_COD_FAC_DEP) "+
                            " WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA = OF.IN_COD_FAC_DEP) "+
                            " ELSE 'NO DEFINIDO' "+
                            " END AS FACULTAD_DEPENDENCIA "+
                            " FROM ORPRO_OFICIO_CIRCULAR OF "+
                            " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO = OF.IN_USUARIO_EMISOR "+
                            " WHERE OF.IN_CODIGO_ESTADO = 43 "+
                            " ORDER BY OF.IN_CODIGO_OFICIO DESC";

            PreparedStatement pstm = cn.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular oOficio = new orpro_oficio_circular();
                oOficio.setIn_codigo_oficio(       rs.getInt("IN_CODIGO_OFICIO"));
                oOficio.setCh_codigo_oficio(       rs.getString("CH_CODIGO_OFICIO"));
                oOficio.setNombre_usuario_emisor(  rs.getString("USUARIO_EMISOR"));
                oOficio.setIn_usuario_emisor( rs.getInt("IN_USUARIO_EMISOR"));
                oOficio.setDt_fecha(rs.getDate("DT_FECHA").toString());
                oOficio.setNombreDependFacu( rs.getString("FACULTAD_DEPENDENCIA"));

                listaOficios.add(oOficio);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el historial de oficios respuesta  - total : "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("finally - Error al cargar el historial de oficios respuesta - total : "+ex);}
       }
       return listaOficios;
    }


    //  24/07/2012
    // para los ocdo , muestra todos los oficios de las dependencias emitidas como respuetas
    public ArrayList<orpro_oficio_circular> getListaHistorialDeOficiosRespuestaSegunDependnecia( int in_cod_facdep, String ch_tipo_facdep )
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();

            cn = ds.getConnection();
            String query =  "SELECT OF.IN_CODIGO_OFICIO, OF.CH_CODIGO_OFICIO,OF.DT_FECHA,OF.IN_USUARIO_EMISOR, "+
                            " CONCAT(U.vc_nombres,' ',U.vc_apellido_paterno ) AS USUARIO_EMISOR, "+
                            " CASE TRIM(OF.CH_TIPO_FAC_DEP) "+
                            " WHEN null THEN 'NO DEFINIDO' "+
                            " WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD = OF.IN_COD_FAC_DEP) "+
                            " WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA = OF.IN_COD_FAC_DEP) "+
                            " ELSE 'NO DEFINIDO' "+
                            " END AS FACULTAD_DEPENDENCIA "+
                            " FROM ORPRO_OFICIO_CIRCULAR OF "+
                            " INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO = OF.IN_USUARIO_EMISOR "+
                            " WHERE OF.IN_CODIGO_ESTADO = 43 AND IN_COD_FAC_DEP = ? AND CH_TIPO_FAC_DEP = ? "+
                            " ORDER BY OF.IN_CODIGO_OFICIO DESC";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, in_cod_facdep);
            pstm.setString(2, ch_tipo_facdep);

            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular oOficio = new orpro_oficio_circular();
                oOficio.setIn_codigo_oficio(       rs.getInt("IN_CODIGO_OFICIO"));
                oOficio.setCh_codigo_oficio(       rs.getString("CH_CODIGO_OFICIO"));
                oOficio.setNombre_usuario_emisor(  rs.getString("USUARIO_EMISOR"));
                oOficio.setIn_usuario_emisor( rs.getInt("IN_USUARIO_EMISOR"));
                oOficio.setDt_fecha(rs.getDate("DT_FECHA").toString());
                oOficio.setNombreDependFacu( rs.getString("FACULTAD_DEPENDENCIA"));

                listaOficios.add(oOficio);
            }
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el historial de oficios respuesta  - total : "+e);
        }
       finally
       {
            try
            {cn.close();}
            catch ( Exception ex)
            { System.out.println("finally - Error al cargar el historial de oficios respuesta - total : "+ex);}
       }
       return listaOficios;
    }

    //24/07/2012
    //Numero de oficios tramitados
    public int getTotalOficioRespuestaSegunDependencia(int in_cod_facdep, String ch_tipo_facdep)
    {
        int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query =  "SELECT COUNT(OF.IN_CODIGO_OFICIO) as NumOficiosRespuesta "+
                            "FROM ORPRO_OFICIO_CIRCULAR OF "+
                            "INNER JOIN ORGEN_TA_USUARIO U ON U.IN_CODIGO_USUARIO = OF.IN_USUARIO_EMISOR "+
                            "WHERE OF.IN_CODIGO_ESTADO = 43 AND IN_COD_FAC_DEP = ? AND CH_TIPO_FAC_DEP = ? ";

            PreparedStatement pstm = cn.prepareStatement(query);
            pstm.setInt(1, in_cod_facdep);
            pstm.setString(2, ch_tipo_facdep);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NumOficiosRespuesta");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios tramitados segun dependencia: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios tramitados segun dependencia: "+ex);
            }
       }
       return exito;
    }

    //24/07/2012
    //Numero de oficios tramitados
    public int getTotalOficioRespuesta()
    {
        int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "SELECT COUNT(OF.IN_CODIGO_OFICIO) as NumOficiosRespuesta FROM ORPRO_OFICIO_CIRCULAR OF WHERE OF.IN_CODIGO_ESTADO = 43";

            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NumOficiosRespuesta");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios tramitados totales: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios tramitados totales: "+ex);
            }
       }
       return exito;
    }

    // lista de oficio en tramite revisados
    public ArrayList<orpro_oficio_circular> getListaDeOficiosenTramiteRevisados()
    {
        ArrayList<orpro_oficio_circular> listaOficios = null;
        try
        {
            listaOficios = new ArrayList<orpro_oficio_circular>();
            cn = ds.getConnection();
            String query =  " select ofdf.in_codigo_oficio, of.ch_codigo_oficio,concat(U.VC_NOMBRES,' ',U.VC_APELLIDO_PATERNO,' ',U.VC_APELLIDO_MATERNO) AS usuario_receptor, "+
                            " ofdf.in_codigo_usuario as codigo_receptor, "+
                            " CASE TRIM(ofdf.CH_TIPO_DEPEND_FACU) "+
                            " WHEN null THEN 'NO DEFICNIDO' "+
                            " WHEN 'f' THEN (SELECT F.VC_NOMBRE FROM ORGEN_TA_FACULTAD F WHERE F.IN_CODIGO_FACULTAD = ofdf.IN_CODIGO_DEPEN_FACU) "+
                            " WHEN 'd' THEN (SELECT D.VC_NOMBRE FROM ORGEN_TA_DEPENDENCIA D WHERE D.IN_CODIGO_DEPENDENCIA = ofdf.IN_CODIGO_DEPEN_FACU) "+
                            " ELSE 'NO DEFICNIDO'  "+
                            " END AS FACULTAD_DEPENDECIA, "+
                            " ofdf.dt_fecha, "+
                            " of.in_usuario_emisor "+
                            " from orpro_oficio_cir_depen_facu ofdf "+
                            " inner join ORPRO_OFICIO_CIRCULAR of on ofdf.in_codigo_oficio = of.in_codigo_oficio "+
                            " inner join ORGEN_TA_USUARIO u on ofdf.in_codigo_usuario = u.in_codigo_usuario "+
                            " WHERE ofdf.in_estado = 29";
            
            PreparedStatement pstm = cn.prepareStatement(query);

            ResultSet rs = pstm.executeQuery();

            while(rs.next())
            {
                orpro_oficio_circular dataForm = new orpro_oficio_circular();
                dataForm.setIn_codigo_oficio(rs.getInt("in_codigo_oficio"));
                dataForm.setCh_codigo_oficio(rs.getString("ch_codigo_oficio"));
                dataForm.setNom_usuario(rs.getString("usuario_receptor"));
                dataForm.setNombreDependFacu(rs.getString("FACULTAD_DEPENDECIA"));
                dataForm.setDt_fecha(rs.getDate("dt_fecha").toString());
                dataForm.setIn_usuario_emisor(rs.getInt("in_usuario_emisor"));
                
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
    
    //25/07/2012
    //Numero de oficios REvisados
    public int getTotalOficioRevisados()
    {
        int exito = 0;
        try
        {
            cn = ds.getConnection();
            cn.setAutoCommit(false);
            String query = "select COUNT(ofdf.in_codigo_oficio) as NumOficiosRespuesta "+
                            "from orpro_oficio_cir_depen_facu ofdf "+
                            "WHERE ofdf.in_estado = 29";

            PreparedStatement pstm = cn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            exito = rs.getInt("NumOficiosRespuesta");
            rs.close();
            pstm.close();
        }
        catch(Exception e)
        {
            exito=0;
            System.out.println("error al obtener la cantidad de oficios tramitados totales: "+e);
        }
       finally
       {
            try
            {
                cn.close();
            }
            catch ( Exception ex)
            {
                System.out.println("finally - error al obtener la cantidad de oficios tramitados totales: "+ex);
            }
       }
       return exito;
    }

    public orpro_oficio_circular getOficioParaExportarPDF(int in_codigo_oficio,int in_cod_fac_dep,String ch_tipo_fac_dep)
    {
        orpro_oficio_circular obj = new orpro_oficio_circular();
        try
        {
            cn = ds.getConnection();
            String sql = "SELECT "+
                        "ofdf.in_codigo_oficio, "+
                        "of.ch_codigo_oficio, "+
                        "of.in_usuario_emisor, "+
                        "ofdf.in_codigo_usuario as in_usuario_receptor, "+
                        "ofdf.dt_fecha, "+
                        "of.in_cod_fac_dep, "+
                        "of.ch_tipo_fac_dep, "+
                        "of.vc_ciudad, "+
                        "of.vc_nombre_anio, "+
                        "of.vc_cuerpo_doc "+
                        "FROM orpro_oficio_cir_depen_facu ofdf "+
                        "inner join ORPRO_OFICIO_CIRCULAR of on of.in_codigo_oficio = ofdf.in_codigo_oficio "+
                        "WHERE ofdf.CH_TIPO_DEPEND_FACU = ? "+
                        "and ofdf.IN_CODIGO_DEPEN_FACU = ? and ofdf.in_codigo_oficio = ?";
            PreparedStatement pstm = cn.prepareStatement(sql);
             pstm.setString(1,ch_tipo_fac_dep);
            pstm.setInt(2,in_cod_fac_dep);
             pstm.setInt(3,in_codigo_oficio);
            ResultSet rs =  pstm.executeQuery();

            while( rs.next() )
            {
                obj.setIn_codigo_oficio(rs.getInt("in_codigo_oficio"));
                obj.setCh_codigo_oficio(rs.getString("ch_codigo_oficio"));
                obj.setIn_codigo_usuario(rs.getInt("in_usuario_receptor"));
                obj.setDt_fecha(rs.getDate("dt_fecha").toString());
                obj.setVc_ciudad(rs.getString("vc_ciudad"));
                obj.setVc_nombre_anio(rs.getString("vc_nombre_anio"));
                obj.setVc_cuerpo_doc(rs.getString("vc_cuerpo_doc"));
                obj.setIn_usuario_emisor(rs.getInt("in_usuario_emisor"));
                obj.setCh_tipo_fac_dep(rs.getString("ch_tipo_fac_dep") );
                obj.setIn_cod_fac_dep(rs.getInt("in_cod_fac_dep"));
            }
            pstm.close();
        }
        catch(Exception e)
        {
            System.out.println("Error al obtener el oficio segun el ID para modificar: "+e);
        }
        finally
        {
            try
            { cn.close(); }
            catch ( Exception ex)
            { System.out.println("Error al obtener el oficio segun el ID para modificar: "+ex);}
        }
        return obj;
    }




}

