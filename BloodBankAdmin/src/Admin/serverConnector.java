
package Admin;

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
            if(subStrings[0].equals("admin"))
            {
                try {
                    message=(boolean) ois.readObject();

                } catch (ClassNotFoundException ex) {
                    System.out.println("Can't read from server(serverConnector.jaja-Admin)\n" + ex);
                }
            }else if(subStrings[0].equals("search"))
            {
                try {
                    message=(boolean) ois.readObject();

                } catch (ClassNotFoundException ex) {
                    System.out.println("Can't read from server(serverConnector.jaja-Admin)\n" + ex);
                }
            }else if(subStrings[0].equals("adminMessage"))
            {
                try {
                    message=(boolean) ois.readObject();
                } catch (ClassNotFoundException ex) {
                    System.out.println("Can't read data (serverConnector.java)" + ex);
                }
            }else if(subStrings[0].equals("notice")){
                try {
                    message=(boolean) ois.readObject();
                } catch (ClassNotFoundException ex) {
                    System.out.println("Can't read data (serverConnector.java)" + ex);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Client Interrupted\n" + e);
            message=false;
        }
    }
    

    
    public boolean getAnswer(){
        return message;
    }
    
}
