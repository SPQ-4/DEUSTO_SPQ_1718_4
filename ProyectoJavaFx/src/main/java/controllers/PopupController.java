package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PopupController {

    @FXML
    public BorderPane borderPane;
    @FXML
    public Pane sidePaneLeft;
    @FXML
    public Pane sidePaneRight;
    @FXML
    public Label lblMessage;

    /**
     * Método constructor.
     */
    public PopupController() {
        this.borderPane = new BorderPane();
        this.sidePaneLeft = new Pane();
        this.sidePaneRight = new Pane();
        this.lblMessage = new Label();
        this.borderPane.setCenter(this.lblMessage);
        this.borderPane.setLeft(this.sidePaneLeft);
        this.borderPane.setRight(this.sidePaneRight);
    }

    /**
     * Método que carga un mensaje en el label de la ventana para informar al usuario.
     */
    @FXML
    public void showMessage(String message) {
        this.lblMessage.setText(message);
    }

}