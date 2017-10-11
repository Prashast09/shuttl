package ethens.shuttl.eventbus;

import ethens.shuttl.Information;

/**
 * Created by ethens on 11/10/17.
 */

public class InformationClick {

  Information information;
  String status;

  public String getStatus() {
    return status;
  }

  public InformationClick(Information information, String s) {
    this.information = information;
    this.status = s;
  }

  public Information getInformation() {
    return information;
  }
}
