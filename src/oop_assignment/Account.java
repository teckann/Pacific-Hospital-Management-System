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
class Account {
    protected String userID;
    protected String userName;
    protected String userPassword;
    protected String userRole;
    protected String safetyQuestionID1;
    protected String safetyAnswer1;
    protected String safetyQuestionID2;
    protected String safetyAnswer2;
    
    public Account() {}
    
    public Account(String userID, String userName, String userPassword, String userRole) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
    
    public Account(String userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }
    
    // update safety question [update attribute value & modify textFile]
    public void updateSafetyQuestion() {
        ArrayList<String[]> loginDataList = File_Control.readFile("loginData.txt", false);
        
        for (String[] list : loginDataList) {
            if (this.userID.equals(list[0])) {
                list[4] = this.safetyQuestionID1;
                list[5] = this.safetyAnswer1;
                list[6] = this.safetyQuestionID2;
                list[7] = this.safetyAnswer2;
                
                break;
            }
        }
        File_Control.writeFile("loginData.txt", loginDataList);
    }
    
    // update password [update attribute value & modify textFile]
    public void updatePassword() {
        ArrayList<String[]> dataList = File_Control.readFile("loginData.txt", false);
        
        for (String[] list : dataList) {
            if (this.userID.equals(list[0])) {
                list[3] = this.userPassword;
                
                break;
            }
        }
        File_Control.writeFile("loginData.txt", dataList);
    }
    
    public void readAllData() {
        ArrayList<String[]> dataList = File_Control.readFile("loginData.txt", true);
        for (String[] list : dataList) {
            if (this.userID.equals(list[0])) {
                userPassword = list[3];
                safetyQuestionID1 = list[4];
                safetyAnswer1 = list[5];
                safetyQuestionID2 = list[6];
                safetyAnswer2 = list[7];
                
                break;
            }
        }
    }
    
    //List of all question
    ArrayList<String[]> safetyQuestionList = File_Control.readFile("securityQuestion.txt", true);

    //filter the selected question
    public ArrayList<String> filterQuestion(String questionID){
        ArrayList<String> questionList = new ArrayList<>();
        
        for (String[] list : safetyQuestionList) {
            questionList.add(list[1]);
        }
        
        questionList.remove(this.questionInString(questionID));
        
        return questionList;
    }
    
    //get the question from questionID
    public String questionInString (String questionID){
        for (String[] list : safetyQuestionList) {
            if (questionID.equals(list[0])) {
                return list[1];
            }
        }
        
        return null;
    }
    
    //get questionID from question
    public String questionInID (String question){
        for (String[] list : safetyQuestionList) {
            if (question.equals(list[1])) {
                return list[0];
            }
        }
        
        return null;
    }
    
    public void logout() {
        System.out.println("Redirect to Login Page");
        Login_Frame loginFrame = new Login_Frame();
        loginFrame.setVisible(true);
    }
    
    // setter method
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setSafetyQuestionID1(String safetyQuestionID1) {
        this.safetyQuestionID1 = safetyQuestionID1;
    }

    public void setSafetyAnswer1(String safetyAnswer1) {
        this.safetyAnswer1 = safetyAnswer1;
    }

    public void setSafetyQuestionID2(String safetyQuestionID2) {
        this.safetyQuestionID2 = safetyQuestionID2;
    }

    public void setSafetyAnswer2(String safetyAnswer2) {
        this.safetyAnswer2 = safetyAnswer2;
    }
    
    // getter method
    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getSafetyQuestionID1() {
        return safetyQuestionID1;
    }

    public String getSafetyAnswer1() {
        return safetyAnswer1;
    }

    public String getSafetyQuestionID2() {
        return safetyQuestionID2;
    }

    public String getSafetyAnswer2() {
        return safetyAnswer2;
    }   
}
