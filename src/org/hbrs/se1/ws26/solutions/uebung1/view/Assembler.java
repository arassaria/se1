package org.hbrs.se1.ws26.solutions.uebung1.view;

import org.hbrs.se1.ws26.solutions.uebung1.control.GermanTranslator;

/**
 * Anwendung Dependency Injection Pattern (DI); Kap. 6 / SE-2
 * In Praxis: Framework Spring (SE-2)
  */
public class Assembler {

    public static void main(String[] args) {
        GermanTranslator translator = new GermanTranslator();
        ClientDI client = new ClientDI( translator );

        translator.translateNumber(1);
        // client.setTranslator();
    }

}
