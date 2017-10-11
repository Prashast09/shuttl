package ethens.shuttl.di.dashboard;

import dagger.Subcomponent;
import ethens.shuttl.activity.dashboard.DashboardActivity;
import ethens.shuttl.activity.dashboard.DashboardViewHolder;
import ethens.shuttl.activity.description.DescriptionActivity;
import ethens.shuttl.activity.description.DescriptionViewHolder;
import ethens.shuttl.adapter.InformationAdapter;
import ethens.shuttl.database.BaseDAO;
import ethens.shuttl.database.DBHelper;
import ethens.shuttl.di.DataModule;
import ethens.shuttl.di.database.DatabaseModule;

/**
 * Created by ethens on 10/10/17.
 */
@Subcomponent(modules = { DashboardModule.class, DatabaseModule.class, DataModule.class })
@DashboardScope public interface DashboardComponent {

  void inject(DBHelper dbHelper);

  void inject(DashboardViewHolder dashboardViewHolder);

  void inject(InformationAdapter informationAdapter);

  void inject(BaseDAO baseDAO);

  void inject(DescriptionActivity descriptionActivity);

  void inject(DashboardActivity dashboardActivity);

  void inject(DescriptionViewHolder descriptionViewHolder);
}

