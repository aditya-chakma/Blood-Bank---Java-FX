/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subtasks;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author User
 */
public class sendMail implements Runnable {
    
     String to="";
     final String from="projectbloodbank@gmail.com";
     final String username="projectbloodbank";
     final String password="AdityaChakma1505120";
     String host="localhost";
     int code=0;
     String date;
     String name="";
     String HospitalName="";
    
     
     public sendMail(String to, String name, String HospitalName,int code,String date){
         this.to=to;
         this.name=name;
         this.date=date;
         this.HospitalName=HospitalName;
         this.code=code;
     }
    
    public static void main(String[] args) {
//        to="adityachakma199@gmail.com";
//        from="aditya_chakma@yahoo.com";
//        host="localhost";
        sendMail s=new sendMail("adityachakma199@gmail.com", "Aditya", "Dhaka medical", 1, "in 2 days");
        s.send();
    }
    
    public  void send(){
        
        /*Properties properties=System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        
        Session session=Session.getDefaultInstance(properties);
        
        try {
         // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("Test");

         // Now set the actual message
         message.setText("Testing...");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }*/
        
        this.to=to;


        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
           }
        });

        try {

           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(from));
           message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));

           
           if(code==1){
           message.setSubject("Blood needed");

           message.setText("Dear, " + name + " a person has wanted blood within " + date + ", in " + HospitalName + ". Can you manage to give blood? Please ensure us as soon as possible :)" );
           }
           else if(code==2){

           message.setSubject("Blood managed");

           message.setText("Dear, " + name + ". You'r blood has been managed. Thank you :) ");
           }
           else if(code==3){
               message.setSubject("Notify");
               message.setText("Dear, " + name + ". We will notify you as soon as the blood is managed!");
           }
           else if(code==4){
               message.setSubject("Welcome");
               message.setText("Dear " + name + " welcome to blood donors family");
           }

           Transport.send(message);

           System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
     }

    @Override
    public void run() {
        send();
    }
      
 }

