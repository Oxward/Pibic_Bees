package andersonfds.pibic.Class;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "pontosMarcados")
public class MarkerTest {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "latitude")
    private float latitude;

    @NonNull
    @ColumnInfo(name = "longitude")
    private float longitude;

    public MarkerTest() {
    }

    public MarkerTest(String email, float latitude, float longitude) {
        this.email = email;
        this.latitude = latitude;
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

    @Override
    public String toString() {
        return "MarkerTest{" + "email='" + email + '\'' + ", latitude=" + latitude + '}';
    }
}