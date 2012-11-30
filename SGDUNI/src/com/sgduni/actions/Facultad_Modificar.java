package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.dao.orgen_ta_organo_DAO;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orgen_ta_organo;
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
 * @author marco
 */
public class Facultad_Modificar extends org.apache.struts.action.Action
{
    private final static String SUCCESS = "success";
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        // System.out.println("INICIO EL ACTION");
        HttpSession sesion = request.getSession();
        String nombre = sesion.getAttribute("xnomus").toString();
        String id = sesion.getAttribute("xid").toString();
        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_facultad_DAO dao = new orgen_ta_facultad_DAO(dataSource);
        orgen_ta_facultad objForm = (orgen_ta_facultad)form;
        boolean estado =  dao.modificarFacultad(objForm,nombre,id);
        //System.out.println("MODIFICO EL REGISTRO");
        if(estado == true){
            objForm.setMensaje("Facultad Modificado Correctamente");
        }
        else {
            objForm.setMensaje("Error! ocurrio un error al modificar el facultad");
        }
        orgen_ta_organo_DAO daoOrg = new orgen_ta_organo_DAO(dataSource);
        ArrayList<orgen_ta_organo> organos = daoOrg.getOrganosActivos();
        request.setAttribute("organos",organos);
        //System.out.println("FIN DEL ACTION");
        return mapping.findForward("modificar");
    }
}
