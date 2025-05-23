module org.eksamen.jobswap {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.net.http;
    requires com.google.gson;
    requires java.desktop;

    opens org.eksamen.jobswap.application to javafx.fxml;
    exports org.eksamen.jobswap.application;

}