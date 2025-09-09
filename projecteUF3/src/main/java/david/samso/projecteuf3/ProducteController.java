package david.samso.projecteuf3;

import david.samso.projecteuf3.classes.Producte;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProducteController {

    @FXML
    private TextField nomProducte;
    @FXML
    private TextField preuProducte;
    @FXML
    private DatePicker dataCaduPreu;
    @FXML
    private Button btnGuarda;
    @FXML
    private TextArea descPreu;
    @FXML
    private Button btnMostra;
    @FXML
    private Button btnElimina;
    @FXML
    private TextField cercaNomProducte;
    @FXML
    private TextArea textMostra;

    static Producte prod = new Producte();

    public void initialize() {}


    @FXML
    public void registra_P() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataCaduca = dataCaduPreu.getValue().format(formatter);

        Producte pr = new Producte(
                nomProducte.getText(),
                Double.parseDouble(preuProducte.getText()),
                descPreu.getText(),
                dataCaduca
        );
        pr.guardaProducte();
    }

    @FXML
    public void mostrarPoducte() {
        try {
            Producte pro = new Producte();
            List<Producte> producteList = pro.retornaProducte();

            if (producteList.isEmpty()) {
                System.out.println("La llista de productes està buida.");
                return;
            }

            StringBuilder text = new StringBuilder();

            for (Producte pr : producteList) {
                text.append(pr.getNom()).append(";")
                        .append(pr.getPreu()).append(";")
                        .append(pr.getDescripcio()).append(";")
                        .append(pr.getDataCaduca()).append("\n");
            }

            System.out.println("Text a mostrar: " + text.toString());
            textMostra.setText(text.toString());
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void eliminarProducte() throws IOException, InterruptedException, ClassNotFoundException {
        String nomProducteAEliminar = cercaNomProducte.getText();
        boolean eliminat = prod.eliminaProducte(nomProducteAEliminar);

        if (eliminat) {
            System.out.println("Producte eliminat amb èxit: " + nomProducteAEliminar);
        } else {
            System.out.println("No s'ha trobat cap producte amb el nom: " + nomProducteAEliminar);
        }
    }



}
