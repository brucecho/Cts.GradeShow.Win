/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cts.gradeshow.win;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author bruce
 */
public class ControlFormController implements Initializable {

    @FXML
    private TextField txtRotateDuration;
    @FXML
    private TextField txtRotateRate;
    @FXML
    private TextField txtRotateDiff;
    @FXML
    private TextField txtRotateMulti;
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
    @FXML
    private TextArea taMessage;

    private VBox objShowForm;//分數顯示視窗的根物件
    private ShowFormController objShowFormController;
    //控制視窗顯示在第二螢幕的參數
    private Screen screens;
    private List<Screen> listScreen;
    private Stage ShowFormStage;

    public void setShowFormController(ShowFormController objShowFormControllerIN) {
        this.objShowFormController = objShowFormControllerIN;
    }
    //訊息視窗
    Alert alertMessage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnPrepareWindow.setDisable(false);
        btnResetWindow.setDisable(true);
        btnShowGrade.setDisable(true);
        btnShowMoney.setDisable(true);
        btnCleanWindow.setDisable(true);
        
        alertMessage = new Alert(Alert.AlertType.INFORMATION);
    }

    /**
     * 準備視窗
     *
     * @param event
     */
    @FXML
    private void btnPrepareWindowClick(ActionEvent event) {
        try {
            //抓取第二個螢幕的位置資訊
            listScreen = screens.getScreens();
            if (listScreen.size() < 2) {
                ShowMessage("偵測不到第二螢幕，請確認！");
                return;
            }
            Rectangle2D secondScreenBound = listScreen.get(1).getVisualBounds();
            

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ShowForm.fxml"));

            objShowForm = (VBox) loader.load();
            objShowFormController = loader.getController();
            Scene scene = new Scene(objShowForm);
            //初始化分數顯示螢幕
            ShowFormStage = new Stage();
            ShowFormStage.setScene(scene);
            ShowFormStage.setX(secondScreenBound.getMinX());
            ShowFormStage.setY(secondScreenBound.getMinY());
            ShowFormStage.setAlwaysOnTop(true);//設定停駐在最上層
            ShowFormStage.setFullScreen(true);//設定全螢幕顯示
            //傳遞必要參數
            objShowFormController.setSecondScreenBound(secondScreenBound);
            objShowFormController.SetFormSize();
           
            objShowFormController.SetFormStatus("A");
            //顯示視窗
            ShowFormStage.show();

            btnPrepareWindow.setDisable(true);
            btnResetWindow.setDisable(false);
            btnShowGrade.setDisable(false);
            btnShowMoney.setDisable(true);
            btnCleanWindow.setDisable(true);

        } catch (Exception error) {
            ShowMessage(error.getMessage());
        }
    }

    /**
     * 重設視窗
     *
     * @param event
     */
    @FXML
    private void btnResetWindowClick(ActionEvent event) {
        try {
            if (ShowFormStage != null && ShowFormStage.isShowing()) {
                ShowFormStage.close();

                btnPrepareWindow.setDisable(false);
                btnResetWindow.setDisable(true);
                btnShowGrade.setDisable(true);
                btnShowMoney.setDisable(true);
                btnCleanWindow.setDisable(true);
            }
        } catch (Exception error) {
            ShowMessage(error.getMessage());
        }
    }

    /**
     * 顯示分數
     *
     * @param event
     */
    @FXML
    private void btnShowGradeClick(ActionEvent event) {

        try {
           //抓取畫面輸入的參數
            String strRotateDuration = txtRotateDuration.getText();
            String strRotateRate = txtRotateRate.getText();
            String strRotateDiff = txtRotateDiff.getText();
            String strRotateMulti = txtRotateMulti.getText();
            //判斷必要職的正確性
            if (strRotateDuration.trim().length() == 0) {
                ShowMessage("旋轉週期請輸入數字！");
                return;
            }
            if (strRotateRate.trim().length() == 0) {
                ShowMessage("旋轉速度請輸入數字！");
                return;
            }
            if (strRotateDiff.trim().length() == 0) {
                ShowMessage("差異數請輸入數字！");
                return;
            }
            if (strRotateMulti.trim().length() == 0) {
                ShowMessage("承數請輸入數字！");
                return;
            }
             objShowFormController.setDbRotateDuration(Double.parseDouble(strRotateDuration));
            objShowFormController.setDbRotateRate(Double.parseDouble(strRotateRate));
            objShowFormController.setDbRotateDiff(Double.parseDouble(strRotateDiff));
            objShowFormController.setDbRotateMulti(Double.parseDouble(strRotateMulti));
            //取得畫面所輸入的分數值
            String strFraction = txtGrade.getText();
            //檢查數字
            if (strFraction.trim().length() == 0 || Integer.parseInt(strFraction) < 0 || Integer.parseInt(strFraction) > 100) {
                ShowMessage("請輸入0到100的數字");
                return;
            }
            objShowFormController.setFraction(Integer.parseInt(strFraction));
            objShowFormController.ShowGrade();

            btnPrepareWindow.setDisable(true);
            btnResetWindow.setDisable(false);
            btnShowGrade.setDisable(true);
            btnShowMoney.setDisable(false);
            btnCleanWindow.setDisable(true);

        } catch (Exception error) {
            ShowMessage(error.getMessage());
        }
    }

    /**
     * 顯示金額
     *
     * @param event
     */
    @FXML
    private void btnShowMoneyClick(ActionEvent event) {
        try {
            objShowFormController.ShowMoney();

            btnPrepareWindow.setDisable(true);
            btnResetWindow.setDisable(false);
            btnShowGrade.setDisable(true);
            btnShowMoney.setDisable(true);
            btnCleanWindow.setDisable(false);
        } catch (Exception error) {
            ShowMessage(error.getMessage());
        }
    }

    /**
     * 按下清空畫面
     *
     * @param event
     */
    @FXML
    private void btnCleanWindowClick(ActionEvent event) {
        try {
            objShowFormController.ClearForm();

            btnPrepareWindow.setDisable(true);
            btnResetWindow.setDisable(false);
            btnShowGrade.setDisable(false);
            btnShowMoney.setDisable(true);
            btnCleanWindow.setDisable(true);
        } catch (Exception error) {
            ShowMessage(error.getMessage());
        }
    }

    private void ShowMessage(String strMessage) {
        alertMessage.setTitle("訊息");
        alertMessage.setContentText(strMessage);
        alertMessage.showAndWait();
//        this.taMessage.setText(strMessage);
    }

}
