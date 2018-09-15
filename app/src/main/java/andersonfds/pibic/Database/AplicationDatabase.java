package andersonfds.pibic.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import andersonfds.pibic.Classes.Marker;
import andersonfds.pibic.Classes.RegisterContacts;

import static andersonfds.pibic.Database.AplicationDatabase.DATABASE_VERSION;

@Database(entities = {Marker.class, RegisterContacts.class}, exportSchema = false, version = DATABASE_VERSION)
public abstract class AplicationDatabase extends RoomDatabase {

    protected static final int DATABASE_VERSION = 1;

    //Markers DAO
    public abstract MarkerDAO markerDAO();

    //RegisterContacts DAO
    public abstract RegisterContactsDAO regConDAO();

}