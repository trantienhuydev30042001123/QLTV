package com.it1k10.qltv.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.it1k10.qltv.database.DbHelper;
import com.it1k10.qltv.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("maLoai", obj.getMaLoai());
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("LoaiSach",null, values );
    }
    public int update(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.update("LoaiSach", values, "maLoai=?", new String[]{String.valueOf(obj.getMaLoai())});
    }
    public int delete(String id){
        return db.delete("LoaiSach", "maLoai=?", new String[]{id});
    }
    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<LoaiSach>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setMaLoai((c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai((c.getString(c.getColumnIndex("tenLoai"))));
            Log.i("//======", obj.toString());
            list.add(obj);
        }
        return list;
    }
    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }
    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach where maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        return list.get(0);
    }
}
