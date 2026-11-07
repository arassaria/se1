package org.hbrs.se1.ws26.exercises.uebung4.control;

import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceException;
import org.hbrs.se1.ws26.exercises.uebung4.persistence.PersistenceStrategy;
import org.hbrs.se1.ws26.exercises.uebung4.entities.Identifiable;

import java.util.ArrayList;
import java.util.List;

public class Container<T extends Identifiable> {

    private List<T> data;
    private PersistenceStrategy<T> persistenceStrategy;

    protected Container() {
        this.data = new ArrayList<>();
    }

    public void setPersistenceStrategy(PersistenceStrategy<T> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public void addEntry(T entry) throws ContainerException {
        if (entry == null) {
            throw new IllegalArgumentException("Entry darf nicht null sein!");
        }
        for (T e : data) {
            if (e.getID() == entry.getID()) {
                throw new ContainerException(e.getID());
            }
        }
        data.add(entry);
    }

    public String deleteEntry(Integer id) {
        for (T e : data) {
            if(e.getID() == id) {
                data.remove(e);
                return "Entry mit der ID " + id + " wurde gelöscht.";
            }
        }
        return "Kein Entry mit der ID " + id + " wurde in den Einträgen gefunden.";
    }

    public void deleteAllEntries() {
        data.clear();
    }

    public int size() {
        return data.size();
    }

    public List<T> getCurrentList() {
        return new ArrayList<>(data);
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenz-Strategie gesetzt!");
        }
        try {
            persistenceStrategy.save(data);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gesetzte Persistenz-Strategie ist nicht implementiert!");
        }
    }

    public void load() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Persistenz-Strategie gesetzt!");
        }
        try {
            List<T> loadedList = persistenceStrategy.load();
            data.clear();
            if (loadedList != null) {
                data.addAll(loadedList);
            }
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Die gesetzte Persistenz-Strategie ist nicht implementiert!");
        }
    }
}
