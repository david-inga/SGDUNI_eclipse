// JavaScript Document
   function formRolUsuario()
   {
       this.in_codigo_usuario=document.getElementById("in_codigo_usuario");
       this.ch_codigo_rol_=document.getElementById("ch_codigo_rol_");
       this.txtnumitem_rol=document.getElementById("txtnumitem_rol");

   }
   //fnl_guardarRolUsuario
   function fnl_guardarRolUsuario()
   {
	   var form=new formRolUsuario();       
	    if(trim(form.in_codigo_usuario.value)=="" || form.in_codigo_usuario.value=="0")
        {
		       alertALCON("Seleccione el nombre del Usuario","alert");
		       form.in_codigo_usuario.focus();
			   return false;
	    }
      document.getElementById("form_rol_usuario").submit();
	  return true;
   }  