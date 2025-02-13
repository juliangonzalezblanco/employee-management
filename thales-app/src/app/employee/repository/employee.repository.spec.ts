import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'src/environments/environment';
import { EmployeeApiGeneralResponse } from '../model/employee-api-general-response.dto';
import { EmployeeDTO } from '../model/employee.dto';
import { EmployeeRepository } from './employee.repository';

describe('EmployeeRepository', () => {
  let service: EmployeeRepository;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EmployeeRepository]
    });
    service = TestBed.inject(EmployeeRepository);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getAllEmployees', () => {
    it('should fetch all employees successfully', () => {
      const mockResponse: EmployeeApiGeneralResponse<EmployeeDTO[]> = {
        data: [
          { id: 1, employee_name: 'Caesar Vance', employee_age: 21 },
          { id: 2, employee_name: 'Doris Wilder', employee_age: 22 }
        ],
        message: 'Success'
      };

      service.getAllEmployees().subscribe((response:any) => {
        expect(response.data.length).toBe(2);
        expect(response.data[0].employee_name).toBe('Caesar Vance');
        expect(response.data[1].employee_name).toBe('Doris Wilder');
      });

      const req = httpMock.expectOne(`${environment.apiUrl}/employees`);
      expect(req.request.method).toBe('GET');
      req.flush(mockResponse);
    });

    it('should handle error response', () => {
      const mockError = { status: 500, statusText: 'Server Error' };

      service.getAllEmployees().subscribe({
        next: () => fail('should have failed with the server error'),
        error: (error:any) => {
          expect(error.status).toBe(500);
          expect(error.statusText).toBe('Server Error');
        }
      });

      const req = httpMock.expectOne(`${environment.apiUrl}/employees`);
      req.flush('Error', mockError);
    });
  });

  describe('getEmployeeById', () => {
    it('should fetch employee by id', () => {
      const mockResponse: EmployeeApiGeneralResponse<EmployeeDTO> = {
        data: { id: 1, employee_name: 'Caesar Vance', employee_age: 21 },
        message: 'Success'
      };

      service.getEmployeeById(1).subscribe((response:any) => {
        expect(response.data.id).toBe(1);
        expect(response.data.employee_name).toBe('Caesar Vance');
      });

      const req = httpMock.expectOne(`${environment.apiUrl}/employees/1`);
      expect(req.request.method).toBe('GET');
      req.flush(mockResponse);
    });

    it('should handle error response for getEmployeeById', () => {
      const mockError = { status: 404, statusText: 'Not Found' };

      service.getEmployeeById(999).subscribe({
        next: () => fail('should have failed with the error'),
        error: (error:any) => {
          expect(error.status).toBe(404);
          expect(error.statusText).toBe('Not Found');
        }
      });

      const req = httpMock.expectOne(`${environment.apiUrl}/employees/999`);
      req.flush('Error', mockError);
    });
  });
});
