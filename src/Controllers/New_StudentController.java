/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

import DBConnection.DBHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author User
 */
public class New_StudentController implements Initializable {

    @FXML
    private TextField reg_txt_username;
    @FXML
    private TextField reg_txt_nsbmid;
    @FXML
    private TextField reg_txt_email;
    @FXML
    private TextField reg_txt_phnmb;
    @FXML
    private TextField reg_txt_nic;
    @FXML
    private Button btn_reg_student;
    @FXML
    private TextField reg_txt_address;
    @FXML
    private TextField reg_txt_guardname;
    @FXML
    private TextField reg_txt_guardtel;

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
    }

    @FXML
    private void signInButtonAction(MouseEvent event) {
        String userName = reg_txt_username.getText();
        String nsbmID = reg_txt_nsbmid.getText();
        String email = reg_txt_email.getText();
        String phoneNumber = reg_txt_phnmb.getText();
        String nic = reg_txt_nic.getText();
        String address = reg_txt_address.getText();
        String guardName = reg_txt_guardname.getText();
        String guardTel = reg_txt_guardtel.getText();

        if (userName.equals("")
                || nsbmID.equals("")
                || email.equals("")
                || phoneNumber.equals("")
                || nic.equals("")
                || address.equals("")
                || guardName.equals("")
                || guardTel.equals(""))
        {
            JOptionPane.showMessageDialog(null, "All Fields Are Required!");
        }
        else
        {
             String insert = "INSERT INTO register_Students(name,nsbmID,email,phoneNumber,nic,address,guardName,guardTel)" + "VALUES(?,?,?,?,?,?,?,?)";
        connection = handler.connectDB();
        try {
            pst = connection.prepareStatement(insert);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            pst.setString(1, reg_txt_username.getText());
            pst.setString(2, reg_txt_nsbmid.getText());
            pst.setString(3, reg_txt_email.getText());
            pst.setString(4, reg_txt_phnmb.getText());
            pst.setString(5, reg_txt_nic.getText());
            pst.setString(6, reg_txt_address.getText());
            pst.setString(7, reg_txt_guardname.getText());
            pst.setString(8, reg_txt_guardtel.getText());
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registered!");
        } catch (SQLException ex) {
            Logger.getLogger(New_StudentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }   
        }
    }

}
