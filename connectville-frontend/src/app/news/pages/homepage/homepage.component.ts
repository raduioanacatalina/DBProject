import { Component, Inject, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { News } from '../../model/news.model';
import { NewsService } from 'src/app/shared/service/news.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

export interface DialogData {
  newsId: number;
  comment: string;
}

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent implements OnInit {
  newsList: News[] = [];

  constructor(
    private router: Router,
    private newsService: NewsService,
    private _snackBar: MatSnackBar,
    private activatedRoute: ActivatedRoute
  ) {}

  onNewsDelete(newsId: number) {
    this.newsList = this.newsList.filter((news) => {
      return news.id !== newsId;
    });
  }

  // filterClicked(cop: string) {
  //   this.newsService.getAllNewsByCop(cop).subscribe((news: News[]) => {
  //     this.newsList = [...news];
  //     console.log(this.newsList);
  //     console.log(cop);
  //     this.router.navigate(['homepage'], {
  //       queryParams: { order: cop },
  //     });
  //   });
  // }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params) => {
      console.log(params);
      if (params['CoP'] == undefined) {
        this.newsService.getAllNews().subscribe((news: News[]) => {
          this.newsList = [...news];
        });
      } else {
        this.newsService
          .getAllNewsByCop(params['CoP'])
          .subscribe((news: News[]) => {
            this.newsList = [...news];
          });
      }
    });
  }
}
