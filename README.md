# Proyek Akhir Pemrograman Berbasis Objek 1

Proyek ini adalah contoh sederhana aplikasi sistem antrean servis bengkel mobil dan motor menggunakan Java sebagai tugas akhir dari mata kuliah pemrograman berbasis objek 1.

## Deskripsi

Aplikasi ini menerima input berupa data kendaraan (plat nomor, merk, nama pemilik, nomor telepon, biaya jasa dasar, jenis kendaraan, dan keterangan servis) yang masuk ke antrean bengkel, lalu memberikan output berupa total biaya servis yang dihitung otomatis, status pengerjaan, hingga total pendapatan dari seluruh antrean.

Aplikasi ini mengimplementasikan beberapa konsep penting dalam pemrograman berorientasi objek (OOP) seperti Class, Object, Atribut, Method Constructor, Method Mutator, Method Accessor, Encapsulation, Inheritance, Overloading, Overriding, Seleksi, Perulangan, IO Sederhana, Array, dan Error Handling.

## Penjelasan Kode

Berikut adalah bagian kode yang relevan dengan konsep OOP yang dijelaskan:

1. **Class** adalah template atau blueprint dari object. Pada kode ini, `Kendaraan`, `Mobil`, dan `BengkelServis` adalah contoh dari class.

```bash
public class Kendaraan {
    ...
}

public class Mobil extends Kendaraan {
    ...
}

public class BengkelServis {
    ...
}
```

2. **Object** adalah instance dari class. Pada kode ini, `new Mobil(plat, merk, pemilik, telepon, biaya, jenisKendaraan, keterangan)` adalah contoh pembuatan object.

```bash
antrian[jumlahAntrian] = new Mobil(plat, merk, pemilik, telepon, biaya, jenisKendaraan, keterangan);
```

3. **Atribut** adalah variabel yang ada dalam class. Pada kode ini, `platNomor`, `merk`, `pemilik`, dan `biayaJasaDasar` adalah contoh atribut.

```bash
private String platNomor;
private String merk;
private String pemilik;
private String noTelepon;
protected double biayaJasaDasar;
```

4. **Constructor** adalah method yang pertama kali dijalankan pada saat pembuatan object. Pada kode ini, constructor ada di dalam class `Kendaraan` dan `Mobil`.

```bash
public Kendaraan(String platNomor, String merk, String pemilik, String noTelepon, double biayaJasaDasar) {
    this.platNomor = platNomor;
    this.merk = merk;
    this.pemilik = pemilik;
    this.noTelepon = noTelepon;
    this.biayaJasaDasar = biayaJasaDasar;
}

public Mobil(String platNomor, String merk, String pemilik, String noTelepon, double biayaJasaDasar,
             String jenisKendaraan, String keterangan) {
    super(platNomor, merk, pemilik, noTelepon, biayaJasaDasar);
    this.jenisKendaraan = jenisKendaraan;
    this.keterangan = keterangan;
    this.status = "Menunggu";
}
```

5. **Mutator** atau setter digunakan untuk mengubah nilai dari suatu atribut. Pada kode ini, `setPemilik`, `setNoTelepon`, dan `setStatus` adalah contoh method mutator.

```bash
public void setPemilik(String pemilik) {
    this.pemilik = pemilik;
}

public void setStatus(String status) {
    this.status = status;
}
```

6. **Accessor** atau getter digunakan untuk mengambil nilai dari suatu atribut. Pada kode ini, `getPlatNomor`, `getMerk`, `getPemilik`, `getJenisKendaraan`, dan `getStatus` adalah contoh method accessor.

```bash
public String getPlatNomor() {
    return platNomor;
}

public String getStatus() {
    return status;
}
```

7. **Encapsulation** adalah konsep menyembunyikan data dengan membuat atribut menjadi private dan hanya bisa diakses melalui method. Pada kode ini, seluruh atribut di `Kendaraan` dan `Mobil` dienkapsulasi dan hanya bisa diakses melalui method getter dan setter.

```bash
private String platNomor;
private String jenisKendaraan;
private String status;
```

8. **Inheritance** adalah konsep di mana sebuah class bisa mewarisi property dan method dari class lain. Pada kode ini, `Mobil` mewarisi `Kendaraan` dengan sintaks `extends`.

```bash
public class Mobil extends Kendaraan {
    ...
}
```

9. **Polymorphism** adalah konsep di mana sebuah nama dapat digunakan untuk merujuk ke beberapa tipe atau bentuk objek berbeda. Ini memungkinkan metode-metode dengan nama yang sama untuk berperilaku berbeda tergantung pada tipe objek yang mereka manipulasi, polymorphism bisa berbentuk Overloading ataupun Overriding. Pada kode ini, method `hitungTotalBiaya(double diskon)` di `Mobil` merupakan overloading dari method `hitungTotalBiaya()`, dan `hitungTotalBiaya()` di `Mobil` merupakan override dari method `hitungTotalBiaya()` di `Kendaraan`.

```bash
// Overriding (isi beda dari class induk Kendaraan)
@Override
public double hitungTotalBiaya() {
    ...
}

// Overloading (nama sama, parameter beda)
public double hitungTotalBiaya(double diskon) {
    double total = hitungTotalBiaya() - diskon;
    return total < 0 ? 0 : total;
}
```

10. **Seleksi** adalah statement kontrol yang digunakan untuk membuat keputusan berdasarkan kondisi. Pada kode ini, digunakan seleksi `if else` untuk memilih jenis kendaraan dan seleksi `switch` untuk menu utama serta perhitungan biaya tambahan.

```bash
if (jenisKendaraan.equalsIgnoreCase("Mobil")) {
    switch (keterangan.toLowerCase()) {
        case "sintetik":
            tambahan = 350000;
            break;
        default:
            tambahan = 150000;
    }
} else {
    switch (keterangan.toLowerCase()) {
        case "berat":
            tambahan = 200000;
            break;
        case "ringan":
            tambahan = 50000;
            break;
        default:
            tambahan = 0;
    }
}
```

11. **Perulangan** adalah statement kontrol yang digunakan untuk menjalankan blok kode berulang kali. Pada kode ini, digunakan loop `do-while` untuk menu utama dan loop `for` untuk menelusuri array antrean.

```bash
do {
    tampilkanMenu();
    pilihan = bacaInt();
    switch (pilihan) {
        ...
    }
} while (pilihan != 8);

for (int i = 0; i < jumlahAntrian; i++) {
    antrian[i].tampilkanData();
}
```

12. **Input Output Sederhana** digunakan untuk menerima input dari user dan menampilkan output ke user. Pada kode ini, digunakan class `Scanner` untuk menerima input dan `System.out.printf` untuk menampilkan output dalam bentuk tabel.

```bash
Scanner scan = new Scanner(System.in);
System.out.print("Plat Nomor  : ");
String plat = scan.nextLine();

System.out.printf("%-4s%-11s%-10s%-14s%-7s%-10s%-14s%n",
        "No", "Plat", "Merk", "Pemilik", "Jenis", "Status", "Total Biaya");
```

13. **Array** adalah struktur data yang digunakan untuk menyimpan beberapa nilai dalam satu variabel. Pada kode ini, `Kendaraan[] antrian = new Kendaraan[10];` adalah contoh penggunaan array.

```bash
Kendaraan[] antrian = new Kendaraan[10];
```

14. **Error Handling** digunakan untuk menangani error yang mungkin terjadi saat runtime. Pada kode ini, digunakan `try catch` untuk menangani error input yang salah dan kondisi antrean penuh.

```bash
try {
    if (jumlahAntrian >= antrian.length) {
        throw new Exception("Antrean servis penuh! Maksimal " + antrian.length + " kendaraan.");
    }
    ...
} catch (NumberFormatException e) {
    System.out.println("Biaya jasa dasar harus berupa angka.");
} catch (Exception e) {
    System.out.println("Gagal menambah: " + e.getMessage());
}
```

## Usulan nilai

| No  | Materi         |  Nilai  |
| :-: | -------------- | :-----: |
|  1  | Class          |    5    |
|  2  | Object         |    5    |
|  3  | Atribut        |    5    |
|  4  | Constructor    |    5    |
|  5  | Mutator        |    5    |
|  6  | Accessor       |    5    |
|  7  | Encapsulation  |    5    |
|  8  | Inheritance    |    5    |
|  9  | Polymorphism   |   10    |
| 10  | Seleksi        |    5    |
| 11  | Perulangan     |    5    |
| 12  | IO Sederhana   |   10    |
| 13  | Array          |   15    |
| 14  | Error Handling |   15    |
|     | **TOTAL**      | **100** |

## Pembuat

Nama: Muhammad Ferdy Firdaus
NPM: 2410010232
