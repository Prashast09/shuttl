package ethens.shuttl.eventbus;

/**
 * Created by ethens on 11/10/17.
 */

public class DbUpdated {
  public boolean isDashboard() {
    return isDashboard;
  }

  public DbUpdated(boolean isDashboard) {

    this.isDashboard = isDashboard;
  }

  boolean isDashboard;
}
