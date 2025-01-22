
package bloodbankfinal;

import Subtasks.closeProgram;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainWindows.clientWindow;
import mainWindows.forgotPassword;
import mainWindows.loginFailedWindow;
import mainWindows.memberDetail;
import mainWindows.signupWindow;


public class BloodBankFinal extends Application{

    private Stage window;
    private ImageView top;
    private ImageView middle;
    private ImageView bottom;
    private Scene scene;
    private ImageView side;
    private ImageView needBlood;
   
    public static void main(String[] args) throws Exception {
        launch(args);
    }
  
    public void start(Stage primaryStage) throws Exception {
        setData();
        window=new Stage();
        
        
        //Closing window
        window.setOnCloseRequest(e-> {
            e.consume();
            if(closeProgram.close("Confirm Exit", "Are you Sure?"))
                window.close();
        });
       
        //Setting Image property
        top.setFitHeight(250);
        top.setFitWidth(500);
        middle.setFitHeight(300);middle.setFitWidth(500);
        bottom.setFitHeight(250);
        bottom.setFitWidth(500);
        side.setFitHeight(222);
        side.setFitWidth(400);
        needBlood.setFitWidth(400);
        needBlood.setFitHeight(270);
        
        
        VBox layout=new VBox();
        layout.getChildren().addAll(top,middle,bottom);
                
        //Setting login option
        Button login=new Button();
        login.setText("Log in!");
        login.getStyleClass().add("button-blue");
        Button signUp=new Button();
        signUp.setText("Sign Up");
        signUp.getStyleClass().add("button-blue");
        Button forgot=new Button("Recover");
        forgot.getStyleClass().add("button-blue");
        
        Label userLabel=new Label("User Name:");
        Label passwordLabel=new Label("Password:");
        Label forgotPassword=new Label("Forgot Password?");
        Label welcome=new Label("Be a donor! Signup here");
        
        TextField userName=new TextField();
        userName.setPromptText("Enter your name");
        PasswordField userPassword=new PasswordField();
        userPassword.setPromptText("Enter password");
        
        
        GridPane sidelayout=new GridPane();
        sidelayout.setVgap(8.0);
        sidelayout.add(needBlood, 0,0);
        sidelayout.add(side, 0, 0+1);
        sidelayout.add(userLabel, 0, 1+1);
        sidelayout.add(userName, 0, 2+1);
        sidelayout.add(passwordLabel, 0, 3+1);
        sidelayout.add(userPassword, 0, 4+1);
        sidelayout.add(login, 0, 5+1);
        sidelayout.add(forgotPassword, 0, 6+1);
        sidelayout.add(forgot, 0, 8);
        sidelayout.add(welcome, 0, 8+1);
        sidelayout.add(signUp, 0, 9+1);
        sidelayout.setAlignment( Pos.CENTER );
        //sidelayout.setMinSize(250, 0);
        

        //Actions
//        welcome.setOnMouseEntered( e -> {
//            welcome.setScaleX(1.5);
//            welcome.setScaleY(1.5);
//        });
//        welcome.setOnMouseExited( e -> {
//            welcome.setScaleX(1);
//            welcome.setScaleY(1);
//        });
//        welcome.setOnMouseClicked( e-> {
//            try {
//                signupWindow signIn=new signupWindow();
//                signIn.start(window);
//                //window.close();
//            } catch (Exception ex) {
//                Logger.getLogger(BloodBankFinal.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
        
        needBlood.setOnMouseClicked( e->{
            clientWindow client=new clientWindow();
            try {
                client.start(window);
            } catch (Exception ex) {
                System.out.println("Exception: "+ex);
            }
        });
        action(needBlood, 1.1);
        
        login.setOnAction(e->{
            if(!userName.getText().trim().isEmpty() || !userPassword.getText().trim().isEmpty())
                try {
                    String data="logIn:";
                    data+= userName.getText() + ":" + userPassword.getText();
                    System.out.println(data);
                    serverConnector loginMatch=new serverConnector(data);
                    Thread serverConThread=new Thread(loginMatch);
                    serverConThread.start();

                    try {
                        serverConThread.join();
                    } catch (InterruptedException ex) {
                        System.out.println("Server Interrupted\n"+ex);
                    }

                    if(loginMatch.getAnswer())
                    {
                        System.out.println("Login Successfull");
                        
                        String info="info:";
                        info+= userName.getText() + ":" + userPassword.getText();
                        String notice="noticeget:notice";
                        
                        serverConnector src=new serverConnector(info);
                        serverConnector srcNotice=new serverConnector(notice);
                        Thread t=new Thread(src);
                        Thread tNotice=new Thread(srcNotice);
                        tNotice.start();
                        t.start();
                        
                        try {
                            t.join();
                            tNotice.join();
                        } catch (Exception ex) {
                            System.out.println("Thread interrupted(BloodBankFinal.java) \n"+ex);
                        }
                        
                        memberDetail md=new memberDetail();
                        //System.out.println("Receive data in final: "+src.getData());
                        //System.out.println("data got: "+ srcNotice.getData());
                        md.setNotice(srcNotice.getData());
                        md.setData(src.getData());
                        md.start(window);
                        window.close();
                    }
                    else{
                        System.out.println("Login failed");
                        
                        loginFailedWindow logfail=new loginFailedWindow();
                        logfail.start(window);
                    }
                } catch (Exception ex) {
                    System.out.println("Failed! logging in(BloodBankFinal.java)\n" + ex);
                }
            else {
                try {
                    loginFailedWindow logfail=new loginFailedWindow();
                    logfail.start(window);
                } catch (Exception ex) {
                    System.out.println("Unable to start new window(BloodBankFinal.java)\n" + ex);
                }
            }
            
        });
        forgot.setOnAction(e->{
            forgotPassword fp=new forgotPassword();
            try {
                fp.start(window);
            } catch (Exception ex) {
                System.out.println("Unable to start window(BloodBankFinal.java)\n" + ex);
            }
        });
        signUp.setOnAction( e-> { 
            signupWindow signIn=new signupWindow();
            try {
                signIn.start(window);
                window.close();
            } catch (Exception ex) {
                System.out.println("Error opening new window(BloodBankFinal.java)\n " +ex);
            }
        });
        
        
        ActionButton(signUp, 1.2);
        ActionButton(login, 1.2);
        ActionButton(forgot, 1.2);
        
        //Setting layout
        BorderPane mainLayout=new BorderPane();
        mainLayout.setPadding(new Insets(5,5,5,5));
        mainLayout.setLeft(layout);
        mainLayout.setRight(sidelayout);
        
        //Setting window
        
        scene=new Scene(mainLayout,925,820);
        String cssPath=this.getClass().getResource("/CustomStyle/styleInfo.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        window.setTitle("Blood Bank");
        window.setScene(scene);
        window.show();
    }
    
    private void setData()
    {
        top= new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Blood_donors_day_woman.jpg")));
        bottom=new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Donor_quote.jpg")));
        middle=new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Side_Image8.jpg")));
        side=new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/id-cards.png")));
        needBlood=new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Need_blood.jpg")));
    }
    
    private void setImageProperty(){
        top.fitWidthProperty().bind(scene.widthProperty());
        top.fitHeightProperty().bind(scene.heightProperty());
        top.setPreserveRatio(false);
        bottom.fitWidthProperty().bind(scene.widthProperty());
        bottom.fitHeightProperty().bind(scene.heightProperty());
        bottom.setPreserveRatio(false);
        middle.fitWidthProperty().bind(scene.widthProperty());
        middle.fitHeightProperty().bind(scene.heightProperty());
        middle.setPreserveRatio(false);
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
    
    private void action(ImageView img,Double pratio){
        
        img.setOnMouseMoved(e->{
            img.setScaleX(pratio);
            img.setScaleY(pratio);
        });
        
        img.setOnMouseExited(e->{
            img.setScaleX(1.0);
            img.setScaleY(1.0);
        });
        
    }
}

