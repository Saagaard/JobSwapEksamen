module org.eksamen.jobswap {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.eksamen.jobswap to javafx.fxml;
    exports org.eksamen.jobswap;
}