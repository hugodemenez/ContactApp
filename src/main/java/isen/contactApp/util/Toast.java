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
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
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
    public static void makeText(DialogPane pane, String toastTitle, String toastMsg, int toastDelay, int fadeInDelay, int fadeOutDelay)
    {
        pane.setVisible(true);
        Button deleteButton = new Button();
        deleteButton.setText("X");
        deleteButton.setFont(Font.font("Hellix", 16));
        deleteButton.setStyle("""
                -fx-background-radius: 5;
                -fx-background-color: rgba(0,0,0,0);
                -fx-font-size: 12pt;
                -fx-text-fill: #000000;
                -fx-background-insets: 0 0 0 0, 0, 1, 2;""".indent(4));
        deleteButton.setAlignment(Pos.CENTER_RIGHT);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.setVisible(false);
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

        FlowPane root = new FlowPane();
        root.getChildren().addAll(textTitle,deleteButton,text);
        root.setHgap(100F);


        pane.setHeader(root);

        pane.setStyle("-fx-background-radius: 5; -fx-background-color: rgba(255, 255, 255, 1); -fx-padding: 15px;-fx-border-radius: 5;-fx-border-color: #F95849; -fx-border-width: 2;-fx-max-width: 80;");

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (pane.opacityProperty(), 1));
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
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (pane.opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> pane.setVisible(false));
                fadeOutTimeline.play();
            }).start();
        });
        fadeInTimeline.play();


    }
}