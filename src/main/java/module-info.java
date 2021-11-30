module WrittersUnited {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;

    opens WrittersUnited to javafx.fxml;
    exports WrittersUnited;
    exports WrittersUnited.models;
    opens WrittersUnited.models to javafx.fxml;
}
