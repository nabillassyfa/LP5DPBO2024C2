// Saya Nabilla Assyfa Ramadhani [2205297]
// mengerjakan Latihan
// dalam mata kuliah Desain dan Pemograman Berorientasi Objek
// untuk keberkahanNya maka saya tidak melakukan kecurangan
// seperti yang telah dispesifikasikan.
// Aamiin


public class Mahasiswa {
    private String nim;
    private String nama;
    private String jenisKelamin;
    private String PT;
    private String jenjang;
    private String status;

    public Mahasiswa(String nim, String nama, String jenisKelamin, String PT, String jenjang, String status) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.PT = PT;
        this.jenjang = jenjang;
        this.status = status;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setPT (String PT){ this.PT = PT;}

    public  void setJenjang (String jenjang){ this.jenjang = jenjang;}

    public  void setStatus(String status){ this.status = status;}

    public String getNim() {
        return this.nim;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public String getPT(){ return  this.PT;}

    public String getJenjang(){ return  this.jenjang;}

    public String getStatus(){ return  this.status;}
}
