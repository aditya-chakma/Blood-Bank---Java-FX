
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


public class done extends Application{
    
    private Stage window;
    private Scene scene;
    
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage alterWindow) throws Exception {
        window=new Stage();
        window.setTitle("Data received");
        window.initModality(Modality.APPLICATION_MODAL);
        
        Label message=new Label("We received your message. We'll try as soon as possible");
        Button back=new Button("go back");
        Button close=new Button("exit");
        
        GridPane layout=new GridPane();
        layout.add(message, 0, 0);
        layout.setVgap(8);
        layout.setAlignment(Pos.CENTER);
        
        GridPane layout1=new GridPane();
        layout1.add(back, 0, 0);
        layout1.add(close, 1, 0);
        layout1.setHgap(10);
        layout1.setAlignment(Pos.CENTER);
        
        BorderPane pane=new BorderPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setTop(layout);
        pane.setBottom(layout1);
        pane.setMinWidth(300);
        
        close.setOnAction(e->{
            alterWindow.close();
            window.close();
        });
        
        back.setOnAction(e->{
            window.close();
        });
        
        scene=new Scene(pane);
        window.setScene(scene);
        window.showAndWait();
        
    }
}
