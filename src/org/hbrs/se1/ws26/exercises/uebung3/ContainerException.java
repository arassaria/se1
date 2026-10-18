package org.hbrs.se1.ws26.exercises.uebung3;

public class ContainerException extends Exception {

    public ContainerException(Integer id) {
            super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");
    }

}
