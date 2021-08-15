package com.dictionary.Server.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VoteId implements Serializable {

    @Column(name = "voter_id")
    private String voterId;

    @Column(name = "definition_id")
    private Long definitionId;

    public VoteId() {
    }

    public VoteId(String voterId, Long definitionId) {
        this.voterId = voterId;
        this.definitionId = definitionId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public Long getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(Long definitionId) {
        this.definitionId = definitionId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
