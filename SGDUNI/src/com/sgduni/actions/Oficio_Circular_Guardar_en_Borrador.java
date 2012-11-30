/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Gerald
 */
public class Oficio_Circular_Guardar_en_Borrador extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String GUARDAR = "solo_guardar";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession();
        int idUser     = Integer.parseInt(session.getAttribute("xid").toString());
        int id_fac_dep = Integer.parseInt(session.getAttribute("xiddepen_facul").toString());
        String tipo_fac_dep = session.getAttribute("xtipodepen_facul").toString();

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);
        orgen_ta_usuario_DAO daoUsu = new orgen_ta_usuario_DAO(dataSource);
        orpro_oficio_circular objForm = (orpro_oficio_circular)form;
        objForm.setIn_codigo_estado(26);
        objForm.setIn_cod_fac_dep(id_fac_dep);
        objForm.setCh_tipo_fac_dep(tipo_fac_dep);

        if(dao.guardarOficio(objForm, idUser))
        {
           objForm.setMensaje("El Oficio fue Guardado en el Borrador");
        }
        else
        {
           objForm.setMensaje("Error! lo sentimos pero no se puede guardar el Oficio");
           request.setAttribute("mensaje_de_exito", "Error! lo sentimos pero no se puede guardar el Oficio");
        }

       ArrayList<orgen_ta_usuario> usuariosOCDO = daoUsu.getListaUsuarioOCDO(id_fac_dep,tipo_fac_dep);
       String nuevo_codigo_generado = dao.getCodigoGenerado().trim();

       request.setAttribute("nuevo_codigo_generado",nuevo_codigo_generado);
       request.setAttribute("usuariosOCDO", usuariosOCDO);

        return mapping.findForward(GUARDAR);
    }
}
