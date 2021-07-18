package com.dictionary.Server.payload.request;

public class AddDefinitionRequest {
    private String token;
    private String term;
    private String definition;
    private String examples;

    public AddDefinitionRequest(String token, String term, String definition, String examples) {
        this.token = token;
        this.term = term;
        this.definition = definition;
        this.examples = examples;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

}
