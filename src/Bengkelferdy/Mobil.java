
package Bengkelferdy;


public class Mobil extends Kendaraan {
    private String jenisKendaraan; // "Mobil" atau "Motor"
    private String keterangan;     // jenis oli (mobil) / jenis servis (motor)
    private String status;         // "Menunggu", "Diproses", "Selesai"

    public Mobil(String platNomor, String merk, String pemilik, String noTelepon, double biayaJasaDasar,
                 String jenisKendaraan, String keterangan) {
        super(platNomor, merk, pemilik, noTelepon, biayaJasaDasar);
        this.jenisKendaraan = jenisKendaraan;
        this.keterangan = keterangan;
        this.status = "Menunggu"; // status awal saat kendaraan baru masuk antrean
    }

    // ---------- Mutator ----------
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ---------- Accessor ----------
    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getStatus() {
        return status;
    }

    // Polymorphism (Overriding): isi method beda dari class induk
    @Override
    public double hitungTotalBiaya() {
        double tambahan;
        // Seleksi
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
        return biayaJasaDasar + tambahan;
    }

    // Polymorphism (Overloading): nama sama, parameter beda -> untuk kendaraan langganan dapat diskon
    public double hitungTotalBiaya(double diskon) {
        double total = hitungTotalBiaya() - diskon;
        return total < 0 ? 0 : total; // jaga-jaga biar tidak minus
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("Jenis        : " + jenisKendaraan);
        System.out.println("Keterangan   : " + keterangan);
        System.out.println("Status       : " + status);
        System.out.println("Total Biaya  : Rp" + hitungTotalBiaya());
    }
}
