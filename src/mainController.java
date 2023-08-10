
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class mainController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    static int busnumber;
    static int price;
    @FXML
    Button butt2;
    @FXML
    ScrollPane scrollpane;
    @FXML
    ComboBox<String> fromcb;
    @FXML
    ComboBox<String> tocb;
    @FXML
    ImageView backbtn1;
    String from="All";
    String to="All";
    startpagecontroller sp = new startpagecontroller();


    public void scenechange(MouseEvent e) throws IOException {
        sp.startscene(e);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        combobox();
        abcd();
    }

    public void mainscene(MouseEvent e) throws IOException {

        root = FXMLLoader.load(getClass().getResource("try2.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Booking");
        stage.show();
    }
    public void combobox()
    {
        ObservableList<String> list=FXCollections.observableArrayList("All","Surat","Ahmedabad","Rajkot","Anand");
                fromcb.setItems(list);
                tocb.setItems(list);
                fromcb.setStyle("-fx-font: 15px \"Serif\";-fx-background-color:transperent;-fx-border-color:Black;-fx-border-radius:5px;");
                tocb.setStyle("-fx-font: 15px \"Serif\";-fx-background-color:transperent;-fx-border-color:Black;-fx-border-radius:5px;");
                fromcb.getSelectionModel().selectFirst();
                tocb.getSelectionModel().selectFirst();
                fromcb.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                    from=fromcb.getSelectionModel().getSelectedItem(); 
                    abcd();
                    }
                    
                } );
               tocb.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                    to=tocb.getSelectionModel().getSelectedItem(); 
                    System.out.println(tocb.getSelectionModel().getSelectedItem());
                    abcd();
                    }
                    
                } );
    }
    public void abcd() {
        try {
                
            Controller c = new Controller();
            Connection connection = c.connection();
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            if(from=="All"&&to=="All")
            {
                resultSet = statement.executeQuery("select * from bus_information");
            }
            else if(from=="All"&& to!="All")
            {
                resultSet = statement.executeQuery("select * from bus_information Where `to` LIKE '"+to+"'");
            }
            else if(from!="All"&& to=="All")
            {
                resultSet = statement.executeQuery("select * from bus_information Where `from` LIKE '"+from+"'");
            }
            else
            {
                resultSet = statement.executeQuery("select * from bus_information Where `from` LIKE '"+from+"' AND `to` LIKE'"+to+"'");
            }
            VBox rootVBox = new VBox();
            while (resultSet.next()) {
                
                int busnumber1= resultSet.getInt("bus_id");
                int price1=resultSet.getInt("price");
                VBox main1 = new VBox();
               main1.getStyleClass().add("borders");
               
                main1.setPrefWidth(583);
                main1.setMargin(main1, new Insets(20, 30, 20, 30));
                // main1.setPadding( new Insets(10, 10, 10, 10));
                // main1.getStyleClass().setStyle("");
                main1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent arg0) {
                        try {
                             busnumber= busnumber1;
                             price=price1;
                            bookingController bc = new bookingController();
                            bc.seatBookingscene(arg0);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                FlowPane fp1 = new FlowPane();
                Label labelf1l1=new Label(resultSet.getString("type"));
                labelf1l1.setPadding(new Insets(0, 0, 0, 10));
                fp1.getChildren().add(labelf1l1);
                labelf1l1.getStyleClass().add("label1");
                fp1.setHgap(125);
                // fp1.setHgap(395);
                Label labelf1l2=new Label("Price : "+price1+" ₹");
                fp1.getChildren().add(labelf1l2);
                labelf1l2.getStyleClass().add("label1");
                labelf1l2.setPadding(new Insets(0, 0, 0,60));
                Label labelf1l3=new Label("Date : "+resultSet.getString("date"));
                fp1.getChildren().add(labelf1l3);
                labelf1l3.getStyleClass().add("label1");
                labelf1l3.setPadding(new Insets(0, 0, 0, 4));
                FlowPane fp2 = new FlowPane();
                fp2.setPadding(new Insets(10, 0, 10, 0));
                Label labelf2l1=new Label(resultSet.getString("bus_route"));
                fp2.getChildren().add(labelf2l1);
                labelf2l1.getStyleClass().add("label2");
                fp2.setStyle("-fx-alignment:center");
                FlowPane fp3 = new FlowPane();
                Statement statement3;
                ResultSet resultSet3;
                statement3 = connection.createStatement();
                resultSet3 = statement3.executeQuery("select * from bookings_table Where bus_id="+busnumber1);
                int j=0;
                while(resultSet3.next()){
                    j++;
                }
                Label labelf3l1 = new Label("Avl seat: "+ (20-j));
                labelf3l1.setPadding(new Insets(0, 0, 0, 5));
                if(20-j==0)
                {
                    labelf3l1.getStyleClass().add("label4");
                    main1.setDisable(true);
                }
                else{
                    labelf3l1.getStyleClass().add("label3");
                }
                
                fp3.getChildren().add(labelf3l1);
                fp3.setHgap(155);
                Label labelf3l2 = new Label(resultSet.getString("arrival_time").substring(0, 5) + " - "
                + resultSet.getString("dest_time").substring(0, 5));
                labelf3l2.getStyleClass().add("label3");
                labelf3l2.setPadding(new Insets(0, 0, 0, 15));
                // labelf3l1.setPadding(new Insets(0, 0, 0, 10));
                fp3.getChildren().add(labelf3l2);
                Label labelf3l3 = new Label("Bus No.: " + busnumber1);
                labelf3l3.setPadding(new Insets(0, 0, 0, 0));
                labelf3l3.getStyleClass().add("label3");

                fp3.getChildren().add(labelf3l3);
                main1.getChildren().add(fp1);
                main1.getChildren().add(fp2);
                main1.getChildren().add(fp3);
                rootVBox.getChildren().add(main1);

            }
            scrollpane.setContent(rootVBox);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void hover() {
        backbtn1.setStyle("-fx-blend-mode:LIGHTEN");
        
    }
    public void hoverreverse() {
        backbtn1.setStyle("-fx-blend-mode:DARKEN");
        
    }
}
