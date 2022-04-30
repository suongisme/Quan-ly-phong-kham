package com.clinic.entity;

import java.util.Date;

public class Prescription {

    private Integer numericOrder;
    private String patientCode;
    private String diseases;
    private Date createdDate;

    public Prescription() {}

    public Prescription(Integer numericOrder, String patientCode, String diseases, Date createdDate) {
        this.numericOrder = numericOrder;
        this.patientCode = patientCode;
        this.diseases = diseases;
        this.createdDate = createdDate;
    }

    public Integer getNumericOrder() {
        return numericOrder;
    }

    public void setNumericOrder(Integer numericOrder) {
        this.numericOrder = numericOrder;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}