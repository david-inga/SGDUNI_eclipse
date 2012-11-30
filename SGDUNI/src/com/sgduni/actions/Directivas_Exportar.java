/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_directivas_DAO;
import com.sgduni.dao.orpro_detalle_objetivo_direc_DAO;
import com.sgduni.dao.orpro_detalle_normas_gen_DAO;
import com.sgduni.dao.orpro_detalle_base_legal_DAO;
import com.sgduni.dao.orpro_det_proc_direc_DAO;

import com.sgduni.forms.orpro_ta_directivas;
import com.sgduni.forms.orpro_detalle_objetivo_direc;
import com.sgduni.forms.orpro_detalle_normas_gen;
import com.sgduni.forms.orpro_detalle_base_legal;
import com.sgduni.forms.orpro_det_proc_direc;



import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Sistemas
 */
public class Directivas_Exportar extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        //Variables
        int inDire=Integer.parseInt(request.getParameter("iddire"));
        //Data Source
        DataSource dataSource = getDataSource(request, "DSconnection");
        
        //dao
        orpro_ta_directivas_DAO daoDirec=new orpro_ta_directivas_DAO(dataSource);
        orpro_ta_directivas objDirec= daoDirec.BuscarDirectiva(inDire);
        //
        orpro_detalle_objetivo_direc_DAO daoObj=new orpro_detalle_objetivo_direc_DAO(dataSource);
        ArrayList<orpro_detalle_objetivo_direc> lisObjetivos=daoObj.getObjetivosDir(String.valueOf(inDire));

        orpro_detalle_normas_gen_DAO daoNG=new orpro_detalle_normas_gen_DAO(dataSource);
        ArrayList<orpro_detalle_normas_gen> lisNormasGenerales = daoNG.getNormasGenDir(String.valueOf(inDire));

        orpro_detalle_base_legal_DAO daoBaseLegal = new orpro_detalle_base_legal_DAO(dataSource);
        ArrayList<orpro_detalle_base_legal> lisBasesLegales = daoBaseLegal.getBasesLegalesDir(String.valueOf(inDire));

        orpro_det_proc_direc_DAO daoProc = new orpro_det_proc_direc_DAO(dataSource);
        ArrayList<orpro_det_proc_direc> lisProcedimientos = daoProc.getProcedimientoDirExportar(String.valueOf(inDire));
        //System.out.println("Sa :"+lisObjetivos.get(1).getVc_descripcion());
        //enviar atributos
        request.setAttribute("objDirec", objDirec);
        request.setAttribute("lisObjetivos", lisObjetivos);
        request.setAttribute("lisNormasGenerales", lisNormasGenerales);
        request.setAttribute("lisBasesLegales", lisBasesLegales);
        request.setAttribute("lisProcedimientos", lisProcedimientos );
        return mapping.findForward("exportar");
    }
}
