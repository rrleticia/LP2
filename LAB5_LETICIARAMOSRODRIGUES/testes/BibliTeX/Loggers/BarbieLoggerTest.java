package BibliTeX.Loggers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BarbieLoggerTest {

    BarbieLogger barbieLogger;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void preparaLogger(){
        this.barbieLogger = new BarbieLogger();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getNomeLogger() {
        assertEquals("BarbieLogger", this.barbieLogger.getNomeLogger());
    }

    @Test
    void logSemParametro() {
        barbieLogger.log("CONTAGEM");
        assertEquals("[BARBIE PERGUNTOU: 'O QUE VOCÊ FEZ KEN?' >KEN RESPONDEU': 'CONTAGEM']", outputStreamCaptor.toString().trim());
    }

    @Test
    void logComParametro() {
        barbieLogger.log("CADASTRO", "NOVA TRANSFORMAÇÃO");
        assertEquals("[BARBIE PERGUNTOU: 'O QUE VOCÊ FEZ KEN?' >KEN RESPONDEU: 'CADASTRO'] NOVA TRANSFORMAÇÃO", outputStreamCaptor.toString().trim());
    }


    @Test
    void testToString() {
        assertEquals("[BarbieLogger] QUANTIDADE DE LOGS: 0", this.barbieLogger.toString());
    }

    @Test
    void testEquals() {
        Logger loggerAuxiliar = new BarbieLogger();
        Logger loggerDiferente = new ConsoleLogger();
        assertEquals(loggerAuxiliar, this.barbieLogger);
        assertNotEquals(loggerDiferente, this.barbieLogger);
    }

    @Test
    void testHashCode() {
        Logger loggerAuxiliar = new BarbieLogger();
        Logger loggerDiferente = new ConsoleLogger();
        assertEquals(loggerAuxiliar.hashCode(), this.barbieLogger.hashCode());
        assertNotEquals(loggerDiferente.hashCode(), this.barbieLogger.hashCode());
    }

}