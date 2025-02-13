import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeePageComponent } from './page/employee-page/employee-page.component';
import { EmployeeRoutingModule } from './employee-routing.module';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeRepository } from './repository/employee.repository';
import { EmployeeService } from './application/employee.service';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    EmployeePageComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    EmployeeRoutingModule,
    MatCardModule,
    SharedModule
  ],
  providers: [EmployeeRepository, EmployeeService]
})
export class EmployeeModule { }