package BibliTeX.Loggers;

import com.sun.tools.jconsole.JConsoleContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ContagemLoggerTest {

    ContagemLogger contagemLogger;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void preparaLogger(){
        this.contagemLogger = new ContagemLogger();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getNomeLogger() {
        assertEquals("ContagemLogger", this.contagemLogger.getNomeLogger());
    }

    @Test
    void logSemParametro() {
        contagemLogger.log("LISTAGEM");
    }

    @Test
    void logComParametro() {
        contagemLogger.log("TRANSFORMA", "NOVA TRANSFORMAÇÃO");
    }

    @Test
    void testToString() {
        contagemLogger.log("TRANSFORMA", "NOVA TRANSFORMAÇÃO");
        contagemLogger.log("LISTAGEM");
        contagemLogger.log("CONTAGEM");
        assertEquals("[LOGGER CONTAGEM: AÇÃO - TRANSFORMA] QUANTIDADE DE CHAMADAS: 1\n" +
                "[LOGGER CONTAGEM: AÇÃO - LISTAGEM] QUANTIDADE DE CHAMADAS: 1\n" +
                "[LOGGER CONTAGEM: AÇÃO - CONTAGEM] QUANTIDADE DE CHAMADAS: 1", contagemLogger.toString());
    }

    @Test
    void testEquals() {
        Logger loggerAuxiliar = new ContagemLogger();
        Logger loggerDiferente = new BarbieLogger();
        assertEquals(loggerAuxiliar, this.contagemLogger);
        assertNotEquals(loggerDiferente, this.contagemLogger);
    }

    @Test
    void testHashCode() {
        Logger loggerAuxiliar = new ContagemLogger();
        Logger loggerDiferente = new BarbieLogger();
        assertEquals(loggerAuxiliar.hashCode(), this.contagemLogger.hashCode());
        assertNotEquals(loggerDiferente.hashCode(), this.contagemLogger.hashCode());
    }
}