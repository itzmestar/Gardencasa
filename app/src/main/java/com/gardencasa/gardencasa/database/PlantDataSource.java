package com.gardencasa.gardencasa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by TARIQUE on 30-04-2017.
 */

public class PlantDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { "name", "desc", "type", "image", "bloomTime", "flower", "plant_id" };
    public static final String TABLE_PLANTS = "plants";

    public PlantDataSource(Context context) {
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

    public Plant createPlant(long plant_id) {
        ContentValues values = new ContentValues();
        values.put("plant_id", plant_id);
        long insertId = database.insert(TABLE_PLANTS, null,
                values);
        Cursor cursor = database.query(TABLE_PLANTS,
                allColumns, "plant_id" + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Plant newPlant = cursorToPlant(cursor);
        cursor.close();
        return newPlant;
    }

    public void deletePlant(Plant plant) {
        long id = plant.getPlantId();
        System.out.println("Plant deleted with id: " + id);
        database.delete(TABLE_PLANTS, "plant_id"
                + " = " + id, null);
    }

    public ArrayList<Plant> getAllPlant() {
        ArrayList<Plant> plants = new ArrayList<Plant>();

/*        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                // Toast.makeText(activityName.this, "Table Name=> "+c.getString(0), Toast.LENGTH_LONG).show();
                System.out.println("Table:"+c.getString(0));
                c.moveToNext();
            }
        }
        c.close();*/

        Cursor cursor = database.query(TABLE_PLANTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Plant plant = cursorToPlant(cursor);
            plants.add(plant);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return plants;
    }

    private Plant cursorToPlant(Cursor cursor) {
        Plant plant = new Plant();
        plant.setPlantId(cursor.getLong(6));
        plant.setName(cursor.getString(0));
        plant.setDesc(cursor.getString(1));
        plant.setType(cursor.getString(2));
        plant.setImage(cursor.getString(3));
        plant.setBloomTime(cursor.getString(4));
        plant.setFlowering(cursor.getString(5));
        return plant;
    }

}
