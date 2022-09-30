import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/service/auth.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private authService:AuthService, private router:Router) { }
  loginClicked() {
    this.authService.logout();
    this.router.navigate(['login']);
  }
  ngOnInit(): void {
  }

}
