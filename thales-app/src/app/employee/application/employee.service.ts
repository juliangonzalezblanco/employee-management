import { Injectable } from '@angular/core';
import { EmployeeRepository } from '../repository/employee.repository';

@Injectable({
    providedIn: 'root',
})
export class EmployeeService {

    constructor(private employeeRepository: EmployeeRepository) { }

    getAllEmployees() {
        return this.employeeRepository.getAllEmployees();
    }

    getEmployeeById(id:number) {
        return this.employeeRepository.getEmployeeById(id);
    }
}
