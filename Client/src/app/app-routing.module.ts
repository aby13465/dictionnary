import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { AddComponent } from './components/add/add.component'
import { HomeComponent } from './components/home/home.component'
import { NotfoundComponent } from './components/notfound/notfound.component'
import { ProfileComponent } from './components/profile/profile.component'
import { SigninComponent } from './components/signin/signin.component'
import { SignupComponent } from './components/signup/signup.component'
import { AuthGuard } from './guards/auth.guard'
import { SignGuard } from './guards/sign.guard'

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'signin', component: SigninComponent, canActivate: [SignGuard] },
  { path: 'signup', component: SignupComponent, canActivate: [SignGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'add', component: AddComponent, canActivate: [AuthGuard] },
  { path: '**', component: NotfoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
