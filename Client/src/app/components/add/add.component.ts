import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DefinitionService } from 'src/app/services/definition.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  term: string = "";
  definition: string = "";
  examples: string = "";

  constructor(private definitionService: DefinitionService, private router: Router) { }

  ngOnInit(): void { }

  onClick() {
    this.definitionService.addDefinition(this.term, this.definition, this.examples).toPromise().then((data: any) => {
      if (data.message === "Success")
        this.router.navigate(["/"])
    })
  }

  onChangeTerm(event: any): void {
    this.term = event.target.value
  }

  onChangeDefinition(event: any): void {
    this.definition = event.target.value
  }

  onChangeExamples(event: any): void {
    this.examples = event.target.value
  }
}
