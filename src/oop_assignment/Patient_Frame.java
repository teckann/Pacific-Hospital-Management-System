/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_assignment;
import java.time.LocalDate;
import java.time.YearMonth;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import oop_assignment.Validation;

/**
 *
 * @author Jimmy
 */
public class Patient_Frame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Patient_Frame.class.getName());

    DefaultListModel lm = new DefaultListModel();
    
    Patient currentPatient = new Patient();
    
    
    
    Child generalChild = new Child();
    Child everyChild = new Child();
    Appointment appMaking;
    Appointment appShowing;
    Payment paymentMaking;
    Payment paymentShowing = new Payment();
    
    LocalDate todayDate = LocalDate.now();
    int todayDay = todayDate.getDayOfMonth();
    String todayMonth = todayDate.getMonth().toString();
    int todayYear = todayDate.getYear();
    String todayDateString = todayDate.toString();
    private boolean choosingYear = false;
    private boolean choosingMonth = false;
    private boolean choosingDay = false;
    private boolean choosingApp = false;
    private boolean isFilteringApp = true;
    private String selectedChildid;
    boolean gotData = false;
    boolean gotVaccine = false;
    
    
    /**
     * Creates new form Patient_Frame
     */
    public Patient_Frame() {
        initComponents();
        this.setLocationRelativeTo(null);
        System.out.println("HI , "+ Main.getCurrentUserID());
        PnlSettings.setVisible(false);

        hiding();
        
        currentPatient.setUserID(Main.getCurrentUserID());
        currentPatient.readAllData();
        currentPatient.assignUnuseData();

        lblUserPhoto.setText(Main.getCurrentUserName());
        lstPaymentToMake.setModel(lm);
        
        txtAreaMedRepTesting.setEnabled(false);
        txtAreaMedRepDescrip.setEnabled(false);
        txtAreaPreMedMedical.setEnabled(false);
        txtAreaVaccDesc.setEnabled(false);
        
        for(String[] medicalList : File_Control.readFile("medicalReport.txt",true)){  
            if(medicalList[1].equals(Main.getCurrentUserID())){
                gotData = true;
                break;
            }
        }
        
        if(!gotData){
            btnMedRecord.setEnabled(false);
            btnPreMed.setEnabled(false);
            btnPayment.setEnabled(false);
        }
        
        for(String[] VaccineList : File_Control.readFile("vaccinationData.txt",true)){  
            if(VaccineList[1].equals(Main.getCurrentUserID())){
                gotVaccine = true;
                break;
            }
        }
        
        for(String child: generalChild.getAllChildID(Main.getCurrentUserID())){
                for(String[] VaccineList : File_Control.readFile("vaccinationData.txt",true)){  
                    if(VaccineList[1].equals(child)){
                        gotVaccine = true;
                        break;
                    }
                }
            }
        
        if(!gotVaccine){
            btnVaccination.setEnabled(false);
            
        }
    }
    
    public void hiding(){
        PnlAppFirst.setVisible(false);
        
        PnlAppointment.setVisible(false);
        lblAppDayWarning.setVisible(false);
        
        PnlMedRecord.setVisible(false);
        PnlPreMed.setVisible(false);
        
        
        PnlPayment.setVisible(false);
        lblPaymentWarninglst.setVisible(false);
        lblPaymentWarningcbo.setVisible(false);
        
        PnlPaymentMake.setVisible(false);
        PnlPaymentReceipt.setVisible(false);
        
        PnlChild.setVisible(false);
        PnlChildReminder.setVisible(false);
        PnlChildDetail.setVisible(false);
        btnChildDetailSave.setVisible(false);
        
        PnlChildPreMed.setVisible(false);
        PnlChildMedRecord.setVisible(false);
        
        PnlRegisterChild.setVisible(false);
        lblChildRegWarning.setVisible(false);
        
        PnlDetailBar.setVisible(false);
        btnAppointment.setBorderPainted(true);
        btnMedRecord.setBorderPainted(true);
        btnPreMed.setBorderPainted(true);
        btnPayment.setBorderPainted(true);
        btnChild.setBorderPainted(true);
        btnVaccination.setBorderPainted(true);
        
        PnlProfile.setVisible(false);
        btnProfNameSave.setVisible(false);

        btnProfConSave.setVisible(false);
        btnProfEmailSave.setVisible(false);

        btnProfBloodEdit.setVisible(false);
        btnProfBloodSave.setVisible(false);
        
        btnProfICEdit.setVisible(false);
        btnProfICSave.setVisible(false);
        
        btnProfGenderEdit.setVisible(false);
        btnProfGenderSave.setVisible(false);
        
        btnProfRaceEdit.setVisible(false);
        btnProfRaceSave.setVisible(false);
        
        btnProfRegionSave.setVisible(false);
        
        PnlEditPassword.setVisible(false);
        lblWrongPass.setVisible(false);
        lblValidationPass1.setVisible(false);
        lblValidationPass2.setVisible(false);
        
        PnlEditSafetyQ.setVisible(false);
        cboSafetyQ1.setVisible(false);
        cboSafetyQ2.setVisible(false);
        btnSafetyQCancel.setVisible(false);
        btnSafetyQSave.setVisible(false);
        
        btnCancelEdit.setVisible(false);
        
        PnlVaccination.setVisible(false);
        
        PnlWarningProfile.setVisible(false);
    }
    
    public void enabling(boolean enable){
        btnProfile.setEnabled(enable);
        
        btnAppointment.setEnabled(enable);

        btnMakeApp.setEnabled(enable);
        
        cboAppSpecialist.setEnabled(enable);
        cboAppDoctor.setEnabled(enable);
        cboAppYear.setEnabled(enable);
        cboAppMonth.setEnabled(enable);
        cboAppDay.setEnabled(enable);
        cboAppTime.setEnabled(enable);
        rbnAppself.setEnabled(enable);
        rbnAppChild.setEnabled(enable);
        cboAppChild.setEnabled(enable);
        btnRequestApp.setEnabled(enable);
        chkAppFilter.setEnabled(enable);
        
        cboAppShowFilter.setEnabled(enable);
        cboAppShowChoose.setEnabled(enable);
        btnAppDelete.setEnabled(enable);
        
        btnMedRecord.setEnabled(enable);
        cboMedRepChoose.setEnabled(enable);
        
        btnPreMed.setEnabled(enable);
        cboMedRepChoose.setEnabled(enable);
        
        btnPayment.setEnabled(enable);
        btnPaymentTotalUp.setEnabled(enable);
        cboPaymentAction.setEnabled(enable);
        cboPaymentChoose.setEnabled(enable);
        cboPaymentMethod.setEnabled(enable);
        btnPaymentMade.setEnabled(enable);
        lstPaymentToMake.setEnabled(enable);
                
        btnChild.setEnabled(enable);
        cboChildList.setEnabled(enable);
        cboChildToDo.setEnabled(enable);
        btnChildDetailEdit.setEnabled(enable);
        btnChildDetailSave.setEnabled(enable);
        
        btnRegisterChild.setEnabled(enable);
        
        btnVaccination.setEnabled(enable);
        
        btnProfPass.setEnabled(enable);
        btnProfSafe.setEnabled(enable);
        
        btnProfNameEdit.setEnabled(enable);
        btnProfConEdit.setEnabled(enable);
        btnProfEmailEdit.setEnabled(enable);
        btnProfBloodEdit.setEnabled(enable);
        
        btnProfNameSave.setEnabled(enable);
        btnProfConSave.setEnabled(enable);
        btnProfEmailSave.setEnabled(enable);
        btnProfBloodSave.setEnabled(enable);
        
        btnProfICEdit.setEnabled(enable);
        btnProfICSave.setEnabled(enable);
        
        btnProfGenderEdit.setEnabled(enable);
        btnProfGenderSave.setEnabled(enable);
        
        btnProfRaceEdit.setEnabled(enable);
        btnProfRaceSave.setEnabled(enable);
        
        btnProfRegionEdit.setEnabled(enable);
        btnProfRegionSave.setEnabled(enable);
        
    }
    
    public void enablingAppointment(boolean enable){
        cboAppDoctor.setEnabled(enable);
        cboAppYear.setEnabled(enable);
        cboAppMonth.setEnabled(enable);
        cboAppDay.setEnabled(enable);
        cboAppTime.setEnabled(enable);
        rbnAppself.setEnabled(enable);
        rbnAppChild.setEnabled(enable);
        cboAppChild.setEnabled(enable);
        btnRequestApp.setEnabled(enable);
        chkAppFilter.setEnabled(enable);
    }
    
    public void enablingChild(boolean enable){
        txtChildShowID.setEnabled(enable);
        txtChildShowName.setEnabled(enable);
        txtChildShowGender.setEnabled(enable);
        txtChildShowIC.setEnabled(enable);
        txtChildShowBlood.setEnabled(enable);
        txtChildShowRegion.setEnabled(enable);
        txtChildShowAge.setEnabled(enable);
        
        cboChildToEdit.setVisible(enable);
        
        cboChildToDo.setEnabled(enable);
        
    }
    
    // to REMOVE all the ITEM in COMBO BOX EXCEPT the FIRST
    public void removeCbo(javax.swing.JComboBox cboToRemove){
        for(int i = cboToRemove.getItemCount()-1; i > 0;i--){
            cboToRemove.removeItemAt(i);
        }
    }
    
    // to SET the TEXT FIELD and SAVE BUTTON ENABLED
    public void profileEdit(javax.swing.JButton btnSave, javax.swing.JButton btnEdit, javax.swing.JTextField txtProfile){
        enabling(false);
        btnSave.setEnabled(true);
        btnSave.setVisible(true);
        btnEdit.setVisible(false);
        btnCancelEdit.setVisible(true);
        btnCancelEdit.setEnabled(true);
        txtProfile.setEnabled(true);
        
    }
    
    // to SET the TEXT FIELD and SAVE BUTTON DISABLED and MODIFY DATA
    public void profileSave(javax.swing.JButton btnSave, javax.swing.JButton btnEdit,
                            javax.swing.JTextField txtProfile, int index){
        
        btnSave.setEnabled(false);
        btnSave.setVisible(false);
        btnEdit.setVisible(true);
        btnCancelEdit.setVisible(false);
        btnCancelEdit.setEnabled(false);
        txtProfile.setEnabled(false);
        Patient.modifyOneValue("patientData.txt", Main.getCurrentUserID(), txtProfile.getText().strip(), index);
        PnlWarningProfile.setVisible(false);
        enabling(true);
    }
    
    public void setWarningText(String warning1,String warning2){
        PnlWarningProfile.setVisible(true);
        lblProfWarning1.setText(warning1);
        lblProfWarning2.setText(warning2);
        btnCancelEdit.setEnabled(false);
    }
    
    public void enablingProfile(boolean enable){
        txtProfID.setEnabled(enable);
        txtProfName.setEnabled(enable);
        txtProfIC.setEnabled(enable);
        txtProfAge.setEnabled(enable);
        txtProfGender.setEnabled(enable);
        txtProfRegion.setEnabled(enable);
        txtProfContact.setEnabled(enable);
        txtProfEmail.setEnabled(enable);
        txtProfRegion.setEnabled(enable);
        txtProfRace.setEnabled(enable);
        txtProfBlood.setEnabled(enable);
    }
    
    public void enablingAllProfile(boolean enable){
        enablingProfile(false);
        
        btnProfNameEdit.setEnabled(enable);
        btnProfNameSave.setEnabled(enable);

        btnProfConSave.setEnabled(enable);
        btnProfEmailSave.setEnabled(enable);

        btnProfConEdit.setEnabled(enable);
        btnProfEmailEdit.setEnabled(enable);

        btnProfBloodEdit.setEnabled(enable);
        btnProfBloodSave.setEnabled(enable);
        
        btnProfICEdit.setEnabled(enable);
        btnProfICSave.setEnabled(enable);
        
        btnProfGenderEdit.setEnabled(enable);
        btnProfGenderSave.setEnabled(enable);
        
        btnProfRaceEdit.setEnabled(enable);
        btnProfRaceSave.setEnabled(enable);
        
        btnProfRegionEdit.setEnabled(enable);
        btnProfRegionSave.setEnabled(enable);
        
        btnCancelEdit.setEnabled(enable);
        
        btnProfPass.setEnabled(enable);
        btnProfSafe.setEnabled(enable);
        
    }
    
    public void hidingProfileIndividual(javax.swing.JButton btnSave,javax.swing.JButton btnEdit){
        btnSave.setVisible(false);
        btnEdit.setVisible(true);
    }
    public void hidingProfileIndividual(javax.swing.JButton btnSave,javax.swing.JButton btnEdit,boolean isShow){
        btnSave.setVisible(false);
        btnEdit.setVisible(isShow);
    }
    
    public void enablingAllProfileOposite(boolean enable){
        enablingProfile(false);
        PnlProfile.setVisible(true);
        btnProfile.setVisible(true);
        btnProfile.setEnabled(true);
        
        btnProfNameEdit.setEnabled(!enable);
        btnProfNameSave.setEnabled(enable);

        btnProfConEdit.setEnabled(!enable);
        btnProfConSave.setEnabled(enable);

        btnProfEmailEdit.setEnabled(!enable);
        btnProfEmailSave.setEnabled(enable);

        btnProfBloodEdit.setEnabled(!enable);
        btnProfBloodSave.setEnabled(enable);
        
        btnProfICEdit.setEnabled(!enable);
        btnProfICSave.setEnabled(enable);
        
        btnProfGenderEdit.setEnabled(!enable);
        btnProfGenderSave.setEnabled(enable);
        
        btnProfRaceEdit.setEnabled(!enable);
        btnProfRaceSave.setEnabled(enable);
        
        btnProfRegionEdit.setEnabled(!enable);
        btnProfRegionSave.setEnabled(enable);
        
        hidingProfileIndividual(btnProfNameSave,btnProfNameEdit);
        hidingProfileIndividual(btnProfConSave,btnProfConEdit);
        hidingProfileIndividual(btnProfEmailSave,btnProfEmailEdit);
        hidingProfileIndividual(btnProfRegionSave,btnProfRegionEdit);
        
        
        
        
        if(currentPatient.getBloodType().equals("-")){
            hidingProfileIndividual(btnProfBloodSave,btnProfBloodEdit);
        }else{
            hidingProfileIndividual(btnProfBloodSave,btnProfBloodEdit,false);
        }
        
        if(currentPatient.getIcNumber().equals("-")){
            hidingProfileIndividual(btnProfICSave,btnProfICEdit);
        }else{
            hidingProfileIndividual(btnProfICSave,btnProfICEdit,false);
        }
        
        if(currentPatient.getGender().equals("-")){
            hidingProfileIndividual(btnProfGenderSave,btnProfGenderEdit);
        }else{
            hidingProfileIndividual(btnProfGenderSave,btnProfGenderEdit,false);
        }
        
        if(currentPatient.getRace().equals("-")){
            hidingProfileIndividual(btnProfRaceSave,btnProfRaceEdit);
        }else{
            hidingProfileIndividual(btnProfRaceSave,btnProfRaceEdit,false);
        }
        
        
        btnCancelEdit.setEnabled(enable);
        
        
        
    }
    
    public void setupSafetyQ(){
        txtSafetyQ1.setEnabled(false);
        txtSafetyQ2.setEnabled(false);
        lblSafetyQ1.setText(currentPatient.questionInString(currentPatient.getSafetyQuestionID1()));
        txtSafetyQ1.setText(currentPatient.getSafetyAnswer1());
        
        lblSafetyQ2.setText(currentPatient.questionInString(currentPatient.getSafetyQuestionID2()));
        txtSafetyQ2.setText(currentPatient.getSafetyAnswer2());
        
        cboSafetyQ1.setVisible(false);
        cboSafetyQ2.setVisible(false);
        
        btnSafetyQEdit1.setVisible(true);

        lblSafetyQ1.setVisible(true);
        lblSafetyQ2.setVisible(true);
        
        btnSafetyQCancel.setVisible(false);
        btnSafetyQSave.setVisible(false);
        
        btnSafetyQEdit2.setVisible(true);
        btnSafetyQEdit2.setEnabled(true);
        btnSafetyQEdit1.setEnabled(true);
        btnSafetyQBack.setVisible(true);
    }

 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ChildRelation = new javax.swing.ButtonGroup();
        jLabel30 = new javax.swing.JLabel();
        AppointmentIndividual = new javax.swing.ButtonGroup();
        PnlSettings = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        btnCancelDelete = new javax.swing.JButton();
        btnDeleteAcc = new javax.swing.JButton();
        PnlWarningProfile = new javax.swing.JPanel();
        lblProfWarning1 = new javax.swing.JLabel();
        btnProfileUnderstand = new javax.swing.JButton();
        lblProfWarning2 = new javax.swing.JLabel();
        PnlDetailBar = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();
        btnSideProf = new javax.swing.JButton();
        btnCloseSideBar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lblUserPhoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        PnlBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnProfile = new javax.swing.JButton();
        PnlBasement = new javax.swing.JPanel();
        PnlRegisterChild = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        rbnParent = new javax.swing.JRadioButton();
        rbnGuardian = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtChildName = new javax.swing.JTextField();
        txtChildIC = new javax.swing.JTextField();
        cboChildRegGender = new javax.swing.JComboBox<>();
        lblChildInvalidIC = new javax.swing.JLabel();
        cboChildRegYear = new javax.swing.JComboBox<>();
        lblChildRegWarning = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtChildRegReg = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtChildRegBlood = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        btnCancelRegChild = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        PnlPayment = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboPaymentChoose = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboPaymentAction = new javax.swing.JComboBox<>();
        PnlPaymentMake = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstPaymentToMake = new javax.swing.JList<>();
        jLabel98 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        btnPaymentTotalUp = new javax.swing.JButton();
        lblPaymentShowTotal = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        cboPaymentMethod = new javax.swing.JComboBox<>();
        btnPaymentMade = new javax.swing.JButton();
        lblPaymentWarningcbo = new javax.swing.JLabel();
        lblPaymentWarninglst = new javax.swing.JLabel();
        PnlPaymentReceipt = new javax.swing.JPanel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        lblPaymentShowID = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        lblPaymentShowMPID = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        lblPaymentShowDate = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        lblPaymentShowAmount = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        lblPaymentShowMethod = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        PnlPaymentReminder = new javax.swing.JPanel();
        jLabel175 = new javax.swing.JLabel();
        PnlPaymentSuccesful = new javax.swing.JPanel();
        PnlMedRecord = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PnlMedRecShow = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        lblMedRepID = new javax.swing.JLabel();
        lblMedRepDID = new javax.swing.JLabel();
        lblMedRepDate = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        lblMedRepPID = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        lblMedRepDiag = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaMedRepTesting = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaMedRepDescrip = new javax.swing.JTextArea();
        cboMedRepChoose = new javax.swing.JComboBox<>();
        PnlMedRecReminder = new javax.swing.JPanel();
        jLabel178 = new javax.swing.JLabel();
        PnlChild = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cboChildList = new javax.swing.JComboBox<>();
        btnRegisterChild = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        PnlChildDetail = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtChildShowID = new javax.swing.JTextField();
        txtChildShowGender = new javax.swing.JTextField();
        txtChildShowBlood = new javax.swing.JTextField();
        txtChildShowAge = new javax.swing.JTextField();
        txtChildShowName = new javax.swing.JTextField();
        txtChildShowIC = new javax.swing.JTextField();
        txtChildShowRegion = new javax.swing.JTextField();
        btnChildDetailEdit = new javax.swing.JButton();
        btnChildDetailSave = new javax.swing.JButton();
        cboChildToEdit = new javax.swing.JComboBox<>();
        cboChildToDo = new javax.swing.JComboBox<>();
        PnlChildPreMed = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        lblPreMedID1 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        lblPreMedMID1 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        lblPreMedDate3 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        lblPreMedDate4 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAreaPreMedMedical1 = new javax.swing.JTextArea();
        cboPreMedChoose1 = new javax.swing.JComboBox<>();
        PnlChildMedRecord = new javax.swing.JPanel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        lblMedRepID1 = new javax.swing.JLabel();
        lblMedRepDID1 = new javax.swing.JLabel();
        lblMedRepPID1 = new javax.swing.JLabel();
        lblMedRepDate1 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        lblMedRepDiag1 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtAreaMedRepTesting1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAreaMedRepDescrip1 = new javax.swing.JTextArea();
        cboMedRepChoose1 = new javax.swing.JComboBox<>();
        PnlChildReminder = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        PnlAppointment = new javax.swing.JPanel();
        cboAppSpecialist = new javax.swing.JComboBox<>();
        cboAppDoctor = new javax.swing.JComboBox<>();
        lblAppDate = new javax.swing.JLabel();
        lblAppTime = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rbnAppself = new javax.swing.JRadioButton();
        btnRequestApp = new javax.swing.JButton();
        cboAppMonth = new javax.swing.JComboBox<>();
        cboAppTime = new javax.swing.JComboBox<>();
        cboAppDay = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        rbnAppChild = new javax.swing.JRadioButton();
        lblAppBookfor = new javax.swing.JLabel();
        lblAppChild = new javax.swing.JLabel();
        cboAppChild = new javax.swing.JComboBox<>();
        btnCancelApp = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        cboAppYear = new javax.swing.JComboBox<>();
        lblAppDayWarning = new javax.swing.JLabel();
        PnlAppFirst = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnMakeApp = new javax.swing.JButton();
        chkAppFilter = new javax.swing.JCheckBox();
        cboAppShowFilter = new javax.swing.JComboBox<>();
        PnlShowingApp = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblAppShowStatus = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        lblAppShowID = new javax.swing.JLabel();
        lblAppShowDID = new javax.swing.JLabel();
        lblAppShowPID = new javax.swing.JLabel();
        lblAppShowDate = new javax.swing.JLabel();
        lblAppShowTime = new javax.swing.JLabel();
        lblAppShowDName = new javax.swing.JLabel();
        lblAppShowPName = new javax.swing.JLabel();
        btnAppDelete = new javax.swing.JButton();
        cboAppShowChoose = new javax.swing.JComboBox<>();
        PnlAppReminder = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        PnlPreMed = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        PnlPreMedShowing = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        lblPreMedID = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        lblPreMedMID = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        lblPreMedDate1 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        lblPreMedDate2 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaPreMedMedical = new javax.swing.JTextArea();
        cboPreMedChoose = new javax.swing.JComboBox<>();
        PnlPreMedReminder = new javax.swing.JPanel();
        jLabel181 = new javax.swing.JLabel();
        PnlVaccination = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        PnlVaccShow = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        lblVaccID = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        lblVaccType = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        lblVaccDate = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        lblVaccPID = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtAreaVaccDesc = new javax.swing.JTextArea();
        cboVaccChoose = new javax.swing.JComboBox<>();
        jLabel145 = new javax.swing.JLabel();
        cboVaccChoose1 = new javax.swing.JComboBox<>();
        lblVaccineShowChild = new javax.swing.JLabel();
        PnlVaccineReminder = new javax.swing.JPanel();
        jLabel182 = new javax.swing.JLabel();
        btnVaccination = new javax.swing.JButton();
        btnAppointment = new javax.swing.JButton();
        btnMedRecord = new javax.swing.JButton();
        btnPreMed = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnChild = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();
        PnlEditPassword = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        txtPasswordOld = new javax.swing.JPasswordField();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        txtPasswordNew = new javax.swing.JPasswordField();
        btnConfirmPassword = new javax.swing.JButton();
        lblValidationPass1 = new javax.swing.JLabel();
        lblValidationPass2 = new javax.swing.JLabel();
        lblWrongPass = new javax.swing.JLabel();
        btnCancelPassword = new javax.swing.JButton();
        PnlEditSafetyQ = new javax.swing.JPanel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        lblSafetyQ1 = new javax.swing.JLabel();
        txtSafetyQ1 = new javax.swing.JTextField();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        lblSafetyQ2 = new javax.swing.JLabel();
        txtSafetyQ2 = new javax.swing.JTextField();
        btnSafetyQCancel = new javax.swing.JButton();
        btnSafetyQSave = new javax.swing.JButton();
        btnSafetyQEdit1 = new javax.swing.JButton();
        btnSafetyQEdit2 = new javax.swing.JButton();
        cboSafetyQ1 = new javax.swing.JComboBox<>();
        cboSafetyQ2 = new javax.swing.JComboBox<>();
        btnSafetyQBack = new javax.swing.JButton();
        PnlProfile = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txtProfID = new javax.swing.JTextField();
        txtProfName = new javax.swing.JTextField();
        txtProfIC = new javax.swing.JTextField();
        btnProfNameEdit = new javax.swing.JButton();
        btnProfNameSave = new javax.swing.JButton();
        txtProfContact = new javax.swing.JTextField();
        txtProfEmail = new javax.swing.JTextField();
        txtProfAge = new javax.swing.JTextField();
        txtProfGender = new javax.swing.JTextField();
        txtProfRace = new javax.swing.JTextField();
        txtProfRegion = new javax.swing.JTextField();
        txtProfBlood = new javax.swing.JTextField();
        btnProfPass = new javax.swing.JButton();
        btnProfSafe = new javax.swing.JButton();
        btnProfConEdit = new javax.swing.JButton();
        btnProfConSave = new javax.swing.JButton();
        btnProfEmailEdit = new javax.swing.JButton();
        btnProfEmailSave = new javax.swing.JButton();
        btnProfBloodEdit = new javax.swing.JButton();
        btnProfBloodSave = new javax.swing.JButton();
        btnCancelEdit = new javax.swing.JButton();
        btnProfRegionEdit = new javax.swing.JButton();
        btnProfRegionSave = new javax.swing.JButton();
        btnProfRaceEdit = new javax.swing.JButton();
        btnProfRaceSave = new javax.swing.JButton();
        btnProfGenderEdit = new javax.swing.JButton();
        btnProfGenderSave = new javax.swing.JButton();
        btnProfICEdit = new javax.swing.JButton();
        btnProfICSave = new javax.swing.JButton();

        jLabel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 490));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnlSettings.setBackground(new java.awt.Color(235, 245, 251));
        PnlSettings.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PnlSettings.setPreferredSize(new java.awt.Dimension(800, 400));
        PnlSettings.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel92.setText("Sure to Delete Account?");
        PnlSettings.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        btnCancelDelete.setBackground(new java.awt.Color(255, 204, 204));
        btnCancelDelete.setText("Cancel");
        btnCancelDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelDeleteActionPerformed(evt);
            }
        });
        PnlSettings.add(btnCancelDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        btnDeleteAcc.setBackground(new java.awt.Color(204, 255, 204));
        btnDeleteAcc.setText("Delete");
        btnDeleteAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAccActionPerformed(evt);
            }
        });
        PnlSettings.add(btnDeleteAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        getContentPane().add(PnlSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 300, 150));

        PnlWarningProfile.setBackground(new java.awt.Color(235, 245, 251));
        PnlWarningProfile.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PnlWarningProfile.setPreferredSize(new java.awt.Dimension(800, 400));
        PnlWarningProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfWarning1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblProfWarning1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfWarning1.setText("INVALID REMINDER");
        PnlWarningProfile.add(lblProfWarning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 280, -1));

        btnProfileUnderstand.setBackground(new java.awt.Color(204, 255, 204));
        btnProfileUnderstand.setText("OK");
        btnProfileUnderstand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileUnderstandActionPerformed(evt);
            }
        });
        PnlWarningProfile.add(btnProfileUnderstand, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        lblProfWarning2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfWarning2.setText("jLabel184");
        PnlWarningProfile.add(lblProfWarning2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 240, -1));

        getContentPane().add(PnlWarningProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 300, 150));

        PnlDetailBar.setBackground(new java.awt.Color(235, 250, 253));
        PnlDetailBar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "-", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        PnlDetailBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(0, 85, 251));
        btnDashboard.setText("DashBoard");
        btnDashboard.setBorder(null);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        PnlDetailBar.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 103, 140, 40));

        btnSettings.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSettings.setForeground(new java.awt.Color(255, 0, 51));
        btnSettings.setText("Delete Account");
        btnSettings.setBorder(null);
        btnSettings.setBorderPainted(false);
        btnSettings.setContentAreaFilled(false);
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });
        PnlDetailBar.add(btnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 140, 40));

        btnSideProf.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSideProf.setForeground(new java.awt.Color(0, 85, 255));
        btnSideProf.setText("Profile");
        btnSideProf.setBorder(null);
        btnSideProf.setBorderPainted(false);
        btnSideProf.setContentAreaFilled(false);
        btnSideProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSideProfActionPerformed(evt);
            }
        });
        PnlDetailBar.add(btnSideProf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 146, 140, 40));

        btnCloseSideBar.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnCloseSideBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/closeJimmy.png"))); // NOI18N
        btnCloseSideBar.setBorderPainted(false);
        btnCloseSideBar.setContentAreaFilled(false);
        btnCloseSideBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseSideBarActionPerformed(evt);
            }
        });
        PnlDetailBar.add(btnCloseSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 24, 45, 45));

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 85, 255));
        jButton6.setText("Information");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        PnlDetailBar.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 189, 140, 40));

        lblUserPhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profileJimmy-1.png"))); // NOI18N
        lblUserPhoto.setText("Ha Ha");
        PnlDetailBar.add(lblUserPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 160, 50));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jButton1.setText("    Log out");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PnlDetailBar.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 210, 50));
        PnlDetailBar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 145, 240, 20));
        PnlDetailBar.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 235, 240, 20));
        PnlDetailBar.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 100, 240, 20));
        PnlDetailBar.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 190, 240, 20));

        getContentPane().add(PnlDetailBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 250, 490));

        PnlBar.setBackground(new java.awt.Color(213, 245, 251));
        PnlBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        PnlBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        PnlBar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnProfile.setBackground(new java.awt.Color(235, 245, 251));
        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/moreJimmy.png"))); // NOI18N
        btnProfile.setBorder(null);
        btnProfile.setBorderPainted(false);
        btnProfile.setContentAreaFilled(false);
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });
        PnlBar.add(btnProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        getContentPane().add(PnlBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 90));

        PnlBasement.setBackground(new java.awt.Color(235, 245, 251));
        PnlBasement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        PnlBasement.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnlRegisterChild.setBackground(new java.awt.Color(178, 235, 242));
        PnlRegisterChild.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register Child", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        PnlRegisterChild.setPreferredSize(new java.awt.Dimension(500, 350));
        PnlRegisterChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Your Are ?");
        PnlRegisterChild.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 62, 75, -1));

        ChildRelation.add(rbnParent);
        rbnParent.setText("Parent");
        rbnParent.setContentAreaFilled(false);
        rbnParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnParentActionPerformed(evt);
            }
        });
        PnlRegisterChild.add(rbnParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 94, 98, -1));

        ChildRelation.add(rbnGuardian);
        rbnGuardian.setText("Guardian");
        rbnGuardian.setContentAreaFilled(false);
        PnlRegisterChild.add(rbnGuardian, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 127, 98, -1));

        jPanel2.setBackground(new java.awt.Color(224, 247, 250));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("Child's Details");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel19.setText("Name");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 46, 110, -1));

        jLabel20.setText(":");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 46, 11, -1));

        jLabel21.setText("IC Number");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, 110, -1));

        jLabel22.setText("Gender");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 129, 110, -1));

        jLabel23.setText(":");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 80, 11, -1));

        jLabel24.setText(":");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 129, 11, -1));

        jLabel25.setText("Age");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 165, 110, -1));

        jLabel26.setText(":");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 165, 11, -1));

        txtChildName.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.add(txtChildName, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 43, 171, -1));

        txtChildIC.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.add(txtChildIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 77, 171, -1));

        cboChildRegGender.setBackground(new java.awt.Color(204, 255, 255));
        cboChildRegGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gender", "Male", "Female" }));
        cboChildRegGender.setBorder(null);
        jPanel2.add(cboChildRegGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 129, -1, -1));

        lblChildInvalidIC.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblChildInvalidIC.setForeground(new java.awt.Color(255, 0, 0));
        lblChildInvalidIC.setText("Invalid IC ");
        jPanel2.add(lblChildInvalidIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 105, 127, -1));

        cboChildRegYear.setBackground(new java.awt.Color(204, 255, 255));
        cboChildRegYear.setMaximumRowCount(20);
        cboChildRegYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YY", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" }));
        cboChildRegYear.setBorder(null);
        jPanel2.add(cboChildRegYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 165, 60, -1));
        jPanel2.add(lblChildRegWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 270, 300, 20));

        jLabel27.setText("Region");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 70, -1));

        jLabel28.setText(":");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 200, 11, -1));

        txtChildRegReg.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.add(txtChildRegReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 197, 171, -1));

        jLabel34.setText("Blood Type");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 230, 80, -1));

        txtChildRegBlood.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.add(txtChildRegBlood, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 227, 171, -1));

        jLabel48.setText(":");
        jPanel2.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 230, 11, -1));

        PnlRegisterChild.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 350, 300));

        btnCancelRegChild.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelRegChild.setText("Cancel");
        btnCancelRegChild.setBorderPainted(false);
        btnCancelRegChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelRegChildActionPerformed(evt);
            }
        });
        PnlRegisterChild.add(btnCancelRegChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 90, 23));

        jButton2.setBackground(new java.awt.Color(167, 255, 235));
        jButton2.setText("Register");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PnlRegisterChild.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 90, 23));

        PnlBasement.add(PnlRegisterChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        PnlPayment.setBackground(new java.awt.Color(235, 245, 251));
        PnlPayment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Payment");
        PnlPayment.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 200, -1));

        cboPaymentChoose.setBackground(new java.awt.Color(204, 255, 255));
        cboPaymentChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose an receipt to View" }));
        cboPaymentChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPaymentChooseActionPerformed(evt);
            }
        });
        PnlPayment.add(cboPaymentChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Receipt to View");
        PnlPayment.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 114, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("View your payment");
        PnlPayment.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 160, -1));

        cboPaymentAction.setBackground(new java.awt.Color(204, 255, 255));
        cboPaymentAction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "To do", "Make Payment", "View Receipt" }));
        cboPaymentAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPaymentActionActionPerformed(evt);
            }
        });
        PnlPayment.add(cboPaymentAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 90, 130, -1));

        PnlPaymentMake.setBackground(new java.awt.Color(224, 247, 250));
        PnlPaymentMake.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Make Payment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        PnlPaymentMake.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lstPaymentToMake.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lstPaymentToMake.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "MR001: RM123.00" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(lstPaymentToMake);

        PnlPaymentMake.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 200, 160));

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel98.setText("Choose bill to pay");
        PnlPaymentMake.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, -1));

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel108.setText("Total :");
        PnlPaymentMake.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        btnPaymentTotalUp.setBackground(new java.awt.Color(204, 255, 204));
        btnPaymentTotalUp.setText("Total up");
        btnPaymentTotalUp.setBorderPainted(false);
        btnPaymentTotalUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentTotalUpActionPerformed(evt);
            }
        });
        PnlPaymentMake.add(btnPaymentTotalUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        lblPaymentShowTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPaymentShowTotal.setText(" ");
        PnlPaymentMake.add(lblPaymentShowTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel114.setText("Payment method :");
        PnlPaymentMake.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        cboPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose a payment method", "Online banking", "Credit Card" }));
        PnlPaymentMake.add(cboPaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        btnPaymentMade.setBackground(new java.awt.Color(204, 255, 204));
        btnPaymentMade.setText("Make Payment");
        btnPaymentMade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentMadeActionPerformed(evt);
            }
        });
        PnlPaymentMake.add(btnPaymentMade, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, -1, -1));

        lblPaymentWarningcbo.setForeground(new java.awt.Color(255, 51, 51));
        lblPaymentWarningcbo.setText("Please select a method");
        PnlPaymentMake.add(lblPaymentWarningcbo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        lblPaymentWarninglst.setForeground(new java.awt.Color(255, 0, 0));
        lblPaymentWarninglst.setText("No bill selected");
        PnlPaymentMake.add(lblPaymentWarninglst, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        PnlPayment.add(PnlPaymentMake, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 130, 640, 250));

        PnlPaymentReceipt.setBackground(new java.awt.Color(224, 247, 250));
        PnlPaymentReceipt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Receipt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        PnlPaymentReceipt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel150.setText("Receipt ID");
        PnlPaymentReceipt.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, -1));

        jLabel151.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel151.setText(":");
        PnlPaymentReceipt.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        lblPaymentShowID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPaymentShowID.setText("PR001");
        PnlPaymentReceipt.add(lblPaymentShowID, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 130, -1));

        jLabel152.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel152.setText("Medical / Prescription ID");
        PnlPaymentReceipt.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 180, -1));

        jLabel153.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel153.setText(":");
        PnlPaymentReceipt.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        lblPaymentShowMPID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPaymentShowMPID.setText("PR001");
        PnlPaymentReceipt.add(lblPaymentShowMPID, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 130, -1));

        jLabel154.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel154.setText("Date");
        PnlPaymentReceipt.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 140, -1));

        jLabel155.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel155.setText(":");
        PnlPaymentReceipt.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        lblPaymentShowDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPaymentShowDate.setText("PR001");
        PnlPaymentReceipt.add(lblPaymentShowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 130, -1));

        jLabel156.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel156.setText("Payment Amount");
        PnlPaymentReceipt.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 140, -1));

        jLabel157.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel157.setText(":");
        PnlPaymentReceipt.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, -1));

        lblPaymentShowAmount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPaymentShowAmount.setText("2025-05-01");
        PnlPaymentReceipt.add(lblPaymentShowAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 130, -1));

        jLabel158.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel158.setText("Payment Method");
        PnlPaymentReceipt.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 140, -1));

        jLabel159.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel159.setText(":");
        PnlPaymentReceipt.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        lblPaymentShowMethod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPaymentShowMethod.setText("2025-05-01");
        PnlPaymentReceipt.add(lblPaymentShowMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 130, -1));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 255));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 255));
        PnlPaymentReceipt.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 113, 635, 10));

        PnlPayment.add(PnlPaymentReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 130, 640, 250));

        PnlPaymentReminder.setBackground(new java.awt.Color(204, 204, 255));
        PnlPaymentReminder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel175.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/paymentBGJimmy-Photoroom-Photoroom.png"))); // NOI18N
        PnlPaymentReminder.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 250));

        PnlPayment.add(PnlPaymentReminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 130, 640, 250));

        javax.swing.GroupLayout PnlPaymentSuccesfulLayout = new javax.swing.GroupLayout(PnlPaymentSuccesful);
        PnlPaymentSuccesful.setLayout(PnlPaymentSuccesfulLayout);
        PnlPaymentSuccesfulLayout.setHorizontalGroup(
            PnlPaymentSuccesfulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        PnlPaymentSuccesfulLayout.setVerticalGroup(
            PnlPaymentSuccesfulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        PnlPayment.add(PnlPaymentSuccesful, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        PnlBasement.add(PnlPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 670, 400));

        PnlMedRecord.setBackground(new java.awt.Color(235, 245, 251));
        PnlMedRecord.setPreferredSize(new java.awt.Dimension(670, 400));
        PnlMedRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Medical Record");
        PnlMedRecord.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 18, 245, 27));

        PnlMedRecShow.setBackground(new java.awt.Color(224, 247, 250));
        PnlMedRecShow.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Medical Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        PnlMedRecShow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Medical Report ID");
        PnlMedRecShow.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 140, -1));

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel87.setText(":");
        PnlMedRecShow.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel88.setText("Doctor ID");
        PnlMedRecShow.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, -1));

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel89.setText(":");
        PnlMedRecShow.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel90.setText("Date");
        PnlMedRecShow.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, -1));

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel91.setText(":");
        PnlMedRecShow.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel94.setText("From Doctor");
        PnlMedRecShow.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 140, -1));

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel95.setText(":");
        PnlMedRecShow.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        lblMedRepID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepID.setText("MR011");
        PnlMedRecShow.add(lblMedRepID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 31, 130, -1));

        lblMedRepDID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepDID.setText("MR011");
        PnlMedRecShow.add(lblMedRepDID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 61, 130, -1));

        lblMedRepDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepDate.setText("MR011");
        PnlMedRecShow.add(lblMedRepDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 91, 130, -1));

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel100.setText("Patient ID");
        PnlMedRecShow.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 140, -1));

        jLabel101.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel101.setText(":");
        PnlMedRecShow.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        lblMedRepPID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepPID.setText("MR011");
        PnlMedRecShow.add(lblMedRepPID, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 31, 130, -1));

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel103.setText("Diagnosis");
        PnlMedRecShow.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 140, -1));

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel104.setText(":");
        PnlMedRecShow.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        lblMedRepDiag.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepDiag.setText("MR011");
        PnlMedRecShow.add(lblMedRepDiag, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 440, -1));

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel106.setText("Testing Result");
        PnlMedRecShow.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 140, -1));

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel107.setText(":");
        PnlMedRecShow.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        txtAreaMedRepTesting.setColumns(20);
        txtAreaMedRepTesting.setRows(5);
        txtAreaMedRepTesting.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(txtAreaMedRepTesting);

        PnlMedRecShow.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 290, 40));

        txtAreaMedRepDescrip.setColumns(20);
        txtAreaMedRepDescrip.setRows(5);
        txtAreaMedRepDescrip.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(txtAreaMedRepDescrip);

        PnlMedRecShow.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 590, 110));

        PnlMedRecord.add(PnlMedRecShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 630, 310));

        cboMedRepChoose.setBackground(new java.awt.Color(204, 255, 255));
        cboMedRepChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medical Report to View" }));
        cboMedRepChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMedRepChooseActionPerformed(evt);
            }
        });
        PnlMedRecord.add(cboMedRepChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 20, 190, -1));

        PnlMedRecReminder.setBackground(new java.awt.Color(204, 255, 204));
        PnlMedRecReminder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel178.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/medicalRepBGJimmy-Photoroom-Photoroom.png"))); // NOI18N
        PnlMedRecReminder.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 310));

        PnlMedRecord.add(PnlMedRecReminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 630, 310));

        PnlBasement.add(PnlMedRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 670, 400));

        PnlChild.setBackground(new java.awt.Color(235, 245, 251));
        PnlChild.setPreferredSize(new java.awt.Dimension(670, 400));
        PnlChild.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Select Child to View & Manage");
        PnlChild.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 18, 285, 48));

        cboChildList.setBackground(new java.awt.Color(204, 255, 255));
        cboChildList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Child" }));
        cboChildList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChildListActionPerformed(evt);
            }
        });
        PnlChild.add(cboChildList, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 210, -1));

        btnRegisterChild.setBackground(new java.awt.Color(204, 255, 204));
        btnRegisterChild.setText("Register Now");
        btnRegisterChild.setFocusPainted(false);
        btnRegisterChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterChildActionPerformed(evt);
            }
        });
        PnlChild.add(btnRegisterChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Register for Child");
        PnlChild.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, 48));

        PnlChildDetail.setBackground(new java.awt.Color(224, 247, 250));
        PnlChildDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Child's Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        PnlChildDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Child ID");
        PnlChildDetail.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 100, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("Age");
        PnlChildDetail.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 80, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Region");
        PnlChildDetail.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 100, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Blood Type");
        PnlChildDetail.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 90, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("IC Number");
        PnlChildDetail.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 80, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Gender");
        PnlChildDetail.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 80, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("Child Name");
        PnlChildDetail.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 100, -1));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setText(":");
        PnlChildDetail.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setText(":");
        PnlChildDetail.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText(":");
        PnlChildDetail.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setText(":");
        PnlChildDetail.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setText(":");
        PnlChildDetail.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setText(":");
        PnlChildDetail.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, -1));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setText(":");
        PnlChildDetail.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        txtChildShowID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtChildShowID.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlChildDetail.add(txtChildShowID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 130, -1));

        txtChildShowGender.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtChildShowGender.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlChildDetail.add(txtChildShowGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 130, -1));

        txtChildShowBlood.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtChildShowBlood.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlChildDetail.add(txtChildShowBlood, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 130, -1));

        txtChildShowAge.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtChildShowAge.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlChildDetail.add(txtChildShowAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 130, -1));

        txtChildShowName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtChildShowName.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlChildDetail.add(txtChildShowName, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 200, -1));

        txtChildShowIC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtChildShowIC.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlChildDetail.add(txtChildShowIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 200, -1));

        txtChildShowRegion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtChildShowRegion.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlChildDetail.add(txtChildShowRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 200, -1));

        btnChildDetailEdit.setBackground(new java.awt.Color(255, 102, 102));
        btnChildDetailEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChildDetailEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnChildDetailEdit.setText("EDIT");
        btnChildDetailEdit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnChildDetailEdit.setBorderPainted(false);
        btnChildDetailEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChildDetailEditActionPerformed(evt);
            }
        });
        PnlChildDetail.add(btnChildDetailEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 120, 30));

        btnChildDetailSave.setBackground(new java.awt.Color(204, 255, 204));
        btnChildDetailSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChildDetailSave.setText("SAVE");
        btnChildDetailSave.setBorderPainted(false);
        btnChildDetailSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChildDetailSaveActionPerformed(evt);
            }
        });
        PnlChildDetail.add(btnChildDetailSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 120, 30));

        cboChildToEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Detail to Edit", "Name", "Blood Type", "Region" }));
        cboChildToEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChildToEditActionPerformed(evt);
            }
        });
        PnlChildDetail.add(cboChildToEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, -1));

        PnlChild.add(PnlChildDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 107, 610, 260));

        cboChildToDo.setBackground(new java.awt.Color(204, 255, 255));
        cboChildToDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "To do", "Child Detail", "Prescription Medication", "Medical Record" }));
        cboChildToDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChildToDoActionPerformed(evt);
            }
        });
        PnlChild.add(cboChildToDo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 170, -1));

        PnlChildPreMed.setBackground(new java.awt.Color(255, 255, 204));
        PnlChildPreMed.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel120.setText("Prescription ID");
        PnlChildPreMed.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel121.setText(":");
        PnlChildPreMed.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        lblPreMedID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreMedID1.setText("PR001");
        PnlChildPreMed.add(lblPreMedID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 130, -1));

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel122.setText("Medical Report ID");
        PnlChildPreMed.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, -1));

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel123.setText(":");
        PnlChildPreMed.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        lblPreMedMID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreMedMID1.setText("PR001");
        PnlChildPreMed.add(lblPreMedMID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 130, -1));

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel124.setText("Date");
        PnlChildPreMed.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, -1));

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel125.setText(":");
        PnlChildPreMed.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        lblPreMedDate3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreMedDate3.setText("PR001");
        PnlChildPreMed.add(lblPreMedDate3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 130, -1));

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel126.setText("Date");
        PnlChildPreMed.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 140, -1));

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel127.setText(":");
        PnlChildPreMed.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        lblPreMedDate4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreMedDate4.setText("2025-05-01");
        PnlChildPreMed.add(lblPreMedDate4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 130, -1));

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel131.setText("Medical");
        PnlChildPreMed.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, -1));

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel132.setText(":");
        PnlChildPreMed.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, -1));

        txtAreaPreMedMedical1.setColumns(20);
        txtAreaPreMedMedical1.setRows(5);
        jScrollPane5.setViewportView(txtAreaPreMedMedical1);

        PnlChildPreMed.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 580, 90));

        cboPreMedChoose1.setBackground(new java.awt.Color(204, 255, 255));
        cboPreMedChoose1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prescription To View" }));
        cboPreMedChoose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPreMedChoose1ActionPerformed(evt);
            }
        });
        PnlChildPreMed.add(cboPreMedChoose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        PnlChild.add(PnlChildPreMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 107, 610, 260));

        PnlChildMedRecord.setBackground(new java.awt.Color(255, 255, 204));
        PnlChildMedRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel133.setText("Medical Report ID");
        PnlChildMedRecord.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));

        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel134.setText(":");
        PnlChildMedRecord.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel135.setText("Doctor ID");
        PnlChildMedRecord.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, -1));

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel136.setText(":");
        PnlChildMedRecord.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel137.setText("Patient ID");
        PnlChildMedRecord.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel138.setText(":");
        PnlChildMedRecord.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel139.setText("Date");
        PnlChildMedRecord.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, -1));

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel140.setText(":");
        PnlChildMedRecord.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel141.setText("From Doctor");
        PnlChildMedRecord.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 140, -1));

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel142.setText(":");
        PnlChildMedRecord.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        lblMedRepID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepID1.setText("MR011");
        PnlChildMedRecord.add(lblMedRepID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 130, -1));

        lblMedRepDID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepDID1.setText("MR011");
        PnlChildMedRecord.add(lblMedRepDID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 130, -1));

        lblMedRepPID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepPID1.setText("MR011");
        PnlChildMedRecord.add(lblMedRepPID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 130, -1));

        lblMedRepDate1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepDate1.setText("MR011");
        PnlChildMedRecord.add(lblMedRepDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 130, -1));

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel146.setText("Diagnosis");
        PnlChildMedRecord.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 140, -1));

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel147.setText(":");
        PnlChildMedRecord.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        lblMedRepDiag1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMedRepDiag1.setText("MR011");
        PnlChildMedRecord.add(lblMedRepDiag1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 130, -1));

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel148.setText("Testing Result");
        PnlChildMedRecord.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 140, -1));

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel149.setText(":");
        PnlChildMedRecord.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));

        txtAreaMedRepTesting1.setColumns(20);
        txtAreaMedRepTesting1.setRows(5);
        jScrollPane6.setViewportView(txtAreaMedRepTesting1);

        PnlChildMedRecord.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 290, 60));

        txtAreaMedRepDescrip1.setColumns(20);
        txtAreaMedRepDescrip1.setRows(5);
        jScrollPane7.setViewportView(txtAreaMedRepDescrip1);

        PnlChildMedRecord.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 590, 90));

        cboMedRepChoose1.setBackground(new java.awt.Color(204, 255, 255));
        cboMedRepChoose1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medical Report To View" }));
        cboMedRepChoose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMedRepChoose1ActionPerformed(evt);
            }
        });
        PnlChildMedRecord.add(cboMedRepChoose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        PnlChild.add(PnlChildMedRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 107, 610, 260));

        PnlChildReminder.setBackground(new java.awt.Color(255, 204, 204));
        PnlChildReminder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ChildBGJimmy-Photoroom.png"))); // NOI18N
        jLabel49.setVerifyInputWhenFocusTarget(false);
        PnlChildReminder.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 300, 320));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel75.setText("child");
        PnlChildReminder.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 70, 70));

        jLabel76.setBackground(new java.awt.Color(255, 204, 204));
        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel76.setText("Register or choose your");
        PnlChildReminder.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 290, 70));

        PnlChild.add(PnlChildReminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 107, 610, 260));

        PnlBasement.add(PnlChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        PnlAppointment.setBackground(new java.awt.Color(224, 247, 250));
        PnlAppointment.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 3, true), "Make an Appointment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N
        PnlAppointment.setPreferredSize(new java.awt.Dimension(670, 400));
        PnlAppointment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboAppSpecialist.setBackground(new java.awt.Color(204, 255, 255));
        cboAppSpecialist.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboAppSpecialist.setMaximumRowCount(30);
        cboAppSpecialist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Specialisation", "Cardiology", "Neurology", "Ophthalmology", "Pediatrics" }));
        cboAppSpecialist.setBorder(null);
        cboAppSpecialist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppSpecialistActionPerformed(evt);
            }
        });
        PnlAppointment.add(cboAppSpecialist, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 190, -1));

        cboAppDoctor.setBackground(new java.awt.Color(204, 255, 255));
        cboAppDoctor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboAppDoctor.setMaximumRowCount(31);
        cboAppDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor" }));
        cboAppDoctor.setBorder(null);
        cboAppDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppDoctorActionPerformed(evt);
            }
        });
        PnlAppointment.add(cboAppDoctor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 240, -1));

        lblAppDate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAppDate.setText("Select Date");
        PnlAppointment.add(lblAppDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 202, -1));

        lblAppTime.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAppTime.setText("Select Time");
        PnlAppointment.add(lblAppTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 110, 150, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Month");
        PnlAppointment.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 43, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Day");
        PnlAppointment.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 43, -1));

        AppointmentIndividual.add(rbnAppself);
        rbnAppself.setText("Booking For Yourself");
        rbnAppself.setContentAreaFilled(false);
        rbnAppself.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnAppselfActionPerformed(evt);
            }
        });
        PnlAppointment.add(rbnAppself, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 170, -1));

        btnRequestApp.setBackground(new java.awt.Color(204, 255, 204));
        btnRequestApp.setText("Send Request");
        btnRequestApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequestAppActionPerformed(evt);
            }
        });
        PnlAppointment.add(btnRequestApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 120, 23));

        cboAppMonth.setBackground(new java.awt.Color(255, 153, 102));
        cboAppMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboAppMonth.setMaximumRowCount(21);
        cboAppMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Month ", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" }));
        cboAppMonth.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cboAppMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppMonthActionPerformed(evt);
            }
        });
        PnlAppointment.add(cboAppMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 130, -1));

        cboAppTime.setBackground(new java.awt.Color(255, 153, 102));
        cboAppTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboAppTime.setMaximumRowCount(31);
        cboAppTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Time", "8:00", "9:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "4:00", "5:00" }));
        cboAppTime.setBorder(null);
        cboAppTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppTimeActionPerformed(evt);
            }
        });
        PnlAppointment.add(cboAppTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 160, 116, -1));

        cboAppDay.setBackground(new java.awt.Color(255, 153, 102));
        cboAppDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboAppDay.setMaximumRowCount(35);
        cboAppDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        cboAppDay.setBorder(null);
        cboAppDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppDayActionPerformed(evt);
            }
        });
        PnlAppointment.add(cboAppDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 100, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Specialisation");
        PnlAppointment.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 132, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setText("Doctor");
        PnlAppointment.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 142, -1));

        AppointmentIndividual.add(rbnAppChild);
        rbnAppChild.setText("Booking for child");
        rbnAppChild.setContentAreaFilled(false);
        rbnAppChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnAppChildActionPerformed(evt);
            }
        });
        PnlAppointment.add(rbnAppChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 140, -1));

        lblAppBookfor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAppBookfor.setText("Booking For?");
        PnlAppointment.add(lblAppBookfor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 253, -1));

        lblAppChild.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAppChild.setText("Select Child");
        PnlAppointment.add(lblAppChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 168, -1));

        cboAppChild.setBackground(new java.awt.Color(51, 204, 255));
        cboAppChild.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Child" }));
        cboAppChild.setBorder(null);
        cboAppChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppChildActionPerformed(evt);
            }
        });
        PnlAppointment.add(cboAppChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 152, -1));

        btnCancelApp.setBackground(new java.awt.Color(255, 51, 0));
        btnCancelApp.setText("Cancel");
        btnCancelApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAppActionPerformed(evt);
            }
        });
        PnlAppointment.add(btnCancelApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 102, 23));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel50.setText("Year");
        PnlAppointment.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        cboAppYear.setBackground(new java.awt.Color(255, 153, 102));
        cboAppYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboAppYear.setMaximumRowCount(21);
        cboAppYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year" }));
        cboAppYear.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cboAppYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppYearActionPerformed(evt);
            }
        });
        PnlAppointment.add(cboAppYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 100, -1));

        lblAppDayWarning.setForeground(new java.awt.Color(255, 51, 51));
        lblAppDayWarning.setText("Selected daywas not working");
        PnlAppointment.add(lblAppDayWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 115, 250, -1));

        PnlBasement.add(PnlAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 550, 350));

        PnlAppFirst.setBackground(new java.awt.Color(235, 245, 251));
        PnlAppFirst.setPreferredSize(new java.awt.Dimension(670, 406));
        PnlAppFirst.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Appointment");
        PnlAppFirst.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 18, 271, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("View Your Appointment");
        PnlAppFirst.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 236, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setText("Make an Appointment");
        PnlAppFirst.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, 236, -1));

        btnMakeApp.setBackground(new java.awt.Color(153, 255, 153));
        btnMakeApp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMakeApp.setText("Book Now");
        btnMakeApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakeAppActionPerformed(evt);
            }
        });
        PnlAppFirst.add(btnMakeApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        chkAppFilter.setText("Show Rejected Appointment?");
        chkAppFilter.setContentAreaFilled(false);
        chkAppFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAppFilterActionPerformed(evt);
            }
        });
        PnlAppFirst.add(chkAppFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 290, -1));

        cboAppShowFilter.setBackground(new java.awt.Color(204, 255, 255));
        cboAppShowFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Appointments", "Your Appointment", "Child's Appointment", "All" }));
        cboAppShowFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppShowFilterActionPerformed(evt);
            }
        });
        PnlAppFirst.add(cboAppShowFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 100, 150, -1));

        PnlShowingApp.setBackground(new java.awt.Color(224, 247, 250));
        PnlShowingApp.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true), "Appointment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        PnlShowingApp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel71.setText("Appointment ID");
        PnlShowingApp.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 33, 120, -1));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Status");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 50, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText(":");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 6, 10, -1));

        lblAppShowStatus.setBackground(new java.awt.Color(255, 153, 153));
        lblAppShowStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAppShowStatus.setForeground(new java.awt.Color(255, 51, 51));
        lblAppShowStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAppShowStatus.setText("Deleted");
        jPanel3.add(lblAppShowStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 6, 130, -1));

        PnlShowingApp.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 210, 32));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel72.setText(":");
        PnlShowingApp.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 33, 10, -1));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel73.setText("Doctor ID");
        PnlShowingApp.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 73, 120, -1));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel74.setText(":");
        PnlShowingApp.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 73, 10, -1));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel77.setText("Patient ID");
        PnlShowingApp.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 113, 120, -1));

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel78.setText(":");
        PnlShowingApp.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 113, 10, -1));

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel79.setText("Date");
        PnlShowingApp.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 153, 120, -1));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel80.setText(":");
        PnlShowingApp.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 153, 10, -1));

        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel81.setText("Time");
        PnlShowingApp.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 193, 120, -1));

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel82.setText(":");
        PnlShowingApp.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 193, 10, -1));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel83.setText("Doctor Name");
        PnlShowingApp.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 73, 120, -1));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel84.setText(":");
        PnlShowingApp.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 73, 10, -1));

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel85.setText("Patient Name");
        PnlShowingApp.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 113, 120, -1));

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel86.setText(":");
        PnlShowingApp.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 113, 10, -1));

        lblAppShowID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAppShowID.setText(" ");
        PnlShowingApp.add(lblAppShowID, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 33, 120, -1));

        lblAppShowDID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAppShowDID.setText(" ");
        PnlShowingApp.add(lblAppShowDID, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 73, 120, -1));

        lblAppShowPID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAppShowPID.setText(" ");
        PnlShowingApp.add(lblAppShowPID, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 113, 120, -1));

        lblAppShowDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAppShowDate.setText(" ");
        PnlShowingApp.add(lblAppShowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 153, 200, -1));

        lblAppShowTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAppShowTime.setText(" ");
        PnlShowingApp.add(lblAppShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 193, 120, -1));

        lblAppShowDName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAppShowDName.setText(" ");
        PnlShowingApp.add(lblAppShowDName, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 73, 170, -1));

        lblAppShowPName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAppShowPName.setText(" ");
        PnlShowingApp.add(lblAppShowPName, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 113, 170, -1));

        btnAppDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnAppDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAppDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnAppDelete.setText("Delete Request");
        btnAppDelete.setBorder(null);
        btnAppDelete.setBorderPainted(false);
        btnAppDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAppDeleteActionPerformed(evt);
            }
        });
        PnlShowingApp.add(btnAppDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 190, 120, 23));

        PnlAppFirst.add(PnlShowingApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 130, 630, 240));

        cboAppShowChoose.setBackground(new java.awt.Color(204, 255, 255));
        cboAppShowChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select an appointment", "AP25001 2025-08-01" }));
        cboAppShowChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAppShowChooseActionPerformed(evt);
            }
        });
        PnlAppFirst.add(cboAppShowChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 180, -1));

        PnlAppReminder.setBackground(new java.awt.Color(255, 255, 204));
        PnlAppReminder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/appointmentBGJimmy-Photoroom-Photoroom (1).png"))); // NOI18N
        PnlAppReminder.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 240));

        PnlAppFirst.add(PnlAppReminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 130, 630, 240));

        PnlBasement.add(PnlAppFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 670, -1));

        PnlPreMed.setBackground(new java.awt.Color(235, 245, 251));
        PnlPreMed.setPreferredSize(new java.awt.Dimension(670, 400));
        PnlPreMed.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Prescription ");
        PnlPreMed.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 200, -1));

        PnlPreMedShowing.setBackground(new java.awt.Color(224, 247, 250));
        PnlPreMedShowing.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Medical use instruction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        PnlPreMedShowing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel96.setText("Prescription ID");
        PnlPreMedShowing.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 140, -1));

        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel97.setText(":");
        PnlPreMedShowing.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        lblPreMedID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreMedID.setText("PR001");
        PnlPreMedShowing.add(lblPreMedID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 130, -1));

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel102.setText("Medical Report ID");
        PnlPreMedShowing.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, -1));

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel105.setText(":");
        PnlPreMedShowing.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        lblPreMedMID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreMedMID.setText("PR001");
        PnlPreMedShowing.add(lblPreMedMID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 130, -1));

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel109.setText("Issues Date");
        PnlPreMedShowing.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 140, -1));

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel110.setText(":");
        PnlPreMedShowing.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        lblPreMedDate1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreMedDate1.setText("PR001");
        PnlPreMedShowing.add(lblPreMedDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 130, -1));

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel112.setText("Despended Date");
        PnlPreMedShowing.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 140, -1));

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel113.setText(":");
        PnlPreMedShowing.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, -1, -1));

        lblPreMedDate2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPreMedDate2.setText("2025-05-01");
        PnlPreMedShowing.add(lblPreMedDate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 130, -1));

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel118.setText("Medical");
        PnlPreMedShowing.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 140, -1));

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel119.setText(":");
        PnlPreMedShowing.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        txtAreaPreMedMedical.setColumns(20);
        txtAreaPreMedMedical.setRows(5);
        txtAreaPreMedMedical.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtAreaPreMedMedical);

        PnlPreMedShowing.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 580, 120));

        PnlPreMed.add(PnlPreMedShowing, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 630, 310));

        cboPreMedChoose.setBackground(new java.awt.Color(204, 255, 255));
        cboPreMedChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Presciption to View" }));
        cboPreMedChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPreMedChooseActionPerformed(evt);
            }
        });
        PnlPreMed.add(cboPreMedChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 20, 190, -1));

        PnlPreMedReminder.setBackground(new java.awt.Color(255, 255, 255));
        PnlPreMedReminder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel181.setBackground(new java.awt.Color(255, 255, 255));
        jLabel181.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/prescriptionBGJimmy-Photoroom-Photoroom.png"))); // NOI18N
        PnlPreMedReminder.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 310));

        PnlPreMed.add(PnlPreMedReminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 60, 630, 310));

        PnlBasement.add(PnlPreMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, -1));

        PnlVaccination.setBackground(new java.awt.Color(235, 245, 251));
        PnlVaccination.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel93.setText("Vaccination");
        PnlVaccination.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        PnlVaccShow.setBackground(new java.awt.Color(224, 247, 250));
        PnlVaccShow.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "Medical use instruction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        PnlVaccShow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel99.setText("Vaccine ID");
        PnlVaccShow.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 140, -1));

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel128.setText(":");
        PnlVaccShow.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        lblVaccID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVaccID.setText("PR001");
        PnlVaccShow.add(lblVaccID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 130, -1));

        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel129.setText("Vaccine Type");
        PnlVaccShow.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, -1));

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel130.setText(":");
        PnlVaccShow.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        lblVaccType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVaccType.setText("PR001");
        PnlVaccShow.add(lblVaccType, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 130, -1));

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel143.setText("Vaccine Date");
        PnlVaccShow.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 140, -1));

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel144.setText(":");
        PnlVaccShow.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        lblVaccDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVaccDate.setText("PR001");
        PnlVaccShow.add(lblVaccDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 130, -1));

        jLabel176.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel176.setText("Patient ID");
        PnlVaccShow.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 140, -1));

        jLabel177.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel177.setText(":");
        PnlVaccShow.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        lblVaccPID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVaccPID.setText("2025-05-01");
        PnlVaccShow.add(lblVaccPID, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 130, -1));

        jLabel179.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel179.setText("Description");
        PnlVaccShow.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 140, -1));

        jLabel180.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel180.setText(":");
        PnlVaccShow.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        txtAreaVaccDesc.setColumns(20);
        txtAreaVaccDesc.setRows(5);
        txtAreaVaccDesc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane8.setViewportView(txtAreaVaccDesc);

        PnlVaccShow.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 580, 80));

        PnlVaccination.add(PnlVaccShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 630, 270));

        cboVaccChoose.setBackground(new java.awt.Color(204, 255, 255));
        cboVaccChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Upcoming Vaccine" }));
        cboVaccChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVaccChooseActionPerformed(evt);
            }
        });
        PnlVaccination.add(cboVaccChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel145.setText("Your Upcoming Vaccine");
        PnlVaccination.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        cboVaccChoose1.setBackground(new java.awt.Color(204, 255, 255));
        cboVaccChoose1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Upcoming Vaccine" }));
        cboVaccChoose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVaccChoose1ActionPerformed(evt);
            }
        });
        PnlVaccination.add(cboVaccChoose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        lblVaccineShowChild.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVaccineShowChild.setText("Your Child's Upcoming Vaccine");
        PnlVaccination.add(lblVaccineShowChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, -1, -1));

        PnlVaccineReminder.setBackground(new java.awt.Color(255, 255, 204));
        PnlVaccineReminder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel182.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/VaccinationBGJimmy-Photoroom-Photoroom.png"))); // NOI18N
        PnlVaccineReminder.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 270));

        PnlVaccination.add(PnlVaccineReminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 630, 270));

        PnlBasement.add(PnlVaccination, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 670, 400));

        btnVaccination.setText("Vaccination");
        btnVaccination.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVaccination.setContentAreaFilled(false);
        btnVaccination.setFocusPainted(false);
        btnVaccination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaccinationActionPerformed(evt);
            }
        });
        PnlBasement.add(btnVaccination, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 130, 60));

        btnAppointment.setText("APPOINTMENT");
        btnAppointment.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAppointment.setContentAreaFilled(false);
        btnAppointment.setFocusPainted(false);
        btnAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAppointmentActionPerformed(evt);
            }
        });
        PnlBasement.add(btnAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 60));

        btnMedRecord.setText("MEDICAL RECORD");
        btnMedRecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMedRecord.setContentAreaFilled(false);
        btnMedRecord.setFocusPainted(false);
        btnMedRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedRecordActionPerformed(evt);
            }
        });
        PnlBasement.add(btnMedRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 130, 60));

        btnPreMed.setText("MEDICATION");
        btnPreMed.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPreMed.setContentAreaFilled(false);
        btnPreMed.setFocusPainted(false);
        btnPreMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreMedActionPerformed(evt);
            }
        });
        PnlBasement.add(btnPreMed, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 130, 60));

        btnPayment.setText("PAYMENT");
        btnPayment.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPayment.setContentAreaFilled(false);
        btnPayment.setFocusPainted(false);
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });
        PnlBasement.add(btnPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 130, 60));

        btnChild.setText("CHILD");
        btnChild.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnChild.setContentAreaFilled(false);
        btnChild.setFocusPainted(false);
        btnChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChildActionPerformed(evt);
            }
        });
        PnlBasement.add(btnChild, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 130, 60));

        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundJimmy.png"))); // NOI18N
        PnlBasement.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 650, 390));

        getContentPane().add(PnlBasement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 800, 400));

        PnlEditPassword.setBackground(new java.awt.Color(235, 245, 251));
        PnlEditPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PnlEditPassword.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel111.setText("Password");
        PnlEditPassword.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel160.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel160.setText("Old Password");
        PnlEditPassword.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, -1));

        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel161.setText(":");
        PnlEditPassword.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 6, -1));
        PnlEditPassword.add(txtPasswordOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 240, -1));

        jLabel162.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel162.setText("New Password");
        PnlEditPassword.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 100, -1));

        jLabel163.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel163.setText(":");
        PnlEditPassword.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 6, -1));
        PnlEditPassword.add(txtPasswordNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 240, -1));

        btnConfirmPassword.setBackground(new java.awt.Color(255, 255, 204));
        btnConfirmPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnConfirmPassword.setText("Change Password");
        btnConfirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmPasswordActionPerformed(evt);
            }
        });
        PnlEditPassword.add(btnConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        lblValidationPass1.setForeground(new java.awt.Color(255, 0, 0));
        lblValidationPass1.setText("Password must contains atleast one");
        PnlEditPassword.add(lblValidationPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        lblValidationPass2.setForeground(new java.awt.Color(255, 0, 0));
        lblValidationPass2.setText("Uppercase, Lowercase and Special Character");
        PnlEditPassword.add(lblValidationPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 136, -1, -1));

        lblWrongPass.setForeground(new java.awt.Color(255, 51, 51));
        lblWrongPass.setText("Wrong password");
        PnlEditPassword.add(lblWrongPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        btnCancelPassword.setBackground(new java.awt.Color(255, 255, 204));
        btnCancelPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelPassword.setText("Cancel");
        btnCancelPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelPasswordActionPerformed(evt);
            }
        });
        PnlEditPassword.add(btnCancelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        getContentPane().add(PnlEditPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 400, 200));

        PnlEditSafetyQ.setBackground(new java.awt.Color(235, 245, 251));
        PnlEditSafetyQ.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PnlEditSafetyQ.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel164.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel164.setText("Safety Question");
        PnlEditSafetyQ.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel165.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel165.setText("Question 1");
        PnlEditSafetyQ.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel166.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel166.setText("Question ");
        PnlEditSafetyQ.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, -1));

        jLabel167.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel167.setText("Answer");
        PnlEditSafetyQ.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 110, -1));

        jLabel168.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel168.setText(":");
        PnlEditSafetyQ.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 10, -1));

        jLabel169.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel169.setText(":");
        PnlEditSafetyQ.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 10, -1));

        lblSafetyQ1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSafetyQ1.setText("jLabel170");
        PnlEditSafetyQ.add(lblSafetyQ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 430, -1));

        txtSafetyQ1.setText("jTextField1");
        PnlEditSafetyQ.add(txtSafetyQ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 430, -1));

        jLabel170.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel170.setText("Question 2");
        PnlEditSafetyQ.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel171.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel171.setText("Question ");
        PnlEditSafetyQ.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 110, -1));

        jLabel172.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel172.setText("Answer");
        PnlEditSafetyQ.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 110, -1));

        jLabel173.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel173.setText(":");
        PnlEditSafetyQ.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 10, -1));

        jLabel174.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel174.setText(":");
        PnlEditSafetyQ.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 10, -1));

        lblSafetyQ2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSafetyQ2.setText("jLabel170");
        PnlEditSafetyQ.add(lblSafetyQ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 430, -1));

        txtSafetyQ2.setText("jTextField1");
        PnlEditSafetyQ.add(txtSafetyQ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 430, -1));

        btnSafetyQCancel.setBackground(new java.awt.Color(255, 204, 204));
        btnSafetyQCancel.setText("Cancel");
        btnSafetyQCancel.setBorderPainted(false);
        btnSafetyQCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafetyQCancelActionPerformed(evt);
            }
        });
        PnlEditSafetyQ.add(btnSafetyQCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, -1, -1));

        btnSafetyQSave.setBackground(new java.awt.Color(204, 255, 204));
        btnSafetyQSave.setText("Save");
        btnSafetyQSave.setBorderPainted(false);
        btnSafetyQSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafetyQSaveActionPerformed(evt);
            }
        });
        PnlEditSafetyQ.add(btnSafetyQSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 72, -1));

        btnSafetyQEdit1.setBackground(new java.awt.Color(204, 255, 204));
        btnSafetyQEdit1.setText("Edit Question 1");
        btnSafetyQEdit1.setBorderPainted(false);
        btnSafetyQEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafetyQEdit1ActionPerformed(evt);
            }
        });
        PnlEditSafetyQ.add(btnSafetyQEdit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 135, -1, -1));

        btnSafetyQEdit2.setBackground(new java.awt.Color(204, 255, 204));
        btnSafetyQEdit2.setText("Edit Question 2");
        btnSafetyQEdit2.setBorderPainted(false);
        btnSafetyQEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafetyQEdit2ActionPerformed(evt);
            }
        });
        PnlEditSafetyQ.add(btnSafetyQEdit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 235, -1, -1));

        cboSafetyQ1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Question" }));
        PnlEditSafetyQ.add(cboSafetyQ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 430, -1));

        cboSafetyQ2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Question" }));
        PnlEditSafetyQ.add(cboSafetyQ2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 430, -1));

        btnSafetyQBack.setBackground(new java.awt.Color(255, 204, 204));
        btnSafetyQBack.setText("Back To Profile");
        btnSafetyQBack.setBorderPainted(false);
        btnSafetyQBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafetyQBackActionPerformed(evt);
            }
        });
        PnlEditSafetyQ.add(btnSafetyQBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        getContentPane().add(PnlEditSafetyQ, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 600, 300));

        PnlProfile.setBackground(new java.awt.Color(235, 245, 251));
        PnlProfile.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Profile", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        PnlProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setText("Patient ID");
        PnlProfile.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setText(":");
        PnlProfile.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 10, -1));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel53.setText("Name");
        PnlProfile.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 140, -1));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setText(":");
        PnlProfile.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 10, -1));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel55.setText("IC Number");
        PnlProfile.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 140, -1));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel56.setText(":");
        PnlProfile.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 10, -1));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setText("Contact Number");
        PnlProfile.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 140, -1));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setText(":");
        PnlProfile.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 10, -1));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel59.setText("Email Address");
        PnlProfile.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 140, -1));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel60.setText(":");
        PnlProfile.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 10, -1));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel61.setText("Age");
        PnlProfile.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 120, -1));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel62.setText(":");
        PnlProfile.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 10, -1));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel63.setText("Gender");
        PnlProfile.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 120, -1));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel64.setText(":");
        PnlProfile.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 10, -1));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel65.setText("Race");
        PnlProfile.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 120, -1));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel66.setText(":");
        PnlProfile.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 10, -1));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel67.setText("Region");
        PnlProfile.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 120, -1));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel68.setText(":");
        PnlProfile.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 10, -1));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel69.setText("Blood Type");
        PnlProfile.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 120, -1));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setText(":");
        PnlProfile.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 10, -1));

        txtProfID.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfID, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 40, 195, -1));

        txtProfName.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 100, 195, -1));

        txtProfIC.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 160, 195, -1));

        btnProfNameEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnProfNameEdit.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfNameEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EditProfileJimmy.png"))); // NOI18N
        btnProfNameEdit.setBorderPainted(false);
        btnProfNameEdit.setFocusPainted(false);
        btnProfNameEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfNameEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfNameEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 20, 20));

        btnProfNameSave.setBackground(new java.awt.Color(204, 255, 204));
        btnProfNameSave.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfNameSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SaveProfileJImmy.png"))); // NOI18N
        btnProfNameSave.setAlignmentY(0.0F);
        btnProfNameSave.setBorderPainted(false);
        btnProfNameSave.setFocusPainted(false);
        btnProfNameSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfNameSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnProfNameSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfNameSaveActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfNameSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 20, 20));

        txtProfContact.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 220, 195, -1));

        txtProfEmail.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 280, 195, -1));

        txtProfAge.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 40, 195, -1));

        txtProfGender.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 100, 195, -1));

        txtProfRace.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfRace, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 160, 195, -1));

        txtProfRegion.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 220, 195, -1));

        txtProfBlood.setDisabledTextColor(new java.awt.Color(153, 204, 255));
        PnlProfile.add(txtProfBlood, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 280, 195, -1));

        btnProfPass.setBackground(new java.awt.Color(255, 163, 163));
        btnProfPass.setText("Password");
        btnProfPass.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProfPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfPassActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 210, 50));

        btnProfSafe.setBackground(new java.awt.Color(204, 255, 204));
        btnProfSafe.setText("Safety Question");
        btnProfSafe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProfSafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfSafeActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfSafe, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 210, 50));

        btnProfConEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnProfConEdit.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfConEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EditProfileJimmy.png"))); // NOI18N
        btnProfConEdit.setBorderPainted(false);
        btnProfConEdit.setFocusPainted(false);
        btnProfConEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfConEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfConEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 20, 20));

        btnProfConSave.setBackground(new java.awt.Color(204, 255, 204));
        btnProfConSave.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfConSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SaveProfileJImmy.png"))); // NOI18N
        btnProfConSave.setAlignmentY(0.0F);
        btnProfConSave.setBorderPainted(false);
        btnProfConSave.setFocusPainted(false);
        btnProfConSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfConSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnProfConSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfConSaveActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfConSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 20, 20));

        btnProfEmailEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnProfEmailEdit.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfEmailEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EditProfileJimmy.png"))); // NOI18N
        btnProfEmailEdit.setBorderPainted(false);
        btnProfEmailEdit.setFocusPainted(false);
        btnProfEmailEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfEmailEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfEmailEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 20, 20));

        btnProfEmailSave.setBackground(new java.awt.Color(204, 255, 204));
        btnProfEmailSave.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfEmailSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SaveProfileJImmy.png"))); // NOI18N
        btnProfEmailSave.setAlignmentY(0.0F);
        btnProfEmailSave.setBorderPainted(false);
        btnProfEmailSave.setFocusPainted(false);
        btnProfEmailSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfEmailSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnProfEmailSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfEmailSaveActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfEmailSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 20, 20));

        btnProfBloodEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnProfBloodEdit.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfBloodEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EditProfileJimmy.png"))); // NOI18N
        btnProfBloodEdit.setBorderPainted(false);
        btnProfBloodEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfBloodEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfBloodEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, 20, 20));

        btnProfBloodSave.setBackground(new java.awt.Color(204, 255, 204));
        btnProfBloodSave.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfBloodSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SaveProfileJImmy.png"))); // NOI18N
        btnProfBloodSave.setAlignmentY(0.0F);
        btnProfBloodSave.setBorderPainted(false);
        btnProfBloodSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfBloodSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnProfBloodSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfBloodSaveActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfBloodSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, 20, 20));

        btnCancelEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnCancelEdit.setText("Cancel");
        btnCancelEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnCancelEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 332, -1, -1));

        btnProfRegionEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnProfRegionEdit.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfRegionEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EditProfileJimmy.png"))); // NOI18N
        btnProfRegionEdit.setBorderPainted(false);
        btnProfRegionEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfRegionEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfRegionEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 20, 20));

        btnProfRegionSave.setBackground(new java.awt.Color(204, 255, 204));
        btnProfRegionSave.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfRegionSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SaveProfileJImmy.png"))); // NOI18N
        btnProfRegionSave.setAlignmentY(0.0F);
        btnProfRegionSave.setBorderPainted(false);
        btnProfRegionSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfRegionSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnProfRegionSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfRegionSaveActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfRegionSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 20, 20));

        btnProfRaceEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnProfRaceEdit.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfRaceEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EditProfileJimmy.png"))); // NOI18N
        btnProfRaceEdit.setBorderPainted(false);
        btnProfRaceEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfRaceEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfRaceEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 20, 20));

        btnProfRaceSave.setBackground(new java.awt.Color(204, 255, 204));
        btnProfRaceSave.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfRaceSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SaveProfileJImmy.png"))); // NOI18N
        btnProfRaceSave.setAlignmentY(0.0F);
        btnProfRaceSave.setBorderPainted(false);
        btnProfRaceSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfRaceSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnProfRaceSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfRaceSaveActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfRaceSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 20, 20));

        btnProfGenderEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnProfGenderEdit.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfGenderEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EditProfileJimmy.png"))); // NOI18N
        btnProfGenderEdit.setBorderPainted(false);
        btnProfGenderEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfGenderEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfGenderEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 20, 20));

        btnProfGenderSave.setBackground(new java.awt.Color(204, 255, 204));
        btnProfGenderSave.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfGenderSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SaveProfileJImmy.png"))); // NOI18N
        btnProfGenderSave.setAlignmentY(0.0F);
        btnProfGenderSave.setBorderPainted(false);
        btnProfGenderSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfGenderSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnProfGenderSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfGenderSaveActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfGenderSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 20, 20));

        btnProfICEdit.setBackground(new java.awt.Color(255, 163, 163));
        btnProfICEdit.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfICEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EditProfileJimmy.png"))); // NOI18N
        btnProfICEdit.setBorderPainted(false);
        btnProfICEdit.setFocusPainted(false);
        btnProfICEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfICEditActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfICEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 20, 20));

        btnProfICSave.setBackground(new java.awt.Color(204, 255, 204));
        btnProfICSave.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProfICSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SaveProfileJImmy.png"))); // NOI18N
        btnProfICSave.setAlignmentY(0.0F);
        btnProfICSave.setBorderPainted(false);
        btnProfICSave.setFocusPainted(false);
        btnProfICSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfICSave.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnProfICSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfICSaveActionPerformed(evt);
            }
        });
        PnlProfile.add(btnProfICSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 20, 20));

        getContentPane().add(PnlProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 800, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
       
        PnlDetailBar.setVisible(true);
        btnProfile.setVisible(false);
        enabling(false);
        
            
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppointmentActionPerformed
        // TODO add your handling code here:
        hiding();
        PnlAppFirst.setVisible(true);
        btnAppointment.setBorderPainted(false);
        
        PnlAppReminder.setVisible(true);
        PnlShowingApp.setVisible(false);
       cboAppShowChoose.setEnabled(false);
        
    }//GEN-LAST:event_btnAppointmentActionPerformed

    private void btnMedRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedRecordActionPerformed
        // TODO add your handling code here:
        hiding();
        
        PnlMedRecord.setVisible(true);
        btnMedRecord.setBorderPainted(false);
        
        removeCbo(cboMedRepChoose);
                
        for(String[] medicalList : File_Control.readFile("medicalReport.txt",true)){  
            if(medicalList[1].equals(Main.getCurrentUserID())){
                cboMedRepChoose.addItem(medicalList[0] + " " + medicalList[8]);
            }
        }
        
        PnlMedRecReminder.setVisible(true);
            PnlMedRecShow.setVisible(false);
            lblMedRepID.setText("");
            lblMedRepPID.setText("");
            lblMedRepDID.setText("");
            lblMedRepDiag.setText("");
            lblMedRepDate.setText("");
            txtAreaMedRepDescrip.setText("");
    }//GEN-LAST:event_btnMedRecordActionPerformed

    private void btnPreMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreMedActionPerformed
        // TODO add your handling code here:
        hiding();
        PnlPreMed.setVisible(true);
        btnPreMed.setBorderPainted(false);
        
        removeCbo(cboPreMedChoose);
                
        for(String[] medicalList : File_Control.readFile("medicalReport.txt",true)){  
            if(medicalList[1].equals(Main.getCurrentUserID())){
                for(String[] PreMedList : File_Control.readFile("prescriptionData.txt",true)){  
                    if(PreMedList[1].equals(medicalList[0])){
                        cboPreMedChoose.addItem(PreMedList[0] + " " + PreMedList[1]);
                    }
            }
            }
        }
            
        PnlPreMedReminder.setVisible(true);
            PnlPreMedShowing.setVisible(false);
            lblPreMedID.setText("");
            lblPreMedMID.setText("");
            lblPreMedDate1.setText("");
            lblPreMedDate2.setText("");
            txtAreaPreMedMedical.setText("");
    }//GEN-LAST:event_btnPreMedActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        // TODO add your handling code here:
        hiding();
        PnlPayment.setVisible(true);
        btnPayment.setBorderPainted(false);
        cboPaymentChoose.setVisible(false);
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnVaccinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaccinationActionPerformed
        // TODO add your handling code here:
        hiding();
        
        PnlVaccination.setVisible(true);
        btnVaccination.setBorderPainted(false);
        
        ArrayList<String> allChild = generalChild.getAllChildID(Main.getCurrentUserID());
        
        removeCbo(cboVaccChoose);
                
        for(String[] VaccineList : File_Control.readFile("vaccinationData.txt",true)){  
            if(VaccineList[1].equals(Main.getCurrentUserID())){
                cboVaccChoose.addItem(VaccineList[0] + " " + VaccineList[3]);
            }
        }
        
        if(allChild != null){
            for(String child: allChild){
                for(String[] VaccineList : File_Control.readFile("vaccinationData.txt",true)){  
                    if(VaccineList[1].equals(child)){
                        cboVaccChoose1.addItem(VaccineList[0] + " " + VaccineList[3]);
                    }
                }
            }
            if(cboVaccChoose1.getItemCount() > 1){
                lblVaccineShowChild.setVisible(true);
                cboVaccChoose1.setVisible(true);
            }
        }
        
        
        PnlVaccineReminder.setVisible(true);
            PnlVaccShow.setVisible(false);
            lblVaccID.setText("");
            lblVaccPID.setText("");
            lblVaccType.setText("");
            lblVaccDate.setText("");
            txtAreaVaccDesc.setText("");
    }//GEN-LAST:event_btnVaccinationActionPerformed

    private void btnChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChildActionPerformed
        // TODO add your handling code here:
        hiding();
        enablingChild(false);
        PnlChild.setVisible(true);
        btnChild.setBorderPainted(false);
        
        PnlChildReminder.setVisible(true);
        
        
        if(cboChildList.getItemCount() > 1){
            removeCbo(cboChildList);
        }
        ArrayList<String[]> currentChildList = everyChild.getIndividual(Main.getCurrentUserID());
        for(String[] child : currentChildList){
            cboChildList.addItem(child[0] + " "+ child[2]);
        }
    }//GEN-LAST:event_btnChildActionPerformed

    private void cboAppSpecialistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppSpecialistActionPerformed
        // TODO add your handling code here:
        String selectedSpecialist = cboAppSpecialist.getSelectedItem().toString();
        int selectedSpecialistInt = cboAppSpecialist.getSelectedIndex();
        ArrayList<String[]> list = File_Control.readFile("doctorData.txt",true);
        
        
        removeCbo(cboAppDoctor);
        
        
        
        if (selectedSpecialistInt != 0){
            cboAppDoctor.setEnabled(true);
           for (String[] UserList : list){
                if (UserList[3].equals(selectedSpecialist)){
                    cboAppDoctor.addItem(UserList[1]);
                }
            }
        }else{
            enablingAppointment(false);
            
        }
    }//GEN-LAST:event_cboAppSpecialistActionPerformed

    private void btnRequestAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequestAppActionPerformed
        if(appMaking.getAppointmentDay().equals("SUNDAY")){
            lblAppDayWarning.setText("Selected day was Sunday");
            lblAppDayWarning.setVisible(true);
            
        }else if(appMaking.getAppointmentDay().equals("SATURDAY")){
            lblAppDayWarning.setText("Selected day was Saturday");
            lblAppDayWarning.setVisible(true);
        }else{
            appMaking.setAppointmentID(Utility_Methods.autoGenerateID("appointmentData.txt","AP"));
            appMaking.setStatus("Pending");


            File_Control.addData("appointmentData.txt", appMaking.makeitStringApp());
            PnlAppointment.setVisible(false);
            lblAppDayWarning.setVisible(false);
            cboAppShowChoose.setEnabled(false);
            enabling(true);
        }
    }//GEN-LAST:event_btnRequestAppActionPerformed

    private void btnRegisterChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterChildActionPerformed
        // TODO add your handling code here:
        hiding();
        
        PnlChild.setVisible(true);
        PnlRegisterChild.setVisible(true);
        lblChildInvalidIC.setVisible(false);
        enabling(false);
        
    }//GEN-LAST:event_btnRegisterChildActionPerformed

    private void rbnParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnParentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbnParentActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        // TODO add your handling code here:
        hiding();
        PnlBasement.setVisible(true);
        PnlSettings.setVisible(false);
        btnProfile.setVisible(true);
        btnProfile.setEnabled(true);
        enabling(true);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        // TODO add your handling code here:
        PnlSettings.setVisible(true);
        enabling(false);
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnCancelRegChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelRegChildActionPerformed
        // TODO add your handling code here:
        hiding();
        
        PnlChild.setVisible(true);
        PnlRegisterChild.setVisible(false);
        
        enabling(true);
        lblChildInvalidIC.setVisible(false);
        lblChildRegWarning.setVisible(false);
    }//GEN-LAST:event_btnCancelRegChildActionPerformed

    private void btnCancelAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAppActionPerformed
        // TODO add your handling code here:
        PnlAppointment.setVisible(false);
        lblAppDayWarning.setVisible(false);
        
        enabling(true);
        cboAppShowChoose.setEnabled(false);
    }//GEN-LAST:event_btnCancelAppActionPerformed

    private void btnSideProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSideProfActionPerformed
        // TODO add your handling code here:
        hiding();
        PnlBasement.setVisible(false);
        PnlSettings.setVisible(false);
        PnlProfile.setVisible(true);
        btnProfile.setVisible(true);
        btnProfile.setEnabled(true);
        enabling(true);
        enablingProfile(false);
        
        String[] accountOwner = new String[10];
        
        for(String[] Patient: currentPatient.getIndividual(Main.getCurrentUserID())){
            accountOwner = Patient;
        }
        
        currentPatient = new Patient(accountOwner[0],accountOwner[1],accountOwner[2],
                                     accountOwner[3],accountOwner[4],accountOwner[5],
                                     accountOwner[6],accountOwner[7],accountOwner[8],
                                     accountOwner[9]);
        
        txtProfID.setText(currentPatient.getUserID());
        txtProfName.setText(currentPatient.getUserName());
        txtProfIC.setText(currentPatient.getIcNumber());
        txtProfAge.setText(currentPatient.getAge());
        txtProfGender.setText(currentPatient.getGender());
        txtProfRegion.setText(currentPatient.getRegion());
        txtProfContact.setText(currentPatient.getContactNumber());
        txtProfEmail.setText(currentPatient.getEmail());
        txtProfRegion.setText(currentPatient.getRegion());
        txtProfRace.setText(currentPatient.getRace());
        txtProfBlood.setText(currentPatient.getBloodType());
        
        
        enablingAllProfileOposite(false);
    }//GEN-LAST:event_btnSideProfActionPerformed

    private void btnMakeAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakeAppActionPerformed
        // TODO add your handling code here:
        hiding();
        enabling(false);
        PnlAppFirst.setVisible(true);
        PnlAppointment.setVisible(true);
        enablingAppointment(false);
        cboAppSpecialist.setEnabled(true);
    }//GEN-LAST:event_btnMakeAppActionPerformed

    private void cboChildToDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChildToDoActionPerformed
        // TODO add your handling code here:
        PnlChildDetail.setVisible(false);
        PnlChildPreMed.setVisible(false);
        PnlChildMedRecord.setVisible(false);
        PnlChildReminder.setVisible(false);
        switch(cboChildToDo.getSelectedIndex()){
            case 1 -> PnlChildDetail.setVisible(true);
            case 2 -> {
                PnlChildPreMed.setVisible(true);
                removeCbo(cboPreMedChoose1);
                
                // ADD ALL MEDICAL RECORD FOR SELECTED CHILD INTO COMBO BOX
                for(String[] medicalList : File_Control.readFile("medicalReport.txt",true)){  
                    if(medicalList[1].equals(selectedChildid)){
                        for(String[] PreMedList : File_Control.readFile("prescriptionData.txt",true)){  
                            if(PreMedList[1].equals(medicalList[0])){
                                cboPreMedChoose1.addItem(PreMedList[0] + " " + PreMedList[1]);
                            }
                    }
                    }
                }

                
                    lblPreMedID1.setText("");
                    lblPreMedMID1.setText("");
                    lblPreMedDate3.setText("");
                    lblPreMedDate4.setText("");
                    txtAreaPreMedMedical1.setText("");
            }
            case 3 -> {
                PnlChildMedRecord.setVisible(true);
                removeCbo(cboMedRepChoose1);
                
                // ADD ALL PRESCRIPTION FOR SELECTED CHILD INTO COMBO BOX
                for(String[] medicalList : File_Control.readFile("medicalReport.txt",true)){  
                    if(medicalList[1].equals(selectedChildid)){
                        cboMedRepChoose1.addItem(medicalList[0] + " " + medicalList[8]);
                    }
                }


                    lblMedRepID1.setText("");
                    lblMedRepDID1.setText("");
                    lblMedRepDiag1.setText("");
                    lblMedRepPID1.setText("");
                    lblMedRepDate1.setText("");
                    txtAreaMedRepDescrip1.setText("");
            }
            default -> PnlChildReminder.setVisible(true);
        }
    }//GEN-LAST:event_cboChildToDoActionPerformed

    private void cboChildListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChildListActionPerformed
        // TODO add your handling code here:
        
        
       ArrayList<String[]> currentChildlist = generalChild.getIndividual(Main.getCurrentUserID());
        
       String selectedChild = cboChildList.getSelectedItem().toString();
       int selectedChildIndex = cboChildList.getSelectedIndex();
        
        Child chosen = null;
        
        for(String[] ChildDetail: currentChildlist){
            if(selectedChild.equals(ChildDetail[0] + " "+ ChildDetail[2]))
                chosen = new Child(ChildDetail[0], ChildDetail[1], ChildDetail[2],
                                         ChildDetail[3], ChildDetail[4], ChildDetail[5],
                                         ChildDetail[6], ChildDetail[7]);
        }
        
        if(selectedChildIndex != 0){
            cboChildToDo.setEnabled(true);
        }
        else{
            cboChildToDo.setEnabled(false);
            PnlChildReminder.setVisible(true);
            PnlChildDetail.setVisible(false);
        }
        if(chosen != null){
            selectedChildid = chosen.getUserID();
            txtChildShowID.setText(chosen.getUserID());
            txtChildShowName.setText(chosen.getUserName());
            txtChildShowGender.setText(chosen.getGender());
            txtChildShowIC.setText(chosen.getIcNumber());
            txtChildShowBlood.setText(chosen.getBloodType());
            txtChildShowRegion.setText(chosen.getRegion());
            txtChildShowAge.setText(chosen.getAge());
        } else{
            txtChildShowID.setText("");
            txtChildShowName.setText("");
            txtChildShowGender.setText("");
            txtChildShowIC.setText("");
            txtChildShowBlood.setText("");
            txtChildShowRegion.setText("");
            txtChildShowAge.setText("");
        }
            
    }//GEN-LAST:event_cboChildListActionPerformed

    private void btnChildDetailSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChildDetailSaveActionPerformed
        // TODO add your handling code here:{userID, ParentID, userName,
                                //PatientIC, Gender, Region,
                                //Age, BloodType}
        
        
        cboChildToDo.setEnabled(true);
        btnChildDetailEdit.setVisible(true);
        btnChildDetailEdit.setEnabled(true);
        btnChildDetailSave.setVisible(false);
        cboChildToEdit.setVisible(false);
        
        int i = 0;
        
        
        
        
        if(txtChildShowName.isEnabled()){
            i = 2;
            generalChild.setUserName(txtChildShowName.getText().strip());
        }else if (txtChildShowRegion.isEnabled()){
            i = 5;
        }else if(txtChildShowBlood.isEnabled()){
            i = 7;
        }
        
        String toChange = switch(i){
            case 2 -> txtChildShowName.getText().strip();
            case 5 -> txtChildShowRegion.getText().strip();
            case 7 -> txtChildShowBlood.getText().strip();
            default -> "";
        };
        

        enablingChild(false);

        
        
        Patient.modifyOneValue("childData.txt", selectedChildid, toChange, i);
        
        //if(isError = false){
        //    Patient.modifyOneValue("childData", allDetail, toModify, j);
        //}
        if(cboChildList.getItemCount() > 1){
            removeCbo(cboChildList);
}
        ArrayList<String[]> currentChildList = everyChild.getIndividual(Main.getCurrentUserID());
        for(String[] child : currentChildList){
            cboChildList.addItem(child[0] + " "+ child[2]);
        }
        
        enabling(true);
        cboChildList.setEnabled(true);
        PnlChildReminder.setVisible(true);
        cboChildToDo.setEnabled(false);
    }//GEN-LAST:event_btnChildDetailSaveActionPerformed

    private void btnChildDetailEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChildDetailEditActionPerformed
        // TODO add your handling code here:
        cboChildToEdit.setVisible(true);
        enabling(false);
        
    }//GEN-LAST:event_btnChildDetailEditActionPerformed

    private void cboChildToEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChildToEditActionPerformed
        // TODO add your handling code here:
        
        switch(cboChildToEdit.getSelectedIndex()){
            case 1 -> {
                txtChildShowName.setEnabled(true);
                generalChild.setUserName("");
            }
            case 2 -> {
                txtChildShowBlood.setEnabled(true);
                generalChild.setBloodType("");
            }
            case 3 -> {
                txtChildShowRegion.setEnabled(true);
                generalChild.setRegion("");
            }
            default -> {
                enabling(true);
            }
        }
        
        
        if(cboChildToEdit.getSelectedIndex() != 0){
            btnChildDetailEdit.setVisible(false);
            btnChildDetailSave.setVisible(true);
            btnChildDetailSave.setEnabled(true);
        }
        
        cboChildToEdit.setVisible(false);
    }//GEN-LAST:event_cboChildToEditActionPerformed

    private void cboAppDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppDoctorActionPerformed
        // TODO add your handling code here:
        
        String selectedDoctor = cboAppDoctor.getSelectedItem().toString();
        int selectedDoctorInt = cboAppDoctor.getSelectedIndex();
        ArrayList<String[]> list = File_Control.readFile("doctorData.txt",true);
        
        
        removeCbo(cboAppYear);
        
        
        
        if (selectedDoctorInt != 0){
            cboAppYear.setEnabled(true);
           for (String[] UserList : list){
                if (UserList[1].equals(selectedDoctor)){
                    appMaking = new Appointment();
                    appMaking.setDoctorID(UserList[0]);
                    cboAppYear.addItem(Integer.toString(todayYear));
                    cboAppYear.addItem(Integer.toString(todayYear+1));
                }
            }
        }else{
            enablingAppointment(false);
            cboAppDoctor.setEnabled(true);
            
        }
    }//GEN-LAST:event_cboAppDoctorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String newChildName = txtChildName.getText().strip();
        String newChildIC = txtChildIC.getText().strip();
        int newGenInt = cboChildRegGender.getSelectedIndex();
        int newYearInt = cboChildRegYear.getSelectedIndex();
        
        String newChildGender = cboChildRegGender.getSelectedItem().toString();
        String newChildAge = cboChildRegYear.getSelectedItem().toString() + ",0";
        String newChildRegion = txtChildRegReg.getText().strip();
        String newChildBlood = txtChildRegBlood.getText().strip();
        
        if(newGenInt != 0){
            if(newYearInt != 0){
                if(Validation.icNumber(newChildIC)== true){
                    Child RegNewChild = new Child(Utility_Methods.autoGenerateID("childData.txt","CH"), Main.getCurrentUserID(),
                                                  newChildName, newChildIC, newChildGender, newChildRegion,
                                                  newChildAge, newChildBlood);
                    RegNewChild.setAlergic("-");
                    RegNewChild.setLifeStyle("-,-,-,-");
                    File_Control.addData("childData.txt", RegNewChild.makeitString());

                    hiding();

                    PnlChild.setVisible(true);
                    PnlRegisterChild.setVisible(false);

                    enabling(true);
                    cboChildToDo.setEnabled(false);
                    lblChildInvalidIC.setVisible(false);
                    lblChildRegWarning.setVisible(false);
                    PnlChildReminder.setVisible(true);
                    
                    if(cboChildList.getItemCount() > 1){
                        removeCbo(cboChildList);
                    }
                    ArrayList<String[]> currentChildList = everyChild.getIndividual(Main.getCurrentUserID());
                    for(String[] child : currentChildList){
                        cboChildList.addItem(child[0] + " "+ child[2]);
                    }
                }else{
                    lblChildInvalidIC.setVisible(true);
                }
            }else{
                lblChildRegWarning.setText("Please Select a Year");
                lblChildRegWarning.setVisible(true);
            }
        }
        
        else{
            lblChildRegWarning.setText("Please Select a Gender");
            lblChildRegWarning.setVisible(true);
        }
        
        
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCancelEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelEditActionPerformed
        // TODO add your handling code here:
        enablingAllProfileOposite(false);
        
        
        
        

        
        txtProfID.setText(currentPatient.getUserID());
        txtProfName.setText(currentPatient.getUserName());
        txtProfIC.setText(currentPatient.getIcNumber());
        txtProfAge.setText(currentPatient.getAge());
        txtProfGender.setText(currentPatient.getGender());
        txtProfRegion.setText(currentPatient.getRegion());
        txtProfContact.setText(currentPatient.getContactNumber());
        txtProfEmail.setText(currentPatient.getEmail());
        txtProfRegion.setText(currentPatient.getRegion());
        txtProfRace.setText(currentPatient.getRace());
        txtProfBlood.setText(currentPatient.getBloodType());
        
    }//GEN-LAST:event_btnCancelEditActionPerformed

    private void cboAppYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppYearActionPerformed
        // TODO add your handling code here:
        String selectedYear = "";
        int selectedYearInteger = 0;
        int selectedYearIndex = cboAppYear.getSelectedIndex();
        int todayMonthIndex = 0;
        choosingYear = true;
        
        removeCbo(cboAppMonth);
        for(String Month : Appointment.getAllMonths()){
            cboAppMonth.addItem(Month);
        }
        if (selectedYearIndex != 0){
            selectedYear = cboAppYear.getSelectedItem().toString();
            selectedYearInteger = Integer.parseInt(selectedYear);
            
            for(int i = 0;i < cboAppMonth.getItemCount();i++){
                if(cboAppMonth.getItemAt(i).equals(todayMonth)){
                todayMonthIndex = i;
                break;
                }
            }
            
            if(selectedYearInteger == todayYear)
                for(int j = todayMonthIndex-1; j > 0;j--){
                         cboAppMonth.removeItemAt(j);
                     }
            else{
                removeCbo(cboAppMonth);
            }
           
           cboAppMonth.setEnabled(true);
           choosingYear = false;
           
            
        }else{
            enablingAppointment(false);
            cboAppDoctor.setEnabled(true);
            cboAppYear.setEnabled(true);

        }
    }//GEN-LAST:event_cboAppYearActionPerformed

    private void cboAppMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppMonthActionPerformed
        // TODO add your handling code here:
        if(choosingYear)return;
        String selectedYear = "";
        String selectedMonth = "";
 
        int selectedMonthIndex = cboAppMonth.getSelectedIndex();
        int todayMontInt = Appointment.getMonthinInt(todayMonth);
        int yearinInt = 0;
        
        int availableDay = 31;
        choosingMonth = true;
        
        
        
        if (selectedMonthIndex != 0){
            selectedYear = cboAppYear.getSelectedItem().toString();
            selectedMonth = cboAppMonth.getSelectedItem().toString();

            yearinInt = Integer.parseInt(selectedYear);
            availableDay = YearMonth.of(yearinInt, Appointment.getMonthinInt(selectedMonth)).lengthOfMonth();

            cboAppDay.setEnabled(true);
            removeCbo(cboAppDay);
            for(int j = 1; j <= availableDay; j++){
            cboAppDay.addItem(Integer.toString(j));
            }
            if (selectedMonth.equals(todayMonth)){
                for(int k = todayDay; k > 0;k--){
                    cboAppDay.removeItemAt(k);
                }
            }

            cboAppDay.setEnabled(true);
            choosingMonth = false;
        }else{
            enablingAppointment(false);
            cboAppDoctor.setEnabled(true);
            cboAppYear.setEnabled(true);
            cboAppMonth.setEnabled(true);
        }
        
    }//GEN-LAST:event_cboAppMonthActionPerformed

    private void cboAppDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppDayActionPerformed
        // TODO add your handling code here:
        if(choosingYear)return;
        if(choosingMonth)return;
        String selectedYear = "";
        String selectedMonth = "";
        String selectedDay = "";
        
        int selectedDayIndex = cboAppDay.getSelectedIndex();
        choosingDay = true;
        if (selectedDayIndex != 0){
            
            selectedYear = cboAppYear.getSelectedItem().toString();
            selectedMonth = cboAppMonth.getSelectedItem().toString();
            selectedDay = cboAppDay.getSelectedItem().toString();
            
            LocalDate selectedDate = LocalDate.of(Integer.parseInt(selectedYear),
                                                  Appointment.getMonthinInt(selectedMonth),
                                                  Integer.parseInt(selectedDay));
            
            String dateinString = selectedDate.toString();
            
            appMaking.setAppointmentDate(dateinString);
            
            cboAppTime.setEnabled(true);
            removeCbo(cboAppTime);
            
            for(String time: Appointment.getTimeItem(Main.getCurrentUserID(),
                                                    appMaking.getDoctorID(), dateinString))
            {
                
                cboAppTime.addItem(time);
            }
            
            String selectedDOF = selectedDate.getDayOfWeek().toString();
            
            appMaking.setAppointmentDay(selectedDOF);
            
            choosingDay = false;
            cboAppTime.setEnabled(true);
        }else{
            enablingAppointment(false);
            cboAppDoctor.setEnabled(true);
            cboAppYear.setEnabled(true);
            cboAppMonth.setEnabled(true);
            cboAppDay.setEnabled(true);
        }
        
    }//GEN-LAST:event_cboAppDayActionPerformed

    private void cboAppTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppTimeActionPerformed
        // TODO add your handling code here:
        if(choosingYear)return;
        if(choosingMonth)return;
        if(choosingDay)return;
        
        String selectedTime = "";
        int selectedTimeIndex = cboAppTime.getSelectedIndex();
        
        if(selectedTimeIndex != 0){
            selectedTime = cboAppTime.getSelectedItem().toString();
            appMaking.setAppointmentTime(selectedTime);
            
            rbnAppself.setEnabled(true);
            rbnAppChild.setEnabled(true);
        }else{
            enablingAppointment(false);
            cboAppDoctor.setEnabled(true);
            cboAppYear.setEnabled(true);
            cboAppMonth.setEnabled(true);
            cboAppDay.setEnabled(true);
            cboAppTime.setEnabled(true);
        }
    }//GEN-LAST:event_cboAppTimeActionPerformed

    private void rbnAppselfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnAppselfActionPerformed
        // TODO add your handling code here:
        if(rbnAppself.isSelected()){
            appMaking.setPatientID(Main.getCurrentUserID());
            lblAppChild.setVisible(false);
            lblAppChild.setVisible(false);
            lblAppChild.setEnabled(false);
            lblAppChild.setEnabled(false);
            cboAppChild.setVisible(false);
            cboAppChild.setEnabled(false);
            btnRequestApp.setEnabled(true);
        }
    }//GEN-LAST:event_rbnAppselfActionPerformed

    private void rbnAppChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnAppChildActionPerformed
        // TODO add your handling code here:
        if(rbnAppChild.isSelected()){
            btnRequestApp.setEnabled(false);
            lblAppChild.setVisible(true);
            lblAppChild.setVisible(true);
            lblAppChild.setEnabled(true);
            lblAppChild.setEnabled(true);
            cboAppChild.setVisible(true);
            
            if(cboAppChild.getItemCount() > 1){
            removeCbo(cboAppChild);
            }
            ArrayList<String[]> currentChildList = everyChild.getIndividual(Main.getCurrentUserID());
            for(String[] child : currentChildList){
                cboAppChild.addItem(child[0] + " "+ child[2]);
            }
            cboAppChild.setEnabled(true);
        }
    }//GEN-LAST:event_rbnAppChildActionPerformed

    private void cboAppChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppChildActionPerformed
        // TODO add your handling code here:
        String selectedChild = cboAppChild.getSelectedItem().toString();
        String selectedChildID = "";
        int selectedChildIndex = cboAppChild.getSelectedIndex();
        
        if(selectedChildIndex != 0 ){
            for (int j = 0;j < 5;j++){
                    selectedChildID += selectedChild.charAt(j);
                }

            appMaking.setPatientID(selectedChildID);
            btnRequestApp.setEnabled(true);
        }else{
            btnRequestApp.setEnabled(false);
        }
        
    }//GEN-LAST:event_cboAppChildActionPerformed
 
    private void btnProfNameEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfNameEditActionPerformed
        // TODO add your handling code here:
        profileEdit(btnProfNameSave,btnProfNameEdit,txtProfName);
    }//GEN-LAST:event_btnProfNameEditActionPerformed

    private void btnProfNameSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfNameSaveActionPerformed
        // TODO add your handling code here:
        profileSave(btnProfNameSave,btnProfNameEdit,txtProfName,1);
        currentPatient.setUserName(txtProfName.getText());
    }//GEN-LAST:event_btnProfNameSaveActionPerformed

    private void btnProfConEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfConEditActionPerformed
        // TODO add your handling code here:
        profileEdit(btnProfConSave,btnProfConEdit,txtProfContact);
    }//GEN-LAST:event_btnProfConEditActionPerformed

    private void btnProfConSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfConSaveActionPerformed
        // TODO add your handling code here:
        if(Validation.contactNumber(txtProfContact.getText().strip())){
            profileSave(btnProfConSave,btnProfConEdit,txtProfContact,6);
            currentPatient.setContactNumber(txtProfContact.getText());
            
        }else{
            setWarningText("Invalid Contact","10 Digit");
        }
    }//GEN-LAST:event_btnProfConSaveActionPerformed

    private void btnProfEmailEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfEmailEditActionPerformed
        // TODO add your handling code here:
        profileEdit(btnProfEmailSave,btnProfEmailEdit,txtProfEmail);
    }//GEN-LAST:event_btnProfEmailEditActionPerformed

    private void btnProfEmailSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfEmailSaveActionPerformed
        // TODO add your handling code here:
        if(Validation.email(txtProfEmail.getText().strip())){
            profileSave(btnProfEmailSave,btnProfEmailEdit,txtProfEmail,7);
            currentPatient.setEmail(txtProfEmail.getText());
        }else{
            
            setWarningText("Invalid Email","xxx@gmail.com");
        }
    }//GEN-LAST:event_btnProfEmailSaveActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Guest_Main_Page guestFrame = new Guest_Main_Page();
        guestFrame.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnCloseSideBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseSideBarActionPerformed
        // TODO add your handling code here:
        PnlDetailBar.setVisible(false);
        btnProfile.setVisible(true);
        enabling(true);
    }//GEN-LAST:event_btnCloseSideBarActionPerformed

    private void btnProfBloodEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfBloodEditActionPerformed
        // TODO add your handling code here:
        profileEdit(btnProfBloodSave,btnProfBloodEdit,txtProfBlood);
    }//GEN-LAST:event_btnProfBloodEditActionPerformed

    private void btnProfBloodSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfBloodSaveActionPerformed
        // TODO add your handling code here:
        boolean isValidBlood = false;
        String[] bloodTypeList = {"A","B","AB","O"};
        for(String bloodType: bloodTypeList){
            if(txtProfBlood.getText().strip().equals(bloodType)){
                isValidBlood = true;
                break;
            }
        }
        
        if(isValidBlood){
            profileSave(btnProfBloodSave,btnProfBloodEdit,txtProfBlood,9);
            enabling(true);
            enablingAllProfileOposite(false);
        }else{
            PnlWarningProfile.setVisible(true);
            setWarningText("Invalid Blood Type","A,B,AB,O");
            enablingAllProfile(false);
        }
    }//GEN-LAST:event_btnProfBloodSaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login_Frame initialFrame = new Login_Frame();
        initialFrame.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboAppShowFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppShowFilterActionPerformed
        // TODO add your handling code here:
        int selectedIndex = cboAppShowFilter.getSelectedIndex();
        choosingApp = true;
        
        String toShow = switch(selectedIndex){
            case 1 -> "adult";
            case 2 -> "child";
            case 3 -> "all";
            default -> "";
                
        };
        
        if (selectedIndex != 0){
            removeCbo(cboAppShowChoose);
                
                for(String appointment : Appointment.getAllAppointment(Main.getCurrentUserID(), toShow, isFilteringApp)){
                    
                    cboAppShowChoose.addItem(appointment);
                }
                
                cboAppShowChoose.setEnabled(true);
                PnlShowingApp.setVisible(true);
                PnlAppReminder.setVisible(false);
        }else{
            cboAppShowChoose.setEnabled(false);
            {
                removeCbo(cboAppShowChoose);
        }
            PnlShowingApp.setVisible(false);
            PnlAppReminder.setVisible(true);
            cboAppShowChoose.setEnabled(false);
        }
        
        choosingApp = false;
    }//GEN-LAST:event_cboAppShowFilterActionPerformed

    private void chkAppFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAppFilterActionPerformed
        // TODO add your handling code here:
        
        
        if(chkAppFilter.isSelected()){
            isFilteringApp = false;
        }else{
            isFilteringApp = true;
        }
        
        
        cboAppShowFilter.setSelectedIndex(cboAppShowFilter.getSelectedIndex());
        cboAppShowChoose.setSelectedIndex(0);
        
        
    }//GEN-LAST:event_chkAppFilterActionPerformed

    private void cboAppShowChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAppShowChooseActionPerformed
        // TODO add your handling code here:

        ArrayList<String[]> appointmentList = File_Control.readFile("appointmentData.txt",false);
        ArrayList<String[]> doctorList = File_Control.readFile("doctorData.txt",true);
        ArrayList<String[]> childList = File_Control.readFile("childData.txt",true);
        String[] currentShowingApp = new String[7];
        String appointmentID = "";
        
        
        int selectedIndex = cboAppShowChoose.getSelectedIndex();
        
        if(selectedIndex != 0){
            
            String selectedAppointment = cboAppShowChoose.getSelectedItem().toString();
            
            for (int i = 0;i < 5;i++){
                appointmentID += selectedAppointment.charAt(i);
            }
            
            for(String[] appList : appointmentList){
                if (appList[0].equals(appointmentID)){
                    appShowing = new Appointment(appList[0],appList[1],appList[2],
                                                 appList[3],appList[4],appList[5],appList[6]);
                
                currentShowingApp = appList;
                break;
                }
                
                
            }
            for(String[] doctor: doctorList){
                if(doctor[0].equals(currentShowingApp[2].strip())){
                    lblAppShowDName.setText(doctor[1]);
                    break;
                }
            }
            
            if(appShowing.getPatientID().equals(Main.getCurrentUserID())){
                lblAppShowPName.setText(Main.getCurrentUserName());
            }else{
                for(String[] child: childList){
                if(child[0].equals(currentShowingApp[1])){
                    lblAppShowPName.setText(child[2]);
                    break;
                }
            }
            }
            
            lblAppShowID.setText(appShowing.getAppointmentID());
            lblAppShowDID.setText(currentShowingApp[2]);
            
            lblAppShowPID.setText(currentShowingApp[1]);
            lblAppShowDate.setText(String.format("%s (%s)", currentShowingApp[3], currentShowingApp[4]));
            lblAppShowTime.setText(currentShowingApp[5]);
            lblAppShowStatus.setText(currentShowingApp[6]);
            
            switch(currentShowingApp[6]){
                case "Accepted" -> lblAppShowStatus.setForeground(Color.GREEN);
                case "Rejected" -> lblAppShowStatus.setForeground(Color.RED);
                case "Pending" -> lblAppShowStatus.setForeground(Color.ORANGE);
                
                
            }
            
        }else{
            lblAppShowID.setText("");
            lblAppShowDID.setText("");
            lblAppShowPID.setText("");
            lblAppShowDate.setText("");
            lblAppShowTime.setText("");
            lblAppShowStatus.setText("");
            lblAppShowDName.setText("");
            lblAppShowPName.setText("");
        }
        
        
    }//GEN-LAST:event_cboAppShowChooseActionPerformed

    private void btnAppDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppDeleteActionPerformed
        // TODO add your handling code here:
        String idtoDelete = lblAppShowID.getText().strip();
        Patient.modifyOneValue("appointmentData.txt", idtoDelete, "Deleted", 6);
        cboAppShowChoose.setSelectedIndex(0);
    }//GEN-LAST:event_btnAppDeleteActionPerformed

    private void cboMedRepChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMedRepChooseActionPerformed
        // TODO add your handling code here:
        ArrayList<String[]> medicalRecordList = File_Control.readFile("medicalReport.txt",true);
        int selectedIndex = cboMedRepChoose.getSelectedIndex();
        String[] currentShowingMedRec = new String[13];
        String medicalRecordID = "";
        String commentDoctor = "";
        String testingResult = "";
        
        
        
        if(selectedIndex != 0){
            String selectedMedRep = cboMedRepChoose.getSelectedItem().toString();
            
            for (int i = 0;i < 5;i++){
                medicalRecordID += selectedMedRep.charAt(i);
            }
            
            for(String[] medicalList : medicalRecordList){
                if (medicalList[0].equals(medicalRecordID.strip())){
                    currentShowingMedRec = medicalList;
                    break;
                }
        }
            
        commentDoctor = currentShowingMedRec[6];
        commentDoctor = commentDoctor.replace(",", ",\n");
        commentDoctor = commentDoctor.replace(".", ".\n");
        
        testingResult = currentShowingMedRec[11];
        testingResult = testingResult.replace(",","\n");
        
        lblMedRepID.setText(currentShowingMedRec[0]);
        lblMedRepPID.setText(currentShowingMedRec[1]);
        lblMedRepDID.setText(currentShowingMedRec[7]);
        lblMedRepDiag.setText(currentShowingMedRec[5]);
        lblMedRepDate.setText(currentShowingMedRec[8]);
        txtAreaMedRepDescrip.setText(" " + commentDoctor);
        txtAreaMedRepTesting.setText(testingResult);
        
        PnlMedRecReminder.setVisible(false);
        PnlMedRecShow.setVisible(true);
        }else{
            PnlMedRecReminder.setVisible(true);
            PnlMedRecShow.setVisible(false);
            lblMedRepID.setText("");
            lblMedRepPID.setText("");
            lblMedRepDID.setText("");
            lblMedRepDiag.setText("");
            lblMedRepDate.setText("");
            txtAreaMedRepDescrip.setText("");
            txtAreaMedRepTesting.setText("");
        }
    }//GEN-LAST:event_cboMedRepChooseActionPerformed

    private void cboPreMedChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPreMedChooseActionPerformed
        // TODO add your handling code here:
        ArrayList<String[]> PrescriptionList = File_Control.readFile("prescriptionData.txt",true);
        int selectedIndex = cboPreMedChoose.getSelectedIndex();
        String[] currentShowingPrescription =  new String[9];
        String PrescriptionID = "";
        String medicalUsing = "";
        
        
        
        if(selectedIndex != 0){
            String selectedPrescription = cboPreMedChoose.getSelectedItem().toString();
            
            for (int i = 0;i < 5;i++){
                PrescriptionID += selectedPrescription.charAt(i);
            }
            
            for(String[] PreMedList : PrescriptionList){
                if (PreMedList[0].equals(PrescriptionID.strip())){
                    currentShowingPrescription = PreMedList;
                    break;
                }
        }
            
        String seperator = "\n" + "-".repeat(30) + "\n";
            
        medicalUsing = currentShowingPrescription[2];
        medicalUsing= medicalUsing.replace(",", ",\n");
        medicalUsing = medicalUsing.replace(".", ".\n");
        medicalUsing = medicalUsing.replace("/", seperator);
        
        lblPreMedID.setText(currentShowingPrescription[0]);
        lblPreMedMID.setText(currentShowingPrescription[1]);
        lblPreMedDate1.setText(currentShowingPrescription[3]);
        lblPreMedDate2.setText(currentShowingPrescription[5]);
        txtAreaPreMedMedical.setText(medicalUsing);
        
        PnlPreMedReminder.setVisible(false);
        PnlPreMedShowing.setVisible(true);
        }else{
            PnlPreMedReminder.setVisible(true);
            PnlPreMedShowing.setVisible(false);
            lblPreMedID.setText("");
            lblPreMedMID.setText("");
            lblPreMedDate1.setText("");
            lblPreMedDate2.setText("");
            txtAreaPreMedMedical.setText("");
            
        }
    }//GEN-LAST:event_cboPreMedChooseActionPerformed

    private void cboPaymentActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPaymentActionActionPerformed
        // TODO add your handling code here:
        int selectedAction = cboPaymentAction.getSelectedIndex();
        
        switch(selectedAction){
            case 0 ->{
                PnlPaymentReminder.setVisible(true);
                PnlPaymentMake.setVisible(false);
                PnlPaymentReceipt.setVisible(false);
                cboPaymentChoose.setVisible(false);
            }
            case 1 ->{
                PnlPaymentMake.setVisible(true);
                PnlPaymentReceipt.setVisible(false);
                PnlPaymentReminder.setVisible(false);
                
                paymentMaking = new Payment();
                
                lm.removeAllElements();
                lblPaymentShowTotal.setText("");
                
                for(String item: paymentMaking.getToPay(Main.getCurrentUserID())){
                    lm.addElement(item);
                }
                
                for(String childID: generalChild.getAllChildID(todayMonth)){
                    for(String item: paymentMaking.getToPay(childID)){
                        lm.addElement(item);
                }
                }
                
                lstPaymentToMake.setModel(lm);
                cboPaymentChoose.setVisible(false);
                
            }
            case 2 ->{
                PnlPaymentReceipt.setVisible(false);
                PnlPaymentReminder.setVisible(true);
                
                cboPaymentChoose.setVisible(true);
                
                removeCbo(cboPaymentChoose);
                
                
                
                for(String receipt: paymentShowing.getPayed(Main.getCurrentUserID())){
                    
                    cboPaymentChoose.addItem(receipt);
                }
            }
            default ->{
                PnlPaymentReminder.setVisible(true);
                PnlPaymentMake.setVisible(false);
                PnlPaymentReceipt.setVisible(false);
                cboPaymentChoose.setVisible(false);
            }
        }
    }//GEN-LAST:event_cboPaymentActionActionPerformed

    private void btnPaymentTotalUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentTotalUpActionPerformed
        // TODO add your handling code here:
        
        String total;
        
        if(!lstPaymentToMake.isSelectionEmpty()){
            
            ArrayList<String> selectedItemToPay = new ArrayList<>(lstPaymentToMake.getSelectedValuesList());
                
            paymentMaking.setPaymentToMake(selectedItemToPay);
            
            
            total = "RM" + paymentMaking.totalUp();
            
            lblPaymentShowTotal.setText(total);
            btnPaymentMade.setEnabled(true);
        }
    }//GEN-LAST:event_btnPaymentTotalUpActionPerformed

    private void btnPaymentMadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentMadeActionPerformed
        // TODO add your handling code here:
        if(!lstPaymentToMake.isSelectionEmpty()){
            if(cboPaymentMethod.getSelectedIndex() != 0){
                paymentMaking.setPatientID(Main.getCurrentUserID());
                paymentMaking.setMethod(cboPaymentMethod.getSelectedItem().toString());
                
                paymentMaking.changeStatus();
                paymentMaking.generateReceipt();
                
                for(String selected: paymentMaking.getPaymentToMake()){
                    lm.removeElement(selected);
                }
                lblPaymentShowTotal.setText("");
            }else{
                lblPaymentWarningcbo.setVisible(true);
            }
        }else{
            lblPaymentWarninglst.setVisible(true);
        }
    }//GEN-LAST:event_btnPaymentMadeActionPerformed

    private void cboPaymentChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPaymentChooseActionPerformed
        // TODO add your handling code here:
        ArrayList<String[]> paymentList = File_Control.readFile("paymentData.txt",true);
        int selectedIndex = cboPaymentChoose.getSelectedIndex();
        String[] showingPayment =  new String[9];
        String ReceiptID = "";
        
        
        
        if(selectedIndex != 0){
            String selectedReceipt = cboPaymentChoose.getSelectedItem().toString();
            
            for (int i = 0;i < 5;i++){
                ReceiptID += selectedReceipt.charAt(i);
            }
            
            for(String[] payment : paymentList){
                if (payment[0].equals(ReceiptID.strip())){
                    showingPayment = payment;
                    break;
                }
        }
            
            paymentShowing = new Payment(showingPayment[0],showingPayment[1],showingPayment[2],
                                         showingPayment[3],showingPayment[4],showingPayment[5]);

            lblPaymentShowID.setText(paymentShowing.getReceiptID());
            lblPaymentShowMPID.setText(paymentShowing.getIdPaying());
            lblPaymentShowAmount.setText(paymentShowing.getAmount());
            lblPaymentShowMethod.setText(paymentShowing.getMethod());
            lblPaymentShowDate.setText(paymentShowing.getDate());

            PnlPaymentReminder.setVisible(false);
            PnlPaymentReceipt.setVisible(true);
            PnlPaymentMake.setVisible(false);
        
        }else{
            PnlPaymentReminder.setVisible(true);
            PnlPaymentReceipt.setVisible(false);
            lblPaymentShowID.setText(paymentShowing.getReceiptID());
            lblPaymentShowMPID.setText(paymentShowing.getIdPaying());
            lblPaymentShowAmount.setText(paymentShowing.getAmount());
            lblPaymentShowMethod.setText(paymentShowing.getMethod());
            lblPaymentShowDate.setText(paymentShowing.getDate());
            
        }
    }//GEN-LAST:event_cboPaymentChooseActionPerformed

    private void btnProfPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfPassActionPerformed
        // TODO add your handling code here:
        PnlEditPassword.setVisible(true);
        enablingAllProfile(false);
        currentPatient.readAllData();
        enabling(false);
    }//GEN-LAST:event_btnProfPassActionPerformed

    private void btnConfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmPasswordActionPerformed
        // TODO add your handling code here:
        if(txtPasswordOld.getText().equals(currentPatient.userPassword)){
            if(Validation.password(txtPasswordNew.getText())){
                currentPatient.setUserPassword(txtPasswordNew.getText());
                currentPatient.updatePassword();
                lblWrongPass.setVisible(false);
                lblValidationPass1.setVisible(false);
                lblValidationPass2.setVisible(false);
                PnlEditPassword.setVisible(false);
                
                enablingAllProfile(true);
                enablingProfile(false);
                enabling(true);
            }else{
                lblValidationPass1.setVisible(true);
                lblValidationPass2.setVisible(true);
                lblWrongPass.setVisible(false);
            }
        }else{
            lblWrongPass.setVisible(true);
            lblValidationPass1.setVisible(false);
            lblValidationPass2.setVisible(false);
        }
    }//GEN-LAST:event_btnConfirmPasswordActionPerformed

    private void btnCancelPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelPasswordActionPerformed
        // TODO add your handling code here:
        lblWrongPass.setVisible(false);
        PnlEditPassword.setVisible(false);
        lblValidationPass1.setVisible(false);
        lblValidationPass2.setVisible(false);
        enablingAllProfile(true);
        enablingProfile(false);
        enabling(true);
    }//GEN-LAST:event_btnCancelPasswordActionPerformed

    private void btnProfSafeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfSafeActionPerformed
        // TODO add your handling code here:
        currentPatient.readAllData();
        enabling(false);
        PnlEditSafetyQ.setVisible(true);
        
        txtSafetyQ1.setEnabled(false);
        txtSafetyQ2.setEnabled(false);
        
        enablingAllProfile(false);
        lblSafetyQ1.setText(currentPatient.questionInString(currentPatient.getSafetyQuestionID1()));
        txtSafetyQ1.setText(currentPatient.getSafetyAnswer1());
        
        lblSafetyQ2.setText(currentPatient.questionInString(currentPatient.getSafetyQuestionID2()));
        txtSafetyQ2.setText(currentPatient.getSafetyAnswer2());
    }//GEN-LAST:event_btnProfSafeActionPerformed

    private void btnSafetyQEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafetyQEdit1ActionPerformed
        // TODO add your handling code here:
        cboSafetyQ1.setVisible(true);
        cboSafetyQ1.setEnabled(true);
        lblSafetyQ1.setVisible(false);
        btnSafetyQCancel.setVisible(true);
        btnSafetyQSave.setVisible(true);
        btnSafetyQBack.setVisible(false);
        
        btnSafetyQEdit2.setVisible(false);
        btnSafetyQEdit2.setEnabled(false);
        btnSafetyQEdit1.setEnabled(false);
        
        txtSafetyQ1.setEnabled(true);
        
        removeCbo(cboSafetyQ1);
                
        for(String question1 : currentPatient.filterQuestion(currentPatient.getSafetyQuestionID2())){
                    
                cboSafetyQ1.addItem(question1);
            }
    }//GEN-LAST:event_btnSafetyQEdit1ActionPerformed

    private void btnSafetyQCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafetyQCancelActionPerformed
        // TODO add your handling code here:
        setupSafetyQ();
    }//GEN-LAST:event_btnSafetyQCancelActionPerformed

    private void btnSafetyQBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafetyQBackActionPerformed
        // TODO add your handling code here:
        PnlEditSafetyQ.setVisible(false);
        enablingAllProfile(true);
        enablingProfile(false);
        enabling(true);
    }//GEN-LAST:event_btnSafetyQBackActionPerformed

    private void btnSafetyQSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafetyQSaveActionPerformed
        // TODO add your handling code here:
        
        
        if(cboSafetyQ1.isVisible()){
            
            if(cboSafetyQ1.getSelectedIndex() != 0 && !txtSafetyQ1.getText().strip().isEmpty()){
            
                currentPatient.setSafetyQuestionID1(
                                     currentPatient.questionInID(
                                            cboSafetyQ1.getSelectedItem().toString().strip()
                                                                                )
                                                    );

                currentPatient.setSafetyAnswer1(txtSafetyQ1.getText().strip());
                txtSafetyQ1.setEnabled(false);

                cboSafetyQ1.setVisible(false);
                cboSafetyQ1.setEnabled(false);
                lblSafetyQ1.setVisible(true);
                lblSafetyQ1.setText(currentPatient.questionInString(currentPatient.getSafetyQuestionID1()));
                txtSafetyQ1.setText(currentPatient.getSafetyAnswer1());

                btnSafetyQCancel.setVisible(false);
                btnSafetyQSave.setVisible(false);
                btnSafetyQBack.setVisible(true);

                btnSafetyQEdit2.setVisible(true);
                btnSafetyQEdit2.setEnabled(true);
                currentPatient.updateSafetyQuestion();
                setupSafetyQ();
            }
            
        }else if(cboSafetyQ2.isVisible()){
            if(cboSafetyQ2.getSelectedIndex() != 0 && !txtSafetyQ2.getText().strip().isEmpty()){
            
                currentPatient.setSafetyQuestionID2(
                                     currentPatient.questionInID(
                                            cboSafetyQ2.getSelectedItem().toString().strip()
                                                                                )
                                                    );

                currentPatient.setSafetyAnswer2(txtSafetyQ2.getText().strip());
                txtSafetyQ2.setEnabled(false);

                cboSafetyQ2.setVisible(false);
                cboSafetyQ2.setEnabled(false);
                lblSafetyQ2.setVisible(true);
                lblSafetyQ2.setText(currentPatient.questionInString(currentPatient.getSafetyQuestionID1()));
                txtSafetyQ2.setText(currentPatient.getSafetyAnswer1());

                btnSafetyQCancel.setVisible(false);
                btnSafetyQSave.setVisible(false);
                btnSafetyQBack.setVisible(true);

                btnSafetyQEdit1.setVisible(true);
                btnSafetyQEdit1.setEnabled(true);
                setupSafetyQ();
                currentPatient.updateSafetyQuestion();
            }
        }
    }//GEN-LAST:event_btnSafetyQSaveActionPerformed

    private void btnSafetyQEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafetyQEdit2ActionPerformed
        // TODO add your handling code here:
        cboSafetyQ2.setVisible(true);
        cboSafetyQ2.setEnabled(true);
        lblSafetyQ2.setVisible(false);
        btnSafetyQCancel.setVisible(true);
        btnSafetyQSave.setVisible(true);
        btnSafetyQBack.setVisible(false);
        
        btnSafetyQEdit1.setVisible(false);
        btnSafetyQEdit1.setEnabled(false);
        btnSafetyQEdit2.setEnabled(false);
        
        txtSafetyQ2.setEnabled(true);
        
        removeCbo(cboSafetyQ2);
                
        for(String question2 : currentPatient.filterQuestion(currentPatient.getSafetyQuestionID1())){
                    
                cboSafetyQ2.addItem(question2);
            }
    }//GEN-LAST:event_btnSafetyQEdit2ActionPerformed

    private void cboMedRepChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMedRepChoose1ActionPerformed
        // TODO add your handling code here:
         ArrayList<String[]> medicalRecordList = File_Control.readFile("medicalReport.txt",true);
        int selectedIndex = cboMedRepChoose1.getSelectedIndex();
        String[] currentShowingMedRec = new String[13];
        String medicalRecordID = "";
        String commentDoctor = "";
        String testingResult = "";
        
        
        
        if(selectedIndex != 0){
            String selectedMedRep = cboMedRepChoose1.getSelectedItem().toString();
            
            for (int i = 0;i < 5;i++){
                medicalRecordID += selectedMedRep.charAt(i);
            }
            
            for(String[] medicalList : medicalRecordList){
                if (medicalList[0].equals(medicalRecordID.strip())){
                    currentShowingMedRec = medicalList;
                    break;
                }
        }
            
        commentDoctor = currentShowingMedRec[6];
        commentDoctor = commentDoctor.replace(",", ",\n");
        commentDoctor = commentDoctor.replace(".", ".\n");
        
        testingResult = currentShowingMedRec[11];
        testingResult = testingResult.replace(",","\n");
        
        lblMedRepID1.setText(currentShowingMedRec[0]);
        lblMedRepPID1.setText(currentShowingMedRec[1]);
        lblMedRepDID1.setText(currentShowingMedRec[7]);
        lblMedRepDiag1.setText(currentShowingMedRec[5]);
        lblMedRepDate1.setText(currentShowingMedRec[8]);
        txtAreaMedRepDescrip1.setText(" " + commentDoctor);
        txtAreaMedRepTesting1.setText(testingResult);
        
        }else{
            
            lblMedRepID1.setText("");
            lblMedRepPID1.setText("");
            lblMedRepDID1.setText("");
            lblMedRepDiag1.setText("");
            lblMedRepDate1.setText("");
            txtAreaMedRepDescrip1.setText("");
            txtAreaMedRepTesting1.setText("");
        }
    }//GEN-LAST:event_cboMedRepChoose1ActionPerformed

    private void cboPreMedChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPreMedChoose1ActionPerformed
        // TODO add your handling code here:
        ArrayList<String[]> PrescriptionList = File_Control.readFile("prescriptionData.txt",true);
        int selectedIndex = cboPreMedChoose1.getSelectedIndex();
        String[] currentShowingPrescription =  new String[9];
        String PrescriptionID = "";
        String medicalUsing = "";
        
        
        
        if(selectedIndex != 0){
            String selectedPrescription = cboPreMedChoose1.getSelectedItem().toString();
            
            for (int i = 0;i < 5;i++){
                PrescriptionID += selectedPrescription.charAt(i);
            }
            
            for(String[] PreMedList : PrescriptionList){
                if (PreMedList[0].equals(PrescriptionID.strip())){
                    currentShowingPrescription = PreMedList;
                    break;
                }
        }
            
        String seperator = "\n" + "-".repeat(30) + "\n";
            
        medicalUsing = currentShowingPrescription[2];
        medicalUsing= medicalUsing.replace(",", ",\n");
        medicalUsing = medicalUsing.replace(".", ".\n");
        medicalUsing = medicalUsing.replace("/", seperator);
        
        lblPreMedID1.setText(currentShowingPrescription[0]);
        lblPreMedMID1.setText(currentShowingPrescription[1]);
        lblPreMedDate3.setText(currentShowingPrescription[3]);
        lblPreMedDate4.setText(currentShowingPrescription[5]);
        txtAreaPreMedMedical1.setText(medicalUsing);
        
        
        }else{
            
            lblPreMedID1.setText("");
            lblPreMedMID1.setText("");
            lblPreMedDate3.setText("");
            lblPreMedDate4.setText("");
            txtAreaPreMedMedical1.setText("");
            
        }
    }//GEN-LAST:event_cboPreMedChoose1ActionPerformed

    private void btnDeleteAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAccActionPerformed
        // TODO add your handling code here:
        Patient.modifyOneValue("patientData.txt", Main.getCurrentUserID(), "0", 12);
        Patient.modifyOneValue("loginData.txt", Main.getCurrentUserID(), "0", 8);

        Login_Frame initialFrame = new Login_Frame();
        initialFrame.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnDeleteAccActionPerformed

    private void btnCancelDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelDeleteActionPerformed
        // TODO add your handling code here:
        PnlSettings.setVisible(false);
        enabling(true);
    }//GEN-LAST:event_btnCancelDeleteActionPerformed

    private void cboVaccChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVaccChooseActionPerformed
        // TODO add your handling code here:
        ArrayList<String[]> VaccinationList = File_Control.readFile("vaccinationData.txt",true);
        int selectedIndex = cboVaccChoose.getSelectedIndex();
        String[] currentShowingVaccine =  new String[9];
        String VaccineID = "";
        
        
        
        if(selectedIndex != 0){
            String selectedVaccine = cboVaccChoose.getSelectedItem().toString();
            
            for (int i = 0;i < 5;i++){
                VaccineID += selectedVaccine.charAt(i);
            }
            
            for(String[] VaccineList : VaccinationList){
                if (VaccineList[0].equals(VaccineID.strip())){
                    currentShowingVaccine = VaccineList;
                    break;
                }
            }


            lblVaccID.setText(currentShowingVaccine[0]);
            lblVaccPID.setText(currentShowingVaccine[1]);
            lblVaccType.setText(currentShowingVaccine[2]);
            lblVaccDate.setText(currentShowingVaccine[3]);
            txtAreaVaccDesc.setText(currentShowingVaccine[5]);
            
            PnlVaccineReminder.setVisible(false);
            PnlVaccShow.setVisible(true);
        
        }else{
            PnlVaccineReminder.setVisible(true);
            PnlVaccShow.setVisible(false);
            
            lblVaccID.setText("");
            lblVaccPID.setText("");
            lblVaccType.setText("");
            lblVaccDate.setText("");
            txtAreaVaccDesc.setText("");
        }
    }//GEN-LAST:event_cboVaccChooseActionPerformed

    private void cboVaccChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVaccChoose1ActionPerformed
        // TODO add your handling code here:
        ArrayList<String[]> VaccinationList = File_Control.readFile("vaccinationData.txt",true);
        int selectedIndex = cboVaccChoose1.getSelectedIndex();
        String[] currentShowingVaccine =  new String[9];
        String VaccineID = "";
        
        
        
        if(selectedIndex != 0){
            String selectedVaccine = cboVaccChoose1.getSelectedItem().toString();
            
            for (int i = 0;i < 5;i++){
                VaccineID += selectedVaccine.charAt(i);
            }
            
            for(String[] VaccineList : VaccinationList){
                if (VaccineList[0].equals(VaccineID.strip())){
                    currentShowingVaccine = VaccineList;
                    break;
                }
            }


            lblVaccID.setText(currentShowingVaccine[0]);
            lblVaccPID.setText(currentShowingVaccine[1]);
            lblVaccType.setText(currentShowingVaccine[2]);
            lblVaccDate.setText(currentShowingVaccine[3]);
            txtAreaVaccDesc.setText(currentShowingVaccine[5]);
            
            PnlVaccineReminder.setVisible(false);
            PnlVaccShow.setVisible(true);
        
        }else{
            PnlVaccineReminder.setVisible(true);
            PnlVaccShow.setVisible(false);
            
            lblVaccID.setText("");
            lblVaccPID.setText("");
            lblVaccType.setText("");
            lblVaccDate.setText("");
            txtAreaVaccDesc.setText("");
        }
    }//GEN-LAST:event_cboVaccChoose1ActionPerformed

    private void btnProfRegionEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfRegionEditActionPerformed
        // TODO add your handling code here:
        profileEdit(btnProfRegionSave,btnProfRegionEdit,txtProfRegion);
    }//GEN-LAST:event_btnProfRegionEditActionPerformed

    private void btnProfRegionSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfRegionSaveActionPerformed
        // TODO add your handling code here:
        if(Validation.string(txtProfRegion.getText().strip())){
            profileSave(btnProfRegionSave,btnProfRegionEdit,txtProfRegion,5);
            currentPatient.setRegion(txtProfRegion.getText());
        }else{
            setWarningText("Invalid Region","Please enter alphabet only");
        }
    }//GEN-LAST:event_btnProfRegionSaveActionPerformed

    private void btnProfRaceEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfRaceEditActionPerformed
        // TODO add your handling code here:
        profileEdit(btnProfRaceSave,btnProfRaceEdit,txtProfRace);
    }//GEN-LAST:event_btnProfRaceEditActionPerformed

    private void btnProfRaceSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfRaceSaveActionPerformed
        // TODO add your handling code here:
        if(Validation.string(txtProfRace.getText().strip())){
            profileSave(btnProfRaceSave,btnProfRaceEdit,txtProfRace,5);
            currentPatient.setRace(txtProfRace.getText());
            enablingAllProfileOposite(false);
        }else{
            setWarningText("Invalid Race","Please enter alphabet only");
        }
    }//GEN-LAST:event_btnProfRaceSaveActionPerformed

    private void btnProfGenderEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfGenderEditActionPerformed
        // TODO add your handling code here:
        
        profileEdit(btnProfGenderSave,btnProfGenderEdit,txtProfGender);
    }//GEN-LAST:event_btnProfGenderEditActionPerformed

    private void btnProfGenderSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfGenderSaveActionPerformed
        // TODO add your handling code here:
        if(txtProfGender.getText().strip().equals("Male")||txtProfGender.getText().strip().equals("Female")){
            profileSave(btnProfBloodSave,btnProfBloodEdit,txtProfBlood,4);
            currentPatient.setGender(txtProfGender.getText());
            enablingAllProfileOposite(false);
        }else{
            setWarningText("Invalid Gender","Male or Female");
        }
    }//GEN-LAST:event_btnProfGenderSaveActionPerformed

    private void btnProfICEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfICEditActionPerformed
        // TODO add your handling code here:
        profileEdit(btnProfICSave,btnProfICEdit,txtProfIC);
    }//GEN-LAST:event_btnProfICEditActionPerformed

    private void btnProfICSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfICSaveActionPerformed
        // TODO add your handling code here:
        
        if(Validation.icNumber(txtProfIC.getText().strip())){
            profileSave(btnProfICSave,btnProfICEdit,txtProfIC,2);
            currentPatient.setIcNumber(txtProfIC.getText());
            enablingAllProfileOposite(false);
        }else{
            setWarningText("Invalid IC","12 Digit");
        }
              
    }//GEN-LAST:event_btnProfICSaveActionPerformed

    private void btnProfileUnderstandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileUnderstandActionPerformed
        // TODO add your handling code here:
        PnlWarningProfile.setVisible(false);
        enablingAllProfileOposite(false);
        
        txtProfID.setText(currentPatient.getUserID());
        txtProfName.setText(currentPatient.getUserName());
        txtProfIC.setText(currentPatient.getIcNumber());
        txtProfAge.setText(currentPatient.getAge());
        txtProfGender.setText(currentPatient.getGender());
        txtProfRegion.setText(currentPatient.getRegion());
        txtProfContact.setText(currentPatient.getContactNumber());
        txtProfEmail.setText(currentPatient.getEmail());
        txtProfRegion.setText(currentPatient.getRegion());
        txtProfRace.setText(currentPatient.getRace());
        txtProfBlood.setText(currentPatient.getBloodType());
        
        btnCancelEdit.setVisible(true);
    }//GEN-LAST:event_btnProfileUnderstandActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Patient_Frame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AppointmentIndividual;
    private javax.swing.ButtonGroup ChildRelation;
    private javax.swing.JPanel PnlAppFirst;
    private javax.swing.JPanel PnlAppReminder;
    private javax.swing.JPanel PnlAppointment;
    private javax.swing.JPanel PnlBar;
    private javax.swing.JPanel PnlBasement;
    private javax.swing.JPanel PnlChild;
    private javax.swing.JPanel PnlChildDetail;
    private javax.swing.JPanel PnlChildMedRecord;
    private javax.swing.JPanel PnlChildPreMed;
    private javax.swing.JPanel PnlChildReminder;
    private javax.swing.JPanel PnlDetailBar;
    private javax.swing.JPanel PnlEditPassword;
    private javax.swing.JPanel PnlEditSafetyQ;
    private javax.swing.JPanel PnlMedRecReminder;
    private javax.swing.JPanel PnlMedRecShow;
    private javax.swing.JPanel PnlMedRecord;
    private javax.swing.JPanel PnlPayment;
    private javax.swing.JPanel PnlPaymentMake;
    private javax.swing.JPanel PnlPaymentReceipt;
    private javax.swing.JPanel PnlPaymentReminder;
    private javax.swing.JPanel PnlPaymentSuccesful;
    private javax.swing.JPanel PnlPreMed;
    private javax.swing.JPanel PnlPreMedReminder;
    private javax.swing.JPanel PnlPreMedShowing;
    private javax.swing.JPanel PnlProfile;
    private javax.swing.JPanel PnlRegisterChild;
    private javax.swing.JPanel PnlSettings;
    private javax.swing.JPanel PnlShowingApp;
    private javax.swing.JPanel PnlVaccShow;
    private javax.swing.JPanel PnlVaccination;
    private javax.swing.JPanel PnlVaccineReminder;
    private javax.swing.JPanel PnlWarningProfile;
    private javax.swing.JButton btnAppDelete;
    private javax.swing.JButton btnAppointment;
    private javax.swing.JButton btnCancelApp;
    private javax.swing.JButton btnCancelDelete;
    private javax.swing.JButton btnCancelEdit;
    private javax.swing.JButton btnCancelPassword;
    private javax.swing.JButton btnCancelRegChild;
    private javax.swing.JButton btnChild;
    private javax.swing.JButton btnChildDetailEdit;
    private javax.swing.JButton btnChildDetailSave;
    private javax.swing.JButton btnCloseSideBar;
    private javax.swing.JButton btnConfirmPassword;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDeleteAcc;
    private javax.swing.JButton btnMakeApp;
    private javax.swing.JButton btnMedRecord;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnPaymentMade;
    private javax.swing.JButton btnPaymentTotalUp;
    private javax.swing.JButton btnPreMed;
    private javax.swing.JButton btnProfBloodEdit;
    private javax.swing.JButton btnProfBloodSave;
    private javax.swing.JButton btnProfConEdit;
    private javax.swing.JButton btnProfConSave;
    private javax.swing.JButton btnProfEmailEdit;
    private javax.swing.JButton btnProfEmailSave;
    private javax.swing.JButton btnProfGenderEdit;
    private javax.swing.JButton btnProfGenderSave;
    private javax.swing.JButton btnProfICEdit;
    private javax.swing.JButton btnProfICSave;
    private javax.swing.JButton btnProfNameEdit;
    private javax.swing.JButton btnProfNameSave;
    private javax.swing.JButton btnProfPass;
    private javax.swing.JButton btnProfRaceEdit;
    private javax.swing.JButton btnProfRaceSave;
    private javax.swing.JButton btnProfRegionEdit;
    private javax.swing.JButton btnProfRegionSave;
    private javax.swing.JButton btnProfSafe;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnProfileUnderstand;
    private javax.swing.JButton btnRegisterChild;
    private javax.swing.JButton btnRequestApp;
    private javax.swing.JButton btnSafetyQBack;
    private javax.swing.JButton btnSafetyQCancel;
    private javax.swing.JButton btnSafetyQEdit1;
    private javax.swing.JButton btnSafetyQEdit2;
    private javax.swing.JButton btnSafetyQSave;
    private javax.swing.JButton btnSettings;
    private javax.swing.JButton btnSideProf;
    private javax.swing.JButton btnVaccination;
    private javax.swing.JComboBox<String> cboAppChild;
    private javax.swing.JComboBox<String> cboAppDay;
    private javax.swing.JComboBox<String> cboAppDoctor;
    private javax.swing.JComboBox<String> cboAppMonth;
    private javax.swing.JComboBox<String> cboAppShowChoose;
    private javax.swing.JComboBox<String> cboAppShowFilter;
    private javax.swing.JComboBox<String> cboAppSpecialist;
    private javax.swing.JComboBox<String> cboAppTime;
    private javax.swing.JComboBox<String> cboAppYear;
    private javax.swing.JComboBox<String> cboChildList;
    private javax.swing.JComboBox<String> cboChildRegGender;
    private javax.swing.JComboBox<String> cboChildRegYear;
    private javax.swing.JComboBox<String> cboChildToDo;
    private javax.swing.JComboBox<String> cboChildToEdit;
    private javax.swing.JComboBox<String> cboMedRepChoose;
    private javax.swing.JComboBox<String> cboMedRepChoose1;
    private javax.swing.JComboBox<String> cboPaymentAction;
    private javax.swing.JComboBox<String> cboPaymentChoose;
    private javax.swing.JComboBox<String> cboPaymentMethod;
    private javax.swing.JComboBox<String> cboPreMedChoose;
    private javax.swing.JComboBox<String> cboPreMedChoose1;
    private javax.swing.JComboBox<String> cboSafetyQ1;
    private javax.swing.JComboBox<String> cboSafetyQ2;
    private javax.swing.JComboBox<String> cboVaccChoose;
    private javax.swing.JComboBox<String> cboVaccChoose1;
    private javax.swing.JCheckBox chkAppFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JLabel jLabel38;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblAppBookfor;
    private javax.swing.JLabel lblAppChild;
    private javax.swing.JLabel lblAppDate;
    private javax.swing.JLabel lblAppDayWarning;
    private javax.swing.JLabel lblAppShowDID;
    private javax.swing.JLabel lblAppShowDName;
    private javax.swing.JLabel lblAppShowDate;
    private javax.swing.JLabel lblAppShowID;
    private javax.swing.JLabel lblAppShowPID;
    private javax.swing.JLabel lblAppShowPName;
    private javax.swing.JLabel lblAppShowStatus;
    private javax.swing.JLabel lblAppShowTime;
    private javax.swing.JLabel lblAppTime;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblChildInvalidIC;
    private javax.swing.JLabel lblChildRegWarning;
    private javax.swing.JLabel lblMedRepDID;
    private javax.swing.JLabel lblMedRepDID1;
    private javax.swing.JLabel lblMedRepDate;
    private javax.swing.JLabel lblMedRepDate1;
    private javax.swing.JLabel lblMedRepDiag;
    private javax.swing.JLabel lblMedRepDiag1;
    private javax.swing.JLabel lblMedRepID;
    private javax.swing.JLabel lblMedRepID1;
    private javax.swing.JLabel lblMedRepPID;
    private javax.swing.JLabel lblMedRepPID1;
    private javax.swing.JLabel lblPaymentShowAmount;
    private javax.swing.JLabel lblPaymentShowDate;
    private javax.swing.JLabel lblPaymentShowID;
    private javax.swing.JLabel lblPaymentShowMPID;
    private javax.swing.JLabel lblPaymentShowMethod;
    private javax.swing.JLabel lblPaymentShowTotal;
    private javax.swing.JLabel lblPaymentWarningcbo;
    private javax.swing.JLabel lblPaymentWarninglst;
    private javax.swing.JLabel lblPreMedDate1;
    private javax.swing.JLabel lblPreMedDate2;
    private javax.swing.JLabel lblPreMedDate3;
    private javax.swing.JLabel lblPreMedDate4;
    private javax.swing.JLabel lblPreMedID;
    private javax.swing.JLabel lblPreMedID1;
    private javax.swing.JLabel lblPreMedMID;
    private javax.swing.JLabel lblPreMedMID1;
    private javax.swing.JLabel lblProfWarning1;
    private javax.swing.JLabel lblProfWarning2;
    private javax.swing.JLabel lblSafetyQ1;
    private javax.swing.JLabel lblSafetyQ2;
    private javax.swing.JLabel lblUserPhoto;
    private javax.swing.JLabel lblVaccDate;
    private javax.swing.JLabel lblVaccID;
    private javax.swing.JLabel lblVaccPID;
    private javax.swing.JLabel lblVaccType;
    private javax.swing.JLabel lblVaccineShowChild;
    private javax.swing.JLabel lblValidationPass1;
    private javax.swing.JLabel lblValidationPass2;
    private javax.swing.JLabel lblWrongPass;
    private javax.swing.JList<String> lstPaymentToMake;
    private javax.swing.JRadioButton rbnAppChild;
    private javax.swing.JRadioButton rbnAppself;
    private javax.swing.JRadioButton rbnGuardian;
    private javax.swing.JRadioButton rbnParent;
    private javax.swing.JTextArea txtAreaMedRepDescrip;
    private javax.swing.JTextArea txtAreaMedRepDescrip1;
    private javax.swing.JTextArea txtAreaMedRepTesting;
    private javax.swing.JTextArea txtAreaMedRepTesting1;
    private javax.swing.JTextArea txtAreaPreMedMedical;
    private javax.swing.JTextArea txtAreaPreMedMedical1;
    private javax.swing.JTextArea txtAreaVaccDesc;
    private javax.swing.JTextField txtChildIC;
    private javax.swing.JTextField txtChildName;
    private javax.swing.JTextField txtChildRegBlood;
    private javax.swing.JTextField txtChildRegReg;
    private javax.swing.JTextField txtChildShowAge;
    private javax.swing.JTextField txtChildShowBlood;
    private javax.swing.JTextField txtChildShowGender;
    private javax.swing.JTextField txtChildShowIC;
    private javax.swing.JTextField txtChildShowID;
    private javax.swing.JTextField txtChildShowName;
    private javax.swing.JTextField txtChildShowRegion;
    private javax.swing.JPasswordField txtPasswordNew;
    private javax.swing.JPasswordField txtPasswordOld;
    private javax.swing.JTextField txtProfAge;
    private javax.swing.JTextField txtProfBlood;
    private javax.swing.JTextField txtProfContact;
    private javax.swing.JTextField txtProfEmail;
    private javax.swing.JTextField txtProfGender;
    private javax.swing.JTextField txtProfIC;
    private javax.swing.JTextField txtProfID;
    private javax.swing.JTextField txtProfName;
    private javax.swing.JTextField txtProfRace;
    private javax.swing.JTextField txtProfRegion;
    private javax.swing.JTextField txtSafetyQ1;
    private javax.swing.JTextField txtSafetyQ2;
    // End of variables declaration//GEN-END:variables
}
