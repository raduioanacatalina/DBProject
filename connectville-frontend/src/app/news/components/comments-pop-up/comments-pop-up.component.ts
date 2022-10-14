import { Component, OnInit } from '@angular/core';
import { Comment } from '../../model/comment.model';
import { CommentService } from '../../service/comment.service';

@Component({
  selector: 'app-comments-pop-up',
  templateUrl: './comments-pop-up.component.html',
  styleUrls: ['./comments-pop-up.component.css'],
})
export class CommentsPopUpComponent implements OnInit {
  commentList: Comment[] = [];
  comment!: Comment;
  constructor(private commentService: CommentService) {}

  ngOnInit(): void {
    this.commentService
      .getAllCommentsByNewsId(2)
      .subscribe((comments: Comment[]) => {
        this.commentList = [...comments];
      });
  }
}
