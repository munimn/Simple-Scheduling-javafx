package edu.lawrence.scheduling;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {
    @FXML
    private VBox vBox;
    private SchedulePane pane;
    
    @FXML
    private void save() {
        pane.saveReservations();
    }
    
    @FXML
    private void exit() {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane = new SchedulePane();
        pane.setPrefSize(600,860);
        vBox.getChildren().add(pane);
    }  
    
    public void focusPane() {
        pane.requestFocus();
    }
}