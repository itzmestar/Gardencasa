package com.gardencasa.gardencasa.database;

/**
 * Created by TARIQUE on 23-04-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class AlarmDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { "alarm_id", "name", "desc", "type", "image",
            "bloomTime", "flower", "timeHour", "timeMinute", "active" };
    public static final String TABLE_ALARM = "alarms";

    public AlarmDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
        try {
            dbHelper.createDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
    }

    public void open() throws SQLException {
       // database = dbHelper.getWritableDatabase();
        try {
            database=  dbHelper.openDatabase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }

 /*   public void close() {
        dbHelper.close();
    }*/

    public void close() {
        dbHelper.closeDataBase();
    }

    public Alarm createAlarm(long plant_id) {
        ContentValues values = new ContentValues();
        values.put("plant_id", plant_id);
        long insertId = database.insert(TABLE_ALARM, null,
                values);
        Cursor cursor = database.query(TABLE_ALARM,
                allColumns, "alarm_id" + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Alarm newAlarm = cursorToAlarm(cursor);
        cursor.close();
        return newAlarm;
    }

    public Alarm createAlarm(ContentValues values) {
       // ContentValues values = new ContentValues();
        //values.put("plant_id", plant_id);
        long insertId = database.insert(TABLE_ALARM, null,
                values);
        Cursor cursor = database.query(TABLE_ALARM,
                allColumns, "alarm_id" + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Alarm newAlarm = cursorToAlarm(cursor);
        cursor.close();
        return newAlarm;
    }

    public void deleteAlarm(Alarm alarm) {
        long id = alarm.getAlarmId();
        System.out.println("Alarm deleted with id: " + id);
        database.delete(TABLE_ALARM, "alarm_id"
                + " = " + id, null);
    }

    public void deleteAlarm(long id) {
       // long id = alarm.getAlarmId();
        System.out.println("Alarm deleted with id: " + id);
        database.delete(TABLE_ALARM, "alarm_id"
                + " = " + id, null);
    }

    public ArrayList<Alarm> getAllAlarm() {
        ArrayList<Alarm> alarms = new ArrayList<Alarm>();

/*        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
               // Toast.makeText(activityName.this, "Table Name=> "+c.getString(0), Toast.LENGTH_LONG).show();
                System.out.println("Table:"+c.getString(0));
                c.moveToNext();
            }
        }
        c.close();*/

        Cursor cursor = database.query(TABLE_ALARM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Alarm alarm = cursorToAlarm(cursor);
            alarms.add(alarm);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return alarms;
    }

    private Alarm cursorToAlarm(Cursor cursor) {
        Alarm alarm = new Alarm();
        alarm.setAlarmId(cursor.getLong(0));
        alarm.setName(cursor.getString(1));
        alarm.setDesc(cursor.getString(2));
        alarm.setType(cursor.getString(3));
        alarm.setImage(cursor.getString(4));
        alarm.setBloomTime(cursor.getString(5));
        alarm.setFlowering(cursor.getString(6));
        alarm.setTimeHour(cursor.getLong(7));
        alarm.setTimeMinute(cursor.getLong(8));
        alarm.setActive(cursor.getLong(9));
        return alarm;
    }
}
