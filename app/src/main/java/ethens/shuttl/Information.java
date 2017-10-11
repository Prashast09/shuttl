package ethens.shuttl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ethens on 10/10/17.
 */

public class Information implements Parcelable {

  public static final Parcelable.Creator<Information> CREATOR =
      new Parcelable.Creator<Information>() {
        @Override public Information createFromParcel(Parcel source) {
          return new Information(source);
        }

        @Override public Information[] newArray(int size) {
          return new Information[size];
        }
      };
  Long id;
  String name;
  String url;
  String title;
  String description;
  long time;
  String text;
  String status;
  int state;

  public Information() {
  }

  protected Information(Parcel in) {
    this.id = (Long) in.readValue(Long.class.getClassLoader());
    this.name = in.readString();
    this.url = in.readString();
    this.title = in.readString();
    this.description = in.readString();
    this.time = in.readLong();
    this.text = in.readString();
    this.status = in.readString();
    this.state = in.readInt();
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.id);
    dest.writeString(this.name);
    dest.writeString(this.url);
    dest.writeString(this.title);
    dest.writeString(this.description);
    dest.writeLong(this.time);
    dest.writeString(this.text);
    dest.writeString(this.status);
    dest.writeInt(this.state);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }
}
