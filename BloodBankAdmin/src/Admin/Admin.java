package Admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import subWindow.loginFailedWindow;
import subWindow.searchMember;


public class Admin extends Application{

    private Stage window;
    private Scene scene;
    private String windowTitle="Admin";
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        
        GridPane centre=new GridPane();
        centre.setVgap(15);
        centre.setHgap(10);
        centre.setPadding(new Insets(10,10,10,10));
        
        
        Label adminLabel=new Label("Admin: ");
        TextField admin=new TextField();
        admin.setPromptText("Admin username");
        admin.setMinWidth(200);
        
        Label passwordLabel=new Label("Password: ");
        PasswordField password=new PasswordField();
        password.setPromptText("Admin password");
        
        
        Button login=new Button("login");
        Label forgot=new Label("Forgot password? ");
        Button contact=new Button("Send mail to developers");
        
        login.setOnAction(e->{
            AdminControl adminC=new AdminControl();
            String data="admin:";
            data+=admin.getText() + ":" + password.getText();
            if(admin.getText().trim().isEmpty() || password.getText().trim().isEmpty())
            {
                
            }else {
                serverConnector svc=new serverConnector(data);
                Thread t=new Thread(svc);
                
                t.start();
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    System.out.println("Thread Interrupted(Admin.java)\n"+ex);
                }
                
                if(svc.getAnswer()) {
                    System.out.println("Login Successfull");
                    searchMember member=new searchMember();
                    try {
                        member.start(window);
                        window.close();
                    } catch (Exception ex) {
                        System.out.println("Can't open new window");
                    }
                    
                }
                else {
                    loginFailedWindow logf=new loginFailedWindow();
                    try {
                        logf.start(window);
                    } catch (Exception ex) {
                        System.out.println("Can't open new window");
                    }
                }
                
            }
        });
        
        centre.add(adminLabel, 0, 0);
        centre.add(admin, 1, 0);
        centre.add(passwordLabel, 0, 1);
        centre.add(password, 1, 1);
        centre.add(login, 1, 2);
//        centre.add(forgot, 0, 3);
//        centre.add(contact, 0, 4);
        
        centre.setAlignment(Pos.CENTER);
        
        scene=new Scene(centre);
        window.setTitle(windowTitle);
        window.setScene(scene);
        window.show();
    }
    
}
