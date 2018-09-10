package andersonfds.pibic.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import andersonfds.pibic.Classes.Markers;

@Dao
public interface MarkersDAO
{

    @Insert
    void addMarker( Markers markers );

    @Query( "SELECT * FROM mapMarkers WHERE email = :em")
    List<Markers> selectMarkers(String em );

    @Delete
    void deleteMarker( Markers markers );

}