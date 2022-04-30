package com.clinic.entity;

public class PrescriptionDetail {

    private String medicineCode;
    private String dosage;
    private Integer numericOrder;

    public PrescriptionDetail() {}

    public PrescriptionDetail(String medicineCode, String dosage, Integer numericOrder) {
        this.medicineCode = medicineCode;
        this.dosage = dosage;
        this.numericOrder = numericOrder;
    }
    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Integer getNumericOrder() {
        return numericOrder;
    }

    public void setNumericOrder(Integer numericOrder) {
        this.numericOrder = numericOrder;
    }
}