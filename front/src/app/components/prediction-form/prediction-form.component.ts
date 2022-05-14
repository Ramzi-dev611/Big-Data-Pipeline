import { Component, DoCheck, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Credit } from 'src/app/Entities/Credit.entity';

@Component({
  selector: 'app-prediction-form',
  templateUrl: './prediction-form.component.html',
  styleUrls: ['./prediction-form.component.css']
})
export class PredictionFormComponent implements OnInit {
  object = new Credit();

  constructor() { }

  ngOnInit(): void {
  }

  submit(){
    console.log(this.object)
  }
}
