package se1.ws26.tests.uebung2;

import org.hbrs.se1.ws26.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws26.exercises.uebung2.Container;
import org.hbrs.se1.ws26.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws26.exercises.uebung2.Member;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    @Test
    public void testZustandsuebergaenge() throws ContainerException {
        Container c = new Container();
        Member m1 = new ConcreteMember(1);
        Member m2 = new ConcreteMember(2);

        assertEquals(0, c.size());

        assertThrows(IllegalArgumentException.class, () -> c.addMember(null));
        assertEquals(0, c.size());

        c.addMember(m1);
        assertEquals(1, c.size());

        ContainerException e = assertThrows(ContainerException.class, () -> c.addMember(m1));
        assertEquals("Das Member-Objekt mit der ID 1 ist bereits vorhanden!", e.getMessage());
        assertEquals(1, c.size());

        assertThrows(IllegalArgumentException.class, () -> c.addMember(null));
        assertEquals(1, c.size());

        c.addMember(m2);
        assertEquals(2, c.size());

        e = assertThrows(ContainerException.class, () -> c.addMember(m2));
        assertEquals("Das Member-Objekt mit der ID 2 ist bereits vorhanden!", e.getMessage());
        assertEquals(2, c.size());

        assertThrows(IllegalArgumentException.class, () -> c.addMember(null));
        assertEquals(2, c.size());

        assertEquals("Kein Member mit der ID 3 wurde in den Einträgen gefunden.", c.deleteMember(3));
        assertEquals(2, c.size());

        assertEquals("Member mit der ID 2 wurde gelöscht.", c.deleteMember(2));
        assertEquals(1, c.size());

        assertEquals("Kein Member mit der ID 2 wurde in den Einträgen gefunden.", c.deleteMember(2));
        assertEquals(1, c.size());

        assertEquals("Member mit der ID 1 wurde gelöscht.", c.deleteMember(1));
        assertEquals(0, c.size());

        assertEquals("Kein Member mit der ID 1 wurde in den Einträgen gefunden.", c.deleteMember(1));
        assertEquals(0, c.size());
    }
}
