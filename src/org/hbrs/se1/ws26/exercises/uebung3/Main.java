package org.hbrs.se1.ws26.exercises.uebung3;

public class Main {
    public static void main(String[] args) {
        Container container = Container.getInstance();
        MemberView view = new MemberView();
        Client client = new Client(container, view);
        try {
            client.run();
        } catch (ContainerException e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }
}
