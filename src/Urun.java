import java.util.ArrayList;

public class Urun {
    private String urunKodu;
    private String ad;
    private String kategori;
    private int stokMiktari;
    private ArrayList<String> stokHareketleri;

    public Urun(String urunKodu, String ad, String kategori, int stokMiktari) {
        this.urunKodu = urunKodu;
        this.ad = ad;
        this.kategori = kategori;
        this.stokMiktari = stokMiktari;
        stokHareketleri = new ArrayList<>();
    }

    public String getUrunKodu() {
        return urunKodu;
    }

    public String getAd() {
        return ad;
    }

    public String getKategori() {
        return kategori;
    }

    public int getStokMiktari() {
        return stokMiktari;
    }

    public ArrayList<String> getStokHareketleri() {
        return stokHareketleri;
    }

    public void stokGuncelle(int adet) {
        stokMiktari += adet;
    }

    public void stokHareketEkle(int adet, String hareket) {
        stokGuncelle(adet);
        stokHareketleri.add(hareket);
    }
}
