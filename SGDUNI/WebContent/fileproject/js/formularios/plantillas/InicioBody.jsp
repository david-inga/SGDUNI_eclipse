<%-- 
    Document   : InicioBody
    Created on : 09-dic-2011, 14:41:35
    Author     : Sistemas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UNI</title>
        <script language="javascript" type="text/javascript" src="fileproject/js/jsLibrary.js"></script>
            <!-- Arquivos utilizados pelo jQuery lightBox plugin -->
            <script type="text/javascript" src="fileproject/js/jquery-1.4.2.min.js"></script>
            <%
            /*
            <script type="text/javascript" src="fileproject/js/jquery.lightbox-0.5.js"></script>
            <link rel="stylesheet" type="text/css" href="fileproject/css/jquery.lightbox-0.5.css" media="screen" />
 *          */
            %>
            <!-- / fim dos arquivos utilizados pelo jQuery lightBox plugin -->
           <script language="javascript" type="text/javascript" src="fileproject/ajax/objAjax.js"></script>          
           <script language="javascript" type="text/javascript" src="fileproject/js/formularios/jsIndex.js"></script>
          <link rel="shortcut icon" href="fileproject/icono/favicon.ico" />
          <link rel="icon" type="image/gif" href="fileproject/icono/animated_favicon1.gif"/>    
          <link href="fileproject/css/cssGlobal.css" rel="stylesheet" type="text/css" />
          <link href="fileproject/css/cssForm.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <tiles:insert attribute="BodyTop"></tiles:insert>
        <tiles:insert attribute="BodyCenter"></tiles:insert>
        <tiles:insert attribute="BodyBottom"></tiles:insert>        
    </body>
</html:html>
