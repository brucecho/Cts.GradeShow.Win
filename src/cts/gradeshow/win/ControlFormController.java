/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cts.gradeshow.win;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author bruce
 */
public class ControlFormController implements Initializable {

    @FXML
    private TextField txtPictureWidth;
    @FXML
    private TextField txtPictureHeight;
    @FXML
    private TextField txtDisplacement;
    @FXML
    private TextField txtStartNumber;
    @FXML
    private TextField txtGrade;
    @FXML
    private Button btnPrepareWindow;
    @FXML
    private Button btnResetWindow;
    @FXML
    private Button btnShowGrade;
    @FXML
    private Button btnShowMoney;
    @FXML
    private Button btnCleanWindow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnPrepareWindowClick(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }

    @FXML
    private void btnResetWindowClick(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }

    @FXML
    private void btnShowGradeClick(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ShowForm.fxml"));
            Scene scene = new Scene(root);
            
            Stage ShowFormStage = new Stage();
            ShowFormStage.setScene(scene);
            //ShowFormStage.setFullScreen(true);
            
            ShowFormStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ControlFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnShowMoneyClick(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }

    @FXML
    private void btnCleanWindowClick(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }

}
