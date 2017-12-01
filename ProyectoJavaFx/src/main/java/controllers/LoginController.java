package controllers;

import db.MySQLDriver;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Main2;
import models.Popup;

import java.net.URL;
import java.sql.ResultSet;

public class LoginController extends Application implements Initializable {

    @FXML
    public BorderPane borderPane;
    @FXML
    public VBox vBox;
    @FXML
    public Pane sidePaneLeft;
    @FXML
    public Pane sidePaneRight;
    @FXML
    public Label lblSiteTitle;
    @FXML
    public TextField txtEmail;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public Button btnLogin;

    public LoginController() {
        super();
    }

    @FXML
    public void login() {
        try {
            if (checkCredentials(txtEmail.getText(), txtPassword.getText())) {
                this.borderPane.getChildren().clear();
                this.borderPane.setBackground(null);
                this.borderPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/views/HomePage.fxml")));
            }
            else
            {
                new Popup("Las credenciales no son válidas");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkCredentials(String email, String password) {
        String query = "SELECT * FROM panenka.temporary_users WHERE email='" + email + "'";
        ResultSet result = Main2.getDBDriver().runQuery(query);
        try {
            if (result.next() && email.equals(result.getString("email"))) {
                if (password.equals(result.getString("password"))) {
                    return true;
                }
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        URL resource =this.getClass().getResource("/views/login.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("Panenka - Admin");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}