Nama: Ghaida Suci Nahiza                                   
NIM: 2509116077

# Sistem Manajemen Perlengkapan dan Produk Makeup 💄💅🛍️

## 1. Deskripsi Singkat
Sistem Manajemen Perlengkapan dan Produk Makeup merupakan program berbasis antarmuka konsol (CLI) yang digunakan untuk mencatat dan mengelola data inventaris barang rias secara terstruktur dan terintegrasi persisten dengan basis data relasional MySQL (`db_makeup`). Program ini menangani dua entitas barang utama, yaitu kosmetik (`ProdukMakeup`) dan aplikator alat rias (`PerlengkapanMakeup`). Setiap barang memiliki atribut identitas berupa ID Barang, Nama Barang, Merk, Harga, dan Stok. Program dilengkapi dengan sistem penanganan input (*defensive programming*) untuk mencegah program terhenti tiba-tiba (*crash*) akibat kesalahan format masukan pengguna, dengan batasan maksimal 3 kali percobaan salah sebelum aksi dibatalkan.

Entitas barang dibedakan menjadi dua kelas spesifik: `ProdukMakeup` yang menyimpan informasi varian/shade riasan, serta `PerlengkapanMakeup` yang menyimpan informasi bahan material alat. Pengguna dapat menjalankan fitur pengelolaan data melalui menu interaktif: Tambah Data Barang (Create), Tampilkan Semua Barang (Read), Ubah Data Barang (Update), Hapus Data Barang (Delete), dan Keluar. Berbeda dengan program berbasis memori sementara (`ArrayList`), data pada program ini tersimpan secara permanen di database MySQL. Data awal (*dummy data*) langsung tersedia sejak aplikasi pertama kali dijalankan melalui file skrip inisialisasi basis data eksternal `db_makeup.sql`.

Program ini dikembangkan sebagai pemenuhan tugas **Mini Project 4** pada mata kuliah Pemrograman Berorientasi Objek dengan menerapkan arsitektur Model-View-Controller (MVC), pola perancangan Data Access Object (DAO), konsep Encapsulation, Inheritance, Abstraction, Polymorphism, konektivitas dasar JDBC, serta fitur nilai tambah berupa implementasi Framework ORM (Jakarta Persistence API / Hibernate Core).

---

## 2. Tujuan Program
Program ini dibuat sebagai pemenuhan tugas Mini Project 4 pada mata kuliah Pemrograman Berorientasi Objek melalui penerapan arsitektur perangkat lunak modern dan integrasi basis data relasional.

Tujuan program adalah:
- Mengintegrasikan aplikasi Java secara persisten dengan basis data MySQL `db_makeup`.
- Mengimplementasikan pemisahan tanggung jawab kode menggunakan arsitektur Model-View-Controller (MVC) dan Data Access Object (DAO).
- Menerapkan inisialisasi dummy data awal langsung melalui skrip eksternal basis data (`db_makeup.sql`) tanpa pengisian langsung di dalam kode (*hardcoding*).
- Mempertahankan prinsip dasar PBO: *Encapsulation* (akses private dan validasi setter), *Inheritance* (hierarki kelas `Makeup`), *Abstraction* (abstract class, abstract method, dan interface), serta *Polymorphism* (method overriding, overloading, dan polymorphic query).
- Memenuhi kriteria nilai tambah dengan mengimplementasikan framework ORM (Hibernate Core / Jakarta Persistence) untuk pemetaan tabel database ke objek Java (*Object-Relational Mapping*) menggunakan strategi *Single Table Inheritance*.
- Menyediakan antarmuka terminal interaktif yang kokoh melalui mekanisme validasi masukan secara defensif (*defensive programming*).

---

## 3. Struktur Package
Program disusun menggunakan pola arsitektur Model-View-Controller (MVC) yang dipadukan dengan DAO Pattern untuk memastikan pemisahan tanggung jawab fungsional (*separation of concerns*):

| Package | Class / Interface / File | Peran |
| --- | --- | --- |
| `config` | `Koneksi.java` | Menyediakan konfigurasi konektivitas native JDBC driver MySQL ke basis data `db_makeup`. |
| `config` | `TesKoneksi.java` | Kelas utilitas pengujian awal untuk memvalidasi keberhasilan koneksi JDBC ke database. |
| `controller` | `MakeupController.java` | Menghubungkan View dan DAO, mengontrol logika alur kerja menu CRUD, serta mengelola validasi masukan dari pengguna. |
| `main` | `Main.java` | Menjadi *entry point* program. Bertugas menginisialisasi JPA `EntityManagerFactory`, `EntityManager`, DAO, View, dan Controller sebelum menjalankan aplikasi. |
| `model.dao` | `MakeupDao.java` | Kelas Data Access Object yang mengenkapsulasi seluruh operasi manipulasi data (CRUD) ke basis data MySQL menggunakan `EntityManager` JPA / Hibernate. |
| `model.entitas` | `KelolaBarang.java` *(Interface)* | Menjadi kontrak antarmuka yang mendefinisikan metode dasar penampilan data (`tampilkanData()`). |
| `model.entitas` | `Makeup.java` *(Abstract Class)* | Superclass entitas abstrak yang memetakan tabel `makeup` menggunakan anotasi JPA (`@Entity`, `@Table`, `@Inheritance`), memuat atribut umum, serta abstract method `getKategori()`. |
| `model.entitas` | `ProdukMakeup.java` | Subclass turunan `Makeup` yang dipetakan sebagai entitas dengan `@DiscriminatorValue("PRODUK")` untuk kosmetik berspesifikasi *shade*. |
| `model.entitas` | `PerlengkapanMakeup.java` | Subclass turunan `Makeup` yang dipetakan sebagai entitas dengan `@DiscriminatorValue("PERLENGKAPAN")` untuk alat rias berspesifikasi *material*. |
| `view` | `MakeupView.java` | Mengatur seluruh tampilan antarmuka konsol, pencetakan pesan sistem, serta menangani masukan pengguna dan validasi defensif melalui `Scanner`. |
| `resources/META-INF` | `persistence.xml` | Berkas konfigurasi deklaratif unit persistensi JPA (`makeupPU`) yang mengatur dialek database, koneksi JDBC, dan pendaftaran kelas entitas ORM. |
| `(root)` | `db_makeup.sql` | Skrip pembuatan database `db_makeup`, pembuatan skema tabel `makeup`, serta pengisian data dummy awal. |

### Diagram Struktur Direktori
```text
ManajemenMakeup-Minproo4/
├── db_makeup.sql
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   ├── config/
        │   │   ├── Koneksi.java
        │   │   └── TesKoneksi.java
        │   ├── controller/
        │   │   └── MakeupController.java
        │   ├── main/
        │   │   └── Main.java
        │   ├── model/
        │   │   ├── dao/
        │   │   │   └── MakeupDao.java
        │   │   └── entitas/
        │   │       ├── KelolaBarang.java
        │   │       ├── Makeup.java
        │   │       ├── PerlengkapanMakeup.java
        │   │       └── ProdukMakeup.java
        │   └── view/
        │       └── MakeupView.java
        └── resources/
            └── META-INF/
                └── persistence.xml
```

---

# 4. Menu Program                   
Menu utama yang tersedia pada sistem adalah:                                  
<img width="314" height="125" alt="image" src="https://github.com/user-attachments/assets/67316c41-df74-428c-9d0b-cbaf014022f6" />                                

### Rincian Fungsi Menu:

**1. Tambah Data Barang**  
   Digunakan untuk memasukkan data barang baru ke dalam database `db_makeup`. Pengguna memilih kategori terlebih dahulu (Produk Makeup atau Perlengkapan Makeup), kemudian mengisikan ID barang (unik dan belum terdaftar), nama, merk, harga (harus > 0), stok (minimal 1), serta atribut spesifik kategori (shade untuk produk kosmetik, atau material bahan untuk perlengkapan alat). Seluruh masukan divalidasi dengan toleransi maksimal 3 kali kesalahan. Objek yang telah divalidasi disimpan ke basis data via `makeupDao.save(baru)`.                      

**2. Tampilkan Semua Barang**  
   Digunakan untuk mencetak seluruh data inventaris yang tersimpan di dalam tabel MySQL. Fitur ini langsung menampilkan data dummy yang diisi dari file `.sql` tanpa perlu input dari awal. Pemanggilan dilakukan via `makeupDao.findAll()` yang mengeksekusi query objek JPQL dan memanfaatkan polimorfisme (dynamic binding) saat mencetak detail barang.

**3. Ubah Data Barang**  
   Digunakan untuk memperbarui informasi barang berdasarkan ID yang dicari. Fitur ini menerapkan method overloading `tampilkanData(header)` untuk menampilkan rincian data saat ini sebelum diubah. Pengguna dapat memperbarui nama, merk, harga, stok, maupun atribut spesifik kategori. Objek yang diperbarui kemudian disimpan kembali ke database via `makeupDao.update(item)`.                   

**4. Hapus Data Barang**  
   Digunakan untuk menghapus data barang dari tabel database berdasarkan ID barang. Sistem menampilkan konfirmasi persetujuan keamanan `(y/n)` agar barang tidak terhapus tanpa sengaja sebelum memanggil `makeupDao.delete(id)`.                         

**5. Keluar**  
   Digunakan untuk menghentikan perulangan menu utama pada controller, menutup `EntityManager` dan `EntityManagerFactory`, serta mengakhiri sesi terminal.

---

# 5. Alur Program

## 5.1 Alur Sistem              
Sistem dimulai dari kelas `Main` yang menginisialisasi JPA `EntityManagerFactory` dari unit persistensi `makeupPU`. Controller mengarahkan alur aplikasi melalui menu utama interaktif, memproses perintah CRUD yang diteruskan ke `MakeupDao`, dan menyinkronkan data langsung ke database MySQL. Program selesai saat pengguna memasukkan angka 5.                           
```text
                          ┌──────────────┐
                          │    MULAI     │
                          └──────┬───────┘
                                 │
                                 ▼
                     Inisialisasi EntityManager
                     (Koneksi Database db_makeup)
                                 │
                                 ▼
                     Inisialisasi Controller & DAO
                                 │
            ┌────────────────────┴────────────────────┐
            │                                         │
            ▼                                         │
┌────────────────────┐                                │
│   Tampilkan Menu   │◄───────────────────────────┐   │
└─────────┬──────────┘                            │   │
          │                                       │   │
          ▼                                       │   │
┌────────────────────┐                            │   │
│   Input Pilihan    │                            │   │
│       Menu         │                            │   │
└─────────┬──────────┘                            │   │
          │                                       │   │
          ▼                                       │   │
  ┌───────────────┐                               │   │
  │ Pilihan Menu? │                               │   │
  └───────┬───────┘                               │   │
          │                                       │   │
  ┌───────┼─────────────┬─────────────┬───────────┤   │
  │       │             │             │           │   │
  ▼       ▼             ▼             ▼           ▼   │
 [1]     [2]           [3]           [4]         [5]  │
Tambah  Lihat         Ubah          Hapus       Keluar│
(Save) (FindAll)    (Update)       (Delete)     Tutup │
  │       │             │             │        Koneksi│
  ▼       ▼             ▼             ▼           ▼   │
Eksekusi Eksekusi    Eksekusi      Eksekusi    Selesai│
  ORM     ORM          ORM           ORM              │
  │       │             │             │               │
  └───────┴──────┬──────┴─────────────┘               │
                 │                                    │
                 ▼                                    │
          Kembali ke Menu ────────────────────────────┘
```

## 5.2 Alur Tambah Barang                                   
Fitur Tambah Data Barang digunakan untuk membuat entitas data barang baru, baik berupa produk kosmetik maupun perlengkapan alat.                  
Urutan prosesnya adalah:    
```text
Pilih Menu Tambah Data Barang
               │
               ▼
     Pilih Kategori Barang?
         ┌─────┴─────┐
         │           │
         ▼           ▼
   Produk Makeup  Perlengkapan Makeup
         │           │
         └─────┬─────┘
               │
               ▼
        Input ID Barang
  (Cek Kosong & Cek Duplikat di DB)
               │
               ▼
       Input Nama & Merk
               │
               ▼
    Input Harga (Harus > 0)
               │
               ▼
    Input Stok (Bulat & >= 1)
               │
               ▼
     Kategori yang Dipilih?
         ┌─────┴─────┐
         │           │
         ▼           ▼
   Produk Makeup  Perlengkapan Makeup
         │           │
         ▼           ▼
    Input Shade  Input Material
         │           │
         ▼           ▼
    Buat Objek   Buat Objek
   ProdukMakeup  PerlengkapanMakeup
         │           │
         └─────┬─────┘
               │
               ▼
       makeupDao.save(baru)
   (ORM: em.persist(makeup))
               │
               ▼
   Data Tersimpan ke MySQL
               │
               ▼
        Kembali ke Menu
```                                            
Setiap input teks dan angka memiliki validasi ketat dengan batas maksimal 3 kali percobaan salah:        
* ID tidak boleh kosong dan tidak boleh sama dengan ID yang sudah ada di list.       
* Harga harus berupa angka riil lebih dari 0.      
* Stok harus berupa bilangan bulat minimal 1 (divalidasi oleh setter agar tidak boleh 0 atau negatif).            

Pilihan kategori menentukan pembuatan objek subclass:     
* Produk Makeup menghasilkan objek ProdukMakeup dan meminta masukan data varian/shade.         
* Perlengkapan Makeup menghasilkan objek PerlengkapanMakeup dan meminta masukan data material bahan.

**Bukti output proses tambah produk makeup**                           
<img width="375" height="164" alt="image" src="https://github.com/user-attachments/assets/67fcf58f-1910-4a20-8cbc-29b94f0af0fd" />                                  


**Bukti output proses tambah perlengkapan makeup**                  
<img width="374" height="157" alt="image" src="https://github.com/user-attachments/assets/7a40c18a-f371-4cff-acf5-a2caac3860e2" />                                            


## 5.3 Tampilkan Semua Barang          
Fitur Tampilkan Semua Barang digunakan untuk mencetak seluruh barang inventaris yang tersimpan di dalam database.                  
<img width="222" height="425" alt="image" src="https://github.com/user-attachments/assets/4264a0c2-854a-4f3d-901b-ef81e7c44dea" />                                                    

Tampilan inventaris pada fitur Tampilkan Semua Barang, termasuk data dummy yang tersedia sejak awal di database.              
```text
Pilih Menu Tampilkan Semua Barang
               │
               ▼
     makeupDao.findAll()
(Eksekusi JPQL: SELECT m FROM Makeup m)
               │
               ▼
Hibernate membaca baris tabel 'makeup'
& memetakan otomatis ke List<Makeup>
               │
               ▼
Perulangan membaca tiap objek
               │
               ▼
Pemanggilan method polimorfik tampilkanData()
               │
               ▼
Rincian barang tampil pada terminal
               │
               ▼
        Kembali ke Menu
```

## 5.4 Alur Ubah Data Barang         
Fitur Ubah Data Barang digunakan untuk memperbarui rincian barang berdasarkan ID yang dimasukkan pengguna.                        
<img width="239" height="253" alt="image" src="https://github.com/user-attachments/assets/902fe9f4-b7cb-403d-ba05-d68ce04a43e9" />                                                     

```text
Pilih Menu Ubah Data Barang
               │
               ▼
    Input ID yang Dicari
               │
               ▼
     makeupDao.findById(id)
   (ORM: em.find(Makeup.class, id))
               │
               ▼
        Data Ditemukan?
         ┌─────┴─────┐
       Tidak        Ya
         │           │
         ▼           ▼
  Tampilkan Pesan  Tampilkan Detail Saat Ini
     Not Found     via Method Overloading
                     │
                     ▼
           Input Nilai Baru
         (Kosongkan jika skip)
                     │
                     ▼
         Update via Method Setter
                     │
                     ▼
           makeupDao.update(item)
          (ORM: em.merge(item))
                     │
                     ▼
          Kembali ke Menu
```

## 5.5 Alur Hapus Data Barang         
Fitur Hapus Data Barang digunakan untuk menghapus data inventaris dari database berdasarkan ID.    
<img width="278" height="75" alt="image" src="https://github.com/user-attachments/assets/4d34222c-ed09-4c4d-a14d-7219b38393ad" />                                         

Proses penghapusan data barang berdasarkan ID dengan konfirmasi pengguna.         
Sebelum data dihapus, sistem menampilkan nama barang dan meminta konfirmasi persetujuan `(y/n)` agar barang tidak terhapus tanpa sengaja.         
```text
Pilih Menu Hapus Data Barang
               │
               ▼
    Input ID yang Dicari
               │
               ▼
     makeupDao.findById(id)
         ┌─────┴─────┐
       Tidak        Ya
         │           │
         ▼           ▼
  Tampilkan Pesan  Tampilkan Konfirmasi
     Not Found     Hapus Barang (y/n)?
                     ┌─────┴─────┐
                     │           │
                     ▼           ▼
                    'y'         'n'
                     │           │
                     ▼           ▼
           makeupDao.delete(id) Batal
          (ORM: em.remove(item)) │
                     │           │
                     └─────┬─────┘
                           │
                           ▼
                    Kembali ke Menu
```

## 5.6 Alur Keluar Program         
Saat opsi 5 dipilih, perulangan `while (running)` dihentikan, sistem menampilkan pesan terima kasih, menutup referensi `em.close()` dan `emf.close()`, serta menyelesaikan eksekusi program.                              
<img width="235" height="39" alt="image" src="https://github.com/user-attachments/assets/5c27b4ae-68e1-46c8-afd9-2481c922ddfe" />                                                     

---

# 6. Penerapan Encapsulation dan Inheritance                              
## 6.1 Encapsulation

Encapsulation diwujudkan dengan menyembunyikan variabel menggunakan modifier private dan membukanya secara aman lewat perantara method getter serta setter.                             

### 1. Deklarasi Atribut Private                        
Pada superclass `Makeup`, seluruh atribut inti dideklarasikan menggunakan access modifier `private`:                    
```text
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
```

Hal yang sama diterapkan pada subclass:      
* Atribut shade pada ProdukMakeup dideklarasikan private String shade;.             
* Atribut material pada PerlengkapanMakeup dideklarasikan private String material;.                                                           
Deklarasi private menjamin data tidak dapat dibaca atau diubah secara langsung dari luar class tanpa melalui method resmi.

### 2. Method Getter dan Setter                   
Setiap atribut dilengkapi method getter dan setter untuk mengakses dan memanipulasi data entitas secara terkontrol.                                   

### 3. Method Setter dan Validasi Defensif                           
husus pada method `setStok()`, diterapkan sistem validasi defensif:                                      
```text
public void setStok(int stok) {
    if (stok <= 0) {
        System.out.println("[Peringatan] Stok minimal 1! Nilai diatur ke 1.");
        this.stok = 1;
    } else {
        this.stok = stok;
    }
}
```                          
Validasi ini memastikan data stok yang tersimpan ke dalam objek dan database tidak bernilai 0 atau negatif.                               

## 6.2 Inheritance             
Inheritance diterapkan dengan menjadikan class `Makeup` sebagai superclass abstrak yang mewariskan atribut serta perilaku umum kepada dua subclass yang lebih spesifik.                                    

### 1. Deklarasi Subclass Menggunakan Keyword `extends`                  
Subclass `ProdukMakeup` mewarisi superclass `Makeup`:                         
```text
public class ProdukMakeup extends Makeup {
    private String shade;
    // ...
}
```
    
Subclass `PerlengkapanMakeup` juga mewarisi superclass `Makeup`:                        
```text
public class PerlengkapanMakeup extends Makeup {
    private String material;
    // ...
}
```

### 2. Penggunaan `super()` pada Constructor Subclass                                           
Constructor pada masing-masing subclass memanggil constructor superclass menggunakan kata kunci `super(...)` untuk menginisialisasi atribut dasar (`id`, `nama`, `merk`, `harga`, `stok`):                      

* Pada `ProdukMakeup`:
```text
public ProdukMakeup(String id, String nama, String merk, double harga, int stok, String shade) {
    super(id, nama, merk, harga, stok);
    this.shade = shade;
}
```

* Pada `PerlengkapanMakeup`:
```text
public PerlengkapanMakeup(String id, String nama, String merk, double harga, int stok, String material) {
    super(id, nama, merk, harga, stok);
    this.material = material;
}
```
Dengan mekanisme inheritance ini, atribut dan method umum tidak perlu ditulis ulang pada masing-masing subclass, sehingga kode program modular dan memenuhi asas code reusability.                       

---

# 7. Penerapan Abstraction dan Polymorphism                                                                
## 7.1 Abstraction              
Abstraction diterapkan untuk menyembunyikan detail teknis yang kompleks dan hanya mengekspos fungsi-fungsi esensial melalui kontrak antarmuka yang terstandarisasi.                                

### 1. Interface `KelolaBarang`.
Pada package `model.entitas`, dibuat interface `KelolaBarang` sebagai kontrak baku:                  
```text
package model.entitas;

public interface KelolaBarang {
    void tampilkanData();
}
```

### 2. Abstract Class `Makeup`                                                         
Superclass `Makeup` dideklarasikan menggunakan kata kunci `abstract`:                                  
```text
public abstract class Makeup implements KelolaBarang {
    // ...
}
```
Penerapan ini mencegah instansiasi langsung objek umum (`new Makeup(...)` tidak dapat dilakukan) dan bertindak sebagai kerangka dasar (blueprint).                                

### 3. Abstract Method `getKategori()`                  
Di dalam class `Makeup`, terdapat method abstrak tanpa bodi:                              
```text
public abstract String getKategori();
```
Method ini mewajibkan setiap kelas turunan untuk mengimplementasikan kategorinya sendiri:                   
* Subclass `ProdukMakeup` mengembalikan `"Produk Makeup"`.
* Subclass `PerlengkapanMakeup` mengembalikan `"Perlengkapan Makeup"`.

## 7.2 Polymorphism                
Polymorphism diterapkan melalui dua bentuk utama: Method Overriding (Dynamic Polymorphism) dan Method Overloading (Static Polymorphism), serta pemanfaatan Koleksi Polimorfik.                             
### 1. Method Overriding (Dynamic Polymorphism)
Method `tampilkanData()` yang didefinisikan pada superclass `Makeup` di-override oleh masing-masing subclass untuk mencetak atribut spesifik:                      
* Pada `ProdukMakeup`: memanggil `super.tampilkanData()` lalu menambahkan informasi Shade / Varian.                               
* Pada `PerlengkapanMakeup`: memanggil `super.tampilkanData()` lalu menambahkan informasi Material / Bahan.

### 2. Method Overloading (Static Polymorphism)                        
Method overloading diterapkan pada superclass `Makeup` dengan menyediakan dua method bernama sama `(tampilkanData)` dengan signature berbeda:                            
* Versi tanpa parameter:
```text
@Override
public void tampilkanData() {
    System.out.println("ID Barang: " + id);
    System.out.println("Nama: " + nama);
    System.out.println("Merk: " + merk);
    System.out.println("Kategori: " + getKategori());
    System.out.printf("Harga: Rp%,.2f\n", harga);
    System.out.println("Stok: " + stok + " pcs");
}
```
* Versi dengan parameter `String header`:
```text
public void tampilkanData(String header) {
    System.out.println(header);
    tampilkanData();
}
```
Dipanggil pada `MakeupController` saat fitur Ubah Data Barang dijalankan:                                       
```text
item.tampilkanData("\nData saat ini:");
```

### 3. Koleksi Polimorfik dan Dynamic Binding                    
Hasil pembacaan basis data ditampung dalam koleksi polimorfik:                         
```text
List<Makeup> daftar = makeupDao.findAll();
```
Saat perulangan dijalankan:                                
```text
for (int i = 0; i < daftar.size(); i++) {
    System.out.println("Data ke-" + (i + 1));
    daftar.get(i).tampilkanData();
    System.out.println("--------------------------------------");
}
```                                             
Java secara dinamis (runtime polymorphism) mengenali tipe objek asli dari setiap indeks dan mengeksekusi implementasi `tampilkanData()` yang tepat.                           

---

# 8. Penerapan JDBC (Java Database Connectivity)                   
Program memanfaatkan konektor JDBC driver MySQL sebagai fondasi komunikasi langsung dengan basis data relasional.                         

### 1. Konfigurasi Kelas Koneksi Native (`config.Koneksi.java`)                       
Koneksi JDBC murni didefinisikan secara modular untuk menghubungkan aplikasi ke MySQL:                                 
```text
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_makeup";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Koneksi JDBC GAGAL: " + e.getMessage());
        }
        return conn;
    }
}
```

### 2. Pengujian Koneksi (`config.TesKoneksi.java`)                                    
Disediakan kelas pengujian untuk memverifikasi kesiapan komunikasi socket JDBC:                                  
```text
package config;

import java.sql.Connection;

public class TesKoneksi {
    public static void main(String[] args) {
        Connection conn = Koneksi.getConnection();
        if (conn != null) {
            System.out.println("Koneksi ke database 'db_makeup' BERHASIL!");
        }
    }
}
```
### 3. Dependensi JDBC Driver pada `pom.xml`             
Driver resmi MySQL didaftarkan pada konfigurasi Maven:                       
```text
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
</dependency>
```

---

# 9. Penjelasan Letak Penerapan Nilai Tambah (Framework ORM)                       
Sebagai pemenuhan Nilai Tambah, proyek ini mengimplementasikan framework ORM (Object-Relational Mapping) menggunakan standar Jakarta Persistence API (JPA) dengan engine Hibernate Core 6.5.2.Final.                                                            
Penerapan ORM mengeliminasi penulisan query SQL mentah dan proses konversi manual `ResultSet`, sehingga interaksi database berjalan sepenuhnya berbasis manipulasi objek Java murni.                                       

### 1. ependensi Framework ORM (`pom.xml`)            
Library Hibernate ditambahkan pada Maven:                           
```text
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.5.2.Final</version>
</dependency>
```

### 2. File Konfigurasi Persistensi (`src/main/resources/META-INF/persistence.xml`)                       
Konfigurasi dideklarasikan secara terpusat pada file XML unit persistensi bernama `makeupPU`:                 
```text
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="[https://jakarta.ee/xml/ns/persistence](https://jakarta.ee/xml/ns/persistence)" version="3.0">
    <persistence-unit name="makeupPU">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        
        <class>model.entitas.Makeup</class>
        <class>model.entitas.ProdukMakeup</class>
        <class>model.entitas.PerlengkapanMakeup</class>

        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/db_makeup"/>
            <property name="jakarta.persistence.jdbc.user" value="root"/>
            <property name="jakarta.persistence.jdbc.password" value=""/>

            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="false"/>
            <property name="hibernate.format_sql" value="false"/>
        </properties>
    </persistence-unit>
</persistence>
```

### 3. Pemetaan Anotasi JPA dan Polimorfisme Inheritance (`Single Table Strategy`)                            
Pola pewarisan kelas Java dihubungkan langsung ke satu tabel fisik MySQL `makeup` menggunakan anotasi JPA:                         
* `@Entity` & `@Table(name = "makeup")`: Memetakan kelas ke tabel basis data.                                                                              
* `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`: Menginstruksikan Hibernate untuk menyimpan superclass dan seluruh subclass ke dalam satu tabel bersama.                      
* `@DiscriminatorColumn(name = "kategori")`: Kolom penanda pembeda tipe kelas pada baris tabel database.                      
* `@DiscriminatorValue("PRODUK")`: Ditambahkan pada kelas ProdukMakeup.                             
* `@DiscriminatorValue("PERLENGKAPAN")`: Ditambahkan pada kelas PerlengkapanMakeup.

### 4. Operasi DAO Berbasis JPA `EntityManager` (`model/dao/MakeupDao.java`)
Seluruh operasi CRUD dijalankan secara otomatis oleh ORM tanpa menyusun string SQL manual:                                                    
* Create: Memanggil `em.persist(makeup)` di dalam transaksi. Hibernate otomatis meracik dan mengeksekusi query `INSERT INTO makeup ....`                                         
* Read All: Menjalankan query berbasis objek (JPQL) `em.createQuery("SELECT m FROM Makeup m", Makeup.class).getResultList()` yang langsung mengembalikan `List<Makeup>`.                                     
* Read by ID: Memanggil em.find`(Makeup.class, id)`.                                         
* Update: Memanggil `em.merge(makeup)` di dalam transaksi untuk mengeksekusi perintah sinkronisasi `UPDATE`.                                        
* Delete: Memanggil `em.remove(item)` di dalam transaksi untuk mengeksekusi perintah `DELETE`.                              
