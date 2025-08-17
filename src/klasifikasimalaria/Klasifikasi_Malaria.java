/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasifikasimalaria;
import java.awt.*;
//import java.awt.Color;
//import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rohman
 */
public class Klasifikasi_Malaria extends javax.swing.JFrame {

    /**
     * Creates new form Klasifikasi_Malaria
     */
    private DefaultTableModel TrainingTable;
    private DefaultTableModel TestingTable;
    private DefaultTableModel tabelHasil;
    private DefaultTableModel alfaBaru;
    public LevelTraining[] panel_data; 

    private double persenTraining = 0;
    private double persenTesting = 0;
    
    private double DataTesting[][];
    private double DataTraining[][];
    private double kernelSVM[][];
    private double matriksHessian[][];
    private double Ei[][];
    public double alfa[];
    private double deltaAlfa[];
    private double KxplusKxmin[][];
    private double bobotW[][]; 
    public double Bias; 
    private double gamma=0.;
    public double maxDeltaAlfa;
    
    public Klasifikasi_Malaria() {
        initComponents();
        judulApps.setText("Klasifikasi Penyakit Malaria Menggunakan Metode Support Vector Machine");
        nama.setText("TRYSE REZZA BIANTONG");
        NIM.setText("145150201111088");
        
        tampilkantabel.setVisible(false);  
        mulaiKlasifikasi.setVisible(false);    
        prosesSVM.setVisible(false);  
        progresHitung.setVisible(false);
        
        String[] Rasio_data = {"90% Data Latih : 10% Data Uji", "80% Data Latih : 20% Data Uji", "70% Data Latih : 30% Data Uji", "60% Data Latih : 40% Data Uji", "50% Data Latih : 50% Data Uji", "40% Data Latih : 60% Data Uji", "30% Data Latih : 70% Data Uji", "20% Data Latih : 80% Data Uji", "10% Data Latih : 90% Data Uji"};
        Rasiodata.removeAllItems();
        for(int i=0; i<Rasio_data.length; i++) Rasiodata.addItem(""+Rasio_data[i]);
        String[] dataKernel = { "Linier", "Polynomial", "Radial Basic Function" };
        jenisKernel.removeAllItems();
        for(int i=0; i<dataKernel.length; i++) jenisKernel.addItem(""+dataKernel[i]);
        TrainingTable = new DefaultTableModel();
        TestingTable = new DefaultTableModel();
        tabelHasil = new DefaultTableModel();
        alfaBaru = new DefaultTableModel();

        tabTraining.setModel(TrainingTable);
        tabTesting.setModel(TestingTable);
        tabelTesting.setModel(tabelHasil);
        tabelAlfaBaru.setModel(alfaBaru);
        //tabelKlasifikasi.getColumnModel().getColumn(0).getCellRenderer(new DefaultTableCellRenderer (){)
        
        TrainingTable.addColumn("no_pasien");
        for(int i=1; i<=32; i++) TrainingTable.addColumn("F"+i); 
        TrainingTable.addColumn("Nama_penyakit");
        
        TestingTable.addColumn("no_pasien");
        for(int i=1; i<=32; i++) TestingTable.addColumn("F"+i); 
        TestingTable.addColumn("Nama_penyakit");
         
        tabelHasil.addColumn("No Pasien");
        tabelHasil.addColumn("Nilai F(x)");
        tabelHasil.addColumn("Sign F(x) Level1");
        tabelHasil.addColumn("Sign f(x) Level2");
        tabelHasil.addColumn("Sign f(x) Level3");
        tabelHasil.addColumn("Kelas Sistem");
        tabelHasil.addColumn("Kelas Penyakit");
         
        alfaBaru.addColumn("Alfa");
        alfaBaru.addColumn("Nilai");
        
        panel_data = new LevelTraining[3];
        panel_data[0] = new LevelTraining();
        //panelLevel1.add(panel_data[0]);
        for(int i=2; i<=3; i++){
            panel_data[i-1] = new LevelTraining(); 
            panel_data[i-1].setLevel(i);
            panel_data[i-1].setMainPage(this);
            tabUtama.add(panel_data[i-1],"LEVEL "+i, i+1); 
        }
        
       
        
        inputLambda.setText("");
        inputGamma.setText("");
        inputSigma.setText("");  
        inputComplexity.setText("");
        inputIterasi.setText("");
        inputEpsilon.setText("");
        numOnly(inputIterasi);
        numOnly(inputComplexity);
        numOnly(inputLambda);
        numOnly(inputEpsilon);
        numOnly(inputGamma);
        numOnly(inputSigma);
    }
    
    private void numOnly(Object objSource){
        ((Component) objSource).addKeyListener(new KeyListener(){ 
            @Override
            public void keyTyped(KeyEvent e) {
                String filterStr = "0123456789.";
                char c = (char)e.getKeyChar();
                if(filterStr.indexOf(c)<0){
                    e.consume();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) { 
                 System.out.println("Release : "+e.getKeyChar());
            }
            @Override
            public void keyPressed(KeyEvent e) { 
                 System.out.println("Pressed : "+e.getKeyChar());
            } 
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabUtama = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        judulApps = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        NIM = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Rasiodata = new javax.swing.JComboBox();
        tampilkantabel = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabTraining = new javax.swing.JTable();
        jlhdatalatih = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabTesting = new javax.swing.JTable();
        jlhdatauji = new javax.swing.JLabel();
        mulaiKlasifikasi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        inputIterasi = new javax.swing.JTextField();
        inputComplexity = new javax.swing.JTextField();
        inputSigma = new javax.swing.JTextField();
        inputLambda = new javax.swing.JTextField();
        inputGamma = new javax.swing.JTextField();
        prosesSVM = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jenisKernel = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        inputEpsilon = new javax.swing.JTextField();
        panelLevel1 = new javax.swing.JPanel();
        nilaiB = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelAlfaBaru = new javax.swing.JTable();
        progresHitung = new javax.swing.JProgressBar();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelTesting = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        labelSesuai = new javax.swing.JLabel();
        labelAkurasi = new javax.swing.JLabel();
        jlhDataSesuai = new javax.swing.JLabel();
        tingkatAkurasi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabUtama.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ABOUT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        judulApps.setFont(new java.awt.Font("Engravers MT", 1, 18)); // NOI18N
        judulApps.setText("Judul");

        nama.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        nama.setText("Nama");

        NIM.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        NIM.setText("NIM");

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));

        jLabel8.setIcon(new javax.swing.ImageIcon("E:\\Echa File PTIIK\\semester 8\\SKRIPSI goo\\filkom.png")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(403, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(356, 356, 356))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(NIM, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(429, 429, 429))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(judulApps, javax.swing.GroupLayout.PREFERRED_SIZE, 1147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(judulApps, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(nama)
                .addGap(52, 52, 52)
                .addComponent(NIM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabUtama.addTab("About", jPanel1);
        jPanel1.getAccessibleContext().setAccessibleName(":::");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pilih Data"));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Rasio Data : ");

        Rasiodata.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Rasiodata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Rasiodata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RasiodataActionPerformed(evt);
            }
        });

        tampilkantabel.setText("Tampilkan Data dan Simpan");
        tampilkantabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tampilkantabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Rasiodata, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(tampilkantabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Rasiodata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tampilkantabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("TABEL DATA"));

        tabTraining.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabTraining);

        jlhdatalatih.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlhdatalatih.setText("Total Data Latih :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(473, 473, 473)
                .addComponent(jlhdatalatih, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlhdatalatih, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Data Training", jPanel4);

        tabTesting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabTesting);

        jlhdatauji.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlhdatauji.setText("Total Data Testing :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(474, 474, 474)
                .addComponent(jlhdatauji, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlhdatauji, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Data Testing", jPanel5);

        mulaiKlasifikasi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        mulaiKlasifikasi.setText("Perhitungan");
        mulaiKlasifikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mulaiKlasifikasiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(mulaiKlasifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mulaiKlasifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        tabUtama.addTab("EDIT DATA", jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Input Data")), "Input Data"));
        jPanel7.setForeground(new java.awt.Color(204, 204, 204));

        inputIterasi.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputIterasi.setText("0");
        inputIterasi.setToolTipText("");
        inputIterasi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputIterasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputIterasiActionPerformed(evt);
            }
        });

        inputComplexity.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputComplexity.setText("0");
        inputComplexity.setToolTipText("");
        inputComplexity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputComplexityActionPerformed(evt);
            }
        });

        inputSigma.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputSigma.setText("0");
        inputSigma.setToolTipText("");

        inputLambda.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputLambda.setText("0");
        inputLambda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputLambdaActionPerformed(evt);
            }
        });

        inputGamma.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputGamma.setText("0");
        inputGamma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputGammaActionPerformed(evt);
            }
        });

        prosesSVM.setText("Proses");
        prosesSVM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prosesSVMActionPerformed(evt);
            }
        });

        jLabel2.setText("Pilih Kernel :");

        jenisKernel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jenisKernel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisKernelActionPerformed(evt);
            }
        });

        jLabel3.setText("Iterasi  :  ");

        jLabel4.setText("Complexity :");

        jLabel5.setText("Lamda   : ");

        jLabel6.setText("Gamma :");

        jLabel7.setText("Sigma   : ");

        jLabel10.setText("Epsilon");

        inputEpsilon.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inputEpsilon.setText("0");
        inputEpsilon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputEpsilonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel5))
                                    .addGap(13, 13, 13))
                                .addComponent(jLabel4))
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inputLambda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(inputComplexity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jenisKernel, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(inputIterasi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(88, Short.MAX_VALUE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(inputSigma, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(prosesSVM, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(inputEpsilon)
                                        .addComponent(inputGamma, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jenisKernel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputIterasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputLambda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inputComplexity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(inputSigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(inputGamma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(inputEpsilon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prosesSVM, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        nilaiB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nilaiB.setText("Nilai B =");

        javax.swing.GroupLayout panelLevel1Layout = new javax.swing.GroupLayout(panelLevel1);
        panelLevel1.setLayout(panelLevel1Layout);
        panelLevel1Layout.setHorizontalGroup(
            panelLevel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLevel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nilaiB, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        panelLevel1Layout.setVerticalGroup(
            panelLevel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLevel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nilaiB)
                .addContainerGap())
        );

        tabelAlfaBaru.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tabelAlfaBaru);

        progresHitung.setBackground(new java.awt.Color(153, 255, 204));
        progresHitung.setForeground(new java.awt.Color(0, 255, 102));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(progresHitung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(progresHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(panelLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        jPanel7.getAccessibleContext().setAccessibleName("INput Data");

        tabUtama.addTab("LEVEL 1", jPanel6);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Hasil Klasifikasi"));

        tabelTesting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tabelTesting);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Hasil Analisa"));

        labelSesuai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelSesuai.setText("DATA SESUAI : ");

        labelAkurasi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelAkurasi.setText("AKURASI :");

        jlhDataSesuai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlhDataSesuai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlhDataSesuai.setText("0");

        tingkatAkurasi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tingkatAkurasi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tingkatAkurasi.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSesuai)
                    .addComponent(labelAkurasi))
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlhDataSesuai, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(tingkatAkurasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelSesuai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlhDataSesuai, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAkurasi)
                    .addComponent(tingkatAkurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(363, Short.MAX_VALUE))
        );

        tabUtama.addTab("KLASIFIKASI", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabUtama)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabUtama, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tabUtama.getAccessibleContext().setAccessibleName("About");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mulaiKlasifikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mulaiKlasifikasiActionPerformed
        // TODO add your handling code here: 
        tabUtama.setSelectedIndex(2);
    }//GEN-LAST:event_mulaiKlasifikasiActionPerformed

    private void tampilkantabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tampilkantabelActionPerformed
        // TODO add your handling code here:

        String[] nama_tabel = {"malaria_tertiana","malaria_kuartana","malaria_tropika","malaria_ovale"};
        int limit[] = new int[4];

        TrainingTable.getDataVector().removeAllElements();
        TrainingTable.fireTableDataChanged();

        TestingTable.getDataVector().removeAllElements();
        TestingTable.fireTableDataChanged();

        try {
            Connection connect = koneksidata.getKoneksi();
            Statement stat, stat2, stat3,stat4,stat5, stat12,stat21, stat30, statA;
            stat = connect.createStatement();
            stat2 = connect.createStatement();

            String sql = "delete from nilai_bias";
            String sql_2 = "insert into nilai_bias values(1,0),(2,0),(3,0)";

            stat.executeUpdate(sql);
            stat2.executeUpdate(sql_2);

            stat3 = connect.createStatement();
            stat4= connect.createStatement();
            stat5= connect.createStatement();
            stat12 = connect.createStatement();
            stat21 = connect.createStatement();
            stat30 = connect.createStatement();
            statA = connect.createStatement();

            String sql30 = "delete from indexuji";
            stat30.executeUpdate(sql30);
            String sqljum = 
              "SELECT (select count(*) from malaria_tertiana) tertiana, "
            + " (select count(*) from malaria_kuartana) kuartana, "
            + " (select count(*) from malaria_tropika) tropika, "
            + " (select count(*) from malaria_ovale) ovale "
            ;
            ResultSet Res3 = stat3.executeQuery(sqljum);
            while (Res3.next()) {
                for(int i=0; i<=3; i++) limit[i] = (int) Math.round(persenTraining * Integer.parseInt(Res3.getString(i+1)));
            }

            System.out.println(persenTraining + "_persen data Training");
            System.out.println(persenTesting + "_persen data Testing");

            for (int i = 0; i < limit.length; i++) {
                System.out.println("size " + i + " : " + limit[i]);
            }
            //pilih random data latih

          //  System.out.println("sukses");
            
            String sql_uji = "", sql_latih="", sql_index_uji="";
            for(int i=0; i<nama_tabel.length; i++){
                String keterangan = nama_tabel[i].replace("malaria", "");
                sql_uji += (sql_uji.equals("")?"":" UNION ")+" SELECT *,'"+keterangan+"' jenisPenyakit FROM (select * from "+nama_tabel[i]+" order by rand() limit "+limit[i]+") a"+(i+1)+" ";
                            sql_latih += (sql_latih.equals("")?"":" UNION ")+" SELECT *,'"+keterangan+"' jenisPenyakit FROM "+nama_tabel[i]+" where ID_pasien NOT IN (select id_pasien from indexuji)";
            }
            sql_uji+=" ORDER BY id_pasien ";
            sql_latih+=" ORDER BY id_pasien ";
            
           // System.out.println(sql_latih);
           // System.out.println(sql_uji);
            
//System.out.println("sql_uji   : " + sql_uji);
            //System.out.println("sql_latih   : " + sql_latih);

            ResultSet Res12 = stat12.executeQuery(sql_uji);
            while (Res12.next()) {
                Object[] Object = new Object[34];
                Object[0] = Res12.getString("ID_pasien");
                Object[1] = Res12.getString("F1");
                Object[2] = Res12.getString("F2");
                Object[3] = Res12.getString("F3");
                Object[4] = Res12.getString("F4");
                Object[5] = Res12.getString("F5");
                Object[6] = Res12.getString("F6");
                Object[7] = Res12.getString("F7");
                Object[8] = Res12.getString("F8");
                Object[9] = Res12.getString("F9");
                Object[10] =Res12.getString("F10");
                Object[11] =Res12.getString("F11");
                Object[12] =Res12.getString("F12");
                Object[13] =Res12.getString("F13");
                Object[14] =Res12.getString("F14");
                Object[15] =Res12.getString("F15");
                Object[16] =Res12.getString("F16");
                Object[17] =Res12.getString("F17");
                Object[18] =Res12.getString("F18");
                Object[19] =Res12.getString("F19");
                Object[20] =Res12.getString("F20");
                Object[21] =Res12.getString("F21");
                Object[22] =Res12.getString("F22");
                Object[23] =Res12.getString("F23");
                Object[24] =Res12.getString("F24");
                Object[25] =Res12.getString("F25");
                Object[26] =Res12.getString("F26");
                Object[27] =Res12.getString("F27");
                Object[28] =Res12.getString("F28");
                Object[29] =Res12.getString("F29");
                Object[30] =Res12.getString("F30");
                Object[31] =Res12.getString("F31");
                Object[32] =Res12.getString("F32");
                Object[33] =Res12.getString("jenisPenyakit"); // "Tertiana"
                TrainingTable.addRow(Object);

                String uji = String.valueOf(Object[0]);

                String sql21 = "insert into indexuji values ('" + uji + "');";
                sql_index_uji += (sql_index_uji.equals("")?" insert into indexuji values ":",")+" ('" + uji + "')";
                //stat21.executeUpdate(sql21);
            }
            stat21.executeUpdate(sql_index_uji);
            String sqlA = "select * from malaria_tertiana where ID_Pasien NOT IN(select ID_Pasien from indexuji)";
            ResultSet ResA = statA.executeQuery(sql_latih);

            
            while (ResA.next()) {
                Object[] Object = new Object[34];
                Object[0] = ResA.getString("ID_pasien");
                Object[1] = ResA.getString("F1");
                Object[2] = ResA.getString("F2");
                Object[3] = ResA.getString("F3");
                Object[4] = ResA.getString("F4");
                Object[5] = ResA.getString("F5");
                Object[6] = ResA.getString("F6");
                Object[7] = ResA.getString("F7");
                Object[8] = ResA.getString("F8");
                Object[9] = ResA.getString("F9");
                Object[10] =ResA.getString("F10");
                Object[11] =ResA.getString("F11");
                Object[12] =ResA.getString("F12");
                Object[13] =ResA.getString("F13");
                Object[14] =ResA.getString("F14");
                Object[15] =ResA.getString("F15");
                Object[16] =ResA.getString("F16");
                Object[17] =ResA.getString("F17");
                Object[18] =ResA.getString("F18");
                Object[19] =ResA.getString("F19");
                Object[20] =ResA.getString("F20");
                Object[21] =ResA.getString("F21");
                Object[22] =ResA.getString("F22");
                Object[23] =ResA.getString("F23");
                Object[24] =ResA.getString("F24");
                Object[25] =ResA.getString("F25");
                Object[26] =ResA.getString("F26");
                Object[27] =ResA.getString("F27");
                Object[28] =ResA.getString("F28");
                Object[29] =ResA.getString("F29");
                Object[30] =ResA.getString("F30");
                Object[31] =ResA.getString("F31");
                Object[32] =ResA.getString("F32");
                Object[33] =ResA.getString("jenisPenyakit"); // "Tertiana";
                TestingTable.addRow(Object);
            }
            jlhdatalatih.setText("Total Data Latih : " + TrainingTable.getRowCount());
            jlhdatalatih.setVisible(true);

            jlhdatauji.setText("Total Data Uji : " + TestingTable.getRowCount());
            jlhdatauji.setVisible(true);

            //simpan data
            String sql_4 = "delete from trainingrandom";
            String sql_3 = "delete from testingrandom";
            stat.executeUpdate(sql_4);
            stat3.executeUpdate(sql_3);

            String sql_datalatih="", sql_datauji="";
            for (int i = 0; i < TrainingTable.getRowCount(); i++) {
                Object no_pasien = TrainingTable.getValueAt(i, 0);
                Object F1 = TrainingTable.getValueAt(i, 1);
                Object F2 = TrainingTable.getValueAt(i, 2);
                Object F3 = TrainingTable.getValueAt(i, 3);
                Object F4 = TrainingTable.getValueAt(i, 4);
                Object F5 = TrainingTable.getValueAt(i, 5);
                Object F6 = TrainingTable.getValueAt(i, 6);
                Object F7 = TrainingTable.getValueAt(i, 7);
                Object F8 = TrainingTable.getValueAt(i, 8);
                Object F9 = TrainingTable.getValueAt(i, 9);
                Object F10 = TrainingTable.getValueAt(i, 10);
                Object F11 = TrainingTable.getValueAt(i, 11);
                Object F12 = TrainingTable.getValueAt(i, 12);
                Object F13 = TrainingTable.getValueAt(i, 13);
                Object F14 = TrainingTable.getValueAt(i, 14);
                Object F15 = TrainingTable.getValueAt(i, 15);
                Object F16 = TrainingTable.getValueAt(i, 16);
                Object F17 = TrainingTable.getValueAt(i, 17);
                Object F18 = TrainingTable.getValueAt(i, 18);
                Object F19 = TrainingTable.getValueAt(i, 19);
                Object F20 = TrainingTable.getValueAt(i, 20);
                Object F21 = TrainingTable.getValueAt(i, 21);
                Object F22 = TrainingTable.getValueAt(i, 22);
                Object F23 = TrainingTable.getValueAt(i, 23);
                Object F24 = TrainingTable.getValueAt(i, 24);
                Object F25 = TrainingTable.getValueAt(i, 25);
                Object F26 = TrainingTable.getValueAt(i, 26);
                Object F27 = TrainingTable.getValueAt(i, 27);
                Object F28 = TrainingTable.getValueAt(i, 28);
                Object F29 = TrainingTable.getValueAt(i, 29);
                Object F30 = TrainingTable.getValueAt(i, 30);
                Object F31 = TrainingTable.getValueAt(i, 31);
                Object F32 = TrainingTable.getValueAt(i, 32);
                Object namaPenyakit = TrainingTable.getValueAt(i, 33);

                String sql2 = " ('" + no_pasien + "','" + F1 + "','" + F2 + "','" + F3 + "','" + F4 + "','" + F5 + "','" + F6 + "','" + F7 + "','" + F8 + "',"
                + "'" + F9 + "','" + F10 + "','" + F11 + "','" + F12 + "','" + F13 + "','" + F14 + "','" + F15 + "','" + F16 + "','" + F17 + "','" + F18 + "','" + F19 + "','" + F20 + "',"
                + "'" + F21 + "','" + F22 + "','" + F23 + "','" + F24 + "','" + F25 + "','" + F26 + "','" + F27 + "','" + F28 + "','" + F29 + "','" + F30 + "','" + F31 + "','" + F32 + "','" + namaPenyakit + "') ";
                sql_datalatih += (sql_datalatih.equals("")?" insert into trainingrandom values ":", ")+sql2;
                //System.out.println(sql2);
            }
            stat4.executeUpdate(sql_datalatih);

            //System.out.println("sukses3");
            
            System.out.println("tabel Latih : ");
            for (int i = 0; i < TrainingTable.getRowCount(); i++) {
                for (int j = 0; j < TrainingTable.getColumnCount(); j++) {
                    //System.out.println("hm");
                    System.out.print(TrainingTable.getValueAt(i, j)+" ");
                }
                System.out.println("");
            }
            
            for (int i = 0; i < TestingTable.getRowCount(); i++) {
                Object no_pasien = TestingTable.getValueAt(i, 0);
                Object F1 = TestingTable.getValueAt(i, 1);
                Object F2 = TestingTable.getValueAt(i, 2);
                Object F3 = TestingTable.getValueAt(i, 3);
                Object F4 = TestingTable.getValueAt(i, 4);
                Object F5 = TestingTable.getValueAt(i, 5);
                Object F6 = TestingTable.getValueAt(i, 6);
                Object F7 = TestingTable.getValueAt(i, 7);
                Object F8 = TestingTable.getValueAt(i, 8);
                Object F9 = TestingTable.getValueAt(i, 9);
                Object F10 = TestingTable.getValueAt(i, 10);
                Object F11 = TestingTable.getValueAt(i, 11);
                Object F12 = TestingTable.getValueAt(i, 12);
                Object F13 = TestingTable.getValueAt(i, 13);
                Object F14 = TestingTable.getValueAt(i, 14);
                Object F15 = TestingTable.getValueAt(i, 15);
                Object F16 = TestingTable.getValueAt(i, 16);
                Object F17 = TestingTable.getValueAt(i, 17);
                Object F18 = TestingTable.getValueAt(i, 18);
                Object F19 = TestingTable.getValueAt(i, 19);
                Object F20 = TestingTable.getValueAt(i, 20);
                Object F21 = TestingTable.getValueAt(i, 21);
                Object F22 = TestingTable.getValueAt(i, 22);
                Object F23 = TestingTable.getValueAt(i, 23);
                Object F24 = TestingTable.getValueAt(i, 24);
                Object F25 = TestingTable.getValueAt(i, 25);
                Object F26 = TestingTable.getValueAt(i, 26);
                Object F27 = TestingTable.getValueAt(i, 27);
                Object F28 = TestingTable.getValueAt(i, 28);
                Object F29 = TestingTable.getValueAt(i, 29);
                Object F30 = TestingTable.getValueAt(i, 30);
                Object F31 = TestingTable.getValueAt(i, 31);
                Object F32 = TestingTable.getValueAt(i, 32);
                Object namaPenyakit = TestingTable.getValueAt(i, 33);

                //System.out.println("akankah suskes");
                
                String sql4 = " ('" + no_pasien + "','" + F1 + "','" + F2 + "','" + F3 + "','" + F4 + "','" + F5 + "','" + F6 + "','" + F7 + "','" + F8 + "',"
                + "'" + F9 + "','" + F10 + "','" + F11 + "','" + F12 + "','" + F13 + "','" + F14 + "','" + F15 + "','" + F16 + "','" + F17 + "','" + F18 + "','" + F19 + "','" + F20 + "',"
                + "'" + F21 + "','" + F22 + "','" + F23 + "','" + F24 + "','" + F25 + "','" + F26 + "','" + F27 + "','" + F28 + "','" + F29 + "','" + F30 + "','" + F31 + "','" + F32 + "','" + namaPenyakit + "') ";
                sql_datauji += (sql_datauji.equals("")?" insert into testingrandom values ":", ")+sql4;
                //System.out.println(sql4);
            }
            stat5.executeUpdate(sql_datauji);

            mulaiKlasifikasi.setVisible(true);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");

            Res3.close();
            stat3.close();
            stat4.close();
            stat5.close();
            Res12.close();
            stat12.close();
            ResA.close();
            statA.close();
            stat21.close();
            stat.close();
            stat2.close();
            stat21.close();
            stat30.close();
        } catch (SQLException ex) {
            Logger.getLogger(koneksidata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tampilkantabelActionPerformed

    private void RasiodataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RasiodataActionPerformed
        // TODO add your handling code here:
        tampilkantabel.setVisible(true);
        if (Rasiodata.getSelectedIndex() == 0) {
            persenTraining = 0.9;
            persenTesting = 0.1;
        } else if (Rasiodata.getSelectedIndex() == 1) {
            persenTraining = 0.8;
            persenTesting = 0.2;
        } else if (Rasiodata.getSelectedIndex() == 2) {
            persenTraining = 0.7;
            persenTesting = 0.3;
        } else if (Rasiodata.getSelectedIndex() == 3) {
            persenTraining = 0.6;
            persenTesting = 0.4;
        } else if (Rasiodata.getSelectedIndex() == 4) {
            persenTraining = 0.5;
            persenTesting = 0.5;
        } else if (Rasiodata.getSelectedIndex() == 5) {
            persenTraining = 0.4;
            persenTesting = 0.6;
        } else if (Rasiodata.getSelectedIndex() == 6) {
            persenTraining = 0.3;
            persenTesting = 0.7;
        } else if (Rasiodata.getSelectedIndex() == 7) {
            persenTraining = 0.2;
            persenTesting = 0.8;
        } else if (Rasiodata.getSelectedIndex() == 8) {
            persenTraining = 0.1;
            persenTesting = 0.9;
        }
    }//GEN-LAST:event_RasiodataActionPerformed

    private void inputLambdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputLambdaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputLambdaActionPerformed

    private void inputComplexityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputComplexityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputComplexityActionPerformed

    private void inputGammaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputGammaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputGammaActionPerformed

    private void inputIterasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputIterasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputIterasiActionPerformed

    private void prosesSVMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prosesSVMActionPerformed
        // TODO add your handling code here: 
        alfaBaru.getDataVector().removeAllElements();
        alfaBaru.fireTableDataChanged();
        
        koneksidata.Kernel= -1; 
        if(jenisKernel.getSelectedIndex()==0){
            koneksidata.Kernel=0;
        }else if(jenisKernel.getSelectedIndex()==1){
            koneksidata.Kernel=1;
        }else if(jenisKernel.getSelectedIndex()==2){
            koneksidata.Kernel=2;
        }
         
        koneksidata.Iterasi = Integer.parseInt(inputIterasi.getText());
        koneksidata.Complexity = Double.parseDouble(inputComplexity.getText());
        koneksidata.Lambda = Double.parseDouble(inputLambda.getText());
        koneksidata.Gamma = Double.parseDouble(inputGamma.getText());
        koneksidata.Epsilon=Double.parseDouble(inputEpsilon.getText());
        koneksidata.Sigma = Double.parseDouble(inputSigma.getText());
        prosesSVM.setEnabled(false); 
        tampilkantabel.setEnabled(false);
        progresHitung.setVisible(true);
        new Thread(new Runnable() { 
            @Override
            public void run() {            
        Connection connect = koneksidata.getKoneksi(); 
        try { 
            Statement stat = connect.createStatement();
            Statement stat2 = connect.createStatement();
            Statement stat3 = connect.createStatement();
                      
            String sql = "delete from inputanuser";
            String sql2 = "insert into inputanuser values ('"+koneksidata.Lambda+"', '"+koneksidata.Gamma+"','"+koneksidata.Sigma+"', '"+koneksidata.Complexity+"', '"+koneksidata.Kernel+"','"+koneksidata.Iterasi+"', '"+koneksidata.Epsilon+"'  )";
            String sql3 = "delete from alfalv1";

            stat.executeUpdate(sql);
            stat2.executeUpdate(sql2);
            stat3.executeUpdate(sql3);
            
            stat.close();
            stat2.close();
            stat3.close();

        } catch (SQLException ex) {
            Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);
        }

        DataTraining = new double[TrainingTable.getRowCount()][TrainingTable.getColumnCount() - 1];

        kernelSVM = new double[TrainingTable.getRowCount()][TrainingTable.getRowCount()];

        matriksHessian = new double[TrainingTable.getRowCount()][TrainingTable.getRowCount()];

        Ei = new double[TrainingTable.getRowCount()][TrainingTable.getRowCount() + 1];

        alfa = new double[TrainingTable.getRowCount()];

        deltaAlfa = new double[TrainingTable.getRowCount()];

        
        
        System.out.println("--------------------------------------------");
        System.out.println("        Data Latih");
        System.out.println("--------------------------------------------");

        for (int i = 0; i < DataTraining.length; i++) {
            for (int j = 0; j < DataTraining[0].length-1; j++) {
                String isi = String.valueOf(TrainingTable.getValueAt(i, j + 1));
                DataTraining[i][j] = Double.parseDouble(isi);
                System.out.print(DataTraining[i][j] + "|");
            }
            String isi = String.valueOf(TrainingTable.getValueAt(i, 32 + 1));
            DataTraining[i][32] =  (isi.toLowerCase().equals("tertiana")?1:-1);
            System.out.print(DataTraining[i][32] + "|");
            System.out.println("");
        }

        if(koneksidata.Kernel==2){
        System.out.println("-------------------------------------");
        System.out.println("               Kernel RBF"            );
        System.out.println("-------------------------------------");

        for (int i = 0; i < kernelSVM.length; i++) {
            for (int j = 0; j < kernelSVM[0].length; j++) {
                double jumlahPangkat = 0;
                //double sigma = 1;
                for (int k = 0; k < DataTraining[0].length - 1; k++) {
                    jumlahPangkat += Math.pow((DataTraining[i][k] - DataTraining[j][k]), 2);
                }
                kernelSVM[i][j] = Math.exp(-(jumlahPangkat) / (2 * Math.pow(koneksidata.Sigma, 2)));
                System.out.print(kernelSVM[i][j] + "|");
            }
            System.out.println("");
        }
        }else if(koneksidata.Kernel==1){
        System.out.println("-----------------------------------");
        System.out.println("            Kernel Poly"            );
        System.out.println("-----------------------------------");

        for (int i = 0; i < kernelSVM.length; i++) {
            for (int j = 0; j < kernelSVM[0].length; j++) {
                double jumlahKali = 0;
                for (int k = 0; k < DataTraining[0].length-1; k++) {
                    jumlahKali += (DataTraining[i][k] * DataTraining[j][k]);
                }
                kernelSVM[i][j] = Math.pow(jumlahKali,2);
                System.out.print(kernelSVM[i][j] + "|");
            }
            System.out.println("");
        }
        }else if(koneksidata.Kernel==0){
        System.out.println("-----------------------------------");
        System.out.println("            Kernel Linier"          );
        System.out.println("-----------------------------------");

        for (int i = 0; i < kernelSVM.length; i++) {
            for (int j = 0; j < kernelSVM[0].length; j++) {
                double jumlahKali = 0;
                for (int k = 0; k < DataTraining[0].length-1; k++) {
                    jumlahKali += (DataTraining[i][k] * DataTraining[j][k]);
                }
                kernelSVM[i][j] = jumlahKali;
                System.out.print(kernelSVM[i][j] + "|");
            }
            System.out.println("");
        }
        }
        
        System.out.println("------------------------------------------");
        System.out.println("               Matrix Hessian             ");
        System.out.println("------------------------------------------");
        double maxHessian = matriksHessian[0][0];
        for (int i = 0; i < matriksHessian.length; i++) {
            for (int j = 0; j < matriksHessian[0].length; j++) {
                //System.out.println(DataTraining[i][32]+" | "+DataTraining[j][32]);
                matriksHessian[i][j] = DataTraining[i][32] * DataTraining[j][32] * (kernelSVM[i][j] + Math.pow(koneksidata.Lambda, 2));
                System.out.print(matriksHessian[i][j] + "|");
                if (maxHessian < matriksHessian[i][j]) {
                    maxHessian = matriksHessian[i][j];
                }
            }
            System.out.println("");
        }

        double alfaAwal = 0;

        for (int i = 0; i < alfa.length; i++) {
            alfa[i] = alfaAwal;
        }
        
        gamma = koneksidata.Gamma / maxHessian;
        
        String sql_insert = "";
        for (int a = 0; a < koneksidata.Iterasi; a++) {
            System.out.println("------------------------------------------------------------");
            System.out.println("        Tabel Ei (iterasi " + (a + 1) + ")");
            System.out.println("------------------------------------------------------------");

            for (int i = 0; i < Ei.length; i++) {
                double jumlahBaris = 0;
                for (int j = 0; j < Ei.length; j++) {
                    Ei[i][j] = 1 * alfa[j] * matriksHessian[i][j];
                    jumlahBaris += Ei[i][j];
                    System.out.print(Ei[i][j]);
                }
                Ei[i][Ei[0].length - 1] = jumlahBaris;
                System.out.println("| " + Ei[i][Ei[0].length - 1]);
            }

            System.out.println("maxH = " + maxHessian);


            System.out.println("gamma = " + gamma);
            System.out.println("complexity = " + koneksidata.Complexity);

            System.out.println("------------------------------------------------------------");
            System.out.println("        Delta Alfa");
            System.out.println("------------------------------------------------------------");

            for (int i = 0; i < deltaAlfa.length; i++) {
                deltaAlfa[i] = Math.min(Math.max(gamma * (1 - Ei[i][Ei[0].length - 1]), -alfa[i]),
                koneksidata.Complexity - alfa[i]);
                System.out.print(deltaAlfa[i] + " | ");
                 if (maxDeltaAlfa < deltaAlfa[i])
                    maxDeltaAlfa = deltaAlfa[i];
            }
            System.out.println("");

            System.out.println("------------------------------------------------------------");
            System.out.println("        Alfa Baru");
            System.out.println("------------------------------------------------------------");

            for (int i = 0; i < alfa.length; i++) {
                alfa[i] = alfa[i] + deltaAlfa[i];
                System.out.print(alfa[i] + " | "); 
                if( a == koneksidata.Iterasi-1  ||
                        maxDeltaAlfa<koneksidata.Epsilon){
                    
                    String sql4 = " ('" + alfa[i] + "')";
                    sql_insert += (sql_insert.equals("")?" insert into alfalv1 values  ":", ")+sql4; 
                }
            } 
            System.out.println(""); 
        }
        try { 
            Statement stat4 = connect.createStatement();  
            stat4.executeUpdate(sql_insert); 
            stat4.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object Object[] = new Object[2];
        for (int i = 0; i < alfa.length; i++) {
            Object[0] = i + 1;
            Object[1] = alfa[i];
            alfaBaru.addRow(Object); 
        }

        System.out.println("---------------------------------------");
        System.out.println("        Tabel K+ dan K-                ");
        System.out.println("---------------------------------------");

        KxplusKxmin = new double[TrainingTable.getRowCount()][2];

        int maxPlus = -99;
        int maxMin = -99;

        double maxAlfaPlus = -99;
        double maxAlfaMinus = -99;

        for (int i = 0; i < TrainingTable.getRowCount(); i++) {
            System.out.println("indeks 32"+DataTraining[i][32]+" | "+alfa[i]);
            if (DataTraining[i][32] > 0) {
                if (maxAlfaPlus < alfa[i]) {
                    maxAlfaPlus = alfa[i];
                    maxPlus = i;
                }

            } else if (DataTraining[i][32] < 0) {
                if (maxAlfaMinus < alfa[i]) {
                    maxAlfaMinus = alfa[i];
                    maxMin = i;
                }
            }
        }

        System.out.println("Max Plus = " + maxPlus);
        System.out.println("nilai : " + alfa[maxPlus]);
        System.out.println("Max Min  = " + maxMin);
        System.out.println("nilai : " + alfa[maxMin]);

        for (int i = 0; i < KxplusKxmin.length; i++) {
            KxplusKxmin[i][0] = kernelSVM[i][maxPlus];
            KxplusKxmin[i][1] = kernelSVM[i][maxMin];
            System.out.print(KxplusKxmin[i][0] + " | " + KxplusKxmin[i][1]);
            System.out.println("");
        }

        System.out.println("-------------------------------------");
        System.out.println("              Tabel W                ");
        System.out.println("-------------------------------------");

        bobotW = new double[TrainingTable.getRowCount() + 1][2];

        double jumlahPlus = 0;
        double jumlahMin = 0;

        for (int i = 0; i < bobotW.length - 1; i++) {
            for (int j = 0; j < bobotW[0].length; j++) {
                bobotW[i][j] = KxplusKxmin[i][j] * DataTraining[i][32] * alfa[i];
                System.out.printf("%.9f | ", bobotW[i][j]);

                if (j == 0) {
                    jumlahPlus += bobotW[i][j];
                } else if (j == 1) {
                    jumlahMin += bobotW[i][j];
                }

            }
            System.out.println("");
        }

        bobotW[bobotW.length - 1][0] = jumlahPlus;
        bobotW[bobotW.length - 1][1] = jumlahMin;
        System.out.println(bobotW[bobotW.length - 1][0] + " | " + bobotW[bobotW.length - 1][1]);
        
        
        Bias = -0.5 * (bobotW[bobotW.length - 1][0] + bobotW[bobotW.length - 1][1]); 
        nilaiB.setText("Nilai B = " + Bias);
        System.out.println("Nilai B = " + Bias);
   
        
        try{ 
            Statement stat = connect.createStatement(); 
            System.out.println("nilai B : "+Bias); 
            String sql = "update nilai_bias set nilai = '"+Bias+"' where level = 1"; 
            stat.executeUpdate(sql); 
        }catch(SQLException ex){
            Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadPanelLevel(1);
        }
        }).start();
    }//GEN-LAST:event_prosesSVMActionPerformed
    
    public void LoadPanelLevel(int level){
        int progress = level*100/4;
        progresHitung.setValue(progress);
        progresHitung.setName(progress+"%");
        if(level<3){
            panel_data[level].refreshData();
        } else {
            hitungHasil();
        }
    }
    
    public  void perhitunganDone(){
        int progress = 4*100/4;
        progresHitung.setValue(progress);
        progresHitung.setName(progress+"%");
        prosesSVM.setEnabled(true);
        tampilkantabel.setEnabled(true);
        progresHitung.setVisible(false);
    }
    
    public void hitungHasil(){ 
        tabelHasil.getDataVector().removeAllElements();
        tabelHasil.fireTableDataChanged(); 
        
         new Thread(new Runnable() { 
             @Override
             public void run() {
                 mulaiPerhitungan();
                 perhitunganDone();
             }
         }).start();
    }
    
    public void mulaiPerhitungan(){
        try {
            int cocok = 0;
            String kata = ""; 
            double fx = 0;
            double b = 0;
            double jumlahKanan = 0; 
            int kondisi = 0; 
            String status = "";
            DataTesting = new double[TestingTable.getRowCount()][TestingTable.getColumnCount() - 2]; 
            int index2 = 0; 
            for (int i = 0; i < DataTesting.length; i++) {
                for (int j = 0; j < DataTesting[0].length; j++) {
                    DataTesting[i][j] = Double.parseDouble(String.valueOf(TestingTable.getValueAt(i, j + 1)));
                    System.out.print(DataTesting[i][j] + " | ");
                }
                index2++;
                System.out.println("");
            } 
            System.out.println("---------------");
            System.out.println(index2);  
            
            for (int d = 0; d < DataTesting.length; d++) {

                String no_pasien = String.valueOf(TestingTable.getValueAt(d, 0));
                String nilaiFx = "0";
                String sign[] = new String[3];

                for (int i = 0; i < sign.length; i++) {
                    sign[i] = "-";
                }

                String KelasSistem = "-";
                String KelasAsli = String.valueOf(TestingTable.getValueAt(d, 33));

                System.out.println("-----------------------------------------------");
                System.out.println("        Pengujian Data Uji " + (d + 1));
                System.out.println("-----------------------------------------------\n");

                DefaultTableModel tabelTemp = new DefaultTableModel();

                tabelTemp.setColumnCount(TrainingTable.getColumnCount());
                tabelTemp.setRowCount(TrainingTable.getRowCount());

                
                for (int i = 0; i < TrainingTable.getRowCount(); i++) {
                    for (int j = 0; j < TrainingTable.getColumnCount(); j++) {
                        tabelTemp.setValueAt(TrainingTable.getValueAt(i, j), i, j);
                    }
                }

                for (int a = 0; a < 3; a++) {

                    System.out.println("-----------------------------------------------");
                    System.out.println("        Pengujian Data Uji " + (d + 1) + " Level " + (a + 1));
                    System.out.println("-----------------------------------------------\n");

                    kondisi = a;

                    System.out.println("-----------------------------------------------");
                    System.out.println("        Data Latih");
                    System.out.println("-----------------------------------------------");

                    double DataTraining[][] = new double[tabelTemp.getRowCount()][tabelTemp.getColumnCount() - 1];
                    int index = 0;

                    for (int i = 0; i < DataTraining.length; i++) {
                        for (int j = 0; j < DataTraining[0].length - 1; j++) {
                            DataTraining[i][j] = Double.parseDouble(String.valueOf(tabelTemp.getValueAt(i, j + 1)));
                            System.out.print(DataTraining[i][j] + " | ");
                        }
                        System.out.println("akan berhasil");
                        System.out.print("|" + tabelTemp.getValueAt(i, 33));
                        if (tabelTemp.getValueAt(i, tabelTemp.getColumnCount() - 1).equals(tabelTemp.getValueAt(0, tabelTemp.getColumnCount() - 1))) {
                            DataTraining[i][DataTraining[0].length - 1] = 1;
                        } else {
                            DataTraining[i][DataTraining[0].length - 1] = -1;
                        }
                        System.out.print(DataTraining[i][DataTraining[0].length - 1] + " ");
                        System.out.println("");
                        index++;
                    }

                    System.out.println("---------------");
                    System.out.println(index);

                    double alfa[] = new double[DataTraining.length];
                    try {
                        Connection connect = koneksidata.getKoneksi();
                        Statement stat3 = connect.createStatement();
                        String sqlAlfa = "select nilaiAlfa from alfalv" + (a + 1);
                        System.out.println("Query "+alfa.length+" : "+sqlAlfa);
                        ResultSet rAlfa = stat3.executeQuery(sqlAlfa);
                        int indexAlfa = 0; 
                        int count = koneksidata.getNumRow(rAlfa);
                        //
                        while (rAlfa.next()) {
                            if(indexAlfa<alfa.length){
                                alfa[indexAlfa] = Double.parseDouble(rAlfa.getString("nilaiAlfa"));
                            } 
                            indexAlfa++;
                        } 
                        rAlfa.close(); 
                    } catch (SQLException ex) {
                        Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (koneksidata.Kernel == 2) {
                        System.out.println("------------------------------");
                        System.out.println(" Hitung Dot Product Kernel RBF");
                        System.out.println("------------------------------");
                        jumlahKanan = 0;
                        kernelSVM = new double[DataTraining.length][2];
                        for (int j = 0; j < kernelSVM.length; j++) {
                            double jumlahPangkat = 0;
                       
                            for (int k = 0; k < DataTraining[0].length - 1; k++) {
                                jumlahPangkat += Math.pow((DataTesting[d][k] - DataTraining[j][k]), 2);
                            }
                            kernelSVM[j][0] = Math.exp(-1 * jumlahPangkat / (2 * Math.pow(koneksidata.Sigma, 2)));
                            System.out.print(kernelSVM[j][0] + "|");

                            kernelSVM[j][1] = alfa[j] * DataTraining[j][32] * kernelSVM[j][0];                          
                            jumlahKanan += kernelSVM[j][1];
                            System.out.printf("%.12f |", kernelSVM[j][1]);

                            System.out.println("");
                        }

                    } else if (koneksidata.Kernel == 1) {
                        System.out.println("------------------------------------");
                        System.out.println("Hitung Dot Product Kernel Polynomial");
                        System.out.println("------------------------------------");

                        jumlahKanan = 0;

                        kernelSVM = new double[DataTraining.length][2];

                        for (int j = 0; j < kernelSVM.length; j++) {

                            double jumlahKali = 0;
                            //double sigma = 1;
                            for (int k = 0; k < DataTraining[0].length - 1; k++) {
                                jumlahKali += (DataTesting[d][k] * DataTraining[j][k]);
                            }
                            kernelSVM[j][0] = Math.pow(jumlahKali, 2);
                            System.out.print(kernelSVM[j][0] + "|");

                            kernelSVM[j][1] = alfa[j] * DataTraining[j][32] * kernelSVM[j][0];
                            //System.out.println(alfa[j] + "x" + DataTraining[j][32] + "x" + kernelSVM[j][0]);
                            jumlahKanan += kernelSVM[j][1];
                            System.out.printf("%.12f |", kernelSVM[j][1]);

                            System.out.println("");
                        }

                    } else if (koneksidata.Kernel == 0) {
                        System.out.println("--------------------------------");
                        System.out.println("Hitung Dot Product Kernel Linier");
                        System.out.println("--------------------------------");

                        jumlahKanan = 0;
                        kernelSVM = new double[DataTraining.length][2];
                        for (int j = 0; j < kernelSVM.length; j++) {
                            double jumlahKali = 0;
                           
                            for (int k = 0; k < DataTraining[0].length - 1; k++) {
                                jumlahKali += (DataTesting[d][k] * DataTraining[j][k]);
                            }
                            kernelSVM[j][0] = jumlahKali;
                            System.out.print(kernelSVM[j][0] + "|");

                            kernelSVM[j][1] = alfa[j] * DataTraining[j][32] * kernelSVM[j][0];
                         
                            jumlahKanan += kernelSVM[j][1];
                            System.out.printf("%.12f |", kernelSVM[j][1]);

                            System.out.println("");
                        }
                    }

                    System.out.printf("Jumlah Kanan : %.12f\n", jumlahKanan);

                    System.out.println("-----------------------------------------------");
                    System.out.println("        Nilai F(x)");
                    System.out.println("-----------------------------------------------");

                    try {
                        Connection connect = koneksidata.getKoneksi(); 
                        Statement stat = connect.createStatement(); 
                        String sql = "select nilai from nilai_bias where level = " + (a+1); 
                        ResultSet Res = stat.executeQuery(sql); 
                        while (Res.next()) {
                            b = Double.parseDouble(Res.getString("nilai"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println(b); 
                    fx = jumlahKanan + b; 
                    System.out.printf("Nilai F{x} : %.12f\n", fx);

                    System.out.println("-----------------------------------------------");
                    System.out.println("        Sign Nilai F(x)");
                    System.out.println("-----------------------------------------------");

                    if (fx > 0) {
                        System.out.println("Positif");
                        sign[a] = "1";
                        nilaiFx = String.valueOf(fx);
                        break;
                    } else {
                        System.out.println("Negatif");
                        sign[a] = "-1";
                    }

                    if (a == 2 && fx < 0) {
                        kondisi = 3;
                        nilaiFx = String.valueOf(fx);
                    }

                    kata = String.valueOf(tabelTemp.getValueAt(0, tabelTemp.getColumnCount() - 1));
                    System.out.println("katanya dihapus " + kata);
                    int jumlahHapus = 0;
                    try {
                        Connection connect = koneksidata.getKoneksi();
                        Statement stat = connect.createStatement();
                        String sql = "select count(*) from trainingrandom where jenisPenyakit = '" + kata + "'";
                        ResultSet Res = stat.executeQuery(sql);
                        while (Res.next()) {
                            jumlahHapus = Integer.parseInt(Res.getString("count(*)"));
                        }
                        Res.close();
                        stat.close();
                        System.out.println("yang dhapus : " + jumlahHapus);
                    } catch (SQLException ex) {
                        Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    for (int i = 0; i < jumlahHapus; i++) {
                        tabelTemp.removeRow(0);
                    }
                }

                switch (kondisi) {
                    case 0:
                        status = "Tertiana";
                        KelasSistem = "Tertiana";
                        break;
                    case 1:
                        status = "Kuartana";
                        KelasSistem = "Kuartana";
                        break;
                    case 2:
                        status = "Tropika";
                        KelasSistem = "Tropika";
                        break;
                    default:
                        status = "Ovale";
                        KelasSistem = "Ovale";
                        break;

                }
                System.out.println("------------------------------------");
                System.out.println("                KONDISI             ");
                System.out.println("------------------------------------");

                System.out.println("STATUS : " + status);

                if (KelasSistem.equalsIgnoreCase(KelasAsli)) {
                    cocok++;
                }

                Object object[] = new Object[7]; 
                object[0] = no_pasien;
                object[1] = nilaiFx;
                object[2] = sign[0];
                object[3] = sign[1];
                object[4] = sign[2];
                object[5] = KelasSistem;
                object[6] = KelasAsli;
                tabelHasil.addRow(object); 
            }

            jlhDataSesuai.setText(String.valueOf(cocok)); 
            System.out.println("jumlah " + TestingTable.getRowCount()); 
            double akurasi = (cocok * 100) / TestingTable.getRowCount(); 
            tingkatAkurasi.setText(String.valueOf(akurasi)); 
            for(int i=2; i<=4; i++) tabelTesting.getColumnModel().getColumn(i).setCellRenderer(new StatusColumnCellRenderer());
        } catch (Exception ex) {
            Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
             JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col); 
             if(value.equals("-")) l.setBackground(Color.red);
             else l.setBackground(Color.WHITE);
            return l; 
        }
    }
    private void jenisKernelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisKernelActionPerformed
        // TODO add your handling code here:
        if(jenisKernel.isValid()){
            prosesSVM.setVisible(true);
        }
    }//GEN-LAST:event_jenisKernelActionPerformed

    private void inputEpsilonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputEpsilonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputEpsilonActionPerformed

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
            java.util.logging.Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Klasifikasi_Malaria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NIM;
    private javax.swing.JComboBox Rasiodata;
    private javax.swing.JTextField inputComplexity;
    private javax.swing.JTextField inputEpsilon;
    private javax.swing.JTextField inputGamma;
    private javax.swing.JTextField inputIterasi;
    private javax.swing.JTextField inputLambda;
    private javax.swing.JTextField inputSigma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JComboBox jenisKernel;
    private javax.swing.JLabel jlhDataSesuai;
    private javax.swing.JLabel jlhdatalatih;
    private javax.swing.JLabel jlhdatauji;
    private javax.swing.JLabel judulApps;
    private javax.swing.JLabel labelAkurasi;
    private javax.swing.JLabel labelSesuai;
    private javax.swing.JButton mulaiKlasifikasi;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel nilaiB;
    private javax.swing.JPanel panelLevel1;
    private javax.swing.JProgressBar progresHitung;
    private javax.swing.JButton prosesSVM;
    private javax.swing.JTable tabTesting;
    private javax.swing.JTable tabTraining;
    private javax.swing.JTabbedPane tabUtama;
    private javax.swing.JTable tabelAlfaBaru;
    private javax.swing.JTable tabelTesting;
    private javax.swing.JButton tampilkantabel;
    private javax.swing.JLabel tingkatAkurasi;
    // End of variables declaration//GEN-END:variables
}
