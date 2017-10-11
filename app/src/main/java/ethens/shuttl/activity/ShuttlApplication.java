package ethens.shuttl.activity;

import android.app.Application;
import ethens.shuttl.di.ComponentFactory;

/**
 * Created by ethens on 10/10/17.
 */

public class ShuttlApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    ComponentFactory.getInstance().initializeComponent(this);
  }
}
