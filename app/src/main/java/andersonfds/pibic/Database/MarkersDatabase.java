package andersonfds.pibic.Database;

import android.arch.persistence.room.Database;

import andersonfds.pibic.Classes.Markers;

import static andersonfds.pibic.Database.MarkersDatabase.DATABASE_VERSION;

@Database( entities = Markers.class, version = DATABASE_VERSION )
public abstract class MarkersDatabase
{

    public static final int DATABASE_VERSION = 1;
    public abstract MarkersDAO markersDAO();

}