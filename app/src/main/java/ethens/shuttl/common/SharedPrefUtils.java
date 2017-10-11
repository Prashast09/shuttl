package ethens.shuttl.common;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;

/**
 * Created by ethens on 10/10/17.
 */

public class SharedPrefUtils {

  @Inject Context context;

  @Inject public SharedPrefUtils() {
  }

  public void putLong(String key, Long value) {
    final SharedPreferences.Editor editor =
        context.getSharedPreferences("shuttlPref", Context.MODE_PRIVATE).edit();
    editor.putLong(key, value);
    editor.apply();
  }

  public Long getLongDataByKey(String key, Long defaultValue) {
    final SharedPreferences preferences =
        context.getSharedPreferences("shuttlPref", Context.MODE_PRIVATE);
    return preferences.getLong(key, defaultValue);
  }
}
