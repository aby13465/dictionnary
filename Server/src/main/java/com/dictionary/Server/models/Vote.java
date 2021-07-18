package com.dictionary.Server.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Vote implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "voter_id")
    private User voter;

    @Id
    @ManyToOne()
    @JoinColumn(name = "definition_id")
    private Definition definition;

    private boolean vote;

    public Vote() {
    }

    public Vote(User voter, Definition definition, boolean vote) {
        this.voter = voter;
        this.definition = definition;
        this.vote = vote;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote1 = (Vote) o;
        return vote == vote1.vote && Objects.equals(voter, vote1.voter) && Objects.equals(definition, vote1.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voter, definition, vote);
    }
}