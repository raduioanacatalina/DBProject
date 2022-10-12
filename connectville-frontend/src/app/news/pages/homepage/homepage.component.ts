import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { News } from 'src/app/auth/model/news.model';
import { AuthService } from 'src/app/auth/service/auth.service';
import { NewsService } from 'src/app/shared/service/news.service';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { CreateNewsComponent } from 'src/app/admin/pages/create-news/create-news.component';
import { DialogOverviewComponent } from '../../components/dialog-overview/dialog-overview.component';

export interface DialogData {
  comment: string;
}

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent implements OnInit {
  comment!: string;
  typesOfShoes: string[] = ['HR', 'Legal', 'Tech'];
  newsList: News[] = [];

  constructor(
    private authService: AuthService,
    private router: Router,
    private newsService: NewsService,
    public dialog: MatDialog
  ) {}
  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewComponent, {
      width: '250px',
      data: { comment: this.comment },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      this.comment = result;
    });
  }

  createNewsClicked() {
    this.router.navigate(['createNews']);
  }

  ngOnInit(): void {
    this.newsService.getAllNews().subscribe((news: News[]) => {
      this.newsList = [...news];
    });
  }
}
