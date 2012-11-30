function fnl_valid_login()
{
	var user=document.getElementById('vc_usuario');
	var clav=document.getElementById('vc_clave');
	if(trim(user.value)==""){alertALCON("Escriba el nombre de usuario","alert");user.focus();return false;}
	if(trim(clav.value)==""){alertALCON("Escriba la contraseña","alert");clav.focus();return false;}
	document.getElementById("formLogn").submit();
}

function presskey(oEvent)
{
if (oEvent.keyCode == 13)
{
 fnl_valid_login();
}
} 
