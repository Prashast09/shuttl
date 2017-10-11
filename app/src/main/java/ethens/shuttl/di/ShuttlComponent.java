package ethens.shuttl.di;

import dagger.Component;
import ethens.shuttl.di.dashboard.DashboardComponent;
import ethens.shuttl.di.dashboard.DashboardModule;
import ethens.shuttl.di.database.DatabaseModule;
import javax.inject.Singleton;

/**
 * Created by ethens on 10/10/17.
 */

@Singleton @Component(modules = { AppModule.class }) public interface ShuttlComponent {

  DashboardComponent plus(DashboardModule dashboardModule, DatabaseModule databaseModule);
}