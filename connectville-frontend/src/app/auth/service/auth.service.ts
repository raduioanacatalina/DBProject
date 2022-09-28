import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _isLoggedIn$ = new BehaviorSubject<boolean>(false);
  isLoggedIn$ = this._isLoggedIn$.asObservable();

  constructor(private http: HttpClient) {
    const token = localStorage.getItem('auth-token');
    this._isLoggedIn$.next(!!token);
  }

  login(username: string, password: string) {
    return this.http.post('login', { username, password }).pipe(
      tap((response: any) => {
        this._isLoggedIn$.next(true);
        localStorage.setItem('auth-token', response.token);
      })
    );
  }

  getToken() {
    return localStorage.getItem('auth-token');
  }
}