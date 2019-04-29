import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {HttpErrorHandlerService} from './http-error-handler.service';

@Injectable()
export class HttpService {

  headers = new HttpHeaders({'token': ''});

  constructor(private http: HttpClient,
              private httpErrorHandler: HttpErrorHandlerService) {
  }

  get<T>(endpoint: string, httpOptions = {}): Observable<T> {
    return this.http.get<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    });
  }

  post<T>(endpoint: string, body, httpOptions = {}): Observable<T> {
    return this.http.post<T>(endpoint, body, {
      headers: this.headers,
      ...httpOptions
    });
  }

  put<T>(endpoint: string, body, httpOptions = {}): Observable<T> {
    return this.http.put<T>(endpoint, body, {
      headers: this.headers,
      ...httpOptions
    });
  }

  putWithoutBody<T>(endpoint: string, httpOptions = {}): Observable<T> {
    return this.http.put<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    });
  }

  delete<T>(endpoint: string, httpOptions = {}) {
    return this.http.delete<T>(endpoint, {
      headers: this.headers,
      ...httpOptions
    });
  }
}
