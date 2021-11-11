package BibliTeX.Loggers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleLoggerTest {

    ConsoleLogger consoleLogger;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void preparaLogger(){
        this.consoleLogger = new ConsoleLogger();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getNomeLogger() {
        assertEquals("ConsoleLogger", this.consoleLogger.getNomeLogger());
    }

    @Test
    void logSemParametro() {
        consoleLogger.log("LISTAGEM");
        assertEquals("[LOGGER CONSOLE: INVOCADO - LISTAGEM]", outputStreamCaptor.toString().trim());
    }

    @Test
    void logComParametro() {
        consoleLogger.log("TRANSFORMA", "TEXTO ORIGINAL");
        assertEquals("[LOGGER CONSOLE: INVOCADO - TRANSFORMA] TEXTO ORIGINAL", outputStreamCaptor.toString().trim());
    }

    @Test
    void testToString() {
        consoleLogger.log("CADASTRO", "NOVA TRANSFORMAÇÃO");
        consoleLogger.log("CONTAGEM");
        consoleLogger.log("LISTAGEM");
        assertEquals("[ConsoleLogger] QUANTIDADE DE LOGS: 3", consoleLogger.toString());
    }

    @Test
    void testEquals() {
        Logger loggerAuxiliar = new ConsoleLogger();
        Logger loggerDiferente = new BarbieLogger();
        assertEquals(loggerAuxiliar, this.consoleLogger);
        assertNotEquals(loggerDiferente, this.consoleLogger);
    }

    @Test
    void testHashCode() {
        Logger loggerAuxiliar = new ConsoleLogger();
        Logger loggerDiferente = new BarbieLogger();
        assertEquals(loggerAuxiliar.hashCode(), this.consoleLogger.hashCode());
        assertNotEquals(loggerDiferente.hashCode(), this.consoleLogger.hashCode());
    }
}