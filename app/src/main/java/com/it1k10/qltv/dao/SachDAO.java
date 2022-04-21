package com.it1k10.qltv.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.it1k10.qltv.database.DbHelper;
import com.it1k10.qltv.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.insert("Sach",null, values );
    }
    public int update(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(obj.getMaSach())});
    }
    public int delete(String id){
        return db.delete("Sach", "maSach=?", new String[]{id});
    }
    @SuppressLint("Range")
    public List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<Sach>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt((c.getString(c.getColumnIndex("maSach")))));
            obj.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            obj.setGiaThue(Integer.parseInt((c.getString(c.getColumnIndex("giaThue")))));
            obj.setMaLoai(Integer.parseInt((c.getString(c.getColumnIndex("maLoai")))));
            list.add(obj);
        }
        return list;
    }
    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }
    public Sach getID(String id){
        String sql = "SELECT * FROM Sach where maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }
    }

