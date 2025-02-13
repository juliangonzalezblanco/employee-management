import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeePageComponent } from './page/employee-page/employee-page.component';
import { EmployeeRoutingModule } from './employee-routing.module';

@NgModule({
  declarations: [
    EmployeePageComponent,
  ],
  imports: [
    CommonModule,
    EmployeeRoutingModule,
  ],
})
export class EmployeeModule { }