package ethens.shuttl.database;

/**
 * Created by ethens on 10/10/17.
 */

public class Schema {
  public static final String TEXT_TYPE = " TEXT";
  public static final String INTEGER_TYPE = " INTEGER";
  private static final String COMMA_SEP = ",";
  private static final String PRIMARY_KEY = " PRIMARY KEY";
  private static final String AUTOINCREMENT = " AUTOINCREMENT";
  private static final String CREATE_TABLE = "CREATE TABLE ";

  public static final String SQL_CREATE_SHUTTL = CREATE_TABLE
      + ShuttlContract.Shuttl.TABLE_NAME
      + " ("
      + ShuttlContract.Shuttl._ID
      + INTEGER_TYPE
      + PRIMARY_KEY
      + AUTOINCREMENT
      + COMMA_SEP
      + ShuttlContract.Shuttl.COLUMN_NAME_TIME
      + INTEGER_TYPE
      + COMMA_SEP
      + ShuttlContract.Shuttl.COLUMN_NAME_NAME
      + TEXT_TYPE
      + COMMA_SEP
      + ShuttlContract.Shuttl.COLUMN_NAME_IMAGE
      + TEXT_TYPE
      + COMMA_SEP
      + ShuttlContract.Shuttl.COLUMN_NAME_TITLE
      + TEXT_TYPE
      + COMMA_SEP
      + ShuttlContract.Shuttl.COLUMN_NAME_TEXT
      + TEXT_TYPE
      + COMMA_SEP
      + ShuttlContract.Shuttl.COLUMN_NAME_DESCRIPTION
      + TEXT_TYPE
      + COMMA_SEP
      + ShuttlContract.Shuttl.COLUMN_NAME_STATUS
      + TEXT_TYPE
      + " )";
}
