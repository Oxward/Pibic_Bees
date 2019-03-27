package andersonfds.pibic.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import andersonfds.pibic.classes.Products;

@Dao
public interface Products_DAO {

    @Insert
    long[] insertProduct(Products... products);

    @Query("SELECT * FROM products")
    LiveData<List<Products>> getAllProducts();

}