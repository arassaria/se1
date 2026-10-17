package se1.ws26.solutions.uebung1.test;

import org.hbrs.se1.ws26.solutions.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws26.solutions.uebung1.control.Translator;
import org.hbrs.se1.ws26.solutions.uebung1.control.factory.TranslatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GermanTranslatorTest {

    private Translator translator = null;

    @BeforeEach
    public void setUp() {
        this.translator = TranslatorFactory.createGermanTranslator();
    }

    @Test
    public void testLowerNumbersFromOnetoTen
            () {
        // Positiver Test zum Testen von Zahlen von 1-10 (Positiv-Test)
        assertEquals("vier", this.translator.translateNumber(4));

        // Zweite Assertion nicht notwendig, aber kann optional sein,
        assertEquals("neun", this.translator.translateNumber(9));
    }

    @Test
    public void testNumbersFromElevenToNineTeen() {
        // Positiver Test zum Testen von Zahlen von 11-19 (Positiv-Test)
        assertEquals("dreizehn", this.translator.translateNumber(13));

        // Zweite Assertion nicht notwendig, aber kann optional sein,
        assertEquals("achtzehn", this.translator.translateNumber(18));
    }

    @Test
    public void testNumbersFromTweentyToTweentyNine() {
        // Positiver Test zum Testen von Zahlen von 20-29 (Positiv-Test)
        assertEquals("dreiundzwanzig", this.translator.translateNumber(23));
    }

    @Test
    public void testNumbersFromThirtyToFortyNine() {
        // Positiver Test zum Testen von Zahlen von 30-49 (Positiv-Test)
        assertEquals("vierunddreißig", this.translator.translateNumber(34));
    }

    @Test
    public void testNumbersFromFiftyToNinetyNine() {
        // Positiver Test zum Testen von Zahlen von 50-99 (Positiv-Test)
        assertEquals("siebenundsechzig", this.translator.translateNumber(67));
    }

    @Test
    public void testNegativeNumbers() {
        // Negativer Test
        String result = "Übersetzung der Zahl -10 nicht möglich! (V " + Translator.version + ")";
        assertEquals(result, this.translator.translateNumber(-10));

        result = translator.translateNumber(-5);
        assertEquals("Übersetzung der Zahl -5 nicht möglich! (V 1.9)" , result);
    }

    @Test
    public void testNero() {
        //  Test auf Zahl Null (0).
        String result = "Übersetzung der Zahl 0 nicht möglich! (V " + Translator.version + ")";
        assertEquals(result, this.translator.translateNumber(0));
    }

    @Test
    public void testBigNumber() {
        //  Test auf  Null.
        String result = "Übersetzung der Zahl 999 nicht möglich! (V " + Translator.version + ")";
        assertEquals(result, this.translator.translateNumber(999));
    }

    @Test
    public void testBiggestNumber() {
        //  Test auf INT-Max (Positiv).
        String result = this.translator.translateNumber( Integer.MAX_VALUE  );
        assertEquals("zwei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig" , result);
    }

    @Test
    public void testBiggestNumberAndOne() {
        //  Test auf INT-Max (Positiv).
        String result = this.translator.translateNumber( Integer.MAX_VALUE + 1 );
        assertEquals("Übersetzung der Zahl -2147483648 nicht möglich! (V 1.9)" , result );
    }

    @Test
    public void testLowestNumberFrom() {
        //  Test auf INT-Max (Positiv).
        String result = this.translator.translateNumber( Integer.MIN_VALUE );
        assertEquals("Übersetzung der Zahl -2147483648 nicht möglich! (V 1.9)" , result );
    }

    @Test
    public void testTweentyFirst() {
        // Anforderung hier: ein-und-zwanzig und nicht eins-und-zwanzig
        String result = translator.translateNumber(21);
        assertEquals("einundzwanzig" , result );

    }

    @Test
    public void testLowestAndBiggestValidNumber() {
        // Anforderung hier: ein-und-zwanzig und nicht eins-und-zwanzig
        String result = translator.translateNumber(1);
        assertEquals("eins" , result );

        result = translator.translateNumber(100);
        assertEquals("einhundert" , result );
    }

    @Test
    public void testDatum() {
        // Aktuelles Datum ermitteln
        LocalDate heute = LocalDate.now();

        // Format festlegen (z. B. Tag.Monat.Jahr)
        DateTimeFormatter formatierer = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Formatieren und ausgeben
        String formatiertesDatum = heute.format(formatierer);

        String date = ( (GermanTranslator) translator).getDate();
        System.out.println(date);
        assertEquals( date , formatiertesDatum );
    }



}
