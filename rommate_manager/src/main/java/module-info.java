module david.samso.rommate_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens david.samso.rommate_manager to javafx.fxml;
    opens david.samso.rommate_manager.controllers to javafx.fxml;
    opens david.samso.rommate_manager.models to javafx.base;

    exports david.samso.rommate_manager;
    exports david.samso.rommate_manager.controllers;
}
