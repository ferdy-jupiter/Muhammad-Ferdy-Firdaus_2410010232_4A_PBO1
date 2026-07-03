
package Bengkelferdy;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class BengkelServis {
    static Kendaraan[] antrian = new Kendaraan[10]; // Array, kapasitas 10 kendaraan
    static int jumlahAntrian = 0;
    static Scanner scan = new Scanner(System.in);
    static NumberFormat rupiah = NumberFormat.getInstance(new Locale("in", "ID"));

    public static void main(String[] args) {
        int pilihan;

        // Perulangan (do-while) untuk menu utama
        do {
            tampilkanMenu();
            pilihan = bacaInt();

            // Seleksi menggunakan switch-case
            switch (pilihan) {
                case 1:
                    tambahKendaraan();
                    break;
                case 2:
                    tampilkanSemua();
                    break;
                case 3:
                    cariKendaraan();
                    break;
                case 4:
                    updateStatus();
                    break;
                case 5:
                    hapusKendaraan();
                    break;
                case 6:
                    tampilkanTotalPendapatan();
                    break;
                case 7:
                    urutkanBerdasarkanBiaya();
                    break;
                case 8:
                    System.out.println("\nTerima kasih sudah mampir ke bengkel kami!");
                    break;
                default:
                    System.out.println("Menu tidak tersedia, coba lagi.");
            }
        } while (pilihan != 8);

        scan.close();
    }

    // ---------- Bagian tampilan ----------

    static void tampilkanMenu() {
        garisGanda();
        System.out.println("      BENGKEL MOBIL & MOTOR FERDY JAYA MOTOR");
        garisGanda();
        System.out.println("1. Tambah Kendaraan ke Antrean Servis");
        System.out.println("2. Tampilkan Semua Antrean Servis");
        System.out.println("3. Cari Kendaraan Berdasarkan Plat Nomor");
        System.out.println("4. Update Status Servis Kendaraan");
        System.out.println("5. Hapus Kendaraan dari Antrean");
        System.out.println("6. Tampilkan Total Pendapatan");
        System.out.println("7. Urutkan Antrean Berdasarkan Total Biaya");
        System.out.println("8. Keluar");
        garis();
        System.out.print("Pilih menu: ");
    }

    static void garis() {
        System.out.println("-".repeat(70));
    }

    static void garisGanda() {
        System.out.println("=".repeat(70));
    }

    static String formatRupiah(double nilai) {
        return "Rp" + rupiah.format(nilai);
    }

    static void pesanSukses(String pesan) {
        System.out.println("[OK] " + pesan);
    }

    static void pesanGagal(String pesan) {
        System.out.println("[GAGAL] " + pesan);
    }

    // ---------- IO Sederhana + Error Handling ----------

    static int bacaInt() {
        int nilai = -1;
        boolean valid = false;
        while (!valid) {
            try {
                nilai = Integer.parseInt(scan.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa angka! Ulangi: ");
            }
        }
        return nilai;
    }

    static String bacaTeks(String label) {
        String teks;
        while (true) {
            System.out.print(label);
            teks = scan.nextLine().trim();
            if (!teks.isEmpty()) {
                break;
            }
            System.out.println("Input tidak boleh kosong!");
        }
        return teks;
    }

    // ---------- Fitur-fitur utama ----------

    static void tambahKendaraan() {
        garisGanda();
        System.out.println("TAMBAH KENDARAAN BARU");
        garis();
        try {
            if (jumlahAntrian >= antrian.length) {
                throw new Exception("Antrean servis penuh! Maksimal " + antrian.length + " kendaraan.");
            }

            String plat = bacaTeks("Plat Nomor  : ");

            for (int i = 0; i < jumlahAntrian; i++) {
                if (antrian[i].getPlatNomor().equalsIgnoreCase(plat)) {
                    pesanGagal("Plat nomor sudah ada dalam antrean, data batal ditambahkan.");
                    return;
                }
            }

            String merk = bacaTeks("Merk        : ");
            String pemilik = bacaTeks("Nama Pemilik: ");
            String telepon = bacaTeks("No. Telepon : ");

            System.out.print("Biaya Jasa Dasar: ");
            double biaya = Double.parseDouble(scan.nextLine());
            if (biaya < 0) {
                throw new IllegalArgumentException("Biaya jasa dasar tidak boleh negatif.");
            }

            System.out.println("Jenis Kendaraan: 1. Mobil  2. Motor");
            int jenis = bacaInt();

            String jenisKendaraan;
            String keterangan;
            if (jenis == 1) {
                jenisKendaraan = "Mobil";
                keterangan = bacaTeks("Jenis Oli (biasa/sintetik): ");
            } else if (jenis == 2) {
                jenisKendaraan = "Motor";
                keterangan = bacaTeks("Jenis Servis (ringan/berat): ");
            } else {
                pesanGagal("Jenis kendaraan tidak valid, data batal ditambahkan.");
                return;
            }

            antrian[jumlahAntrian] = new Mobil(plat, merk, pemilik, telepon, biaya, jenisKendaraan, keterangan);
            jumlahAntrian++;
            garis();
            pesanSukses("Kendaraan berhasil ditambahkan ke antrean!");

        } catch (NumberFormatException e) {
            pesanGagal("Biaya jasa dasar harus berupa angka. Data batal ditambahkan.");
        } catch (IllegalArgumentException e) {
            pesanGagal(e.getMessage());
        } catch (Exception e) {
            pesanGagal(e.getMessage());
        }
    }

    // Tampilan tabel rapi memanfaatkan Perulangan + Polymorphism
    static void tampilkanSemua() {
        garisGanda();
        System.out.println("DAFTAR ANTREAN SERVIS");
        garisGanda();

        if (jumlahAntrian == 0) {
            System.out.println("Belum ada kendaraan dalam antrean.");
            garisGanda();
            return;
        }

        System.out.printf("%-4s%-11s%-10s%-14s%-7s%-10s%-14s%n",
                "No", "Plat", "Merk", "Pemilik", "Jenis", "Status", "Total Biaya");
        garis();

        for (int i = 0; i < jumlahAntrian; i++) {
            Mobil k = (Mobil) antrian[i]; // downcasting untuk akses jenis & status
            System.out.printf("%-4s%-11s%-10s%-14s%-7s%-10s%-14s%n",
                    (i + 1) + ".",
                    k.getPlatNomor(),
                    k.getMerk(),
                    k.getPemilik(),
                    k.getJenisKendaraan(),
                    k.getStatus(),
                    formatRupiah(k.hitungTotalBiaya()));
        }
        garisGanda();
    }

    static void cariKendaraan() {
        garisGanda();
        String cari = bacaTeks("Masukkan plat nomor yang dicari: ");
        boolean ketemu = false;

        for (int i = 0; i < jumlahAntrian; i++) {
            if (antrian[i].getPlatNomor().equalsIgnoreCase(cari)) {
                garis();
                antrian[i].tampilkanData();
                ketemu = true;
                break;
            }
        }
        if (!ketemu) {
            pesanGagal("Kendaraan dengan plat nomor tersebut tidak ditemukan.");
        }
        garisGanda();
    }

    static void updateStatus() {
        garisGanda();
        String cari = bacaTeks("Masukkan plat nomor yang mau diupdate: ");
        boolean ketemu = false;

        for (int i = 0; i < jumlahAntrian; i++) {
            if (antrian[i].getPlatNomor().equalsIgnoreCase(cari)) {
                Mobil kendaraan = (Mobil) antrian[i];
                System.out.println("Status sekarang: " + kendaraan.getStatus());
                System.out.println("Pilih status baru: 1. Menunggu  2. Diproses  3. Selesai");
                int pilih = bacaInt();

                switch (pilih) {
                    case 1:
                        kendaraan.setStatus("Menunggu");
                        break;
                    case 2:
                        kendaraan.setStatus("Diproses");
                        break;
                    case 3:
                        kendaraan.setStatus("Selesai");
                        break;
                    default:
                        pesanGagal("Pilihan tidak valid, status tidak diubah.");
                        garisGanda();
                        return;
                }
                pesanSukses("Status berhasil diupdate menjadi: " + kendaraan.getStatus());
                ketemu = true;
                break;
            }
        }
        if (!ketemu) {
            pesanGagal("Kendaraan dengan plat nomor tersebut tidak ditemukan.");
        }
        garisGanda();
    }

    static void hapusKendaraan() {
        garisGanda();
        if (jumlahAntrian == 0) {
            System.out.println("Antrean masih kosong, tidak ada yang bisa dihapus.");
            garisGanda();
            return;
        }

        String cari = bacaTeks("Masukkan plat nomor yang mau dihapus: ");
        int indexDitemukan = -1;

        for (int i = 0; i < jumlahAntrian; i++) {
            if (antrian[i].getPlatNomor().equalsIgnoreCase(cari)) {
                indexDitemukan = i;
                break;
            }
        }

        try {
            if (indexDitemukan == -1) {
                throw new Exception("Kendaraan dengan plat nomor tersebut tidak ditemukan.");
            }
            for (int i = indexDitemukan; i < jumlahAntrian - 1; i++) {
                antrian[i] = antrian[i + 1];
            }
            antrian[jumlahAntrian - 1] = null;
            jumlahAntrian--;
            pesanSukses("Kendaraan berhasil dihapus dari antrean.");
        } catch (Exception e) {
            pesanGagal(e.getMessage());
        }
        garisGanda();
    }

    static void tampilkanTotalPendapatan() {
        garisGanda();
        System.out.println("RINGKASAN PENDAPATAN");
        garis();
        if (jumlahAntrian == 0) {
            System.out.println("Belum ada kendaraan dalam antrean.");
            garisGanda();
            return;
        }
        double total = 0;
        for (int i = 0; i < jumlahAntrian; i++) {
            total += antrian[i].hitungTotalBiaya();
        }
        System.out.println("Jumlah kendaraan   : " + jumlahAntrian);
        System.out.println("Total pendapatan   : " + formatRupiah(total));
        garisGanda();
    }

    static void urutkanBerdasarkanBiaya() {
        if (jumlahAntrian < 2) {
            System.out.println("Data belum cukup untuk diurutkan.");
            return;
        }
        for (int i = 0; i < jumlahAntrian - 1; i++) {
            for (int j = 0; j < jumlahAntrian - 1 - i; j++) {
                if (antrian[j].hitungTotalBiaya() > antrian[j + 1].hitungTotalBiaya()) {
                    Kendaraan sementara = antrian[j];
                    antrian[j] = antrian[j + 1];
                    antrian[j + 1] = sementara;
                }
            }
        }
        pesanSukses("Antrean berhasil diurutkan dari biaya terkecil ke terbesar.");
        tampilkanSemua();
    }
}
