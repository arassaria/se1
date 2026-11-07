package org.hbrs.se1.ws26.exercises.uebung4.entities;

import java.io.Serializable;

public class UserStory implements Serializable, Identifiable {
    private final int ID;
    private String title, acceptance, project;
    private int value, penalty, effort, risk;
    private double priority;

    public UserStory(int id, String title, int value, int penalty, int effort, int risk, String acceptance, String project) {

        this.ID = id;
        this.title = title;
        this.value = value;
        this.penalty = penalty;
        this.effort = effort;
        this.risk = risk;
        this.acceptance = acceptance;
        this.project = project;
        if (validate()) {
            calcPriority();
        } else {
            throw new UserStoryException("Arguments for Value, Penalty, Effort or Risk are faulty and illegal. Value and Effort must be > 0, Penalty and Risk must be >= 0.");
        }
    }

    public int getID() {
        return this.ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setValue(int value) {
        if (value <= 0) {
            throw new UserStoryException("Value must be > 0.");
        } else {
            this.value = value;
            calcPriority();
        }
    }

    public int getValue() {
        return this.value;
    }

    public void setPenalty(int penalty) {
        if (penalty < 0) {
            throw new UserStoryException("Penalty must be >= 0.");
        } else {
            this.penalty = penalty;
            calcPriority();
        }
    }

    public int getPenalty() {
        return this.penalty;
    }

    public void setEffort(int effort) {
        if (effort <= 0) {
            throw new UserStoryException("Effort must be > 0.");
        } else {
            this.effort = effort;
            calcPriority();
        }
    }

    public int getEffort() {
        return this.effort;
    }

    public void setRisk(int risk) {
        if (risk < 0) {
            throw new UserStoryException("Risk must be >= 0.");
        } else {
            this.risk = risk;
            calcPriority();
        }
    }

    public int getRisk() {
        return this.risk;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getAcceptance() {
        return this.acceptance;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProject() {
        return this.project;
    }

    public double getPriority() {
        return this.priority;
    }

    private void calcPriority() {
        this.priority = (this.value + this.penalty)/(double)(this.effort + this.risk);
    }

    private boolean validate() {
        return this.value > 0 && this.penalty >= 0 && this.effort > 0 && this.risk >= 0;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID + ", Titel: " + this.title + ", Akzeptanzkriterium: " + this.acceptance + ", Projekt: " + this.project
                + ", Priorität: " + this.priority + ", Mehrwert: " + this.value + ", Strafe: " + this.penalty + ", Aufwand: " + this.effort
                + ", Risiko: " + this.risk;
    }
}
