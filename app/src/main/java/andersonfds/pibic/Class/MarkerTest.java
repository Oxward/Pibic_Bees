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
    @ColumnInfo(name = "ponto")
    private float ponto;

    public MarkerTest() {
    }

    public MarkerTest(String email, float ponto) {
        this.email = email;
        this.ponto = ponto;
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

    public float getPonto() {
        return ponto;
    }

    public void setPonto(float ponto) {
        this.ponto = ponto;
    }

    @Override
    public String toString() {
        return "MarkerTest{" + "email='" + email + '\'' + ", ponto=" + ponto + '}';
    }
}