import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DepoYonetimiForm extends JFrame {
    private Depo depo;

    private JTextField urunKoduField;
    private JTextField urunAdiField;
    private JTextField urunKategoriField;
    private JTextField stokMiktariField;

    public DepoYonetimiForm() {
        // Depo nesnesi oluşturuluyor.
        depo = new Depo();
        // Arayüzü oluşturmak için metot çağırılıyor.
        initializeUI();
    }

    private void initializeUI() {
        // Formun başlığı ayarlanıyor.
        setTitle("Depo Yönetimi");
        // Formun boyutu ayarlanıyor.
        setSize(400, 300);
        // Formun kapatılma işlemi belirleniyor.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Formun ekran ortasında açılması sağlanıyor.
        setLocationRelativeTo(null);

        // Ana panel oluşturuluyor ve boşluklar ayarlanıyor.
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form elemanları için panel oluşturuluyor ve düzeni ayarlanıyor.
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));

        // Ürün Kodu etiketi ve metin alanı oluşturuluyor.
        JLabel urunKoduLabel = new JLabel("Ürün Kodu:");
        urunKoduField = new JTextField(10);
        formPanel.add(urunKoduLabel);
        formPanel.add(urunKoduField);

        // Ürün Adı etiketi ve metin alanı oluşturuluyor.
        JLabel urunAdiLabel = new JLabel("Ürün Adı:");
        urunAdiField = new JTextField(10);
        formPanel.add(urunAdiLabel);
        formPanel.add(urunAdiField);

        // Ürün Kategorisi etiketi ve metin alanı oluşturuluyor.
        JLabel urunKategoriLabel = new JLabel("Kategori:");
        urunKategoriField = new JTextField(10);
        formPanel.add(urunKategoriLabel);
        formPanel.add(urunKategoriField);

        // Stok Miktarı etiketi ve metin alanı oluşturuluyor.
        JLabel stokMiktariLabel = new JLabel("Stok Miktarı:");
        stokMiktariField = new JTextField(10);
        formPanel.add(stokMiktariLabel);
        formPanel.add(stokMiktariField);

        // Form elemanlarını ana panele ekleniyor.
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Butonlar için panel oluşturuluyor ve düzeni ayarlanıyor.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // "Ürün Ekle" butonu oluşturuluyor ve ekleme işlemini gerçekleştiren ActionListener ekleniyor.
        JButton urunEkleButton = new JButton("Ürün Ekle");
        urunEkleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Metin alanlarından ürün bilgileri alınıyor.
                String urunKodu = urunKoduField.getText();
                String urunAdi = urunAdiField.getText();
                String urunKategori = urunKategoriField.getText();
                int stokMiktari = Integer.parseInt(stokMiktariField.getText());

                // Yeni bir ürün nesnesi oluşturuluyor ve depoya ekleniyor.
                Urun urun = new Urun(urunKodu, urunAdi, urunKategori, stokMiktari);
                depo.urunEkle(urun);

                // Kullanıcıya bilgi mesajı gösteriliyor.
                JOptionPane.showMessageDialog(DepoYonetimiForm.this, "Ürün eklendi!");
            }
        });
        // "Ürün Ekle" butonu panele ekleniyor.
        buttonPanel.add(urunEkleButton);

        // "Stok Güncelle" butonu oluşturuluyor ve güncelleme işlemini gerçekleştiren ActionListener ekleniyor.
        JButton stokGuncelleButton = new JButton("Stok Güncelle");
        stokGuncelleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Metin alanlarından ürün kodu ve adet bilgisi alınıyor.
                String urunKodu = urunKoduField.getText();
                int adet = Integer.parseInt(stokMiktariField.getText());

                // Depo üzerinden stok güncelleme işlemi yapılıyor.
                depo.stokGuncelle(urunKodu, adet);

                // Kullanıcıya bilgi mesajı gösteriliyor.
                JOptionPane.showMessageDialog(DepoYonetimiForm.this, "Stok güncellendi!");
            }
        });
        // "Stok Güncelle" butonu panele ekleniyor.
        buttonPanel.add(stokGuncelleButton);

        // "Stokları Göster" butonu oluşturuluyor ve stokları gösteren ActionListener ekleniyor.
        JButton stoklariGosterButton = new JButton("Stokları Göster");
        stoklariGosterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Depo içindeki ürünlerin stok bilgileri alınıyor ve ekrana yazdırılıyor.
                StringBuilder stokBilgileri = new StringBuilder();
                HashMap<String, Urun> urunler = depo.getUrunler();

                for (Urun urun : urunler.values()) {
                    stokBilgileri.append("Ürün Kodu: ").append(urun.getUrunKodu())
                            .append(", Ürün Adı: ").append(urun.getAd())
                            .append(", Kategori: ").append(urun.getKategori())
                            .append(", Stok Miktarı: ").append(urun.getStokMiktari())
                            .append("\n");
                }

                // Stok bilgileri kullanıcıya bir ileti kutusuyla gösteriliyor.
                JOptionPane.showMessageDialog(DepoYonetimiForm.this, stokBilgileri.toString(), "Stoklar", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // "Stokları Göster" butonu panele ekleniyor.
        buttonPanel.add(stoklariGosterButton);

        // Butonlar paneli ana panele ekleniyor.
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ana panel form için içeriğe ekleniyor.
        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        // Swing arayüzü EDT üzerinde çalıştırmak için invokeLater kullanılıyor.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // DepoYonetimiForm sınıfından bir nesne oluşturularak form görüntüleniyor.
                new DepoYonetimiForm().setVisible(true);
            }
        });
    }
}
