package oop_assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class File_Control {
    // read file method
    public static ArrayList<String[]> readFile(String fileName, boolean filter) {
        ArrayList<String[]> dataList = new ArrayList<>();
        ArrayList<String[]> filterList = new ArrayList<>();

        File file = new File(fileName);

        try {
            Scanner fileSC = new Scanner(file);

            while (fileSC.hasNextLine()) {
                String line = fileSC.nextLine();

                if (!(line.isEmpty())) {
                    String[] lineData = line.split(";");

                    for (int i=0; i<lineData.length; i++) {
                        lineData[i] = lineData[i].trim();
                    }
                    dataList.add(lineData);
                }
            }
            fileSC.close();

            for (String[] list : dataList) {
                if(list[list.length-1].equals("0")) {
                    continue;
                }
                else {
                    filterList.add(list);
                }
            }
            
            if (filter == true) {
                return filterList;
            }
            else {
                return dataList;
            }
        }
        catch (IOException Ex) {
            System.out.println("File not found");
            return null;
        }
    }

    // write file method
    public static void writeFile(String fileName, ArrayList<String[]> dataList) {
        File file = new File(fileName);
        try {
            // make it overwrite
            FileWriter FW = new FileWriter(file); // default is false (overwrite)
            BufferedWriter BW = new BufferedWriter(FW);
            PrintWriter PW = new PrintWriter(BW);

            for (String[] list : dataList) {
                for (int i=0; i<list.length; i++) {
                    PW.print(list[i]);
                    if (i != list.length-1) {
                        PW.print(";");
                    }
                }
                BW.newLine();
            }
            PW.close();
        }
        catch (IOException Ex) {
            System.out.println("File not found");
        }
    }
    
    // add file method
    public static void addData(String fileName, String line) {
        File file = new File(fileName);

        try {
            FileWriter FW = new FileWriter(file,true);
            BufferedWriter BW = new BufferedWriter(FW);
            PrintWriter PW = new PrintWriter(BW);

            PW.println(line);
            PW.close();
        }
        catch (IOException Ex) {
            System.out.println("File not found");
        }
    }
}
