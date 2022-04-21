package com.it1k10.qltv.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.it1k10.qltv.database.DbHelper;
import com.it1k10.qltv.model.LoaiSach;
import com.it1k10.qltv.model.ThanhVien;
import com.it1k10.qltv.model.ThuThu;

public class DemoDB {
    private SQLiteDatabase db;
    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
    LoaiSachDAO loaiSachDAO;
    static final String TAG = "//==========";


    public DemoDB(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDAO= new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO(context);
        loaiSachDAO=new LoaiSachDAO(context);

    }
    //
    public void thanhVien() {
        //List<ThanhVien> ls = new ArrayList<>();
        ThanhVien tv = new ThanhVien(1, "Mai Thi Oanh", "2000");
        if (thanhVienDAO.insert(tv) > 0) {
            Log.i(TAG, "them tv thanh cong");
        } else {
            Log.i(TAG, "them tv khong thanh cong");
        }
        Log.i(TAG, "==================");
        Log.i(TAG, "Tong so thanh vien: " + thanhVienDAO.getAll().size());

        Log.i(TAG, "==================Sau khi sua");
        tv = new ThanhVien(0, "Mai Thi Oanh", "2000");
        thanhVienDAO.update(tv);
        Log.i(TAG, "Tong so thanh vien:" + thanhVienDAO.getAll().size());
        thanhVienDAO.delete("1");
        Log.i(TAG, "==================Sau khi xóa");
        Log.i(TAG, "Tong so thanh vien: " + thanhVienDAO.getAll().size());
    }
    //
    public void thuThu(){
        ThuThu tt = new ThuThu("admin", "Nguyen AMIN", "adimn123");
        thuThuDAO.insert(tt);
        Log.i(TAG, "==================");
        Log.i(TAG, "Tong so thanh vien: " + thuThuDAO.getAll().size());

        tt = new ThuThu("admin", "Tran ADMIN","admin123456" );
        thuThuDAO.updatePass(tt);
        Log.i(TAG, "==================");
        Log.i(TAG, "Tong so thanh vien: " + thuThuDAO.getAll().size());
        if(thuThuDAO.checkLogin("admin","admin1123456")>0) {
            Log.i(TAG, "Dang nhap thanh cong");
        }else{
            Log.i(TAG, "Dangv nhap khong thanh cong");
        }
    }
    //
    public void loaiSach(){
        LoaiSach ls = new LoaiSach("2", "Toán học 2");
        loaiSachDAO.insert(ls);
        Log.i(TAG, "==================");
        Log.i(TAG, "Tong so loai sach: " + loaiSachDAO.getAll().size());

        ls= new LoaiSach("2","Toán1");
        loaiSachDAO.update(ls);
        Log.i(TAG, "==================");
        Log.i(TAG, "Tong so loại sach: " + loaiSachDAO.getAll().size());
    }
}
