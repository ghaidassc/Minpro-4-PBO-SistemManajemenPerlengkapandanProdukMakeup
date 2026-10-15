/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;

/**
 *
 * @author gedascc
 */
public class MakeupView {
    private final Scanner scanner = new Scanner(System.in);

    public void tampilkanMenuUtama() {
        System.out.println("==================================================");
        System.out.println(" SISTEM MANAJEMEN PERLENGKAPAN & PRODUK MAKEUP");
        System.out.println("==================================================");
        System.out.println("1. Tambah Data Barang");
        System.out.println("2. Tampilkan Semua Barang");
        System.out.println("3. Ubah Data Barang");
        System.out.println("4. Hapus Data Barang");
        System.out.println("5. Keluar");
    }

    public void tampilkanHeaderMenu(String judul) {
        System.out.println("\n===== " + judul + " =====");
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    public int inputPilihanMenu() {
        System.out.print("Pilih menu: ");
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String inputTeks(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public String inputTeksWajib(String prompt) {
        for (int i = 0; i < 3; i++) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input tidak boleh kosong! (Percobaan " + (i + 1) + "/3)");
        }
        System.out.println("Terlalu banyak percobaan salah.");
        return null;
    }

    public int inputKategoriBarang() {
        for (int i = 0; i < 3; i++) {
            System.out.print("Pilih Kategori (1. Produk Makeup / 2. Perlengkapan Makeup): ");
            String input = scanner.nextLine().trim();
            if ("1".equals(input) || "2".equals(input)) {
                return Integer.parseInt(input);
            }
            System.out.println("Pilihan harus 1 atau 2! (Percobaan " + (i + 1) + "/3)");
        }
        System.out.println("Terlalu banyak percobaan salah.");
        return -1;
    }

    public double inputHarga() {
        for (int i = 0; i < 3; i++) {
            System.out.print("Masukkan Harga (Rp): ");
            String input = scanner.nextLine().trim();
            try {
                double harga = Double.parseDouble(input);
                if (harga > 0) return harga;
                System.out.println("Harga harus lebih dari 0!");
            } catch (NumberFormatException e) {
                System.out.println("Harga harus berupa angka!");
            }
        }
        return -1;
    }

    public int inputStok() {
        for (int i = 0; i < 3; i++) {
            System.out.print("Masukkan Stok (pcs): ");
            String input = scanner.nextLine().trim();
            try {
                int stok = Integer.parseInt(input);
                if (stok >= 1) return stok;
                System.out.println("Stok harus minimal 1!");
            } catch (NumberFormatException e) {
                System.out.println("Stok harus bilangan bulat!");
            }
        }
        return -1;
    }

    public boolean konfirmasiAksi(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim().toLowerCase();
        return "y".equals(input);
    }
}
