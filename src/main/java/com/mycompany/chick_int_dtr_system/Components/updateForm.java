/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.chick_int_dtr_system.Components;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.chick_int_dtr_system.Dashboard;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald
 */
public class updateForm extends javax.swing.JFrame {

    /**
     * Creates new form updateForm
     *
     */
    String idEmpl;

    public updateForm() {
        FlatLightLaf.setup();

        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        jScrollPane2.getVerticalScrollBar().setUnitIncrement(9);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\440964081_363357396210951_7696104280231318921_n.png");
        this.setIconImage(icon.getImage());
    }

    public void selectData(String idemp) {
        try {
            idEmpl = idemp;
            Connection conn = Database.getConnection();
            String query = "SELECT * FROM db_chick_int.employee WHERE idemployee = " + idemp;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

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

                System.out.println(num + " " + id + " " + firstname);

                jFirstName.setText(rs.getString("firstname"));
                jMiddleName.setText(rs.getString("middlename"));
                jLastName.setText(rs.getString("lastname"));
                jAge.setText(String.valueOf(rs.getInt("age"))); // Convert int to String
                jGender.setText(rs.getString("gender"));
                //jBirth.setDate(rs.getString("date")); // Convert String to Date if using JDateChooser or similar
                setDateFromString(rs.getString("birthdate"), jBirth);
                jStreetAddress.setText(rs.getString("streetAddress"));
                jCity.setText(rs.getString("city"));
                jRegion.setText(rs.getString("region"));
                jPostalCode.setText(rs.getString("postalCode"));
                jPhoneNumber.setText(rs.getString("phoneNumber"));
                jEmail.setText(rs.getString("email"));
                jEmergencyName.setText(rs.getString("emergencyName"));
                jEmergencyNumber.setText(rs.getString("emergencyNumber"));
                jPositionTitle.setText(rs.getString("positionTitle"));
                jEmploymentType.setText(rs.getString("employmentType"));
                jPassword.setText(rs.getString("password"));

            }

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setDateFromString(String dateStr, JDateChooser dateChooser) {
        // Define possible date formats
        String[] dateFormats = {"EEE MMM dd HH:mm:ss z yyyy", "yyyy-MM-dd"};
        Date date = null;

        // Try parsing the date string with each format
        for (String format : dateFormats) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat(format);
                date = inputFormat.parse(dateStr);
                break; // Exit the loop if parsing is successful
            } catch (ParseException e) {
                // Continue to the next format if parsing fails
            }
        }

        // Set the parsed date on the JDateChooser if successful
        if (date != null) {
            dateChooser.setDate(date);
        } else {
            System.out.println("Failed to parse the date string: " + dateStr);
        }
    }

    public void updateData(String id) {
        try {
            String firstname = jFirstName.getText();
            String middlename = jMiddleName.getText();
            String lastname = jLastName.getText();
            int age = Integer.parseInt(jAge.getText());
            String gender = jGender.getText();
            String birth = String.valueOf(jBirth.getDate());
            String street = jStreetAddress.getText();
            String city = jCity.getText();
            String region = jRegion.getText();
            String postal = jPostalCode.getText();
            String pNumber = jPhoneNumber.getText();
            String email = jEmail.getText();
            String eName = jEmergencyName.getText();
            String eNum = jEmergencyNumber.getText();
            String pTitle = jPositionTitle.getText();
            String emType = jEmploymentType.getText();
            String pass = jPassword.getText();

            Connection conn = Database.getConnection();
            //String query = "INSERT INTO `db_chick_int`.`employee` (`firstname`, `middlename`, `lastname`) VALUES ('" + firstname + "', '" + middlename + "', '" + lastname + "');";
            String query2 = "UPDATE `db_chick_int`.`employee` SET "
                    + "`firstname` = '" + firstname + "', "
                    + "`middlename` = '" + middlename + "', "
                    + "`lastname` = '" + lastname + "', "
                    + "`age` = " + age + ", "
                    + "`gender` = '" + gender + "', "
                    + "`birthdate` = '" + birth + "', "
                    + "`streetAddress` = '" + street + "', "
                    + "`city` = '" + city + "', "
                    + "`region` = '" + region + "', "
                    + "`postalCode` = '" + postal + "', "
                    + "`phoneNumber` = '" + pNumber + "', "
                    + "`email` = '" + email + "', "
                    + "`emergencyName` = '" + eName + "', "
                    + "`emergencyNumber` = '" + eNum + "', "
                    + "`positionTitle` = '" + pTitle + "', "
                    + "`employmentType` = '" + emType + "', "
                    + "`password` = '" + pass + "' "
                    + "WHERE `idemployee` = '" + id + "'";
            Statement statement = conn.createStatement();
            statement.execute(query2);

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void checkuser() {

        if (!jFirstName.getText().isEmpty() && !jMiddleName.getText().isEmpty() && !jMiddleName.getText().equals(" ") && !jFirstName.getText().equals(" ") && !jFirstName.getText().isBlank() && !jMiddleName.getText().isBlank()
                && !jLastName.getText().isEmpty() && !jAge.getText().isEmpty() && !jAge.getText().equals(" ") && !jLastName.getText().equals(" ") && !jLastName.getText().isBlank() && !jAge.getText().isBlank()
                && !jGender.getText().isEmpty() && !jBirth.getDate().toString().isEmpty() && !jBirth.getDate().toString().equals(" ") && !jGender.getText().equals(" ") && !jGender.getText().isBlank() && !jBirth.getDate().toString().isBlank()
                && !jStreetAddress.getText().isEmpty() && !jCity.getText().isEmpty() && !jCity.getText().equals(" ") && !jStreetAddress.getText().equals(" ") && !jStreetAddress.getText().isBlank() && !jCity.getText().isBlank()
                && !jPostalCode.getText().isEmpty() && !jRegion.getText().isEmpty() && !jRegion.getText().equals(" ") && !jPostalCode.getText().equals(" ") && !jPostalCode.getText().isBlank() && !jRegion.getText().isBlank()
                && !jPhoneNumber.getText().isEmpty() && !jEmail.getText().isEmpty() && !jEmail.getText().equals(" ") && !jPhoneNumber.getText().equals(" ") && !jPhoneNumber.getText().isBlank() && !jEmail.getText().isBlank()
                && !jEmergencyName.getText().isEmpty() && !jEmergencyNumber.getText().isEmpty() && !jEmergencyNumber.getText().equals(" ") && !jEmergencyName.getText().equals(" ") && !jEmergencyName.getText().isBlank() && !jEmergencyNumber.getText().isBlank()
                && !jEmploymentType.getText().isEmpty() && !jPositionTitle.getText().isEmpty() && !jPositionTitle.getText().equals(" ") && !jEmploymentType.getText().equals(" ") && !jEmploymentType.getText().isBlank() && !jPositionTitle.getText().isBlank()
                && !jPassword.getText().isEmpty() && !jPassword.getText().equals(" ") && !jPassword.getText().isBlank()) {
            updateData(idEmpl);
            JOptionPane.showMessageDialog(this, "Updated Successfully!" + " ID: " + idEmpl);
            Dashboard d = new Dashboard();
            d.tabIndex(1);
            d.setVisible(rootPaneCheckingEnabled);

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Fill in the blank!", "Warning", JOptionPane.ERROR_MESSAGE);

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
        java.awt.GridBagConstraints gridBagConstraints;

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        jForm = new javax.swing.JPanel();
        FNameMain = new javax.swing.JPanel();
        FName = new javax.swing.JPanel();
        jFirstName = new javax.swing.JTextField();
        lFirstName = new javax.swing.JLabel();
        jMiddleName = new javax.swing.JTextField();
        lMiddleName = new javax.swing.JLabel();
        jLastName = new javax.swing.JTextField();
        lLastName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        FGenderAgeBirthMain = new javax.swing.JPanel();
        jGender = new javax.swing.JTextField();
        lGender = new javax.swing.JLabel();
        jAge = new javax.swing.JTextField();
        lAge = new javax.swing.JLabel();
        lBirth = new javax.swing.JLabel();
        jBirth = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        FAddressMain = new javax.swing.JPanel();
        FAddess = new javax.swing.JPanel();
        jStreetAddress = new javax.swing.JTextField();
        lStreet = new javax.swing.JLabel();
        jCity = new javax.swing.JTextField();
        lCity = new javax.swing.JLabel();
        jRegion = new javax.swing.JTextField();
        lRegion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPostalCode = new javax.swing.JTextField();
        lPostalCode = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        FContactMain = new javax.swing.JPanel();
        FContact = new javax.swing.JPanel();
        jPhoneNumber = new javax.swing.JTextField();
        lPhoneNumber = new javax.swing.JLabel();
        jEmail = new javax.swing.JTextField();
        lEmail = new javax.swing.JLabel();
        jEmergencyName = new javax.swing.JTextField();
        lEmergencyName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jEmergencyNumber = new javax.swing.JTextField();
        lEmergencyNumber = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        FPositionMain = new javax.swing.JPanel();
        FPosition = new javax.swing.JPanel();
        jPositionTitle = new javax.swing.JTextField();
        lPositionTitle = new javax.swing.JLabel();
        jEmploymentType = new javax.swing.JTextField();
        lEmploymentType = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lProfile = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPassword = new javax.swing.JTextField();
        lPassword = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Updatae Employee");
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(248, 98, 24));
        jPanel4.setMinimumSize(new java.awt.Dimension(747, 600));
        jPanel4.setPreferredSize(new java.awt.Dimension(747, 600));
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0};
        jPanel4Layout.rowHeights = new int[] {0};
        jPanel4.setLayout(jPanel4Layout);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setMinimumSize(new java.awt.Dimension(500, 600));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(500, 500));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 500));

        jPanel1.setMinimumSize(new java.awt.Dimension(500, 800));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 800));
        jPanel1.setLayout(new java.awt.BorderLayout());

        buttonPanel.setBackground(new java.awt.Color(255, 206, 0));
        buttonPanel.setMinimumSize(new java.awt.Dimension(171, 60));
        buttonPanel.setPreferredSize(new java.awt.Dimension(171, 60));
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        okButton.setBackground(new java.awt.Color(51, 204, 0));
        okButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        okButton.setText("OK");
        okButton.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        okButton.setMinimumSize(new java.awt.Dimension(100, 40));
        okButton.setPreferredSize(new java.awt.Dimension(100, 40));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        cancelButton.setMaximumSize(new java.awt.Dimension(100, 40));
        cancelButton.setPreferredSize(new java.awt.Dimension(100, 40));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancelButton);

        jPanel1.add(buttonPanel, java.awt.BorderLayout.PAGE_END);

        main.setBackground(new java.awt.Color(255, 206, 0));
        main.setMinimumSize(new java.awt.Dimension(500, 100));
        main.setPreferredSize(new java.awt.Dimension(100, 100));
        main.setLayout(new java.awt.BorderLayout());

        jForm.setBackground(new java.awt.Color(255, 206, 0));
        jForm.setMinimumSize(new java.awt.Dimension(4550, 900));
        jForm.setPreferredSize(new java.awt.Dimension(500, 900));
        jForm.setRequestFocusEnabled(false);

        FNameMain.setMinimumSize(new java.awt.Dimension(100, 100));
        FNameMain.setPreferredSize(new java.awt.Dimension(600, 100));
        FNameMain.setLayout(new java.awt.BorderLayout());

        FName.setBackground(new java.awt.Color(255, 206, 0));
        java.awt.GridBagLayout FNameLayout = new java.awt.GridBagLayout();
        FNameLayout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        FNameLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        FName.setLayout(FNameLayout);

        jFirstName.setColumns(12);
        jFirstName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFirstName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jFirstName.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jFirstName.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jFirstName.setMinimumSize(new java.awt.Dimension(5, 32));
        jFirstName.setOpaque(true);
        jFirstName.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        FName.add(jFirstName, gridBagConstraints);

        lFirstName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lFirstName.setText("First Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FName.add(lFirstName, gridBagConstraints);

        jMiddleName.setColumns(12);
        jMiddleName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMiddleName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMiddleName.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jMiddleName.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jMiddleName.setMinimumSize(new java.awt.Dimension(5, 32));
        jMiddleName.setOpaque(true);
        jMiddleName.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        FName.add(jMiddleName, gridBagConstraints);

        lMiddleName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lMiddleName.setText("Middle Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FName.add(lMiddleName, gridBagConstraints);

        jLastName.setColumns(12);
        jLastName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLastName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLastName.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jLastName.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jLastName.setMinimumSize(new java.awt.Dimension(5, 32));
        jLastName.setOpaque(true);
        jLastName.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 12;
        FName.add(jLastName, gridBagConstraints);

        lLastName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lLastName.setText("Last Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FName.add(lLastName, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Personal Information:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FName.add(jLabel1, gridBagConstraints);

        FNameMain.add(FName, java.awt.BorderLayout.CENTER);

        jForm.add(FNameMain);

        FGenderAgeBirthMain.setBackground(new java.awt.Color(255, 206, 0));
        FGenderAgeBirthMain.setMinimumSize(new java.awt.Dimension(600, 100));
        FGenderAgeBirthMain.setPreferredSize(new java.awt.Dimension(600, 100));
        java.awt.GridBagLayout FGenderAgeBirthMainLayout = new java.awt.GridBagLayout();
        FGenderAgeBirthMainLayout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        FGenderAgeBirthMainLayout.rowHeights = new int[] {0, 0, 0, 0, 0};
        FGenderAgeBirthMain.setLayout(FGenderAgeBirthMainLayout);

        jGender.setColumns(12);
        jGender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jGender.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jGender.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jGender.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jGender.setMinimumSize(new java.awt.Dimension(5, 32));
        jGender.setOpaque(true);
        jGender.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        FGenderAgeBirthMain.add(jGender, gridBagConstraints);

        lGender.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lGender.setText("Gender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FGenderAgeBirthMain.add(lGender, gridBagConstraints);

        jAge.setColumns(12);
        jAge.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jAge.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jAge.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jAge.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jAge.setMinimumSize(new java.awt.Dimension(5, 32));
        jAge.setOpaque(true);
        jAge.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        FGenderAgeBirthMain.add(jAge, gridBagConstraints);

        lAge.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lAge.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FGenderAgeBirthMain.add(lAge, gridBagConstraints);

        lBirth.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lBirth.setText("Birth Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FGenderAgeBirthMain.add(lBirth, gridBagConstraints);

        jBirth.setMinimumSize(new java.awt.Dimension(160, 32));
        jBirth.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 4;
        FGenderAgeBirthMain.add(jBirth, gridBagConstraints);

        jForm.add(FGenderAgeBirthMain);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setMinimumSize(new java.awt.Dimension(500, 10));
        jSeparator1.setPreferredSize(new java.awt.Dimension(500, 10));
        jForm.add(jSeparator1);

        FAddressMain.setMinimumSize(new java.awt.Dimension(600, 150));
        FAddressMain.setPreferredSize(new java.awt.Dimension(600, 150));
        FAddressMain.setLayout(new java.awt.BorderLayout());

        FAddess.setBackground(new java.awt.Color(255, 206, 0));
        FAddess.setMinimumSize(new java.awt.Dimension(296, 300));
        FAddess.setPreferredSize(new java.awt.Dimension(296, 300));
        java.awt.GridBagLayout FAddessLayout = new java.awt.GridBagLayout();
        FAddessLayout.columnWidths = new int[] {0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0};
        FAddessLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        FAddess.setLayout(FAddessLayout);

        jStreetAddress.setColumns(20);
        jStreetAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jStreetAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jStreetAddress.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jStreetAddress.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jStreetAddress.setMinimumSize(new java.awt.Dimension(160, 32));
        jStreetAddress.setOpaque(true);
        jStreetAddress.setPreferredSize(new java.awt.Dimension(160, 32));
        jStreetAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStreetAddressActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FAddess.add(jStreetAddress, gridBagConstraints);

        lStreet.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lStreet.setText("Street Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FAddess.add(lStreet, gridBagConstraints);

        jCity.setColumns(20);
        jCity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCity.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCity.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCity.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jCity.setMinimumSize(new java.awt.Dimension(160, 32));
        jCity.setOpaque(true);
        jCity.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 12;
        FAddess.add(jCity, gridBagConstraints);

        lCity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lCity.setText("City");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FAddess.add(lCity, gridBagConstraints);

        jRegion.setColumns(20);
        jRegion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRegion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jRegion.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jRegion.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jRegion.setMinimumSize(new java.awt.Dimension(160, 32));
        jRegion.setOpaque(true);
        jRegion.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        FAddess.add(jRegion, gridBagConstraints);

        lRegion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lRegion.setText("Region");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FAddess.add(lRegion, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FAddess.add(jLabel3, gridBagConstraints);

        jPostalCode.setColumns(20);
        jPostalCode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPostalCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPostalCode.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPostalCode.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jPostalCode.setMinimumSize(new java.awt.Dimension(160, 32));
        jPostalCode.setOpaque(true);
        jPostalCode.setPreferredSize(new java.awt.Dimension(160, 32));
        jPostalCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPostalCodeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 24;
        FAddess.add(jPostalCode, gridBagConstraints);

        lPostalCode.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPostalCode.setText("Postal Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FAddess.add(lPostalCode, gridBagConstraints);

        FAddressMain.add(FAddess, java.awt.BorderLayout.CENTER);

        jForm.add(FAddressMain);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setMinimumSize(new java.awt.Dimension(500, 10));
        jSeparator2.setPreferredSize(new java.awt.Dimension(500, 10));
        jForm.add(jSeparator2);

        FContactMain.setMinimumSize(new java.awt.Dimension(600, 150));
        FContactMain.setPreferredSize(new java.awt.Dimension(600, 150));
        FContactMain.setLayout(new java.awt.BorderLayout());

        FContact.setBackground(new java.awt.Color(255, 206, 0));
        FContact.setMinimumSize(new java.awt.Dimension(296, 300));
        FContact.setPreferredSize(new java.awt.Dimension(296, 300));
        java.awt.GridBagLayout FContactLayout = new java.awt.GridBagLayout();
        FContactLayout.columnWidths = new int[] {0, 2, 0, 2, 0, 2, 0, 2, 0};
        FContactLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        FContact.setLayout(FContactLayout);

        jPhoneNumber.setColumns(20);
        jPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPhoneNumber.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPhoneNumber.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPhoneNumber.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jPhoneNumber.setMinimumSize(new java.awt.Dimension(160, 32));
        jPhoneNumber.setOpaque(true);
        jPhoneNumber.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FContact.add(jPhoneNumber, gridBagConstraints);

        lPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPhoneNumber.setText("Phone Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FContact.add(lPhoneNumber, gridBagConstraints);

        jEmail.setColumns(20);
        jEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jEmail.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jEmail.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jEmail.setMinimumSize(new java.awt.Dimension(160, 32));
        jEmail.setOpaque(true);
        jEmail.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        FContact.add(jEmail, gridBagConstraints);

        lEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEmail.setText("E-mail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FContact.add(lEmail, gridBagConstraints);

        jEmergencyName.setColumns(20);
        jEmergencyName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jEmergencyName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jEmergencyName.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jEmergencyName.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jEmergencyName.setMinimumSize(new java.awt.Dimension(160, 32));
        jEmergencyName.setOpaque(true);
        jEmergencyName.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        FContact.add(jEmergencyName, gridBagConstraints);

        lEmergencyName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEmergencyName.setText("Emergency Contact Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FContact.add(lEmergencyName, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Contact:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FContact.add(jLabel4, gridBagConstraints);

        jEmergencyNumber.setColumns(20);
        jEmergencyNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jEmergencyNumber.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jEmergencyNumber.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jEmergencyNumber.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jEmergencyNumber.setMinimumSize(new java.awt.Dimension(160, 32));
        jEmergencyNumber.setOpaque(true);
        jEmergencyNumber.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 24;
        FContact.add(jEmergencyNumber, gridBagConstraints);

        lEmergencyNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEmergencyNumber.setText("Emergency Contact  Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FContact.add(lEmergencyNumber, gridBagConstraints);

        FContactMain.add(FContact, java.awt.BorderLayout.CENTER);

        jForm.add(FContactMain);

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setMinimumSize(new java.awt.Dimension(500, 10));
        jSeparator3.setPreferredSize(new java.awt.Dimension(500, 10));
        jForm.add(jSeparator3);

        FPositionMain.setMinimumSize(new java.awt.Dimension(600, 200));
        FPositionMain.setOpaque(false);
        FPositionMain.setPreferredSize(new java.awt.Dimension(600, 200));
        FPositionMain.setLayout(new java.awt.BorderLayout());

        FPosition.setBackground(new java.awt.Color(255, 206, 0));
        FPosition.setMinimumSize(new java.awt.Dimension(250, 100));
        FPosition.setPreferredSize(new java.awt.Dimension(250, 100));
        java.awt.GridBagLayout FPositionLayout = new java.awt.GridBagLayout();
        FPositionLayout.columnWidths = new int[] {0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0};
        FPositionLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        FPosition.setLayout(FPositionLayout);

        jPositionTitle.setColumns(20);
        jPositionTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPositionTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPositionTitle.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPositionTitle.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jPositionTitle.setMinimumSize(new java.awt.Dimension(160, 32));
        jPositionTitle.setOpaque(true);
        jPositionTitle.setPreferredSize(new java.awt.Dimension(160, 32));
        jPositionTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPositionTitleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FPosition.add(jPositionTitle, gridBagConstraints);

        lPositionTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPositionTitle.setText("Position Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FPosition.add(lPositionTitle, gridBagConstraints);

        jEmploymentType.setColumns(20);
        jEmploymentType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jEmploymentType.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jEmploymentType.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jEmploymentType.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jEmploymentType.setMinimumSize(new java.awt.Dimension(160, 32));
        jEmploymentType.setOpaque(true);
        jEmploymentType.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 12;
        FPosition.add(jEmploymentType, gridBagConstraints);

        lEmploymentType.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEmploymentType.setText("Employment Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FPosition.add(lEmploymentType, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Position Information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FPosition.add(jLabel5, gridBagConstraints);

        lProfile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lProfile.setText("Choose Profile");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 48;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FPosition.add(lProfile, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Select Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 52;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FPosition.add(jButton1, gridBagConstraints);

        jPassword.setColumns(20);
        jPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPassword.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPassword.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jPassword.setMinimumSize(new java.awt.Dimension(160, 32));
        jPassword.setOpaque(true);
        jPassword.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 52;
        FPosition.add(jPassword, gridBagConstraints);

        lPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPassword.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 48;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FPosition.add(lPassword, gridBagConstraints);

        FPositionMain.add(FPosition, java.awt.BorderLayout.CENTER);

        jForm.add(FPositionMain);

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setMinimumSize(new java.awt.Dimension(500, 10));
        jSeparator4.setPreferredSize(new java.awt.Dimension(500, 10));
        jForm.add(jSeparator4);

        main.add(jForm, java.awt.BorderLayout.CENTER);

        jPanel1.add(main, java.awt.BorderLayout.CENTER);

        jScrollPane2.setViewportView(jPanel1);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 206, 0));
        jPanel3.setMinimumSize(new java.awt.Dimension(251, 80));
        jPanel3.setPreferredSize(new java.awt.Dimension(586, 80));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 206, 0));
        jPanel5.setMinimumSize(new java.awt.Dimension(298, 48));
        jPanel5.setPreferredSize(new java.awt.Dimension(298, 48));

        jLabel2.setBackground(new java.awt.Color(248, 98, 24));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(248, 98, 24));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Update Employee");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 206, 0));
        jPanel6.setMinimumSize(new java.awt.Dimension(32, 32));
        jPanel6.setPreferredSize(new java.awt.Dimension(32, 32));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jButton2.setBackground(new java.awt.Color(255, 206, 0));
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ronald\\Documents\\NetBeansProjects\\Chick_Int_DTR_System\\src\\main\\java\\com\\mycompany\\chick_int_dtr_system\\assets\\arrow_arrows_back_direction_left_navigation_right_icon_123237.png")); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, java.awt.BorderLayout.WEST);

        jPanel3.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 172;
        gridBagConstraints.ipady = 236;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(50, 34, 50, 35);
        jPanel4.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:

        checkuser();


    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        Dashboard d = new Dashboard();
        d.tabIndex(1);
        d.setVisible(rootPaneCheckingEnabled);
        this.dispose();

    }//GEN-LAST:event_cancelButtonActionPerformed

    private void jStreetAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStreetAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jStreetAddressActionPerformed

    private void jPostalCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPostalCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPostalCodeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jFileChooser1.showOpenDialog(null) == jFileChooser1.APPROVE_OPTION) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPositionTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPositionTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPositionTitleActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Dashboard d = new Dashboard();
        d.tabIndex(1);
        d.setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(updateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(updateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(updateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(updateForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new updateForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FAddess;
    private javax.swing.JPanel FAddressMain;
    private javax.swing.JPanel FContact;
    private javax.swing.JPanel FContactMain;
    private javax.swing.JPanel FGenderAgeBirthMain;
    private javax.swing.JPanel FName;
    private javax.swing.JPanel FNameMain;
    private javax.swing.JPanel FPosition;
    private javax.swing.JPanel FPositionMain;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField jAge;
    private com.toedter.calendar.JDateChooser jBirth;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jCity;
    private javax.swing.JTextField jEmail;
    private javax.swing.JTextField jEmergencyName;
    private javax.swing.JTextField jEmergencyNumber;
    private javax.swing.JTextField jEmploymentType;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JTextField jFirstName;
    private javax.swing.JPanel jForm;
    private javax.swing.JTextField jGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jLastName;
    private javax.swing.JTextField jMiddleName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jPassword;
    private javax.swing.JTextField jPhoneNumber;
    private javax.swing.JTextField jPositionTitle;
    private javax.swing.JTextField jPostalCode;
    private javax.swing.JTextField jRegion;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jStreetAddress;
    private javax.swing.JLabel lAge;
    private javax.swing.JLabel lBirth;
    private javax.swing.JLabel lCity;
    private javax.swing.JLabel lEmail;
    private javax.swing.JLabel lEmergencyName;
    private javax.swing.JLabel lEmergencyNumber;
    private javax.swing.JLabel lEmploymentType;
    private javax.swing.JLabel lFirstName;
    private javax.swing.JLabel lGender;
    private javax.swing.JLabel lLastName;
    private javax.swing.JLabel lMiddleName;
    private javax.swing.JLabel lPassword;
    private javax.swing.JLabel lPhoneNumber;
    private javax.swing.JLabel lPositionTitle;
    private javax.swing.JLabel lPostalCode;
    private javax.swing.JLabel lProfile;
    private javax.swing.JLabel lRegion;
    private javax.swing.JLabel lStreet;
    private javax.swing.JPanel main;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
