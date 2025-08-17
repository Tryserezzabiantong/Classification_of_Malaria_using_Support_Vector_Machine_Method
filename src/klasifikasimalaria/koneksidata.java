/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasifikasimalaria;

import java.sql.Connection;// untuk mempresentasikan suatu koneksi dengan suatu data source
import java.sql.DriverManager; // memanggil driver JDBC ke memori dan juga digunakan untuk membuka koneksi ke sumber data
import java.sql.SQLException; // agar jika terjadi kesalahan (error) dalam pengaksesan dapat dibungkus oleh suatu class exception
import java.sql.ResultSet; //mempresentasikan sebuah hasil dari database yang dihasilkan dari statemen SQL SELECT.
import java.util.logging.Level; //fungsi untuk menampilkan informasi dari database tiap levelnya
import java.util.logging.Logger; //fungsi untuk menampilkan segala informasi apa saja yang sudah dilakukan user serta error apa saja yang terjadi sehingga gampang untuk melakukan maintanace
import javax.swing.JOptionPane;// agar user dapat berinteraksi dengan sistem melalu input/output dialog yang nantinya dapat ditampilkan.
import java.lang.ClassNotFoundException;

/**
 *
 * @author Tryse Rezza Biantong
 */
public class koneksidata {
//Source code untuk Koneksi ke Database
    private static Connection koneksi; 
    public static Connection getKoneksi(){ //untuk proses autorisasi terhubung ke database
        if (koneksi == null){
            try {
                String url = "jdbc:mysql://localhost/penyakitmalaria1"; //database yang berada di server localhost dengan nama database test
                String user = "root"; //user untuk masuk ke database
                String pass = ""; //password untuk masuk ke database
                
                // Gunakan driver yang lebih modern dan kompatibel
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    koneksi = (Connection) DriverManager.getConnection(url, user, pass);
                } catch (ClassNotFoundException e) {
                    // Jika driver tidak ditemukan, buat mock connection
                    JOptionPane.showMessageDialog(null, "MySQL Driver tidak ditemukan. Program akan berjalan dalam mode offline.");
                    return null;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Proses Koneksi Gagal ke Database "+e); //pemberitahuan jika koneksi ke database gagal
            }
        }
        return koneksi; 
    } 
    
    //fungsi untuk mengambil nilai dari setiap kolom
    public  static int getNumRow(ResultSet resultSet){ 
        int count = 0;
        try {
            resultSet.last();
            count = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(koneksidata.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count; //menghitung data yang diambil dari tiap kolom di database
    }
    
    //variabel yang ada pada  database
    
    public static  int Iterasi=0;
    public static  double Complexity=0;
    public static  double Lambda=0;
    public static  double Gamma=0;
    public static  double Sigma=0;
    public static double Epsilon=0;
    public static  int Kernel = -1;
}
