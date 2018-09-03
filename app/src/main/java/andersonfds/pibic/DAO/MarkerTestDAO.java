package andersonfds.pibic.DAO;

import android.arch.persistence.room.Query;

import andersonfds.pibic.Class.MarkerTest;
import io.reactivex.Flowable;

public interface MarkerTestDAO {

    @Query("SELECT * FROM pontosMarcados WHERE email = email")
    Flowable<MarkerTest> getMarkerTestByEmail(String email);


}