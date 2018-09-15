package andersonfds.pibic.Classes;

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

    @ColumnInfo(name = "numTelRegCon", typeAffinity = ColumnInfo.INTEGER)
    private long numTel;

    @ColumnInfo(name = "numWppRegCon", typeAffinity = ColumnInfo.INTEGER)
    private long numWpp;

    @NonNull
    @ColumnInfo(name = "locLatRegCon", typeAffinity = ColumnInfo.REAL)
    private float locLat;

    @NonNull
    @ColumnInfo(name = "locLonRegCon", typeAffinity = ColumnInfo.REAL)
    private float locLon;

    public RegisterContacts() {
    }

    @Ignore
    public RegisterContacts(String nome, float locLat, float locLon) {
        this.nome = nome;
        this.locLat = locLat;
        this.locLon = locLon;
    }

    @Ignore
    public RegisterContacts(String nome, long numTel, float locLat, float locLon) {
        this.nome = nome;
        this.numTel = numTel;
        this.locLat = locLat;
        this.locLon = locLon;
    }

    @Ignore
    public RegisterContacts(String nome, long numTel, long numWpp, float locLat, float locLon) {
        this.nome = nome;
        this.numTel = numTel;
        this.numWpp = numWpp;
        this.locLat = locLat;
        this.locLon = locLon;
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

    public long getNumTel() {
        return numTel;
    }

    public void setNumTel(long numTel) {
        this.numTel = numTel;
    }

    public long getNumWpp() {
        return numWpp;
    }

    public void setNumWpp(long numWpp) {
        this.numWpp = numWpp;
    }

    public float getLocLat() {
        return locLat;
    }

    public void setLocLat(float locLat) {
        this.locLat = locLat;
    }

    public float getLocLon() {
        return locLon;
    }

    public void setLocLon(float locLon) {
        this.locLon = locLon;
    }

}