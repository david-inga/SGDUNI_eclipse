package com.sgduni.actions;

import com.sgduni.dao.orpro_detalle_objetivo_direc_DAO;
import com.sgduni.forms.orpro_detalle_objetivo_direc;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/*
 * @author marco
 */
public class Directivas_listarObjetivos extends org.apache.struts.action.Action
{   

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String codDirec = request.getParameter("cod_dirc");
        System.out.println("codid = "+codDirec);
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_detalle_objetivo_direc_DAO dao = new orpro_detalle_objetivo_direc_DAO(dataSource);
        ArrayList<orpro_detalle_objetivo_direc> objetivos = dao.getObjetivosDir(codDirec);
        // System.out.println( "DESCRIPCION :"+objetivos.get(1).getVc_descripcion() );
        request.setAttribute("objetivos",objetivos);
        return mapping.findForward("listado");
    }
}
