package andersonfds.pibic.classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "products")
public class Products {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idProd", typeAffinity = ColumnInfo.INTEGER)
    private int idProd;

    @ForeignKey(entity = Category.class, parentColumns = "prodCat", childColumns = "idCat")
    @ColumnInfo(name = "prodCat", typeAffinity = ColumnInfo.INTEGER)
    private int category;

    @NonNull
    @ColumnInfo(name = "descProd", typeAffinity = ColumnInfo.TEXT)
    private String descProd;

    @NonNull
    @ColumnInfo(name = "valorProd", typeAffinity = ColumnInfo.REAL)
    private double valorProd;

    @NonNull
    @ColumnInfo(name = "imgProd", typeAffinity = ColumnInfo.BLOB)
    private byte[] imgProd;

    @Ignore
    public Products() {
    }

    @Ignore
    public Products(String descProd, double valorProd) {
        this.descProd = descProd;
        this.valorProd = valorProd;
    }

    @Ignore
    public Products(String descProd, double valorProd, byte[] imgProd) {
        this.descProd = descProd;
        this.valorProd = valorProd;
        this.imgProd = imgProd;
    }

    public Products(int idProd, String descProd, double valorProd, byte[] imgProd) {
        this.idProd = idProd;
        this.descProd = descProd;
        this.valorProd = valorProd;
        this.imgProd = imgProd;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    public double getValorProd() {
        return valorProd;
    }

    public void setValorProd(double valorProd) {
        this.valorProd = valorProd;
    }

    public byte[] getImgProd() {
        return imgProd;
    }

    public void setImgProd(byte[] imgProd) {
        this.imgProd = imgProd;
    }
}