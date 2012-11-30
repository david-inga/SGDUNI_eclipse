/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




function form()
   {
       this.in_codigo_req             =   document.getElementById("in_codigo_req");
       this.ch_codigo_req             =   document.getElementById("ch_codigo_req");
       this.vc_nombre_req             =   document.getElementById("vc_nombre_req");
       this.vc_descripcion_req        =   document.getElementById("vc_descripcion_req");
       this.formCap                   =   document.getElementById("frmRegiReq");
   }

   function fnl_nuevoRequisito()
   {
      // document.getElementById("ch_codigo_req").disabled =false;
       document.getElementById("ch_codigo_req").value ="";
       document.getElementById("ch_codigo_req").focus();
       document.getElementById("vc_nombre_req").value ="";
       document.getElementById("vc_descripcion_req").value ="";
       var objAvios=$(".textAviso").html();
	   $(".textAviso").css("display","none");
       return true;
   }

   function fnl_ModificarReq()
   {
       var forTr=new form();

       if(trim(forTr.vc_nombre_req.value)==""){
           alertALCON("Escriba el Nombre del Requisito","alert");
           forTr.vc_nombre_req.focus();
           return false;
       }
       //

       if(trim(forTr.vc_descripcion_req.value)==""){
           alertALCON("Escriba la Descripcion del Requisito","alert");
           forTr.vc_descripcion_req.focus();
           return false;
       }

      document.getElementById("frmModReq").submit();

      return true;
   }

   function fnl_regiRequisito()
   {
       var forTr=new form();
	   if(trim(forTr.ch_codigo_req.value)=="")
       {
           alertALCON("Escriba el codigo","alert");
           forTr.ch_codigo_req.focus();
           return false;

       }

       if(trim(forTr.vc_nombre_req.value)==""){
           alertALCON("Escriba el Nombre del Requisito","alert");
           forTr.vc_nombre_req.focus();
           return false;
       }
       //

       if(trim(forTr.vc_descripcion_req.value)==""){
           alertALCON("Escriba la Descripcion del Requisito","alert");
           forTr.vc_descripcion_req.focus();
           return false;
       }



      document.getElementById("frmRegiReq").submit();

      return true;
   }






function fnl_estadoRequisito(valorestado,xcod)
   {
       
			
				precargaALCON("Procesando...",true,1,"center");
					$.ajax({
						type: 'post',
                        url:'/SGDUNI/cambiarEstadoRequisito.uni',
						dataType: 'html',
						data:'estado='+valorestado+'&xcod='+xcod,
						success: function(data)
                        {
						 var divFon=document.getElementById("img_ico_"+xcod);
						 var link_r=document.getElementById("a_ico_"+xcod);
						 if(Number(data)==1)
						 {//Si no hay errores
							  if(String(valorestado)=="01"){
								  divFon.src="fileproject/img/sistema/formularios/bien.gif";
								  link_r.href="javascript:void(0);fnl_estadoRequisito('02','"+xcod+"');";
							  }else if(String(valorestado)=="02"){
								  divFon.src="fileproject/img/sistema/formularios/bien2.gif";
								  link_r.href="javascript:void(0);fnl_estadoRequisito('01','"+xcod+"');";
							  }
						}
                        else
                        {
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias","alert");
                            precargaALCON("",true,0,"center");
							return;
						}
							precargaALCON("",true,0,"center");
							return;
						},
						error: function(dato)
                        {
							alertALCON("Ups!! ocurrio un error intentelo nuevamente, gracias"+dato,"alert");
							precargaALCON("",true,0,"center");
							return;
						}
					});
			return;
   }


