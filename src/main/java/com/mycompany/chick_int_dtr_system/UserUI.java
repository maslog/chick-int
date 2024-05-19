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
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald
 */
public class UserUI extends javax.swing.JFrame {

    /**
     * Creates new form UserUI
     */
    DigitalClock TimeAndDate = new DigitalClock();

    public UserUI() {

        FlatLightLaf.setup();

        initComponents();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\440964081_363357396210951_7696104280231318921_n.png");
        this.setIconImage(icon.getImage());
        
        updateTimeAndDate();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeAndDate();
            }
        });

        timer.start();

        addDataToTable();

    }

//    public void btnDesign() {
//        ImageIcon homeIcon = new ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\assets\\icon _home simple door_.png");
//        ImageIcon settingIcon = new ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\assets\\icon _settings_.png");
//
//        homeBTN.setIcon((Icon) homeIcon.getImage());
//        settingBTN.setIcon((Icon) settingIcon.getImage());
//        
//        
//    }
    public void updateTimeAndDate() {

        DigitalClock upTimeAndDate = new DigitalClock();
        jTime.setText(upTimeAndDate.updateTime());
        jDate.setText(upTimeAndDate.updateDate());

    }

    public void addDataToTable() {
        String dateToday = String.valueOf(TimeAndDate.updateDate());

        try {

            Connection conn = Database.getConnection();
//          String query = "INSERT INTO `db_chick_int`.`employee` (`firstname`, `middlename`, `lastname`) VALUES ('" + firstname + "', '" + middlename + "', '" + lastname + "');";
//            String query = "SELECT * FROM db_chick_int.employee WHERE isActive = 1";
//            String query = "SELECT * FROM db_chick_int.record AS tb1 INNER JOIN db_chick_int.employee AS tb2 ON tb1.idemployee = tb2.idemployee WHERE tb1.timein = 12:47:03 AM ORDER BY tb1.idtime DESC ";
            String query = "SELECT * FROM db_chick_int.record AS tb1 INNER JOIN db_chick_int.employee AS tb2 ON tb1.idemployee = tb2.idemployee WHERE tb1.date = '"+dateToday+"' ORDER BY tb1.idtime DESC ";

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            DefaultTableModel tblModel = (DefaultTableModel) jTimeTable.getModel();
            tblModel.setRowCount(0);

//            int cols = rsmd.getColumnCount();
//            String[] colName = new String[cols];
//            for(int i =0; i < cols; i++){
//                colName[i] = rsmd.getColumnName(i+1);
//            }
//            tblModel.setColumnIdentifiers(colName);
            String num, id, firstname, middlename, lastname, name, timein, timeout, date;
            int no = 0;
            while (rs.next()) {
                id = String.valueOf(rs.getInt(1));
                firstname = rs.getString("firstname");
                middlename = rs.getString("middlename");
                lastname = rs.getString("lastname");
                timein = rs.getString("timein");
                timeout = rs.getString("timeout");
                date = rs.getString("date");

                name = firstname + " " + middlename + " " + lastname;
                no += 1;
                num = String.valueOf(no);
                System.out.println(firstname);
                String[] data = {num, name, timein, timeout, date};

                tblModel.addRow(data);

            }

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
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

        container = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        homeBTN = new javax.swing.JButton();
        hero = new javax.swing.JPanel();
        time = new javax.swing.JPanel();
        jTime = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        timeInOut = new javax.swing.JPanel();
        jDate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        list = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTimeTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chick-Int");
        setSize(new java.awt.Dimension(1100, 800));

        container.setBackground(new java.awt.Color(255, 255, 255));
        container.setLayout(new java.awt.BorderLayout());

        header.setBackground(new java.awt.Color(255, 206, 0));
        header.setMaximumSize(new java.awt.Dimension(1395, 60));
        header.setMinimumSize(new java.awt.Dimension(1395, 60));
        header.setPreferredSize(new java.awt.Dimension(1395, 60));
        header.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 206, 0));
        jPanel6.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        homeBTN.setBackground(new java.awt.Color(255, 206, 0));
        homeBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        homeBTN.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\home_icon-icons.com_73532.png")); // NOI18N
        homeBTN.setBorderPainted(false);
        homeBTN.setMaximumSize(new java.awt.Dimension(32, 32));
        homeBTN.setMinimumSize(new java.awt.Dimension(32, 32));
        homeBTN.setPreferredSize(new java.awt.Dimension(32, 32));
        homeBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBTNActionPerformed(evt);
            }
        });
        jPanel6.add(homeBTN);

        header.add(jPanel6, java.awt.BorderLayout.LINE_END);

        container.add(header, java.awt.BorderLayout.PAGE_START);

        hero.setBackground(new java.awt.Color(255, 255, 255));
        hero.setLayout(new java.awt.BorderLayout());

        time.setBackground(new java.awt.Color(255, 206, 0));
        time.setMinimumSize(new java.awt.Dimension(1395, 200));
        time.setPreferredSize(new java.awt.Dimension(1395, 200));
        time.setLayout(new java.awt.BorderLayout());

        jTime.setFont(new java.awt.Font("Segoe UI", 1, 200)); // NOI18N
        jTime.setForeground(new java.awt.Color(248, 103, 32));
        jTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTime.setText("00:00:00");
        time.add(jTime, java.awt.BorderLayout.CENTER);

        hero.add(time, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 206, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        timeInOut.setBackground(new java.awt.Color(255, 206, 0));
        timeInOut.setMinimumSize(new java.awt.Dimension(1395, 200));
        timeInOut.setPreferredSize(new java.awt.Dimension(1395, 200));
        timeInOut.setLayout(new java.awt.BorderLayout());

        jDate.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jDate.setText("00-00-00");
        timeInOut.add(jDate, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 206, 0));
        jPanel2.setMinimumSize(new java.awt.Dimension(1395, 120));
        jPanel2.setPreferredSize(new java.awt.Dimension(1395, 120));

        jButton1.setBackground(new java.awt.Color(0, 204, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("TIME IN");
        jButton1.setToolTipText("");
        jButton1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMargin(new java.awt.Insets(10, 14, 10, 14));
        jButton1.setMaximumSize(new java.awt.Dimension(400, 100));
        jButton1.setMinimumSize(new java.awt.Dimension(400, 100));
        jButton1.setPreferredSize(new java.awt.Dimension(400, 100));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("TIME OUT");
        jButton2.setToolTipText("");
        jButton2.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMargin(new java.awt.Insets(10, 14, 10, 14));
        jButton2.setMaximumSize(new java.awt.Dimension(400, 100));
        jButton2.setMinimumSize(new java.awt.Dimension(400, 100));
        jButton2.setPreferredSize(new java.awt.Dimension(400, 100));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        timeInOut.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.add(timeInOut, java.awt.BorderLayout.PAGE_START);

        list.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 206, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        list.add(jPanel3, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(255, 206, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        list.add(jPanel4, java.awt.BorderLayout.LINE_END);

        jList.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setBackground(new java.awt.Color(255, 206, 0));
        jScrollPane1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());

        jTimeTable.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jTimeTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "NAME", "TIME IN", "TIME OUT", "DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTimeTable.setGridColor(new java.awt.Color(51, 51, 51));
        jTimeTable.setRowHeight(40);
        jTimeTable.setRowSelectionAllowed(true);
        jTimeTable.setSelectionBackground(new java.awt.Color(255, 206, 0));
        jTimeTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTimeTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTimeTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTimeTable.setShowGrid(false);
        jTimeTable.setShowHorizontalLines(true);
        jTimeTable.getTableHeader().setResizingAllowed(false);
        jTimeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTimeTable);
        if (jTimeTable.getColumnModel().getColumnCount() > 0) {
            jTimeTable.getColumnModel().getColumn(0).setResizable(false);
            jTimeTable.getColumnModel().getColumn(1).setResizable(false);
            jTimeTable.getColumnModel().getColumn(2).setResizable(false);
            jTimeTable.getColumnModel().getColumn(3).setResizable(false);
            jTimeTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jList.add(jScrollPane1);

        list.add(jList, java.awt.BorderLayout.CENTER);

        jPanel1.add(list, java.awt.BorderLayout.CENTER);

        hero.add(jPanel1, java.awt.BorderLayout.CENTER);

        container.add(hero, java.awt.BorderLayout.CENTER);

        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here
        new TimeOut().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new TimeIn().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void homeBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBTNActionPerformed
        //Admin Login Button
        new AdminLogin().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_homeBTNActionPerformed

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
            java.util.logging.Logger.getLogger(UserUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JPanel header;
    private javax.swing.JPanel hero;
    private javax.swing.JButton homeBTN;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jDate;
    private javax.swing.JPanel jList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTime;
    private javax.swing.JTable jTimeTable;
    private javax.swing.JPanel list;
    private javax.swing.JPanel time;
    private javax.swing.JPanel timeInOut;
    // End of variables declaration//GEN-END:variables

    private ImageIcon ImageIcon(String cUsersRonaldDocumentsNetBeansProjectsChic) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
