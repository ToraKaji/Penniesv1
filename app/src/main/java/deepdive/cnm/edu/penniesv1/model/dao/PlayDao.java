package deepdive.cnm.edu.penniesv1.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import deepdive.cnm.edu.penniesv1.model.entities.Play;

@Dao
public interface PlayDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Play play);

}
