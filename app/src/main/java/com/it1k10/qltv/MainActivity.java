package com.it1k10.qltv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.it1k10.qltv.dao.DemoDB;
import com.it1k10.qltv.dao.PhieuMuonDAO;
import com.it1k10.qltv.dao.ThuThuDAO;
import com.it1k10.qltv.fragment.ChanegPassFragment;
import com.it1k10.qltv.fragment.DoanhThuFragment;
import com.it1k10.qltv.fragment.LoaiSachFragment;
import com.it1k10.qltv.fragment.PhieuMuonFragment;
import com.it1k10.qltv.fragment.SachFragment;
import com.it1k10.qltv.fragment.ThanhVienFragment;
import com.it1k10.qltv.fragment.TopFragment;
import com.it1k10.qltv.model.ThuThu;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;
    ThuThuDAO thuThuDAO;
    PhieuMuonDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);

        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager= getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.flCintent, phieuMuonFragment)
                .commit();
        NavigationView nv = findViewById(R.id.nvView);
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        edUser.setText("Welcome " +username+"!");
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager= getSupportFragmentManager();

                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        Toast.makeText(getApplicationContext(),"Quản lý phiếu mượn", Toast.LENGTH_SHORT).show();
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction()
                                .replace(R.id.flCintent, phieuMuonFragment)
                                .commit();
                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lý loại sách");
                        Toast.makeText(getApplicationContext(),"Quản lý loại sách", Toast.LENGTH_SHORT).show();
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flCintent, loaiSachFragment)
                                .commit();
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lý sách");
                        Toast.makeText(getApplicationContext(),"Quản lý sách", Toast.LENGTH_SHORT).show();
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flCintent, sachFragment)
                                .commit();
                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý thành viên");
                        Toast.makeText(getApplicationContext(),"Quản lý thành viên", Toast.LENGTH_SHORT).show();
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction()
                                .replace(R.id.flCintent, thanhVienFragment)
                                .commit();
                        break;
                    case R.id.sub_Top:
                        setTitle("Top 10 sách cho thuê nhiều nhất");
                        Toast.makeText(getApplicationContext(),"Top 10 sách cho thuê nhiều nhất", Toast.LENGTH_SHORT).show();
                        TopFragment topFragment = new TopFragment();
                        manager.beginTransaction()
                                .replace(R.id.flCintent, topFragment)
                                .commit();
                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Thống kê doanh thu");
                        Toast.makeText(getApplicationContext(),"Thống kê doanh du", Toast.LENGTH_SHORT).show();
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.flCintent, doanhThuFragment)
                                .commit();
                        break;
                    case R.id.sub_AddduUser:
                        setTitle("Thêm người dùng");
                        Toast.makeText(getApplicationContext(),"Thêm người dùng", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.sub_Pass:
                        setTitle("Thay đổi mật khẩu");
                        Toast.makeText(getApplicationContext(),"Thay đổi mật khẩu", Toast.LENGTH_SHORT).show();
                        ChanegPassFragment chanegPassFragment = new ChanegPassFragment();
                        manager.beginTransaction()
                                .replace(R.id.flCintent, chanegPassFragment)
                                .commit();
                        break;
                    case R.id.sub_Logout:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;
                }

                drawer.closeDrawers();
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
    int id=item.getItemId();
    if (id==android.R.id.home)
        drawer.openDrawer(GravityCompat.START);
    return  super.onOptionsItemSelected(item);
    }
}