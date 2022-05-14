import { Injectable } from '@angular/core';
import { Credit } from '../Entities/Credit.entity';
import { CreditsFormatterService } from './credits-formatter.service';

@Injectable({
  providedIn: 'root'
})
export class CreditsService {
  tuplet = new Credit();
  credits = [this.tuplet, this.tuplet];

  constructor(private formattingService: CreditsFormatterService) { }

  // Get All predictions
  getAllPredictions(){
    const new_credits = []
    for (var element of this.credits) {
      new_credits.push(this.formattingService.getFormattedCredit(element))
    }
    return new_credits;
  } 
}
