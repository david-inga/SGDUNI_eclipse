// JavaScript Document
/*****************************
EDITOR HTML BASICO
AUTOR : LUIS ALCANTARA CONTRERAS
FECHA : 21-07-2011
V. 1.0
*****************************/
/*
 n=negrita
 i=cursiva
 s=subrrayado
 ancla=anclaje
 link=enlaces
*/
function instagHTML(tag){
	var input = document.getElementById('txtareahtmlalcon');
	if(typeof document.selection != 'undefined' && document.selection) 
	{
		var str = document.selection.createRange().text;
		input.focus();
	    var sel = document.selection.createRange();
	    sel.text = "[" + tag + "]" + str + "[/" +tag+ "]";
	    sel.select();
	    return;
	}
	else if(typeof input.selectionStart != 'undefined')
	{
		var start = input.selectionStart;
		var end = input.selectionEnd;
		var insText = input.value.substring(start, end);
		input.value = input.value.substr(0, start) + '['+tag+']' + insText + '[/'+tag+']'+ input.value.substr(end);
		input.focus();
		input.setSelectionRange(start+2+tag.length+insText.length+3+tag.length,start+2+tag.length+insText.length+3+tag.length);
		return;
	}
	else
	{
		input.value+=' ['+tag+'].....[/'+tag+']';
		return;
	}
}
/**/
function instagLinkHTML()
{
		var input = document.getElementById('txtareahtmlalcon');
		if(typeof document.selection != 'undefined' && document.selection) {
		var str = document.selection.createRange().text;
		input.focus();
		var my_link = prompt("Enter URL:","http://");
		if (my_link != null) {
		if(str.length==0){
		str=my_link;
		}
		var sel = document.selection.createRange();
		sel.text = "[a href=\"" + my_link + "\"]" + str + "[/a]";
		sel.select();
		}
		return;
		}else if(typeof input.selectionStart != 'undefined'){
		var start = input.selectionStart;
		var end = input.selectionEnd;
		var insText = input.value.substring(start, end);
		var my_link = prompt("Enter URL:","http://");
		if (my_link != null) {
		if(insText.length==0){
		insText=my_link;
		}
		input.value = input.value.substr(0, start) +"[a href=\"" + my_link +"\"]" + insText + "[/a]"+ input.value.substr(end);
		input.focus();
		input.setSelectionRange(start+11+my_link.length+insText.length+4,start+11+my_link.length+insText.length+4);
		}
		return;
		}else{
		var my_link = prompt("Ingresar URL:","http://");
		var my_text = prompt("Ingresar el texto del link:","");
		input.value+=" [a href=\"" + my_link + "\"]" + my_text + "[/a]";
		return;
	}
}

function falconHtml(tipo,obj){
	switch(tipo){
		case 'link':
		   dBoton(obj);
		   instagLinkHTML();		  
		break;
		case 'ancla':		  
		  alert("Falta pensar???");
		break;
		default:
		 dBoton(obj);
		 instagHTML(tipo);
		break;
	}
}

function dBoton(obj){//diseño 
   if(obj.dir=='0'){//SE ACTIVA
       //obj.dir='1';//SE DESACTIVA
	   //obj.style.backgroundPosition='0px 0px';
   }else{
	   //obj.dir='0'; 
	   //obj.style.backgroundPosition='0px -21px';
   }
}