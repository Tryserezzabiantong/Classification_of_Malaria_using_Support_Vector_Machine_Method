/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasifikasimalaria;

import java.sql.Connection; //koneksi ke database
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;// untuk menerima object resultSet
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;// fungsi untuk membuat model tabel dan isinya

/**
 *
 * @author Tryse Rezza Biantong
 */
public class LevelTraining extends javax.swing.JPanel {

    /**
     * Creates new form LevelTraining
     */
    String[] kelas = {"tertiana","kuartana","tropika","ovale"};
    public int baris = 0;
    private double gamma=0;
    private double TrainingData[][];
    private double kernelSVM[][];
    private double matriksHessian[][];
    private double Ei[][];
    public double alfa[];
    private double deltaAlfa[];
    public  double maxDeltaAlfa;
    private double KxplusKxmin[][];
    private double bobotW[][];
    public double Bias;
    public boolean Hitung = false;
    public int level = 1;
    public Object main_page=null;
    public DefaultTableModel alfaBaru; 
    
    public LevelTraining() {
        initComponents();
        alfaBaru = new DefaultTableModel(); //mengkonstruksi baris dan kolom = 0
         
        tabelKernel.setModel(alfaBaru); //menyisipkan sebuah baris pada baris di model
         
        alfaBaru.addColumn("Alfa"); //Menambah sebuah kolom dengan nama alfa di model
        alfaBaru.addColumn("Nilai");//Menambah sebuah model dengan nama Nilai di model
    }
    
    public void setLevel(int traininglevel){ //
        level = traininglevel;
    }
    public void setMainPage(Object utama){
        main_page = utama;
    }
    
    public Boolean getStatus(){
        return Hitung;
    }
    
    public  void refreshData(){ 
        Hitung = true; 
        
    //mulai hitung
        alfaBaru.getDataVector().removeAllElements();
        alfaBaru.fireTableDataChanged();
        new Thread(new Runnable() { 
            @Override
            public void run() {
            
        Connection connect = koneksidata.getKoneksi(); 
        try{ 
            Statement stat = connect.createStatement();
            Statement statA = connect.createStatement();
            String sql = "delete from alfalv"+level; 
            stat.executeUpdate(sql);
            String kondisi ="";
            for(int i=0; i<level-1; i++)
            kondisi += (kondisi.equals("")?" WHERE ":" AND ")+"  jenisPenyakit <> '"+kelas[i]+"' ";
            String sql1 = "SELECT * FROM trainingrandom "+kondisi; 
            System.out.println("Query : "+sql1);
            String sql2 = "SELECT COUNT(*) FROM trainingrandom "+kondisi;
            ResultSet Res = stat.executeQuery(sql1); 
            ResultSet Res2 = statA.executeQuery(sql2); 
            while (Res2.next()) {  baris = Integer.parseInt(Res2.getString("COUNT(*)")); }
             
            TrainingData = new double[baris][33]; 
            kernelSVM = new double[baris][baris]; 
            matriksHessian = new double[baris][baris]; 
            Ei = new double[baris][baris + 1]; 
            alfa = new double[baris]; 
            deltaAlfa = new double[baris]; 
            int index= 0; 
            while (Res.next()) {  
                TrainingData[index][0] = Double.parseDouble(Res.getString("F1"));
                TrainingData[index][1] = Double.parseDouble(Res.getString("F2"));
                TrainingData[index][2] = Double.parseDouble(Res.getString("F3"));
                TrainingData[index][3] = Double.parseDouble(Res.getString("F4"));
                TrainingData[index][4] = Double.parseDouble(Res.getString("F5"));
                TrainingData[index][5] = Double.parseDouble(Res.getString("F6"));
                TrainingData[index][6] = Double.parseDouble(Res.getString("F7"));
                TrainingData[index][7] = Double.parseDouble(Res.getString("F8"));
                TrainingData[index][8] = Double.parseDouble(Res.getString("F9"));
                TrainingData[index][9] = Double.parseDouble(Res.getString("F10"));
                TrainingData[index][10] = Double.parseDouble(Res.getString("F11"));
                TrainingData[index][11] = Double.parseDouble(Res.getString("F12"));
                TrainingData[index][12] = Double.parseDouble(Res.getString("F13"));
                TrainingData[index][13] = Double.parseDouble(Res.getString("F14"));
                TrainingData[index][14] = Double.parseDouble(Res.getString("F15"));
                TrainingData[index][15] = Double.parseDouble(Res.getString("F16"));
                TrainingData[index][16] = Double.parseDouble(Res.getString("F17"));
                TrainingData[index][17] = Double.parseDouble(Res.getString("F18"));
                TrainingData[index][18] = Double.parseDouble(Res.getString("F19"));
                TrainingData[index][19] = Double.parseDouble(Res.getString("F20"));
                TrainingData[index][20] = Double.parseDouble(Res.getString("F21"));
                TrainingData[index][21] = Double.parseDouble(Res.getString("F22"));
                TrainingData[index][22] = Double.parseDouble(Res.getString("F23"));
                TrainingData[index][23] = Double.parseDouble(Res.getString("F24"));
                TrainingData[index][24] = Double.parseDouble(Res.getString("F25"));
                TrainingData[index][25] = Double.parseDouble(Res.getString("F26"));
                TrainingData[index][26] = Double.parseDouble(Res.getString("F27"));
                TrainingData[index][27] = Double.parseDouble(Res.getString("F28"));
                TrainingData[index][28] = Double.parseDouble(Res.getString("F29"));
                TrainingData[index][29] = Double.parseDouble(Res.getString("F30"));
                TrainingData[index][30] = Double.parseDouble(Res.getString("F31"));
                TrainingData[index][31] = Double.parseDouble(Res.getString("F32"));
                String penyakit = (Res.getString("jenisPenyakit")); 
                if (penyakit.toLowerCase().equals(kelas[level-1])) {
                    TrainingData[index][32] = 1; 
                } else {
                    TrainingData[index][32] = -1;
                }
                index++; 
            }
            
            
            System.out.println("------------------------------------------------------------");
            System.out.println("        Data Latih");
            System.out.println("------------------------------------------------------------");

            for (int i = 0; i < TrainingData.length; i++) {
                for (int j = 0; j < TrainingData[0].length; j++) { 
                    System.out.print(TrainingData[i][j] + "|");
                }
                System.out.println("");
            }

            if(koneksidata.Kernel==2){
            System.out.println("--------------------------------------");
            System.out.println("              Kernel RBF"              );
            System.out.println("--------------------------------------");

            for (int i = 0; i < kernelSVM.length; i++) {
                for (int j = 0; j < kernelSVM[0].length; j++) {
                    double jumlahPangkat = 0;
                    //double sigma = 1;
                    for (int k = 0; k < TrainingData[0].length - 1; k++) {
                        jumlahPangkat += Math.pow((TrainingData[i][k] - TrainingData[j][k]), 2);
                    }
                    kernelSVM[i][j] = Math.exp(-1 * jumlahPangkat / (2 * Math.pow(koneksidata.Sigma, 2)));
                    System.out.print(kernelSVM[i][j] + "|");
                }
                System.out.println("");
            }

            }else if(koneksidata.Kernel==1){
            System.out.println("--------------------------------------");
            System.out.println("             Kernel Poly"              );
            System.out.println("--------------------------------------");

            for (int i = 0; i < kernelSVM.length; i++) {
                for (int j = 0; j < kernelSVM[0].length; j++) {
                    double jumlahKali = 0;
                    for (int k = 0; k < TrainingData[0].length-1; k++) {
                        jumlahKali += (TrainingData[i][k] * TrainingData[j][k]);
                    }
                    kernelSVM[i][j] = Math.pow(jumlahKali,2);
                    System.out.print(kernelSVM[i][j] + "|");
                }
                System.out.println("");
            }
            }else if(koneksidata.Kernel==0){
            System.out.println("--------------------------------------");
            System.out.println("           Kernel Linier"              );
            System.out.println("--------------------------------------");

            for (int i = 0; i < kernelSVM.length; i++) {
                for (int j = 0; j < kernelSVM[0].length; j++) {
                    double jumlahKali = 0;
                    for (int k = 0; k < TrainingData[0].length-1; k++) {
                        jumlahKali += (TrainingData[i][k] * TrainingData[j][k]);
                    }
                    kernelSVM[i][j] = jumlahKali;
                    System.out.print(kernelSVM[i][j] + "|");
                }
                System.out.println("");
            }

            }

            System.out.println("------------------------------------------------------------");
            System.out.println("        Matriks Hessian");
            System.out.println("------------------------------------------------------------");

            //double lambda = 0.5;
            double maxHessian = matriksHessian[0][0];

            System.out.println("size : " + matriksHessian.length + " x " + matriksHessian[0].length);

            for (int i = 0; i < matriksHessian.length; i++) {
                for (int j = 0; j < matriksHessian[0].length; j++) {
                    //System.out.println(dataLatih[i][32]+" | "+dataLatih[j][32]);
                    matriksHessian[i][j] = TrainingData[i][32] * TrainingData[j][32] * (kernelSVM[i][j] + Math.pow(koneksidata.Lambda, 2));
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
                System.out.println("------------------------------------");
                System.out.println(" Tabel Ei (iterasi " + (a + 1) + ")" );
                System.out.println("------------------------------------");
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

                System.out.println("----------------------------------------");
                System.out.println("                Delta Alfa"              );
                System.out.println("----------------------------------------");
                for (int i = 0; i < deltaAlfa.length; i++) {
                    deltaAlfa[i] = Math.min(Math.max(gamma * (1 - Ei[i][Ei[0].length - 1]), -alfa[i]), koneksidata.Complexity - alfa[i]);
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
                    if ( a == koneksidata.Iterasi-1 ||
                            maxDeltaAlfa < koneksidata.Epsilon) {
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
                Object[0] = i+1;
                Object[1] = alfa[i];
                alfaBaru.addRow(Object);
            } 

            System.out.println("------------------------------------------------------------");
            System.out.println("        Tabel K+ dan K-");
            System.out.println("------------------------------------------------------------");

            KxplusKxmin = new double[baris][2];

            int maxPlus = -99;
            int maxMin = -99;

            double maxAlfaPlus = -99;
            double maxAlfaMinus = -99;

            for (int i = 0; i < baris; i++) {
                System.out.println("dataLatih = "+TrainingData[i][32]);
                System.out.println("indeks 32"+TrainingData[i][32]+" | "+alfa[i]);
                if (TrainingData[i][32] > 0) {
                    System.out.println("Cari Max Plus = " + maxPlus+" ("+maxAlfaPlus+" < "+alfa[i]+")" );
                    if (maxAlfaPlus < alfa[i]) {
                        maxAlfaPlus = alfa[i];
                        maxPlus = i;
                        System.out.println("Ketemu Max Plus = " + maxPlus);
                    } 
                } else if (TrainingData[i][32] < 0) {
                    System.out.println("Cari Max Min = " + maxMin+" ("+maxAlfaMinus+" < "+alfa[i]+")" );
                    if (maxAlfaMinus < alfa[i]) {
                        maxAlfaMinus = alfa[i];
                        maxMin = i; 
                        System.out.println("Ketemu Max Plus = " + maxPlus);
                    }
                }
            }
            try{
                System.out.println("Max Plus = " + maxPlus);
                System.out.println("nilai : " + alfa[maxPlus]); 
            } catch(Exception ex){Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);}
            try{ 
                System.out.println("Max Min  = " + maxMin);
                System.out.println("nilai : " + alfa[maxMin]);
            } catch(Exception ex){Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);}
            for (int i = 0; i < KxplusKxmin.length; i++) {
                KxplusKxmin[i][0] = kernelSVM[i][maxPlus];
                KxplusKxmin[i][1] = kernelSVM[i][maxMin];
                System.out.print(KxplusKxmin[i][0] + " | " + KxplusKxmin[i][1]);
                System.out.println("");
            }

            System.out.println("------------------------------------------------------------");
            System.out.println("        Tabel W");
            System.out.println("------------------------------------------------------------");

            bobotW = new double[baris + 1][2];

            double jumlahPlus = 0;
            double jumlahMin = 0;

            for (int i = 0; i < bobotW.length - 1; i++) {
                for (int j = 0; j < bobotW[0].length; j++) {
                    bobotW[i][j] = KxplusKxmin[i][j] * TrainingData[i][32] * alfa[i];
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


            try{ 
                System.out.println("nilai B : "+Bias);  
                stat.executeUpdate("update nilai_bias set nilai = '"+Bias+"' where level = "+level); 
            }catch(SQLException ex){
                Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);
            }
            stat.close(); 
            statA.close();
            Hitung = false;
            ((Klasifikasi_Malaria)main_page).LoadPanelLevel(level);
        }catch(SQLException ex){Logger.getLogger(Klasifikasi_Malaria.class.getName()).log(Level.SEVERE, null, ex);}
        Hitung = false;
        }
        }).start();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JKernel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKernel = new javax.swing.JTable();
        nilaiB = new javax.swing.JLabel();

        tabelKernel.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelKernel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );

        JKernel.addTab("Alfa Baru", jPanel1);

        nilaiB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nilaiB.setText("Nilai B = ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JKernel)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nilaiB, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JKernel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nilaiB)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane JKernel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nilaiB;
    private javax.swing.JTable tabelKernel;
    // End of variables declaration//GEN-END:variables
}
