package org.hbrs.se1.ws26.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) throws ContainerException {
        if (member == null) {
            throw new IllegalArgumentException("Member darf nicht null sein!");
        }
        for (Member m : members) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException(m.getID());
            }
        }
        members.add(member);
    }

    public String deleteMember(Integer id) {
        for (Member m : members) {
            if(m.getID().equals(id)) {
                members.remove(m);
                return "Member mit der ID " + id + " wurde gelöscht.";
            }
        }
        return "Kein Member mit der ID " + id + " wurde in den Einträgen gefunden.";
    }

    public int size() {
        return members.size();
    }

    public void dump() {
        for (Member m : members) {
            System.out.println(m);
        }
    }
}
