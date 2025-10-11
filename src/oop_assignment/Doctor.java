/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

import java.util.ArrayList;

/**
 *
 * @author user1
 */
public class Doctor extends Users{
    private String specialist;
    private String workingDay;
    private String room;
    private String nationality;
    private String qualification;
    private String spokenLanguage;
    private int age;
    private int experience;
    private int accountStatus;

    public Doctor() {}
    
    public Doctor(String userID) {
        ArrayList<String[]> doctorData = File_Control.readFile("doctorData.txt", true);
        ArrayList<String[]> loginData = File_Control.readFile("loginData.txt", true);
        
        this.userID = userID;
        
        for (String[] line : doctorData) {
            if (this.userID.equals(line[0])) {
            this.userName = line[1];
            this.email = line[2];
            this.specialist = line[3];
            this.region = line[4];
            this.icNumber = line[5];
            this.workingDay = line[6];
            this.room = line[7];
            this.nationality = line[8];
            this.contactNumber = line[9];
            this.gender = line[10];
            this.age = Integer.parseInt(line[11]);
            this.qualification = line[12];
            this.spokenLanguage = line[13];
            if (line[14] != null && !line[14].trim().isEmpty() && !line[14].equals("-")) {
                this.experience = Integer.parseInt(line[14]);
            } else {
                this.experience = 0;
            }
            break;
        }
        }
        
        for (String[] line : loginData) {
            if (this.userID.equals(line[0])) {
                this.userRole = line[1];
                this.userName = line[2];
                this.userPassword = line[3];
                this.safetyQuestionID1 = line[4];
                this.safetyAnswer1 = line[5];
                this.safetyQuestionID2 = line[6];
                this.safetyAnswer2 = line[7];
                this.accountStatus = Integer.parseInt(line[8]);
                break;
            }
        }
        
    }
    
    // initialize NEW doctor data [Register Process - Admin Frame]
    public Doctor (String userID, String userName, String userPassword, String userRole, String icNumber, 
            String gender, String region, String email, String contactNumber, String specialist, String workingDay,
            String room, int age, String nationality) {
        super (userID, userName, userPassword, userRole, icNumber, gender, region, email, contactNumber);

        this.specialist = specialist;
        this.workingDay = workingDay;
        this.room = room;
        this.age = age;
        this.nationality = nationality;
    }
    
    // add NEW doctor to txtFile [Register Process - Admin Frame]
    public void writeNewDoctorData() {
        String loginDataLine = this.userID + ";" + this.userRole + ";" + this.userName + ";" + this.userPassword + ";-;-;-;-;1";
        String doctorDataLine = this.userID + ";" + this.userName + ";" + this.email + ";" + this.specialist + ";" + this.region + ";" + 
                this.icNumber + ";" + this.workingDay + ";" + this.room + ";" + this.nationality + ";" + this.contactNumber + ";" + 
                this.gender + ";" + Integer.toString(this.age) + ";-;-;-;1";

        // add to text file
        File_Control.addData("loginData.txt", loginDataLine);
        File_Control.addData("doctorData.txt", doctorDataLine);
    }
    
    public void updateProfile(String profilePage) {
        ArrayList<String[]> doctorData = File_Control.readFile("doctorData.txt",false);
        
        if (profilePage.equals("page1")) {
            
            for (String[] list : doctorData) {
                if (this.userID.equals(list[0])) {
                    list[1] = this.userName;
                    list[2] = this.email;
                    list[4] = this.region;
                    list[8] = this.nationality;
                    list[9] = this.contactNumber;
                
                break;
                }
                
            }
        }
        else if (profilePage.equals("page2")) {
            for (String[] line : doctorData) {
                if (userID.equals(line[0])) {
                    line[11] = Integer.toString(this.age);
                    line[12] = this.qualification;
                    line[14] = Integer.toString(this.experience);
                    break;
                }
            }
        }
        File_Control.writeFile("doctorData.txt",doctorData);
}
    
    public void updateLogInName() {
        ArrayList<String[]> loginData = File_Control.readFile("loginData.txt", false);
        
        for (String[] line : loginData) {
                    if (line[0].equals(this.userID)) {
                        line[2] = this.userName;
                        break;
                    }
                }
                
                File_Control.writeFile("logindata.txt",loginData);
    }
    
    public void updateSpokenLanguage() {
        ArrayList<String[]> doctorData = File_Control.readFile("doctorData.txt", false);
        
        for (String[] line : doctorData) {
            if (line[0].equals(this.userID)) {
                line[13] = this.spokenLanguage;
            }
        }
        
        File_Control.writeFile("doctorData.txt", doctorData);
    }
    
    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setSpokenLanguage(String spokenLanguage) {
        this.spokenLanguage = spokenLanguage;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    
    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getSpecialist() {
        return specialist;
    }

    public String getWorkingDay() {
        return workingDay;
    }

    public String getRoom() {
        return room;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public String getQualification() {
        return qualification;
    }

    public String getSpokenLanguage() {
        return spokenLanguage;
    }

    public int getExperience() {
        return experience;
    }

    public int getAccountStatus() {
        return accountStatus;
    }
}
    
    
   
