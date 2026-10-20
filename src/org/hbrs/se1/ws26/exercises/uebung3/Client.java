package org.hbrs.se1.ws26.exercises.uebung3;

public class Client {

    private final Container c;
    private final MemberView view;

    public Client(Container c, MemberView view) {
        this.c = c;
        this.view = view;
    }

    public void run() throws ContainerException {
        for (int i = 0; i < 5; i++) {
            c.addMember(new ConcreteMember(i));
        }
        view.dump(c.getCurrentList());
    }

}
