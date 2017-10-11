package ethens.shuttl.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import ethens.shuttl.R;

/**
 * Created by ethens on 10/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

  protected Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupComponent();
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
  }

  abstract public void setupComponent();

  protected void setupUI(int layoutId, int viewHolderId) {
    setContentView(layoutId);
    setupViewHolder(findViewById(viewHolderId));
  }

  abstract protected void setupViewHolder(View view);


}
