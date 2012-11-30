package com.sgduni.actions.ajax;

import com.sgduni.dao.orgen_ta_funcionalidad_DAO;
import com.sgduni.forms.orgen_ta_funcionalidad;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Sistemas
 */
public class Menu_Funcionalidad_Body_Center extends org.apache.struts.action.Action
{

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession sesion = request.getSession(true);
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");

        String codRol = sesion.getAttribute("xrol").toString();        

        DataSource dataSource = getDataSource(request, "DSconnection");
        orgen_ta_funcionalidad_DAO daoFun=new orgen_ta_funcionalidad_DAO(dataSource);
        ArrayList<orgen_ta_funcionalidad> lista=daoFun.getFuncionalidadSegunRolMenu(codRol);
        PrintWriter writer;
        try {
           writer =response.getWriter();
           String jsonTEX="[";
           int numItem=0;
           for(orgen_ta_funcionalidad func:lista){
              String nomFuncionalidad =new String(func.getVc_nombre().getBytes("UTF-8"),"ISO-8859-1");
              jsonTEX+="{ \"enlace\": \""+func.getVc_url_funcionalidad()+"\", \"nombre\": \""+nomFuncionalidad+"\", \"icono\": \""+func.getVc_icono()+"\"}";
              if((numItem + 1)<lista.size())
                 jsonTEX+=",";//Al final del JSON ya no se pone COMAS
               numItem++;
           }
           jsonTEX+="]";
           writer.print(jsonTEX);//Imprimimos el objeto JSON
             writer.flush();
             writer.close();
        } catch (IOException ex) {
            writer =response.getWriter();
            writer.print("0");
            writer.flush();
            writer.close();
            Logger.getLogger(Menu_Funcionalidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
