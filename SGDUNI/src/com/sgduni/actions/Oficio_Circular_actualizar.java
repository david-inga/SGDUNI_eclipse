package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.forms.orpro_oficio_circular;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author Gerald
 */
public class Oficio_Circular_actualizar extends org.apache.struts.action.Action {
    
   
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
       HttpSession session=request.getSession();
       int codigo =Integer.parseInt( request.getParameter("codOfc") );
       int id_fac_dep = Integer.parseInt(session.getAttribute("xiddepen_facul").toString());
       String tipo_fac_dep = session.getAttribute("xtipodepen_facul").toString();

       DataSource dataSource = getDataSource(request, "DSconnection");
       orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);
       orgen_ta_usuario_DAO daoUsu = new orgen_ta_usuario_DAO(dataSource);

       ArrayList<orgen_ta_usuario> usuariosOCDO = daoUsu.getListaUsuarioOCDO(id_fac_dep,tipo_fac_dep);
       orpro_oficio_circular oficio = dao.getOficioParaModificar(codigo);

       request.setAttribute("oficio",oficio);
       request.setAttribute("usuariosOCDO", usuariosOCDO);
       return mapping.findForward("modificarOficio");
    }
}
