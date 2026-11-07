package org.hbrs.se1.ws26.exercises.uebung4.control;

public class ContainerException extends Exception {

    public ContainerException(Integer id) {
            super("Der Eintrag mit der ID " + id + " ist bereits vorhanden!");
    }

}
