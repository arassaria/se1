package org.hbrs.se1.ws26.solutions.uebung1.view;

import org.hbrs.se1.ws26.exercises.uebung1.control.GermanTranslator;

/**
 * Verwendung Implementation Inheritance.
 */
public class ClientAlt extends GermanTranslator {

	/*
	 * Methode zur Ausgabe einer Zahl auf der Console
	 */
	public void display( int aNumber ){
		// In dieser Methode soll die Methode translateNumber 
		// mit dem übergegebenen Wert der Variable aNumber 
		// aufgerufen werden.
		// Strenge Implementierung gegen das Interface Translator gewuenscht!

		// Referenz aktiv beziehen
		// Translator translator = TranslatorFactory.createGermanTranslator(); // new GermanTranslator();
		String result = translateNumber( aNumber );

		System.out.println("Das Ergebnis der Berechnung: " + result );

		// translator = TranslatorFactory.createEnglishTranslator();
		result = translateNumber( aNumber );
		System.out.println(result);

		// System.out.println("Ergebnis auf Englisch: " + result );

	}
}




