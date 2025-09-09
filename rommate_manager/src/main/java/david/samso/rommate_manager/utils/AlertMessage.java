package david.samso.rommate_manager.utils;

    import javafx.scene.control.Alert;

public class AlertMessage {

    public static void start(String type, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type.toUpperCase()));
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showError(String message) {
        start("ERROR", "Error", message);
    }

    public static void showInfo(String message) {
        start("INFORMATION", "Information", message);

    }

    public static void showWarning(String message) {
        start("WARNING", "Warning", message);
    }

    public static void showSuccess(String message) {
      start("SUCCESS", "Success", message);
    }
}