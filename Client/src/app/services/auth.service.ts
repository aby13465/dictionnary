import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  signupUser(email: string, password: string) {
    return this.http.post("/user/add", {
      email: email,
      password: password
    })
  }

  signinUser(email: string, password: string) {
    return this.http.post("/user/find", {
      email,
      password
    })
  }

  disconnect(){
    localStorage.removeItem("user")
  }

  loggedIn() {
    return !!localStorage.getItem("user")
  }
}
