package controllers;

import db.MySQLDriver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Popup;

import java.sql.ResultSet;

public class PopupController {

    @FXML
    public BorderPane borderPane;
    @FXML
    public Pane sidePaneLeft;
    @FXML
    public Pane sidePaneRight;
    @FXML
    public Label lblMessage;

    public PopupController() {
        this.borderPane = new BorderPane();
        this.sidePaneLeft = new Pane();
        this.sidePaneRight = new Pane();
        this.lblMessage = new Label();
        this.borderPane.setCenter(this.lblMessage);
        this.borderPane.setLeft(this.sidePaneLeft);
        this.borderPane.setRight(this.sidePaneRight);
    }

    @FXML
    public void showMessage(String message) {
        this.lblMessage.setText(message);
    }

}