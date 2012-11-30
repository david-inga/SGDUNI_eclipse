// JavaScript Document
	function ShowHide(id,efectoJquery,verNover){/**/
	  var efect=efectoJquery||false;
		if (document.getElementById(''+id+'')){
		  var el = document.getElementById(''+id+'');	
		   if(Number(verNover)){
				if(Number(verNover)==0){
					if(efect==true){				
						$('#'+id).animate({height:"hide",opacity:"hide"},"hide");		
					}else{				
						el.style.display ='none';
					}
		        }else{
					if(efect==true){				
						$('#'+id).animate({height:"show",opacity:"show"},"show");		
					}else{				
						el.style.display ='block';
					}
				}
		   }else{
				if(efect==true){				
					if(el.style.display=='none' || el.style.display==""){
						$('#'+id).animate({height:"show",opacity:"show"},"show");
					}else{
						$('#'+id).animate({height:"hide",opacity:"hide"},"hide");		
					}
				}else{				
					el.style.display = (el.style.display == 'block') ? 'none' : 'block';
				}
		   }
		}
	}/**/
	function getPopUp(el_url,mi_nombre, w, h,scroll,posicion) {//
	   if((posicion) && posicion!=""){
		   var pos=posicion.split(":");
			var winl = (screen.width - w) / Number(pos[0]); 
			var wint = (screen.height - h) / Number(pos[1]); 
	   }else{
		var winl = (screen.width - w) / 2; 
		var wint = (screen.height - h) / 2; 
	   }		
			ancho_alto = 'height='+h+',width='+w+',top='+wint+',left='+winl+',scrollbars='+scroll+'';
			ventan = window.open(el_url, mi_nombre ,ancho_alto);
			if (parseInt(navigator.appVersion) >= 4) { ventan.window.focus(); } 
	}/**/
	function adapIframe(nomIframe) {/**/
		var miIframe=document.getElementById(""+nomIframe);
		var alturaPagina=miIframe.contentWindow.document.body.scrollHeight;
		if(alturaPagina<550){
		  alturaPagina=600;
		}
		miIframe.height=alturaPagina + 40;
	}/**/
	
	/**/
	  /*texto marca de agua para los tex*/
//	  function MarcaAguaText(tx,idObj,clas){
//		  var usoClass=clas||'';
//		  var objText=document.getElementById(''+idObj);
//		  if(trim(objText.value)==""){
//			  objText.value=tx;
//			  if(usoClass!=""){
//			    objText.className=usoClass;
//			  }else{
//				objText.style.color='#B2B2B2';
//			  }
//		  }else if(objText.value==tx){
//			  objText.value='';
//			  if(usoClass!=""){
//			    objText.className=usoClass;
//			  }else{
//				objText.style.color='#333';
//			  }
//		  }  
//	  }
	/**/
/*captura las varibles de la URL */	
function variablesURL(){
	var url = String(window.location.href);
	var params = String(window.location.search);
	var aux1 = new Array();
	var aux2 = new Array();
	var cont = 0;
	var tot = 0;
	//si hay parametros
	if (params.length > 0){
 	    this.varible= new Array();/*acumula las varibles y su valor*/		
		//eliminamos el primer caracter '?'
		params = params.substr(1);		
		aux1 = params.split("&");
		tot = aux1.length;
		for (cont = 0; cont < tot; cont++){
			aux2 = aux1[cont].split("=");
			this.varible[aux2[0]]=aux2[1];			
		}
	} 
}
	

function getFechaComple(){
		var fecha=new Date();
		var diames=fecha.getDate();
		var diasemana=fecha.getDay();
		var mes=fecha.getMonth() +1 ;
		var ano=fecha.getFullYear();
		
		var textosemana = new Array (7); 
		  textosemana[0]="Domingo";
		  textosemana[1]="Lunes";
		  textosemana[2]="Martes";
		  textosemana[3]="Miércoles";
		  textosemana[4]="Jueves";
		  textosemana[5]="Viernes";
		  textosemana[6]="Sábado";
		
		var textomes = new Array (12);
		  textomes[1]="Enero";
		  textomes[2]="Febrero";
		  textomes[3]="Marzo";
		  textomes[4]="Abril";
		  textomes[5]="Mayo";
		  textomes[6]="Junio";
		  textomes[7]="Julio";
		  textomes[8]="Agosto";
		  textomes[9]="Septiembre";
		  textomes[10]="Octubre";
		  textomes[11]="Noviembre";
		  textomes[12]="Diciembre";
		
//		document.write("Fecha completa: " + fecha + "<br>");
//		document.write("Dia mes: " + diames + "<br>");
//		document.write("Dia semana: " + diasemana + "<br>");
//		document.write("Mes: " + mes + "<br>");
//		document.write("Año: " + ano + "<br>");
//		document.write("Fecha: " + diames + "/" + mes + "/" + ano + "<br>");
		document.write("" + textosemana[diasemana] + " " + diames + " de " + textomes[mes] + " del " + ano + "");
//		document.write("Fecha: " + textosemana[diasemana] + ", " + diames + " de " + textomes[mes] + " de " + ano + "<br>");
}
/***************************************
***************************************/
function trim(cadena)
{
  if(cadena){
	for(i=0; i<cadena.length; )
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(i+1, cadena.length);
		else
			break;
	}

	for(i=cadena.length-1; i>=0; i=cadena.length-1){
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(0,i);
		else
			break;
	}
	
	return cadena;
  }else{
	  return '';
	}
}
/*****************************
******************************/
function permite(elEvento, permitidos) {
   // Variables que definen los caracteres permitidos
  var numeros = "0123456789";
  var caracteres = " abcdefghijklmn�opqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ";
  var numeros_caracteres = numeros + caracteres;
  var teclas_especiales = [8, 37, 39, 46];
  // 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha
 
 
  // Seleccionar los caracteres a partir del par�metro de la funci�n
  switch(permitidos) {
    case 'num':
      permitidos = numeros;
      break;
    case 'car':
      permitidos = caracteres;
      break;
    case 'num_car':
      permitidos = numeros_caracteres;
      break;
  }
  // Obtener la tecla pulsada 
  var evento = elEvento || window.event;
  var codigoCaracter = evento.charCode || evento.keyCode;
  var caracter = String.fromCharCode(codigoCaracter);
 
  // Comprobar si la tecla pulsada es alguna de las teclas especiales
  // (teclas de borrado y flechas horizontales)
  var tecla_especial = false;
  for(var i in teclas_especiales) {
    if(codigoCaracter == teclas_especiales[i]) {
      tecla_especial = true;
      break;
    }
  }   
  // Comprobar si la tecla pulsada se encuentra en los caracteres permitidos
  // o si es una tecla especial
  return permitidos.indexOf(caracter) != -1 || tecla_especial;
  
}
/******************************
VALIDACIONES DEL FORMULARIO
*******************************/
function ValidarFormulario(){
	this.NumDecimal=function(numV){//solo numeros
	    if (!/^([0-9])*[.]?[0-9]*$/.test(numV)){
		  return false;
	    }else{
		  return true;
	   }
	}
	
	this.Email=function(valTex){//comprobar correo electronico
	   if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(valTex)){
		  return true;
	   }else{
		  return false; 
	   }
	}

	this.Fecha=function(fecha){//comprobar fecha con formato dd/mm/aaaa
			if (fecha != undefined && fecha != "" ){
				if (!/^\d{2}\/\d{2}\/\d{4}$/.test(fecha)){					    				
					return false;
				}
				var dia  =  parseInt(fecha.substring(0,2),10);
				var mes  =  parseInt(fecha.substring(3,5),10);
				var anio =  parseInt(fecha.substring(6),10);
				switch(mes){
					case 1:
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:
						numDias=31;
						break;
					case 4: case 6: case 9: case 11:
						numDias=30;
						break;
					case 2:
						if (this.comprobarSiBisisesto(anio)){ numDias=29 }else{ numDias=28};
						break;
					default:
						//alert("Fecha introducida errónea");
						return false;
				}
				if (dia>numDias || dia==0){
					//alert("Fecha introducida errónea");
					return false;
				}
				return true;
			}
	
	}
	
	this.comprobarSiBisisesto=function(anio){// saber si el a�os es bisiesto
		if ( ( anio % 100 != 0) && ((anio % 4 == 0) || (anio % 400 == 0))) {
			return true;
	    }else {
			return false;
	    }
	 }		
	
}

function VerificaEmail(email) {  
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
       return (true);
    }
    return (false);
}

    /*******************************************
	FUNCIONES PARA CARGAR ARCHIVOS CSS Y JAVASCRIPT
	*******************************************/	
	function fng_loadCss(urlCss,idCss,target)
	{//CARGA ARCHIVOS CSS
	var t_target=target||'';
		var idCss_=idCss||'myCss';
		var $_al = eval(t_target+'document;');
		var cssId = idCss_;
		if (!$_al.getElementById(cssId))
		{
			var head  = $_al.getElementsByTagName('head')[0];
			var link_  = $_al.createElement('link');
			link_.id   = cssId;
			link_.rel  = 'stylesheet';
			link_.type = 'text/css';
			link_.href = urlCss;
			link_.media = 'all';
			head.appendChild(link_);
		}
	   return true;		
	}
	
	function fng_loadJs(urlJs,carga,target)
	{//CARGA ARCHIVOS JS
	var carga_=1
	var t_target=target||'';
	var $_document = eval(t_target+'document;');
		
	if(carga==0 || carga==1){
		carga_=carga;
	}
		var sc = $_document.getElementsByTagName("script");
		
		if(carga_==1)
		{//para no volver a cargar el scriptssssss			
	  	  for (var x in sc)//AGREGAR SOLO SI NO EXITE include_once();
			if (sc[x].src != null && sc[x].src.indexOf(urlJs) != -1) return false;
		}
		
		var $_al = $_document;
			var head  = $_al.getElementsByTagName('head')[0];
			head|| (head=$_al.body.parentNode.appendChild($_al.createElement(" head")));
			var js_  = $_al.createElement('script');			
			js_.charset  = 'UTF-8';
			js_.type = 'text/javascript';
			js_.src = urlJs;
			head.appendChild(js_);
	   return true;		
	}
	
	//ADICIONA EVENTOS A LOS OBJETOS
		function adicionaEvento(obj,fun,type){ 
			if(obj.addEventListener){ 
				obj.addEventListener(type,fun,false); 
			}else if(obj.attachEvent){ 
				var f=function(){ 
					fun.call(obj,window.event); 
				} 
				obj.attachEvent('on'+type,f); 
				obj[fun.toString()+type]=f; 
			}else{ 
				obj['on'+type]=fun; 
			} 
		}
	// MARCA DE AGUA , DESPUES DE A�ADIR LOS EVENTOS
		function txtMarcaAgua() {
			 if(this.value==this.getAttribute('title')){
				 this.value="";	
				 this.style.color='#333';
			 }else if(trim(this.value)==""){							 
				this.value=this.getAttribute('title');								  
				this.style.color='#B2B2B2';
		   }
		}
		function MarcarDeAgua(){
	       var onj=document.getElementsByTagName('input');
				for(i=0;i<onj.length;i++){
					 if(onj[i].getAttribute('alt') == 'matxt'){
				       adicionaEvento(onj[i],txtMarcaAgua,'focus');
					   adicionaEvento(onj[i],txtMarcaAgua,'blur');
					 }
				}	
		}
    
	//LEER MAS
	function fnl_leerMas(idc,txt){
	 if(document.getElementById(idc)){
		document.getElementById(idc).innerHTML=txt;
	 }
	}

//validar extencion archivo
function fng_validar_archivo(val,tipo,fn)
{
	if(tipo=='imagen')
    {
        extensiones_permitidasadjuntos = new Array(".jpeg",".jpg", ".gif",".png"); 			  
    }
    else if(tipo=='archivo')
    {
        extensiones_permitidasadjuntos = new Array(".doc", ".xls", ".pdf", ".ppt", ".pptx", ".xlsx", ".docx", ".rar", ".zip",".ood",".oxd",".otd",".jpeg",".jpg",".odg");
	}else if(tipo=='todo')
    {
       extensiones_permitidasadjuntos = new Array(".jpg", ".gif",".doc", ".xls", ".pdf", ".ppt", ".pptx", ".xlsx", ".docx", ".rar", ".zip");	
	}	

 	extensionadjuntos = (val.substring(val.lastIndexOf("."))).toLowerCase();
 	nombreadjunto=(val.substring(val.lastIndexOf(".")));
	permitidaadjuntos = false; 
		for (var i = 0; i < extensiones_permitidasadjuntos.length; i++) { 
         	if (extensiones_permitidasadjuntos[i] == extensionadjuntos) { 
         	   permitidaadjuntos = true; 
         	   break; 
         	} 
      	} 
 		if(!val){
        }else{
		   if (!permitidaadjuntos) { 
		       alertALCON("Compruebe la extencion del archivo. \nSolo se pueden subir archivos con extensiones: " + extensiones_permitidasadjuntos.join(),"alert");
			   if(fn){
				   fn()
				}
			  return false; 
		   }else{
			   
		   }
		}
		return true;
}
///seleccionar chek
	  function selectCheckForm(form,obj,cant){
	     eval("var checkTodos=document."+form+".checkTodos;");
		 if(checkTodos.checked){
			 for(var r=1;r<=Number(cant);r++){
		         eval("var objCk=document."+form+"."+obj+""+r+".checked=true;");			 
			 }
		 }else{
			 for(var r=1;r<=Number(cant);r++){
		         eval("var objCk=document."+form+"."+obj+""+r+".checked=false;");
			}
		 }
	  }
//Comparar Fechas
function compare_dates(fecha, fecha2,TipEspa)
      {
        if(fecha2=="")
        {//fecha 2 por defecto
            var fechaAc=new Date();
            fecha2=fechaAc.getDate()+TipEspa+(fechaAc.getMonth() + 1)+TipEspa+fechaAc.getFullYear();
        }
        
        var fechaIni=fecha.split(TipEspa);
        var fechaFin=fecha2.split(TipEspa);
        if(parseInt(fechaIni[2],10)>parseInt(fechaFin[2],10)){
             return(true);
        }else{
            if(parseInt(fechaIni[2],10)==parseInt(fechaFin[2],10))
            {
                   if(parseInt(fechaIni[1],10)>parseInt(fechaFin[1],10))
                   {
                    return(true);
                   }
                   
                if(parseInt(fechaIni[1],10)==parseInt(fechaFin[1],10))
                {

                    if(parseInt(fechaIni[0],10)>parseInt(fechaFin[0],10))
                    {
                        return(true);
                    }else{
                        return(false);
                    }
                }else{
                    return(false);
                }
        }else{
            return(false);
        }
        }
}