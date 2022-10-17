import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth/service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  constructor(private authService: AuthService, public router: Router) {}
  logoutClicked() {
    this.authService.logout();
    this.router.navigate(['login']);
  }
  createNewsClicked() {
    this.router.navigate(['createNews']);
  }
}
