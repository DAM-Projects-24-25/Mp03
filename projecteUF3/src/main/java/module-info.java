module david.samso.projecteuf3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;


    opens david.samso.projecteuf3 to javafx.fxml;
    exports david.samso.projecteuf3;
}