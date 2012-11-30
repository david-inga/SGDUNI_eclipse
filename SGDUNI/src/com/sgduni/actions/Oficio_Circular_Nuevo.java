package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.forms.orgen_ta_usuario;
import com.sgduni.utilitarios.ValidarCadena;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 * @author Sistemas
 */

public class Oficio_Circular_Nuevo extends org.apache.struts.action.Action
{        
    private final static String NUEVO = "nuevo";

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception
    {
        System.out.println("inicio el nuevo oficio");
        HttpSession session=request.getSession();
        int id_fac_dep = Integer.parseInt(session.getAttribute("xiddepen_facul").toString());
        String tipo_fac_dep = session.getAttribute("xtipodepen_facul").toString();

        //Enumeration parametros = request.getp.get();
        
        int in_cod_oficio_en_tramite = 0;

        //if( parametros.hasMoreElements() )
        //{
            //System.out.println("hay parametros");
         in_cod_oficio_en_tramite = ( request.getParameter("in_cod_oficio")!=null && ValidarCadena.isNumeric(request.getParameter("in_cod_oficio").trim() ) == true   ) ? Integer.parseInt(request.getParameter("in_cod_oficio")) : 0;
         System.out.println("el codigo en tramite es : "+in_cod_oficio_en_tramite);
        //}
        //else
        //{
           //System.out.println("no hay parametros");
          // in_cod_oficio_en_tramite = 0;
        //}


        
       DataSource dataSource = getDataSource(request, "DSconnection");
       //orpro_oficio_circular_DAO dao = new orpro_oficio_circular_DAO(dataSource);
       orgen_ta_usuario_DAO daoUsu = new orgen_ta_usuario_DAO(dataSource);

       ArrayList<orgen_ta_usuario> usuariosOCDO = daoUsu.getListaUsuarioOCDO(id_fac_dep,tipo_fac_dep);
       //String nuevo_codigo_generado = dao.getCodigoGenerado().trim();

       //request.setAttribute("nuevo_codigo_generado",nuevo_codigo_generado);
       request.setAttribute("in_codigo_oficio_en_tramite", in_cod_oficio_en_tramite);
       request.setAttribute("usuariosOCDO", usuariosOCDO);
       return mapping.findForward(NUEVO);
    }
}
