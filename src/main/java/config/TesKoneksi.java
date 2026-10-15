/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;

/**
 *
 * @author gedascc
 */
public class TesKoneksi {
    public static void main(String[] args) {
        Connection conn = Koneksi.getConnection();
        
        if(conn != null ){
            System.out.println("Koneksi Berhasil");
        }else{
            System.out.println("Gagal");
        }
    }
}
