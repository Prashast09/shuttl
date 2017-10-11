package ethens.shuttl.activity.dashboard;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import de.greenrobot.event.EventBus;
import ethens.shuttl.Information;
import ethens.shuttl.R;
import ethens.shuttl.adapter.InformationAdapter;
import ethens.shuttl.common.SharedPrefUtils;
import ethens.shuttl.database.dao.InformationDAO;
import ethens.shuttl.di.ComponentFactory;
import ethens.shuttl.eventbus.ButtonClick;
import ethens.shuttl.eventbus.DbInsertionComplete;
import ethens.shuttl.eventbus.DbUpdated;
import ethens.shuttl.eventbus.StartDbInsertion;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ethens on 10/10/17.
 */

public class DashboardViewHolder {

  ProgressBar progressBar;
  @Inject @Named("list") ArrayList<Information> informationList;
  @Inject SharedPrefUtils sharedPrefUtils;
  @Inject Context context;
  @Inject InformationDAO informationDAO;
  private RecyclerView recyclerView;

  public DashboardViewHolder(View view) {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);

    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    EventBus.getDefault().register(this, 1);

    renderUi();
  }

  public void onEventBackgroundThread(StartDbInsertion startDbInsertion) {
    informationDAO.saveInformation(informationList);
  }

  public void onEventMainThread(DbInsertionComplete dbInsertionComplete) {
    sharedPrefUtils.putLong("data", 1L);
    fetchDataFromDb();
    displayRecyclerView();
  }

  public void onEventMainThread(ButtonClick buttonClick){
    informationDAO.updateStatus(buttonClick.getId(), buttonClick.getStatus(),buttonClick.getDashboard());
  }

  private void renderUi() {

    if (sharedPrefUtils.getLongDataByKey("data", 0L) == 0L) {
      recyclerView.setVisibility(View.GONE);
      progressBar.setVisibility(View.VISIBLE);
      EventBus.getDefault().post(new StartDbInsertion());
    } else {
      refreshHolder();
    }
  }

  private void setupComponent(){
    ComponentFactory.getInstance().removeDashboardComponent();
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }
  public void displayRecyclerView() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
    recyclerView.setLayoutManager(linearLayoutManager);
    modifyList();
    InformationAdapter informationAdapter = new InformationAdapter(informationList);
    recyclerView.setAdapter(informationAdapter);
    progressBar.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
    sharedPrefUtils.putLong("time", (long) Integer.MAX_VALUE);
  }

  private void modifyList() {
    ArrayList<Information> newInfoList = new ArrayList<>();
    Information newInfo;
    for (Information information : informationList) {
      newInfo = information;
      if (sharedPrefUtils.getLongDataByKey("time", (long) Integer.MAX_VALUE)
          != information.getTime()) {
        newInfo.setState(1);
        sharedPrefUtils.putLong("time", information.getTime());
      } else {
        newInfo.setState(0);
      }
      newInfoList.add(newInfo);
    }

    informationList = new ArrayList<>(newInfoList);
  }

  private void fetchDataFromDb() {
    informationList = informationDAO.getInformationList();
  }

  public void refreshHolder(){
    fetchDataFromDb();
    displayRecyclerView();
  }
}
