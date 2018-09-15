package andersonfds.pibic.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import andersonfds.pibic.Classes.RegisterContacts;

@Dao
public interface RegisterContatsDAO {

    @Insert
    void registerContacs(RegisterContacts rc);

    @Query("SELECT * FROM contacts")
    List<RegisterContacts> selectContacts();

    @Query("SELECT * FROM contacts WHERE nome = :nome")
    List<RegisterContacts> selecContacts(String nome);

    @Delete
    void deleteContact(RegisterContacts rc);

}