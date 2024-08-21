module uptc.proyectofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.media;

    opens uptc.proyectofx.view to javafx.base; // Abrir uptc.proyectofx.view para javafx.base
    opens uptc.proyectofx to javafx.fxml;      // Abrir uptc.proyectofx para javafx.fxml

    exports uptc.proyectofx;// Exportar uptc.proyectofx
    exports uptc.proyectofx.Employed;
}
