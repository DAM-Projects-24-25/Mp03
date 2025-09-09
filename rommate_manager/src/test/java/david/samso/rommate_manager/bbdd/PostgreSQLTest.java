package david.samso.rommate_manager.bbdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PostgreSQLTest {
    
    private static final String TEST_USER = "test_user";
    private static final String TEST_PASSWD = "test_password";
    private static final String TEST_HOST = "localhost";
    private static final String TEST_PORT = "5432";
    private static final String TEST_NAME = "test_db";
    
    private PostgreSQL postgreSQL;
    
    @BeforeEach
    public void setUp() {
        postgreSQL = new PostgreSQL(TEST_USER, TEST_PASSWD, TEST_HOST, TEST_PORT, TEST_NAME);
    }
    
    @Test
    @DisplayName("Prova de creació de l'objecte PostgreSQL")
    public void testCreacioObjectePostgreSQL() {
        assertNotNull(postgreSQL);
        assertEquals(TEST_USER, postgreSQL.DB_USER);
        assertEquals(TEST_PASSWD, postgreSQL.DB_PASSWD);
        assertEquals(TEST_HOST, postgreSQL.DB_HOST);
        assertEquals(TEST_PORT, postgreSQL.DB_PORT);
        assertEquals(TEST_NAME, postgreSQL.DB_NAME);
    }
    
    @Test
    @DisplayName("Prova de la cadena de connexió")
    public void testCadenaConnexio() {
        String expectedConnectionString = "jdbc:postgresql://" + TEST_HOST + ":" + TEST_PORT + "/" + TEST_NAME;
        assertEquals(expectedConnectionString, postgreSQL.getStringConnexio());
    }
    
    @Test
    @Disabled("Aquesta prova requereix una connexió real a PostgreSQL")
    @DisplayName("Prova de connexió a PostgreSQL")
    public void testConnexio() {
        try {
            Connection conn = postgreSQL.getConnection();
            assertNotNull(conn);
            assertFalse(conn.isClosed());
            
            conn.close();
            assertTrue(conn.isClosed());
        } catch (SQLException e) {
            fail("No s'hauria de llançar cap excepció: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Prova del mètode obteDadesTableView")
    public void testObteDadesTableView() {
        assertDoesNotThrow(() -> {
            assertNotNull(postgreSQL.obteDadesTableView(null, null));
        });
    }
} 