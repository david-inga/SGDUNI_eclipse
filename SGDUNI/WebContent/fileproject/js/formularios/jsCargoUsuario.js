// JavaScript Document
   function formCargoUsuario()
   {
       this.in_codigo_usuario = document.getElementById("in_codigo_usuario");
       this.in_codigo_cargo_  = document.getElementById("in_codigo_cargo_");
       this.txtnumitem_cargo  = document.getElementById("txtnumitem_cargo");

   }
   //fnl_guardarRolUsuario
   function fnl_guardarCargoUsuario()
   {
	   var form = new formCargoUsuario();
	    if(trim(form.in_codigo_usuario.value)=="" || form.in_codigo_usuario.value=="0")
        {
		       alertALCON("Seleccione el nombre del Usuario","alert");
		       form.in_codigo_usuario.focus();
			   return false;
	    }
      document.getElementById("form_cargo_usuario").submit();
	  return true;
   }  

