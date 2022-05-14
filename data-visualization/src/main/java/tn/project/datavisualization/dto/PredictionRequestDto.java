package tn.project.datavisualization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionRequestDto {
    protected String accountBalance;
    protected String durationOfCredit;
    protected String paymentStatus;
    protected String purpose;
    protected int creditAmount;
    protected String valueSavings;
    protected String currentEmploymentLength;
    protected int istalment;
    protected String sex;
    protected String guarantors;
    protected int durationCurrentAddress;
    protected String proprety;
    protected int age;
    protected String concurrentCredits;
    protected String housing;
    protected int numberOfExistingCredits;
    protected String occupation;
    protected int dependentNumber;
    protected String tel;
    protected String foreignWorker;
}