package andersonfds.pibic.Classes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity ( tableName = "mapMarkers" )
public class Markers
{

    @PrimaryKey( autoGenerate = true )
    private int id;

    @NonNull
    private String email;

    @NonNull
    private float latitute;

    @NonNull
    private float longitude;

    public Markers(){}

    public Markers(String email, float latitute, float longitude)
    {
        this.email = email;
        this.latitute = latitute;
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

    public float getLatitute() {
        return latitute;
    }

    public void setLatitute(float latitute) {
        this.latitute = latitute;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}