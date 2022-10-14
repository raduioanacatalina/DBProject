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

  getAllCommentsByNewsId(id: number): Observable<Comment[]> {
    // return this.http.get<Comment[]>(
    //   environment.apiUrl + '/news/' + id + '/comments'
    // );
    return of([
      { id: 2, text: 'dada' },

      { id: 3, text: 'asdasdas' },
    ]);
  }

  createComment(text: string): Observable<Comment> {
    return this.http.post<Comment>(environment.apiUrl + '/news/comment', {
      text,
    });
  }
}
