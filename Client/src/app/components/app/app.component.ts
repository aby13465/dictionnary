import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  toggler: boolean = false
  loggedin: boolean = true

  constructor(private auth: AuthService, private router: Router) { 
    this.loggedin = this.auth.loggedIn()
  }

  disconnect(){
    this.auth.disconnect()
    location.reload()
  }

  changeToggler() {
    this.toggler = !this.toggler
  }
}