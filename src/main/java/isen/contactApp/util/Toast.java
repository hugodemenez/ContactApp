package isen.contactApp.util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import static javafx.application.Platform.exit;

// From https://stackoverflow.com/questions/26792812/android-toast-equivalent-in-javafx

public final class Toast
{
    public static void makeText(Stage ownerStage, String toastTitle,String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay)
    {
        Stage toastStage=new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Button deleteButton = new Button();
        deleteButton.setText("X");
        deleteButton.setFont(Font.font("Hellix", 16));
        deleteButton.setStyle("""
                -fx-background-radius: 5;
                -fx-background-color: rgba(0,0,0,0);
                -fx-font-size: 12pt;
                -fx-text-fill: #000000;
                -fx-background-insets: 0 0 0 0, 0, 1, 2;""".indent(4));
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                toastStage.close();
            }
        });

        Text textTitle = new Text(toastTitle);
        textTitle.setFont(Font.font("Hellix", 16));
        textTitle.setFill(Color.BLACK);
        textTitle.setTextAlignment(TextAlignment.LEFT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Hellix", 12));
        text.setFill(Color.BLACK);
        text.setTextAlignment(TextAlignment.LEFT);

        FlowPane root = new FlowPane(textTitle,deleteButton,text);
        root.setHgap(10F);
        root.setStyle("-fx-background-radius: 5; -fx-background-color: rgba(255, 255, 255, 1); -fx-padding: 15px;-fx-border-radius: 5;-fx-border-color: #F95849; -fx-border-width: 2;-fx-max-width: 80;");
        root.setOpacity(0);


        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) ->
        {
            new Thread(() -> {
                try
                {
                    Thread.sleep(toastDelay);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Timeline fadeOutTimeline = new Timeline();
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());
                fadeOutTimeline.play();
            }).start();
        });
        fadeInTimeline.play();
    }
}