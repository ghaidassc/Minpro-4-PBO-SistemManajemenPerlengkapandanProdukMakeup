/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entitas;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
    
/**
 *
 * @author gedascc
 */
@Entity
@Table(name = "makeup")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kategori", discriminatorType = DiscriminatorType.STRING)
public abstract class Makeup implements KelolaBarang {

    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "nama", nullable = false, length = 100)
    private String nama;

    @Column(name = "merk", nullable = false, length = 100)
    private String merk;

    @Column(name = "harga", nullable = false)
    private double harga;

    @Column(name = "stok", nullable = false)
    private int stok;
    public Makeup() {}
    
    public Makeup(String id, String nama, String merk, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.merk = merk;
        this.harga = harga;
        setStok(stok);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getMerk() { return merk; }
    public void setMerk(String merk) { this.merk = merk; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) {
        if (stok <= 0) {
            System.out.println("Stok minimal 1! Nilai diatur ke 1.");
            this.stok = 1;
        } else {
            this.stok = stok;
        }
    }

    public abstract String getKategori();

    @Override
    public void tampilkanData() {
        System.out.println("ID Barang: " + id);
        System.out.println("Nama: " + nama);
        System.out.println("Merk: " + merk);
        System.out.println("Kategori: " + getKategori());
        System.out.printf("Harga: Rp%,.2f\n", harga);
        System.out.println("Stok: " + stok + " pcs");
    }

    public void tampilkanData(String header) {
        System.out.println(header);
        tampilkanData();
    }
}


