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
 * @author pc
 */
public class ROF_guardar_parte3 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "irFormularioTres";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        System.out.println("ROF 3");
        DataSource dataSource = getDataSource(request, "DSconnection");

        HttpSession sesion = request.getSession();
        int idVersion =Integer.parseInt( sesion.getAttribute("xidVersionROF").toString() );


            orpro_ta_rof_DAO daoRof = new orpro_ta_rof_DAO(dataSource);
            
            orpro_ta_rof objForm = (orpro_ta_rof)form;
//            System.out.println("id = "+objForm.getIn_codigo_rof() );
//            System.out.println("sd = "+objForm.getVc_relaciones_interinstitucionales() );
//            System.out.println("sdsd = "+objForm.getVc_disposiciones_finales() );
                if(daoRof.guardarTerceraParteRof(objForm,idVersion))
                {
                   request.setAttribute("mensaje_de_exito", "La Estructura Organica, Las Relaciones<br/>  interinstitucionales y las dispocisiones finales  <br/>Fueron Guardados Correctamente,<br/> Gracias!");
                }
                else
                {
                   request.setAttribute("mensaje_de_exito", "Error! lo sentimos pero no se pudo guardar el ROF, <br/> porfavor intente denuevo!");
                }

        return mapping.findForward(SUCCESS);
    }
}
