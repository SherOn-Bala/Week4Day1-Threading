package ca.judacribz.week4day1_threading.models;

import java.util.Locale;

public class CelebrityDBContract {
    public static final String DATABASE_NAME = "celebrity_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Celebrity_Table";
    public static final String STMT_CREATE_TABLE =
            "CREATE TABLE %s( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER)";

    public static final String COL_ID = "id";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_AGE = "age";

    public static final String DROP_TABLE_QUERY = "DROP TABLE " + TABLE_NAME;
    public static final String SELECT_ALL_CELEBRITIES = "SELECT * FROM " + TABLE_NAME;

    public static String getCreateTableQuery() {
        return String.format(
                Locale.US,
                STMT_CREATE_TABLE,
                TABLE_NAME,
                COL_ID,
                COL_FIRST_NAME,
                COL_LAST_NAME,
                COL_AGE
        );
    }

    public static String getCelebrityByIdQuery(long id) {
        return String.format(
                Locale.US,
                "SELECT * FROM %s WHERE %s = \'%s\'",
                TABLE_NAME,
                COL_ID,
                id
        );
    }
}
