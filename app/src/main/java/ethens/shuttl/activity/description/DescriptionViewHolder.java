package ethens.shuttl.activity.description;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import de.greenrobot.event.EventBus;
import ethens.shuttl.Information;
import ethens.shuttl.R;
import ethens.shuttl.di.ComponentFactory;
import ethens.shuttl.eventbus.ButtonClick;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

/**
 * Created by ethens on 11/10/17.
 */

public class DescriptionViewHolder {

  @Inject Context context;
  TextView name, title, time, text, description;
  ImageView image;
  Button button;

  public DescriptionViewHolder(View view, Information information) {
    ComponentFactory.getInstance().getDashboardComponent().inject(this);
    name = (TextView) view.findViewById(R.id.name);
    image = (ImageView) view.findViewById(R.id.image);
    title = (TextView) view.findViewById(R.id.title);
    time = (TextView) view.findViewById(R.id.date);
    text = (TextView) view.findViewById(R.id.text);
    description = (TextView) view.findViewById(R.id.description);
    button = (Button) view.findViewById(R.id.button);

    setData(information);
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

    if (information.getStatus() == null) {
      button.setText("LIKE");
    } else {
      button.setText(information.getStatus());
    }
    time.setText(new SimpleDateFormat("dd MMM yy hh:mm", Locale.UK).format(
        new Date(information.getTime())));

    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String status = button.getText().toString().equals("LIKE") ? "UNLIKE" : "LIKE";
        button.setText(status);
        EventBus.getDefault()
            .post(new ButtonClick(button.getText().toString(), information.getId(),false));
      }
    });

    description.setText(information.getDescription());
  }
}
