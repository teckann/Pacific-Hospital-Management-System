/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

/**
 *
 * @author Yang Ee
 */
public class Consultance {
    private String MRID, patientID, region, gender, age, disease, description, doctorID, reportDate, consultanceFee, paymentStatus, bloodTest;
    
    public Consultance() {
        
    }
    

    public Consultance(String MRID, String patientID, String region, String gender, String age, String disease, String description, String doctorID, String reportDate, String consultanceFee, String paymentStatus, String bloodTest) {
        this.MRID = MRID;
        this.patientID = patientID;
        this.region = region;
        this.gender = gender;
        this.age = age;
        this.disease = disease;
        this.description = description;
        this.doctorID = doctorID;
        this.reportDate = reportDate;
        this.consultanceFee = consultanceFee;
        this.paymentStatus = paymentStatus;
        this.bloodTest = bloodTest;
    }
    
    
    

    public void setMRID(String MRID) {
        this.MRID = MRID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public void setComsultanceFee(String comsultanceFee) {
        this.consultanceFee = comsultanceFee;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setBloodtest(String bloodtest) {
        this.bloodTest = bloodtest;
    }

    public String getMRID() {
        return MRID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getRegion() {
        return region;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    public String getDescription() {
        return description;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getComsultanceFee() {
        return consultanceFee;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getBloodtest() {
        return bloodTest;
    }
    
    public void saveMedicalReport() {
        String newRecord = String.join(";", MRID, patientID, region, gender, age, disease, description, doctorID, reportDate, consultanceFee, paymentStatus, bloodTest, "1");
        File_Control.addData("medicalReport.txt",newRecord);
    }
    
    
}
