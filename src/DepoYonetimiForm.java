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
        depo = new Depo();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Depo Yönetimi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel urunKoduLabel = new JLabel("Ürün Kodu:");
        urunKoduField = new JTextField(10);
        formPanel.add(urunKoduLabel);
        formPanel.add(urunKoduField);

        JLabel urunAdiLabel = new JLabel("Ürün Adı:");
        urunAdiField = new JTextField(10);
        formPanel.add(urunAdiLabel);
        formPanel.add(urunAdiField);

        JLabel urunKategoriLabel = new JLabel("Kategori:");
        urunKategoriField = new JTextField(10);
        formPanel.add(urunKategoriLabel);
        formPanel.add(urunKategoriField);

        JLabel stokMiktariLabel = new JLabel("Stok Miktarı:");
        stokMiktariField = new JTextField(10);
        formPanel.add(stokMiktariLabel);
        formPanel.add(stokMiktariField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton urunEkleButton = new JButton("Ürün Ekle");
        urunEkleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String urunKodu = urunKoduField.getText();
                String urunAdi = urunAdiField.getText();
                String urunKategori = urunKategoriField.getText();
                int stokMiktari = Integer.parseInt(stokMiktariField.getText());

                Urun urun = new Urun(urunKodu, urunAdi, urunKategori, stokMiktari);
                depo.urunEkle(urun);

                JOptionPane.showMessageDialog(DepoYonetimiForm.this, "Ürün eklendi!");
            }
        });
        buttonPanel.add(urunEkleButton);

        JButton stokGuncelleButton = new JButton("Stok Güncelle");
        stokGuncelleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String urunKodu = urunKoduField.getText();
                int adet = Integer.parseInt(stokMiktariField.getText());

                depo.stokGuncelle(urunKodu, adet);

                JOptionPane.showMessageDialog(DepoYonetimiForm.this, "Stok güncellendi!");
            }
        });
        buttonPanel.add(stokGuncelleButton);

        JButton stoklariGosterButton = new JButton("Stokları Göster");
        stoklariGosterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder stokBilgileri = new StringBuilder();
                HashMap<String, Urun> urunler = depo.getUrunler();

                for (Urun urun : urunler.values()) {
                    stokBilgileri.append("Ürün Kodu: ").append(urun.getUrunKodu())
                            .append(", Ürün Adı: ").append(urun.getAd())
                            .append(", Kategori: ").append(urun.getKategori())
                            .append(", Stok Miktarı: ").append(urun.getStokMiktari())
                            .append("\n");
                }

                JOptionPane.showMessageDialog(DepoYonetimiForm.this, stokBilgileri.toString(), "Stoklar", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel.add(stoklariGosterButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DepoYonetimiForm().setVisible(true);
            }
        });
    }
}
