package andersonfds.pibic.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "contacts")
public class RegisterContacts {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idRegCon", typeAffinity = ColumnInfo.INTEGER)
    private int id;

    @NonNull
    @ColumnInfo(name = "nomeRegCon", typeAffinity = ColumnInfo.TEXT)
    private String nome;

    @ColumnInfo(name = "numTelRegCon", typeAffinity = ColumnInfo.TEXT)
    private String numTel;

    @ColumnInfo(name = "numWppRegCon", typeAffinity = ColumnInfo.TEXT)
    private String numWpp;

    @NonNull
    @ColumnInfo(name = "locLatRegCon", typeAffinity = ColumnInfo.REAL)
    private double locLat;

    @NonNull
    @ColumnInfo(name = "locLonRegCon", typeAffinity = ColumnInfo.REAL)
    private double locLon;

    @NonNull
    @ColumnInfo(name = "imgRegCon1", typeAffinity = ColumnInfo.BLOB)
    private byte[] img1;

    @NonNull
    @ColumnInfo(name = "imgRegCon2", typeAffinity = ColumnInfo.BLOB)
    private byte[] img2;

    public RegisterContacts() {
    }

    @Ignore
    public RegisterContacts(String nome, double locLat, double locLon, byte[] img1, byte[] img2) {
        this.nome = nome;
        this.locLat = locLat;
        this.locLon = locLon;
        this.img1 = img1;
        this.img2 = img2;
    }

    @Ignore
    public RegisterContacts(String nome, String numTel, String numWpp, double locLat, double locLon) {
        this.nome = nome;
        this.numTel = numTel;
        this.numWpp = numWpp;
        this.locLat = locLat;
        this.locLon = locLon;
    }

    @Ignore
    public RegisterContacts(String nome, String numTel, double locLat, double locLon, byte[] img1, byte[] img2) {
        this.nome = nome;
        this.numTel = numTel;
        this.locLat = locLat;
        this.locLon = locLon;
        this.img1 = img1;
        this.img2 = img2;
    }

    @Ignore
    public RegisterContacts(String nome, String numTel, String numWpp, double locLat, double locLon, byte[] img1, byte[] img2) {
        this.nome = nome;
        this.numTel = numTel;
        this.numWpp = numWpp;
        this.locLat = locLat;
        this.locLon = locLon;
        this.img1 = img1;
        this.img2 = img2;
    }

    public RegisterContacts(String name, String cont, String wpp, byte[] img1, byte[] img2) {
        this.nome = name;
        this.numTel = cont;
        this.numWpp = wpp;
        this.img1 = img1;
        this.img2 = img2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getNumWpp() {
        return numWpp;
    }

    public void setNumWpp(String numWpp) {
        this.numWpp = numWpp;
    }

    public double getLocLat() {
        return locLat;
    }

    public void setLocLat(double locLat) {
        this.locLat = locLat;
    }

    public double getLocLon() {
        return locLon;
    }

    public void setLocLon(double locLon) {
        this.locLon = locLon;
    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public byte[] getImg2() {
        return img2;
    }

    public void setImg2(byte[] img2) {
        this.img2 = img2;
    }

}