package deepdive.cnm.edu.penniesv1.model.entities;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import deepdive.cnm.edu.penniesv1.model.entities.User;


@Entity(tableName = "play",
foreignKeys = {@ForeignKey(
    entity = User.class,
    parentColumns = "user_id",
    childColumns = "user_id",
    onDelete = ForeignKey.CASCADE
)},
indices = @Index(value = "play_id",unique = true))
public class Play {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "play_id")
  private long playId;

  private boolean outcome;

  private long won;

  public long getWon() {
    return won;
  }

  public void setWon(long won) {
    this.won = won;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  @ColumnInfo(name = "user_id")
  private long userId;



  public boolean isOutcome() {

    return outcome;
  }

  public void setOutcome(boolean outcome) {
    this.outcome = outcome;
  }

  public long getPlayId() {
    return playId;
  }

  public void setPlayId(long playId) {
    this.playId = playId;
  }

}
