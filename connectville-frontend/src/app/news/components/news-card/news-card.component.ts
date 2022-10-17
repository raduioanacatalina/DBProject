import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  @Output()
  deleted: EventEmitter<void> = new EventEmitter();

  constructor(
    public dialog: MatDialog,
    private newsService: NewsService,
    private _snackBar: MatSnackBar
  ) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewComponent, {
      width: '250px',
      data: { comment: this.comment, newsId: this.news.id },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      this.comment = result;
    });
  }

  openDialogSeeComments() {
    this.dialog.open(CommentsPopUpComponent, {
      data: { comment: this.comment, newsId: this.news.id },
    });
  }

  deleteNews() {
    this.newsService.deleteNews(this.news.id).subscribe({
      next: () => {
        this.deleted.emit();
      },
      error: () => {
        this._snackBar.open('Delete failed!', 'Ok');
      },
    });
  }

  // increaseLike() {
  //   this.news.likes++;
  // }

  pinPost(): void {
    if (this.news.isPinned === false) this.news.isPinned = true;
    else this.news.isPinned = false;
    console.log(this.news);
  }

  ngOnInit(): void {}
}
