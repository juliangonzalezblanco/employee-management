import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { EmployeeApiGeneralResponse } from '../model/employee-api-general-response.dto';
import { EmployeeDTO } from '../model/employee.dto';

@Injectable({
    providedIn: 'root',
})
export class EmployeeRepository {
    private employeePath = `${environment.apiUrl}/employees`;

    constructor(private http: HttpClient) { }

    getAllEmployees(): Observable<any> {
        return this.http.get<EmployeeApiGeneralResponse<EmployeeDTO[]>>(`${this.employeePath}`);
    }

    getEmployeeById(id:number): Observable<any> {
        return this.http.get<EmployeeApiGeneralResponse<EmployeeDTO>>(`${this.employeePath}/${id}`);
    }
}