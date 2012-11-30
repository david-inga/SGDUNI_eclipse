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
 *
 * @author Programmer : Marco A. Estrella Cardenas
 * boton para la ocdo
 */
public class Oficio_Circular_Guardar_Aprobado extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private final static String GUARDAR = "guardadoyAprobadoOficio";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession();
        String rol     = session.getAttribute("xrol").toString().trim().toUpperCase();
        String cargo   = session.getAttribute("xcodcargo").toString().trim();
        int idUser     = Integer.parseInt(session.getAttribute("xid").toString());
        int id_fac_dep = Integer.parseInt(session.getAttribute("xiddepen_facul").toString());
        String tipo_fac_dep = session.getAttribute("xtipodepen_facul").toString();

        int in_codigo_oficio_en_tramite = Integer.parseInt(request.getParameter("in_codigo_oficio_en_tramite"));
        System.out.println("in_codigo_oficio_en_tramite = "+in_codigo_oficio_en_tramite);

        DataSource dataSource = getDataSource(request, "DSconnection");
        orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);
        orgen_ta_usuario_DAO daoUsu = new orgen_ta_usuario_DAO(dataSource);
        orpro_oficio_circular objForm = (orpro_oficio_circular)form;

        if(rol.equals("ROL01"))
        {
            if( cargo.equals("1"))
            {
              objForm.setIn_codigo_estado(42);
            }
        }
        else if(rol.equals("ROL02"))
        {
            objForm.setIn_codigo_estado(43);
        }

        System.out.println("el estado es : "+objForm.getIn_codigo_estado());

        
        objForm.setIn_cod_fac_dep(id_fac_dep);
        objForm.setCh_tipo_fac_dep(tipo_fac_dep);

        if(dao.guardarOficio(objForm, idUser))
        {
            if(rol.equals("ROL02"))
            {
               //cambiando a estado : revisado por el usuario
                if(in_codigo_oficio_en_tramite > 0)
                {
                 dao.cambiarEstadoOficioEnTramite( 29 , in_codigo_oficio_en_tramite , id_fac_dep, tipo_fac_dep);
                }
           }
           objForm.setMensaje("El Oficio fue guardado y aprobado correctamente");
        }
        else
        {
           objForm.setMensaje("Error! lo sentimos, no se pudo guardar y/o aprobar el oficio ");
           request.setAttribute("mensaje_de_exito", "Error! lo sentimos, no se pudo guardar y/o aprobar el oficio ");
        }

       ArrayList<orgen_ta_usuario> usuariosOCDO = daoUsu.getListaUsuarioOCDO(id_fac_dep,tipo_fac_dep);
       String nuevo_codigo_generado = dao.getCodigoGenerado().trim();

       request.setAttribute("nuevo_codigo_generado",nuevo_codigo_generado);
       request.setAttribute("usuariosOCDO", usuariosOCDO);

        return mapping.findForward(GUARDAR);
    }
}
