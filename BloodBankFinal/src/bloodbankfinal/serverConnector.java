
package bloodbankfinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverConnector implements Runnable{
    
    String ip="localhost";
    int port=12345;
    Socket socket;
    String data="";
    boolean message=false;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    String s="";
    
    public serverConnector(String data){
        this.data=data;
       
    }

    @Override
    public void run() {
        //System.out.println("Hello");
        try {
            socket=new Socket(ip,port);
            System.out.println("Connected to server ->");
            
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
            
            oos.writeObject(data);
            
            System.out.println("Data sent to server successfully.");
            
            //System.out.println(data);
            
            String[] subStrings=data.split(":");
            if(subStrings[0].equals("logIn"))
            {
                //System.out.println("You're about to attempt login");
                
                try {
                    Object ob=ois.readObject();
                    //String[] splited=ob.toString().split("?");
                    //s=splited[1];
                    if(ob.toString().equals("true")) message=true;
                    //System.out.println(message);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Unable to read Data\n" +ex);
                }
                
            }else if(subStrings[0].equals("info"))
            {
                try {
                    Object ob=ois.readObject();
                    
                    System.out.println("server: " + ob.toString());
                    
                    s=ob.toString();
                } catch (Exception e) {
                    System.out.println("Can't connect or read data\n" + e);
                }
            }
            else if(subStrings[0].equals("recovery"))
            {
                try {
                    message=(boolean) ois.readObject();
                } catch (ClassNotFoundException ex) {
                    System.out.println("Can't read data from server(Connector.java)\n" + ex);
                }
            }if(subStrings[0].equals("noticeget")){
                try {
                    s=ois.readObject().toString();
                    
                } catch (ClassNotFoundException ex) {
                    System.out.println("Can't read Object(serverConnector.java)\n" + ex);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Client Interrupted\n" + e);
        }
    }
    
    public String getData(){
        return s;
    }
    
    public boolean getAnswer(){
        return message;
    }
    
}
