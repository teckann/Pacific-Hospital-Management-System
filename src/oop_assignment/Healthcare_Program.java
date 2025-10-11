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
public class Healthcare_Program {
    private String programID;
    private String category;
    private String programTitle;
    private String date;
    private String time;
    private String location;
    private String speaker;
    private int maxParticipants;
    private int currentParticipants;
    private String startTime;
    private String duration;
    
    private String txtFileName = "healthcareProgramData.txt";
    private ArrayList<String[]> filterDataList = File_Control.readFile(txtFileName, true);
    private ArrayList<String[]> nonFilterDataList = File_Control.readFile(txtFileName, false);
    
    // empty constructor
    public Healthcare_Program() {
        this.currentParticipants = 0;
    }
    
    // constructor with parameter
    // initialize all healthcare program data
    public Healthcare_Program(String programID) {
        for (String[] list : filterDataList) {
            if (programID.equals(list[0])) {
                this.programID = list[0];
                this.category = list[1];
                this.programTitle = list[2];
                this.date = list[3];
                this.time = list[4];
                this.location = list[5];
                this.speaker = list[6];
                this.maxParticipants = Integer.parseInt(list[7]);
                this.currentParticipants = Integer.parseInt(list[8]);
                this.startTime = list[9];
                this.duration = list[10];
                
                break;
            }
        }
    }
    
    // update healthcare programs [update attribute value & modify textFile]
    public void updatePorgram(String programTitle, String category, String date, String startTime, String duration,
            String startEndTime, String location , String speaker, int maxParticipants) {
        this.programTitle = programTitle;
        this.category = category;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.time = startEndTime;
        this.location = location;
        this.speaker = speaker;
        this.maxParticipants = maxParticipants;

        for (String[] list : nonFilterDataList) {
            if(this.programID.equals(list[0])) {
                list[1] = this.category;
                list[2] = this.programTitle;
                list[3] = this.date;
                list[4] = this.time;
                list[5] = this.location;
                list[6] = this.speaker;
                list[7] = Integer.toString(this.maxParticipants);
                list[9] = this.startTime;
                list[10] = this.duration;
            }
        }
        
        File_Control.writeFile(txtFileName, nonFilterDataList);
    }
    
    // initialize NEW healthcare program data [Add Healthcare Program Process]
    public void addProgram(String programID, String programTitle, String category, String date, String startTime,
            String duration, String startEndTime, String location , String speaker, int maxParticipants) {
        this.programID = programID;
        this.programTitle = programTitle;
        this.category = category;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.time = startEndTime;
        this.location = location;
        this.speaker = speaker;
        this.maxParticipants = maxParticipants;
        
        String dataLine = this.programID + ";" + this.category + ";" + this.programTitle + ";" + this.date + ";" +
                this.time + ";" + this.location + ";" + this.speaker + ";" + Integer.toString(this.maxParticipants) + ";" +
                Integer.toString(this.currentParticipants) + ";" + this.startTime + ";" + this.duration + ";1";
        
        File_Control.addData(txtFileName, dataLine);
     }
    
    // delete healthcare program
    public boolean deleteProgram(String deleteProgramID) {
        boolean status = false;
        
        for (String[] list : nonFilterDataList) {
            String programId = list[0];

            if (programId.equals(deleteProgramID)) {
                list[list.length - 1] = "0";
                status = true;
            }
        }
        
        if (status == true) {
            File_Control.writeFile(txtFileName, nonFilterDataList);
            return true;
        }
        return false;
    }
    
    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }
 
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getProgramID() {
        return programID;
    }

    public String getCategory() {
        return category;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getSpeaker() {
        return speaker;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public String getDuration() {
        return duration;
    }
}
