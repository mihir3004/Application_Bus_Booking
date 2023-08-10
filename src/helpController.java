import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class helpController {
    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    ImageView backbtn1;
    public void backbtn(MouseEvent e) throws IOException{
    startpagecontroller mc=new startpagecontroller();
    mc.startscene(e);
    }
    public void hover() {
        backbtn1.setStyle("-fx-blend-mode:LIGHTEN");
        
    }
    public void hoverreverse() {
        backbtn1.setStyle("-fx-blend-mode:DARKEN");
        
    }
}
