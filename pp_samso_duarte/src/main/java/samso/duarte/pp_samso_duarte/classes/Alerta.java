package samso.duarte.pp_samso_duarte.classes;

import javafx.scene.control.Alert;

public class Alerta {

    private String missatge;
    private String titol;

    // CONSTRUCTORS
    public Alerta(String missatge) {
        this.missatge = missatge;
    }

    public Alerta(String missatge, String titol) {
        this.missatge = missatge;
        this.titol = titol;
    }

    // MÈTODES
    public void alertaError(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (this.titol==null)
            this.titol="ERROR!!";
        a.setTitle(this.titol);
        a.setContentText(missatge);
        a.show();
    }
    public void alertaWarning(){
        Alert a = new Alert(Alert.AlertType.WARNING);
        if (this.titol==null)
            this.titol="WARNING!!";
        a.setTitle(this.titol);
        a.setContentText(missatge);
        a.show();
    }

}
