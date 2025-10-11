/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

/**
 *
 * @author teckann
 */

// Super Class / Parent Class
class Users extends Account {
    protected String icNumber;
    protected String gender;
    protected String region;
    protected String email;
    protected String contactNumber;

    // empty constructor
    public Users() {}
    
    // constructor with parameter
    public Users(String userID, String userName, String userPassword, String userRole,
            String icNumber, String gender, String region, String email, String contactNumber) {
        super (userID, userName, userPassword, userRole);
        
        this.icNumber = icNumber;
        this.gender = gender;
        this.region = region;
        this.email = email;
        this.contactNumber = contactNumber;
    }
    
    public Users(String userID, String userName, String icNumber, String gender, String region) {
        super (userID, userName);
        this.icNumber = icNumber;
        this.gender = gender;
        this.region = region;
    }
    
    // use for load Admin Data [User Management - View & Update Admin Record] [Admin Frame]
    public Users(String userID, String userName, String icNumber, String gender, String contactNumber, 
            String email, String region) {
        super(userID, userName);
        
        this.gender = gender;
        this.icNumber = icNumber;
        this.contactNumber = contactNumber;
        this.email = email;
        this.region = region;
    }
    

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getRegion() {
        return region;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
