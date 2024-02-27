module com.cesi.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.cesi.app to javafx.fxml;
    opens com.cesi.app.model.employes to javafx.fxml;
    opens com.cesi.app.model.services to javafx.fxml;
    opens com.cesi.app.model.sites to javafx.fxml;

    exports com.cesi.app;
    //exports com.cesi.app.model.employes to com.fasterxml.jackson.databind;
    exports com.cesi.app.model.employes;
    exports com.cesi.app.model.services;
    exports com.cesi.app.model.sites;
}