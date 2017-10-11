package ethens.shuttl.di.database;

/**
 * Created by ethens on 10/10/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import dagger.Module;
import dagger.Provides;
import ethens.shuttl.R;
import ethens.shuttl.database.DBHelper;
import ethens.shuttl.di.dashboard.DashboardScope;

@Module public class DatabaseModule {

  @Provides @DashboardScope SQLiteDatabase providesDatabase(Context context) {
    String dbName = "product" + ".db";
    int dbVersion = context.getResources().getInteger(R.integer.DATABASE_VERSION);
    SQLiteDatabase database = new DBHelper(context, dbName, dbVersion).getWritableDatabase();
    database.enableWriteAheadLogging();
    return database;
  }
}
