
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


public class loginFailedWindow extends Application {
    
    private Stage window;
    private Scene scene;
    
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        Label message=new Label("Username or Password didn't match");
        Button back=new Button("go back");
        Button close=new Button("close");
        
        GridPane top=new GridPane();
        top.add(message, 0, 0);
        top.setAlignment(Pos.CENTER);
        top.setVgap(10);
        
        GridPane bottom=new GridPane();
        bottom.add(back, 0, 0);
        bottom.add(close, 1, 0);
        bottom.setHgap(10);
        bottom.setAlignment(Pos.CENTER);
        
        BorderPane layout=new BorderPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setTop(top);
        layout.setBottom(bottom);
        layout.setMinWidth(250);
        
        back.setOnAction(e->{
            window.close();
        });
        close.setOnAction(e->{
            window.close();
            primaryStage.close();
        });
                
        scene=new Scene(layout);
        window.setScene(scene);
        window.setTitle("Failed to login");
        window.showAndWait();
    }
    
}
