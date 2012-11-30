package com.sgduni.actions;

import com.sgduni.dao.orpro_ta_rof_DAO;
import com.sgduni.forms.orpro_ta_rof;
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
 * @author JMarcos
 */
public class ROF_guardar_parte1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irFormularioGuardarROFDOS";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        
            HttpSession session=request.getSession();
            int idUser = Integer.parseInt( session.getAttribute("xid").toString() );
            int idFACDepend = Integer.parseInt(session.getAttribute("xiddepen_facul").toString());
            String tipo_fac_depend = session.getAttribute("xtipodepen_facul").toString();

            DataSource dataSource = getDataSource(request, "DSconnection");

            orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);

            orpro_ta_rof objForm = (orpro_ta_rof)form;

            objForm.setIn_depend_fac(idFACDepend);
            objForm.setCh_tipo_depend_fac(tipo_fac_depend);

            if(daoRof.guardarPrimeraParteRof(objForm,idUser))
            {
               orpro_ta_rof rof = daoRof.getIDDelROF(objForm.getCh_codigo_rof());
               int id_version_rof = daoRof.getIDVERSIONDelROF(rof.getIn_codigo_rof());

               //request.setAttribute("idversion", id_version_rof);

               session.setAttribute("xidVersionROF", id_version_rof);
               // System.out.println("el id versino es "+id_version_rof);
               request.setAttribute("rof", rof);
               request.setAttribute("mensaje_de_exito", "La Introducción , Naturaleza y Finalidad <br/> Fueron Guardados Correctamente,<br/> ahora prosiga porfavor!");
            }
            else
            {
               request.setAttribute("mensaje_de_exito", "Error! lo sentimos pero no se pudo guardar el ROF, <br/> porfavor intente denuevo!");
            }

        return mapping.findForward(SUCCESS);
    }  
}
