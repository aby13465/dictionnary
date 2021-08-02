import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  email: string = "";
  password: string = "";
  message: string = "";

  constructor(private auth: AuthService) { }

  onClick() {
    this.auth.signupUser(this.email, this.password).toPromise().then((data: any) => {
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
