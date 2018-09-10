package andersonfds.pibic.Classes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity( tableName = "mapMarkers")
public class Marker
{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String email;

    @NonNull
    private float latitude;

    @NonNull
    private float longitude;

    public Marker(){}

    @Ignore
    public Marker(String email, float latitude, float longitude) {
        this.email = email;
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