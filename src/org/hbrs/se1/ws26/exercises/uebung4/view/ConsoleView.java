package org.hbrs.se1.ws26.exercises.uebung4.view;

import org.hbrs.se1.ws26.exercises.uebung4.control.Container;
import org.hbrs.se1.ws26.exercises.uebung4.control.ContainerException;
import org.hbrs.se1.ws26.exercises.uebung4.entities.UserStory;
import org.hbrs.se1.ws26.exercises.uebung4.entities.UserStoryException;
import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceException;

import javax.sound.midi.SysexMessage;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Container<UserStory> CONTAINER;
    private final Scanner sc;
    private final String indicator = "> ";
    private boolean isRunning;

    public ConsoleView(Container<UserStory> container) {
        this.CONTAINER = container;
        sc = new Scanner(System.in);
        this.isRunning = true;
    }

    public void start() {
        String starttext = "UserStory-Tool v1.0 – Gib 'help' ein, um alle Befehle zu sehen.";
        System.out.println(indicator + starttext);
        menu();
    }

    private void menu() {
        while (isRunning) {
            System.out.println(indicator + "Gib einen Befehl ein:");
            System.out.print(indicator);
            String[] input = sc.nextLine().trim().split(" ", 2);
            String param = input.length == 2 ? input[1] : "";
            switch (input[0]) {
                case "help" -> help(param);
                case "enter" -> enter(param);
                case "store" -> store(param);
                case "load" -> load(param);
                case "dump" -> dump(param);
                case "exit" -> exit(param);
                default -> {
                    System.out.println(indicator + "Dieser Befehl existiert nicht. Bitte versuchen sie es erneut.");
                }
            }
        }
    }

    private void help(String param) {
        System.out.println(indicator + "dump - Gibt alle User Stories nach Priorität sortiert aus.");
        System.out.println(indicator + "enter - Neue User Story eingeben.");
        System.out.println(indicator + "exit - Anwendung beenden.");
        System.out.println(indicator + "help - Gibt eine Übersicht über alle Befehle aus.");
        System.out.println(indicator + "load - Lädt Daten über die gesetzte Persistenz-Strategie.");
        System.out.println(indicator + "store - Speichert Daten über die gesetzte Persistenz-Strategie.");
    }

    private void enter(String param) {
        int id = readInt("ID");
        String title = readString("Titel");
        String acceptance = readString("Akzeptanzkriterium");
        String project = readString("Projekt");
        int value = readInt("Mehrwert");
        int penalty = readInt("Strafe");
        int effort = readInt("Aufwand");
        int risk = readInt("Risiko");
        try {
            CONTAINER.addEntry(new UserStory(id, title, value, penalty, effort, risk, acceptance, project));
            System.out.println(indicator + "Eintrag " + id + " wurde erfolgreich angelegt.");
        } catch (ContainerException | UserStoryException e) {
            System.out.println(indicator + "Eintrag abgelehnt: " + e.getMessage());
        }
    }

    private void store(String param) {
        try {
            CONTAINER.store();
            System.out.println(indicator + CONTAINER.size() + " User Stories gespeichert.");
        } catch (PersistenceException e) {
            System.out.println(indicator + "Fehler beim Speichern: " + e.getMessage());
        }
    }

    private void load(String param) {
        try {
            CONTAINER.load();
            System.out.println(indicator + CONTAINER.size() + " User Stories geladen.");
        } catch (PersistenceException e) {
            System.out.println(indicator + "Fehler beim Laden: " + e.getMessage());
        }
    }

    private void dump(String param) {
        List<UserStory> userStoryListSorted = CONTAINER.getCurrentList().stream().filter(param.isEmpty() ? p->true : p->p.getProject().equals(param)).sorted(Comparator.comparingDouble(UserStory::getPriority).reversed()).toList();
        if(userStoryListSorted.isEmpty()) {
            System.out.println(indicator + "Keine Einträge zum Anzeigen gefunden.");
            return;
        }
        System.out.printf(indicator + "%-5s %-20s %-20s %10s%n", "ID", "Titel", "Projekt", "Priorität");
        for (UserStory u : userStoryListSorted) {
            System.out.printf(indicator + "%-5s %-20s %-20s %10.2f%n", u.getID(), u.getTitle(), u.getProject(), u.getPriority());
        }
    }

    private void exit(String param) {
        isRunning = false;
    }

    private int readInt(String label) {
        int value = 0;
        boolean isInt = false;
        System.out.print(indicator + label + ": ");
        String input = sc.nextLine().trim();
        while (!isInt) {
            try {
                value = Integer.parseInt(input);
                isInt = true;
            } catch (NumberFormatException e) {
                System.out.println(indicator + label + " muss eine Zahl sein.");
                System.out.print(indicator + label + ": ");
                input = sc.nextLine().trim();
            }
        }
        return value;
    }

    private String readString(String label) {
        System.out.print(indicator + label + ": ");
        return sc.nextLine().trim();
    }
}
