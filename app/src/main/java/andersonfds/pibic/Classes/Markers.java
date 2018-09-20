package andersonfds.pibic.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "mapMarkers")
public class Markers
{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idMarker", typeAffinity = ColumnInfo.INTEGER)
    private int id;

    @NonNull
    @ColumnInfo(name = "nameMarker", typeAffinity = ColumnInfo.TEXT)
    private String nome;

    @NonNull
    @ColumnInfo(name = "emailMarker", typeAffinity = ColumnInfo.TEXT)
    private String email;

    @NonNull
    @ColumnInfo(name = "latMarker", typeAffinity = ColumnInfo.REAL)
    private float latitude;

    @NonNull
    @ColumnInfo(name = "lonMarker", typeAffinity = ColumnInfo.REAL)
    private float longitude;

    public Markers() {
    }

    @Ignore
    public Markers(String email, String nome, float latitude, float longitude) {
        this.email = email;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

}