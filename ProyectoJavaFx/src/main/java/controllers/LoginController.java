package controllers;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Main2;
import models.Popup;

import java.net.URL;
import java.sql.ResultSet;

public class LoginController implements Initializable {

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

    /**
     * Método constructor
     */
    public LoginController() {
        super();
    }

    /**
     * Método que se encarga de leer los campos de input de las credenciales y carga el dashboard
     * en caso de que sean válidas. En caso de no serlo, presenta un pop-up al usuario informándole
     * del error.
     */
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

    /**
     * Método que realiza una query de todos los usuarios de la BD y compara las credenciales
     * recibidas por parámetro con las de dichos usuarios. En caso de que encuentre una coincidencia
     * devuelve TRUE, que indica que se le proporciona acceso a la aplicación al usuario, y en caso
     * de no ser así devuelve FALSE.
     */
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

    /**
     * Método de inicialización de la clase por implementar Initializable. No se usa.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}