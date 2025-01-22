/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subWindow;

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

/**
 *
 * @author User
 */
public class successfull extends Application {
    
    private Stage window;
    private Scene scene;
    private String msg="";
    
    public static void main(String[] args) {
        launch(args);
    }

    public successfull(String s) {
        msg=s;
    }

    public successfull() {
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        Label message=new Label(msg);
        Button ok=new Button("Ok");
        Button close=new Button("Close");
        
        GridPane layout1=new GridPane();
        layout1.setVgap(10);
        layout1.add(message, 0, 0);
        layout1.setAlignment(Pos.CENTER);
        
        GridPane layout2=new GridPane();
        layout2.setHgap(10);
        layout2.add(ok, 0, 0);
        layout2.add(close, 1, 0);
        layout2.setAlignment(Pos.CENTER);
        
        BorderPane layout=new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setTop(layout1);
        layout.setBottom(layout2);
        layout.setMinWidth(250);
        
        ok.setOnAction(e->{
            window.close();
        });
        
        close.setOnAction(e->{
            window.close();
            primaryStage.close();
        });
        
        scene=new Scene(layout);
        window.setTitle("Confirmation");
        window.setScene(scene);
        window.showAndWait();
    }
    
}
