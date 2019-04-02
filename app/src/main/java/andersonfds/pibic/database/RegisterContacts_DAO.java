package andersonfds.pibic.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import andersonfds.pibic.classes.RegisterContacts;

@Dao
public interface RegisterContacts_DAO {

    @Insert
    long[] insertContacts(RegisterContacts... rc);

    @Query("SELECT * FROM contacts")
    LiveData<List<RegisterContacts>> selectAllContacts();

    @Query("SELECT * FROM contacts WHERE foiResolvido = 0")
    LiveData<List<RegisterContacts>> selectUnsolved();

    @Query("SELECT * FROM contacts WHERE foiResolvido = 1")
    LiveData<List<RegisterContacts>> selectSolved();

    @Delete
    void deleteContact(RegisterContacts rc);

}