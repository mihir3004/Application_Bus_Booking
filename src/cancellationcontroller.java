
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class cancellationcontroller implements Initializable {
    // Controller c=new Controller();
    // Controller.connection= c.connection();
    
    // int i=0;


private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    ImageView backbtn1;
    @FXML
    ScrollPane scrlpane;
 public void cancellationscene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("cancellation.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox rootvbox=new VBox();
        try{
        Statement statement;
        statement = Controller.connection.createStatement();
        ResultSet resultSet;
        resultSet = statement.executeQuery("SELECT * from bookings_table,bus_information,login_table where login_table.customer_id = "+ Controller.globaluserid +" and bookings_table.customer_id="+ Controller.globaluserid +" and bookings_table.bus_id=bus_information.bus_id;");
        System.out.println(resultSet);
        int i=0;
        while(resultSet.next())
        {
            int bookingid=resultSet.getInt("bookings_table.booking_id");
            i++;
            VBox main1 = new VBox();
            // main1.setPrefHeight(100);
            //    main1.getStyleClass().add("borders");
            if(i%2==1)
            {
                main1.setStyle("-fx-background-color:rgb(159, 159, 162)");
            }
               main1.setPrefWidth(640);
               main1.setPrefHeight(40);
                // main1.setPrefWidth(583);
                // main1.setMargin(main1, new Insets(0, 0, 0, 5));
                
                FlowPane fp1 = new FlowPane();
                Label labelf1l1=new Label(i+"");
                labelf1l1.setPadding(new Insets(0, 0, 0, 5));
                labelf1l1.setPrefWidth(46);
                labelf1l1.setPrefHeight(18);
                labelf1l1.setAlignment(Pos.CENTER);
                fp1.getChildren().add(labelf1l1);
                labelf1l1.setStyle("-fx-font-family:Serif,Calisto MT;" + //
                        "    -fx-font-size:15;");

                // fp1.setHgap(125);
                // fp1.setHgap(395);
                Label labelf1l2=new Label(""+resultSet.getString("bus_information.bus_route"));
                fp1.getChildren().add(labelf1l2);
                labelf1l2.setStyle("-fx-font-family:Serif,Calisto MT;" + //
                        "    -fx-font-size:15;");
                labelf1l2.setPrefWidth(250);
                labelf1l2.setPrefHeight(18);
                labelf1l2.setAlignment(Pos.CENTER);
                labelf1l2.setPadding(new Insets(0, 0, 0, 7));
                Label labelf1l5=new Label(""+resultSet.getString("bookings_table.seat_number"));
                fp1.getChildren().add(labelf1l5);
                labelf1l5.setStyle("-fx-font-family:Serif,Calisto MT;" + //
                        "    -fx-font-size:15;");
                labelf1l5.setPrefWidth(40);
                labelf1l5.setPrefHeight(18);
                labelf1l5.setAlignment(Pos.CENTER);
                Label labelf1l3=new Label(""+resultSet.getString("bus_information.price")+" \u20B9");
                fp1.getChildren().add(labelf1l3);
                labelf1l3.setStyle("-fx-font-family:Serif,Calisto MT;" + //
                        "    -fx-font-size:15;");
                labelf1l3.setPrefWidth(120);
                labelf1l3.setPrefHeight(18);
                labelf1l3.setAlignment(Pos.CENTER);
                labelf1l3.setPadding(new Insets(0, 0, 0, 5));
                //  main1.getChildren().add(fp1);
                Label labelf1l4=new Label(""+resultSet.getString("bus_information.date"));
                fp1.getChildren().add(labelf1l4);
                labelf1l4.setStyle("-fx-font-family:Serif,Calisto MT;" + //
                        "    -fx-font-size:15;");
                labelf1l4.setPrefWidth(95);
                labelf1l4.setPrefHeight(40);
                labelf1l4.setAlignment(Pos.CENTER);
                labelf1l4.setPadding(new Insets(0, 10, 0, 5));
                Button btn=new Button("Cancel");
                btn.setStyle("-fx-background-color:RED;-fx-text-fill:white;-fx-font-family:Serif,Calisto MT;");
                // btn.setStyle("hover:-fx-background-color:White;-fx-text-fill:RED;-fx-font-family:Serif,Calisto MT;");
              
                
                btn.setPrefWidth(78);
                btn.setPrefHeight(18);
                
                btn.setAlignment(Pos.CENTER);
                // btn.setPadding(new Insets(0, 0, 0, 5));
                fp1.getChildren().add(btn);
                main1.setMargin(btn,new Insets(5, 5, 5, 10));
                btn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                       try{
                        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Booking Cancellation");
        alert.setHeaderText("Are You sure You want to cancel the ticket?");
        // alert.setContentText("");
        if(alert.showAndWait().get()== ButtonType.OK)
        {
                Statement statement1;
        statement1 = Controller.connection.createStatement();
        // ResultSet resultSet;
         statement.executeUpdate("DELETE FROM `bookings_table` WHERE booking_id="+bookingid);
            cancellationscene(arg0);
            
        }
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
                    }
                    
                });
                 main1.getChildren().add(fp1);

                
            rootvbox.getChildren().add(main1);
            // srno.setCellValueFactory(new PropertyValueFactory<>(i)i);
        }



        scrlpane.setContent(rootvbox);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    

    public void scenechange(MouseEvent e) throws IOException {
        startpagecontroller sp = new startpagecontroller();
        sp.startscene(e);
    }
    public void hover() {
        backbtn1.setStyle("-fx-blend-mode:LIGHTEN");
        
    }
    public void hoverreverse() {
        backbtn1.setStyle("-fx-blend-mode:DARKEN");
        
    }
}
