package com.spring.reactive.loan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Document
public class LoanEntity {

    @Id
    private String loanId;
    @Size(min = 2,message = "name should have minimum 4 Character")
    private String userId;
    @NotNull(message = "Loan Type should be not null")
    private String loanType;
    @NotNull(message = "Amount should be not null")
    private Double loanAmount;
    private LocalDate loanDate;
    @NotNull(message = "Rate Of Interest should be not null")
    private Double rateOfInterest;
    @NotNull(message = "Duration In Years should be not null")
    private Double durationInYears;

    public LoanEntity(){

    }

    public LoanEntity(String userId, String loanType, Double loanAmount, LocalDate loanDate, Double rateOfInterest, Double durationInYears) {
        this.userId = userId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.rateOfInterest = rateOfInterest;
        this.durationInYears = durationInYears;
    }



    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public Double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(Double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public Double getDurationInYears() {
        return durationInYears;
    }

    public void setDurationInYears(Double durationInYears) {
        this.durationInYears = durationInYears;
    }

    @Override
    public String toString() {
        return "LoanDetails{" +
                "loanId='" + loanId + '\'' +
                ", userId='" + userId + '\'' +
                ", loanType='" + loanType + '\'' +
                ", loanAmount=" + loanAmount +
                ", loanDate=" + loanDate +
                ", rateOfInterest=" + rateOfInterest +
                ", durationInYears=" + durationInYears +
                '}';
    }
}
