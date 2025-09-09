module samso.duarte.pp_samso_duarte {
    requires javafx.controls;
    requires javafx.fxml;

    opens samso.duarte.pp_samso_duarte to javafx.fxml;
    opens samso.duarte.pp_samso_duarte.controladors to javafx.fxml;
    opens samso.duarte.pp_samso_duarte.classes to javafx.fxml;

    exports samso.duarte.pp_samso_duarte;
    exports samso.duarte.pp_samso_duarte.controladors;
}
