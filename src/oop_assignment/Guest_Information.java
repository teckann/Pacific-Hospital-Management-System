/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_assignment;

import java.awt.Desktop;
import java.net.URI;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.DefaultListModel;

/**
 *
 * @author Cynthia
 */
public class Guest_Information extends javax.swing.JFrame {
    public Guest_Information() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadHospitalPolicies();
        try (BufferedReader reader = new BufferedReader(new FileReader("hospital_information.txt"))) {
            String firstLine = reader.readLine(); 
            if (firstLine != null) {
                String[] parts = firstLine.split(";");
                if (parts.length >= 3) {
                    String address = parts[2]; 
                    lblAddress1.setText(address); 
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading address: " + e.getMessage());
        }
        jScrollPane5.getVerticalScrollBar().setUI(Utility_Methods.createWindowsScrollBarUI());
        jScrollPane5.getHorizontalScrollBar().setUI(Utility_Methods.createWindowsScrollBarUI());
    }

    private void loadHospitalPolicies() {
        DefaultListModel<String> operatingModel = new DefaultListModel<>();
        DefaultListModel<String> visitingModel = new DefaultListModel<>();
        DefaultListModel<String> safetyModel = new DefaultListModel<>();
        DefaultListModel<String> emergencyModel = new DefaultListModel<>();

        int opCount = 1;
        int visitCount = 1;
        int safetyCount = 1;
        int emergencyCount = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader("hospital_information.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String id = parts[0];
                    String category = parts[1];
                    String description = parts[2];

                    switch (category) {
                        case "Operating Hours":
                            operatingModel.addElement(opCount + ". " + description);
                            opCount++;
                            break;
                        case "Visiting Hours":
                            visitingModel.addElement(visitCount + ". " + description);
                            visitCount++;
                            break;
                        case "Safety Measures":
                            safetyModel.addElement(safetyCount + ". " + description);
                            safetyCount++;
                            break;
                        case "Accident & Emergency":
                            emergencyModel.addElement(emergencyCount + ". " + description);
                            emergencyCount++;
                            break;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading hospital information: " + e.getMessage());
        }

        jListEmergency.setModel(emergencyModel);
        jListVisitingHours.setModel(visitingModel);
        jListSafetyMeasures.setModel(safetyModel);
        jListOperatingHours.setModel(operatingModel);
        
        DefaultListModel<String> generalModel = new DefaultListModel<>();
        generalModel.addElement(""); 

        int generalCount = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader("hospital_information.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3 && parts[1].equals("General Information")) {
                    String description = parts[2];
                    if (!description.startsWith("Address:")) {
                        generalModel.addElement(generalCount + ". " + description);
                        generalCount++;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading general information: " + e.getMessage());
        }

        jListGeneralInfo.setModel(generalModel);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel279 = new javax.swing.JLabel();
        jLabel280 = new javax.swing.JLabel();
        jPanel66 = new javax.swing.JPanel();
        jLabel282 = new javax.swing.JLabel();
        jLabel283 = new javax.swing.JLabel();
        jLabel284 = new javax.swing.JLabel();
        btnDoctor_VisitorInformation = new javax.swing.JButton();
        btnVisitorInformation_VisitorInformation = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        btnSpecialties_VisitorInformation = new javax.swing.JButton();
        jLabel302 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel37 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jLabel285 = new javax.swing.JLabel();
        lblEmergency = new javax.swing.JLabel();
        jLabel424 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jPanel70 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jPanel68 = new javax.swing.JPanel();
        jPanel107 = new javax.swing.JPanel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel291 = new javax.swing.JLabel();
        jPanel120 = new javax.swing.JPanel();
        jLabel425 = new javax.swing.JLabel();
        lblOperatingHoursTitle1 = new javax.swing.JLabel();
        lblOperatingHoursInfo1 = new javax.swing.JLabel();
        lblOperatingHoursInfo2 = new javax.swing.JLabel();
        lblOperatingHoursTitle2 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblOperatingHoursTitle3 = new javax.swing.JLabel();
        lblOperatingHoursInfo3 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel123 = new javax.swing.JPanel();
        jLabel293 = new javax.swing.JLabel();
        lblVisitingHourTitle1 = new javax.swing.JLabel();
        lblVisitorHoursInfo1 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblVisitingHourTitle2 = new javax.swing.JLabel();
        lblVisitorHoursInfo2 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblVisitingHourTitle3 = new javax.swing.JLabel();
        lblVisitorHoursInfo3 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel125 = new javax.swing.JPanel();
        jLabel292 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel127 = new javax.swing.JPanel();
        jLabel426 = new javax.swing.JLabel();
        lblSafetyMeasuresInfo3 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        lblSafetyMeasuresInfo1 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblSafetyMeasuresInfo2 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel295 = new javax.swing.JLabel();
        jLabel297 = new javax.swing.JLabel();
        jLabel298 = new javax.swing.JLabel();
        jLabel304 = new javax.swing.JLabel();
        jLabel427 = new javax.swing.JLabel();
        jLabel437 = new javax.swing.JLabel();
        jLabel438 = new javax.swing.JLabel();
        jLabel439 = new javax.swing.JLabel();
        jLabel440 = new javax.swing.JLabel();
        jLabel441 = new javax.swing.JLabel();
        jLabel442 = new javax.swing.JLabel();
        jLabel443 = new javax.swing.JLabel();
        jLabel444 = new javax.swing.JLabel();
        jLabel445 = new javax.swing.JLabel();
        jLabel446 = new javax.swing.JLabel();
        jLabel447 = new javax.swing.JLabel();
        jLabel448 = new javax.swing.JLabel();
        jLabel449 = new javax.swing.JLabel();
        jLabel450 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel451 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel72 = new javax.swing.JPanel();
        jPanel74 = new javax.swing.JPanel();
        jLabel453 = new javax.swing.JLabel();
        jLabel454 = new javax.swing.JLabel();
        jLabel455 = new javax.swing.JLabel();
        jLabel456 = new javax.swing.JLabel();
        jLabel457 = new javax.swing.JLabel();
        jLabel458 = new javax.swing.JLabel();
        jLabel459 = new javax.swing.JLabel();
        jLabel460 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel461 = new javax.swing.JLabel();
        jPanel77 = new javax.swing.JPanel();
        jLabel462 = new javax.swing.JLabel();
        jLabel463 = new javax.swing.JLabel();
        lblMainLine = new javax.swing.JLabel();
        jPanel80 = new javax.swing.JPanel();
        jLabel465 = new javax.swing.JLabel();
        jLabel466 = new javax.swing.JLabel();
        lblOfficalWebsite = new javax.swing.JLabel();
        jPanel81 = new javax.swing.JPanel();
        jLabel468 = new javax.swing.JLabel();
        jLabel469 = new javax.swing.JLabel();
        lblOfficialEmail = new javax.swing.JLabel();
        jPanel82 = new javax.swing.JPanel();
        jLabel471 = new javax.swing.JLabel();
        jLabel472 = new javax.swing.JLabel();
        lblComplaintsEmail = new javax.swing.JLabel();
        jPanel83 = new javax.swing.JPanel();
        jLabel474 = new javax.swing.JLabel();
        jLabel475 = new javax.swing.JLabel();
        lblPartnershipEmail = new javax.swing.JLabel();
        jPanel114 = new javax.swing.JPanel();
        jLabel477 = new javax.swing.JLabel();
        jLabel478 = new javax.swing.JLabel();
        lblEmergencyHotline = new javax.swing.JLabel();
        jLabel480 = new javax.swing.JLabel();
        jLabel481 = new javax.swing.JLabel();
        jLabel482 = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnBackToPatient_VisitorInformation = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel281 = new javax.swing.JLabel();
        jLabel286 = new javax.swing.JLabel();
        jPanel69 = new javax.swing.JPanel();
        jLabel287 = new javax.swing.JLabel();
        jLabel288 = new javax.swing.JLabel();
        jLabel289 = new javax.swing.JLabel();
        jLabel303 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel38 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jLabel290 = new javax.swing.JLabel();
        lblEmergency1 = new javax.swing.JLabel();
        jLabel428 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jPanel71 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jPanel75 = new javax.swing.JPanel();
        jPanel108 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel294 = new javax.swing.JLabel();
        jPanel124 = new javax.swing.JPanel();
        jLabel296 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListVisitingHours = new javax.swing.JList<>();
        jPanel128 = new javax.swing.JPanel();
        jLabel430 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListSafetyMeasures = new javax.swing.JList<>();
        jLabel300 = new javax.swing.JLabel();
        jLabel301 = new javax.swing.JLabel();
        jLabel305 = new javax.swing.JLabel();
        jLabel306 = new javax.swing.JLabel();
        jLabel431 = new javax.swing.JLabel();
        jLabel452 = new javax.swing.JLabel();
        jLabel464 = new javax.swing.JLabel();
        jLabel467 = new javax.swing.JLabel();
        jLabel470 = new javax.swing.JLabel();
        jLabel473 = new javax.swing.JLabel();
        jLabel476 = new javax.swing.JLabel();
        jLabel479 = new javax.swing.JLabel();
        jLabel483 = new javax.swing.JLabel();
        jLabel484 = new javax.swing.JLabel();
        jLabel485 = new javax.swing.JLabel();
        jLabel486 = new javax.swing.JLabel();
        jLabel487 = new javax.swing.JLabel();
        jLabel488 = new javax.swing.JLabel();
        jLabel489 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel490 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jPanel76 = new javax.swing.JPanel();
        jPanel78 = new javax.swing.JPanel();
        jLabel491 = new javax.swing.JLabel();
        jLabel492 = new javax.swing.JLabel();
        jLabel493 = new javax.swing.JLabel();
        jLabel494 = new javax.swing.JLabel();
        jLabel495 = new javax.swing.JLabel();
        jLabel496 = new javax.swing.JLabel();
        jLabel497 = new javax.swing.JLabel();
        jLabel498 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel499 = new javax.swing.JLabel();
        jLabel512 = new javax.swing.JLabel();
        jLabel513 = new javax.swing.JLabel();
        jLabel514 = new javax.swing.JLabel();
        lblAddress1 = new javax.swing.JLabel();
        btnWaze = new javax.swing.JButton();
        btnGoogleMap = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListGeneralInfo = new javax.swing.JList<>();
        jPanel129 = new javax.swing.JPanel();
        jLabel307 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jPanel122 = new javax.swing.JPanel();
        jLabel432 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jListEmergency = new javax.swing.JList<>();
        jPanel121 = new javax.swing.JPanel();
        jLabel429 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListOperatingHours = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnSpecialties_MainPage = new javax.swing.JButton();
        btnDoctor_MainPage = new javax.swing.JButton();
        btnBackToPatient_MainPage = new javax.swing.JButton();
        btnVisitorInformation_Specialties = new javax.swing.JButton();
        btnHealthcareProgram = new javax.swing.JButton();

        jLabel279.setForeground(new java.awt.Color(255, 255, 255));
        jLabel279.setText("🕓 Visting Hours: 8AM- 10PM");

        jLabel280.setForeground(new java.awt.Color(255, 255, 255));
        jLabel280.setText("📞 General Line: +603-8126 0888     🚑 Ambulance/ Emergency:  +603-8126 0999");

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));

        jLabel282.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        jLabel282.setForeground(new java.awt.Color(0, 12, 102));
        jLabel282.setText("Visitor Information");

        jLabel283.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jLabel283.setText("Discover our comprehensive medical services and meet our expert healthcare");

        jLabel284.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jLabel284.setText("professionals dedicated to providing exceptional patient care.");

        btnDoctor_VisitorInformation.setText("👨 ‍Doctors");
        btnDoctor_VisitorInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoctor_VisitorInformationActionPerformed(evt);
            }
        });

        btnVisitorInformation_VisitorInformation.setText("👥 Vistor Information");
        btnVisitorInformation_VisitorInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisitorInformation_VisitorInformationActionPerformed(evt);
            }
        });

        jButton48.setText("📆 Healthcare Programs");

        btnSpecialties_VisitorInformation.setText("🏥 Specialties");
        btnSpecialties_VisitorInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpecialties_VisitorInformationActionPerformed(evt);
            }
        });

        jLabel302.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel51.setBackground(new java.awt.Color(254, 242, 242));
        jPanel51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 202, 202)));

        jLabel285.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel285.setForeground(new java.awt.Color(127, 29, 29));
        jLabel285.setText("EMERGENCY");

        lblEmergency.setForeground(new java.awt.Color(153, 27, 27));
        lblEmergency.setText("Call Emergency Hot Line: +603-8126 0999 or go to Emergency Department");

        jLabel424.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Emergency.png"))); // NOI18N

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel424, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel285, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmergency, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel424)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(jLabel285)
                        .addGap(0, 0, 0)
                        .addComponent(lblEmergency)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));
        jPanel37.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 114, -1, 197));

        jPanel70.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Care icon.png"))); // NOI18N

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setText("Compassionate Care");

        jLabel49.setText("Our dedicated staff provides");

        jLabel50.setText("exceptional care with");

        jLabel97.setText("compassion and respect ");

        jLabel98.setText("for every patient and family.");

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel50)
                .addGap(36, 36, 36))
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel70Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel33))
                            .addComponent(jLabel34)))
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel49))
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel98)
                            .addComponent(jLabel97))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(jLabel49)
                .addGap(0, 0, 0)
                .addComponent(jLabel50)
                .addGap(0, 0, 0)
                .addComponent(jLabel97)
                .addGap(0, 0, 0)
                .addComponent(jLabel98)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel37.add(jPanel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 640, -1, 209));

        jPanel67.setBackground(new java.awt.Color(255, 255, 255));

        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Safety icon.png"))); // NOI18N

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel100.setText("Safety First");

        jLabel108.setText("We maintain the highest");

        jLabel109.setText("standards of safety and ");

        jLabel110.setText("cleanliness to protect");

        jLabel111.setText("oatient, visitor and staff.");

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel99)
                .addGap(78, 78, 78))
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel100)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel108)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addComponent(jLabel111)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel67Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel110))
                        .addComponent(jLabel109)))
                .addGap(32, 32, 32))
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel100)
                .addGap(18, 18, 18)
                .addComponent(jLabel108)
                .addGap(0, 0, 0)
                .addComponent(jLabel109)
                .addGap(0, 0, 0)
                .addComponent(jLabel110)
                .addGap(0, 0, 0)
                .addComponent(jLabel111)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel37.add(jPanel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 640, 200, 210));

        jPanel107.setBackground(new java.awt.Color(255, 255, 255));

        jLabel132.setText("We understand the importance");

        jLabel133.setText("of family support and welcome");

        jLabel134.setText("loved ones as partners");

        jLabel135.setText("in healing.");

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Family icon.png"))); // NOI18N

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel126.setText("Family- Centered");

        javax.swing.GroupLayout jPanel107Layout = new javax.swing.GroupLayout(jPanel107);
        jPanel107.setLayout(jPanel107Layout);
        jPanel107Layout.setHorizontalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addGroup(jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel107Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel133)
                            .addComponent(jLabel132)))
                    .addGroup(jPanel107Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel134))
                    .addGroup(jPanel107Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel135)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel107Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel107Layout.createSequentialGroup()
                        .addComponent(jLabel112)
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel107Layout.createSequentialGroup()
                        .addComponent(jLabel126)
                        .addGap(37, 37, 37))))
        );
        jPanel107Layout.setVerticalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel107Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel126)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel132)
                .addGap(0, 0, 0)
                .addComponent(jLabel133)
                .addGap(0, 0, 0)
                .addComponent(jLabel134)
                .addGap(0, 0, 0)
                .addComponent(jLabel135)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel37.add(jPanel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 640, -1, 209));

        jLabel136.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(0, 12, 102));
        jLabel136.setText("Why Choose Us ");
        jPanel37.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 329, 366, -1));
        jPanel37.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 542, -1, 54));
        jPanel37.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 534, -1, 62));

        jLabel138.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bed icon.png"))); // NOI18N
        jLabel138.setText("jLabel138");
        jPanel37.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 388, 201, -1));

        jLabel139.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Patient icon.png"))); // NOI18N
        jLabel139.setText("jLabel138");
        jPanel37.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 388, 201, 134));

        jLabel141.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel141.setText("Patient Served Per Year");
        jPanel37.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 600, 163, -1));

        jLabel143.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel143.setText("Licensed Beds");
        jPanel37.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, 106, -1));

        jLabel291.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel291.setForeground(new java.awt.Color(0, 12, 102));
        jLabel291.setText("Hospital Policies ");
        jPanel37.add(jLabel291, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 880, 366, -1));

        jPanel120.setBackground(new java.awt.Color(251, 230, 230));
        jPanel120.setForeground(new java.awt.Color(251, 188, 199));
        jPanel120.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel425.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel425.setText("Operating Hours");
        jPanel120.add(jLabel425, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 40));

        lblOperatingHoursTitle1.setText("Pharmacy operating hours: ");
        jPanel120.add(lblOperatingHoursTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 240, -1));

        lblOperatingHoursInfo1.setText("08:00AM to 07:00PM (Weekday)");
        jPanel120.add(lblOperatingHoursInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 280, -1));

        lblOperatingHoursInfo2.setForeground(new java.awt.Color(251, 163, 167));
        lblOperatingHoursInfo2.setText("___________________________________________________________");
        jPanel120.add(lblOperatingHoursInfo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 320, 30));

        lblOperatingHoursTitle2.setText("Admin Office operating hours");
        jPanel120.add(lblOperatingHoursTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 240, -1));

        jLabel36.setForeground(new java.awt.Color(251, 163, 167));
        jLabel36.setText("___________________________________________________________");
        jPanel120.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 320, 30));

        jLabel5.setText("8:30AM – 5:30PM (Weekday)");
        jPanel120.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 260, -1));

        lblOperatingHoursTitle3.setText(" ");
        jPanel120.add(lblOperatingHoursTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 240, -1));

        lblOperatingHoursInfo3.setText(" ");
        jPanel120.add(lblOperatingHoursInfo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 280, -1));

        jLabel37.setForeground(new java.awt.Color(251, 163, 167));
        jLabel37.setText("___________________________________________________________");
        jPanel120.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 320, 30));

        jPanel37.add(jPanel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 940, 330, 220));

        jPanel123.setBackground(new java.awt.Color(229, 241, 252));
        jPanel123.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel293.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel293.setText("Visiting Hours");
        jPanel123.add(jLabel293, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        lblVisitingHourTitle1.setText("General visiting hours:");
        jPanel123.add(lblVisitingHourTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 240, -1));

        lblVisitorHoursInfo1.setText("12:00PM to 02:00PM & 06:00PM to 08:00PM");
        jPanel123.add(lblVisitorHoursInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 280, -1));

        jLabel38.setForeground(new java.awt.Color(147, 196, 252));
        jLabel38.setText("________________________________________________________________");
        jPanel123.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 320, 30));

        lblVisitingHourTitle2.setText("ICU / CCU / HDU :");
        jPanel123.add(lblVisitingHourTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 240, -1));

        lblVisitorHoursInfo2.setText("12:00PM to 01:30PM & 05:00PM to 06:30PM");
        jPanel123.add(lblVisitorHoursInfo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 300, -1));

        jLabel39.setForeground(new java.awt.Color(147, 196, 252));
        jLabel39.setText("_______________________________________________________________");
        jPanel123.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 320, 30));

        lblVisitingHourTitle3.setText("NICU:");
        jPanel123.add(lblVisitingHourTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 240, -1));

        lblVisitorHoursInfo3.setText("Only parents are allowed to visit (12:00PM to 08:00PM)");
        jPanel123.add(lblVisitorHoursInfo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 300, -1));

        jLabel40.setForeground(new java.awt.Color(147, 196, 252));
        jLabel40.setText("_______________________________________________________________");
        jPanel123.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 320, 30));

        jPanel37.add(jPanel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 940, -1, 220));

        jPanel125.setBackground(new java.awt.Color(252, 243, 217));
        jPanel125.setForeground(new java.awt.Color(252, 239, 115));
        jPanel125.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel292.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel292.setText("Guidelines ");
        jPanel125.add(jLabel292, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel26.setText("Always respect patient privacy and confidentiality.");
        jPanel125.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 270, -1));

        jLabel47.setForeground(new java.awt.Color(252, 196, 110));
        jLabel47.setText("_______________________________________________________________");
        jPanel125.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 320, 30));

        jLabel27.setText(" ");
        jPanel125.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 270, -1));

        jLabel48.setForeground(new java.awt.Color(252, 196, 110));
        jLabel48.setText("_______________________________________________________________");
        jPanel125.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 350, 30));

        jLabel51.setForeground(new java.awt.Color(252, 196, 110));
        jLabel51.setText("_______________________________________________________________");
        jPanel125.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 320, 30));

        jLabel25.setText(" ");
        jPanel125.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 240, -1));

        jLabel29.setText("Maintain strict hygiene and a clean environment.");
        jPanel125.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 270, -1));

        jPanel37.add(jPanel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1190, 330, 230));

        jPanel127.setBackground(new java.awt.Color(233, 254, 226));
        jPanel127.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel426.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel426.setText("Safety Measures");
        jPanel127.add(jLabel426, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        lblSafetyMeasuresInfo3.setText(" ");
        jPanel127.add(lblSafetyMeasuresInfo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 240, -1));

        jLabel44.setForeground(new java.awt.Color(111, 254, 122));
        jLabel44.setText("_______________________________________________________________");
        jPanel127.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 320, 30));

        lblSafetyMeasuresInfo1.setText("A maximum of 2 visitors are allowed at a time");
        jPanel127.add(lblSafetyMeasuresInfo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 270, -1));

        jLabel45.setForeground(new java.awt.Color(111, 254, 122));
        jLabel45.setText("_______________________________________________________________");
        jPanel127.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 320, 30));

        lblSafetyMeasuresInfo2.setText(" ");
        jPanel127.add(lblSafetyMeasuresInfo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 240, -1));

        jLabel46.setForeground(new java.awt.Color(111, 254, 122));
        jLabel46.setText("_______________________________________________________________");
        jPanel127.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 320, 30));

        jPanel37.add(jPanel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 1190, 340, 230));

        jLabel295.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel295.setForeground(new java.awt.Color(0, 12, 102));
        jLabel295.setText("Visitor Amenities");
        jPanel37.add(jLabel295, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1450, 366, -1));

        jLabel297.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/parking icon.png"))); // NOI18N
        jPanel37.add(jLabel297, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1530, 49, 51));

        jLabel298.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cafeteria icon.png"))); // NOI18N
        jPanel37.add(jLabel298, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 1530, 49, -1));

        jLabel304.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/convenience store icon.png"))); // NOI18N
        jPanel37.add(jLabel304, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1520, 60, 50));

        jLabel427.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wifi icon.png"))); // NOI18N
        jPanel37.add(jLabel427, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1520, 49, -1));

        jLabel437.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel437.setText("Parking");
        jPanel37.add(jLabel437, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 1570, -1, -1));

        jLabel438.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel438.setText("Minimart");
        jPanel37.add(jLabel438, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 1560, -1, 30));

        jLabel439.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel439.setText("Free Wifi");
        jPanel37.add(jLabel439, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1550, -1, 40));

        jLabel440.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel440.setText("Cafeteria");
        jPanel37.add(jLabel440, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 1570, -1, -1));

        jLabel441.setForeground(new java.awt.Color(102, 102, 102));
        jLabel441.setText("Free visitor parking available");
        jPanel37.add(jLabel441, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 1590, -1, -1));

        jLabel442.setForeground(new java.awt.Color(102, 102, 102));
        jLabel442.setText("in the main lot");
        jPanel37.add(jLabel442, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 1600, -1, 20));

        jLabel443.setForeground(new java.awt.Color(102, 102, 102));
        jLabel443.setText("Fresh meals and beverage");
        jPanel37.add(jLabel443, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 1590, -1, -1));

        jLabel444.setForeground(new java.awt.Color(102, 102, 102));
        jLabel444.setText("open 8AM- 10PM");
        jPanel37.add(jLabel444, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1600, -1, 20));

        jLabel445.setForeground(new java.awt.Color(102, 102, 102));
        jLabel445.setText("Groceries and essentials");
        jPanel37.add(jLabel445, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 1590, -1, -1));

        jLabel446.setForeground(new java.awt.Color(102, 102, 102));
        jLabel446.setText("open 8AM- 10PM");
        jPanel37.add(jLabel446, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 1600, -1, 20));

        jLabel447.setForeground(new java.awt.Color(102, 102, 102));
        jLabel447.setText("Complimentary wireless");
        jPanel37.add(jLabel447, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 1590, -1, -1));

        jLabel448.setForeground(new java.awt.Color(102, 102, 102));
        jLabel448.setText("internet");
        jPanel37.add(jLabel448, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1600, -1, 20));

        jLabel449.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel449.setForeground(new java.awt.Color(0, 12, 102));
        jLabel449.setText("Hospital Navigation");
        jPanel37.add(jLabel449, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1660, 405, -1));

        jLabel450.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Navigation icon.png"))); // NOI18N
        jPanel37.add(jLabel450, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 1720, 405, 230));

        jPanel27.setBackground(new java.awt.Color(231, 255, 246));

        jLabel451.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel451.setText("Quick Direction");

        jPanel52.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel53.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel72.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel74.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel453.setBackground(new java.awt.Color(255, 51, 51));
        jLabel453.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel453.setForeground(new java.awt.Color(255, 51, 51));
        jLabel453.setText("Emergency:");

        jLabel454.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel454.setForeground(new java.awt.Color(51, 102, 255));
        jLabel454.setText("Reception:");

        jLabel455.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel455.setForeground(new java.awt.Color(164, 0, 255));
        jLabel455.setText("Department:");

        jLabel456.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel456.setForeground(new java.awt.Color(255, 102, 51));
        jLabel456.setText("Cafeteria:");

        jLabel457.setText("Main entrance, turn right");

        jLabel458.setText("Main entrance, straight ahead");

        jLabel459.setText("Take elevator to respective floors");

        jLabel460.setText("Ground floor, west wing");

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel451))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel453)
                .addGap(24, 24, 24)
                .addComponent(jLabel457, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(660, 660, 660)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(620, 620, 620)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel454)
                .addGap(29, 29, 29)
                .addComponent(jLabel458, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel455)
                    .addComponent(jLabel456))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel460, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel459)))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel451))
                .addGap(16, 16, 16)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel453)
                    .addComponent(jLabel457))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel454)
                    .addComponent(jLabel458))
                .addGap(17, 17, 17)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel459)
                    .addComponent(jLabel455))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel456)
                    .addComponent(jLabel460))
                .addGap(114, 114, 114)
                .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1419, 1419, 1419)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel37.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 1950, 390, 230));

        jLabel461.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel461.setForeground(new java.awt.Color(0, 12, 102));
        jLabel461.setText("Hospital Location");
        jPanel37.add(jLabel461, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2520, 453, -1));

        jPanel77.setBackground(new java.awt.Color(255, 255, 255));
        jPanel77.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel462.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel462.setText("Main Line");

        lblMainLine.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMainLine.setForeground(new java.awt.Color(53, 99, 235));
        lblMainLine.setText("+603-8126 0888");

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel463, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel462, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMainLine, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel462)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMainLine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel463)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.add(jPanel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 2260, 290, -1));

        jPanel80.setBackground(new java.awt.Color(255, 255, 255));
        jPanel80.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel465.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel465.setText("Official Website");

        lblOfficalWebsite.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblOfficalWebsite.setForeground(new java.awt.Color(53, 99, 235));
        lblOfficalWebsite.setText("www.pacifichospital.com.my");

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel80Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel466, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel80Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel465)
                            .addComponent(lblOfficalWebsite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel465)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOfficalWebsite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel466)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel37.add(jPanel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 2260, 330, 70));

        jPanel81.setBackground(new java.awt.Color(255, 255, 255));
        jPanel81.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel468.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel468.setText("Official Email");

        lblOfficialEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblOfficialEmail.setForeground(new java.awt.Color(53, 99, 235));
        lblOfficialEmail.setText("info@pacifichospital.com.my");

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel469, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel468, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOfficialEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel468)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOfficialEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel469)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.add(jPanel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2340, 301, -1));

        jPanel82.setBackground(new java.awt.Color(255, 255, 255));
        jPanel82.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel471.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel471.setText("Complaint Email");

        lblComplaintsEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblComplaintsEmail.setForeground(new java.awt.Color(53, 99, 235));
        lblComplaintsEmail.setText("complaints@pacifichospital.com.my");

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel472, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblComplaintsEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel82Layout.createSequentialGroup()
                        .addComponent(jLabel471, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel471)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblComplaintsEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel472)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel37.add(jPanel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 2340, -1, 70));

        jPanel83.setBackground(new java.awt.Color(255, 255, 255));
        jPanel83.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel474.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel474.setText("Excellent Hospital Partnership Program Email");

        lblPartnershipEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPartnershipEmail.setForeground(new java.awt.Color(53, 99, 235));
        lblPartnershipEmail.setText("ehpp@pacifichospital.com.my");

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel474, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel83Layout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(jLabel475, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel83Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblPartnershipEmail))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel474)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPartnershipEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel475)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel37.add(jPanel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2430, 301, -1));

        jPanel114.setBackground(new java.awt.Color(255, 255, 255));
        jPanel114.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel477.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel477.setText("Emergency Hotline");

        lblEmergencyHotline.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblEmergencyHotline.setForeground(new java.awt.Color(53, 99, 235));
        lblEmergencyHotline.setText("+603-8126 0999");

        javax.swing.GroupLayout jPanel114Layout = new javax.swing.GroupLayout(jPanel114);
        jPanel114.setLayout(jPanel114Layout);
        jPanel114Layout.setHorizontalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel114Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel114Layout.createSequentialGroup()
                        .addComponent(lblEmergencyHotline)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel478, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel477, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel114Layout.setVerticalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel114Layout.createSequentialGroup()
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel114Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel478))
                    .addGroup(jPanel114Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel477)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmergencyHotline)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel37.add(jPanel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 2420, 326, 80));

        jLabel480.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel480.setForeground(new java.awt.Color(0, 12, 102));
        jLabel480.setText("Important Contact Methods");
        jPanel37.add(jLabel480, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2210, 453, -1));

        jLabel481.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Map.png"))); // NOI18N
        jLabel481.setText("jLabel332");
        jPanel37.add(jLabel481, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 2580, 422, 220));

        jLabel482.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel482.setText("Address");
        jPanel37.add(jLabel482, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 2590, 152, -1));

        lblAddress.setText("8, Jalan ABC, 50123 Kuala Lumpur, Malaysia");
        jPanel37.add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 2630, 254, 28));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Waze.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel37.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 2700, 110, 40));

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Google Map.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel37.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 2700, 110, 40));

        jLabel4.setText("jLabel1");
        jPanel37.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 536, -1, 20));

        jLabel1.setText("jLabel1");
        jPanel37.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 590, 210));

        jLabel6.setText("jLabel1");
        jPanel37.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 540, -1, -1));

        jScrollPane4.setViewportView(jPanel37);

        btnBackToPatient_VisitorInformation.setText("🔙");
        btnBackToPatient_VisitorInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToPatient_VisitorInformationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel302)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBackToPatient_VisitorInformation)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel66Layout.createSequentialGroup()
                                .addComponent(btnSpecialties_VisitorInformation)
                                .addGap(18, 18, 18)
                                .addComponent(btnDoctor_VisitorInformation)
                                .addGap(18, 18, 18)
                                .addComponent(btnVisitorInformation_VisitorInformation)
                                .addGap(18, 18, 18)
                                .addComponent(jButton48))
                            .addComponent(jLabel282)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel66Layout.createSequentialGroup()
                        .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel66Layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel283, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                                        .addComponent(jLabel284)
                                        .addGap(44, 44, 44))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel66Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel302))
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDoctor_VisitorInformation)
                            .addComponent(btnVisitorInformation_VisitorInformation)
                            .addComponent(jButton48)
                            .addComponent(btnSpecialties_VisitorInformation)
                            .addComponent(btnBackToPatient_VisitorInformation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jLabel282, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jLabel283)
                .addGap(0, 0, 0)
                .addComponent(jLabel284)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 64, 175));

        jLabel281.setForeground(new java.awt.Color(255, 255, 255));
        jLabel281.setText("🕓 Visiting Hours: 12:00 PM- 8:00 PM");

        jLabel286.setForeground(new java.awt.Color(255, 255, 255));
        jLabel286.setText("📞 General Line: +603-8126 0888     🚑 Ambulance/ Emergency:  +603-8126 0999");

        jPanel69.setBackground(new java.awt.Color(255, 255, 255));

        jLabel287.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        jLabel287.setForeground(new java.awt.Color(0, 12, 102));
        jLabel287.setText("Visitor Information");

        jLabel288.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jLabel288.setText("Discover everything you need to know about our hospital");

        jLabel289.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jLabel289.setText("from facilities to visiting guidelines all in one place.");

        jLabel303.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel54.setBackground(new java.awt.Color(254, 242, 242));
        jPanel54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 202, 202)));

        jLabel290.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel290.setForeground(new java.awt.Color(127, 29, 29));
        jLabel290.setText("EMERGENCY");

        lblEmergency1.setForeground(new java.awt.Color(153, 27, 27));
        lblEmergency1.setText("Call Emergency Hot Line: +603-8126 0999 or go to Emergency Department");

        jLabel428.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Emergency.png"))); // NOI18N

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel428, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel290, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmergency1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel428)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jLabel290)
                        .addGap(0, 0, 0)
                        .addComponent(lblEmergency1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel38.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));
        jPanel38.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 114, -1, 197));

        jPanel71.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Care icon.png"))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setText("Compassionate Care");

        jLabel52.setText("Our dedicated staff provides");

        jLabel53.setText("exceptional care with");

        jLabel101.setText("compassion and respect ");

        jLabel102.setText("for every patient and family.");

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel71Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel53)
                .addGap(36, 36, 36))
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel71Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel35))
                            .addComponent(jLabel41)))
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel52))
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel102)
                            .addComponent(jLabel101))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addGap(0, 0, 0)
                .addComponent(jLabel53)
                .addGap(0, 0, 0)
                .addComponent(jLabel101)
                .addGap(0, 0, 0)
                .addComponent(jLabel102)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel38.add(jPanel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, -1, 209));

        jPanel73.setBackground(new java.awt.Color(255, 255, 255));

        jLabel103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Safety icon.png"))); // NOI18N

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel104.setText("Safety First");

        jLabel113.setText("We maintain the highest");

        jLabel114.setText("standards of safety and ");

        jLabel115.setText("cleanliness to protect");

        jLabel116.setText("patient, visitor and staff.");

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel103)
                .addGap(78, 78, 78))
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel104)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel113)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addComponent(jLabel116)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel73Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel115))
                        .addComponent(jLabel114)))
                .addGap(32, 32, 32))
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel103)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel104)
                .addGap(18, 18, 18)
                .addComponent(jLabel113)
                .addGap(0, 0, 0)
                .addComponent(jLabel114)
                .addGap(0, 0, 0)
                .addComponent(jLabel115)
                .addGap(0, 0, 0)
                .addComponent(jLabel116)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel38.add(jPanel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 670, 200, 210));

        jPanel108.setBackground(new java.awt.Color(255, 255, 255));

        jLabel137.setText("We understand the importance");

        jLabel144.setText("of family support and welcome");

        jLabel145.setText("loved ones as partners");

        jLabel146.setText("in healing.");

        jLabel117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Family icon.png"))); // NOI18N

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel127.setText("Family- Centered");

        javax.swing.GroupLayout jPanel108Layout = new javax.swing.GroupLayout(jPanel108);
        jPanel108.setLayout(jPanel108Layout);
        jPanel108Layout.setHorizontalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel108Layout.createSequentialGroup()
                .addGroup(jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel108Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel144)
                            .addComponent(jLabel137)))
                    .addGroup(jPanel108Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel145))
                    .addGroup(jPanel108Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel146)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel108Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel108Layout.createSequentialGroup()
                        .addComponent(jLabel117)
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel108Layout.createSequentialGroup()
                        .addComponent(jLabel127)
                        .addGap(37, 37, 37))))
        );
        jPanel108Layout.setVerticalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel108Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel127)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel137)
                .addGap(0, 0, 0)
                .addComponent(jLabel144)
                .addGap(0, 0, 0)
                .addComponent(jLabel145)
                .addGap(0, 0, 0)
                .addComponent(jLabel146)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel75Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel75Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel38.add(jPanel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 670, 200, 210));

        jLabel147.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(0, 12, 102));
        jLabel147.setText("Why Choose Us ");
        jPanel38.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 366, -1));
        jPanel38.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 542, -1, 54));
        jPanel38.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 534, -1, 62));

        jLabel150.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Bed icon.png"))); // NOI18N
        jLabel150.setText("jLabel138");
        jPanel38.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 448, 190, 130));

        jLabel151.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Patient icon.png"))); // NOI18N
        jLabel151.setText("jLabel138");
        jPanel38.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 200, 120));

        jLabel152.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel152.setText("Patient Served Per Year");
        jPanel38.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 620, 163, -1));

        jLabel153.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel153.setText("Licensed Beds");
        jPanel38.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 620, 106, -1));

        jLabel294.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel294.setForeground(new java.awt.Color(0, 12, 102));
        jLabel294.setText("Hospital Policies ");
        jPanel38.add(jLabel294, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 900, 366, -1));

        jPanel124.setBackground(new java.awt.Color(233, 254, 226));
        jPanel124.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel296.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel296.setText("Visiting Hours");
        jPanel124.add(jLabel296, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jListVisitingHours.setBackground(new java.awt.Color(236, 250, 220));
        jListVisitingHours.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListVisitingHours.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListVisitingHours);

        jPanel124.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 660, 150));

        jPanel38.add(jPanel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1440, 680, 220));

        jPanel128.setBackground(new java.awt.Color(252, 243, 217));
        jPanel128.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel430.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel430.setText("Safety Measures");
        jPanel128.add(jLabel430, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jListSafetyMeasures.setBackground(new java.awt.Color(255, 255, 225));
        jListSafetyMeasures.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListSafetyMeasures.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jListSafetyMeasures);

        jPanel128.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 660, 160));

        jPanel38.add(jPanel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1690, 680, 230));

        jLabel300.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel300.setForeground(new java.awt.Color(0, 12, 102));
        jLabel300.setText("Visitor Amenities");
        jPanel38.add(jLabel300, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2210, 366, -1));

        jLabel301.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/parking icon.png"))); // NOI18N
        jPanel38.add(jLabel301, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 2280, 49, 51));

        jLabel305.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cafeteria icon.png"))); // NOI18N
        jPanel38.add(jLabel305, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 2280, 49, -1));

        jLabel306.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/convenience store icon.png"))); // NOI18N
        jPanel38.add(jLabel306, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 2270, 60, 50));

        jLabel431.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wifi icon.png"))); // NOI18N
        jPanel38.add(jLabel431, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 2270, 49, 50));

        jLabel452.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel452.setText("Parking");
        jPanel38.add(jLabel452, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 2320, -1, -1));

        jLabel464.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel464.setText("Minimart");
        jPanel38.add(jLabel464, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 2310, -1, 40));

        jLabel467.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel467.setText("Free Wifi");
        jPanel38.add(jLabel467, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 2310, 70, 40));

        jLabel470.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel470.setText("Cafeteria");
        jPanel38.add(jLabel470, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2320, -1, -1));

        jLabel473.setForeground(new java.awt.Color(102, 102, 102));
        jLabel473.setText("Free visitor parking available");
        jPanel38.add(jLabel473, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 2350, -1, -1));

        jLabel476.setForeground(new java.awt.Color(102, 102, 102));
        jLabel476.setText("in the main lot");
        jPanel38.add(jLabel476, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 2360, -1, 20));

        jLabel479.setForeground(new java.awt.Color(102, 102, 102));
        jLabel479.setText("Fresh meals and beverage");
        jPanel38.add(jLabel479, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 2350, -1, -1));

        jLabel483.setForeground(new java.awt.Color(102, 102, 102));
        jLabel483.setText("open 8AM- 10PM");
        jPanel38.add(jLabel483, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 2360, -1, 20));

        jLabel484.setForeground(new java.awt.Color(102, 102, 102));
        jLabel484.setText("Groceries and essentials");
        jPanel38.add(jLabel484, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 2350, -1, -1));

        jLabel485.setForeground(new java.awt.Color(102, 102, 102));
        jLabel485.setText("open 8AM- 10PM");
        jPanel38.add(jLabel485, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 2360, -1, 20));

        jLabel486.setForeground(new java.awt.Color(102, 102, 102));
        jLabel486.setText("Complimentary wireless");
        jPanel38.add(jLabel486, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 2350, -1, -1));

        jLabel487.setForeground(new java.awt.Color(102, 102, 102));
        jLabel487.setText("internet");
        jPanel38.add(jLabel487, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 2360, -1, 20));

        jLabel488.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel488.setForeground(new java.awt.Color(0, 12, 102));
        jLabel488.setText("Hospital Navigation");
        jPanel38.add(jLabel488, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2420, 405, -1));

        jLabel489.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Navigation icon.png"))); // NOI18N
        jPanel38.add(jLabel489, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 2490, 405, 230));

        jPanel28.setBackground(new java.awt.Color(231, 255, 246));

        jLabel490.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel490.setText("Quick Direction");

        jPanel55.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel56.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel76.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel78.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel491.setBackground(new java.awt.Color(255, 51, 51));
        jLabel491.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel491.setForeground(new java.awt.Color(255, 51, 51));
        jLabel491.setText("Emergency:");

        jLabel492.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel492.setForeground(new java.awt.Color(51, 102, 255));
        jLabel492.setText("Reception:");

        jLabel493.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel493.setForeground(new java.awt.Color(164, 0, 255));
        jLabel493.setText("Department:");

        jLabel494.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel494.setForeground(new java.awt.Color(255, 102, 51));
        jLabel494.setText("Cafeteria:");

        jLabel495.setText("Main entrance, turn right");

        jLabel496.setText("Main entrance, straight ahead");

        jLabel497.setText("Take elevator to respective floors");

        jLabel498.setText("Ground floor, west wing");

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel490))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel491)
                .addGap(24, 24, 24)
                .addComponent(jLabel495, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(660, 660, 660)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(620, 620, 620)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel492)
                .addGap(29, 29, 29)
                .addComponent(jLabel496, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel493)
                    .addComponent(jLabel494))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel498, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel497)))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel490))
                .addGap(16, 16, 16)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel491)
                    .addComponent(jLabel495))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel492)
                    .addComponent(jLabel496))
                .addGap(17, 17, 17)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel497)
                    .addComponent(jLabel493))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel494)
                    .addComponent(jLabel498))
                .addGap(114, 114, 114)
                .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1419, 1419, 1419)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel38.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 2720, 390, 230));

        jLabel499.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel499.setForeground(new java.awt.Color(0, 12, 102));
        jLabel499.setText("Hospital Location");
        jPanel38.add(jLabel499, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 3320, 453, -1));

        jLabel512.setFont(new java.awt.Font("Nirmala UI", 1, 30)); // NOI18N
        jLabel512.setForeground(new java.awt.Color(0, 12, 102));
        jLabel512.setText("Contact Us");
        jPanel38.add(jLabel512, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 2990, 453, -1));

        jLabel513.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Map.png"))); // NOI18N
        jLabel513.setText("jLabel332");
        jPanel38.add(jLabel513, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 3370, 390, 220));

        jLabel514.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel514.setText("Address");
        jPanel38.add(jLabel514, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 3390, 152, -1));

        lblAddress1.setText("address");
        jPanel38.add(lblAddress1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 3430, 300, 28));

        btnWaze.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Waze.png"))); // NOI18N
        btnWaze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWazeActionPerformed(evt);
            }
        });
        jPanel38.add(btnWaze, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 3500, 110, 40));

        btnGoogleMap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apple maps.png"))); // NOI18N
        btnGoogleMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoogleMapActionPerformed(evt);
            }
        });
        jPanel38.add(btnGoogleMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 3500, 110, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 12, 102));
        jLabel8.setText("333, 870");
        jPanel38.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 580, -1, 40));

        jListGeneralInfo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jListGeneralInfo);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel38.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 3040, 680, 250));

        jPanel129.setBackground(new java.awt.Color(213, 205, 243));
        jPanel129.setForeground(new java.awt.Color(252, 239, 115));
        jPanel129.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel307.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel307.setText("Guidelines ");
        jPanel129.add(jLabel307, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel42.setText(" ");
        jPanel129.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 270, -1));

        jList4.setBackground(new java.awt.Color(230, 232, 255));
        jList4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "1. All visitors and patients must wear a face mask inside hospital premises at all times.", "2. Use the hand sanitizer provided at the entrance and outside wards before and after visiting.", "3. Smoking and vaping are strictly prohibited within hospital buildings and compounds." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(jList4);

        jPanel129.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 660, 160));

        jPanel38.add(jPanel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1950, 680, 230));

        jPanel122.setBackground(new java.awt.Color(255, 232, 232));
        jPanel122.setForeground(new java.awt.Color(251, 188, 199));
        jPanel122.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel432.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel432.setText("Emergency");
        jPanel122.add(jLabel432, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 10, 150, 40));

        jListEmergency.setBackground(new java.awt.Color(255, 240, 240));
        jListEmergency.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListEmergency.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(jListEmergency);

        jPanel122.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 660, 150));

        jPanel38.add(jPanel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 960, 680, 220));

        jPanel121.setBackground(new java.awt.Color(190, 234, 255));
        jPanel121.setForeground(new java.awt.Color(251, 188, 199));
        jPanel121.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel429.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel429.setText("Operating Hours");
        jPanel121.add(jLabel429, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 10, 150, 40));

        jListOperatingHours.setBackground(new java.awt.Color(229, 241, 252));
        jListOperatingHours.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListOperatingHours.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListOperatingHours);

        jPanel121.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 660, 150));

        jPanel38.add(jPanel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1200, 680, 220));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Visitor Video.gif"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel38.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 590, 240));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 12, 102));
        jLabel10.setText("376");
        jPanel38.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, -1, 40));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel38.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 3600, 620, 20));

        jScrollPane5.setViewportView(jPanel38);

        btnSpecialties_MainPage.setBackground(new java.awt.Color(211, 211, 211));
        btnSpecialties_MainPage.setText("🏥 Specialties");
        btnSpecialties_MainPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpecialties_MainPageActionPerformed(evt);
            }
        });

        btnDoctor_MainPage.setBackground(new java.awt.Color(211, 211, 211));
        btnDoctor_MainPage.setText("👨 ‍Doctors");
        btnDoctor_MainPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoctor_MainPageActionPerformed(evt);
            }
        });

        btnBackToPatient_MainPage.setBackground(new java.awt.Color(211, 211, 211));
        btnBackToPatient_MainPage.setText("🔙");
        btnBackToPatient_MainPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToPatient_MainPageActionPerformed(evt);
            }
        });

        btnVisitorInformation_Specialties.setBackground(new java.awt.Color(211, 211, 211));
        btnVisitorInformation_Specialties.setText("👥 Visitor Information");
        btnVisitorInformation_Specialties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisitorInformation_SpecialtiesActionPerformed(evt);
            }
        });

        btnHealthcareProgram.setBackground(new java.awt.Color(211, 211, 211));
        btnHealthcareProgram.setText("📆 Healthcare Programs");
        btnHealthcareProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHealthcareProgramActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                        .addComponent(jLabel303)
                        .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel69Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel288, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel287, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                                        .addComponent(jLabel289)
                                        .addGap(26, 26, 26))))
                            .addGroup(jPanel69Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBackToPatient_MainPage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnSpecialties_MainPage)
                                .addGap(18, 18, 18)
                                .addComponent(btnDoctor_MainPage)
                                .addGap(13, 13, 13)
                                .addComponent(btnVisitorInformation_Specialties)
                                .addGap(22, 22, 22)
                                .addComponent(btnHealthcareProgram)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel303))
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBackToPatient_MainPage)
                            .addComponent(btnSpecialties_MainPage)
                            .addComponent(btnDoctor_MainPage)
                            .addComponent(btnVisitorInformation_Specialties)
                            .addComponent(btnHealthcareProgram))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel287, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel288)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel289)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel281)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel286)
                .addGap(20, 20, 20))
            .addComponent(jPanel69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel286, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(jLabel281, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoctor_VisitorInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctor_VisitorInformationActionPerformed
        Guest_Doctor guestDoctor = new Guest_Doctor();
        guestDoctor.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnDoctor_VisitorInformationActionPerformed

    private void btnVisitorInformation_VisitorInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisitorInformation_VisitorInformationActionPerformed
        Guest_Information guestInformation = new Guest_Information();
        guestInformation.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnVisitorInformation_VisitorInformationActionPerformed

    private void btnSpecialties_VisitorInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpecialties_VisitorInformationActionPerformed
        Guest_Main_Page guestMainPage = new Guest_Main_Page();
        guestMainPage.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnSpecialties_VisitorInformationActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void btnBackToPatient_VisitorInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToPatient_VisitorInformationActionPerformed
        /* Patient_Frame patientFrame = new Patient_Frame();
        patientFrame.setVisible(true);
        this.hide();
        */
    }//GEN-LAST:event_btnBackToPatient_VisitorInformationActionPerformed

    private void btnWazeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWazeActionPerformed
    try {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI("https://www.waze.com/live-map/directions/apu-jalan-teknologi-5-kuala-lumpur?to=place.w.66650143.666501426.11060407&from=ll.3.0605312%2C101.6922112&utm_medium=lm_share_directions&utm_campaign=default&utm_source=waze_website"));
        } else {
            JOptionPane.showMessageDialog(this, "Desktop browsing not supported.");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Unable to open link.");
    }
    }//GEN-LAST:event_btnWazeActionPerformed

    private void btnGoogleMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoogleMapActionPerformed
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("https://maps.apple.com/place?place-id=I41B46BECF2A68490&address=Jalan+Teknologi+5%2C+Technology+Park+Malaysia%2C+Bukit+Jalil%2C+57000+Kuala+Lumpur%2C+Kuala+Lumpur%2C+Malaysia&coordinate=3.0554644%2C101.7003644&name=Asia+Pacific+University+of+Technology+%26+Innovation&_provider=9902"));
            } else {
                JOptionPane.showMessageDialog(this, "Desktop browsing not supported.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to open link.");
        }

    }//GEN-LAST:event_btnGoogleMapActionPerformed

    private void btnSpecialties_MainPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpecialties_MainPageActionPerformed
        Guest_Main_Page guestmainPage = new Guest_Main_Page();
        guestmainPage.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnSpecialties_MainPageActionPerformed

    private void btnDoctor_MainPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctor_MainPageActionPerformed
        Guest_Doctor guestDoctor = new Guest_Doctor();
        guestDoctor.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnDoctor_MainPageActionPerformed

    private void btnBackToPatient_MainPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToPatient_MainPageActionPerformed
        if (Main.getCurrentUserID() == null) {
            Login_Frame loginFrame = new Login_Frame();
            loginFrame.setVisible(true);
            this.hide();
        }
        else{
            Patient_Frame patientFrame = new Patient_Frame();
            patientFrame.setVisible(true);
            this.hide();
        }

    }//GEN-LAST:event_btnBackToPatient_MainPageActionPerformed

    private void btnVisitorInformation_SpecialtiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisitorInformation_SpecialtiesActionPerformed
        Guest_Information guestInformation = new Guest_Information();
        guestInformation.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnVisitorInformation_SpecialtiesActionPerformed

    private void btnHealthcareProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHealthcareProgramActionPerformed
        Guest_Healthcare guestHealthcare = new Guest_Healthcare();
        guestHealthcare.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnHealthcareProgramActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Guest_Information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Guest_Information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Guest_Information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Guest_Information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Guest_Information().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackToPatient_MainPage;
    private javax.swing.JButton btnBackToPatient_VisitorInformation;
    private javax.swing.JButton btnDoctor_MainPage;
    private javax.swing.JButton btnDoctor_VisitorInformation;
    private javax.swing.JButton btnGoogleMap;
    private javax.swing.JButton btnHealthcareProgram;
    private javax.swing.JButton btnSpecialties_MainPage;
    private javax.swing.JButton btnSpecialties_VisitorInformation;
    private javax.swing.JButton btnVisitorInformation_Specialties;
    private javax.swing.JButton btnVisitorInformation_VisitorInformation;
    private javax.swing.JButton btnWaze;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton48;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
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
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JLabel jLabel424;
    private javax.swing.JLabel jLabel425;
    private javax.swing.JLabel jLabel426;
    private javax.swing.JLabel jLabel427;
    private javax.swing.JLabel jLabel428;
    private javax.swing.JLabel jLabel429;
    private javax.swing.JLabel jLabel430;
    private javax.swing.JLabel jLabel431;
    private javax.swing.JLabel jLabel432;
    private javax.swing.JLabel jLabel437;
    private javax.swing.JLabel jLabel438;
    private javax.swing.JLabel jLabel439;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel440;
    private javax.swing.JLabel jLabel441;
    private javax.swing.JLabel jLabel442;
    private javax.swing.JLabel jLabel443;
    private javax.swing.JLabel jLabel444;
    private javax.swing.JLabel jLabel445;
    private javax.swing.JLabel jLabel446;
    private javax.swing.JLabel jLabel447;
    private javax.swing.JLabel jLabel448;
    private javax.swing.JLabel jLabel449;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel450;
    private javax.swing.JLabel jLabel451;
    private javax.swing.JLabel jLabel452;
    private javax.swing.JLabel jLabel453;
    private javax.swing.JLabel jLabel454;
    private javax.swing.JLabel jLabel455;
    private javax.swing.JLabel jLabel456;
    private javax.swing.JLabel jLabel457;
    private javax.swing.JLabel jLabel458;
    private javax.swing.JLabel jLabel459;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel460;
    private javax.swing.JLabel jLabel461;
    private javax.swing.JLabel jLabel462;
    private javax.swing.JLabel jLabel463;
    private javax.swing.JLabel jLabel464;
    private javax.swing.JLabel jLabel465;
    private javax.swing.JLabel jLabel466;
    private javax.swing.JLabel jLabel467;
    private javax.swing.JLabel jLabel468;
    private javax.swing.JLabel jLabel469;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel470;
    private javax.swing.JLabel jLabel471;
    private javax.swing.JLabel jLabel472;
    private javax.swing.JLabel jLabel473;
    private javax.swing.JLabel jLabel474;
    private javax.swing.JLabel jLabel475;
    private javax.swing.JLabel jLabel476;
    private javax.swing.JLabel jLabel477;
    private javax.swing.JLabel jLabel478;
    private javax.swing.JLabel jLabel479;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel480;
    private javax.swing.JLabel jLabel481;
    private javax.swing.JLabel jLabel482;
    private javax.swing.JLabel jLabel483;
    private javax.swing.JLabel jLabel484;
    private javax.swing.JLabel jLabel485;
    private javax.swing.JLabel jLabel486;
    private javax.swing.JLabel jLabel487;
    private javax.swing.JLabel jLabel488;
    private javax.swing.JLabel jLabel489;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel490;
    private javax.swing.JLabel jLabel491;
    private javax.swing.JLabel jLabel492;
    private javax.swing.JLabel jLabel493;
    private javax.swing.JLabel jLabel494;
    private javax.swing.JLabel jLabel495;
    private javax.swing.JLabel jLabel496;
    private javax.swing.JLabel jLabel497;
    private javax.swing.JLabel jLabel498;
    private javax.swing.JLabel jLabel499;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel512;
    private javax.swing.JLabel jLabel513;
    private javax.swing.JLabel jLabel514;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JList<String> jList4;
    private javax.swing.JList<String> jListEmergency;
    private javax.swing.JList<String> jListGeneralInfo;
    private javax.swing.JList<String> jListOperatingHours;
    private javax.swing.JList<String> jListSafetyMeasures;
    private javax.swing.JList<String> jListVisitingHours;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel129;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAddress1;
    private javax.swing.JLabel lblComplaintsEmail;
    private javax.swing.JLabel lblEmergency;
    private javax.swing.JLabel lblEmergency1;
    private javax.swing.JLabel lblEmergencyHotline;
    private javax.swing.JLabel lblMainLine;
    private javax.swing.JLabel lblOfficalWebsite;
    private javax.swing.JLabel lblOfficialEmail;
    private javax.swing.JLabel lblOperatingHoursInfo1;
    private javax.swing.JLabel lblOperatingHoursInfo2;
    private javax.swing.JLabel lblOperatingHoursInfo3;
    private javax.swing.JLabel lblOperatingHoursTitle1;
    private javax.swing.JLabel lblOperatingHoursTitle2;
    private javax.swing.JLabel lblOperatingHoursTitle3;
    private javax.swing.JLabel lblPartnershipEmail;
    private javax.swing.JLabel lblSafetyMeasuresInfo1;
    private javax.swing.JLabel lblSafetyMeasuresInfo2;
    private javax.swing.JLabel lblSafetyMeasuresInfo3;
    private javax.swing.JLabel lblVisitingHourTitle1;
    private javax.swing.JLabel lblVisitingHourTitle2;
    private javax.swing.JLabel lblVisitingHourTitle3;
    private javax.swing.JLabel lblVisitorHoursInfo1;
    private javax.swing.JLabel lblVisitorHoursInfo2;
    private javax.swing.JLabel lblVisitorHoursInfo3;
    // End of variables declaration//GEN-END:variables
}
