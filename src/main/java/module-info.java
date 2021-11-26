module WrittersUnited {
    requires javafx.controls;
    requires javafx.fxml;

    opens WrittersUnited to javafx.fxml;
    exports WrittersUnited;
}
