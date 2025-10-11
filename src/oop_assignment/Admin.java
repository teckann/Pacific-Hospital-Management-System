/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

import java.util.ArrayList;

/**
 *
 * @author teckann
 */

// Account <- Users <- Admin
// Admin inheritance Users & Users inheritance Account
public class Admin extends Users {
    private String nationality;

    // empty constructor
    public Admin() {}
    
    // constructor with parameter
    // initialize all admin data
    public Admin(String userID) {
        ArrayList<String[]> loginDataList = File_Control.readFile("loginData.txt", true);
        ArrayList<String[]> adminDataList = File_Control.readFile("adminData.txt", true);
        
        // assign value to userID first (Account Class' attribute)
        this.userID = userID;
        
        for (String[] list : loginDataList) {
            if (this.userID.equals(list[0])) {
                // assign value to Account Class' attribute
                this.userRole = list[1];
                this.userName = list[2];
                this.userPassword = list[3];
                this.safetyQuestionID1 = list[4];
                this.safetyAnswer1 = list[5];
                this.safetyQuestionID2 = list[6];
                this.safetyAnswer2 = list[7];
                break;
            }
        }
        
        for (String[] list : adminDataList) {
            if (this.userID.equals(list[0])) {
                // assign value to Users Class' attribute
                this.icNumber = list[2];
                this.gender = list[3];
                this.region = list[5];
                this.contactNumber = list[6];
                this.email = list[7];
                
                // assign value to this class' attribute
                this.nationality = list[4];
                break;
            }
        }  
    }
    
    // initialize NEW admin data [Register Process]
    public Admin(String userID, String userName, String userPassword, String userRole, String icNumber,
            String gender, String region, String email, String contactNumber, String nationality) {
        // assign the value to super class (Users Class)
        super (userID, userName, userPassword, userRole, icNumber, gender, region, email, contactNumber);
        
        this.nationality = nationality;
    }
    
    // add NEW admin to txtFile [Register Process]
    public void writeNewAdminData() {
        String loginDataLine = this.userID + ";" + this.userRole + ";" + this.userName + ";" + this.userPassword + ";-;-;-;-;1";
        String adminDataLine = this.userID + ";" + this.userName + ";" + this.icNumber + ";" + this.gender + ";" + this.nationality + 
                ";" + this.region + ";" + this.contactNumber + ";" + this.email + ";1";
        
        // add to text file
        File_Control.addData("loginData.txt", loginDataLine);
        File_Control.addData("adminData.txt", adminDataLine);
    }
    
    
    // use for load Admin Data [User Management - View & Update Admin Record]
    public Admin(String userID, String userName, String icNumber, String gender,
            String contactNumber, String email, String region, String nationality) {
        super(userID, userName, icNumber, gender, contactNumber, email, region);
        
        this.nationality = nationality;
    }
    
    // [User Management - View & Update Admin Record]
    // read admin data from adminData.txt by using readFile method in File_Control class
    // and convert it to a list of Admin objects
    public static ArrayList<Admin> loadAdmins(boolean filter) {
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<String[]> dataList = File_Control.readFile("adminData.txt", filter); 
        for (String[] list : dataList) {
            String UserID = list[0];
            String UserName = list[1];
            String IcNumber = list[2];
            String Gender = list[3];
            String Nationality = list[4];
            String Region = list[5];
            String ContactNumber = list[6];
            String Email = list[7];
            
            Admin admin = new Admin (UserID, UserName, IcNumber, Gender, ContactNumber, Email, Region, Nationality);
            admins.add(admin);
        }
        return admins;
    }
    
    // update admin profile [update attribute value & modify textFile]
    public void updateProfile() {
        ArrayList<String[]> adminDataList = File_Control.readFile("adminData.txt", false);
        ArrayList<String[]> loginDataList = File_Control.readFile("loginData.txt", false);
        
        for (String[] list : adminDataList) {
            if (this.userID.equals(list[0])) {
                list[1] = this.userName;
                list[4] = this.nationality;
                list[5] = this.region;
                list[6] = this.contactNumber;
                list[7] = this.email;

                break;
            }
        }
        File_Control.writeFile("adminData.txt", adminDataList);
        
        for (String[] list : loginDataList) {
            if (this.userID.equals(list[0])) {
                list[2] = this.userName;

                Main.setCurrentUserName(this.userName);
                break;
            }
        }
        File_Control.writeFile("loginData.txt", loginDataList);
    }
    
    // delete user [delete the user based on userID (an argument) and modify related textfile also]
    public boolean deleteUsers(String deleteUserID) {
        ArrayList<String[]> loginDataList = File_Control.readFile("loginData.txt", false);
        ArrayList<String[]> adminDataList = File_Control.readFile("adminData.txt", false);
        ArrayList<String[]> doctorDataList = File_Control.readFile("doctorData.txt", false);
        ArrayList<String[]> patientDataList = File_Control.readFile("patientData.txt", false);
        
        boolean loginFileStatus = false;
        boolean roleFileStatus = false;
        
        for (String[] list : loginDataList) {
            String UserID = list[0];

            if (UserID.equals(deleteUserID)) {
                list[list.length - 1] = "0";
                loginFileStatus = true;
                break;
            }
        }

        if (deleteUserID.startsWith("A")) {
            for (String[] list : adminDataList) {
                String UserID = list[0];

                if (UserID.equals(deleteUserID)) {
                    list[list.length - 1] = "0";
                    roleFileStatus = true;
                    break;
                }
            }
        }

        else if (deleteUserID.startsWith("D")) {
            for (String[] list : doctorDataList) {
                String UserID = list[0];

                if (UserID.equals(deleteUserID)) {
                    list[list.length - 1] = "0";
                    roleFileStatus = true;
                    break;
                }
            }
        }

        else if (deleteUserID.startsWith("P")) {
            for (String[] list : patientDataList) {
                String UserID = list[0];

                if (UserID.equals(deleteUserID)) {
                    list[list.length - 1] = "0";
                    roleFileStatus = true;
                    break;
                }
            }
        }
        
        // check either all the processes are passed
        if (loginFileStatus == true && roleFileStatus == true) {
            // start writing file
            File_Control.writeFile("loginData.txt", loginDataList);
            
            // write file based on the role
            if (deleteUserID.startsWith("A")) {
                File_Control.writeFile("adminData.txt", adminDataList);
            }
            else if (deleteUserID.startsWith("D")) {
                File_Control.writeFile("doctorData.txt", doctorDataList);
            }
            else if (deleteUserID.startsWith("P")) {
                File_Control.writeFile("patientData.txt", patientDataList);
            }
            
            return true;
        }
        
        else {
            return false;
        }
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }
}
