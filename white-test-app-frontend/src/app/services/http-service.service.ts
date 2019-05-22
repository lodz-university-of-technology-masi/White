import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SessionService} from './session.service';

@Injectable()
export class HttpService {

  headers = null;

  constructor(private http: HttpClient,
              private sessionService: SessionService) {
  }

  setAuthHeader() {
    this.headers = new HttpHeaders({'Authorization': 'Bearer ' + this.sessionService.getUser().accessToken});
  }

  get<T>(endpoint: string, httpOptions = {}): Observable<T> {
    this.setAuthHeader();
    return this.http.get<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    });
  }

  post<T>(endpoint: string, body, httpOptions = {}): Observable<T> {
    this.setAuthHeader();
    return this.http.post<T>(endpoint, body, {
      headers: this.headers,
      ...httpOptions
    });
  }

  put<T>(endpoint: string, body, httpOptions = {}): Observable<T> {
    this.setAuthHeader();
    return this.http.put<T>(endpoint, body, {
      headers: this.headers,
      ...httpOptions
    });
  }

  putWithoutBody<T>(endpoint: string, httpOptions = {}): Observable<T> {
    this.setAuthHeader();
    return this.http.put<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    });
  }

  delete<T>(endpoint: string, httpOptions = {}) {
    this.setAuthHeader();
    return this.http.delete<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    });
  }
}
