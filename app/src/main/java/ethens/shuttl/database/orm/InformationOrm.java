package ethens.shuttl.database.orm;

import android.content.ContentValues;
import android.database.Cursor;
import ethens.shuttl.Information;
import ethens.shuttl.database.ShuttlContract;
import javax.inject.Inject;

/**
 * Created by ethens on 11/10/17.
 */

public class InformationOrm {
  @Inject public InformationOrm() {
  }

  public ContentValues getInformationCV(Information information) {
    ContentValues cv = new ContentValues();
    cv.put(ShuttlContract.Shuttl.COLUMN_NAME_NAME, information.getName());
    cv.put(ShuttlContract.Shuttl.COLUMN_NAME_IMAGE, information.getUrl());
    cv.put(ShuttlContract.Shuttl.COLUMN_NAME_TITLE, information.getTitle());
    cv.put(ShuttlContract.Shuttl.COLUMN_NAME_TEXT, information.getText());
    cv.put(ShuttlContract.Shuttl.COLUMN_NAME_TIME, information.getTime());
    cv.put(ShuttlContract.Shuttl.COLUMN_NAME_DESCRIPTION, information.getDescription());
    cv.put(ShuttlContract.Shuttl.COLUMN_NAME_STATUS, information.getStatus());
    return cv;
  }

  public Information getInformationFromCursor(Cursor cursor) {
    Information information = new Information();
    information.setName(
        cursor.getString(cursor.getColumnIndex(ShuttlContract.Shuttl.COLUMN_NAME_NAME)));
    information.setUrl(
        cursor.getString(cursor.getColumnIndex(ShuttlContract.Shuttl.COLUMN_NAME_IMAGE)));
    information.setTitle(
        cursor.getString(cursor.getColumnIndex(ShuttlContract.Shuttl.COLUMN_NAME_TITLE)));
    information.setText(
        cursor.getString(cursor.getColumnIndex(ShuttlContract.Shuttl.COLUMN_NAME_TEXT)));
    information.setTime(
        cursor.getLong(cursor.getColumnIndex(ShuttlContract.Shuttl.COLUMN_NAME_TIME)));
    information.setDescription(
        cursor.getString(cursor.getColumnIndex(ShuttlContract.Shuttl.COLUMN_NAME_DESCRIPTION)));
    information.setStatus(
        cursor.getString(cursor.getColumnIndex(ShuttlContract.Shuttl.COLUMN_NAME_STATUS)));
    information.setId(cursor.getLong(cursor.getColumnIndex(ShuttlContract.Shuttl._ID)));
    return information;
  }
}
