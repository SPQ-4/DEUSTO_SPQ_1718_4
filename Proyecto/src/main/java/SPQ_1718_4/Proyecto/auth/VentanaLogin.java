package SPQ_1718_4.Proyecto.auth;

import SPQ_1718_4.Proyecto.db.MySQLDriver;
import Visualizacion_DashBoard.Ventana_DashBoard;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class VentanaLogin {
    //ventana en la que se va a colocar el formulario
    private JFrame ventana;
    private JLabel labelTitle;
    private JTextField fieldEmail;
    private JLabel labelEmail;
    private JTextField fieldPassword;
    private JLabel labelPassword;
    private JButton buttonLogin;
    private JTextArea linkSignUp;

    public VentanaLogin() {
        initialize();
    }
    private void initialize() {
        ventana = new JFrame();
        ventana.setSize(ventana.getToolkit().getScreenSize());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().setLayout(new FlowLayout());

        labelTitle = new JLabel("PANENKA");
        ventana.getContentPane().add(labelTitle);
        labelTitle.setPreferredSize(new Dimension(300, 700));

        labelEmail = new JLabel("Email");
        ventana.getContentPane().add(labelEmail);

        fieldEmail = new JTextField();
        ventana.getContentPane().add(fieldEmail);
        fieldEmail.setColumns(20);

        labelPassword = new JLabel("Password");
        ventana.getContentPane().add(labelPassword);

        fieldPassword = new JPasswordField();
        ventana.getContentPane().add(fieldPassword);
        fieldPassword.setColumns(20);

        buttonLogin = new JButton("Login");
        ventana.getContentPane().add(buttonLogin);
        buttonLogin.addActionListener( new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               MySQLDriver dbDriver = new MySQLDriver();
               try {
                   String query = "SELECT * FROM panenka.temporary_users WHERE email='" + fieldEmail.getText() + "'";
                   ResultSet result = dbDriver.runQuery(query);
                   try {
                       if (result.next() && fieldEmail.getText().equals(result.getString("email"))) {
                           if (fieldPassword.getText().equals(result.getString("password"))) {
                               Ventana_DashBoard dashBoard = new Ventana_DashBoard(result.getString("id_temporary_user"));
                               desdibujarVentana();
                           }
                           else
                           {
//                              JFrame message_window = new JFrame();
//                              message_window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                              message_window.getContentPane().setLayout(new FlowLayout());
//                              JLabel label_message = new JLabel("Las credenciales no son válidas");
//                             label_message = new JLabel("No existe ninguna cuenta con ese email");
//                              message_window.getContentPane().add(label_message);
//                              message_window.setVisible(true);
                             VentanaPopup popup = new VentanaPopup(new JLabel("Las credenciales no son válidas"));
                           }
                       }
                       else {
                          VentanaPopup popup = new VentanaPopup(new JLabel("No existe ninguna cuenta con ese email"));
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
        });
        linkSignUp = new JTextArea("¿No tienes cuenta? Crea una");
        ventana.getContentPane().add(linkSignUp);
    }

    public void dibujarVentana() {
        ventana.setVisible(true);
    }

    public void desdibujarVentana() {
        ventana.setVisible(false);
    }

    private class VentanaPopup {
      private JFrame popup_window;
      private JLabel label_message;

      public VentanaPopup(JLabel label_message) {
        popup_window = new JFrame();
        popup_window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup_window.getContentPane().setLayout(new FlowLayout());
        this.label_message = label_message;
        popup_window.getContentPane().add(this.label_message);
        popup_window.setVisible(true);
      }
  }

    public static void main(String[] args) {
        VentanaLogin ventana= new VentanaLogin();
        ventana.dibujarVentana();
    }
}
