package ethens.shuttl.activity.description;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import ethens.shuttl.Information;
import ethens.shuttl.R;
import ethens.shuttl.common.BaseActivity;
import ethens.shuttl.di.ComponentFactory;

/**
 * Created by ethens on 11/10/17.
 */

public class DescriptionActivity extends BaseActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupUI(R.layout.activity_description, R.id.description_layout);
  }

  @Override public void setupComponent() {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
  }

  @Override protected void setupViewHolder(View view) {
    Intent intent = getIntent();
    Information information = intent.getParcelableExtra("information_data");
    new DescriptionViewHolder(view, information);
  }
}
