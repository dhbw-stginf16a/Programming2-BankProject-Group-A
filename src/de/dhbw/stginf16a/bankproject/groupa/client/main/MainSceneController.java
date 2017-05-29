package de.dhbw.stginf16a.bankproject.groupa.client.main;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Created by Jan-Robin Aumann on 23.05.2017.
 */
public class MainSceneController {

    @FXML
    private ListView listview;

    @FXML
    public void close(){
        Stage stage = (Stage) listview.getScene().getWindow();

        stage.close();
    }
}
