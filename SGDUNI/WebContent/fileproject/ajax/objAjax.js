// JavaScript Document
function objetoAjax(){
 var http_request = false;
        if (window.XMLHttpRequest) { // Mozilla, Safari,...

            try {
                  http_request = new XMLHttpRequest();

            } catch (e) {
                try {
                    http_request = new window.ActiveXObject("Microsoft.XMLHTTP");

                } catch (e) {}
            }

            if (http_request.overrideMimeType) {
                http_request.overrideMimeType('text/xml');
            }
        } else if (window.ActiveXObject) { // IE
            try {
                http_request = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    http_request = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {}
            }
        }
        if (!http_request) {
            alert('Falla :( No es posible crear una instancia XMLHTTP');
            return false;
        }else
   return http_request;
}
/***********************************************************
VENTANAS TRANPARANTES CON EFECTOS DE JQUERY
AUTOR : LUIS ALCANTARA CONTRERAS
V 2.0
Ejemplo
windowPopUpALCON("[{\"fondo\":{\"backgroundColor\":\"#fff\",\"opacidad\": \"60\",\"zIndex\": \"100\"},\"bordeContenedor\":{\"backgroundColor\":\"#000\",\"opacidad\": \"60\"},\"opciones\":{\"botonCerrar\":true,\"width\": \"300\",\"url\":\"luis.php\",\"urlVar\":{\"var1\":\"valor1\",\"var2\":\"valor2\"},\"precarga\":\"Cargando los Datos\","jqueryefecto":true,"iniClose":true}}];");
windowPopUpALCON('[{"opciones":{"url":"BORRAR_SI_O_SI.php","urlVar":{"ki":"juan josos","lu":"varible"},"jqueryefecto":true,"iniClose":true}}];');
************************************************************/
function windowPopUpALCON(Opciones,jQuery,fn){
	 var ajaxJquery=jQuery||false;
      /***VERSION I.E - SOLO PARA INTRENET EXPLORE***/
	  // var navegador = navigator.appName;
	  // var ieVer=/*@cc_on function(){ switch(@_jscript_version){ case 1.0:return 1; case 3.0:return 3; case 5.0:return 5; case 5.1:return 5; case 5.5:return 5.5; case 5.6:return 6; case 5.7:return 7; case 5.8:return 8; }}()||@*/0;
  	  //if(/MSIE 6.0/i.test(navigator.userAgent)) {ieVer=6;}
	  /***********************************************/

	/**/var fh=new Date();var vv=fh.getHours()*fh.getMinutes()*fh.getSeconds()*3600;/*VARIBLE ALEATORIA*/
      var fondoT=false;/*POR DEFECTO*/
	  var borderTrans=true;
	  var botonCerrar=false;
	  var urlPag='';
	  var urlVariables='';
	  var precarga="Cargando...";
	  var iniClose=false;/**/
	  this.jQueryEfecto=false;
	  /************/
	  var colorFondoT='#FFF';/*color de fobto total tranparente*/
	  var nCapa=100;
	  var opacidadFondo='60';
	  /**/
	  var ancho='400';
	  var alto='500';
	  var UndMe='px';
	  var opacidadBorder='60';
	  var colorBorde='#000';
	  var radio='7';
      var target="parent";
	  var posiScrollObjc=false;
	  var backgroundColorContPag='#fff';//color de fondo de la pagina donde carga el archivo solicitado

		  //if (navegador == "Microsoft Internet Explorer"){
			  //if(ieVer==6){// SI ES INTERNET EXPLORE VERSION 6, SE OCULTA LOS SELECT
			 	//HideSelect();
			  //}
		  //}

	  if((Opciones) && Opciones!=""){
	       var varOpciones=eval(Opciones);/*LEE EL JSON CON LAS OPCIONES*/
	  }else{
		  alert("LE FALTA DEFINIR LAS OPCIONES");return false;
	  }
			if(varOpciones[0]["fondo"]){
			   fondoT=true;
			   if(varOpciones[0]["fondo"]["backgroundColor"]){colorFondoT=varOpciones[0]["fondo"]["backgroundColor"];}
			   if(varOpciones[0]["fondo"]["opacidad"]){opacidadFondo=varOpciones[0]["fondo"]["opacidad"];}
			   if(varOpciones[0]["fondo"]["zIndex"]){nCapa=varOpciones[0]["fondo"]["zIndex"];}
			}
			if(varOpciones[0]["bordeContenedor"]){
			   borderTrans=true;
			   if(varOpciones[0]["bordeContenedor"]["backgroundColor"]){colorBorde=varOpciones[0]["bordeContenedor"]["backgroundColor"];}
			   if(varOpciones[0]["bordeContenedor"]["opacidad"]){opacidadBorder=varOpciones[0]["bordeContenedor"]["opacidad"];}
			   if(varOpciones[0]["bordeContenedor"]["borderRadius"]){radio=varOpciones[0]["bordeContenedor"]["borderRadius"];}
			}

			if(varOpciones[0]["opciones"]){
			   if(varOpciones[0]["opciones"]["botonCerrar"]){botonCerrar=varOpciones[0]["opciones"]["botonCerrar"];}
			   if(varOpciones[0]["opciones"]["url"]){urlPag=varOpciones[0]["opciones"]["url"];}
			   if(varOpciones[0]["opciones"]["urlVar"]){
				 var urlPagVar=varOpciones[0]["opciones"]["urlVar"];
				   for(var key in urlPagVar){
					   urlVariables+=key+'='+urlPagVar[key]+'&';
				   }
			   }
			   if(varOpciones[0]["opciones"]["precarga"]){precarga=varOpciones[0]["opciones"]["precarga"];}
			   if(varOpciones[0]["opciones"]["width"]){ancho=varOpciones[0]["opciones"]["width"];}
			   if(varOpciones[0]["opciones"]["height"]){alto=varOpciones[0]["opciones"]["height"];}
			   if(varOpciones[0]["opciones"]["jqueryefecto"]){this.jQueryEfecto=varOpciones[0]["opciones"]["jqueryefecto"];}
			   if(varOpciones[0]["opciones"]["iniClose"]){iniClose=varOpciones[0]["opciones"]["iniClose"];}
			   if(varOpciones[0]["opciones"]["target"]){target=varOpciones[0]["opciones"]["target"];}
   			   if(varOpciones[0]["opciones"]["posiScrollObjec"]){posiScrollObjc=varOpciones[0]["opciones"]["posiScrollObjec"];}
			   if(varOpciones[0]["opciones"]["fondoColorPg"]){backgroundColorContPag=varOpciones[0]["opciones"]["fondoColorPg"];}


			}else{
				alert("DEFINE LAS OPCIONES PARA CARGAR LA PAGINA");return false;
			}

	  if(target=="this"){
		  this.targetT="";
	      target="";
	  }else{
		  this.targetT=target;

		  target=target+".";

	  }
     /*************/
	  if(alto>480){alto=480;}/*ALTURA MAXIMA*/
	  if(iniClose==true){/*LIMPIA EL CONTENEDOR SI EXISTE Y SI LA OPCION ESTE EN TRUE*/
		   CloseWindowPopUpAlcon(false,this.targetT);/*Si existe borra*/
	  }
	/**/

   /****************************************
   CONTENEDOR PRINCIPAL
   ****************************************/
	var body=eval(""+target+"document.getElementsByTagName('body').item(0)");
	if(!eval(""+target+"document.getElementById('divContenedorTF')") && fondoT==true){
		var divFon = eval(''+target+'document.createElement("div")');/*div de fondo total*/
		divFon.id='divContenedorTF';
		divFon.style.backgroundColor=colorFondoT;
		  var posi='fixed';
		  //if (navegador == "Microsoft Internet Explorer"){
			  //if(ieVer==6){// SI ES INTERNET EXPLORE VERSION 6, SE OCULTA LOS SELECT
				//posi='absolute';
			  //}
		  //}
        divFon.style.position=posi;
		divFon.style.height='100%';divFon.style.width='100%';
		divFon.style.top='0px';
		divFon.style.left='0px';
		if(this.jQueryEfecto==true){
			divFon.style.display='none';
		}
		divFon.style.filter='alpha(opacity='+opacidadFondo+')';
		divFon.style.MozOpacity='.'+opacidadFondo;
		divFon.style.opacity='.'+opacidadFondo;
		divFon.style.zIndex=nCapa;
			    if (divFon.addEventListener){
					divFon.addEventListener('click',function(event){ CloseWindowPopUpAlcon("","");}, false);
				} else if (divFon.attachEvent){
					divFon.attachEvent('onclick', function(event){ CloseWindowPopUpAlcon("","");});
				}
		body.appendChild(divFon);
		/**/
	}else{
		if(eval(""+target+"document.getElementById('divContenedorTF')")){
			eval(""+target+"document.getElementById('divContenedorTF').style.display='block'");
		 }
	}
    /************************************
	CREA EL DIV DEL BORDE
	************************************/
	if(!eval(""+target+"document.getElementById('borderDivCon')") && borderTrans==true){
		var divBorde=eval(''+target+'document.createElement("div")');
		divBorde.id='borderDivCon';
		divBorde.style.backgroundColor=colorBorde;
		  var posi='fixed';
		  //if (navegador == "Microsoft Internet Explorer"){
			  //if(ieVer==6){
				//posi='absolute';
			  //}
		  //}
        divBorde.style.position=posi;
		divBorde.style.height=alto+String(UndMe);divBorde.style.width=ancho+String(UndMe);
		divBorde.style.top='50%';
		divBorde.style.left='50%';
		if(this.jQueryEfecto==true){
		  divBorde.style.display='none';
		}
		divBorde.style.marginLeft='-'+ (ancho/2) +String(UndMe);
		divBorde.style.marginTop='-'+ (alto/2) +String(UndMe);

		divBorde.style.filter='alpha(opacity='+opacidadBorder+')';
		divBorde.style.MozOpacity='.'+opacidadBorder;
		divBorde.style.opacity='.'+opacidadBorder;

		divBorde.style.MozBorderRadius=radio+String(UndMe);
		divBorde.style.KhtmlBorderRadius=radio+String(UndMe);
		divBorde.style.WebkitBorderRadius=radio+String(UndMe);
		divBorde.style.borderRadius=radio+String(UndMe);
		divBorde.style.zIndex=nCapa + 1;
		body.appendChild(divBorde);

	}else{
		 if(eval(""+target+"document.getElementById('borderDivCon')")){
			 eval(""+target+"document.getElementById('borderDivCon').style.display='block'");
	     }
	}

	/****************************************
	SE CREA EL DIV DONDE CARGARA LA PAGINA
	*****************************************/
	if(!eval(""+target+"document.getElementById('divContePagT')")){
		  var divPagC=eval(""+target+"document.createElement('div')");
		      divPagC.id='divContePagT';
		  var posi='fixed';
		  //if (navegador == "Microsoft Internet Explorer"){
			  //if(ieVer==6){
				//posi='absolute';
			  //}
		  //}
              divPagC.style.position=posi;
			  divPagC.style.backgroundColor=backgroundColorContPag;
			  divPagC.style.zIndex=Number(nCapa + 2);
			  divPagC.style.height=(Number(alto) - 20) + String(UndMe);
			  divPagC.style.width=(Number(ancho) - 20) + String(UndMe);
			  divPagC.style.top='50%';
			  divPagC.style.left='50%';
			  if(this.jQueryEfecto==true){
				  divPagC.style.display='none';
			  }
			  divPagC.style.overflow='auto';
				divPagC.style.marginLeft='-'+ Number((ancho/2) - 10)+String(UndMe);
				divPagC.style.marginTop='-'+ Number((alto/2) - 10) +String(UndMe);
			  body.appendChild(divPagC);
			/*** FIN DIV CARGA PAGINA **************/


			/******************************************************
			SE CREA EL BOTON CERRAR
			*******************************************************/
		   if(botonCerrar==true)
			{
			  //Div contenedor total para el boton cerrar
				var divTopPagC=eval(""+target+"document.createElement('div')");
				  divTopPagC.id='divTopContePagT';
				   posi='fixed';
					//if (navegador == "Microsoft Internet Explorer"){
					  //if(ieVer==6){
						//posi='absolute';
					  //}
					//}
				  divTopPagC.style.position=posi;
				  divTopPagC.style.zIndex=Number(nCapa + 2);
				  divTopPagC.style.height=1
				  divTopPagC.style.width=(Number(ancho) - 20) + String(UndMe);
				  divTopPagC.style.top='50%';
				  divTopPagC.style.left='50%';
				  if(this.jQueryEfecto==true){
					 divTopPagC.style.display='none';
				  }
					divTopPagC.style.marginLeft='-'+ Number((ancho/2) - 10)+String(UndMe);
					divTopPagC.style.marginTop='-'+ Number((alto/2) - 10) +String(UndMe);
				  //divPagC.style.border='#000000 solid 1px';
				  body.appendChild(divTopPagC);

			   //Div segundo contenedor total para el boton cerrar
				   var divContCerrar=eval(''+target+'document.createElement("div")');
					  divContCerrar.id='divContCerrarPopUpD';
					  divContCerrar.style.position='relative';
					  divContCerrar.style.height=1
					  divTopPagC.appendChild(divContCerrar);

			  //Div boton cerrar
				  var divCerrar=eval(''+target+'document.createElement("div")');
				  divCerrar.id='divCerrarPopUpD';
				  divCerrar.style.fontFamily='Verdana, Geneva, sans-serif';
				  divCerrar.style.fontWeight='bold';
				  divCerrar.style.cursor='pointer';
				  divCerrar.style.height='18px';
				  divCerrar.style.width='14px';
				  divCerrar.style.backgroundColor=colorBorde;
					divCerrar.style.filter='alpha(opacity='+opacidadBorder+')';
					divCerrar.style.MozOpacity='.'+opacidadBorder;
					divCerrar.style.opacity='.'+opacidadBorder;

					divCerrar.style.MozBorderRadiusBottomLeft=radio+String(UndMe);
					divCerrar.style.KhtmlBorderRadiusBottomLeft=radio+String(UndMe);
					divCerrar.style.WebkitBorderRadiusBottomLeft=radio+String(UndMe);
					divCerrar.style.borderBottomLeftRadius=radio+String(UndMe);


				  divCerrar.style.right='0px';
				  divCerrar.style.top='0px';
				  divCerrar.style.position='absolute';
				  divCerrar.style.color='#fff';
				  divCerrar.align="right";
				  divCerrar.style.zIndex=nCapa + 5;
				  divCerrar.style.fontSize='12px';
				  divCerrar.title='Cerrar';
				  divCerrar.innerHTML='X';
					if (divCerrar.addEventListener){
						divCerrar.addEventListener('click',function(event){ CloseWindowPopUpAlcon("","");}, false);
					} else if (divCerrar.attachEvent){
						divCerrar.attachEvent('onclick', function(event){ CloseWindowPopUpAlcon("","");});
					}
				 divContCerrar.appendChild(divCerrar);//se agrega dentro del div BORDE
		     }
		     /****** FIN BOTON CERRAR *******************/
	}else{
		eval(""+target+"document.getElementById('divContePagT').style.display='block'");
	}
	/*EFECTO DE Fading CON JQUERY*/
	   if(this.jQueryEfecto==true){
          if(eval(""+target+"document.getElementById('divContePagT')")){$('#divContePagT').fadeIn();eval(""+target+"document.getElementById('divContePagT').style.display='block';");}
		  if(eval(""+target+"document.getElementById('borderDivCon')")){$("#borderDivCon").fadeIn();eval(""+target+"document.getElementById('borderDivCon').style.display='block';");}
		  if(eval(""+target+"document.getElementById('divContenedorTF')")){$("#divContenedorTF").fadeIn();eval(""+target+"document.getElementById('divContenedorTF').style.display='block';");}
	   }
	/**/

	//posicion del Scroll
	 if(posiScrollObjc==true){
		eval(""+target+"document.getElementById('divContePagT').scrollIntoView(true);");
	 }
	//

	/**************************************
	USO DE AJAX PARA CARGAR EL CONTENIDO DE LA PAGINA (JQUERY-AJAX NORMAL)
	**************************************/
	if(ajaxJquery==true){
			  $.ajax({
				  beforeSend:function(){
					  eval(""+target+"document.getElementById('divContePagT').innerHTML='Cargando...'");
				  },
				  type:'post',
				  url:urlPag,
				  dataType:'html',
				  data:urlVariables,
				  success:function(data){
					eval(""+target+"document.getElementById('divContePagT')").innerHTML=data;
					if(fn){
					 fn();
					}
				  },
				  error:function(err){
					 eval(""+target+"document.getElementById('divContePagT')").innerHTML="Ups! ocurrio un error, intentelo nuevamente";
				 }
			  });
	}else{
			var divContenido=eval(""+target+"document.getElementById('divContePagT')");
			var ajax=objetoAjax();
			divContenido.innerHTML='Cargando...';
			ajax.open("POST", urlPag,true);
			ajax.onreadystatechange=function(){
					if (ajax.readyState==4){
						if(ajax.status==200){
							divContenido.innerHTML=ajax.responseText;
							if(fn){
								fn();
							}
						}else if(ajax.status==404){
							   divContenido.innerHTML = "<div style='font-family:Arial; margin-top:5px; color:#666;' align='center'>LA DIRECCION URL, NO EXISTE</div>";
						}else{
							   divContenido.innerHTML= "<div style='font-family:Arial; margin-top:5px; color:#666;' align='center'>Ups! ocurrio un error, intentelo nuevamente</div>";//ajax.status
						}
					}else{
						divContenido.innerHTML="<div style='font-family:Arial; margin-top:5px; color:#666;' align='center'>"+precarga+"</div>";

					}
			}
			ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			ajax.send(urlVariables+"fh="+fh);
	}
	return true;
}
function CloseWindowPopUpAlcon(efect,target_t){
     /***VERSION I.E - SOLO PARA INTRENET EXPLORE***/
	   //var navegador = navigator.appName;
	   //var ieVer=/*@cc_on function(){ switch(@_jscript_version){ case 1.0:return 1; case 3.0:return 3; case 5.0:return 5; case 5.1:return 5; case 5.5:return 5.5; case 5.6:return 6; case 5.7:return 7; case 5.8:return 8; }}()||@*/0;
	  //if(/MSIE 6.0/i.test(navigator.userAgent)) {ieVer=6;}
	/***********************************************/
	   if(efect==true || efect==false){
		   var efect=efect;
	   }else{
		   var efect=this.jQueryEfecto;
	   }
	   if(target_t && target_t!="")
	   {
	     var target=target_t+".";
	   }else{
		   if(this.targetT!="" && this.targetT!="this"){
				var target=this.targetT+".";
		   }else{
				var target="";
		   }
	   }
	   if(efect==true){
			  if(eval(""+target+"document.getElementById('divContePagT')")){$("#divContePagT").fadeOut();}
			  if(eval(""+target+"document.getElementById('borderDivCon')")){$("#borderDivCon").fadeOut();}
			  if(eval(""+target+"document.getElementById('divContenedorTF')")){$("#divContenedorTF").fadeOut();}
			  setTimeout("CloseWindowPopUpRemove('"+target+"')",300);
	   }else{
		  CloseWindowPopUpRemove(target);
	   }
	  return true;
}
function CloseWindowPopUpRemove(target){
	 var body=eval(""+target+"document.getElementsByTagName('body').item(0)");
		if(eval(""+target+"document.getElementById('divTopContePagT')"))
		{
		   eval(""+target+"document.getElementById('divTopContePagT').removeChild("+target+"document.getElementById('divContCerrarPopUpD'))");
		   body.removeChild(eval(""+target+"document.getElementById('divTopContePagT')"));
		}
	   if(eval(""+target+"document.getElementById('borderDivCon')")) body.removeChild(eval(""+target+"document.getElementById('borderDivCon')"));
	   if(eval(""+target+"document.getElementById('divContenedorTF')")) body.removeChild(eval(""+target+"document.getElementById('divContenedorTF')"));
	   if(eval(""+target+"document.getElementById('divContePagT')")) body.removeChild(eval(""+target+"document.getElementById('divContePagT')"));
 return true;
}

/***************************************************
AUTOR : LUIS ALCANTARA CONTRERAS
FECHA :
V. 1 Ventana pequeña
windowPopUpMin('[{"opciones":{"url":"BORRAR_SI_O_SI.php","urlVar":{"ki":"juan josos","lu":"varible"},"jqueryefectoMin":true,"iniClose":true}}];');
***************************************************/
function windowPopUpMin(Opciones,cerrar){
	/**/var fh=new Date();var vv=fh.getHours()*fh.getMinutes()*fh.getSeconds()*3600;/*VARIBLE ALEATORIA*/
	  var bolCerrar=cerrar||false;
      var fondoT=true;/*POR DEFECTO*/
	  var borderTrans=true;
	  var botonCerrar=false;
	  var urlPag='';
	  var urlVariables='';
	  var precarga="Cargando...";
	  var iniClose=false;/**/
	  this.jqueryefectoMin=false;
	  /************/
	  var colorFondoT='#FFF';/*color de fobto total tranparente*/
	  var nCapa=50;
	  var opacidadFondo='99';
	  /**/
	  var ancho='340';
	  var alto='200';
	  var UndMe='px';
  	  var radio='7';
	  var titulo='# Aviso'
	  var PosiBottom='2';
	  var PosiLeft='2';
	  var PosiTop='';
	  var PosiRight='';
	  /**************/
	  if((Opciones) && Opciones!=""){
	    var varOpciones=eval(Opciones);/*LEE EL JSON CON LAS OPCIONES*/
	  }else{
		alert("LE FALTA DEFINIR LAS OPCIONES");return false;
	  }
			if(varOpciones[0]["fondo"]){
			   if(varOpciones[0]["fondo"]["backgroundColor"]){colorFondoT=varOpciones[0]["fondo"]["backgroundColor"];}
			   if(varOpciones[0]["fondo"]["opacidad"]){opacidadFondo=varOpciones[0]["fondo"]["opacidad"];}
			   if(varOpciones[0]["fondo"]["zIndex"]){nCapa=varOpciones[0]["fondo"]["zIndex"];}
			}

			if(varOpciones[0]["opciones"]){
			   if(varOpciones[0]["opciones"]["botonCerrar"]){botonCerrar=varOpciones[0]["opciones"]["botonCerrar"];}
			   if(varOpciones[0]["opciones"]["url"]){urlPag=varOpciones[0]["opciones"]["url"];}
			   if(varOpciones[0]["opciones"]["urlVar"]){
				 var urlPagVar=varOpciones[0]["opciones"]["urlVar"];
				   for(var key in urlPagVar){
					   urlVariables+=key+'='+urlPagVar[key]+'&';
				   }
			   }
			   if(varOpciones[0]["opciones"]["precarga"]){precarga=varOpciones[0]["opciones"]["precarga"];}
			   if(varOpciones[0]["opciones"]["width"]){ancho=varOpciones[0]["opciones"]["width"];}
			   if(varOpciones[0]["opciones"]["height"]){alto=varOpciones[0]["opciones"]["height"];}

			   if(varOpciones[0]["opciones"]["PosiTop"]){PosiTop=varOpciones[0]["opciones"]["PosiTop"];}
			   if(varOpciones[0]["opciones"]["PosiLeft"]){PosiLeft=varOpciones[0]["opciones"]["PosiLeft"];}
			   if(varOpciones[0]["opciones"]["PosiRight"]){PosiRight=varOpciones[0]["opciones"]["PosiRight"];}
			   if(varOpciones[0]["opciones"]["PosiBottom"]){PosiBottom=varOpciones[0]["opciones"]["PosiBottom"];}


			   if(varOpciones[0]["opciones"]["jqueryefectoMin"]){this.jqueryefectoMin=varOpciones[0]["opciones"]["jqueryefectoMin"];}
			   if(varOpciones[0]["opciones"]["iniClose"]){iniClose=varOpciones[0]["opciones"]["iniClose"];}
			   if(varOpciones[0]["opciones"]["titulo"]){titulo=varOpciones[0]["opciones"]["titulo"];}
			}else{
				alert("DEFINE LAS OPCIONES PARA CARGAR LA PAGINA");return false;
			}
     /*************/
	  if(alto>550){alto=550;}/*ALTURA MAXIMA*/
	  if(iniClose==true){/*LIMPIA EL CONTENEDOR SI EXISTE Y SI LA OPCION ESTE EN TRUE*/
		   //CloseWindowPopUpAlcon(false);/*Si existe borra*/
	  }
	/**/

	var body=document.getElementsByTagName('body').item(0);/*CONTENEDOR PRINCIPAL*/

	if(!document.getElementById('divContenedorTF')){
		var divFon = document.createElement("div");
		divFon.id='divContenedorTF';
		divFon.style.backgroundColor=colorFondoT;
		divFon.style.border='#000 solid 1px';
     /***VERSION I.E - SOLO PARA INTRENET EXPLORE***/
	   var navegador = navigator.appName;
	   var ieVer=/*@cc_on function(){ switch(@_jscript_version){ case 1.0:return 1; case 3.0:return 3; case 5.0:return 5; case 5.1:return 5; case 5.5:return 5.5; case 5.6:return 6; case 5.7:return 7; case 5.8:return 8; }}()||@*/0;
	//if(/MSIE 6.0/i.test(navigator.userAgent)) {ieVer=6;}
	/***********************************************/
	var posi='fixed';
	  if (navegador == "Microsoft Internet Explorer"){
		  if(ieVer==6){
			posi='absolute';
		  }
	  }
	divFon.style.position=posi;

			  divFon.style.height=Number(alto) + String(UndMe);
			  divFon.style.width=Number(ancho) + String(UndMe);
		if(PosiBottom!=""){divFon.style.bottom=Number(PosiBottom) + String(UndMe);}
		if(PosiLeft!=""){divFon.style.left=Number(PosiLeft) + String(UndMe);}
		if(PosiTop!=""){divFon.style.top=Number(PosiTop) + String(UndMe);}
		if(PosiRight!=""){divFon.style.right=Number(PosiRight) + String(UndMe);}
		if(this.jqueryefectoMin==true){
			divFon.style.display='none';
		}
		divFon.style.filter='alpha(opacity='+opacidadFondo+')';
		divFon.style.MozOpacity='.'+opacidadFondo;
		divFon.style.opacity='.'+opacidadFondo;
		divFon.style.zIndex=nCapa;
		/*divFon.style.MozBorderRadius=radio+String(UndMe);
		divFon.style.KhtmlBorderRadius=radio+String(UndMe);
		divFon.style.WebkitBorderRadius=radio+String(UndMe);
		divFon.style.borderRadius=radio+String(UndMe);
		*/
		body.appendChild(divFon);
		/**/
	}else{
		if(document.getElementById('divContenedorTF')){document.getElementById('divContenedorTF').style.display='block'};
	}
   /*SE CREA EL DIV TOP*/
   var altoTop='30';
	if(!document.getElementById('divConteTop')){
		  var divPagTop=document.createElement('div');
		      divPagTop.id='divConteTop';
			  divPagTop.style.backgroundColor='#eeeeee';
			  divPagTop.style.borderBottom='#cdcdcd solid 1px';
			  divPagTop.style.fontFamily='Arial, Helvetica, sans-serif';
			  divPagTop.style.height=altoTop+String(UndMe);
			  divPagTop.style.width=ancho + String(UndMe);
			  divPagTop.style.fontSize='10px';
			  divPagTop.style.color='#000';
			  divPagTop.style.fontWeight='bold';
    	      var texDescrip=titulo.toUpperCase();
		      var maxCa=40;
   		      if(texDescrip.length>maxCa){
			      var nu=texDescrip.substr(0,maxCa)+'...';
		      }else{
			      var nu=texDescrip;
		      }
			  divPagTop.innerHTML='<table><tr><td width="320" style="padding:5px"> '+nu+'</td><td onclick="CloseWindowPopUpMin(\''+bolCerrar+'\');" style="cursor:pointer"><strong>X</strong></td></tr></table>';
			 if(divPagTop)
			 {
			  divFon.appendChild(divPagTop);
			 }
			/*** FIN DIV CARGA PAGINA **************/
	}else{
		document.getElementById('divConteTop').style.display='block'
	}
   /**/

			/*SE CREA EL DIV DONDE CARGARA LA PAGINA*/
	if(!document.getElementById('divContePagT')){
		  var divPagC=document.createElement('div');
		      divPagC.id='divContePagT';
			  divPagC.style.backgroundColor='#f8f8f8';
			  divPagC.style.position='relative';
			  divPagC.style.maxHeight=(Number(alto) - Number(altoTop) - 3) + String(UndMe);
			  divPagC.style.width='100%';
			  //if(this.jqueryefectoMin==true){
				 // divPagC.style.display='none';
			  //}
			  divPagC.style.overflow='auto';
			  divFon.appendChild(divPagC);
			/*** FIN DIV CARGA PAGINA **************/
	}else{
		document.getElementById('divContePagT').style.display='block'
	}
	/*EFECTO DE Fading CON JQUERY*/
	   if(this.jqueryefectoMin==true){
          if(document.getElementById('divContenedorTF')){$("#divContenedorTF").show("show");}

	   }
	/**/
	var divContenido=document.getElementById('divContePagT');
	var ajax=objetoAjax();;
	ajax.open("POST", urlPag,true);
	ajax.onreadystatechange=function(){
			if (ajax.readyState==4){
				if(ajax.status==200){
					divContenido.innerHTML=ajax.responseText;
				}else if(ajax.status==404){
					   divContenido.innerHTML = "<div style='font-family:Arial; margin-top:5px; color:#666;' align='center'>LA DIRECCION URL, NO EXISTE</div>";
				}else{
					   divContenido.innerHTML= "<div style='font-family:Arial; margin-top:5px; color:#666;' align='center'>ERROR "+ajax.status+"</div>";
				}
			}else{
				divContenido.innerHTML="<div style='font-family:Arial; margin-top:5px; color:#666;' align='center'>"+precarga+"</div>";

			}
	}
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	ajax.send(urlVariables+"fh="+fh);
	return true;
}

function CloseWindowPopUpMin(efect){
	  /*CREA UN COOKIE PARA SABER SI SE ABRIRA EL PoopUp al actualizar la pagina*/
       document.cookie="popUpMrs=false";
	   if(efect==true || efect==false){
		   efect=efect;
	   }else{
		   efect=this.jqueryefectoMin;
	   }
	   if(efect==true){
          if(document.getElementById('divContenedorTF')){$("#divContenedorTF").hide("show");}
		  setTimeout("CloseWindowPopUpRemoveMin()",500);
	   }else{
		  CloseWindowPopUpRemoveMin();
	   }
}
function CloseWindowPopUpRemoveMin(){
 GuardarCookie("ventanaMin","0");//creamos un cookies para saber que esta desactivada
  var body=document.getElementsByTagName('body').item(0);
  if(document.getElementById('divContenedorTF')){body.removeChild(document.getElementById('divContenedorTF'));}
}
/**************************************************
ZOOM DE FOTOS
AUTOR : LUIS ALCANTARA CONTRERAS
FECHA :
V. 1
**************************************************/
function zoomFotosEs(objImgTh,rutImg,Img,altoImg,anchoImg,errImgExiste,efectoJquery){
	var dominioWbImgPrecarga='http://www.'+document.domain+'/adminmrs/';
	var efectQuery=efectoJquery||false;
	var ContImgAncho=500;
	var ContImgAlto=400;
	var Calidad=95;
	var Contenedor= document.getElementsByTagName('body').item(0);

	if(altoImg<=ContImgAlto){
	  ContImgAlto=altoImg;
	  if(anchoImg<=ContImgAncho){
	   ContImgAncho=anchoImg;
	  }
	}else{
	  ContImgAncho = (ContImgAlto/altoImg)*anchoImg;
	}

  if(document.getElementById('IdZoomFoto')){
	  var obAnFon=document.getElementById('IdZoomFoto');
	  Contenedor.removeChild(obAnFon);
  }

	var divZoomFoto = document.createElement("div");	/*BOTON CERRAR*/
	divZoomFoto.id='IdZoomFoto';
	divZoomFoto.style.zIndex='150';

	divZoomFoto.style.height=String(ContImgAlto)+'px';
	divZoomFoto.style.overflow='hidden';
	divZoomFoto.style.width=String(ContImgAncho)+'px';
        divZoomFoto.style.marginLeft='-'+ (ContImgAncho/2) +String('px');
 		divZoomFoto.style.marginTop='-'+ (ContImgAlto/2) +String('px');
	divZoomFoto.style.top='50%';
	divZoomFoto.style.left='50%';
	divZoomFoto.style.padding='0px;';
     /***VERSION I.E - SOLO PARA INTRENET EXPLORE***/
	   var navegador = navigator.appName;
	   var ieVer=/*@cc_on function(){ switch(@_jscript_version){ case 1.0:return 1; case 3.0:return 3; case 5.0:return 5; case 5.1:return 5; case 5.5:return 5.5; case 5.6:return 6; case 5.7:return 7; case 5.8:return 8; }}()||@*/0;
	//if(/MSIE 6.0/i.test(navigator.userAgent)) {ieVer=6;}
	/***********************************************/
	var posi='fixed';
	  if (navegador == "Microsoft Internet Explorer"){
		  if(ieVer==6){
			posi='absolute';
		  }
	  }
	divZoomFoto.style.position=posi;
	divZoomFoto.style.backgroundColor='#fff';
	divZoomFoto.style.border="#333 solid 5px";
	divZoomFoto.style.cursor='pointer';
				if (divZoomFoto.addEventListener){
					divZoomFoto.addEventListener('click',function(event){ CerrZooFot(efectQuery);}, false);
				} else if (divZoomFoto.attachEvent){
					divZoomFoto.attachEvent('onclick', function(event){ CerrZooFot(efectQuery);});
				}
	if(efectQuery==true){
		divZoomFoto.style.display='none';
	}
	  Contenedor.appendChild(divZoomFoto);
    var imgG=new Image();
		imgG.title='Clic para cerrar';
	    //imgG.style.background="url("+dominioWbImgPrecarga+"fileproject/img/precarga_g.gif) no-repeat 50% 50%";
		imgG.style.backgroundImage="url("+dominioWbImgPrecarga+"fileproject/img/precarga_g.gif)";
		imgG.style.backgroundRepeat="no-repeat";
		imgG.style.backgroundPosition="50% 50%";

	if(altoImg>ContImgAlto){
	   //imgG.src=objImgTh+'?ruta_i='+rutImg+'&al='+ContImgAlto+'&calid='+Calidad;
	    imgG.src='?tip=instituciones&'+rutImg+'&foto='+Img+'&h='+ContImgAlto+'&w='+ContImgAncho+'&err='+errImgExiste;
	}else{
	    imgG.src=rutImg+Img;
	}
   divZoomFoto.appendChild(imgG);
   if(efectQuery==true){
	  $("#IdZoomFoto").show("show");
   }
}
function CerrZooFot(efectoJquery){
	var efectQuery=efectoJquery||false;
	var Contenedor= document.getElementsByTagName('body').item(0);
	var ob=document.getElementById('IdZoomFoto');
	if(efectQuery==true){
	    $("#IdZoomFoto").hide("show");
	}else{
		//Contenedor.removeChild(ob);
	}
}
/*fin zoom de fotos*/
/***************************************************
VENTANA ALERTA TOP
AUTOR :  LUIS ALCANTARA CONTRERAS
FECHA:
V. 1
****************************************************/
  function alertALCON(text,tipo,next,fnAceptar,jQuery){
	  var varNext=next||false;
	  var jQueryT=jQuery||false;

	  var bodyContainer=document.getElementsByTagName('body').item(0);
	  if(document.getElementById('idDivAlerta')){
		  var objCon=document.getElementById('idDivAlerta');
		   bodyContainer.removeChild(objCon);
	  }
       var divAlert = document.createElement("div");/**/
			 divAlert.id='idDivAlerta';
			 divAlert.style.color='#333';
			 divAlert.style.fontFamily='Verdana, Geneva, sans-serif';
			 divAlert.style.fontSize='14px';
			 divAlert.style.padding='8px;';
				if (navegador == "Microsoft Internet Explorer"){
					if(ieVer==6){
						divAlert.style.position='absolute';
					}
				}else{
					divAlert.style.position='fixed';
				}

			 divAlert.style.borderTop='#000 solid 3px';
			 divAlert.style.borderBottom='#666 solid 1px';
			 divAlert.style.top='0px';
			 divAlert.style.display='block';
			 divAlert.style.left='0px';
			 divAlert.style.width='100%';
			 divAlert.style.zIndex='101';
			 divAlert.style.minHeight='40px';
			 divAlert.style.backgroundColor='#faf8c1';
			 divAlert.style.textAlign='center';
			 divAlert.style.cursor='pointer';
			 divAlert.style.boxShadow='2px 2px 5px #999';
			 /***VERSION I.E - SOLO PARA INTRENET EXPLORE***/
		 	   var navegador = navigator.appName;
			   var ieVer=/*@cc_on function(){ switch(@_jscript_version){ case 1.0:return 1; case 3.0:return 3; case 5.0:return 5; case 5.1:return 5; case 5.5:return 5.5; case 5.6:return 6; case 5.7:return 7; case 5.8:return 8; }}()||@*/0;
			//if(/MSIE 6.0/i.test(navigator.userAgent)) {ieVer=6;}
			/***********************************************/
		var ImgVist=true;
		if (navegador == "Microsoft Internet Explorer"){
			if(ieVer==6){
				ImgVist=false;
			}
		}
			var imgIcono='';
			if(tipo.toLowerCase()=='ok'){
			     if(ImgVist){
					 imgIcono='<img src="data:image/gif;base64,R0lGODlhGQAZAOZ/ALXUgH/HAMvioufy2bfqOI3FPHivFKniL5TUI7HnNsTuRZ3bKs71SXexFY3NIY/RJrPVcavjMnWsEbHbY/z9/IW3G9vpw+bt1KTfLne8AITIH4TFGMHvPefy1sPcmXmxFXyzFfj79fD15dHjssneoavPYpPLQInNIcbyRL3tPK7Nd5/JP36+G7rbYKLXMJbWKM3xS8DUndXmvn2+G3u2F/b58fL264DEHYvDMqLVRnK2AIW7Mn66GpfGUI/GPrzdanKzAIzGHuLvzo2+GHi1GHOvAZraKoDBHOry3sLdh53TQfP47bfkRd3qyX66GHy1GHy1FnatE7/beojPB3nBAHaxFaXhL+rz263dQpnZGo64O6bbR5XXEJK/RaLHTpW8Q5/AZHGxAKnfNr/aj6XhIIK+JKDFXrvsPMHpRpXHMrntO73kR5/dHc7gr5fBTXy4GZnJU7jsPJjQQsbrTN7vzHq1GIm9KoHEHX/CFJDEHYq4Osvgpo/BQI7TDP///////yH5BAEAAH8ALAAAAAAZABkAAAf/gH+Cg4JLTSRJUlJJMUI1hJCENgI/CgyXmGg/e4+RgxYtMAwoHKWmKKMTTZ5/JHMMKbGysykcKEwjka4cBL2+v78cuIQWa2cJyMnKywlqWBeCNS1xBxHW19jXB2QRBwQQFH97CVYY5ufo5lZsWxMLVlZiMiETB0YL+Pn6+Fw5HRQTsryDcMGFkRcIEypE2EdJBz9IlCB4YSRHGxcPMmrcmHGKnIcD5EzJiODBGAQnUqpc6SDARz8DTARwkNKBAwAPNOjcQGWDTp1UTAzwc8VHgJ86bzo4cgePDwAFqNy5Q8XH0AE+pN64s/VGEA9BjhzJ4MEPHRwZMvgA6SOD2Ldix4PIuMOCRQY4IvwIKVNg6JUCGeoKZjGDRRoROFjw4KGjR14hD//qWOykMuUjJSioYPGmMxDHfojy0dG5dGkiecb8aWKHRh0aND4j6cAHSJ06UOo8uf2EyJsVIv5QAPDmA4gGRMLs2BHGeIMP0EF8aNAgz55BNtxAqUK9QZEi3cM3qGJgCDhCA7qAMMC+vXv2UaIMKbEkEhIz66NIkBA//n4JHwwBQCeRhBCDFtLp958BIFTgRS6sDIJEDGAgCEKDX6jQRn2QBAIAOw==" align="absmiddle" hspace="4" vspace="4"/>';
			     }
			}else if(tipo.toLowerCase()=='error'){
				 if(ImgVist){
					imgIcono='<img src="data:image/gif;base64,R0lGODlhGAAYAOZ/APrw8Pjr69A5OdZcXL98fOZqaaoqKvv19eKnpc1padyqqsiIh+vV1eFOTvdkY8NoaP38/J4sLJkqKroyMupVVPJdXdhHR7QyMdWQj701NeVRUJgtLNFAP7IlJL5KSrczM91ubuqurNaVlbhfX6QuLd1JR9Sbm960tKswMOCtq8o8PNl7e+nPz+XFxcVycuW/v+CDg5YrK9JwcPrz86g9PZosK5seHpIqKu1ZWepmZufJydhEQ5snJ9Vzc/Hi4tpERKxGRvz4+MM3N82Af6AgILhjY7EsK+jBwa1RUfhoaLM4OK8xMe7d3aozMss2NtxkY8MwLtc/P8pkZOBDQsQ4OL0pKKArK7w1NOXExLRFRK1MTOBLS+JKSuFLS9zFxdagoOC+vsdQT8+Tk+RPT71lZb9ubuZOTtaHh9GJia81Na5PTtKkpdWnpeZgX6MyMbIxMPVhYNtpZ9dGRb5cW+TGxqcxMK0wMK4xMOhXV+xcW9yurueqquaurMpdXf///////yH5BAEAAH8ALAAAAAAYABgAAAf/gH+CgxAsJisgBYo9InQHg5CQDGcFSUkOcJkODm0rR5GQeiBJFTimp6YVcG0iEKBfORUUs7S1szh5aEGQJzkUYxrBwsPCFHitgkxxFFsNXVNcDdLTz11jDRoNL38QGBQl4BZPcj/g4D/iFuFmCUE+AyU7OwIwACECUfJRAnsAMALy0IE5YYGDQScgDvjh40SAACd7/AQB4cQghx1D0HBQwVEFFBkzFkKBEnFGDygdVXDoI0OFECowqVSRAcAPAgR+TFZ5GVNFGClCrmQYOrRDggB+/ARI0IGoUw9SrnyYStVIhxdJj3QwQpXqBA8urry5QPaCESMK/ByomeJsWbIfujyI+GDnzpIlBgykDfLggcIUee3eTTMHSxoUiA00STvDBREiLmrqaWIAMQklQwDMQUGCBJEFah13tlGmJhoidTorafFHQeoIPEbocGHDSmcrpHUU4UEiQpoHrmaQ6Q2bB48IyJMbP16DRBYWg5gAqSGBeo3r2KtL2F5DyZdIOoBsiEG+fPnxN2qkEQPqD4Mi6G/In38jhpssetoLmsEGSXz5MZBAAwEM6AeJD2uUgYQWWqgxwgJePBJJIAA7" align="absmiddle" hspace="4" vspace="4"/>';
				 }
			}else if(tipo.toLowerCase()=='alert'){
				 if(ImgVist){
					imgIcono='<img src="data:image/gif;base64,R0lGODlhJQAiAOZ/AOSMR/+7dv+zAf+3PsRFAPrt6f38/P/DNv+aANJlJvjk3eOrm/+UAP+vAP+WAP+eAP+nAP+bAO+IH/fTxf+pPv+TOv+KAP+VAOx3FvLKuv3w6v/IQv+JAN18Sv+KAP+jAP9zAOdjAP/Hhf+WAP+jAP+2E/+GAP+9UN+ch/+rAP+vU/9+AP+aKv+bAP+6Gt54Is5rQv+BAOq7rv+NAP+nCv97AP+pJfZ8AP96AP+TANeFaNB4Wu/NxP/bpf+bAPbeydtbAPv08v+/KP+tA+RlAP96AMZaKv+NAP92APulQ/+MAN6ScvdlAP+vIP/RWNqNc//aw/9pAOprAPTRxf+0L/+PAP+QAP99AP/BHvPX0O17JP+OAP+HAP+aAPTRwPnUxfTb1f+fAP+BAP+RAP+SAP/ovf+XAP+kAP+cAOxyBfCDAf/alv9/Gf+4I/359/+KAP+NAP9sAO6fSP+IAP+HAP+DAOhxEf64V//Mj/+TAP+XAOScav7Utu2tcf///////yH5BAEAAH8ALAAAAAAlACIAAAf/gH+Cg4SFggo/QYaLjI2DMkknAAWOlY4FHRtYVCiWnoYoSVglBxIKn6gKmQIlLlRLqJ9LKiVDAgJYElmxlWAdBzRDQw0CbTq8jjoqNMxDKQ25GciLPB0DYSQkZ2fPbR0G04QGO3c+PtjbEBAuEjLhjwBUej4XHBxVDyQQJTCK4W4w7szLowIKFD40HjwQIGHBuwUAmujR8waPn4sDyOQrkYASsiABJ9YTcdEPBTJdujSQ0AkZCjk0RL4heZGCkhEpISQ4FUsBjAEX9FzIM7OkzTFvxDDgcCzWEzkkLgwlStMABTE1iqyoQwLIrk9gYFDJIzUP1ZIVooAocmWFFQ47/1DtSEIv6IgRSqqmRcJWjJgHQHh44vGiSZ6Jd/HS9LNXq18rVWCAczTOhrl5I8xdCYB2rWMTJgC7cyTjRUxz5saIQRLAgB8DLLRe8QsashF/i0DSQO1jBJ0VK8TAoUCBBR0TfsWAfvOmCxGHjBZgOIMaDu03cEwUYVMDDpw3oJe/se2xkIYEJMyNqBMcuxU4LBQYyACHwXvwzJk7b1kIhZozvmllwngMjMBADyWdYEUX9n3HHBxWbEEAT4MokIAPW2S1woBWMNDFA10geNEJH3Yxwn3YwTECEU0N8sQNYqy14Xg45UNCCWVosEYK6ih0oncPwkHAV39kAQQIICAxG7B2Hj4AwTPElNDAlDySwKAV+Y3HRFyCdMAEkgK+UaCTz5TQigtoSskjiAw4OOAcBEijAAZxJFmEch12QUKZLghxwJ9CuKAmCQ+cCB5ocxSxhBteJKHWbCZYMQKZAvS5wQZOZOrEBgcIISUEVjLAHHJo9KGAAgCEAEQIrEohRRqwYiCrFrTWqoWsdtjhKqusAgFEGnto8IcCXnwxwbFfJKvssswae+wEU0A7BQ9TVEtJIAA7" align="absmiddle" hspace="4" vspace="4"/>';
				 }
			}else{
				alert("FALTA DECLARAR LA VARIABLE 'TIPO'");
				return false;
			}

			var TextDefaulCerrar=1;
			if(fnAceptar){
			  TextDefaulCerrar=0;
			  this.f_fnAceptar=fnAceptar;
			  divAlert.innerHTML=imgIcono+'<strong>'+text+'</strong><div><a href="javascript:;" onclick="fng_Acepta_g_p()">ACEPTAR</a></div><br/>';
			}

			if(TextDefaulCerrar==1)
			{
			  divAlert.innerHTML=imgIcono+'<strong>'+text+'</strong><div><a href="javascript:;" onclick="cerrarAlert(\''+jQueryT+'\');">CERRAR</a></div><br/>';
			}

			if (navegador == "Microsoft Internet Explorer"){
				if(ieVer!=7){
					divAlert.style.display='none';
				}
				if(ieVer==6){
					HideSelect();
				}
			}else{
		  		divAlert.style.display='none';
			}
			bodyContainer.appendChild(divAlert);
			if(jQueryT==true){
			  /*jquery*/$('#idDivAlerta').animate({height:'show',opacity:'show'},'show');
			}else{
			   divAlert.style.display='block';
			}

		   if(varNext==true){
			    var divFond = document.createElement("div");	/*fondo*/
				 divFond.id='idDivFondo';
				 divFond.style.position='absolute';
				 divFond.style.top='0px';
				 divFond.style.left='0px';
				 divFond.style.display='block';
				 divFond.style.width='100%';
				 divFond.style.height='800px';
				 divFond.style.zIndex='9';
				 divFond.style.backgroundColor='#000';
				 divFond.style.filter='alpha(opacity=60)';
				 divFond.style.MozOpacity='.60';
				 divFond.style.opacity='.60';
			     bodyContainer.appendChild(divFond);
			     return false;
		   }else{
				if(this.timeOut){
					clearTimeout(this.timeOut);
				}
				this.timeOut=setTimeout("cerrarAlert();",7500);
				return true;
		  }
}
//
function fng_Acepta_g_p(fn){
	f_fnAceptar();
}
//
function cerrarAlert(jQuery){
			 /***VERSION I.E - SOLO PARA INTRENET EXPLORE***/
		 	   var navegador = navigator.appName;
			   var ieVer=/*@cc_on function(){ switch(@_jscript_version){ case 1.0:return 1; case 3.0:return 3; case 5.0:return 5; case 5.1:return 5; case 5.5:return 5.5; case 5.6:return 6; case 5.7:return 7; case 5.8:return 8; }}()||@*/0;
			//if(/MSIE 6.0/i.test(navigator.userAgent)) {ieVer=6;}
			/***********************************************/
		 	if (navegador == "Microsoft Internet Explorer"){
				if(ieVer==6){
					ShowSelect();
				}
			}

	  var idDivAlerta=document.getElementById('idDivAlerta');
	  if(idDivAlerta){
		  var bodyContainer=document.getElementsByTagName('body').item(0);
		  if(jQuery==true){
		     /*jquery*/$('#idDivAlerta').animate({height:'hide',opacity:'hide'},'show');
		  }else{
			 document.getElementById('idDivAlerta').style.display='none';
		  }
	  }
	  var idDivFondo=document.getElementById('idDivFondo');
	  if(idDivFondo){
		  var bodyContainer=document.getElementsByTagName('body').item(0);
		  bodyContainer.removeChild(idDivFondo);
	  }
	  return true;
}
/*****************************************
PARA OCULTAR LOS SELECT EN INTERNET EXPLORE <6
*****************************************/
function HideSelect(){
			var lisSelct=document.getElementsByTagName('select');/*SOLO PARA IE 6*/
			if(lisSelct.length>0){
				for(y=0;y<lisSelct.length;y++){
					lisSelct[y].style.visibility='hidden';
				}
			}
}
function ShowSelect(){
			var lisSelct=document.getElementsByTagName('select');/*SOLO PARA IE 6*/
			if(lisSelct.length>0){
				for(y=0;y<lisSelct.length;y++){
					lisSelct[y].style.visibility='visible';
				}
			}
}

 /*************************************
  TITLE TEX V. 1
  AUTOR : LUIS ALCANTARA CONTRERAS
  FECHA : 09 DE JUNIO DEL 2011
 **************************************/
  function alconTitle_(objCont,text,Posicion,jqueryEfect){
	    var efect=jqueryEfect||false;
        var body 	= document.getElementsByTagName('body').item(0);
		//verificamos si existe el title
		if(document.getElementById("IdAlconTitle")){
			if(efect==true){
			  $("#IdAlconTitle").fadeOut();
			}
			 body.removeChild(document.getElementById("IdAlconTitle"));
		}
		if(document.getElementById("IdFlechaTitle")){
			if(efect==true){
			  $("#IdFlechaTitle").fadeOut();
			}
			 body.removeChild(document.getElementById("IdFlechaTitle"));
		}

			//CAPTURANDO EL TOP Y LEFT DEL OBJETO
			var left=objCont.offsetLeft;
			var top=objCont.offsetTop;
			//CAPTURANDO EL ANCHO Y ALTO OBJETO
			var width=objCont.offsetWidth;
			var height=objCont.offsetHeight;
			//CALCULANDO LA POSICION DEL DIV TITLE
			var PosiX=left+width + 5;
			var PosiY=top + 2;

            //VALIDANDO LA POSICION
			if(Posicion=="top"){
				PosiX=left+ 5;
				PosiY=top - height -2;
			}else if(Posicion=="bottom"){
				PosiX=left+5;
				PosiY=top +height + 2;
			}else if(Posicion=="right"){
				PosiX=left-5;
				PosiY=top + 2;
			}else if(Posicion=="left"){
				PosiX=left+width + 5;
				PosiY=top + 2;
			}else{
				PosiX=left+width + 5;
				PosiY=top + 2;
			}


			/************************
			  SE CREA EL CONTENEDOR PARA LA FLECHA DEL TITLE
			*************************/
			var divFlechaTitle = document.createElement("div");
			divFlechaTitle.id='IdFlechaTitle';
			divFlechaTitle.style.position='absolute';
			divFlechaTitle.style.zIndex='4';
			divFlechaTitle.style.width="12px";
			divFlechaTitle.style.height="16px";
			divFlechaTitle.style.top=PosiY+'px';
			divFlechaTitle.style.left=PosiX+'px';
			//divFlechaTitle.style.filter='alpha(opacity=75)';
			//divFlechaTitle.style.MozOpacity='.75';
			//divFlechaTitle.style.opacity='.75';
			if(efect==true){
				divFlechaTitle.style.display="none";
			}

			/************************
			  SE CREA LA FLECHA DEL TITLE
			*************************/
			var img = document.createElement("img");
			img.src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAQCAYAAAAiYZ4HAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAK1JREFUeNpiDAuLYiABFLEQqVAdiOcDsSUTAYXMQFwNxOdBikEC+GzQBeJFQGyALIjNBjYgbgHiM+iKsdlgBnWrFi5rYTZwAnEvEB/Dpxhmgz0QzwFiFWKCC2SDHBCLEhsRIA2LgVgDiNcTqwEEXgBxEBCHAvFrYjTAwBqobcuI1QAC74A4Goh9gPgpMRpgYCsQawPxLGI1gMBHIE4HYmcgvk+MBhjYB7WtDyDAAJ29F7QVZNGiAAAAAElFTkSuQmCC";
			divFlechaTitle.appendChild(img);
			body.appendChild(divFlechaTitle);

			/************************
			  SE CREA EL DIV QUE CONTENDRA EL TEXTO
			*************************/
			var divFondTitle = document.createElement("div");
			divFondTitle.id='IdAlconTitle';
			divFondTitle.style.color='#ffffff';
			divFondTitle.style.fontFamily='Verdana, Geneva, sans-serif';
			divFondTitle.style.fontSize='12px';
			divFondTitle.style.padding='7px';
			divFondTitle.style.position='absolute';
			divFondTitle.style.zIndex='4';

			//divFondTitle.style.filter='alpha(opacity=75)';
			//divFondTitle.style.MozOpacity='.75';
			//divFondTitle.style.opacity='.75';
			divFondTitle.style.boxShadow='2px 2px 5px #999';

			divFondTitle.style.MozBorderRadius='4px';
			divFondTitle.style.KhtmlBorderRadius='4px';
			divFondTitle.style.WebkitBorderRadius='4px';
			divFondTitle.style.borderRadius='4px';
			if(efect==true){
				divFondTitle.style.display="none";
			}
			divFondTitle.style.backgroundColor="#57575a";
			divFondTitle.style.top=PosiY - 4 +'px';
			divFondTitle.style.left=PosiX+12+'px';
			divFondTitle.innerHTML=text;
			body.appendChild(divFondTitle);

			if(efect==true){
			  $("#IdAlconTitle").fadeIn();
			  $("#IdFlechaTitle").fadeIn();
			}
			return true;
  }

  function alconTitleClose_(){
	  var body 	= document.getElementsByTagName('body').item(0);
		if(document.getElementById("IdAlconTitle")){
			 body.removeChild(document.getElementById("IdAlconTitle"));
		}
		if(document.getElementById("IdFlechaTitle")){
			 body.removeChild(document.getElementById("IdFlechaTitle"));
		}
  }

 /*************************************
  PRECARGA GLOBAL
  AUTOR : LUIS ALCON
  FECHA : 21 DE OCTUBRE DEL 2011
 **************************************/
  function precargaALCON(txtHTML,jqueryEfect,accion,posicion)
  {
	    var efect=jqueryEfect||false;
		var posi=posicion||'top';
        var body 	= document.getElementsByTagName('body').item(0);

	if(accion==0)
	{
		if(document.getElementById("IddivComPrecarALCON")){
			if(efect==true){
			  $("#IddivComPrecarALCON").fadeOut();
			}
			 body.removeChild(document.getElementById("IddivComPrecarALCON"));
		}
	}
			/************************
			  Se crea el contenedor para de la precarga
			*************************/
	   if(accion==1 && !document.getElementById("IddivComPrecarALCON"))
	   {
			var divComPrecarALCON = document.createElement("div");
			divComPrecarALCON.id='IddivComPrecarALCON';
			divComPrecarALCON.style.position='fixed';
			divComPrecarALCON.style.zIndex='500';
			divComPrecarALCON.style.width="110px";
			divComPrecarALCON.style.height="15px";
			divComPrecarALCON.style.fontFamily="Verdana, Geneva, sans-serif";
			divComPrecarALCON.style.color="#000000";
			divComPrecarALCON.style.backgroundColor="#fff1a8";
			divComPrecarALCON.style.fontSize="12px";
			divComPrecarALCON.style.border="#f4e17e solid 1px";
			divComPrecarALCON.style.textAlign="center";
			divComPrecarALCON.style.padding="3px";
			if(posi=="center")
			{
			   divComPrecarALCON.style.top='40%';
			}else{
			  divComPrecarALCON.style.top='0px';
			}
			divComPrecarALCON.style.left='50%';
			divComPrecarALCON.style.MozBoxShadow="0px 0px 10px #000";
			divComPrecarALCON.style.WebkitBoxShadow="0px 0px 10px #000";
			divComPrecarALCON.style.BoxShadow="0px 0px 10px #000";
			//divComPrecarALCON.style.filter='alpha(opacity=85)';
			//divComPrecarALCON.style.MozOpacity='.85';
			//divComPrecarALCON.style.opacity='.85';
			if(efect==true){
				divComPrecarALCON.style.display="none";
			}
			divComPrecarALCON.innerHTML=txtHTML;
			body.appendChild(divComPrecarALCON);

			if(efect==true){
			  $("#IddivComPrecarALCON").fadeIn("slow");
			}
	   }
			return true;
  }




/************************************************************************
ESTILOS PARA LOS checkbox,radio Y select
*************************************************************************/
/*

CUSTOM FORM ELEMENTS

Created by Ryan Fait
www.ryanfait.com

The only things you may need to change in this file are the following
variables: checkboxHeight, radioHeight and selectWidth (lines 24, 25, 26)

The numbers you set for checkboxHeight and radioHeight should be one quarter
of the total height of the image want to use for checkboxes and radio
buttons. Both images should contain the four stages of both inputs stacked
on top of each other in this order: unchecked, unchecked-clicked,
checked, checked-clicked.

You may need to adjust your images a bit if there is a slight vertical
movement during the different stages of the button activation.

The value of selectWidth should be the width of your select list image.

Visit http://ryanfait.com/ for more information.

*/

//var checkboxHeight = "25";
//var radioHeight = "25";
//var selectWidth = "190";
//
//
///* No need to change anything after this */
//
//
//document.write('<style type="text/css">'
//			   +'input.styleControlForm {'
//			   +' display: block;'
//			   +' } '
//			   +'select.styleControlForm { '
//			   +'position: relative; width: ' + selectWidth + 'px; opacity: 0; '
//			   +'filter: alpha(opacity=0); z-index: 5;'
//			   +' }'
//			   +'.disabled { '
//			   +'opacity: 0.5; filter: alpha(opacity=50);'
//			   +' }'
//			   +'</style>');
//
//var Custom = {
//	init: function() {
//		var inputs = document.getElementsByTagName("input"), span = Array(), textnode, option, active;
//		for(a = 0; a < inputs.length; a++) {
//			if((inputs[a].type == "checkbox" || inputs[a].type == "radio") && inputs[a].className == "styleControlForm") {
//				span[a] = document.createElement("span");
//				span[a].className = inputs[a].type;
//				alert("Luis Miguel Alca ");
//				if(inputs[a].checked == true) {
//					if(inputs[a].type == "checkbox") {
//						position = "0 -" + (checkboxHeight*2) + "px";
//						span[a].style.backgroundPosition = position;
//					} else {
//						position = "0 -" + (radioHeight*2) + "px";
//						span[a].style.backgroundPosition = position;
//					}
//				}
//				inputs[a].parentNode.insertBefore(span[a], inputs[a]);
//				inputs[a].onchange = Custom.clear;
//				if(!inputs[a].getAttribute("disabled")) {
//					span[a].onmousedown = Custom.pushed;
//					span[a].onmouseup = Custom.check;
//				} else {
//					span[a].className = span[a].className += " disabled";
//				}
//			}
//		}
//		inputs = document.getElementsByTagName("select");
//		for(a = 0; a < inputs.length; a++) {
//			if(inputs[a].className == "styleControlForm") {
//				option = inputs[a].getElementsByTagName("option");
//				active = option[0].childNodes[0].nodeValue;
//				textnode = document.createTextNode(active);
//				for(b = 0; b < option.length; b++) {
//					if(option[b].selected == true) {
//						textnode = document.createTextNode(option[b].childNodes[0].nodeValue);
//					}
//				}
//				span[a] = document.createElement("span");
//				span[a].className = "select";
//				span[a].id = "select" + inputs[a].name;
//				span[a].appendChild(textnode);
//				inputs[a].parentNode.insertBefore(span[a], inputs[a]);
//				if(!inputs[a].getAttribute("disabled")) {
//					inputs[a].onchange = Custom.choose;
//				} else {
//					inputs[a].previousSibling.className = inputs[a].previousSibling.className += " disabled";
//				}
//			}
//		}
//		document.onmouseup = Custom.clear;
//	},
//	pushed: function() {
//		element = this.nextSibling;
//		if(element.checked == true && element.type == "checkbox") {
//			this.style.backgroundPosition = "0 -" + checkboxHeight*3 + "px";
//		} else if(element.checked == true && element.type == "radio") {
//			this.style.backgroundPosition = "0 -" + radioHeight*3 + "px";
//		} else if(element.checked != true && element.type == "checkbox") {
//			this.style.backgroundPosition = "0 -" + checkboxHeight + "px";
//		} else {
//			this.style.backgroundPosition = "0 -" + radioHeight + "px";
//		}
//	},
//	check: function() {
//		element = this.nextSibling;
//		if(element.checked == true && element.type == "checkbox") {
//			this.style.backgroundPosition = "0 0";
//			element.checked = false;
//		} else {
//			if(element.type == "checkbox") {
//				this.style.backgroundPosition = "0 -" + checkboxHeight*2 + "px";
//			} else {
//				this.style.backgroundPosition = "0 -" + radioHeight*2 + "px";
//				group = this.nextSibling.name;
//				inputs = document.getElementsByTagName("input");
//				for(a = 0; a < inputs.length; a++) {
//					if(inputs[a].name == group && inputs[a] != this.nextSibling) {
//						inputs[a].previousSibling.style.backgroundPosition = "0 0";
//					}
//				}
//			}
//			element.checked = true;
//		}
//	},
//	clear: function() {
//		inputs = document.getElementsByTagName("input");
//		for(var b = 0; b < inputs.length; b++) {
//			if(inputs[b].type == "checkbox" && inputs[b].checked == true && inputs[b].className == "styleControlForm") {
//				inputs[b].previousSibling.style.backgroundPosition = "0 -" + checkboxHeight*2 + "px";
//			} else if(inputs[b].type == "checkbox" && inputs[b].className == "styleControlForm") {
//				inputs[b].previousSibling.style.backgroundPosition = "0 0";
//			} else if(inputs[b].type == "radio" && inputs[b].checked == true && inputs[b].className == "styleControlForm") {
//				inputs[b].previousSibling.style.backgroundPosition = "0 -" + radioHeight*2 + "px";
//			} else if(inputs[b].type == "radio" && inputs[b].className == "styleControlForm") {
//				inputs[b].previousSibling.style.backgroundPosition = "0 0";
//			}
//		}
//	},
//	choose: function() {
//		option = this.getElementsByTagName("option");
//		for(d = 0; d < option.length; d++) {
//			if(option[d].selected == true) {
//				document.getElementById("select" + this.name).childNodes[0].nodeValue = option[d].childNodes[0].nodeValue;
//			}
//		}
//	}
//}
//window.onload = Custom.init;
