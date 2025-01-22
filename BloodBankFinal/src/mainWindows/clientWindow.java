
package mainWindows;

import Subtasks.sendMail;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import bloodbankfinal.BloodBankFinal;
import bloodbankfinal.serverConnector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class clientWindow extends Application{
    
    Stage window;
    private Scene scene;
    private Image backImage;
    private ImageView icon;
    
    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        icon= new ImageView(new Image(BloodBankFinal.class.getResourceAsStream("images/Go-back-icon.png")));
        icon.setFitHeight(100);
        icon.setFitWidth(100);
        
        //Creating background
        backImage=new Image(BloodBankFinal.class.getResourceAsStream("images/bdonate.jpg"));
        BackgroundSize backSize=new BackgroundSize(0, 0, true, true, true, true);
        BackgroundImage background=new BackgroundImage(backImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,backSize);
        
        //Setting backgroundImage
        GridPane layout=new GridPane();
        layout.setVgap(12);
        layout.setHgap(15);
        //layout.setBackground(new Background(background));
        BorderPane b=new BorderPane();
        b.setBackground(new Background(background));
        b.setRight(layout);
        b.setPadding(new Insets(10,10,10,10));
        
        //Contact Information of client
        Label clientName=new Label("Name: ");
        //clientName.setTextFill(Color.web("#0000ff"));
        TextField client=new TextField();
        client.setPromptText("Enter your name");
        client.setMinWidth(250);
        
        Label bloodGroupLabel=new Label("Blood group: ");
        ChoiceBox<String> bloodGroup=new ChoiceBox<>();
        bloodGroup.getItems().addAll("A+","A-","B+","B-","AB+","AB-","O+","O-");
        bloodGroup.setValue("B+");
        
        Label amountLabel=new Label("Amount: ");
        ChoiceBox<String> amount=new ChoiceBox<>();
        amount.getItems().addAll("1 Bag","2 Bags","3 Bags","4 Bags","5 Bags");
        amount.setValue("1 Bag");
        
        Label timeLabel=new Label("Time: ");
        ChoiceBox<String> time=new ChoiceBox<>();
        time.getItems().addAll("Emergency","Today","In 1 day","In 2 days","In 3 Days","In a week");
        time.setValue("In 2 days");
        
        Label HospitalLabel=new Label("Name of Hospital: ");
        TextField hospital=new TextField();
        hospital.setPromptText("hospital name");
        
        Label cityLabel=new Label("City: ");
        ChoiceBox<String> city=new ChoiceBox<>();
        city.getItems().addAll("Dhaka","Chittagong","Rajshahi","Khulna","Barishal","Rangpur","Mymensingh");
        city.setValue("Dhaka");
        
        Label e_mailLabel=new Label("E-mail: ");
        TextField e_mail=new TextField();
        e_mail.setPromptText("Example@yhaoo.com");
        
        //districts dis=null;
        
        city.setOnAction( e-> {
            
        });
        
        //Unimplemented
        Label districtLabel=new Label("District: ");
        ChoiceBox<String> district=new ChoiceBox();
        //district.getItems().addAll(dis.getValue());
        
        Label contactLabel=new Label("Contact no: ");
        TextField contact=new TextField();
        contact.setPromptText("+88019999999");
        
        Button submit=new Button("Submit");
        submit.getStyleClass().add("blue-button");
        
        submit.setOnAction(e->{
            
            String data="submit:";
            data+=client.getText()+":" + bloodGroup.getValue() + ":" + amount.getValue() + ":" + 
                    time.getValue()+ ":" + city.getValue() + ":" +e_mail.getText()+":"+ hospital.getText();
            Thread t=new Thread(new serverConnector(data));
            t.start();
            
            done d=new done();
            try {
                d.start(window);
            } catch (Exception ex) {
                System.out.println("Couldn't Open new window(clientWindow.java)\n" + ex);
            }
            try {
                //String to, String name, String HospitalName,int code,String date
                //code =1 Donor
                //code =2 Client
                //code=3 Blood Managed
                //code=4 Welcome
                Thread mail=new Thread(new sendMail(e_mail.getText(), client.getText() , hospital.getText(), 3, STYLESHEET_MODENA));
                mail.start();
            } catch (Exception ex) {
                System.out.println("Exception in sending mail(clientWindow.java)\n" + ex);
            }
            
        });
        
        icon.setOnMouseClicked(e->{
            BloodBankFinal bloodBankFinal=new BloodBankFinal();
            try {
                bloodBankFinal.start(window);
                window.close();
            } catch (Exception ex) {
                System.out.println("Could't switch window(clientWindow.java)\n" + ex);
            }
        });
        
        //Adding in background
        layout.add(clientName, 0, 0);
        layout.add(client, 1, 0);
        layout.add(bloodGroupLabel, 0, 1);
        layout.add(bloodGroup, 1, 1);
        layout.add(amountLabel, 0, 2);
        layout.add(amount, 1, 2);
        layout.add(timeLabel, 0, 3);
        layout.add(time, 1, 3);
        layout.add(HospitalLabel, 0, 4);
        layout.add(hospital, 1, 4);
        layout.add(cityLabel, 0, 5);
        layout.add(city, 1, 5);
        //layout.add(districtLabel, 0, 6);
        //layout.add(district, 1, 6);
        layout.add(contactLabel, 0, 6);
        layout.add(contact, 1, 6);        
        layout.add(e_mailLabel, 0, 7);
        layout.add(e_mail, 1, 7);
        layout.add(submit, 1, 8);
        layout.add(icon, 1, 9);
        
        action(icon);
        
        //Setting window
        layout.setAlignment(Pos.CENTER);
        scene=new Scene(b,1200,800);
        String cssPath=this.getClass().getResource("/CustomStyle/viper.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        window.setScene(scene);
        window.setTitle("Blood Bank - Client Info");
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
