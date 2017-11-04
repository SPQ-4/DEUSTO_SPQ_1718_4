package controllers;

import db.MySQLDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Popup;

import java.sql.ResultSet;

public class LoginController {

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
        borderPane = new BorderPane();
        vBox = new VBox();
        sidePaneLeft = new Pane();
        sidePaneRight = new Pane();
        lblSiteTitle = new Label();
        txtEmail = new TextField();
        txtPassword = new PasswordField();
        btnLogin = new Button();
        borderPane.setTop(lblSiteTitle);
        borderPane.setLeft(sidePaneLeft);
        borderPane.setRight(sidePaneRight);
        vBox.getChildren().add(txtEmail);
        vBox.getChildren().add(txtPassword);
        vBox.getChildren().add(btnLogin);
        borderPane.setCenter(vBox);
    }

    @FXML
    public void login() {
        MySQLDriver dbDriver = new MySQLDriver();
        try {
            String query = "SELECT * FROM panenka.temporary_users WHERE email='" + txtEmail.getText() + "'";
            ResultSet result = dbDriver.runQuery(query);
            try {
                if (result.next() && txtEmail.getText().equals(result.getString("email"))) {
                    if (txtPassword.getText().equals(result.getString("password"))) {
                        System.out.println("Opening dashboard");
                        //TODO - Mirar como hacer para cerrar la ventana entera en lugar del Scene
                        this.borderPane.getParent().getScene().getRoot().setVisible(false);
                    }
                    else
                    {
                        new Popup("Las credenciales no son válidas");
                    }
                }
                else {
                    new Popup("No existe ninguna cuenta con ese email");
                }
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        dbDriver.close();
    }

}