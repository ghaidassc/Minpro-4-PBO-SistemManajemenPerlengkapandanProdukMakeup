/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.dao.MakeupDao;
import model.entitas.Makeup;
import model.entitas.PerlengkapanMakeup;
import model.entitas.ProdukMakeup;
import view.MakeupView;

/**
 *
 * @author gedascc
 */
public class MakeupController {
    private final MakeupView view;
    private final MakeupDao makeupDao;

    public MakeupController(MakeupView view, MakeupDao makeupDao) {
        this.view = view;
        this.makeupDao = makeupDao;
    }

    public void mulaiProgram() {
        boolean running = true;
        while (running) {
            view.tampilkanMenuUtama();
            int pilihan = view.inputPilihanMenu();

            switch (pilihan) {
                case 1 -> menuTambahBarang();
                case 2 -> menuTampilkanSemua();
                case 3 -> menuUbahBarang();
                case 4 -> menuHapusBarang();
                case 5 -> {
                    view.tampilkanPesan("\nTerima kasih telah menggunakan program ini :)");
                    running = false;
                }
                default -> view.tampilkanPesan("[Peringatan] Pilihan tidak valid! Masukkan angka 1-5.");
            }
        }
    }

    private void menuTambahBarang() {
        view.tampilkanHeaderMenu("Tambah Barang Baru");
        int kategori = view.inputKategoriBarang();
        if (kategori == -1) return;

        String id = null;
        for (int i = 0; i < 3; i++) {
            String inputId = view.inputTeks("Masukkan ID Barang: ");
            if (inputId.isEmpty()) {
                view.tampilkanPesan("ID tidak boleh kosong!");
            } else if (makeupDao.findById(inputId) != null) {
                view.tampilkanPesan("ID sudah terdaftar di database!");
            } else {
                id = inputId;
                break;
            }
            if (i == 2) return;
        }

        String nama = view.inputTeksWajib("Masukkan Nama Barang: ");
        if (nama == null) return;

        String merk = view.inputTeksWajib("Masukkan Merk Barang: ");
        if (merk == null) return;

        double harga = view.inputHarga();
        if (harga == -1) return;

        int stok = view.inputStok();
        if (stok == -1) return;

        Makeup baru;
        if (kategori == 1) {
            String shade = view.inputTeksWajib("Masukkan Shade / Varian: ");
            if (shade == null) return;
            baru = new ProdukMakeup(id, nama, merk, harga, stok, shade);
        } else {
            String material = view.inputTeksWajib("Masukkan Material / Bahan: ");
            if (material == null) return;
            baru = new PerlengkapanMakeup(id, nama, merk, harga, stok, material);
        }

        if (makeupDao.save(baru)) {
            view.tampilkanPesan("Barang berhasil disimpan!");
        }
    }

    private void menuTampilkanSemua() {
        view.tampilkanHeaderMenu("Daftar Semua Barang");
        List<Makeup> daftar = makeupDao.findAll();

        if (daftar.isEmpty()) {
            view.tampilkanPesan("Belum ada data barang di database.");
            return;
        }

        for (int i = 0; i < daftar.size(); i++) {
            System.out.println("Data ke-" + (i + 1));
            daftar.get(i).tampilkanData();
            System.out.println("--------------------------------------");
        }
    }

    private void menuUbahBarang() {
        view.tampilkanHeaderMenu("Ubah Data Barang");
        String id = view.inputTeks("Masukkan ID Barang yang ingin diubah: ");
        Makeup item = makeupDao.findById(id);

        if (item == null) {
            view.tampilkanPesan("Barang dengan ID '" + id + "' tidak ditemukan!");
            return;
        }

        item.tampilkanData("\nData saat ini:");
        System.out.println("--------------------------------------");

        String namaBaru = view.inputTeks("Nama baru (kosongkan jika tidak berubah): ");
        if (!namaBaru.isEmpty()) item.setNama(namaBaru);

        String merkBaru = view.inputTeks("Merk baru (kosongkan jika tidak berubah): ");
        if (!merkBaru.isEmpty()) item.setMerk(merkBaru);

        if (view.konfirmasiAksi("Ingin mengubah harga? (y/n): ")) {
            double hargaBaru = view.inputHarga();
            if (hargaBaru != -1) item.setHarga(hargaBaru);
        }

        if (view.konfirmasiAksi("Ingin mengubah stok? (y/n): ")) {
            int stokBaru = view.inputStok();
            if (stokBaru != -1) item.setStok(stokBaru);
        }

        if (item instanceof ProdukMakeup produk) {
            String shadeBaru = view.inputTeks("Shade baru (kosongkan jika tidak berubah): ");
            if (!shadeBaru.isEmpty()) produk.setShade(shadeBaru);
        } else if (item instanceof PerlengkapanMakeup perlengkapan) {
            String materialBaru = view.inputTeks("Material baru (kosongkan jika tidak berubah): ");
            if (!materialBaru.isEmpty()) perlengkapan.setMaterial(materialBaru);
        }

        if (makeupDao.update(item)) {
            view.tampilkanPesan("Data berhasil diperbarui!");
        }
    }

    private void menuHapusBarang() {
        view.tampilkanHeaderMenu("Hapus Barang");
        String id = view.inputTeks("Masukkan ID Barang yang ingin dihapus: ");
        Makeup item = makeupDao.findById(id);

        if (item == null) {
            view.tampilkanPesan("Barang dengan ID '" + id + "' tidak ditemukan!");
            return;
        }

        if (view.konfirmasiAksi("Yakin hapus '" + item.getNama() + "'? (y/n): ")) {
            if (makeupDao.delete(id)) {
                view.tampilkanPesan("Barang berhasil dihapus!");
            }
        } else {
            view.tampilkanPesan("Penghapusan dibatalkan.");
        }
    }
}
