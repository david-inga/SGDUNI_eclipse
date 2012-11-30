package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_tupa_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_estado;
import com.sgduni.forms.orgen_ta_facultad;
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
public class Tupa_llamarFormInsertar extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        System.out.println(" INICIO LA ACCINO DE LLAMAR FORMULARIO TUPA ");

        String ShowHideBton="0";
        
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_dependencia_DAO daoDepend = new orgen_ta_dependencia_DAO(dataSource);
        ArrayList<orgen_ta_dependencia> depend = daoDepend.getDependenciasActivas();
        request.setAttribute("dependencias",depend);

        orgen_ta_facultad_DAO daoFac = new orgen_ta_facultad_DAO(dataSource);
        ArrayList<orgen_ta_facultad> facultades = daoFac.getFacultades();
        request.setAttribute("facultades",facultades);

        orgen_ta_estado_DAO daoEstado = new  orgen_ta_estado_DAO(dataSource);
        ArrayList<orgen_ta_estado> estados = daoEstado.getEstados();
        request.setAttribute("estados",estados);

        orpro_ta_tupa_DAO daoTupa = new orpro_ta_tupa_DAO(dataSource);
        String codigo_generado = daoTupa.getCodigoGenerado();

        request.setAttribute("ShowHideBton",ShowHideBton);

        System.out.println("CODIGO GENERADO: "+codigo_generado);

        request.setAttribute("codigo_generado", codigo_generado);

        System.out.println(" FIn LA ACCION DE LLAMAR FORMULARIO TUPA ");

        return mapping.findForward("registrarTupa");
    }
}
