package org.hbrs.se1.ws26.exercises.uebung4.control;

import org.hbrs.se1.ws26.exercises.uebung4.entities.UserStory;

public class UserStoryContainer extends Container<UserStory>{
    private static final UserStoryContainer INSTANCE = new UserStoryContainer();

    private UserStoryContainer() {
        super();
    }

    public static UserStoryContainer getInstance() {
        return INSTANCE;
    }
}
