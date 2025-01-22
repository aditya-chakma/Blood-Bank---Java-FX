
package Admin;

import Subtasks.updateData;
import Subtasks.matchLogin;
import Subtasks.saveData;
import Subtasks.sendDonor;
import Subtasks.sendMail;
import Subtasks.setNotice;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connector implements Runnable {
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Object obj;
    private final String fileName="information.dat";
    private final String noticeName="notice.dat";
    
    public Connector(Socket sock){
        socket=sock;
        try {
            ois=new ObjectInputStream(sock.getInputStream());
            oos=new ObjectOutputStream(socket.getOutputStream());
            obj=ois.readObject();
        } catch (Exception ex) {
            System.out.println("Unable to receive data from client(Connector.java)\n" +ex);
        }
       
    }

    @Override
    public void run() {
        String data=obj.toString();
        
        System.out.println("Total String received: "+data);
        
        String[] subStrings=data.split(":");
        
        //System.out.println(subStrings[1] + " " + subStrings[2]);
        
        if(subStrings[0].equals("signUp")){
            saveData save=new saveData();
            save.putData(data);
        }else if(subStrings[0].equals("logIn"))
        {
            try {
                matchLogin match=new matchLogin(subStrings[1], subStrings[2]);
                
//                String sh="true@"+match.get();
//                String[] ans=sh.split("@");
//                System.out.println(ans[0] + " " + ans[1]);
                
                if(match.match()) oos.writeObject("true");
                else oos.writeObject("false");
                
            } catch (IOException ex) {
                System.out.println("Unable to send info to client(Connector.java)\n" +ex);
            }
        }else if(subStrings[0].equals("update"))
        {
            String[] up=data.split(">");
            System.out.println("Splited : "+up[0]+ " "+up[1]+ " "+up[2]);
            
            updateData upData=new updateData(up[1], up[2]);
            upData.update();
            
            System.out.println("Data updated successfully");
        }else if(subStrings[0].equals("info"))
        {
            try {
                matchLogin match=new matchLogin(subStrings[1], subStrings[2]);
                match.match();
                String dataToSend="";
                dataToSend=match.get();
                
                //System.out.println("data to send: " + dataToSend);
                
                oos.writeObject(dataToSend);
            } catch (IOException ex) {
                System.out.println("Unable to send data(Connector.java)\n"+ex);
            }
        }else if(subStrings[0].equals("submit"))
        {
            //System.out.println("in submit section");
            Thread t=new Thread(new sendDonor(data));
            t.start();
        }
        else if(subStrings[0].equals("admin")){
            AdminControl adc=new AdminControl();
            boolean ans=adc.AdminLogin(subStrings[1], subStrings[2]);
            
            try {
                oos.writeObject(ans);
            } catch (IOException ex) {
                System.out.println("Unable to wrtie object of Admin(Connector.java)\n"+ex);
            }
        }else if(subStrings[0].equals("recovery")){
            sendMail mail=new sendMail (subStrings[1], subStrings[2],subStrings[3],5);
            Thread t=new Thread(mail);
            t.start();
            
            try {
                t.join();
                boolean ans=mail.getAns();
                oos.writeObject(ans);
            } catch (Exception ex) {
                System.out.println("Recovery password Failed (Connector.java)\n" + ex);
            }
        }else if(subStrings[0].equals("search"))
        {
            File f=new File(fileName);
            
            if(f.exists())
            {
                try {
                    Scanner sc=new Scanner(f);
                    
                    while(sc.hasNext())
                    {
                        String s=sc.nextLine();
                        if(s.length()==0) continue;
                        
                        String[] splited=s.split(":");
                        if(subStrings[1].equalsIgnoreCase(splited[1]))
                        {
                            boolean ans=true;
                            try {
                                oos.writeObject(ans);
                            } catch (IOException ex) {
                                System.out.println("Couldn't send data(Connector.java)\n" + ex);
                            }
                        }
                    }
                   // System.out.println("Mara");
                    
                    boolean ans=false;
                    try {
                        oos.writeObject(ans);
                    } catch (IOException ex) {
                        System.out.println("Unable to send data(Connector.java)\n" + ex);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found@!(Connector.java)\n" + ex);
                }
            }
        }else if(subStrings[0].equals("adminMessage")){
            String[] s=subStrings[1].split(">");
            String userName=s[0];
            String message=s[1];
            String email="";
            
            File f=new File(fileName);
            
            if(f.exists())
            {
                try {
                    Scanner sc=new Scanner(f);
                    
                    while(sc.hasNext())
                    {
                        String scaned=sc.nextLine();
                        if(scaned.length()==0) continue;
                        
                        String[] splited=scaned.split(":");
                        
                        if(splited[1].equalsIgnoreCase(userName))
                        {
                            email=splited[3];
                            break;
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found(Connector.java)\n" + ex);
                    
                    boolean ans=false;
                    try {
                        oos.writeObject(ans);
                    } catch (IOException eex) {
                        System.out.println("Can't send data(Connector.java)\n" + eex);
                    }
                }
            }
            
            sendMail mail=new sendMail(email, message, 6);
            Thread t=new Thread(mail);
            t.start();
            
            try {
                t.join();
            } catch (InterruptedException ex) {
                System.out.println("Thread Interrupted....(Connector.java)\n" + ex);
                
                boolean ans=false;
                try {
                    oos.writeObject(ans);
                } catch (IOException eex) {
                    System.out.println("Can't send data(Connector.java)\n" + eex);
                }
            }
            
            boolean ans=true;
            try {
                oos.writeObject(ans);
            } catch (IOException ex) {
                System.out.println("Can't send data(Connector.java)\n" + ex);
            }
        }else if(subStrings[0].equals("notice"))
        {
            try {
                setNotice notice=new setNotice(subStrings[1]);
                boolean ans=notice.set();
                oos.writeObject(ans);
            } catch (IOException ex) {
                System.out.println("Couldn't send data to client(Connector.java)\n" + ex);
            }
        }else if(subStrings[0].equals("noticeget")){
            File f=new File(noticeName);
            
            if(f.exists())
            {
                try {
                    Scanner sc=new Scanner(f);
                    String s="";
                    while(sc.hasNext()){
                        s+=sc.nextLine();
                    }
                    System.out.println("data got: "+ s);
                    oos.writeObject(s);
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found\n" + ex);
                } catch (IOException ex) {
                    System.out.println("Can't read(Connector.java)\n" + ex);
                }
            }
        }
        
    }
}
