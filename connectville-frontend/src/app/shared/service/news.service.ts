import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { News } from 'src/app/news/model/news.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class NewsService {
  newsList: News[] | undefined;
  private _snackBar: any;
  router: any;

  constructor(private http: HttpClient) {}

  createNews(
    text: string,
    topics: string[],
    cop: string,
    image: string
  ): Observable<News> {
    return this.http.post<News>(environment.apiUrl + '/news/new', {
      text,
      topics,
      image,
      cop,
    });
  }

  getAllNews(): Observable<News[]> {
    return this.http.get<News[]>(environment.apiUrl + '/news');
  }

  getAllNewsByCop(cop: string): Observable<News[]> {
    return this.http.get<News[]>(environment.apiUrl + '/news' + '/CoP/' + cop);
  }

  deleteNews(newsId: number): Observable<any> {
    return this.http.delete(environment.apiUrl + '/news/' + newsId);
  }

  putSelectPin(newsId: number): Observable<News> {
    return this.http.put<News>(
      environment.apiUrl + '/news/' + newsId + '/pin',
      newsId
    );
  }
}
