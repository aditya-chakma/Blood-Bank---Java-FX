
package Admin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {
    
    int port=12345;
    
    

    public Server(int p) {
        port=p;
    }
    
    public Server(){
        
    }

    @Override
    public void run() {
        
        
        try {
            ServerSocket serve=new ServerSocket(port);
            
            while(true){
                Socket socket=serve.accept();
                
                System.out.println("Client connected ->");
                
                Thread con=new Thread(new Connector(socket));
                con.start();
            }
        } catch (IOException ex) {
            System.out.println("Exception in main server(server.java)\n" + ex);
        }
    }
    
}
