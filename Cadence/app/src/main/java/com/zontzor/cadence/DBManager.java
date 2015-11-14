package com.zontzor.cadence;

/**
 * Created by Zontzor on 2015-11-14.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class DBManager {
    private static final int DATABASE_VERSION = 11;

    private static final String DATABASE_NAME = "Cadence.db";

    private static final String TABLE_USERS = "users";
    private static final String KEY_USER_ID = "_userid";
    private static final String KEY_USER_USERNAME = "username";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_FIRSTNAME = "firstname";
    private static final String KEY_USER_LASTNAME = "lastname";

    private static final String TABLE_BICYCLES = "bicycles";
    private static final String KEY_BICYCLE_ID = "_bicycleid";
    private static final String KEY_BICYCLE_NAME = "bicyclename";
    private static final String KEY_BICYCLE_TYPE = "bicycletype";
    private static final String KEY_BICYLE_BRAND = "bicyclebrand";
    private static final String KEY_BICYCLE_NOTES = "bicyclenotes";
    private static final String KEY_BICYCLE_USERID = "_userid";

    private static final String TABLE_RIDES = "rides";
    private static final String KEY_RIDE_ID = "_rideid";
    private static final String KEY_RIDE_RIDENAME = "ridename";
    private static final String KEY_RIDE_RIDEDURATION = "rideduration";
    private static final String KEY_RIDE_USERID = "_userid";
    private static final String KEY_RIDE_BICYCLEID = "_bicycleid";

    private static final String CREATE_USERS_TABLE =
        "CREATE TABLE " + TABLE_USERS + " (" +
            KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_USER_USERNAME + " TEXT," +
            KEY_USER_PASSWORD + " TEXT," +
            KEY_USER_FIRSTNAME + " TEXT," +
            KEY_USER_LASTNAME + " TEXT);";

    private static final String CREATE_BICYCLE_TABLE =
        "CREATE TABLE " + TABLE_BICYCLES + " (" +
            KEY_BICYCLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_BICYCLE_NAME + " TEXT," +
            KEY_BICYCLE_TYPE + " TEXT," +
            KEY_BICYLE_BRAND + " TEXT," +
            KEY_BICYCLE_NOTES  + " TEXT," +
            KEY_BICYCLE_USERID  + " INTEGER," +
            "FOREIGN KEY(" + KEY_BICYCLE_USERID + ") REFERENCES " + TABLE_USERS +
                "(" + KEY_USER_ID + "));";

    private static final String CREATE_RIDE_TABLE =
        "CREATE TABLE " + TABLE_RIDES + " (" +
            KEY_RIDE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_RIDE_RIDENAME + " TEXT," +
            KEY_RIDE_RIDEDURATION + " INTEGER," +
            KEY_RIDE_USERID + " INTEGER," +
            KEY_RIDE_BICYCLEID + " INTEGER," +
            "FOREIGN KEY(" + KEY_RIDE_USERID + ") REFERENCES " + TABLE_USERS +
                "(" + KEY_USER_ID + ")," +
            "FOREIGN KEY(" + KEY_RIDE_BICYCLEID + ") REFERENCES " + TABLE_BICYCLES +
                "(" + KEY_BICYCLE_ID + "));";

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
            db.execSQL(CREATE_USERS_TABLE);
            db.execSQL(CREATE_BICYCLE_TABLE);
            db.execSQL(CREATE_RIDE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BICYCLES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RIDES);

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

    public long insertUser(String uName, String passwd, String fName, String lName) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USER_USERNAME, uName);
        initialValues.put(KEY_USER_PASSWORD, passwd);
        initialValues.put(KEY_USER_FIRSTNAME, fName);
        initialValues.put(KEY_USER_LASTNAME, lName);
        return db.insert(TABLE_USERS, null, initialValues);
    }

    public long insertBicycle(String bikeName, String bikeType, String bikeBrand, String bikeNotes, int uId) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_BICYCLE_NAME, bikeName);
        initialValues.put(KEY_BICYCLE_TYPE, bikeType);
        initialValues.put(KEY_BICYLE_BRAND, bikeBrand);
        initialValues.put(KEY_BICYCLE_NOTES, bikeNotes);
        initialValues.put(KEY_BICYCLE_USERID, uId);
        return db.insert(TABLE_BICYCLES, null, initialValues);
    }

    public long insertRide(String rName, int dur, int uId, int bikeId) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_RIDE_RIDENAME, rName);
        initialValues.put(KEY_RIDE_RIDEDURATION, dur);
        initialValues.put(KEY_RIDE_USERID, uId);
        initialValues.put(KEY_RIDE_BICYCLEID, bikeId);
        return db.insert(TABLE_RIDES, null, initialValues);
    }
}