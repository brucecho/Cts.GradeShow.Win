/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cts.gradeshow.win;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author bruce
 */
public class ShowFormController implements Initializable {

    //化面物件存取參數
    @FXML
    private HBox hboxNumber;
    @FXML
    private MediaView mediaViewHead;
    @FXML
    private MediaPlayer mediaPlayerHead;
    @FXML
    private MediaView mediaViewGrade;
    @FXML
    private MediaPlayer mediaPlayerGrade;
//    @FXML
//    private StackPane spNumberAS;
//    @FXML
//    private StackPane spNumberBS;
//    @FXML
//    private StackPane spNumberCS;
    @FXML
    private ImageView imvNumberA;
    @FXML
    private ImageView imvNumberB;
    @FXML
    private ImageView imvNumberC;
//    @FXML
//    private ImageView imvNumberAB;
//    @FXML
//    private ImageView imvNumberBB;
//    @FXML
//    private ImageView imvNumberCB;
//    @FXML
//    private ImageView imvMoney;
    //旋轉週期
    private double dbRotateDuration;

    public void setDbRotateDuration(double dbRotateDuration) {
        this.dbRotateDuration = dbRotateDuration;
    }
    //旋轉速度
    private double dbRotateRate;

    public void setDbRotateRate(double dbRotateRate) {
        this.dbRotateRate = dbRotateRate;
    }
    //實際旋轉速度
    private double dbRealRotateRate;
    //差多少分數後開始變慢
    private double dbRotateDiff;

    public void setDbRotateDiff(double dbRotateDiff) {
        this.dbRotateDiff = dbRotateDiff;
    }
    //速度差異乘數
    private double dbRotateMulti;

    public void setDbRotateMulti(double dbRotateMulti) {
        this.dbRotateMulti = dbRotateMulti;
    }
    //實際分數
    private int intFraction;
    private int intFractionA;//實際分數百位數
    private int intFractionB;//實際分數十位數
    private int intFractionC;//實際分數個位數
    //控制視窗Controller
    private ControlFormController objControlFormController;

    public void setControlFormController(ControlFormController objControlFormController) {
        this.objControlFormController = objControlFormController;
    }

    public void setFraction(int intFraction) {
        this.intFraction = intFraction;
        String strFraction = Integer.toString(intFraction);
        if (strFraction.trim().length() == 1) {
            intFractionA = 0;
            intFractionB = 0;
            intFractionC = Integer.parseInt(strFraction.trim());
        } else if (strFraction.trim().length() == 2) {
            intFractionA = 0;
            intFractionB = Integer.parseInt(strFraction.trim().substring(0, 1));
            intFractionC = Integer.parseInt(strFraction.trim().substring(1));
        } else if (strFraction.trim().length() == 3) {
            intFractionA = Integer.parseInt(strFraction.trim().substring(0, 1));
            intFractionB = Integer.parseInt(strFraction.trim().substring(1, 2));
            intFractionC = Integer.parseInt(strFraction.trim().substring(2));
        }
//        nowNumber = 0;
//        nowNumberA = 0;
//        nowNumberB = 0;
//        nowNumberC = 0;
//        imvNumberA.setImage(number0);
//        imvNumberB.setImage(number0);
//        imvNumberC.setImage(number0);
    }
    //視窗大小編排需要的參數
    Rectangle2D secondScreenBound;

    public void setSecondScreenBound(Rectangle2D secondScreenBound) {
        this.secondScreenBound = secondScreenBound;
    }
    //顯示分數動作用的參數
    Rotate rotationTransform;
    Timeline rotationAnimation;
    private double width;
    private double heigh;
    private double setWidth;
    private double setHeigh;
    private Image number0 = new Image(ShowFormController.class.getResourceAsStream("picture/0.png"));
    private Image number1 = new Image(ShowFormController.class.getResourceAsStream("picture/1.png"));
    private Image number2 = new Image(ShowFormController.class.getResourceAsStream("picture/2.png"));
    private Image number3 = new Image(ShowFormController.class.getResourceAsStream("picture/3.png"));
    private Image number4 = new Image(ShowFormController.class.getResourceAsStream("picture/4.png"));
    private Image number5 = new Image(ShowFormController.class.getResourceAsStream("picture/5.png"));
    private Image number6 = new Image(ShowFormController.class.getResourceAsStream("picture/6.png"));
    private Image number7 = new Image(ShowFormController.class.getResourceAsStream("picture/7.png"));
    private Image number8 = new Image(ShowFormController.class.getResourceAsStream("picture/8.png"));
    private Image number9 = new Image(ShowFormController.class.getResourceAsStream("picture/9.png"));
    private int nowNumber;//目前顯示分數
    private int nowNumberA;//目前顯示的百位數
    private int nowNumberB;//目前顯示的十位數
    private int nowNumberC;//目前顯示的個位數

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mediaPlayerHead.setAutoPlay(true);
        this.mediaPlayerHead.setStartTime(Duration.seconds(0));
        this.mediaPlayerHead.setStopTime(Duration.seconds(14.5));
        this.mediaPlayerHead.setCycleCount(MediaPlayer.INDEFINITE);

        this.mediaPlayerGrade.setAutoPlay(true);
        this.mediaPlayerGrade.setStartTime(Duration.seconds(0));
        this.mediaPlayerGrade.setStopTime(Duration.seconds(4.5));
        this.mediaPlayerGrade.setCycleCount(MediaPlayer.INDEFINITE);

    }

    public void SetFormSize() throws Exception {
        try {
            width = this.secondScreenBound.getWidth();
            heigh = this.secondScreenBound.getHeight();

            hboxNumber.setAlignment(Pos.CENTER);
            hboxNumber.setPrefSize(width, heigh);

            setWidth = width / 3;
            setHeigh = heigh / 4;
            imvNumberA.setFitHeight(setHeigh * 2);
            imvNumberB.setFitHeight(setHeigh * 2);
            imvNumberC.setFitHeight(setHeigh * 2);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    /**
     * 控制視窗上個物件的狀態
     *
     * @param strStatusType
     */
    public void SetFormStatus(String strStatusType) {
        //各物件的顯示狀態
        if (strStatusType.equals("A")) {
            //顯示分數之前
            mediaViewHead.setVisible(true);

            mediaViewGrade.setVisible(false);
            imvNumberA.setVisible(false);
            imvNumberB.setVisible(false);
            imvNumberC.setVisible(false);
        } else if (strStatusType.equals("B")) {
            //準備開始顯示分數
            mediaViewHead.setVisible(false);

            mediaViewGrade.setVisible(true);
            imvNumberA.setVisible(false);
            imvNumberB.setVisible(false);
            imvNumberC.setVisible(false);
        } else if (strStatusType.equals("C")) {
            //顯示分數
            mediaViewHead.setVisible(false);

            mediaViewGrade.setVisible(true);
        }
    }

    /**
     * 顯示分數
     *
     * @throws Exception
     */
    public void ShowGrade() throws Exception {
        try {
            nowNumber = 0;
            nowNumberA = 0;//目前顯示的百位數
            nowNumberB = 0;//目前顯示的十位數
            nowNumberC = 0;//目前顯示的個位數

            //設定旋轉動作參數
            dbRealRotateRate = dbRotateRate;
            rotationTransform = new Rotate(0, 50, 50);
            rotationTransform.setAxis(Rotate.X_AXIS);
            rotationTransform.setPivotY(setHeigh);
            rotationTransform.setPivotZ(0);

            //將各圖片加上旋轉動作
            imvNumberA.getTransforms().clear();
            imvNumberA.setMouseTransparent(true);
            imvNumberA.getTransforms().add(rotationTransform);
            imvNumberB.getTransforms().clear();
            imvNumberB.setMouseTransparent(true);
            imvNumberB.getTransforms().add(rotationTransform);
            imvNumberC.getTransforms().clear();
            imvNumberC.setMouseTransparent(true);
            imvNumberC.getTransforms().add(rotationTransform);

            //設定動作時間序
            rotationAnimation = new Timeline();
            rotationAnimation.setRate(dbRealRotateRate);
            rotationAnimation.getKeyFrames().add(
                    new KeyFrame(
                            Duration.seconds(dbRotateDuration), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            String strNowNumber = Integer.toString(nowNumber);
                             if (nowNumber > intFraction) {
                                 rotationAnimation.stop();
                                 return;
                             }
                             
                            if (strNowNumber.trim().length() == 1) {
                                nowNumberC = Integer.parseInt(strNowNumber.trim());
                            } else if (strNowNumber.trim().length() == 2) {
                                nowNumberB = Integer.parseInt(strNowNumber.trim().substring(0, 1));
                                nowNumberC = Integer.parseInt(strNowNumber.trim().substring(1));
                            } else if (strNowNumber.trim().length() == 3) {
                                nowNumberA = Integer.parseInt(strNowNumber.trim().substring(0, 1));
                                nowNumberB = Integer.parseInt(strNowNumber.trim().substring(1, 2));
                                nowNumberC = Integer.parseInt(strNowNumber.trim().substring(2));
                            }
                            switch (nowNumberA) {
                                case 0:
                                    imvNumberA.setImage(number0);
                                    break;
                                case 1:
                                    imvNumberA.setImage(number1);
                                    break;
                                case 2:
                                    imvNumberA.setImage(number2);
                                    break;
                                case 3:
                                    imvNumberA.setImage(number3);
                                    break;
                                case 4:
                                    imvNumberA.setImage(number4);
                                    break;
                                case 5:
                                    imvNumberA.setImage(number5);
                                    break;
                                case 6:
                                    imvNumberA.setImage(number6);
                                    break;
                                case 7:
                                    imvNumberA.setImage(number7);
                                    break;
                                case 8:
                                    imvNumberA.setImage(number8);
                                    break;
                                case 9:
                                    imvNumberA.setImage(number9);
                                    break;
                                default:
                                    break;
                            }

                            switch (nowNumberB) {
                                case 0:
                                    imvNumberB.setImage(number0);
                                    break;
                                case 1:
                                    imvNumberB.setImage(number1);
                                    break;
                                case 2:
                                    imvNumberB.setImage(number2);
                                    break;
                                case 3:
                                    imvNumberB.setImage(number3);
                                    break;
                                case 4:
                                    imvNumberB.setImage(number4);
                                    break;
                                case 5:
                                    imvNumberB.setImage(number5);
                                    break;
                                case 6:
                                    imvNumberB.setImage(number6);
                                    break;
                                case 7:
                                    imvNumberB.setImage(number7);
                                    break;
                                case 8:
                                    imvNumberB.setImage(number8);
                                    break;
                                case 9:
                                    imvNumberB.setImage(number9);
                                    break;
                                default:
                                    break;
                            }

                            switch (nowNumberC) {
                                case 0:
                                    imvNumberC.setImage(number0);
                                    break;
                                case 1:
                                    imvNumberC.setImage(number1);
                                    break;
                                case 2:
                                    imvNumberC.setImage(number2);
                                    break;
                                case 3:
                                    imvNumberC.setImage(number3);
                                    break;
                                case 4:
                                    imvNumberC.setImage(number4);
                                    break;
                                case 5:
                                    imvNumberC.setImage(number5);
                                    break;
                                case 6:
                                    imvNumberC.setImage(number6);
                                    break;
                                case 7:
                                    imvNumberC.setImage(number7);
                                    break;
                                case 8:
                                    imvNumberC.setImage(number8);
                                    break;
                                case 9:
                                    imvNumberC.setImage(number9);
                                    break;
                                default:
                                    break;
                            }

                            //設定數字的顯示狀況
                            if (nowNumberA == 0 && nowNumberB == 0 && nowNumberC
                                    == 0) {
                                imvNumberA.setVisible(false);
                                imvNumberB.setVisible(false);
                                imvNumberC.setVisible(false);
                            } else if (nowNumberA == 0 && nowNumberB == 0 && nowNumberC > 0) {
                                imvNumberA.setFitWidth(1);
                                imvNumberB.setFitWidth(1);
                                imvNumberC.setFitWidth(setWidth);

                                imvNumberA.setVisible(false);
                                imvNumberB.setVisible(false);
                                imvNumberC.setVisible(true);
                            } else if (nowNumberA == 0 && nowNumberB > 0 && nowNumberC > 0) {
                                imvNumberA.setFitWidth(1);
                                imvNumberB.setFitWidth(setWidth);
                                imvNumberC.setFitWidth(setWidth);

                                imvNumberA.setVisible(false);
                                imvNumberB.setVisible(true);
                                imvNumberC.setVisible(true);
                            } else if (nowNumberA > 0 && nowNumberB == 0 && nowNumberC == 0) {
                                imvNumberA.setFitWidth(setWidth);
                                imvNumberB.setFitWidth(setWidth);
                                imvNumberC.setFitWidth(setWidth);

                                imvNumberA.setVisible(true);
                                imvNumberB.setVisible(true);
                                imvNumberC.setVisible(true);
                            }

                            if ((intFractionB == nowNumberB && intFractionA == 0)) {
                                imvNumberB.getTransforms().clear();
                            }

                            if (nowNumber == intFraction) {
//                                rotationAnimation.stop();
                                objControlFormController.setBtnEnable("D");
                            } else {
                                if ((intFraction - nowNumber) < dbRotateDiff) {
                                    if (dbRealRotateRate > 1) {
                                        //dbRealRotateRate = dbRealRotateRate - (5 * dbRotateMulti);
                                        dbRealRotateRate = dbRealRotateRate * dbRotateMulti;
                                    } else {
                                        dbRealRotateRate = 1;
                                    }
                                    rotationAnimation.setRate(dbRealRotateRate);
                                }
                            }
                            nowNumber++;
                        }
                    }, new KeyValue(
                            rotationTransform.angleProperty(),
                            360
                    )));
            rotationAnimation.setCycleCount(Animation.INDEFINITE);
            rotationAnimation.setAutoReverse(false);
            rotationAnimation.play();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    /**
     * 顯示獲得金額
     *
     * @throws Exception
     */
    public void ShowMoney() throws Exception {
        try {
//            SetFormStatus("C");
//            if (intFraction < 60) {
//                imvMoney.setImage(image2000);
//            } else if (60 <= intFraction && intFraction < 70) {
//                imvMoney.setImage(image3000);
//            } else if (70 <= intFraction && intFraction < 80) {
//                imvMoney.setImage(image3500);
//            } else if (80 <= intFraction && intFraction < 90) {
//                imvMoney.setImage(image4000);
//            } else if (90 <= intFraction && intFraction < 95) {
//                imvMoney.setImage(image4500);
//            } else if (95 <= intFraction && intFraction <= 100) {
//                imvMoney.setImage(image5000);
//            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    /**
     * 重新顯示下一個分數前，重設視窗
     *
     * @throws Exception
     */
//    public void ClearForm() throws Exception {
//        try {
//            intFraction = 0;
//            setFraction(intFraction);
//            SetFormStatus("B");
//        } catch (Exception error) {
//            throw new Exception(error.getMessage());
//        }
//    }
}
