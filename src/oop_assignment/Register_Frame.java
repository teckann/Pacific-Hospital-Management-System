/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oop_assignment;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Cynthia
 */
public class Register_Frame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Register_Frame.class.getName());
    public Register_Frame() {
        initComponents();
        this.setLocationRelativeTo(null);
        jScrollPane3.getVerticalScrollBar().setUI(Utility_Methods.createWindowsScrollBarUI());
        jScrollPane3.getHorizontalScrollBar().setUI(Utility_Methods.createWindowsScrollBarUI());
        loadSecurityQuestions();
    }

    @SuppressWarnings("unchecked")
    
    private void loadSecurityQuestions() {
        ArrayList<String> questionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("securityQuestion.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    questionList.add(parts[0] + ";" + parts[1]); 
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading security questions file.");
        }

        cboquestion1.removeAllItems();
        cboquestion2.removeAllItems();

        for (String q : questionList) {
            cboquestion1.addItem(q);
        }

        cboquestion1.addActionListener(e -> {
            String selected = (String) cboquestion1.getSelectedItem();
            cboquestion2.removeAllItems();
            for (String q : questionList) {
                if (!q.equals(selected)) {
                    cboquestion2.addItem(q);
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtname4 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCreateAccount = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnLoginHere = new javax.swing.JButton();
        lblMessage1 = new javax.swing.JLabel();
        lblMessage2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtage = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtEmailAddress = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtanswer1 = new javax.swing.JTextField();
        txtanswer2 = new javax.swing.JTextField();
        cboquestion1 = new javax.swing.JComboBox<>();
        cboquestion2 = new javax.swing.JComboBox<>();
        txtpassword = new javax.swing.JPasswordField();
        btnBackToLogin = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel14.setText("jLabel14");

        jLabel22.setText("Email Address");

        txtname4.setText("👤 Kindly fill in your full name (as per IC)");
        txtname4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtname4ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 153, 0));
        setMinimumSize(new java.awt.Dimension(800, 490));
        setResizable(false);

        jPanel7.setBackground(new java.awt.Color(239, 246, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnCreateAccount.setBackground(new java.awt.Color(37, 99, 235));
        btnCreateAccount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreateAccount.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateAccount.setText("Create Account");
        btnCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAccountActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 12, 102));
        jLabel3.setText("Password Requirements");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("• At least 1 uppercase letter (A-Z)");

        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("• At least 1 lowercase letter (a-z)");

        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("• At least 1 number (0-9)");

        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("• At least 1 symbol (!@#$%...)");

        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Already have an account?");

        btnLoginHere.setBackground(new java.awt.Color(37, 99, 235));
        btnLoginHere.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLoginHere.setForeground(new java.awt.Color(255, 255, 255));
        btnLoginHere.setText("Login Here ");
        btnLoginHere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginHereActionPerformed(evt);
            }
        });

        lblMessage1.setText(" ");

        lblMessage2.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMessage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMessage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(21, 21, 21)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnLoginHere, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreateAccount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMessage1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMessage2)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(6, 6, 6)))
                .addComponent(btnLoginHere, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 12, 102));
        jLabel17.setText("Hospital Registration");

        jLabel1.setText("Create your account to access our services");

        jLabel18.setText("Name");

        jLabel19.setText("Age");

        jLabel20.setText("Phone Number");

        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });

        jLabel21.setText("Email Address");

        txtEmailAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailAddressActionPerformed(evt);
            }
        });

        jLabel23.setText("Password");

        jLabel24.setText("Safety Question");

        txtanswer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtanswer1ActionPerformed(evt);
            }
        });

        txtanswer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtanswer2ActionPerformed(evt);
            }
        });

        cboquestion1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                     ------ Safety Question --------", "Q1: What's the city where you were born?", "Q2: What's the first concert you attended?", "Q3: What's your childhood nickname?", "Q4: What's your university name?", "Q5: What's your best friend name?" }));

        cboquestion2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                     ------ Safety Question --------", "Q1: What's the city where you were born?", "Q2: What's the first concert you attended?", "Q3: What's your childhood nickname?", "Q4: What's your university name?", "Q5: What's your best friend name?" }));

        btnBackToLogin.setText("<<<");
        btnBackToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnBackToLogin)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1))
                            .addComponent(jLabel17)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18)
                            .addComponent(txtname, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(jLabel19)
                            .addComponent(txtage, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(jLabel20)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(jLabel21)
                            .addComponent(txtEmailAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(txtanswer1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(txtanswer2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(cboquestion1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboquestion2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtpassword))))
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(btnBackToLogin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboquestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtanswer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboquestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtanswer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAccountActionPerformed
        String role = "Patient";
        String name = txtname.getText().trim();
        String password = new String(txtpassword.getPassword());
        String question1 = cboquestion1.getSelectedItem() != null ? cboquestion1.getSelectedItem().toString() : "";
        String question2 = cboquestion2.getSelectedItem() != null ? cboquestion2.getSelectedItem().toString() : "";
        String answer1 = txtanswer1.getText();
        String answer2 = txtanswer2.getText();
        String ageText = txtage.getText().trim();
        String phoneNumber = txtPhoneNumber.getText().trim();
        String emailAddress = txtEmailAddress.getText().trim();
 
        
        if (!Validation.string(name)) {
            JOptionPane.showMessageDialog(this, "Invalid name. Please enter alphabetic characters only.");
            return;
        }

        if (!Validation.age(ageText)) {
            JOptionPane.showMessageDialog(this, "Invalid age. Please enter a number between 1 and 99.");
            return;
        }

        if (!Validation.contactNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(this, "Invalid phone number. It should be 10 or 11 digits.");
            return;
        }

        if (!Validation.email(emailAddress)) {
            JOptionPane.showMessageDialog(this, "Invalid email format. Please enter a valid email.");
            return;
        }

        if (!Validation.password(password)) {
            JOptionPane.showMessageDialog(this, "Password must contain uppercase, lowercase, digit, and symbol.");
            return;
            
        }
        
        if (question1 == null || question1.isEmpty() || question2 == null || question2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select both security questions before continuing.");
            return;
        }

       
        if (answer1 == null || answer1.isEmpty() || answer2 == null || answer2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please answer both security questions before processing.");
            return;

        }

      
        JOptionPane.showMessageDialog(this, "Registration successful!");

        // {"Q1", "..."}
        String[] Q1set = question1.split(";");
        String[] Q2set = question2.split(";");
        
        String QID1 = Q1set[0];
        String QID2 = Q2set[0];
        
        String newPatientID = Utility_Methods.autoGenerateID("patientData.txt", "P");
        
        String loginLine = newPatientID + ";" + role + ";" + name + ";" + password + ";" +
                QID1 + ";" + answer1 + ";" + QID2 + ";" + answer2 + ";1";
        
        String patientLine = newPatientID + ";" + name + ";" + "-" + ";" + ageText + ";" + "-" + 
                ";" + "-" + ";" + phoneNumber + ";" + emailAddress + ";" + "-" + ";" + "-" + 
                ";-;-,-,-,-;1";

        
        File_Control.addData("loginData.txt", loginLine);
        File_Control.addData("patientData.txt", patientLine);
        
        lblMessage1.setText("          Account created successfully!");
        lblMessage2.setText("          Click the Login Button below.");
        lblMessage1.setForeground(java.awt.Color.GREEN);
        lblMessage2.setForeground(java.awt.Color.GREEN);
        
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Patient ID: " + newPatientID +
            "\nName: " + name +
            "\nPassword: " + password,
            "Registration Successful",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_btnCreateAccountActionPerformed

    private void btnLoginHereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginHereActionPerformed
        this.setVisible(false);
        new Login_Frame().setVisible(true);
    }//GEN-LAST:event_btnLoginHereActionPerformed

    private void txtname4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtname4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtname4ActionPerformed

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNumberActionPerformed

    private void txtEmailAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailAddressActionPerformed

    private void txtanswer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtanswer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtanswer1ActionPerformed

    private void txtanswer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtanswer2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtanswer2ActionPerformed

    private void btnBackToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToLoginActionPerformed
        Login_Frame loginPage = new Login_Frame();
        loginPage.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnBackToLoginActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Register_Frame().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackToLogin;
    private javax.swing.JButton btnCreateAccount;
    private javax.swing.JButton btnLoginHere;
    private javax.swing.JComboBox<String> cboquestion1;
    private javax.swing.JComboBox<String> cboquestion2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMessage1;
    private javax.swing.JLabel lblMessage2;
    private javax.swing.JTextField txtEmailAddress;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtage;
    private javax.swing.JTextField txtanswer1;
    private javax.swing.JTextField txtanswer2;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtname4;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
