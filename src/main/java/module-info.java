module edu.lawrence.scheduling {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.lawrence.scheduling to javafx.fxml;
    exports edu.lawrence.scheduling;
}
