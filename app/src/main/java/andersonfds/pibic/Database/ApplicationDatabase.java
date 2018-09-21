package andersonfds.pibic.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import andersonfds.pibic.Classes.Markers;
import andersonfds.pibic.Classes.RegisterContacts;

import static andersonfds.pibic.Database.ApplicationDatabase.DATABASE_VERSION;

@Database(entities = {Markers.class, RegisterContacts.class}, exportSchema = false, version = DATABASE_VERSION)
public abstract class ApplicationDatabase extends RoomDatabase {

    static final int DATABASE_VERSION = 1;
    private static volatile ApplicationDatabase INSTANCE;

    static ApplicationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ApplicationDatabase.class) {
                if (INSTANCE == null) {
                    //BD criado aqui
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ApplicationDatabase.class, "pibees_project").build();
                }
            }
        }
        return INSTANCE;
    }

    //Markers DAO
    public abstract Marker_DAO markerDAO();

    //RegisterContacts DAO
    public abstract RegisterContacts_DAO regConDAO();

}