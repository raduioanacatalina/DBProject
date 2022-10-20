import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, Subject, tap } from 'rxjs';
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
    // return of({
    //   id: 1,
    //   image: undefined,
    //   publisher: 'Alex',
    //   text: 'dada',
    //   publishDate: new Date(),
    //   isPinned: true,
    //   likes: 20,
    //   comments: 20,
    //   topics: ['nunu', 'lalaa'],
    // });
  }

  getAllNews(): Observable<News[]> {
    // console.log(
    //   this.http
    //     .get<News[]>(environment.apiUrl + '/news')
    //     .subscribe((news: News[]) => {
    //       console.log(news);
    //     })
    // );
    return this.http.get<News[]>(environment.apiUrl + '/news');

    // return of([
    //   {
    //     id: 1,
    //     image: undefined,
    //     publisher: 'Alex',
    //     text: 'It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using Content here, content here, making it look like readable English. Many deskt',
    //     publishDate: new Date(),
    //     isPinned: false,
    //     likes: 20,
    //     comments: 20,
    //     topics: ['nunu', 'lalaa'],
    //   },
    //   {
    //     id: 2,
    //     image: undefined,
    //     publisher: 'dan',
    //     text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."',
    //     publishDate: new Date(),
    //     isPinned: true,
    //     likes: 30,
    //     comments: 30,
    //     topics: ['nunu', 'lalaa'],
    //   },
    // ]);
  }

  deleteNews(newsId: number): Observable<any> {
    return this.http.delete(environment.apiUrl + '/news/' + newsId);
    // this.emitDelete(id);
  }

  putSelectPin(newsId: number): Observable<News> {
    return this.http.put<News>(
      environment.apiUrl + '/news/' + newsId + '/pin',
      newsId
    );
  }

  putLikePost(newsId: number): Observable<News> {
    return this.http.put<News>(
      environment.apiUrl + '/news/' + newsId + '/like',
      newsId
    );
  }
  
}
