
package Bengkelferdy;

public class Kendaraan {
    // Atribut (private = Encapsulation)
    private String platNomor;
    private String merk;
    private String pemilik;
    private String noTelepon;
    protected double biayaJasaDasar;

    // Constructor
    public Kendaraan(String platNomor, String merk, String pemilik, String noTelepon, double biayaJasaDasar) {
        this.platNomor = platNomor;
        this.merk = merk;
        this.pemilik = pemilik;
        this.noTelepon = noTelepon;
        this.biayaJasaDasar = biayaJasaDasar;
    }

    // ---------- Mutator (setter) ----------
    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public void setBiayaJasaDasar(double biayaJasaDasar) {
        this.biayaJasaDasar = biayaJasaDasar;
    }

    // ---------- Accessor (getter) ----------
    public String getPlatNomor() {
        return platNomor;
    }

    public String getMerk() {
        return merk;
    }

    public String getPemilik() {
        return pemilik;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public double getBiayaJasaDasar() {
        return biayaJasaDasar;
    }

    // Method dasar, nanti di-override di class turunan (dasar dari Polymorphism)
    public double hitungTotalBiaya() {
        return biayaJasaDasar;
    }

    public void tampilkanData() {
        System.out.println("Plat Nomor   : " + platNomor);
        System.out.println("Merk         : " + merk);
        System.out.println("Pemilik      : " + pemilik);
        System.out.println("No. Telepon  : " + noTelepon);
    }
}
