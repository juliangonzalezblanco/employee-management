import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EmployeePageComponent } from './employee-page.component';
import { EmployeeService } from '../../application/employee.service';
import { of, throwError } from 'rxjs';
import { EmployeeApiGeneralResponse } from '../../model/employee-api-general-response.dto';
import { EmployeeDTO } from '../../model/employee.dto';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatCardModule } from '@angular/material/card';

describe('EmployeePageComponent', () => {
  let component: EmployeePageComponent;
  let fixture: ComponentFixture<EmployeePageComponent>;
  let employeeService: jasmine.SpyObj<EmployeeService>;

  beforeEach(() => {
    const employeeServiceSpy = jasmine.createSpyObj('EmployeeService', ['getAllEmployees', 'getEmployeeById']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, MatCardModule],
      declarations: [EmployeePageComponent],
      providers: [
        { provide: EmployeeService, useValue: employeeServiceSpy }
      ]
    });

    fixture = TestBed.createComponent(EmployeePageComponent);
    component = fixture.componentInstance;
    employeeService = TestBed.inject(EmployeeService) as jasmine.SpyObj<EmployeeService>;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  describe('search', () => {
    it('should call getAllEmployees when searchQuery is empty', () => {
      const mockResponse: EmployeeApiGeneralResponse<EmployeeDTO[]> = {
        data: [{ id: 1, employee_name: 'John Doe', employee_age: 30 }],
        message: 'Success'
      };
      employeeService.getAllEmployees.and.returnValue(of(mockResponse));

      component.search();

      expect(employeeService.getAllEmployees).toHaveBeenCalled();
      expect(component.employees.length).toBe(1);
      expect(component.employees[0].employee_name).toBe('John Doe');
      expect(component.error).toBe('');
    });

    it('should call getEmployeeById when searchQuery is not empty', () => {
      const mockResponse: EmployeeApiGeneralResponse<EmployeeDTO> = {
        data: { id: 1, employee_name: 'John Doe', employee_age: 30 },
        message: 'Success'
      };
      employeeService.getEmployeeById.and.returnValue(of(mockResponse));
      component.searchQuery = '1';

      component.search();

      expect(employeeService.getEmployeeById).toHaveBeenCalledWith(1);
      expect(component.employees.length).toBe(1);
      expect(component.employees[0].employee_name).toBe('John Doe');
      expect(component.error).toBe('');
    });

    it('should handle error when getAllEmployees fails', () => {
      employeeService.getAllEmployees.and.returnValue(throwError(() => new Error('Error obtaining employees.')));

      component.search();

      expect(component.employees.length).toBe(0);
      expect(component.error).toBe('Error obtaining employees.');
    });

    it('should handle error when getEmployeeById fails', () => {
      employeeService.getEmployeeById.and.returnValue(throwError(() => new Error('Error obtaining employee.')));
      component.searchQuery = '1';

      component.search();

      expect(component.employees.length).toBe(0);
      expect(component.error).toBe('Error obtaining employees.');
    });
  });
});
