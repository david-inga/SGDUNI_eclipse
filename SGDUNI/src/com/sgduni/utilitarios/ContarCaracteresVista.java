/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.utilitarios;

/**
 *
 * @author Sistemas
 */
public class ContarCaracteresVista {
  private String texto;

  public ContarCaracteresVista(String txt){
      this.texto=txt;
  }

  public String getTexMax(int numTexMax,String txtNext)
  {
      int numCaracter=this.texto.length();
      System.out.println("Num Caracteres = "+numCaracter);
      String txtg = null;

      if(txtNext == null) 
          txtNext="....";

      if(numCaracter>numTexMax)
      {
          txtg=this.texto.substring(0,numTexMax)+""+txtNext;
      }
      else
      {
          txtg=this.texto;
      }

      if(numCaracter == 0)
      {
          txtg = "";
      }
      
      return txtg;
  }
}
