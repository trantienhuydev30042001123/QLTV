package com.it1k10.qltv.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.it1k10.qltv.R;
import com.it1k10.qltv.dao.SachDAO;
import com.it1k10.qltv.dao.ThanhVienDAO;
import com.it1k10.qltv.fragment.PhieuMuonFragment;
import com.it1k10.qltv.model.PhieuMuon;
import com.it1k10.qltv.model.Sach;
import com.it1k10.qltv.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


@RequiresApi(api = Build.VERSION_CODES.N)
public class PhieuMuonAdapter<date> extends ArrayAdapter<PhieuMuon> {

    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView ìmgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
        super(context, 0, lists);
        this.context=context;
        this.lists=lists;
        this.fragment=fragment;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phieu_muon_item, null);
        }

        final PhieuMuon item = lists.get(position);
        if(item != null){
            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiễu:" +item.getMaPM());

            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.getTenSach());
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành viên: "+thanhVien.getHoTen());
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            tvNgay = v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if(item.getTraSach()==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
//            ìmgDel=v.findViewById(R.id.imgDeleteLS);
        }
//        ìmgDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment.xoa(String.valueOf(item.getMaPM()));
//            }
//        });

        return v;
    }
}
