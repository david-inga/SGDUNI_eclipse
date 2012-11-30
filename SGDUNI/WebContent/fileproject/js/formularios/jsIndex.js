/***********************
PARA LOS AVISO GENERAL
************************/
var CountDocPen=1;

$(document).ready(
   function(){
	 /************************************
	 INI: PARA LA VISUALIZACION DE LOS MENSAJES - bean:write
	 *************************************/  
	 if($(".textAviso").length)
	 {
		  var objAvios=$(".textAviso").html();
		  objAvios=objAvios.replace(/\n/gi,'');objAvios=objAvios.replace(/\t/gi,'');objAvios=trim(objAvios);//Limpiamos los saltos de linea y los espacios en blanco
		  if(objAvios==""){
			 $(".textAviso").css("display","none");
		  }else{
			 $(".textAviso").css("display","block");
		  }
	 }
	 /************************************
	 FIN: 
	 *************************************/

    /************************************
	 INI: VISUALIZAR SI EXISTE DOCUMENTOOS PENDIENTES Y/O TRAMITES
	 *************************************/       
           fng_doc_pendientes();
    /************************************
	 FIN:
	 *************************************/

     /**************************
      PARA EL MENU DESPLEGABLE
     ***************************/
       $(".li_menu_desplegable_top").hover(function(){
           $(this).find("ul").show();          
       },function(){
           $(this).find("ul").hide();
       });
});
/**/
function fng_seac(){
    var obses=document.getElementById("ixsact");
    if(trim(obses.value)!="" && Number(trim(obses.value))>0)
      return true;
    else
      return false;
}
/**/
var fsto="";
var fstoobl="";
function fng_doc_pendientes()
{   
    
   if(CountDocPen==1)
   {  fnl_obj_doc();
      fsto=setInterval("fng_doc_pendientes()",1000);//No cambiar
   }else{
      if(CountDocPen==2)
      {  setTimeout("fng_doc_pendientes()",1000);//No cambiar
         clearInterval(fsto);
      }else{        
         fstoobl=setInterval("fnl_obj_doc()",600000);//Cada 10 minutos
         //1000===>1Sg //60 000=====>1Min
      }
   }
   CountDocPen++;
}
function fnl_obj_doc()
{   
    if(fng_seac())
        {           
            $.ajax({
						type: 'post',
                        url:'/SGDUNI/numeroOficiosPendientes.uni',
						dataType: 'html',
						success: function(data)
                        {
                           fng_ussecactv(data);
                            var ObjJSON=eval(data);
                            var NumHistorialOficiosEnviados=ObjJSON[0].numHistorialOficiosEnviados;
                            var NumOficiosEnviadosDependencias = ObjJSON[0].numOficiosEnviadosDependencias;
                            var NumOficiosGuardados = ObjJSON[0].numOficiosGuardados;
                            var NumOficioEnviadoJefeOCDO = ObjJSON[0].numOficioEnviadoJefeOCDO;
                            var NumOficiosAprobadosporJefeOCDO = ObjJSON[0].numOficiosAprobadosporJefeOCDO;
                            var NumHistorialOficioTramitrado = ObjJSON[0].numHistorialOficioTramitrado;
                            var NumHistorialOficioRespuesta = ObjJSON[0].numHistorialOficioRespuesta;
                            var NumOficiosRevisados = ObjJSON[0].numOficiosRevisados;
                            var NumEstrucOrg=ObjJSON[0].numEstrucOrg;
                            var NumEstrucOrgAprobados = ObjJSON[0].numEstrucOrgAprobados;
                            var NumEstrucOrgHistorial = ObjJSON[0].numEstrucOrgHistorial;


                            //OFICIO CIRCULAR
                            if(Number(NumOficiosRevisados) > 0)
                                $("#dvg_oficios_revisados").html("("+NumOficiosRevisados+")");
                            if(Number(NumHistorialOficioRespuesta) > 0)
                                $("#dvg_historial_respuestas_p").html("("+NumHistorialOficioRespuesta+")");
                            if(Number(NumHistorialOficioTramitrado) > 0)
                                $("#dvg_historial_tramitados_p").html("("+NumHistorialOficioTramitrado+")");
                            if(Number(NumOficiosAprobadosporJefeOCDO) > 0)
                                $("#dvg_aprobados_por_jefe").html("("+NumOficiosAprobadosporJefeOCDO+")");
                            if(Number(NumOficioEnviadoJefeOCDO) > 0)
                                $("#dvg_enviado_jefe_ocdo").html("("+NumOficioEnviadoJefeOCDO+")");
                            if(Number(NumOficiosGuardados) > 0)
                                $("#dvg_borrador").html("("+NumOficiosGuardados+")");
                            if(Number(NumOficiosEnviadosDependencias) > 0)
                                $("#dvg_enviado_dependencias").html("("+NumOficiosEnviadosDependencias+")");
                            if(Number(NumHistorialOficiosEnviados)>0)
                                $("#dvg_historial_enviados_p").html("("+NumHistorialOficiosEnviados+")");

                            //ESTRUCTURA ORGANICA
                            if(Number(NumEstrucOrg)>0)
                                $("#dvg_estructuras_org_p").html("("+NumEstrucOrg+")");
                            if(Number(NumEstrucOrgHistorial) > 0)
                                $("#dvg_estr_historial_org_p").html("("+NumEstrucOrgHistorial+")");
                            if(Number(NumEstrucOrgAprobados) > 0)
                                $("#dvg_estr_aprob_org_p").html("("+NumEstrucOrgAprobados+")");

						},
						error: function(dato){
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							//precargaALCON("",true,0,"center");
							return false;
						}

					});
        }
}
/**/
/***********************
ROLES DE LOS USUARIOS
************************/
   function fnl_lisRoles()
   {         
               var contLis=document.getElementById("dvl_lista_roles_user");//Contenedor
               if(!contLis)
               {//Solo carga la primera vez
                        precargaALCON("Cargando...",true,1,"center");
                            $.ajax({
                                type: 'post',
                                url:'/SGDUNI/listarRolesUserLogeado.uni',
                                dataType: 'json',
                                success: function(data)
                                {
                                    fnl_objListaRolesUser(data);
                                    precargaALCON("",true,0,"center");
                                },
                                error: function(dato){
                                    alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                                    precargaALCON("",true,0,"center");
                                    return false;
                                }

                            });
               }else{
                    if(contLis.style.display=="none")
                    {
                       ShowHide("dvl_lista_roles_user",true,1);
                    }
                    else
                    {
                      ShowHide("dvl_lista_roles_user",true,0);
                    }
               }
	   return false;
   } 
   //
  function fnl_objListaRolesUser(data){
     var cont=document.getElementById("idMenRoles");
     cont.style.position="relative";
     if(!document.getElementById("dvl_lista_roles_user"))
       {
          var DivHtml=document.createElement("div");
          var ContHtml="";
             ContHtml+='<ul class="lisBoxStyle2">'
             if(data)
                 {
                     if(data.length>0)
                     {
                         $.each(data, function(i,item){
                           ContHtml+='<li>'                           
                             ContHtml+='<a href="protocolosUsuarios.uni?rol='+item.id+'">'
                               ContHtml+=''+item.nombre
                             ContHtml+='</a>'
                           ContHtml+='</li>'
                         });
                     }else{
                           ContHtml+='<li>'                           
                             ContHtml+='<a href="javascript:;">'
                               ContHtml+='No cuenta con Protocolos'
                             ContHtml+='</a>'
                           ContHtml+='</li>'
                     }
                 }
             ContHtml+='</ul>'

          DivHtml.id="dvl_lista_roles_user";
          DivHtml.style.width="150px";
          DivHtml.style.backgroundColor="#000";
          DivHtml.style.position="absolute";
          DivHtml.style.left="-2px";
          DivHtml.style.top="81px";
          DivHtml.style.borderLeft="#ccc solid 1px";
          DivHtml.style.borderBottom="#ccc solid 1px";
          DivHtml.style.borderRight="#ccc solid 1px";
          DivHtml.style.boxShadow="2px 2px 5px #CCC";
          DivHtml.style.display='none';
		  DivHtml.style.zIndex='100';
          DivHtml.innerHTML=ContHtml;
          cont.appendChild(DivHtml);
          ShowHide("dvl_lista_roles_user",true,1);
       }
       if(document.getElementById("dvl_lista_roles_user").style.display=="none"){
          ShowHide("dvl_lista_roles_user",true,1);
       }else{
          ShowHide("dvl_lista_roles_user",true,0);
       }
       return true;
   }
   //Cargar Menus
   function fnl_lisSubMenu(ixmenu)
   {        
        precargaALCON("Cargando...",true,1,"center");
        $.ajax({
            type: 'post',
            url:'/SGDUNI/menuFuncionalidades.uni',
            dataType: 'html',
            data:'ixmenu='+ixmenu,
            success: function(dataXml)
            {
               fng_ussecactv(dataXml);
               fnl_leerXmlMenu(dataXml,ixmenu);
               precargaALCON("",true,0,"center");

            },error: function(dato)
            {
                alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
                precargaALCON("",true,0,"center");
                return false;
            }

        });
       return true;         
   }
   //
   function fnl_leerXmlMenu(dataXml,ixmenu)
   {
       var SubMenu=eval(dataXml);              
       var htmlMenu='<div id="menuSubOPciones" style="margin-top:20px;">';
       document.getElementById("contBodyC").style.display='none';
       if(SubMenu.length>0)
           {
               for(var rowSm=0;rowSm<SubMenu.length;rowSm++)
                {//Recorremos el archivo de                    
                     htmlMenu+='<div class="boxBotonSubMenu">'
                     htmlMenu+='<a href="'+SubMenu[rowSm].enlace+'">'
                     htmlMenu+='<p>'
                     htmlMenu+='<img src="fileproject/img/sistema/administrador/'+SubMenu[rowSm].icono+'"  border="0" />'
                     htmlMenu+='</p>'
                     htmlMenu+='<span>'
                     htmlMenu+=''+SubMenu[rowSm].nombre
                     htmlMenu+='</span>'
                     htmlMenu+='</a>'
                     htmlMenu+='</div>'
                }
                htmlMenu+='</div>';
                //
                document.getElementById("contBodyC").innerHTML=htmlMenu;
           }
        ShowHide("contBodyC",true,1);     
        return true;
   }
   /*******************************
    Menu Body - mas usados
   ********************************/
  function fnl_vista_menu_p()
  {
    
    precargaALCON("Cargando...",true,1,"center");
    $.ajax({
        type: 'post',
        //url:'/SGDUNI/plantillas/menus/menu_body.xml',
        url:'/SGDUNI/menuFuncionalidadBodyCenter.uni',
        dataType: 'html',
        success: function(dataXml)
        {
            fnl_leerXmlMenuBodyUsados(dataXml);
            precargaALCON("",true,0,"center");
        },
        error: function(dato){
            alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
            precargaALCON("",true,0,"center");
            return false;
        }
    });
  }
  //
  function fnl_leerXmlMenuBodyUsados(dataXml)
  {
   var SubMenu=eval(dataXml);
   var htmlMenu='';
   document.getElementById("menuSubOPciones").style.display='none';
   if(SubMenu.length > 0)
   {
       for(var rowSm=0;rowSm<SubMenu.length;rowSm++)
        {//Recorremos el archivo de
         htmlMenu+='<div class="boxBotonSubMenu">'
         htmlMenu+='<a href="'+SubMenu[rowSm].enlace+'">'
         htmlMenu+='<p>'
         htmlMenu+='<img src="fileproject/img/sistema/administrador/'+SubMenu[rowSm].icono+'"  border="0" />'
         htmlMenu+='</p>'
         htmlMenu+='<span>'
         htmlMenu+=''+SubMenu[rowSm].nombre
         htmlMenu+='</span>'
         htmlMenu+='</a>'
         htmlMenu+='</div>'
        }
        htmlMenu+='<div style="clear:both;"></div>';
        //
        document.getElementById("menuSubOPciones").innerHTML=htmlMenu;
   }
   ShowHide("menuSubOPciones",true,1);
   return true;
  }

//
function fng_ussecactv(valt)
{
    if(Number(valt)==50)
    {
        alertALCON("La sesión de usuario ha caduca, vuelva a ingresar","alert",true,function(){
            document.location.href="login.uni";
        });
        if(fstoobl){
            clearInterval(fstoobl);
        }
      return false;
    }
    else
    {
       return true;
    }
}