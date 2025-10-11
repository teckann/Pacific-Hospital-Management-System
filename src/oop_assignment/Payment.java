/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author Jimmy
 */
public class Payment {
    
    private String receiptID,idPaying,patientID,date,amount,method;
    private ArrayList<String> paymentToMake = new ArrayList<>();
    LocalDate todayDate = LocalDate.now();
    String todayDateString = todayDate.toString();
    private String paymentFile = "paymentData.txt";
    
    public Payment(){}

    public Payment(String receiptID, String idPaying, String patientID, String date, String amount, String method) {
        this.receiptID = receiptID;
        this.idPaying = idPaying;
        this.patientID = patientID;
        this.date = date;
        this.amount = amount;
        this.method = method;
    }
    
    public Payment(String receiptID, String idPaying, String patientID, String date, String amount) {
        this.receiptID = receiptID;
        this.idPaying = idPaying;
        this.patientID = patientID;
        this.date = date;
        this.amount = amount;
    }
    
    
    public Payment(ArrayList<String> paymentToMake){
        this.paymentToMake = paymentToMake;
    }

    public ArrayList<String> getPaymentToMake() {
        return paymentToMake;
    }

    public void setPaymentToMake(ArrayList<String> paymentToMake) {
        this.paymentToMake = paymentToMake;
    }
    
    
    public String totalUp(){
        String[] oneReceipt = new String[2];
        double total = 0;
        double toAdd;
        
        for(String paymentList: paymentToMake){
            oneReceipt = paymentList.split(": ");
            toAdd =  Double.parseDouble(oneReceipt[1].replace("RM",""));
            total += toAdd;
        }
        
        
        
        return String.format("%.2f", total);
    }
    
    public void generateReceipt(){
        String[] oneReceipt = new String[2];
        String receipt = "";
        int i = 0;
        
        for(String paymentList: paymentToMake){
            oneReceipt = paymentList.split(": ");
            receipt = String.join(";", Utility_Methods.autoGenerateID(paymentFile, "RC"),
                                  oneReceipt[0], patientID,todayDateString,
                                  oneReceipt[1],method,"1");
            File_Control.addData(paymentFile,receipt);
        }
        
        
    }
    
    public ArrayList<String> getPayed(String userID){
        ArrayList<String[]> paymentList = File_Control.readFile(paymentFile,true);
        ArrayList<String> paymentMade = new ArrayList<>();
        
        for(String[] payment:paymentList){
            paymentMade.add(payment[0] + " " + payment[1]);
        }
        
        return paymentMade;
    }
    
    public void changeStatus(){
        String[] oneReceipt = new String[2];
        String firstletter = "";
        
        for(String paymentList: paymentToMake){
            oneReceipt = paymentList.split(": ");
            for (int i = 0;i < 2;i++){
                firstletter += oneReceipt[0].charAt(i);
            }
            
            switch(firstletter.strip()){
                case "MR" -> Patient.modifyOneValue("medicalReport.txt", oneReceipt[0], "0", 10);
                case "PR" -> Patient.modifyOneValue("prescriptionData.txt", oneReceipt[0], "0", 7);
                case "VC" -> Patient.modifyOneValue("vaccinationData.txt", oneReceipt[0], "0", 7);
            }
            firstletter = "";
        }
    }
    
    public ArrayList<String> getToPay(String userID){
        ArrayList<String[]> medicalList = File_Control.readFile("medicalReport.txt",true);
        ArrayList<String[]> prescriptionList = File_Control.readFile("prescriptionData.txt",true);
        ArrayList<String[]> vaccinationList = File_Control.readFile("vaccinationData.txt",true);
        ArrayList<String[]> paymentList = File_Control.readFile(paymentFile,true);
        ArrayList<String> paymentCanMake = new ArrayList<>();
        
        
        for (String[] med: medicalList){
            if(userID.equals(med[1])){
                paymentCanMake.add(med[0] + ": " + med[9]);
                for (String[] pre:prescriptionList){
                    if(med[0].equals(pre[1])){
                        paymentCanMake.add(pre[0] + ": " + pre[6]);
                    }
                }
            }
        }
        
        for (String[] vacc: vaccinationList){
            if(userID.equals(vacc[1])){
                paymentCanMake.add(vacc[0] + ": " + vacc[4]);
            }
        }
        
        for(String[] payment:paymentList){
            paymentCanMake.remove(payment[1] + ": " + payment[4]);
        }
        
        
        
        return paymentCanMake;
        
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getIdPaying() {
        return idPaying;
    }

    public void setIdPaying(String idPaying) {
        this.idPaying = idPaying;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    
}
