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
public class Patient extends Users{
    protected String Age;
    protected String Race;
    protected String BloodType;
    protected String lifeStyle,alergic;
    
    public Patient(){}
    
    public Patient (String userID, String userName, String icNumber,
                    String Age, String gender, String region, String BloodType)
    {
        super(userID, userName, icNumber, gender, region);
        
        this.Age = Age;
        
        this.BloodType = BloodType;
    }
      
    public Patient (String userID, String userName, String icNumber,
                    String Age, String gender, String Race,
                    String contactNumber, String email, String region,
                    String BloodType) 
    {
        super(userID, userName, icNumber, gender, contactNumber, email, region);
        
        this.Age = Age;
        
        this.Race = Race;
        
        this.BloodType = BloodType;
    }

    // initialize NEW patient data [Register Process - Admin Frame]
    public Patient (String userID, String userName, String userPassword, String userRole, String icNumber, 
            String gender, String region, String email, String contactNumber, String age, String race) {
        super(userID, userName, userPassword, userRole, icNumber, gender, region, email, contactNumber);

        this.Age = age;
        this.Race = race;
    }
    
    // add NEW patient to txtFile [Register Process - Admin Frame]
    public void writeNewPatientData() {
        String loginDataLine = this.userID + ";" + this.userRole + ";" + this.userName + ";" + this.userPassword + ";-;-;-;-;1";
        String patientDataLine = this.userID + ";" + this.userName + ";" + this.icNumber + ";" + this.Age + ";" + this.gender + 
                ";" + this.Race + ";" + this.contactNumber + ";" + this.email + ";" + this.region + ";-;-,-,-,-;1";

        // add to text file
        File_Control.addData("loginData.txt", loginDataLine);
        File_Control.addData("patientData.txt", patientDataLine);
    }
    
    public void assignUnuseData(){
        ArrayList<String[]> list = getIndividual(Main.getCurrentUserID());
        
        lifeStyle = list.get(0)[10];
        alergic = list.get(0)[11];
    }
    
    
    public ArrayList<String[]> getIndividual(String UserID){
        ArrayList<String[]> list = File_Control.readFile("patientData.txt",false);
        ArrayList<String[]> UserDetail = new ArrayList<>();
        
        for (String[] UserList : list){
            if(UserList[0].equals(UserID)){
                UserDetail.add(UserList);
            }
        }
        
        return UserDetail;
    }
    
    public String makeitString(){
        String toWrite = String.join(";", userID, userName, icNumber,
                                    Age, gender, Race, contactNumber,
                                    email, region, BloodType,lifeStyle,
                                    alergic,"1");
        return toWrite;
    }
    
    public static void modifyListPatient(String filename,String[] toModify){
        ArrayList<String[]> list = File_Control.readFile(filename,false);
        int i = 0;
        
        
        for (String[] individual : list){
            if(individual[0].equals(toModify[0])){
                break;
            }
            i++;
        }
        
        list.set(i, toModify);
        
        File_Control.writeFile(filename, list);
    }
    
    public static void modifyOneValue (String filename,String IDtoModify,
                                             String toModify,int indexToModify)
    {
        ArrayList<String[]> list = File_Control.readFile(filename,false);
        int i = 0;
        
        
        for (String[] individual : list){
            if(individual[0].equals(IDtoModify)){
                break;
            }
            i++;
        }
        
        if (i >= list.size()) {
            System.out.println("ID not found: " + IDtoModify);
            return;
        }
        
        String[] listToModify = list.get(i);
        listToModify[indexToModify] = toModify;
        list.set(i, listToModify);
        
        File_Control.writeFile(filename, list);
    }
    
    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String Race) {
        this.Race = Race;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String BloodType) {
        this.BloodType = BloodType;
    }

    public String getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public String getAlergic() {
        return alergic;
    }

    public void setAlergic(String alergic) {
        this.alergic = alergic;
    }
}
