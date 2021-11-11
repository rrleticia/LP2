package BibliTeX.Loggers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BreakPointLoggerTest {

    BreakPointLogger breakpointLogger;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void preparaLogger(){
        this.breakpointLogger = new BreakPointLogger("CADASTRO");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void getNomeLogger() {
        assertEquals("BreakPointLogger", this.breakpointLogger.getNomeLogger());
    }

    @Test
    void getChamadaBreakPoint() {
        assertEquals("CADASTRO", breakpointLogger.getChamadaBreakPoint());
    }

    @Test
    void getQuantidadeChamadas() {
        breakpointLogger.log("TRANSFORMA");
        breakpointLogger.log("CADASTRO");
        breakpointLogger.log("LISTAGEM");
        assertEquals(1, breakpointLogger.getQuantidadeChamadas());
        breakpointLogger.log("CADASTRO");
        assertEquals(2, breakpointLogger.getQuantidadeChamadas());
    }

    @Test
    void logSemParametro() {
        breakpointLogger.log("TRANSFORMA");
        breakpointLogger.log("CADASTRO");
        breakpointLogger.log("LISTAGEM");
        assertEquals("[BREAKPOINT LOGGER: MÉTODO DE BREAKPOINT - CADASTRO]", outputStreamCaptor.toString().trim());
    }

    @Test
    void logComParametro() {
        Logger breakpointLoggerAuxiliar = new BreakPointLogger("TRANSFORMA");
        breakpointLoggerAuxiliar.log("TRANSFORMA", "TEXTO ORIGINAL");
        breakpointLoggerAuxiliar.log("CADASTRO");
        breakpointLoggerAuxiliar.log("CADASTRO");
        assertEquals("[BREAKPOINT LOGGER: MÉTODO DE BREAKPOINT - TRANSFORMA] TEXTO ORIGINAL", outputStreamCaptor.toString().trim());
    }


    @Test
    void testToString() {
        breakpointLogger.log("CADASTRO");
        breakpointLogger.log("TRANSFORMA");
        breakpointLogger.log("CADASTRO");
        breakpointLogger.log("LISTAGEM");
        assertEquals("[BREAKPOINT LOGGER: MÉTODO DE BREAKPOINT - CADASTRO] QUANTIDADE DE CHAMADAS: 2", breakpointLogger.toString());
    }

    @Test
    void testEquals() {
        Logger loggerAuxiliarIgual = new BreakPointLogger("CADASTRO");
        Logger loggerAuxiliarDiferente = new BreakPointLogger("TRANSFORMA");
        Logger loggerDiferente = new ConsoleLogger();
        assertEquals(loggerAuxiliarIgual, this.breakpointLogger);
        assertNotEquals(loggerAuxiliarDiferente, this.breakpointLogger);
        assertNotEquals(loggerDiferente, this.breakpointLogger);
    }

    @Test
    void testHashCode() {
        Logger loggerAuxiliar = new BreakPointLogger("CADASTRO");
        Logger loggerDiferente = new ConsoleLogger();
        assertEquals(loggerAuxiliar.hashCode(), this.breakpointLogger.hashCode());
        assertNotEquals(loggerDiferente.hashCode(), this.breakpointLogger.hashCode());
    }

}