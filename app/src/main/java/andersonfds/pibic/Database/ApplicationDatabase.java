package andersonfds.pibic.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import andersonfds.pibic.Classes.Marker;
import andersonfds.pibic.Classes.RegisterContacts;

import static andersonfds.pibic.Database.ApplicationDatabase.DATABASE_VERSION;

@Database(entities = {Marker.class, RegisterContacts.class}, exportSchema = false, version = DATABASE_VERSION)
public abstract class ApplicationDatabase extends RoomDatabase {

    protected static final int DATABASE_VERSION = 1;
    private static volatile ApplicationDatabase INSTANCE;

    static ApplicationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ApplicationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "pibees_project").build();
                }
            }
        }
        return INSTANCE;
    }

    //Markers DAO
    public abstract MarkerDAO markerDAO();

    //RegisterContacts DAO
    public abstract RegisterContactsDAO regConDAO();

}