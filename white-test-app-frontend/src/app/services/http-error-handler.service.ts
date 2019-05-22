import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';
import {MessageService} from './message.service';

@Injectable({
  providedIn: 'root'
})
export class HttpErrorHandlerService implements HttpInterceptor {

  private badRequest = 400;
  private notAuthorizedUserErrorCode = 401;
  private forbiddenErrorCode = 403;
  private internalErrorCode = 500;

  constructor(private router: Router,
              private messageService: MessageService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .pipe(
        retry(1),
        catchError((error: HttpErrorResponse) => {
          const errorMessage = '';
          if (error.error instanceof ErrorEvent) {
            // client-side error
            console.error('An error occurred:', error.error.message);
          } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            if (error.status === this.notAuthorizedUserErrorCode) {
              this.router.navigateByUrl('/login');
              this.messageService.error('Nieautoryzowany dostęp');
            } else if (error.status === this.forbiddenErrorCode) {
              this.messageService.error('Zabroniona akcja');
            } else if (error.status === this.badRequest) {
              this.messageService.error('Błąd');
            } else {
              this.messageService.error('Błąd serwera');
              console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error}`);
            }

          }
          // window.alert(errorMessage);
          return throwError(errorMessage);
        })
      );
  }
}
