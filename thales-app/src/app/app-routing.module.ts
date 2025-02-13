import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeePageComponent } from './employee/page/employee-page/employee-page.component';

const routes: Routes = [
  {path:'', redirectTo:'employees', pathMatch: 'full'},
  {path:'employees', component: EmployeePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
