package ethens.shuttl.di;

import android.support.v7.app.AppCompatActivity;
import ethens.shuttl.activity.ShuttlApplication;
import ethens.shuttl.di.dashboard.DashboardComponent;
import ethens.shuttl.di.dashboard.DashboardModule;
import ethens.shuttl.di.database.DatabaseModule;

/**
 * Created by ethens on 10/10/17.
 */

public class ComponentFactory {

  public static ComponentFactory componentFactory;
  public ShuttlComponent shuttlComponent;
  public DashboardComponent dashboardComponent;

  public static ComponentFactory getInstance() {
    if (componentFactory == null) {
      componentFactory = new ComponentFactory();
    }

    return componentFactory;
  }

  public ComponentFactory initializeComponent(ShuttlApplication shuttlApplication) {
    shuttlComponent = DaggerShuttlComponent.builder()
        // This also corresponds to the name of your module: %component_name%Module
        .appModule(new AppModule(shuttlApplication)).build();
    return componentFactory;
  }

  public ShuttlComponent getshuttlComponent() {
    return shuttlComponent;
  }

  public DashboardComponent getDashboardComponent() {
    if (dashboardComponent == null) {
      dashboardComponent =
          getshuttlComponent().plus(new DashboardModule(), new DatabaseModule());
    }
    return dashboardComponent;
  }


  public void removeDashboardComponent() {
    dashboardComponent = null;
  }
}
