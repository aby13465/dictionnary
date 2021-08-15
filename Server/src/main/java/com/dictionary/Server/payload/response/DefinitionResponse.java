package com.dictionary.Server.payload.response;

import java.time.LocalDate;

public class DefinitionResponse {
    private Long id;
    private String term;
    private String definition;
    private String examples;
    private LocalDate creationDate;
    private String author;
    private int likes;
    private int dislikes;
    private String voteState;

    public DefinitionResponse(Long id, String term, String definition, String examples, LocalDate creationDate, String author, int likes, int dislikes, String voteState) {
        this.id = id;
        this.term = term;
        this.definition = definition;
        this.examples = examples;
        this.creationDate = creationDate;
        this.author = author;
        this.likes = likes;
        this.dislikes = dislikes;
        this.voteState = voteState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getVoteState() {
        return voteState;
    }

    public void setVoteState(String voteState) {
        this.voteState = voteState;
    }
}
