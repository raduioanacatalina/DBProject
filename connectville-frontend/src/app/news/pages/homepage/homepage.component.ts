import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { News } from 'src/app/auth/model/news.model';
import { AuthService } from 'src/app/auth/service/auth.service';
import { NewsService } from 'src/app/shared/service/news.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  typesOfShoes: string[] = ['Boots', 'Clogs', 'Loafers', 'Moccasins', 'Sneakers'];
  newsList:News[] = [];
  constructor(private authService:AuthService, private router:Router, private newsService: NewsService) { }
  createNewsClicked() {
    this.router.navigate(['createNews']);
  }
  ngOnInit(): void {
    this.newsService.getAllNews().subscribe((news:News[]) => {
      this.newsList = [...news];
    });
  }

}
