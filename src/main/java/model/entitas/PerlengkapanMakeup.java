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
@DiscriminatorValue("PERLENGKAPAN")

public class PerlengkapanMakeup extends Makeup {
    @Column(name = "material", length = 50)
    private String material;

    public PerlengkapanMakeup() {
        super();
    }

    public PerlengkapanMakeup(String id, String nama, String merk, double harga, int stok, String material) {
        super(id, nama, merk, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    @Override
    public String getKategori() {
        return "Perlengkapan Makeup";
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("Material / Bahan: " + material);
    }
}
