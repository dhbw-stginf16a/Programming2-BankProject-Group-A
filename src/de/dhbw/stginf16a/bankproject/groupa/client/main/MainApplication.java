package de.dhbw.stginf16a.bankproject.groupa.client.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Jan-Robin Aumann on 23.05.2017.
 */
public class MainApplication extends Application {
    private Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));

        mainScene = new Scene(root);

        primaryStage.setTitle("AGROUP Bank System");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}