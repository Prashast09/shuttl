package ethens.shuttl.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;
import ethens.shuttl.activity.ShuttlApplication;
import javax.inject.Singleton;

/**
 * Created by ethens on 10/10/17.
 */
@Module public class AppModule {
  public ShuttlApplication shuttlApplication;

  public AppModule(ShuttlApplication shuttlApplication) {
    this.shuttlApplication = shuttlApplication;
  }

  @Provides @Singleton ShuttlApplication providesProductApplication() {
    return shuttlApplication;
  }

  @Provides @Singleton Application providesApplication() {
    return shuttlApplication;
  }

  @Provides @Singleton SharedPreferences providesSharedPrefs() {
    return PreferenceManager.getDefaultSharedPreferences(shuttlApplication);
  }

  @Provides @Singleton Context providesContext() {
    return shuttlApplication;
  }
}
