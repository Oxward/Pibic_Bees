package andersonfds.pibic.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "catetories")
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCat", typeAffinity = ColumnInfo.INTEGER)
    private int id;

    @NonNull
    @ColumnInfo(name = "descCat", typeAffinity = ColumnInfo.TEXT)
    private String desc;

    @Ignore
    public Category() {
    }

    @Ignore
    public Category(@NonNull String desc) {
        this.desc = desc;
    }

    @Ignore
    public Category(int id) {
        this.id = id;
    }

    @Ignore
    public Category(int id, @NonNull String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }

    public void setDesc(@NonNull String desc) {
        this.desc = desc;
    }
}
