package org.hbrs.se1.ws26.exercises.uebung1.control;

public class GermanTranslator implements Translator {

	private String date = null;

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	 public String translateNumber(int number) {
		if (number < 1 || number > 100) return "Übersetzung der Zahl " + number + " nicht möglich (" + version + ")";
		String[] numbers = {"ein", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun", "zehn", "elf", "zwölf"};
		String[] tens = {"zehn", "zwanzig", "dreißig", "vierzig", "fünfzig", "sechzig", "siebzig", "achtzig", "neunzig", "einhundert"};
		if (number < 13) return numbers[number];
		int ten = number / 10;
		if (number == 16) return "sechzehn";
		if (number == 17) return "siebzehn";
		if (number < 20) return numbers[number % 10] + tens[ten-1];
		if (number % 10 == 0) return tens[ten-1];
		return (number % 10 == 1 ? numbers[0] : numbers[number % 10]) + "und" + tens[ten-1];
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
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
