package com.sgduni.actions;

import com.sgduni.dao.orgen_ta_cargo_usuario_DAO;
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
public class Usuario_asignar_Facultad_Dependencia_Guardar extends org.apache.struts.action.Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");
        //dao        
        orgen_ta_cargo_usuario_DAO daoConf=new orgen_ta_cargo_usuario_DAO(dataSource);

        //
        String idUsu=(request.getParameter("in_codigo_usuario")!=null && request.getParameter("in_codigo_usuario")!="0")?request.getParameter("in_codigo_usuario"):"0";
        String rdtipofacdep=request.getParameter("rdtipofacdep");
        String txtidfacdep=request.getParameter("txtidfacdep");
        int error_d=0;
        //
       if(!idUsu.equals("0"))
        {
          daoConf.eliminarUsuarioCargoEstructural(Integer.parseInt(idUsu));//Eliminamos
          if(!daoConf.guardarUsuarioCargoEstructural(Integer.parseInt(idUsu),Integer.parseInt(txtidfacdep),rdtipofacdep))
                   error_d++;        
        }else{
           error_d++;
        }
        response.sendRedirect("seleccionarEntidadParaUsuario.uni?coduser="+idUsu+"&err="+error_d);
        return null;//mapping.findForward("nuevo");
    }
}

