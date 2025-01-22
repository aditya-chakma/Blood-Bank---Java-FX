
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class searchMember extends Application {
    
    private String userName;
    private Stage window;
    private Scene scene;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=new Stage();
        
        Label userLabel=new Label("username: ");
        TextField user=new TextField();
        user.setPromptText("Enter members username");
        user.setMaxWidth(250);
        
        Button search=new Button("Search");
        
        Label noticeLabel=new Label("Notice: ");
        TextArea notice=new TextArea();
        notice.setPromptText("Set a notice");
        
        Button set=new Button("Set");
        
        GridPane layout=new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        
        Button nameButton=new Button();
        
        layout.add(userLabel, 0, 0);
        layout.add(user, 1, 0);
        layout.add(search, 1, 1);
        layout.add(noticeLabel, 0, 3);
        layout.add(notice, 1, 3);
        layout.add(set, 1, 4);
        
        search.setOnAction(e->{
            String s="search:";
            if(!user.getText().trim().isEmpty()){
                s+=user.getText();
                serverConnector sConnector=new serverConnector(s);
                Thread t=new Thread(sConnector);
                t.start();
                
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    System.out.println("Thread Interrupted...(searchMember.java)\n" + ex);
                }
                
                if(sConnector.getAnswer()){
                    System.out.println("found");
                    
                    Label searchResult=new Label("Search Result:");
                    //Button nameButton=new Button(user.getText());
                    //layout.add(searchResult, 0, 2);
                    nameButton.setText(user.getText());
                    layout.add(nameButton, 1, 2);
                }else{
                    System.out.println("Didn't find");
                }
            }
            
        });
        
        nameButton.setOnAction(e->{
            memberContact mContact=new memberContact(user.getText());
            try {
                //            Thread t=new Thread(mContact);
//            t.start();
            mContact.start(window);
            window.close();
            } catch (Exception ex) {
                System.out.println("Can't start new window(searchMember.java)\n" + ex);
            }
        });
        
        set.setOnAction(e->{
            String data="notice:";
            if(!notice.getText().trim().isEmpty()){
                data+=notice.getText();
                serverConnector sConnector=new serverConnector(data);
                
                Thread t=new Thread(sConnector);
                t.start();
                
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    System.out.println("Thread interrupted(searchMember.java)\n" + ex);
                }
                if(sConnector.getAnswer()){
                    try {
                        successfull sf=new successfull("Notice set");
                        sf.start(window);
                    } catch (Exception ex) {
                        System.out.println("Thread error! Can't open window(searchMember.java)\n" + ex);
                    }
                }else{
                    try {
                        successfull sf=new successfull("Unable to set Notice");
                        sf.start(window);
                    } catch (Exception ex) {
                        System.out.println("Thread error! Can't open window(searchMember.java)\n" + ex);
                    }
                }
            }
            
        });
        
        scene=new Scene(layout);
        window.setTitle("Admin");
        window.setScene(scene);
        window.show();
        
    }
    
}
