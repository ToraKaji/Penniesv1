package deepdive.cnm.edu.penniesv1.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import deepdive.cnm.edu.penniesv1.model.entities.Scratcher;
import java.util.List;

@Dao
public interface ScratcherDao {

  @Query("SELECT * FROM Scratcher")
  List<Scratcher> getAll();

  @Query("SELECT play_id FROM Scratcher")
  long getLastGame();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Scratcher scratcher);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Scratcher> scratchers);

}