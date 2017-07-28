/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.boleka.reactor.domain;

import java.util.Date;

/**
 *
 * @author kelvinashu
 */
public class Loan {

    private Integer id;
    private String borrower_avatar;
    private Double loan_amount;
    private Double balance;
    private Integer time_left;
    private String loan_type;
    private String description;
    private Double loan_interest;
    private String risk_level;
    private String risk_lable;
    private String risk_progress;
    private String risk_alert;
    private Integer payment_period;
    private Integer loan_progress;
    private Date created_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBorrower_avatar() {
        return borrower_avatar;
    }

    public void setBorrower_avatar(String borrower_avatar) {
        this.borrower_avatar = borrower_avatar;
    }

    public Double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(Double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getTime_left() {
        return time_left;
    }

    public void setTime_left(Integer time_left) {
        this.time_left = time_left;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLoan_interest() {
        return loan_interest;
    }

    public void setLoan_interest(Double loan_interest) {
        this.loan_interest = loan_interest;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public String getRisk_lable() {
        return risk_lable;
    }

    public void setRisk_lable(String risk_lable) {
        this.risk_lable = risk_lable;
    }

    public String getRisk_progress() {
        return risk_progress;
    }

    public void setRisk_progress(String risk_progress) {
        this.risk_progress = risk_progress;
    }

    public String getRisk_alert() {
        return risk_alert;
    }

    public void setRisk_alert(String risk_alert) {
        this.risk_alert = risk_alert;
    }

    public Integer getPayment_period() {
        return payment_period;
    }

    public void setPayment_period(Integer payment_period) {
        this.payment_period = payment_period;
    }

    public Integer getLoan_progress() {
        return loan_progress;
    }

    public void setLoan_progress(Integer loan_progress) {
        this.loan_progress = loan_progress;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Loan{" + "id=" + id + ", borrower_avatar=" + borrower_avatar + ", loan_amount=" + loan_amount + ", balance=" + balance + ", time_left=" + time_left + ", loan_type=" + loan_type + ", description=" + description + ", loan_interest=" + loan_interest + ", risk_level=" + risk_level + ", risk_lable=" + risk_lable + ", risk_progress=" + risk_progress + ", risk_alert=" + risk_alert + ", payment_period=" + payment_period + ", loan_progress=" + loan_progress + ", created_date=" + created_date + '}';
    }
    
    
    
}
