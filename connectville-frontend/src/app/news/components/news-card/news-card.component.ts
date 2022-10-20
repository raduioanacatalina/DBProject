import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from 'src/app/auth/service/auth.service';
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
  public clicked: boolean = false;
  router: any;

  constructor(
    public dialog: MatDialog,
    private authService: AuthService,
    private newsService: NewsService,
    private _snackBar: MatSnackBar,
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

  pressLikePost() {
      this.newsService.putLikePost(this.news.id).subscribe({
        next: (newsUpdated: News) => {
          if (!this.likedByUser()) {
            this._snackBar.open('You liked this post!', 'Ok');
          }
          else {
            this._snackBar.open('You unliked this post!', 'Ok');
          }
          this.news = newsUpdated;
        },
        error: () => {
          this._snackBar.open('Like failed!', 'Ok');
        },
      });
  }

  // increaseLike() {
  //   this.news.likes++;
  // }

  pinPost(): void {
    this.newsService.putSelectPin(this.news.id).subscribe({
      next: () => {
        if (this.news.pinned == false) {
          this._snackBar.open('You pinned this post!', 'Ok');
          this.news.pinned = !this.news.pinned;
        } else {
          this._snackBar.open('You unpinned this post!', 'Ok');
          this.news.pinned = !this.news.pinned;
        }
      },
      error: () => {
        this._snackBar.open('Pin failed!', 'Ok');
      },
    });
  }

  likedByUser(): boolean {
    return this.news.likes.filter(like => "" + like.userId === "" + this.authService.getId()).length > 0;
  }

  
  ngOnInit(): void {}
}
