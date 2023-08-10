
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// import com.mysql.cj.xdevapi.Statement;

import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;



public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene sc= new Scene(root, 650, 500);
        sc.getStylesheets().add("styles.css");
        primaryStage.setTitle("Login");
        primaryStage.setScene(sc);
        Image e=new Image("images.png");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(e);
        primaryStage.show();    
        }
        
    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
