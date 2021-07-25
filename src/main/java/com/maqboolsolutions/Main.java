package com.maqboolsolutions;

import com.gluonhq.attach.orientation.OrientationService;
import com.gluonhq.attach.util.Platform;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        Label lblHeading = new Label("Orientation:");
        Label lblOrientation = new Label();

        root.getChildren().addAll(lblHeading, lblOrientation);

        Scene scene;
        if (Platform.isDesktop()) {
            scene = new Scene(root);
        } else {
            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());
        }

        primaryStage.setScene(scene);
        primaryStage.show();

        // Run codding for Landscape or Portrait on Mobile Device
        OrientationService.create().ifPresent(service -> {

            // First time run codding w.r.t Screen Orientation
            if (service.getOrientation().get() == Orientation.HORIZONTAL) {
                lblOrientation.setText("1'st Landscape");
            } else {
                lblOrientation.setText("1'st Portrait");
            }

            // Run codding w.r.t Screen Orientation when it's changed
            service.orientationProperty().addListener(observable -> {
                if (service.getOrientation().get() == Orientation.HORIZONTAL) {
                    lblOrientation.setText("Landscape");
                } else {
                    lblOrientation.setText("Portrait");
                }
            });

        });

    }
}
