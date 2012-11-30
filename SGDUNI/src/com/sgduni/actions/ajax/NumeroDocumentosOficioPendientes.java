package com.sgduni.actions.ajax;

import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.dao.orpro_ta_estructura_organica_DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author marco
 */

public class NumeroDocumentosOficioPendientes extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //Header
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        DataSource dataSource = getDataSource(request, "DSconnection");

        //Variables Session
        HttpSession sesion = request.getSession();

        PrintWriter writer;
        try
        {
           writer =response.getWriter();
           if(sesion.getAttribute("xrol") != null)
           {
                String Rol=(String)sesion.getAttribute("xrol");
                Rol=Rol.trim().toUpperCase();

                String idDepFacu = sesion.getAttribute("xiddepen_facul").toString();
                String tipDepFac = (String)sesion.getAttribute("xtipodepen_facul").toString();
                //sesion.invalidate();//

                //variables
                int numOficiosGuardados=0;
                int numOficiosEnviadosDependencias=0;
                int numOficiosAprobadosporJefeOCDO=0;
                int numOficioEnviadoJefeOCDO = 0;
                int numHistorialOficiosEnviados = 0;
                int numHistorialOficioTramitado =0;
                int numHistorialOficioRespuesta = 0;
                int numOficiosRevisados =0;
                int numEstrucOrgTramitando=0;
                int numEstrucOrgAprobados =0;
                int numEstrucOrgHistorial =0;

  

                   orpro_oficio_circular_DAO dao=new orpro_oficio_circular_DAO(dataSource);
                   orpro_ta_estructura_organica_DAO daoEstruc=new orpro_ta_estructura_organica_DAO(dataSource);

                   if(Rol.equals("ROL02") || Rol.equals("ROL03"))
                   {//rol usuario - visualizacion de pendientes para este nivel
                      numOficiosGuardados       = dao.getCountOficiosGuardados(Integer.parseInt(idDepFacu),tipDepFac);
                      numHistorialOficioTramitado = dao.getTotalOficioTramitadoSegunDependencia(Integer.parseInt(idDepFacu),tipDepFac);
                      numOficiosEnviadosDependencias = dao.getOficiosPendientesSegunUsuarios(Integer.parseInt(idDepFacu),tipDepFac);
                      numHistorialOficioRespuesta = dao.getTotalOficioRespuestaSegunDependencia(Integer.parseInt(idDepFacu), tipDepFac);
                      // Estructura Organica
                      numEstrucOrgTramitando  = daoEstruc.getCountEstructurasPendientesPorUsuarios(Integer.parseInt(idDepFacu), tipDepFac);//YA ESTA
                      numEstrucOrgAprobados = daoEstruc.getCountEstructurasAprobadasPorUsuarios(Integer.parseInt(idDepFacu), tipDepFac);//YA
                      numEstrucOrgHistorial = daoEstruc.getCountEstructurasHistorialPorUsuarios(Integer.parseInt(idDepFacu), tipDepFac);//YA
                     //System.out.println(" numEstrucOrgAprobados = "+numEstrucOrgAprobados);
                   }
                   else if(Rol.equals("ROL01"))
                   {//ROL OCDO - visualizacion de pendientes para este nivel
                      numOficiosAprobadosporJefeOCDO = dao.getCountOficiosAprobadosPorJefeOCDO(Integer.parseInt(idDepFacu),tipDepFac);
                      numOficiosEnviadosDependencias = dao.getCountOficiosEnviadosADependencias();
                      numOficiosGuardados            = dao.getCountOficiosGuardados(Integer.parseInt(idDepFacu),tipDepFac);
                      numOficioEnviadoJefeOCDO       = dao.getCountOficiosEnviadosAlJefeOCDO(Integer.parseInt(idDepFacu),tipDepFac);
                      numHistorialOficiosEnviados    = dao.getoficiosTotalesHistorialEnviados();
                      numHistorialOficioTramitado    = dao.getTotalOficioTramitado();
                      numHistorialOficioRespuesta = dao.getTotalOficioRespuesta();
                      numOficiosRevisados = dao.getTotalOficioRevisados();
                      // Estructura Organica
                      numEstrucOrgTramitando = daoEstruc.getCountEstructurasPendientes();//para revision ocdo YA ESTA
                      numEstrucOrgAprobados  = daoEstruc.getCountEstructurasAprobadas();
                      numEstrucOrgHistorial  = daoEstruc.getCountEstructurasHistorial();
                   }
                   
                   writer.print("[{ 'numOficiosRevisados':'"+numOficiosRevisados+"', 'numHistorialOficioRespuesta':'"+numHistorialOficioRespuesta+"', 'numHistorialOficioTramitrado':'"+numHistorialOficioTramitado+"' , 'numOficiosAprobadosporJefeOCDO':'"+numOficiosAprobadosporJefeOCDO+"', 'numOficioEnviadoJefeOCDO':'"+numOficioEnviadoJefeOCDO+"' ,'numHistorialOficiosEnviados' : '"+numHistorialOficiosEnviados+"','numOficiosEnviadosDependencias':'"+numOficiosEnviadosDependencias+"' , 'numOficiosGuardados':'"+numOficiosGuardados+"' , 'numEstrucOrg': '"+numEstrucOrgTramitando+"', 'numEstrucOrgAprobados':'"+numEstrucOrgAprobados+"', 'numEstrucOrgHistorial':'"+numEstrucOrgHistorial+"'}]");//JSON
           }
           else
           {
                writer.print("50");//SESSION NO EXIXTE
           }
           writer.flush();
           writer.close();
        }
        catch (IOException ex)
        {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(NumeroDocumentosOficioPendientes.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
                //mapping.findForward(SUCCESS);
    }
}
