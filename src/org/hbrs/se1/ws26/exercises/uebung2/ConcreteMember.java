package org.hbrs.se1.ws26.exercises.uebung2;

public class ConcreteMember implements Member {
    private final int id;

    public ConcreteMember(int id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Member (ID = " + id + ")";
    }
}
