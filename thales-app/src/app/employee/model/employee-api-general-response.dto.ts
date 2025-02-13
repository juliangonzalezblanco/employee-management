export interface EmployeeApiGeneralResponse<T> {
    status?: string;
    data?: T;
    message?: string;
}  