/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_assignment;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.*;

/**
 *
 * @author teckann
 */
public final class Admin_Frame2 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Admin_Frame2.class.getName());
    
    /**
     * Creates new form Admin_Frame2
     */
    
    // initialize all admin information
    Admin admin = new Admin(Main.getCurrentUserID());
    
    private Admin_Frame adminFrame; // declare a variable & the type is Admin_Frame
    private final String defaultText = "N/A"; // set default value
    
    // set the default color and hover color (improve user experience - UX)
    private final Color defaultColor = new Color(234, 242, 248);
    private final Color hoverColor = new Color(200,220,235);
    
    // model the JTable that need real-time updates based on selection
    private final DefaultTableModel modelParticipantsTable = new DefaultTableModel();
        
    public Admin_Frame2() {
        initComponents();
        this.setLocationRelativeTo(null); // center the jFrame
    }
    
    // is a method that enable program pass Admin_Frame directly into this frame
    public void setFirst_adminFrame(Admin_Frame adminFrame){
        this.adminFrame = adminFrame;
    }
    
    // is a method that allows the program to identify and display the correct panel based on the user's operation
    // for Admin_Frame ONLY
    public void showPanel(String navbarTitle) {
        lblAdminName2.setText(admin.getUserName());

        switch (navbarTitle) {
            case "Hospital Information" -> {
                showHospitalInformationPanel();
            }
            
            case "HealthCare Programs" -> {
                showHealthCareProgramsPanel();
            }
        }
    }
    
    public void warningDialog(String invalidType, String Title, javax.swing.JLabel label) {
        switch (invalidType) {
            case "String" -> {
                JOptionPane.showMessageDialog(this,
                    "Please make sure the format of " + Title + " is correct.\n" +
                    "1. Only letters\n" +
                    "2. Without number",
                    "Pacific Data Validation Center (DVC)",
                    JOptionPane.WARNING_MESSAGE);
            }
            
            case "Number" -> {
                JOptionPane.showMessageDialog(this,
                    "Please make sure the format of " + Title + " is correct.\n" +
                    "1. Only Numbers",
                    "Pacific Data Validation Center (DVC)",
                    JOptionPane.WARNING_MESSAGE);
            }

            case "Empty" -> {
                JOptionPane.showMessageDialog(this, 
                    "Please make sure all sections are not empty.",
                    "Pacific Data Validation Center (DVC)", 
                    JOptionPane.WARNING_MESSAGE);
            }
            
            case "deleteStatement" -> {
                JOptionPane.showMessageDialog(this, 
                    "Please agree to all statements before delete.",
                    "Pacific Data Security Center (DSC)", 
                    JOptionPane.WARNING_MESSAGE);
            }
            
            default -> {}
        }
        
        if (label != null) {
            label.setVisible(false);
        }
    }
    
    // main panel include (Hhospital Information Panel, Healthcare Program Panel) - NavBar Elements
    public void mainPanelControl(javax.swing.JPanel showPanel) {
        // first, hide all the main panel
        HospitalInfoPanel.setVisible(false);
        HealthCarePanel.setVisible(false);
        
        // only show the panel that I give (argument)
        showPanel.setVisible(true);
    }
    
    // the purpose of create 2 methods below is reduce duplication of the same code/logic
    public void showHospitalInformationPanel() {
        mainPanelControl(HospitalInfoPanel);
        System.out.println("Redirect to the Hsopital Information page.");

        hospitalInformationTable();
        disenable();

        updateInformationPanel.setVisible(true);
        addInfomationPanel.setVisible(false);
        deleteInformationPanel.setVisible(false);

        lblTipsInformation1.setVisible(false);
        lblTipsInformation2.setVisible(false);
        lblSuccessInformation.setVisible(false);
        btnSaveInformation.setEnabled(false);
    }
    
    public void showHealthCareProgramsPanel() {
        mainPanelControl(HealthCarePanel);
        System.out.println("Redirect to the Healthcare Programs page.");
        
        programMainPanel.setVisible(true);
        deleteProgramPanel.setVisible(false);
        manageParticipantsPanel.setVisible(false);
        updateProgramPanel.setVisible(false);
        addProgramPanel.setVisible(false);
        
        disenable();
        healthcareProgramTable();
    }
    
    // the purpose of these methods below are model table / combo box
    public void hospitalInformationTable() {
        String[] tableTitle = {"Information ID", "Information Type", "Details"};
        
        ArrayList<String[]> dataList = File_Control.readFile("hospital_information.txt", true);
        
        // Model table - clear the table
        // it will only display the data that we add below
        DefaultTableModel tableModel = new DefaultTableModel() {
            // override isCellEditable
            // so the admin cannot make any change for column 0 and 1
            // which is Information ID and Information Type
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1) {
                    return false;
                }
                return true;
            }
        };
        
        // show the title in table
        tableModel.setColumnIdentifiers(tableTitle);
        
        // start add data into table
        for (String[] list : dataList) {
            tableModel.addRow(list);
        }
        
        // model table
        tblHospitalInformation.setModel(tableModel);
    }
    
    public void healthcareProgramTable() {
        String[] tableTitle1 = {"Program ID", "Category", "Title", "Date", "Time", "Location", "Speaker"};
        String[] tableTitle2 = {"ProgramID", "Category", "Title"};
        
        ArrayList<String[]> dataList = File_Control.readFile("healthcareProgramData.txt", true);
        
        // Model table - clear the table
        // it will only display the data that we add below
        DefaultTableModel tableModelA = new DefaultTableModel();
        DefaultTableModel tableModelB = new DefaultTableModel();
        
        // show the title in table
        tableModelA.setColumnIdentifiers(tableTitle1);
        tableModelB.setColumnIdentifiers(tableTitle2);
        
        // start add data into table
        for (String[] list : dataList) {
            tableModelA.addRow(list);
            tableModelB.addRow(list);
        }
        
        // model table
        tblHealthcarePrograms.setModel(tableModelA);
        tblHealthcareProgramsDeletePanel.setModel(tableModelB);
    }

    public void participantsTable() {
        // clear table first
        modelParticipantsTable.setRowCount(0);
        String[] tableTitle2 = {"Ticket ID", "Program ID", "Name", "Email", "Contact Number"};
        
        ArrayList<String[]> dataList = File_Control.readFile("ticketData.txt", true);

        // show the title in table
        modelParticipantsTable.setColumnIdentifiers(tableTitle2);
        
        // start add data into table
        for (String[] list : dataList) {
            modelParticipantsTable.addRow(list);
        }
        
        tblParticipants.setModel(modelParticipantsTable);
    }
    
    public void modelComboBoxInformationType() {
        String[] type = {"General Information", "Accident & Emergency", "Visiting Hours", "Operating Hours", "Safety Measures"};
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String data : type) {
            comboModel.addElement(data);
        }
        
        cboInformationType.setModel(comboModel);
    }
    
    public void modelComboBoxInformationID() {
        ArrayList<String[]> dataList = File_Control.readFile("hospital_information.txt", true);
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String[] list : dataList) {
            comboModel.addElement(list[0]);
        }
        
        cboInformationID.setModel(comboModel);
    }
    
    public void setSpinnerDate() {
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnDate, "yyyy-MM-dd");
        spnDate.setEditor(editor);
    }
    
    public void modelSpinnerDate() {
        Date today = new Date();

        SpinnerDateModel model = new SpinnerDateModel(today, today, null, Calendar.DAY_OF_MONTH);
        spnDateAdd.setModel(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnDateAdd, "yyyy-MM-dd");
        spnDateAdd.setEditor(editor);
    }
    
   public void modelComboBoxDuration() {
       String[] duration = {"30m", "1h", "1h 30m", "2h", "2h 30m", "3h"};
       
       DefaultComboBoxModel<String> comboModelA = new DefaultComboBoxModel<>();
       DefaultComboBoxModel<String> comboModelB = new DefaultComboBoxModel<>();
        
        for (String data : duration) {
            comboModelA.addElement(data);
            comboModelB.addElement(data);
        }
        
        cboDuration.setModel(comboModelA);
        cboDurationAdd.setModel(comboModelB);
   }
   
   public void modelComboBoxProgramID() {
       ArrayList<String[]> dataList = File_Control.readFile("healthcareProgramData.txt", true);
       
       DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String[] list : dataList) {
            comboModel.addElement(list[0]);
        }
        
        cboProgramID.setModel(comboModel);
        cboProgramIDDeletePanel.setModel(comboModel);
        cboProgramIDViewParticipants.setModel(comboModel);
   }
   
   public void modelComboBoxLocation() {
       String[] location = {"Hall A", "Hall B", "Hall C"};
       
       DefaultComboBoxModel<String> comboModelA = new DefaultComboBoxModel<>();
       DefaultComboBoxModel<String> comboModelB = new DefaultComboBoxModel<>();
        
        for (String data : location) {
            comboModelA.addElement(data);
            comboModelB.addElement(data);
        }
        
       cboLocation.setModel(comboModelA);
       cboLocationAdd.setModel(comboModelB);
   }
   
   public void modelComboBoxCategory() {
       String[] category = {"Education", "Support", "Lifestyle"};
       
       DefaultComboBoxModel<String> comboModelA = new DefaultComboBoxModel<>();
       DefaultComboBoxModel<String> comboModelB = new DefaultComboBoxModel<>();
        
        for (String data : category) {
            comboModelA.addElement(data);
            comboModelB.addElement(data);
        }
        
       cboCategory.setModel(comboModelA);
       cboCategoryAdd.setModel(comboModelB);
   }
          
   public void modelComboBoxStartTime() {
       String[] startTime = {
            "07:00", "07:30", "08:00", "08:30", "09:00", "09:30",
            "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
            "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
            "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
            "19:00", "19:30", "20:00"
       };
       
       DefaultComboBoxModel<String> comboModelA = new DefaultComboBoxModel<>();
       DefaultComboBoxModel<String> comboModelB = new DefaultComboBoxModel<>();
        
        for (String data : startTime) {
            comboModelA.addElement(data);
            comboModelB.addElement(data);
        }
        
       cboStartTime.setModel(comboModelA);
       cboStartTimeAdd.setModel(comboModelB);
   }
   
   
   // changed argument to minute (e.g. 1h 30m to 90)
    public int findDurationMinute(String duration) {
        // all the minute that we allow users choose
        int[] minuteList = {30, 60, 90, 120, 150, 180}; 
        
        int durationMinute = 0;
        // based on the argument to assign different minute
        switch(duration) {
            case "30m" -> {
                durationMinute = minuteList[0];
            }
            
            case "1h" -> {
                durationMinute = minuteList[1];
            }
            
            case "1h 30m" -> {
                durationMinute = minuteList[2];
            }
            
            case "2h" -> {
                durationMinute = minuteList[3];
            }
            
            case "2h 30m" -> {
                durationMinute = minuteList[4];
            }
            
            case "3h" -> {
                durationMinute = minuteList[5];
            }
            
            default -> {}
        }
        
        // return minute
        return durationMinute;
    }
    
    // find the satrt - end time (e.g. 13:00 - 14:00)
    public String startEndTime(String startTime, int minute) {
        // set the time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        
        // parse the startTime to time format at we set
        LocalTime formatedStartTime = LocalTime.parse(startTime, formatter);
        
        // calculate the end time based on start time and duration 
        LocalTime endTime = formatedStartTime.plusMinutes(minute);
        
        // final display data
        return formatedStartTime.toString() + " - " + endTime.toString();
    }
    
    // this method is MAIN method to update the time in real time (e.g. 13:00 - 14:00)
    public void realTimeUpdate_startEndTime(String page) {
        // update program page
        if (page.equals("Update")) {
            // get the text from combo box
            String startTime = cboStartTime.getSelectedItem().toString();
            String duration = cboDuration.getSelectedItem().toString();
            
            // call findDurationMinute() method to calculate the duaration
            int durationMinute = findDurationMinute(duration);
            
            // call startEndTime() method to find the time (e.g. 13:00 - 14:00)
            String startEndTime = startEndTime(startTime, durationMinute);
        
            lblStartEndTime.setText(startEndTime);
        }
        
        // add program page
        else if (page.equals("Add")) {
            // same concept as above
            String startTime = cboStartTimeAdd.getSelectedItem().toString();
            String duration = cboDurationAdd.getSelectedItem().toString();
            
            int durationMinute = findDurationMinute(duration);
      
            String startEndTime = startEndTime(startTime, durationMinute);
            
            lblStartEndTimeAdd.setText(startEndTime);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel93 = new javax.swing.JLabel();
        jComboBox24 = new javax.swing.JComboBox<>();
        jComboBox25 = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        pnlMenuBar = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        ProfileNavBar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        AssistanceNavBar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        RegisterNavBar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        InventoryNavBar = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        HospitalInfoNavBar = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        HealthCareNavBar = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        UserManagementNavBar = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblAdminName2 = new javax.swing.JLabel();
        ShowSpace = new javax.swing.JPanel();
        HospitalInfoPanel = new javax.swing.JPanel();
        showSpace1 = new javax.swing.JPanel();
        updateInformationPanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnUpdateInformation = new javax.swing.JButton();
        btnSaveInformation = new javax.swing.JButton();
        lblSuccessInformation = new javax.swing.JLabel();
        lblTipsInformation1 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        btnAddInformation = new javax.swing.JButton();
        btnDeleteInformation = new javax.swing.JButton();
        lblTipsInformation2 = new javax.swing.JLabel();
        addInfomationPanel = new javax.swing.JPanel();
        btnReturnHospitalInformation = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        areaInformationDetails = new javax.swing.JTextArea();
        cboInformationType = new javax.swing.JComboBox<>();
        jLabel382 = new javax.swing.JLabel();
        jLabel381 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jLabel380 = new javax.swing.JLabel();
        lblCounterInformation = new javax.swing.JLabel();
        jLabel385 = new javax.swing.JLabel();
        btnAddInfo = new javax.swing.JButton();
        jLabel384 = new javax.swing.JLabel();
        jLabel383 = new javax.swing.JLabel();
        deleteInformationPanel = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        cboInformationID = new javax.swing.JComboBox<>();
        chbDeleteInformation1 = new javax.swing.JCheckBox();
        jLabel55 = new javax.swing.JLabel();
        chbDeleteInformation2 = new javax.swing.JCheckBox();
        btnDeleteInformation2 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        btnReturnInformation = new javax.swing.JButton();
        lblSuccessDeleteInformation = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel378 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHospitalInformation = new javax.swing.JTable();
        HealthCarePanel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        showSpace = new javax.swing.JPanel();
        deleteProgramPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHealthcareProgramsDeletePanel = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        cboProgramIDDeletePanel = new javax.swing.JComboBox<>();
        chbDeleteProgram1 = new javax.swing.JCheckBox();
        jLabel58 = new javax.swing.JLabel();
        chbDeleteProgram2 = new javax.swing.JCheckBox();
        btnDeleteProgram2 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        btnReturnDeleteProgram = new javax.swing.JButton();
        lblSuccessDeleteProgram = new javax.swing.JLabel();
        manageParticipantsPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblParticipants = new javax.swing.JTable();
        jPanel36 = new javax.swing.JPanel();
        jLabel225 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        jLabel278 = new javax.swing.JLabel();
        jLabel282 = new javax.swing.JLabel();
        jLabel283 = new javax.swing.JLabel();
        lblProgramIDView = new javax.swing.JLabel();
        lblMaxParticipantsView = new javax.swing.JLabel();
        lblCurrentParticipants = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        jLabel279 = new javax.swing.JLabel();
        lblProgramTitleView = new javax.swing.JLabel();
        cboProgramIDViewParticipants = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        programMainPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHealthcarePrograms = new javax.swing.JTable();
        btnUpdateProgram = new javax.swing.JButton();
        btnAddProgram = new javax.swing.JButton();
        btnDeleteProgram = new javax.swing.JButton();
        btnViewParticipants = new javax.swing.JButton();
        updateProgramPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProgramID = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtProgramTitle = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cboCategory = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        spnDate = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cboLocation = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnUpdateProgram2 = new javax.swing.JButton();
        btnSaveUpdateProgram = new javax.swing.JButton();
        btnReturnUpdateProgram2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cboProgramID = new javax.swing.JComboBox<>();
        lblSuccessUpdateProgram = new javax.swing.JLabel();
        txtSpeaker = new javax.swing.JTextField();
        cboDuration = new javax.swing.JComboBox<>();
        lblStartEndTime = new javax.swing.JLabel();
        cboStartTime = new javax.swing.JComboBox<>();
        txtMaxParticipants = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        addProgramPanel = new javax.swing.JPanel();
        cboCategoryAdd = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cboStartTimeAdd = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        spnDateAdd = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cboLocationAdd = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtSpeakerAdd = new javax.swing.JTextField();
        txtMaxParticipantsAdd = new javax.swing.JTextField();
        cboDurationAdd = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        lblStartEndTimeAdd = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtProgramTitleAdd = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        btnReturnAddProgram = new javax.swing.JButton();
        btnAddProgram2 = new javax.swing.JButton();
        lblSuccessAddProgram = new javax.swing.JLabel();
        comfirmSlip = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        lblProgramID = new javax.swing.JLabel();
        lblProgramTitle = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblLocation = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        lblSpeaker = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        lblMaxParticipants = new javax.swing.JLabel();

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel93.setText("-");

        jComboBox24.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        jComboBox25.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(235, 245, 251));

        pnlMenuBar.setBackground(new java.awt.Color(214, 234, 248));
        pnlMenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setBackground(new java.awt.Color(242, 215, 213));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        pnlMenuBar.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        ProfileNavBar.setBackground(new java.awt.Color(234, 242, 248));
        ProfileNavBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ProfileNavBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProfileNavBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProfileNavBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProfileNavBarMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 82, 118));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Profile");

        javax.swing.GroupLayout ProfileNavBarLayout = new javax.swing.GroupLayout(ProfileNavBar);
        ProfileNavBar.setLayout(ProfileNavBarLayout);
        ProfileNavBarLayout.setHorizontalGroup(
            ProfileNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ProfileNavBarLayout.setVerticalGroup(
            ProfileNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfileNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenuBar.add(ProfileNavBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 176, 40));

        AssistanceNavBar.setBackground(new java.awt.Color(234, 242, 248));
        AssistanceNavBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AssistanceNavBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AssistanceNavBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AssistanceNavBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AssistanceNavBarMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 82, 118));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Assistance");

        javax.swing.GroupLayout AssistanceNavBarLayout = new javax.swing.GroupLayout(AssistanceNavBar);
        AssistanceNavBar.setLayout(AssistanceNavBarLayout);
        AssistanceNavBarLayout.setHorizontalGroup(
            AssistanceNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AssistanceNavBarLayout.setVerticalGroup(
            AssistanceNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssistanceNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenuBar.add(AssistanceNavBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 176, 40));

        RegisterNavBar.setBackground(new java.awt.Color(234, 242, 248));
        RegisterNavBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RegisterNavBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterNavBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RegisterNavBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegisterNavBarMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 82, 118));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Register User");

        javax.swing.GroupLayout RegisterNavBarLayout = new javax.swing.GroupLayout(RegisterNavBar);
        RegisterNavBar.setLayout(RegisterNavBarLayout);
        RegisterNavBarLayout.setHorizontalGroup(
            RegisterNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RegisterNavBarLayout.setVerticalGroup(
            RegisterNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegisterNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenuBar.add(RegisterNavBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 176, 40));

        InventoryNavBar.setBackground(new java.awt.Color(234, 242, 248));
        InventoryNavBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        InventoryNavBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventoryNavBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InventoryNavBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InventoryNavBarMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 82, 118));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Inventory Management");

        javax.swing.GroupLayout InventoryNavBarLayout = new javax.swing.GroupLayout(InventoryNavBar);
        InventoryNavBar.setLayout(InventoryNavBarLayout);
        InventoryNavBarLayout.setHorizontalGroup(
            InventoryNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        InventoryNavBarLayout.setVerticalGroup(
            InventoryNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenuBar.add(InventoryNavBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 176, 40));

        HospitalInfoNavBar.setBackground(new java.awt.Color(234, 242, 248));
        HospitalInfoNavBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        HospitalInfoNavBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HospitalInfoNavBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HospitalInfoNavBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HospitalInfoNavBarMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(26, 82, 118));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Hospital Informations");

        javax.swing.GroupLayout HospitalInfoNavBarLayout = new javax.swing.GroupLayout(HospitalInfoNavBar);
        HospitalInfoNavBar.setLayout(HospitalInfoNavBarLayout);
        HospitalInfoNavBarLayout.setHorizontalGroup(
            HospitalInfoNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HospitalInfoNavBarLayout.setVerticalGroup(
            HospitalInfoNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HospitalInfoNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenuBar.add(HospitalInfoNavBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 176, 40));

        HealthCareNavBar.setBackground(new java.awt.Color(234, 242, 248));
        HealthCareNavBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        HealthCareNavBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HealthCareNavBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HealthCareNavBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HealthCareNavBarMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(26, 82, 118));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("HealthCare Programs");

        javax.swing.GroupLayout HealthCareNavBarLayout = new javax.swing.GroupLayout(HealthCareNavBar);
        HealthCareNavBar.setLayout(HealthCareNavBarLayout);
        HealthCareNavBarLayout.setHorizontalGroup(
            HealthCareNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        HealthCareNavBarLayout.setVerticalGroup(
            HealthCareNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HealthCareNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenuBar.add(HealthCareNavBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 176, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        pnlMenuBar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        UserManagementNavBar.setBackground(new java.awt.Color(234, 242, 248));
        UserManagementNavBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        UserManagementNavBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserManagementNavBarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UserManagementNavBarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserManagementNavBarMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(26, 82, 118));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("User Management");

        javax.swing.GroupLayout UserManagementNavBarLayout = new javax.swing.GroupLayout(UserManagementNavBar);
        UserManagementNavBar.setLayout(UserManagementNavBarLayout);
        UserManagementNavBarLayout.setHorizontalGroup(
            UserManagementNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UserManagementNavBarLayout.setVerticalGroup(
            UserManagementNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserManagementNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenuBar.add(UserManagementNavBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 176, 40));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin.png"))); // NOI18N
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });
        pnlMenuBar.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 70, 50));

        lblAdminName2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAdminName2.setForeground(new java.awt.Color(26, 82, 118));
        lblAdminName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdminName2.setText("Admin Name");
        pnlMenuBar.add(lblAdminName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 170, -1));

        ShowSpace.setBackground(new java.awt.Color(255, 255, 255));

        HospitalInfoPanel.setBackground(new java.awt.Color(235, 245, 251));

        updateInformationPanel.setBackground(new java.awt.Color(235, 245, 251));
        updateInformationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(209, 242, 235));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUpdateInformation.setBackground(new java.awt.Color(255, 204, 204));
        btnUpdateInformation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateInformation.setText("Update Information");
        btnUpdateInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInformationActionPerformed(evt);
            }
        });
        jPanel10.add(btnUpdateInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 54, 160, 31));

        btnSaveInformation.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveInformation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveInformation.setText("Save");
        btnSaveInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveInformationActionPerformed(evt);
            }
        });
        jPanel10.add(btnSaveInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 54, 82, 31));

        lblSuccessInformation.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessInformation.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessInformation.setText("Updated successfully!");
        jPanel10.add(lblSuccessInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 160, -1));

        lblTipsInformation1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblTipsInformation1.setForeground(new java.awt.Color(102, 102, 255));
        lblTipsInformation1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tips2.png"))); // NOI18N
        lblTipsInformation1.setText("Hint: Double-click a cell in the table to edit the contents.");
        jPanel10.add(lblTipsInformation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, -1));

        updateInformationPanel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 55, 352, 150));

        jPanel46.setBackground(new java.awt.Color(209, 242, 235));

        btnAddInformation.setBackground(new java.awt.Color(204, 255, 204));
        btnAddInformation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddInformation.setText("Add Information");
        btnAddInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInformationActionPerformed(evt);
            }
        });

        btnDeleteInformation.setBackground(new java.awt.Color(255, 204, 204));
        btnDeleteInformation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteInformation.setText("Delete Information");
        btnDeleteInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteInformationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeleteInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(btnAddInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        updateInformationPanel.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 55, -1, 150));

        lblTipsInformation2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblTipsInformation2.setForeground(new java.awt.Color(102, 102, 255));
        lblTipsInformation2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipsInformation2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tips2.png"))); // NOI18N
        lblTipsInformation2.setText("Hint: You can adjust the size of columns in the table to be able to fully read the longer content in the table.");
        updateInformationPanel.add(lblTipsInformation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 13, 608, -1));

        addInfomationPanel.setBackground(new java.awt.Color(235, 245, 251));

        btnReturnHospitalInformation.setBackground(new java.awt.Color(102, 153, 255));
        btnReturnHospitalInformation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReturnHospitalInformation.setText("Return");
        btnReturnHospitalInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnHospitalInformationActionPerformed(evt);
            }
        });

        areaInformationDetails.setColumns(20);
        areaInformationDetails.setRows(5);
        jScrollPane12.setViewportView(areaInformationDetails);

        cboInformationType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General Information", "Accident & Emergency", "Visiting Hours", "Operating Hours", "Safety Measures" }));

        jLabel382.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel382.setText(":");

        jLabel381.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel381.setText("Information Type");

        jPanel49.setBackground(new java.awt.Color(235, 245, 251));
        jPanel49.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Counter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel380.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel380.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel380.setText("Total Info Added:");

        lblCounterInformation.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCounterInformation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCounterInformation.setText("0");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel380, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCounterInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel380)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCounterInformation)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jLabel385.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel385.setForeground(new java.awt.Color(51, 51, 255));
        jLabel385.setText("Kindly Reminder: Please do not press ENTER when typing.");

        btnAddInfo.setBackground(new java.awt.Color(204, 255, 204));
        btnAddInfo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddInfo.setText("Add");
        btnAddInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddInfoActionPerformed(evt);
            }
        });

        jLabel384.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel384.setText(":");

        jLabel383.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel383.setText("Details");

        javax.swing.GroupLayout addInfomationPanelLayout = new javax.swing.GroupLayout(addInfomationPanel);
        addInfomationPanel.setLayout(addInfomationPanelLayout);
        addInfomationPanelLayout.setHorizontalGroup(
            addInfomationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addInfomationPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(addInfomationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addInfomationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel381, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel382)
                        .addGap(18, 18, 18)
                        .addComponent(cboInformationType, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addInfomationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel383, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel384))
                    .addComponent(jScrollPane12)
                    .addComponent(jLabel385, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(addInfomationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturnHospitalInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        addInfomationPanelLayout.setVerticalGroup(
            addInfomationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addInfomationPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReturnHospitalInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addInfomationPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addInfomationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel381)
                    .addComponent(jLabel382)
                    .addComponent(cboInformationType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addInfomationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel383)
                    .addComponent(jLabel384))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel385)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        deleteInformationPanel.setBackground(new java.awt.Color(235, 245, 251));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Hsopital Information ID");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setText(":");

        cboInformationID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chbDeleteInformation1.setBackground(new java.awt.Color(235, 245, 251));
        chbDeleteInformation1.setText("   Once deleted, the data cannot be recovered.");
        chbDeleteInformation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDeleteInformation1ActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 255));
        jLabel55.setText("Reminder from Data Security Center (DSC):");

        chbDeleteInformation2.setBackground(new java.awt.Color(235, 245, 251));
        chbDeleteInformation2.setText("   Clear, understood and agreed.");

        btnDeleteInformation2.setBackground(new java.awt.Color(255, 204, 204));
        btnDeleteInformation2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteInformation2.setText("Delete");
        btnDeleteInformation2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteInformation2ActionPerformed(evt);
            }
        });

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DSClogo.png"))); // NOI18N

        btnReturnInformation.setBackground(new java.awt.Color(102, 153, 255));
        btnReturnInformation.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReturnInformation.setText("Return");
        btnReturnInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnInformationActionPerformed(evt);
            }
        });

        lblSuccessDeleteInformation.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessDeleteInformation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessDeleteInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessDeleteInformation.setText("Successfully deleted.");

        javax.swing.GroupLayout deleteInformationPanelLayout = new javax.swing.GroupLayout(deleteInformationPanel);
        deleteInformationPanel.setLayout(deleteInformationPanelLayout);
        deleteInformationPanelLayout.setHorizontalGroup(
            deleteInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteInformationPanelLayout.createSequentialGroup()
                .addGroup(deleteInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteInformationPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(deleteInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbDeleteInformation1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbDeleteInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(deleteInformationPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btnDeleteInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnReturnInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(deleteInformationPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(cboInformationID, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(deleteInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteInformationPanelLayout.createSequentialGroup()
                        .addComponent(lblSuccessDeleteInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(26, 26, 26))
        );
        deleteInformationPanelLayout.setVerticalGroup(
            deleteInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteInformationPanelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(deleteInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSuccessDeleteInformation))
                    .addGroup(deleteInformationPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(deleteInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboInformationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54)
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55)
                        .addGap(6, 6, 6)
                        .addComponent(chbDeleteInformation1)
                        .addGap(6, 6, 6)
                        .addComponent(chbDeleteInformation2)
                        .addGap(27, 27, 27)
                        .addGroup(deleteInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDeleteInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReturnInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout showSpace1Layout = new javax.swing.GroupLayout(showSpace1);
        showSpace1.setLayout(showSpace1Layout);
        showSpace1Layout.setHorizontalGroup(
            showSpace1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deleteInformationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpace1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addInfomationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpace1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(updateInformationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        showSpace1Layout.setVerticalGroup(
            showSpace1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deleteInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpace1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addInfomationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpace1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(updateInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel45.setBackground(new java.awt.Color(52, 152, 219));

        jLabel378.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel378.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel378.setText("Hospital Information");

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel378, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel378)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tblHospitalInformation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHospitalInformation.setRowHeight(30);
        tblHospitalInformation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHospitalInformationMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHospitalInformation);

        javax.swing.GroupLayout HospitalInfoPanelLayout = new javax.swing.GroupLayout(HospitalInfoPanel);
        HospitalInfoPanel.setLayout(HospitalInfoPanelLayout);
        HospitalInfoPanelLayout.setHorizontalGroup(
            HospitalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(HospitalInfoPanelLayout.createSequentialGroup()
                .addGroup(HospitalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HospitalInfoPanelLayout.createSequentialGroup()
                        .addComponent(showSpace1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(HospitalInfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        HospitalInfoPanelLayout.setVerticalGroup(
            HospitalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HospitalInfoPanelLayout.createSequentialGroup()
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showSpace1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        HealthCarePanel.setBackground(new java.awt.Color(235, 245, 251));

        jPanel11.setBackground(new java.awt.Color(52, 152, 219));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("HealthCare Programs");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        deleteProgramPanel.setBackground(new java.awt.Color(235, 245, 251));

        tblHealthcareProgramsDeletePanel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHealthcareProgramsDeletePanel.setRowHeight(30);
        tblHealthcareProgramsDeletePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHealthcareProgramsDeletePanelMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHealthcareProgramsDeletePanel);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setText("Program ID");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel57.setText(":");

        cboProgramIDDeletePanel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        chbDeleteProgram1.setBackground(new java.awt.Color(235, 245, 251));
        chbDeleteProgram1.setText("   Once deleted, the data cannot be recovered.");
        chbDeleteProgram1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDeleteProgram1ActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 255));
        jLabel58.setText("Reminder from Data Security Center (DSC):");

        chbDeleteProgram2.setBackground(new java.awt.Color(235, 245, 251));
        chbDeleteProgram2.setText("   Clear, understood and agreed.");

        btnDeleteProgram2.setBackground(new java.awt.Color(255, 204, 204));
        btnDeleteProgram2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteProgram2.setText("Delete");
        btnDeleteProgram2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProgram2ActionPerformed(evt);
            }
        });

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DSClogo.png"))); // NOI18N

        btnReturnDeleteProgram.setBackground(new java.awt.Color(102, 153, 255));
        btnReturnDeleteProgram.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReturnDeleteProgram.setText("Return");
        btnReturnDeleteProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnDeleteProgramActionPerformed(evt);
            }
        });

        lblSuccessDeleteProgram.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessDeleteProgram.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessDeleteProgram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessDeleteProgram.setText("Successfully deleted.");

        javax.swing.GroupLayout deleteProgramPanelLayout = new javax.swing.GroupLayout(deleteProgramPanel);
        deleteProgramPanel.setLayout(deleteProgramPanelLayout);
        deleteProgramPanelLayout.setHorizontalGroup(
            deleteProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteProgramPanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteProgramPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(deleteProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteProgramPanelLayout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel57)
                        .addGap(28, 28, 28)
                        .addComponent(cboProgramIDDeletePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbDeleteProgram1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbDeleteProgram2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(deleteProgramPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnDeleteProgram2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnReturnDeleteProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(deleteProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteProgramPanelLayout.createSequentialGroup()
                        .addComponent(lblSuccessDeleteProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(28, 28, 28))
        );
        deleteProgramPanelLayout.setVerticalGroup(
            deleteProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteProgramPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(deleteProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteProgramPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSuccessDeleteProgram))
                    .addGroup(deleteProgramPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(deleteProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addGroup(deleteProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboProgramIDDeletePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel57)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel58)
                        .addGap(6, 6, 6)
                        .addComponent(chbDeleteProgram1)
                        .addGap(6, 6, 6)
                        .addComponent(chbDeleteProgram2)
                        .addGap(27, 27, 27)
                        .addGroup(deleteProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDeleteProgram2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReturnDeleteProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        manageParticipantsPanel.setBackground(new java.awt.Color(235, 245, 251));

        tblParticipants.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblParticipants.setRowHeight(30);
        tblParticipants.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblParticipantsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblParticipants);

        jPanel36.setBackground(new java.awt.Color(235, 245, 251));
        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Program Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel225.setText("Program ID");

        jLabel233.setText("Max Participants");

        jLabel246.setText("Current Participants");

        jLabel278.setText(":");

        jLabel282.setText(":");

        jLabel283.setText(":");

        lblProgramIDView.setText("N/A");

        lblMaxParticipantsView.setText("N/A");

        lblCurrentParticipants.setText("N/A");

        jLabel226.setText("Program Title");

        jLabel279.setText(":");

        lblProgramTitleView.setText("N/A");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel246, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel233, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel282)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(lblMaxParticipantsView, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel283)
                                .addGap(18, 18, 18)
                                .addComponent(lblCurrentParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(101, 101, 101))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel226, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jLabel225, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel278)
                                .addGap(18, 18, 18)
                                .addComponent(lblProgramIDView, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel279)
                                .addGap(18, 18, 18)
                                .addComponent(lblProgramTitleView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel225)
                    .addComponent(jLabel278)
                    .addComponent(lblProgramIDView))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel226)
                    .addComponent(jLabel279)
                    .addComponent(lblProgramTitleView))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel282)
                    .addComponent(lblMaxParticipantsView)
                    .addComponent(jLabel233))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel246)
                    .addComponent(jLabel283)
                    .addComponent(lblCurrentParticipants))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        cboProgramIDViewParticipants.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProgramIDViewParticipants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProgramIDViewParticipantsActionPerformed(evt);
            }
        });

        jLabel37.setText("Program ID");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Administrators can view participant information through the program ID.");

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Return");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout manageParticipantsPanelLayout = new javax.swing.GroupLayout(manageParticipantsPanel);
        manageParticipantsPanel.setLayout(manageParticipantsPanelLayout);
        manageParticipantsPanelLayout.setHorizontalGroup(
            manageParticipantsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageParticipantsPanelLayout.createSequentialGroup()
                .addGroup(manageParticipantsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manageParticipantsPanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(cboProgramIDViewParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(manageParticipantsPanelLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(manageParticipantsPanelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageParticipantsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manageParticipantsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );
        manageParticipantsPanelLayout.setVerticalGroup(
            manageParticipantsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageParticipantsPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addGroup(manageParticipantsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manageParticipantsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(manageParticipantsPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(cboProgramIDViewParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        programMainPanel.setBackground(new java.awt.Color(235, 245, 251));

        tblHealthcarePrograms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHealthcarePrograms.setRowHeight(30);
        tblHealthcarePrograms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHealthcareProgramsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHealthcarePrograms);

        btnUpdateProgram.setBackground(new java.awt.Color(255, 255, 204));
        btnUpdateProgram.setText("Update Program");
        btnUpdateProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProgramActionPerformed(evt);
            }
        });

        btnAddProgram.setBackground(new java.awt.Color(204, 255, 204));
        btnAddProgram.setText("Add Program");
        btnAddProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProgramActionPerformed(evt);
            }
        });

        btnDeleteProgram.setBackground(new java.awt.Color(255, 204, 204));
        btnDeleteProgram.setText("Delete Program");
        btnDeleteProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProgramActionPerformed(evt);
            }
        });

        btnViewParticipants.setBackground(new java.awt.Color(204, 255, 255));
        btnViewParticipants.setText("View Program Participants");
        btnViewParticipants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewParticipantsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout programMainPanelLayout = new javax.swing.GroupLayout(programMainPanel);
        programMainPanel.setLayout(programMainPanelLayout);
        programMainPanelLayout.setHorizontalGroup(
            programMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(programMainPanelLayout.createSequentialGroup()
                .addGroup(programMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, programMainPanelLayout.createSequentialGroup()
                        .addContainerGap(10, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(programMainPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnUpdateProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnDeleteProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addContainerGap())
            .addGroup(programMainPanelLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(btnViewParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        programMainPanelLayout.setVerticalGroup(
            programMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(programMainPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(programMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnViewParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        updateProgramPanel.setBackground(new java.awt.Color(235, 245, 251));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Program ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText(":");

        txtProgramID.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Program Title");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText(":");

        txtProgramTitle.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Category");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText(":");

        cboCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Date");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText(":");

        spnDate.setModel(new javax.swing.SpinnerDateModel());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText(":");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Time");

        cboLocation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Location");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText(":");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Speaker");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText(":");

        jPanel2.setBackground(new java.awt.Color(235, 245, 251));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnUpdateProgram2.setBackground(new java.awt.Color(255, 204, 204));
        btnUpdateProgram2.setText("Update");
        btnUpdateProgram2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProgram2ActionPerformed(evt);
            }
        });

        btnSaveUpdateProgram.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveUpdateProgram.setText("Save");
        btnSaveUpdateProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUpdateProgramActionPerformed(evt);
            }
        });

        btnReturnUpdateProgram2.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnUpdateProgram2.setText("Return");
        btnReturnUpdateProgram2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnUpdateProgram2ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Program ID");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText(":");

        cboProgramID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProgramID.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                cboProgramIDComponentAdded(evt);
            }
        });
        cboProgramID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProgramIDActionPerformed(evt);
            }
        });

        lblSuccessUpdateProgram.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessUpdateProgram.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessUpdateProgram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessUpdateProgram.setText("Successfully updated.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(cboProgramID, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lblSuccessUpdateProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReturnUpdateProgram2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdateProgram2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSaveUpdateProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnUpdateProgram2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveUpdateProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReturnUpdateProgram2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(cboProgramID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSuccessUpdateProgram)
                .addGap(22, 22, 22))
        );

        txtSpeaker.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        cboDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        cboDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDurationActionPerformed(evt);
            }
        });

        lblStartEndTime.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblStartEndTime.setText("N/A");

        cboStartTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        cboStartTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboStartTimeActionPerformed(evt);
            }
        });

        txtMaxParticipants.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText(":");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Max Participants");

        javax.swing.GroupLayout updateProgramPanelLayout = new javax.swing.GroupLayout(updateProgramPanel);
        updateProgramPanel.setLayout(updateProgramPanelLayout);
        updateProgramPanelLayout.setHorizontalGroup(
            updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateProgramPanelLayout.createSequentialGroup()
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateProgramPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, updateProgramPanelLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtProgramID, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtProgramTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                        .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel16)
                                                .addGap(18, 18, 18)
                                                .addComponent(spnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel17)
                                                .addGap(18, 18, 18)
                                                .addComponent(cboStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cboDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(lblStartEndTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, updateProgramPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel24)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtSpeaker))
                                        .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel22)
                                            .addGap(18, 18, 18)
                                            .addComponent(cboLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(updateProgramPanelLayout.createSequentialGroup()
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel27)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtMaxParticipants)))))))
                    .addGroup(updateProgramPanelLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        updateProgramPanelLayout.setVerticalGroup(
            updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateProgramPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtProgramID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(txtProgramTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(spnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(cboDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStartEndTime)
                    .addComponent(cboStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(cboLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(txtSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27)
                    .addComponent(txtMaxParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        addProgramPanel.setBackground(new java.awt.Color(235, 245, 251));

        cboCategoryAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Date");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Max Participants");

        cboStartTimeAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        cboStartTimeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboStartTimeAddActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText(":");

        spnDateAdd.setModel(new javax.swing.SpinnerDateModel());

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText(":");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Time");

        cboLocationAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Location");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText(":");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Speaker");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText(":");

        txtSpeakerAdd.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        txtMaxParticipantsAdd.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        cboDurationAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        cboDurationAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDurationAddActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText(":");

        lblStartEndTimeAdd.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblStartEndTimeAdd.setText("07:00 - 07:30");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("Program Title");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText(":");

        txtProgramTitleAdd.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("Category");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText(":");

        btnReturnAddProgram.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnAddProgram.setText("Return");
        btnReturnAddProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnAddProgramActionPerformed(evt);
            }
        });

        btnAddProgram2.setBackground(new java.awt.Color(204, 255, 204));
        btnAddProgram2.setText("Add program");
        btnAddProgram2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProgram2ActionPerformed(evt);
            }
        });

        lblSuccessAddProgram.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessAddProgram.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessAddProgram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessAddProgram.setText("Successfully added.");

        comfirmSlip.setBackground(new java.awt.Color(255, 255, 255));
        comfirmSlip.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Confirmation Slip", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel113.setText("Program Title");

        jLabel114.setText("Program ID");

        jLabel115.setText("Category");

        jLabel116.setText("Date");

        jLabel117.setText(":");

        jLabel118.setText(":");

        jLabel119.setText(":");

        jLabel120.setText(":");

        jLabel122.setText("Time");

        lblProgramID.setText("N/A");

        lblProgramTitle.setText("N/A");

        lblCategory.setText("N/A");

        jLabel121.setText(":");

        jLabel123.setText("Location");

        jLabel124.setText(":");

        lblDate.setText("N/A");

        lblTime.setText("N/A");

        lblLocation.setText("N/A");

        jLabel125.setText("Speaker");

        jLabel126.setText(":");

        lblSpeaker.setText("N/A");

        jLabel127.setText("Max Participants");

        jLabel128.setText(":");

        lblMaxParticipants.setText("N/A");

        javax.swing.GroupLayout comfirmSlipLayout = new javax.swing.GroupLayout(comfirmSlip);
        comfirmSlip.setLayout(comfirmSlipLayout);
        comfirmSlipLayout.setHorizontalGroup(
            comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comfirmSlipLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(comfirmSlipLayout.createSequentialGroup()
                        .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel118)
                            .addComponent(jLabel117)
                            .addComponent(jLabel119))
                        .addGap(18, 18, 18)
                        .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProgramTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(comfirmSlipLayout.createSequentialGroup()
                                .addComponent(lblProgramID, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel127, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel128)
                                .addGap(18, 18, 18)
                                .addComponent(lblMaxParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comfirmSlipLayout.createSequentialGroup()
                        .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel120)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel121)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(comfirmSlipLayout.createSequentialGroup()
                                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel124)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(comfirmSlipLayout.createSequentialGroup()
                                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel126)
                                .addGap(18, 18, 18)
                                .addComponent(lblSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        comfirmSlipLayout.setVerticalGroup(
            comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comfirmSlipLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaxParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel127)
                        .addComponent(jLabel128))
                    .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel114)
                        .addComponent(jLabel117)
                        .addComponent(lblProgramID)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113)
                    .addComponent(jLabel118)
                    .addComponent(lblProgramTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel125)
                        .addComponent(jLabel126))
                    .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel115)
                        .addComponent(jLabel119)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(comfirmSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel116)
                    .addComponent(jLabel120)
                    .addComponent(jLabel122)
                    .addComponent(jLabel121)
                    .addComponent(jLabel123)
                    .addComponent(jLabel124)
                    .addComponent(lblLocation)
                    .addComponent(lblDate)
                    .addComponent(lblTime))
                .addContainerGap())
        );

        javax.swing.GroupLayout addProgramPanelLayout = new javax.swing.GroupLayout(addProgramPanel);
        addProgramPanel.setLayout(addProgramPanelLayout);
        addProgramPanelLayout.setHorizontalGroup(
            addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addProgramPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comfirmSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addProgramPanelLayout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41)
                        .addGap(18, 18, 18)
                        .addComponent(txtProgramTitleAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addProgramPanelLayout.createSequentialGroup()
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addProgramPanelLayout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel45))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addProgramPanelLayout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)))
                        .addGap(18, 18, 18)
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboCategoryAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnDateAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addProgramPanelLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addProgramPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel34))
                        .addGap(18, 18, 18)
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSpeakerAdd)
                            .addComponent(cboLocationAdd, 0, 135, Short.MAX_VALUE)))
                    .addGroup(addProgramPanelLayout.createSequentialGroup()
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addProgramPanelLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(cboStartTimeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboDurationAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addProgramPanelLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)
                                .addGap(18, 18, 18)
                                .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAddProgram2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaxParticipantsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblStartEndTimeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSuccessAddProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addProgramPanelLayout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(btnReturnAddProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        addProgramPanelLayout.setVerticalGroup(
            addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addProgramPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(txtProgramTitleAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addProgramPanelLayout.createSequentialGroup()
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(cboCategoryAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel30)
                            .addComponent(spnDateAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addProgramPanelLayout.createSequentialGroup()
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(cboLocationAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txtSpeakerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))))
                .addGap(18, 18, 18)
                .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31)
                    .addComponent(cboDurationAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStartEndTimeAdd)
                    .addComponent(cboStartTimeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(jLabel39)
                        .addComponent(txtMaxParticipantsAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSuccessAddProgram))
                .addGap(18, 18, 18)
                .addGroup(addProgramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReturnAddProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddProgram2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(comfirmSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout showSpaceLayout = new javax.swing.GroupLayout(showSpace);
        showSpace.setLayout(showSpaceLayout);
        showSpaceLayout.setHorizontalGroup(
            showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateProgramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addProgramPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(deleteProgramPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(manageParticipantsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(programMainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        showSpaceLayout.setVerticalGroup(
            showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateProgramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addProgramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(deleteProgramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(manageParticipantsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(programMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout HealthCarePanelLayout = new javax.swing.GroupLayout(HealthCarePanel);
        HealthCarePanel.setLayout(HealthCarePanelLayout);
        HealthCarePanelLayout.setHorizontalGroup(
            HealthCarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HealthCarePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        HealthCarePanelLayout.setVerticalGroup(
            HealthCarePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HealthCarePanelLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ShowSpaceLayout = new javax.swing.GroupLayout(ShowSpace);
        ShowSpace.setLayout(ShowSpaceLayout);
        ShowSpaceLayout.setHorizontalGroup(
            ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HealthCarePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(HospitalInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShowSpaceLayout.setVerticalGroup(
            ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HealthCarePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(HospitalInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShowSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ShowSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        System.out.println("\nLogout Successful.");
        Account Admin = new Admin();
        Admin.logout();
        this.hide();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void InventoryNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryNavBarMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        adminFrame.setVisible(true);
        adminFrame.showPanel("Inventory Management");
    }//GEN-LAST:event_InventoryNavBarMouseClicked
    
    private void ProfileNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileNavBarMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        adminFrame.setVisible(true);
        adminFrame.showPanel("Profile");
    }//GEN-LAST:event_ProfileNavBarMouseClicked

    private void AssistanceNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssistanceNavBarMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        adminFrame.setVisible(true);
        adminFrame.showPanel("Assistance");
    }//GEN-LAST:event_AssistanceNavBarMouseClicked

    private void RegisterNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterNavBarMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        adminFrame.setVisible(true);
        adminFrame.showPanel("Register User");
    }//GEN-LAST:event_RegisterNavBarMouseClicked

    private void UserManagementNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserManagementNavBarMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        adminFrame.setVisible(true);
        adminFrame.showPanel("User Management");
    }//GEN-LAST:event_UserManagementNavBarMouseClicked

    private void HospitalInfoNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HospitalInfoNavBarMouseClicked
        // TODO add your handling code here:
        showHospitalInformationPanel();
    }//GEN-LAST:event_HospitalInfoNavBarMouseClicked

    private void HealthCareNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HealthCareNavBarMouseClicked
        // TODO add your handling code here:
        showHealthCareProgramsPanel();
    }//GEN-LAST:event_HealthCareNavBarMouseClicked

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        adminFrame.setVisible(true);
        adminFrame.showPanel("Admin Welcome");
    }//GEN-LAST:event_jLabel43MouseClicked
  
    private void tblHospitalInformationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHospitalInformationMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHospitalInformationMouseClicked

    private void btnUpdateInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInformationActionPerformed
        // TODO add your handling code here:
        tblHospitalInformation.setEnabled(true);
        btnSaveInformation.setEnabled(true);
        
        lblTipsInformation1.setVisible(true);
        lblTipsInformation2.setVisible(true);
        lblSuccessInformation.setVisible(false);
    }//GEN-LAST:event_btnUpdateInformationActionPerformed

    private void btnSaveInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveInformationActionPerformed
        // TODO add your handling code here:
        boolean stop = false;
        
        ArrayList<String[]> dataList = File_Control.readFile("hospital_information.txt", false);
        
        // get the table model
        DefaultTableModel tableModel = (DefaultTableModel)tblHospitalInformation.getModel();
        
        int count = 0;
        for (String[] list : dataList) {
            String accStatus = list[list.length - 1];
            
            if (accStatus.equals("1")) {
                count += 1;
            } 
        }
        
        for (int row = 0; row < count; row++) {
            String infoID = tableModel.getValueAt(row, 0).toString();
            String details = tableModel.getValueAt(row, 2).toString();

            if (details.isEmpty()) {
                stop = true;
                break;
            } 
            else {
                for (String[] list : dataList) {
                    if (infoID.equals(list[0])) {
                        list[2] = details;
                    }
                }
            }
        }
        
        if (stop == true) {
            warningDialog("Empty", null, lblSuccessInformation);
        }
        
        else {
            File_Control.writeFile("hospital_information.txt", dataList);
            System.out.println("Successfully Updated");
            
            lblSuccessInformation.setVisible(true);
            lblTipsInformation1.setVisible(false);
            lblTipsInformation2.setVisible(false);
            btnSaveInformation.setEnabled(false);
            hospitalInformationTable();
            tblHospitalInformation.setEnabled(false);
        }
    }//GEN-LAST:event_btnSaveInformationActionPerformed

    private void btnAddInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInformationActionPerformed
        // TODO add your handling code here:
        addInfomationPanel.setVisible(true);
        updateInformationPanel.setVisible(false);
        deleteInformationPanel.setVisible(false);
        
        lblSuccessInformation.setVisible(false);
        modelComboBoxInformationType();
        
        areaInformationDetails.setLineWrap(true);
        areaInformationDetails.setWrapStyleWord(true);
        
    }//GEN-LAST:event_btnAddInformationActionPerformed

    private void btnDeleteInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteInformationActionPerformed
        // TODO add your handling code here:
        deleteInformationPanel.setVisible(true);
        addInfomationPanel.setVisible(false);
        updateInformationPanel.setVisible(false);
        
        lblSuccessDeleteInformation.setVisible(false);
        
        modelComboBoxInformationID();
    }//GEN-LAST:event_btnDeleteInformationActionPerformed
    
    int counter1;  
    private void btnReturnHospitalInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnHospitalInformationActionPerformed
        // TODO add your handling code here:
        // every time click done, it will reset counter1 to 0
        counter1 = 0;
        lblCounterInformation.setText(String.valueOf(counter1));

        hospitalInformationTable();
        updateInformationPanel.setVisible(true);
        addInfomationPanel.setVisible(false);
        deleteInformationPanel.setVisible(false);

        textClear();
    }//GEN-LAST:event_btnReturnHospitalInformationActionPerformed

    private void btnAddInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddInfoActionPerformed
        // TODO add your handling code here:
        lblCounterInformation.setText(String.valueOf(counter1));

        String informationType = cboInformationType.getSelectedItem().toString();
        String details = areaInformationDetails.getText();


        if (details.isEmpty()) {
            warningDialog("Empty", null, null);
        }

        else {
            // auto generate hospital information ID
            String informationID = Utility_Methods.autoGenerateID("hospital_information.txt", "HI");

            String line = informationID + ";" + informationType + ";" + details + ";1";
            File_Control.addData("hospital_information.txt", line);

            counter1++;
            lblCounterInformation.setText(String.valueOf(counter1));
            hospitalInformationTable();
            modelComboBoxInformationID();
            System.out.println("Information succuessful added.");
            textClear();
        }
    }//GEN-LAST:event_btnAddInfoActionPerformed

    private void chbDeleteInformation1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDeleteInformation1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbDeleteInformation1ActionPerformed

    private void btnDeleteInformation2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteInformation2ActionPerformed
        // TODO add your handling code here:
        ArrayList<String[]> dataList = File_Control.readFile("hospital_information.txt", false);
        boolean status = false;

        String deleteItemID = cboInformationID.getSelectedItem().toString();

        if (chbDeleteInformation1.isSelected() && chbDeleteInformation2.isSelected()) {
            for (String[] list : dataList) {
                String infoID = list[0];

                if (infoID.equals(deleteItemID)) {
                    list[list.length - 1] = "0";
                    status = true;
                    break;
                }
            }

            if (status == true) {
                File_Control.writeFile("hospital_information.txt", dataList);
                System.out.println(deleteItemID + "'s record has been successfully deleted.");
                chbDeleteInformation1.setSelected(false);
                chbDeleteInformation2.setSelected(false);

                lblSuccessDeleteInformation.setVisible(true);

                // everytime click delete will model the combobox
                // to get the latest ID
                modelComboBoxInformationID();

                // everytime click delete also need to renew table
                hospitalInformationTable();
            }
        }
        
        else {
            warningDialog("deleteStatement", null, lblSuccessDeleteInformation);
        }
    }//GEN-LAST:event_btnDeleteInformation2ActionPerformed

    private void btnReturnInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnInformationActionPerformed
        // TODO add your handling code here:
        updateInformationPanel.setVisible(true);
        addInfomationPanel.setVisible(false);
        deleteInformationPanel.setVisible(false);
        
        chbDeleteInformation1.setSelected(false);
        chbDeleteInformation2.setSelected(false);

        lblSuccessDeleteInformation.setVisible(false);
    }//GEN-LAST:event_btnReturnInformationActionPerformed

    private void tblHealthcareProgramsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHealthcareProgramsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHealthcareProgramsMouseClicked

    private void btnUpdateProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProgramActionPerformed
        // TODO add your handling code here:
        updateProgramPanel.setVisible(true);
        programMainPanel.setVisible(false);
        deleteProgramPanel.setVisible(false);
        manageParticipantsPanel.setVisible(false);
        addProgramPanel.setVisible(false);
        
        disenable();
        setSpinnerDate();
        modelComboBoxProgramID();
        
        btnUpdateProgram2.setEnabled(false);
        btnSaveUpdateProgram.setEnabled(false);
        
        cboProgramID.setEnabled(true);
        lblSuccessUpdateProgram.setVisible(false);
    }//GEN-LAST:event_btnUpdateProgramActionPerformed

    private void cboProgramIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProgramIDActionPerformed
        // TODO add your handling code here:
        modelComboBoxDuration();
        modelComboBoxLocation();
        modelComboBoxCategory();
        modelComboBoxStartTime();
        
        String programID = cboProgramID.getSelectedItem().toString();
        
        Healthcare_Program program = new Healthcare_Program(programID);
        
        txtProgramID.setText(program.getProgramID());
        txtProgramTitle.setText(program.getProgramTitle());
        cboCategory.setSelectedItem(program.getCategory());
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(program.getDate()); 
            spnDate.setValue(date);
        }
        catch (ParseException e) {
            System.out.println("Parse Exception Occur.");
        }
        
        cboStartTime.setSelectedItem(program.getStartTime());
        cboDuration.setSelectedItem(program.getDuration());
        lblStartEndTime.setText(program.getTime());
        cboLocation.setSelectedItem(program.getLocation());
        txtSpeaker.setText(program.getSpeaker());
        txtMaxParticipants.setText(Integer.toString(program.getMaxParticipants()));
        
        btnUpdateProgram2.setEnabled(true);
    }//GEN-LAST:event_cboProgramIDActionPerformed

    private void cboProgramIDComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_cboProgramIDComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProgramIDComponentAdded

    private void btnUpdateProgram2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProgram2ActionPerformed
        // TODO add your handling code here:
        cboProgramID.setEnabled(false);
        btnSaveUpdateProgram.setEnabled(true);
        btnUpdateProgram2.setEnabled(false);
        
        EnableUpdateProgram();
    }//GEN-LAST:event_btnUpdateProgram2ActionPerformed

    private void btnSaveUpdateProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUpdateProgramActionPerformed
        // TODO add your handling code here:
        String programID = txtProgramID.getText();
        String programTitle = txtProgramTitle.getText();
        String category = cboCategory.getSelectedItem().toString();
        Date date = (Date) spnDate.getValue();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatedDate = sdf.format(date);

        String startTime = cboStartTime.getSelectedItem().toString();
        String duration = cboDuration.getSelectedItem().toString();
        String startEndTime = lblStartEndTime.getText();
        String location = cboLocation.getSelectedItem().toString();
        String speaker = txtSpeaker.getText();
        String maxParticipantsStr = txtMaxParticipants.getText();
        
        
        boolean speakerValid = Validation.string(speaker);
        boolean maxParticipantsStrValid = Validation.integer(maxParticipantsStr);
        
        if (programTitle.isEmpty() || speaker.isEmpty() || maxParticipantsStr.isEmpty()) {
            warningDialog("Empty", null, lblSuccessUpdateProgram);
        }
        
        else if (speakerValid == false) {
            warningDialog("String", "SPEAKER", lblSuccessUpdateProgram);
        }
        
        else if (maxParticipantsStrValid == false) {
            warningDialog("Number", "MAX PARTICIPANTS", lblSuccessUpdateProgram);
        }
        
        else {
            int maxParticipants = Integer.parseInt(maxParticipantsStr);
            Healthcare_Program program = new Healthcare_Program(programID);

            program.updatePorgram(programTitle, category, formatedDate, startTime, duration,
                    startEndTime, location, speaker, maxParticipants);
            
            lblSuccessUpdateProgram.setVisible(true);
            btnUpdateProgram2.setEnabled(true);
            cboProgramID.setEnabled(true);
            btnSaveUpdateProgram.setEnabled(false);
            System.out.println(programID + " (" + programTitle + ")'s record has been successfully updated.");
            disenable();
            healthcareProgramTable();
        }        
    }//GEN-LAST:event_btnSaveUpdateProgramActionPerformed

    private void cboStartTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStartTimeActionPerformed
        // TODO add your handling code here:  
        // call this method and give an argument (page - update program page)
        realTimeUpdate_startEndTime("Update");
    }//GEN-LAST:event_cboStartTimeActionPerformed

    private void cboDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDurationActionPerformed
        // TODO add your handling code here:
        // call this method and give an argument (page - update program page)
        realTimeUpdate_startEndTime("Update");
    }//GEN-LAST:event_cboDurationActionPerformed

    private void btnReturnUpdateProgram2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnUpdateProgram2ActionPerformed
        // TODO add your handling code here:
        programMainPanel.setVisible(true);
        updateProgramPanel.setVisible(false);
        deleteProgramPanel.setVisible(false);
        manageParticipantsPanel.setVisible(false);
        addProgramPanel.setVisible(false);
        
        lblSuccessUpdateProgram.setVisible(false);
    }//GEN-LAST:event_btnReturnUpdateProgram2ActionPerformed

    private void btnViewParticipantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewParticipantsActionPerformed
        // TODO add your handling code here:
        manageParticipantsPanel.setVisible(true);
        programMainPanel.setVisible(false);
        updateProgramPanel.setVisible(false);
        deleteProgramPanel.setVisible(false);
        addProgramPanel.setVisible(false);
        
        tblParticipants.setEnabled(false);
        
        participantsTable();
        modelComboBoxProgramID();
        setDefaultText();
    }//GEN-LAST:event_btnViewParticipantsActionPerformed

    private void cboStartTimeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStartTimeAddActionPerformed
        // TODO add your handling code here:
        // call this method and give an argument (page - add program page)
        realTimeUpdate_startEndTime("Add");
    }//GEN-LAST:event_cboStartTimeAddActionPerformed

    private void cboDurationAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDurationAddActionPerformed
        // TODO add your handling code here:
        // call this method and give an argument (page - add program page)
        realTimeUpdate_startEndTime("Add");
    }//GEN-LAST:event_cboDurationAddActionPerformed

    private void btnAddProgram2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProgram2ActionPerformed
        // TODO add your handling code here:
        String programTitle = txtProgramTitleAdd.getText();
        String category = cboCategoryAdd.getSelectedItem().toString();
        Date date = (Date) spnDateAdd.getValue();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatedDate = sdf.format(date);

        String startTime = cboStartTimeAdd.getSelectedItem().toString();
        String duration = cboDurationAdd.getSelectedItem().toString();
        String startEndTime = lblStartEndTimeAdd.getText();
        String location = cboLocationAdd.getSelectedItem().toString();
        String speaker = txtSpeakerAdd.getText();
        String maxParticipantsStr = txtMaxParticipantsAdd.getText();
        
        
        boolean speakerValid = Validation.string(speaker);
        boolean maxParticipantsStrValid = Validation.integer(maxParticipantsStr);
        
        if (programTitle.isEmpty() || speaker.isEmpty() || maxParticipantsStr.isEmpty()) {
            warningDialog("Empty", null, lblSuccessAddProgram);
        }
        
        else if (speakerValid == false) {
            warningDialog("String", "SPEAKER", lblSuccessAddProgram);
        }
        
        else if (maxParticipantsStrValid == false) {
            warningDialog("Number", "MAX PARTICIPANTS", lblSuccessAddProgram);
        }
        
        else {
            int maxParticipants = Integer.parseInt(maxParticipantsStr);
            
            String newProgramID = Utility_Methods.autoGenerateID("healthcareProgramData.txt", "HP");
            
            Healthcare_Program newProgram = new Healthcare_Program();

            newProgram.addProgram(newProgramID, programTitle, category, formatedDate, startTime, duration,
                    startEndTime, location, speaker, maxParticipants);
            
            lblSuccessAddProgram.setVisible(true);
            
            System.out.println(newProgramID + " (" + programTitle + ") has been successfully added.");
            healthcareProgramTable();
            
            lblProgramID.setText(newProgram.getProgramID());
            lblProgramTitle.setText(newProgram.getProgramTitle());
            lblCategory.setText(newProgram.getCategory());
            lblMaxParticipants.setText(Integer.toString(newProgram.getMaxParticipants()));
            lblSpeaker.setText(newProgram.getSpeaker()); 
            lblDate.setText(newProgram.getDate());
            lblTime.setText(newProgram.getTime());
            lblLocation.setText(newProgram.getLocation());
            textClear();
        }
    }//GEN-LAST:event_btnAddProgram2ActionPerformed

    private void btnAddProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProgramActionPerformed
        // TODO add your handling code here:
        addProgramPanel.setVisible(true);
        programMainPanel.setVisible(false);
        updateProgramPanel.setVisible(false);
        deleteProgramPanel.setVisible(false);
        manageParticipantsPanel.setVisible(false);
        
        modelSpinnerDate();
        modelComboBoxStartTime();
        modelComboBoxCategory();
        modelComboBoxLocation();
        modelComboBoxDuration();
        
        lblSuccessAddProgram.setVisible(false);
        setDefaultText();
        textClear();
    }//GEN-LAST:event_btnAddProgramActionPerformed

    private void btnReturnAddProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnAddProgramActionPerformed
        // TODO add your handling code here:
        programMainPanel.setVisible(true);
        addProgramPanel.setVisible(false);
        updateProgramPanel.setVisible(false);
        deleteProgramPanel.setVisible(false);
        manageParticipantsPanel.setVisible(false);
        
        lblSuccessAddProgram.setVisible(false);
        setDefaultText();
        textClear();
    }//GEN-LAST:event_btnReturnAddProgramActionPerformed

    private void tblHealthcareProgramsDeletePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHealthcareProgramsDeletePanelMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblHealthcareProgramsDeletePanelMouseClicked

    private void btnDeleteProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProgramActionPerformed
        // TODO add your handling code here:
        deleteProgramPanel.setVisible(true);
        programMainPanel.setVisible(false);
        addProgramPanel.setVisible(false);
        updateProgramPanel.setVisible(false);
        manageParticipantsPanel.setVisible(false);
        
        modelComboBoxProgramID();
        healthcareProgramTable();
        
        tblHealthcareProgramsDeletePanel.setEnabled(false);
        lblSuccessDeleteProgram.setVisible(false);
    }//GEN-LAST:event_btnDeleteProgramActionPerformed

    private void chbDeleteProgram1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDeleteProgram1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbDeleteProgram1ActionPerformed

    private void btnDeleteProgram2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProgram2ActionPerformed
        // TODO add your handling code here:
        boolean status = false;

        String deleteProgramID = cboProgramIDDeletePanel.getSelectedItem().toString();

        if (chbDeleteProgram1.isSelected() && chbDeleteProgram2.isSelected()) {
            
            Healthcare_Program program = new Healthcare_Program();
            status = program.deleteProgram(deleteProgramID);

            if (status == true) {
                System.out.println(deleteProgramID + "'s record has been successfully deleted.");
                chbDeleteProgram1.setSelected(false);
                chbDeleteProgram2.setSelected(false);

                lblSuccessDeleteProgram.setVisible(true);

                // everytime click delete will model the combobox
                // to get the latest ID
                modelComboBoxProgramID();

                // everytime click delete also need to renew table
                healthcareProgramTable();
            }
        }
        else {
            JOptionPane.showMessageDialog(this,
                "Please agree to all statements before delete.",
                "Pacific Data Security Center (DSC)",
                JOptionPane.WARNING_MESSAGE);

            lblSuccessDeleteProgram.setVisible(false);
        }
    }//GEN-LAST:event_btnDeleteProgram2ActionPerformed

    private void btnReturnDeleteProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnDeleteProgramActionPerformed
        // TODO add your handling code here:
        programMainPanel.setVisible(true);
        deleteProgramPanel.setVisible(false);
        addProgramPanel.setVisible(false);
        updateProgramPanel.setVisible(false);
        manageParticipantsPanel.setVisible(false);
        
        chbDeleteProgram1.setSelected(false);
        chbDeleteProgram2.setSelected(false);

        lblSuccessDeleteProgram.setVisible(false);
    }//GEN-LAST:event_btnReturnDeleteProgramActionPerformed

    private void tblParticipantsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblParticipantsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblParticipantsMouseClicked

    private void cboProgramIDViewParticipantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProgramIDViewParticipantsActionPerformed
        // TODO add your handling code here:
        // clear current table elements
        modelParticipantsTable.setRowCount(0);
        
        String programID = cboProgramIDViewParticipants.getSelectedItem().toString();
        ArrayList<String[]> ticketDataList = File_Control.readFile("ticketData.txt", true);
        
        // append new elements
        for (String[] list : ticketDataList) {
            String id = list[1];
            if (programID.equals(id)) {
                modelParticipantsTable.addRow(list);
            }
        }
        
        // get max participants and current participants etc
        Healthcare_Program program = new Healthcare_Program(programID);
        lblProgramIDView.setText(program.getProgramID());
        lblProgramTitleView.setText(program.getProgramTitle());
        lblMaxParticipantsView.setText(Integer.toString(program.getMaxParticipants()));
        lblCurrentParticipants.setText(Integer.toString(program.getCurrentParticipants()));
    }//GEN-LAST:event_cboProgramIDViewParticipantsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        programMainPanel.setVisible(true);
        deleteProgramPanel.setVisible(false);
        addProgramPanel.setVisible(false);
        updateProgramPanel.setVisible(false);
        manageParticipantsPanel.setVisible(false);
        
        setDefaultText();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ProfileNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileNavBarMouseExited
        // TODO add your handling code here:
        ProfileNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_ProfileNavBarMouseExited

    private void AssistanceNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssistanceNavBarMouseExited
        // TODO add your handling code here:
        AssistanceNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_AssistanceNavBarMouseExited

    private void RegisterNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterNavBarMouseExited
        // TODO add your handling code here:
        RegisterNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_RegisterNavBarMouseExited

    private void UserManagementNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserManagementNavBarMouseExited
        // TODO add your handling code here:
        UserManagementNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_UserManagementNavBarMouseExited

    private void InventoryNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryNavBarMouseExited
        // TODO add your handling code here:
        InventoryNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_InventoryNavBarMouseExited

    private void HospitalInfoNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HospitalInfoNavBarMouseExited
        // TODO add your handling code here:
        HospitalInfoNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_HospitalInfoNavBarMouseExited

    private void HealthCareNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HealthCareNavBarMouseExited
        // TODO add your handling code here:
        HealthCareNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_HealthCareNavBarMouseExited

    private void ProfileNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileNavBarMouseEntered
        // TODO add your handling code here:
        ProfileNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_ProfileNavBarMouseEntered

    private void AssistanceNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssistanceNavBarMouseEntered
        // TODO add your handling code here:
        AssistanceNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_AssistanceNavBarMouseEntered

    private void RegisterNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterNavBarMouseEntered
        // TODO add your handling code here:
        RegisterNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_RegisterNavBarMouseEntered

    private void UserManagementNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserManagementNavBarMouseEntered
        // TODO add your handling code here:
        UserManagementNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_UserManagementNavBarMouseEntered

    private void InventoryNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryNavBarMouseEntered
        // TODO add your handling code here:
        InventoryNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_InventoryNavBarMouseEntered

    private void HospitalInfoNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HospitalInfoNavBarMouseEntered
        // TODO add your handling code here:
        HospitalInfoNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_HospitalInfoNavBarMouseEntered

    private void HealthCareNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HealthCareNavBarMouseEntered
        // TODO add your handling code here:
        HealthCareNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_HealthCareNavBarMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        // TODO add your handling code here:
        btnLogout.setBackground(new Color(242,215,213));
    }//GEN-LAST:event_btnLogoutMouseExited

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        // TODO add your handling code here:
        btnLogout.setBackground(new Color(235,180,175));
    }//GEN-LAST:event_btnLogoutMouseEntered
     
    public void disenable() {
        // hospital information section
        tblHospitalInformation.setEnabled(false);
        
        // healthcare program
        tblHealthcarePrograms.setEnabled(false);
        txtProgramID.setEnabled(false);
        txtProgramTitle.setEnabled(false);
        cboCategory.setEnabled(false);
        spnDate.setEnabled(false);
        cboStartTime.setEnabled(false);
        cboDuration.setEnabled(false);
        cboLocation.setEnabled(false);
        txtSpeaker.setEnabled(false);
        txtMaxParticipants.setEnabled(false);
    }
    
    public void EnableUpdateProgram() {
        txtProgramTitle.setEnabled(true);
        cboCategory.setEnabled(true);
        spnDate.setEnabled(true);
        cboStartTime.setEnabled(true);
        cboDuration.setEnabled(true);
        cboLocation.setEnabled(true);
        txtSpeaker.setEnabled(true);
        txtMaxParticipants.setEnabled(true);
    }    
    
    public void textClear() {
        // hospital information section
        areaInformationDetails.setText("");
        
        // add new healthcare program section
        txtProgramTitleAdd.setText("");
        txtSpeakerAdd.setText("");
        txtMaxParticipantsAdd.setText("");
        cboCategoryAdd.setSelectedItem("Education");
        cboLocationAdd.setSelectedItem("Hall A");
        cboStartTimeAdd.setSelectedItem("07:00");
        cboDurationAdd.setSelectedItem("30m");
        lblStartEndTimeAdd.setText("07:00 - 07:30");
    }
    
    public void setDefaultText() {
        lblProgramID.setText(defaultText);
        lblProgramTitle.setText(defaultText);
        lblCategory.setText(defaultText);
        lblMaxParticipants.setText(defaultText);
        lblSpeaker.setText(defaultText); 
        lblDate.setText(defaultText);
        lblTime.setText(defaultText);
        lblLocation.setText(defaultText);
        
        lblProgramIDView.setText(defaultText);
        lblProgramTitleView.setText(defaultText);
        lblMaxParticipantsView.setText(defaultText);
        lblCurrentParticipants.setText(defaultText);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Admin_Frame2().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AssistanceNavBar;
    private javax.swing.JPanel HealthCareNavBar;
    private javax.swing.JPanel HealthCarePanel;
    private javax.swing.JPanel HospitalInfoNavBar;
    private javax.swing.JPanel HospitalInfoPanel;
    private javax.swing.JPanel InventoryNavBar;
    private javax.swing.JPanel ProfileNavBar;
    private javax.swing.JPanel RegisterNavBar;
    private javax.swing.JPanel ShowSpace;
    private javax.swing.JPanel UserManagementNavBar;
    private javax.swing.JPanel addInfomationPanel;
    private javax.swing.JPanel addProgramPanel;
    private javax.swing.JTextArea areaInformationDetails;
    private javax.swing.JButton btnAddInfo;
    private javax.swing.JButton btnAddInformation;
    private javax.swing.JButton btnAddProgram;
    private javax.swing.JButton btnAddProgram2;
    private javax.swing.JButton btnDeleteInformation;
    private javax.swing.JButton btnDeleteInformation2;
    private javax.swing.JButton btnDeleteProgram;
    private javax.swing.JButton btnDeleteProgram2;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReturnAddProgram;
    private javax.swing.JButton btnReturnDeleteProgram;
    private javax.swing.JButton btnReturnHospitalInformation;
    private javax.swing.JButton btnReturnInformation;
    private javax.swing.JButton btnReturnUpdateProgram2;
    private javax.swing.JButton btnSaveInformation;
    private javax.swing.JButton btnSaveUpdateProgram;
    private javax.swing.JButton btnUpdateInformation;
    private javax.swing.JButton btnUpdateProgram;
    private javax.swing.JButton btnUpdateProgram2;
    private javax.swing.JButton btnViewParticipants;
    private javax.swing.JComboBox<String> cboCategory;
    private javax.swing.JComboBox<String> cboCategoryAdd;
    private javax.swing.JComboBox<String> cboDuration;
    private javax.swing.JComboBox<String> cboDurationAdd;
    private javax.swing.JComboBox<String> cboInformationID;
    private javax.swing.JComboBox<String> cboInformationType;
    private javax.swing.JComboBox<String> cboLocation;
    private javax.swing.JComboBox<String> cboLocationAdd;
    private javax.swing.JComboBox<String> cboProgramID;
    private javax.swing.JComboBox<String> cboProgramIDDeletePanel;
    private javax.swing.JComboBox<String> cboProgramIDViewParticipants;
    private javax.swing.JComboBox<String> cboStartTime;
    private javax.swing.JComboBox<String> cboStartTimeAdd;
    private javax.swing.JCheckBox chbDeleteInformation1;
    private javax.swing.JCheckBox chbDeleteInformation2;
    private javax.swing.JCheckBox chbDeleteProgram1;
    private javax.swing.JCheckBox chbDeleteProgram2;
    private javax.swing.JPanel comfirmSlip;
    private javax.swing.JPanel deleteInformationPanel;
    private javax.swing.JPanel deleteProgramPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox24;
    private javax.swing.JComboBox<String> jComboBox25;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel278;
    private javax.swing.JLabel jLabel279;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel282;
    private javax.swing.JLabel jLabel283;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel378;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel380;
    private javax.swing.JLabel jLabel381;
    private javax.swing.JLabel jLabel382;
    private javax.swing.JLabel jLabel383;
    private javax.swing.JLabel jLabel384;
    private javax.swing.JLabel jLabel385;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblAdminName2;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblCounterInformation;
    private javax.swing.JLabel lblCurrentParticipants;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblMaxParticipants;
    private javax.swing.JLabel lblMaxParticipantsView;
    private javax.swing.JLabel lblProgramID;
    private javax.swing.JLabel lblProgramIDView;
    private javax.swing.JLabel lblProgramTitle;
    private javax.swing.JLabel lblProgramTitleView;
    private javax.swing.JLabel lblSpeaker;
    private javax.swing.JLabel lblStartEndTime;
    private javax.swing.JLabel lblStartEndTimeAdd;
    private javax.swing.JLabel lblSuccessAddProgram;
    private javax.swing.JLabel lblSuccessDeleteInformation;
    private javax.swing.JLabel lblSuccessDeleteProgram;
    private javax.swing.JLabel lblSuccessInformation;
    private javax.swing.JLabel lblSuccessUpdateProgram;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTipsInformation1;
    private javax.swing.JLabel lblTipsInformation2;
    private javax.swing.JPanel manageParticipantsPanel;
    private javax.swing.JPanel pnlMenuBar;
    private javax.swing.JPanel programMainPanel;
    private javax.swing.JPanel showSpace;
    private javax.swing.JPanel showSpace1;
    private javax.swing.JSpinner spnDate;
    private javax.swing.JSpinner spnDateAdd;
    private javax.swing.JTable tblHealthcarePrograms;
    private javax.swing.JTable tblHealthcareProgramsDeletePanel;
    private javax.swing.JTable tblHospitalInformation;
    private javax.swing.JTable tblParticipants;
    private javax.swing.JTextField txtMaxParticipants;
    private javax.swing.JTextField txtMaxParticipantsAdd;
    private javax.swing.JTextField txtProgramID;
    private javax.swing.JTextField txtProgramTitle;
    private javax.swing.JTextField txtProgramTitleAdd;
    private javax.swing.JTextField txtSpeaker;
    private javax.swing.JTextField txtSpeakerAdd;
    private javax.swing.JPanel updateInformationPanel;
    private javax.swing.JPanel updateProgramPanel;
    // End of variables declaration//GEN-END:variables
}
