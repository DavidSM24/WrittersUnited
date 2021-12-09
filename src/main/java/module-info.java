open module WrittersUnited {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires mail;
	requires java.persistence;

    //opens WrittersUnited to javafx.fxml,javafx.graphics,java.sql,java.persistence;
    //opens WrittersUnited.models to javafx.fxml,javafx.graphics,java.sql,java.persistence;
    exports WrittersUnited;
}
