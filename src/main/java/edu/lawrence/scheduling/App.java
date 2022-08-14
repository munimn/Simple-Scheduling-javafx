package edu.lawrence.scheduling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 300);
        stage.setScene(scene);
        PrimaryController controller = (PrimaryController) fxmlLoader.getController();
        controller.focusPane();
        scene.getRoot().setStyle("-fx-font-family: 'sans-serif'");
        stage.setTitle("Scheduling");
        stage.show();
    }

    
    public static void main(String[] args) {
        launch();
    }

}