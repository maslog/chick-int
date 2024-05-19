/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.chick_int_dtr_system;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.chick_int_dtr_system.Components.AddEmployeeModal;
import com.mycompany.chick_int_dtr_system.Components.Database;
import com.mycompany.chick_int_dtr_system.Components.DigitalClock;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Ronald
 */
public class TimeInDashboard extends javax.swing.JFrame {

    /**
     * Creates new form TimeInOut
     */
    int employee;
    DigitalClock TimeAndDate = new DigitalClock();

    public TimeInDashboard() {
        FlatLightLaf.setup();
        initComponents();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        updateTimeAndDate();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeAndDate();
            }
        });

        timer.start();

    }

    public void updateTimeAndDate() {

        DigitalClock upTimeAndDate = new DigitalClock();
        jTime.setText(upTimeAndDate.updateTime());
        jDate.setText(upTimeAndDate.updateDate());

    }

    public void checkuser() {
        if (!userID.getText().isEmpty() && !userPassword.getText().isEmpty() && !userPassword.getText().equals(" ") && !userID.getText().equals(" ") && !userID.getText().isBlank() && !userPassword.getText().isBlank()) {
            if (timeInForUser()) {
                insertData();
                new Dashboard().setVisible(rootPaneCheckingEnabled);

                this.dispose();
                JOptionPane.showMessageDialog(this, "Time-In Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Wrong Credentials!", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Fill in the blank!", "Warning", JOptionPane.ERROR_MESSAGE);

        }
    }

    

    public void insertData() {
        try {
            String firstname = "Hello";
            String middlename = "Hello";
            String lastname = "Hello";
            String timein = String.valueOf( TimeAndDate.updateTime());
            String date = String.valueOf(TimeAndDate.updateDate());
            Connection conn = Database.getConnection();
            String query = "INSERT INTO `db_chick_int`.`record` (`timeIn`, `date`, `idemployee`) VALUES ('" + timein + "', '" + date + "', '" + employee + "');";
            Statement statement = conn.createStatement();
            statement.execute(query);

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean timeInForUser() {
        try {

            Connection conn = Database.getConnection();
//          String query = "INSERT INTO `db_chick_int`.`employee` (`firstname`, `middlename`, `lastname`) VALUES ('" + firstname + "', '" + middlename + "', '" + lastname + "');";
            String query = "SELECT `idemployee`, `password` FROM db_chick_int.employee WHERE isActive = 1";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String id = String.valueOf(rs.getString("idemployee"));
                String pass = rs.getString("password");

                if (userID.getText().equals(id) && userPassword.getText().equals(pass)) {
                    employee = rs.getInt("idemployee");
                    return true;
                }

            }

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        timeInBackBTN = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        timeInBTN = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        userID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        userPassword = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jDate = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Chick-Int");
        setBackground(new java.awt.Color(255, 206, 0));
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBackground(new java.awt.Color(255, 206, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(743, 400));

        jPanel2.setBackground(new java.awt.Color(248, 98, 24));
        jPanel2.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(350, 100));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 370));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(248, 98, 24));
        jPanel3.setMinimumSize(new java.awt.Dimension(40, 40));
        jPanel3.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        timeInBackBTN.setBackground(new java.awt.Color(248, 98, 24));
        timeInBackBTN.setForeground(new java.awt.Color(248, 98, 24));
        timeInBackBTN.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\arrow_arrows_back_direction_left_navigation_right_icon_123237.png")); // NOI18N
        timeInBackBTN.setBorderPainted(false);
        timeInBackBTN.setMaximumSize(new java.awt.Dimension(32, 32));
        timeInBackBTN.setMinimumSize(new java.awt.Dimension(32, 32));
        timeInBackBTN.setPreferredSize(new java.awt.Dimension(32, 32));
        timeInBackBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeInBackBTNActionPerformed(evt);
            }
        });
        jPanel3.add(timeInBackBTN);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(344, 100));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(248, 98, 24));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel5.setPreferredSize(new java.awt.Dimension(344, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TIME IN");
        jPanel5.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBackground(new java.awt.Color(248, 98, 24));
        jPanel6.setMaximumSize(new java.awt.Dimension(100, 100));
        jPanel6.setMinimumSize(new java.awt.Dimension(100, 10));
        jPanel6.setName(""); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 10));
        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWidths = new int[] {0, 0, 0};
        jPanel6Layout.rowHeights = new int[] {0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 0};
        jPanel6.setLayout(jPanel6Layout);

        timeInBTN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        timeInBTN.setText("TIME IN");
        timeInBTN.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        timeInBTN.setMaximumSize(new java.awt.Dimension(200, 40));
        timeInBTN.setMinimumSize(new java.awt.Dimension(200, 40));
        timeInBTN.setPreferredSize(new java.awt.Dimension(200, 40));
        timeInBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeInBTNActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 32;
        jPanel6.add(timeInBTN, gridBagConstraints);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel2, gridBagConstraints);

        userID.setColumns(13);
        userID.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(userID, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(jLabel3, gridBagConstraints);

        userPassword.setColumns(13);
        userPassword.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(userPassword, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(248, 98, 24));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 44;
        jPanel6.add(jPanel7, gridBagConstraints);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel8.setMinimumSize(new java.awt.Dimension(100, 260));
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 260));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 206, 0));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jDate.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jDate.setText("00-00-00");
        jPanel9.add(jDate, java.awt.BorderLayout.PAGE_START);

        jPanel8.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jPanel10.setBackground(new java.awt.Color(255, 206, 0));
        jPanel10.setMinimumSize(new java.awt.Dimension(100, 300));
        jPanel10.setPreferredSize(new java.awt.Dimension(1395, 300));

        jTime.setFont(new java.awt.Font("Segoe UI", 1, 200)); // NOI18N
        jTime.setForeground(new java.awt.Color(248, 103, 32));
        jTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTime.setText("00:00:00");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1285, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTime, javax.swing.GroupLayout.PREFERRED_SIZE, 1395, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTime, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel8.add(jPanel10, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void timeInBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeInBTNActionPerformed
        // TODO add your handling code here:
        checkuser();
    }//GEN-LAST:event_timeInBTNActionPerformed

    private void timeInBackBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeInBackBTNActionPerformed
        // TODO add your handling code here:
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_timeInBackBTNActionPerformed

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
            java.util.logging.Logger.getLogger(TimeInDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimeInDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimeInDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimeInDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeInDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jTime;
    private javax.swing.JButton timeInBTN;
    private javax.swing.JButton timeInBackBTN;
    private javax.swing.JTextField userID;
    private javax.swing.JPasswordField userPassword;
    // End of variables declaration//GEN-END:variables
}
