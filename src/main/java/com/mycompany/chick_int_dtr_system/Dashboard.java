/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.chick_int_dtr_system;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.chick_int_dtr_system.Components.AddEmployeeModal;
import com.mycompany.chick_int_dtr_system.Components.Database;
import com.mycompany.chick_int_dtr_system.Components.DigitalClock;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.xdevapi.Result;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        FlatLightLaf.setup();

        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        ScaleImg(logo);
        addDataToTable();
        dashboardTable();
        
       

        updateTimeAndDate();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeAndDate();
            }
        });

        timer.start();

    }
    
    public void ScaleImg(JLabel label){
        ImageIcon ic = (ImageIcon) label.getIcon();
        Image scaled = ic.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
    }

    public void updateDataToRow(Object[] dataRow) {
        DefaultTableModel tblModel = (DefaultTableModel) jTableEmployee.getModel();
        tblModel.addRow(dataRow);
    }

    public void addDataToTable() {
        try {

            Connection conn = Database.getConnection();
//          String query = "INSERT INTO `db_chick_int`.`employee` (`firstname`, `middlename`, `lastname`) VALUES ('" + firstname + "', '" + middlename + "', '" + lastname + "');";
            String query = "SELECT `idemployee`, `firstname`,`middlename`, `lastname` FROM db_chick_int.employee WHERE isActive = 1";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            DefaultTableModel tblModel = (DefaultTableModel) jTableEmployee.getModel();
            tblModel.setRowCount(0);

//            int cols = rsmd.getColumnCount();
//            String[] colName = new String[cols];
//            for(int i =0; i < cols; i++){
//                colName[i] = rsmd.getColumnName(i+1);
//            }
//            tblModel.setColumnIdentifiers(colName);
            String num, id, firstname, middlename, lastname;
            int no = 0;
            while (rs.next()) {
                id = String.valueOf(rs.getInt(1));
                firstname = rs.getString(2);
                middlename = rs.getString(3);
                lastname = rs.getString(4);
                no += 1;
                num = String.valueOf(no);

                String[] data = {num, id, firstname, middlename, lastname};

                tblModel.addRow(data);

            }

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dashboardTable() {
        try {

            Connection conn = Database.getConnection();
//          String query = "INSERT INTO `db_chick_int`.`employee` (`firstname`, `middlename`, `lastname`) VALUES ('" + firstname + "', '" + middlename + "', '" + lastname + "');";
//            String query = "SELECT * FROM db_chick_int.employee WHERE isActive = 1";
            String query = "SELECT * FROM db_chick_int.record AS tb1 INNER JOIN db_chick_int.employee AS tb2 ON tb1.idemployee = tb2.idemployee ORDER BY tb1.idtime DESC";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            DefaultTableModel tblModel = (DefaultTableModel) jTableDashboard.getModel();
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
                id = String.valueOf(rs.getInt("idemployee"));
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
                String[] data = {num, id, name, timein, timeout, date};

                tblModel.addRow(data);

            }

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTimeAndDate() {

        DigitalClock upTimeAndDate = new DigitalClock();
        jLabel2.setText(upTimeAndDate.updateTime());
        jLabel1.setText(upTimeAndDate.updateDate());

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

        header = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        hero = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        dashboardPane = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDashboard = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        TimeInBTN = new javax.swing.JButton();
        TimeOutBTN = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        employeePane = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEmployee = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        addEmployeeBTN = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        reportsPane = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        settingsPane = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 800));

        header.setBackground(new java.awt.Color(248, 98, 24));
        header.setMinimumSize(new java.awt.Dimension(100, 150));
        header.setPreferredSize(new java.awt.Dimension(779, 150));
        header.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(248, 103, 32));
        jPanel5.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 150));

        jLabel3.setBackground(new java.awt.Color(248, 103, 32));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);

        logo.setBackground(new java.awt.Color(248, 103, 32));
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\system.png")); // NOI18N
        logo.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        header.add(jPanel5, java.awt.BorderLayout.LINE_START);

        jPanel6.setBackground(new java.awt.Color(248, 103, 32));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setText("Log Out");
        jButton2.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jButton2.setMinimumSize(new java.awt.Dimension(100, 40));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2);

        header.add(jPanel6, java.awt.BorderLayout.LINE_END);

        jPanel7.setBackground(new java.awt.Color(248, 103, 32));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("00:00:00");
        jPanel7.add(jLabel1, java.awt.BorderLayout.PAGE_END);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 100)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 206, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("00:00:00");
        jPanel7.add(jLabel2, java.awt.BorderLayout.CENTER);

        header.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(248, 103, 32));
        jPanel8.setMinimumSize(new java.awt.Dimension(0, 10));
        jPanel8.setName(""); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(1308, 10));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1486, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        header.add(jPanel8, java.awt.BorderLayout.SOUTH);

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        hero.setBackground(new java.awt.Color(255, 206, 0));
        hero.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 206, 0));
        jTabbedPane1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);

        dashboardPane.setBackground(new java.awt.Color(255, 206, 0));
        dashboardPane.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(300);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jTableDashboard.setAutoCreateRowSorter(true);
        jTableDashboard.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "ID", "Name", "Time In", "Time Out", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDashboard.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableDashboard.setRowHeight(40);
        jTableDashboard.setShowHorizontalLines(true);
        jTableDashboard.getTableHeader().setResizingAllowed(false);
        jTableDashboard.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableDashboard);
        if (jTableDashboard.getColumnModel().getColumnCount() > 0) {
            jTableDashboard.getColumnModel().getColumn(1).setResizable(false);
            jTableDashboard.getColumnModel().getColumn(2).setResizable(false);
            jTableDashboard.getColumnModel().getColumn(3).setResizable(false);
            jTableDashboard.getColumnModel().getColumn(4).setResizable(false);
            jTableDashboard.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 206, 0));
        jPanel4.setMinimumSize(new java.awt.Dimension(220, 150));
        jPanel4.setName(""); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(1175, 150));
        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel9.setMinimumSize(new java.awt.Dimension(200, 40));
        jPanel9.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 206, 0));
        jPanel10.setMinimumSize(new java.awt.Dimension(250, 100));
        jPanel10.setPreferredSize(new java.awt.Dimension(250, 100));
        java.awt.GridBagLayout jPanel10Layout = new java.awt.GridBagLayout();
        jPanel10Layout.columnWidths = new int[] {0, 2, 0, 2, 0, 2, 0};
        jPanel10Layout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        jPanel10.setLayout(jPanel10Layout);

        TimeInBTN.setBackground(new java.awt.Color(0, 204, 51));
        TimeInBTN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TimeInBTN.setForeground(new java.awt.Color(255, 255, 255));
        TimeInBTN.setText("Time In");
        TimeInBTN.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        TimeInBTN.setMinimumSize(new java.awt.Dimension(120, 32));
        TimeInBTN.setPreferredSize(new java.awt.Dimension(120, 32));
        TimeInBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeInBTNActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel10.add(TimeInBTN, gridBagConstraints);

        TimeOutBTN.setBackground(new java.awt.Color(255, 0, 51));
        TimeOutBTN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TimeOutBTN.setForeground(new java.awt.Color(255, 255, 255));
        TimeOutBTN.setText("Time Out");
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowColor(new java.awt.Color(255, 255, 255));
        TimeOutBTN.setBorder(dropShadowBorder1);
        TimeOutBTN.setMinimumSize(new java.awt.Dimension(120, 32));
        TimeOutBTN.setPreferredSize(new java.awt.Dimension(120, 32));
        TimeOutBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeOutBTNActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel10.add(TimeOutBTN, gridBagConstraints);

        jPanel9.add(jPanel10, java.awt.BorderLayout.WEST);

        jPanel11.setBackground(new java.awt.Color(255, 206, 0));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Search:");
        jPanel11.add(jLabel5);

        jTextField1.setColumns(20);
        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField1);

        jPanel9.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9, java.awt.BorderLayout.SOUTH);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setRightComponent(jPanel2);

        dashboardPane.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Dashboard", dashboardPane);

        employeePane.setBackground(new java.awt.Color(255, 206, 0));
        employeePane.setLayout(new java.awt.BorderLayout());

        jSplitPane2.setDividerLocation(300);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(jPanel3);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jTableEmployee.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jTableEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableEmployee.setForeground(new java.awt.Color(0, 0, 0));
        jTableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "ID", "First Name", "Middle Name", "Last Name", "Gender", "Phone Number", "E-mail", "Position", "Action"
            }
        ));
        jTableEmployee.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableEmployee.setEditingColumn(0);
        jTableEmployee.setEditingRow(0);
        jTableEmployee.setEnabled(false);
        jTableEmployee.setFillsViewportHeight(true);
        jTableEmployee.setGridColor(new java.awt.Color(153, 153, 153));
        jTableEmployee.setRowHeight(30);
        jTableEmployee.setShowGrid(true);
        jTableEmployee.setShowVerticalLines(false);
        jTableEmployee.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableEmployee);

        jPanel12.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel13.setBackground(new java.awt.Color(255, 206, 0));
        jPanel13.setMinimumSize(new java.awt.Dimension(220, 150));
        jPanel13.setName(""); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(1175, 150));
        jPanel13.setRequestFocusEnabled(false);
        jPanel13.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel14.setMinimumSize(new java.awt.Dimension(200, 40));
        jPanel14.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel15.setBackground(new java.awt.Color(255, 206, 0));
        jPanel15.setMinimumSize(new java.awt.Dimension(250, 100));
        jPanel15.setPreferredSize(new java.awt.Dimension(250, 100));
        jPanel15.setLayout(new java.awt.GridBagLayout());

        addEmployeeBTN.setBackground(new java.awt.Color(0, 102, 153));
        addEmployeeBTN.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addEmployeeBTN.setForeground(new java.awt.Color(255, 255, 255));
        addEmployeeBTN.setText("Add Employee");
        addEmployeeBTN.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        addEmployeeBTN.setMinimumSize(new java.awt.Dimension(150, 38));
        addEmployeeBTN.setPreferredSize(new java.awt.Dimension(150, 38));
        addEmployeeBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmployeeBTNActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel15.add(addEmployeeBTN, gridBagConstraints);

        jPanel14.add(jPanel15, java.awt.BorderLayout.WEST);

        jPanel16.setBackground(new java.awt.Color(255, 206, 0));
        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Search:");
        jPanel16.add(jLabel9);

        jTextField2.setColumns(20);
        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel16.add(jTextField2);

        jPanel14.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel14, java.awt.BorderLayout.SOUTH);

        jPanel12.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jSplitPane2.setRightComponent(jPanel12);

        employeePane.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Employee", employeePane);

        reportsPane.setBackground(new java.awt.Color(255, 206, 0));

        jLabel7.setText("Reports");

        javax.swing.GroupLayout reportsPaneLayout = new javax.swing.GroupLayout(reportsPane);
        reportsPane.setLayout(reportsPaneLayout);
        reportsPaneLayout.setHorizontalGroup(
            reportsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPaneLayout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(996, Short.MAX_VALUE))
        );
        reportsPaneLayout.setVerticalGroup(
            reportsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPaneLayout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel7)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reports", reportsPane);

        settingsPane.setBackground(new java.awt.Color(255, 206, 0));

        jLabel8.setText("Settings");

        javax.swing.GroupLayout settingsPaneLayout = new javax.swing.GroupLayout(settingsPane);
        settingsPane.setLayout(settingsPaneLayout);
        settingsPaneLayout.setHorizontalGroup(
            settingsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPaneLayout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(995, Short.MAX_VALUE))
        );
        settingsPaneLayout.setVerticalGroup(
            settingsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPaneLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel8)
                .addContainerGap(301, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Settings", settingsPane);

        hero.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(hero, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1500, 636));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TimeOutBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimeOutBTNActionPerformed
        // TODO add your handling code here:
        new TimeOutDashboard().setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_TimeOutBTNActionPerformed

    private void TimeInBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimeInBTNActionPerformed
        // TODO add your handling code here:
        new TimeInDashboard().setVisible(rootPaneCheckingEnabled);
        this.dispose();

    }//GEN-LAST:event_TimeInBTNActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new UserUI().setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void addEmployeeBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmployeeBTNActionPerformed
        // TODO add your handling code here:
        new AddEmployeeModal(null, true).show();

        addDataToTable();

    }//GEN-LAST:event_addEmployeeBTNActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TimeInBTN;
    private javax.swing.JButton TimeOutBTN;
    private javax.swing.JButton addEmployeeBTN;
    private javax.swing.JPanel dashboardPane;
    private javax.swing.JPanel employeePane;
    private javax.swing.JPanel header;
    private javax.swing.JPanel hero;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableDashboard;
    private javax.swing.JTable jTableEmployee;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel reportsPane;
    private javax.swing.JPanel settingsPane;
    // End of variables declaration//GEN-END:variables
}
