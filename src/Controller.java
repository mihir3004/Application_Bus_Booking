import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;

public class Controller {
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Label error;
    @FXML
    TextField rusername;
    @FXML
    TextField rpassword;
    @FXML
    TextField rcpassword;
    @FXML
    TextField rcontactnumber;
    @FXML
    TextField remail;
    @FXML
    Label error1;
    @FXML
    AnchorPane anchorpane234;
    @FXML
    ScrollPane scrlpane;
    @FXML
    Button abc;
    static String globalusername;
    static int globaluserid;
    // static int busnumber;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginscene(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     public void loginscene1(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registerscene(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
   

    public static Connection connection = null;

    public  Connection connection() {
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/bus_ticket",
                    "root", "");
            return connection;
        } catch (Exception exception) {
            System.out.println(exception);
            return connection;
        }
    }

    public void loginbtn(ActionEvent e) {
        connection = connection();
        // System.out.println(password.getText());
        if (username.getText().trim().equalsIgnoreCase("")) {
            error.setText("Username is Empty");
        } else if (password.getText().trim().equalsIgnoreCase("")) {
            error.setText("Password is Empty");
        } else {
            error.setText("");
            try {
                Statement statement;
                statement = connection.createStatement();
                ResultSet resultSet;
                resultSet = statement.executeQuery("select * from login_table");
                int customer_id;
                String name;
                String customerpassword;
                while (resultSet.next()) {
                    customer_id = resultSet.getInt("customer_id");
                    name = resultSet.getString("customer_name").trim();
                    globalusername=name;
                    globaluserid=customer_id;
                    customerpassword = resultSet.getString("customer_password");
                    // System.out.println(name+customerpassword);
                    if (name.equals(username.getText()) && customerpassword.equals(password.getText())) {
                        System.out.println("Succesfully Loggedin");
                        startpagecontroller mc=new startpagecontroller();
                        mc.startscene1(e);
                        // root = FXMLLoader.load(getClass().getResource("try1.fxml"));
                        // stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        // // Group xyz = new Group();
                        // // for (int i = 0; i < 100; i++) {

                        // //     xyz.getChildren().add(new Label("AHHHHHHHHH" + i));
                        // // }
                        // // // ScrollPane abc = new ScrollPane(xyz);
                        // // scrlpane.setContent(new Label("null"));
                        
                        // abc=new Button("customerpassword");
                        // // scene.getChildren().add(abc);
                        // scene = new Scene(root);
                        // stage.setScene(scene);
                        // stage.show();
                        // abc.setText("customerpassword");
                        break;
                    } else {
                        error.setText("Invalid Credentials");
                    }
                }
                resultSet.close();
                statement.close();
                // connection.close();
            } catch (Exception exception) {
                System.out.println(exception);
            }

        }
        // System.out.println("login");
    }
public boolean format(String input) {
        // Implement your format validation logic here
        // For example, you can check if the input matches a specific pattern using regular expressions
        // Modify this method according to your specific format requirements
        return input.matches("[0-9]+"); // Only allows alphanumeric characters
    }
    public void registerbtn(ActionEvent e) {
        connection = connection();

        if (rusername.getText().trim().equalsIgnoreCase("")) {
            error1.setText("Username is Empty");
        } else if (rpassword.getText().trim().equalsIgnoreCase("")) {
            error1.setText("Password is Empty");
        } else if (rcpassword.getText().trim().equalsIgnoreCase("")) {
            error1.setText("Confirm Password is Empty");
        } else if (rcontactnumber.getText().trim().equalsIgnoreCase("")) {
            error1.setText("Contact Number is Empty");
        } else if (remail.getText().trim().equalsIgnoreCase("")) {
            error1.setText("Email is Empty");
        } else if((!format(rcontactnumber.getText()))) {
           error1.setText("Contact Format is invalid ");
    } else {
            try {
                Statement statement;
                statement = connection.createStatement();
                ResultSet resultSet;
                String name = rusername.getText();
                resultSet = statement.executeQuery(
                        "select * from login_table WHERE `customer_name`= '" + name + "'");
                if (resultSet.next()) {
                    error1.setText("User Already Exist");
                } else {
                    if (rpassword.getText().equals(rcpassword.getText())) {
                        try {
                            error1.setText("");
                            Statement statement1 = connection.createStatement();
                            statement1.executeUpdate(
                                    "INSERT INTO `login_table`(`customer_name`,`customer_password`,`email_id`,`contact_number`)"
                                            + "VALUES('" + rusername.getText() + "','" + rpassword.getText() + "','"
                                            + remail.getText() + "','" + rcontactnumber.getText() + "');");
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        System.out.println("");
                         Alert alert1=new Alert(AlertType.INFORMATION);
        alert1.setTitle("Registered Successfully");
        alert1.setHeaderText("You have registered Successfully");
        alert1.show();
                        loginscene1(e);
                    } else {
                        error1.setText("Password and Confirm Password Doesn't Match");
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());

            }

        }

    }

}
