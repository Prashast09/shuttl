package ethens.shuttl.di;

import android.content.Context;
import android.util.Log;
import dagger.Module;
import dagger.Provides;
import ethens.shuttl.Information;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.inject.Named;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by ethens on 11/10/17.
 */
@Module public class DataModule {

  @Provides @Named("list" + "") ArrayList<Information> provideInformationList(Context context) {
    InputStream inputStream = null;
    try {
      inputStream = context.getResources().getAssets().open("data.json");
      int size = inputStream.available();
      byte[] buffer = new byte[size];
      inputStream.read(buffer);
      return getInformationConfig(new JSONObject(new String(buffer, "UTF-8")));
    } catch (IOException | JSONException e) {
      Log.d(TAG, e.toString());
      return new ArrayList<>();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          Log.e("providesBankMap", e.toString());
        }
      }
    }
  }

  private ArrayList<Information> getInformationConfig(JSONObject jsonObject) {

    ArrayList<Information> informationList = new ArrayList<>();

    JSONArray informationArray;
    try {
      Information information = new Information();
      informationArray = jsonObject.getJSONArray("data");
      for (int i = 0; i < informationArray.length(); i++) {
        JSONObject objects = informationArray.getJSONObject(i);
        information.setDescription(objects.getString("description"));
        information.setName(objects.getString("name"));
        if (objects.has("text")) information.setText(objects.getString("text"));
        information.setTime(objects.getLong("time"));
        information.setTitle(objects.getString("title"));
        if (objects.has("imageUrl")) information.setUrl(objects.getString("imageUrl"));
        informationList.add(information);
        information = new Information();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    Collections.sort(informationList, new Comparator<Information>() {
      @Override public int compare(Information o1, Information o2) {
        return o1.getTime() < o2.getTime() ? 1 : 0;
      }
    });
    return informationList;
  }
}
