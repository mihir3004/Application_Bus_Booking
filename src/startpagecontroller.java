import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class startpagecontroller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label startlbl; 
    public void startscene1(ActionEvent e) throws IOException {

        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    public void startscene(MouseEvent e) throws IOException {

        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    public void bookingvbox(MouseEvent e) throws IOException{
        mainController mc=new mainController();
        mc.mainscene(e);

        
    }
     public void cancellationscene(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("cancellation.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   public void helpscene(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("help.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // Controller c=new Controller();
        
        startlbl.setText("Welcome "+ Controller.globalusername);
    }
    public void logout(MouseEvent e) throws IOException
    {
        Controller c=new Controller();
        c.loginscene(e);
    }
    public void logouthover(MouseEvent e)throws IOException{
        
    }
}
