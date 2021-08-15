import { Component, OnInit } from '@angular/core';
import { DefinitionService } from 'src/app/services/definition.service';

@Component({
  selector: 'app-term',
  templateUrl: './term.component.html',
  styleUrls: ['./term.component.css']
})
export class TermComponent implements OnInit {

  term: string = location.pathname.substring(6)
  data: any = []

  constructor(private definitionService: DefinitionService) { }

  ngOnInit(): void {
    this.definitionService.findTerm(this.term).toPromise().then(data => this.data = data)
  }

  voteStateLiked(state: string): string {
    return state === "liked" ? "primary" : "basic"
  }

  voteStateDisliked(state: string): string {
    return state === "disliked" ? "primary" : "basic"
  }

  voteTerm(definition: number, vote: boolean) {
    this.definitionService.Vote(definition, vote).toPromise().then(message => console.log(message))
  }

}
