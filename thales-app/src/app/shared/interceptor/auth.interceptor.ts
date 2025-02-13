import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoaderService } from '../service/loader.service';
import { SessionStorageService } from '../service/session-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private loaderService: LoaderService, private sessionStorageService: SessionStorageService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.loaderService.show();

    let headers = req.headers;
      
    if (!req.url.includes('/auth/login')) {
      const token = this.sessionStorageService.get('token');
      if (token) {
        headers = headers.append('Authorization', `Bearer ${token}`);
      }
    }

    const clonedRequest = req.clone({ headers });

    return next.handle(clonedRequest).pipe(
      tap(
        event => {},
        error => {
          this.loaderService.hide();
        },
        () => {
          this.loaderService.hide();
        }
      )
    );
  }
}