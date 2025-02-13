import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Auth } from '../model/auth.model';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class AuthRepository {
    private authPath = `${environment.apiUrl}/auth`;

    constructor(private http: HttpClient) { }

    authenticate(authData: Auth): Observable<any> {
        return this.http.post<any>(`${this.authPath}/login`, authData);
    }
}