
package mainWindows;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class resistration extends Application{
    Stage window;
    Scene scene;
    Stage alterWindow;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=new Stage();
        alterWindow=primaryStage;
        window.initModality(Modality.APPLICATION_MODAL);
        
        Label confirm=new Label("Registration completed");
        Button back=new Button("Ok");
        Button close=new Button("Close");
        
        back.setOnAction(e->{
            try {
                signupWindow s=new signupWindow();
                alterWindow.close();
                s.start(window);
                window.close();
            } catch (Exception ex) {
                System.out.println("Couldn't set window to signUpWindow\n"+ex);
            }
        });
        close.setOnAction(e->{
            try {
                window.close();
                alterWindow.close();
            } catch (Exception ex) {
            }
        });
        
        
        GridPane top=new GridPane();
        top.add(confirm, 0, 0);
        GridPane bottom=new GridPane();
        bottom.add(back, 0, 0);
        bottom.add(close, 1, 0);
        top.setAlignment(Pos.CENTER); bottom.setAlignment(Pos.CENTER); bottom.setHgap(10);
        
        BorderPane layout=new BorderPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setMinWidth(250);
        layout.setTop(top);
        layout.setBottom(bottom);
        
        scene=new Scene(layout);
        window.setScene(scene);
        window.setTitle("Confirm");
        window.showAndWait();
    }
}
