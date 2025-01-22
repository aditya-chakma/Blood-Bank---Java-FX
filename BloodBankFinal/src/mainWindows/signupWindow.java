
package mainWindows;

import Subtasks.confirmationBox;
import Subtasks.sendMail;
import bloodbankfinal.BloodBankFinal;
import bloodbankfinal.serverConnector;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class signupWindow extends Application{
    
    Scene scene;
    Stage window;
    Stage alterWindow;
    ImageView icon;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       
       window=new Stage();
       window.setOnCloseRequest(e->{
           e.consume();
           confirmationBox c=new confirmationBox();
           if(c.defaultMessage()) window.close();
       });
       icon= new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Go-back-icon.png")));
       icon.setFitHeight(100);
       icon.setFitWidth(100);
       
       //background image
       Image backImage=new Image(BloodBankFinal.class.getResourceAsStream("images/Donor_male.jpg"));
       BackgroundSize back=new BackgroundSize(0, 0, true, true, true, true);
       BackgroundImage background=new BackgroundImage(backImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, back);
       
       
       //setting background
       GridPane layout=new GridPane();
       layout.setPadding(new Insets(10,10,10,10));
       layout.setVgap(12);
       layout.setHgap(15);
       
       layout.setBackground(new Background(background));
//       layout.setMinHeight(1014);
//       layout.setMinWidth(1536);
       
       
       //Setting Input
       Label e_mailLabel=new Label("E-mail: ");
       TextField e_mail=new TextField();
       e_mail.setPromptText("Example@yhaoo.com");
       
       Label userLabel=new Label("Username: ");
       TextField user=new TextField();
       user.setPromptText("username");
       
       Label passwordLabel=new Label("Password: ");
       PasswordField password=new PasswordField();
       password.setPromptText("password");
       
       Label bloodGroup=new Label("Blood Group: ");
       ChoiceBox<String> blood=new ChoiceBox<>();
       blood.getItems().addAll("A+","A-","B+","B-","AB+","AB-","O+","O-");
       blood.setValue("AB+");
       
       Label cityLabel=new Label("City: ");
       ChoiceBox<String> city=new ChoiceBox<>();
       city.getItems().addAll("Dhaka","Chittagong","Rajshahi","Khulna","Barishal","Rangpur","Mymensingh");
       city.setValue("Dahka");
       
       Label addressLabel=new Label("Address: ");
       TextField address=new TextField();
       address.setPromptText("current address(full)");
       address.setMinHeight(50);
       address.setMinWidth(250);
       
       Label mobileLabel=new Label("Contact No.: ");
       TextField mobile=new TextField();
       mobile.setPromptText("contact no.");
       
       Button signup=new Button();
       signup.setText("Sign Up");
       
//       signup.setOnAction(e->{
//           try {
//               String data="";
//               data+=user.getText() + ":" + password.getText() + ":" + e_mail.getText() + ":" + blood.getValue() + ":" +
//                       city.getValue() + ":" + address.getText() + ":" + mobile.getText();
//               saveData save=new saveData(data);
//               resistration res=new resistration();
//               res.start(window);
//
//           } catch (Exception ex) {
//           }
//       });

        signup.setOnAction(e-> {
            try {
               String data="signUp:";
               data+=user.getText() + ":" + password.getText() + ":" + e_mail.getText() + ":" + blood.getValue() + ":" +
                       city.getValue() + ":" + address.getText() + ":" + mobile.getText();
               
               Thread clientThread=new Thread(new serverConnector(data));
               clientThread.start();
               resistration reg=new resistration();
               reg.start(window);
                
            } catch (Exception ex) {
                System.out.println("Thread interrupted in SignUp Window");
            }
            try {
                //String to, String name, String HospitalName,int code,String date
                //code =1 Donor
                //code =2 Client
                //code=3 Blood Managed]\
                //code=4 Welcome
                Thread mail=new Thread(new sendMail(e_mail.getText(), user.getText(), null, 4, null));
                mail.start();
            } catch (Exception ex) {
            }
        });
        
        icon.setOnMouseClicked(e->{
            try {
                BloodBankFinal bloodBankFinal=new BloodBankFinal();
                bloodBankFinal.start(primaryStage);
                window.close();
            } catch (Exception ex) {
            }
        });
       
       //Adding all to layout
       layout.add(e_mailLabel, 0, 0);
       layout.add(e_mail, 1, 0);
       layout.add(userLabel, 0, 1);
       layout.add(user, 1, 1);
       layout.add(passwordLabel, 0, 2);
       layout.add(password, 1, 2);
       layout.add(bloodGroup, 0, 3);
       layout.add(blood, 1, 3);
       layout.add(cityLabel, 0, 4);
       layout.add(city, 1, 4);
       layout.add(addressLabel, 0, 5);
       layout.add(address, 1, 5);
       layout.add(mobileLabel, 0, 6);
       layout.add(mobile, 1, 6);
       layout.add(signup, 1, 7);
       layout.add(icon, 1, 8);
       action(icon);
       
       layout.setAlignment(Pos.CENTER);
       
       //Adding to window
       window.setTitle("Sign Up!");
       scene=new Scene(layout,1200,800);
       String cssPath=this.getClass().getResource("/CustomStyle/viper.css").toExternalForm();
       scene.getStylesheets().add(cssPath);
       window.setScene(scene);
       window.show();
    }
    
    private void action(ImageView img){
        
        img.setOnMouseMoved(e->{
            img.setScaleX(1.1);
            img.setScaleY(1.1);
        });
        
        img.setOnMouseExited(e->{
            img.setScaleX(1.0);
            img.setScaleY(1.0);
        });
        
    }
}
