package ethens.shuttl.database;

/**
 * Created by ethens on 10/10/17.
 */

public final class ShuttlContract {

  public static abstract class Shuttl {
    public static final String TABLE_NAME = "shuttl";
    public static final String _ID = "_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_IMAGE = "image";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_TEXT = "text";
    public static final String COLUMN_NAME_TIME = "time";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_STATUS = "status";
  }
}
