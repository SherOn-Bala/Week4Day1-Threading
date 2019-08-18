package ca.judacribz.week4day1_threading.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static ca.judacribz.week4day1_threading.models.CelebrityDBContract.*;

public class CelebrityHelper extends SQLiteOpenHelper {
    public CelebrityHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    /* Helper Functions ----------------------------------------------------------------------- */
    private Celebrity getCelebrity(Cursor cursor, long id) {
        Celebrity celebrity = new Celebrity(
                cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME)),
                cursor.getString(cursor.getColumnIndex(COL_LAST_NAME)),
                cursor.getInt(cursor.getColumnIndex(COL_AGE))
        );

        celebrity.setId(id);

        return celebrity;
    }

    private ContentValues createContentValues(Celebrity celebrity) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_AGE, celebrity.getAge());
        contentValues.put(COL_FIRST_NAME, celebrity.getFirstName());
        contentValues.put(COL_LAST_NAME, celebrity.getLastName());
        contentValues.put(COL_AGE, celebrity.getAge());

        return contentValues;
    }
    /* --- END --- Helper Functions ------------------------------------------------------------ */

    /* Create --------------------------------------------------------------------------------- */
    public void insertCelebrity(Celebrity celebrity) {
        SQLiteDatabase database = this.getWritableDatabase();

        database.insert(TABLE_NAME, null, createContentValues(celebrity));
    }

    /* Retrieve ------------------------------------------------------------------------------- */
    public ArrayList<Celebrity> getAllCelebrities() {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        ArrayList<Celebrity> celebrities = new ArrayList<>();

        Cursor cursor = readableDatabase.rawQuery(SELECT_ALL_CELEBRITIES, null);
        if (cursor.moveToFirst()) {
            do {
                celebrities.add(getCelebrity(
                        cursor,
                        cursor.getLong(cursor.getColumnIndex(COL_ID))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return celebrities;
    }


    /* Update --------------------------------------------------------------------------------- */
    public void updateCelebrity(Celebrity celebrity) {
        SQLiteDatabase database = this.getWritableDatabase();

        database.update(
                TABLE_NAME,
                createContentValues(celebrity),
                COL_ID + " = ?",
                new String[]{String.valueOf(celebrity.getId())}
        );
    }

    /* Delete --------------------------------------------------------------------------------- */
    public void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, null, null);
    }
}
