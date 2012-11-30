/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sgduni.utilitarios;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Programmer : Marco A. Estrella Cardenas
 */
public class GestorDeEmails
{

    public GestorDeEmails() 
    {
        
    }

    public String cargarMensaje(String dependencia,String de,String mensaje)
    {
       String cuerpo =
                "<p style=\"text-align: center;\"><span style=\"font-size: medium;\"><span style=\"font-family: arial, helvetica, sans-serif; background-color: #ffffff; color: #0000ff;;\">SISTEMA DE GESTION DOCUMENTARIA UNI<br/> OCDO </span></p> "+
                "<p style=\"text-align: left;\">  <span style=\"font-family: arial, helvetica, sans-serif; font-size: small; background-color: #ffffff; color: #ff0000;\">Dependencia: </span> "+dependencia+" </p> "+
                "<p style=\"text-align: left;\">  <span style=\"font-family: arial, helvetica, sans-serif; font-size: small; background-color: #ffffff; color: #ff0000;\">De: </span>"+de+"</p> "+
                "<p style=\"text-align: left;\">  <span style=\"font-family: arial, helvetica, sans-serif; background-color: #ffffff; color: #000000; font-size: small;\">"+mensaje+"</span></p> "+
                "<p style=\"text-align: center;\"><span style=\"color: #ff0000; font-family: arial, helvetica, sans-serif; font-size: small;\"><br /></span></p> "+
                "<p style=\"text-align: center;\"><span style=\"color: #ff0000; font-family: arial, helvetica, sans-serif; font-size: xx-small;\">Es necesario entrar al sistema para atender el asunto. <br/> atentamente. <br/> Oficina Central de Desarrollo Organizacional <br/><br/> Porfavor no responder <br/>Este mensaje fue enviado por el sistema</span></p>";
        return cuerpo;
    }


    public boolean enviarCorreoElectronico(String receptor,String asunto,String cuerpo)
    {
        boolean estado = false;

        Properties props = new Properties();

        props.setProperty("mail.transport.protocol" , "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth","true");
        props.setProperty("mail.smtp.starttls.enable","true");
        props.setProperty("mail.smtp.user", "sistemaocdo@gmail.com");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);

        try
        {
            message.setFrom(new InternetAddress("sistemaocdo@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));

            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html");

            Transport t = session.getTransport("smtp");
            t.connect("sistemaocdo@gmail.com","sgduni2012");
            t.sendMessage(message,message.getAllRecipients());

            estado = true;
            System.out.println("Correo Enviado exitosamente!");

            t.close();
        }
        catch (Exception e)
        {
            estado = false;
            System.out.println("error al enviar el correo electronico : "+e);
        }
        return estado;
    }

    public boolean enviarCorreoElectronicoMuchosReceptores(ArrayList<String> receptores,String asunto,String cuerpo)
    {
        boolean estado = false;

        Properties props = new Properties();

        props.setProperty("mail.transport.protocol" , "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth","true");
        props.setProperty("mail.smtp.starttls.enable","true");
        props.setProperty("mail.smtp.user", "sistemaocdo@gmail.com");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);

        try
        {


            message.setFrom(new InternetAddress("sistemaocdo@gmail.com"));

            InternetAddress[] addressTo = new InternetAddress[receptores.size()];
            for (int i = 0; i < receptores.size(); i++)
            {
                addressTo[i] = new InternetAddress(receptores.get(i));
            }

            message.setRecipients(RecipientType.TO, addressTo);
            
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html");

            Transport t = session.getTransport("smtp");
            t.connect("sistemaocdo@gmail.com","sgduni2012");
            t.sendMessage(message,message.getAllRecipients());

            estado = true;
            System.out.println("Correo Enviado exitosamente!");

            t.close();
        }
        catch (Exception e)
        {
            estado = false;
            System.out.println("error al enviar el correo electronico : "+e);
        }
        return estado;
    }
}
