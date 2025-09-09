# Gestor de Companys de Pis (Roommate Manager)

Una aplicació de gestió per a compartir pis que permet controlar despeses, tasques i gestionar els companys.

## Característiques

- **Gestió de companys**: Afegeix i segueix els companys de pis.
- **Gestió d'apartaments**: Registra detalls dels apartaments.
- **Control de despeses**: Seguiment de despeses compartides.
- **Gestió de tasques**: Assigna i segueix tasques domèstiques.

## Requisits previs

- Java JDK 11 o superior
- PostgreSQL per a la base de dades
- Gradle per a la construcció del projecte

## Instal·lació

1. Clona el repositori:
   ```
   git clone https://github.com/teurepo/rommate_manager.git
   ```

2. Configura la base de dades PostgreSQL:
   - Crea una base de dades anomenada `roommate_manager`
   - Configura les credencials a `DatabaseConnection.java`

3. Construeix el projecte amb Gradle:
   ```
   ./gradlew build
   ```

4. Executa l'aplicació:
   ```
   ./gradlew run
   ```

## Ús

L'aplicació té una interfície gràfica intuïtiva:

1. Utilitza les pestanyes per navegar entre les diferents seccions:
   - Roommates (Companys de pis)
   - Apartaments
   - Despeses
   - Tasques

2. Fes clic als botons d'afegir per crear nous registres.

## Tecnologies utilitzades

- JavaFX per a la interfície d'usuari
- PostgreSQL per a l'emmagatzematge de dades
- JDBC per a la connexió amb la base de dades
- Gradle per a la gestió de dependències i construcció

## Estructura del projecte

- `src/main/java/david/samso/rommate_manager/`: Codi font
  - `bbdd/`: Classes per a la connexió amb diferents bases de dades
  - `bbdd_daos/`: Classes per a l'accés a dades
  - `controllers/`: Controladors per a les vistes
  - `models/`: Classes de model de dades
  - `view/`: Arxius FXML per a la interfície d'usuari

## Autor

David Samsó

## Llicència

Aquest projecte està llicenciat sota la Llicència MIT - veure l'arxiu [LICENSE](LICENSE) per més detalls. 