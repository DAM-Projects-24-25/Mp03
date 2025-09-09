package david.samso.projecteuf3.classes;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import david.samso.projecteuf3.classes.MeuObjectOutputStream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Fitxers {


    public void escriuFitxerText(String fitxer, String text, boolean afegir){

        try (FileWriter out = new FileWriter(fitxer,afegir)) {

            out.write(text+"\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String retornaFitxerText(String fitxer){
        String linia;
        String text="";
        try (FileReader in =new FileReader(fitxer)){
            Scanner input=new Scanner(in);
            while (input.hasNextLine()){
                linia = input.nextLine();
                text = text + linia+"\n";
            }
        } catch (Exception e){
            System.err.println("ERROR!");
        }
        return text;
    }

    public Queue<String> retornaFitxerTextEnCua(String fitx) throws IOException {
        Path path = Paths.get(fitx);
        Queue<String> CFitxer = new LinkedList<>();   // per acumular les línies
        String linia;                               // per recollir la línia

        Charset charset = Charset.forName("UTF-8"); // definim el charset
        BufferedReader reader = Files.newBufferedReader(path, charset);
        while ((linia = reader.readLine()) != null) {
            CFitxer.add(linia);                     // linia a linia la posem a la CUA
        }

        return CFitxer;
    }

    public String cercaEnFitxerText(String fitxer, int edat){
        String text = retornaFitxerText(fitxer);
        String [] lineas = text.split("\n");
        String []dades;
        String linia;
        String textFinal = "";

        int edatFitxer;

        for (int i = 0; i <lineas.length ; i++) {
            linia = lineas[i];

            dades = linia.split(";");
            edatFitxer = Integer.parseInt(dades[2]);
            if (edatFitxer>=edat){
                textFinal = textFinal+linia+"\n";
            }
        }
        return textFinal;
    }

    public List<String> retornaFitxerTextLlista(String fitxer){
        List<String> llistaFitxer = new ArrayList();
        String contingut = retornaFitxerText(fitxer);
        String dades[] = contingut.split("\n");

        for (int i = 0; i < dades.length; i++) {
            llistaFitxer.add(dades[i]);
        }
        return llistaFitxer;
    }

    public boolean existeixFitxer(String ruta) {
        Path fitxer = Paths.get(ruta);
        return Files.exists(fitxer, new LinkOption[0]);
    }

    public boolean ComprovaRuta(String dir) {
        // Comprova la existència d'un directori o un fitxer
        return existeixFitxer(dir);    // Files --> import java.nio.file.Files
    }

    public Path obtePath(String fitxer) {
        return Paths.get(fitxer);
    }

    public void borrar(String fitxer) throws IOException {
        try {
            DataOutputStream output = new DataOutputStream(new FileOutputStream(fitxer));

            try {
                System.out.println("El archivo " + fitxer + " se ha dejado en blanco.");
                File file = new File(fitxer);
                if (file.delete()) {
                    System.out.println("El archivo " + fitxer + " se ha eliminado exitosamente.");
                } else {
                    System.out.println("No se pudo eliminar el archivo " + fitxer);
                }
            } catch (Throwable var5) {
                try {
                    output.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            output.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }








    public void recorrerArbreDirectoris(String fitx) {
        //
        // NO NIVELL ENCARA
        // NO UTILITZAT EN PROGRAMA EXEMPLE
        //
        File arx = new File(fitx);
        File listFile[] = arx.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    recorrerArbreDirectoris(listFile[i].toString());        //  RECURSIU  //
                } else {
                    System.out.println(listFile[i].getPath());
                }
            }
        }
    }

    public String metadadesFitxer(String fitx) throws IOException {

        String cadena = "";
        if (existeixFitxer(fitx)) {
            Path path = Paths.get(fitx);
            cadena = "Metadades del fitxer " + fitx + "\n";
            cadena = cadena + "Tamany: " + Files.size(path) + " Bytes\n";
            cadena = cadena + "Directory?: " + Files.isDirectory(path) + "\n";
            cadena = cadena + "Fitxer?: " + Files.isRegularFile(path) + "\n";
            cadena = cadena + "Simbòlic: " + Files.isSymbolicLink(path) + "\n";
            cadena = cadena + "Ocult: " + Files.isHidden(path) + "\n";
            cadena = cadena + "Ultima Modificació: " + Files.getLastModifiedTime(path) + "\n";
            cadena = cadena + "Propietari: " + Files.getOwner(path) + "\n";
        } else {
            cadena = "No existeix el fitxer";
        }
        return cadena;
    }

    public void moureFitxerDirectori(String origen,String desti) throws IOException {

        int eleccio;

        // copia un fitxer (origen) en un fitxer (desti)
        Path pathOrigen = Paths.get(origen);
        Path pathDesti = Paths.get(desti);

//        if (existeix(desti)){
//            eleccio=Pregunta("El arxiu ja existeix.\n" +
//                    "vols sobreescriure'l?");
//            if (eleccio==0)
        Files.move(pathOrigen, pathDesti, REPLACE_EXISTING);
//            else
//                MissatgeInfo("OK. No sobreescrivim el fitxer");

//        }
    }

    public void copiarFitxerDirectori(String origen,String desti) throws IOException {

        Path pathOrigen = Paths.get(origen);
        Path pathDesti = Paths.get(desti);

        Files.copy(pathOrigen, pathDesti, REPLACE_EXISTING);

    }

    public void eliminarFitxerDirectori(String fitx) throws IOException {

        // Borrem un fitxer o directori
        try {
            Path path = Paths.get(fitx);


            Files.delete(path);                 // No veiem encara la clàusula try .. catch .. finally


        } catch (NoSuchFileException x) {
            // No existeix el fitxer
        } catch (DirectoryNotEmptyException x) {
            // El directori no està buit i no es pot borrar
        } catch (IOException x) {
            // Altres tipus d'errors. Per exemple no tenir permissos per borrar
        }
    }

    public void eliminaRegistreFitxerText(String fitxer,String text,String caracterSeparador,int posicio) {
        String linia;
        String fitxer_tmp = ".tmp";
        String[] camps;

        try ( // BLOC DE TRY .. CATCH

              FileReader in = new FileReader(fitxer);) {
            Scanner input = new Scanner(in);

            while (input.hasNextLine()) {           // Mentre hi hagen línies a l'arxiu ...
                linia = input.nextLine();           // Agafa una línia
                camps = linia.split(caracterSeparador);          // Parteix per columnes

                if (!text.equals(camps[posicio])) {
                    escriuFitxerText(fitxer_tmp, linia, true);
                }
            }
            //EliminarFitxerDirectori(fitxer);
            moureFitxerDirectori(fitxer_tmp, fitxer);
        } catch (Exception e) {
            System.err.println(";-(");
        }

    }

    @Deprecated
    public List<Object> llegeixFitxerEnLlistaObject(String fitxer,String caracterSeparador) {

        List<Object> LFitxer = new ArrayList<>();   // per retornar una llista d'objectes
        Object obj = new Object();                    // per encapsular cada objecte
        String linia;                               // per recollir la línia
        String[] v;                                 // per fer l'split


        try ( // BLOC DE TRY .. CATCH
              FileReader in = new FileReader(fitxer);) {
            Scanner input = new Scanner(in);
            while (input.hasNextLine()) {           // Mentre hi hagen línies a l'arxiu ...
                linia = input.nextLine();           // Agafa una línia
                v = linia.split(caracterSeparador);   // Partim la línia en les posicions del vector
                LFitxer.add(v);                     // afegim la línia a la llista
            }

        } catch (Exception e) {
            System.err.println(";-(. Algun error en el fitxer");
        }


        return LFitxer;
    }

    public void creaDirectori(String ruta) throws IOException {
        Path path = Paths.get(ruta);
        Files.createDirectory(path);
    }

    private void tancaFitxerObjecte(ObjectOutputStream out) throws IOException {
        // tanquem els fitxer (en el SO windows s'ha de tancar el fitxer.)
        out.flush();
        out.close();
        out = null;
    }

    public void escriuObjecteFitxer(Object obj,String arxiu,boolean afegir) throws IOException {
        // Si hem posat afegir=false o si no existeix l'arxiu (sobreescrivim el fitxer)
        if (!afegir || !existeixFitxer(arxiu)) {
            // PER CREAR EL PRIMER OBJECTE
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arxiu, afegir));
            out.writeObject(obj);
            tancaFitxerObjecte(out);    //tanquem el fitxer d'objectes
        } else {
            // PER AFEGIR LA RESTA D'OBJECTES.  Per afegir TAMBÉ posem true després el nom del fitxer
            MeuObjectOutputStream out2 = new MeuObjectOutputStream(new
                    FileOutputStream(arxiu, afegir));
            out2.writeObject(obj);
            tancaFitxerObjecte(out2);    //tanquem el fitxer d'objectes
        }
    }

    public void escriuLlistaObjecteFitxer(List<Object> LObjs,String arxiu) throws IOException {

        // Si hem posat afegir=false o si no existeix l'arxiu (sobreescrivim el fitxer)
        int i;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arxiu, false));
        MeuObjectOutputStream out2 = new MeuObjectOutputStream(new FileOutputStream(arxiu, true));


        out.writeObject(LObjs.get(0));          // escrivim el primer:
        tancaFitxerObjecte(out);                //tanquem el fitxer d'objectes
        for (i = 1; i < LObjs.size(); i++) {
            out2.writeObject(LObjs.get(i));     // escrivim la resta
        }
        tancaFitxerObjecte(out2);               //tanquem el fitxer d'objectes
    }

    @Deprecated
    public List<Object> retornaFitxerObjecteEnLlista(String arxiu) throws IOException,InterruptedException, ClassNotFoundException {
        List<Object> LObjs = new ArrayList<>();
        ObjectInputStream in=null;
        try {
            in = new ObjectInputStream(
                    new FileInputStream(arxiu));
            do {
                Object obj = in.readObject();
                LObjs.add(obj);
            } while (in != null);
        } catch (IOException e) {
            //e.printStackTrace();
            //System.err.println("\nFinal Fitxer");
        } finally {     // aquest finally és per assegurar-nos que tanquem el fitxer sempre
            try {
                if (in != null) {
                    in.close();     // tanquem el fitxer d'objectes
                    in = null;      // i l’alliberem de la memòria
                }
            } catch (IOException e) {
                // Maneig d'excepcions al tancar el stream
            }
        }
        return LObjs;
    }



    public void guardaLlistaObjecteFitxerObjecte(String arxiu,List<Object> LObj) throws IOException {
        int i;
        for (i = 0; i < LObj.size(); i++) {
            if (i == 0) {
                escriuObjecteFitxer(LObj.get(i), arxiu, false);
            } else {
                escriuObjecteFitxer(LObj.get(i), arxiu, true);
            }
        }
    }


    // IO

    public void escriuFitxerTextIO(String text,String fitxer, Boolean afegir) throws IOException {
        FileWriter out = new FileWriter(fitxer, afegir);

        try {
            out.write(text + "\n");
        } catch (Throwable var7) {
            try {
                out.close();
            } catch (Throwable var6) {
                var7.addSuppressed(var6);
            }

            throw var7;
        }

        out.close();
    }

    public void escriuFitxerTextLlistaIO(
            String fitxer,
            List<String> text,
            boolean afegir) throws IOException {

        for (String linia : text) {
            escriuFitxerTextIO(fitxer, linia, afegir);
        }
    }


    public String retornaFitxerTextIO(String fitxer) {
        String linia = "";

        try {
            FileReader in = new FileReader(fitxer);

            try {
                for(Scanner input = new Scanner(in); input.hasNextLine(); linia = linia + input.nextLine() + "\n") {
                }
            } catch (Throwable var6) {
                try {
                    in.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            in.close();
        } catch (Exception var7) {
            System.err.println(";.(.Algun error en el fitxer)");
        }

        return linia;
    }

    public List<String> retornaFitxerTextLlistaIO(String fitxer) {

        String linia; // per recollir la línia
        List<String> text = new ArrayList<>();        // contingut del fitxer
        try {
            // BLOC DE TRY .. CATCH
            // NECESSARI PER UTILITZAR STREAMS
            FileReader in = new FileReader(fitxer);
            {
                Scanner input = new Scanner(in);
                while (input.hasNextLine()) { // Mentre hi hagen línies a l'arxiu ...
                    linia = input.nextLine(); // Agafa una línia
                    text.add(linia); // guardem les dades de l’usuari
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    public String obteRutaIO(String fitxer) {
        File file = new File(fitxer);
        return file.getAbsolutePath();
    }

    public boolean esDirectoriIO(String directori) {
        File file = new File(directori);
        return file.isDirectory();
    }

    public void renombraFitxerIO(String fitxer, String nouNom) {
        java.io.File file = new java.io.File(fitxer);
        java.io.File file2 = new java.io.File(nouNom);
        if (file.renameTo(file2)) {
            System.out.println("Fitxer renombrat: " + file.getName() + " a " + file2.getName());
        } else {
            System.out.println("No s'ha pogut renombrar el fitxer.");
        }
    }

    public void llistaFitxersIO(String directori) {
        File file = new File(directori);
        String[] fitxers = file.list();
        for (String fitxer : fitxers) {
            System.out.println(fitxer);
        }
    }

    public void mostraFitxerTestIO(String fitxer) {
        System.out.println(this.retornaFitxerTextIO(fitxer));
    }

    public Boolean existeixIO(String ruta) {
        File f = new File(ruta);
        if (f.exists()) {
            System.out.println("Existeix");
        } else {
            System.out.println("No existeix");
        }

        return f.exists();
    }

    public void creaDirectoriIO(String directori) throws IOException {
        File f = new File(directori);
        if (f.mkdir()) {
            System.out.println("Fitxer creat: " + f.getName());
        } else {
            System.err.println("S'ha produït un error :( o ja esta creat :)");
        }

    }

    public void eliminaFitxerDirectoriIO(String ruta) {
        File f = new File(ruta);
        if (f.delete()) {
            System.out.println("El fitxer " + f.getName() + " s'ha esborrat amb exit");
        } else {
            System.err.println("S'ha produït un error :(");
        }

    }

    public void moureFitxerDirectoriIO(String fitxerOriginal, String fitxerDesti) {
        File fOG = new File(fitxerOriginal);
        fOG.renameTo(new File(fitxerDesti));
    }

    public String propietatsFitxerIO(String fitxer) {
        File fitxers = new File(fitxer);
        boolean isExecutable = fitxers.canExecute();
        boolean isFile = fitxers.isFile();
        boolean isDirectory = fitxers.isDirectory();
        boolean ishidden = fitxers.isHidden();
        boolean isreadable = fitxers.canRead();
        boolean isWritable = fitxers.canWrite();
        String text = "" + isreadable + ";" + isWritable + ";" + isExecutable + ";" + isDirectory + ";" + isFile + ";" + ishidden;
        return text;
    }




}
