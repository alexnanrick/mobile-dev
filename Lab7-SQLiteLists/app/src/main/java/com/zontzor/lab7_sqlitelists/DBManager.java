package com.zontzor.lab7_sqlitelists;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Zontzor on 2015-10-28.
 */
public class DBManager {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "TaskList";

    private static final String TABLE_TASKS = "Tasks";

    private static final String KEY_ID = "_id";
    private static final String KEY_TASK_NAME = "name";
    private static final String KEY_TASK_DESCRIPTION = "description";
    private static final String KEY_TASK_STATUS = "status";

    private static final String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "(" +
            KEY_ID + " INTEGER PRIMARY KEY autoincrement," +
            KEY_TASK_NAME + " TEXT," +
            KEY_TASK_DESCRIPTION + " TEXT" +
            KEY_TASK_STATUS + "INTEGER" + ")";

    private final Context context;
    private MyDatabaseHelper DBHelper;
    private SQLiteDatabase db;

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

    public long insertTask(String name, String desc) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TASK_NAME, name);
        initialValues.put(KEY_TASK_DESCRIPTION, desc);
        return db.insert(TABLE_TASKS, null, initialValues);
    }

    public Cursor getTask(String query)
    {
        Cursor mCursor = db.rawQuery(
                "SELECT description FROM Tasks WHERE name LIKE " + "'" + query + "';", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }

    public Cursor getAll()
    {
        Cursor mCursor = db.rawQuery(
                "SELECT * FROM Tasks;", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;

    }
}
