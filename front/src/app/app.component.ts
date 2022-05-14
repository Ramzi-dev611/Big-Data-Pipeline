import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  page = 0;

  clickPredict(){
    this.page=0
  }

  clickDashboard(){
    this.page=1
  }
}
