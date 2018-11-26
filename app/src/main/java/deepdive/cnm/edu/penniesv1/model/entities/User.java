package deepdive.cnm.edu.penniesv1.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

@PrimaryKey(autoGenerate = true)
@ColumnInfo(name = "user_id")
  private long UserId;
@ColumnInfo(name = "wins")
  private long wins;

private long coins;

  public long getUserId() {
    return UserId;
  }

  public void setUserId(long userId) {
    UserId = userId;
  }

  public long getWins() {
    return wins;
  }

  public void setWins(long wins) {
    this.wins = wins;
  }

  public long getCoins() {
    return coins;
  }

  public void setCoins(long coins) {
    this.coins = coins;
  }
}
