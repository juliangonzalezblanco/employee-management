import { Injectable } from '@angular/core';
import { AuthRepository } from '../repository/auth.repository';
import { Auth } from '../model/auth.model';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    constructor(private authRepository: AuthRepository) { }

    authenticate(authData: Auth) {
        return this.authRepository.authenticate(authData);
    }
}
