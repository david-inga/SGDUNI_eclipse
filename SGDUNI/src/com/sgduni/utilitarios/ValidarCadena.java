/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.utilitarios;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */
public class ValidarCadena
{
    public static boolean isNumeric(String cadena)
    {
      try
      {
        Integer.parseInt(cadena);
        return true;
      }
      catch(NumberFormatException e)
      {
        return false;
      }
    }

}
