
package mainWindows;

import Subtasks.closeProgram;
import bloodbankfinal.BloodBankFinal;
import bloodbankfinal.serverConnector;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class memberDetail extends Application {
    
    
    Stage window;
    private Scene scene;
    //private Image backImage;
    private ImageView logo;
    public static String data;
    private ImageView icon;
    private String notice="notice demo";
    
    
    public static void main(String[] args) {
        data="signUp:aditya29:12345:aditya_chakma@yahoo.com:A+:Dhaka:asdfadsf:23534513";
        launch(args);
    }

 
    public void setData(String data){
        this.data=data;
    }
    public void setNotice(String notice){
        this.notice=notice;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        window=new Stage();
        window.setOnCloseRequest(e-> {
            e.consume();
            if(closeProgram.close("Confirm Exit", "Are you Sure?"))
                window.close();
        });
        
        
//        backImage=new Image(BloodBankFinal.class.getResourceAsStream("images/bdonate.jpg"));
//        BackgroundSize backSize=new BackgroundSize(0, 0, true, true, true, true);
//        BackgroundImage background=new BackgroundImage(backImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,backSize);
        
        logo=new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Blood_bank_logo.png")));
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        
        icon= new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Go-back-icon.png")));
        icon.setFitHeight(100);
        icon.setFitWidth(100);
        
        String[] info=data.split(":");
        //signUp:aditya29:12345:aditya_chakma@yahoo.com:A+:Dhaka:palashi,Buet:01521429165
        
        Label usernameLabel=new Label("UserName: ");
        Label passwordLabel=new Label("Password: ");
        Label e_mailLabel=new Label("E-mail: ");
        Label bloodLabel=new Label("Blood-Group: ");
        Label addressLabel=new Label("Address: ");
        Label contactLabel=new Label("Contact No: ");
        
        TextField username=new TextField();
        username.setText(info[1]);
        PasswordField password=new PasswordField();
        password.setText(info[2]);
        //password.setPromptText("Enter new password to change");
        Label email=new Label(info[3]);
        Label blood=new Label(info[4]);
        TextField address=new TextField();
        address.setText(info[6]);
        TextField contact=new TextField(info[7]);
        
        GridPane layout1=new GridPane();
        Label noticeLabel=new Label("Notice: ");
        TextArea noticeArea=new TextArea(notice);
        noticeArea.getStyleClass().add("text-input");
        noticeArea.setEditable(false);
        noticeArea.setWrapText(true);
        layout1.add(noticeLabel, 0, 0);
        layout1.add(noticeArea, 1, 0);
        layout1.setAlignment(Pos.CENTER);
        layout1.setHgap(8);
        
        GridPane layout2=new GridPane();
        //layout2.setPadding(new Insets(10,10,10,10));
        layout2.setVgap(10);
        layout2.setHgap(15);
        layout2.setAlignment(Pos.CENTER);
        //layout.setBackground(new Background(background));
        
        Button update=new Button("Update");
        
        layout2.add(logo, 1, 0);
        layout2.add(usernameLabel, 0, 1);
        layout2.add(username, 1, 1);
        layout2.add(passwordLabel, 0, 2);
        layout2.add(password, 1, 2);
        layout2.add(e_mailLabel, 0, 3);
        layout2.add(email, 1, 3);
        layout2.add(bloodLabel, 0, 4);
        layout2.add(blood, 1, 4);
        layout2.add(addressLabel, 0, 5);
        layout2.add(address, 1, 5);
        layout2.add(contactLabel, 0, 6);
        layout2.add(contact, 1, 6);
        layout2.add(update, 1, 7);
        layout2.add(icon, 1, 8);
        
        update.setOnAction(e->{
            String newdata="update:>"+data+">update:";
            newdata+=username.getText()+":";
            if(password.getText().equals(info[2])) newdata+=info[2]+":";
            else newdata+=password.getText()+":";
            newdata+=email.getText() + ":" + blood.getText() + ":" + info[5] + ":" + address.getText() + ":" + contact.getText();
            try {
                Thread clientThread=new Thread(new serverConnector(newdata));
                clientThread.start();
                updateWindow upw=new updateWindow();
                upw.start(window);
            } catch (Exception ex) {
                System.out.println("Thread interrupted\n" + ex);
            }
        });
        
        icon.setOnMouseClicked(e->{
            BloodBankFinal b=new BloodBankFinal();
            try {
                b.start(window);
                window.close();
            } catch (Exception ex) {
                System.out.println("Couldn't open new window\n" + ex);
            }
        });
        
        action(icon, 1.1);
        ActionButton(update, 1.2);
//        HBox box=new HBox();
//        box.getChildren().add(0, layout1);
//        box.getChildren().add(1, layout2);
//        box.setAlignment(Pos.CENTER);
        
        BorderPane layout=new BorderPane();
        layout.setLeft(layout1);
        layout.setRight(layout2);
//        layout.setCenter(box);
        layout.setPadding(new Insets(10));
        
        
        scene=new Scene(layout,1200,800);
        String cssPath=this.getClass().getResource("/CustomStyle/viper.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        window.setTitle("Update Information");
        window.setScene(scene);
        window.show();
        
    }
    
    private void action(ImageView img, Double pratio){
        
        img.setOnMouseMoved(e->{
            img.setScaleX(pratio);
            img.setScaleY(pratio);
        });
        
        img.setOnMouseExited(e->{
            img.setScaleX(1.0);
            img.setScaleY(1.0);
        });
        
    }
    
    private void ActionButton(Button button, Double pratio){
        button.setOnMouseMoved( e-> {
            button.setScaleX(pratio);
            button.setScaleY(pratio);
        });
        button.setOnMouseExited( e-> {
            button.setScaleX(1);
            button.setScaleY(1);
        });
    }
    
}
