import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, of, throwError } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Role, User } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  roleAs!: string;
  private _loggedInUser$ = new BehaviorSubject<User | null>(null);
  loggedInUser$ = this._loggedInUser$.asObservable();

  constructor(private http: HttpClient) {
    const token = localStorage.getItem('auth-token');
    if (token != null) {
      this._loggedInUser$.next(this.parseJwt(token));
    }
  }

  login(username: string, password: string) {
    return this.http
      .post(environment.apiUrl + '/users/login', { username, password })
      .pipe(
        tap((response: any) => {
          console.log(response.jwt);
          this._loggedInUser$.next(this.parseJwt(response.jwt));
          localStorage.setItem('auth-token', response.jwt);
        })
      );
  }
  // login(username: string, password: string) {
  //   return of({
  //     token:
  //       'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJsYXN0TmFtZSI6IkRvZSIsImZpcnN0TmFtZSI6IkpvaG4iLCJ1c2VybmFtZSI6ImpvaG5kb2UiLCJlbWFpbCI6ImpvaG5AZG9lLmNvbSIsInJvbGUiOiJ1c2VyIn0.VgWLhFkDCLcQ79n4E6AQw3j4m2fgGqoxm3XnX0e2-18',
  //   }).pipe(
  //     tap((response: any) => {
  //       this._loggedInUser$.next({
  //         ...this.parseJwt(response.token),
  //         role: Role.admin,
  //       });
  //       localStorage.setItem('auth-token', response.token);
  //     })
  //   );
  // }

  logout() {
    localStorage.removeItem('auth-token');
    this._loggedInUser$.next(null);
  }

  getToken() {
    return localStorage.getItem('auth-token');
  }

  getRole(): Role | undefined {
    return this._loggedInUser$.getValue()?.role;
  }
  
  getName(): string | undefined {
    return this._loggedInUser$.getValue()?.lastName + ' ' + this._loggedInUser$.getValue()?.firstName + '   ';
  }
  
  getId(): number | undefined {
    return this._loggedInUser$.getValue()?.id;
  }

  parseJwt(token: string): User {
    console.log(token);
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      window
        .atob(base64)
        .split('')
        .map(function (c) {
          return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join('')
    );

    return JSON.parse(jsonPayload);
  }
}
