package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_estado_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orpro_ta_tupa_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_estado;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_ta_tupa;
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
 * @author marco
 */
public class Tupa_insertar extends org.apache.struts.action.Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        System.out.print("accion insertar tupa iniciado");


        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id =sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_ta_tupa_DAO daoCap = new orpro_ta_tupa_DAO(dataSource);
        orpro_ta_tupa objForm = (orpro_ta_tupa)form;
        boolean estado = daoCap.guardarTupa(objForm, nombre, id);
        String ShowHideBton="0";//CERO POR DEFECTO, SIGNIFICA OCULTAR
        if(estado == true)
        {
            ShowHideBton="1";//SIGNIFICA VER
            objForm.setMensaje("Tupa registrado correctamente, Ahora agrege los detalles del TUPA Creado");
        }
        else
        {
            objForm.setMensaje("Error! ocurrio un error al registrar el tupa");
        }

        request.setAttribute("codTupa", daoCap.getIn_codigo_tupa().toString().trim() );

        orgen_ta_dependencia_DAO daoDepend = new orgen_ta_dependencia_DAO(dataSource);
        ArrayList<orgen_ta_dependencia> depend = daoDepend.getDependenciasActivas();
        request.setAttribute("dependencias",depend);

        orgen_ta_facultad_DAO daoFac = new orgen_ta_facultad_DAO(dataSource);
        ArrayList<orgen_ta_facultad> facultades = daoFac.getFacultades();
        request.setAttribute("facultades",facultades);

        orgen_ta_estado_DAO daoEstado = new  orgen_ta_estado_DAO(dataSource);
        ArrayList<orgen_ta_estado> estados = daoEstado.getEstados();
        request.setAttribute("estados",estados);

        System.out.print("accion insertar tupa finalizado");
        request.setAttribute("ShowHideBton",ShowHideBton);
        System.out.println("x:"+ShowHideBton);
        return mapping.findForward("registrarTupa");
    }
}

