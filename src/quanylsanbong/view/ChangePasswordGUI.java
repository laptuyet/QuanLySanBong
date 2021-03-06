/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package quanylsanbong.view;

import javax.swing.JOptionPane;
import quanlysanbong.controller.UserDAO;
import quanlysanbong.model.Account;

/**
 *
 * @author tranh
 */
public class ChangePasswordGUI extends javax.swing.JDialog {

    private AdminGUI adminGui;
    private StaffGUI staffGui;
    private CustomerGUI customerGui;
    private UserLogin userLogin;

    /**
     * Creates new form ChangePasswordGUI
     */
    public ChangePasswordGUI(java.awt.Frame parent, boolean modal, String username) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        if (parent.getClass().getName().equals("quanylsanbong.view.AdminGUI")) {
            this.adminGui = (AdminGUI) parent;
            userTxt.setText(username);
            userTxt.setEnabled(false);

        } else if (parent.getClass().getName().equals("quanylsanbong.view.StaffGUI")) {
            this.staffGui = (StaffGUI) parent;
            userTxt.setText(username);
            userTxt.setEnabled(false);

        } else if (parent.getClass().getName().equals("quanylsanbong.view.CustomerGUI")) {
            this.customerGui = (CustomerGUI) parent;
            userTxt.setText(username);
            userTxt.setEnabled(false);

        } else {
            this.userLogin = (UserLogin) parent;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        userTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        oldPwdTxt = new javax.swing.JPasswordField();
        newPwdTxt = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        changePwdBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        newPwdConfirmWrong = new javax.swing.JLabel();
        oldPwdWrong = new javax.swing.JLabel();
        userWrong = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        confirmPwdTxt = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Unispace", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHANGE PASSWORD");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/user-icon.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        jLabel5.setText("USERNAME:");

        userTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        jLabel6.setText("OLD PASSWORD:");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-password-32.png"))); // NOI18N

        oldPwdTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        newPwdTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        jLabel7.setText("NEW PASSWORD:");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-password-reset-32.png"))); // NOI18N

        changePwdBtn.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        changePwdBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-checked-radio-button-32.png"))); // NOI18N
        changePwdBtn.setText("CHANGE");
        changePwdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePwdBtnActionPerformed(evt);
            }
        });

        cancelBtn.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-cancel-32.png"))); // NOI18N
        cancelBtn.setText("CANCLE");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        newPwdConfirmWrong.setForeground(new java.awt.Color(255, 0, 0));
        newPwdConfirmWrong.setText(" ");

        oldPwdWrong.setForeground(new java.awt.Color(255, 0, 0));
        oldPwdWrong.setText(" ");

        userWrong.setForeground(new java.awt.Color(255, 51, 0));
        userWrong.setText(" ");

        jLabel8.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel8.setText("CONFIRM PASSWORD:");

        confirmPwdTxt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6))
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(oldPwdTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                        .addGap(87, 87, 87))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(newPwdTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(oldPwdWrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userWrong, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(newPwdConfirmWrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(confirmPwdTxt))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(changePwdBtn)
                        .addGap(53, 53, 53)
                        .addComponent(cancelBtn)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userWrong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(oldPwdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(oldPwdWrong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(newPwdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(confirmPwdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPwdConfirmWrong)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(changePwdBtn))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changePwdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePwdBtnActionPerformed
        // TODO add your handling code here:
        String user = userTxt.getText().trim();
        String oldPwd = String.valueOf(oldPwdTxt.getPassword());
        String newPwd = String.valueOf(newPwdTxt.getPassword());
        boolean tonTai = false;
        boolean pwdWrong = false;

        UserDAO validator = new UserDAO();
        Account acc = validator.getAccount(user);

        if (acc.getTaikhoan() == null) {
            userWrong.setText("Wrong username");
        } else {
            userWrong.setText(" ");
            if (!acc.getMatkhau().equals(oldPwd)) {
                oldPwdWrong.setText("Wrong password");
                pwdWrong = true;
            } else {
                oldPwdWrong.setText(" ");
                tonTai = true;
            }
        }
        if (!pwdWrong) {
            if (!String.valueOf(confirmPwdTxt.getPassword()).equals(newPwd)) {
                newPwdConfirmWrong.setText("Password isn't same!");
                return;
            }
        }

        if (tonTai) {

            if (!validator.changPassword(user, newPwd)) {
                JOptionPane.showMessageDialog(rootPane, "Change password failed");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Change password successfully!");
                this.dispose();
            }
        }
    }//GEN-LAST:event_changePwdBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ChangePasswordGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChangePasswordGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChangePasswordGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChangePasswordGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ChangePasswordGUI dialog = new ChangePasswordGUI(new javax.swing.JFrame(), true, "");
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton changePwdBtn;
    private javax.swing.JPasswordField confirmPwdTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel newPwdConfirmWrong;
    private javax.swing.JPasswordField newPwdTxt;
    private javax.swing.JPasswordField oldPwdTxt;
    private javax.swing.JLabel oldPwdWrong;
    private javax.swing.JTextField userTxt;
    private javax.swing.JLabel userWrong;
    // End of variables declaration//GEN-END:variables
}
