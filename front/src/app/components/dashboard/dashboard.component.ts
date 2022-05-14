import { Component, OnInit } from '@angular/core';
import { CreditsService } from 'src/app/services/credits.service';
import { Credit } from '../../Entities/Credit.entity';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  predictions: Credit[] = [];

  constructor(private creditServices: CreditsService) { 
  }

  ngOnInit(): void {
    this.predictions = this.creditServices.getAllPredictions();
  }

}
