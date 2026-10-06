package org.hbrs.se1.ws26.exercises.uebung1.view;
import org.hbrs.se1.ws26.exercises.uebung1.control.*;

public class Client {

	/**
	 * Methode zur Ausgabe einer Zahl auf der Console
	 * (auch bezeichnet als CLI, Terminal)
	 * Verwendung des Design Pattern: Factory Method (GoF, Kapitel 6)
	 * Problem: Der Client darf keine konkrete Klasse (GermanTranslator) mit new erzeugen, sonst ist er fest an diese Implementierung gekoppelt.
	 * Lösung: Die Objekterzeugung wird an die Klasse TranslatorFactory ausgelagert. Der Client kennt nur das Interface Translator; die Factory erzeugt das konkrete Objekt und setzt dabei auch das Erstellungsdatum.
	 *
	 */
		 void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!

			 Translator t = TranslatorFactory.createGT();

			 System.out.println("Das Ergebnis der Berechnung: " +
					t.translateNumber(aNumber)  );

		 }
}





