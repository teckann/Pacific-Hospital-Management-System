/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

/**
 *
 * @author user
 */
public class Prescription {
    private String PRID, MRID, medicines, prescriptionDate, dispenserName, dispenceDate, price, paymentStatus;

    public Prescription(String PRID, String MRID, String medicines, String prescriptionDate, String dispenserName, String dispenceDate, String price, String paymentStatus) {
        this.PRID = PRID;
        this.MRID = MRID;
        this.medicines = medicines;
        this.prescriptionDate = prescriptionDate;
        this.dispenserName = dispenserName;
        this.dispenceDate = dispenceDate;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }

    public Prescription() {
        
    }
    
    public void setPRID(String PRID) {
        this.PRID = PRID;
    }

    public void setMRID(String MRID) {
        this.MRID = MRID;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public void setDispenserName(String dispenserName) {
        this.dispenserName = dispenserName;
    }

    public void setDispenceDate(String dispenceDate) {
        this.dispenceDate = dispenceDate;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    public String getPRID() {
        return PRID;
    }

    public String getMRID() {
        return MRID;
    }

    public String getMedicines() {
        return medicines;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public String getDispenserName() {
        return dispenserName;
    }

    public String getDispenceDate() {
        return dispenceDate;
    }

    public String getPrice() {
        return price;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void addPrescription() {
        String newRecord = String.join(";", PRID, MRID, medicines, prescriptionDate, dispenserName, dispenceDate, price, paymentStatus, "1");
        File_Control.addData("prescriptionData.txt", newRecord);
    }
    
}
