
package Admin;


public class serverRunner {
    
    public static void main(String[] args) {
        Thread serv=new Thread(new Server(12345));
        serv.start();
        
        try {
            serv.join();
        } catch (InterruptedException e) {
            System.out.println("Server Interrupted(serverRunner.java)\n" + e);
        }
    }
    
}
