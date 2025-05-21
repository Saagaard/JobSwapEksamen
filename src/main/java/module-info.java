module org.eksamen.jobswap {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.eksamen.jobswap.application to javafx.fxml;
    exports org.eksamen.jobswap.application;

}