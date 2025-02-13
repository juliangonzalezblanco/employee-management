import { Component } from '@angular/core';
import { LoaderService } from './shared/service/loader.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'thales-app';
  loading$ = this.loaderService.loading$;

  constructor(private loaderService: LoaderService) {}
}
