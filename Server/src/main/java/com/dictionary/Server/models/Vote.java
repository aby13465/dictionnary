package com.dictionary.Server.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Vote {

    @EmbeddedId
    private VoteId voteId;

    @ManyToOne
    @JoinColumn(name = "voter_id", insertable = false, updatable = false)
    private User voter;

    @ManyToOne
    @JoinColumn(name = "definition_id", insertable = false, updatable = false)
    private Definition definition;

    private boolean vote;

    public Vote(VoteId voteId, boolean vote) {
        this.voteId = voteId;
        this.vote = vote;
    }

    public Vote() {

    }

    public VoteId getVoteId() {
        return voteId;
    }

    public void setVoteId(VoteId voteId) {
        this.voteId = voteId;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}