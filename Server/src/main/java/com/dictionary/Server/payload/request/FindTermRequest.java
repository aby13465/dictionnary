package com.dictionary.Server.payload.request;

public class FindTermRequest {
    private String token;
    private String term;

    public FindTermRequest(String token, String term) {
        this.token = token;
        this.term = term;
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
}
