package com.example.tareamvc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tareamvc.entity.Employee;

import java.util.ArrayList;

public class DbEmployees extends DbHelper {

    Context context;

    public DbEmployees(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertEmployee(String name, String lastNames, String age, String address, String position) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("lastNames", lastNames);
            values.put("age", age);
            values.put("address", address);
            values.put("position", position);

            id = db.insert(EMPLOYEES_TABLE, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Employee> showEmployees() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ArrayList<Employee> listEmployees = new ArrayList<>();
        Employee employee;
        Cursor cursorEmployees;

        cursorEmployees = db.rawQuery("SELECT * FROM " + EMPLOYEES_TABLE, null);

        if (cursorEmployees.moveToFirst()) {
            do {
                employee = new Employee();
                employee.setId(cursorEmployees.getInt(0));
                employee.setName(cursorEmployees.getString(1));
                employee.setLastNames(cursorEmployees.getString(2));
                employee.setAge(cursorEmployees.getString(3));
                employee.setAddress(cursorEmployees.getString(4));
                employee.setPosition(cursorEmployees.getString(5));
                listEmployees.add(employee);
            } while (cursorEmployees.moveToNext());
        }

        cursorEmployees.close();

        return  listEmployees;
    }

    // SHOW EMPLOYEE BY ID
    public Employee showEmployee(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Employee employee = null;
        Cursor cursorEmployee;

        cursorEmployee = db.rawQuery("SELECT * FROM " + EMPLOYEES_TABLE + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorEmployee.moveToFirst()) {
            employee = new Employee();
            employee.setId(cursorEmployee.getInt(0));
            employee.setName(cursorEmployee.getString(1));
            employee.setLastNames(cursorEmployee.getString(2));
            employee.setAge(cursorEmployee.getString(3));
            employee.setAddress(cursorEmployee.getString(4));
            employee.setPosition(cursorEmployee.getString(5));
        }

        cursorEmployee.close();

        return employee;
    }

    /* UPDATE EMPLOYEE */
    public boolean updateEmployee(int id, String name, String lastNames, String age, String address, String position) {

        boolean correct = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + EMPLOYEES_TABLE + " SET name = '" + name + "', lastNames = '" + lastNames + "', age = '" + age +"', address = '" + address +"', position = '" + position + "' WHERE id='" + id + "' ");
            correct = true;
        } catch (Exception ex) {
            ex.toString();
            correct = false;
        } finally {
            db.close();
        }

        return correct;
    }

    /* DELETE EMPLOYEE BY ID */
    public boolean deleteEmployee(int id) {

        boolean correct = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + EMPLOYEES_TABLE + " WHERE id = '" + id + "'");
            correct = true;
        } catch (Exception ex) {
            ex.toString();
            correct = false;
        } finally {
            db.close();
        }

        return correct;
    }
}


