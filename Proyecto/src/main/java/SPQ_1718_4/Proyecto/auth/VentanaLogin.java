package SPQ_1718_4.Proyecto.auth;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import SPQ_1718_4.Proyecto.db.MySQLDriver;

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
        //inicializamos la ventana, le metemos un panel y que se pueda cerrar
        ventana = new JFrame();
        ventana.setSize(ventana.getToolkit().getScreenSize());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().setLayout(new FlowLayout());

        //vamos metiendo cada uno de los elementos del formulario		
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
                   String query = "SELECT email, password FROM panenka.temporary_users WHERE email='" + fieldEmail.getText() + "'";
                   ResultSet result = dbDriver.runQuery(query);
                   try {
                       if (result.next() && fieldEmail.getText().equals(result.getString("email"))) {
                           if (fieldPassword.getText().equals(result.getString("password"))) {
                               System.out.println("You're logged in!");
                           }
                           else
                           {
                               System.out.println("The login credentials are invalid");
                           }
                       }
                       else {
                           System.out.println("That email is not registered");
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

    public void dibujarventana() {
        ventana.setVisible(true);

    }

    public void desdibujarventana() {
        ventana.setVisible(false);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        VentanaLogin ventana= new VentanaLogin();
        ventana.dibujarventana();
    }

    public void loginListener(ActionEvent e) {
//        if(e.getSource()==Continuar){
//            try {
//                int min= Integer.parseInt(numeroParticipantesMin.getText());
//                int max= Integer.parseInt(numeroParticipantesMax.getText());
//                comprobarMaxMin(min, max);
//                comprobarFecha(fechaTorneo.getText());
//            } catch (NumberFormatException e1) {
//                numeroParticipantesMin.setText(null);
//                numeroParticipantesMax.setText(null);
//                JOptionPane.showMessageDialog(ventana,"El numero de participantes es un numero","MAL",JOptionPane.NO_OPTION);
//            } catch (validarMinMax e1) {
//                // TODO Auto-generated catch block
//                JOptionPane.showMessageDialog(ventana,e1.getMessage(),"Imposible",JOptionPane.NO_OPTION);
//                numeroParticipantesMin.setText(null);
//                numeroParticipantesMax.setText(null);
//            } catch (validarFecha e2) {
//                JOptionPane.showMessageDialog(ventana,e2.getMessage(),"Imposible",JOptionPane.NO_OPTION);
//                fechaTorneo.setBackground(Color.RED);
//            }
//        }
    }

//    public void comprobarMaxMin(int minimo, int maximo)throws validarMinMax {
//        if (minimo>maximo){
//            throw new validarMinMax();
//        }
//    }

//    public void comprobarFecha(String fecha)throws validarFecha {
//        if (!fecha.matches("^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-[0-9][0-9][0-9][0-9]$")){
//            throw new validarFecha();
//        }else{
//            fechaTorneo.setBackground(Color.WHITE);
//        }
//    }
}
