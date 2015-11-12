package com.zontzor.lab8_networking;

/**
 * Created by nanrick on 12/11/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Zontzor on 2015-11-12.
 */
public class DBManager {
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "TaskList";

    private static final String TABLE_TASKS = "Tasks";

    private static final String KEY_ID = "_id";
    private static final String KEY_TASK_TITLE = "title";
    private static final String KEY_TASK_COMPLETED = "completed";

    private static final String CREATE_TASKS_TABLE =
            "CREATE TABLE Tasks (_id INTEGER PRIMARY KEY, title TEXT, completed TEXT);";

    private final Context context;
    private MyDatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // we must pass the context from our class that we called from
    public DBManager(Context ctx) {
        this.context = ctx;
        DBHelper = new MyDatabaseHelper(context);
    }

    private static class MyDatabaseHelper extends SQLiteOpenHelper {

        public MyDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TASKS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

            onCreate(db);
        }
    }

    public DBManager open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    public long insertTask(String id, String title, String comp) {
        ContentValues initialValues = new ContentValues();
        initialValues.put (KEY_ID, id);
        initialValues.put (KEY_TASK_TITLE, title);
        initialValues.put (KEY_TASK_COMPLETED, comp);
        return db.insert(TABLE_TASKS, null, initialValues);
    }

    public Cursor getTask(String query)
    {
        Cursor mCursor = db.rawQuery(
                "SELECT title FROM Tasks WHERE id LIKE " + "'" + query + "';", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }

    public Cursor getAll()
    {
        Cursor mCursor = db.rawQuery(
                "SELECT * FROM Tasks WHERE status = 'true';", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }
}