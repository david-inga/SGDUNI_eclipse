/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.actions;
import com.sgduni.forms.orgen_ta_configura_flujo;
import com.sgduni.dao.orgen_ta_usuario_conf_flujo_DAO;

import com.sgduni.forms.orgen_ta_usuario_lista;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class UsuarioConfigFlujoGuardar extends org.apache.struts.action.Action
{
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DataSource dataSource = getDataSource(request, "DSconnection");

        //dao        
        orgen_ta_usuario_conf_flujo_DAO daoConf=new orgen_ta_usuario_conf_flujo_DAO(dataSource);
        orgen_ta_configura_flujo daoMens=new orgen_ta_configura_flujo();

        //variables
        String idUsu=(request.getParameter("in_codigo_usuario")!=null && request.getParameter("in_codigo_usuario")!="0")?request.getParameter("in_codigo_usuario"):"0";

        String numListConfFl=(request.getParameter("txtnumitem_confg_flujo")!=null && request.getParameter("txtnumitem_confg_flujo")!="0")?request.getParameter("txtnumitem_confg_flujo"):"0";
        String campo_in_codigo_cong_="in_codigo_cong_";
        int error_d=0;

        //
        daoConf.eliminarUsuarioConfFlujo(Integer.parseInt(idUsu));//Eliminamos
        for(int i=1;i<=Integer.parseInt(numListConfFl);i++)
        {
            String campo_c=request.getParameter(campo_in_codigo_cong_+i);
            if(campo_c!=null){
                if(!daoConf.guardarUsuarioConfFlujo(Integer.parseInt(idUsu),Integer.parseInt(campo_c)))
                   error_d++;
            }
        }        
        response.sendRedirect("usuarioConfigFlujoNuevo.uni?coduser="+idUsu+"&err="+error_d);
        return null;//mapping.findForward("nuevo");
    }
}
