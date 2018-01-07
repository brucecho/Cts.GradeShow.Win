/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cts.gradeshow.win;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author bruce
 */
public class CtsGradeShowWin extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("ControlForm.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        */
        Node card = createCard();

        stage.setScene(createScene(card));
        stage.show();

        RotateTransition rotator = createRotator(card);
        rotator.play();
    }
    
    private Scene createScene(Node card) {
        StackPane root = new StackPane();
        root.getChildren().addAll(card);

        Scene scene = new Scene(root, 600, 700, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());

        return scene;
    }

    private Node createCard() {
        return new ImageView(
            new Image(
                "http://www.ohmz.net/wp-content/uploads/2012/05/Game-of-Throne-Magic-trading-cards-2.jpg"
            )
        );
    }

    private RotateTransition createRotator(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(10), card);
        rotator.setAxis(Rotate.X_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(360);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(10);

        return rotator;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
