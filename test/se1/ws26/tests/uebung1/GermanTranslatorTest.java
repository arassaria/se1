package se1.ws26.tests.uebung1;

import org.hbrs.se1.ws26.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws26.exercises.uebung1.control.Translator;
import org.hbrs.se1.ws26.exercises.uebung1.control.TranslatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Blackbox-Test der Methode translateNumber(int) aus GermanTranslator.
 *
 * Äquivalenzklassen für den Parameter "zahl":
 *   neg_Ä1: zahl < 1                              Repräsentant: -5
 *   pos_Ä2: 1 <= zahl <= 12 (eigene Zahlwörter)   Repräsentant: 7
 *   pos_Ä3: 13 <= zahl <= 19 ("...zehn")          Repräsentant: 14
 *   pos_Ä4: glatte Zehner 20, 30, ..., 90         Repräsentant: 40
 *   pos_Ä5: zusammengesetzt 21..99 ("...und...")  Repräsentant: 67
 *   pos_Ä6: zahl = 100                            Repräsentant: 100
 *   neg_Ä7: zahl > 100                            Repräsentant: 150
 *
 * Grenzwerte: Integer.MIN_VALUE, 0, 1, 12, 13, 19, 20, 21, 99, 100, 101, Integer.MAX_VALUE
 * Sonderfälle: 16 ("sechzehn"), 17 ("siebzehn"), Einer 1 in "einundzwanzig"
 */
public class GermanTranslatorTest {

    private Translator translator;

    @BeforeEach
    public void setUp() {
        translator = TranslatorFactory.createGT();
    }

    /** Erwartete Fehlermeldung laut Spezifikation */
    private String fehler(int zahl) {
        return "Übersetzung der Zahl " + zahl + " nicht möglich (" + Translator.version + ")";
    }

    // ---------- Positivtests (Repräsentanten der Äquivalenzklassen) ----------

    @Test
    public void testEinstelligeUndEigeneZahlwoerter() { // pos_Ä2
        assertEquals("sieben", translator.translateNumber(7));
    }

    @Test
    public void testZehnerZahlen13bis19() { // pos_Ä3
        assertEquals("vierzehn", translator.translateNumber(14));
    }

    @Test
    public void testSonderfaelle16und17() { // pos_Ä3, unregelmäßige Formen
        assertEquals("sechzehn", translator.translateNumber(16));
        assertEquals("siebzehn", translator.translateNumber(17));
    }

    @Test
    public void testGlatteZehner() { // pos_Ä4
        assertEquals("vierzig", translator.translateNumber(40));
    }

    @Test
    public void testZusammengesetzteZahlen() { // pos_Ä5
        assertEquals("siebenundsechzig", translator.translateNumber(67));
    }

    @Test
    public void testZusammengesetztMitEins() { // pos_Ä5, "ein" statt "eins"
        assertEquals("einundzwanzig", translator.translateNumber(21));
    }

    // ---------- Grenzwerte im gültigen Bereich ----------

    @Test
    public void testUntereGrenzeGueltig() {
        assertEquals("eins", translator.translateNumber(1));
    }

    @Test
    public void testGrenzen12und13() {
        assertEquals("zwölf", translator.translateNumber(12));
        assertEquals("dreizehn", translator.translateNumber(13));
    }

    @Test
    public void testGrenzen19und20() {
        assertEquals("neunzehn", translator.translateNumber(19));
        assertEquals("zwanzig", translator.translateNumber(20));
    }

    @Test
    public void testGrenze99() {
        assertEquals("neunundneunzig", translator.translateNumber(99));
    }

    @Test
    public void testObereGrenzeGueltig() { // pos_Ä6
        assertEquals("einhundert", translator.translateNumber(100));
    }

    // ---------- Negativtests ----------

    @Test
    public void testNegativeZahl() { // neg_Ä1
        assertEquals(fehler(-5), translator.translateNumber(-5));
    }

    @Test
    public void testNull() { // neg_Ä1, Grenzwert
        assertEquals(fehler(0), translator.translateNumber(0));
    }

    @Test
    public void testGroesser100() { // neg_Ä7
        assertEquals(fehler(150), translator.translateNumber(150));
    }

    @Test
    public void testObereGrenzeUngueltig() { // neg_Ä7, Grenzwert
        assertEquals(fehler(101), translator.translateNumber(101));
    }

    @Test
    public void testIntegerExtremwerte() { // neg_Ä1 / neg_Ä7, technische Grenzwerte
        assertEquals(fehler(Integer.MIN_VALUE), translator.translateNumber(Integer.MIN_VALUE));
        assertEquals(fehler(Integer.MAX_VALUE), translator.translateNumber(Integer.MAX_VALUE));
    }

    // ---------- Datum ----------

    @Test
    public void testDatumWirdDurchFactoryGesetzt() {
        assertInstanceOf(GermanTranslator.class, translator);
        String heute = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        assertEquals(heute, ((GermanTranslator) translator).getDate());
    }
}
