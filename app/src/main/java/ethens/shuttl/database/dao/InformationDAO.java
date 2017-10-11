package ethens.shuttl.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import de.greenrobot.event.EventBus;
import ethens.shuttl.Information;
import ethens.shuttl.common.ParameterCallback;
import ethens.shuttl.common.ReturnCallback;
import ethens.shuttl.database.BaseDAO;
import ethens.shuttl.database.ShuttlContract;
import ethens.shuttl.database.orm.InformationOrm;
import ethens.shuttl.eventbus.DbInsertionComplete;
import ethens.shuttl.eventbus.DbUpdated;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by ethens on 11/10/17.
 */

public class InformationDAO extends BaseDAO {

  @Inject InformationOrm informationOrm;

  @Inject public InformationDAO() {
  }

  public void saveInformation(final List<Information> informationList) {
    executeBatchQuery(new ReturnCallback<Long>() {
      @Override public Long onResponse() {
        ContentValues cv;
        for (Information information : informationList) {
          cv = informationOrm.getInformationCV(information);
          writeableDatabase.insert(ShuttlContract.Shuttl.TABLE_NAME, null, cv);
          cv.clear();
        }
        return 0L;
      }
    });
    EventBus.getDefault().post(new DbInsertionComplete());
  }

  public ArrayList<Information> getInformationList() {
    String query = "select * from " + ShuttlContract.Shuttl.TABLE_NAME;
    final ArrayList<Information> informationList = new ArrayList<>();
    runRawQuery(query, new ParameterCallback<Cursor>() {
      @Override public void onResponse(Cursor cursor) {
        informationList.add(informationOrm.getInformationFromCursor(cursor));
      }
    });
    return informationList;
  }

  public void updateStatus(Long id, String status, boolean isDashboard) {
    String query = ShuttlContract.Shuttl._ID
        + " = '"
        + id
        + "'";
    ContentValues cv = new ContentValues();
    cv.put(ShuttlContract.Shuttl.COLUMN_NAME_STATUS, status);
    writeableDatabase.update(ShuttlContract.Shuttl.TABLE_NAME, cv, query, null);
    EventBus.getDefault().post(new DbUpdated(isDashboard));
  }
}
