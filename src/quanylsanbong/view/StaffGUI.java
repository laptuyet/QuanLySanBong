/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanylsanbong.view;

import Clock.ManageRentTimeAndBill;
import Clock.MyClock;
import Utils.AutoID;
import Utils.CalendarHelper;
import Utils.CheckText;
import java.awt.Color;
import java.awt.Image;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import quanlysanbong.controller.*;
import quanlysanbong.model.*;

/**
 *
 * @author Tran Hoang Phi N19DCCN136
 */
public class StaffGUI extends javax.swing.JFrame {

     //========= DAO ==============================================
    private StaffDAO staffDao = new StaffDAO();
    private DichVuDAO dvDao = new DichVuDAO();
    private AccountDAO accDao = new AccountDAO();
    private KhachHangDAO khDao = new KhachHangDAO();
    private SanBongDAO staDao = new SanBongDAO();
    private TrangThaiSanDAO stateDao = new TrangThaiSanDAO();
    private HeSoDAO rateDao = new HeSoDAO();
    private PhieuDatDAO pdDao = new PhieuDatDAO();
    private CT_PhieuDatDAO ctpdDao = new CT_PhieuDatDAO();
    private PhieuThueDAO ptDao = new PhieuThueDAO();
    private CT_PhieuThueDAO ctptDao = new CT_PhieuThueDAO();
    private CT_DichVuDAO ctdvDao = new CT_DichVuDAO();
    private BillDAO billDao = new BillDAO();
    //============================================================

    private NhanVien staff;
    
    private ArrayList<KhachHang> khList;
    private DefaultTableModel khTableModel;
    
    private DefaultTableModel dvTableModel;
    private ArrayList<DichVu> dvList;
    
    private ArrayList<SanBong> staList;
    private DefaultTableModel staTableModel;

    private ArrayList<TrangThaiSan> stateList;
    private DefaultTableModel stateStaTableModel;

    private ArrayList<HeSo> rateList;
    private DefaultTableModel rateTableModel;

    private ArrayList<PhieuDat> pdList;
    private ArrayList<PhieuThue> ptList;

    private DefaultTableModel avaiStaTableModel;
    private ArrayList<TrangThaiSan> avaiStaList;

    private ArrayList<CT_PhieuDat> ctpdList;
    private DefaultTableModel tempPreOrderTableModel;
    private DefaultTableModel preOrderDetailTableModel;
    private TrangThaiSan preOrderItem;
    private DefaultTableModel preOrderListTableModel;
    private boolean checkPreOrderFlag = true;
    
    private ArrayList<CT_PhieuThue> ctptList;
    private DefaultTableModel orderDetailTableModel;
    private DefaultTableModel orderDetailTableModel2;
    private DefaultTableModel orderServicesTableModel;
    private DefaultTableModel orderServicesTableModel2;
    private DefaultTableModel orderAvaiStaTableModel;
    private int ctptListSize;
    private DefaultTableModel tempOrderTableModel;
    private TrangThaiSan orderItem;
    private ArrayList<CT_DichVu> ctdvList;
    private DefaultTableModel orderListTableModel;
    private ArrayList<CT_PhieuThue> ctptList2;
    private ArrayList<CT_DichVu> ctdvList2;
    private boolean checkOrderFlag = true;
    
    
    private DefaultTableModel billListTableModel;
    private DefaultTableModel billStaDetailTableModel;
    private DefaultTableModel billServiceDetailTableModel;
    private ArrayList<Bill> billList;
    
    
    private CalendarHelper cal;
    private MyClock clock;
    private String glbMsg;

    /**
     * Creates new form StaffGUI
     */
    public StaffGUI(String username, String role) {
        initComponents();
        this.getContentPane().setBackground(new Color(242, 222, 135));
        this.setLocationRelativeTo(null);

        usernameLabel.setText(username);
        roleLabel.setText(role);
        
        infoPanel.setVisible(true);
        bookingPanel.setVisible(false);
        customerPanel.setVisible(false);
        viewFoodPanel.setVisible(false);
        billPanel.setVisible(false);
        
        //set clock
        clock = new MyClock(timeLabel, dateLabel);
        
        // calendar helper
        cal = new CalendarHelper();
        
        // get info cua nhan vien
        staff = staffDao.getStaff(username);
        showInfo();
        
        khTableModel = (DefaultTableModel) khTable.getModel();
        khList = khDao.getCustomerList();
        showCusList(khList);
        
        dvTableModel = (DefaultTableModel) foodTable.getModel();
        dvList = dvDao.getDichVuList();
        showFoodList(dvList);
        
        // get rate
        rateList = rateDao.getRateList();
        //rateTableModel = (DefaultTableModel) rateTable.getModel();
        //mRateLabel.setText("Morning: x" + rateDao.getRate("kgsang").getHeSo());
        //aRateLabel.setText("Afternoon: x" + rateDao.getRate("kgchieu").getHeSo());
        //eRateLabel.setText("Evening: x" + rateDao.getRate("kgtoi").getHeSo());
        //enableRateComponent(false);
        //showRateList(rateList);
        
        //get san bong list
        staList = staDao.getStadiumList();

        avaiStaTableModel = (DefaultTableModel) avaiStaTable.getModel();

        preOrderListTableModel = (DefaultTableModel) preOrderListTable.getModel();
        
        orderAvaiStaTableModel = (DefaultTableModel) orderAvaiStaTable.getModel();
        orderListTableModel = (DefaultTableModel) orderListTable.getModel();
        
        
        // get danh sach hoa don cua rieng nhan vien nay, khong cho xem cua nvien khac
        billList = (ArrayList<Bill>)billDao.getBillList().stream()
                .filter(item -> item.getManv().equals(staff.getManv())).collect(Collectors.toList());
        billListTableModel = (DefaultTableModel) billListTable.getModel();
        showBillList(billList);
        
        billStaDetailTableModel = (DefaultTableModel) billStaDetailTable.getModel();
        billServiceDetailTableModel = (DefaultTableModel) billServiceDetailTable.getModel();
        
        // auto make bill with expired order
        
        ptList = ptDao.getOrderList(); // pt duoc dua len day de ho tro tao bill auto
        
        //========= Tao thread quan li thoi gian thue cho tung san =============
        ManageRentTimeAndBill mt = new ManageRentTimeAndBill(ptList, billList, dvList, rateList);
        //======================================================================
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outOfStockGrpBtn = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        infoBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        bookingBtn = new javax.swing.JButton();
        viewFoodBtn = new javax.swing.JButton();
        roleLabel = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        payBtn = new javax.swing.JButton();
        customerBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bookingPanel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        preOrderNoteTxt = new javax.swing.JTextField();
        preOrderIdLabel = new javax.swing.JLabel();
        preOrderDateCreateLabel = new javax.swing.JLabel();
        preOrderCusIDCbBox = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        preOrderStaffIdLabel = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        preOrderPhoneTxt = new javax.swing.JTextField();
        preOrderPhoneWrong = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        avaiStaTable = new javax.swing.JTable();
        addDetailPreOrderBtn = new javax.swing.JButton();
        endHour1 = new javax.swing.JSpinner();
        endMinute1 = new javax.swing.JSpinner();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        dateTimeWrong1 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        startYearChooser1 = new com.toedter.calendar.JYearChooser();
        startHour1 = new javax.swing.JSpinner();
        startMinute1 = new javax.swing.JSpinner();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        startMonthCbBox1 = new javax.swing.JComboBox<>();
        startDayCbBox1 = new javax.swing.JComboBox<>();
        checkAvaiStaBtn = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        preOrderDetailTable = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        removePreOderItemBtn = new javax.swing.JButton();
        createPreOrderBtn = new javax.swing.JButton();
        preOrderWrong = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        preOrderListTable = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        preOrderDetailTable2 = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        preOrderStaffIdLabel2 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        preOrderCusIdLabel2 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        preOrderNoteTxt2 = new javax.swing.JTextField();
        preOrderPhoneTxt2 = new javax.swing.JTextField();
        preOrderDateCreate2 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        totalDepositLabel = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        preOrderExpiredLabel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        orderIdLabel = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        orderStaffIdLabel = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        orderDateCreateLabel = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        orderNoteTxt = new javax.swing.JTextField();
        orderCusIDCbBox = new javax.swing.JComboBox<>();
        jLabel76 = new javax.swing.JLabel();
        orderPhoneTxt = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        orderPhoneWrong = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        orderAvaiStaTable = new javax.swing.JTable();
        addDetailOrderBtn = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        startYearChooser2 = new com.toedter.calendar.JYearChooser();
        startMonthCbBox2 = new javax.swing.JComboBox<>();
        startDayCbBox2 = new javax.swing.JComboBox<>();
        startHour2 = new javax.swing.JSpinner();
        startMinute2 = new javax.swing.JSpinner();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        endHour2 = new javax.swing.JSpinner();
        jLabel83 = new javax.swing.JLabel();
        endMinute2 = new javax.swing.JSpinner();
        jLabel84 = new javax.swing.JLabel();
        checkAvaiStaBtn1 = new javax.swing.JButton();
        dateTimeWrong2 = new javax.swing.JLabel();
        createOrderBtn = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        orderDetailTable = new javax.swing.JTable();
        jLabel85 = new javax.swing.JLabel();
        removeOrderItemBtn = new javax.swing.JButton();
        removeOrderServiceBtn = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        orderServicesTable = new javax.swing.JTable();
        jSeparator6 = new javax.swing.JSeparator();
        viewServicesBtn = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        refreshOrderServiceTableBtn = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        preOrderIdTxt = new javax.swing.JTextField();
        checkPreOrderIdBtn = new javax.swing.JButton();
        preOrderIdWrong = new javax.swing.JLabel();
        orderWrong = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        orderListTable = new javax.swing.JTable();
        jLabel88 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        orderDetailTable2 = new javax.swing.JTable();
        jLabel89 = new javax.swing.JLabel();
        orderStaffIdLabel2 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        orderCusIdLabel2 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        orderNoteTxt2 = new javax.swing.JTextField();
        orderPhoneTxt2 = new javax.swing.JTextField();
        orderDateCreate2 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        currentTotalOrderLabel = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane16 = new javax.swing.JScrollPane();
        orderServicesTable2 = new javax.swing.JTable();
        viewServicesBtn2 = new javax.swing.JButton();
        removeOrderServiceBtn2 = new javax.swing.JButton();
        orderIdLabel2 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        refreshOrderServiceTableBtn2 = new javax.swing.JButton();
        endStadiumBtn = new javax.swing.JButton();
        customerPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        khTable = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        cusIdTxt = new javax.swing.JTextField();
        cusIdWrong = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cusFnameTxt = new javax.swing.JTextField();
        cusFnameWrong = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cusLnameTxt = new javax.swing.JTextField();
        cusLnameWrong = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cusPhoneTxt = new javax.swing.JTextField();
        cusPhoneWrong = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cusCmndTxt = new javax.swing.JTextField();
        cusCmndWrong = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cusUsernameTxt = new javax.swing.JTextField();
        cusUsernameWrong = new javax.swing.JLabel();
        addCusBtn = new javax.swing.JButton();
        updateCusBtn = new javax.swing.JButton();
        cancelCusBtn = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        sortCusCbBox = new javax.swing.JComboBox<>();
        viewFoodPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        foodTable = new javax.swing.JTable();
        foodImageLabel = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        outOfStockYes = new javax.swing.JRadioButton();
        outOfStockNo = new javax.swing.JRadioButton();
        updateFoodBtn = new javax.swing.JButton();
        billPanel = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        billListTable = new javax.swing.JTable();
        jLabel97 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        billIDLabel = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        billOrderIDLabel = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        billDateCreateLabel = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        billStaffIDLabel = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        billCusIDLabel = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel109 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        billStaDetailTable = new javax.swing.JTable();
        jLabel110 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        billServiceDetailTable = new javax.swing.JTable();
        jLabel111 = new javax.swing.JLabel();
        billTempTotalLabel = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        memDiscountLabel = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        billTotalLabel = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        billMemshipLabel = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        billDepositLabel = new javax.swing.JLabel();
        infoPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idTxt = new javax.swing.JTextField();
        fnameTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lnameTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        phoneTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmndTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        quitYesRadio = new javax.swing.JRadioButton();
        quitNoRadio = new javax.swing.JRadioButton();
        fnameWrong = new javax.swing.JLabel();
        lnameWrong = new javax.swing.JLabel();
        phoneWrong = new javax.swing.JLabel();
        cmndWrong = new javax.swing.JLabel();
        settingBtn = new javax.swing.JButton();
        changepwdBtn = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-user-50.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Unispace", 2, 14)); // NOI18N
        jLabel2.setText("Hello");

        usernameLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        usernameLabel.setText("username");

        infoBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        infoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-info-32.png"))); // NOI18N
        infoBtn.setText("User Info");
        infoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoBtnActionPerformed(evt);
            }
        });

        logoutBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        logoutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-logout-32.png"))); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        bookingBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        bookingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-booking-32.png"))); // NOI18N
        bookingBtn.setText("Booking Stadium");
        bookingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingBtnActionPerformed(evt);
            }
        });

        viewFoodBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        viewFoodBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-soda-32.png"))); // NOI18N
        viewFoodBtn.setText("View Drinks and Foods");
        viewFoodBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFoodBtnActionPerformed(evt);
            }
        });

        roleLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        roleLabel.setText("role");

        jLabel22.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel22.setText("Role:");

        payBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        payBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-bill-32.png"))); // NOI18N
        payBtn.setText("Bill");
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });

        customerBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        customerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-human-32.png"))); // NOI18N
        customerBtn.setText("Customer");
        customerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBtnActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-clock-50.png"))); // NOI18N
        jLabel26.setText(" ");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        timeLabel.setFont(new java.awt.Font("Unispace", 1, 18)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("hh:mm:ss");

        dateLabel.setFont(new java.awt.Font("Unispace", 1, 18)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("E, dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(roleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoBtn)
                            .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bookingBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewFoodBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(payBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(infoBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoutBtn)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(usernameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(roleLabel)
                                    .addComponent(jLabel22))))))
                .addGap(39, 39, 39)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bookingBtn)
                .addGap(18, 18, 18)
                .addComponent(customerBtn)
                .addGap(18, 18, 18)
                .addComponent(viewFoodBtn)
                .addGap(18, 18, 18)
                .addComponent(payBtn)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateLabel)))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        bookingPanel.setBackground(new java.awt.Color(255, 255, 204));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        jLabel52.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel52.setText("ID:");

        jLabel54.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel54.setText("Date:");

        jLabel55.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel55.setText("Note:");

        preOrderIdLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        preOrderIdLabel.setText(" ");

        preOrderDateCreateLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        preOrderDateCreateLabel.setText(" ");

        preOrderCusIDCbBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                preOrderCusIDCbBoxItemStateChanged(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel58.setText("Cus ID:");

        jLabel59.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel59.setText("Staff ID:");

        preOrderStaffIdLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        preOrderStaffIdLabel.setText(" ");

        jLabel61.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel61.setText("Phone:");

        preOrderPhoneWrong.setForeground(new java.awt.Color(255, 0, 0));
        preOrderPhoneWrong.setText(" ");

        jPanel6.setBackground(new java.awt.Color(153, 153, 0));

        jLabel53.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("Available Stadiums");

        avaiStaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Price/h"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        avaiStaTable.getTableHeader().setReorderingAllowed(false);
        avaiStaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                avaiStaTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(avaiStaTable);

        addDetailPreOrderBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        addDetailPreOrderBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-add-32.png"))); // NOI18N
        addDetailPreOrderBtn.setEnabled(false);
        addDetailPreOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDetailPreOrderBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addDetailPreOrderBtn))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addDetailPreOrderBtn)
                        .addGap(82, 82, 82))))
        );

        endHour1.setModel(new javax.swing.SpinnerNumberModel(0, -1, 24, 1));
        endHour1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endHour1StateChanged(evt);
            }
        });

        endMinute1.setModel(new javax.swing.SpinnerNumberModel(0, -1, 60, 1));
        endMinute1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endMinute1StateChanged(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel56.setText("h");

        jLabel57.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel57.setText("m");

        dateTimeWrong1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        dateTimeWrong1.setForeground(new java.awt.Color(255, 51, 0));
        dateTimeWrong1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateTimeWrong1.setText(" ");

        jLabel60.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel60.setText("To");

        startHour1.setModel(new javax.swing.SpinnerNumberModel(0, -1, 24, 1));
        startHour1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startHour1StateChanged(evt);
            }
        });

        startMinute1.setModel(new javax.swing.SpinnerNumberModel(0, -1, 60, 1));
        startMinute1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startMinute1StateChanged(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel63.setText("h");

        jLabel64.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel64.setText("m");

        jLabel65.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel65.setText("From");

        startMonthCbBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        startMonthCbBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                startMonthCbBox1ItemStateChanged(evt);
            }
        });

        startDayCbBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        checkAvaiStaBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        checkAvaiStaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-check-32.png"))); // NOI18N
        checkAvaiStaBtn.setText("Check");
        checkAvaiStaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAvaiStaBtnActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(204, 153, 0));

        preOrderDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Come", "Leave", "Deposit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        preOrderDetailTable.getTableHeader().setReorderingAllowed(false);
        preOrderDetailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                preOrderDetailTableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(preOrderDetailTable);

        jLabel62.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel62.setText("Pre-order Detail");

        removePreOderItemBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        removePreOderItemBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-delete-32.png"))); // NOI18N
        removePreOderItemBtn.setEnabled(false);
        removePreOderItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePreOderItemBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removePreOderItemBtn)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removePreOderItemBtn))
                .addGap(73, 73, 73))
        );

        createPreOrderBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        createPreOrderBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-checked-radio-button-32.png"))); // NOI18N
        createPreOrderBtn.setText("Create");
        createPreOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPreOrderBtnActionPerformed(evt);
            }
        });

        preOrderWrong.setForeground(new java.awt.Color(255, 0, 51));
        preOrderWrong.setText(" ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(startYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(startMonthCbBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startDayCbBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel65)
                                .addGap(41, 41, 41)
                                .addComponent(startHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(endHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(endMinute1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(startMinute1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addGap(31, 31, 31)
                .addComponent(checkAvaiStaBtn)
                .addGap(91, 91, 91))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(dateTimeWrong1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(preOrderWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(createPreOrderBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(preOrderIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(preOrderStaffIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(preOrderCusIDCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(preOrderDateCreateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(preOrderNoteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(preOrderPhoneWrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(preOrderPhoneTxt))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel52)
                                    .addComponent(preOrderIdLabel)
                                    .addComponent(jLabel59)
                                    .addComponent(preOrderStaffIdLabel))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel54)
                                    .addComponent(preOrderDateCreateLabel))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel55)
                                    .addComponent(preOrderNoteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel58)
                                    .addComponent(preOrderCusIDCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61)
                                    .addComponent(preOrderPhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(preOrderPhoneWrong))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(startHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startMinute1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63)
                                    .addComponent(jLabel64)
                                    .addComponent(jLabel65))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(endHour1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(endMinute1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel60)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(startDayCbBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startMonthCbBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(21, 21, 21))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(checkAvaiStaBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTimeWrong1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(createPreOrderBtn)
                        .addGap(18, 18, 18)
                        .addComponent(preOrderWrong))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("PRE-ORDER", new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-pre-order-32.png")), jPanel5); // NOI18N

        jPanel10.setBackground(new java.awt.Color(255, 255, 204));

        jPanel11.setBackground(new java.awt.Color(0, 204, 153));

        preOrderListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        preOrderListTable.getTableHeader().setReorderingAllowed(false);
        preOrderListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                preOrderListTableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(preOrderListTable);

        jLabel66.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Pre-Order List");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );

        jPanel12.setBackground(new java.awt.Color(0, 153, 153));

        preOrderDetailTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Come", "Leave", "Deposit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        preOrderDetailTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(preOrderDetailTable2);

        jLabel67.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Staff ID:");

        preOrderStaffIdLabel2.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        preOrderStaffIdLabel2.setForeground(new java.awt.Color(255, 255, 255));
        preOrderStaffIdLabel2.setText(" ");

        jLabel70.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Customer ID:");

        preOrderCusIdLabel2.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        preOrderCusIdLabel2.setForeground(new java.awt.Color(255, 255, 255));
        preOrderCusIdLabel2.setText(" ");

        jLabel71.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Phone:");

        jLabel73.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Note:");

        preOrderDateCreate2.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        preOrderDateCreate2.setForeground(new java.awt.Color(255, 255, 255));
        preOrderDateCreate2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        preOrderDateCreate2.setText("date:");

        jLabel68.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Total deposit:");

        totalDepositLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        totalDepositLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalDepositLabel.setText(" ");

        jSeparator4.setBackground(new java.awt.Color(255, 255, 255));

        preOrderExpiredLabel.setFont(new java.awt.Font("Unispace", 2, 18)); // NOI18N
        preOrderExpiredLabel.setForeground(new java.awt.Color(255, 255, 51));
        preOrderExpiredLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        preOrderExpiredLabel.setText(" ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(preOrderExpiredLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(preOrderDateCreate2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jSeparator5)
                        .addContainerGap())
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addGap(18, 18, 18)
                                .addComponent(totalDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(preOrderStaffIdLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(preOrderPhoneTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(preOrderCusIdLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(preOrderNoteTxt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(117, 117, 117))))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preOrderDateCreate2)
                    .addComponent(preOrderExpiredLabel))
                .addGap(43, 43, 43)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(preOrderStaffIdLabel2)
                    .addComponent(jLabel70)
                    .addComponent(preOrderCusIdLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(preOrderPhoneTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(preOrderNoteTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(totalDepositLabel))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("PRE-ORDER LIST", new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-list-32.png")), jPanel10); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 204));

        jLabel69.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel69.setText("ID:");

        orderIdLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        orderIdLabel.setText(" ");

        jLabel72.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel72.setText("Staff ID:");

        orderStaffIdLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        orderStaffIdLabel.setText(" ");

        jLabel74.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel74.setText("Date:");

        orderDateCreateLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        orderDateCreateLabel.setText(" ");

        jLabel75.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel75.setText("Note:");

        orderCusIDCbBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderCusIDCbBoxItemStateChanged(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel76.setText("Cus ID:");

        jLabel77.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel77.setText("Phone:");

        orderPhoneWrong.setForeground(new java.awt.Color(255, 0, 0));
        orderPhoneWrong.setText(" ");

        jPanel14.setBackground(new java.awt.Color(153, 153, 0));

        jLabel78.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Available Stadiums");

        orderAvaiStaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Price/h"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderAvaiStaTable.getTableHeader().setReorderingAllowed(false);
        orderAvaiStaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderAvaiStaTableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(orderAvaiStaTable);

        addDetailOrderBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        addDetailOrderBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-add-32.png"))); // NOI18N
        addDetailOrderBtn.setEnabled(false);
        addDetailOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDetailOrderBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addDetailOrderBtn))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel78)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addDetailOrderBtn)
                        .addGap(82, 82, 82))))
        );

        jLabel79.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel79.setText("From");

        startMonthCbBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        startMonthCbBox2.setEnabled(false);
        startMonthCbBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                startMonthCbBox2ItemStateChanged(evt);
            }
        });

        startDayCbBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        startDayCbBox2.setEnabled(false);

        startHour2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        startHour2.setEnabled(false);

        startMinute2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        startMinute2.setEnabled(false);

        jLabel80.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel80.setText("h");

        jLabel81.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel81.setText("m");

        jLabel82.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel82.setText("To");

        endHour2.setModel(new javax.swing.SpinnerNumberModel(0, -1, 24, 1));
        endHour2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endHour2StateChanged(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel83.setText("h");

        endMinute2.setModel(new javax.swing.SpinnerNumberModel(0, -1, 60, 1));
        endMinute2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endMinute2StateChanged(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel84.setText("m");

        checkAvaiStaBtn1.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        checkAvaiStaBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-check-32.png"))); // NOI18N
        checkAvaiStaBtn1.setText("Check");
        checkAvaiStaBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAvaiStaBtn1ActionPerformed(evt);
            }
        });

        dateTimeWrong2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        dateTimeWrong2.setForeground(new java.awt.Color(255, 51, 0));
        dateTimeWrong2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateTimeWrong2.setText(" ");

        createOrderBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        createOrderBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-checked-radio-button-32.png"))); // NOI18N
        createOrderBtn.setText("Create");
        createOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrderBtnActionPerformed(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(204, 153, 0));

        orderDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Come", "Leave"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderDetailTable.getTableHeader().setReorderingAllowed(false);
        orderDetailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderDetailTableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(orderDetailTable);

        jLabel85.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel85.setText("Order Detail");

        removeOrderItemBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        removeOrderItemBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-delete-32.png"))); // NOI18N
        removeOrderItemBtn.setEnabled(false);
        removeOrderItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOrderItemBtnActionPerformed(evt);
            }
        });

        removeOrderServiceBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        removeOrderServiceBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-delete-32.png"))); // NOI18N
        removeOrderServiceBtn.setEnabled(false);
        removeOrderServiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOrderServiceBtnActionPerformed(evt);
            }
        });

        orderServicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service name", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderServicesTable.getTableHeader().setReorderingAllowed(false);
        orderServicesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderServicesTableMouseClicked(evt);
            }
        });
        orderServicesTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                orderServicesTablePropertyChange(evt);
            }
        });
        jScrollPane13.setViewportView(orderServicesTable);

        jSeparator6.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        viewServicesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-eyes-cartoon-32.png"))); // NOI18N
        viewServicesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewServicesBtnActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("Service List");

        refreshOrderServiceTableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-update-32.png"))); // NOI18N
        refreshOrderServiceTableBtn.setText("Refresh");
        refreshOrderServiceTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshOrderServiceTableBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeOrderItemBtn))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(refreshOrderServiceTableBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(viewServicesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeOrderServiceBtn))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel85))
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(removeOrderServiceBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(viewServicesBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(removeOrderItemBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel87)
                                        .addComponent(refreshOrderServiceTableBtn)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator6))
                .addContainerGap())
        );

        jLabel86.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel86.setText("Preorder ID:");

        checkPreOrderIdBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        checkPreOrderIdBtn.setText("CHECK");
        checkPreOrderIdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPreOrderIdBtnActionPerformed(evt);
            }
        });

        preOrderIdWrong.setForeground(new java.awt.Color(255, 0, 0));
        preOrderIdWrong.setText(" ");

        orderWrong.setForeground(new java.awt.Color(255, 0, 0));
        orderWrong.setText(" ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(createOrderBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(startYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(startMonthCbBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(startDayCbBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel79)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(startHour2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endHour2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(endMinute2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(startMinute2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(orderCusIDCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(checkAvaiStaBtn1)
                .addGap(89, 89, 89))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orderWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel86)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(preOrderIdWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(preOrderIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkPreOrderIdBtn)))))
                        .addContainerGap(690, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel69)
                                        .addGap(9, 9, 9)))))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(orderIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel72)
                                        .addGap(18, 18, 18)
                                        .addComponent(orderStaffIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(orderPhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orderPhoneWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(orderDateCreateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orderNoteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(dateTimeWrong2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69)
                            .addComponent(orderIdLabel)
                            .addComponent(jLabel72)
                            .addComponent(orderStaffIdLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74)
                            .addComponent(orderDateCreateLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75)
                            .addComponent(orderNoteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(orderCusIDCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(orderPhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(orderPhoneWrong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel86)
                            .addComponent(preOrderIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkPreOrderIdBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(preOrderIdWrong)))
                .addGap(16, 16, 16)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(startHour2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startMinute2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel80)
                                        .addComponent(jLabel81)
                                        .addComponent(jLabel79))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(startYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(startDayCbBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(startMonthCbBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(2, 2, 2)))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(endHour2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(endMinute2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel83)
                                .addComponent(jLabel84)
                                .addComponent(jLabel82)))
                        .addComponent(createOrderBtn))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(checkAvaiStaBtn1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateTimeWrong2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderWrong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("ORDER NOW", new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-pre-order-32.png")), jPanel13); // NOI18N

        jPanel16.setBackground(new java.awt.Color(255, 255, 204));

        jPanel17.setBackground(new java.awt.Color(0, 204, 153));

        orderListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderListTable.getTableHeader().setReorderingAllowed(false);
        orderListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderListTableMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(orderListTable);

        jLabel88.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("Order List");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );

        jPanel18.setBackground(new java.awt.Color(0, 153, 153));

        orderDetailTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Come", "Estimated time out", "Leave"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderDetailTable2.getTableHeader().setReorderingAllowed(false);
        orderDetailTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderDetailTable2MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(orderDetailTable2);

        jLabel89.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Staff ID:");

        orderStaffIdLabel2.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        orderStaffIdLabel2.setForeground(new java.awt.Color(255, 255, 255));
        orderStaffIdLabel2.setText(" ");

        jLabel90.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Customer ID:");

        orderCusIdLabel2.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        orderCusIdLabel2.setForeground(new java.awt.Color(255, 255, 255));
        orderCusIdLabel2.setText(" ");

        jLabel91.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Phone:");

        jLabel92.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Note:");

        orderDateCreate2.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        orderDateCreate2.setForeground(new java.awt.Color(255, 255, 255));
        orderDateCreate2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        orderDateCreate2.setText("date:");

        jLabel93.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Current Total:");

        currentTotalOrderLabel.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        currentTotalOrderLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentTotalOrderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentTotalOrderLabel.setText(" ");

        jSeparator7.setBackground(new java.awt.Color(255, 255, 255));

        orderServicesTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service name", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderServicesTable2.getTableHeader().setReorderingAllowed(false);
        orderServicesTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderServicesTable2MouseClicked(evt);
            }
        });
        orderServicesTable2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                orderServicesTable2PropertyChange(evt);
            }
        });
        jScrollPane16.setViewportView(orderServicesTable2);

        viewServicesBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-eyes-cartoon-32.png"))); // NOI18N
        viewServicesBtn2.setEnabled(false);
        viewServicesBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewServicesBtn2ActionPerformed(evt);
            }
        });

        removeOrderServiceBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-delete-32.png"))); // NOI18N
        removeOrderServiceBtn2.setEnabled(false);
        removeOrderServiceBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOrderServiceBtn2ActionPerformed(evt);
            }
        });

        orderIdLabel2.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        orderIdLabel2.setForeground(new java.awt.Color(255, 255, 255));
        orderIdLabel2.setText(" ");

        jLabel94.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("ID:");

        refreshOrderServiceTableBtn2.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        refreshOrderServiceTableBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-update-32.png"))); // NOI18N
        refreshOrderServiceTableBtn2.setText("Refresh");
        refreshOrderServiceTableBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshOrderServiceTableBtn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jScrollPane15)
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(refreshOrderServiceTableBtn2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viewServicesBtn2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeOrderServiceBtn2))
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(currentTotalOrderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93))
                        .addGap(89, 89, 89))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jSeparator8)
                        .addContainerGap())
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(orderStaffIdLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(orderIdLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(orderCusIdLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(orderPhoneTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(orderNoteTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(orderDateCreate2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderDateCreate2)
                    .addComponent(orderIdLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94))
                .addGap(32, 32, 32)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(orderStaffIdLabel2)
                    .addComponent(jLabel90)
                    .addComponent(orderCusIdLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(orderPhoneTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderNoteTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel93)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentTotalOrderLabel))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(viewServicesBtn2)
                                .addComponent(removeOrderServiceBtn2))
                            .addComponent(refreshOrderServiceTableBtn2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        endStadiumBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        endStadiumBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-check-32.png"))); // NOI18N
        endStadiumBtn.setText("End");
        endStadiumBtn.setEnabled(false);
        endStadiumBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endStadiumBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endStadiumBtn))
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(endStadiumBtn)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("ORDER LIST", new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-list-32.png")), jPanel16); // NOI18N

        javax.swing.GroupLayout bookingPanelLayout = new javax.swing.GroupLayout(bookingPanel);
        bookingPanel.setLayout(bookingPanelLayout);
        bookingPanelLayout.setHorizontalGroup(
            bookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        bookingPanelLayout.setVerticalGroup(
            bookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        customerPanel.setBackground(new java.awt.Color(255, 255, 204));

        khTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First name", "Last name", "Phone", "Personal ID", "Number of rent", "Username", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        khTable.getTableHeader().setReorderingAllowed(false);
        khTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(khTable);

        jLabel18.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel18.setText("ID");

        cusIdWrong.setForeground(new java.awt.Color(255, 0, 51));
        cusIdWrong.setText(" ");

        jLabel20.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel20.setText("Fname");

        cusFnameWrong.setForeground(new java.awt.Color(255, 0, 51));
        cusFnameWrong.setText(" ");

        jLabel25.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel25.setText("Lname");

        cusLnameWrong.setForeground(new java.awt.Color(255, 0, 51));
        cusLnameWrong.setText(" ");

        jLabel27.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel27.setText("Phone");

        cusPhoneWrong.setForeground(new java.awt.Color(255, 0, 51));
        cusPhoneWrong.setText(" ");

        jLabel29.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel29.setText("P-ID");

        cusCmndWrong.setForeground(new java.awt.Color(255, 0, 51));
        cusCmndWrong.setText(" ");

        jLabel31.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel31.setText("Username");

        cusUsernameWrong.setForeground(new java.awt.Color(255, 0, 51));
        cusUsernameWrong.setText(" ");

        addCusBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        addCusBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-add-32.png"))); // NOI18N
        addCusBtn.setText("Add");
        addCusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusBtnActionPerformed(evt);
            }
        });

        updateCusBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        updateCusBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-update-32.png"))); // NOI18N
        updateCusBtn.setText("Update");
        updateCusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCusBtnActionPerformed(evt);
            }
        });

        cancelCusBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        cancelCusBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-cancel-32.png"))); // NOI18N
        cancelCusBtn.setText("Cancel");
        cancelCusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelCusBtnActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel19.setText("Sort by:");

        sortCusCbBox.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        sortCusCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Last name", "Number of rent" }));
        sortCusCbBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortCusCbBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cusIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cusIdWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, customerPanelLayout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cusFnameWrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cusFnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, customerPanelLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cusPhoneWrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cusPhoneTxt)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, customerPanelLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(3, 3, 3)
                                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cusUsernameWrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cusUsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(83, 83, 83)
                        .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(customerPanelLayout.createSequentialGroup()
                                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cusLnameWrong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cusLnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cusCmndTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cusCmndWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sortCusCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(customerPanelLayout.createSequentialGroup()
                                .addComponent(addCusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateCusBtn)))
                        .addGap(56, 56, 56)
                        .addComponent(cancelCusBtn)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cusIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(sortCusCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cusIdWrong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cusFnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(cusLnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cusFnameWrong)
                    .addComponent(cusLnameWrong))
                .addGap(18, 18, 18)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cusPhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(cusCmndTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cusPhoneWrong)
                    .addComponent(cusCmndWrong))
                .addGap(18, 18, 18)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(cusUsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addCusBtn)
                        .addComponent(updateCusBtn)
                        .addComponent(cancelCusBtn))
                    .addComponent(cusUsernameWrong))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        viewFoodPanel.setBackground(new java.awt.Color(255, 255, 204));

        foodTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Unit", "Price", "Out of stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        foodTable.getTableHeader().setReorderingAllowed(false);
        foodTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(foodTable);

        foodImageLabel.setBackground(new java.awt.Color(255, 255, 255));
        foodImageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel95.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel95.setText("Out of stock:");

        outOfStockGrpBtn.add(outOfStockYes);
        outOfStockYes.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        outOfStockYes.setText("Yes");

        outOfStockGrpBtn.add(outOfStockNo);
        outOfStockNo.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        outOfStockNo.setSelected(true);
        outOfStockNo.setText("No");

        updateFoodBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        updateFoodBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-check-32.png"))); // NOI18N
        updateFoodBtn.setText("Update");
        updateFoodBtn.setEnabled(false);
        updateFoodBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateFoodBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewFoodPanelLayout = new javax.swing.GroupLayout(viewFoodPanel);
        viewFoodPanel.setLayout(viewFoodPanelLayout);
        viewFoodPanelLayout.setHorizontalGroup(
            viewFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewFoodPanelLayout.createSequentialGroup()
                .addGroup(viewFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewFoodPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(foodImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewFoodPanelLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(outOfStockYes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(outOfStockNo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewFoodPanelLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(updateFoodBtn)))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        viewFoodPanelLayout.setVerticalGroup(
            viewFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewFoodPanelLayout.createSequentialGroup()
                .addGroup(viewFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewFoodPanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(foodImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewFoodPanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(viewFoodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(outOfStockYes)
                    .addComponent(outOfStockNo))
                .addGap(30, 30, 30)
                .addComponent(updateFoodBtn)
                .addContainerGap(229, Short.MAX_VALUE))
        );

        billPanel.setBackground(new java.awt.Color(255, 255, 204));

        jPanel20.setBackground(new java.awt.Color(255, 204, 153));

        billListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        billListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billListTableMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(billListTable);

        jLabel97.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel97.setText("Bill List");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addComponent(jLabel97)
                .addGap(114, 114, 114))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel98.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel98.setText("Bill ID:");

        billIDLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        billIDLabel.setText(" ");

        jLabel100.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel100.setText("Order ID:");

        billOrderIDLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        billOrderIDLabel.setText(" ");

        jLabel102.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel102.setText("Date:");

        billDateCreateLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        billDateCreateLabel.setText(" ");

        jLabel104.setFont(new java.awt.Font("Unispace", 3, 24)); // NOI18N
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel104.setText("THANK YOU!");

        jLabel105.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel105.setText("Staff's ID:");

        billStaffIDLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        billStaffIDLabel.setText(" ");

        jLabel107.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel107.setText("Cus's ID:");

        billCusIDLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        billCusIDLabel.setText(" ");

        jLabel109.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel109.setText("Stadium detail:");

        billStaDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stadium", "From", "To", "Total time", "Rate", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Float.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        billStaDetailTable.setEnabled(false);
        billStaDetailTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane18.setViewportView(billStaDetailTable);

        jLabel110.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel110.setText("Service detail:");

        billServiceDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service name", "Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        billServiceDetailTable.setEnabled(false);
        billServiceDetailTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane19.setViewportView(billServiceDetailTable);

        jLabel111.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel111.setText("Temp:");

        billTempTotalLabel.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        billTempTotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        billTempTotalLabel.setText(" ");

        jLabel113.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("See you again.");

        jLabel99.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel99.setText("Member Discount:");

        memDiscountLabel.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        memDiscountLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        memDiscountLabel.setText(" ");

        billTotalLabel.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        billTotalLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        billTotalLabel.setText(" ");

        jLabel103.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel103.setText("Total:");

        jLabel101.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel101.setText("Membership:");

        billMemshipLabel.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        billMemshipLabel.setText(" ");

        jLabel106.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel106.setText("Deposit:");

        billDepositLabel.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        billDepositLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        billDepositLabel.setText(" ");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel102)
                            .addComponent(jLabel98))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(billIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(billDateCreateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(81, 81, 81)))
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel100)
                            .addComponent(jLabel105)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel107)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(billCusIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel101)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(billStaffIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(billMemshipLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(billOrderIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(130, 130, 130))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(247, 247, 247))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(283, 283, 283))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel103)
                                .addGap(18, 18, 18)
                                .addComponent(billTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel99)
                                        .addComponent(jLabel111)
                                        .addComponent(jLabel106))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(billTempTotalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(memDiscountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                        .addComponent(billDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(38, 38, 38))))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane19))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane18))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel109))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel110)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel104)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(billIDLabel)
                    .addComponent(billOrderIDLabel)
                    .addComponent(jLabel100))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(billDateCreateLabel)
                    .addComponent(jLabel105)
                    .addComponent(billStaffIDLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(billCusIDLabel)
                    .addComponent(jLabel101)
                    .addComponent(billMemshipLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel109)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel110)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(billTempTotalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(billDepositLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(memDiscountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(billTotalLabel)
                    .addComponent(jLabel103))
                .addGap(28, 28, 28)
                .addComponent(jLabel113)
                .addContainerGap())
        );

        javax.swing.GroupLayout billPanelLayout = new javax.swing.GroupLayout(billPanel);
        billPanel.setLayout(billPanelLayout);
        billPanelLayout.setHorizontalGroup(
            billPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        billPanelLayout.setVerticalGroup(
            billPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(billPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        infoPanel.setBackground(new java.awt.Color(255, 255, 204));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel4.setText("ID Work");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel5.setText("FName");

        idTxt.setBackground(new java.awt.Color(242, 242, 242));
        idTxt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        idTxt.setEnabled(false);

        fnameTxt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fnameTxt.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel6.setText("LName");

        lnameTxt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lnameTxt.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel7.setText("Phone");

        phoneTxt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        phoneTxt.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel8.setText("ID");

        cmndTxt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmndTxt.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        jLabel3.setText("Off");

        quitYesRadio.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        quitYesRadio.setText("Yes");
        quitYesRadio.setEnabled(false);
        quitYesRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitYesRadioActionPerformed(evt);
            }
        });

        quitNoRadio.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        quitNoRadio.setText("No");
        quitNoRadio.setEnabled(false);
        quitNoRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitNoRadioActionPerformed(evt);
            }
        });

        fnameWrong.setForeground(new java.awt.Color(255, 0, 51));
        fnameWrong.setText(" ");

        lnameWrong.setForeground(new java.awt.Color(255, 0, 51));
        lnameWrong.setText(" ");

        phoneWrong.setForeground(new java.awt.Color(255, 0, 51));
        phoneWrong.setText(" ");

        cmndWrong.setForeground(new java.awt.Color(255, 0, 0));
        cmndWrong.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(quitNoRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitYesRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(fnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmndTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                        .addComponent(phoneTxt, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(fnameWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lnameWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(phoneWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmndWrong, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(lnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fnameWrong)
                    .addComponent(lnameWrong))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(5, 5, 5)
                .addComponent(phoneWrong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmndTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(cmndWrong)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(quitYesRadio)
                    .addComponent(quitNoRadio))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        settingBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        settingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-setting-32.png"))); // NOI18N
        settingBtn.setText("Settings");
        settingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingBtnActionPerformed(evt);
            }
        });

        changepwdBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        changepwdBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/change-pwd-icon.png"))); // NOI18N
        changepwdBtn.setText("Change password");
        changepwdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changepwdBtnActionPerformed(evt);
            }
        });

        savebtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-save-32.png"))); // NOI18N
        savebtn.setText("Save");
        savebtn.setEnabled(false);
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        cancelBtn.setFont(new java.awt.Font("Unispace", 0, 12)); // NOI18N
        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlysanbong/images/icons8-cancel-32.png"))); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.setEnabled(false);
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(settingBtn)
                        .addGap(71, 71, 71)
                        .addComponent(changepwdBtn)
                        .addGap(59, 59, 59)
                        .addComponent(savebtn)
                        .addGap(88, 88, 88)
                        .addComponent(cancelBtn)))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settingBtn)
                    .addComponent(changepwdBtn)
                    .addComponent(savebtn)
                    .addComponent(cancelBtn))
                .addContainerGap(236, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(customerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(viewFoodPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(billPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(customerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(viewFoodPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(billPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(infoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void infoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoBtnActionPerformed
        // TODO add your handling code here:
        infoPanel.setVisible(true);
        bookingPanel.setVisible(false);
        customerPanel.setVisible(false);
        viewFoodPanel.setVisible(false);
        billPanel.setVisible(false);

    }//GEN-LAST:event_infoBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new UserLogin().setVisible(true);
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void bookingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingBtnActionPerformed
        // TODO add your handling code here:
        infoPanel.setVisible(false);
        bookingPanel.setVisible(true);
        customerPanel.setVisible(false);
        viewFoodPanel.setVisible(false);
        billPanel.setVisible(false);
        
        
        //PRE ORDER ===========================================================
        
        preOrderStaffIdLabel.setText(staff.getManv());
        preOrderDateCreateLabel.setText(clock.getCurrentDateTime2());
        pdList = pdDao.getPreOrderList();
        
        // tu tao mapd 
        String autoPdID = new AutoID().getAutoPhieuDatID(pdList);
        preOrderIdLabel.setText(autoPdID);

        preOrderCusIDCbBox.removeAllItems();
        for (KhachHang kh : khList) {
            preOrderCusIDCbBox.addItem(kh.getMakh());
        }

        ctpdList = new ArrayList<>();
        tempPreOrderTableModel = (DefaultTableModel) preOrderDetailTable.getModel();
        preOrderDetailTableModel = (DefaultTableModel) preOrderDetailTable2.getModel();

        startYearChooser1.setYear(Integer.valueOf(clock.getCurrentYMD_HM("year")));
        startMonthCbBox1.setSelectedIndex(Integer.valueOf(clock.getCurrentYMD_HM("month")) - 1);
        startDayCbBox1.setSelectedIndex(Integer.valueOf(clock.getCurrentYMD_HM("day")) - 1);
        startHour1.setValue(Integer.valueOf(clock.getCurrentYMD_HM("hour")));
        startMinute1.setValue(Integer.valueOf(clock.getCurrentYMD_HM("minute")));

        endHour1.setValue(Integer.valueOf(clock.getCurrentYMD_HM("hour")));
        endMinute1.setValue(Integer.valueOf(clock.getCurrentYMD_HM("minute")));

        // pre-order list
        preOrderListTableModel.setRowCount(0);
        showPreOrderList(pdList);

        preOrderPhoneWrong.setText(" ");
        preOrderWrong.setText(" ");
        //======================================================================

        // ORDER NOW ===========================================================
        
        orderStaffIdLabel.setText(staff.getManv());
        orderDateCreateLabel.setText(clock.getCurrentDateTime2());
        // phiethue da tung dat o day
        
        // tu tao mapt 
        String autoPtID = new AutoID().getAutoPhieuThueID(ptList);
        orderIdLabel.setText(autoPtID);
        
        orderCusIDCbBox.removeAllItems();
        for (KhachHang kh : khList) {
            orderCusIDCbBox.addItem(kh.getMakh());
        }
        
        tempOrderTableModel = (DefaultTableModel) orderDetailTable.getModel();
        
        startYearChooser2.setYear(Integer.valueOf(clock.getCurrentYMD_HM("year")));
        startMonthCbBox2.setSelectedIndex(Integer.valueOf(clock.getCurrentYMD_HM("month")) - 1);
        startDayCbBox2.setSelectedIndex(Integer.valueOf(clock.getCurrentYMD_HM("day")) - 1);
        startHour2.setValue(Integer.valueOf(clock.getCurrentYMD_HM("hour")));
        startMinute2.setValue(Integer.valueOf(clock.getCurrentYMD_HM("minute")));

        endHour2.setValue(Integer.valueOf(clock.getCurrentYMD_HM("hour")));
        endMinute2.setValue(Integer.valueOf(clock.getCurrentYMD_HM("minute")));
        
        ctptList = new ArrayList<>();
        orderDetailTableModel = (DefaultTableModel) orderDetailTable.getModel();
        orderServicesTableModel = (DefaultTableModel) orderServicesTable.getModel();
        orderDetailTableModel2 = (DefaultTableModel) orderDetailTable2.getModel();
        ctdvList = new ArrayList<>();
        
        orderListTableModel.setRowCount(0);
        showOrderList(ptList);
        
        orderServicesTableModel2 = (DefaultTableModel) orderServicesTable2.getModel();
    }//GEN-LAST:event_bookingBtnActionPerformed

    private void viewFoodBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFoodBtnActionPerformed
        // TODO add your handling code here:
        infoPanel.setVisible(false);
        bookingPanel.setVisible(false);
        customerPanel.setVisible(false);
        viewFoodPanel.setVisible(true);
        billPanel.setVisible(false);

    }//GEN-LAST:event_viewFoodBtnActionPerformed

    private void settingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingBtnActionPerformed
        // TODO add your handling code here:
        fnameTxt.setEnabled(true);
        lnameTxt.setEnabled(true);
        phoneTxt.setEnabled(true);
        cmndTxt.setEnabled(true);

        savebtn.setEnabled(true);
        cancelBtn.setEnabled(true);
    }//GEN-LAST:event_settingBtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        // TODO add your handling code here:
        String fname = fnameTxt.getText();
        String lname = lnameTxt.getText();
        String phone = phoneTxt.getText();
        String cmnd = cmndTxt.getText();

        boolean hopLe = true;
        CheckText ct = new CheckText();

        if (fname.equals("")) {
            fnameWrong.setText("First name empty");
            hopLe = false;
        } else if(fname.length() > 50){
            fnameWrong.setText("First name must have maximum 50 characters");
            hopLe = false;
        }
        
        if (lname.equals("")) {
            lnameWrong.setText("Last name empty");
            hopLe = false;
        } else if (lname.length() > 20 ) {
            lnameWrong.setText("Last name must have maximum 20 characters");
            hopLe = false;
        }
        
        if (!ct.isPhoneNumber(phone)) {
            phoneWrong.setText("Phone must has maxinum 11 numbers");
            hopLe = false;
        }
        
        if (!ct.isCmnd(cmnd)) {
            cmndWrong.setText("ID must has 12 numbers");
            hopLe = false;
        }

        if (hopLe) {
            staff.setHo(fname);
            staff.setTen(lname);
            staff.setSdt(phone);
            staff.setCmnd(cmnd);
            if (staffDao.updateStaffInfo(staff)) {
                JOptionPane.showMessageDialog(rootPane, "Update info successful");
                resetInfolabels();
                showInfo();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Update info failed");
            }
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        fnameTxt.setText(staff.getHo());
        lnameTxt.setText(staff.getTen());
        phoneTxt.setText(staff.getSdt());
        cmndTxt.setText(staff.getCmnd());

        fnameTxt.setEnabled(false);
        lnameTxt.setEnabled(false);
        phoneTxt.setEnabled(false);
        cmndTxt.setEnabled(false);
        quitNoRadio.setEnabled(false);
        quitYesRadio.setEnabled(false);

        savebtn.setEnabled(false);
        cancelBtn.setEnabled(false);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void quitYesRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitYesRadioActionPerformed
        // TODO add your handling code here:
        quitNoRadio.setSelected(false);
    }//GEN-LAST:event_quitYesRadioActionPerformed

    private void quitNoRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitNoRadioActionPerformed
        // TODO add your handling code here:
        quitYesRadio.setSelected(false);
    }//GEN-LAST:event_quitNoRadioActionPerformed

    private void customerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBtnActionPerformed
        // TODO add your handling code here:
        infoPanel.setVisible(false);
        bookingPanel.setVisible(false);
        customerPanel.setVisible(true);
        viewFoodPanel.setVisible(false);
        billPanel.setVisible(false);
    }//GEN-LAST:event_customerBtnActionPerformed

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        // TODO add your handling code here:
        infoPanel.setVisible(false);
        bookingPanel.setVisible(false);
        customerPanel.setVisible(false);
        viewFoodPanel.setVisible(false);
        billPanel.setVisible(true);
    }//GEN-LAST:event_payBtnActionPerformed

    private void sortCusCbBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortCusCbBoxItemStateChanged
        // TODO add your handling code here:

        String sortChoose = sortCusCbBox.getSelectedItem().toString();
        ArrayList<KhachHang> tempKhList = khList;
        switch (sortChoose) {
            case "All": {
                khTableModel.setRowCount(0);
                showCusList(khList);
                break;
            }
            case "Last name": {
                khTableModel.setRowCount(0);
                tempKhList.sort((KhachHang a, KhachHang b) -> {
                    return a.getTen().compareTo(b.getTen());
                });
                showCusList(tempKhList);
                break;
            }
            case "Number of rent": {
                khTableModel.setRowCount(0);
                tempKhList.sort((KhachHang a, KhachHang b) -> {
                    return b.getSolan_thuesan() - a.getSolan_thuesan();
                });
                showCusList(tempKhList);
                break;
            }
        }
    }//GEN-LAST:event_sortCusCbBoxItemStateChanged

    private void cancelCusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelCusBtnActionPerformed
        // TODO add your handling code here:
        cusIdTxt.setEnabled(true);
        cusUsernameTxt.setEnabled(true);

        resetCusTexts();
        resetCusLabels();
    }//GEN-LAST:event_cancelCusBtnActionPerformed

    private void updateCusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCusBtnActionPerformed
        // TODO add your handling code here:
        resetCusLabels();

        String id = cusIdTxt.getText();
        String fname = cusFnameTxt.getText();
        String lname = cusLnameTxt.getText();
        String phone = cusPhoneTxt.getText();
        String cmnd = cusCmndTxt.getText();
        String username = cusUsernameTxt.getText();

        int index = khTable.getSelectedRow();

        boolean hopLe = true;
        CheckText ct = new CheckText();

        if (fname.equals("")) {
            cusFnameWrong.setText("First name is empty!");
            hopLe = false;
        } else if (fname.length() > 50) {
            cusFnameWrong.setText("First name is maximum 50 characters!");
            hopLe = false;
        } 

        if (lname.equals("")) {
            cusLnameWrong.setText("Last name is empty!");
            hopLe = false;
        } else if (lname.length() > 20) {
            cusLnameWrong.setText("Last name is maximum 20 characters!");
            hopLe = false;
        }

        if (phone.equals("")) {
            cusPhoneWrong.setText("Phone is empty!");
            hopLe = false;

        } else if (!ct.isPhoneNumber(phone)) {
            cusPhoneWrong.setText("Phone is invalid, must me number");
            hopLe = false;
        }

        if (cmnd.equals("")) {
            cusCmndWrong.setText("ID is empty!");
            hopLe = false;
        } else if (!ct.isCmnd(cmnd)) {
            cusCmndWrong.setText("ID is invalid, maximum 12 numbers");
            hopLe = false;
        }

        if (hopLe) {

            KhachHang kh = new KhachHang(id, fname, lname, phone, cmnd, 0, username, "normal");

            if (khDao.updateCusInfo(kh)) {

                khList.set(index, kh);

                khTableModel.setRowCount(0);
                resetCusTexts();
                resetCusLabels();
                cusIdTxt.setEnabled(true);
                cusUsernameTxt.setEnabled(true);
                showCusList(khList);

                JOptionPane.showMessageDialog(rootPane, "Update customer info successfully!");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Update customer info failed!");
            }
        }
    }//GEN-LAST:event_updateCusBtnActionPerformed

    private void addCusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusBtnActionPerformed
        // TODO add your handling code here:

        resetCusLabels();

        String id = cusIdTxt.getText();
        String fname = cusFnameTxt.getText();
        String lname = cusLnameTxt.getText();
        String phone = cusPhoneTxt.getText();
        String cmnd = cusCmndTxt.getText();
        String username = cusUsernameTxt.getText();

        boolean hopLe = true;
        CheckText ct = new CheckText();

        if (id.equals("")) {
            cusIdWrong.setText("ID is empty!");
            hopLe = false;
        } else if (khDao.checkCusId(id)) {
            cusIdWrong.setText("ID is existed!");
            hopLe = false;
        }

        if (fname.equals("")) {
            cusFnameWrong.setText("First name is empty!");
            hopLe = false;
        }

        if (lname.equals("")) {
            cusLnameWrong.setText("Last name is empty!");
            hopLe = false;
        }

        if (phone.equals("")) {
            cusPhoneWrong.setText("Phone is empty!");
            hopLe = false;

        } else if (!ct.isPhoneNumber(phone)) {
            cusPhoneWrong.setText("Phone is invalid, must me number");
            hopLe = false;
        }

        if (cmnd.equals("")) {
            cusCmndWrong.setText("ID is empty!");
            hopLe = false;
        } else if (!ct.isCmnd(cmnd)) {
            cusCmndWrong.setText("ID is invalid, maximum 12 numbers");
            hopLe = false;
        }

        if (username.equals("")) {
            cusUsernameWrong.setText("Username is empty!");
            hopLe = false;
        } else if (accDao.checkUsername(username)) {
            cusUsernameWrong.setText("Username is already existed!");
            hopLe = false;
        }

        if (hopLe) {

            KhachHang kh = new KhachHang(id, fname, lname, phone, cmnd, 0, username, "normal");

            if (accDao.addAccount(username, "khach") && khDao.addCus(kh)) {
                khList.add(kh);
                khTableModel.addRow(new Object[]{id, fname, lname, phone, cmnd, 0, username, "normal"});
                JOptionPane.showMessageDialog(rootPane, "Add customer successfully!"
                    + "\nusername: " + username + "\npassword: 123"
                    + "\nChange password before use");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Add customer failed!");
            }
        }
    }//GEN-LAST:event_addCusBtnActionPerformed

    private void khTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khTableMouseClicked
        // TODO add your handling code here:
        cusIdTxt.setEnabled(false);
        cusUsernameTxt.setEnabled(false);

        int index = khTable.getSelectedRow();

        if (index == -1) {

        } else {
            KhachHang kh = khList.get(index);

            cusIdTxt.setText(kh.getMakh());
            cusFnameTxt.setText(kh.getHo());
            cusLnameTxt.setText(kh.getTen());
            cusPhoneTxt.setText(kh.getSdt());
            cusCmndTxt.setText(kh.getCmnd());
            cusUsernameTxt.setText(kh.getTaikhoan());

        }
    }//GEN-LAST:event_khTableMouseClicked

    private void foodTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodTableMouseClicked
        // TODO add your handling code here:

        int index = foodTable.getSelectedRow();
        if (index == -1) {
        } else {
            DichVu dv = dvList.get(index);
            ImageIcon image = new ImageIcon(dv.getHinhanh());
            Image temp = image.getImage();
            ImageIcon scaledImage = new ImageIcon(temp.getScaledInstance(foodImageLabel.getWidth(), foodImageLabel.getHeight(), Image.SCALE_SMOOTH));
            foodImageLabel.setIcon(scaledImage);
            
            if(dv.isHethang()){
                outOfStockNo.setSelected(false);
                outOfStockYes.setSelected(true);
            }
            else {
                outOfStockNo.setSelected(true);
                outOfStockYes.setSelected(false);
            }
            
            updateFoodBtn.setEnabled(true);
        }
    }//GEN-LAST:event_foodTableMouseClicked

    private void preOrderCusIDCbBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_preOrderCusIDCbBoxItemStateChanged
        // TODO add your handling code here:
        //        String cusID = (String) cusIDCbBox.getSelectedItem();
        //        if (!cusID.equals("khvanglai")) {
            //            String cusPhone = khDao.getCusPhone(cusID);
            //            preOrderPhoneTxt.setText(cusPhone);
            //        } else {
            //            preOrderPhoneTxt.setText("");
            //        }
    }//GEN-LAST:event_preOrderCusIDCbBoxItemStateChanged

    private void avaiStaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_avaiStaTableMouseClicked
        // TODO add your handling code here:

        addDetailPreOrderBtn.setEnabled(true);
    }//GEN-LAST:event_avaiStaTableMouseClicked

    private void addDetailPreOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDetailPreOrderBtnActionPerformed
        // TODO add your handling code here:
        int index = avaiStaTable.getSelectedRow();

        if (index == -1) {

        } else {
            TrangThaiSan preOrderItem = avaiStaList.get(index);
            this.preOrderItem = preOrderItem;

            int sYear = startYearChooser1.getYear();
            int sMonth = startMonthCbBox1.getSelectedIndex() + 1;
            int sDay = startDayCbBox1.getSelectedIndex() + 1;

            String date = cal.formatDate(sYear, sMonth, sDay);
            String sTime = cal.formatHourAndMinute((int)startHour1.getValue(), (int)startMinute1.getValue());
            String eTime = cal.formatHourAndMinute((int)endHour1.getValue(), (int)endMinute1.getValue());

            AddToDetailPreOrder addDetailForm = new AddToDetailPreOrder(this,
                rootPaneCheckingEnabled, preOrderItem, date, sTime, eTime, preOrderIdLabel.getText(), index);
            addDetailForm.setVisible(true);
        }
    }//GEN-LAST:event_addDetailPreOrderBtnActionPerformed

    private void endHour1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endHour1StateChanged
        // TODO add your handling code here:
        int val = (int)endHour1.getValue();
        if( val == 24)
        endHour1.setValue(0);
        else if(val == -1)
        endHour1.setValue(23);
    }//GEN-LAST:event_endHour1StateChanged

    private void endMinute1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endMinute1StateChanged
        // TODO add your handling code here:
        int val = (int)endMinute1.getValue();
        if( val == 60)
        endMinute1.setValue(0);
        else if(val == -1)
        endMinute1.setValue(59);
    }//GEN-LAST:event_endMinute1StateChanged

    private void startHour1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startHour1StateChanged
        // TODO add your handling code here:
        int val = (int)startHour1.getValue();
        if( val == 24)
        startHour1.setValue(0);
        else if(val == -1)
        startHour1.setValue(23);
    }//GEN-LAST:event_startHour1StateChanged

    private void startMinute1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startMinute1StateChanged
        // TODO add your handling code here:
        int val = (int)startMinute1.getValue();
        if( val == 60)
        startMinute1.setValue(0);
        else if(val == -1)
        startMinute1.setValue(59);
    }//GEN-LAST:event_startMinute1StateChanged

    private void startMonthCbBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_startMonthCbBox1ItemStateChanged
        // TODO add your handling code here:
        int sYear = startYearChooser1.getYear();
        int sMonth = startMonthCbBox1.getSelectedIndex();
        int numsDay = cal.getNumsDayOfMonth(sYear, sMonth);
        startDayCbBox1.removeAllItems();

        for (int i = 0; i < numsDay; i++) {
            startDayCbBox1.addItem(Integer.toString(i + 1));
        }
    }//GEN-LAST:event_startMonthCbBox1ItemStateChanged

    private void checkAvaiStaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAvaiStaBtnActionPerformed
        // TODO add your handling code here:

        // ====== kiem tra neu trong gio lam viec thi cho check time ===========
        String currTime = clock.getCurrentTime();
        if( !isInWorkTime(currTime, rateList)) {
            showWorkTimeMessage(currTime, rateList);
            return;
        }
        //======================================================================

        if(!checkPreOrderFlag && !ctpdList.isEmpty()) {
            int ans = JOptionPane.showConfirmDialog(this, "This will clear pre-order detail list below!",
                "Caution", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(ans == JOptionPane.YES_OPTION) {
                tempPreOrderTableModel.setRowCount(0);
                ctpdList.clear();

            } else {
                return;
            }
        }

        int sYear = startYearChooser1.getYear();
        int sMonth = startMonthCbBox1.getSelectedIndex() + 1;
        int sDay = startDayCbBox1.getSelectedIndex() + 1;
        int sHour = (int) startHour1.getValue();
        int sMinute = (int) startMinute1.getValue();

        int eHour = (int) endHour1.getValue();
        int eMinute = (int) endMinute1.getValue();

        String sDateTime = cal.formatDate(sYear, sMonth, sDay) + " " + cal.formatHourAndMinute(sHour, sMinute);
        String eDateTime = cal.formatDate(sYear, sMonth, sDay) + " " + cal.formatHourAndMinute(eHour, eMinute);

        if (cal.isEndDateGtStartDate(sDateTime, eDateTime)) {
            stateList = stateDao.getTrangThaiSanList(sDateTime, eDateTime);
            avaiStaTableModel.setRowCount(0);
            avaiStaList = (ArrayList<TrangThaiSan>) stateList.stream()
            .filter(sta -> sta.getTenTrangThai().equals("Trống")).collect(Collectors.toList());
            dateTimeWrong1.setText(" ");
            showAvaiStadiums(avaiStaList);

            // set flag de lan check time tiep theo se clear preorder staidum list
            checkPreOrderFlag = false;

        } else {
            dateTimeWrong1.setText("End date-time must be greater than Start date-time");
        }
    }//GEN-LAST:event_checkAvaiStaBtnActionPerformed

    private void preOrderDetailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preOrderDetailTableMouseClicked
        // TODO add your handling code here:
        removePreOderItemBtn.setEnabled(true);
    }//GEN-LAST:event_preOrderDetailTableMouseClicked

    private void removePreOderItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePreOderItemBtnActionPerformed
        // TODO add your handling code here:
        int index = preOrderDetailTable.getSelectedRow();

        if (index == -1) {

        } else {
            ctpdList.remove(index);
            avaiStaList.add(preOrderItem);
            avaiStaTableModel.addRow(new Object[]{this.preOrderItem.getTenSan(),
                this.preOrderItem.getTenLoaiSan(), this.preOrderItem.getPricePerHour()});
        tempPreOrderTableModel.removeRow(index);
        removePreOderItemBtn.setEnabled(false);
        }
    }//GEN-LAST:event_removePreOderItemBtnActionPerformed

    private void createPreOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPreOrderBtnActionPerformed
        // TODO add your handling code here:
        CheckText ct = new CheckText();

        String id = preOrderIdLabel.getText();
        String date = preOrderDateCreateLabel.getText();
        String note = preOrderNoteTxt.getText();
        String cusId = (String) preOrderCusIDCbBox.getSelectedItem();
        String staffId = preOrderStaffIdLabel.getText();

        boolean hopLe = true;
        String phone = preOrderPhoneTxt.getText();
        if (phone.equals("")) {
            preOrderPhoneWrong.setText("Phone is empty!");
            hopLe = false;
        } else if (!ct.isPhoneNumber(phone)) {
            preOrderPhoneWrong.setText("Phone is invalid!");
            hopLe = false;
        }

        if (ctpdList.isEmpty()) {
            preOrderWrong.setText("Pre-order list is empty. Select at least 1 stadium!");
            hopLe = false;
        }

        if (hopLe) {
            PhieuDat pd = new PhieuDat(id, date, note, cusId, staffId, phone);
            try {
                if (pdDao.addPreOrder(pd) && ctpdDao.addPreOrderDetail(ctpdList)
                    && stateDao.addTrangThaiSanPreOrder(ctpdList)) {
                    pdList.add(pd);
                    resetPreOrder();

                    // show pd list
                    preOrderListTableModel.setRowCount(0);
                    showPreOrderList(pdList);
                    JOptionPane.showMessageDialog(rootPane, "Add Pre-Order successfully!");

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Add Pre-Order failed!");
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_createPreOrderBtnActionPerformed

    private void preOrderListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preOrderListTableMouseClicked
        // TODO add your handling code here:
        int index = preOrderListTable.getSelectedRow();

        if (index == -1) {

        } else {
            // show pre-order detail
            String mapd = pdList.get(index).getMapd();

            preOrderDateCreate2.setText("Date: " + pdList.get(index).getNgayDat()) ;
            preOrderStaffIdLabel2.setText(pdList.get(index).getManv());
            preOrderCusIdLabel2.setText(pdList.get(index).getMakh());
            preOrderPhoneTxt2.setText(pdList.get(index).getSdt());
            preOrderNoteTxt2.setText(pdList.get(index).getGhiChu());

            ArrayList<CT_PhieuDat> ctpdList2 = ctpdDao.getPreOrderDetail(mapd);
            showPreOrderDetail(ctpdList2);

            double total = countTotalDeposit(ctpdList2);
            totalDepositLabel.setText(String.valueOf(total));

            if(hasOrder(mapd)) {
                preOrderExpiredLabel.setText("HAS ORDER");
            } else if(checkExpiredPreOrder(mapd))
            preOrderExpiredLabel.setText("EXPIRED");
            else preOrderExpiredLabel.setText(" ");
        }
    }//GEN-LAST:event_preOrderListTableMouseClicked

    private void orderCusIDCbBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orderCusIDCbBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_orderCusIDCbBoxItemStateChanged

    private void orderAvaiStaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderAvaiStaTableMouseClicked
        // TODO add your handling code here:
        addDetailOrderBtn.setEnabled(true);
    }//GEN-LAST:event_orderAvaiStaTableMouseClicked

    private void addDetailOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDetailOrderBtnActionPerformed
        // TODO add your handling code here:

        int index = orderAvaiStaTable.getSelectedRow();

        if (index == -1) {

        } else {
            TrangThaiSan orderItem = avaiStaList.get(index);
            this.orderItem = orderItem;

            int sYear = startYearChooser2.getYear();
            int sMonth = startMonthCbBox2.getSelectedIndex() + 1;
            int sDay = startDayCbBox2.getSelectedIndex() + 1;

            String date = cal.formatDate(sYear, sMonth, sDay);
            String sTime = cal.formatHourAndMinute((int)startHour2.getValue(), (int)startMinute2.getValue());
            String eTime = cal.formatHourAndMinute((int)endHour2.getValue(), (int)endMinute2.getValue());

            String makhunggio = getMaKhungGio(rateList);
            AddToDetailOrder addOrder = new AddToDetailOrder(this, rootPaneCheckingEnabled,
                orderItem, sTime, eTime, orderIdLabel.getText(), makhunggio, index);
            addOrder.setVisible(true);
        }

    }//GEN-LAST:event_addDetailOrderBtnActionPerformed

    private void startMonthCbBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_startMonthCbBox2ItemStateChanged
        // TODO add your handling code here:
        int sYear = startYearChooser2.getYear();
        int sMonth = startMonthCbBox2.getSelectedIndex();
        int numsDay = cal.getNumsDayOfMonth(sYear, sMonth);
        startDayCbBox2.removeAllItems();

        for (int i = 0; i < numsDay; i++) {
            startDayCbBox2.addItem(Integer.toString(i + 1));
        }
    }//GEN-LAST:event_startMonthCbBox2ItemStateChanged

    private void endHour2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endHour2StateChanged
        // TODO add your handling code here:
        int val = (int)endHour2.getValue();
        if( val == 24)
        endHour2.setValue(0);
        else if(val == -1)
        endHour2.setValue(23);
    }//GEN-LAST:event_endHour2StateChanged

    private void endMinute2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endMinute2StateChanged
        // TODO add your handling code here:
        int val = (int)endMinute2.getValue();
        if( val == 60)
        endMinute2.setValue(0);
        else if(val == -1)
        endMinute2.setValue(59);
    }//GEN-LAST:event_endMinute2StateChanged

    private void checkAvaiStaBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAvaiStaBtn1ActionPerformed
        // TODO add your handling code here:

        // ====== kiem tra neu trong gio lam viec thi cho check time ===========
        String currTime = clock.getCurrentTime();
        if( !isInWorkTime(currTime, rateList)) {
            showWorkTimeMessage(currTime, rateList);
            return;
        }
        //======================================================================
        
        if(!checkOrderFlag && !ctptList.isEmpty()) {
            int ans = JOptionPane.showConfirmDialog(this, "This will clear Order detail list below!",
                    "Caution", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(ans == JOptionPane.YES_OPTION) {
                orderDetailTableModel.setRowCount(0);
                ctptList.clear();
                
            } else {
                return;
            }
        }

        int sYear = startYearChooser2.getYear();
        int sMonth = startMonthCbBox2.getSelectedIndex() + 1;
        int sDay = startDayCbBox2.getSelectedIndex() + 1;
        int sHour = (int) startHour2.getValue();
        int sMinute = (int) startMinute2.getValue();

        int eHour = (int) endHour2.getValue();
        int eMinute = (int) endMinute2.getValue();

        String sDateTime = cal.formatDate(sYear, sMonth, sDay) + " " + cal.formatHourAndMinute(sHour, sMinute);
        String eDateTime = cal.formatDate(sYear, sMonth, sDay) + " " + cal.formatHourAndMinute(eHour, eMinute);
        if (cal.isEndDateGtStartDate(sDateTime, eDateTime)) {
            stateList = stateDao.getTrangThaiSanList(sDateTime, eDateTime);
            orderAvaiStaTableModel.setRowCount(0);
            avaiStaList = (ArrayList<TrangThaiSan>) stateList.stream()
            .filter(sta -> sta.getTenTrangThai().equals("Trống")).collect(Collectors.toList());
            dateTimeWrong2.setText(" ");
            showOrderAvaiStadiums(avaiStaList);
            
            // set flag de lan check time tiep theo se clear order staidum list
            checkOrderFlag = false;
        } else {
            dateTimeWrong2.setText("End date-time must be greater than Start date-time");
        }
    }//GEN-LAST:event_checkAvaiStaBtn1ActionPerformed

    private void createOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrderBtnActionPerformed
        // TODO add your handling code here:
        CheckText ct = new CheckText();

        String id = orderIdLabel.getText();
        String date = orderDateCreateLabel.getText();
        String note = orderNoteTxt.getText();
        String cusId = (String) orderCusIDCbBox.getSelectedItem();
        String staffId = orderStaffIdLabel.getText();
        String mapd = preOrderIdTxt.getText();
        String phone = orderPhoneTxt.getText();

        boolean hopLe = true;

        if (phone.equals("")) {
            orderPhoneWrong.setText("Phone is empty!");
            hopLe = false;
        } else if (!ct.isPhoneNumber(phone)) {
            orderPhoneWrong.setText("Phone is invalid!");
            hopLe = false;
        }

        if (ctptList.isEmpty()) {
            orderWrong.setText("Order list is empty. Select at least 1 stadium!");
            hopLe = false;
        }

        if(mapd.equals("")) {
            mapd = null;
        }

        if(hopLe) {
            PhieuThue pt = new PhieuThue(id, date, note, cusId, staffId, mapd, phone);

            try {
                if(ptDao.addOrder(pt) && ctptDao.addOrderDetailList(ctptList)
                    && stateDao.addTrangThaiSanOrder(ctptList)
                    && ctdvDao.addServiceDetailOrder(ctdvList)) {

                    resetOrder();
                    ptList.add(pt);
                    showOrderList(ptList);

                    JOptionPane.showMessageDialog(rootPane, "Add Order successfully!");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Add Order failed!");
                }

            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_createOrderBtnActionPerformed

    private void orderDetailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderDetailTableMouseClicked
        // TODO add your handling code here:
        int index = orderDetailTable.getSelectedRow();
        if(index == -1) {

        } else {
            if(index < ctptListSize ) {
                removeOrderItemBtn.setEnabled(false);
            } else removeOrderItemBtn.setEnabled(true);
        }
    }//GEN-LAST:event_orderDetailTableMouseClicked

    private void removeOrderItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOrderItemBtnActionPerformed
        // TODO add your handling code here:
        int index = orderDetailTable.getSelectedRow();

        if (index == -1) {

        } else {
            ctptList.remove(index);
            avaiStaList.add(orderItem);
            orderAvaiStaTableModel.addRow(new Object[]{this.orderItem.getTenSan(),
                this.orderItem.getTenLoaiSan(), this.orderItem.getPricePerHour()});
        orderDetailTableModel.removeRow(index);
        removeOrderItemBtn.setEnabled(false);
        }
    }//GEN-LAST:event_removeOrderItemBtnActionPerformed

    private void removeOrderServiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOrderServiceBtnActionPerformed
        // TODO add your handling code here:
        int index = orderServicesTable.getSelectedRow();

        if(index == -1) {

        } else {
            orderServicesTableModel.removeRow(index);
            ctdvList.remove(index);
            removeOrderServiceBtn.setEnabled(false);
            if(orderServicesTableModel.getRowCount() == 0 ) {
                removeOrderServiceBtn.setEnabled(false);
            }
        }
    }//GEN-LAST:event_removeOrderServiceBtnActionPerformed

    private void orderServicesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderServicesTableMouseClicked
        // TODO add your handling code here:
        int index = orderServicesTable.getSelectedRow();

        if(index == -1) {

        } else {
            removeOrderServiceBtn.setEnabled(true);
        }
    }//GEN-LAST:event_orderServicesTableMouseClicked

    private void orderServicesTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_orderServicesTablePropertyChange
        // TODO add your handling code here:
        int index = orderServicesTable.getSelectedRow();

        if(index == -1) {

        } else {
            CT_DichVu item = ctdvList.get(index);
            if(evt.getOldValue() == null) { // tranh truong hop de cell quantity rong~
                orderServicesTableModel.setValueAt((int)item.getSoluong(),index, 1);
            }
            item.setSoluong((int)orderServicesTableModel.getValueAt(index, 1));
        }
    }//GEN-LAST:event_orderServicesTablePropertyChange

    private void viewServicesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewServicesBtnActionPerformed
        // TODO add your handling code here:
        AddServiceOrder addService = new AddServiceOrder(this, rootPaneCheckingEnabled,
            dvList, 1, orderIdLabel.getText());
        addService.setVisible(true);
    }//GEN-LAST:event_viewServicesBtnActionPerformed

    private void refreshOrderServiceTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshOrderServiceTableBtnActionPerformed
        // TODO add your handling code here:
        orderServicesTableModel.setRowCount(0);
        for(int i = 0; i < ctdvList.size(); i++) {
            CT_DichVu item = ctdvList.get(i);
            String itemName = getNameOfService(item.getMadv());
            orderServicesTableModel.addRow(new Object[]{itemName, item.getSoluong()});
        }
        removeOrderServiceBtn.setEnabled(false);
    }//GEN-LAST:event_refreshOrderServiceTableBtnActionPerformed

    private void checkPreOrderIdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPreOrderIdBtnActionPerformed
        // TODO add your handling code here:

        String currTime = clock.getCurrentTime();
        if( !isInWorkTime(currTime, rateList)) {
            showWorkTimeMessage(currTime, rateList);
            return;
        }

        String preOrderID = preOrderIdTxt.getText();

        if(hasOrder(preOrderID)) { // neu pd da duoc lap phieu thue roi thi bao loi
            preOrderIdWrong.setText("This already has ORDER!");
            return;
        }

        if(preOrderID.equals("")) {
            orderDetailTableModel.setRowCount(0);
            preOrderIdWrong.setText(" ");

            orderPhoneTxt.setText("");
            orderCusIDCbBox.setSelectedItem("khvanglai");
            orderNoteTxt.setText("");

            ctptList.clear();
            ctptListSize = 0;
        }
        else if (tonTaiPhieuDat(preOrderID)) {

            if( checkExpiredPreOrder(preOrderID)) {
                JOptionPane.showMessageDialog(this, "This PREORDER has expried.\nCan't be used to make ORDER");
                return;
            }

            preOrderIdWrong.setText(" ");
            ctptList = ctptDao.getOrderDetailWithPreOrder(preOrderID, orderIdLabel.getText());

            // luu kich thuoc cua ctptList de sau nay kiem tra san nao moi dc them vao
            // ctptList, tranh xoa cac san da dat truoc
            ctptListSize = ctptList.size();

            showOrderDetailList(ctptList);

        } else {
            ctptList.clear();
            orderDetailTableModel.setRowCount(0);
            orderCusIDCbBox.setSelectedItem("khvanglai");
            orderPhoneTxt.setText("");
            orderNoteTxt.setText("");
            preOrderIdWrong.setText("Not exist!");
        }

        PhieuDat pd = pdDao.getPreOrder(preOrderID, pdList);

        if(pd == null) {

        } else {
            orderNoteTxt.setText(pd.getGhiChu());
            orderPhoneTxt.setText(pd.getSdt());

            String makh = pd.getMakh();
            orderCusIDCbBox.setSelectedItem(makh);

        }
    }//GEN-LAST:event_checkPreOrderIdBtnActionPerformed

    private void orderListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderListTableMouseClicked
        // TODO add your handling code here:
        int index = orderListTable.getSelectedRow();
        if(index == -1) {

        } else {

            PhieuThue item = ptList.get(index);

            String mapt = item.getMapt();

            orderIdLabel2.setText(mapt);
            orderDateCreate2.setText(item.getNgayThue());
            orderStaffIdLabel2.setText(item.getManv());
            orderCusIdLabel2.setText(item.getMakh());
            orderPhoneTxt2.setText(item.getSdt());
            orderNoteTxt2.setText(item.getGhiChu());

            ctptList2 = ctptDao.getOrderDetail(mapt);

            // Neu trong phieu co san chua het gio thi van cho order food
            // Khong thi tat chuc nang chon food di
            checkExpiredOrder(ctptList2);

            showOrderDetail(ctptList2);

            ctdvList2 = ctdvDao.getOrderServices(mapt);
            showOrderServices(ctdvList2);

            double currentTotal = countCurrentTotal(ctptList2, ctdvList2);
            currentTotalOrderLabel.setText(String.format("%.3f", currentTotal));
        }
    }//GEN-LAST:event_orderListTableMouseClicked

    private void orderDetailTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderDetailTable2MouseClicked
        // TODO add your handling code here:
        endStadiumBtn.setEnabled(true);
    }//GEN-LAST:event_orderDetailTable2MouseClicked

    private void orderServicesTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderServicesTable2MouseClicked
        // TODO add your handling code here:
        removeOrderServiceBtn2.setEnabled(true);
    }//GEN-LAST:event_orderServicesTable2MouseClicked

    private void orderServicesTable2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_orderServicesTable2PropertyChange
        // TODO add your handling code here:
        int index = orderServicesTable2.getSelectedRow();

        if(index == -1) {

        } else {
            CT_DichVu item = ctdvList2.get(index);
            if(evt.getOldValue() == null) { // tranh truong hop de cell quantity rong~
                orderServicesTableModel2.setValueAt((int)item.getSoluong(),index, 1);
            }
            item.setSoluong((int)orderServicesTableModel2.getValueAt(index, 1));

            ctdvDao.updateServiceDetail(item);

            // update current total
            double currentTotal = countCurrentTotal(ctptList2, ctdvList2);
            currentTotalOrderLabel.setText(String.format("%.3f", currentTotal));
        }
    }//GEN-LAST:event_orderServicesTable2PropertyChange

    private void viewServicesBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewServicesBtn2ActionPerformed
        // TODO add your handling code here:
        AddServiceOrder addService = new AddServiceOrder(this, rootPaneCheckingEnabled, dvList, 2, orderIdLabel2.getText());
        addService.setVisible(true);

    }//GEN-LAST:event_viewServicesBtn2ActionPerformed

    private void removeOrderServiceBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOrderServiceBtn2ActionPerformed
        // TODO add your handling code here:
        int index = orderServicesTable2.getSelectedRow();

        if(index == -1) {

        } else {
            CT_DichVu item = ctdvList2.get(index);
            ctdvDao.deleteServiceDetail(item);
            ctdvList2.remove(index);
            orderServicesTableModel2.removeRow(index);
            removeOrderServiceBtn2.setEnabled(false);

            // update current total
            double currentTotal = countCurrentTotal(ctptList2, ctdvList2);
            currentTotalOrderLabel.setText(String.format("%.3f", currentTotal));

            if(orderServicesTableModel2.getRowCount() == 0 ) {
                removeOrderServiceBtn2.setEnabled(false);
            }
        }
    }//GEN-LAST:event_removeOrderServiceBtn2ActionPerformed

    private void refreshOrderServiceTableBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshOrderServiceTableBtn2ActionPerformed
        // TODO add your handling code here:
        if(ctdvList2 != null) {
            showOrderServices(ctdvList2);
        }
        removeOrderServiceBtn2.setEnabled(false);
    }//GEN-LAST:event_refreshOrderServiceTableBtn2ActionPerformed

    private void endStadiumBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endStadiumBtnActionPerformed
        // TODO add your handling code here:
        int index = orderDetailTable2.getSelectedRow();
        if(index == -1) {

        } else {
            CT_PhieuThue item = ctptList2.get(index);

            if(!item.getGiotra().equals("")){
                JOptionPane.showMessageDialog(this, "This stadium was set LEAVE TIME");
                return;
            }

            int ans = JOptionPane.showConfirmDialog(this, "Do you want to set LEAVE TIME for this stadium ?",
                "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if( ans == JOptionPane.YES_OPTION ){
                String leaveTime = "";
                if(cal.isEndDateGtStartDate(clock.getCurrentDateTime2(), item.getGio_dukientra())) {
                    leaveTime = clock.getCurrentDateTime2();
                } else {
                    leaveTime = item.getGio_dukientra();
                }

                item.setGiotra(leaveTime);
                ctptList2.set(index, item);
                try {
                    ctptDao.updateLeaveTimeOfStadium(item);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                showOrderDetail(ctptList2);

                // kiem tra lai phieu dat nay da qua han hay chua
                checkExpiredOrder(ctptList2);
                showOrderServices(ctdvList2);

                //kiem tra lai xem toan bo chi tiet phieu thue da het thoi gian thue
                //hay chua, neu roi thi auto tao bill
                if(isAllOrderDetailExpired(ctptList2)) {

                    String mahd = new AutoID().getAutoHoaDonID(billList);

                    Bill bill = autoCreateBill(mahd, orderIdLabel2.getText(), orderStaffIdLabel2.getText(),
                        orderCusIdLabel2.getText());
                    if(billDao.addBill(bill)) {
                        JOptionPane.showMessageDialog(this, "New bill: " + mahd + "\nhas been created for this order!");
                        billList.add(bill);
                        showBillList(billList);
                    } else {
                        JOptionPane.showMessageDialog(this, "Create bill for this order FAILED!");
                    }

                }
            }
        }
    }//GEN-LAST:event_endStadiumBtnActionPerformed

    private void billListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billListTableMouseClicked
        // TODO add your handling code here:
        int index = billListTable.getSelectedRow();
        if(index == -1) {

        } else {
            Bill bill = billList.get(index);
            billIDLabel.setText(bill.getMahd());
            billDateCreateLabel.setText(bill.getNgaylap());
            billOrderIDLabel.setText(bill.getMapt());
            billStaffIDLabel.setText(bill.getManv());
            billCusIDLabel.setText(bill.getMakh());
            billMemshipLabel.setText(khDao.getMembership(bill.getMakh()).toUpperCase());

            ArrayList<CT_PhieuThue> ctptList = ctptDao.getOrderDetailWithBillId(bill.getMahd(), bill.getMapt());
            ArrayList<CT_DichVu> ctdvList = ctdvDao.getOrderServices(bill.getMapt());

            showBillStadiumDetail(ctptList);
            showBillServiceDetail(ctdvList);

            double tempTotal = getTotalBillWithoutMemDiscount(ctptList, ctdvList);
            double deposit = 0.0;
            if(hasPreOrder(bill.getMapt()) != null) {
                deposit = ctpdDao.getTotalDeposit(hasPreOrder(bill.getMapt()));
            }

            float discount = khDao.getMemRateDiscount(bill.getMakh());
            double total = (tempTotal - deposit) * (1.0 - discount);

            billTempTotalLabel.setText(String.format("%.3f", tempTotal));
            billDepositLabel.setText("- " + String.format("%.3f", deposit));
            memDiscountLabel.setText(String.valueOf(discount * 100) + "%");
            billTotalLabel.setText(String.format("%.3f", total));
        }
    }//GEN-LAST:event_billListTableMouseClicked

    private void updateFoodBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateFoodBtnActionPerformed
        // TODO add your handling code here:
        int index = foodTable.getSelectedRow();
        if (index == -1) {
            updateFoodBtn.setEnabled(false);
        } else {
            DichVu item = dvList.get(index);
            item.setHethang(outOfStockYes.isSelected());
            if(dvDao.updateDichVu(item)){
                showFoodList(dvList);
                JOptionPane.showMessageDialog(rootPane, "Update food successfully");
                updateFoodBtn.setEnabled(false);
            }
            else JOptionPane.showMessageDialog(rootPane, "Update food failed");
        }
    }//GEN-LAST:event_updateFoodBtnActionPerformed

    private void changepwdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changepwdBtnActionPerformed
        // TODO add your handling code here:
        new ChangePasswordGUI(this, rootPaneCheckingEnabled, staff.getTaikhoan()).setVisible(true);
    }//GEN-LAST:event_changepwdBtnActionPerformed

    public void showInfo() {
        idTxt.setText(staff.getManv());
        fnameTxt.setText(staff.getHo());
        lnameTxt.setText(staff.getTen());
        phoneTxt.setText(staff.getSdt());
        cmndTxt.setText(staff.getCmnd());
        if (staff.isTrangthainghi()) {
            quitYesRadio.setSelected(true);
        } else {
            quitNoRadio.setSelected(true);
        }

        fnameTxt.setEnabled(false);
        lnameTxt.setEnabled(false);
        phoneTxt.setEnabled(false);
        cmndTxt.setEnabled(false);
        quitNoRadio.setEnabled(false);
        quitYesRadio.setEnabled(false);

        savebtn.setEnabled(false);
        cancelBtn.setEnabled(false);
    }

    public void resetInfolabels() {
        fnameWrong.setText(" ");
        lnameWrong.setText(" ");
        phoneWrong.setText(" ");
        cmndWrong.setText(" ");
    }
    
    public void resetCusTexts() {
        cusIdTxt.setText("");
        cusFnameTxt.setText("");
        cusLnameTxt.setText("");
        cusPhoneTxt.setText("");
        cusCmndTxt.setText("");
        cusUsernameTxt.setText("");
    }

    public void resetCusLabels() {
        cusIdWrong.setText(" ");
        cusFnameWrong.setText(" ");
        cusLnameWrong.setText(" ");
        cusPhoneWrong.setText(" ");
        cusCmndWrong.setText(" ");
        cusUsernameWrong.setText(" ");
    }
    
    public void showCusList(ArrayList<KhachHang> khList) {
        for (KhachHang kh : khList) {
            khTableModel.addRow(new Object[]{kh.getMakh(), kh.getHo(), kh.getTen(),
                kh.getSdt(), kh.getCmnd(), kh.getSolan_thuesan(), kh.getTaikhoan(), kh.getLoaiTk()});
        }
    }
    
    public void showFoodList(ArrayList<DichVu> dvList) {
        dvTableModel.setRowCount(0);
        String hetHang;
        for (DichVu dv : dvList) {
            hetHang = dv.isHethang() ? "Yes" : "No";
            dvTableModel.addRow(new Object[]{dv.getMadv(), dv.getTendv(),
                dv.getDvt(), dv.getDongia(), hetHang});
        }
    }
    
    public void showPreOrderList(ArrayList<PhieuDat> pdList) {
        for (PhieuDat pd : pdList) {
            preOrderListTableModel.addRow(new Object[]{pd.getMapd(), pd.getNgayDat()});
        }
    }

    public void showOrderList(ArrayList<PhieuThue> ptList){
        orderListTableModel.setRowCount(0);
        for(PhieuThue item : ptList){
            orderListTableModel.addRow(new Object[]{item.getMapt(), item.getNgayThue()});
        }
    }
    
    public void showAvaiStadiums(ArrayList<TrangThaiSan> stateList) {
        for (TrangThaiSan sta : stateList) {

            if (sta.getTenTrangThai().equals("Trống")) {
                avaiStaTableModel.addRow(new Object[]{sta.getTenSan(), sta.getTenLoaiSan(), sta.getPricePerHour()});
            }
        }
    }
    
    public boolean isInWorkTime(String currTime, ArrayList<HeSo> rateList) {
        for(HeSo item : rateList) {
            if(cal.isTimeBetween(item.getTg_bd(), currTime, item.getTg_kt())) {
                return true;
            }
        }
        return false;
    }
    
    public void showWorkTimeMessage(String currentTime, ArrayList<HeSo> rateList) {
        String msg = "OUT OF WORK TIME, COME BACK LATER :D";
        msg += "\n\nCURRENT TIME: " + currentTime;
        
        HeSo m = rateDao.getRate("kgsang"),
             a = rateDao.getRate("kgchieu"),
             e = rateDao.getRate("kgtoi");
        
        msg += ( "\n\nMorning: " + m.getTg_bd() + " --> " + m.getTg_kt()
                + "\nAfternoon: " + a.getTg_bd() + " --> " + a.getTg_kt()
                + "\nEvening: " + e.getTg_bd() + " --> " + e.getTg_kt() );
        
        JOptionPane.showMessageDialog(this, msg);
    }
    
    public void resetPreOrder() {
        preOrderWrong.setText(" ");
        preOrderPhoneWrong.setText(" ");
        dateTimeWrong1.setText(" ");
        preOrderNoteTxt.setText("");
        preOrderPhoneTxt.setText("");

        String autoPdID = new AutoID().getAutoPhieuDatID(pdList);
        preOrderIdLabel.setText(autoPdID);

        preOrderDateCreateLabel.setText(clock.getCurrentDateTime());

        tempPreOrderTableModel.setRowCount(0);
        avaiStaTableModel.setRowCount(0);
    }
    
    public void showPreOrderDetail(ArrayList<CT_PhieuDat> ctpdList) {
        preOrderDetailTableModel.setRowCount(0);
        for (CT_PhieuDat ctpdItem : ctpdList) {
            preOrderDetailTableModel.addRow(new Object[]{ctpdItem.getMasan(),
                cal.formatDateTime(ctpdItem.getTg_bd()), cal.formatDateTime(ctpdItem.getTg_kt()), ctpdItem.getDeposit()});
        }
    }
    
    public double countTotalDeposit(ArrayList<CT_PhieuDat> ctpdList) {
        double total = 0;
        for (CT_PhieuDat ctpd : ctpdList) {
            total += ctpd.getDeposit();
        }
        return total;
    }
    
    public boolean hasOrder(String mapd) {
        for(PhieuThue item : ptList) 
            if(item.getMapd() != null) // neu phieuthue do co mapd
                if(item.getMapd().equals(mapd)) // kiem tra mapd do co khop khong ?
                    return true;
        return false;
    }
    
    public String getMaKhungGio(ArrayList<HeSo> rateList) {
        String makg = "";
        
        String currentTime = clock.getCurrentTime();
        
        for(HeSo rate : rateList) {
            if(cal.isTimeBetween(rate.getTg_bd(), currentTime, rate.getTg_kt())) {
                makg = rate.getMaKhungGio();
            }
        }
        return makg;
    }
    
    public boolean checkExpiredPreOrder(String mapd) {
        ArrayList<CT_PhieuDat> tempList = ctpdDao.getPreOrderDetail(mapd);
        for(CT_PhieuDat item : tempList)
            return cal.isEndDateGtStartDate(cal.formatDateTime(item.getTg_bd()),
                    clock.getCurrentDateTime2());
        return false;
    }
    
    public void showOrderAvaiStadiums(ArrayList<TrangThaiSan> stateList) {
        for (TrangThaiSan sta : stateList) {

            if (sta.getTenTrangThai().equals("Trống")) {
                orderAvaiStaTableModel.addRow(new Object[]{sta.getTenSan(), sta.getTenLoaiSan(), sta.getPricePerHour()});
            }
        }
    }
    
    public void resetOrder() {
        
        orderNoteTxt.setText("");
        orderPhoneTxt.setText("");
        preOrderIdTxt.setText("");
        orderCusIDCbBox.setSelectedItem("khvanglai");
        orderPhoneWrong.setText(" ");
        preOrderIdWrong.setText(" ");
        orderWrong.setText(" ");
        
        removeOrderItemBtn.setEnabled(false);
        removeOrderServiceBtn.setEnabled(false);
        addDetailOrderBtn.setEnabled(false);
        
        String autoPtID = new AutoID().getAutoPhieuThueID(ptList);
        orderIdLabel.setText(autoPtID);
        orderDateCreateLabel.setText(clock.getCurrentDateTime());
        
        orderDetailTableModel.setRowCount(0);
        orderServicesTableModel.setRowCount(0);
    }
    
    public String getNameOfService(String itemID) {
        for(DichVu dv : dvList) {
            if(dv.getMadv().equals(itemID)) {
                return dv.getTendv();
            }
        }
        return "";
    }
    
    public boolean tonTaiPhieuDat(String preOrderID) {
        for(PhieuDat pd : pdList) {
            if(pd.getMapd().equals(preOrderID)) {
                return true;
            }
        }
        return false;
    }
    
    public void showOrderDetailList(ArrayList<CT_PhieuThue> ctptList){
        orderDetailTableModel.setRowCount(0);
        for(CT_PhieuThue ctpt : ctptList) {
            orderDetailTableModel.addRow(new Object[]{ctpt.getMasan(),
                ctpt.getGioden(), ctpt.getGio_dukientra()});
        }
    }
    
    public void checkExpiredOrder(ArrayList<CT_PhieuThue> ctptList) {
        for(int i = 0; i < ctptList.size(); i++) {
            CT_PhieuThue ctpt = ctptList.get(i);
            if(ctpt.getGiotra().equals("")) {
                viewServicesBtn2.setEnabled(true);
                orderServicesTable2.setEnabled(true);
                break;
            } else {
                viewServicesBtn2.setEnabled(false);
                orderServicesTable2.setEnabled(false);
            }
        }
    }
    
    public void showOrderDetail(ArrayList<CT_PhieuThue> ctptList){
        orderDetailTableModel2.setRowCount(0);
        String gioTra;
        for(CT_PhieuThue item : ctptList){
            
            if(item.getGiotra() == null || item.getGiotra().equals(""))
                gioTra = "Chưa có";
            else gioTra = item.getGiotra();
            
            orderDetailTableModel2.addRow(new Object[]{item.getMasan(), item.getGioden(), item.getGio_dukientra()
                , gioTra});
        }
    }
    
    public double countCurrentTotal(ArrayList<CT_PhieuThue> ctptList, ArrayList<CT_DichVu> ctdvList) {
        double currentTotal = 0;
        
        float tiLe, itemTotalTime;
        double pricePerHour, servicePrice;
        
        for(CT_PhieuThue item : ctptList) {
            
            itemTotalTime = cal.totalTime(item.getGioden(), item.getGio_dukientra());
            tiLe = getRate(item.getMakhunggio());
            pricePerHour = staDao.getPricePerHour(item.getMasan());
            
            currentTotal += (itemTotalTime * pricePerHour * tiLe);
        }
        
        for(CT_DichVu item : ctdvList) {
            
            servicePrice = getServicePrice(item.getMadv());
            
            currentTotal += (servicePrice * item.getSoluong());
        }
        
        return  currentTotal;
    }
    
    public void showOrderServices(ArrayList<CT_DichVu> ctdvList) {
        orderServicesTableModel2.setRowCount(0);
        String itemName;
        for(CT_DichVu item : ctdvList) {
            itemName = getNameOfService(item.getMadv());
            orderServicesTableModel2.addRow(new Object[]{itemName, item.getSoluong()});
        }
    }
    
    public boolean isAllOrderDetailExpired(ArrayList<CT_PhieuThue> ctptList) {
        for(CT_PhieuThue item : ctptList)
            if(item.getGiotra().equals("")) return false;
        return true;
    }
    
    public Bill autoCreateBill(String mahd, String mapt, String manv, String makh) {
        ArrayList<CT_PhieuThue> ctptList = ctptDao.getOrderDetail(mapt);
        ArrayList<CT_DichVu> ctdvList = ctdvDao.getOrderServices(mapt);
        
        double totalBill = getTotalBillWithoutMemDiscount(ctptList, ctdvList);
        
        float memberDiscount = khDao.getMemRateDiscount(makh);
        totalBill = totalBill * (1.0 - memberDiscount);
        
        Bill bill = new Bill(mahd, clock.getCurrentDateTime2(), totalBill, mapt, manv, makh);
        return bill;
    }
    
    public void showBillList(ArrayList<Bill> billList) {
        billListTableModel.setRowCount(0);
        for(Bill item : billList) {
            billListTableModel.addRow(new Object[]{item.getMahd(), item.getNgaylap()});
        }
    }
    
    public float getRate(String makhunggio) {
        for(HeSo hs : rateList) {
            if(hs.getMaKhungGio().equals(makhunggio)){
                return hs.getHeSo();
            }
        }
        return -1;
    }
    
    public double getTotalBillWithoutMemDiscount(ArrayList<CT_PhieuThue> ctptList, ArrayList<CT_DichVu> ctdvList) {
        double totalBill = 0;
        float heso;
        
        for(CT_PhieuThue item : ctptList) {
            
            heso = getRate(item.getMakhunggio());
            totalBill += ( item.getThanhtien() * heso );
        }
        
        for(CT_DichVu item : ctdvList)
           totalBill += ( getServicePrice(item.getMadv()) * item.getSoluong());
        
        return  totalBill;
    }
    
    public double getServicePrice(String madv){
        for(DichVu item : dvList) {
            if(item.getMadv().equals(madv)) {
                return item.getDongia();
            }
        }
        return -1;
    }
    
    public void addDetailPreOrder(CT_PhieuDat ctpdItem, int index) {
        ctpdList.add(ctpdItem);
        tempPreOrderTableModel.addRow(new Object[]{ctpdItem.getMasan(),
            ctpdItem.getTg_bd(), ctpdItem.getTg_kt(), ctpdItem.getDeposit()});

        avaiStaList.remove(index);
        avaiStaTableModel.removeRow(index);
    }
    
    public void addServiceOrder(String mapt, DichVu dvItem, int qty, int flag){
        
        int pos = serviceExisted(dvItem, flag);
        
        if (flag == 1) {
            if( pos > -1){ // da ton tai
                CT_DichVu item = ctdvList.get(pos);
                item.setSoluong(item.getSoluong() + qty);
                ctdvList.set(pos, item);

                orderServicesTableModel.setValueAt(item.getSoluong(), pos, 1);
            } else {
                CT_DichVu item = new CT_DichVu(mapt, dvItem.getMadv(), qty);
                ctdvList.add(item);
                orderServicesTableModel.addRow(new Object[]{dvItem.getTendv(), qty});
            }
        } else if (flag == 2) {
            if( pos > -1){ // da ton tai
                CT_DichVu item = ctdvList2.get(pos);
                item.setSoluong(item.getSoluong() + qty);
                ctdvList2.set(pos, item);
                ctdvDao.updateServiceDetail(ctdvList2.get(pos));
                orderServicesTableModel2.setValueAt(item.getSoluong(), pos, 1);
                
                // update current total
                double currentTotal = countCurrentTotal(ctptList2, ctdvList2);     
                currentTotalOrderLabel.setText(String.format("%.3f", currentTotal));
            } else {
                CT_DichVu item = new CT_DichVu(mapt, dvItem.getMadv(), qty);
                ctdvList2.add(item);
                ctdvDao.addServiceDetailItem(item);
                orderServicesTableModel2.addRow(new Object[]{dvItem.getTendv(), qty});
                
                // update current total
                double currentTotal = countCurrentTotal(ctptList2, ctdvList2);     
                currentTotalOrderLabel.setText(String.format("%.3f", currentTotal));
            }
        }  
    }
    
    public int serviceExisted(DichVu dvItem, int flag){
        
        // tra ve vi tri cua phan tu da ton tai trong chi tiet dich vu
        
        if(flag == 1) {
            for(int i = 0; i < ctdvList.size(); i++) {
                if(ctdvList.get(i).getMadv().equals(dvItem.getMadv())){
                    return i;
                }
            }
        } else if(flag == 2){
            for(int i = 0; i < ctdvList2.size(); i++) {
                if(ctdvList2.get(i).getMadv().equals(dvItem.getMadv())){
                    return i;
                }
            }
        }
        
        return -1; // khong ton tai
    }
    
    public void addDetailOrder(CT_PhieuThue ctptItem, int index) {
        ctptList.add(ctptItem);
        
        tempOrderTableModel.addRow(new Object[]{ctptItem.getMasan(),
            ctptItem.getGioden(), ctptItem.getGio_dukientra()});

        orderAvaiStaTableModel.removeRow(index);
        avaiStaList.remove(index);
    }
    
    public void showBillStadiumDetail(ArrayList<CT_PhieuThue> ctptList) {
        String staName;
        float totalTime, heso;
        
        billStaDetailTableModel.setRowCount(0);
        for(CT_PhieuThue item : ctptList) {
            staName = getNameOfStadium(item.getMasan());
            totalTime = cal.totalTime(item.getGioden(), item.getGio_dukientra());
            heso = getRate(item.getMakhunggio());
            
            billStaDetailTableModel.addRow(new Object[]{staName, item.getGioden(),
                item.getGio_dukientra(), totalTime, heso, item.getThanhtien() * heso});
        }
    }
    
    public void showBillServiceDetail(ArrayList<CT_DichVu> ctdvList) {
        billServiceDetailTableModel.setRowCount(0);
        String serviceName;
        double price;
        for(CT_DichVu item : ctdvList) {
            serviceName = getNameOfService(item.getMadv());
            price = getServicePrice(item.getMadv());
            billServiceDetailTableModel.addRow(new Object[]{serviceName, price,
                item.getSoluong(), item.getSoluong() * price});
        }
    }
    
    public String getNameOfStadium(String masan) {
        for(SanBong item : staList) {
            if(item.getMaSan().equals(masan))
                return item.getTenSan();
        }
        return "";
    }
    
    public String hasPreOrder(String mapt) {
        for(PhieuThue item : ptList)
            if(item.getMapt().equals(mapt)) // kiem tra phieu thue nao khop
                if(item.getMapd() != null) // kiem tra phieu thue do co phieu dat khong ?
                    return item.getMapd();
        return null;
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaffGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffGUI("hung", "nhanvien").setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCusBtn;
    private javax.swing.JButton addDetailOrderBtn;
    private javax.swing.JButton addDetailPreOrderBtn;
    private javax.swing.JTable avaiStaTable;
    private javax.swing.JLabel billCusIDLabel;
    private javax.swing.JLabel billDateCreateLabel;
    private javax.swing.JLabel billDepositLabel;
    private javax.swing.JLabel billIDLabel;
    private javax.swing.JTable billListTable;
    private javax.swing.JLabel billMemshipLabel;
    private javax.swing.JLabel billOrderIDLabel;
    private javax.swing.JPanel billPanel;
    private javax.swing.JTable billServiceDetailTable;
    private javax.swing.JTable billStaDetailTable;
    private javax.swing.JLabel billStaffIDLabel;
    private javax.swing.JLabel billTempTotalLabel;
    private javax.swing.JLabel billTotalLabel;
    private javax.swing.JButton bookingBtn;
    private javax.swing.JPanel bookingPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton cancelCusBtn;
    private javax.swing.JButton changepwdBtn;
    private javax.swing.JButton checkAvaiStaBtn;
    private javax.swing.JButton checkAvaiStaBtn1;
    private javax.swing.JButton checkPreOrderIdBtn;
    private javax.swing.JTextField cmndTxt;
    private javax.swing.JLabel cmndWrong;
    private javax.swing.JButton createOrderBtn;
    private javax.swing.JButton createPreOrderBtn;
    private javax.swing.JLabel currentTotalOrderLabel;
    private javax.swing.JTextField cusCmndTxt;
    private javax.swing.JLabel cusCmndWrong;
    private javax.swing.JTextField cusFnameTxt;
    private javax.swing.JLabel cusFnameWrong;
    private javax.swing.JTextField cusIdTxt;
    private javax.swing.JLabel cusIdWrong;
    private javax.swing.JTextField cusLnameTxt;
    private javax.swing.JLabel cusLnameWrong;
    private javax.swing.JTextField cusPhoneTxt;
    private javax.swing.JLabel cusPhoneWrong;
    private javax.swing.JTextField cusUsernameTxt;
    private javax.swing.JLabel cusUsernameWrong;
    private javax.swing.JButton customerBtn;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dateTimeWrong1;
    private javax.swing.JLabel dateTimeWrong2;
    private javax.swing.JSpinner endHour1;
    private javax.swing.JSpinner endHour2;
    private javax.swing.JSpinner endMinute1;
    private javax.swing.JSpinner endMinute2;
    private javax.swing.JButton endStadiumBtn;
    private javax.swing.JTextField fnameTxt;
    private javax.swing.JLabel fnameWrong;
    private javax.swing.JLabel foodImageLabel;
    private javax.swing.JTable foodTable;
    private javax.swing.JTextField idTxt;
    private javax.swing.JButton infoBtn;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable khTable;
    private javax.swing.JTextField lnameTxt;
    private javax.swing.JLabel lnameWrong;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel memDiscountLabel;
    private javax.swing.JTable orderAvaiStaTable;
    private javax.swing.JComboBox<String> orderCusIDCbBox;
    private javax.swing.JLabel orderCusIdLabel2;
    private javax.swing.JLabel orderDateCreate2;
    private javax.swing.JLabel orderDateCreateLabel;
    private javax.swing.JTable orderDetailTable;
    private javax.swing.JTable orderDetailTable2;
    private javax.swing.JLabel orderIdLabel;
    private javax.swing.JLabel orderIdLabel2;
    private javax.swing.JTable orderListTable;
    private javax.swing.JTextField orderNoteTxt;
    private javax.swing.JTextField orderNoteTxt2;
    private javax.swing.JTextField orderPhoneTxt;
    private javax.swing.JTextField orderPhoneTxt2;
    private javax.swing.JLabel orderPhoneWrong;
    private javax.swing.JTable orderServicesTable;
    private javax.swing.JTable orderServicesTable2;
    private javax.swing.JLabel orderStaffIdLabel;
    private javax.swing.JLabel orderStaffIdLabel2;
    private javax.swing.JLabel orderWrong;
    private javax.swing.ButtonGroup outOfStockGrpBtn;
    private javax.swing.JRadioButton outOfStockNo;
    private javax.swing.JRadioButton outOfStockYes;
    private javax.swing.JButton payBtn;
    private javax.swing.JTextField phoneTxt;
    private javax.swing.JLabel phoneWrong;
    private javax.swing.JComboBox<String> preOrderCusIDCbBox;
    private javax.swing.JLabel preOrderCusIdLabel2;
    private javax.swing.JLabel preOrderDateCreate2;
    private javax.swing.JLabel preOrderDateCreateLabel;
    private javax.swing.JTable preOrderDetailTable;
    private javax.swing.JTable preOrderDetailTable2;
    private javax.swing.JLabel preOrderExpiredLabel;
    private javax.swing.JLabel preOrderIdLabel;
    private javax.swing.JTextField preOrderIdTxt;
    private javax.swing.JLabel preOrderIdWrong;
    private javax.swing.JTable preOrderListTable;
    private javax.swing.JTextField preOrderNoteTxt;
    private javax.swing.JTextField preOrderNoteTxt2;
    private javax.swing.JTextField preOrderPhoneTxt;
    private javax.swing.JTextField preOrderPhoneTxt2;
    private javax.swing.JLabel preOrderPhoneWrong;
    private javax.swing.JLabel preOrderStaffIdLabel;
    private javax.swing.JLabel preOrderStaffIdLabel2;
    private javax.swing.JLabel preOrderWrong;
    private javax.swing.JRadioButton quitNoRadio;
    private javax.swing.JRadioButton quitYesRadio;
    private javax.swing.JButton refreshOrderServiceTableBtn;
    private javax.swing.JButton refreshOrderServiceTableBtn2;
    private javax.swing.JButton removeOrderItemBtn;
    private javax.swing.JButton removeOrderServiceBtn;
    private javax.swing.JButton removeOrderServiceBtn2;
    private javax.swing.JButton removePreOderItemBtn;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JButton savebtn;
    private javax.swing.JButton settingBtn;
    private javax.swing.JComboBox<String> sortCusCbBox;
    private javax.swing.JComboBox<String> startDayCbBox1;
    private javax.swing.JComboBox<String> startDayCbBox2;
    private javax.swing.JSpinner startHour1;
    private javax.swing.JSpinner startHour2;
    private javax.swing.JSpinner startMinute1;
    private javax.swing.JSpinner startMinute2;
    private javax.swing.JComboBox<String> startMonthCbBox1;
    private javax.swing.JComboBox<String> startMonthCbBox2;
    private com.toedter.calendar.JYearChooser startYearChooser1;
    private com.toedter.calendar.JYearChooser startYearChooser2;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel totalDepositLabel;
    private javax.swing.JButton updateCusBtn;
    private javax.swing.JButton updateFoodBtn;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JButton viewFoodBtn;
    private javax.swing.JPanel viewFoodPanel;
    private javax.swing.JButton viewServicesBtn;
    private javax.swing.JButton viewServicesBtn2;
    // End of variables declaration//GEN-END:variables
}
