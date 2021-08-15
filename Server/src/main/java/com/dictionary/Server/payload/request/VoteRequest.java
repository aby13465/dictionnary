package com.dictionary.Server.payload.request;

public class VoteRequest {
    private String token;
    private Long definition;
    private Boolean vote;

    public VoteRequest(String token, Long definition, Boolean vote) {
        this.token = token;
        this.definition = definition;
        this.vote = vote;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getDefinition() {
        return definition;
    }

    public void setDefinition(Long definition) {
        this.definition = definition;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }
}
