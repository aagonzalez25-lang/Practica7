module com.fitmanage.smartgym {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.fitmanage.smartgym to javafx.fxml;
    exports com.fitmanage.smartgym;
}