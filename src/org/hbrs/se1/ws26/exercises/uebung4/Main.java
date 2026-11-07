package org.hbrs.se1.ws26.exercises.uebung4;

import org.hbrs.se1.ws26.exercises.uebung4.control.Container;
import org.hbrs.se1.ws26.exercises.uebung4.control.UserStoryContainer;
import org.hbrs.se1.ws26.exercises.uebung4.entities.UserStory;
import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws26.exercises.uebung4.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        PersistenceStrategyStream<UserStory> persistenceStrategyStream = new PersistenceStrategyStream<>();
        Container<UserStory> container = UserStoryContainer.getInstance();
        container.setPersistenceStrategy(persistenceStrategyStream);
        ConsoleView view = new ConsoleView(container);
        view.start();
    }
}
