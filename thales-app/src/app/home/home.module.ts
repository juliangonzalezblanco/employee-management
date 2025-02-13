import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './page/home-page/home-page.component';
import { HomeRoutingModule } from './home-routing.module';
import { MatCardModule } from '@angular/material/card';


@NgModule({
  declarations: [
    HomePageComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    MatCardModule
  ],
})
export class HomeModule { }
