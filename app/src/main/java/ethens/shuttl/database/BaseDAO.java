package ethens.shuttl.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ethens.shuttl.common.ParameterCallback;
import ethens.shuttl.common.ReturnCallback;
import ethens.shuttl.database.cursor.CursorHelper;
import ethens.shuttl.di.ComponentFactory;
import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by ethens on 11/10/17.
 */

public class BaseDAO {
  @Inject protected SQLiteDatabase writeableDatabase;

  @Inject CursorHelper cursorHelper;

  public SQLiteDatabase getWriteableDatabase() {
    if (!writeableDatabase.isOpen()) {
      ComponentFactory.getInstance().removeDashboardComponent();
      ComponentFactory.getInstance().getDashboardComponent().inject(this);
    }
    return writeableDatabase;
  }

  public long executeBatchQuery(ReturnCallback<Long> batchFunction) {
    long rowsAffectd = 0;
    try {
      // requires permission
      getWriteableDatabase().beginTransaction();
      rowsAffectd = batchFunction.onResponse();
      getWriteableDatabase().setTransactionSuccessful();
    } catch (Exception e) {
      Log.e(TAG, "executeBatchQuery: ", e);
    } finally {
      try {
        getWriteableDatabase().endTransaction();
      } catch (Exception e) {
        Log.e("BaseDao", e.toString());
      }
    }
    return rowsAffectd;
  }

  public void runRawQuery(String query, ParameterCallback<Cursor> callback) {
    try {
      Cursor c = getWriteableDatabase().rawQuery(query, null);
      cursorHelper.iterateCursor(c, callback);
    } catch (IllegalStateException e) {
      Log.e(TAG, e.toString());
    }
  }
}
