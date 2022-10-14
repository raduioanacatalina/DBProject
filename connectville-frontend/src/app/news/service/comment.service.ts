import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { News } from '../model/news.model';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  constructor(private http: HttpClient) {}

  getAllCommentsById(): Observable<News> {
    return this.http.get<News>(environment.apiUrl + '/news/{id}/comments');
  }

  createComment(text: string): Observable<News> {
    return this.http.post<News>(environment.apiUrl + '/news/comment', { text });
  }
}
