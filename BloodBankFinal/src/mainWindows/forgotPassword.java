package mainWindows;

import bloodbankfinal.serverConnector;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class forgotPassword extends Application{
    
    private Stage window;
    private Scene scene;
    
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10));
        layout.setMinWidth(300);
        
        Label usernameLabel=new Label("username: ");
        TextField username=new TextField();
        username.setPromptText("Enter username");
        
        Label contactLabel=new Label("Contact No.: ");
        TextField contact=new TextField();
        contact.setPromptText("01999999999");
        
        Label emailLabel=new Label("E-mail: ");
        TextField email=new TextField();
        email.setPromptText("example@Example.com");
        
        Button sendMail=new Button("Send mail");
        
        sendMail.setOnAction(e->{
            String message="recovery:";
            message+=username.getText()+ ":" + contact.getText() + ":" + email.getText();
            
            serverConnector sc=new serverConnector(message);
            Thread t=new Thread(sc);
            t.start();
            try {
                t.join();
                
                
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted(forgotPassword.java)\n" + ex);
            }
            
            if(sc.getAnswer())
            {
                mailSent mm=new mailSent();
                try {
                    mm.start(primaryStage);
                } catch (Exception ex) {
                    System.out.println("New window couldn't open...(forgotPassword.java)\n" + ex);
                }
            }
            else{
                failed fail=new failed("Password recovery failed");
                try {
                    fail.start(primaryStage);
                } catch (Exception ex) {
                    System.out.println("New window couldn't open...(forgotPassword.java)\n" + ex);
                }
            }
        });
        
        
        layout.add(usernameLabel, 0, 0);
        layout.add(username, 1, 0);
        layout.add(contactLabel, 0, 1);
        layout.add(contact, 1, 1);
        layout.add(emailLabel, 0, 2);
        layout.add(email, 1, 2);
        layout.add(sendMail, 1, 3);
        
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(8);
        
        window.setTitle("Password recovery");
        scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
}
