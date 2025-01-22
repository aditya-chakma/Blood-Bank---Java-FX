
package Subtasks;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class confirmationBox {
    
    private static Stage window;
    private static boolean result;

    
    
    public static boolean confirm(String title, String message){
        
        window=new Stage();
        window.setTitle(title);
        window.setMinWidth(325);
        window.initModality(Modality.APPLICATION_MODAL);
        
        Label warning=new Label();
        warning.setAlignment(Pos.CENTER);
        warning.setText(message);
        
        Button yes=new Button("Yes");
        Button no=new Button("No");
        
        yes.setOnAction(e-> {
            result=true;
            window.close();
        });
        no.setOnAction(e->{
            result=false;
            window.close();
        });
        
        GridPane layoutLabel=new GridPane();
        layoutLabel.add(warning, 0, 0);
        layoutLabel.setAlignment(Pos.CENTER);
        GridPane layoutButtons=new GridPane();
        layoutButtons.setHgap(5);
        layoutButtons.add(yes, 0, 1);
        layoutButtons.add(no, 1, 1);
        layoutButtons.setAlignment(Pos.CENTER);
        
        BorderPane layout=new BorderPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setTop(layoutLabel);
        layout.setBottom(layoutButtons);
        
        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return result;
    }
    
    public boolean defaultMessage(){
        window=new Stage();
        window.setTitle("Confirm Exit");
        window.setMinWidth(325);
        window.initModality(Modality.APPLICATION_MODAL);
        
        Label warning=new Label();
        warning.setAlignment(Pos.CENTER);
        warning.setText("Are you Sure?");
        
        Button yes=new Button("Yes");
        Button no=new Button("No");
        
        yes.setOnAction(e-> {
            result=true;
            window.close();
        });
        no.setOnAction(e->{
            result=false;
            window.close();
        });
        
        GridPane layoutLabel=new GridPane();
        layoutLabel.add(warning, 0, 0);
        layoutLabel.setAlignment(Pos.CENTER);
        GridPane layoutButtons=new GridPane();
        layoutButtons.setHgap(5);
        layoutButtons.add(yes, 0, 1);
        layoutButtons.add(no, 1, 1);
        layoutButtons.setAlignment(Pos.CENTER);
        
        BorderPane layout=new BorderPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setTop(layoutLabel);
        layout.setBottom(layoutButtons);
        
        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return result;
    }
    
}
