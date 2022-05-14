import { Injectable } from '@angular/core';
import { Credit } from '../Entities/Credit.entity';

@Injectable({
  providedIn: 'root'
})
export class CreditsFormatterService {

  constructor() { }

  getAccountBalance(index: any){
    if(index=="1") {return new String("... < 0 DM");}
    if(index=="2") {return new String("0 <= ... < 200 DM");}
    if(index=="3") { return new String("200 DM /salary");}
    if(index=="4") {return new String("no checking account");}
    else return;
  };

  getPaymentStatus(index: any){
    if(index=="0"){return("no credits taken");}
    if(index=="1"){return("credits paid");}
    if(index=="2"){return("existing credits");}
    if(index=="3"){return("delay in paying off");}
    if(index=="4"){return("other credits(at another bank)");}
    else return;
  };

  getPurpose(index: any){
    if(index=="0"){return("car (new)");}
    if(index=="1"){return("car (used)");}
    if(index=="2"){return("furniture/equipment");}
    if(index=="3"){return("radio/television");}
    if(index=="4"){return("domestic appliances");}
    if(index=="5"){return("repairs");}
    if(index=="6"){return("education");}
    if(index=="7"){return("vacation");}
    if(index=="8"){return("retraining");}
    if(index=="9"){return("business");}
    if(index=="10"){return("others");}
    else return;
  };

  getValueSavings(index: any){
    if(index=="1"){return("... < 100 DM");}
    if(index=="2"){return("100 <= ... < 500 DM");}
    if(index=="3"){return("500 <= ... < 1000 DM");}
    if(index=="4"){return(".. >= 1000 DM");}
    if(index=="5"){return("unknown/ no savings account");}
    else return;
  };

  getCurrentEmploymentLength(index: any){
    if(index=="1"){return("unemployed");}
    if(index=="2"){return("... < 1 year");}
    if(index==3){return("1 <= ... < 4 years");}
    if(index==4){return("4 <= ... < 7 yearS");}
    if(index==5){return(".. >= 7 years");}
    else return;
  };

  getSex (index: any){
    if(index==1){return("male: divorced/separated");}
    if(index==2){return("female: divorced/separated/married");}
    if(index==3){return("male: single");}
    if(index==4){return("male: married/widowed");}
    if(index==5){return("female: single");}
    else return;
  };

  getGuarantors(index: any){
    if(index==1){return("none");}
    if(index==2){return("co-applicant");}
    if(index==3){return("guarantor");}
    else return;
  };
     
  getProperty(index: any){
    if(index==1){return("real estate");}
    if(index==2){return("building society savings agreement/life insurance");}
    if(index==3){return("car or other");}
    if(index==4){return("unknown / no property");}
    else return;
  };
                    
  getConcurrentCredits(index: any){
    if(index==1){return("bank");}
    if(index==2){return("stores");}
    if(index==3){return("none");}
    else return;
  };
                    
  getApartmentType(index: any){
    if(index==1){return("rent");}
    if(index==2){return("own");}
    if(index==3){return("for free");}
    else return;
  };

  getJob(index: any){
    if(index==1){return("unemployed/unskilled - non-resident");}
    if(index==2){return("unskilled - resident");}
    if(index==3){return("skilled employee / official");}
    if(index==4){return("management/ self-employed/ highly qualified employee/ officer");}
    else return;
  };


  getTel(index: any){
    if(index==1){return("none");}
    if(index==2){return("yes, registered under the customers name");}
    else return;
  };


  getForeignWorker(index: any){
    if(index==1){return("Yes");}
    if(index==2){return("No");}
    else return;
  };

  getFormattedCredit(credit: Credit){
    const new_credit = new Credit();
    new_credit.id = credit.id;
    new_credit.accountBalance = this.getAccountBalance(credit.accountBalance) || ''
    new_credit.durationOfCredit = credit.durationOfCredit
    new_credit.paymentStatus = this.getPaymentStatus(credit.paymentStatus) || ''
    new_credit.purpose = this.getPurpose(credit.purpose) || ''
    new_credit.creditAmount = credit.creditAmount
    new_credit.valueSavings = this.getValueSavings(credit.valueSavings) || ''
    new_credit.currentEmploymentLength = this.getCurrentEmploymentLength(credit.currentEmploymentLength) || ''
    new_credit.istalment = credit.istalment
    new_credit.sex = this.getSex(credit.sex) || ''
    new_credit.guarantors = this.getGuarantors(credit.guarantors) || ''
    new_credit.durationCurrentAddress = credit.durationCurrentAddress
    new_credit.proprety = this.getProperty(credit.proprety) || ''
    new_credit.age = credit.age
    new_credit.concurrentCredits = this.getConcurrentCredits(credit.concurrentCredits) || ''
    new_credit.housing = this.getApartmentType(credit.housing) || ''
    new_credit.numberOfExistingCredits = credit.numberOfExistingCredits
    new_credit.occupation = this.getJob(credit.occupation) || ''
    new_credit.dependentNumber = credit.dependentNumber
    new_credit.tel = this.getTel(credit.tel) || ''
    new_credit.foreignWorker = this.getForeignWorker(credit.foreignWorker) || ''
    return new_credit
  }
}
