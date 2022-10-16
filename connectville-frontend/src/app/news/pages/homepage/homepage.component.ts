import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { News } from '../../model/news.model';
import { AuthService } from 'src/app/auth/service/auth.service';
import { NewsService } from 'src/app/shared/service/news.service';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { CreateNewsComponent } from 'src/app/admin/pages/create-news/create-news.component';
import { DialogOverviewComponent } from '../../components/dialog-overview/dialog-overview.component';
import { MatSnackBar } from '@angular/material/snack-bar';

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
    private _snackBar: MatSnackBar
  ) {}

  createNewsClicked() {
    this.router.navigate(['createNews']);
  }

  ngOnInit(): void {
    this.newsService.getAllNews().subscribe((news: News[]) => {
      this.newsList = [...news];
    });
    this.newsService.onDelete().subscribe((response) => {
      let index = this.newsList.findIndex((e) => e.id === response);
      this.newsList.splice(index, 1);
      this._snackBar.open('Succesfully deleted', 'Ok');
    });
  }
}
