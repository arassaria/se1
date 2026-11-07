package se1.ws26.tests.uebung4;

import org.hbrs.se1.ws26.exercises.uebung4.entities.UserStory;
import org.hbrs.se1.ws26.exercises.uebung4.entities.UserStoryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStoryTest {
    @Test
    void priorityTest(){
        UserStory us = new UserStory(1,"test",3,2,2,2,"test","test");
        assertEquals(1.25, us.getPriority(), 0.001);
    }

    @Test
    void illegalArgumentsTest() {
        assertThrows(UserStoryException.class, () -> new UserStory(1, "test", -1, -2, -3, -4, "test", "test"));
    }

    @Test
    void illegalArgumentSetterTest() {
        UserStory us = new UserStory(1, "test", 1,1,1,1,"test","test");
        assertThrows(UserStoryException.class, ()->us.setEffort(0));
    }

    @Test
    void priorityAfterSetterTest() {
        UserStory us = new UserStory(1, "test", 3,2,2,2,"test","test");
        assertEquals(1.25, us.getPriority(), 0.001);
        us.setValue(4);
        assertEquals(1.5, us.getPriority(), 0.001);
    }
}
