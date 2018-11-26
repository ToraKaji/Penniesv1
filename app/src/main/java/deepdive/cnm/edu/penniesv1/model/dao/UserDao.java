package deepdive.cnm.edu.penniesv1.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import deepdive.cnm.edu.penniesv1.model.entities.User;
import java.util.List;

@Dao
public interface UserDao {

@Query("SELECT * FROM User ORDER BY user_id")
List<User> select();

@Query("SELECT * FROM User WHERE user_id=:userId LIMIT 1")
  User select(long userId);

@Insert(onConflict = OnConflictStrategy.REPLACE)
long insert(User user);

@Update(onConflict = OnConflictStrategy.REPLACE)
int update(User user);
}
