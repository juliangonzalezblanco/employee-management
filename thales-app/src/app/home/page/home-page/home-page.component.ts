import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/application/auth.service';
import { Auth } from '../../auth/model/auth.model';
import { SessionStorageService } from 'src/app/shared/service/session-storage.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit{

  constructor(private router: Router, private authService: AuthService, private sessionStorageService: SessionStorageService) { }

  ngOnInit(): void {
    let authData: Auth = {
      username: 'admin',
      password: 'admin123'
    }
    this.authService.authenticate(authData).subscribe((resp)=>{
      if(resp && resp.token)this.sessionStorageService.save('token',resp.token);
    });
  }

  goToEmployees() {
    this.router.navigate(['/employees']);
  }
}
