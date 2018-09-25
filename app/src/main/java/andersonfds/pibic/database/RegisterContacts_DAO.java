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
    void insertContacts(RegisterContacts rc);

    @Query("SELECT * FROM contacts")
    LiveData<List<RegisterContacts>> selectAllContacts();

    @Query("SELECT * FROM contacts WHERE idRegCon = :nome")
    LiveData<List<RegisterContacts>> selectContacts(String nome);

    @Delete
    void deleteContact(RegisterContacts rc);

}