package se1.ws26.tests.uebung4;

import org.hbrs.se1.ws26.exercises.uebung4.control.Container;
import org.hbrs.se1.ws26.exercises.uebung4.control.ContainerException;
import org.hbrs.se1.ws26.exercises.uebung4.control.UserStoryContainer;
import org.hbrs.se1.ws26.exercises.uebung4.entities.UserStory;
import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {

    private Container<UserStory> container;

    @BeforeEach
    void setup() {
        container = UserStoryContainer.getInstance();
        container.deleteAllEntries();
        container.setPersistenceStrategy(null);
    }

    @Test
    void addEntryTest() throws ContainerException {
        container.addEntry(new UserStory(1, "test", 1,1,1,1,"test","test"));
        assertEquals(1, container.size());
    }

    @Test
    void illegalDoubleEntryIDTest() throws ContainerException {
        container.addEntry(new UserStory(1,"test",1,1,1,1,"test","test"));
        assertThrows(ContainerException.class, ()->container.addEntry(new UserStory(1,"test2",1,1,1,1,"test","test")));
    }

    @Test
    void roundTripTest() throws ContainerException, PersistenceException {
        PersistenceStrategyStream<UserStory> persistenceStrategyStream = new PersistenceStrategyStream<>();
        container.setPersistenceStrategy(persistenceStrategyStream);
        container.addEntry(new UserStory(1,"test",1,1,1,1,"test","test"));
        assertEquals(1, container.size());
        container.store();
        assertEquals(1, container.size());
        container.deleteAllEntries();
        assertEquals(0, container.size());
        container.load();
        assertEquals(1, container.size());
        assertEquals(1, container.getCurrentList().get(0).getID());
    }
}
