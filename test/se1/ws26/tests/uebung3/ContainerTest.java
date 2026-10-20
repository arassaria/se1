package se1.ws26.tests.uebung3;


import org.hbrs.se1.ws26.exercises.uebung3.*;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws26.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
        container.deleteAllMembers();
        container.setPersistenceStrategy(null);
    }

    @Test
    void testMongoDBNotImplementedSolution() {
        container.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());

        PersistenceException e1 = assertThrows(PersistenceException.class, () -> container.store());
        assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, e1.getExceptionTypeType());
        assertEquals("Die gesetzte Persistenz-Strategie ist nicht implementiert!", e1.getMessage());

        PersistenceException e2 = assertThrows(PersistenceException.class, () -> container.load());
        assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, e2.getExceptionTypeType());
        assertEquals("Die gesetzte Persistenz-Strategie ist nicht implementiert!", e2.getMessage());
    }

    @Test
    void testNoStrategeySet() {
        PersistenceException e = assertThrows(PersistenceException.class, () -> container.store());
        assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, e.getExceptionTypeType());
        assertEquals("Keine Persistenz-Strategie gesetzt!", e.getMessage());
    }

    @Test
    void testWrongLocationOfFile() {
        PersistenceStrategyStream<Member> strat = new PersistenceStrategyStream<Member>();
        strat.setLocation(".");
        container.setPersistenceStrategy(strat);

        PersistenceException e1 = assertThrows(PersistenceException.class, () -> container.store());
        assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e1.getExceptionTypeType());
        assertTrue(e1.getMessage().startsWith("Fehler beim Speichern: "));

        PersistenceException e2 = assertThrows(PersistenceException.class, () -> container.load());
        assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e2.getExceptionTypeType());
        assertTrue(e2.getMessage().startsWith("Fehler beim Laden: "));
    }

    @Test
    void testStoreDeleteAndLoad() throws ContainerException, PersistenceException {
        assertEquals(0, container.size());

        container.setPersistenceStrategy(new PersistenceStrategyStream<Member>());
        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());

        container.store();
        assertEquals(1, container.size());

        container.deleteMember(1);
        assertEquals(0, container.size());

        container.load();
        assertEquals(1, container.size());

        assertEquals(1, container.getCurrentList().get(0).getID());

    }

}