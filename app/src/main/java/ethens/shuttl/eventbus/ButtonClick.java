package ethens.shuttl.eventbus;

/**
 * Created by ethens on 11/10/17.
 */

public class ButtonClick {
  String status;
  Long id;
  Boolean isDashboard;

  public Boolean getDashboard() {
    return isDashboard;
  }

  public ButtonClick(String status, Long id,Boolean isDashboard) {
    this.id = id;
    this.status = status;
    this.isDashboard = isDashboard;
  }

  public Long getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }
}
