import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  email: string = "";
  password: string = "";
  message: string = "";

  constructor(private auth: AuthService, private router: Router) { }

  onClick() {
    this.auth.signinUser(this.email, this.password).toPromise().then((data: any) => {
      if (data.message)
        this.message = data.message;
      if (data.token) {
        localStorage.setItem("user", data.token)
        location.reload()
      }
    })
  }

  onChangeEmail(event: any): void {
    this.email = event.target.value;
  }

  onChangePassword(event: any): void {
    this.password = event.target.value;
  }
}
