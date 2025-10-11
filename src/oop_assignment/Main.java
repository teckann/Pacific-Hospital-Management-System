/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oop_assignment;

/**
 *
 * @author teckann
 */
public class Main {
    private static String currentUserID;
    private static String currentUserName;

    public static void setCurrentUserID(String currentUserID) {
        Main.currentUserID = currentUserID;
    }
    
    public static void setCurrentUserName(String currentUserName) {
        Main.currentUserName = currentUserName;
    }

    public static String getCurrentUserID() {
        return currentUserID;
    }
    
    public static String getCurrentUserName() {
        return currentUserName;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login_Frame initialFrame = new Login_Frame();
        initialFrame.setVisible(true);
    }   
}