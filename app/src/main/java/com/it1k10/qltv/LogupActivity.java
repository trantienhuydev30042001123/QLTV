package com.it1k10.qltv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.it1k10.qltv.adapter.ThanhVienAdapter;
import com.it1k10.qltv.dao.ThuThuDAO;
import com.it1k10.qltv.database.Data_SQLite;
import com.it1k10.qltv.model.ThanhVien;
import com.it1k10.qltv.model.ThuThu;

import java.util.ArrayList;


public class LogupActivity extends AppCompatActivity {
    EditText edUserName,edHoVaTen, edPassword;
    Button btnLogup, btnExit;
    ThuThuDAO dao;
    ThuThu item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);
        setTitle("ĐĂNG KÝ");
        edUserName = findViewById(R.id.edUserName);
        edHoVaTen = findViewById(R.id.edTenTT);
        edPassword = findViewById(R.id.edPassword);
        btnLogup= findViewById(R.id.btnLogup1);
        btnExit = findViewById(R.id.btnExit1);
//        chkRememberPass = findViewById(R.id.chkRememberPass);

        dao = new ThuThuDAO(this);

//        // doc user, pass trong SharedPreferences


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnLogup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new ThuThu();
                item.setMaTT(edUserName.getText().toString());
                item.setHoTen(edHoVaTen.getText().toString());
                item.setMatKhau(edPassword.getText().toString());

                if (edUserName.getText().length() == 0 || edHoVaTen.getText().length() == 0){
                    Toast.makeText(LogupActivity.this,"Vui lòng nhập đầy đủ thông tin để đăng ký tài khoản",Toast.LENGTH_LONG).show();

                }
                // nếu đầy đủ thông tin nhập vào thì add tài khoản vào database
                else{
                    if(dao.insert(item)>0){
                        Toast.makeText(LogupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(LogupActivity.this, "Tài khoản đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });



}}