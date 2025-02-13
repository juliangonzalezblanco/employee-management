import { TestBed } from '@angular/core/testing';
import { EmployeeService } from './employee.service';
import { EmployeeRepository } from '../repository/employee.repository';
import { of } from 'rxjs';

describe('EmployeeService', () => {
  let service: EmployeeService;
  let employeeRepositorySpy: jasmine.SpyObj<EmployeeRepository>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('EmployeeRepository', ['getAllEmployees', 'getEmployeeById']);

    TestBed.configureTestingModule({
      providers: [
        EmployeeService,
        { provide: EmployeeRepository, useValue: spy }
      ]
    });

    service = TestBed.inject(EmployeeService);
    employeeRepositorySpy = TestBed.inject(EmployeeRepository) as jasmine.SpyObj<EmployeeRepository>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call getAllEmployees from repository', () => {
    const mockEmployees = [{ id: 1, name: 'John Doe' }, { id: 2, name: 'Jane Doe' }];
    employeeRepositorySpy.getAllEmployees.and.returnValue(of(mockEmployees));

    service.getAllEmployees().subscribe((employees) => {
      expect(employees).toEqual(mockEmployees);
    });

    expect(employeeRepositorySpy.getAllEmployees).toHaveBeenCalled();
  });

  it('should call getEmployeeById from repository with correct ID', () => {
    const mockEmployee = { id: 1, name: 'John Doe' };
    employeeRepositorySpy.getEmployeeById.and.returnValue(of(mockEmployee));

    service.getEmployeeById(1).subscribe((employee) => {
      expect(employee).toEqual(mockEmployee);
    });

    expect(employeeRepositorySpy.getEmployeeById).toHaveBeenCalledWith(1);
  });
});