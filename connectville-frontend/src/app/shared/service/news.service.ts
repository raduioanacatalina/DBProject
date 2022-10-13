import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, tap } from 'rxjs';
import { News } from 'src/app/auth/model/news.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class NewsService {
  constructor(private http: HttpClient) {}

  createNews(text: string, topics: string[]): Observable<News> {
    // return this.http
    //   .post<News>(environment.apiUrl + '/news', { text, topics });
    return of({
      id: 1,
      image: undefined,
      publisher: 'Alex',
      text: 'dada',
      publishDate: new Date(),
      isPinned: true,
      likes: 20,
      comments: 20,
      topics: ['nunu', 'lalaa'],
    });
  }

  getAllNews(): Observable<News[]> {
    // return this.http.get<News[]>(environment.apiUrl + '/news');
    return of([
      {
        id: 1,
        image: undefined,
        publisher: 'Alex',
        text: 'dada',
        publishDate: new Date(),
        isPinned: false,
        likes: 20,
        comments: 20,
        topics: ['nunu', 'lalaa'],
      },

      {
        id: 2,
        image: undefined,
        publisher: 'dan',
        text: 'asdasdsa',
        publishDate: new Date(),
        isPinned: true,
        likes: 30,
        comments: 30,
        topics: ['nunu', 'lalaa'],
      },
    ]);
  }
}
