/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.utilitarios;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class FormatoFecha {
    private String fechWebTXT;
   public FormatoFecha()
   {
   }
   
   public String getFormatoFecha(Date fecha ,String Tipo)
   {
    if(Tipo.equals(null))
      Tipo="DD MMM yyyy";
      SimpleDateFormat fechWeb=new SimpleDateFormat(Tipo);
      fechWebTXT=fechWeb.format(fecha);
      return fechWebTXT;
   }

   public String getFormatoFecha(Date fecha)
   {

      SimpleDateFormat fechWeb=new SimpleDateFormat("DD MMM yyyy");
      fechWebTXT=fechWeb.format(fecha);
      return fechWebTXT;
   }

   public static String getFechaFormateada(String fecha)
   {
      String [] partes =  fecha.split("-") ;

      String anio = partes[0];
      int mes = Integer.parseInt( partes[1] );
      String nomMes = "";
      String dia = partes[2];

      switch(mes)
      {
          case 1 : nomMes = "Enero"     ;break;
          case 2 : nomMes = "Febrero"   ;break;
          case 3 : nomMes = "Marzo"     ;break;
          case 4 : nomMes = "Abril"     ;break;
          case 5 : nomMes = "Mayo"      ;break;
          case 6 : nomMes = "Junio"     ;break;
          case 7 : nomMes = "Julio"     ;break;
          case 8 : nomMes = "Agosto"    ;break;
          case 9 : nomMes = "Setiembre" ;break;
          case 10 : nomMes = "Octubre"  ;break;
          case 11 : nomMes = "Noviembre";break;
          case 12 : nomMes = "Diciembre";break;
          default: nomMes = "no existe" ;break;

      }

      return dia+" de "+nomMes+" del "+anio;
    }
}
