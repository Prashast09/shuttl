package ethens.shuttl.activity.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import de.greenrobot.event.EventBus;
import ethens.shuttl.Information;
import ethens.shuttl.R;
import ethens.shuttl.activity.description.DescriptionActivity;
import ethens.shuttl.common.BaseActivity;
import ethens.shuttl.di.ComponentFactory;
import ethens.shuttl.eventbus.ButtonClick;
import ethens.shuttl.eventbus.DbUpdated;
import ethens.shuttl.eventbus.DescriptionFinish;
import ethens.shuttl.eventbus.InformationClick;

/**
 * Created by ethens on 10/10/17.
 */

public class DashboardActivity extends BaseActivity {

  DashboardViewHolder dashboardViewHolder;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupUI(R.layout.activity_main, R.id.coordinator_layout);
    EventBus.getDefault().register(this, 0);
  }

  @Override protected void setupViewHolder(View view) {
    dashboardViewHolder = new DashboardViewHolder(view);
  }

  @Override public void setupComponent() {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  public void onEventMainThread(InformationClick informationClick) {
    Intent intent = new Intent(this, DescriptionActivity.class);
    Information info = informationClick.getInformation();
    info.setStatus(informationClick.getStatus());
    intent.putExtra("information_data", info);
    startActivity(intent);
  }

  public void onEventMainThread(DbUpdated dbUpdated) {
    if (!dbUpdated.isDashboard()) refreshActivity();
  }

  public void onEventMainThread(DescriptionFinish descriptionFinish){
    refreshActivity();
  }

  public void refreshActivity(){
    ComponentFactory.getInstance().removeDashboardComponent();
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
    dashboardViewHolder.refreshHolder();
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    EventBus.getDefault().post(new DescriptionFinish());
  }
}
