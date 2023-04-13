package com.example.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseControl {

    SQLiteDatabase database;
    DatabaseHelper helper;

    public DatabaseControl(Context context) {

        helper = new DatabaseHelper(context);

    }

    public void open() {

        database = helper.getWritableDatabase();

    }

    public void close() {

        helper.close();

    }

    public boolean insert(String name, String developer, String year) {

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("developer", developer);
        values.put("year", year);
        return database.insert("games", null, values) > 0;

    }

    public void delete(String n) {

        database.delete("games",  "name = \""+n+"\"", null);

    }

    public String getDeveloper(String name) {

        String query = "select developer from games where name = \""+name+"\"";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String developer = cursor.getString(0);
        cursor.close();
        return developer;

    }

    public String getYear(String name) {

        String query = "select year from games where name = \""+name+"\"";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        String year = cursor.getString(0);
        cursor.close();
        return year;

    }

    public String[] getAllNamesArray() {

        String query = "select name from games";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<String> list = new ArrayList<String>();
        while(!cursor.isAfterLast()) {

            String name = cursor.getString(0);
            list.add(name);
            cursor.moveToNext();

        }

        cursor.close();
        String[] array = new String[list.size()];
        return list.toArray(array);

    }

    public String[] getAllDevelopersArray() {

        String query = "select developer from games";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<String> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {

            String developer = cursor.getString(0);
            list.add(developer);
            cursor.moveToNext();


        }

        cursor.close();
        String[] array = new String[list.size()];
        return list.toArray(array);

    }

    public String[] getAllYearsArray() {

        String query = "select year from games";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<String> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {

            String year = cursor.getString(0);
            list.add(year);
            cursor.moveToNext();


        }

        cursor.close();
        String[] array = new String[list.size()];
        return list.toArray(array);

    }

}
