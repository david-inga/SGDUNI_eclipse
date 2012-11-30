package com.sgduni.actions;

import com.sgduni.dao.orpro_detalle_proc_req_DAO;
import com.sgduni.forms.orpro_detalle_proc_req;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class Tupa_listaRequisitoSegunProc extends org.apache.struts.action.Action {
    

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String codTupa = request.getParameter("idTupa");
        //System.out.println("codid = "+codDirec);
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_detalle_proc_req_DAO dao = new orpro_detalle_proc_req_DAO(dataSource);
        ArrayList<orpro_detalle_proc_req> req = dao.getRequisitosSegunProcedimientoTUPA(codTupa);
        // System.out.println( "DESCRIPCION :"+objetivos.get(1).getVc_descripcion() );
        request.setAttribute("requisitos",req);
        return mapping.findForward("listado");
    }
}
