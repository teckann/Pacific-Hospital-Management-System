/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_assignment;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author teckann
 */
public class Admin_Frame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Admin_Frame.class.getName());
    
    /**
     * Creates new form Admin_Frame
     */
    
    // initialize all admin information
    Admin admin = new Admin(Main.getCurrentUserID());
    
    private Admin_Frame2 adminFrame2; // declare a variable & the type is Admin_Frame2
    private final String defaultText = "N/A"; // set default value
    private int counter = 0;
    
    private final LocalDate localDate = LocalDate.now(); // get the current local date
    private final String localDateToString = this.localDate.toString();
    
    // set the default color and hover color (improve user experience - UX)
    private final Color defaultColor = new Color(234, 242, 248);
    private final Color hoverColor = new Color(200,220,235);
    
    // model the Jlist that need real-time updates based on input
    private final DefaultListModel modelWorkingDayList = new DefaultListModel();
    private final DefaultListModel modelBloodTestList = new DefaultListModel();
    private final DefaultListModel modelUpdateWorkingDayList = new DefaultListModel();
    
    // model the JTable that need real-time updates based on selection
    private final DefaultTableModel userTableModel = new DefaultTableModel();
    
    public Admin_Frame() {
        initComponents();
        this.setLocationRelativeTo(null); // center the jFrame
        
        // display the welcome greetings
        System.out.println("Welcome, " + admin.getUserName() + "!");
    }
    
    // is a method that enable program pass Admin_Frame2 directly into this frame
    public void setSecond_adminFrame (Admin_Frame2 adminFrame2){
        this.adminFrame2 = adminFrame2;
    }
    
    // is a method that allows the program to identify and display the correct panel based on the user's operation
    // for Admin_Frame2 ONLY
    public void showPanel(String navbarTitle) {
        lblAdminName.setText("Welcome, " + admin.getUserName() + "!");
        lblAdminName2.setText(admin.getUserName());
        
        switch (navbarTitle) {
            case "Admin Welcome" -> {
                showAdminWelcomePanel();
            }
            
            case "Profile" -> {
                showProfilePanel();
            }
            
            case "Assistance" -> {
                showAssistancePanel();
            }
            
            case "Register User" -> {
                showRegisterStaffPanel();
            }
                
            case "User Management" -> {
                showUserManagementPanel();
            }
                
            case "Inventory Management" -> {
                showInventoryManagementPanel();
            }
                
            default -> {}
        }
    }
    
    // display the related data - profileMainPanel
    public void initializeProfile() {
        txtUserID.setText(admin.getUserID());
        txtName.setText(admin.getUserName());
        txtICNumber.setText(admin.getIcNumber());
        txtNationality.setText(admin.getNationality());
        txtState.setText(admin.getRegion());
        txtGender.setText(admin.getGender());
        txtContactNumber.setText(admin.getContactNumber());
        txtEmail.setText(admin.getEmail());
        txtRole.setText(admin.getUserRole());
    }
    
    public void checkAccSecurityStatus() {
        String icNumber = admin.getIcNumber();
        
        String birthday = icNumber.substring(2, 6);
        String defaultPassword = admin.getUserID() + "@" + birthday;
        
        String password=admin.getUserPassword();
        String QID1 = admin.getSafetyQuestionID1();
        String Ans1 = admin.getSafetyAnswer1();
        String QID2 = admin.getSafetyQuestionID2();
        String Ans2 = admin.getSafetyAnswer2();
        
        // password NOT a default password AND set the safety question already (Secure state)
        if (!defaultPassword.equals(password) && !(QID1.equals("-") || Ans1.equals("-") || QID2.equals("-") || Ans2.equals("-"))) {
            txtAccSecurityStatus.setText("Your account is currently in a secure state.");
            txtAccSecurityStatus.setBackground(new Color(209, 242, 235));
        }
        // didnt change defualt password AND add safety question
        else if (defaultPassword.equals(password) && (QID1.equals("-") || Ans1.equals("-") || QID2.equals("-") || Ans2.equals("-"))) {
            txtAccSecurityStatus.setText("Please change the defualt password AND set the safety question!");
            txtAccSecurityStatus.setBackground(new Color(250, 219, 216));
        }
        // didnt change default password ONLY
        else if (defaultPassword.equals(password) && !(QID1.equals("-") || Ans1.equals("-") || QID2.equals("-") || Ans2.equals("-"))) {
            txtAccSecurityStatus.setText("Please change the defualt password!");
            txtAccSecurityStatus.setBackground(new Color(250, 219, 216));
        }
        // didnt change safety question ONLY
        else if (!defaultPassword.equals(password) && (QID1.equals("-") || Ans1.equals("-") || QID2.equals("-") || Ans2.equals("-"))) {
            txtAccSecurityStatus.setText("Please set the safety question !");
            txtAccSecurityStatus.setBackground(new Color(250, 219, 216));
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
            
            case "Email" -> {
                JOptionPane.showMessageDialog(this,
                    """
                    Please make sure the format of EMAIL is correct.
                    Email Format: xxxx@xxx.xxx
                    Example: abc123@gmail.com
                    """,
                    "Pacific Data Validation Center (DVC)",
                    JOptionPane.WARNING_MESSAGE);
            }
            
            case "Contact Number" -> {
                JOptionPane.showMessageDialog(this,
                    """
                    Please make sure the format of CONTACT NUMBER is correct.
                    1. Only number and without "-"
                    2. Only 10 or 11 digit number
                    """,
                    "Pacific Data Validation Center (DVC)",
                    JOptionPane.WARNING_MESSAGE);
            }
            
            case "IC Number" -> {
                JOptionPane.showMessageDialog(this,
                    """
                    Please make sure the format of IC NUMBER is correct.
                    1. Only number and whitout "-"
                    2. Only 12 digit number
                    """,
                    "Pacific Data Validation Center (DVC)",
                    JOptionPane.WARNING_MESSAGE);
            }
            
            case "Age" -> {
                JOptionPane.showMessageDialog(this,
                    """
                    Please make sure the format of AGE is correct.
                    1. Only number
                    2. Only 2 digit number
                    3. Cannot smaller than or equals to 0
                    """,
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
            
            case "Price" -> {
                JOptionPane.showMessageDialog(this,
                    """
                    Please make sure the format of PRICE is correct.
                    1. Only Number
                    Example Format: xx.xx / xx
                    """,
                    "Pacific Data Validation Center (DVC)",
                    JOptionPane.WARNING_MESSAGE);
            }
            
            case "Password" -> {
                JOptionPane.showMessageDialog(this,
                    """
                    Please make sure the New Password is strong
                    1. At least 8 characters
                    2. Must contain at least one Uppercase Letter (A–Z)
                    3. Must contain at least one Lowercase Letter (a–z)
                    4. Must contain at least one Digit (0–9)
                    5. Must contain at least one Special Character (!, @, #, *, etc.)
                    """,
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
            
            case "updateStatement" -> {
                JOptionPane.showMessageDialog(this, 
                    "Please agree to all statements before update.",
                    "Pacific Data Security Center (DSC)", 
                    JOptionPane.WARNING_MESSAGE);
            }
            
            default -> {}
        }
        
        if (label != null) {
            label.setVisible(false);
        }
    }
    
    // main panel include (Admin Welcome Panel, Profile Panel, Assistance Panel,
    // Register Staff Panel, User Management Panel, Inventory Panel) - NavBar Elements
    public void mainPanelControl(javax.swing.JPanel showPanel) {
        // first, hide all the main panel
        AdminWelcomePanel.setVisible(false);
        ProfilePanel.setVisible(false);
        AssistancePanel.setVisible(false);
        RegisterStaffPanel.setVisible(false);
        UserManagementPanel.setVisible(false);
        InventoryPanel.setVisible(false);
        
        // only show the panel that I give (argument)
        showPanel.setVisible(true);
    }
    
    // the purpose of create 6 methods below is reduce duplication of the same code/logic
    public void showAdminWelcomePanel() {
        mainPanelControl(AdminWelcomePanel);
    }
    
    public void showProfilePanel() {
        mainPanelControl(ProfilePanel);
        System.out.println("Redirect to the Profile page.");
        
        profileMainPanel.setVisible(true);
        safetyQuestionPanel.setVisible(false);
        changePasswordPanel.setVisible(false);

        // display the profile information
        // but didnt given edit permission
        // upon click edit profile
        disenable();

        // hide the message
        lblTipsProfile.setVisible(false);
        lblSuccessProfile.setVisible(false);

        lblTipsSafetyQuestion.setVisible(false);
        lblSuccessSafetyQuestion.setVisible(false);

        lblSuccessChangePassword.setVisible(false);

        // display the related data - profileMainPanel
        initializeProfile();

        // account security status checking
        checkAccSecurityStatus();
    }
    
    public void showAssistancePanel() {
        mainPanelControl(AssistancePanel);
        System.out.println("Redirect to the Assistance page.");

        viewPrescriptionPanel.setVisible(true);
        dispenseMedicinesPanel.setVisible(false);
        viewMedicalReportPanel.setVisible(false);

        lblPrescriptionTips.setText("");
        modelComboBoxPrescriptionID();

        btnViewMedicalReport.setEnabled(false);
        btnDispenseMedicines.setEnabled(false);
        
        // set Default Text ("N/A")
        setDefaultText();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        lstPrescribeMedication.setModel(listModel);
    }
    
    public void showRegisterStaffPanel() {
        mainPanelControl(RegisterStaffPanel);
        System.out.println("Redirect to the Register User page.");

        registerChoosePanel.setVisible(true);
        registerStaffPanel.setVisible(false);
        registerAdminPanel.setVisible(false);
        registerPatientPanel.setVisible(false);
    }
    
    public void showUserManagementPanel() {
        mainPanelControl(UserManagementPanel);
        System.out.println("Redirect to the User Management page.");

        UserManagementMainPanel.setVisible(true);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        ManageAdminPanel.setVisible(false);
        UpdatePasswordPanel.setVisible(false);
    }
    
    public void showInventoryManagementPanel() {
        mainPanelControl(InventoryPanel);
        System.out.println("Redirect to the Inventory Management page.");
        
        inventoryTable();

        UpdateItemPanel.setVisible(true);
        AddItemPanel.setVisible(false);
        DeleteItemPanel.setVisible(false);
        StockReportPanel.setVisible(false);

        inventoryTable();
        // does not provide edit promission first
        // upon click update inventory
        disenable();
        // hide the message
        lblSuccessInventory.setVisible(false);
        lblTipsInventory.setVisible(false);
        lblSuccessDeleteItem.setVisible(false);
    }
    
    // the purpose of these methods below are model table / combo box
    public void userTable() {
        // clear table first
        userTableModel.setRowCount(0);
        String[] tableTitle = {"User ID", "Role", "User Name"};
        
        // readFile method will return an ArrayList
        // create an ArrayList to store it
        ArrayList<String[]> dataList = File_Control.readFile("loginData.txt", true);
        
        userTableModel.setColumnIdentifiers(tableTitle);

        for (String[] list : dataList) {
            userTableModel.addRow(list);
        }
        tblUsers.setModel(userTableModel);
    }
    
    public void updatePasswordTable() {
        String[] tableTitle = {"User ID", "User Role", "User Name", "User Password"};
        
        // readFile method will return an ArrayList
        // create an ArrayList to store it
        ArrayList<String[]> dataList = File_Control.readFile("loginData.txt", true);
        
        // Model table - clear the table
        // it will only display the data that we add below
        DefaultTableModel tableModel = new DefaultTableModel() {
            // override isCellEditable
            // so the admin cannot make any change for column 0 and 1
            // which is Information ID and Information Type
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2) {
                    return false;
                }
                return true;
            }
        };
        
        tableModel.setColumnIdentifiers(tableTitle);

        for (String[] list : dataList) {
            tableModel.addRow(list);
        }
        tblUpdatePassword.setModel(tableModel);
    }
    
    public void inventoryTable() {
        String[] tableTitle = {"Item ID", "Item Name", "Category", "Suplier", "Price", "Safety Stock", "Current Stock"};
        
        // readFile method will return an ArrayList
        // create an ArrayList to store it
        ArrayList<String[]> dataList = File_Control.readFile("inventoryData.txt", true);
        
        // Model table - clear the table
        // it will only display the data that we add below
        DefaultTableModel tableModel = new DefaultTableModel() {
            // override isCellEditable
            // so the admin cannot make any change for column 0
            // which is Item ID
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 2) {
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
        tblInventory.setModel(tableModel);
    }
    
    public void modelComboBoxSafetyQuestion() {
        ArrayList<String[]> dataList = File_Control.readFile("securityQuestion.txt", true);
        
        DefaultComboBoxModel<String> comboModelQ1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> comboModelQ2 = new DefaultComboBoxModel<>();
        
        comboModelQ1.addElement("- Please Select -");
        comboModelQ2.addElement("- Please Select -");
            
        for (String[] line : dataList) {
            comboModelQ1.addElement(line[1]);
            comboModelQ2.addElement(line[1]);
        }

        cboSafetyQuestion1.setModel(comboModelQ1);
        cboSafetyQuestion2.setModel(comboModelQ2);
    }
    
    public void modelComboBoxItemID() {
        ArrayList<String[]> dataList = File_Control.readFile("inventoryData.txt", true);

        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

        for (String[] list : dataList) {
            comboModel.addElement(list[0]);
        }

        cboItemID.setModel(comboModel);
    }
    
    public void modelComboBoxCategory() {
        String[] category = {"Medication", "Medical Consumables", "Vaccine"};
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String data : category) {
            comboModel.addElement(data);
        }
        
        cboCategory.setModel(comboModel);
    }
    
    public void modelComboBoxWorkingDay() {
        String[] workingDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String day : workingDay) {
            comboModel.addElement(day);
        }
        
        cboDayRegisterStaff.setModel(comboModel);
    }
    
    public void modelComboBoxUpdateWorkingDay() {
        String[] workingDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String day : workingDay) {
            comboModel.addElement(day);
        }
        
        cboDayStaff.setModel(comboModel);
    }
    
    public void modelComboBoxPrescriptionID() {
        ArrayList<String[]> dataList = File_Control.readFile("prescriptionData.txt", true);
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

        for (String[] list : dataList) {
            comboModel.addElement(list[0]);
        }

        cboPrescriptionID.setModel(comboModel);
    }
    
    public void modelComboBoxMedicineID() {
        ArrayList<String[]> dataList = File_Control.readFile("inventoryData.txt", true);

        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

        for (String[] list : dataList) {
            String itemType = list[2];
            
            if (itemType.equals("Medication")) {
                comboModel.addElement(list[0]);
            }
        }

        cboMedicineID.setModel(comboModel);
    }
    
    public void modelComboBoxUserID(String role) {
        ArrayList<String[]> dataList = File_Control.readFile("loginData.txt", true);

        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

        for (String[] list : dataList) {
            String UserID = list[0];

            if (role.equals("Admin")) {
                // not include current Admin ID in combobox
                if (!admin.getUserID().equals(UserID)) {
                    if (UserID.startsWith("A")) {
                        comboModel.addElement(list[0]);
                    }
                }    
            }
            
            else if (role.equals("Doctor")) {
                if (UserID.startsWith("D")) {
                    comboModel.addElement(list[0]);
                }
            }

            else if (role.equals("Patient")) {
                if (UserID.startsWith("P")) {
                    comboModel.addElement(list[0]);
                }
            }
        }

        cboUserID.setModel(comboModel);
    }
    
    public void modelComboBoxAdminID() {
        ArrayList<String[]> dataList = File_Control.readFile("adminData.txt", true);

        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

        for (String[] list : dataList) {
            if (!admin.getUserID().equals(list[0])) {
                comboModel.addElement(list[0]);
            }
        }

        cboAdminID.setModel(comboModel);
    }
    
    public void modelComboBoxStaffID() {
        ArrayList<String[]> dataList = File_Control.readFile("doctorData.txt", true);

        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

        for (String[] list : dataList) {
            comboModel.addElement(list[0]);
        }

        cboStaffID.setModel(comboModel);
    }
    
    public void modelComboBoxPatientID() {
        ArrayList<String[]> dataList = File_Control.readFile("patientData.txt", true);

        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

        for (String[] list : dataList) {
            comboModel.addElement(list[0]);
        }

        cboPatientID.setModel(comboModel);
    }
    
    public void modelComboBoxChildrenID() {
        ArrayList<String[]> dataList = File_Control.readFile("childData.txt", true);

        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();

        for (String[] list : dataList) {
            comboModel.addElement(list[0]);
        }

        cboPatientID.setModel(comboModel);
    }
    
    public void modelComboBoxChildrenYearsMonths() {
        DefaultComboBoxModel<String> comboModelA = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> comboModelB = new DefaultComboBoxModel<>();

        for (int i = 0; i < 12; i++) {
            comboModelA.addElement(String.valueOf(i));
            comboModelB.addElement(String.valueOf(i));
        }

        cboYears.setModel(comboModelA);
        cboMonths.setModel(comboModelB);
    }
    
    public void modelComboBoxInformationType() {
        String[] type = {"General Information", "Accident & Emergency", "Visiting Hours", "Operating Hours", "Safety Measures"};
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String data : type) {
            comboModel.addElement(data);
        }
    }
    
    public void modelComboBoxRole() {
        String[] type = {"Admin", "Doctor", "Patient"};
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String data : type) {
            comboModel.addElement(data);
        }
        
        cboRole.setModel(comboModel);
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
        AdminWelcomePanel = new javax.swing.JPanel();
        WelcomeTitle = new javax.swing.JPanel();
        lblAdminName = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ProfilePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        showSpace = new javax.swing.JPanel();
        changePasswordPanel = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtCurrentPassword = new javax.swing.JPasswordField();
        btnIdentify = new javax.swing.JButton();
        lblTipsChangePassword = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JTextField();
        txtConfirmNewPassword = new javax.swing.JTextField();
        btnReturnChangePassword = new javax.swing.JButton();
        btnSaveChangePassword = new javax.swing.JButton();
        lblSuccessChangePassword = new javax.swing.JLabel();
        profileMainPanel = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtRole = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        txtICNumber = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtState = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtContactNumber = new javax.swing.JTextField();
        txtNationality = new javax.swing.JTextField();
        btnEditProfile = new javax.swing.JButton();
        btnSaveProfile = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblTipsProfile = new javax.swing.JLabel();
        lblSuccessProfile = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        btnSafetyQuestion = new javax.swing.JButton();
        txtAccSecurityStatus = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        btnPassword = new javax.swing.JButton();
        safetyQuestionPanel = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        cboSafetyQuestion1 = new javax.swing.JComboBox<>();
        jLabel84 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txtAnswer1 = new javax.swing.JTextField();
        cboSafetyQuestion2 = new javax.swing.JComboBox<>();
        jLabel98 = new javax.swing.JLabel();
        txtAnswer2 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        btnEditSafetyQuestion = new javax.swing.JButton();
        btnSaveSafetyQuestion = new javax.swing.JButton();
        lblTipsSafetyQuestion = new javax.swing.JLabel();
        lblSuccessSafetyQuestion = new javax.swing.JLabel();
        btnReturnSafetyQuestion = new javax.swing.JButton();
        AssistancePanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        showSpace6 = new javax.swing.JPanel();
        dispenseMedicinesPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstPrescribeMedication2 = new javax.swing.JList<>();
        jLabel224 = new javax.swing.JLabel();
        jLabel247 = new javax.swing.JLabel();
        jLabel248 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel234 = new javax.swing.JLabel();
        jLabel235 = new javax.swing.JLabel();
        jLabel236 = new javax.swing.JLabel();
        jLabel237 = new javax.swing.JLabel();
        jLabel238 = new javax.swing.JLabel();
        jLabel239 = new javax.swing.JLabel();
        jLabel240 = new javax.swing.JLabel();
        jLabel241 = new javax.swing.JLabel();
        lblMedicineID = new javax.swing.JLabel();
        lblMedicineName = new javax.swing.JLabel();
        lblMedicinePrice = new javax.swing.JLabel();
        lblMedicineCurrentStock = new javax.swing.JLabel();
        cboMedicineID = new javax.swing.JComboBox<>();
        jLabel232 = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        lblSuccessDispenseMedicines = new javax.swing.JLabel();
        btnDispense = new javax.swing.JButton();
        btnDispenseMedicinesBack = new javax.swing.JButton();
        viewMedicalReportPanel = new javax.swing.JPanel();
        btnReturnMedicalReoport = new javax.swing.JButton();
        medicalReportSlip = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel242 = new javax.swing.JLabel();
        lblDoctorID2 = new javax.swing.JLabel();
        jLabel243 = new javax.swing.JLabel();
        jLabel244 = new javax.swing.JLabel();
        lblDoctorName2 = new javax.swing.JLabel();
        jLabel245 = new javax.swing.JLabel();
        jLabel249 = new javax.swing.JLabel();
        jLabel250 = new javax.swing.JLabel();
        lblMedicalReportDate = new javax.swing.JLabel();
        lblSpecialist = new javax.swing.JLabel();
        jLabel265 = new javax.swing.JLabel();
        jLabel266 = new javax.swing.JLabel();
        lblRegisterPersonNameAdmin3 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel257 = new javax.swing.JLabel();
        jLabel259 = new javax.swing.JLabel();
        lblMedicalReportID2 = new javax.swing.JLabel();
        jLabel261 = new javax.swing.JLabel();
        jLabel262 = new javax.swing.JLabel();
        lblPatientID2 = new javax.swing.JLabel();
        lblPatientName2 = new javax.swing.JLabel();
        jLabel263 = new javax.swing.JLabel();
        jLabel264 = new javax.swing.JLabel();
        lblRegisterPersonNameAdmin8 = new javax.swing.JLabel();
        jLabel267 = new javax.swing.JLabel();
        lblPatientAge = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        areaPatientDescription = new javax.swing.JTextArea();
        jPanel30 = new javax.swing.JPanel();
        jLabel251 = new javax.swing.JLabel();
        lblPrescriptionFee1 = new javax.swing.JLabel();
        jLabel252 = new javax.swing.JLabel();
        lblRegisterPersonNameAdmin9 = new javax.swing.JLabel();
        jLabel268 = new javax.swing.JLabel();
        lblPatientGender = new javax.swing.JLabel();
        lblRegisterPersonNameAdmin10 = new javax.swing.JLabel();
        jLabel269 = new javax.swing.JLabel();
        lblPatientBloodType = new javax.swing.JLabel();
        lblPatientRegion = new javax.swing.JLabel();
        jLabel270 = new javax.swing.JLabel();
        lblRegisterPersonNameAdmin11 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jLabel253 = new javax.swing.JLabel();
        lblPaymentStatus2 = new javax.swing.JLabel();
        jLabel254 = new javax.swing.JLabel();
        lblDiasease = new javax.swing.JLabel();
        jLabel260 = new javax.swing.JLabel();
        jLabel258 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listBloodTest = new javax.swing.JList<>();
        viewPrescriptionPanel = new javax.swing.JPanel();
        prescriptionSlip = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel213 = new javax.swing.JLabel();
        lblProgression = new javax.swing.JLabel();
        jLabel214 = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        lblDispensedBy = new javax.swing.JLabel();
        jLabel215 = new javax.swing.JLabel();
        jLabel228 = new javax.swing.JLabel();
        jLabel229 = new javax.swing.JLabel();
        lblDispensedDate = new javax.swing.JLabel();
        jLabel226 = new javax.swing.JLabel();
        jLabel227 = new javax.swing.JLabel();
        lblPaymentStatus = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstPrescribeMedication = new javax.swing.JList<>();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        lblRegisterPersonNameAdmin2 = new javax.swing.JLabel();
        lblDoctorName = new javax.swing.JLabel();
        jLabel217 = new javax.swing.JLabel();
        jLabel218 = new javax.swing.JLabel();
        lblDoctorID = new javax.swing.JLabel();
        jLabel219 = new javax.swing.JLabel();
        jLabel220 = new javax.swing.JLabel();
        lblPrescriptionDate = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        lblPrescriptionID = new javax.swing.JLabel();
        lblMedicalReportID = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        lblPatientID = new javax.swing.JLabel();
        lblPatientName = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel230 = new javax.swing.JLabel();
        lblPrescriptionFee = new javax.swing.JLabel();
        jLabel231 = new javax.swing.JLabel();
        jLabel216 = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        cboPrescriptionID = new javax.swing.JComboBox<>();
        btnDispenseMedicines = new javax.swing.JButton();
        btnViewMedicalReport = new javax.swing.JButton();
        lblPrescriptionTips = new javax.swing.JLabel();
        RegisterStaffPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        showspace3 = new javax.swing.JPanel();
        registerChoosePanel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        registerPatientPanel = new javax.swing.JPanel();
        jLabel173 = new javax.swing.JLabel();
        txtNameRegisterPatient = new javax.swing.JTextField();
        txtICNumberRegisterPatient = new javax.swing.JTextField();
        txtRaceRegisterPatient = new javax.swing.JTextField();
        txtStateRegisterPatient = new javax.swing.JTextField();
        txtContactNumberRegisterPatient = new javax.swing.JTextField();
        txtEmailRegisterPatient = new javax.swing.JTextField();
        btnRegisterPatient = new javax.swing.JToggleButton();
        btnReturnRegisterPatient = new javax.swing.JToggleButton();
        jLabel174 = new javax.swing.JLabel();
        lblSuccessRegisterPatient = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        cboGenderRegisterPatient = new javax.swing.JComboBox<>();
        registerSlip1 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        lblNamePatient = new javax.swing.JLabel();
        lblRolePatient = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        lblUserIDPatient = new javax.swing.JLabel();
        lblDefaultPasswordPatient = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        jLabel184 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        lblRegisterPersonNamePatient = new javax.swing.JLabel();
        lblDatePatient = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        txtAgeRegisterPatient = new javax.swing.JTextField();
        jLabel201 = new javax.swing.JLabel();
        jLabel202 = new javax.swing.JLabel();
        registerStaffPanel = new javax.swing.JPanel();
        staffPanel = new javax.swing.JPanel();
        jLabel166 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        cboGenderRegisterStaff = new javax.swing.JComboBox<>();
        jLabel115 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        btnRegisterStaff = new javax.swing.JButton();
        btnReturnStaff = new javax.swing.JButton();
        lblSuccessRegisterStaff = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        btnSaveRegisterStaff = new javax.swing.JButton();
        jLabel161 = new javax.swing.JLabel();
        btnAddRegisterStaff = new javax.swing.JButton();
        txtAgeRegisterStaff = new javax.swing.JTextField();
        cboDayRegisterStaff = new javax.swing.JComboBox<>();
        jLabel164 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        cboSpecialistRegisterStaff = new javax.swing.JComboBox<>();
        jLabel123 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        txtNationalityRegisterStaff = new javax.swing.JTextField();
        jLabel162 = new javax.swing.JLabel();
        txtICNumberRegisterStaff = new javax.swing.JTextField();
        jLabel152 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        txtContactNumberRegisterStaff = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        txtStateRegisterStaff = new javax.swing.JTextField();
        txtEmailRegisterStaff = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        txtNameRegisterStaff = new javax.swing.JTextField();
        txtOfficeRegisterStaff = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstWorkingDayRegisterStaff = new javax.swing.JList<>();
        Slip = new javax.swing.JPanel();
        Slip1 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        lblRegisterPersonNameSlip = new javax.swing.JLabel();
        lblDateSlip = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        lblSlipTitle = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        lblNameSlip = new javax.swing.JLabel();
        lblICNumberSlip = new javax.swing.JLabel();
        lblUserIDSlip = new javax.swing.JLabel();
        lblDefaultPasswordSlip = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        lblRoleSlip = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        lblSpecialistSlip = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        lblWorkingDaySlip = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        lblOfficeSlip = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        registerAdminPanel = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        txtNameRegisterAdmin = new javax.swing.JTextField();
        txtIcNumberRegisterAdmin = new javax.swing.JTextField();
        txtNationalityRegisterAdmin = new javax.swing.JTextField();
        txtStateRegisterAdmin = new javax.swing.JTextField();
        txtContactNumberRegisterAdmin = new javax.swing.JTextField();
        txtEmailRegisterAdmin = new javax.swing.JTextField();
        btnRegisterAdmin = new javax.swing.JToggleButton();
        btnReturnAdmin = new javax.swing.JToggleButton();
        lblSuccessRegisterAdmin = new javax.swing.JLabel();
        cboGenderRegisterAdmin = new javax.swing.JComboBox<>();
        registerSlip = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        lblNameAdmin = new javax.swing.JLabel();
        lblRoleAdmin = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        lblUserIDAdmin = new javax.swing.JLabel();
        lblDefaultPasswordAdmin = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        lblRegisterPersonNameAdmin = new javax.swing.JLabel();
        lblDateAdmin = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        UserManagementPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        ShowSpace6 = new javax.swing.JPanel();
        UpdatePasswordPanel = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblUpdatePassword = new javax.swing.JTable();
        jPanel48 = new javax.swing.JPanel();
        btnUpdateUserPassword = new javax.swing.JButton();
        btnSaveUpdatePassword = new javax.swing.JButton();
        btnReturnUpdatePassword = new javax.swing.JButton();
        chbCondition2 = new javax.swing.JCheckBox();
        chbCondition1 = new javax.swing.JCheckBox();
        lblSuccessUpdatePassword = new javax.swing.JLabel();
        lblTipsUpdatePassword = new javax.swing.JLabel();
        ManageAdminPanel = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel293 = new javax.swing.JLabel();
        jLabel295 = new javax.swing.JLabel();
        btnReturnAdmin1 = new javax.swing.JButton();
        btnUpdateRecordAdmin = new javax.swing.JButton();
        btnSaveAdmin = new javax.swing.JButton();
        jLabel291 = new javax.swing.JLabel();
        jLabel292 = new javax.swing.JLabel();
        cboAdminID = new javax.swing.JComboBox<>();
        jPanel38 = new javax.swing.JPanel();
        jLabel284 = new javax.swing.JLabel();
        jLabel285 = new javax.swing.JLabel();
        lblAdminUserID = new javax.swing.JLabel();
        jLabel287 = new javax.swing.JLabel();
        jLabel288 = new javax.swing.JLabel();
        lblAdminRole = new javax.swing.JLabel();
        jLabel300 = new javax.swing.JLabel();
        jLabel301 = new javax.swing.JLabel();
        lblAdminIcNumber = new javax.swing.JLabel();
        jLabel290 = new javax.swing.JLabel();
        jLabel294 = new javax.swing.JLabel();
        txtUserNameAdmin = new javax.swing.JTextField();
        jLabel296 = new javax.swing.JLabel();
        jLabel297 = new javax.swing.JLabel();
        txtNationalityAdmin = new javax.swing.JTextField();
        jLabel298 = new javax.swing.JLabel();
        jLabel299 = new javax.swing.JLabel();
        txtRegionAdmin = new javax.swing.JTextField();
        jLabel302 = new javax.swing.JLabel();
        jLabel303 = new javax.swing.JLabel();
        txtContactNumberAdmin = new javax.swing.JTextField();
        jLabel304 = new javax.swing.JLabel();
        jLabel305 = new javax.swing.JLabel();
        txtEmailAdmin = new javax.swing.JTextField();
        lblSuccessUpdateRecordAdmin = new javax.swing.JLabel();
        ManageStaffPanel = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel307 = new javax.swing.JLabel();
        jLabel308 = new javax.swing.JLabel();
        btnReturnStaff1 = new javax.swing.JButton();
        btnUpdateRecordStaff = new javax.swing.JButton();
        btnSaveStaff = new javax.swing.JButton();
        jLabel309 = new javax.swing.JLabel();
        jLabel310 = new javax.swing.JLabel();
        cboStaffID = new javax.swing.JComboBox<>();
        jPanel39 = new javax.swing.JPanel();
        jLabel311 = new javax.swing.JLabel();
        jLabel312 = new javax.swing.JLabel();
        lblStaffUserID = new javax.swing.JLabel();
        jLabel314 = new javax.swing.JLabel();
        jLabel315 = new javax.swing.JLabel();
        lblStaffRole = new javax.swing.JLabel();
        jLabel317 = new javax.swing.JLabel();
        jLabel318 = new javax.swing.JLabel();
        lblStaffIcNumber = new javax.swing.JLabel();
        showSpace7 = new javax.swing.JPanel();
        positionInformationPanel = new javax.swing.JPanel();
        jLabel334 = new javax.swing.JLabel();
        jLabel335 = new javax.swing.JLabel();
        cboSpecialistStaff = new javax.swing.JComboBox<>();
        jLabel338 = new javax.swing.JLabel();
        jLabel339 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        lstWorkingDayStaff = new javax.swing.JList<>();
        jLabel336 = new javax.swing.JLabel();
        jLabel337 = new javax.swing.JLabel();
        txtOfficeStaff = new javax.swing.JTextField();
        btnClearWorkingDayStaff = new javax.swing.JButton();
        jLabel340 = new javax.swing.JLabel();
        jLabel341 = new javax.swing.JLabel();
        cboDayStaff = new javax.swing.JComboBox<>();
        btnAddWorkingDayStaff = new javax.swing.JButton();
        btnSaveWorkingDayStaff = new javax.swing.JButton();
        personalInformationPanel = new javax.swing.JPanel();
        jLabel320 = new javax.swing.JLabel();
        jLabel321 = new javax.swing.JLabel();
        txtUserNameStaff = new javax.swing.JTextField();
        jLabel322 = new javax.swing.JLabel();
        jLabel323 = new javax.swing.JLabel();
        txtNationalityStaff = new javax.swing.JTextField();
        jLabel324 = new javax.swing.JLabel();
        jLabel325 = new javax.swing.JLabel();
        txtRegionStaff = new javax.swing.JTextField();
        jLabel326 = new javax.swing.JLabel();
        jLabel327 = new javax.swing.JLabel();
        txtQualificationStaff = new javax.swing.JTextField();
        jLabel328 = new javax.swing.JLabel();
        jLabel329 = new javax.swing.JLabel();
        txtContactNumberStaff = new javax.swing.JTextField();
        jLabel330 = new javax.swing.JLabel();
        jLabel331 = new javax.swing.JLabel();
        txtEmailStaff = new javax.swing.JTextField();
        jLabel332 = new javax.swing.JLabel();
        jLabel333 = new javax.swing.JLabel();
        txtAgeStaff = new javax.swing.JTextField();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblSuccessUpdateRecordStaff = new javax.swing.JLabel();
        ManagePatientPanel = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jLabel313 = new javax.swing.JLabel();
        jLabel316 = new javax.swing.JLabel();
        btnReturnPatient = new javax.swing.JButton();
        btnUpdateRecordPatient = new javax.swing.JButton();
        btnSavePatient = new javax.swing.JButton();
        jLabel319 = new javax.swing.JLabel();
        jLabel342 = new javax.swing.JLabel();
        cboPatientID = new javax.swing.JComboBox<>();
        jLabel376 = new javax.swing.JLabel();
        jLabel377 = new javax.swing.JLabel();
        cboPatientRole = new javax.swing.JComboBox<>();
        lblSuccessUpdateRecordPatient = new javax.swing.JLabel();
        showSpace8 = new javax.swing.JPanel();
        childrenPanel = new javax.swing.JPanel();
        txtRegionChildren = new javax.swing.JTextField();
        jLabel358 = new javax.swing.JLabel();
        jLabel359 = new javax.swing.JLabel();
        jLabel360 = new javax.swing.JLabel();
        jLabel361 = new javax.swing.JLabel();
        jLabel362 = new javax.swing.JLabel();
        jLabel363 = new javax.swing.JLabel();
        jLabel364 = new javax.swing.JLabel();
        txtUserNameChildren = new javax.swing.JTextField();
        jLabel365 = new javax.swing.JLabel();
        cboYears = new javax.swing.JComboBox<>();
        jPanel44 = new javax.swing.JPanel();
        jLabel366 = new javax.swing.JLabel();
        jLabel367 = new javax.swing.JLabel();
        lblChildrenUserID = new javax.swing.JLabel();
        jLabel368 = new javax.swing.JLabel();
        jLabel369 = new javax.swing.JLabel();
        lblParientID = new javax.swing.JLabel();
        jLabel370 = new javax.swing.JLabel();
        jLabel371 = new javax.swing.JLabel();
        lblChildrenIcNumber = new javax.swing.JLabel();
        jLabel372 = new javax.swing.JLabel();
        jLabel373 = new javax.swing.JLabel();
        lblParentName = new javax.swing.JLabel();
        jLabel374 = new javax.swing.JLabel();
        cboMonths = new javax.swing.JComboBox<>();
        jLabel375 = new javax.swing.JLabel();
        cboBloodTypeChildren = new javax.swing.JComboBox<>();
        patientPanel = new javax.swing.JPanel();
        txtRegionPatient = new javax.swing.JTextField();
        jLabel351 = new javax.swing.JLabel();
        jLabel357 = new javax.swing.JLabel();
        jLabel350 = new javax.swing.JLabel();
        jLabel356 = new javax.swing.JLabel();
        txtAgePatient = new javax.swing.JTextField();
        jLabel349 = new javax.swing.JLabel();
        txtEmailPatient = new javax.swing.JTextField();
        jLabel348 = new javax.swing.JLabel();
        jLabel355 = new javax.swing.JLabel();
        txtContactNumberPatient = new javax.swing.JTextField();
        jLabel346 = new javax.swing.JLabel();
        jLabel353 = new javax.swing.JLabel();
        jLabel352 = new javax.swing.JLabel();
        txtUserNamePatient = new javax.swing.JTextField();
        jLabel354 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel286 = new javax.swing.JLabel();
        jLabel289 = new javax.swing.JLabel();
        lblPatientUserID = new javax.swing.JLabel();
        jLabel306 = new javax.swing.JLabel();
        jLabel343 = new javax.swing.JLabel();
        lblPatientRole = new javax.swing.JLabel();
        jLabel344 = new javax.swing.JLabel();
        jLabel345 = new javax.swing.JLabel();
        lblPatientIcNumber = new javax.swing.JLabel();
        jLabel347 = new javax.swing.JLabel();
        cboBloodTypePatient = new javax.swing.JComboBox<>();
        DeleteAccountPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnDeleteUser = new javax.swing.JButton();
        btnReturnDeleteUser = new javax.swing.JButton();
        lblSuccessDeleteUser = new javax.swing.JLabel();
        jLabel279 = new javax.swing.JLabel();
        jLabel280 = new javax.swing.JLabel();
        cboUserID = new javax.swing.JComboBox<>();
        chbDeleteUser1 = new javax.swing.JCheckBox();
        jLabel281 = new javax.swing.JLabel();
        chbDeleteUser2 = new javax.swing.JCheckBox();
        jPanel36 = new javax.swing.JPanel();
        jLabel225 = new javax.swing.JLabel();
        jLabel233 = new javax.swing.JLabel();
        jLabel246 = new javax.swing.JLabel();
        jLabel278 = new javax.swing.JLabel();
        jLabel282 = new javax.swing.JLabel();
        jLabel283 = new javax.swing.JLabel();
        lblUserID = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblUserRole = new javax.swing.JLabel();
        jLabel378 = new javax.swing.JLabel();
        jLabel380 = new javax.swing.JLabel();
        cboRole = new javax.swing.JComboBox<>();
        UserManagementMainPanel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        ManageAdmin = new javax.swing.JPanel();
        jLabel255 = new javax.swing.JLabel();
        jLabel256 = new javax.swing.JLabel();
        ManageStaff = new javax.swing.JPanel();
        jLabel271 = new javax.swing.JLabel();
        jLabel274 = new javax.swing.JLabel();
        ManagePatient = new javax.swing.JPanel();
        jLabel273 = new javax.swing.JLabel();
        jLabel272 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        DeleteUserAccount = new javax.swing.JPanel();
        jLabel275 = new javax.swing.JLabel();
        jLabel276 = new javax.swing.JLabel();
        jLabel277 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jLabel379 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        InventoryPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        ShowSpace2 = new javax.swing.JPanel();
        StockReportPanel = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        lblTotalCurrentStock = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        lblTotalSafetyStock = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        lblSafetyPercenrage = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        lblRiskAnalysis = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        DeleteItemPanel = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        cboItemID = new javax.swing.JComboBox<>();
        chbDeleteItem1 = new javax.swing.JCheckBox();
        jLabel55 = new javax.swing.JLabel();
        chbDeleteItem2 = new javax.swing.JCheckBox();
        btnDeleteItem = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        btnReturnDeleteItem = new javax.swing.JButton();
        lblSuccessDeleteItem = new javax.swing.JLabel();
        UpdateItemPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnUpdateInventory = new javax.swing.JButton();
        lblTipsInventory = new javax.swing.JLabel();
        btnSaveInventory = new javax.swing.JButton();
        lblSuccessInventory = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnAddItems = new javax.swing.JButton();
        btnDeleteItems = new javax.swing.JButton();
        ReportPanel = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        AddItemPanel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtSuplier = new javax.swing.JTextField();
        txtSafetyStock = new javax.swing.JTextField();
        txtCurrentStock = new javax.swing.JTextField();
        btnReturnInventory = new javax.swing.JButton();
        btnAddItem = new javax.swing.JButton();
        cboCategory = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        lblCounterInventory = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();

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

        AdminWelcomePanel.setBackground(new java.awt.Color(235, 245, 251));

        WelcomeTitle.setBackground(new java.awt.Color(52, 152, 219));

        lblAdminName.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        lblAdminName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdminName.setText("Welcome, Administrator!");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Glad to have you back, wishing you a productive day ahead.");

        javax.swing.GroupLayout WelcomeTitleLayout = new javax.swing.GroupLayout(WelcomeTitle);
        WelcomeTitle.setLayout(WelcomeTitleLayout);
        WelcomeTitleLayout.setHorizontalGroup(
            WelcomeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAdminName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        WelcomeTitleLayout.setVerticalGroup(
            WelcomeTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WelcomeTitleLayout.createSequentialGroup()
                .addComponent(lblAdminName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adminWelcome.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(72, 201, 176));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administrator Panel - Full Access Allowed");

        javax.swing.GroupLayout AdminWelcomePanelLayout = new javax.swing.GroupLayout(AdminWelcomePanel);
        AdminWelcomePanel.setLayout(AdminWelcomePanelLayout);
        AdminWelcomePanelLayout.setHorizontalGroup(
            AdminWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(WelcomeTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminWelcomePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        AdminWelcomePanelLayout.setVerticalGroup(
            AdminWelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminWelcomePanelLayout.createSequentialGroup()
                .addComponent(WelcomeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        ProfilePanel.setBackground(new java.awt.Color(235, 245, 251));
        ProfilePanel.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(52, 152, 219));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Profile");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        ProfilePanel.add(jPanel2);
        jPanel2.setBounds(0, 0, 630, 68);

        showSpace.setBackground(new java.awt.Color(235, 245, 251));

        changePasswordPanel.setBackground(new java.awt.Color(235, 245, 251));
        changePasswordPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel51.setText("Current Password");
        changePasswordPanel.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 63, 177, -1));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel59.setText("New Password");
        changePasswordPanel.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 182, 177, -1));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel72.setText(":");
        changePasswordPanel.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 63, -1, -1));

        txtCurrentPassword.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        changePasswordPanel.add(txtCurrentPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 65, 170, -1));

        btnIdentify.setBackground(new java.awt.Color(204, 255, 204));
        btnIdentify.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnIdentify.setText("Identify");
        btnIdentify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIdentifyActionPerformed(evt);
            }
        });
        changePasswordPanel.add(btnIdentify, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 65, 89, 24));

        lblTipsChangePassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblTipsChangePassword.setText("tips");
        changePasswordPanel.add(lblTipsChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 90, 300, -1));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel73.setText(":");
        changePasswordPanel.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 182, -1, -1));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel74.setText("Confirm New Password");
        changePasswordPanel.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 224, 177, -1));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel75.setText(":");
        changePasswordPanel.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 224, -1, -1));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel76.setText("Step 1: Identify Current Password");
        changePasswordPanel.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 18, 360, -1));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel77.setText("Step 2: Enter New Password");
        changePasswordPanel.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 137, 360, -1));

        txtNewPassword.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        changePasswordPanel.add(txtNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 184, 170, -1));

        txtConfirmNewPassword.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        changePasswordPanel.add(txtConfirmNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 226, 170, -1));

        btnReturnChangePassword.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReturnChangePassword.setText("Return");
        btnReturnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnChangePasswordActionPerformed(evt);
            }
        });
        changePasswordPanel.add(btnReturnChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 320, 93, 37));

        btnSaveChangePassword.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveChangePassword.setText("Update");
        btnSaveChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChangePasswordActionPerformed(evt);
            }
        });
        changePasswordPanel.add(btnSaveChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 320, 93, 37));

        lblSuccessChangePassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessChangePassword.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessChangePassword.setText("Updated successfully!");
        changePasswordPanel.add(lblSuccessChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 375, -1, -1));

        profileMainPanel.setBackground(new java.awt.Color(235, 245, 251));
        profileMainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel23.setText("Contact Number");
        profileMainPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 132, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText(":");
        profileMainPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText(":");
        profileMainPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setText(":");
        profileMainPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel27.setText(":");
        profileMainPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel28.setText(":");
        profileMainPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, -1, -1));

        txtUserID.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtUserID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserIDActionPerformed(evt);
            }
        });
        profileMainPanel.add(txtUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 144, -1));

        txtName.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 144, -1));

        txtRole.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 144, -1));

        txtEmail.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 144, -1));

        txtGender.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 144, -1));

        txtICNumber.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtICNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 144, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel46.setText(":");
        profileMainPanel.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel47.setText("IC Number");
        profileMainPanel.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 87, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel48.setText("Region");
        profileMainPanel.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 132, -1));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel50.setText(":");
        profileMainPanel.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        txtState.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtState, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 144, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Name");
        profileMainPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 87, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("User ID");
        profileMainPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 87, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Account Security Status:");
        profileMainPanel.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Role");
        profileMainPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 87, -1));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel53.setText(":");
        profileMainPanel.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("E-mail");
        profileMainPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 132, -1));

        txtContactNumber.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtContactNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 144, -1));

        txtNationality.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtNationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 144, -1));

        btnEditProfile.setBackground(new java.awt.Color(255, 204, 204));
        btnEditProfile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditProfile.setText("Update Profile");
        btnEditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfileActionPerformed(evt);
            }
        });
        profileMainPanel.add(btnEditProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 333, 145, 39));

        btnSaveProfile.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveProfile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveProfile.setText("Save");
        btnSaveProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveProfileActionPerformed(evt);
            }
        });
        profileMainPanel.add(btnSaveProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 333, 145, 39));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel29.setText(":");
        profileMainPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, -1, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel30.setText("Password");
        profileMainPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 132, -1));

        lblTipsProfile.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblTipsProfile.setForeground(new java.awt.Color(255, 102, 102));
        lblTipsProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tips.png"))); // NOI18N
        lblTipsProfile.setText("Administrator information very important. Please do not make arbitrary changes!");
        profileMainPanel.add(lblTipsProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 292, 460, 29));

        lblSuccessProfile.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessProfile.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessProfile.setText("Updated successfully!");
        profileMainPanel.add(lblSuccessProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(234, 384, 160, 29));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel44.setText("Gender");
        profileMainPanel.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 87, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel45.setText(":");
        profileMainPanel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        btnSafetyQuestion.setBackground(new java.awt.Color(204, 204, 255));
        btnSafetyQuestion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSafetyQuestion.setText("Safety Question");
        btnSafetyQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafetyQuestionActionPerformed(evt);
            }
        });
        profileMainPanel.add(btnSafetyQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 333, 145, 39));

        txtAccSecurityStatus.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        txtAccSecurityStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAccSecurityStatus.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        profileMainPanel.add(txtAccSecurityStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 560, -1));

        jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel103.setText("Nationality");
        profileMainPanel.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 132, -1));

        btnPassword.setText("Change Password");
        btnPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasswordActionPerformed(evt);
            }
        });
        profileMainPanel.add(btnPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 140, -1));

        safetyQuestionPanel.setBackground(new java.awt.Color(235, 245, 251));
        safetyQuestionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel82.setText("Safety Question");
        safetyQuestionPanel.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 59, 140, -1));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel83.setText(":");
        safetyQuestionPanel.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 59, -1, -1));

        cboSafetyQuestion1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSafetyQuestion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSafetyQuestion1MouseClicked(evt);
            }
        });
        cboSafetyQuestion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSafetyQuestion1ActionPerformed(evt);
            }
        });
        safetyQuestionPanel.add(cboSafetyQuestion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 61, 318, -1));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel84.setText("Safety Question 1");
        safetyQuestionPanel.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 16, 170, -1));

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel91.setText("Answer");
        safetyQuestionPanel.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 95, 140, -1));

        jLabel97.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel97.setText(":");
        safetyQuestionPanel.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 95, -1, -1));

        txtAnswer1.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        safetyQuestionPanel.add(txtAnswer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 97, 318, -1));

        cboSafetyQuestion2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSafetyQuestion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboSafetyQuestion2MouseClicked(evt);
            }
        });
        cboSafetyQuestion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSafetyQuestion2ActionPerformed(evt);
            }
        });
        safetyQuestionPanel.add(cboSafetyQuestion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 193, 318, -1));

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel98.setText("Safety Question 2");
        safetyQuestionPanel.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 148, 170, -1));

        txtAnswer2.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        safetyQuestionPanel.add(txtAnswer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 229, 318, -1));

        jLabel99.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel99.setText("Safety Question");
        safetyQuestionPanel.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 191, 140, -1));

        jLabel100.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel100.setText(":");
        safetyQuestionPanel.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 191, -1, -1));

        jLabel101.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel101.setText("Answer");
        safetyQuestionPanel.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 227, 140, -1));

        jLabel102.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel102.setText(":");
        safetyQuestionPanel.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 227, -1, -1));

        btnEditSafetyQuestion.setBackground(new java.awt.Color(255, 204, 204));
        btnEditSafetyQuestion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditSafetyQuestion.setText("Update");
        btnEditSafetyQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSafetyQuestionActionPerformed(evt);
            }
        });
        safetyQuestionPanel.add(btnEditSafetyQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 335, 118, 39));

        btnSaveSafetyQuestion.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveSafetyQuestion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveSafetyQuestion.setText("Save");
        btnSaveSafetyQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSafetyQuestionActionPerformed(evt);
            }
        });
        safetyQuestionPanel.add(btnSaveSafetyQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 335, 118, 39));

        lblTipsSafetyQuestion.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblTipsSafetyQuestion.setForeground(new java.awt.Color(255, 102, 102));
        lblTipsSafetyQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tips.png"))); // NOI18N
        lblTipsSafetyQuestion.setText("Safety Question very important for reset password. Please do not make arbitrary changes!");
        safetyQuestionPanel.add(lblTipsSafetyQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 287, 507, -1));

        lblSuccessSafetyQuestion.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessSafetyQuestion.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessSafetyQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessSafetyQuestion.setText("Updated successfully!");
        safetyQuestionPanel.add(lblSuccessSafetyQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 392, -1, -1));

        btnReturnSafetyQuestion.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnSafetyQuestion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReturnSafetyQuestion.setText("Return");
        btnReturnSafetyQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnSafetyQuestionActionPerformed(evt);
            }
        });
        safetyQuestionPanel.add(btnReturnSafetyQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 335, 118, 39));

        javax.swing.GroupLayout showSpaceLayout = new javax.swing.GroupLayout(showSpace);
        showSpace.setLayout(showSpaceLayout);
        showSpaceLayout.setHorizontalGroup(
            showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(safetyQuestionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(showSpaceLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(profileMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(showSpaceLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(changePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        showSpaceLayout.setVerticalGroup(
            showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(safetyQuestionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(showSpaceLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(profileMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(showSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(showSpaceLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(changePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        ProfilePanel.add(showSpace);
        showSpace.setBounds(0, 70, 620, 430);

        AssistancePanel.setBackground(new java.awt.Color(235, 245, 251));

        jPanel3.setBackground(new java.awt.Color(52, 152, 219));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Assistance");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        dispenseMedicinesPanel.setBackground(new java.awt.Color(235, 245, 251));
        dispenseMedicinesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane7.setViewportView(lstPrescribeMedication2);

        dispenseMedicinesPanel.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 40, 577, 92));

        jLabel224.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel224.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel224.setText("Prescribe Medication");
        dispenseMedicinesPanel.add(jLabel224, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 14, 164, -1));

        jLabel247.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel247.setText("Prescribe Medication Format:");
        dispenseMedicinesPanel.add(jLabel247, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 144, 577, -1));

        jLabel248.setText("Medicine ID, Medicine Name, Quantity, Medication Frequency, Medication Timing");
        dispenseMedicinesPanel.add(jLabel248, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 166, 577, -1));

        jPanel33.setBackground(new java.awt.Color(235, 245, 251));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check Current Stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel27.setBackground(new java.awt.Color(235, 245, 251));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel234.setText("Medicine ID");
        jPanel27.add(jLabel234, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 96, -1));

        jLabel235.setText("Medicine Name");
        jPanel27.add(jLabel235, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 35, 96, -1));

        jLabel236.setText(":");
        jPanel27.add(jLabel236, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 7, -1, -1));

        jLabel237.setText(":");
        jPanel27.add(jLabel237, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 35, -1, -1));

        jLabel238.setText("Price");
        jPanel27.add(jLabel238, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 63, 96, -1));

        jLabel239.setText(":");
        jPanel27.add(jLabel239, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 63, -1, -1));

        jLabel240.setText("Current Stock");
        jPanel27.add(jLabel240, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 91, 96, -1));

        jLabel241.setText(":");
        jPanel27.add(jLabel241, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 91, -1, -1));

        lblMedicineID.setText("N/A");
        jPanel27.add(lblMedicineID, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 7, 196, -1));

        lblMedicineName.setText("N/A");
        jPanel27.add(lblMedicineName, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 35, 190, -1));

        lblMedicinePrice.setText("N/A");
        jPanel27.add(lblMedicinePrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 63, 196, -1));

        lblMedicineCurrentStock.setText("N/A");
        jPanel27.add(lblMedicineCurrentStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 91, 196, -1));

        cboMedicineID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMedicineID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMedicineIDActionPerformed(evt);
            }
        });

        jLabel232.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel232.setText(":");

        jLabel223.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel223.setText("Medicine ID");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel223, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel232)
                        .addGap(17, 17, 17)
                        .addComponent(cboMedicineID, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel223)
                    .addComponent(jLabel232)
                    .addComponent(cboMedicineID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dispenseMedicinesPanel.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 200, -1, 221));

        jPanel34.setBackground(new java.awt.Color(235, 245, 251));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dispense Medicines", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSuccessDispenseMedicines.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessDispenseMedicines.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessDispenseMedicines.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessDispenseMedicines.setText("Dispensed successfully!");
        jPanel34.add(lblSuccessDispenseMedicines, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 168, -1, -1));

        btnDispense.setBackground(new java.awt.Color(204, 255, 204));
        btnDispense.setText("Dispense");
        btnDispense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDispenseActionPerformed(evt);
            }
        });
        jPanel34.add(btnDispense, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 152, 35));

        btnDispenseMedicinesBack.setBackground(new java.awt.Color(204, 204, 255));
        btnDispenseMedicinesBack.setText("Return");
        btnDispenseMedicinesBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDispenseMedicinesBackActionPerformed(evt);
            }
        });
        jPanel34.add(btnDispenseMedicinesBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 152, 35));

        dispenseMedicinesPanel.add(jPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 200, 220, 220));

        viewMedicalReportPanel.setBackground(new java.awt.Color(235, 245, 251));

        btnReturnMedicalReoport.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnMedicalReoport.setText("Return");
        btnReturnMedicalReoport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnMedicalReoportActionPerformed(evt);
            }
        });

        medicalReportSlip.setBackground(new java.awt.Color(255, 255, 255));
        medicalReportSlip.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medical Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel242.setText("Doctor ID");

        lblDoctorID2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoctorID2.setText("N/A");

        jLabel243.setText(":");

        jLabel244.setText(":");

        lblDoctorName2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoctorName2.setText("N/A");

        jLabel245.setText("Doctor Name");

        jLabel249.setText("Date");

        jLabel250.setText(":");

        lblMedicalReportDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMedicalReportDate.setText("N/A");

        lblSpecialist.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSpecialist.setText("N/A");

        jLabel265.setText(":");

        jLabel266.setText("Specialist");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel245, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel242, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel243)
                        .addGap(18, 18, 18)
                        .addComponent(lblDoctorID2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel244)
                        .addGap(18, 18, 18)
                        .addComponent(lblDoctorName2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel266, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel249, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel265)
                        .addGap(18, 18, 18)
                        .addComponent(lblSpecialist, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel250)
                        .addGap(18, 18, 18)
                        .addComponent(lblMedicalReportDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel242)
                    .addComponent(jLabel243)
                    .addComponent(lblDoctorID2)
                    .addComponent(jLabel266)
                    .addComponent(jLabel265)
                    .addComponent(lblSpecialist))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel245)
                    .addComponent(jLabel244)
                    .addComponent(lblDoctorName2)
                    .addComponent(jLabel249)
                    .addComponent(jLabel250)
                    .addComponent(lblMedicalReportDate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblRegisterPersonNameAdmin3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRegisterPersonNameAdmin3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegisterPersonNameAdmin3.setText("Description");

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel257.setText("Medical Report ID");

        jLabel259.setText(":");

        lblMedicalReportID2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMedicalReportID2.setText("N/A");

        jLabel261.setText("Patient ID");

        jLabel262.setText(":");

        lblPatientID2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPatientID2.setText("N/A");

        lblPatientName2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPatientName2.setText("N/A");

        jLabel263.setText(":");

        jLabel264.setText("Patient Name");

        lblRegisterPersonNameAdmin8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRegisterPersonNameAdmin8.setText("Age");

        jLabel267.setText(":");

        lblPatientAge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPatientAge.setText("N/A");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel257, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel261, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel259)
                        .addGap(18, 18, 18)
                        .addComponent(lblMedicalReportID2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel262)
                        .addGap(18, 18, 18)
                        .addComponent(lblPatientID2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRegisterPersonNameAdmin8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel264, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel267)
                        .addGap(18, 18, 18)
                        .addComponent(lblPatientAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel263)
                        .addGap(18, 18, 18)
                        .addComponent(lblPatientName2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel263)
                            .addComponent(lblPatientName2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel267)
                            .addComponent(lblPatientAge)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel257)
                            .addComponent(jLabel259)
                            .addComponent(lblMedicalReportID2)
                            .addComponent(jLabel264))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel261)
                            .addComponent(jLabel262)
                            .addComponent(lblPatientID2)
                            .addComponent(lblRegisterPersonNameAdmin8))))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        areaPatientDescription.setColumns(20);
        areaPatientDescription.setRows(5);
        jScrollPane10.setViewportView(areaPatientDescription);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel251.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel251.setText(":");
        jPanel30.add(jLabel251, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        lblPrescriptionFee1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPrescriptionFee1.setText("N/A");
        jPanel30.add(lblPrescriptionFee1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 97, -1));

        jLabel252.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel252.setText("Fee");
        jPanel30.add(jLabel252, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 60, -1));

        lblRegisterPersonNameAdmin9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRegisterPersonNameAdmin9.setText("Gender");

        jLabel268.setText(":");

        lblPatientGender.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPatientGender.setText("N/A");

        lblRegisterPersonNameAdmin10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRegisterPersonNameAdmin10.setText("Blood Type");

        jLabel269.setText(":");

        lblPatientBloodType.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPatientBloodType.setText("N/A");

        lblPatientRegion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPatientRegion.setText("N/A");

        jLabel270.setText(":");

        lblRegisterPersonNameAdmin11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRegisterPersonNameAdmin11.setText("Region");

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel253.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel253.setText(":");
        jPanel31.add(jLabel253, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, -1));

        lblPaymentStatus2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPaymentStatus2.setText("N/A");
        jPanel31.add(lblPaymentStatus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 130, -1));

        jLabel254.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel254.setText("Payment Status");
        jPanel31.add(jLabel254, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 120, -1));

        lblDiasease.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiasease.setText("N/A");

        jLabel260.setText(":");

        jLabel258.setText("Diasease");

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Blood Test", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jScrollPane2.setViewportView(listBloodTest);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout medicalReportSlipLayout = new javax.swing.GroupLayout(medicalReportSlip);
        medicalReportSlip.setLayout(medicalReportSlipLayout);
        medicalReportSlipLayout.setHorizontalGroup(
            medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medicalReportSlipLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(medicalReportSlipLayout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(medicalReportSlipLayout.createSequentialGroup()
                        .addComponent(jLabel258, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel260)
                        .addGap(18, 18, 18)
                        .addComponent(lblDiasease, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(lblRegisterPersonNameAdmin3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medicalReportSlipLayout.createSequentialGroup()
                        .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(medicalReportSlipLayout.createSequentialGroup()
                                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRegisterPersonNameAdmin9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRegisterPersonNameAdmin11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRegisterPersonNameAdmin10, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(medicalReportSlipLayout.createSequentialGroup()
                                        .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel268)
                                            .addComponent(jLabel270))
                                        .addGap(18, 18, 18)
                                        .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPatientRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPatientGender, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(medicalReportSlipLayout.createSequentialGroup()
                                        .addComponent(jLabel269)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblPatientBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 77, Short.MAX_VALUE))
                            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        medicalReportSlipLayout.setVerticalGroup(
            medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicalReportSlipLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel258)
                    .addComponent(jLabel260)
                    .addComponent(lblDiasease)
                    .addComponent(lblRegisterPersonNameAdmin3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(medicalReportSlipLayout.createSequentialGroup()
                        .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(medicalReportSlipLayout.createSequentialGroup()
                                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel268)
                                    .addComponent(lblPatientGender))
                                .addGap(28, 28, 28)
                                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel270)
                                    .addComponent(lblPatientRegion)))
                            .addGroup(medicalReportSlipLayout.createSequentialGroup()
                                .addComponent(lblRegisterPersonNameAdmin9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblRegisterPersonNameAdmin10)
                                    .addComponent(jLabel269)
                                    .addComponent(lblPatientBloodType))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblRegisterPersonNameAdmin11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(medicalReportSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewMedicalReportPanelLayout = new javax.swing.GroupLayout(viewMedicalReportPanel);
        viewMedicalReportPanel.setLayout(viewMedicalReportPanelLayout);
        viewMedicalReportPanelLayout.setHorizontalGroup(
            viewMedicalReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMedicalReportPanelLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(medicalReportSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(viewMedicalReportPanelLayout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(btnReturnMedicalReoport, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewMedicalReportPanelLayout.setVerticalGroup(
            viewMedicalReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewMedicalReportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(medicalReportSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReturnMedicalReoport, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewPrescriptionPanel.setBackground(new java.awt.Color(235, 245, 251));

        prescriptionSlip.setBackground(new java.awt.Color(255, 255, 255));
        prescriptionSlip.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prescription", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel213.setText("Progression");

        lblProgression.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblProgression.setText("N/A");

        jLabel214.setText(":");

        jLabel222.setText(":");

        lblDispensedBy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDispensedBy.setText("N/A");

        jLabel215.setText("Dispensed By");

        jLabel228.setText("Dispensing Date");

        jLabel229.setText(":");

        lblDispensedDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDispensedDate.setText("N/A");

        jLabel226.setText("Payment Status");

        jLabel227.setText(":");

        lblPaymentStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPaymentStatus.setText("N/A");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel213, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel215, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel214)
                        .addGap(18, 18, 18)
                        .addComponent(lblProgression, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel222)
                        .addGap(18, 18, 18)
                        .addComponent(lblDispensedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel228, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(jLabel226, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel227)
                        .addGap(18, 18, 18)
                        .addComponent(lblPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel229)
                        .addGap(18, 18, 18)
                        .addComponent(lblDispensedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel213)
                    .addComponent(jLabel214)
                    .addComponent(lblProgression)
                    .addComponent(jLabel226)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel227)
                        .addComponent(lblPaymentStatus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel215)
                    .addComponent(jLabel222)
                    .addComponent(lblDispensedBy)
                    .addComponent(jLabel228)
                    .addComponent(jLabel229)
                    .addComponent(lblDispensedDate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(lstPrescribeMedication);

        jLabel211.setText(":");

        jLabel212.setText("Doctor Name");

        lblRegisterPersonNameAdmin2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRegisterPersonNameAdmin2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegisterPersonNameAdmin2.setText("Prescribe Medication");

        lblDoctorName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoctorName.setText("N/A");

        jLabel217.setText("Doctor ID");

        jLabel218.setText(":");

        lblDoctorID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDoctorID.setText("N/A");

        jLabel219.setText("Date");

        jLabel220.setText(":");

        lblPrescriptionDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPrescriptionDate.setText("N/A");

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel203.setText("Prescription ID");

        jLabel204.setText("Medical Report ID");

        jLabel205.setText(":");

        jLabel206.setText(":");

        lblPrescriptionID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPrescriptionID.setText("N/A");

        lblMedicalReportID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMedicalReportID.setText("N/A");

        jLabel207.setText("Patient ID");

        jLabel208.setText(":");

        lblPatientID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPatientID.setText("N/A");

        lblPatientName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPatientName.setText("N/A");

        jLabel209.setText(":");

        jLabel210.setText("Patient Name");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel204, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel203, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel206)
                        .addGap(18, 18, 18)
                        .addComponent(lblMedicalReportID, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel205)
                        .addGap(18, 18, 18)
                        .addComponent(lblPrescriptionID, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel210, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel207, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel209)
                        .addGap(18, 18, 18)
                        .addComponent(lblPatientName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel208)
                        .addGap(18, 18, 18)
                        .addComponent(lblPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel207)
                            .addComponent(jLabel208)
                            .addComponent(lblPatientID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel210)
                            .addComponent(jLabel209)
                            .addComponent(lblPatientName)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel203)
                            .addComponent(jLabel205)
                            .addComponent(lblPrescriptionID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel204)
                            .addComponent(jLabel206)
                            .addComponent(lblMedicalReportID))))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel230.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel230.setText(":");
        jPanel26.add(jLabel230, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        lblPrescriptionFee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPrescriptionFee.setText("N/A");
        jPanel26.add(lblPrescriptionFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 97, -1));

        jLabel231.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel231.setText("Fee");
        jPanel26.add(jLabel231, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 90, -1));

        javax.swing.GroupLayout prescriptionSlipLayout = new javax.swing.GroupLayout(prescriptionSlip);
        prescriptionSlip.setLayout(prescriptionSlipLayout);
        prescriptionSlipLayout.setHorizontalGroup(
            prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prescriptionSlipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescriptionSlipLayout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(prescriptionSlipLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(prescriptionSlipLayout.createSequentialGroup()
                                .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblRegisterPersonNameAdmin2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(prescriptionSlipLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel219, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel217, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel212, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(prescriptionSlipLayout.createSequentialGroup()
                                                .addComponent(jLabel220)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblPrescriptionDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(prescriptionSlipLayout.createSequentialGroup()
                                                .addComponent(jLabel218)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblDoctorID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(prescriptionSlipLayout.createSequentialGroup()
                                                .addComponent(jLabel211)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(prescriptionSlipLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(12, 12, 12)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        prescriptionSlipLayout.setVerticalGroup(
            prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prescriptionSlipLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblRegisterPersonNameAdmin2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(prescriptionSlipLayout.createSequentialGroup()
                        .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel217)
                            .addComponent(jLabel218)
                            .addComponent(lblDoctorID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel212)
                            .addComponent(jLabel211)
                            .addComponent(lblDoctorName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(prescriptionSlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel219)
                            .addComponent(jLabel220)
                            .addComponent(lblPrescriptionDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel216.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel216.setText("Prescription ID");

        jLabel221.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel221.setText(":");

        cboPrescriptionID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPrescriptionID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPrescriptionIDActionPerformed(evt);
            }
        });

        btnDispenseMedicines.setBackground(new java.awt.Color(204, 255, 204));
        btnDispenseMedicines.setText("Dispense Medicines Now");
        btnDispenseMedicines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDispenseMedicinesActionPerformed(evt);
            }
        });

        btnViewMedicalReport.setBackground(new java.awt.Color(255, 255, 204));
        btnViewMedicalReport.setText("View Medical Report");
        btnViewMedicalReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewMedicalReportActionPerformed(evt);
            }
        });

        lblPrescriptionTips.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblPrescriptionTips.setText("Medication not dispensed. Please dispense.");

        javax.swing.GroupLayout viewPrescriptionPanelLayout = new javax.swing.GroupLayout(viewPrescriptionPanel);
        viewPrescriptionPanel.setLayout(viewPrescriptionPanelLayout);
        viewPrescriptionPanelLayout.setHorizontalGroup(
            viewPrescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPrescriptionPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(prescriptionSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewPrescriptionPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(viewPrescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewPrescriptionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel216, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel221)
                        .addGap(18, 18, 18)
                        .addComponent(cboPrescriptionID, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPrescriptionTips, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(viewPrescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnViewMedicalReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDispenseMedicines, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        viewPrescriptionPanelLayout.setVerticalGroup(
            viewPrescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPrescriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(prescriptionSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(viewPrescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewPrescriptionPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnViewMedicalReport, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDispenseMedicines, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewPrescriptionPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(viewPrescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel216)
                            .addComponent(jLabel221)
                            .addComponent(cboPrescriptionID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPrescriptionTips)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout showSpace6Layout = new javax.swing.GroupLayout(showSpace6);
        showSpace6.setLayout(showSpace6Layout);
        showSpace6Layout.setHorizontalGroup(
            showSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewPrescriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dispenseMedicinesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(viewMedicalReportPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        showSpace6Layout.setVerticalGroup(
            showSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewPrescriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dispenseMedicinesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(viewMedicalReportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AssistancePanelLayout = new javax.swing.GroupLayout(AssistancePanel);
        AssistancePanel.setLayout(AssistancePanelLayout);
        AssistancePanelLayout.setHorizontalGroup(
            AssistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(showSpace6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AssistancePanelLayout.setVerticalGroup(
            AssistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssistancePanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showSpace6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RegisterStaffPanel.setBackground(new java.awt.Color(235, 245, 251));

        jPanel4.setBackground(new java.awt.Color(52, 152, 219));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Register User");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        showspace3.setBackground(new java.awt.Color(235, 245, 251));

        registerChoosePanel.setBackground(new java.awt.Color(235, 245, 251));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Register Administrator");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("System Manager");

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adminTeam.png"))); // NOI18N
        jLabel69.setText("jLabel69");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66)
                .addGap(21, 21, 21))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Register Hospital Staff");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("Doctor");

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DoctorNurse.png"))); // NOI18N
        jLabel68.setText("jLabel68");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel68)
                .addGap(6, 6, 6)
                .addComponent(jLabel61)
                .addGap(6, 6, 6)
                .addComponent(jLabel64)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setText("Register Hospital Patient");

        jLabel119.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel119.setText("Patient / User");

        jLabel120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/registerPatient.png"))); // NOI18N
        jLabel120.setText("jLabel120");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel120))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel110)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel119))
        );

        javax.swing.GroupLayout registerChoosePanelLayout = new javax.swing.GroupLayout(registerChoosePanel);
        registerChoosePanel.setLayout(registerChoosePanelLayout);
        registerChoosePanelLayout.setHorizontalGroup(
            registerChoosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerChoosePanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(registerChoosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(registerChoosePanelLayout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        registerChoosePanelLayout.setVerticalGroup(
            registerChoosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerChoosePanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(registerChoosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        registerPatientPanel.setBackground(new java.awt.Color(235, 245, 251));

        jLabel173.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel173.setText(":");

        btnRegisterPatient.setBackground(new java.awt.Color(204, 255, 204));
        btnRegisterPatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegisterPatient.setText("Register ");
        btnRegisterPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterPatientActionPerformed(evt);
            }
        });

        btnReturnRegisterPatient.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnRegisterPatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReturnRegisterPatient.setText("Return");
        btnReturnRegisterPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnRegisterPatientActionPerformed(evt);
            }
        });

        jLabel174.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel174.setText("Name");

        lblSuccessRegisterPatient.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessRegisterPatient.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessRegisterPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessRegisterPatient.setText(" Register successfully!");

        jLabel175.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel175.setText("IC Number");

        jLabel176.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel176.setText("Gender");

        cboGenderRegisterPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        registerSlip1.setBackground(new java.awt.Color(255, 255, 255));
        registerSlip1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register Slip", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        registerSlip1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel177.setText("Name");

        jLabel178.setText("Role");

        jLabel179.setText(":");

        jLabel180.setText(":");

        lblNamePatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNamePatient.setText("N/A");

        lblRolePatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRolePatient.setText("N/A");

        jLabel181.setText("User ID");

        jLabel182.setText(":");

        lblUserIDPatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUserIDPatient.setText("N/A");

        lblDefaultPasswordPatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDefaultPasswordPatient.setText("N/A");

        jLabel183.setText(":");

        jLabel184.setText("Default Password");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel178, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel180)
                        .addGap(18, 18, 18)
                        .addComponent(lblRolePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel177, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel179)
                        .addGap(18, 18, 18)
                        .addComponent(lblNamePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel184, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(jLabel181, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel183)
                        .addGap(18, 18, 18)
                        .addComponent(lblDefaultPasswordPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel182)
                        .addGap(18, 18, 18)
                        .addComponent(lblUserIDPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel181)
                            .addComponent(jLabel182)
                            .addComponent(lblUserIDPatient))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel184)
                            .addComponent(jLabel183)
                            .addComponent(lblDefaultPasswordPatient)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel177)
                            .addComponent(jLabel179)
                            .addComponent(lblNamePatient))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel178)
                            .addComponent(jLabel180)
                            .addComponent(lblRolePatient))))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        registerSlip1.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 28, 578, -1));

        jLabel185.setText(":");
        registerSlip1.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 102, -1, -1));

        jLabel186.setText("Issued By");
        registerSlip1.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 102, 75, -1));

        lblRegisterPersonNamePatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRegisterPersonNamePatient.setText("N/A");
        registerSlip1.add(lblRegisterPersonNamePatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 102, 125, -1));

        lblDatePatient.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDatePatient.setText("N/A");
        registerSlip1.add(lblDatePatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 102, 125, -1));

        jLabel187.setText(":");
        registerSlip1.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 102, -1, -1));

        jLabel188.setText("Date");
        registerSlip1.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 102, 75, -1));

        jLabel189.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel189.setText("Kindly Reminder: Once successful login account, please change default password and set safety question.");
        registerSlip1.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 551, -1));

        jLabel190.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel190.setText("THIS IS A COMPUTER GENERATED REGISTER SLIP. NO SIGNATURE IS REQUIRED");
        registerSlip1.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 153, 530, -1));

        jLabel191.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel191.setText("Race");

        jLabel192.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel192.setText("Region");

        jLabel193.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel193.setText("Contact Number");

        jLabel194.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel194.setText("Email");

        jLabel195.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel195.setText(":");

        jLabel196.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel196.setText(":");

        jLabel197.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel197.setText(":");

        jLabel198.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel198.setText(":");

        jLabel199.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel199.setText(":");

        jLabel200.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel200.setText(":");

        jLabel201.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel201.setText("Age");

        jLabel202.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel202.setText(":");

        javax.swing.GroupLayout registerPatientPanelLayout = new javax.swing.GroupLayout(registerPatientPanel);
        registerPatientPanel.setLayout(registerPatientPanelLayout);
        registerPatientPanelLayout.setHorizontalGroup(
            registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPatientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registerPatientPanelLayout.createSequentialGroup()
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel175, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel196)
                                .addGap(18, 18, 18)
                                .addComponent(txtICNumberRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPatientPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel176, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel197)
                                    .addGap(18, 18, 18)
                                    .addComponent(cboGenderRegisterPatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPatientPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel191, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel198)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtRaceRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(61, 61, 61)
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel193)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel200)
                                .addGap(17, 17, 17)
                                .addComponent(txtContactNumberRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel194, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel173)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmailRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel201, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel202)
                                    .addGap(17, 17, 17)
                                    .addComponent(txtAgeRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel192, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel199)
                                    .addGap(17, 17, 17)
                                    .addComponent(txtStateRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(registerPatientPanelLayout.createSequentialGroup()
                            .addComponent(jLabel174, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jLabel195)
                            .addGap(18, 18, 18)
                            .addComponent(txtNameRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(registerPatientPanelLayout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                    .addComponent(lblSuccessRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(213, 213, 213)
                                    .addComponent(btnReturnRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(registerSlip1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        registerPatientPanelLayout.setVerticalGroup(
            registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerPatientPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerPatientPanelLayout.createSequentialGroup()
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel174)
                            .addComponent(jLabel195)
                            .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNameRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerPatientPanelLayout.createSequentialGroup()
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel201)
                            .addComponent(jLabel202)
                            .addComponent(txtAgeRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registerPatientPanelLayout.createSequentialGroup()
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel175)
                            .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel196)
                                    .addComponent(txtICNumberRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16)
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel176)
                            .addGroup(registerPatientPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel197)
                                    .addComponent(cboGenderRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(14, 14, 14)
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel191)
                            .addComponent(jLabel198)
                            .addComponent(txtRaceRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(registerPatientPanelLayout.createSequentialGroup()
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel192)
                            .addComponent(jLabel199)
                            .addComponent(txtStateRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel193)
                            .addComponent(jLabel200)
                            .addComponent(txtContactNumberRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel194)
                            .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel173)
                                .addComponent(txtEmailRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(24, 24, 24)
                .addGroup(registerPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSuccessRegisterPatient)
                    .addComponent(btnRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturnRegisterPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(registerSlip1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        registerStaffPanel.setBackground(new java.awt.Color(255, 255, 204));

        staffPanel.setBackground(new java.awt.Color(235, 245, 251));

        jLabel166.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel166.setText(":");

        jLabel165.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel165.setText("Office");

        jLabel153.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel153.setText(":");

        jLabel122.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel122.setText("Age");

        cboGenderRegisterStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel115.setText("Personal Information");

        jPanel21.setBackground(new java.awt.Color(235, 245, 251));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegisterStaff.setBackground(new java.awt.Color(204, 255, 204));
        btnRegisterStaff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegisterStaff.setText("Register");
        btnRegisterStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterStaffActionPerformed(evt);
            }
        });
        jPanel21.add(btnRegisterStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 94, 32));

        btnReturnStaff.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnStaff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReturnStaff.setText("Return");
        btnReturnStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnStaffActionPerformed(evt);
            }
        });
        jPanel21.add(btnReturnStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 95, 32));

        lblSuccessRegisterStaff.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessRegisterStaff.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessRegisterStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessRegisterStaff.setText(" Register successfully!");
        jPanel21.add(lblSuccessRegisterStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        jLabel121.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel121.setText(":");

        btnSaveRegisterStaff.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveRegisterStaff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveRegisterStaff.setText("Save");
        btnSaveRegisterStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveRegisterStaffActionPerformed(evt);
            }
        });

        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel161.setText("Position Details");

        btnAddRegisterStaff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddRegisterStaff.setText("Add");
        btnAddRegisterStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRegisterStaffActionPerformed(evt);
            }
        });

        cboDayRegisterStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        cboDayRegisterStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDayRegisterStaffActionPerformed(evt);
            }
        });

        jLabel164.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel164.setText("Working Day");

        jLabel135.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel135.setText(":");

        jLabel134.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel134.setText("Region");

        cboSpecialistRegisterStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cardiology", "Neurology", "Ophthalmology", "Pediatrics" }));
        cboSpecialistRegisterStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSpecialistRegisterStaffActionPerformed(evt);
            }
        });

        jLabel123.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel123.setText("Gender");

        jLabel154.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel154.setText("Email");

        jLabel163.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel163.setText(":");

        txtNationalityRegisterStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel162.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel162.setText(":");

        jLabel152.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel152.setText("Contact Number");

        jLabel156.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel156.setText("Nationality");

        jLabel151.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel151.setText(":");

        jLabel155.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel155.setText("Name");

        jLabel116.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel116.setText(":");

        jLabel124.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel124.setText(":");

        jLabel125.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel125.setText(":");

        jLabel160.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel160.setText(":");

        jLabel126.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel126.setText("IC Number");

        jLabel159.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel159.setText("Specialist");

        jScrollPane5.setViewportView(lstWorkingDayRegisterStaff);

        javax.swing.GroupLayout staffPanelLayout = new javax.swing.GroupLayout(staffPanel);
        staffPanel.setLayout(staffPanelLayout);
        staffPanelLayout.setHorizontalGroup(
            staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(staffPanelLayout.createSequentialGroup()
                        .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(319, 319, 319))
                    .addGroup(staffPanelLayout.createSequentialGroup()
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel116)
                                .addGap(18, 18, 18)
                                .addComponent(txtNameRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel125)
                                .addGap(18, 18, 18)
                                .addComponent(txtICNumberRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel124)
                                .addGap(18, 18, 18)
                                .addComponent(cboGenderRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel121)
                                .addGap(18, 18, 18)
                                .addComponent(txtAgeRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel163))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(staffPanelLayout.createSequentialGroup()
                                        .addComponent(cboDayRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(btnAddRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnSaveRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel162)
                                .addGap(18, 18, 18)
                                .addComponent(txtNationalityRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel135)
                                .addGap(18, 18, 18)
                                .addComponent(txtStateRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel151)
                                .addGap(18, 18, 18)
                                .addComponent(txtContactNumberRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel153)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmailRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel165, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel166)
                                .addGap(18, 18, 18)
                                .addComponent(txtOfficeRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel160)
                                .addGap(18, 18, 18)
                                .addComponent(cboSpecialistRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        staffPanelLayout.setVerticalGroup(
            staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staffPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(staffPanelLayout.createSequentialGroup()
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel156)
                            .addComponent(jLabel162)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNationalityRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel134)
                            .addComponent(jLabel135)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtStateRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel152)
                            .addComponent(jLabel151)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtContactNumberRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel154)
                            .addComponent(jLabel153)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtEmailRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel159)
                            .addComponent(jLabel160)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(cboSpecialistRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel165)
                            .addComponent(jLabel166)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtOfficeRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(staffPanelLayout.createSequentialGroup()
                        .addComponent(jLabel115)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel155)
                            .addComponent(jLabel116)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNameRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel126)
                            .addComponent(jLabel125)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtICNumberRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel123)
                            .addComponent(jLabel124)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(cboGenderRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel122)
                            .addComponent(jLabel121)
                            .addGroup(staffPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtAgeRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel161)
                        .addGap(18, 18, 18)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel164)
                            .addComponent(jLabel163)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(staffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboDayRegisterStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddRegisterStaff))
                        .addGap(12, 12, 12)
                        .addComponent(btnSaveRegisterStaff)
                        .addGap(42, 42, 42)))
                .addGap(14, 14, 14))
        );

        Slip.setBackground(new java.awt.Color(235, 245, 251));

        Slip1.setBackground(new java.awt.Color(255, 255, 255));
        Slip1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Official Onboarding Slip", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel127.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel127.setText("Kindly Reminder: Once successful login account, please change default password and set safety question.");

        jLabel128.setText("Issued By");

        jLabel129.setText("Date");

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel130.setText(":");

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel131.setText(":");

        lblRegisterPersonNameSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRegisterPersonNameSlip.setText("N/A");

        lblDateSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDateSlip.setText("N/A");

        jLabel132.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel132.setText("*Please complete your personal information in the Profile section after logging in.");

        jLabel136.setFont(new java.awt.Font("Segoe Script", 3, 12)); // NOI18N
        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel136.setText("Pacific Hospital");

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText("Pacific Hospital Medical Center");

        lblSlipTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSlipTitle.setText("Congratulations Mr. / Ms. / Dr.");

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel133.setText(":");

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel137.setText(":");

        jLabel138.setText("Name");

        jLabel139.setText("IC Number");

        jLabel141.setText("User ID");

        jLabel142.setText("Default Password");

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel143.setText(":");

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel144.setText(":");

        lblNameSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNameSlip.setText("N/A");

        lblICNumberSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblICNumberSlip.setText("N/A");

        lblUserIDSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUserIDSlip.setText("N/A");

        lblDefaultPasswordSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDefaultPasswordSlip.setText("N/A");

        jLabel145.setText("Role");

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel146.setText(":");

        lblRoleSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRoleSlip.setText("N/A");

        jLabel147.setText("Spacialist");

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel148.setText(":");

        lblSpecialistSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSpecialistSlip.setText("N/A");

        jLabel149.setText("Working Day");

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel150.setText(":");

        lblWorkingDaySlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblWorkingDaySlip.setText("N/A");

        jLabel171.setText("Office");

        jLabel172.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel172.setText(":");

        lblOfficeSlip.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblOfficeSlip.setText("N/A");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel138, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel139, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jLabel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel146)
                                .addGap(18, 18, 18)
                                .addComponent(lblRoleSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel24Layout.createSequentialGroup()
                                    .addComponent(jLabel137)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblNameSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                                    .addComponent(jLabel133)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblICNumberSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel142, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel148)
                                .addGap(18, 18, 18)
                                .addComponent(lblSpecialistSlip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel143)
                                .addGap(18, 18, 18)
                                .addComponent(lblUserIDSlip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel144)
                                .addGap(18, 18, 18)
                                .addComponent(lblDefaultPasswordSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel171, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel149, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel150)
                                .addGap(18, 18, 18)
                                .addComponent(lblWorkingDaySlip, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(jLabel172)
                                .addGap(18, 18, 18)
                                .addComponent(lblOfficeSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel137)
                                .addComponent(lblNameSlip))
                            .addComponent(jLabel138))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel139)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel133)
                                .addComponent(lblICNumberSlip)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel145, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel146)
                                .addComponent(lblRoleSlip))))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel143)
                                .addComponent(lblUserIDSlip))
                            .addComponent(jLabel141))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel144)
                                .addComponent(lblDefaultPasswordSlip))
                            .addComponent(jLabel142))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel148)
                                .addComponent(lblSpecialistSlip))
                            .addComponent(jLabel147, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel171)
                    .addComponent(jLabel172)
                    .addComponent(lblOfficeSlip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel149)
                    .addComponent(jLabel150)
                    .addComponent(lblWorkingDaySlip))
                .addContainerGap())
        );

        jLabel167.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel167.setText("We are pleased to welcome you to Pacific Hospital Medical Center. ");

        jLabel168.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel168.setText("We look forward to working successfully with you and seeing you showcase your talents!");

        javax.swing.GroupLayout Slip1Layout = new javax.swing.GroupLayout(Slip1);
        Slip1.setLayout(Slip1Layout);
        Slip1Layout.setHorizontalGroup(
            Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Slip1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel127, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                    .addComponent(jLabel132, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Slip1Layout.createSequentialGroup()
                        .addGroup(Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel167, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel168, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSlipTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Slip1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Slip1Layout.createSequentialGroup()
                                .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel130)
                                .addGap(18, 18, 18)
                                .addComponent(lblRegisterPersonNameSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Slip1Layout.createSequentialGroup()
                                .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel131)
                                .addGap(18, 18, 18)
                                .addComponent(lblDateSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        Slip1Layout.setVerticalGroup(
            Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Slip1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSlipTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel167)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel168)
                .addGap(18, 18, 18)
                .addComponent(jLabel127)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel132)
                .addGap(18, 18, 18)
                .addGroup(Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Slip1Layout.createSequentialGroup()
                        .addGroup(Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel128)
                            .addComponent(jLabel130)
                            .addComponent(lblRegisterPersonNameSlip))
                        .addGap(11, 11, 11)
                        .addGroup(Slip1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel129)
                            .addComponent(jLabel131)
                            .addComponent(lblDateSlip)))
                    .addGroup(Slip1Layout.createSequentialGroup()
                        .addComponent(jLabel136)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel140)))
                .addContainerGap())
        );

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("X");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel106.setText("Below is the Official Onboarding Slip, can take a screenshot and send it to that person.");
        jLabel106.setIconTextGap(8);

        javax.swing.GroupLayout SlipLayout = new javax.swing.GroupLayout(Slip);
        Slip.setLayout(SlipLayout);
        SlipLayout.setHorizontalGroup(
            SlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SlipLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(SlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Slip1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SlipLayout.createSequentialGroup()
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        SlipLayout.setVerticalGroup(
            SlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SlipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SlipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel106))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(Slip1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout registerStaffPanelLayout = new javax.swing.GroupLayout(registerStaffPanel);
        registerStaffPanel.setLayout(registerStaffPanelLayout);
        registerStaffPanelLayout.setHorizontalGroup(
            registerStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Slip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(registerStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(staffPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        registerStaffPanelLayout.setVerticalGroup(
            registerStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Slip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(registerStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(staffPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        registerAdminPanel.setBackground(new java.awt.Color(235, 245, 251));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel70.setText("Name");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel78.setText("IC Number");

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel79.setText("Gender");

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel80.setText("Nationality");

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel81.setText("Region");

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel85.setText("Contact Number");

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel86.setText("Email");

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel87.setText(":");

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel88.setText(":");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel89.setText(":");

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel90.setText(":");

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel92.setText(":");

        jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel94.setText(":");

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel95.setText(":");

        txtNationalityRegisterAdmin.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        btnRegisterAdmin.setBackground(new java.awt.Color(204, 255, 204));
        btnRegisterAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegisterAdmin.setText("Register ");
        btnRegisterAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterAdminActionPerformed(evt);
            }
        });

        btnReturnAdmin.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReturnAdmin.setText("Return");
        btnReturnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnAdminActionPerformed(evt);
            }
        });

        lblSuccessRegisterAdmin.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessRegisterAdmin.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessRegisterAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessRegisterAdmin.setText(" Register successfully!");

        cboGenderRegisterAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        registerSlip.setBackground(new java.awt.Color(255, 255, 255));
        registerSlip.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register Slip", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        registerSlip.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel49.setText("Name");

        jLabel71.setText("Role");

        jLabel96.setText(":");

        jLabel104.setText(":");

        lblNameAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNameAdmin.setText("N/A");

        lblRoleAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRoleAdmin.setText("N/A");

        jLabel107.setText("User ID");

        jLabel108.setText(":");

        lblUserIDAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUserIDAdmin.setText("N/A");

        lblDefaultPasswordAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDefaultPasswordAdmin.setText("N/A");

        jLabel111.setText(":");

        jLabel112.setText("Default Password");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel104)
                        .addGap(18, 18, 18)
                        .addComponent(lblRoleAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel96)
                        .addGap(18, 18, 18)
                        .addComponent(lblNameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel111)
                        .addGap(18, 18, 18)
                        .addComponent(lblDefaultPasswordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel108)
                        .addGap(18, 18, 18)
                        .addComponent(lblUserIDAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel107)
                            .addComponent(jLabel108)
                            .addComponent(lblUserIDAdmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel112)
                            .addComponent(jLabel111)
                            .addComponent(lblDefaultPasswordAdmin)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel96)
                            .addComponent(lblNameAdmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(jLabel104)
                            .addComponent(lblRoleAdmin))))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        registerSlip.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 28, 578, -1));

        jLabel114.setText(":");
        registerSlip.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 102, -1, -1));

        jLabel113.setText("Issued By");
        registerSlip.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 102, 75, -1));

        lblRegisterPersonNameAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRegisterPersonNameAdmin.setText("N/A");
        registerSlip.add(lblRegisterPersonNameAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 102, 125, -1));

        lblDateAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDateAdmin.setText("N/A");
        registerSlip.add(lblDateAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 102, 125, -1));

        jLabel117.setText(":");
        registerSlip.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 102, -1, -1));

        jLabel118.setText("Date");
        registerSlip.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 102, 75, -1));

        jLabel169.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel169.setText("Kindly Reminder: Once successful login account, please change default password and set safety question.");
        registerSlip.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 551, -1));

        jLabel170.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel170.setText("THIS IS A COMPUTER GENERATED REGISTER SLIP. NO SIGNATURE IS REQUIRED");
        registerSlip.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 153, 530, -1));

        javax.swing.GroupLayout registerAdminPanelLayout = new javax.swing.GroupLayout(registerAdminPanel);
        registerAdminPanel.setLayout(registerAdminPanelLayout);
        registerAdminPanelLayout.setHorizontalGroup(
            registerAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerAdminPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel87)
                .addGap(18, 18, 18)
                .addComponent(txtNameRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel92)
                .addGap(17, 17, 17)
                .addComponent(txtStateRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(registerAdminPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel88)
                .addGap(18, 18, 18)
                .addComponent(txtIcNumberRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel85)
                .addGap(4, 4, 4)
                .addComponent(jLabel94)
                .addGap(17, 17, 17)
                .addComponent(txtContactNumberRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(registerAdminPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel89)
                .addGap(22, 22, 22)
                .addComponent(cboGenderRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel95)
                .addGap(17, 17, 17)
                .addComponent(txtEmailRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(registerAdminPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel90)
                .addGap(18, 18, 18)
                .addComponent(txtNationalityRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnReturnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(registerAdminPanelLayout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(lblSuccessRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(registerAdminPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(registerSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        registerAdminPanelLayout.setVerticalGroup(
            registerAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerAdminPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(registerAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70)
                    .addComponent(jLabel87)
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtNameRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel81))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel92))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtStateRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(registerAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78)
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel88))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtIcNumberRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel85))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel94))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtContactNumberRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(registerAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79)
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel89))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cboGenderRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel86))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel95))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtEmailRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(registerAdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel80)
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel90))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtNationalityRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnRegisterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerAdminPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnReturnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(lblSuccessRegisterAdmin)
                .addGap(20, 20, 20)
                .addComponent(registerSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout showspace3Layout = new javax.swing.GroupLayout(showspace3);
        showspace3.setLayout(showspace3Layout);
        showspace3Layout.setHorizontalGroup(
            showspace3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registerChoosePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showspace3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(registerStaffPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showspace3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(registerAdminPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showspace3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(showspace3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(registerPatientPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        showspace3Layout.setVerticalGroup(
            showspace3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registerChoosePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showspace3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(registerStaffPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showspace3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(registerAdminPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(showspace3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(showspace3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(registerPatientPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout RegisterStaffPanelLayout = new javax.swing.GroupLayout(RegisterStaffPanel);
        RegisterStaffPanel.setLayout(RegisterStaffPanelLayout);
        RegisterStaffPanelLayout.setHorizontalGroup(
            RegisterStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(showspace3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RegisterStaffPanelLayout.setVerticalGroup(
            RegisterStaffPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegisterStaffPanelLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showspace3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        UserManagementPanel.setBackground(new java.awt.Color(235, 245, 251));

        jPanel12.setBackground(new java.awt.Color(52, 152, 219));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("User Management");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        ShowSpace6.setBackground(new java.awt.Color(235, 245, 251));

        UpdatePasswordPanel.setBackground(new java.awt.Color(235, 245, 251));
        UpdatePasswordPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblUpdatePassword.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUpdatePassword.setRowHeight(30);
        tblUpdatePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUpdatePasswordMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblUpdatePassword);

        UpdatePasswordPanel.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 15, 618, 171));

        jPanel48.setBackground(new java.awt.Color(235, 245, 251));
        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnUpdateUserPassword.setBackground(new java.awt.Color(255, 204, 204));
        btnUpdateUserPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateUserPassword.setText("Update User Password");
        btnUpdateUserPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUserPasswordActionPerformed(evt);
            }
        });

        btnSaveUpdatePassword.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveUpdatePassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveUpdatePassword.setText("Save");
        btnSaveUpdatePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUpdatePasswordActionPerformed(evt);
            }
        });

        btnReturnUpdatePassword.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnUpdatePassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReturnUpdatePassword.setText("Return");
        btnReturnUpdatePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnUpdatePasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReturnUpdatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnUpdateUserPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSaveUpdatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpdateUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaveUpdatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReturnUpdatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        UpdatePasswordPanel.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 238, -1, 172));

        chbCondition2.setBackground(new java.awt.Color(235, 245, 251));
        chbCondition2.setText("   Clear, understood and agreed.");
        UpdatePasswordPanel.add(chbCondition2, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 309, 264, -1));

        chbCondition1.setBackground(new java.awt.Color(235, 245, 251));
        chbCondition1.setText("   Please do not disclose the user's password.");
        chbCondition1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbCondition1ActionPerformed(evt);
            }
        });
        UpdatePasswordPanel.add(chbCondition1, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 283, 294, -1));

        lblSuccessUpdatePassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessUpdatePassword.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessUpdatePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessUpdatePassword.setText("Updated successfully!");
        UpdatePasswordPanel.add(lblSuccessUpdatePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 160, -1));

        lblTipsUpdatePassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblTipsUpdatePassword.setForeground(new java.awt.Color(102, 102, 255));
        lblTipsUpdatePassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipsUpdatePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tips2.png"))); // NOI18N
        lblTipsUpdatePassword.setText("Hint: Double-click a cell in the table to edit the contents. Only User Password column can be modify.");
        UpdatePasswordPanel.add(lblTipsUpdatePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 610, -1));

        ManageAdminPanel.setBackground(new java.awt.Color(235, 245, 251));
        ManageAdminPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel40.setBackground(new java.awt.Color(235, 245, 251));
        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnReturnAdmin1.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnAdmin1.setText("Return");
        btnReturnAdmin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnAdmin1ActionPerformed(evt);
            }
        });

        btnUpdateRecordAdmin.setBackground(new java.awt.Color(255, 255, 204));
        btnUpdateRecordAdmin.setText("Update Record");
        btnUpdateRecordAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordAdminActionPerformed(evt);
            }
        });

        btnSaveAdmin.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveAdmin.setText("Save");
        btnSaveAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAdminActionPerformed(evt);
            }
        });

        jLabel291.setText("User ID");

        jLabel292.setText(":");

        cboAdminID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboAdminID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAdminIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel293)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel291, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel292)
                        .addGap(18, 18, 18)
                        .addComponent(cboAdminID, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdateRecordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnSaveAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnReturnAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel293))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel291)
                            .addComponent(jLabel292)
                            .addComponent(cboAdminID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateRecordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturnAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        ManageAdminPanel.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 550, 138));

        jPanel38.setBackground(new java.awt.Color(235, 245, 251));
        jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel284.setText("User ID");

        jLabel285.setText(":");

        lblAdminUserID.setText("N/A");

        jLabel287.setText("User Role");

        jLabel288.setText(":");

        lblAdminRole.setText("N/A");

        jLabel300.setText("IC Number");

        jLabel301.setText(":");

        lblAdminIcNumber.setText("N/A");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel284, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel285)
                        .addGap(18, 18, 18)
                        .addComponent(lblAdminUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel287, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel288)
                        .addGap(18, 18, 18)
                        .addComponent(lblAdminRole, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel300, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel301)
                        .addGap(18, 18, 18)
                        .addComponent(lblAdminIcNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel284)
                    .addComponent(jLabel285)
                    .addComponent(lblAdminUserID)
                    .addComponent(jLabel287)
                    .addComponent(jLabel288)
                    .addComponent(lblAdminRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel300)
                    .addComponent(jLabel301)
                    .addComponent(lblAdminIcNumber))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        ManageAdminPanel.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 6, -1, -1));

        jLabel290.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel290.setText("User Name");
        ManageAdminPanel.add(jLabel290, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 105, 109, -1));

        jLabel294.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel294.setText(":");
        ManageAdminPanel.add(jLabel294, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 105, 4, -1));

        txtUserNameAdmin.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtUserNameAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameAdminActionPerformed(evt);
            }
        });
        ManageAdminPanel.add(txtUserNameAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 105, 122, -1));

        jLabel296.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel296.setText("Nationality");
        ManageAdminPanel.add(jLabel296, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 145, 109, -1));

        jLabel297.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel297.setText(":");
        ManageAdminPanel.add(jLabel297, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 145, 4, -1));

        txtNationalityAdmin.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtNationalityAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNationalityAdminActionPerformed(evt);
            }
        });
        ManageAdminPanel.add(txtNationalityAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 145, 122, -1));

        jLabel298.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel298.setText("Region");
        ManageAdminPanel.add(jLabel298, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 185, 109, -1));

        jLabel299.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel299.setText(":");
        ManageAdminPanel.add(jLabel299, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 185, 4, -1));

        txtRegionAdmin.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtRegionAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegionAdminActionPerformed(evt);
            }
        });
        ManageAdminPanel.add(txtRegionAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 185, 122, -1));

        jLabel302.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel302.setText("Contact Number");
        ManageAdminPanel.add(jLabel302, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 105, 115, -1));

        jLabel303.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel303.setText(":");
        ManageAdminPanel.add(jLabel303, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 105, 4, -1));

        txtContactNumberAdmin.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtContactNumberAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactNumberAdminActionPerformed(evt);
            }
        });
        ManageAdminPanel.add(txtContactNumberAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 105, 140, -1));

        jLabel304.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel304.setText("Email");
        ManageAdminPanel.add(jLabel304, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 145, 115, -1));

        jLabel305.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel305.setText(":");
        ManageAdminPanel.add(jLabel305, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 145, 4, -1));

        txtEmailAdmin.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtEmailAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailAdminActionPerformed(evt);
            }
        });
        ManageAdminPanel.add(txtEmailAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 145, 140, -1));

        lblSuccessUpdateRecordAdmin.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessUpdateRecordAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessUpdateRecordAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessUpdateRecordAdmin.setText("Successfully Updated.");
        ManageAdminPanel.add(lblSuccessUpdateRecordAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 210, -1));

        ManageStaffPanel.setBackground(new java.awt.Color(235, 245, 251));
        ManageStaffPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel41.setBackground(new java.awt.Color(235, 245, 251));
        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnReturnStaff1.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnStaff1.setText("Return");
        btnReturnStaff1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnStaff1ActionPerformed(evt);
            }
        });

        btnUpdateRecordStaff.setBackground(new java.awt.Color(255, 255, 204));
        btnUpdateRecordStaff.setText("Update Record");
        btnUpdateRecordStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordStaffActionPerformed(evt);
            }
        });

        btnSaveStaff.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveStaff.setText("Save");
        btnSaveStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveStaffActionPerformed(evt);
            }
        });

        jLabel309.setText("User ID");

        jLabel310.setText(":");

        cboStaffID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboStaffID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboStaffIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel307)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel309, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel310)
                        .addGap(18, 18, 18)
                        .addComponent(cboStaffID, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdateRecordStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnSaveStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnReturnStaff1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel307))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel309)
                            .addComponent(jLabel310)
                            .addComponent(cboStaffID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateRecordStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaveStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturnStaff1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        ManageStaffPanel.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 550, 138));

        jPanel39.setBackground(new java.awt.Color(235, 245, 251));
        jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel311.setText("User ID");

        jLabel312.setText(":");

        lblStaffUserID.setText("N/A");

        jLabel314.setText("User Role");

        jLabel315.setText(":");

        lblStaffRole.setText("N/A");

        jLabel317.setText("IC Number");

        jLabel318.setText(":");

        lblStaffIcNumber.setText("N/A");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel311, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel312)
                        .addGap(18, 18, 18)
                        .addComponent(lblStaffUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel314, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel315)
                        .addGap(18, 18, 18)
                        .addComponent(lblStaffRole, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel317, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel318)
                        .addGap(18, 18, 18)
                        .addComponent(lblStaffIcNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel311)
                    .addComponent(jLabel312)
                    .addComponent(lblStaffUserID)
                    .addComponent(jLabel314)
                    .addComponent(jLabel315)
                    .addComponent(lblStaffRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel317)
                    .addComponent(jLabel318)
                    .addComponent(lblStaffIcNumber))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        ManageStaffPanel.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 6, -1, -1));

        showSpace7.setBackground(new java.awt.Color(235, 245, 251));

        positionInformationPanel.setBackground(new java.awt.Color(235, 245, 251));
        positionInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Position Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel334.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel334.setText("Specialist");

        jLabel335.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel335.setText(":");

        cboSpecialistStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cardiology", "Neurology", "Ophthalmology", "Pediatrics" }));

        jLabel338.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel338.setText("Working Day");

        jLabel339.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel339.setText(":");

        lstWorkingDayStaff.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(lstWorkingDayStaff);

        jLabel336.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel336.setText("Office");

        jLabel337.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel337.setText(":");

        txtOfficeStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        btnClearWorkingDayStaff.setBackground(new java.awt.Color(255, 204, 204));
        btnClearWorkingDayStaff.setText("Clear");
        btnClearWorkingDayStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearWorkingDayStaffActionPerformed(evt);
            }
        });

        jLabel340.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel340.setText("Day");

        jLabel341.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel341.setText(":");

        cboDayStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));

        btnAddWorkingDayStaff.setBackground(new java.awt.Color(204, 204, 255));
        btnAddWorkingDayStaff.setText("Add");
        btnAddWorkingDayStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWorkingDayStaffActionPerformed(evt);
            }
        });

        btnSaveWorkingDayStaff.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveWorkingDayStaff.setText("Save");
        btnSaveWorkingDayStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveWorkingDayStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout positionInformationPanelLayout = new javax.swing.GroupLayout(positionInformationPanel);
        positionInformationPanel.setLayout(positionInformationPanelLayout);
        positionInformationPanelLayout.setHorizontalGroup(
            positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(positionInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(positionInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel334, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel335)
                        .addGap(18, 18, 18)
                        .addComponent(cboSpecialistStaff, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(positionInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel338, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel339)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(positionInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel336, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel337)
                        .addGap(18, 18, 18)
                        .addComponent(txtOfficeStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(positionInformationPanelLayout.createSequentialGroup()
                            .addComponent(jLabel340, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel341)
                            .addGap(24, 24, 24)
                            .addComponent(cboDayStaff, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(positionInformationPanelLayout.createSequentialGroup()
                            .addComponent(btnClearWorkingDayStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnAddWorkingDayStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnSaveWorkingDayStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );
        positionInformationPanelLayout.setVerticalGroup(
            positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(positionInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel334)
                    .addComponent(jLabel335)
                    .addComponent(cboSpecialistStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel336)
                    .addComponent(jLabel337)
                    .addComponent(txtOfficeStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel338)
                        .addComponent(jLabel339))
                    .addGroup(positionInformationPanelLayout.createSequentialGroup()
                        .addGroup(positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClearWorkingDayStaff)
                            .addComponent(btnAddWorkingDayStaff)
                            .addComponent(btnSaveWorkingDayStaff))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(positionInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel340)
                            .addComponent(jLabel341)
                            .addComponent(cboDayStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        personalInformationPanel.setBackground(new java.awt.Color(235, 245, 251));
        personalInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel320.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel320.setText("User Name");

        jLabel321.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel321.setText(":");

        txtUserNameStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel322.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel322.setText("Nationality");

        jLabel323.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel323.setText(":");

        txtNationalityStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel324.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel324.setText("Region");

        jLabel325.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel325.setText(":");

        txtRegionStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel326.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel326.setText("Qualification");

        jLabel327.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel327.setText(":");

        txtQualificationStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel328.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel328.setText("Contact Number");

        jLabel329.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel329.setText(":");

        txtContactNumberStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel330.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel330.setText("Email");

        jLabel331.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel331.setText(":");

        txtEmailStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        jLabel332.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel332.setText("Age");

        jLabel333.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel333.setText(":");

        txtAgeStaff.setDisabledTextColor(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout personalInformationPanelLayout = new javax.swing.GroupLayout(personalInformationPanel);
        personalInformationPanel.setLayout(personalInformationPanelLayout);
        personalInformationPanelLayout.setHorizontalGroup(
            personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel326, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel327)
                        .addGap(18, 18, 18)
                        .addComponent(txtQualificationStaff))
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel320, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel321)
                        .addGap(18, 18, 18)
                        .addComponent(txtUserNameStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel332, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel333)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAgeStaff))
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel322, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel323)
                        .addGap(18, 18, 18)
                        .addComponent(txtNationalityStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel328, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel329)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtContactNumberStaff))
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel324, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel325)
                        .addGap(18, 18, 18)
                        .addComponent(txtRegionStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel330, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel331)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmailStaff, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                .addContainerGap())
        );
        personalInformationPanelLayout.setVerticalGroup(
            personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel332)
                        .addComponent(jLabel333)
                        .addComponent(txtAgeStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel320)
                        .addComponent(jLabel321)
                        .addComponent(txtUserNameStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel328)
                        .addComponent(jLabel329)
                        .addComponent(txtContactNumberStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel322)
                        .addComponent(jLabel323)
                        .addComponent(txtNationalityStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel330)
                        .addComponent(jLabel331)
                        .addComponent(txtEmailStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel324)
                        .addComponent(jLabel325)
                        .addComponent(txtRegionStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel326)
                    .addComponent(jLabel327)
                    .addComponent(txtQualificationStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout showSpace7Layout = new javax.swing.GroupLayout(showSpace7);
        showSpace7.setLayout(showSpace7Layout);
        showSpace7Layout.setHorizontalGroup(
            showSpace7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personalInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpace7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(positionInformationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        showSpace7Layout.setVerticalGroup(
            showSpace7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personalInformationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpace7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(positionInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ManageStaffPanel.add(showSpace7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 84, -1, -1));

        btnPrevious.setBackground(new java.awt.Color(153, 255, 204));
        btnPrevious.setText("<");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        ManageStaffPanel.add(btnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 50, -1));

        btnNext.setBackground(new java.awt.Color(153, 255, 204));
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        ManageStaffPanel.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 50, -1));

        lblSuccessUpdateRecordStaff.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessUpdateRecordStaff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessUpdateRecordStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessUpdateRecordStaff.setText("Successfully Updated.");
        ManageStaffPanel.add(lblSuccessUpdateRecordStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 170, -1));

        ManagePatientPanel.setBackground(new java.awt.Color(235, 245, 251));
        ManagePatientPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel42.setBackground(new java.awt.Color(235, 245, 251));
        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnReturnPatient.setBackground(new java.awt.Color(204, 204, 255));
        btnReturnPatient.setText("Return");
        btnReturnPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnPatientActionPerformed(evt);
            }
        });

        btnUpdateRecordPatient.setBackground(new java.awt.Color(255, 255, 204));
        btnUpdateRecordPatient.setText("Update Record");
        btnUpdateRecordPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordPatientActionPerformed(evt);
            }
        });

        btnSavePatient.setBackground(new java.awt.Color(204, 255, 204));
        btnSavePatient.setText("Save");
        btnSavePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePatientActionPerformed(evt);
            }
        });

        jLabel319.setText("User ID");

        jLabel342.setText(":");

        cboPatientID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User ID" }));
        cboPatientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPatientIDActionPerformed(evt);
            }
        });

        jLabel376.setText("Role");

        jLabel377.setText(":");

        cboPatientRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Patient", "Children" }));
        cboPatientRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPatientRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel376, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel377)
                        .addGap(18, 18, 18)
                        .addComponent(cboPatientRole, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel319, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel342)
                        .addGap(18, 18, 18)
                        .addComponent(cboPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel313))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(btnUpdateRecordPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnSavePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnReturnPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel313))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel376)
                                .addComponent(jLabel377)
                                .addComponent(cboPatientRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel319)
                                .addComponent(jLabel342)
                                .addComponent(cboPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateRecordPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSavePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturnPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        ManagePatientPanel.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 550, 138));

        lblSuccessUpdateRecordPatient.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessUpdateRecordPatient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessUpdateRecordPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessUpdateRecordPatient.setText("Successfully Updated.");
        ManagePatientPanel.add(lblSuccessUpdateRecordPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 210, -1));

        childrenPanel.setBackground(new java.awt.Color(235, 245, 251));

        txtRegionChildren.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtRegionChildren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegionChildrenActionPerformed(evt);
            }
        });

        jLabel358.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel358.setText(":");

        jLabel359.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel359.setText(":");

        jLabel360.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel360.setText("Region");

        jLabel361.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel361.setText("Age");

        jLabel362.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel362.setText(":");

        jLabel363.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel363.setText("Blood Type");

        jLabel364.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel364.setText("Children Name");

        txtUserNameChildren.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtUserNameChildren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameChildrenActionPerformed(evt);
            }
        });

        jLabel365.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel365.setText("Years");

        jPanel44.setBackground(new java.awt.Color(235, 245, 251));
        jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel366.setText("Children ID");

        jLabel367.setText(":");

        lblChildrenUserID.setText("N/A");

        jLabel368.setText("Parent ID");

        jLabel369.setText(":");

        lblParientID.setText("N/A");

        jLabel370.setText("IC Number");

        jLabel371.setText(":");

        lblChildrenIcNumber.setText("N/A");

        jLabel372.setText("Parent Name");

        jLabel373.setText(":");

        lblParentName.setText("N/A");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(jLabel366, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel367)
                        .addGap(18, 18, 18)
                        .addComponent(lblChildrenUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel368, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel369)
                        .addGap(18, 18, 18)
                        .addComponent(lblParientID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addComponent(jLabel370, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel371)
                        .addGap(18, 18, 18)
                        .addComponent(lblChildrenIcNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel372, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel373)
                        .addGap(18, 18, 18)
                        .addComponent(lblParentName, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel366)
                    .addComponent(jLabel367)
                    .addComponent(lblChildrenUserID)
                    .addComponent(jLabel368)
                    .addComponent(jLabel369)
                    .addComponent(lblParientID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel370)
                    .addComponent(jLabel371)
                    .addComponent(lblChildrenIcNumber)
                    .addComponent(jLabel372)
                    .addComponent(jLabel373)
                    .addComponent(lblParentName))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jLabel374.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel374.setText(":");

        jLabel375.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel375.setText("Months");

        cboBloodTypeChildren.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "A", "B", "AB", "O" }));

        javax.swing.GroupLayout childrenPanelLayout = new javax.swing.GroupLayout(childrenPanel);
        childrenPanel.setLayout(childrenPanelLayout);
        childrenPanelLayout.setHorizontalGroup(
            childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(childrenPanelLayout.createSequentialGroup()
                .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(childrenPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(childrenPanelLayout.createSequentialGroup()
                                .addComponent(jLabel363, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel362, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboBloodTypeChildren, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(childrenPanelLayout.createSequentialGroup()
                                .addComponent(jLabel360, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel358, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtRegionChildren, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(childrenPanelLayout.createSequentialGroup()
                                .addComponent(jLabel364, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel374, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtUserNameChildren, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addComponent(jLabel361, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel359, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(childrenPanelLayout.createSequentialGroup()
                                .addComponent(cboYears, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel365, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(childrenPanelLayout.createSequentialGroup()
                                .addComponent(cboMonths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel375, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(childrenPanelLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        childrenPanelLayout.setVerticalGroup(
            childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(childrenPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel364)
                    .addComponent(jLabel374)
                    .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUserNameChildren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel361)
                        .addComponent(jLabel359)
                        .addComponent(cboYears, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel365)))
                .addGap(18, 18, 18)
                .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel363)
                    .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel362)
                        .addComponent(cboBloodTypeChildren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboMonths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel375)))
                .addGap(18, 18, 18)
                .addGroup(childrenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel360)
                    .addComponent(jLabel358)
                    .addComponent(txtRegionChildren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        patientPanel.setBackground(new java.awt.Color(235, 245, 251));

        txtRegionPatient.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtRegionPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegionPatientActionPerformed(evt);
            }
        });

        jLabel351.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel351.setText(":");

        jLabel357.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel357.setText(":");

        jLabel350.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel350.setText("Blood Type");

        jLabel356.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel356.setText("Region");

        txtAgePatient.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtAgePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgePatientActionPerformed(evt);
            }
        });

        jLabel349.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel349.setText(":");

        txtEmailPatient.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtEmailPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailPatientActionPerformed(evt);
            }
        });

        jLabel348.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel348.setText("Age");

        jLabel355.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel355.setText(":");

        txtContactNumberPatient.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtContactNumberPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactNumberPatientActionPerformed(evt);
            }
        });

        jLabel346.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel346.setText("User Name");

        jLabel353.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel353.setText(":");

        jLabel352.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel352.setText("Contact Number");

        txtUserNamePatient.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtUserNamePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNamePatientActionPerformed(evt);
            }
        });

        jLabel354.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel354.setText("Email");

        jPanel43.setBackground(new java.awt.Color(235, 245, 251));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel286.setText("User ID");

        jLabel289.setText(":");

        lblPatientUserID.setText("null");

        jLabel306.setText("User Role");

        jLabel343.setText(":");

        lblPatientRole.setText("null");

        jLabel344.setText("IC Number");

        jLabel345.setText(":");

        lblPatientIcNumber.setText("null");

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jLabel286, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel289)
                        .addGap(18, 18, 18)
                        .addComponent(lblPatientUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel306, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel343)
                        .addGap(18, 18, 18)
                        .addComponent(lblPatientRole, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(jLabel344, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel345)
                        .addGap(18, 18, 18)
                        .addComponent(lblPatientIcNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel286)
                    .addComponent(jLabel289)
                    .addComponent(lblPatientUserID)
                    .addComponent(jLabel306)
                    .addComponent(jLabel343)
                    .addComponent(lblPatientRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel344)
                    .addComponent(jLabel345)
                    .addComponent(lblPatientIcNumber))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jLabel347.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel347.setText(":");

        cboBloodTypePatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "A", "B", "AB", "O" }));

        javax.swing.GroupLayout patientPanelLayout = new javax.swing.GroupLayout(patientPanel);
        patientPanel.setLayout(patientPanelLayout);
        patientPanelLayout.setHorizontalGroup(
            patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientPanelLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patientPanelLayout.createSequentialGroup()
                        .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(patientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel348, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel349, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAgePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(patientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel350, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel351, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboBloodTypePatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(patientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel346, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel347, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtUserNamePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel356, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel357, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtRegionPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(patientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel352, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel353, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtContactNumberPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(patientPanelLayout.createSequentialGroup()
                                .addComponent(jLabel354, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel355, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmailPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(patientPanelLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        patientPanelLayout.setVerticalGroup(
            patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel346)
                    .addComponent(jLabel347)
                    .addComponent(txtUserNamePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel356)
                    .addComponent(jLabel357)
                    .addComponent(txtRegionPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patientPanelLayout.createSequentialGroup()
                        .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel348)
                            .addComponent(jLabel349)
                            .addComponent(txtAgePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel350)
                            .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel351)
                                .addComponent(cboBloodTypePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(patientPanelLayout.createSequentialGroup()
                        .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel352)
                            .addComponent(jLabel353)
                            .addComponent(txtContactNumberPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel354)
                            .addComponent(jLabel355)
                            .addComponent(txtEmailPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout showSpace8Layout = new javax.swing.GroupLayout(showSpace8);
        showSpace8.setLayout(showSpace8Layout);
        showSpace8Layout.setHorizontalGroup(
            showSpace8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(patientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpace8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(childrenPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        showSpace8Layout.setVerticalGroup(
            showSpace8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(patientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(showSpace8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(childrenPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ManagePatientPanel.add(showSpace8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        DeleteAccountPanel.setBackground(new java.awt.Color(235, 245, 251));
        DeleteAccountPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUsers.setRowHeight(30);
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblUsers);

        DeleteAccountPanel.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 15, 618, 171));

        btnDeleteUser.setBackground(new java.awt.Color(255, 204, 204));
        btnDeleteUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteUser.setText("Delete");
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
            }
        });
        DeleteAccountPanel.add(btnDeleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 82, 36));

        btnReturnDeleteUser.setBackground(new java.awt.Color(102, 153, 255));
        btnReturnDeleteUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReturnDeleteUser.setText("Return");
        btnReturnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnDeleteUserActionPerformed(evt);
            }
        });
        DeleteAccountPanel.add(btnReturnDeleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 82, 36));

        lblSuccessDeleteUser.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessDeleteUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessDeleteUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessDeleteUser.setText("Successfully deleted.");
        DeleteAccountPanel.add(lblSuccessDeleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 383, 176, -1));

        jLabel279.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel279.setText("User ID");
        DeleteAccountPanel.add(jLabel279, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 64, -1));

        jLabel280.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel280.setText(":");
        DeleteAccountPanel.add(jLabel280, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        cboUserID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        cboUserID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUserIDActionPerformed(evt);
            }
        });
        DeleteAccountPanel.add(cboUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 170, -1));

        chbDeleteUser1.setBackground(new java.awt.Color(235, 245, 251));
        chbDeleteUser1.setText(" The account still can reactive by administrator.");
        chbDeleteUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDeleteUser1ActionPerformed(evt);
            }
        });
        DeleteAccountPanel.add(chbDeleteUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 300, -1));

        jLabel281.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel281.setForeground(new java.awt.Color(51, 51, 255));
        jLabel281.setText("Reminder from Data Security Center (DSC):");
        DeleteAccountPanel.add(jLabel281, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 252, -1));

        chbDeleteUser2.setBackground(new java.awt.Color(235, 245, 251));
        chbDeleteUser2.setText(" Clear, understood and agreed.");
        DeleteAccountPanel.add(chbDeleteUser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 264, -1));

        jPanel36.setBackground(new java.awt.Color(235, 245, 251));
        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel225.setText("User ID");
        jPanel36.add(jLabel225, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 39, 82, -1));

        jLabel233.setText("User Name");
        jPanel36.add(jLabel233, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 73, 82, -1));

        jLabel246.setText("User Role");
        jPanel36.add(jLabel246, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 107, 82, -1));

        jLabel278.setText(":");
        jPanel36.add(jLabel278, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 39, -1, -1));

        jLabel282.setText(":");
        jPanel36.add(jLabel282, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 73, -1, -1));

        jLabel283.setText(":");
        jPanel36.add(jLabel283, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 107, -1, -1));

        lblUserID.setText("N/A");
        jPanel36.add(lblUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 39, 137, -1));

        lblUserName.setText("N/A");
        jPanel36.add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 73, 137, -1));

        lblUserRole.setText("N/A");
        jPanel36.add(lblUserRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 107, 137, -1));

        DeleteAccountPanel.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 233, 280, 138));

        jLabel378.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel378.setText("Role");
        DeleteAccountPanel.add(jLabel378, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 64, -1));

        jLabel380.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel380.setText(":");
        DeleteAccountPanel.add(jLabel380, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        cboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRoleActionPerformed(evt);
            }
        });
        DeleteAccountPanel.add(cboRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 170, -1));

        UserManagementMainPanel.setBackground(new java.awt.Color(235, 245, 251));
        UserManagementMainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel32.setBackground(new java.awt.Color(235, 245, 251));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Manage Users", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        ManageAdmin.setBackground(new java.awt.Color(255, 255, 255));
        ManageAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ManageAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ManageAdminMouseClicked(evt);
            }
        });

        jLabel255.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel255.setText("Manage Administrator");

        jLabel256.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel256.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel256.setText("System Manager");

        javax.swing.GroupLayout ManageAdminLayout = new javax.swing.GroupLayout(ManageAdmin);
        ManageAdmin.setLayout(ManageAdminLayout);
        ManageAdminLayout.setHorizontalGroup(
            ManageAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageAdminLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(ManageAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel255, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel256, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        ManageAdminLayout.setVerticalGroup(
            ManageAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageAdminLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel255)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel256)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        ManageStaff.setBackground(new java.awt.Color(255, 255, 255));
        ManageStaff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ManageStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ManageStaffMouseClicked(evt);
            }
        });

        jLabel271.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel271.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel271.setText("Manage Hospital Staff");

        jLabel274.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel274.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel274.setText("Doctor");

        javax.swing.GroupLayout ManageStaffLayout = new javax.swing.GroupLayout(ManageStaff);
        ManageStaff.setLayout(ManageStaffLayout);
        ManageStaffLayout.setHorizontalGroup(
            ManageStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManageStaffLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ManageStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel274, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel271, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        ManageStaffLayout.setVerticalGroup(
            ManageStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageStaffLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel271)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel274)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        ManagePatient.setBackground(new java.awt.Color(255, 255, 255));
        ManagePatient.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ManagePatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ManagePatientMouseClicked(evt);
            }
        });

        jLabel273.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel273.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel273.setText("Manage Hospital Patient");

        jLabel272.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel272.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel272.setText("Patient / User");

        javax.swing.GroupLayout ManagePatientLayout = new javax.swing.GroupLayout(ManagePatient);
        ManagePatient.setLayout(ManagePatientLayout);
        ManagePatientLayout.setHorizontalGroup(
            ManagePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManagePatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ManagePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManagePatientLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel272, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel273, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap())
        );
        ManagePatientLayout.setVerticalGroup(
            ManagePatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManagePatientLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel273)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel272)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ManageAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ManageStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ManagePatient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(ManageAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(ManageStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(ManagePatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        UserManagementMainPanel.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 30, -1, -1));

        jPanel35.setBackground(new java.awt.Color(235, 245, 251));
        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Manage User Accounts", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        DeleteUserAccount.setBackground(new java.awt.Color(255, 255, 255));
        DeleteUserAccount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DeleteUserAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteUserAccountMouseClicked(evt);
            }
        });

        jLabel275.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteAccount.png"))); // NOI18N

        jLabel276.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel276.setText("Delete User Account");

        jLabel277.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel277.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel277.setText("Admin, Doctor, Patient");

        javax.swing.GroupLayout DeleteUserAccountLayout = new javax.swing.GroupLayout(DeleteUserAccount);
        DeleteUserAccount.setLayout(DeleteUserAccountLayout);
        DeleteUserAccountLayout.setHorizontalGroup(
            DeleteUserAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeleteUserAccountLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(DeleteUserAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel276, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel277, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel275, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        DeleteUserAccountLayout.setVerticalGroup(
            DeleteUserAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeleteUserAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel275, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel276)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel277)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel47MouseClicked(evt);
            }
        });

        jLabel379.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel379.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel379.setText("Update User Account");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Update User's Password");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel379, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel379)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(14, 14, 14))
        );

        jButton4.setBackground(new java.awt.Color(255, 255, 204));
        jButton4.setText("Reactive Suspended Account");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DeleteUserAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(DeleteUserAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        UserManagementMainPanel.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, 370));

        javax.swing.GroupLayout ShowSpace6Layout = new javax.swing.GroupLayout(ShowSpace6);
        ShowSpace6.setLayout(ShowSpace6Layout);
        ShowSpace6Layout.setHorizontalGroup(
            ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserManagementMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(DeleteAccountPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ManagePatientPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ManageStaffPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ManageAdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ShowSpace6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(UpdatePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ShowSpace6Layout.setVerticalGroup(
            ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserManagementMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(DeleteAccountPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ManagePatientPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ManageStaffPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ManageAdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ShowSpace6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(UpdatePasswordPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout UserManagementPanelLayout = new javax.swing.GroupLayout(UserManagementPanel);
        UserManagementPanel.setLayout(UserManagementPanelLayout);
        UserManagementPanelLayout.setHorizontalGroup(
            UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ShowSpace6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UserManagementPanelLayout.setVerticalGroup(
            UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserManagementPanelLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShowSpace6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        InventoryPanel.setBackground(new java.awt.Color(235, 245, 251));

        jPanel5.setBackground(new java.awt.Color(52, 152, 219));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Inventory Management");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInventory.setRowHeight(30);
        tblInventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInventoryMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblInventory);

        ShowSpace2.setBackground(new java.awt.Color(235, 245, 251));

        StockReportPanel.setBackground(new java.awt.Color(235, 245, 251));

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel60.setText("Total Current Stock");
        jPanel14.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 136, -1));

        lblTotalCurrentStock.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTotalCurrentStock.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalCurrentStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalCurrentStock.setText("1000");
        jPanel14.add(lblTotalCurrentStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 29, 192, 55));

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setText("Total Safety Stock");
        jPanel15.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 128, -1));

        lblTotalSafetyStock.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTotalSafetyStock.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSafetyStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSafetyStock.setText("1000");
        jPanel15.add(lblTotalSafetyStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 29, 192, 55));

        jPanel16.setBackground(new java.awt.Color(204, 255, 204));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setText("Inventory Safety Percentage");

        lblSafetyPercenrage.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblSafetyPercenrage.setForeground(new java.awt.Color(255, 255, 255));
        lblSafetyPercenrage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSafetyPercenrage.setText("98%");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSafetyPercenrage, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSafetyPercenrage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 204, 204));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel65.setText("Inventory Risk Analysis");

        lblRiskAnalysis.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblRiskAnalysis.setForeground(new java.awt.Color(255, 255, 255));
        lblRiskAnalysis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRiskAnalysis.setText("98%");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRiskAnalysis, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRiskAnalysis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/analysis.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(234, 236, 238));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Return");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StockReportPanelLayout = new javax.swing.GroupLayout(StockReportPanel);
        StockReportPanel.setLayout(StockReportPanelLayout);
        StockReportPanelLayout.setHorizontalGroup(
            StockReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockReportPanelLayout.createSequentialGroup()
                .addGroup(StockReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StockReportPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockReportPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(StockReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StockReportPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockReportPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        StockReportPanelLayout.setVerticalGroup(
            StockReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockReportPanelLayout.createSequentialGroup()
                .addGroup(StockReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StockReportPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel67)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StockReportPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(StockReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(StockReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        DeleteItemPanel.setBackground(new java.awt.Color(235, 245, 251));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Item ID");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setText(":");

        cboItemID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        chbDeleteItem1.setBackground(new java.awt.Color(235, 245, 251));
        chbDeleteItem1.setText("   Once deleted, the data cannot be recovered.");
        chbDeleteItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDeleteItem1ActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 255));
        jLabel55.setText("Reminder from Data Security Center (DSC):");

        chbDeleteItem2.setBackground(new java.awt.Color(235, 245, 251));
        chbDeleteItem2.setText("   Clear, understood and agreed.");

        btnDeleteItem.setBackground(new java.awt.Color(255, 204, 204));
        btnDeleteItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteItem.setText("Delete");
        btnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemActionPerformed(evt);
            }
        });

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DSClogo.png"))); // NOI18N

        btnReturnDeleteItem.setBackground(new java.awt.Color(102, 153, 255));
        btnReturnDeleteItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReturnDeleteItem.setText("Return");
        btnReturnDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnDeleteItemActionPerformed(evt);
            }
        });

        lblSuccessDeleteItem.setForeground(new java.awt.Color(0, 204, 51));
        lblSuccessDeleteItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuccessDeleteItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessDeleteItem.setText("Successfully deleted.");

        javax.swing.GroupLayout DeleteItemPanelLayout = new javax.swing.GroupLayout(DeleteItemPanel);
        DeleteItemPanel.setLayout(DeleteItemPanelLayout);
        DeleteItemPanelLayout.setHorizontalGroup(
            DeleteItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeleteItemPanelLayout.createSequentialGroup()
                .addGroup(DeleteItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DeleteItemPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(DeleteItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DeleteItemPanelLayout.createSequentialGroup()
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel54)
                                .addGap(18, 18, 18)
                                .addComponent(cboItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbDeleteItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbDeleteItem2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DeleteItemPanelLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnReturnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(DeleteItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DeleteItemPanelLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DeleteItemPanelLayout.createSequentialGroup()
                        .addComponent(lblSuccessDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        DeleteItemPanelLayout.setVerticalGroup(
            DeleteItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeleteItemPanelLayout.createSequentialGroup()
                .addGroup(DeleteItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DeleteItemPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSuccessDeleteItem))
                    .addGroup(DeleteItemPanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(DeleteItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel54)
                            .addComponent(cboItemID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel55)
                        .addGap(6, 6, 6)
                        .addComponent(chbDeleteItem1)
                        .addGap(6, 6, 6)
                        .addComponent(chbDeleteItem2)
                        .addGap(27, 27, 27)
                        .addGroup(DeleteItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReturnDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        UpdateItemPanel.setBackground(new java.awt.Color(235, 245, 251));
        UpdateItemPanel.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(209, 242, 235));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUpdateInventory.setBackground(new java.awt.Color(255, 204, 204));
        btnUpdateInventory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateInventory.setText("Update Inventory");
        btnUpdateInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInventoryActionPerformed(evt);
            }
        });
        jPanel7.add(btnUpdateInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 54, 150, 31));

        lblTipsInventory.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblTipsInventory.setForeground(new java.awt.Color(102, 102, 255));
        lblTipsInventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tips2.png"))); // NOI18N
        lblTipsInventory.setText("Hint: Double-click a cell in the table to edit the contents.");
        jPanel7.add(lblTipsInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 330, -1));

        btnSaveInventory.setBackground(new java.awt.Color(204, 255, 204));
        btnSaveInventory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveInventory.setText("Save");
        btnSaveInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveInventoryActionPerformed(evt);
            }
        });
        jPanel7.add(btnSaveInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 54, 82, 31));

        lblSuccessInventory.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblSuccessInventory.setForeground(new java.awt.Color(0, 204, 0));
        lblSuccessInventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        lblSuccessInventory.setText("Updated successfully!");
        jPanel7.add(lblSuccessInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 160, -1));

        UpdateItemPanel.add(jPanel7);
        jPanel7.setBounds(10, 10, 370, 150);

        jPanel8.setBackground(new java.awt.Color(209, 242, 235));

        btnAddItems.setBackground(new java.awt.Color(204, 255, 204));
        btnAddItems.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddItems.setText("Add Items");
        btnAddItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemsActionPerformed(evt);
            }
        });

        btnDeleteItems.setBackground(new java.awt.Color(255, 204, 204));
        btnDeleteItems.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteItems.setText("Delete Items");
        btnDeleteItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteItemsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteItems, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddItems, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(btnAddItems, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteItems, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        UpdateItemPanel.add(jPanel8);
        jPanel8.setBounds(400, 10, 200, 150);

        ReportPanel.setBackground(new java.awt.Color(174, 214, 241));
        ReportPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportPanelMouseClicked(evt);
            }
        });
        ReportPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        jLabel57.setText("Click Here to Generate Stock Level Report");
        ReportPanel.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 50));

        UpdateItemPanel.add(ReportPanel);
        ReportPanel.setBounds(90, 170, 450, 50);

        AddItemPanel.setBackground(new java.awt.Color(235, 245, 251));
        AddItemPanel.setLayout(null);

        jLabel31.setText("Item Name");
        AddItemPanel.add(jLabel31);
        jLabel31.setBounds(60, 10, 110, 16);

        jLabel32.setText("Category");
        AddItemPanel.add(jLabel32);
        jLabel32.setBounds(60, 50, 110, 16);

        jLabel33.setText("Suplier");
        AddItemPanel.add(jLabel33);
        jLabel33.setBounds(60, 90, 110, 16);

        jLabel34.setText("Safety Stock");
        AddItemPanel.add(jLabel34);
        jLabel34.setBounds(60, 170, 110, 16);

        jLabel35.setText("Current Stock");
        AddItemPanel.add(jLabel35);
        jLabel35.setBounds(60, 210, 110, 16);

        jLabel36.setText(":");
        AddItemPanel.add(jLabel36);
        jLabel36.setBounds(180, 10, 3, 16);

        jLabel37.setText(":");
        AddItemPanel.add(jLabel37);
        jLabel37.setBounds(180, 50, 3, 16);

        jLabel38.setText(":");
        AddItemPanel.add(jLabel38);
        jLabel38.setBounds(180, 90, 3, 16);

        jLabel39.setText(":");
        AddItemPanel.add(jLabel39);
        jLabel39.setBounds(180, 170, 3, 16);

        jLabel40.setText(":");
        AddItemPanel.add(jLabel40);
        jLabel40.setBounds(180, 210, 3, 16);
        AddItemPanel.add(txtItemName);
        txtItemName.setBounds(220, 10, 190, 22);
        AddItemPanel.add(txtSuplier);
        txtSuplier.setBounds(220, 90, 189, 22);
        AddItemPanel.add(txtSafetyStock);
        txtSafetyStock.setBounds(220, 170, 189, 22);
        AddItemPanel.add(txtCurrentStock);
        txtCurrentStock.setBounds(220, 210, 189, 22);

        btnReturnInventory.setBackground(new java.awt.Color(102, 153, 255));
        btnReturnInventory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReturnInventory.setText("Return");
        btnReturnInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnInventoryActionPerformed(evt);
            }
        });
        AddItemPanel.add(btnReturnInventory);
        btnReturnInventory.setBounds(450, 170, 150, 30);

        btnAddItem.setBackground(new java.awt.Color(204, 255, 204));
        btnAddItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddItem.setText("Add");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });
        AddItemPanel.add(btnAddItem);
        btnAddItem.setBounds(450, 120, 150, 30);

        cboCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medication", "Medical Consumables", "Vaccine" }));
        AddItemPanel.add(cboCategory);
        cboCategory.setBounds(220, 50, 190, 22);

        jPanel6.setBackground(new java.awt.Color(235, 245, 251));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Counter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Total Item Added:");

        lblCounterInventory.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCounterInventory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCounterInventory.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .addComponent(lblCounterInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCounterInventory)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        AddItemPanel.add(jPanel6);
        jPanel6.setBounds(450, 10, 150, 100);

        jLabel105.setText("Item Price");
        AddItemPanel.add(jLabel105);
        jLabel105.setBounds(60, 130, 110, 16);

        jLabel109.setText(":");
        AddItemPanel.add(jLabel109);
        jLabel109.setBounds(180, 130, 3, 16);
        AddItemPanel.add(txtPrice);
        txtPrice.setBounds(220, 130, 189, 22);

        javax.swing.GroupLayout ShowSpace2Layout = new javax.swing.GroupLayout(ShowSpace2);
        ShowSpace2.setLayout(ShowSpace2Layout);
        ShowSpace2Layout.setHorizontalGroup(
            ShowSpace2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ShowSpace2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(UpdateItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ShowSpace2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DeleteItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(ShowSpace2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ShowSpace2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(StockReportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ShowSpace2Layout.setVerticalGroup(
            ShowSpace2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AddItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ShowSpace2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(UpdateItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpace2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ShowSpace2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DeleteItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(ShowSpace2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ShowSpace2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(StockReportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout InventoryPanelLayout = new javax.swing.GroupLayout(InventoryPanel);
        InventoryPanel.setLayout(InventoryPanelLayout);
        InventoryPanelLayout.setHorizontalGroup(
            InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(InventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addComponent(ShowSpace2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        InventoryPanelLayout.setVerticalGroup(
            InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventoryPanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ShowSpace2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ShowSpaceLayout = new javax.swing.GroupLayout(ShowSpace);
        ShowSpace.setLayout(ShowSpaceLayout);
        ShowSpaceLayout.setHorizontalGroup(
            ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProfilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AssistancePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(RegisterStaffPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(UserManagementPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(InventoryPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AdminWelcomePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShowSpaceLayout.setVerticalGroup(
            ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ProfilePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AssistancePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(RegisterStaffPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(UserManagementPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(InventoryPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ShowSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(AdminWelcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        showInventoryManagementPanel();
    }//GEN-LAST:event_InventoryNavBarMouseClicked
    
    private void ProfileNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileNavBarMouseClicked
        // TODO add your handling code here:
        showProfilePanel();
    }//GEN-LAST:event_ProfileNavBarMouseClicked

    private void AssistanceNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssistanceNavBarMouseClicked
        // TODO add your handling code here:
        showAssistancePanel();
    }//GEN-LAST:event_AssistanceNavBarMouseClicked

    private void RegisterNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterNavBarMouseClicked
        // TODO add your handling code here:
        showRegisterStaffPanel();
    }//GEN-LAST:event_RegisterNavBarMouseClicked

    private void UserManagementNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserManagementNavBarMouseClicked
        // TODO add your handling code here:
        showUserManagementPanel();
    }//GEN-LAST:event_UserManagementNavBarMouseClicked

    private void HospitalInfoNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HospitalInfoNavBarMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        adminFrame2.setVisible(true);
        adminFrame2.showPanel("Hospital Information");
    }//GEN-LAST:event_HospitalInfoNavBarMouseClicked

    private void HealthCareNavBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HealthCareNavBarMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        adminFrame2.setVisible(true);
        adminFrame2.showPanel("HealthCare Programs");
    }//GEN-LAST:event_HealthCareNavBarMouseClicked

    private void btnUpdateInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInventoryActionPerformed
        // TODO add your handling code here:
        tblInventory.setEnabled(true);
        btnSaveInventory.setEnabled(true);
        
        lblTipsInventory.setVisible(true);
        lblSuccessInventory.setVisible(false);
    }//GEN-LAST:event_btnUpdateInventoryActionPerformed

    private void btnSaveInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveInventoryActionPerformed
        // TODO add your handling code here:
        boolean stop = false;
        boolean checkpoint1 = false;
        boolean checkpoint2 = false;

        ArrayList<String[]> dataList = File_Control.readFile("inventoryData.txt", false);

        // get the table model
        DefaultTableModel tableModel = (DefaultTableModel)tblInventory.getModel();

        int count = 0;
        for (String[] list : dataList) {
            String itemStatus = list[list.length - 1];

            if (itemStatus.equals("1")) {
                count += 1;
            }
        }
        
        String exceptionTitle = "";
        for (int row = 0; row < count; row++) {
            String itemID = tableModel.getValueAt(row, 0).toString();
            String itemName = tableModel.getValueAt(row, 1).toString();
            String category = tableModel.getValueAt(row, 2).toString();
            String suplier = tableModel.getValueAt(row, 3).toString();
            String price = tableModel.getValueAt(row, 4).toString();
            String safetyStock = tableModel.getValueAt(row, 5).toString();
            String currentStock = tableModel.getValueAt(row, 6).toString();
            
            boolean priceValid = Validation.price(price);
            boolean safetyStockValid = Validation.integer(safetyStock);
            boolean currentStockValid = Validation.integer(currentStock);

            if (itemName.isEmpty() || category.isEmpty() || suplier.isEmpty() || safetyStock.isEmpty() || currentStock.isEmpty()) {
                stop = true;
                break;
            }
            
            else if (priceValid == false) {
                checkpoint1 = true;
                break;
            }
            
            else if (safetyStockValid == false || currentStockValid == false) {
                if (safetyStockValid == false) {
                    exceptionTitle = "SAFETY STOCK";
                }
                else if (currentStockValid == false) {
                    exceptionTitle = "CURRENT STOVK";
                }
                
                checkpoint2 = true;
                break;
            }
            
            else {
                for (String[] list : dataList) {
                    if (itemID.equals(list[0])) {
                        list[1] = itemName;
                        list[2] = category;
                        list[3] = suplier;
                        
                        String formatedPrice = Utility_Methods.definePriceFormat(price);
                        list[4] = formatedPrice;
                        list[5] = safetyStock;
                        list[6] = currentStock;
                    }
                }
            }
        }
        
        if (stop == true) {
            warningDialog("Empty", null, lblSuccessInventory);
        }
        
        else if (checkpoint1 == true) {
            warningDialog("Price", null, lblSuccessInventory);
        }
        
        else if (checkpoint2 == true) {
            warningDialog("Number", exceptionTitle, lblSuccessInventory);
        }
        
        else if (stop == false) {
            File_Control.writeFile("inventoryData.txt", dataList);
            System.out.println("Item records has been successfully updated.");
            
            tblInventory.setEnabled(false);
            lblSuccessInventory.setVisible(true);
            lblTipsInventory.setVisible(false);
            inventoryTable();
        }
    }//GEN-LAST:event_btnSaveInventoryActionPerformed

    private void btnAddItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemsActionPerformed
        // TODO add your handling code here:
        AddItemPanel.setVisible(true);
        UpdateItemPanel.setVisible(false);
        DeleteItemPanel.setVisible(false);
        StockReportPanel.setVisible(false);
        
        disenable();
        lblSuccessInventory.setVisible(false);
        lblTipsInventory.setVisible(false);
        
        textClear();
        modelComboBoxCategory();
    }//GEN-LAST:event_btnAddItemsActionPerformed

    private void btnReturnInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnInventoryActionPerformed
        // TODO add your handling code here:
        // every time click done, it will reset counter to 0
        counter = 0;
        lblCounterInventory.setText(String.valueOf(counter));
        
        inventoryTable();
        UpdateItemPanel.setVisible(true);
        AddItemPanel.setVisible(false);
        DeleteItemPanel.setVisible(false);
        
        textClear();
    }//GEN-LAST:event_btnReturnInventoryActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        // TODO add your handling code here:                                    
        lblCounterInventory.setText(String.valueOf(counter)); 

        String newItemID, itemName, category, suplier, price, safetyStock, currentStock;

        itemName = txtItemName.getText().strip();
        category = cboCategory.getSelectedItem().toString().strip();
        suplier = txtSuplier.getText().strip();
        price = txtPrice.getText().strip();
        safetyStock = txtSafetyStock.getText().strip();
        currentStock = txtCurrentStock.getText().strip();
        
        boolean priceValid = Validation.price(price);
        boolean safetyStockValid = Validation.integer(safetyStock);
        boolean currentStockValid = Validation.integer(currentStock);
        
        if (itemName.isEmpty() || category.isEmpty() || suplier.isEmpty() || price.isEmpty() || safetyStock.isEmpty() || currentStock.isEmpty()) {
            warningDialog("Empty", null, null);
        }
        
        else if (safetyStockValid == false || currentStockValid == false) {
            String title = "";
            
            if (safetyStockValid == false) {
                title = "SAFETY STOCK";
            }
            else if (currentStockValid == false) {
                title = "CURRENT STOCK";
            }
            
            warningDialog("Number", title, null);
        }
        else if (priceValid == false) {
            warningDialog("Price", null, null);
        }
        else {
            // auto generate inventory ID
            newItemID = Utility_Methods.autoGenerateID("inventoryData.txt", "I");
            
            String formatedPrice = Utility_Methods.definePriceFormat(price);
            
            String line = newItemID + ";" + itemName + ";" + category + ";" + suplier + ";" + formatedPrice + ";" + safetyStock + ";" + currentStock + ";1";
            File_Control.addData("inventoryData.txt", line);
            
            counter++;
            lblCounterInventory.setText(String.valueOf(counter)); 
            inventoryTable();
            modelComboBoxItemID();
            System.out.println(newItemID + " (" + itemName + ") has been successfully added.");
            textClear();
        }
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnDeleteItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteItemsActionPerformed
        // TODO add your handling code here:
        DeleteItemPanel.setVisible(true);
        UpdateItemPanel.setVisible(false);
        AddItemPanel.setVisible(false);
        StockReportPanel.setVisible(false);
        
        disenable();
        lblSuccessInventory.setVisible(false);
        lblTipsInventory.setVisible(false);
        modelComboBoxItemID();
    }//GEN-LAST:event_btnDeleteItemsActionPerformed

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:
        AdminWelcomePanel.setVisible(true);
        
        ProfilePanel.setVisible(false);
        AssistancePanel.setVisible(false);
        RegisterStaffPanel.setVisible(false);
        UserManagementPanel.setVisible(false);
        InventoryPanel.setVisible(false);
    }//GEN-LAST:event_jLabel43MouseClicked

    private void chbDeleteItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDeleteItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbDeleteItem1ActionPerformed

    private void btnReturnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnDeleteItemActionPerformed
        // TODO add your handling code here:
        UpdateItemPanel.setVisible(true);
        DeleteItemPanel.setVisible(false);
        AddItemPanel.setVisible(false);
        
        chbDeleteItem1.setSelected(false);
        chbDeleteItem2.setSelected(false);
                
        lblSuccessDeleteItem.setVisible(false);
    }//GEN-LAST:event_btnReturnDeleteItemActionPerformed

    private void btnDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteItemActionPerformed
        // TODO add your handling code here:
   
        ArrayList<String[]> dataList = File_Control.readFile("inventoryData.txt", false);
        boolean status = false;
        
        String deleteItemID = cboItemID.getSelectedItem().toString();
        
        if (chbDeleteItem1.isSelected() && chbDeleteItem2.isSelected()) {
            for (String[] list : dataList) {
                String itemID = list[0];
                
                if (itemID.equals(deleteItemID)) {
                    list[list.length - 1] = "0";
                    status = true;
                    break;
                }
            }
            
            if (status == true) {
                File_Control.writeFile("inventoryData.txt", dataList);
                System.out.println(deleteItemID + "'s record has been successfully deleted.");
                chbDeleteItem1.setSelected(false);
                chbDeleteItem2.setSelected(false);
                
                lblSuccessDeleteItem.setVisible(true);
                
                // everytime click delete will model the combobox
                // to get the latest ID
                modelComboBoxItemID();

                // everytime click delete also need to renew table
                inventoryTable();
            }
        }
        else {
            warningDialog("deleteStatement", null, lblSuccessDeleteItem);
        }
    }//GEN-LAST:event_btnDeleteItemActionPerformed

    private void ReportPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportPanelMouseClicked
        // TODO add your handling code here:
        StockReportPanel.setVisible(true);
        AddItemPanel.setVisible(false);
        UpdateItemPanel.setVisible(false);
        DeleteItemPanel.setVisible(false);
        
        disenable();
        lblSuccessInventory.setVisible(false);
        lblTipsInventory.setVisible(false);
        
        ArrayList<String[]> dataList = File_Control.readFile("inventoryData.txt", true);
        
        int safetyStock = 0;
        int currentStock = 0;
        double count = 0.0;
        
        for (String[] list : dataList) {
            safetyStock += Integer.parseInt(list[5]);
            currentStock += Integer.parseInt(list[6]);
            count ++;
        }
        
        lblTotalSafetyStock.setText(Integer.toString(safetyStock));
        lblTotalCurrentStock.setText(Integer.toString(currentStock));
        
        // that means every items should 50 more than original safety stock
        // this is use to count the inventory safety percentage
        double bestSafetyStock = 50 * count;
        
        double safetyPercentage = (currentStock / (safetyStock + bestSafetyStock)) * 100.0;
        String result1 = String.format("%.2f%%", safetyPercentage);
        lblSafetyPercenrage.setText(result1);
        
        double riskPercentage = 100 - safetyPercentage;
        String result2 = String.format("%.2f%%", riskPercentage);
        lblRiskAnalysis.setText(result2);
    }//GEN-LAST:event_ReportPanelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        UpdateItemPanel.setVisible(true);
        AddItemPanel.setVisible(false);
        DeleteItemPanel.setVisible(false);
        StockReportPanel.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblInventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventoryMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblInventoryMouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
        registerStaffPanel.setVisible(true);
        registerChoosePanel.setVisible(false);
        registerAdminPanel.setVisible(false);
        registerPatientPanel.setVisible(false);
        Slip.setVisible(false);
        
        lblSuccessRegisterStaff.setVisible(false);
        
        // model combo box
        modelComboBoxWorkingDay();
        
        modelWorkingDayList.clear();
        
        // model list
        lstWorkingDayRegisterStaff.setModel(modelWorkingDayList);
        
        // set Working Day list disenabled
        lstWorkingDayRegisterStaff.setEnabled(false);
        
        cboDayRegisterStaff.setEnabled(true);
        btnAddRegisterStaff.setEnabled(true);
        
        txtNationalityRegisterStaff.setText("Malaysian");
        txtNationalityRegisterStaff.setEnabled(false);
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        registerAdminPanel.setVisible(true);
        registerChoosePanel.setVisible(false);
        registerStaffPanel.setVisible(false);
        registerPatientPanel.setVisible(false);
        
        lblSuccessRegisterAdmin.setVisible(false);
        
        lblNameAdmin.setText(this.defaultText);
        lblRoleAdmin.setText(this.defaultText);
        lblUserIDAdmin.setText(this.defaultText);
        lblUserIDAdmin.setText(this.defaultText);

        lblRegisterPersonNameAdmin.setText(this.defaultText);
        lblDateAdmin.setText(this.defaultText);
        
        txtNationalityRegisterAdmin.setText("Malaysian");
        txtNationalityRegisterAdmin.setEnabled(false);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void txtUserIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserIDActionPerformed

    private void btnEditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfileActionPerformed
        // TODO add your handling code here:
        profileEnableText();
        lblTipsProfile.setVisible(true);
        lblSuccessProfile.setVisible(false);
    }//GEN-LAST:event_btnEditProfileActionPerformed

    private void btnSaveProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveProfileActionPerformed
        // TODO add your handling code here:
        String userName = txtName.getText().strip();
        String nationality = txtNationality.getText().strip();
        String region = txtState.getText().strip();
        String contactNumber = txtContactNumber.getText().strip();
        String email = txtEmail.getText().strip();
        
        boolean userNameValid = Validation.string(userName);
        boolean nationalityValid = Validation.string(nationality);
        boolean regionValid = Validation.string(region);
        boolean contactNumberValid = Validation.contactNumber(contactNumber);
        boolean emailValid = Validation.email(email);


        if (userName.isEmpty() || nationality.isEmpty() || region.isEmpty() || 
                contactNumber.isEmpty() || email.isEmpty()) {
            warningDialog("Empty", null, lblSuccessProfile);
        }
        
        else if (userNameValid == false || nationalityValid == false || regionValid == false) {
            String title = "";
            
            if (userNameValid == false) {
                title = "USER NAME";
            }
            else if (nationalityValid == false) {
                title = "NATIONALITY";
            }
            else if (regionValid == false) {
                title = "REGION";
            }
            
            warningDialog("String", title, lblSuccessProfile);
        }
        
        else if (emailValid == false) {
            warningDialog("Email", null, lblSuccessProfile);
        }

        else if (contactNumberValid == false) {
            warningDialog("Contact Number", null, lblSuccessProfile);
        }
        
        else {
            disenable();
            
            admin.setUserName(userName);
            admin.setNationality(nationality);
            admin.setRegion(region);
            admin.setContactNumber(contactNumber);
            admin.setEmail(email);
            
            // update the profile and write to txt file
            admin.updateProfile();
            
            lblTipsProfile.setVisible(false);
            lblSuccessProfile.setVisible(true);
            System.out.println(admin.getUserName() + "'s profile has been successfully updated.");

            // update name display
            lblAdminName.setText("Welcome, " + admin.getUserName() + "!");
            lblAdminName2.setText(admin.getUserName());
        }
        
        // account security status checking
        checkAccSecurityStatus();
    }//GEN-LAST:event_btnSaveProfileActionPerformed

    private void btnSafetyQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafetyQuestionActionPerformed
        // TODO add your handling code here:
        String password = JOptionPane.showInputDialog("Please Enter Your Password: ");
        
        // when user close / cancel the dialog
        if (password == null) {
            return; // stop it
        }
        
        else {
            // because the value maybe is null, so need to skip the null (secure state) so that can start strip it
            password = password.strip();
        }
        
        if (password.equals(admin.getUserPassword())) {
            safetyQuestionPanel.setVisible(true);
            profileMainPanel.setVisible(false);
            changePasswordPanel.setVisible(false);

            disenable();
            // display the related data - safetyQuestionPanel
            modelComboBoxSafetyQuestion(); // model the safetyQuestion combobox

            String QID1 = admin.getSafetyQuestionID1();
            String QID2 = admin.getSafetyQuestionID2();
            String Answer1 = admin.getSafetyAnswer1();
            String Answer2 = admin.getSafetyAnswer2();
            
            if (QID1.equals("-")) {
                cboSafetyQuestion1.setSelectedItem("- Please Select -");
            }
            
            else if (QID2.equals("-")) {
                cboSafetyQuestion2.setSelectedItem("- Please Select -");
            }
            
            else {
                cboSafetyQuestion1.setSelectedItem(admin.questionInString(QID1));
                cboSafetyQuestion2.setSelectedItem(admin.questionInString(QID2));

                txtAnswer1.setText(Answer1);
                txtAnswer2.setText(Answer2);
            }
        }
        
        else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please make sure password is not empty.",
                "Pacific Data Validation Center (DVC)",
                JOptionPane.WARNING_MESSAGE);
        }
        
        else {
            JOptionPane.showMessageDialog(this,
                """
                Wrong Password! Access not granted.
                Hint: If forgot your password, please contact the administrator to reset it.
                """,
                "Pacific Data Security Center (DSC)",
                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSafetyQuestionActionPerformed

    private void btnEditSafetyQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSafetyQuestionActionPerformed
        // TODO add your handling code here:
        profileEnableText();
        lblTipsSafetyQuestion.setVisible(true);
        lblSuccessSafetyQuestion.setVisible(false);
    }//GEN-LAST:event_btnEditSafetyQuestionActionPerformed

    private void btnSaveSafetyQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSafetyQuestionActionPerformed
        // TODO add your handling code here:
        String Q1 = cboSafetyQuestion1.getSelectedItem().toString();
        String Answer1 = txtAnswer1.getText().strip();
        String Q2 = cboSafetyQuestion2.getSelectedItem().toString();
        String Answer2 = txtAnswer2.getText().strip();
        
        if (Answer1.isEmpty() || Answer2.isEmpty()) {
            warningDialog("Empty", null, lblSuccessSafetyQuestion);
        }
        
        else if (Q1.equals("- Please Select -") || Q2.equals("- Please Select -")) {
            JOptionPane.showMessageDialog(this,
                "Please make sure 2 questions are selected.",
                "Pacific Data Validation Center (DVC)",
                JOptionPane.WARNING_MESSAGE);
            
            lblSuccessSafetyQuestion.setVisible(false);
        }
        
        else if (Q1.equals(Q2)) {
            JOptionPane.showMessageDialog(this,
                "Please make sure 2 questions are different.",
                "Pacific Data Validation Center (DVC)",
                JOptionPane.WARNING_MESSAGE);
            
            lblSuccessSafetyQuestion.setVisible(false);
        }
        
        else {
            String QID1 = admin.questionInID(Q1);
            String QID2 = admin.questionInID(Q2);
            
            admin.setSafetyQuestionID1(QID1);
            admin.setSafetyQuestionID2(QID2);
            
            admin.setSafetyAnswer1(Answer1);
            admin.setSafetyAnswer2(Answer2);
            
            admin.updateSafetyQuestion();
            
            System.out.println(admin.getUserName() + "'s safety question has been successfully updated.");
            
            disenable();
            lblSuccessSafetyQuestion.setVisible(true);
            lblTipsSafetyQuestion.setVisible(false);
        }
        
        // account security status checking
        checkAccSecurityStatus();
    }//GEN-LAST:event_btnSaveSafetyQuestionActionPerformed

    private void btnReturnSafetyQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnSafetyQuestionActionPerformed
        // TODO add your handling code here:
        disenable();
        profileMainPanel.setVisible(true);
        safetyQuestionPanel.setVisible(false);
        
        lblSuccessProfile.setVisible(false);
        lblTipsProfile.setVisible(false);
        
        lblSuccessSafetyQuestion.setVisible(false);
        lblTipsSafetyQuestion.setVisible(false);
        
        // account security status checking
        checkAccSecurityStatus();
    }//GEN-LAST:event_btnReturnSafetyQuestionActionPerformed

    private void btnIdentifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIdentifyActionPerformed
        // TODO add your handling code here:
        String currentPassword = admin.getUserPassword().strip();
        String inputPassword = txtCurrentPassword.getText().strip();

        if (currentPassword.equals(inputPassword)) {
            profileEnableText();
            txtCurrentPassword.setEnabled(false);
            btnIdentify.setEnabled(false);
            lblTipsChangePassword.setText("Password verified successfully.");
            lblTipsChangePassword.setForeground(new Color(0,204,0)); 
        }
        else {
            txtCurrentPassword.setEnabled(true);
            lblTipsChangePassword.setText("Password verification failed, please try again.");
            lblTipsChangePassword.setForeground(Color.RED);
        }
        
    }//GEN-LAST:event_btnIdentifyActionPerformed

    private void btnPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasswordActionPerformed
        // TODO add your handling code here:
        changePasswordPanel.setVisible(true);
        safetyQuestionPanel.setVisible(false);
        profileMainPanel.setVisible(false);
        
        disenable();
        txtCurrentPassword.setEnabled(true);
        lblTipsChangePassword.setText("");
        txtCurrentPassword.setText("");
        btnIdentify.setEnabled(true);
        txtNewPassword.setText("");
        txtConfirmNewPassword.setText("");
        lblSuccessChangePassword.setVisible(false);
    }//GEN-LAST:event_btnPasswordActionPerformed

    private void btnReturnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnChangePasswordActionPerformed
        // TODO add your handling code here:
        profileMainPanel.setVisible(true);
        safetyQuestionPanel.setVisible(false);
        changePasswordPanel.setVisible(false);
        
        disenable();
        lblTipsProfile.setVisible(false);
        lblSuccessProfile.setVisible(false);
        lblTipsSafetyQuestion.setVisible(false);
        lblSuccessSafetyQuestion.setVisible(false);
        checkAccSecurityStatus();
    }//GEN-LAST:event_btnReturnChangePasswordActionPerformed

    private void btnSaveChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChangePasswordActionPerformed
        // TODO add your handling code here:
        String currentPassword = admin.getUserPassword().strip();
        String newPassword = txtNewPassword.getText().strip();
        String confirmNewPassword = txtConfirmNewPassword.getText().strip();
        
        if (newPassword.equals(currentPassword) && confirmNewPassword.equals(currentPassword)) {
            JOptionPane.showMessageDialog(this,
                "Please make sure it is different from your old password.",
                "Pacific Data Validation Center (DVC)",
                JOptionPane.WARNING_MESSAGE);
            
            lblSuccessChangePassword.setVisible(false);
            txtNewPassword.setText("");
            txtConfirmNewPassword.setText("");
        }
        
        else if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            warningDialog("Empty", null,  lblSuccessChangePassword);
            
            txtNewPassword.setText("");
            txtConfirmNewPassword.setText("");
        }
        
        else if (!newPassword.equals(confirmNewPassword)) {
            JOptionPane.showMessageDialog(this,
                "Please make sure New Password and Confirm New Password are same.",
                "Pacific Data Validation Center (DVC)",
                JOptionPane.WARNING_MESSAGE);
            
            lblSuccessChangePassword.setVisible(false);
            txtNewPassword.setText("");
            txtConfirmNewPassword.setText("");
        }
        else {
            boolean passwordValid = Validation.password(newPassword);

            if (passwordValid == false) {
                warningDialog("Password", null,  lblSuccessChangePassword);
            }
            
            else {
                admin.setUserPassword(newPassword);
                
                // update admin password and also txt file
                admin.updatePassword();
                System.out.println(admin.getUserName() + "'s password has been successfully updated.");

                disenable();
                lblSuccessChangePassword.setVisible(true);
                checkAccSecurityStatus();
            }
        }
    }//GEN-LAST:event_btnSaveChangePasswordActionPerformed

    private void btnReturnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnAdminActionPerformed
        // TODO add your handling code here:
        registerChoosePanel.setVisible(true);
        registerStaffPanel.setVisible(false);
        registerAdminPanel.setVisible(false);
        
        lblSuccessRegisterAdmin.setVisible(false);
        
        lblNameAdmin.setText(this.defaultText);
        lblRoleAdmin.setText(this.defaultText);
        lblUserIDAdmin.setText(this.defaultText);
        lblDefaultPasswordAdmin.setText(this.defaultText);

        lblRegisterPersonNameAdmin.setText(this.defaultText);
        lblDateAdmin.setText(this.defaultText);
        
        textClear();
    }//GEN-LAST:event_btnReturnAdminActionPerformed

    private void btnRegisterAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterAdminActionPerformed
        // TODO add your handling code here:
        // get the value
        String name = txtNameRegisterAdmin.getText().strip();
        String icNumber = txtIcNumberRegisterAdmin.getText().strip();
        String gender = cboGenderRegisterAdmin.getSelectedItem().toString().strip();
        String nationality = txtNationalityRegisterAdmin.getText().strip();
        String state = txtStateRegisterAdmin.getText().strip();
        String contactNumber = txtContactNumberRegisterAdmin.getText().strip();
        String email = txtEmailRegisterAdmin.getText().strip();
        String userRole = "Admin";
        
        // input validation
        boolean nameValid = Validation.string(name);
        boolean icNumberValid = Validation.icNumber(icNumber);
        boolean nationalityValid = Validation.string(nationality);
        boolean stateValid = Validation.string(state);
        boolean contactNumberValid = Validation.contactNumber(contactNumber);
        boolean emailValid = Validation.email(email);
        
        // checking the valid status
        if(name.isEmpty() || icNumber.isEmpty() || gender.isEmpty() || nationality.isEmpty() || 
        state.isEmpty() || contactNumber.isEmpty() || email.isEmpty()) {
            warningDialog("Empty", null,  lblSuccessRegisterAdmin);
        }
        
        else if (nameValid == false || nationalityValid == false || stateValid == false) {
            String title = "";
            
            if (nameValid == false) {
                title = "NAME";
            }
            else if (nationalityValid == false) {
                title = "NATIONALITY";
            }
            else if (stateValid == false) {
                title = "REGION";
            }
            
            warningDialog("String", title,  lblSuccessRegisterAdmin);
        }
        
        else if (icNumberValid == false) {
            warningDialog("IC Number", null,  lblSuccessRegisterAdmin);
        }

        else if (contactNumberValid == false) {
            warningDialog("Contact Number", null,  lblSuccessRegisterAdmin);
        }
        
        else if (emailValid == false) {
            warningDialog("Email", null,  lblSuccessRegisterAdmin);
        }
        
        // run when pass the validation
        else {
            // generate new user ID
            String newUserID = Utility_Methods.autoGenerateID("adminData.txt", "A");
            
            // generate default password
            String birthday = icNumber.substring(2, 6);
            String defaultPassword = newUserID + "@" + birthday;
            
            // set the data
            Admin newAdmin = new Admin(newUserID, name, defaultPassword, userRole, icNumber, gender, state,
                           email, contactNumber, nationality);
            
            newAdmin.writeNewAdminData();
            System.out.println(name + " (" + userRole + ") " + "has been successfully registered.");

            lblSuccessRegisterAdmin.setVisible(true);
            
            // data that display at register slip
            lblNameAdmin.setText(newAdmin.getUserName());
            lblRoleAdmin.setText(newAdmin.getUserRole());
            lblUserIDAdmin.setText(newAdmin.getUserID());
            lblDefaultPasswordAdmin.setText(newAdmin.getUserPassword());
            
            lblRegisterPersonNameAdmin.setText(admin.getUserName());
            lblDateAdmin.setText(localDateToString);
            
            // clear the text
            textClear();
        }
    }//GEN-LAST:event_btnRegisterAdminActionPerformed
    
    public String convertWorkingDayToTxtData(String[] dataList) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String result = "";

        for (int i = 0; i < dataList.length; i++) {
            for (int j = 0; j < days.length; j++) {
                if (dataList[i].equals(days[j])) {
                    int data = j + 1;
                    result += data;

                    if (i < dataList.length - 1) {
                        result += "/";
                    }
                    break;
                }
            }
        }

        return result;
    }
    
    public String convertWorkingDayToDisplayData(String data) {
        // change the string to array ("1/2/3" -> {"1", "2", "3"})
        String[] dayNumbers = data.split("/");

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String result = "";

        for (int i = 0; i < dayNumbers.length; i++) {
            // change the "1" -> 0, so it will match with days[0]
            int indexNum = Integer.parseInt(dayNumbers[i]) - 1;
            
            result += days[indexNum];
            if (i < dayNumbers.length - 1) {
                result += "/";
            }
        }

        return result;
    }
  
    private void btnRegisterStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterStaffActionPerformed
        // TODO add your handling code here:
        String name = txtNameRegisterStaff.getText().strip();
        String icNumber = txtICNumberRegisterStaff.getText().strip();
        String gender = cboGenderRegisterStaff.getSelectedItem().toString();
        String age = txtAgeRegisterStaff.getText().strip();
        String nationality = txtNationalityRegisterStaff.getText().strip();
        String state = txtStateRegisterStaff.getText().strip();
        String contactNumber = txtContactNumberRegisterStaff.getText().strip();
        String email = txtEmailRegisterStaff.getText().strip();
        String userRole = "Doctor";
        String specialist = cboSpecialistRegisterStaff.getSelectedItem().toString();
        String office = txtOfficeRegisterStaff.getText().strip();
        
        // create an empty array
        String[] workingDay = new String[modelWorkingDayList.getSize()];
        // add data into that array
        for (int i = 0; i < modelWorkingDayList.getSize(); i++) {
            workingDay[i] = modelWorkingDayList.getElementAt(i).toString();
        }
        
        // input validation
        boolean nameValid = Validation.string(name);
        boolean icNumberValid = Validation.icNumber(icNumber);
        boolean ageValid = Validation.age(age);
        boolean nationalityValid = Validation.string(nationality);
        boolean stateValid = Validation.string(state);
        boolean contactNumberValid = Validation.contactNumber(contactNumber);
        boolean emailValid = Validation.email(email);
        
        // checking the valid status
        if(name.isEmpty() || icNumber.isEmpty() || gender.isEmpty() || nationality.isEmpty() || 
        state.isEmpty() || contactNumber.isEmpty() || email.isEmpty() || office.isEmpty() || workingDay.length == 0) {
            warningDialog("Empty", null,  lblSuccessRegisterStaff);
        }
        
        else if (nameValid == false || nationalityValid == false || stateValid == false) {
            String title = "";
            
            if (nameValid == false) {
                title = "NAME";
            }
            else if (nationalityValid == false) {
                title = "NATIONALITY";
            }
            else if (stateValid == false) {
                title = "REGION";
            }
            
            warningDialog("String", title,  lblSuccessRegisterStaff);
        }
        
        else if (icNumberValid == false) {
            warningDialog("IC Number", null,  lblSuccessRegisterStaff);
        }
        
        else if (ageValid == false) {
            warningDialog("Age", null,  lblSuccessRegisterStaff);
        }

        else if (contactNumberValid == false) {
            warningDialog("Contact Number", null,  lblSuccessRegisterStaff);
        }
        
        else if (emailValid == false) {
            warningDialog("Email", null,  lblSuccessRegisterStaff);
        }
        
        // run when pass the validation
        else {
            // change the array to string for working day
            String workingDayData = convertWorkingDayToTxtData(workingDay);
            
            // generate new userID
            String newUserID = Utility_Methods.autoGenerateID("doctorData.txt", "D");
            
            // generate default password
            String birthday = icNumber.substring(2, 6);
            String defaultPassword = newUserID + "@" + birthday;
            
            
            Doctor newDoctor = new Doctor(newUserID, name, defaultPassword, userRole, icNumber, gender,
                                   state, email, contactNumber, specialist, workingDayData, office, Integer.parseInt(age), nationality);
            
            newDoctor.writeNewDoctorData();
            System.out.println(name + " (" + userRole + ") " + "has been successfully registered.");
            
            
            lblSuccessRegisterStaff.setVisible(true);
            
            // set the slip value
            lblSlipTitle.setText("Congratulations Mr. / Ms. / Dr. " + newDoctor.getUserName());
            lblNameSlip.setText(newDoctor.getUserName());
            lblICNumberSlip.setText(newDoctor.getIcNumber());
            lblRoleSlip.setText(newDoctor.getUserRole());
            lblOfficeSlip.setText(newDoctor.getRoom());
            lblUserIDSlip.setText(newDoctor.getUserID());
            lblDefaultPasswordSlip.setText(newDoctor.getUserPassword());
            lblSpecialistSlip.setText(newDoctor.getSpecialist());
            
            String workingday = convertWorkingDayToDisplayData(newDoctor.getWorkingDay());
            lblWorkingDaySlip.setText(workingday);
            
            lblRegisterPersonNameSlip.setText(admin.getUserName());
            lblDateSlip.setText(localDateToString);
            
            // show the slip
            Slip.setVisible(true);
            staffPanel.setVisible(false);
        }
    }//GEN-LAST:event_btnRegisterStaffActionPerformed

    private void cboDayRegisterStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDayRegisterStaffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDayRegisterStaffActionPerformed

    private void cboSpecialistRegisterStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSpecialistRegisterStaffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSpecialistRegisterStaffActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        staffPanel.setVisible(true);
        Slip.setVisible(false);
        
        lblSlipTitle.setText(this.defaultText);
        lblNameSlip.setText(this.defaultText);
        lblICNumberSlip.setText(this.defaultText);
        lblRoleSlip.setText(this.defaultText);
        lblOfficeSlip.setText(this.defaultText);
        lblUserIDSlip.setText(this.defaultText);
        lblDefaultPasswordSlip.setText(this.defaultText);
        lblSpecialistSlip.setText(this.defaultText);
        lblWorkingDaySlip.setText(this.defaultText);
        lblRegisterPersonNameSlip.setText(this.defaultText);
        lblDateSlip.setText(this.defaultText);
        
        modelComboBoxWorkingDay();
        modelWorkingDayList.clear();
        
        cboDayRegisterStaff.setEnabled(true);
        btnAddRegisterStaff.setEnabled(true);
        
        textClear();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void btnAddRegisterStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRegisterStaffActionPerformed
        // TODO add your handling code here:        
        String workingDay = cboDayRegisterStaff.getSelectedItem().toString();
        
        if (modelWorkingDayList.contains(workingDay)) {
            JOptionPane.showMessageDialog(this,
                "This day already added, please select another day.",
                "Pacific Data Validation Center (DVC)",
                JOptionPane.WARNING_MESSAGE);
        } 
        else {
            modelWorkingDayList.addElement(workingDay);
            cboDayRegisterStaff.removeItem(workingDay);
        }
    }//GEN-LAST:event_btnAddRegisterStaffActionPerformed

    private void btnSaveRegisterStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveRegisterStaffActionPerformed
        // TODO add your handling code here:
        disenable();
    }//GEN-LAST:event_btnSaveRegisterStaffActionPerformed

    private void btnReturnStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnStaffActionPerformed
        // TODO add your handling code here:
        registerChoosePanel.setVisible(true);
        registerStaffPanel.setVisible(false);
        registerAdminPanel.setVisible(false);
        
        lblSuccessRegisterStaff.setVisible(false);
        
        modelWorkingDayList.clear();
        textClear();
    }//GEN-LAST:event_btnReturnStaffActionPerformed

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
        registerPatientPanel.setVisible(true);
        registerAdminPanel.setVisible(false);
        registerChoosePanel.setVisible(false);
        registerStaffPanel.setVisible(false);
        
        lblSuccessRegisterPatient.setVisible(false);
    }//GEN-LAST:event_jPanel19MouseClicked

    private void btnRegisterPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterPatientActionPerformed
        // TODO add your handling code here:
        // get the value
        String name = txtNameRegisterPatient.getText().strip();
        String icNumber = txtICNumberRegisterPatient.getText().strip();
        String gender = cboGenderRegisterPatient.getSelectedItem().toString();
        String race = txtRaceRegisterPatient.getText().strip();
        String age = txtAgeRegisterPatient.getText().strip();
        String state = txtStateRegisterPatient.getText().strip();
        String contactNumber = txtContactNumberRegisterPatient.getText().strip();
        String email = txtEmailRegisterPatient.getText().strip();
        String userRole = "Patient";

        // input validation
        boolean nameValid = Validation.string(name);
        boolean icNumberValid = Validation.icNumber(icNumber);
        boolean raceValid = Validation.string(race);
        boolean ageValid = Validation.age(age);
        boolean stateValid = Validation.string(state);
        boolean contactNumberValid = Validation.contactNumber(contactNumber);
        boolean emailValid = Validation.email(email);

        // checking the valid status
        if(name.isEmpty() || icNumber.isEmpty() || gender.isEmpty() || race.isEmpty() ||
            state.isEmpty() || contactNumber.isEmpty() || email.isEmpty()) {
            warningDialog("Empty", null,  lblSuccessRegisterPatient);
        }

        else if (nameValid == false || raceValid == false || stateValid == false) {
            String title = "";

            if (nameValid == false) {
                title = "NAME";
            }
            else if (raceValid == false) {
                title = "RACE";
            }
            else if (stateValid == false) {
                title = "REGION";
            }
            
            warningDialog("String", title,  lblSuccessRegisterPatient);
        }

        else if (icNumberValid == false) {
            warningDialog("IC Number", null,  lblSuccessRegisterPatient);
        }
        
        else if (ageValid == false) {
            warningDialog("Age", null,  lblSuccessRegisterPatient);
        }
        
        else if (contactNumberValid == false) {
            warningDialog("Contact Number", null,  lblSuccessRegisterPatient);
        }

        else if (emailValid == false) {
            warningDialog("Email", null,  lblSuccessRegisterPatient);
        }

        // run when pass the validation
        else {
            // generate new user ID
            String newUserID = Utility_Methods.autoGenerateID("patientData.txt", "P");

            // generate default password
            String birthday = icNumber.substring(2, 6);
            String defaultPassword = newUserID + "@" + birthday;

            // set the data
            Patient newPatient = new Patient(newUserID, name, defaultPassword, userRole, icNumber, gender,
                                    state, email, contactNumber, age, race);

            newPatient.writeNewPatientData();
            
            System.out.println(name + " (" + userRole + ") " + "has been successfully registered.");


            lblSuccessRegisterPatient.setVisible(true);

            // data that display at register slip
            lblNamePatient.setText(newPatient.getUserName());
            lblRolePatient.setText(userRole);
            lblUserIDPatient.setText(newPatient.getUserID());
            lblDefaultPasswordPatient.setText(newPatient.getUserPassword());

            lblRegisterPersonNamePatient.setText(admin.getUserName());
            lblDatePatient.setText(localDateToString);

            // clear the text
            textClear();
        }
    }//GEN-LAST:event_btnRegisterPatientActionPerformed

    private void btnReturnRegisterPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnRegisterPatientActionPerformed
        // TODO add your handling code here:
        registerChoosePanel.setVisible(true);
        registerStaffPanel.setVisible(false);
        registerAdminPanel.setVisible(false);
        registerPatientPanel.setVisible(false);

        lblSuccessRegisterAdmin.setVisible(false);

        lblNameAdmin.setText(this.defaultText);
        lblRoleAdmin.setText(this.defaultText);
        lblUserIDAdmin.setText(this.defaultText);
        lblDefaultPasswordAdmin.setText(this.defaultText);

        lblRegisterPersonNameAdmin.setText(this.defaultText);
        lblDateAdmin.setText(this.defaultText);

        textClear();
    }//GEN-LAST:event_btnReturnRegisterPatientActionPerformed

    private void cboPrescriptionIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPrescriptionIDActionPerformed
        // TODO add your handling code here:
        btnViewMedicalReport.setEnabled(true);
        
        String userSelectedID = cboPrescriptionID.getSelectedItem().toString();
        
        ArrayList<String[]> prescriptionDataList = File_Control.readFile("prescriptionData.txt", true);
        ArrayList<String[]> medicalReportDataList = File_Control.readFile("medicalReport.txt", true);
        ArrayList<String[]> patientDataList = File_Control.readFile("patientData.txt", true);
        ArrayList<String[]> childDataList = File_Control.readFile("childData.txt", true);
        ArrayList<String[]> doctorDataList = File_Control.readFile("doctorData.txt", true);
        
        String prescriptionID, medicalReportID="", medicines="", prescriptionDate, dispensePerson, dispenseDate, fee, paymentStatus;
        for (String[] list : prescriptionDataList) {
            prescriptionID = list[0];
            
            if (prescriptionID.equals(userSelectedID)) {
                medicalReportID = list[1];
                medicines = list[2];
                prescriptionDate = list[3];
                dispensePerson = list[4];
                dispenseDate = list[5];
                fee = list[6];
                paymentStatus = list[7];
            
                lblPrescriptionID.setText(prescriptionID);
                lblMedicalReportID.setText(medicalReportID);
                lblPrescriptionDate.setText(prescriptionDate);
                lblPrescriptionFee.setText("RM " + fee);
                
                if (dispensePerson.equals("-") || dispenseDate.equals("-")) {
                    lblProgression.setText("Pending Dispense");
                    lblDispensedBy.setText(this.defaultText);
                    lblDispensedDate.setText(this.defaultText);
                    lblPrescriptionTips.setText("Medication not dispensed. Please dispense.");
                    
                    btnDispenseMedicines.setEnabled(true);
                }
                else {
                    lblProgression.setText("Dispensed");
                    lblDispensedBy.setText(dispensePerson);
                    lblDispensedDate.setText(dispenseDate);
                    lblPrescriptionTips.setText("Medication already dispensed. Cannot dispense again.");
                    
                    btnDispenseMedicines.setEnabled(false);
                }
                
                if (paymentStatus.equals("1")) {
                    lblPaymentStatus.setText("Pending Payment");
                }
                else {
                    lblPaymentStatus.setText("Paid");
                }
                
                break;
            }
        }
        
        String patientID="", doctorID="";
        for (String[] list : medicalReportDataList) {
            
            if (medicalReportID.equals(list[0])) {
                patientID = list[1];
                doctorID = list[7];
                
                lblPatientID.setText(patientID);
                lblDoctorID.setText(doctorID);
                
                break;
            }
        }
        
        String patientName;
        if (patientID.startsWith("P")) {
            for (String[] list : patientDataList) {
                if (patientID.equals(list[0])) {
                    patientName = list[1];

                    lblPatientName.setText(patientName);
                    break;
                }
            }
        }
        
        else if (patientID.startsWith("CH")) {
            for (String[] list : childDataList) {
                if (patientID.equals(list[0])) {
                    patientName = list[2];

                    lblPatientName.setText(patientName);
                    break;
                }
            }
        }
        
        String doctorName;
        for (String[] list : doctorDataList) {
            if (doctorID.equals(list[0])) {
                doctorName = list[1];
                
                lblDoctorName.setText(doctorName);
                break;
            }
        }
        
        // make medicines from String to Array
        String[] medicinesList = medicines.split("/");
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        for (String data : medicinesList) {
            listModel.addElement(data);
        }
        
        lstPrescribeMedication.setModel(listModel);
        
    }//GEN-LAST:event_cboPrescriptionIDActionPerformed

    private void btnDispenseMedicinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDispenseMedicinesActionPerformed
        // TODO add your handling code here:
        dispenseMedicinesPanel.setVisible(true);
        viewPrescriptionPanel.setVisible(false);
        viewMedicalReportPanel.setVisible(false);
        
        lblSuccessDispenseMedicines.setVisible(false);
        
        
        cboMedicineID.setEnabled(true);
        btnDispense.setEnabled(true);
        
        modelComboBoxMedicineID();
        
        
        // model the Prescribe Medication Jlist in Dispense Medicine Panel
        ArrayList<String[]> prescriptionDataList = File_Control.readFile("prescriptionData.txt", true);
        String prescriptionID = cboPrescriptionID.getSelectedItem().toString();
        
        for (String[] list : prescriptionDataList) {
            String medicines = list[2];
            
            if (prescriptionID.equals(list[0])) {
                // make medicines from String to Array
                String[] medicinesList = medicines.split("/");

                DefaultListModel<String> listModel = new DefaultListModel<>();

                for (String data : medicinesList) {
                    listModel.addElement(data);
                }

                lstPrescribeMedication2.setModel(listModel);
            }
        }
        
        lstPrescribeMedication2.setEnabled(false);
        
    }//GEN-LAST:event_btnDispenseMedicinesActionPerformed

    private void btnViewMedicalReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewMedicalReportActionPerformed
        // TODO add your handling code here:
        viewMedicalReportPanel.setVisible(true);
        dispenseMedicinesPanel.setVisible(false);
        viewPrescriptionPanel.setVisible(false);

        String medicalReportID = lblMedicalReportID.getText().strip();
        
        ArrayList<String[]> medicalReportDataList = File_Control.readFile("medicalReport.txt", true);
        ArrayList<String[]> patientDataList = File_Control.readFile("patientData.txt", true);
        ArrayList<String[]> childDataList = File_Control.readFile("childData.txt", true);
        ArrayList<String[]> doctorDataList = File_Control.readFile("doctorData.txt", true);
        
        String patientID="", region, gender, age="", diasease, description, doctorID="", date, fee, paymentStatus, bloodTest;
        for (String[] list : medicalReportDataList) {
            if (medicalReportID.equals(list[0])) {
                patientID = list[1];
                region = list[2];
                gender = list[3];
                age = list[4];
                diasease = list[5];
                description = list[6];
                doctorID = list[7];
                date = list[8];
                fee = list[9];
                paymentStatus = list[10];
                bloodTest = list[11];
                
                lblMedicalReportID2.setText(medicalReportID);
                lblPatientID2.setText(patientID);
                lblDiasease.setText(diasease);
                lblPatientGender.setText(gender);
                lblPatientRegion.setText(region);
                
                areaPatientDescription.setLineWrap(true);
                areaPatientDescription.setWrapStyleWord(true);
                areaPatientDescription.setText(description);
                
                if (bloodTest.equals("-")) {
                    modelBloodTestList.addElement("-");
                    
                    listBloodTest.setModel(modelBloodTestList);
                }
                else {
                    String[] bloodTestList = bloodTest.split(",");
                    
                    for (String data : bloodTestList) {
                        modelBloodTestList.addElement(data);
                    }
                    
                    listBloodTest.setModel(modelBloodTestList);
                }
                
                lblDoctorID2.setText(doctorID);
                lblMedicalReportDate.setText(date);
                lblPrescriptionFee1.setText("RM " + fee);
                
                
                if (paymentStatus.equals("1")) {
                    lblPaymentStatus2.setText("Pending Payment");
                }
                else {
                    lblPaymentStatus2.setText("Paid");
                }
                
                break;
            }
        }
        
        String doctorName, specialist;
        for (String[] list : doctorDataList) {
            if (doctorID.equals(list[0])) {
                doctorName = list[1];
                specialist = list[3];
                
                lblDoctorName2.setText(doctorName);
                lblSpecialist.setText(specialist);
                break;
            }
        }
        
        String patientName, bloodType;
        if (patientID.startsWith("P")) {
            for (String[] list : patientDataList) {
                if (patientID.equals(list[0])) {
                    patientName = list[1];
                    bloodType = list[9];

                    lblPatientName2.setText(patientName);
                    lblPatientBloodType.setText(bloodType);
                    break;
                }
            }
        }
        
        else if (patientID.startsWith("CH")) {
            for (String[] list : childDataList) {
                if (patientID.equals(list[0])) {
                    patientName = list[2];
                    bloodType = list[7];

                    lblPatientName2.setText(patientName);
                    lblPatientBloodType.setText(bloodType);
                    break;
                }
            }
        }
        
        if (patientID.startsWith("P")) {
            lblPatientAge.setText(age);
        }
        
        else if (patientID.startsWith("CH")) {
            String[] childAge = age.split(",");
            
            lblPatientAge.setText(childAge[0] + " Yaers " + childAge[1] + " Months");
        }
        
    }//GEN-LAST:event_btnViewMedicalReportActionPerformed

    private void ManageAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageAdminMouseClicked
        // TODO add your handling code here:
        ManageAdminPanel.setVisible(true);
        UserManagementMainPanel.setVisible(false);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        UpdatePasswordPanel.setVisible(false);
        
        modelComboBoxAdminID();
        disenable();
        
        btnUpdateRecordAdmin.setEnabled(true);
        btnSaveAdmin.setEnabled(false);
        lblSuccessUpdateRecordAdmin.setVisible(false);
        
        btnUpdateRecordAdmin.setEnabled(false);
    }//GEN-LAST:event_ManageAdminMouseClicked

    private void ManageStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageStaffMouseClicked
        // TODO add your handling code here:
        ManageStaffPanel.setVisible(true);
        ManageAdminPanel.setVisible(false);
        UserManagementMainPanel.setVisible(false);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        UpdatePasswordPanel.setVisible(false);
        
        personalInformationPanel.setVisible(true);
        positionInformationPanel.setVisible(false);
        btnPrevious.setEnabled(false);
        btnNext.setEnabled(true);
        
        modelComboBoxStaffID();
        btnClearWorkingDayStaff.setEnabled(false);
        btnAddWorkingDayStaff.setEnabled(false);
        btnSaveWorkingDayStaff.setEnabled(false);
        btnSaveStaff.setEnabled(false);
        lblSuccessUpdateRecordStaff.setVisible(false);
        disenable();
        
        modelComboBoxUpdateWorkingDay();
        
        modelUpdateWorkingDayList.clear();
        lstWorkingDayStaff.setModel(modelUpdateWorkingDayList);
        
        btnUpdateRecordStaff.setEnabled(false);
    }//GEN-LAST:event_ManageStaffMouseClicked

    private void ManagePatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManagePatientMouseClicked
        // TODO add your handling code here:
        ManagePatientPanel.setVisible(true);
        ManageStaffPanel.setVisible(false);
        ManageAdminPanel.setVisible(false);
        UserManagementMainPanel.setVisible(false);
        DeleteAccountPanel.setVisible(false);
        UpdatePasswordPanel.setVisible(false);
        
        modelComboBoxPatientID();
        patientPanel.setVisible(true);
        childrenPanel.setVisible(false);
        
        btnSavePatient.setEnabled(false);
        cboPatientID.setEnabled(false);
        disenable();
        
        modelComboBoxChildrenYearsMonths();
        lblSuccessUpdateRecordPatient.setVisible(false);
        btnUpdateRecordPatient.setEnabled(false);
        
    }//GEN-LAST:event_ManagePatientMouseClicked

    private void DeleteUserAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteUserAccountMouseClicked
        // TODO add your handling code here:
        DeleteAccountPanel.setVisible(true);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        ManageAdminPanel.setVisible(false);
        UserManagementMainPanel.setVisible(false);
        UpdatePasswordPanel.setVisible(false);
        
        userTable();
        tblUsers.setEnabled(false);
        
        lblSuccessDeleteUser.setVisible(false);
        
        modelComboBoxRole();
        cboUserID.setEnabled(false);
    }//GEN-LAST:event_DeleteUserAccountMouseClicked

    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUsersMouseClicked

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        // TODO add your handling code here:
        String selectedRole = cboRole.getSelectedItem().toString();
        String deleteUserID = cboUserID.getSelectedItem().toString();

        if (chbDeleteUser1.isSelected() && chbDeleteUser2.isSelected()) {
            // pass the deleteUserID to deleteUsers Method that at Admin class
            boolean deleteUserProcess = admin.deleteUsers(deleteUserID);
            
            if (deleteUserProcess == true) {
                System.out.println(deleteUserID + "'s record has been successfully deleted.");
                chbDeleteUser1.setSelected(false);
                chbDeleteUser2.setSelected(false);
                
                lblSuccessDeleteUser.setVisible(true);
                
                // everytime click delete will model the combobox
                // to get the latest ID
                modelComboBoxUserID(selectedRole);

                // everytime click delete also need to renew table
                userTable();
                tblUsers.setEnabled(false);
            }
        }
        
        else {
            warningDialog("deleteStatement", null,  lblSuccessDeleteUser);
        }
    }//GEN-LAST:event_btnDeleteUserActionPerformed

    private void btnReturnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnDeleteUserActionPerformed
        // TODO add your handling code here:
        UserManagementMainPanel.setVisible(true);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        ManageAdminPanel.setVisible(false);
        
        textClear();
    }//GEN-LAST:event_btnReturnDeleteUserActionPerformed

    private void chbDeleteUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDeleteUser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbDeleteUser1ActionPerformed

    private void cboMedicineIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMedicineIDActionPerformed
        // TODO add your handling code here:
        String userSelectedID = cboMedicineID.getSelectedItem().toString();

        ArrayList<String[]> inventoryDataList = File_Control.readFile("inventoryData.txt", true);

        for (String[] list : inventoryDataList) {
            String itemID = list[0];
            String itemName = list[1];
            String price = list[4];
            String currentStock = list[6];

            if (itemID.equals(userSelectedID)) {
                lblMedicineID.setText(itemID);
                lblMedicineName.setText(itemName);
                lblMedicinePrice.setText("RM " + price);
                lblMedicineCurrentStock.setText(currentStock);
            }
        }
    }//GEN-LAST:event_cboMedicineIDActionPerformed

    private void btnDispenseMedicinesBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDispenseMedicinesBackActionPerformed
        // TODO add your handling code here:
        viewPrescriptionPanel.setVisible(true);
        viewMedicalReportPanel.setVisible(false);
        dispenseMedicinesPanel.setVisible(false);
        
        lblSuccessDispenseMedicines.setVisible(false);
    }//GEN-LAST:event_btnDispenseMedicinesBackActionPerformed

    private void btnDispenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDispenseActionPerformed
        // TODO add your handling code here:
        ArrayList<String[]> inventoryDataList = File_Control.readFile("inventoryData.txt", false);
        ArrayList<String[]> prescriptionDataList = File_Control.readFile("prescriptionData.txt", false);        
                
        int size = lstPrescribeMedication2.getModel().getSize();
        // create an empty Array
        String[] JListData = new String[size];
        // create an empty ArrayList
        ArrayList<String[]> medicineList = new ArrayList<>();
        
        
        // make JList to Array
        for (int i = 0; i < size; i++) {
            JListData[i] = lstPrescribeMedication2.getModel().getElementAt(i);
        }
        
        // make Array to ArrayList
        for (String row : JListData) {
            String[] data = row.split(",");
            medicineList.add(data);
        }
        
        boolean stockStatus = false; // check the stock is enough for dispense medicines or not
        boolean loopStatus = true; // use to stop the entire loop if one of the medicines insufficient
        for (String[] i : medicineList) {
            if (loopStatus == true) {
                String medicineID = i[0];
                int quantity = Integer.parseInt(i[2]);

                for (String[] j : inventoryDataList) {
                    if (medicineID.equals(j[0])) {
                        int currentStock = Integer.parseInt(j[6]);
                        
                        // check the current stock enough or not
                        // stock not enough
                        if (quantity >= currentStock) {
                            JOptionPane.showMessageDialog(this,
                                "Medicine ID: " + medicineID + "\n" +
                                "The current stock is insufficient.\n" +
                                "Decision: Pending Dispense Medicine\n"+
                                "Please contact the relevant person in charge to replenish the stock.",
                                "Stock Insufficient",
                                JOptionPane.WARNING_MESSAGE);
                            loopStatus = false;
                            stockStatus = false;
                            break;
                        }
                        
                        // stock enough
                        else {
                            int updatedStock = currentStock - quantity;
                            j[6] = Integer.toString(updatedStock);
                            stockStatus = true;
                            loopStatus = true; // the loop will continue
                        }
                    }
                }
            }
            else {
                break;
            }
        }
        
        if (stockStatus == true && loopStatus == true) {
            String prescriptionID = cboPrescriptionID.getSelectedItem().toString();

            for (String[] list : prescriptionDataList) {
                if (prescriptionID.equals(list[0])) {
                    list[4] = admin.getUserName();
                    list[5] = localDateToString;
                }
            }

            File_Control.writeFile("inventoryData.txt", inventoryDataList);
            File_Control.writeFile("prescriptionData.txt", prescriptionDataList);
            
            System.out.println(prescriptionID + "'s medicines has been successfully dispensed by " + admin.getUserID() + 
                    " (" + admin.getUserName() + ").");
            
            lblProgression.setText("Dispensed");
            lblDispensedBy.setText(admin.getUserName());
            lblDispensedDate.setText(localDateToString);

            lblSuccessDispenseMedicines.setVisible(true);
            btnDispenseMedicines.setEnabled(false);
            btnDispense.setEnabled(false);
        }
    }//GEN-LAST:event_btnDispenseActionPerformed

    private void cboUserIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUserIDActionPerformed
        // TODO add your handling code here:
        String userID = cboUserID.getSelectedItem().toString();
        ArrayList<String[]> dataList = File_Control.readFile("loginData.txt", true);
        
        for (String[] list : dataList) {
            if (userID.equals(list[0])) {
                lblUserID.setText(list[0]);
                lblUserRole.setText(list[1]);
                lblUserName.setText(list[2]);
                break;
            }
        }
       
    }//GEN-LAST:event_cboUserIDActionPerformed

    private void btnReturnMedicalReoportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnMedicalReoportActionPerformed
        // TODO add your handling code here:
        viewPrescriptionPanel.setVisible(true);
        viewMedicalReportPanel.setVisible(false);
        dispenseMedicinesPanel.setVisible(false);
        
        modelBloodTestList.clear();
    }//GEN-LAST:event_btnReturnMedicalReoportActionPerformed

    private void btnReturnAdmin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnAdmin1ActionPerformed
        // TODO add your handling code here:
        UserManagementMainPanel.setVisible(true);
        ManageAdminPanel.setVisible(false);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        
        disenable();
    }//GEN-LAST:event_btnReturnAdmin1ActionPerformed

    private void btnUpdateRecordAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordAdminActionPerformed
        // TODO add your handling code here:
        updateRecordEnableText();
        btnSaveAdmin.setEnabled(true);
    }//GEN-LAST:event_btnUpdateRecordAdminActionPerformed

    private void btnSaveAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAdminActionPerformed
        // TODO add your handling code here:
        String userName = txtUserNameAdmin.getText().strip();
        String nationality = txtNationalityAdmin.getText().strip();
        String region = txtRegionAdmin.getText().strip();
        String contactNumber = txtContactNumberAdmin.getText().strip();
        String email = txtEmailAdmin.getText().strip();
        
        boolean userNameValid = Validation.string(userName);
        boolean nationalityValid = Validation.string(nationality);
        boolean regionValid = Validation.string(region);
        boolean contactNumberValid = Validation.contactNumber(contactNumber);
        boolean emailValid = Validation.email(email);
        
        if(userName.isEmpty() || nationality.isEmpty() || region.isEmpty() ||
            contactNumber.isEmpty() || email.isEmpty()) {
            warningDialog("Empty", null,  lblSuccessUpdateRecordAdmin);
        }

        else if (userNameValid == false || nationalityValid == false || regionValid == false) {
            String title = "";

            if (userNameValid == false) {
                title = "USER NAME";
            }
            else if (nationalityValid == false) {
                title = "NATIONALITY";
            }
            else if (regionValid == false) {
                title = "REGION";
            }
            
            warningDialog("String", title,  lblSuccessUpdateRecordAdmin);
        }

        else if (contactNumberValid == false) {
            warningDialog("Contact Number", null,  lblSuccessUpdateRecordAdmin);
        }

        else if (emailValid == false) {
            warningDialog("Email", null,  lblSuccessUpdateRecordAdmin);
        }
        
        else {
            String selectedID = lblAdminUserID.getText();
            
            ArrayList<String[]> dataList = File_Control.readFile("adminData.txt", false);
            
            for (String[] list : dataList) {
                if (selectedID.equals(list[0])) {
                    list[1] = userName;
                    list[4] = nationality;
                    list[5] = region;
                    list[6] = contactNumber;
                    list[7] = email;
                }
            }
            
            File_Control.writeFile("adminData.txt", dataList);
            System.out.println(userName + " (Admin)'s record has been successfully updated.");
            lblSuccessUpdateRecordAdmin.setVisible(true);
            btnSaveAdmin.setEnabled(false);
            disenable();
        }
    }//GEN-LAST:event_btnSaveAdminActionPerformed

    private void txtUserNameAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameAdminActionPerformed

    private void txtNationalityAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNationalityAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNationalityAdminActionPerformed

    private void txtRegionAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegionAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegionAdminActionPerformed

    private void txtContactNumberAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactNumberAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactNumberAdminActionPerformed

    private void txtEmailAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailAdminActionPerformed

    private void btnReturnStaff1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnStaff1ActionPerformed
        // TODO add your handling code here:
        UserManagementMainPanel.setVisible(true);
        ManageAdminPanel.setVisible(false);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        
        disenable();
        
    }//GEN-LAST:event_btnReturnStaff1ActionPerformed

    private void btnUpdateRecordStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordStaffActionPerformed
        // TODO add your handling code here:
        updateRecordEnableText();
        btnSaveStaff.setEnabled(true);
        btnClearWorkingDayStaff.setEnabled(true);
        modelComboBoxUpdateWorkingDay();
    }//GEN-LAST:event_btnUpdateRecordStaffActionPerformed

    private void btnSaveStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveStaffActionPerformed
        // TODO add your handling code here:
        String userName = txtUserNameStaff.getText().strip();
        String nationality = txtNationalityStaff.getText().strip();
        String region = txtRegionStaff.getText().strip();
        String qualification = txtQualificationStaff.getText().strip();
        String age = txtAgeStaff.getText().strip();
        String contactNumber = txtContactNumberStaff.getText().strip();
        String email = txtEmailStaff.getText().strip();
        
        String specialist = cboSpecialistStaff.getSelectedItem().toString();
        String office = txtOfficeStaff.getText().strip();
        
        // create an empty array
        String[] workingDay = new String[modelUpdateWorkingDayList.getSize()];
        // add data into that array
        for (int i = 0; i < modelUpdateWorkingDayList.getSize(); i++) {
            workingDay[i] = modelUpdateWorkingDayList.getElementAt(i).toString();
        }
        
        // input validation
        boolean userNameValid = Validation.string(userName);
        boolean nationalityValid = Validation.string(nationality);
        boolean regionValid = Validation.string(region);
        boolean qualificationValid = Validation.string(qualification);
        boolean ageValid = Validation.age(age);
        boolean contactNumberValid = Validation.contactNumber(contactNumber);
        boolean emailValid = Validation.email(email);
        
        // checking the valid status
        if(userName.isEmpty() || nationality.isEmpty() || region.isEmpty() || qualification.isEmpty() || 
        age.isEmpty() || contactNumber.isEmpty() || email.isEmpty() || office.isEmpty() || workingDay.length == 0) {
            warningDialog("Empty", null,  lblSuccessUpdateRecordStaff);
        }
        
        else if (userNameValid == false || nationalityValid == false || regionValid == false || qualificationValid == false) {
            String title = "";
            
            if (userNameValid == false) {
                title = "USER NAME";
            }
            else if (nationalityValid == false) {
                title = "NATIONALITY";
            }
            else if (regionValid == false) {
                title = "REGION";
            }
            else if (qualificationValid == false) {
                title = "QUALIFICATION";
            }
            
            warningDialog("String", title,  lblSuccessUpdateRecordStaff);
        }
        
        else if (ageValid == false) {
            warningDialog("Age", null,  lblSuccessUpdateRecordStaff);
        }

        else if (contactNumberValid == false) {
            warningDialog("Contact Number", null,  lblSuccessUpdateRecordStaff);
        }
        
        else if (emailValid == false) {
            warningDialog("Email", null,  lblSuccessUpdateRecordStaff);
        }
        
        // run when pass the validation
        else {
            String selectedID = lblStaffUserID.getText();
            ArrayList<String[]> dataList = File_Control.readFile("doctorData.txt", false);
            
            // change the array to string for working day
            String workingDayData = convertWorkingDayToTxtData(workingDay);
            
            for (String[] list : dataList) {
                if (selectedID.equals(list[0])) {
                    list[1] = userName;
                    list[2] = email;
                    list[3] = specialist;
                    list[4] = region;
                    list[6] = workingDayData;
                    list[7] = office;
                    list[8] = nationality;
                    list[9] = contactNumber;
                    list[11] = age;
                    list[12] = qualification;
                }
            }
            
            File_Control.writeFile("doctorData.txt", dataList);
            System.out.println(userName + " (Doctor)'s record has been successfully updated.");
            disenable();
            btnSaveStaff.setEnabled(false);
            lblSuccessUpdateRecordStaff.setVisible(true);
            btnClearWorkingDayStaff.setEnabled(false);
            btnAddWorkingDayStaff.setEnabled(false);
            btnSaveWorkingDayStaff.setEnabled(false);
        }
    }//GEN-LAST:event_btnSaveStaffActionPerformed

    private void btnClearWorkingDayStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearWorkingDayStaffActionPerformed
        // TODO add your handling code here:
        modelUpdateWorkingDayList.clear();
        btnAddWorkingDayStaff.setEnabled(true);
        btnSaveWorkingDayStaff.setEnabled(true);
        cboDayStaff.setEnabled(true);
    }//GEN-LAST:event_btnClearWorkingDayStaffActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        positionInformationPanel.setVisible(true);
        personalInformationPanel.setVisible(false); 
        
        btnPrevious.setEnabled(true);
        btnNext.setEnabled(false);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        personalInformationPanel.setVisible(true); 
        positionInformationPanel.setVisible(false);
        
        btnNext.setEnabled(true);
        btnPrevious.setEnabled(false);
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void cboAdminIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAdminIDActionPerformed
        // TODO add your handling code here:
        btnUpdateRecordAdmin.setEnabled(true);
        String selectedID = cboAdminID.getSelectedItem().toString();
        
        ArrayList<Admin> adminDataList = Admin.loadAdmins(true);
        
        for (Admin admins : adminDataList) {
            if (admins.getUserID().equals(selectedID)) {
                lblAdminUserID.setText(admins.getUserID());
                lblAdminIcNumber.setText(admins.getIcNumber());
                lblAdminRole.setText("Admin");
                
                txtUserNameAdmin.setText(admins.getUserName());
                txtNationalityAdmin.setText(admins.getNationality());
                txtRegionAdmin.setText(admins.getRegion());
                txtContactNumberAdmin.setText(admins.getContactNumber());
                txtEmailAdmin.setText(admins.getEmail());
                break;
            }
        }    
    }//GEN-LAST:event_cboAdminIDActionPerformed

    private void cboStaffIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboStaffIDActionPerformed
        // TODO add your handling code here:
        btnUpdateRecordStaff.setEnabled(true);
        modelUpdateWorkingDayList.clear();
        String selectedID = cboStaffID.getSelectedItem().toString();
        ArrayList<String[]> dataList = File_Control.readFile("doctorData.txt", true);
        
        for (String[] list : dataList) {
            String userID = list[0];
            String userName = list[1];
            String email = list[2];
            String specialist = list[3];
            String region = list[4];
            String icNumber = list[5];
            String workingDay = list[6];
            String office = list[7];
            String nationality = list[8];
            String contactNumber = list[9];
            String age = list[11];
            String education = list[12];
            
            if (selectedID.equals(userID)) {
                lblStaffUserID.setText(userID);
                lblStaffIcNumber.setText(icNumber);
                lblStaffRole.setText("Doctor");
                
                txtUserNameStaff.setText(userName);
                txtNationalityStaff.setText(nationality);
                txtRegionStaff.setText(region);
                txtAgeStaff.setText(age);
                txtContactNumberStaff.setText(contactNumber);
                txtEmailStaff.setText(email);
                txtQualificationStaff.setText(education);
                
                cboSpecialistStaff.setSelectedItem(specialist);
                txtOfficeStaff.setText(office);
                
                String workingDayToDisplayFormat = convertWorkingDayToDisplayData(workingDay);
                String[] workingDayList = workingDayToDisplayFormat.split("/");
                
                for (String data : workingDayList) {
                    modelUpdateWorkingDayList.addElement(data);
                }
                
                break;
            }
        }
    }//GEN-LAST:event_cboStaffIDActionPerformed

    private void btnAddWorkingDayStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWorkingDayStaffActionPerformed
        // TODO add your handling code here:
        String workingDay = cboDayStaff.getSelectedItem().toString();
        
        if (modelUpdateWorkingDayList.contains(workingDay)) {
            JOptionPane.showMessageDialog(this,
                "This day already added, please select another day.",
                "Pacific Data Validation Center (DVC)",
                JOptionPane.WARNING_MESSAGE);
        } 
        else {
            modelUpdateWorkingDayList.addElement(workingDay);
            cboDayStaff.removeItem(workingDay);
        }
    }//GEN-LAST:event_btnAddWorkingDayStaffActionPerformed

    private void btnSaveWorkingDayStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveWorkingDayStaffActionPerformed
        // TODO add your handling code here:
        btnAddWorkingDayStaff.setEnabled(false);
        btnSaveWorkingDayStaff.setEnabled(false);
        cboDayStaff.setEnabled(false);
    }//GEN-LAST:event_btnSaveWorkingDayStaffActionPerformed

    private void btnReturnPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnPatientActionPerformed
        // TODO add your handling code here:
        UserManagementMainPanel.setVisible(true);
        ManageAdminPanel.setVisible(false);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        
        cboPatientRole.setEnabled(true);
        lblSuccessUpdateRecordPatient.setVisible(false);
        disenable();
    }//GEN-LAST:event_btnReturnPatientActionPerformed

    private void btnUpdateRecordPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordPatientActionPerformed
        // TODO add your handling code here:
        updateRecordEnableText();
        btnSavePatient.setEnabled(true);
        cboPatientRole.setEnabled(false);
        cboPatientID.setEnabled(false);
    }//GEN-LAST:event_btnUpdateRecordPatientActionPerformed

    private void btnSavePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePatientActionPerformed
        // TODO add your handling code here:
        String selectedID = cboPatientID.getSelectedItem().toString();
        
        String targetUserName = "";
        boolean status = false;
        
        if (selectedID.startsWith("P")) {
            String userName = txtUserNamePatient.getText().strip();
            String age = txtAgePatient.getText().strip();
            String bloodType = cboBloodTypePatient.getSelectedItem().toString();
            String region = txtRegionPatient.getText().strip();
            String contactNumber = txtContactNumberPatient.getText().strip();
            String email = txtEmailPatient.getText().strip();
            
            boolean userNameValid = Validation.string(userName);
            boolean ageValid = Validation.age(age);
            boolean regionValid = Validation.string(region);
            boolean contactNumberValid = Validation.contactNumber(contactNumber);
            boolean emailValid = Validation.email(email);
            
            if(userName.isEmpty() || age.isEmpty() || region.isEmpty() || contactNumber.isEmpty() || email.isEmpty()) {
                warningDialog("Empty", null,  lblSuccessUpdateRecordPatient);
                }
            
            else if (userNameValid == false ||  regionValid == false) {
                String title = "";

                if (userNameValid == false) {
                    title = "USER NAME";
                }
                else if (regionValid == false) {
                    title = "REGION";
                }
                
                warningDialog("String", title,  lblSuccessUpdateRecordPatient);
            }

            else if (ageValid == false) {
                warningDialog("Age", null,  lblSuccessUpdateRecordPatient);
            }

            else if (contactNumberValid == false) {
                warningDialog("Contact Number", null,  lblSuccessUpdateRecordPatient);
            }

            else if (emailValid == false) {
                warningDialog("Email", null,  lblSuccessUpdateRecordPatient);
            }

            // run when pass the validation
            else {
                ArrayList<String[]> patientDataList = File_Control.readFile("patientData.txt", false);

                for (String[] list : patientDataList) {
                    if (selectedID.equals(list[0])) {
                        list[1] = userName;
                        list[3] = age;
                        list[6] = contactNumber;
                        list[7] = email;
                        list[8] = region;
                        list[9] = bloodType;
                    }
                }
                
                File_Control.writeFile("patientData.txt", patientDataList);
                targetUserName = userName;
                status = true;
            }
        }
        
        else if (selectedID.startsWith("C")) {
            String childrenName = txtUserNameChildren.getText().strip();
            String bloodType = cboBloodTypeChildren.getSelectedItem().toString();
            String region = txtRegionChildren.getText().strip();
            String age = cboYears.getSelectedItem().toString() + "," + cboMonths.getSelectedItem().toString();
            
            boolean childrenNameValid = Validation.string(childrenName);
            boolean regionValid = Validation.string(region);
            
            if(childrenName.isEmpty() || region.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "Please make sure all sections are not empty.",
                        "Pacific Data Validation Center (DVC)",
                        JOptionPane.WARNING_MESSAGE);

                    lblSuccessUpdateRecordPatient.setVisible(false);
                }
            
            else if (childrenNameValid == false ||  regionValid == false) {
                String title = "";

                if (childrenNameValid == false) {
                    title = "CHILDREN NAME";
                }
                else if (regionValid == false) {
                    title = "REGION";
                }

                JOptionPane.showMessageDialog(this,
                        "Please make sure the format of " + title + " is correct.\n" +
                        "1. Only letters\n" +
                        "2. Without number",
                        "Pacific Data Validation Center (DVC)",
                        JOptionPane.WARNING_MESSAGE);

                lblSuccessUpdateRecordPatient.setVisible(false);
            }
            
            else {
                ArrayList<String[]> childDataList = File_Control.readFile("childData.txt", false);

                for (String[] list : childDataList) {
                    if(selectedID.equals(list[0])) {
                        list[2] = childrenName;
                        list[5] = region;
                        list[6] = age;
                        list[7] = bloodType;
                    }
                }
                
                File_Control.writeFile("childData.txt", childDataList);
                targetUserName = childrenName;
                status = true;
            }
        }
        
        if (status == true) {
            System.out.println(targetUserName + " (Patient)'s record has been successfully updated.");
            disenable();
            lblSuccessUpdateRecordPatient.setVisible(true);
            btnSavePatient.setEnabled(false);
            cboPatientRole.setEnabled(true);
            cboPatientID.setEnabled(true);
        }
    }//GEN-LAST:event_btnSavePatientActionPerformed

    private void cboPatientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPatientIDActionPerformed
        // TODO add your handling code here:
        btnUpdateRecordPatient.setEnabled(true);
        String selectedID = cboPatientID.getSelectedItem().toString();
        
        ArrayList<String[]> patientDataList = File_Control.readFile("patientData.txt", true);
        ArrayList<String[]> childrenDataList = File_Control.readFile("childData.txt", true);
        
        if (selectedID.startsWith("P")) {
            for (String[] list : patientDataList) {
                String userID = list[0];
                String userName = list[1];
                String icNumber = list[2];
                String age = list[3];
                String contactNumber = list[6];
                String email = list[7];
                String region = list[8];
                String bloodType = list[9];
                
                if (selectedID.equals(userID)) {
                    lblPatientUserID.setText(userID);
                    lblPatientRole.setText(cboPatientRole.getSelectedItem().toString());
                    lblPatientIcNumber.setText(icNumber);
                    
                    txtUserNamePatient.setText(userName);
                    txtAgePatient.setText(age);
                    cboBloodTypePatient.setSelectedItem(bloodType);
                    txtRegionPatient.setText(region);
                    txtContactNumberPatient.setText(contactNumber);
                    txtEmailPatient.setText(email);
                    
                    break;
                }
            }   
        }
        else if (selectedID.startsWith("C")) {
            String parentID="";
            for (String[] list : childrenDataList) {
                String userID = list[0];
                parentID = list[1];
                String userName = list[2];
                String icNumber = list[3];
                String region = list[5];
                String age = list[6];
                String bloodType = list[7];
                
                if (selectedID.equals(userID)) {
                    lblChildrenUserID.setText(userID);
                    lblParientID.setText(parentID);
                    lblChildrenIcNumber.setText(icNumber);
                    
                    txtUserNameChildren.setText(userName);
                    cboBloodTypeChildren.setSelectedItem(bloodType);
                    txtRegionChildren.setText(region);
                    
                    String[] ageGroup = age.split(",");
                    
                    cboYears.setSelectedItem(ageGroup[0]);
                    cboMonths.setSelectedItem(ageGroup[1]);
                    
                    break;
                }
            }
            
            for (String[] list : patientDataList) {
                String userID = list[0];
                String parentName = list[1];
                
                if (parentID.equals(userID)) {
                    lblParentName.setText(parentName);
                }
            }
        }
    }//GEN-LAST:event_cboPatientIDActionPerformed

    private void txtRegionPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegionPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegionPatientActionPerformed

    private void txtAgePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgePatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgePatientActionPerformed

    private void txtEmailPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailPatientActionPerformed

    private void txtContactNumberPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactNumberPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactNumberPatientActionPerformed

    private void txtUserNamePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNamePatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNamePatientActionPerformed

    private void txtRegionChildrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegionChildrenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegionChildrenActionPerformed

    private void txtUserNameChildrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameChildrenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameChildrenActionPerformed

    private void cboPatientRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPatientRoleActionPerformed
        // TODO add your handling code here:
        String selectedRole = cboPatientRole.getSelectedItem().toString();
        
        if (selectedRole.equals("Patient")) {
            patientPanel.setVisible(true);
            childrenPanel.setVisible(false);
            modelComboBoxPatientID();
        }
        else if (selectedRole.equals("Children")) {
            childrenPanel.setVisible(true);    
            patientPanel.setVisible(false);
            modelComboBoxChildrenID();
        }
        
        cboPatientID.setEnabled(true);
    }//GEN-LAST:event_cboPatientRoleActionPerformed

    private void jPanel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel47MouseClicked
        // TODO add your handling code here:
        UpdatePasswordPanel.setVisible(true);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        ManageAdminPanel.setVisible(false);
        UserManagementMainPanel.setVisible(false);

        updatePasswordTable();
        tblUpdatePassword.setEnabled(false);

        btnSaveUpdatePassword.setEnabled(false);
        lblSuccessUpdatePassword.setVisible(false);
        lblTipsUpdatePassword.setVisible(false);   
    }//GEN-LAST:event_jPanel47MouseClicked

    private void tblUpdatePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUpdatePasswordMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblUpdatePasswordMouseClicked

    private void btnUpdateUserPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserPasswordActionPerformed
        // TODO add your handling code here:
        chbCondition1.setSelected(false);
        chbCondition2.setSelected(false);
        
        tblUpdatePassword.setEnabled(true);
        btnSaveUpdatePassword.setEnabled(true);
        lblSuccessUpdatePassword.setVisible(false);
        lblTipsUpdatePassword.setVisible(true);
    }//GEN-LAST:event_btnUpdateUserPasswordActionPerformed

    private void btnSaveUpdatePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUpdatePasswordActionPerformed
        // TODO add your handling code here:
        boolean stop = false;
        
        ArrayList<String[]> dataList = File_Control.readFile("loginData.txt", false);
        
        // get the table model
        DefaultTableModel tableModel = (DefaultTableModel)tblUpdatePassword.getModel();
        
        int count = 0;
        for (String[] list : dataList) {
            String accStatus = list[list.length - 1];
            
            if (accStatus.equals("1")) {
                count += 1;
            } 
        }
        
        for (int row = 0; row < count; row++) {
            String userID = tableModel.getValueAt(row, 0).toString();
            String password = tableModel.getValueAt(row, 3).toString();

            if (password.isEmpty()) {
                stop = true;
                break;
            }
            
            else {
                for (String[] list : dataList) {
                    if (userID.equals(list[0])) {
                        list[3] = password;
                    }
                }
            }
        }
        
        if (stop == true) {
            warningDialog("Empty", null, lblSuccessUpdatePassword);
        }
        
        else if (chbCondition1.isSelected() && chbCondition2.isSelected()) {
            File_Control.writeFile("loginData.txt", dataList);
            System.out.println("Password has been successfully updated.");
            
            lblSuccessUpdatePassword.setVisible(true);
            lblTipsUpdatePassword.setVisible(false);
            btnSaveUpdatePassword.setEnabled(false);
            tblUpdatePassword.setEnabled(false);
            updatePasswordTable();
            
            chbCondition1.setSelected(false);
            chbCondition2.setSelected(false);
        }
        
        else {
            warningDialog("updateStatement", null, lblSuccessUpdatePassword);
        }
    }//GEN-LAST:event_btnSaveUpdatePasswordActionPerformed

    private void btnReturnUpdatePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnUpdatePasswordActionPerformed
        // TODO add your handling code here:
        UserManagementMainPanel.setVisible(true);
        UpdatePasswordPanel.setVisible(false);
        DeleteAccountPanel.setVisible(false);
        ManagePatientPanel.setVisible(false);
        ManageStaffPanel.setVisible(false);
        ManageAdminPanel.setVisible(false);
    }//GEN-LAST:event_btnReturnUpdatePasswordActionPerformed

    private void chbCondition1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbCondition1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbCondition1ActionPerformed

    private void cboRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRoleActionPerformed
        // TODO add your handling code here:
        cboUserID.setEnabled(true);
        String selectedRole = cboRole.getSelectedItem().toString();
        
        modelComboBoxUserID(selectedRole);
        
        // clear current table elements
        userTableModel.setRowCount(0);
        
        ArrayList<String[]> dataList = File_Control.readFile("loginData.txt", true);
        // append new elements
        for (String[] list : dataList) {
            String role = list[1];
            if (selectedRole.equals(role)) {
                userTableModel.addRow(list);
            }
        }
    }//GEN-LAST:event_cboRoleActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Suspended_Account sa = new Suspended_Account();
        sa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sa.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ProfileNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileNavBarMouseExited
        // TODO add your handling code here:
        ProfileNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_ProfileNavBarMouseExited

    private void ProfileNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileNavBarMouseEntered
        // TODO add your handling code here:
        ProfileNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_ProfileNavBarMouseEntered

    private void AssistanceNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssistanceNavBarMouseExited
        // TODO add your handling code here:
        AssistanceNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_AssistanceNavBarMouseExited

    private void AssistanceNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AssistanceNavBarMouseEntered
        // TODO add your handling code here:
        AssistanceNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_AssistanceNavBarMouseEntered

    private void RegisterNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterNavBarMouseExited
        // TODO add your handling code here:
        RegisterNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_RegisterNavBarMouseExited

    private void RegisterNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterNavBarMouseEntered
        // TODO add your handling code here:
        RegisterNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_RegisterNavBarMouseEntered

    private void UserManagementNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserManagementNavBarMouseExited
        // TODO add your handling code here:
        UserManagementNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_UserManagementNavBarMouseExited

    private void UserManagementNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserManagementNavBarMouseEntered
        // TODO add your handling code here:
        UserManagementNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_UserManagementNavBarMouseEntered

    private void InventoryNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryNavBarMouseExited
        // TODO add your handling code here:
        InventoryNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_InventoryNavBarMouseExited

    private void InventoryNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryNavBarMouseEntered
        // TODO add your handling code here:
        InventoryNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_InventoryNavBarMouseEntered

    private void HospitalInfoNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HospitalInfoNavBarMouseExited
        // TODO add your handling code here:
        HospitalInfoNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_HospitalInfoNavBarMouseExited

    private void HospitalInfoNavBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HospitalInfoNavBarMouseEntered
        // TODO add your handling code here:
        HospitalInfoNavBar.setBackground(hoverColor);
    }//GEN-LAST:event_HospitalInfoNavBarMouseEntered

    private void HealthCareNavBarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HealthCareNavBarMouseExited
        // TODO add your handling code here:
        HealthCareNavBar.setBackground(defaultColor);
    }//GEN-LAST:event_HealthCareNavBarMouseExited

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

    private void cboSafetyQuestion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSafetyQuestion2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSafetyQuestion2ActionPerformed

    private void cboSafetyQuestion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSafetyQuestion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSafetyQuestion1ActionPerformed

    private void cboSafetyQuestion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSafetyQuestion1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSafetyQuestion1MouseClicked

    private void cboSafetyQuestion2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboSafetyQuestion2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSafetyQuestion2MouseClicked
  
    public void disenable() {
        // profile section
        txtUserID.setEnabled(false);
        txtRole.setEnabled(false);
        txtName.setEnabled(false);
        txtICNumber.setEnabled(false);
        txtNationality.setEnabled(false);
        txtState.setEnabled(false);
        txtGender.setEnabled(false);
        txtContactNumber.setEnabled(false);
        txtEmail.setEnabled(false);
        txtAccSecurityStatus.setEnabled(false);
        btnPassword.setEnabled(false);
        btnSaveProfile.setEnabled(false);
        
        // profile section - safetyQuestion
        cboSafetyQuestion1.setEnabled(false);
        txtAnswer1.setEnabled(false);
        cboSafetyQuestion2.setEnabled(false);
        txtAnswer2.setEnabled(false);
        btnSaveSafetyQuestion.setEnabled(false);
        
        // profile section - changePassword
        txtNewPassword.setEnabled(false);
        txtConfirmNewPassword.setEnabled(false);
        btnSaveChangePassword.setEnabled(false);
        
        // inventory section
        tblInventory.setEnabled(false);
        btnSaveInventory.setEnabled(false);
        
        // register staff
        cboDayRegisterStaff.setEnabled(false);
        btnAddRegisterStaff.setEnabled(false);
        
        // update admin info
        txtUserNameAdmin.setEnabled(false);
        txtNationalityAdmin.setEnabled(false);
        txtRegionAdmin.setEnabled(false);
        txtContactNumberAdmin.setEnabled(false);
        txtEmailAdmin.setEnabled(false);
        
        // update staff info
        txtUserNameStaff.setEnabled(false);
        txtNationalityStaff.setEnabled(false);
        txtRegionStaff.setEnabled(false);
        txtAgeStaff.setEnabled(false);
        txtContactNumberStaff.setEnabled(false);
        txtEmailStaff.setEnabled(false);
        txtQualificationStaff.setEnabled(false);
        cboSpecialistStaff.setEnabled(false);
        txtOfficeStaff.setEnabled(false);
        cboDayStaff.setEnabled(false);
        lstWorkingDayStaff.setEnabled(false);
        
        // update patient info
        txtUserNamePatient.setEnabled(false);
        txtAgePatient.setEnabled(false);
        cboBloodTypePatient.setEnabled(false);
        txtRegionPatient.setEnabled(false);
        txtContactNumberPatient.setEnabled(false);
        txtEmailPatient.setEnabled(false);
        
        txtUserNameChildren.setEnabled(false);
        cboBloodTypeChildren.setEnabled(false);
        txtRegionChildren.setEnabled(false);
        cboYears.setEnabled(false);
        cboMonths.setEnabled(false);
    }
    
    public void profileEnableText() {
        // profile section
        txtUserID.setEnabled(false);
        txtRole.setEnabled(false);
        txtName.setEnabled(true);
        txtICNumber.setEnabled(false);
        txtNationality.setEnabled(false);
        txtState.setEnabled(true);
        txtGender.setEnabled(false);
        txtContactNumber.setEnabled(true);
        txtEmail.setEnabled(true);
        txtAccSecurityStatus.setEnabled(false);
        btnPassword.setEnabled(true);
        btnSaveProfile.setEnabled(true);
        
        // profile section - safetyQuestion
        cboSafetyQuestion1.setEnabled(true);
        txtAnswer1.setEnabled(true);
        cboSafetyQuestion2.setEnabled(true);
        txtAnswer2.setEnabled(true);
        btnSaveSafetyQuestion.setEnabled(true);
        
        // profile section - changePassword
        txtNewPassword.setEnabled(true);
        txtConfirmNewPassword.setEnabled(true);
        btnSaveChangePassword.setEnabled(true);
    }
    
    public void updateRecordEnableText() {
        // update admin info
        txtUserNameAdmin.setEnabled(true);
        txtNationalityAdmin.setEnabled(true);
        txtRegionAdmin.setEnabled(true);
        txtContactNumberAdmin.setEnabled(true);
        txtEmailAdmin.setEnabled(true);
        
        // update staff info
        txtUserNameStaff.setEnabled(true);
        txtNationalityStaff.setEnabled(true);
        txtRegionStaff.setEnabled(true);
        txtAgeStaff.setEnabled(true);
        txtContactNumberStaff.setEnabled(true);
        txtEmailStaff.setEnabled(true);
        txtQualificationStaff.setEnabled(true);
        cboSpecialistStaff.setEnabled(true);
        txtOfficeStaff.setEnabled(true);
        
        // update patient info
        txtUserNamePatient.setEnabled(true);
        txtAgePatient.setEnabled(true);
        cboBloodTypePatient.setEnabled(true);
        txtRegionPatient.setEnabled(true);
        txtContactNumberPatient.setEnabled(true);
        txtEmailPatient.setEnabled(true);
        
        txtUserNameChildren.setEnabled(true);
        cboBloodTypeChildren.setEnabled(true);
        txtRegionChildren.setEnabled(true);
        cboYears.setEnabled(true);
        cboMonths.setEnabled(true);
    }
    
    public void textClear() {
        txtItemName.setText("");
        txtSuplier.setText("");
        txtSafetyStock.setText("");
        txtCurrentStock.setText("");
        txtPrice.setText("");
        
        txtNameRegisterAdmin.setText("");
        txtIcNumberRegisterAdmin.setText("");
        txtStateRegisterAdmin.setText("");
        txtContactNumberRegisterAdmin.setText("");
        txtEmailRegisterAdmin.setText("");
        
        txtNameRegisterStaff.setText("");
        txtICNumberRegisterStaff.setText("");
        txtAgeRegisterStaff.setText("");
        txtStateRegisterStaff.setText("");
        txtContactNumberRegisterStaff.setText("");
        txtEmailRegisterStaff.setText("");
        txtOfficeRegisterStaff.setText("");
        
        txtNameRegisterPatient.setText("");
        txtICNumberRegisterPatient.setText("");
        txtRaceRegisterPatient.setText("");
        txtAgeRegisterPatient.setText("");
        txtStateRegisterPatient.setText("");
        txtContactNumberRegisterPatient.setText("");
        txtEmailRegisterPatient.setText("");
        
        lblUserID.setText(this.defaultText);
        lblUserName.setText(this.defaultText);
        lblUserRole.setText(this.defaultText);
        
        txtItemName.setText(""); 
        txtSuplier.setText("");
        txtPrice.setText("");
        txtSafetyStock.setText("");
        txtCurrentStock.setText("");
    }
    
    public void setDefaultText() {
        // assistance section
        lblPrescriptionID.setText(this.defaultText);
        lblMedicalReportID.setText(this.defaultText);
        lblPatientID.setText(this.defaultText);
        lblPatientName.setText(this.defaultText);
        lblDoctorID.setText(this.defaultText);
        lblDoctorName.setText(this.defaultText);
        lblPrescriptionDate.setText(this.defaultText);
        lblPrescriptionFee.setText(this.defaultText);
        lblProgression.setText(this.defaultText);
        lblDispensedBy.setText(this.defaultText);
        lblPaymentStatus.setText(this.defaultText);
        lblDispensedDate.setText(this.defaultText);
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
        java.awt.EventQueue.invokeLater(() -> new Admin_Frame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddItemPanel;
    private javax.swing.JPanel AdminWelcomePanel;
    private javax.swing.JPanel AssistanceNavBar;
    private javax.swing.JPanel AssistancePanel;
    private javax.swing.JPanel DeleteAccountPanel;
    private javax.swing.JPanel DeleteItemPanel;
    private javax.swing.JPanel DeleteUserAccount;
    private javax.swing.JPanel HealthCareNavBar;
    private javax.swing.JPanel HospitalInfoNavBar;
    private javax.swing.JPanel InventoryNavBar;
    private javax.swing.JPanel InventoryPanel;
    private javax.swing.JPanel ManageAdmin;
    private javax.swing.JPanel ManageAdminPanel;
    private javax.swing.JPanel ManagePatient;
    private javax.swing.JPanel ManagePatientPanel;
    private javax.swing.JPanel ManageStaff;
    private javax.swing.JPanel ManageStaffPanel;
    private javax.swing.JPanel ProfileNavBar;
    private javax.swing.JPanel ProfilePanel;
    private javax.swing.JPanel RegisterNavBar;
    private javax.swing.JPanel RegisterStaffPanel;
    private javax.swing.JPanel ReportPanel;
    private javax.swing.JPanel ShowSpace;
    private javax.swing.JPanel ShowSpace2;
    private javax.swing.JPanel ShowSpace6;
    private javax.swing.JPanel Slip;
    private javax.swing.JPanel Slip1;
    private javax.swing.JPanel StockReportPanel;
    private javax.swing.JPanel UpdateItemPanel;
    private javax.swing.JPanel UpdatePasswordPanel;
    private javax.swing.JPanel UserManagementMainPanel;
    private javax.swing.JPanel UserManagementNavBar;
    private javax.swing.JPanel UserManagementPanel;
    private javax.swing.JPanel WelcomeTitle;
    private javax.swing.JTextArea areaPatientDescription;
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnAddItems;
    private javax.swing.JButton btnAddRegisterStaff;
    private javax.swing.JButton btnAddWorkingDayStaff;
    private javax.swing.JButton btnClearWorkingDayStaff;
    private javax.swing.JButton btnDeleteItem;
    private javax.swing.JButton btnDeleteItems;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnDispense;
    private javax.swing.JButton btnDispenseMedicines;
    private javax.swing.JButton btnDispenseMedicinesBack;
    private javax.swing.JButton btnEditProfile;
    private javax.swing.JButton btnEditSafetyQuestion;
    private javax.swing.JButton btnIdentify;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPassword;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JToggleButton btnRegisterAdmin;
    private javax.swing.JToggleButton btnRegisterPatient;
    private javax.swing.JButton btnRegisterStaff;
    private javax.swing.JToggleButton btnReturnAdmin;
    private javax.swing.JButton btnReturnAdmin1;
    private javax.swing.JButton btnReturnChangePassword;
    private javax.swing.JButton btnReturnDeleteItem;
    private javax.swing.JButton btnReturnDeleteUser;
    private javax.swing.JButton btnReturnInventory;
    private javax.swing.JButton btnReturnMedicalReoport;
    private javax.swing.JButton btnReturnPatient;
    private javax.swing.JToggleButton btnReturnRegisterPatient;
    private javax.swing.JButton btnReturnSafetyQuestion;
    private javax.swing.JButton btnReturnStaff;
    private javax.swing.JButton btnReturnStaff1;
    private javax.swing.JButton btnReturnUpdatePassword;
    private javax.swing.JButton btnSafetyQuestion;
    private javax.swing.JButton btnSaveAdmin;
    private javax.swing.JButton btnSaveChangePassword;
    private javax.swing.JButton btnSaveInventory;
    private javax.swing.JButton btnSavePatient;
    private javax.swing.JButton btnSaveProfile;
    private javax.swing.JButton btnSaveRegisterStaff;
    private javax.swing.JButton btnSaveSafetyQuestion;
    private javax.swing.JButton btnSaveStaff;
    private javax.swing.JButton btnSaveUpdatePassword;
    private javax.swing.JButton btnSaveWorkingDayStaff;
    private javax.swing.JButton btnUpdateInventory;
    private javax.swing.JButton btnUpdateRecordAdmin;
    private javax.swing.JButton btnUpdateRecordPatient;
    private javax.swing.JButton btnUpdateRecordStaff;
    private javax.swing.JButton btnUpdateUserPassword;
    private javax.swing.JButton btnViewMedicalReport;
    private javax.swing.JComboBox<String> cboAdminID;
    private javax.swing.JComboBox<String> cboBloodTypeChildren;
    private javax.swing.JComboBox<String> cboBloodTypePatient;
    private javax.swing.JComboBox<String> cboCategory;
    private javax.swing.JComboBox<String> cboDayRegisterStaff;
    private javax.swing.JComboBox<String> cboDayStaff;
    private javax.swing.JComboBox<String> cboGenderRegisterAdmin;
    private javax.swing.JComboBox<String> cboGenderRegisterPatient;
    private javax.swing.JComboBox<String> cboGenderRegisterStaff;
    private javax.swing.JComboBox<String> cboItemID;
    private javax.swing.JComboBox<String> cboMedicineID;
    private javax.swing.JComboBox<String> cboMonths;
    private javax.swing.JComboBox<String> cboPatientID;
    private javax.swing.JComboBox<String> cboPatientRole;
    private javax.swing.JComboBox<String> cboPrescriptionID;
    private javax.swing.JComboBox<String> cboRole;
    private javax.swing.JComboBox<String> cboSafetyQuestion1;
    private javax.swing.JComboBox<String> cboSafetyQuestion2;
    private javax.swing.JComboBox<String> cboSpecialistRegisterStaff;
    private javax.swing.JComboBox<String> cboSpecialistStaff;
    private javax.swing.JComboBox<String> cboStaffID;
    private javax.swing.JComboBox<String> cboUserID;
    private javax.swing.JComboBox<String> cboYears;
    private javax.swing.JPanel changePasswordPanel;
    private javax.swing.JCheckBox chbCondition1;
    private javax.swing.JCheckBox chbCondition2;
    private javax.swing.JCheckBox chbDeleteItem1;
    private javax.swing.JCheckBox chbDeleteItem2;
    private javax.swing.JCheckBox chbDeleteUser1;
    private javax.swing.JCheckBox chbDeleteUser2;
    private javax.swing.JPanel childrenPanel;
    private javax.swing.JPanel dispenseMedicinesPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox24;
    private javax.swing.JComboBox<String> jComboBox25;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
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
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel214;
    private javax.swing.JLabel jLabel215;
    private javax.swing.JLabel jLabel216;
    private javax.swing.JLabel jLabel217;
    private javax.swing.JLabel jLabel218;
    private javax.swing.JLabel jLabel219;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel220;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JLabel jLabel225;
    private javax.swing.JLabel jLabel226;
    private javax.swing.JLabel jLabel227;
    private javax.swing.JLabel jLabel228;
    private javax.swing.JLabel jLabel229;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel230;
    private javax.swing.JLabel jLabel231;
    private javax.swing.JLabel jLabel232;
    private javax.swing.JLabel jLabel233;
    private javax.swing.JLabel jLabel234;
    private javax.swing.JLabel jLabel235;
    private javax.swing.JLabel jLabel236;
    private javax.swing.JLabel jLabel237;
    private javax.swing.JLabel jLabel238;
    private javax.swing.JLabel jLabel239;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel240;
    private javax.swing.JLabel jLabel241;
    private javax.swing.JLabel jLabel242;
    private javax.swing.JLabel jLabel243;
    private javax.swing.JLabel jLabel244;
    private javax.swing.JLabel jLabel245;
    private javax.swing.JLabel jLabel246;
    private javax.swing.JLabel jLabel247;
    private javax.swing.JLabel jLabel248;
    private javax.swing.JLabel jLabel249;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel250;
    private javax.swing.JLabel jLabel251;
    private javax.swing.JLabel jLabel252;
    private javax.swing.JLabel jLabel253;
    private javax.swing.JLabel jLabel254;
    private javax.swing.JLabel jLabel255;
    private javax.swing.JLabel jLabel256;
    private javax.swing.JLabel jLabel257;
    private javax.swing.JLabel jLabel258;
    private javax.swing.JLabel jLabel259;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel260;
    private javax.swing.JLabel jLabel261;
    private javax.swing.JLabel jLabel262;
    private javax.swing.JLabel jLabel263;
    private javax.swing.JLabel jLabel264;
    private javax.swing.JLabel jLabel265;
    private javax.swing.JLabel jLabel266;
    private javax.swing.JLabel jLabel267;
    private javax.swing.JLabel jLabel268;
    private javax.swing.JLabel jLabel269;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel270;
    private javax.swing.JLabel jLabel271;
    private javax.swing.JLabel jLabel272;
    private javax.swing.JLabel jLabel273;
    private javax.swing.JLabel jLabel274;
    private javax.swing.JLabel jLabel275;
    private javax.swing.JLabel jLabel276;
    private javax.swing.JLabel jLabel277;
    private javax.swing.JLabel jLabel278;
    private javax.swing.JLabel jLabel279;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel280;
    private javax.swing.JLabel jLabel281;
    private javax.swing.JLabel jLabel282;
    private javax.swing.JLabel jLabel283;
    private javax.swing.JLabel jLabel284;
    private javax.swing.JLabel jLabel285;
    private javax.swing.JLabel jLabel286;
    private javax.swing.JLabel jLabel287;
    private javax.swing.JLabel jLabel288;
    private javax.swing.JLabel jLabel289;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel290;
    private javax.swing.JLabel jLabel291;
    private javax.swing.JLabel jLabel292;
    private javax.swing.JLabel jLabel293;
    private javax.swing.JLabel jLabel294;
    private javax.swing.JLabel jLabel295;
    private javax.swing.JLabel jLabel296;
    private javax.swing.JLabel jLabel297;
    private javax.swing.JLabel jLabel298;
    private javax.swing.JLabel jLabel299;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel300;
    private javax.swing.JLabel jLabel301;
    private javax.swing.JLabel jLabel302;
    private javax.swing.JLabel jLabel303;
    private javax.swing.JLabel jLabel304;
    private javax.swing.JLabel jLabel305;
    private javax.swing.JLabel jLabel306;
    private javax.swing.JLabel jLabel307;
    private javax.swing.JLabel jLabel308;
    private javax.swing.JLabel jLabel309;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel310;
    private javax.swing.JLabel jLabel311;
    private javax.swing.JLabel jLabel312;
    private javax.swing.JLabel jLabel313;
    private javax.swing.JLabel jLabel314;
    private javax.swing.JLabel jLabel315;
    private javax.swing.JLabel jLabel316;
    private javax.swing.JLabel jLabel317;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JLabel jLabel319;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel320;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    private javax.swing.JLabel jLabel328;
    private javax.swing.JLabel jLabel329;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel330;
    private javax.swing.JLabel jLabel331;
    private javax.swing.JLabel jLabel332;
    private javax.swing.JLabel jLabel333;
    private javax.swing.JLabel jLabel334;
    private javax.swing.JLabel jLabel335;
    private javax.swing.JLabel jLabel336;
    private javax.swing.JLabel jLabel337;
    private javax.swing.JLabel jLabel338;
    private javax.swing.JLabel jLabel339;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel340;
    private javax.swing.JLabel jLabel341;
    private javax.swing.JLabel jLabel342;
    private javax.swing.JLabel jLabel343;
    private javax.swing.JLabel jLabel344;
    private javax.swing.JLabel jLabel345;
    private javax.swing.JLabel jLabel346;
    private javax.swing.JLabel jLabel347;
    private javax.swing.JLabel jLabel348;
    private javax.swing.JLabel jLabel349;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel350;
    private javax.swing.JLabel jLabel351;
    private javax.swing.JLabel jLabel352;
    private javax.swing.JLabel jLabel353;
    private javax.swing.JLabel jLabel354;
    private javax.swing.JLabel jLabel355;
    private javax.swing.JLabel jLabel356;
    private javax.swing.JLabel jLabel357;
    private javax.swing.JLabel jLabel358;
    private javax.swing.JLabel jLabel359;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel360;
    private javax.swing.JLabel jLabel361;
    private javax.swing.JLabel jLabel362;
    private javax.swing.JLabel jLabel363;
    private javax.swing.JLabel jLabel364;
    private javax.swing.JLabel jLabel365;
    private javax.swing.JLabel jLabel366;
    private javax.swing.JLabel jLabel367;
    private javax.swing.JLabel jLabel368;
    private javax.swing.JLabel jLabel369;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel370;
    private javax.swing.JLabel jLabel371;
    private javax.swing.JLabel jLabel372;
    private javax.swing.JLabel jLabel373;
    private javax.swing.JLabel jLabel374;
    private javax.swing.JLabel jLabel375;
    private javax.swing.JLabel jLabel376;
    private javax.swing.JLabel jLabel377;
    private javax.swing.JLabel jLabel378;
    private javax.swing.JLabel jLabel379;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel380;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblAdminIcNumber;
    private javax.swing.JLabel lblAdminName;
    private javax.swing.JLabel lblAdminName2;
    private javax.swing.JLabel lblAdminRole;
    private javax.swing.JLabel lblAdminUserID;
    private javax.swing.JLabel lblChildrenIcNumber;
    private javax.swing.JLabel lblChildrenUserID;
    private javax.swing.JLabel lblCounterInventory;
    private javax.swing.JLabel lblDateAdmin;
    private javax.swing.JLabel lblDatePatient;
    private javax.swing.JLabel lblDateSlip;
    private javax.swing.JLabel lblDefaultPasswordAdmin;
    private javax.swing.JLabel lblDefaultPasswordPatient;
    private javax.swing.JLabel lblDefaultPasswordSlip;
    private javax.swing.JLabel lblDiasease;
    private javax.swing.JLabel lblDispensedBy;
    private javax.swing.JLabel lblDispensedDate;
    private javax.swing.JLabel lblDoctorID;
    private javax.swing.JLabel lblDoctorID2;
    private javax.swing.JLabel lblDoctorName;
    private javax.swing.JLabel lblDoctorName2;
    private javax.swing.JLabel lblICNumberSlip;
    private javax.swing.JLabel lblMedicalReportDate;
    private javax.swing.JLabel lblMedicalReportID;
    private javax.swing.JLabel lblMedicalReportID2;
    private javax.swing.JLabel lblMedicineCurrentStock;
    private javax.swing.JLabel lblMedicineID;
    private javax.swing.JLabel lblMedicineName;
    private javax.swing.JLabel lblMedicinePrice;
    private javax.swing.JLabel lblNameAdmin;
    private javax.swing.JLabel lblNamePatient;
    private javax.swing.JLabel lblNameSlip;
    private javax.swing.JLabel lblOfficeSlip;
    private javax.swing.JLabel lblParentName;
    private javax.swing.JLabel lblParientID;
    private javax.swing.JLabel lblPatientAge;
    private javax.swing.JLabel lblPatientBloodType;
    private javax.swing.JLabel lblPatientGender;
    private javax.swing.JLabel lblPatientID;
    private javax.swing.JLabel lblPatientID2;
    private javax.swing.JLabel lblPatientIcNumber;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblPatientName2;
    private javax.swing.JLabel lblPatientRegion;
    private javax.swing.JLabel lblPatientRole;
    private javax.swing.JLabel lblPatientUserID;
    private javax.swing.JLabel lblPaymentStatus;
    private javax.swing.JLabel lblPaymentStatus2;
    private javax.swing.JLabel lblPrescriptionDate;
    private javax.swing.JLabel lblPrescriptionFee;
    private javax.swing.JLabel lblPrescriptionFee1;
    private javax.swing.JLabel lblPrescriptionID;
    private javax.swing.JLabel lblPrescriptionTips;
    private javax.swing.JLabel lblProgression;
    private javax.swing.JLabel lblRegisterPersonNameAdmin;
    private javax.swing.JLabel lblRegisterPersonNameAdmin10;
    private javax.swing.JLabel lblRegisterPersonNameAdmin11;
    private javax.swing.JLabel lblRegisterPersonNameAdmin2;
    private javax.swing.JLabel lblRegisterPersonNameAdmin3;
    private javax.swing.JLabel lblRegisterPersonNameAdmin8;
    private javax.swing.JLabel lblRegisterPersonNameAdmin9;
    private javax.swing.JLabel lblRegisterPersonNamePatient;
    private javax.swing.JLabel lblRegisterPersonNameSlip;
    private javax.swing.JLabel lblRiskAnalysis;
    private javax.swing.JLabel lblRoleAdmin;
    private javax.swing.JLabel lblRolePatient;
    private javax.swing.JLabel lblRoleSlip;
    private javax.swing.JLabel lblSafetyPercenrage;
    private javax.swing.JLabel lblSlipTitle;
    private javax.swing.JLabel lblSpecialist;
    private javax.swing.JLabel lblSpecialistSlip;
    private javax.swing.JLabel lblStaffIcNumber;
    private javax.swing.JLabel lblStaffRole;
    private javax.swing.JLabel lblStaffUserID;
    private javax.swing.JLabel lblSuccessChangePassword;
    private javax.swing.JLabel lblSuccessDeleteItem;
    private javax.swing.JLabel lblSuccessDeleteUser;
    private javax.swing.JLabel lblSuccessDispenseMedicines;
    private javax.swing.JLabel lblSuccessInventory;
    private javax.swing.JLabel lblSuccessProfile;
    private javax.swing.JLabel lblSuccessRegisterAdmin;
    private javax.swing.JLabel lblSuccessRegisterPatient;
    private javax.swing.JLabel lblSuccessRegisterStaff;
    private javax.swing.JLabel lblSuccessSafetyQuestion;
    private javax.swing.JLabel lblSuccessUpdatePassword;
    private javax.swing.JLabel lblSuccessUpdateRecordAdmin;
    private javax.swing.JLabel lblSuccessUpdateRecordPatient;
    private javax.swing.JLabel lblSuccessUpdateRecordStaff;
    private javax.swing.JLabel lblTipsChangePassword;
    private javax.swing.JLabel lblTipsInventory;
    private javax.swing.JLabel lblTipsProfile;
    private javax.swing.JLabel lblTipsSafetyQuestion;
    private javax.swing.JLabel lblTipsUpdatePassword;
    private javax.swing.JLabel lblTotalCurrentStock;
    private javax.swing.JLabel lblTotalSafetyStock;
    private javax.swing.JLabel lblUserID;
    private javax.swing.JLabel lblUserIDAdmin;
    private javax.swing.JLabel lblUserIDPatient;
    private javax.swing.JLabel lblUserIDSlip;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserRole;
    private javax.swing.JLabel lblWorkingDaySlip;
    private javax.swing.JList<String> listBloodTest;
    private javax.swing.JList<String> lstPrescribeMedication;
    private javax.swing.JList<String> lstPrescribeMedication2;
    private javax.swing.JList<String> lstWorkingDayRegisterStaff;
    private javax.swing.JList<String> lstWorkingDayStaff;
    private javax.swing.JPanel medicalReportSlip;
    private javax.swing.JPanel patientPanel;
    private javax.swing.JPanel personalInformationPanel;
    private javax.swing.JPanel pnlMenuBar;
    private javax.swing.JPanel positionInformationPanel;
    private javax.swing.JPanel prescriptionSlip;
    private javax.swing.JPanel profileMainPanel;
    private javax.swing.JPanel registerAdminPanel;
    private javax.swing.JPanel registerChoosePanel;
    private javax.swing.JPanel registerPatientPanel;
    private javax.swing.JPanel registerSlip;
    private javax.swing.JPanel registerSlip1;
    private javax.swing.JPanel registerStaffPanel;
    private javax.swing.JPanel safetyQuestionPanel;
    private javax.swing.JPanel showSpace;
    private javax.swing.JPanel showSpace6;
    private javax.swing.JPanel showSpace7;
    private javax.swing.JPanel showSpace8;
    private javax.swing.JPanel showspace3;
    private javax.swing.JPanel staffPanel;
    private javax.swing.JTable tblInventory;
    private javax.swing.JTable tblUpdatePassword;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtAccSecurityStatus;
    private javax.swing.JTextField txtAgePatient;
    private javax.swing.JTextField txtAgeRegisterPatient;
    private javax.swing.JTextField txtAgeRegisterStaff;
    private javax.swing.JTextField txtAgeStaff;
    private javax.swing.JTextField txtAnswer1;
    private javax.swing.JTextField txtAnswer2;
    private javax.swing.JTextField txtConfirmNewPassword;
    private javax.swing.JTextField txtContactNumber;
    private javax.swing.JTextField txtContactNumberAdmin;
    private javax.swing.JTextField txtContactNumberPatient;
    private javax.swing.JTextField txtContactNumberRegisterAdmin;
    private javax.swing.JTextField txtContactNumberRegisterPatient;
    private javax.swing.JTextField txtContactNumberRegisterStaff;
    private javax.swing.JTextField txtContactNumberStaff;
    private javax.swing.JPasswordField txtCurrentPassword;
    private javax.swing.JTextField txtCurrentStock;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailAdmin;
    private javax.swing.JTextField txtEmailPatient;
    private javax.swing.JTextField txtEmailRegisterAdmin;
    private javax.swing.JTextField txtEmailRegisterPatient;
    private javax.swing.JTextField txtEmailRegisterStaff;
    private javax.swing.JTextField txtEmailStaff;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtICNumber;
    private javax.swing.JTextField txtICNumberRegisterPatient;
    private javax.swing.JTextField txtICNumberRegisterStaff;
    private javax.swing.JTextField txtIcNumberRegisterAdmin;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNameRegisterAdmin;
    private javax.swing.JTextField txtNameRegisterPatient;
    private javax.swing.JTextField txtNameRegisterStaff;
    private javax.swing.JTextField txtNationality;
    private javax.swing.JTextField txtNationalityAdmin;
    private javax.swing.JTextField txtNationalityRegisterAdmin;
    private javax.swing.JTextField txtNationalityRegisterStaff;
    private javax.swing.JTextField txtNationalityStaff;
    private javax.swing.JTextField txtNewPassword;
    private javax.swing.JTextField txtOfficeRegisterStaff;
    private javax.swing.JTextField txtOfficeStaff;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQualificationStaff;
    private javax.swing.JTextField txtRaceRegisterPatient;
    private javax.swing.JTextField txtRegionAdmin;
    private javax.swing.JTextField txtRegionChildren;
    private javax.swing.JTextField txtRegionPatient;
    private javax.swing.JTextField txtRegionStaff;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtSafetyStock;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtStateRegisterAdmin;
    private javax.swing.JTextField txtStateRegisterPatient;
    private javax.swing.JTextField txtStateRegisterStaff;
    private javax.swing.JTextField txtSuplier;
    private javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUserNameAdmin;
    private javax.swing.JTextField txtUserNameChildren;
    private javax.swing.JTextField txtUserNamePatient;
    private javax.swing.JTextField txtUserNameStaff;
    private javax.swing.JPanel viewMedicalReportPanel;
    private javax.swing.JPanel viewPrescriptionPanel;
    // End of variables declaration//GEN-END:variables
}
