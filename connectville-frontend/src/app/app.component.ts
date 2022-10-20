import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth/service/auth.service';
import { News } from './news/model/news.model';
import { HomepageComponent } from './news/pages/homepage/homepage.component';
import { NewsService } from './shared/service/news.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  newsList: News[] = [];
  news!: News;

  constructor(
    public authService: AuthService,
    private newsService: NewsService,
    public router: Router
  ) {}
  logoutClicked() {
    this.authService.logout();
    this.router.navigate(['login']);
  }
  createNewsClicked() {
    this.router.navigate(['createNews']);
  }

  homepageClicked() {
    this.router.navigate(['homepage']);
  }
}
