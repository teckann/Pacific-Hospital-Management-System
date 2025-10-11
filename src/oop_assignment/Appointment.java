/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Jimmy
 */
public class Appointment {
    private String appointmentID, patientID, doctorID, appointmentDate, appointmentDay, appointmentTime, status;
    
    final private static String[] allMonths = {"JANUARY","FEBRUARY",
                                        "MARCH","APRIL","MAY","JUNE","JULY",
                                        "AUGUST","SEPTEMBER","OCTOBER",
                                        "NOVEMBER","DECEMBER"};

    
    public Appointment(String appointmentID, String patientID, String doctorID,
                       String appointmentDate, String appointmentDay, String appointmentTime,
                       String status) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }
    
    public Appointment(){
        
    }

    public static String[] getAllMonths() {
        return allMonths;
    }

    
    
    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public static ArrayList<String> getTimeItem (String PatientID,String DoctorID,String Date){
        ArrayList<String[]> list = File_Control.readFile("appointmentData.txt",false);
        ArrayList<String[]> acceptedAppointment = new ArrayList<>();
        ArrayList<String> selectedTime = new ArrayList<>();
        ArrayList<String> availableTime = new ArrayList<>();
        
        for (String[] UserList : list){
            if(UserList[6].equals("Accepted") || UserList[6].equals("Pending")){
                acceptedAppointment.add(UserList);
            }
        }

        for(int i = 8;i < 18;i++){
            int timeNumber = (i < 13) ? i:i-12;
            availableTime.add(timeNumber+":00");
        }
        
        
        
        for (String[] RelatedApp: acceptedAppointment){
            
            boolean isPorD = PatientID.equals(RelatedApp[1]) || DoctorID.equals(RelatedApp[2]);
            
            if(isPorD && Date.equals(RelatedApp[3]))
            {
                selectedTime.add(RelatedApp[5]);

            }
        }
        
        availableTime.removeAll(selectedTime);
        
        return availableTime;
        
    }
    
    
    
    public String makeitStringApp(){
        String toWrite = String.join(";", appointmentID, patientID, doctorID,
                                     appointmentDate, appointmentDay, appointmentTime,
                                     status);
        return toWrite;
    }
    
    public static ArrayList<String> getAllAppointment(String PatientID,String toGet,boolean filter){
        ArrayList<String[]> appointmentList = File_Control.readFile("appointmentData.txt",false);
        ArrayList<String[]> childList = File_Control.readFile("childData.txt",false);
        ArrayList<String> currentAppointment = new ArrayList<>();
        

        if (!toGet.equals("child")){
            for (String[] AppList : appointmentList){
                if(AppList[1].equals(PatientID)){
                    if(filter){
                        if(AppList[6].equals("Rejected")){
                            continue;
                        }
                    }
                    if(AppList[6].equals("Deleted")){
                            continue;
                        }
                    currentAppointment.add(AppList[0] + " " + AppList[3]);
                }
            }
        }
        if(!toGet.equals("adult")){
            for (String[] UserChildList : childList){
                if(UserChildList[1].equals(PatientID)){
                    for (String[] AppList : appointmentList){
                        if(AppList[1].equals(UserChildList[0])){
                            if(filter){
                                if(AppList[6].equals("Rejected")){
                                    continue;
                                }
                            }
                            if(AppList[6].equals("Deleted")){
                            continue;
                            }
                        currentAppointment.add(AppList[0] + " " + AppList[3]);
                        }
                    }
                }
            }
        }
        
        
        return currentAppointment;
    }
                               
    public static int getMonthinInt(String Month){
        return switch(Month.strip()){
            case "JANUARY" -> 1;
            case "FEBRUARY" -> 2;
            case "MARCH" -> 3;
            case "APRIL" -> 4;
            case "MAY" -> 5;
            case "JUNE" -> 6;
            case "JULY" -> 7;
            case "AUGUST" -> 8;
            case "SEPTEMBER" -> 9;
            case "OCTOBER" -> 10;
            case "NOVEMBER" -> 11;
            case "DECEMBER" -> 12;
            default -> 1;
        };
    }
    
    public ArrayList<String[]> initilizePendingAppointment() {
        ArrayList<String[]> appointmentList = new ArrayList<>();
        // read data from appointment data text file
        ArrayList<String[]> appointmentData = File_Control.readFile("appointmentData.txt",true);

        // will be used for converting string format into LocalDate format
        DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // clear the  list
        appointmentList.clear();
        
        // get current date
        LocalDate date = LocalDate.now();
        // used to store text file data
        LocalDate appointmentDate1 = null;
        
        for (String[] individual : appointmentData) {
             appointmentDate1 = LocalDate.parse(individual[3], DateFormatter);
             // only add upcoming date with pending
            if (individual[6].equals("Pending") && this.doctorID.equals(individual[2]) && appointmentDate1.isAfter(date)) {
                appointmentList.add(individual);
            }
        }

        return appointmentList;
    }
    
    public void doctorModifyAppointment(String action) {
        ArrayList<String[]> appointmentData = File_Control.readFile("appointmentData.txt", false);
        
        for (String[] line : appointmentData) {
            if (line[0].equals(this.appointmentID)) {
                if (action.equals("Approve")) {
                line[6] = "Accepted";
                }
                else if (action.equals("Reject")) {
                    line[6] = "Rejected";
                }
            }
        }
        
        File_Control.writeFile("appointmentData.txt", appointmentData);
    }
    
    public ArrayList<String[]> getDoctorApprovedAppointment(ArrayList<String[]> appointmentData, ArrayList<String[]> patientData, ArrayList<String[]> childData) {
        ArrayList<String[]> tableData = new ArrayList<>();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        
        String patientName = "";
        
        //set the current dat to compare
        LocalDate date = LocalDate.now();
        
        for (String[] line : appointmentData) {
            String patientID1 = line[1];
            // only append data is the date if today or onward and is accepted
            LocalDate appointmentDate1 = LocalDate.parse(line[3], dateFormatter);
            if (line[2].equals(this.doctorID) && (appointmentDate1.isEqual(date) || appointmentDate1.isAfter(date)) 
                    && line[6].equals("Accepted")) {
                
                
                // use patientID to find patientName
                if (patientID1.startsWith("P")) {
                    for (String[] list : patientData) {
                        if (patientID1.equals(list[0])) {
                            patientName = list[1];
                        }
                    }
                }
                
                else {
                    for (String[] list : childData) {
                        if (patientID1.equals(list[0])) {
                            patientName = list[2];
                        }
                    }
                }
                
                // convert the string into string[] because the add only applicable for string[]
               tableData.add(new String[]{line[0], patientName, line[3], line[4], line[5]});
            }
        }
        
        // start compare the added data
        tableData.sort((a, b) -> {
                // convert date format
            LocalDate dateA = LocalDate.parse(a[2], dateFormatter);
            LocalDate dateB = LocalDate.parse(b[2], dateFormatter);
            
            // start compare date
            int dateCompare = dateA.compareTo(dateB);
            
            // return number is the date is different
            if (dateCompare != 0) {
                return dateCompare;
            }
            
            // compare time if the date is same
            LocalTime timeA = LocalTime.parse(a[4], timeFormatter);
            LocalTime timeB = LocalTime.parse(b[4], timeFormatter);
            return timeA.compareTo(timeB);
            });
        
        return tableData;
    }
    
    public int getTodayAppointment(ArrayList<String[]> appointmentData, LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // initialize counter
           int counter = 0;
           // to count number of appointment of the day
           for (String[] individual : appointmentData) {
               LocalDate date1 = LocalDate.parse(individual[3], dateFormatter);
               if (individual[2].equals(this.doctorID) && date.equals(date1) && individual[6].equals("Accepted")) {
                   counter++;
               }
           }
           
           return counter;
    }
    
}
