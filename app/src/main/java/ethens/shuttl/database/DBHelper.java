package ethens.shuttl.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ethens.shuttl.di.ComponentFactory;

import static ethens.shuttl.database.Schema.SQL_CREATE_SHUTTL;

/**
 * Created by ethens on 10/10/17.
 */

public class DBHelper extends SQLiteOpenHelper {

  public DBHelper(Context context, String databaseName, int databaseVersion) {
    super(context, databaseName, null, databaseVersion);
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_SHUTTL);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
