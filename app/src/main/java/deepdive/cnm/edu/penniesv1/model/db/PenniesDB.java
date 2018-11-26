package deepdive.cnm.edu.penniesv1.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import deepdive.cnm.edu.penniesv1.model.dao.PlayDao;
import deepdive.cnm.edu.penniesv1.model.dao.ScratcherDao;
import deepdive.cnm.edu.penniesv1.model.dao.UserDao;
import deepdive.cnm.edu.penniesv1.model.db.PenniesDB.Converters;
import deepdive.cnm.edu.penniesv1.model.entities.Play;
import deepdive.cnm.edu.penniesv1.model.entities.Scratcher;
import deepdive.cnm.edu.penniesv1.model.entities.User;
import java.util.Date;

@Database(
    entities = {Scratcher.class, Play.class, User.class},
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters.class)
public abstract class PenniesDB extends RoomDatabase {

  private static final String DB_NAME = "text_db";
  private static PenniesDB instance = null;

  public synchronized static void forgetInstance() {
    instance = null;
  }

  public synchronized static PenniesDB getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), PenniesDB.class, DB_NAME)
          .build();
    }
    return instance;
  }

  public abstract PlayDao getPlayDao();

  public abstract UserDao getUserDao();

  public abstract ScratcherDao getScratcherDao();

  public static class Converters {

    @TypeConverter
    public static Date dateFromLong(Long time) {
      return (time != null) ? new Date(time) : null;
    }

    @TypeConverter
    public static Long longFromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }
  }
}

