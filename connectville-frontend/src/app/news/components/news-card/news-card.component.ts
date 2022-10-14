import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NewsService } from 'src/app/shared/service/news.service';
import { News } from '../../model/news.model';
import { CommentsPopUpComponent } from '../comments-pop-up/comments-pop-up.component';
import { DialogOverviewComponent } from '../dialog-overview/dialog-overview.component';

@Component({
  selector: 'app-news-card',
  templateUrl: './news-card.component.html',
  styleUrls: ['./news-card.component.css'],
})
export class NewsCardComponent implements OnInit {
  comment!: string;
  @Input()
  news!: News;
  constructor(public dialog: MatDialog, private newsService: NewsService) {}

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

  openDialogSeeComments() {
    this.dialog.open(CommentsPopUpComponent);
  }

  deleteNews() {
    this.newsService.deleteNews();
  }

  ngOnInit(): void {}
}
