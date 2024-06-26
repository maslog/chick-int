/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.chick_int_dtr_system;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.chick_int_dtr_system.Components.AddEmployeeModal;
import com.mycompany.chick_int_dtr_system.Components.Database;
import com.mycompany.chick_int_dtr_system.Components.DigitalClock;
import com.mycompany.chick_int_dtr_system.Components.experiment.reportToday;
import com.mycompany.chick_int_dtr_system.Components.updateEmployee;
import com.mycompany.chick_int_dtr_system.Components.updateForm;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.xdevapi.Result;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
    DigitalClock TimeAndDate = new DigitalClock();

    public Dashboard() {
        FlatLightLaf.setup();

        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        ScaleImg(logo);
        ScaleImg(jImageProfile);
        addDataToTable();
        dashboardTable();
        cardToday();

        ImageIcon icon = new ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\chick-int-logo.png");
        this.setIconImage(icon.getImage());

        updateTimeAndDate();
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeAndDate();
            }
        });

        timer.start();

    }

    public void ScaleImg(JLabel label) {
        ImageIcon ic = (ImageIcon) label.getIcon();
        Image scaled = ic.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
    }

    public void ScaleImg2(JLabel label) {
        ImageIcon ic = (ImageIcon) label.getIcon();
        Image scaled = ic.getImage().getScaledInstance(jImageProfile.getWidth(), jImageProfile.getHeight(), Image.SCALE_SMOOTH);
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
            String query = "SELECT * FROM db_chick_int.employee WHERE isActive = 1 ORDER BY idemployee DESC";
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
            String num, id, firstname, middlename, lastname, gender, pNumber, email, position, imagePath, streetAddress, city, region, birthdate, address;
            String age;
            int no = 0;
            while (rs.next()) {
                id = String.valueOf(rs.getInt(1));
                firstname = rs.getString(2);
                middlename = rs.getString(3);
                lastname = rs.getString(4);
                gender = rs.getString("gender");
                email = rs.getString("email");
                pNumber = rs.getString("phoneNumber");
                position = rs.getString("positionTitle");
                imagePath = rs.getString("imageUrl");
                age = String.valueOf(rs.getInt("age"));
                streetAddress = rs.getString("streetAddress");
                city = rs.getString("city");
                region = rs.getString("region");
                birthdate = rs.getString("birthdate");

                address = streetAddress + ", " + city + ", " + region;

                no += 1;
                num = String.valueOf(no);

                String[] data = {num, id, firstname, middlename, lastname, gender, pNumber, email, imagePath, age, address, birthdate};
                jCardEmployee.setText("EMPLOYEES = " + num);
                jCardEmployee2.setText("EMPLOYEES = " + num);
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
            String num, id, firstname, middlename, lastname, name, timein, timeout, date, url, address, age;
            int no = 0;
            while (rs.next()) {
                id = String.valueOf(rs.getInt("idemployee"));
                firstname = rs.getString("firstname");

                middlename = rs.getString("middlename");
                lastname = rs.getString("lastname");
                timein = rs.getString("timein");
                timeout = rs.getString("timeout");
                date = rs.getString("date");
                age = String.valueOf(rs.getInt("age"));
                url = rs.getString("imageUrl");
                address = rs.getString("streetAddress") + " " + rs.getString("city") + " " + rs.getString("region");

                name = firstname + " " + middlename + " " + lastname;
                no += 1;
                num = String.valueOf(no);
                System.out.println(firstname);
                String[] data = {num, id, name, timein, timeout, date, url, address, age};
                jCardEmployee1.setText("RECORDS = " + num);

                tblModel.addRow(data);

            }

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cardToday() {
        try {

            Connection conn = Database.getConnection();
            String query = "SELECT * FROM db_chick_int.record AS tb1 INNER JOIN db_chick_int.employee AS tb2 ON tb1.idemployee = tb2.idemployee WHERE tb1.date = '" + TimeAndDate.updateDate() + "' ORDER BY tb1.idtime DESC";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            String num;
            int no = 0;
            while (rs.next()) {

                no += 1;
                num = String.valueOf(no);
                jToday.setText("TODAY = " + num);

            }

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void search() {
//        try {
//
//            Connection conn = Database.getConnection();
//            String query = "SELECT * FROM db_chick_int.employee WHERE isActive = 1 AND firstname LIKE  %ron%";
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//
//            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
//            DefaultTableModel tblModel = (DefaultTableModel) jTableDashboard.getModel();
//            tblModel.setRowCount(0);
//
//            String num, id, firstname, middlename, lastname, gender, pNumber, email, position;
//            int no = 0;
//            while (rs.next()) {
//                id = String.valueOf(rs.getInt(1));
//                firstname = rs.getString(2);
//                middlename = rs.getString(3);
//                lastname = rs.getString(4);
//                gender = rs.getString("gender");
//                email = rs.getString("email");
//                pNumber = rs.getString("phoneNumber");
//                position = rs.getString("positionTitle");
//
//                no += 1;
//                num = String.valueOf(no);
//
//                String[] data = {num, id, firstname, middlename, lastname, gender, pNumber, email};
//                tblModel.addRow(data);
//
//            }
//
//            statement.close();
//
//            Database.closeConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void searching() {
        try {
            String searchTerm = jSearchDashboard.getText();

//            String query2 = "SELECT * FROM db_chick_int.employee WHERE isActive = 1 AND "
//                    + "(`idtime` LIKE '%" + searchTerm + "%' OR "
//                    + "`timeIn` LIKE '%" + searchTerm + "%' OR "
//                    + "`timeOut` LIKE '%" + searchTerm + "%' OR "
//                    + "`date` LIKE '%" + searchTerm + "%' OR "
//                    + "`idemployee` LIKE '%" + searchTerm + "%' OR ";
            String query2 = "SELECT * FROM db_chick_int.record r "
                    + "JOIN db_chick_int.employee e ON r.idemployee = e.idemployee "
                    + "WHERE e.isActive = 1 AND ("
                    + "r.idtime LIKE '%" + searchTerm + "%' OR "
                    + "r.timeIn LIKE '%" + searchTerm + "%' OR "
                    + "r.timeOut LIKE '%" + searchTerm + "%' OR "
                    + "r.date LIKE '%" + searchTerm + "%' OR "
                    + "r.idemployee LIKE '%" + searchTerm + "%' OR "
                    + "e.firstname LIKE '%" + searchTerm + "%' OR "
                    + "e.middlename LIKE '%" + searchTerm + "%' OR "
                    + "e.lastname LIKE '%" + searchTerm + "%' OR "
                    + "e.gender LIKE '%" + searchTerm + "%' OR "
                    + "e.streetAddress LIKE '%" + searchTerm + "%' OR "
                    + "e.city LIKE '%" + searchTerm + "%' OR "
                    + "e.region LIKE '%" + searchTerm + "%' OR "
                    + "e.postalCode LIKE '%" + searchTerm + "%' OR "
                    + "e.phoneNumber LIKE '%" + searchTerm + "%' OR "
                    + "e.email LIKE '%" + searchTerm + "%' OR "
                    + "e.emergencyName LIKE '%" + searchTerm + "%' OR "
                    + "e.emergencyNumber LIKE '%" + searchTerm + "%' OR "
                    + "e.positionTitle LIKE '%" + searchTerm + "%' OR "
                    + "e.employmentType LIKE '%" + searchTerm + "%')";
            Connection conn = Database.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query2);

//            while (rs.next()) {
//                String id = String.valueOf(rs.getString("idemployee"));
//                System.out.println(rs.getString("firstname"));
//
//            }
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            DefaultTableModel tblModel = (DefaultTableModel) jTableDashboard.getModel();
            tblModel.setRowCount(0);

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

    public void searchingEmp() {
        try {
            String searchTerm = jSearchEmp.getText();

            String query2 = "SELECT * FROM db_chick_int.employee WHERE isActive = 1 AND "
                    + "(`firstname` LIKE '%" + searchTerm + "%' OR "
                    + "`middlename` LIKE '%" + searchTerm + "%' OR "
                    + "`lastname` LIKE '%" + searchTerm + "%' OR "
                    + "`gender` LIKE '%" + searchTerm + "%' OR "
                    + "`streetAddress` LIKE '%" + searchTerm + "%' OR "
                    + "`city` LIKE '%" + searchTerm + "%' OR "
                    + "`region` LIKE '%" + searchTerm + "%' OR "
                    + "`postalCode` LIKE '%" + searchTerm + "%' OR "
                    + "`phoneNumber` LIKE '%" + searchTerm + "%' OR "
                    + "`email` LIKE '%" + searchTerm + "%' OR "
                    + "`emergencyName` LIKE '%" + searchTerm + "%' OR "
                    + "`emergencyNumber` LIKE '%" + searchTerm + "%' OR "
                    + "`positionTitle` LIKE '%" + searchTerm + "%' OR "
                    + "`employmentType` LIKE '%" + searchTerm + "%')";

            Connection conn = Database.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query2);

//            while (rs.next()) {
//                String id = String.valueOf(rs.getString("idemployee"));
//                System.out.println(rs.getString("firstname"));
//
//            }
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            DefaultTableModel tblModel = (DefaultTableModel) jTableEmployee.getModel();
            tblModel.setRowCount(0);

            String num, id, firstname, middlename, lastname, gender, pNumber, email, position;
            int no = 0;
            while (rs.next()) {
                id = String.valueOf(rs.getInt(1));
                firstname = rs.getString(2);
                middlename = rs.getString(3);
                lastname = rs.getString(4);
                gender = rs.getString("gender");
                email = rs.getString("email");
                pNumber = rs.getString("phoneNumber");
                position = rs.getString("positionTitle");

                no += 1;
                num = String.valueOf(no);

                String[] data = {num, id, firstname, middlename, lastname, gender, pNumber, email};
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

    public void deleteData() {
        try {
            int row = jTableEmployee.getSelectedRow();
            DefaultTableModel tblmodel = (DefaultTableModel) jTableEmployee.getModel();
            String id = String.valueOf(tblmodel.getValueAt(row, 1));

            String fname = String.valueOf(tblmodel.getValueAt(row, 2));
            String lname = String.valueOf(tblmodel.getValueAt(row, 4));
            String name = fname + " " + lname;

            int i = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + id + " " + name + "?", "Delete Data", JOptionPane.YES_NO_OPTION);

            if (i == 0) {
                Connection conn = Database.getConnection();
                //String query = "INSERT INTO `db_chick_int`.`record` (`timeIn`, `date`, `idemployee`) VALUES ('" + timein + "', '" + date + "', '" + employee + "');";
                //String query = "UPDATE `record` SET `timeout` = '"+timeToday+"' WHERE `record`.`idtime` = 1 AND `record`.`date` = '"++"' AND  `record`.`timeout` = '0'  " ;

                String query = "UPDATE `db_chick_int`.`employee` SET `employee`.`isActive` = 0 WHERE `employee`.`idemployee` =" + id;
                Statement statement = conn.createStatement();
                statement.execute(query);

                statement.close();
                tblmodel.removeRow(jTableEmployee.getSelectedRow());
                addDataToTable();
                Database.closeConnection();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser() {

        int row = jTableEmployee.getSelectedRow();
        DefaultTableModel tblmodel = (DefaultTableModel) jTableEmployee.getModel();
        String id = String.valueOf(tblmodel.getValueAt(row, 1));

        String fname = String.valueOf(tblmodel.getValueAt(row, 2));
        String lname = String.valueOf(tblmodel.getValueAt(row, 4));
        String name = fname + " " + lname;

        int i = JOptionPane.showConfirmDialog(this, "Are you sure you want to update ID: " + id + " " + name + "?", "Delete Data", JOptionPane.CANCEL_OPTION);
        if (i == 0) {
            updateForm upF = new updateForm();
            upF.setVisible(true);
            upF.selectData(id);
            this.dispose();
        }

    }

    public void tabIndex(int i) {
        jTabbedPane1.setSelectedIndex(i);
    }

    public void showImageSelected() {
        int row = jTableEmployee.getSelectedRow();
        DefaultTableModel tblmodel = (DefaultTableModel) jTableEmployee.getModel();
        String id = String.valueOf(tblmodel.getValueAt(row, 1));
        String urll = String.valueOf(tblmodel.getValueAt(row, 8));
        String fname = String.valueOf(tblmodel.getValueAt(row, 2));
        String mname = String.valueOf(tblmodel.getValueAt(row, 3));
        String lname = String.valueOf(tblmodel.getValueAt(row, 4));
        String age = String.valueOf(tblmodel.getValueAt(row, 9));
        String address = String.valueOf(tblmodel.getValueAt(row, 10));
        String birth = String.valueOf(tblmodel.getValueAt(row, 11));
        String name = fname + " " + mname + " " + lname;
        jShowAddress.setText(address);
        jShowAge.setText(age);
        jShowName.setText(name);
//        jShowBirth.setText(birth);

        jImageProfile.setIcon(new ImageIcon("C:\\Users\\Ronald\\Desktop\\BSCSINC\\Uploads\\Profiles\\" + urll));
        ScaleImg(jImageProfile);
    }

    public void showImageSelectedDash() {
        int row = jTableDashboard.getSelectedRow();
        
        DefaultTableModel tblmodel = (DefaultTableModel) jTableDashboard.getModel();
        String id = String.valueOf(tblmodel.getValueAt(row, 1));
        String urll = String.valueOf(tblmodel.getValueAt(row, 6));
        
        System.out.println(urll);
        String name = String.valueOf(tblmodel.getValueAt(row, 2));
        String mname = String.valueOf(tblmodel.getValueAt(row, 3));
        String lname = String.valueOf(tblmodel.getValueAt(row, 4));
        String age = String.valueOf(tblmodel.getValueAt(row, 8));
        String address = String.valueOf(tblmodel.getValueAt(row, 7));
        

        jShowAddress1.setText(address);
        jShowAge1.setText(age);
        jShowName1.setText(name);
//        jShowBirth.setText(birth);

        jImageProfile1.setIcon(new ImageIcon("C:\\Users\\Ronald\\Desktop\\BSCSINC\\Uploads\\Profiles\\" + urll));
        ScaleImg(jImageProfile1);
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
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jImageProfile1 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jShowAge1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jShowAddress1 = new javax.swing.JTextField();
        jShowName1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDashboard = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        TimeInBTN = new javax.swing.JButton();
        TimeOutBTN = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        refEmp2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSearchDashboard = new javax.swing.JTextField();
        cards = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jCardEmployee = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jCardEmployee1 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jToday = new javax.swing.JLabel();
        employeePane = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jImageProfile = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jShowAge = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jShowAddress = new javax.swing.JTextField();
        jShowName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEmployee = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        addEmployeeBTN = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        refEmp = new javax.swing.JButton();
        refEmp1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jSearchEmp = new javax.swing.JTextField();
        cards1 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jCardEmployee2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chick-Int");
        setPreferredSize(new java.awt.Dimension(1100, 800));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setMinimumSize(new java.awt.Dimension(100, 150));
        header.setPreferredSize(new java.awt.Dimension(779, 150));
        header.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(248, 103, 32));
        jPanel5.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 150));

        jLabel3.setBackground(new java.awt.Color(248, 103, 32));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);

        logo.setBackground(new java.awt.Color(255, 255, 255));
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\chick-int-logo.png")); // NOI18N
        logo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        jButton2.setBackground(new java.awt.Color(248, 103, 32));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\Vector.png")); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setMaximumSize(new java.awt.Dimension(100, 40));
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
            .addGap(0, 10, Short.MAX_VALUE)
        );

        header.add(jPanel8, java.awt.BorderLayout.SOUTH);

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        hero.setBackground(new java.awt.Color(255, 206, 0));
        hero.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 206, 0));
        jTabbedPane1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jTabbedPane1.setForeground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);

        dashboardPane.setBackground(new java.awt.Color(255, 206, 0));
        dashboardPane.setLayout(new javax.swing.BoxLayout(dashboardPane, javax.swing.BoxLayout.LINE_AXIS));

        jSplitPane1.setDividerLocation(300);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel29.setLayout(new java.awt.BorderLayout());

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setLayout(new java.awt.GridBagLayout());

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel31.setMinimumSize(new java.awt.Dimension(250, 250));
        jPanel31.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel31.setLayout(new java.awt.BorderLayout());

        jImageProfile1.setBackground(new java.awt.Color(255, 255, 255));
        jImageProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jImageProfile1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\chick-int-logo.png")); // NOI18N
        jPanel31.add(jImageProfile1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel30.add(jPanel31, gridBagConstraints);

        jPanel29.add(jPanel30, java.awt.BorderLayout.PAGE_START);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setLayout(new java.awt.BorderLayout());

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setMinimumSize(new java.awt.Dimension(302, 250));
        jPanel24.setPreferredSize(new java.awt.Dimension(302, 250));
        jPanel24.setLayout(new java.awt.GridBagLayout());

        jShowAge1.setColumns(16);
        jShowAge1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jShowAge1.setMinimumSize(new java.awt.Dimension(300, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel24.add(jShowAge1, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("AGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 1);
        jPanel24.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 1);
        jPanel24.add(jLabel10, gridBagConstraints);

        jShowAddress1.setColumns(16);
        jShowAddress1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jShowAddress1.setMinimumSize(new java.awt.Dimension(300, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel24.add(jShowAddress1, gridBagConstraints);

        jShowName1.setColumns(16);
        jShowName1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jShowName1.setMinimumSize(new java.awt.Dimension(300, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel24.add(jShowName1, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("NAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 1);
        jPanel24.add(jLabel11, gridBagConstraints);

        jPanel32.add(jPanel24, java.awt.BorderLayout.PAGE_START);

        jPanel29.add(jPanel32, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel29, java.awt.BorderLayout.CENTER);

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
                "#", "ID", "Name", "Time In", "Time Out", "Date", "url", "address", "age"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDashboard.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableDashboard.setRowHeight(40);
        jTableDashboard.setRowMargin(10);
        jTableDashboard.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableDashboard.setShowHorizontalLines(true);
        jTableDashboard.getTableHeader().setResizingAllowed(false);
        jTableDashboard.getTableHeader().setReorderingAllowed(false);
        jTableDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDashboardMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDashboard);
        if (jTableDashboard.getColumnModel().getColumnCount() > 0) {
            jTableDashboard.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTableDashboard.getColumnModel().getColumn(1).setPreferredWidth(5);
            jTableDashboard.getColumnModel().getColumn(6).setMinWidth(0);
            jTableDashboard.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTableDashboard.getColumnModel().getColumn(6).setMaxWidth(0);
            jTableDashboard.getColumnModel().getColumn(7).setMinWidth(0);
            jTableDashboard.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTableDashboard.getColumnModel().getColumn(7).setMaxWidth(0);
            jTableDashboard.getColumnModel().getColumn(8).setMinWidth(0);
            jTableDashboard.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTableDashboard.getColumnModel().getColumn(8).setMaxWidth(0);
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
        jPanel10.setMinimumSize(new java.awt.Dimension(300, 100));
        jPanel10.setPreferredSize(new java.awt.Dimension(300, 100));
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
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 5));

        refEmp2.setBackground(new java.awt.Color(153, 0, 153));
        refEmp2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        refEmp2.setForeground(new java.awt.Color(255, 255, 255));
        refEmp2.setText("Print Report");
        refEmp2.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        refEmp2.setMinimumSize(new java.awt.Dimension(120, 32));
        refEmp2.setPreferredSize(new java.awt.Dimension(100, 32));
        refEmp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refEmp2ActionPerformed(evt);
            }
        });
        jPanel11.add(refEmp2);

        jButton4.setBackground(new java.awt.Color(0, 153, 153));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Refresh");
        jButton4.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jButton4.setMinimumSize(new java.awt.Dimension(120, 32));
        jButton4.setPreferredSize(new java.awt.Dimension(100, 32));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton4);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Search:");
        jPanel11.add(jLabel5);

        jSearchDashboard.setColumns(20);
        jSearchDashboard.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jSearchDashboard.setActionCommand("<Not Set>");
        jSearchDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchDashboardActionPerformed(evt);
            }
        });
        jSearchDashboard.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSearchDashboardKeyPressed(evt);
            }
        });
        jPanel11.add(jSearchDashboard);

        jPanel9.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9, java.awt.BorderLayout.SOUTH);

        cards.setBackground(new java.awt.Color(255, 206, 0));
        java.awt.GridBagLayout cardsLayout = new java.awt.GridBagLayout();
        cardsLayout.columnWidths = new int[] {0, 25, 0, 25, 0};
        cardsLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        cards.setLayout(cardsLayout);

        jPanel17.setBackground(new java.awt.Color(0, 150, 255));
        jPanel17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jCardEmployee.setBackground(new java.awt.Color(153, 204, 255));
        jCardEmployee.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jCardEmployee.setForeground(new java.awt.Color(255, 255, 255));
        jCardEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCardEmployee.setText(" EMPLOYEES = 0");
        jCardEmployee.setMinimumSize(new java.awt.Dimension(300, 80));
        jCardEmployee.setPreferredSize(new java.awt.Dimension(300, 80));
        jPanel17.add(jCardEmployee, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        cards.add(jPanel17, gridBagConstraints);

        jPanel18.setBackground(new java.awt.Color(0, 150, 255));
        jPanel18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jCardEmployee1.setBackground(new java.awt.Color(255, 255, 204));
        jCardEmployee1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jCardEmployee1.setForeground(new java.awt.Color(255, 255, 255));
        jCardEmployee1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCardEmployee1.setText(" RECORD = 0");
        jCardEmployee1.setMinimumSize(new java.awt.Dimension(300, 80));
        jCardEmployee1.setPreferredSize(new java.awt.Dimension(300, 80));
        jPanel18.add(jCardEmployee1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        cards.add(jPanel18, gridBagConstraints);

        jPanel23.setBackground(new java.awt.Color(0, 150, 255));
        jPanel23.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jToday.setBackground(new java.awt.Color(102, 255, 102));
        jToday.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jToday.setForeground(new java.awt.Color(255, 255, 255));
        jToday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jToday.setText("TODAY = 0");
        jToday.setMinimumSize(new java.awt.Dimension(300, 80));
        jToday.setPreferredSize(new java.awt.Dimension(300, 80));
        jToday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTodayMouseClicked(evt);
            }
        });
        jPanel23.add(jToday, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        cards.add(jPanel23, gridBagConstraints);

        jPanel4.add(cards, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setRightComponent(jPanel2);

        dashboardPane.add(jSplitPane1);

        jTabbedPane1.addTab("Dashboard", dashboardPane);

        employeePane.setBackground(new java.awt.Color(255, 206, 0));
        employeePane.setLayout(new java.awt.BorderLayout());

        jSplitPane2.setDividerLocation(300);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel25.setLayout(new java.awt.BorderLayout());

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel26Layout = new java.awt.GridBagLayout();
        jPanel26Layout.columnWidths = new int[] {0, 0, 0};
        jPanel26Layout.rowHeights = new int[] {0};
        jPanel26.setLayout(jPanel26Layout);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel28.setMinimumSize(new java.awt.Dimension(250, 250));
        jPanel28.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel28.setLayout(new java.awt.BorderLayout());

        jImageProfile.setBackground(new java.awt.Color(255, 255, 255));
        jImageProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jImageProfile.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\chick-int-logo.png")); // NOI18N
        jPanel28.add(jImageProfile, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel26.add(jPanel28, gridBagConstraints);

        jPanel25.add(jPanel26, java.awt.BorderLayout.PAGE_START);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setLayout(new java.awt.BorderLayout());

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setMinimumSize(new java.awt.Dimension(302, 250));
        jPanel21.setPreferredSize(new java.awt.Dimension(302, 250));
        jPanel21.setLayout(new java.awt.GridBagLayout());

        jShowAge.setColumns(16);
        jShowAge.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jShowAge.setMinimumSize(new java.awt.Dimension(300, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel21.add(jShowAge, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("AGE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 1);
        jPanel21.add(jLabel4, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 1);
        jPanel21.add(jLabel7, gridBagConstraints);

        jShowAddress.setColumns(16);
        jShowAddress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jShowAddress.setMinimumSize(new java.awt.Dimension(300, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel21.add(jShowAddress, gridBagConstraints);

        jShowName.setColumns(16);
        jShowName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jShowName.setMinimumSize(new java.awt.Dimension(300, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel21.add(jShowName, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("NAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 1);
        jPanel21.add(jLabel8, gridBagConstraints);

        jPanel27.add(jPanel21, java.awt.BorderLayout.PAGE_START);

        jPanel25.add(jPanel27, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel25, java.awt.BorderLayout.CENTER);

        jSplitPane2.setLeftComponent(jPanel3);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jTableEmployee.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jTableEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableEmployee.setForeground(new java.awt.Color(0, 0, 0));
        jTableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "ID", "First Name", "Middle Name", "Last Name", "Gender", "Phone Number", "E-mail", "url", "age", "address", "birthdate"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEmployee.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableEmployee.setGridColor(new java.awt.Color(153, 153, 153));
        jTableEmployee.setRowHeight(30);
        jTableEmployee.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableEmployee.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableEmployee.setShowGrid(false);
        jTableEmployee.setShowHorizontalLines(true);
        jTableEmployee.setShowVerticalLines(false);
        jTableEmployee.getTableHeader().setReorderingAllowed(false);
        jTableEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmployeeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableEmployee);
        if (jTableEmployee.getColumnModel().getColumnCount() > 0) {
            jTableEmployee.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTableEmployee.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTableEmployee.getColumnModel().getColumn(7).setPreferredWidth(200);
            jTableEmployee.getColumnModel().getColumn(8).setMinWidth(0);
            jTableEmployee.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTableEmployee.getColumnModel().getColumn(8).setMaxWidth(0);
            jTableEmployee.getColumnModel().getColumn(9).setMinWidth(0);
            jTableEmployee.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTableEmployee.getColumnModel().getColumn(9).setMaxWidth(0);
            jTableEmployee.getColumnModel().getColumn(10).setMinWidth(0);
            jTableEmployee.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTableEmployee.getColumnModel().getColumn(10).setMaxWidth(0);
            jTableEmployee.getColumnModel().getColumn(11).setMinWidth(0);
            jTableEmployee.getColumnModel().getColumn(11).setPreferredWidth(0);
            jTableEmployee.getColumnModel().getColumn(11).setMaxWidth(0);
        }

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
        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel22.setBackground(new java.awt.Color(255, 206, 0));
        java.awt.GridBagLayout jPanel22Layout = new java.awt.GridBagLayout();
        jPanel22Layout.columnWidths = new int[] {0, 12, 0};
        jPanel22Layout.rowHeights = new int[] {0};
        jPanel22.setLayout(jPanel22Layout);

        jButton1.setBackground(new java.awt.Color(0, 204, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Update");
        jButton1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jButton1.setMinimumSize(new java.awt.Dimension(150, 38));
        jButton1.setPreferredSize(new java.awt.Dimension(150, 38));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel22.add(jButton1, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(255, 51, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete");
        jButton3.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        jButton3.setMinimumSize(new java.awt.Dimension(150, 38));
        jButton3.setPreferredSize(new java.awt.Dimension(150, 38));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel22.add(jButton3, gridBagConstraints);

        jPanel16.add(jPanel22, java.awt.BorderLayout.CENTER);

        jPanel20.setBackground(new java.awt.Color(255, 206, 0));
        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 5));

        refEmp.setBackground(new java.awt.Color(0, 153, 153));
        refEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        refEmp.setForeground(new java.awt.Color(255, 255, 255));
        refEmp.setText("Refresh");
        refEmp.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        refEmp.setMinimumSize(new java.awt.Dimension(120, 32));
        refEmp.setPreferredSize(new java.awt.Dimension(100, 32));
        refEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refEmpActionPerformed(evt);
            }
        });
        jPanel20.add(refEmp);

        refEmp1.setBackground(new java.awt.Color(153, 0, 153));
        refEmp1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        refEmp1.setForeground(new java.awt.Color(255, 255, 255));
        refEmp1.setText("Print Report");
        refEmp1.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        refEmp1.setMinimumSize(new java.awt.Dimension(120, 32));
        refEmp1.setPreferredSize(new java.awt.Dimension(100, 32));
        refEmp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refEmp1ActionPerformed(evt);
            }
        });
        jPanel20.add(refEmp1);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Search:");
        jPanel20.add(jLabel6);

        jSearchEmp.setColumns(13);
        jSearchEmp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jSearchEmp.setActionCommand("<Not Set>");
        jSearchEmp.setMinimumSize(new java.awt.Dimension(150, 35));
        jSearchEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchEmpActionPerformed(evt);
            }
        });
        jSearchEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSearchEmpKeyPressed(evt);
            }
        });
        jPanel20.add(jSearchEmp);

        jPanel16.add(jPanel20, java.awt.BorderLayout.LINE_END);

        jPanel14.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel14, java.awt.BorderLayout.SOUTH);

        cards1.setBackground(new java.awt.Color(255, 206, 0));
        java.awt.GridBagLayout cards1Layout = new java.awt.GridBagLayout();
        cards1Layout.columnWidths = new int[] {0, 25, 0};
        cards1Layout.rowHeights = new int[] {0};
        cards1.setLayout(cards1Layout);

        jPanel19.setBackground(new java.awt.Color(0, 150, 255));
        jPanel19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jCardEmployee2.setBackground(new java.awt.Color(153, 204, 255));
        jCardEmployee2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jCardEmployee2.setForeground(new java.awt.Color(255, 255, 255));
        jCardEmployee2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCardEmployee2.setText(" EMPLOYEES = 0");
        jCardEmployee2.setMinimumSize(new java.awt.Dimension(300, 80));
        jCardEmployee2.setPreferredSize(new java.awt.Dimension(300, 80));
        jPanel19.add(jCardEmployee2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        cards1.add(jPanel19, gridBagConstraints);

        jPanel13.add(cards1, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jSplitPane2.setRightComponent(jPanel12);

        employeePane.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Employee", employeePane);

        hero.add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(hero, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1500, 857));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new UserUI().setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jSearchEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchEmpKeyPressed
        // TODO add your handling code here:
        searchingEmp();
    }//GEN-LAST:event_jSearchEmpKeyPressed

    private void jSearchEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchEmpActionPerformed
        // TODO add your handling code here:
        searchingEmp();
    }//GEN-LAST:event_jSearchEmpActionPerformed

    private void refEmp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refEmp1ActionPerformed
        // TODO add your handling code here:
        try {
            MessageFormat header = new MessageFormat("CHICK-INT SYSTEM EMPLOYEE REPORT");
            MessageFormat footer = new MessageFormat("UNIVERSITY OF THE IMMACULATE CONCEPTION");
            jTableEmployee.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Cannot be Print!");
        }
    }//GEN-LAST:event_refEmp1ActionPerformed

    private void refEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refEmpActionPerformed
        // TODO add your handling code here:
        addDataToTable();
    }//GEN-LAST:event_refEmpActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        updateUser();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addEmployeeBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmployeeBTNActionPerformed
        // TODO add your handling code here:
        new AddEmployeeModal(null, true).show();

        addDataToTable();
    }//GEN-LAST:event_addEmployeeBTNActionPerformed

    private void jTodayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTodayMouseClicked
        // TODO add your handling code here:
        new reportToday().setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_jTodayMouseClicked

    private void jSearchDashboardKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchDashboardKeyPressed
        // TODO add your handling code here:

        searching();

    }//GEN-LAST:event_jSearchDashboardKeyPressed

    private void jSearchDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchDashboardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchDashboardActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dashboardTable();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void refEmp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refEmp2ActionPerformed
        // TODO add your handling code here:
        try {
            MessageFormat header = new MessageFormat("CHICK-INT SYSTEM ATTENDANCE REPORT");
            MessageFormat footer = new MessageFormat("UNIVERSITY OF THE IMMACULATE CONCEPTION");
            jTableDashboard.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Cannot be Print!");
        }
    }//GEN-LAST:event_refEmp2ActionPerformed

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

    private void jTableEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmployeeMouseClicked
        // TODO add your handling code here:
        showImageSelected();
    }//GEN-LAST:event_jTableEmployeeMouseClicked

    private void jTableDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDashboardMouseClicked
        // TODO add your handling code here:
        showImageSelectedDash();
    }//GEN-LAST:event_jTableDashboardMouseClicked

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
            java.util.logging.Logger.getLogger(Dashboard.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JPanel cards;
    private javax.swing.JPanel cards1;
    private javax.swing.JPanel dashboardPane;
    private javax.swing.JPanel employeePane;
    private javax.swing.JPanel header;
    private javax.swing.JPanel hero;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jCardEmployee;
    private javax.swing.JLabel jCardEmployee1;
    private javax.swing.JLabel jCardEmployee2;
    private javax.swing.JLabel jImageProfile;
    private javax.swing.JLabel jImageProfile1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jSearchDashboard;
    private javax.swing.JTextField jSearchEmp;
    private javax.swing.JTextField jShowAddress;
    private javax.swing.JTextField jShowAddress1;
    private javax.swing.JTextField jShowAge;
    private javax.swing.JTextField jShowAge1;
    private javax.swing.JTextField jShowName;
    private javax.swing.JTextField jShowName1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableDashboard;
    private javax.swing.JTable jTableEmployee;
    private javax.swing.JLabel jToday;
    private javax.swing.JLabel logo;
    private javax.swing.JButton refEmp;
    private javax.swing.JButton refEmp1;
    private javax.swing.JButton refEmp2;
    // End of variables declaration//GEN-END:variables
}
