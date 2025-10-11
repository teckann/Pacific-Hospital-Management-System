/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

import java.util.ArrayList;


/**
 *
 * @author Jimmy
 */
public class Child extends Patient {
    private String ParentID;
    
    public Child(){}
    
    public Child(String userID, String ParentID, String userName,
                String icNumber,String gender, String region,
                String Age,String BloodType)
    {
        super(userID, userName, icNumber, Age, gender, region,BloodType);
        this.ParentID = ParentID;
    }
    
    
    
    public String[] getAllVariable(){
        String[] allVariable = {userID, ParentID, userName,
                                icNumber, gender, region,
                                Age, BloodType,"1"};
        return allVariable;
    }
    
    @Override
    public String makeitString(){
        String toWrite = String.join(";", userID, ParentID, userName,
                                icNumber, gender, region, Age, BloodType,
                                alergic,lifeStyle,"1");
        return toWrite;
    }
    
    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String ParentID) {
        this.ParentID = ParentID;
    }
    
    // GET ALL CHILD DETAILS FOR ONE PARENT
    @Override
    public ArrayList<String[]> getIndividual(String ParentID){
        ArrayList<String[]> list = File_Control.readFile("childData.txt",true);
        ArrayList<String[]> currentChild = new ArrayList<>();
        

        
        for (String[] UserList : list){
            if(UserList[1].equals(ParentID)){
                currentChild.add(UserList);
            }
        }
        
        
        
        return currentChild;
    }
    
    //GET ALL CHILD ID FOR ONE PARENT
    public ArrayList<String> getAllChildID(String ParentID){
        ArrayList<String[]> list = File_Control.readFile("childData.txt",true);
        ArrayList<String> currentChild = new ArrayList<>();
        

        
        for (String[] UserList : list){
            if(UserList[1].equals(ParentID)){
                currentChild.add(UserList[0]);
            }
        }
        
        
        
        return currentChild;
    }
    
}

