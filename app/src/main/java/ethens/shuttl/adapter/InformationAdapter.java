package ethens.shuttl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import de.greenrobot.event.EventBus;
import ethens.shuttl.Information;
import ethens.shuttl.R;
import ethens.shuttl.common.SharedPrefUtils;
import ethens.shuttl.di.ComponentFactory;
import ethens.shuttl.eventbus.ButtonClick;
import ethens.shuttl.eventbus.InformationClick;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

/**
 * Created by ethens on 11/10/17.
 */

public class InformationAdapter
    extends RecyclerView.Adapter<InformationAdapter.InformationViewHolder> {

  @Inject Context context;
  @Inject SharedPrefUtils sharedPrefUtils;
  List<Information> mInformationList;

  public InformationAdapter(List<Information> informationList) {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
    mInformationList = informationList;
  }

  @Override public InformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);
    return new InformationViewHolder(v);
  }

  @Override public void onBindViewHolder(InformationViewHolder holder, int position) {
    Information information = mInformationList.get(position);
    holder.setData(information);
  }

  @Override public int getItemCount() {
    return mInformationList.size();
  }

  public void setData(List<Information> informationList) {
    mInformationList = informationList;
    notifyDataSetChanged();
  }

  public class InformationViewHolder extends RecyclerView.ViewHolder {
    TextView name, title, time, text;
    ImageView image;
    Button button;
    LinearLayout item, layoutTime;

    InformationViewHolder(View view) {
      super(view);
      name = (TextView) view.findViewById(R.id.name);
      image = (ImageView) view.findViewById(R.id.image);
      title = (TextView) view.findViewById(R.id.title);
      time = (TextView) view.findViewById(R.id.date);
      text = (TextView) view.findViewById(R.id.text);
      button = (Button) view.findViewById(R.id.button);
      item = (LinearLayout) view.findViewById(R.id.item);
      layoutTime = (LinearLayout) view.findViewById(R.id.layout_time);
    }

    public void setData(final Information information) {
      if (information.getText() == null) {
        text.setVisibility(View.GONE);
      } else {
        text.setVisibility(View.VISIBLE);
        text.setText(information.getText());
      }

      if (information.getUrl() == null) {
        image.setVisibility(View.GONE);
      } else {
        image.setVisibility(View.VISIBLE);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(context.getApplicationContext()));
        builder.build()
            .load(information.getUrl())
            .placeholder(R.drawable.no_image_background)
            .into(image);
      }

      name.setText(String.format("From %s", information.getName()));
      title.setText(information.getTitle());
      if (information.getStatus() == null) button.setText("LIKE");
      else button.setText(information.getStatus());

      if (information.getState() == 1) {
        time.setVisibility(View.VISIBLE);
        time.setText(new SimpleDateFormat("dd MMM yy hh:mm", Locale.UK).format(
            new Date(information.getTime())));
        layoutTime.setVisibility(View.VISIBLE);
      } else {
        layoutTime.setVisibility(View.GONE);
      }

      item.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          EventBus.getDefault().post(new InformationClick(information,button.getText().toString()));
        }
      });

      button.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          String status = button.getText().toString().equals("LIKE") ? "UNLIKE" : "LIKE";
          button.setText(status);
          EventBus.getDefault()
              .post(new ButtonClick(button.getText().toString(), information.getId(),true));
        }
      });
    }
  }
}
