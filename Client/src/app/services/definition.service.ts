import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DefinitionService {

  constructor(private http: HttpClient) { }

  addDefinition(term: string, definition: string, examples: string) {
    return this.http.post("/definition/add", {
      token: "Bearer " + localStorage.getItem("user"),
      term,
      definition,
      examples
    })
  }

  findTerm(term: string) {
    return this.http.post("definition/findTerm", {
      token: "Bearer " + localStorage.getItem("user"),
      term
    })
  }

  Vote(definition: number, vote: boolean) {
    return this.http.post("definition/vote", {
      token: "Bearer " + localStorage.getItem("user"),
      definition,
      vote
    })
  }
}
