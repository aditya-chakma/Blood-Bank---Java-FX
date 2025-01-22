package subWindow;

import Admin.serverConnector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class memberContact extends Application implements Runnable{
    
    private Stage window;
    private Scene scene;
    private String username="aditya29";
    
    public static void main(String[] args) {
        launch(args);
    }

    public memberContact(String userName) {
        username=userName;
    }

    public memberContact() {
    }
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=new Stage();
        
        Label userLabel=new Label("User name: ");
        Label user=new Label(username);
        
        TextArea mail=new TextArea();
        mail.setPromptText("Type your message here!");
        
        Button send=new Button("send");
        
        send.setOnAction( e->{
            String message="adminMessage:";
            message+=username+ ">" + mail.getText();
            serverConnector sConnector=new serverConnector(message);
            Thread t=new Thread(sConnector);
            t.start();
            
            try {
                t.join();
            } catch (InterruptedException ex) {
                System.out.println("Thread Interrupted(memberContact.java)\n" + ex);
            }
            
            if(sConnector.getAnswer()){
                successfull sf=new successfull("Mail sent Successfully");
                try {
                sf.start(window);
                } catch (Exception ex) {
                    System.out.println("Unable to open new window(memberContact.java)\n" + ex);
                }
            }else{
                successfull sf=new successfull("Can't connect to server");
                try {
                sf.start(window);
                } catch (Exception ex) {
                    System.out.println("Unable to open new window(memberContact.java)\n" + ex);
                }
            }
            
        });
        
        
        
        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(8);
        layout.setHgap(8);
        
        layout.add(userLabel, 0, 0);
        layout.add(user, 1, 0);
        layout.add(mail, 1, 2);
        layout.add(send, 1, 3);
        
        window.setTitle("Send mail");
        scene=new Scene(layout);
        window.setScene(scene);
        window.show();     
    } 

    @Override
    public void run() {
        try {
            start(window);
        } catch (Exception ex) {
            System.out.println("Error in thread(memberContact.java)\n" + ex);
        }
    }
}
