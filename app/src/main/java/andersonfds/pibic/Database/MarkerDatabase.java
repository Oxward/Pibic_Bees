package andersonfds.pibic.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import andersonfds.pibic.Classes.Marker;

import static andersonfds.pibic.Database.RegisterContactsDatabase.DATABASE_VERSION;

@Database(entities = Marker.class, exportSchema = false, version = DATABASE_VERSION)
public abstract class MarkerDatabase extends RoomDatabase
{

    public abstract MarkerDAO markerDAO();

}