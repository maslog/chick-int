/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/OkCancelDialog.java to edit this template
 */
package com.mycompany.chick_int_dtr_system.Components;

import static com.mycompany.chick_int_dtr_system.Components.NewOkCancelDialog.RET_CANCEL;
import static com.mycompany.chick_int_dtr_system.Components.NewOkCancelDialog.RET_OK;
import com.mycompany.chick_int_dtr_system.Dashboard;
import com.mycompany.chick_int_dtr_system.UserUI;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Ronald
 */
public class AddEmployeeModal extends javax.swing.JDialog {

    private Dashboard dashboard;
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form AddEmployeeModal
     */
    public AddEmployeeModal(com.mycompany.chick_int_dtr_system.Dashboard parent, boolean modal) {
        super(parent, modal);
        dashboard = parent;
        initComponents();

        jScrollPane2.getVerticalScrollBar().setUnitIncrement(9);

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
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
                && !jPassword.getText().isEmpty() && !jStartDate.getDate().toString().isEmpty() && !jStartDate.getDate().toString().equals(" ") && !jPassword.getText().equals(" ") && !jPassword.getText().isBlank() && !jStartDate.getDate().toString().isBlank()
                && !jEndDate.getDate().toString().isEmpty() && !jEndDate.getDate().toString().equals(" ") && !jEndDate.getDate().toString().isBlank()) {
insertData();
            JOptionPane.showMessageDialog(this, "Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, "Fill in the blank!", "Warning", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void insertData() {
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
            String sTime = String.valueOf(jStartDate.getDate());
            String eTime = String.valueOf(jEndDate.getDate());
            String pass = jPassword.getText();

            Connection conn = Database.getConnection();
            //String query = "INSERT INTO `db_chick_int`.`employee` (`firstname`, `middlename`, `lastname`) VALUES ('" + firstname + "', '" + middlename + "', '" + lastname + "');";
            String query2 = "INSERT INTO `db_chick_int`.`employee` ( `firstname`, `middlename`, `lastname`, `age`, `gender`, `birthdate`, `streetAdress`, `city`, `region`, `postalCode`, `phoneNumber`, `email`, `emergencyName`, `emergencyNumber`, `positionTitle`, `employmentType`, `startDate`, `endDate`,   `password`) VALUES ('" + firstname + "', '" + middlename + "', '" + lastname + "', " + age + ", '" + gender + "', '" + birth + "', '" + street + "', '" + city + "', '" + region + "', '" + postal + "', '" + pNumber + "', '" + email + "', '" + eName + "', '" + eNum + "', '" + pTitle + "', '" + emType + "', '" + sTime + "', '" + eTime + "', '" + pass + "')";

            Statement statement = conn.createStatement();
            statement.execute(query2);

            statement.close();

            Database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeeModal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
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
        jBirth = new org.jdesktop.swingx.JXDatePicker();
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
        lStartDate = new javax.swing.JLabel();
        lProfile = new javax.swing.JLabel();
        lEndDate1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jEndDate = new org.jdesktop.swingx.JXDatePicker();
        jStartDate = new org.jdesktop.swingx.JXDatePicker();
        jPassword = new javax.swing.JTextField();
        lPassword = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 700));
        setPreferredSize(new java.awt.Dimension(600, 700));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(500, 800));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 800));

        jPanel1.setMinimumSize(new java.awt.Dimension(500, 1000));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 1000));
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
        main.setMinimumSize(new java.awt.Dimension(500, 500));
        main.setPreferredSize(new java.awt.Dimension(500, 500));
        main.setLayout(new java.awt.BorderLayout());

        jForm.setBackground(new java.awt.Color(255, 206, 0));
        jForm.setMinimumSize(new java.awt.Dimension(4550, 100));
        jForm.setPreferredSize(new java.awt.Dimension(500, 100));
        jForm.setRequestFocusEnabled(false);

        FNameMain.setMinimumSize(new java.awt.Dimension(100, 100));
        FNameMain.setPreferredSize(new java.awt.Dimension(600, 100));
        FNameMain.setLayout(new java.awt.BorderLayout());

        FName.setBackground(new java.awt.Color(255, 206, 0));
        java.awt.GridBagLayout FNameLayout1 = new java.awt.GridBagLayout();
        FNameLayout1.columnWidths = new int[] {0, 13, 0, 13, 0, 13, 0, 13, 0, 13, 0};
        FNameLayout1.rowHeights = new int[] {0, 1, 0, 1, 0, 1, 0};
        FName.setLayout(FNameLayout1);

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
        gridBagConstraints.gridy = 6;
        FName.add(jFirstName, gridBagConstraints);

        lFirstName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lFirstName.setText("First Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        FName.add(jMiddleName, gridBagConstraints);

        lMiddleName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lMiddleName.setText("Middle Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
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
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        FName.add(jLastName, gridBagConstraints);

        lLastName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lLastName.setText("Last Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FName.add(lLastName, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Personal Information:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FName.add(jLabel1, gridBagConstraints);

        FNameMain.add(FName, java.awt.BorderLayout.CENTER);

        jForm.add(FNameMain);

        FGenderAgeBirthMain.setBackground(new java.awt.Color(255, 206, 0));
        FGenderAgeBirthMain.setMinimumSize(new java.awt.Dimension(600, 100));
        FGenderAgeBirthMain.setPreferredSize(new java.awt.Dimension(600, 100));
        java.awt.GridBagLayout FGenderAgeBirthMainLayout = new java.awt.GridBagLayout();
        FGenderAgeBirthMainLayout.columnWidths = new int[] {0, 13, 0, 13, 0, 13, 0, 13, 0, 13, 0};
        FGenderAgeBirthMainLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
        gridBagConstraints.gridy = 2;
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        FGenderAgeBirthMain.add(jAge, gridBagConstraints);

        lAge.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lAge.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FGenderAgeBirthMain.add(lAge, gridBagConstraints);

        lBirth.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lBirth.setText("Birth Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FGenderAgeBirthMain.add(lBirth, gridBagConstraints);

        jBirth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBirth.setMinimumSize(new java.awt.Dimension(160, 32));
        jBirth.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
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
        FAddessLayout.columnWidths = new int[] {0, 5, 0, 5, 0};
        FAddessLayout.rowHeights = new int[] {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
        FAddess.setLayout(FAddessLayout);

        jStreetAddress.setColumns(20);
        jStreetAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jStreetAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jStreetAddress.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jStreetAddress.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jStreetAddress.setMinimumSize(new java.awt.Dimension(160, 32));
        jStreetAddress.setOpaque(true);
        jStreetAddress.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FAddess.add(jStreetAddress, gridBagConstraints);

        lStreet.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lStreet.setText("Street Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        FAddess.add(jCity, gridBagConstraints);

        lCity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lCity.setText("City");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
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
        gridBagConstraints.gridy = 12;
        FAddess.add(jRegion, gridBagConstraints);

        lRegion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lRegion.setText("Region");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        FAddess.add(jPostalCode, gridBagConstraints);

        lPostalCode.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPostalCode.setText("Postal Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
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
        FContactLayout.columnWidths = new int[] {0, 5, 0, 5, 0};
        FContactLayout.rowHeights = new int[] {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
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
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FContact.add(jPhoneNumber, gridBagConstraints);

        lPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPhoneNumber.setText("Phone Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        FContact.add(jEmail, gridBagConstraints);

        lEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEmail.setText("E-mail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
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
        gridBagConstraints.gridy = 12;
        FContact.add(jEmergencyName, gridBagConstraints);

        lEmergencyName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEmergencyName.setText("Emergency Contact Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        FContact.add(jEmergencyNumber, gridBagConstraints);

        lEmergencyNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEmergencyNumber.setText("Emergency Contact  Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FContact.add(lEmergencyNumber, gridBagConstraints);

        FContactMain.add(FContact, java.awt.BorderLayout.CENTER);

        jForm.add(FContactMain);

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setMinimumSize(new java.awt.Dimension(500, 10));
        jSeparator3.setPreferredSize(new java.awt.Dimension(500, 10));
        jForm.add(jSeparator3);

        FPositionMain.setMinimumSize(new java.awt.Dimension(600, 300));
        FPositionMain.setPreferredSize(new java.awt.Dimension(600, 300));
        FPositionMain.setLayout(new java.awt.BorderLayout());

        FPosition.setBackground(new java.awt.Color(255, 206, 0));
        FPosition.setMinimumSize(new java.awt.Dimension(296, 100));
        FPosition.setPreferredSize(new java.awt.Dimension(296, 100));
        java.awt.GridBagLayout FPositionLayout = new java.awt.GridBagLayout();
        FPositionLayout.columnWidths = new int[] {0, 2, 0, 2, 0, 2, 0, 2, 0};
        FPositionLayout.rowHeights = new int[] {0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0};
        FPosition.setLayout(FPositionLayout);

        jPositionTitle.setColumns(20);
        jPositionTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPositionTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPositionTitle.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPositionTitle.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jPositionTitle.setMinimumSize(new java.awt.Dimension(160, 32));
        jPositionTitle.setOpaque(true);
        jPositionTitle.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FPosition.add(jPositionTitle, gridBagConstraints);

        lPositionTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPositionTitle.setText("Position Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
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
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        FPosition.add(jEmploymentType, gridBagConstraints);

        lEmploymentType.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEmploymentType.setText("Employment Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
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

        lStartDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lStartDate.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FPosition.add(lStartDate, gridBagConstraints);

        lProfile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lProfile.setText("Choose Profile");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FPosition.add(lProfile, gridBagConstraints);

        lEndDate1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lEndDate1.setText("Contract End Date (optional)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FPosition.add(lEndDate1, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Select Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        FPosition.add(jButton1, gridBagConstraints);

        jEndDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jEndDate.setMinimumSize(new java.awt.Dimension(200, 32));
        jEndDate.setPreferredSize(new java.awt.Dimension(200, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FPosition.add(jEndDate, gridBagConstraints);

        jStartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jStartDate.setMinimumSize(new java.awt.Dimension(200, 32));
        jStartDate.setPreferredSize(new java.awt.Dimension(200, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        FPosition.add(jStartDate, gridBagConstraints);

        jPassword.setColumns(20);
        jPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPassword.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPassword.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jPassword.setMinimumSize(new java.awt.Dimension(160, 32));
        jPassword.setOpaque(true);
        jPassword.setPreferredSize(new java.awt.Dimension(160, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 26;
        FPosition.add(jPassword, gridBagConstraints);

        lPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lPassword.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 24;
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

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 206, 0));
        jPanel3.setMinimumSize(new java.awt.Dimension(251, 60));
        jPanel3.setPreferredSize(new java.awt.Dimension(586, 60));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(248, 98, 24));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(248, 98, 24));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("New Employee");
        jPanel3.add(jLabel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void jPostalCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPostalCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPostalCodeActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        checkuser();

        doClose(RET_OK);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        doClose(RET_CANCEL);

    }//GEN-LAST:event_cancelButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jFileChooser1.showOpenDialog(null) == jFileChooser1.APPROVE_OPTION) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddEmployeeModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEmployeeModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEmployeeModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEmployeeModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddEmployeeModal dialog = new AddEmployeeModal(new com.mycompany.chick_int_dtr_system.Dashboard(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
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
    private org.jdesktop.swingx.JXDatePicker jBirth;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jCity;
    private javax.swing.JTextField jEmail;
    private javax.swing.JTextField jEmergencyName;
    private javax.swing.JTextField jEmergencyNumber;
    private javax.swing.JTextField jEmploymentType;
    private org.jdesktop.swingx.JXDatePicker jEndDate;
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
    private javax.swing.JPanel jPanel3;
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
    private org.jdesktop.swingx.JXDatePicker jStartDate;
    private javax.swing.JTextField jStreetAddress;
    private javax.swing.JLabel lAge;
    private javax.swing.JLabel lBirth;
    private javax.swing.JLabel lCity;
    private javax.swing.JLabel lEmail;
    private javax.swing.JLabel lEmergencyName;
    private javax.swing.JLabel lEmergencyNumber;
    private javax.swing.JLabel lEmploymentType;
    private javax.swing.JLabel lEndDate1;
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
    private javax.swing.JLabel lStartDate;
    private javax.swing.JLabel lStreet;
    private javax.swing.JPanel main;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
