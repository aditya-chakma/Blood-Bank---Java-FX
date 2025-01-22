package mainWindows;

import bloodbankfinal.BloodBankFinal;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainWindow {

    private Stage windowMain;
    private ImageView top;
    private ImageView middle;
    private ImageView bottom;
    String[] args;
    
    public MainWindow() {
        top= new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Blood_donors_day_boy.jpg")));
        bottom=new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Donor_quote.jpg")));
        middle=new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Side_Image8.jpg")));
        //launch();
    }

    
    public void start() throws Exception {
        windowMain=new Stage();
        
        VBox layout=new VBox();
        //layout.getChildren().add(top);
//        top.setX(800);top.setY(420);
//        middle.setX(800);middle.setY(578);
//        bottom.setX(800);bottom.setY(280);

        top.setFitHeight(250);
        top.setFitWidth(500);
        middle.setFitHeight(300);middle.setFitWidth(500);
        bottom.setFitHeight(250);
        bottom.setFitWidth(500);
        
        layout.getChildren().addAll(top,middle,bottom);
        
        Scene scene=new Scene(layout);
        
        windowMain.setTitle("Blood Bank");
        windowMain.setScene(scene);
        windowMain.show();
    }

   
    
}
