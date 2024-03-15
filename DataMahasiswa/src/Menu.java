// Saya Nabilla Assyfa Ramadhani [2205297]
// mengerjakan Latihan
// dalam mata kuliah Desain dan Pemograman Berorientasi Objek
// untuk keberkahanNya maka saya tidak melakukan kecurangan
// seperti yang telah dispesifikasikan.
// Aamiin


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480,560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.cyan);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JComboBox JenjangcomboBox;
    private JComboBox StatuscomboBox;
    private JTextField UniversityField;
    private JLabel nameUniversity;
    private JLabel StatusMahasiswa;
    private JLabel JenjangSekolah;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // isi listMahasiswa
        populateList();

        // isi tabel mahasiswa
        mahasiswaTable.setModel((setTable()));

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // Mengisi combo box jenjang perkuliahan
        String[] jenjangKuliahData = {"", "D1", "D2", "D3", "D4", "S1", "S2", "S3"};
        JenjangcomboBox.setModel(new DefaultComboBoxModel(jenjangKuliahData));

        // Mengisi combo box untuk status awal mahasiswa
        String[] statusMahasiswa ={"", "Peserta didik baru", "Pindahan Alih Bentuk", "Peserta didik Tamu"};
        StatuscomboBox.setModel(new DefaultComboBoxModel(statusMahasiswa));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1){
                    insertData();
                }else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0){
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedPerguruanTinggi = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();
                String selectedJenjang = mahasiswaTable.getModel().getValueAt(selectedIndex, 5).toString();
                String selectedStatus = mahasiswaTable.getModel().getValueAt(selectedIndex, 6).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                UniversityField.setText(selectedPerguruanTinggi);
                JenjangcomboBox.setSelectedItem(selectedJenjang);
                StatuscomboBox.setSelectedItem(selectedStatus);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object [] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Perguruan Tinggi", "Jenjang Pendidikan", "Status Awal Mahasiswa"};


        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null,column);


        // isi tabel dengan listMahasiswa
        for (int i = 0; i < listMahasiswa.size(); i++){
            Object[] row = new Object[7];
            row [0] = i + 1;
            row [1] = listMahasiswa.get(i).getNim();
            row [2] = listMahasiswa.get(i).getNama();
            row [3] = listMahasiswa.get(i).getJenisKelamin();
            row [4] = listMahasiswa.get(i).getPT();
            row [5] = listMahasiswa.get(i).getJenjang();
            row [6] = listMahasiswa.get(i).getStatus();

            temp.addRow(row);
        }
        return temp;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String Univ = UniversityField.getText();
        String jenjang = JenjangcomboBox.getSelectedItem().toString();
        String status = StatuscomboBox.getSelectedItem().toString();

        // tambahkan data ke dalam list
        listMahasiswa.add(new Mahasiswa(nim, nama, jenisKelamin, Univ, jenjang, status));

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert data berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");

    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String Univ = UniversityField.getText();
        String jenjang = JenjangcomboBox.getSelectedItem().toString();
        String status = StatuscomboBox.getSelectedItem().toString();

        // ubah data mahasiswa di list
        listMahasiswa.get(selectedIndex).setNim(nim);
        listMahasiswa.get(selectedIndex).setNama(nama);
        listMahasiswa.get(selectedIndex).setJenisKelamin(jenisKelamin);
        listMahasiswa.get(selectedIndex).setPT(Univ);
        listMahasiswa.get(selectedIndex).setJenjang(jenjang);
        listMahasiswa.get(selectedIndex).setStatus(status);


        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }

    public void deleteData() {
        int result = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // hapus data dari list
            listMahasiswa.remove(selectedIndex);

            // update tabel
            mahasiswaTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Delete Berhasil!");
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
//        } else {
//            System.out.println("Delete Gagal!");
//            JOptionPane.showMessageDialog(null, "Data gagal dihapus!");
        }


    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        UniversityField.setText("");
        JenjangcomboBox.setSelectedItem("");
        StatuscomboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "Institut Teknologi Bandung", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "Universitas Padjajaran", "S2", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "Universitas Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "Politeknik Negri Bandung", "D3", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "Institut Teknologi Bandung", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "Politeknik Kesehatan Bandung", "D3", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "Universitas Pendidikan Indoensia", "S1", "Peserta didik baru"));
    }
}
