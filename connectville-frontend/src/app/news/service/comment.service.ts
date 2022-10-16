import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../model/comment.model';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  constructor(private http: HttpClient) {}

  getAllCommentsByNewsId(newsId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(
      environment.apiUrl + '/news/' + newsId + '/comments'
    );
  }

  createComment(text: string, newsId: number): Observable<Comment> {
    return this.http.put<Comment>(
      environment.apiUrl + '/news/' + newsId + '/comment',
      {
        text,
      }
    );
  }
}
