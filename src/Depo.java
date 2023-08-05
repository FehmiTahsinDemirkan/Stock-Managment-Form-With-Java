import java.util.HashMap;

public class Depo {
    private HashMap<String, Urun> urunler;

    public Depo() {
        urunler = new HashMap<>();
    }

    public void urunEkle(Urun urun) {
        urunler.put(urun.getUrunKodu(), urun);
    }

    public void stokGuncelle(String urunKodu, int adet) {
        Urun urun = urunBul(urunKodu);
        if (urun != null) {
            urun.stokGuncelle(adet);
        }
    }

    public Urun urunBul(String urunKodu) {
        return urunler.get(urunKodu);
    }

    public HashMap<String, Urun> getUrunler() {
        return urunler;
    }
}
