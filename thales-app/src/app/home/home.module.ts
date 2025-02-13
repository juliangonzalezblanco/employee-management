import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './page/home-page/home-page.component';
import { HomeRoutingModule } from './home-routing.module';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from './auth/application/auth.service';
import { AuthRepository } from './auth/repository/auth.repository';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    HomePageComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    MatCardModule,
    HttpClientModule
  ],
  providers: [AuthService, AuthRepository],
})
export class HomeModule { }
