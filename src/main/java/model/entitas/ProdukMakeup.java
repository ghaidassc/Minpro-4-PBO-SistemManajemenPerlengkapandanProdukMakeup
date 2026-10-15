/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entitas;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author gedascc
 */
@Entity
@DiscriminatorValue("PRODUK")

public class ProdukMakeup extends Makeup {
    @Column(name = "shade", length = 50)
    private String shade;

    public ProdukMakeup() {
        super();
    }

    public ProdukMakeup(String id, String nama, String merk, double harga, int stok, String shade) {
        super(id, nama, merk, harga, stok);
        this.shade = shade;
    }

    public String getShade() { return shade; }
    public void setShade(String shade) { this.shade = shade; }

    @Override
    public String getKategori() {
        return "Produk Makeup";
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("Shade / Varian: " + shade);
    }
}
