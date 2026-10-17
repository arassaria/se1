package org.hbrs.se1.ws26.solutions.uebung1.control;

public class GermanTranslator implements Translator {

	public String date = null; // Default-Wert

	// Das initiale Array mit den Zahlen
	private String[] zahlen = { "eins", "zwei", "drei", "vier" , "fünf",
			"sechs" , "sieben" , "acht" , "neun" , "zehn" };

	private String[] zehner = { "zwanzig", "dreißig", "vierzieg", "fünfzig" , "sechzig",
			"siebzig" , "achtzig" , "neunzig"  };


	/*
	 * Methode zur Transformation einer numerischen Zahl in einen String
	 * Kompilierfehler deswegen, weil kein Return gegeben war.
	 */
	public String translateNumber( int number ) {
		String result = "";

		if ( number == Integer.MAX_VALUE ) return "zwei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig";

		if ( (number <= 0) || (number > 100) ) {
			return "Übersetzung der Zahl " + number + " nicht möglich! (V " + version + ")";
		}

		if ( number == 100 ) return "einhundert";

		// Für Zahlen kleiner 20: individuelle Ausgabe
		if (number < 20) {
			return translateBelowTwenty(number);
		}

		// Ab hier: Berechnung einer Zahl > 20
		int einser = number % 10; // Rest von 34 => 4 (letzte Ziffer) - Module Division; ermittelt den Rest
		int zehner = number / 10; // Zehnerstelle von 34 => 3 (erste Ziffer) - Division über Integer

		if (einser > 0) {
			if ( einser == 1 ) {
				result += "ein" + "und";
			} else {
				result += zahlen[einser - 1] + "und";
			}
		}

		result = result + this.zehner[ zehner - 2 ];
		return result;
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}


	private String translateBelowTwenty(int number) {
		return switch (number) {
			case 1  -> "eins";
			case 2  -> "zwei";
			case 3  -> "drei";
			case 4  -> "vier";
			case 5  -> "fünf";
			case 6  -> "sechs";
			case 7  -> "sieben";
			case 8  -> "acht";
			case 9  -> "neun";
			case 10 -> "zehn";
			case 11 -> "elf";
			case 12 -> "zwölf";
			case 13 -> "dreizehn";
			case 14 -> "vierzehn";
			case 15 -> "fünfzehn";
			case 16 -> "sechzehn";
			case 17 -> "siebzehn";
			case 18 -> "achtzehn";
			case 19 -> "neunzehn";
			default -> throw new IllegalArgumentException(
					"Number must be between 0 and 19: " + number
			);
		};
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: dd.MM.yyyy (Beispiel: "20.08.2026"))
	 * Das Datum sollte system-intern durch eine Factory-Klasse gesetzt werden und nicht von externen View-Klassen
	 * Technisch sollte einfach das "heutige" Datum gesetzt werden.
	 */
	public void setDate( String date ) {
		this.date = date;
	}

	/**
	 * Auslesen des gesetzten Datums
	 * @return
	 */
	public String getDate() {
		return date;
	}
}

