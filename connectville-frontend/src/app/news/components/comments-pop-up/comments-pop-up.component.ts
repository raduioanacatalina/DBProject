import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Comment } from '../../model/comment.model';
import { DialogData } from '../../pages/homepage/homepage.component';
import { CommentService } from '../../service/comment.service';

@Component({
  selector: 'app-comments-pop-up',
  templateUrl: './comments-pop-up.component.html',
  styleUrls: ['./comments-pop-up.component.css'],
})
export class CommentsPopUpComponent implements OnInit {
  commentList: Comment[] = [];
  comment!: Comment;
  constructor(private commentService: CommentService, 
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  ngOnInit(): void {
    this.commentService
      .getAllCommentsByNewsId(this.data.newsId)
      .subscribe((comments: Comment[]) => {
        this.commentList = [...comments];
      });
  }
}
