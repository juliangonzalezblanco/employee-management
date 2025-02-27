import { Component } from '@angular/core';
import { EmployeeService } from '../../application/employee.service';
import { EmployeeApiGeneralResponse } from '../../model/employee-api-general-response.dto';
import { EmployeeDTO } from '../../model/employee.dto';

@Component({
  selector: 'app-employee-page',
  templateUrl: './employee-page.component.html',
  styleUrls: ['./employee-page.component.css']
})
export class EmployeePageComponent {
  searchQuery: string = '';
  error: string = '';
  employees: EmployeeDTO[] = [];

  constructor(private employeeService: EmployeeService) { }

  search() {
    if (!this.searchQuery) {
      this.employeeService.getAllEmployees().subscribe((resp: EmployeeApiGeneralResponse<EmployeeDTO[]>) => {
        this.employees = resp && resp.data ? resp.data : [];
      }, (error) => {
        this.employees = [];
        this.error = 'Error obtaining employees.';
      });
    } else {
      this.employeeService.getEmployeeById(Number(this.searchQuery)).subscribe((resp: EmployeeApiGeneralResponse<EmployeeDTO>) => {
        this.employees = resp && resp.data ? [resp.data] : [];
      }, (error) => {
        this.employees = [];
        this.error = 'Error obtaining employees.';
      });
    }
  }
}
