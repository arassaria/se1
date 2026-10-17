package org.hbrs.se1.ws26.solutions.uebung1.control.factory;

import org.hbrs.se1.ws26.solutions.uebung1.control.EnglishTranslator;
import org.hbrs.se1.ws26.solutions.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws26.solutions.uebung1.control.Translator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Factory-Klasse zur konsistenten und zentralen Erstellung von Translator-Objekten
 * (Design Pattern Factory Method (Gamma, 1995), siehe SE-1, Kapitel 6)
 *
 * @author saschaalda
 *
 */
public class TranslatorFactory {

	public static Translator createGermanTranslator() {
		// Vorteil hier: der Typ kann hier und auch NUR hier ausgetauscht werden
		// Auch die Objektparametrisierung (z.B. das initiale Setzen eines Datums)
		// kann zentral organisiert werden
		GermanTranslator translator = new GermanTranslator();
		translator.setDate( getDatum() );
		return translator;
	}

	public static Translator createEnglishTranslator() {
		// Vorteil hier: der Typ kann hier und auch NUR hier ausgetauscht werden
		// Auch die Objektparametrisierung kann zentral organisiert werden
		EnglishTranslator translator = new EnglishTranslator();
		translator.setDate( getDatum() );
		return translator;
	}

	private static String getDatum() {
		// Aktuelles Datum ermitteln
		LocalDate heute = LocalDate.now();

		// Format festlegen (z. B. Tag.Monat.Jahr)
		DateTimeFormatter formatierer = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		// Formatieren und ausgeben
		String formatiertesDatum = heute.format(formatierer);
		return formatiertesDatum;
	}
}
