package org.hbrs.se1.ws26.exercises.uebung3;

import org.hbrs.se1.ws26.exercises.uebung3.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Container {

    private static final Container container = new Container();
    private List<Member> members;
    private PersistenceStrategy<Member> persistenceStrategy;

    private Container() {
        this.members = new ArrayList<>();
    }

    public static Container getInstance() {
        return container;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

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

    public void deleteAllMembers() {
        members.clear();
    }

    public int size() {
        return members.size();
    }

    public List<Member> getCurrentList() {
        return new ArrayList<>(members);
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenz-Strategie gesetzt!");
        }
        try {
            persistenceStrategy.save(members);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gesetzte Persistenz-Strategie ist nicht implementiert!");
        }
    }

    public void load() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenz-Strategie gesetzt!");
        }
        try {
            List<Member> loadedList = persistenceStrategy.load();
            members.clear();
            if (loadedList != null) {
                members.addAll(loadedList);
            }
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gesetzte Persistenz-Strategie ist nicht implementiert!");
        }
    }
}
