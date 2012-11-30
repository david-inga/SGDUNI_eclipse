package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_cargo_usuario_DAO;
import com.sgduni.dao.orgen_ta_dependencia_DAO;
import com.sgduni.dao.orgen_ta_facultad_DAO;
import com.sgduni.forms.orgen_ta_dependencia;
import com.sgduni.forms.orgen_ta_facultad;
import com.sgduni.dao.orgen_ta_usuario_DAO;
import com.sgduni.forms.orgen_ta_usuario_lista;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author marco
 */
public class Usuario_asignar_Facultad_Dependencia extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        //dao
        orgen_ta_usuario_DAO daoUsu=new orgen_ta_usuario_DAO(dataSource);        
        orgen_ta_dependencia_DAO dao = new orgen_ta_dependencia_DAO(dataSource);
        orgen_ta_facultad_DAO daoFac=new orgen_ta_facultad_DAO(dataSource);
        
        orgen_ta_cargo_usuario_DAO daoUsuarioCar=new orgen_ta_cargo_usuario_DAO(dataSource);

        //
        String codUsu=(request.getParameter("coduser")!=null && request.getParameter("coduser") != "0" )?request.getParameter("coduser"):"0";

        //Lista ARRAY
        ArrayList<orgen_ta_usuario_lista> usuarios=daoUsu.getUsuariosActivos();
        
        ArrayList<orgen_ta_dependencia> listaDependencia = dao.getDependenciasActivas();
        ArrayList<orgen_ta_facultad> listaFacultad = daoFac.getFacultadesActivas();

        int idDepenFacu=0;
        String tipDepenFacu="";
        if(daoUsuarioCar.buscarUsuarioCargoEstructural(Integer.parseInt(codUsu)))
        {
            idDepenFacu=daoUsuarioCar.getIn_codigo_depen_facu();
            tipDepenFacu=daoUsuarioCar.getTipo_depen_facu();
        }
        request.setAttribute("DepenFacuId", idDepenFacu);
        request.setAttribute("DepenFacuTip", tipDepenFacu);

        request.setAttribute("codUsu", codUsu);
        request.setAttribute("usuarios", usuarios);
        
        request.setAttribute("dependencias", listaDependencia);
        request.setAttribute("facultad", listaFacultad);
        return mapping.findForward("nuevo");
    }
}
