

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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

public class bookingController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    ImageView backbtn1;
    @FXML
    VBox seatvbox;
    Button b[]=new Button[20];
    int seatFlag[]=new int[20];
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<ActionEvent> btnpress= new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Button btn=(Button)e.getSource();
                String s=btn.getText();
                s=s.substring(1);
                int index=Integer.parseInt(s);
                if(seatFlag[index-1]==2)
                {
                    seatFlag[index-1]=0;
                }
                else
                seatFlag[index-1]=2;
                if(seatFlag[index-1]==2)
                {
                    btn.setStyle("-fx-background-color:Green;-fx-text-fill:YELLOW;-fx-font-weight:BOLD");
                }
                else if(seatFlag[index-1]==1)
                {
                    btn.setStyle("-fx-background-color:red;-fx-text-fill:YELLOW;-fx-font-weight:BOLD");
                }
                else{
                    btn.setStyle("-fx-background-color:White;-fx-text-fill:black;-fx-font-weight:BOLD");
                    
                }
            } 
         };
        try{
            Statement statement;
            statement = Controller.connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("select seat_number from bookings_table WHERE bus_id="+ mainController.busnumber);
            while (resultSet.next()) {
                seatFlag[resultSet.getInt("seat_number")-1]=1;
            }
            }
                
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
       GridPane gp=new GridPane();
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(i*4 + j<9)
                b[(i*4 + j)]=new Button("S0"+ (i*4 + j+1));
                else
                b[(i*4 + j)]=new Button("S"+ (i*4 + j+1));
                if(seatFlag[(i*4+j)]==1)
                {
                    b[i*4+j].setStyle("-fx-background-color:red;-fx-text-fill:YELLOW;-fx-font-weight:BOLD");
                    // b[i*4+j].setStyle("");
                    b[i*4+j].setDisable(true);
                }
                else
                b[i*4+j].setStyle("-fx-background-color:White;-fx-text-fill:black;-fx-font-weight:BOLD");
                b[(i*4 + j)].setOnAction(btnpress);
                b[(i*4 + j)].setPadding(new Insets(10, 10, 10, 10));
                gp.setMargin(b[(i*4 + j)], new Insets(10, 10, 10, 10));
                gp.addRow(i, b[(i*4 + j)]);
                if(j==1)
                gp.addRow(i,new Label("                       "));
            }
        }
        seatvbox.getChildren().add(gp);
    }
    
    public void seatBookingscene(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("bookseat.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void seatBookingscene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("bookseat.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void backbtn(MouseEvent e) throws IOException {
        mainController mc=new mainController();
        mc.mainscene(e);
        
    }
    

    public void bookbtn(ActionEvent e)throws IOException{
        int j=0;
        for(int i=0;i<20;i++)
        {
            if(seatFlag[i]==2)
            {
                j++;
            }
        }
        if(j==0)
        {
              Alert alert=new Alert(AlertType.ERROR);
        alert.setTitle("Select the Tickets");
        alert.setHeaderText("You don't have selected ant tickets");
        alert.show();
        }
        else if(j>2)
        {
            Alert alert=new Alert(AlertType.ERROR);
        alert.setTitle("Too many tickets Selected");
        alert.setHeaderText("You can book only 2 ticket at one time");
        alert.show();
        }
        else{
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Booking Confrirm");
        alert.setHeaderText("Are You sure You want to seat the book?");
        alert.setContentText("Your Booking total is: "+ (mainController.price*j)+ " \u20B9 ");
        if(alert.showAndWait().get()== ButtonType.OK)
        {
        for(int i=0;i<20;i++)
        {
            if(seatFlag[i]==2)
            {

                try{
                Statement statement1 = Controller.connection.createStatement();
                statement1.executeUpdate(
                        "INSERT INTO `bookings_table`(`customer_id`,`bus_id`,`seat_number`)"
                                + "VALUES('" + Controller.globaluserid + "','" + mainController.busnumber + "','" + (i+1)  + "');");
                                // initialize(null, null);
                                Alert alert1=new Alert(AlertType.INFORMATION);
                                alert1.setTitle("Booking Confrirm");
                                alert1.setHeaderText("Your Seat has been Booked");
                                alert1.show();
       
                            }
                            catch(Exception ex)
                            {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                }
                    seatBookingscene(e);
    }
}
public void hover() {
        backbtn1.setStyle("-fx-blend-mode:LIGHTEN");
        
    }
    public void hoverreverse() {
        backbtn1.setStyle("-fx-blend-mode:DARKEN");
        
    }
}
