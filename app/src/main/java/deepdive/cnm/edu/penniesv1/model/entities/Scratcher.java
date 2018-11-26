package deepdive.cnm.edu.penniesv1.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = {@ForeignKey(
        entity = Play.class,
        parentColumns = "play_id",
        childColumns = "play_id",
        onDelete = ForeignKey.CASCADE
    )},
    indices = @Index(value = "play_id")
)
public class Scratcher {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "unit_id")
  private long unitId;

  @ColumnInfo(name = "play_id")
  private long playId;

  @ColumnInfo(name = "button_clicked")
  private long buttonClicked;
  @ColumnInfo(name = "button_id")
  private long id;
  @ColumnInfo(name = "timestamp")
  private Date date;
  private long value;

  public Scratcher() {

  }

  public Scratcher(long id, long value, Date date) {
    this.id = id;
    this.value = value;
    this.date = date;
  }

  public long getUnitId() {
    return unitId;
  }

  public void setUnitId(long unitId) {
    this.unitId = unitId;
  }

  public long getPlayId() {
    return playId;
  }

  public void setPlayId(long playId) {
    this.playId = playId;
  }

  public long getButtonClicked() {
    return buttonClicked;
  }

  public void setButtonClicked(long buttonClicked) {
    this.buttonClicked = buttonClicked;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
