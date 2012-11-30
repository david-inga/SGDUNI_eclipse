package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sgduni.dao.orpro_oficio_circular_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.forms.orpro_oficio_circular;
import java.util.ArrayList;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
/**
 * @author Sistemas
 */
public class Oficio_Circular_Guardar extends org.apache.struts.action.Action
{        
    private final static String GUARDAR = "guardar";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
        HttpSession session=request.getSession();
        int idUser=Integer.parseInt(session.getAttribute("xid").toString());

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);
        orpro_oficio_circular objForm = (orpro_oficio_circular)form;

        if(dao.guardarOficio(objForm, idUser))
        {

           objForm.setMensaje("Oficio Guardado Correctamente");

           orpro_oficio_circular oficio = dao.getCodigoDelOficio(objForm.getCh_codigo_oficio());

           //DAO
           orgen_ta_dependencia_DAO daoDep = new orgen_ta_dependencia_DAO(dataSource);
           orgen_ta_facultad_DAO daoFac=new orgen_ta_facultad_DAO(dataSource);

           //array
           ArrayList<orgen_ta_dependencia> listaDependencia = daoDep.getDependencias();
           ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultades();

           request.setAttribute("dependencias", listaDependencia);
           request.setAttribute("facultad", listaFacultad);
           request.setAttribute("emisor", objForm.getIn_usuario_emisor());
           request.setAttribute("mensaje_de_exito", "");
           request.setAttribute("in_codigo_oficio", oficio.getIn_codigo_oficio());
           request.setAttribute("ch_codigo_oficio", oficio.getCh_codigo_oficio());
        }
        else
        {
           //objForm.setMensaje("Error! lo sentimos pero no se pudo guardar el Oficio");
           request.setAttribute("mensaje_de_exito", "Error! lo sentimos pero no se pudo guardar el Oficio");
        }
        
        return mapping.findForward(GUARDAR);
    }
}
