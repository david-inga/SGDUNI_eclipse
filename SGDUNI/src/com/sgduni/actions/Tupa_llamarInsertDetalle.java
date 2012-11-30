package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_procedimiento_DAO;
import com.sgduni.dao.orgen_ta_requisitos_DAO;
import com.sgduni.forms.orgen_ta_procedimiento;
import com.sgduni.forms.orgen_ta_requisitos;
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
public class Tupa_llamarInsertDetalle extends org.apache.struts.action.Action {
    

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        System.out.println("LLAMAR FORMULARIO PARA DETALLE TUPA");
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_procedimiento_DAO dao = new orgen_ta_procedimiento_DAO(dataSource);
        ArrayList<orgen_ta_procedimiento> obj = dao.getProcedimientosActivos();
        request.setAttribute("procedimientos",obj);
        
        orgen_ta_requisitos_DAO daoReq = new orgen_ta_requisitos_DAO(dataSource);
        ArrayList<orgen_ta_requisitos> objReq = daoReq.getRequisitosActivos();
        request.setAttribute("requisitos",objReq);

        String codTupa = request.getParameter("codTupa");
        request.setAttribute("codTupa", codTupa);

        String idTupa = request.getParameter("idTupa");
        request.setAttribute("idTupa", idTupa);

         System.out.println("COD TUPA : "+codTupa+" - ID TUPA : "+idTupa);
         System.out.println("FIN LLAMAR FORMULARIO PARA DETALLE TUPA");
         
        return mapping.findForward("registrarDetalleTupa");
    }
}
