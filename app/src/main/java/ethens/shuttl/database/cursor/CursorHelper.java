package ethens.shuttl.database.cursor;

import android.database.Cursor;
import android.util.Log;
import ethens.shuttl.common.ParameterCallback;
import javax.inject.Inject;

/**
 * Created by ethens on 11/10/17.
 */

public class CursorHelper {

  public static final String TAG = CursorHelper.class.getSimpleName();

  @Inject public CursorHelper() {
  }

  public void iterateCursor(Cursor c, ParameterCallback<Cursor> callback) {
    // http://stackoverflow.com/questions/14316082/cursor-window-could-not-be-created-from-binder
    try {
      if (c
          != null) { // must close cursor regardless of the count and the condition of not affected by this way
        if (c.getCount() > 0 && c.moveToFirst()) {
          do {
            try {
              callback.onResponse(c);
            } catch (Exception e) {
              Log.e(TAG, "iterateCursor: ", e);
            }
          } while (c.moveToNext());
        }
        c.close();
      }
    } catch (Exception e) {
      c.close();
      Log.e(TAG, "iterateCursor: ", e);
    }
  }
}
